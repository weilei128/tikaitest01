package com.oo.system.service;


import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.domain.vo.RoleFilePermissionVo;
import com.oo.system.domain.vo.WdpQueryVo;

import java.util.List;

public interface ISysWdpService {
    /**
     * 根据用户id查询对应权限下的wdp文件
     *
     * @param userId userId
     * @return 查询出的wdp文件id及名称
     */
    public List<WdpQueryVo> selectWdpList(String userId);
    /**
     * 更新角色权限文件类型
     *
     * @param sysRoleDocMenuList sysRoleDocMenuList
     */
    public int updateRoleFilePermission(List<SysRoleDocMenu> sysRoleDocMenuList,int doc_type);


    /**
     * 查询角色权限文件类型
     *
     * @param sysRoleDocMenuList sysRoleDocMenuList
     */
    public RoleFilePermissionVo selectRoleFilePermission(Long role_id);

    /**
     * 查询角色权限文件类型
     *
     */
    public List<TreeSelect> selectSysRoleMenu(Long role_id);

    /**
     * 查询角色权限文件类型
     *
     */
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(SysDocMenuWdp sysDocMenuWdp);

    /**
     * 查询角色权限文件类型
     *
     * @param sysRoleDocMenuList sysRoleDocMenuList
     */
    public List<Integer> selectRoleFile(Long role_id,Long business_id);
}
