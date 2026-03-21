package com.pcitc.szgt.contract.make.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.finality.service.IFinalityService;
import com.pcitc.szgt.contract.make.entity.CrProjectinfo;
import com.pcitc.szgt.contract.make.mapper.CrProjectinfoMapper;
import com.pcitc.szgt.contract.make.modelEx.ProjectInfo;
import com.pcitc.szgt.contract.make.service.ICrProjectinfoService;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


/**
 * <p>
 * 项目信息管理
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
@Service
public class CrProjectinfoServiceImpl extends ServiceImpl<CrProjectinfoMapper, CrProjectinfo> implements ICrProjectinfoService {

    @Autowired
    private CrProjectinfoMapper crProjectinfoMapper;
    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;

    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private OrganizationRequest organizationRequest;
    @Autowired
    private DictionaryRequest dictionaryRequest;
    @Autowired
    private UserInfoRequest userInfoRequest;

    /*
     * 新增项目信息
     * */
    @Override
    public boolean addProject(ProjectInfo projectInfo) {
        if (StringUtils.isEmpty(projectInfo.projectId)) {
            throw new NotFoundException("主键必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.projectName)) {
            throw new NotFoundException("项目名称必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.projectCode)) {
            throw new NotFoundException("项目编号必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.execOrgan)) {
            throw new NotFoundException("建设单位必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.atYear)) {
            throw new NotFoundException("立项年度必填！", Constants.FAILCODE);
        }
        if (projectInfo.investAmount == null) {
            throw new NotFoundException("项目投资金额！", Constants.FAILCODE);
        }
        if (projectInfo.isValid != 0 && projectInfo.isValid != 1) {
            throw new NotFoundException("是否有效取值范围（0，1）！", Constants.FAILCODE);
        }
        if (projectInfo.source != 0 && projectInfo.source != 1) {
            throw new NotFoundException("数据来源取值范围（0，1）！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrProjectinfo crProjectinfo = new CrProjectinfo();
        crProjectinfo.setProjectID(projectInfo.projectId);
        crProjectinfo.setProjectCode(projectInfo.projectCode);
        crProjectinfo.setProjectName(projectInfo.projectName);
        crProjectinfo.setExecOrgan(projectInfo.execOrgan);
        crProjectinfo.setAtYear(projectInfo.atYear);
        crProjectinfo.setInvestAmount(projectInfo.investAmount);
        crProjectinfo.setCurrency(projectInfo.currency);
        crProjectinfo.setSource(projectInfo.source);
        String orgIds = "";
        Integer[] orgId = projectInfo.orgID;
        if (orgId != null && orgId.length > 0) {
            for (Integer org : orgId) {
                orgIds += org + ",";
            }
        }
        if (!StringUtils.isEmpty(orgIds)) {
            orgIds = orgIds.substring(0, orgIds.length() - 1);
        }
        crProjectinfo.setOrgID(orgIds);
        crProjectinfo.setFactory(projectInfo.factory);
        crProjectinfo.setResponsibleperson(projectInfo.responsibleperson);
        crProjectinfo.setResponsiblepersoncode(projectInfo.responsiblepersoncode);
        crProjectinfo.setCompanycode(projectInfo.companycode);
        crProjectinfo.setIsValid(projectInfo.isValid);
        crProjectinfo.setRemark(projectInfo.Remark);
        crProjectinfo.setLogicDel(0);
        crProjectinfo.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crProjectinfo.setCreatedDate(LocalDateTime.now());
        crProjectinfo.setOulabel(userInfo.getUnitId());
        return baseMapper.insert(crProjectinfo) > 0 ? true : false;
    }

    /*
     * 修改项目信息
     * */
    @Override
    public boolean updateProject(ProjectInfo projectInfo) {
        if (StringUtils.isEmpty(projectInfo.projectId)) {
            throw new NotFoundException("主键必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.projectName)) {
            throw new NotFoundException("项目名称必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.projectCode)) {
            throw new NotFoundException("项目编号必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.execOrgan)) {
            throw new NotFoundException("建设单位必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(projectInfo.atYear)) {
            throw new NotFoundException("立项年度必填！", Constants.FAILCODE);
        }
        if (projectInfo.investAmount == null) {
            throw new NotFoundException("项目投资金额！", Constants.FAILCODE);
        }
        if (projectInfo.isValid != 0 && projectInfo.isValid != 1) {
            throw new NotFoundException("是否有效取值范围（0，1）！", Constants.FAILCODE);
        }
        if (projectInfo.source != 0 && projectInfo.source != 1) {
            throw new NotFoundException("数据来源取值范围（0，1）！", Constants.FAILCODE);
        }
        CrProjectinfo crProjectinfo = new CrProjectinfo();
        crProjectinfo = baseMapper.selectById(projectInfo.projectId);
        if (crProjectinfo == null) {
            throw new NotFoundException("数据不存在！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crProjectinfo.setProjectCode(projectInfo.projectCode);
        crProjectinfo.setProjectName(projectInfo.projectName);
        crProjectinfo.setExecOrgan(projectInfo.execOrgan);
        crProjectinfo.setAtYear(projectInfo.atYear);
        crProjectinfo.setInvestAmount(projectInfo.investAmount);
        crProjectinfo.setCurrency(projectInfo.currency);
        crProjectinfo.setSource(projectInfo.source);
        String orgIds = "";
        Integer[] orgId = projectInfo.orgID;
        if (orgId != null && orgId.length > 0) {
            for (Integer org : orgId) {
                orgIds += org + ",";
            }
        }
        if (!StringUtils.isEmpty(orgIds)) {
            orgIds = orgIds.substring(0, orgIds.length() - 1);
        }
        crProjectinfo.setOrgID(orgIds);
        //crProjectinfo.setOrgID(String.join(",", projectInfo.orgID));
        crProjectinfo.setFactory(projectInfo.factory);
        crProjectinfo.setResponsibleperson(projectInfo.responsibleperson);
        crProjectinfo.setResponsiblepersoncode(projectInfo.responsiblepersoncode);
        crProjectinfo.setCompanycode(projectInfo.companycode);
        crProjectinfo.setIsValid(projectInfo.isValid);
        crProjectinfo.setRemark(projectInfo.Remark);
        crProjectinfo.setLogicDel(0);
        crProjectinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crProjectinfo.setModifiedDate(LocalDateTime.now());
        return baseMapper.updateById(crProjectinfo) > 0 ? true : false;
    }

    /*
     * 项目信息删除
     * */
    @Override
    public boolean delProject(String projectId) {
        if (StringUtils.isEmpty(projectId)) {
            throw new NotFoundException("主键必填！", Constants.FAILCODE);
        }
        CrProjectinfo crProjectinfo = baseMapper.selectById(projectId);
        if (crProjectinfo == null) {
            throw new NotFoundException("数据存在！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crProjectinfo.setLogicDel(1);
        crProjectinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crProjectinfo.setModifiedDate(LocalDateTime.now());
        return baseMapper.updateById(crProjectinfo) > 0 ? true : false;
    }

    /*
     * 根据ID获取项目详细信息
     * */
    @Override
    public DataResult getProjectInfoById(String projectId) {
        if (StringUtils.isEmpty(projectId)) {
            throw new NotFoundException("主键必填！", Constants.FAILCODE);
        }
        CrProjectinfo crProjectinfo = baseMapper.selectById(projectId);
        if (crProjectinfo == null) {
            throw new NotFoundException("数据不存在！", Constants.FAILCODE);
        }
     /*   ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.projectId = crProjectinfo.getProjectID();
        projectInfo.projectName = crProjectinfo.getProjectName();
        projectInfo.projectCode = crProjectinfo.getProjectCode();
        projectInfo.execOrgan = crProjectinfo.getExecOrgan();
        projectInfo.atYear = crProjectinfo.getAtYear();
        projectInfo.investAmount = crProjectinfo.getInvestAmount();
        projectInfo.currency = crProjectinfo.getCurrency();
        projectInfo.source = crProjectinfo.getSource();
        projectInfo.orgID = crProjectinfo.getOrgID();
        projectInfo.Remark = crProjectinfo.getRemark();
        projectInfo.factory = crProjectinfo.getFactory();
        projectInfo.responsibleperson = crProjectinfo.getResponsibleperson();
        projectInfo.responsiblepersoncode = crProjectinfo.getResponsiblepersoncode();
        projectInfo.companycode = crProjectinfo.getCompanycode();
        return DataResult.success(projectInfo);*/


        Map<String, Object> map = new HashMap<String, Object>();
        if (crProjectinfo != null) {
            map.put("projectId", crProjectinfo.getProjectID());
            map.put("projectName", crProjectinfo.getProjectName());
            map.put("projectCode", crProjectinfo.getProjectCode());
            map.put("execOrgan", crProjectinfo.getExecOrgan());
            map.put("atYear", crProjectinfo.getAtYear());
            map.put("investAmount", crProjectinfo.getInvestAmount());
            map.put("currency", crProjectinfo.getCurrency());
            map.put("source", crProjectinfo.getSource());
            if (crProjectinfo.getSource() == 0) {
                map.put("sourceName", "手工");
            } else if (crProjectinfo.getSource() == 1) {
                map.put("sourceName", "ERP");
            } else {
                map.put("sourceName", "");
            }
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crProjectinfo.getCurrency());
            if (sysDictionarycategory != null) {
                map.put("currencyName", sysDictionarycategory.getfName());
            } else {
                map.put("currencyName", "");
            }
            List<Integer> orgIds = new ArrayList<>();
            List<String> orgNames = new ArrayList<>();
            if (!crProjectinfo.getOrgID().equals("null") && !StringUtils.isEmpty(crProjectinfo.getOrgID())) {
                String[] orgs = crProjectinfo.getOrgID().split(",");
                for (String orgId : orgs) {
                    SysOrganization sysOrganization = organizationRequest.queryOrganization(Integer.parseInt(orgId));
                    if (sysOrganization != null) {
                        orgIds.add(sysOrganization.getfId());
                        orgNames.add(sysOrganization.getfName());
                    }
                }
            }
            map.put("orgIds", orgIds);
            map.put("orgNames", orgNames);
            map.put("remark", crProjectinfo.getRemark());
            map.put("isValid", crProjectinfo.getIsValid());
            map.put("factory", crProjectinfo.getFactory());
            map.put("responsibleperson", crProjectinfo.getResponsibleperson());
            map.put("responsiblepersoncode", crProjectinfo.getResponsiblepersoncode());
            map.put("companycode", crProjectinfo.getCompanycode());


            map.put("createdBy", crProjectinfo.getCreatedBy());
            String createdByName = "";
            if (!StringUtils.isEmpty(crProjectinfo.getCreatedBy())) {
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crProjectinfo.getCreatedBy()));
                if (sysUserinfo != null) {
                    createdByName = sysUserinfo.getfCname();
                }

            }
            map.put("createdByName", createdByName);
            map.put("createdDate", crProjectinfo.getCreatedDate());
            map.put("isValid", crProjectinfo.getIsValid());
        }
        return DataResult.success(map);
    }

    /*
     *查询
     * */
    @Override
    public DataResult selectProjectInfo(String projectName, String atYear, Integer pageNum, Integer pageSize, Integer isValid) {
        if (pageNum == null) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize == null) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrProjectinfo> queryWrapper = new QueryWrapper<>();
        if (projectName != null && !StringUtils.isEmpty(projectName)) {
            queryWrapper.lambda().like(CrProjectinfo::getProjectName, projectName);
        }
        if (atYear != null && !StringUtils.isEmpty(atYear)) {
            queryWrapper.lambda().eq(CrProjectinfo::getAtYear, atYear);
        }
        if(isValid != null){
            queryWrapper.lambda().eq(CrProjectinfo::getIsValid, isValid);
        }
        queryWrapper.lambda().eq(CrProjectinfo::getLogicDel, 0);
        queryWrapper.lambda().orderByDesc(CrProjectinfo::getCreatedDate);
        Page<CrProjectinfo> page = new Page<CrProjectinfo>(pageNum, pageSize);
        IPage<CrProjectinfo> crProjectInfo = crProjectinfoMapper.selectPage(page, queryWrapper);
        List<ProjectInfo> projectInfos = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        for (CrProjectinfo crProjectinfo : crProjectInfo.getRecords()) {
          /*  ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.projectId = crProjectinfo.getProjectID();
            projectInfo.projectName = crProjectinfo.getProjectName();
            projectInfo.projectCode = crProjectinfo.getProjectCode();
            projectInfo.execOrgan = crProjectinfo.getExecOrgan();
            projectInfo.atYear = crProjectinfo.getAtYear();
            projectInfo.investAmount = crProjectinfo.getInvestAmount();
            projectInfo.currency = crProjectinfo.getCurrency();
            projectInfo.source = crProjectinfo.getSource();
            projectInfo.orgID = crProjectinfo.getOrgID();
            projectInfo.Remark = crProjectinfo.getRemark();
            projectInfo.factory = crProjectinfo.getFactory();
            projectInfo.responsibleperson = crProjectinfo.getResponsibleperson();
            projectInfo.responsiblepersoncode = crProjectinfo.getResponsiblepersoncode();
            projectInfo.companycode = crProjectinfo.getCompanycode();
            projectInfo.createDate = crProjectinfo.getCreatedDate().toString();
            projectInfos.add(projectInfo);*/

            Map<String, Object> map = new HashMap<String, Object>();
            if (crProjectinfo != null) {
                map.put("projectId", crProjectinfo.getProjectID());
                map.put("projectName", crProjectinfo.getProjectName());
                map.put("projectCode", crProjectinfo.getProjectCode());
                map.put("execOrgan", crProjectinfo.getExecOrgan());
                map.put("atYear", crProjectinfo.getAtYear());
                map.put("investAmount", crProjectinfo.getInvestAmount());
                map.put("currency", crProjectinfo.getCurrency());
                map.put("source", crProjectinfo.getSource());
                if (crProjectinfo.getSource() == 0) {
                    map.put("sourceName", "手工");
                } else if (crProjectinfo.getSource() == 1) {
                    map.put("sourceName", "ERP");
                } else {
                    map.put("sourceName", "");
                }
                SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crProjectinfo.getCurrency());
                if (sysDictionarycategory != null) {
                    map.put("currencyName", sysDictionarycategory.getfName());
                } else {
                    map.put("currencyName", "");
                }
                List<Integer> orgIds = new ArrayList<>();
                List<String> orgNames = new ArrayList<>();
                if (!StringUtils.isEmpty(crProjectinfo.getOrgID())) {
                    if (!crProjectinfo.getOrgID().equals("null") && !StringUtils.isEmpty(crProjectinfo.getOrgID())) {
                        String[] orgs = crProjectinfo.getOrgID().split(",");
                        if (orgs != null && orgs.length > 0) {
                            for (String orgId : orgs) {
                                if (orgId != null && !StringUtils.isEmpty(orgId)) {
                                    SysOrganization sysOrganization = organizationRequest.queryOrganization(Integer.parseInt(orgId));
                                    if (sysOrganization != null) {
                                        orgIds.add(sysOrganization.getfId());
                                        orgNames.add(sysOrganization.getfName());
                                    }
                                }
                            }
                        }
                    }


                }
                map.put("orgIds", orgIds);
                map.put("orgNames", orgNames);
                map.put("remark", crProjectinfo.getRemark());
                map.put("isValid", crProjectinfo.getIsValid());
                String createdByName = "";
                if (!StringUtils.isEmpty(crProjectinfo.getCreatedBy())) {
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crProjectinfo.getCreatedBy()));
                    if (sysUserinfo != null) {
                        createdByName = sysUserinfo.getfCname();
                    }
                }
                map.put("createdByName", createdByName);
                map.put("createdDate", crProjectinfo.getCreatedDate());
                objectList.add(map);
            }
        }

        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }
}
