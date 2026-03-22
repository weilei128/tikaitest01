package com.oo.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.system.domain.SysRoleDocMenu;

/**
 * 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型Mapper接口
 * 
 * @author oo
 * @date 2023-07-21
 */
public interface SysRoleDocMenuMapper extends BaseMapper<SysRoleDocMenu>
{

    /**
     * 查询文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型列表
     * 
     * @param sysRoleDocMenu 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型
     * @return 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型集合
     */
    public List<SysRoleDocMenu> selectSysRoleDocMenuList(SysRoleDocMenu sysRoleDocMenu);

}
