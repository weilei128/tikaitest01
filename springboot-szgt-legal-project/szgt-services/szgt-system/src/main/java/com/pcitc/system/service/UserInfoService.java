package com.pcitc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.entity.BaseEntity;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysRoleBo;
import com.pcitc.system.bo.SysUserInfoBo;
import com.pcitc.system.dbService.SysRoleService;
import com.pcitc.system.dbService.SysUserInfoService;
import com.pcitc.system.po.SysRole;
import com.pcitc.system.po.SysUserInfo;
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
@Service
public class UserInfoService {

    @Autowired
    private SysUserInfoService sysUserInfoService;


    public IPage<SysUserInfo> queryUserInfoList(BaseEntity baseEntity) {
        IPage<SysUserInfo> page = new Page<>(baseEntity.getCurrent(), baseEntity.getSize());
        return sysUserInfoService.page(page);
    }

    public void addUserInfo(SysUserInfoBo sysUserInfoBo) {
        if (sysUserInfoBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysUserInfo sysUserInfo = new SysUserInfo();
        BeanUtils.copyProperties(sysUserInfoBo, sysUserInfo);
        sysUserInfoService.save(sysUserInfo);
    }

    public void updateUser(SysUserInfoBo sysUserInfoBo) {
        if (sysUserInfoBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        if (sysUserInfoBo.getfId() == null || sysUserInfoBo.getfId() == 0) {
            throw new BaseException("主键id不存在", 500);
        }
        SysUserInfo sysUserInfo = sysUserInfoService.getById(sysUserInfoBo.getfId());
        if (sysUserInfo == null) {
            throw new BaseException("菜单不存在或者已经删除", 500);
        }
        SysUserInfo userInfo = new SysUserInfo();
        BeanUtils.copyProperties(sysUserInfoBo, userInfo);
        sysUserInfoService.updateById(userInfo);
    }

    public void deleteBatch(List<Integer> batchIds) {
        if (CollectionUtils.isEmpty(batchIds)) {
            return;
        }
        sysUserInfoService.removeByIds(batchIds);
    }

    public void updatePsw(String userId, String psw) {
        if (StringUtils.isEmpty(psw)) {
            throw new BaseException("密码不能为空", 500);
        }
        SysUserInfo userInfo = sysUserInfoService.getById(userId);
        if (userInfo == null) {
            throw new BaseException("用户不存在或者已经删除", 500);
        }
        userInfo.setfPassword(psw);
        sysUserInfoService.updateById(userInfo);
    }

}
