package com.pcitc.system.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysMenuBo;
import com.pcitc.system.bo.SysMenuGroupBo;
import com.pcitc.system.dbService.SysMenuGroupService;
import com.pcitc.system.dbService.SysMenuService;
import com.pcitc.system.po.SysMenu;
import com.pcitc.system.po.SysMenuGroup;
import com.pcitc.system.po.SysOrganization;
import com.pcitc.system.vo.SysMenuGroupVo;
import com.pcitc.system.vo.SysMenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @description 菜单管理服务
 * @author leigang
 * @date 2020年2月19日 09:40:25
 *
 */
@Service
@Slf4j
public class MenuService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuGroupService sysMenuGroupService;


    public void addMenuNote(SysMenuBo sysMenuBo) {
        if (sysMenuBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuBo, sysMenu);
        sysMenuService.save(sysMenu);
    }

    public void addMenuGroupNote(SysMenuGroupBo sysMenuGroupBo) {
        if (sysMenuGroupBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysMenuGroup sysMenuGroup = new SysMenuGroup();
        BeanUtils.copyProperties(sysMenuGroupBo, sysMenuGroup);
        sysMenuGroupService.save(sysMenuGroup);
    }

    public void updateMenuNote(SysMenuBo sysMenuBo) {
        if (sysMenuBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        if (sysMenuBo.getfId() == null || sysMenuBo.getfId() == 0) {
            throw new BaseException("主键id不存在", 500);
        }
        SysMenu sysMenu = sysMenuService.getById(sysMenuBo.getfId());
        if (sysMenu == null) {
            throw new BaseException("菜单不存在或者已经删除", 500);
        }
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(sysMenuBo, menu);
        sysMenuService.updateById(menu);
    }

    public void deleteMenuNote(Integer noteId) {
        SysMenu sysMenu = sysMenuService.getById(noteId);
        if (sysMenu == null) {
            throw new BaseException("菜单不存在", 500);
        }
        List<Integer> idList = new ArrayList<>();
        loopDelete(noteId, idList);
        log.info(JSON.toJSONString(idList));
        //删除子节点
        sysMenuService.removeByIds(idList);
    }


    /**
     * 轮询 查找子节点 数据
     *
     * @param id
     * @param idList
     */
    private void loopDelete(Integer id, List<Integer> idList) {
        idList.add(id);
        LambdaQueryWrapper<SysMenu> wrapper =
                sysMenuService
                        .lambdaQueryWrapper().eq(SysMenu::getFkParentId, id);
        List<SysMenu> sysMenuList = sysMenuService.list(wrapper);
        if (!CollectionUtils.isEmpty(sysMenuList)) {
            List<Integer> integerList =
                    sysMenuList.stream().map(SysMenu::getfId).collect(Collectors.toList());
            integerList.forEach(fId -> loopDelete(fId, idList));
        }
    }


    /***
     * 查询菜单组下面的权限
     * @return
     */
    public List<SysMenuGroupVo> queryMenuTree() {
        List<SysMenuGroup> menuGroupList = sysMenuGroupService.list();
        List<SysMenuGroupVo> menuGroupVoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuGroupList)) {
            return menuGroupVoList;
        }
        menuGroupList.forEach(sysMenuGroup -> {
            SysMenuGroupVo sysMenuGroupVo = new SysMenuGroupVo();
            BeanUtils.copyProperties(sysMenuGroup, sysMenuGroupVo);
            menuGroupVoList.add(sysMenuGroupVo);
        });

        menuGroupVoList.forEach(sysMenuGroupVo -> {
            Integer groupVoFId = sysMenuGroupVo.getfId();

            LambdaQueryWrapper<SysMenu> queryWrapper = sysMenuService.lambdaQueryWrapper();
            queryWrapper.eq(SysMenu::getFkMenuGroupId, groupVoFId);

            List<SysMenu> sysMenuList = sysMenuService.list(queryWrapper);
            List<SysMenuVo> sysMenuVoList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(sysMenuList)) {
                sysMenuList.forEach(sysMenu -> {
                    SysMenuVo sysMenuVo = new SysMenuVo();
                    BeanUtils.copyProperties(sysMenu, sysMenuVo);
                    sysMenuVoList.add(sysMenuVo);
                });
                //查找子节点
                loop(sysMenuVoList);
            }
            sysMenuGroupVo.setChildNodeList(sysMenuVoList);
        });

        return menuGroupVoList;
    }

    private void loop(List<SysMenuVo> sysMenuVoList) {
        sysMenuVoList.forEach(sysMenuVo -> {
            Integer fid = sysMenuVo.getfId();
            LambdaQueryWrapper<SysMenu> queryWrapper = sysMenuService.lambdaQueryWrapper();
            queryWrapper.eq(SysMenu::getFkParentId, fid);
            List<SysMenu> sysMenuList = sysMenuService.list(queryWrapper);
            List<SysMenuVo> menuVoList = new ArrayList<>();
            sysMenuList.forEach(sysMenu -> {
                SysMenuVo menuVo = new SysMenuVo();
                BeanUtils.copyProperties(sysMenu, menuVo);
                menuVoList.add(menuVo);
            });
            sysMenuVo.setChildNodeList(menuVoList);
            loop(menuVoList);
        });
    }

}
