package com.pcitc.szgt.contract.make.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import com.pcitc.szgt.contract.make.mapper.CrContractaccordoaotherMapper;
import com.pcitc.szgt.contract.make.modelEx.Accord;
import com.pcitc.szgt.contract.make.modelEx.AccordQuery;
import com.pcitc.szgt.contract.make.service.ICrContractaccordoaotherService;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.offeree.model.OffereeInfoVo;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 签约依据
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
@Service
public class CrContractaccordoaotherServiceImpl extends ServiceImpl<CrContractaccordoaotherMapper, CrContractaccordoaother> implements ICrContractaccordoaotherService {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private CrContractaccordoaotherMapper crContractaccordoaotherMapper;
    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Autowired
    private DictionaryRequest dictionaryRequest;
    @Autowired
    private OrganizationRequest organizationRequest;

    /*
     * 签约依据新增
     * */
    @Override
    public boolean addAccord(Accord accord) {
        if (accord.accordId == null || StringUtils.isEmpty(accord.accordId)) {
            throw new NotFoundException("签约依据ID必填！", Constants.FAILCODE);
        }
       /* if (accord.accordCode == null || StringUtils.isEmpty(accord.accordCode)) {
            throw new NotFoundException("签约依据编码必填！", Constants.FAILCODE);
        }*/
        if (accord.accordName == null || StringUtils.isEmpty(accord.accordName)) {
            throw new NotFoundException("签约依据名称必填！", Constants.FAILCODE);
        }
        if (accord.orgIds == null || accord.orgIds.length <= 0) {
            throw new NotFoundException("使用范围必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractaccordoaother> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractaccordoaother::getACode, accord.accordCode).eq(CrContractaccordoaother::getLogicDel, 0);
        List<CrContractaccordoaother> crContractAccordOaOtherList = list(queryWrapper);
        if (crContractAccordOaOtherList != null && crContractAccordOaOtherList.size() > 0) {
            /*  throw new NotFoundException("系统存在相同签约依据编码！", Constants.FAILCODE);*/
        }
        CrContractaccordoaother crContractAccordOaOther = new CrContractaccordoaother();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractAccordOaOther = new CrContractaccordoaother();
        crContractAccordOaOther.setAccordingID(accord.accordId);
        crContractAccordOaOther.setACode(accord.accordCode);//签约依据编码
        crContractAccordOaOther.setAccordingName(accord.accordName);//签约依据名称
        crContractAccordOaOther.setAccordingSource(accord.accordSource);//签约依据来源
        crContractAccordOaOther.setAccordingType(accord.accordType);//签约依据类型

        crContractAccordOaOther.setOrgID(String.join(",", accord.orgIds));//签约依据部门
        crContractAccordOaOther.setIsValid(accord.isVaild);//是否有效
        crContractAccordOaOther.setLogicDel(0);//是否删除
        crContractAccordOaOther.setRemark(accord.remark);
        crContractAccordOaOther.setUseCount(0);//应用次数
        crContractAccordOaOther.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContractAccordOaOther.setCreatedDate(LocalDateTime.now());
        crContractAccordOaOther.setOulabel(userInfo.getUnitId());
        boolean success = baseMapper.insert(crContractAccordOaOther) > 0 ? true : false;
        return success;
    }

    /*
     * 签约依据修改
     * */
    @Override
    public boolean updateAccord(Accord accord) {
        if (accord.accordId == null || StringUtils.isEmpty(accord.accordId)) {
            throw new NotFoundException("签约依据主键必填！", Constants.FAILCODE);
        }
       /* if (accord.accordCode == null || StringUtils.isEmpty(accord.accordCode)) {
            throw new NotFoundException("签约依据编码必填！", Constants.FAILCODE);
        }*/
        if (accord.accordName == null || StringUtils.isEmpty(accord.accordName)) {
            throw new NotFoundException("签约依据名称必填！", Constants.FAILCODE);
        }
        if (accord.orgIds == null || accord.orgIds.length <= 0) {
            throw new NotFoundException("使用范围必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractaccordoaother> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractaccordoaother::getACode, accord.accordCode).eq(CrContractaccordoaother::getLogicDel, 0)
                .ne(CrContractaccordoaother::getAccordingID, accord.accordId);

        List<CrContractaccordoaother> crContractAccordOaOtherList = list(queryWrapper);
        if (crContractAccordOaOtherList != null && crContractAccordOaOtherList.size() > 0) {
            /*  throw new NotFoundException("系统存在相同签约依据编码！", Constants.FAILCODE);*/
        }

        CrContractaccordoaother crContractAccordOaOther = baseMapper.selectById(accord.accordId);
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractAccordOaOther.setACode(accord.accordCode);//签约依据编码
        crContractAccordOaOther.setAccordingName(accord.accordName);//签约依据名称
        crContractAccordOaOther.setAccordingSource(accord.accordSource);//签约依据来源
        crContractAccordOaOther.setAccordingType(accord.accordType);//签约依据类型
        crContractAccordOaOther.setOrgID(String.join(",", accord.orgIds));//签约依据部门
        crContractAccordOaOther.setIsValid(accord.isVaild);//是否有效
        crContractAccordOaOther.setLogicDel(0);//是否删除
        crContractAccordOaOther.setRemark(accord.remark);
        crContractAccordOaOther.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractAccordOaOther.setModifiedDate(LocalDateTime.now());
        boolean success = baseMapper.updateById(crContractAccordOaOther) > 0 ? true : false;
        return success;
    }

    @Override
    /*
     * 签约依据删除
     * */
    public int delAccord(String Id) {
        if (StringUtils.isEmpty(Id)) {
            throw new NotFoundException("参数为空！", Constants.FAILCODE);
        }
        CrContractaccordoaother crContractaccordoaother = baseMapper.selectById(Id);
        if (crContractaccordoaother == null) {
            throw new NotFoundException("数据不存在！", Constants.FAILCODE);
        }
        if (crContractaccordoaother.getUseCount() > 0) {
            return 0;
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractaccordoaother.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));
        crContractaccordoaother.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractaccordoaother.setModifiedDate(LocalDateTime.now());
        if (baseMapper.updateById(crContractaccordoaother) > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    /*
     * 根据ID获取签约依据信息
     * */
    public DataResult getAccordById(String Id) {
        if (StringUtils.isEmpty(Id)) {
            throw new NotFoundException("参数为空！", Constants.FAILCODE);
        }
        CrContractaccordoaother crContractaccordoaother = baseMapper.selectById(Id);

        Map<String, Object> map = new HashMap<String, Object>();
        if (crContractaccordoaother != null) {
            map.put("accordId", crContractaccordoaother.getAccordingID());
            map.put("accordCode", crContractaccordoaother.getACode());
            map.put("accordName", crContractaccordoaother.getAccordingName());
            Integer accordType = 0;
            if (crContractaccordoaother.getAccordingType() != null) {
                accordType = crContractaccordoaother.getAccordingType();
            }
            map.put("accordType", accordType);
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(accordType);
            if (sysDictionarycategory != null) {
                map.put("accordTypeName", sysDictionarycategory.getfName());
            } else {
                map.put("accordTypeName", "");
            }
            Integer accordSource = 0;
            if (crContractaccordoaother.getAccordingSource() != null) {
                accordSource = crContractaccordoaother.getAccordingSource();
            }
            map.put("accordSource", accordSource);
            sysDictionarycategory = dictionaryRequest.queryCategoryById(accordSource);
            if (sysDictionarycategory != null) {
                map.put("accordSourceName", sysDictionarycategory.getfName());
            } else {
                map.put("accordSourceName", "");
            }
            List<Integer> orgIds = new ArrayList<>();
            List<String> orgNames = new ArrayList<>();
            if (!StringUtils.isEmpty(crContractaccordoaother.getOrgID())) {
                String[] orgs = crContractaccordoaother.getOrgID().split(",");
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
            map.put("remark", crContractaccordoaother.getRemark());
            map.put("isValid", crContractaccordoaother.getIsValid().toString());
        }
        return DataResult.success(map);
    }

    /*
     * 签约依据查询
     * */
    @Override
    public DataResult queryAccord(String accordCode, String accordName, Integer accordSource, Integer accordType,
                                  String createdBy, String userName, String orgId, Integer useCount, Integer pageNum, Integer pageSize, Integer isValid) {
        if (pageNum == null) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize == null) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractaccordoaother> queryWrapper = new QueryWrapper<>();
        if (accordCode != null && !StringUtils.isEmpty(accordCode)) {
            queryWrapper.like("ACode", accordCode.trim());
        }
        if (accordName != null && !StringUtils.isEmpty(accordName)) {
            queryWrapper.like("AccordingName", accordName.trim());
        }
        if (accordSource != null && accordSource > 0) {
            queryWrapper.eq("AccordingSource", accordSource);
        }
        if (accordType != null && accordType > 0) {
            queryWrapper.eq("AccordingType", accordType);
        }
        if (!StringUtils.isEmpty(createdBy)) {
            queryWrapper.eq("CreatedBy", createdBy);
        }

        if(!StringUtils.isEmpty(userName)){
            List<SysUserinfo> sysUserinfos = userInfoRequest.queryByName(userName);
            if(CollectionUtils.isEmpty(sysUserinfos)){
                PageData<Object> pageData = new PageData<>();
                pageData.setCurrentPage(pageNum);
                pageData.setPageSize(pageSize);
                pageData.setTotalCount(0);
                pageData.setTotalPage(0);
                pageData.setData(new ArrayList<>());
                return DataResult.success(pageData);
            }else{
                List<String> uids = sysUserinfos.stream().map(SysUserinfo::getfId).map(Object::toString).collect(Collectors.toList());
                queryWrapper.lambda().in(CrContractaccordoaother::getCreatedBy, uids);
            }
        }

        if (isValid != null) {
            queryWrapper.eq("IsValid", isValid);
        }

        if (orgId != null && !StringUtils.isEmpty(orgId)) {
            queryWrapper.like("OrgID", orgId);
        }
        if (useCount != null) {
            if (useCount == 0) {
                queryWrapper.eq("UseCount", 0);
            } else {
                queryWrapper.ge("UseCount", 1);
            }
        }

        queryWrapper.lambda().eq(CrContractaccordoaother::getLogicDel, ContractEnum.EnumIsLogicDel.Valid.getCode());
        //20200809 增加公开范围
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //当前用户所在组织机构
        List<SysOrganization> sysOrganizationList = userInfo.getSysOrgList();
        if(CollectionUtils.isEmpty(sysOrganizationList)){
            queryWrapper.lambda().apply("find_in_set({0}", -1);
        }else{
            List<Integer> orgIds = sysOrganizationList.stream().map(SysOrganization::getfId).collect(Collectors.toList());
            queryWrapper.lambda().and(i -> {
                for(int j = 0;j<orgIds.size();j++){
                    i.apply("find_in_set({0}, OrgID)", orgIds.get(j));
                    if(j < orgIds.size() - 1){
                        i.or();
                    }
                }
                return i;
            });
        }

        queryWrapper.orderByDesc("CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractaccordoaotherMapper.selectAccord(page, queryWrapper);
        for (HashMap map : list) {
            JSONObject obj = new JSONObject(true);
            obj.put("AccordingID", map.get("AccordingID"));//签约依据ID
            obj.put("AccordingName", map.get("AccordingName"));
            obj.put("ACode", map.get("ACode"));
            /* obj.put("OrgID", map.get("OrgID"));*/

            List<Integer> orgIds = new ArrayList<>();
            List<String> orgNames = new ArrayList<>();
            if (map.get("OrgID") != null && !StringUtils.isEmpty(map.get("OrgID"))) {
                String[] orgs = map.get("OrgID").toString().split(",");
                for (String org : orgs) {
                    SysOrganization sysOrganization = organizationRequest.queryOrganization(Integer.parseInt(org));
                    if (sysOrganization != null) {
                        orgIds.add(sysOrganization.getfId());
                        orgNames.add(sysOrganization.getfName());
                    }
                }
            }


            obj.put("orgIds", orgIds);
            obj.put("orgNames", orgNames);
            obj.put("AccordingType", map.get("AccordingType"));

            if (map.get("AccordingType") != null && !StringUtils.isEmpty(map.get("AccordingType"))) {
                SysDictionarycategory sysDictionarycategory = sysDictionarycategory = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("AccordingType").toString()));
                if (sysDictionarycategory != null) {
                    obj.put("AccordingTypeName", sysDictionarycategory.getfName());
                } else {
                    obj.put("AccordingTypeName", "");
                }
            } else {
                obj.put("AccordingTypeName", "");
            }


            obj.put("AccordingSource", map.get("AccordingSource"));
            if (map.get("AccordingSource") != null && !StringUtils.isEmpty(map.get("AccordingSource"))) {
                SysDictionarycategory sysDictionarycategory = sysDictionarycategory = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("AccordingSource").toString()));
                if (sysDictionarycategory != null) {
                    obj.put("AccordingSourceName", sysDictionarycategory.getfName());
                } else {
                    obj.put("AccordingSourceName", "");
                }
            } else {
                obj.put("AccordingSourceName", "");
            }

            obj.put("IsValid", map.get("IsValid"));
            obj.put("CreatedBy", map.get("CreatedBy"));
            if (map.get("CreatedBy") != null) {
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("CreatedBy").toString()));
                if (sysUserinfo != null) {
                    obj.put("userName", sysUserinfo.getfCname());
                }
            } else {
                obj.put("userName", "");
            }
            obj.put("CreatedDate", map.get("CreatedDate"));
            objectList.add(obj);
        }
       /* select a.AccordingID,a.AccordingName ,a.ACode,a.OrgID,a.AccordingType,'test' as AccordingTypeName,
        a.AccordingSource,'test' as AccordingSourceName,a.IsValid,a.CreatedBy,'test' as userName,a.Remark,
                a.CreatedDate from cr_contractaccordoaother  a
                */
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);

    }
}
