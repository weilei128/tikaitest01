package com.pcitc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.entity.BaseEntity;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysRoleBo;
import com.pcitc.system.dbService.SysRoleService;
import com.pcitc.system.po.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @description 角色管理
 * @author leigang
 * @date 2020年2月19日 11:34:43
 *
 */
@Slf4j
@Service
public class RoleService {

    @Autowired
    private SysRoleService sysRoleService;

    public IPage<SysRole> queryRoleList(BaseEntity baseEntity) {
        IPage<SysRole> page = new Page<>(baseEntity.getCurrent(), baseEntity.getSize());
        return sysRoleService.page(page);
    }

    public void addRole(SysRoleBo sysRoleBo) {
        if (sysRoleBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleBo, sysRole);
        sysRoleService.save(sysRole);
    }

    public void updateRole(SysRoleBo sysRoleBo) {
        if (sysRoleBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        if (sysRoleBo.getfId() == null || sysRoleBo.getfId() == 0) {
            throw new BaseException("主键id不存在", 500);
        }
        SysRole sysRole = sysRoleService.getById(sysRoleBo.getfId());
        if (sysRole == null) {
            throw new BaseException("菜单不存在或者已经删除", 500);
        }
        SysRole role = new SysRole();
        BeanUtils.copyProperties(sysRole, role);
        sysRoleService.updateById(role);
    }

    public void deleteBatch(List<Integer> batchIds) {
        if (CollectionUtils.isEmpty(batchIds)) {
            return;
        }
        sysRoleService.removeByIds(batchIds);
    }

}
