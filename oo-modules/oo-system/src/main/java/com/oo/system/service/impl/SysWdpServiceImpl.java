package com.oo.system.service.impl;

import com.oo.common.core.web.domain.SysMenu;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.domain.vo.RoleFilePermissionVo;
import com.oo.system.domain.vo.WdpQueryVo;
import com.oo.system.mapper.SysWdpMapper;
import com.oo.system.service.ISysMenuService;
import com.oo.system.service.ISysWdpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysWdpServiceImpl implements ISysWdpService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysWdpMapper sysWdpMapper;
    @Autowired
    private ISysMenuService iSysMenuService;

    @Override
    public List<WdpQueryVo> selectWdpList(String userId){
        return sysWdpMapper.selectWdpList(userId);
    }
    @Override
    public int updateRoleFilePermission(List<SysRoleDocMenu> sysRoleDocMenuList,int doc_type){
        sysWdpMapper.delete(sysRoleDocMenuList.get(0).getRoleId());
        int a=sysWdpMapper.insert_business_id(sysRoleDocMenuList);
        int b=sysWdpMapper.updateSysRoles(sysRoleDocMenuList.get(0).getRoleId(),doc_type);
        return a;
    }

    @Override
    public RoleFilePermissionVo selectRoleFilePermission(Long role_id){
//        int a=sysWdpMapper.selectSysRoles(role_id);
//        int a=0;
//        if(1==a){//0：全部可见
//            List<SysMenu> sysMenuList=sysWdpMapper.selectSysDocMenuBusiness(role_id);
//            return iSysMenuService.buildMenuTreeSelect(sysMenuList);
//
//        }else{//1部分可见，查询RoleDocMenu和DocMenuBusiness
//            List<SysMenu> sysRoleDocMenuList=sysWdpMapper.selectSysRoleDocMenu(role_id);
//            return iSysMenuService.buildMenuTreeSelect(sysRoleDocMenuList);
//        }

            List<SysMenu> sysRoleDocMenuList1=sysWdpMapper.selectSysDocMenuBusiness(role_id);
            List<SysMenu> sysRoleDocMenuList2=sysWdpMapper.selectSysRoleDocMenu2(role_id);

        for(SysMenu sysMenu:sysRoleDocMenuList1){
            sysMenu.setType(0);
            for(SysMenu sysMenu1:sysRoleDocMenuList2){
                    if(sysMenu1.getParentId().equals(sysMenu.getMenuId())){
                        sysMenu.setCheck(1);
                    }else{
                        sysMenu.setCheck(0);
                    }
                }
            }

        List<Integer> defaultSelectKeys=sysWdpMapper.selectSysRoleDocMenu3(role_id);
        RoleFilePermissionVo roleFilePermissionVo=new RoleFilePermissionVo();
        roleFilePermissionVo.setList(iSysMenuService.buildMenuTreeSelect(sysRoleDocMenuList1));
        roleFilePermissionVo.setDefaultSelectKeys(defaultSelectKeys);


//            List<SysMenu> sysRoleDocMenuList2=sysWdpMapper.selectSysRoleDocMenu2(role_id);
//            sysRoleDocMenuList1.addAll(sysRoleDocMenuList2);

            return roleFilePermissionVo;
    }

    @Override
    public List<TreeSelect> selectSysRoleMenu(Long role_id){
        List<SysMenu> sysMenuList=sysWdpMapper.selectSysRoleMenu(role_id);
        return iSysMenuService.buildMenuTreeSelect(sysMenuList);


    }

    @Override
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(SysDocMenuWdp sysDocMenuWdp){

        List<SysDocMenuBusiness> sysDocMenuBusinessList=sysWdpMapper.selectDocMenuBusinessList(sysDocMenuWdp.getWdpName(),sysDocMenuWdp.getParentId(),sysDocMenuWdp.getOrderNum());
        return sysDocMenuBusinessList;
    }


    @Override
    public List<Integer> selectRoleFile(Long role_id,Long business_id){
        return(sysWdpMapper.selectRoleFile(role_id,business_id));
    }
}
