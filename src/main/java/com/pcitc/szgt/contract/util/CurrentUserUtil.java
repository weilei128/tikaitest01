package com.pcitc.szgt.contract.util;

import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.jackson.LocalDateTimeStamp;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.oauthconfig.WrapUser;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CurrentUserUtil {

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private OrganizationRequest orgRequest;

    @Deprecated
    public UserInfo currentUser() {
        WrapUser wrapUser = (WrapUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return wrapUser.getUserInfo();
    }

    public UserInfo currentUserInfo() {
        Object details = ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication()).
                getUserAuthentication().getDetails();

        SysUserinfo sysUserinfo = null;
        try {
            sysUserinfo = LocalDateTimeStamp.timeStampMapper.readValue((String) details, SysUserinfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("对象解析失败", 500);
        }
        
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUserinfo);

        List<SysOrganization> list = new ArrayList<>();
        List<Integer> orgIds = userInfoRequest.queryOrgs(String.valueOf(sysUserinfo.getfId()));
//      List<SysOrganization> orgs = orgRequest.queryOrgByIdBatch(orgIds.toArray(new Integer[0]));

        long startTimeBatch = System.currentTimeMillis() ;
        SysOrganization sysOrg = null ;
        for (Integer orgId : orgIds) {
        	sysOrg = orgRequest.queryOrganization(orgId);
        	if(sysOrg!=null) {
        		list.add(sysOrg);
        		log.info("-----方法=currentUserInfo查询组织机构ID={}",orgId);
        	}else {
        		log.info("-----方法=currentUserInfo查询组织机构ID={}=NULL",orgId);
        	}
		}
        long endTimeBatch = System.currentTimeMillis() ; 
        log.info("-----方法=currentUserInfo查询组织机构总耗时--> {} ms",(endTimeBatch - startTimeBatch));
        
        if(!CollectionUtils.isEmpty(list)){
            List<SysOrganization> depts = list.stream().filter(org -> ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
            //没有部门
            if(CollectionUtils.isEmpty(depts)){
                userInfo.setUnitId(list.get(0).getfId());
                userInfo.setUnitName(list.get(0).getfName());
                userInfo.setSysOrgList(list);
            }else{
                //设置单位
                List<SysOrganization> units = list.stream().filter(org -> !ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(units)){
                    userInfo.setUnitId(units.get(0).getfId());
                    userInfo.setUnitName(units.get(0).getfName());
                }else{
                    SysOrganization unit = orgRequest.getOrgCompany(depts.get(0).getfId());
                    if(unit != null){
                        userInfo.setUnitId(unit.getfId());
                        userInfo.setUnitName(unit.getfName());
//                      userInfo.setUnitName(orgRequest.getAbsPath(unit.getfId()));
                    }
                }
                userInfo.setSysOrgList(depts);
            }
        }

        //update  zhouziran 20200327 取用户所在单位/企业
//        SysOrganization sysOrganization = orgRequest.getOrgCompany(orgs.get(0).getfId());
//        if (sysOrganization != null) {
//            userInfo.setUnitId(sysOrganization.getfId());
//            userInfo.setUnitName(sysOrganization.getfName());
//        } else {
//            sysOrganization = orgRequest.queryTopOrg(orgIds.get(0));
//            userInfo.setUnitId(orgs.get(0).getfId());
//            userInfo.setUnitName(orgs.get(0).getfName());
//        }
//
//        List<String> orgCodes = orgs.stream().map(SysOrganization::getfCode).collect(Collectors.toList());
//        if(orgCodes.contains("20000137")){
//            orgs.sort(Comparator.comparing(SysOrganization::getfType, Comparator.reverseOrder()));
//        }else{
//            orgs.sort(Comparator.comparing(SysOrganization::getfType));
//        }
//
//        userInfo.setSysOrgList(orgs);
        return userInfo;
    }
    /**
     * 	根据用户ID查询组织结构
     * @param userId
     * @return
     */
    public List<SysOrganization> queryOrganizationById(String userId){
    	 UserInfo userInfo = new UserInfo();
    	 List<Integer> orgIds = userInfoRequest.queryOrgs(userId);
         List<SysOrganization> orgs = orgRequest.queryOrgByIdBatch(orgIds.toArray(new Integer[0]));
         List<SysOrganization> depts = null ;
         if(!CollectionUtils.isEmpty(orgs)){
             depts = orgs.stream().filter(org -> ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
             //没有部门
             if(CollectionUtils.isEmpty(depts)){
                 userInfo.setUnitId(orgs.get(0).getfId());
                 userInfo.setUnitName(orgs.get(0).getfName());
                 userInfo.setSysOrgList(orgs);
             }else{
                 //设置单位
                 List<SysOrganization> units = orgs.stream().filter(org -> !ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
                 if(!CollectionUtils.isEmpty(units)){
                     userInfo.setUnitId(units.get(0).getfId());
                     userInfo.setUnitName(units.get(0).getfName());
                 }else{
                     SysOrganization unit = orgRequest.getOrgCompany(depts.get(0).getfId());
                     if(unit != null){
                         userInfo.setUnitId(unit.getfId());
                         userInfo.setUnitName(unit.getfName());
                     }
                 }
                 userInfo.setSysOrgList(depts);
             }
         }
         return depts ;
    }
}
