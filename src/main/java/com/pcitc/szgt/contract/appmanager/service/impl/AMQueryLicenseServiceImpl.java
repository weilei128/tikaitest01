package com.pcitc.szgt.contract.appmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.appmanager.entity.AmQuerylicense;
import com.pcitc.szgt.contract.appmanager.mapper.AmQuerylicenseMapper;
import com.pcitc.szgt.contract.appmanager.model.*;
import com.pcitc.szgt.contract.appmanager.service.AMQueryLicenseService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 查询授权管理功能
 * */
@Service
public class AMQueryLicenseServiceImpl implements AMQueryLicenseService {

    @Autowired
    private AmQuerylicenseMapper amQueryLicenseMapper;

    @Autowired
    private OrganizationRequest orgRequest;

    @Autowired
    private UserInfoRequest userRequest;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    /*
     * 设置查询授权
     * */
    @Transactional
    @Override
    public boolean setAMQueryLicense(AmQueryLicenseSetVo amQueryLicenseSetVo) {

        List<Integer> userIds = amQueryLicenseSetVo.getUserIds();
        List<Integer> orgIds = amQueryLicenseSetVo.getOrgIds();

        if (CollectionUtils.isEmpty(userIds)) {
            throw new BaseException("授权用户必填", 500);
        }
        if (CollectionUtils.isEmpty(orgIds)) {
            throw new BaseException("授权组织机构必填", 500);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        for (Integer userId : userIds) {
            SysUserinfo sysUserinfo = userRequest.queryById(userId);
            if (sysUserinfo == null) {
                throw new BaseException("用户【" + userId + "】系统不存在", 500);
            }

            QueryWrapper<AmQuerylicense> aqlQueryWrapper = new QueryWrapper<>();
            aqlQueryWrapper.lambda().eq(AmQuerylicense::getUserID, userId);
            List<AmQuerylicense> amQuerylicenses = amQueryLicenseMapper.selectList(aqlQueryWrapper);

            //先删除，再新增
            int delete = amQueryLicenseMapper.delete(aqlQueryWrapper);

            for (Integer orgId : orgIds) {
                SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);
                if (sysOrganization == null) {
                    throw new BaseException("组织机构【" + orgId + "】系统不存在", 500);
                }

                AmQuerylicense amQueryLicense = new AmQuerylicense();
                amQueryLicense.setQueryLicenseID(UUIDUtils.getUUID());
                amQueryLicense.setUserID(userId.toString());
                amQueryLicense.setOrgID(orgId);
                amQueryLicense.setOrgCode(sysOrganization.getfCode());
                amQueryLicense.setOulabel(userInfo.getUnitId());
                amQueryLicense.setCreatedBy(userInfo.getSysUser().getfId().toString());
                amQueryLicense.setCreatedDate(LocalDateTime.now());
                amQueryLicenseMapper.insert(amQueryLicense);
            }
        }
        return true;
    }

    /*
     * 查询授权管理
     * */
    @Override
    public DataResult<PageData<AmQueryLicenseResultVo>> queryAMQueryLicense(AmQueryLicenseQueryVo queryVo) {
        List<Object> list = new ArrayList<>();
        if (queryVo.getPageNum() == null || queryVo.getPageNum() <= 0) {
            queryVo.setPageNum(1);
        }
        if (queryVo.getPageSize() == null || queryVo.getPageSize() <= 0) {
            queryVo.setPageSize(10);
        }

        Page<String> page = new Page<String>(queryVo.getPageNum(), queryVo.getPageSize());
        PageData<AmQueryLicenseResultVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(Collections.emptyList());

        AmQueryLicenseQueryBo queryBo = new AmQueryLicenseQueryBo();

        // 组织机构范围
        if (queryVo.getOrgId() != null) {
            SysOrganization sysOrganization = orgRequest.queryOrganization(queryVo.getOrgId());
            if (sysOrganization == null) {
                throw new BaseException("组织机构【" + queryVo.getOrgId() + "】系统不存在", 500);
            }

            List<SysOrganization> subOrgs = orgRequest.queryAllSubOrgs(queryVo.getOrgId());
            subOrgs.add(0, sysOrganization);
            List<Integer> orgIds = subOrgs.stream().map(SysOrganization::getfId).collect(Collectors.toList());
            queryBo.setOrgIds(orgIds);
        }

        // 根据用户账号或名称条件确定范围
        if (!StringUtils.isEmpty(queryVo.getUserName())) {
            List<SysUserinfo> sysUserinfos = userRequest.queryByName(queryVo.getUserName());
            if (CollectionUtils.isEmpty(sysUserinfos)) {
                return DataResult.success(pageData);
            }
            List<String> userIds = sysUserinfos.stream().map(SysUserinfo::getfId).map(Object::toString).collect(Collectors.toList());
            Map<String, String> userMap = sysUserinfos.stream().collect(Collectors.<SysUserinfo, String, String>toMap(i -> i.getfId().toString(), SysUserinfo::getfCname, (k1, k2) -> k2));
            queryBo.setUserIds(userIds);
        }

        List<AmQueryLicenseResultBo> resultBos = amQueryLicenseMapper.queryAMQueryLicense(page, queryBo);
        if (CollectionUtils.isEmpty(resultBos)) {
            return DataResult.success(pageData);
        }

        // 用户-授权机构
        Map<String, List<Integer>> userOrgAuth = new HashMap<String, List<Integer>>();
        for (AmQueryLicenseResultBo bo : resultBos) {
            userOrgAuth.put(bo.getUserID(), Arrays.stream(bo.getOrgIDs().split(",")).
                    map(Integer::valueOf).collect(Collectors.toList()));
        }

        //所有用户id
        List<Integer> userIDs = resultBos.stream().map(AmQueryLicenseResultBo::getUserID).map(Integer::valueOf).collect(Collectors.toList());
        //用户-组织机构
        Map<String, List<Integer>> userOrgBelong = new HashMap<String, List<Integer>>();
        for (Integer i : userIDs) {
            List<Integer> integers = userRequest.queryOrgs(String.valueOf(i));
            userOrgBelong.put(String.valueOf(i), integers);
        }

        Set<Integer> allOrg = new HashSet<>();
        for (List<Integer> val : userOrgAuth.values()) {
            allOrg.addAll(val);
        }
        for (List<Integer> val : userOrgBelong.values()) {
            allOrg.addAll(val);
        }

        //查出所有组织机构
        List<SysOrganization> sysOrganizations = orgRequest.queryOrgByIdBatch(allOrg.toArray(new Integer[0]));
        Map<Integer, SysOrganization> orgMap = sysOrganizations.stream().collect(
                Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k2));

