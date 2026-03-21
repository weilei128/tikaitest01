package com.pcitc.szgt.contract.util;

import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private OrganizationRequest orgRequest;

    public static class UserUnitDeptInfo{
        SysOrganization unit;
        SysOrganization dept;

        public SysOrganization getUnit() {
            return unit;
        }

        public void setUnit(SysOrganization unit) {
            this.unit = unit;
        }

        public SysOrganization getDept() {
            return dept;
        }

        public void setDept(SysOrganization dept) {
            this.dept = dept;
        }

        @Override
        public String toString() {
            if(unit != null && dept != null){
                return unit.getfName() + "/" + dept.getfName();
            } else if(unit != null){
                return unit.getfName();
            } else if(dept != null){
                return dept.getfName();
            } else {
                return "";
            }
        }
    }

    public UserUnitDeptInfo getOrgPath(Integer userId){
        SysUserinfo sysUserinfo = userInfoRequest.queryById(userId);

        List<Integer> orgIds = userInfoRequest.queryOrgs(String.valueOf(sysUserinfo.getfId()));
        List<SysOrganization> orgs = orgRequest.queryOrgByIdBatch(orgIds.toArray(new Integer[0]));

        UserUnitDeptInfo userUnitDeptInfo = new UserUnitDeptInfo();
        if(CollectionUtils.isEmpty(orgs)){
            return userUnitDeptInfo;
        }

        List<SysOrganization> depts = orgs.stream().filter(org -> ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
        SysOrganization dept = null;
        //没有部门, 选择单位
        if(CollectionUtils.isEmpty(depts)){
            userUnitDeptInfo.setUnit(orgs.get(0));
            return userUnitDeptInfo;
        }else{
            userUnitDeptInfo.setDept(depts.get(0));
        }

        if(userUnitDeptInfo.getDept().getFkParentId() != null){
            SysOrganization unit = orgRequest.queryOrganization(userUnitDeptInfo.getDept().getFkParentId());
            if(unit != null && unit.getFkParentId() != null && unit.getFkParentId() > 0){
                userUnitDeptInfo.setUnit(unit);
            }
        }

        return userUnitDeptInfo;
    }

}
