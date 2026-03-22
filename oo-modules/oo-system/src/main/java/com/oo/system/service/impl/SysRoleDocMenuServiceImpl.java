package com.oo.system.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.system.mapper.SysRoleDocMenuMapper;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.service.ISysRoleDocMenuService;

/**
 * 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型Service业务层处理
 * 
 * @author oo
 * @date 2023-07-21
 */
@Service
public class SysRoleDocMenuServiceImpl extends ServiceImpl<SysRoleDocMenuMapper, SysRoleDocMenu> implements ISysRoleDocMenuService
{

    /**
     * 查询文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型列表
     * 
     * @param sysRoleDocMenu 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型
     * @return 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型
     */
    @Override
    public List<SysRoleDocMenu> selectSysRoleDocMenuList(SysRoleDocMenu sysRoleDocMenu)
    {
        return baseMapper.selectSysRoleDocMenuList(sysRoleDocMenu);
    }

}