        //所有用户信息
        List<SysUserinfo> sysUserinfos = userRequest.queryByIdBatch(userIDs.toArray(new Integer[0]));

        List<AmQueryLicenseResultVo> results = new ArrayList<>();
        for (SysUserinfo userInfo : sysUserinfos) {
            AmQueryLicenseResultVo resultVo = new AmQueryLicenseResultVo();
            resultVo.setUserId(userInfo.getfId());
            resultVo.setUserName(userInfo.getfCname());

            List<Integer> authOrgs = userOrgAuth.get(String.valueOf(userInfo.getfId()));
            StringBuilder orgAdminName = new StringBuilder();
            for (Integer orgId : authOrgs) {
                if (orgMap.get(orgId) != null) {
                    orgAdminName.append(orgMap.get(orgId).getfName());
                    orgAdminName.append(";");
                }
            }
            resultVo.setOrgAdminName(orgAdminName.substring(0, orgAdminName.length() - 1));

            List<Integer> belongOrgs = userOrgBelong.get(String.valueOf(userInfo.getfId()));
            StringBuilder userOrg = new StringBuilder();
            for (Integer orgId : belongOrgs) {
                if (orgMap.get(orgId) != null) {
                    userOrg.append(orgMap.get(orgId).getfName());
                    userOrg.append(";");
                }
            }
            resultVo.setUserOrg(userOrg.substring(0, userOrg.length() - 1));

            results.add(resultVo);
        }

        pageData.setData(results);

        return DataResult.success(pageData);
    }

    /*
     * 根据用户删除查询授权数据
     * */
    @Override
    @Transactional
    public boolean delAMQueryLicense(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException("用户账号必填", 500);
        }

        SysUserinfo sysUserinfo = userRequest.queryById(Integer.valueOf(userId));
        if (sysUserinfo == null) {
            throw new BaseException("系统不存在账号【" + userId + "】", 500);
        }

        QueryWrapper<AmQuerylicense> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmQuerylicense::getUserID, userId);
        amQueryLicenseMapper.delete(queryWrapper);

        return true;
    }

    /*
     * 根据用户ID获取查询授权数据
     * */
    @Override
    public DataResult<List<AmQueryLicenseResultVo>> getAMQueryLicenseById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException("用户账号必填", 500);
        }

        SysUserinfo sysUserinfo = userRequest.queryById(Integer.valueOf(userId));
        if (sysUserinfo == null) {
            throw new BaseException("用户不存在", 500);
        }

        QueryWrapper<AmQuerylicense> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmQuerylicense::getUserID, userId);
        List<AmQuerylicense> amQuerylicenses = amQueryLicenseMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(amQuerylicenses)) {
            return DataResult.success(Collections.emptyList());
        }

        List<Integer> orgIds = amQuerylicenses.stream().map(AmQuerylicense::getOrgID).collect(Collectors.toList());
        List<SysOrganization> orgs = orgRequest.queryOrgByIdBatch(orgIds.toArray(new Integer[0]));
        Map<Integer, SysOrganization> orgMap = orgs.stream().collect(
                Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k2));

        List<AmQueryLicenseResultVo> results = new ArrayList<>();
        if (!CollectionUtils.isEmpty(amQuerylicenses)) {
            for (AmQuerylicense amQuerylicense : amQuerylicenses) {
                AmQueryLicenseResultVo resultVo = new AmQueryLicenseResultVo();
                resultVo.setUserId(Integer.valueOf(amQuerylicense.getUserID()));
                resultVo.setUserName(sysUserinfo.getfCname());
                resultVo.setOrgAdminId(amQuerylicense.getOrgID().toString());
                resultVo.setOrgAdminName(orgMap.get(amQuerylicense.getOrgID()).getfName());
                results.add(resultVo);
            }
        }

        return DataResult.success(results);
    }
}
