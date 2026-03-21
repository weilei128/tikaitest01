package com.pcitc.system.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysOrganizationBo;
import com.pcitc.system.dbService.SysOrganizationService;
import com.pcitc.system.po.SysOrganization;
import com.pcitc.system.vo.SysOrganizationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @description 系统管理服务
 * @author leigang
 * @date 2020年2月17日 17:01:01
 *
 */
@Service
@Slf4j
public class SystemService {

    private SysOrganizationService sysOrganizationService;

    @Autowired
    public void setSysOrganizationService(SysOrganizationService sysOrganizationService) {
        this.sysOrganizationService = sysOrganizationService;
    }

    /**
     * 添加子节点和根目录字典
     *
     * @param sysOrganizationBo
     */
    public void addNote(SysOrganizationBo sysOrganizationBo) {
        if (sysOrganizationBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysOrganization sysOrganization = new SysOrganization();
        BeanUtils.copyProperties(sysOrganizationBo, sysOrganization);
        sysOrganizationService.save(sysOrganization);
    }

    /**
     * 更新节点信息
     *
     * @param sysOrganizationBo
     */
    public void updateNote(SysOrganizationBo sysOrganizationBo) {
        if (sysOrganizationBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        if (sysOrganizationBo.getfId() == null || sysOrganizationBo.getfId() == 0) {
            throw new BaseException("主键id不存在", 500);
        }
        SysOrganization organization = sysOrganizationService.getById(sysOrganizationBo.getfId());
        if (organization == null) {
            throw new BaseException("组织机构不存在或者已经删除", 500);
        }
        SysOrganization sysOrganization = new SysOrganization();
        BeanUtils.copyProperties(sysOrganizationBo, sysOrganization);
        sysOrganizationService.updateById(sysOrganization);
    }

    public void deleteNote(Integer noteId) {
        SysOrganization organization = sysOrganizationService.getById(noteId);
        if (organization == null) {
            throw new BaseException("缺少参数", 500);
        }
        List<Integer> idList = new ArrayList<>();
        loopDelete(noteId, idList);
        log.info(JSON.toJSONString(idList));
        //删除子节点
        sysOrganizationService.removeByIds(idList);
    }

    /**
     * 轮询 查找子节点 数据
     *
     * @param id
     * @param idList
     */
    private void loopDelete(Integer id, List<Integer> idList) {
        idList.add(id);
        LambdaQueryWrapper<SysOrganization> wrapper =
                sysOrganizationService
                        .lambdaQueryWrapper().eq(SysOrganization::getFkParentId, id);
        List<SysOrganization> organizationList = sysOrganizationService.list(wrapper);
        if (!CollectionUtils.isEmpty(organizationList)) {
            List<Integer> integerList =
                    organizationList.stream().map(SysOrganization::getfId).collect(Collectors.toList());
            integerList.forEach(fId -> loopDelete(fId, idList));
        }
    }

    /***
     *
     * 查询组织机构信息
     * @return
     */
    public List<SysOrganizationVo> queryOrganization() {
        List<SysOrganizationVo> listVo = new ArrayList<>();
        List<SysOrganization> list = sysOrganizationService.list();
        if (CollectionUtils.isEmpty(list)) {
            return listVo;
        }
        list.forEach(sysOrganization -> {
            SysOrganizationVo sysOrganizationVo = new SysOrganizationVo();
            BeanUtils.copyProperties(sysOrganization, sysOrganizationVo);
            listVo.add(sysOrganizationVo);
        });

        List<SysOrganizationVo> sysOrganizationList =
                listVo.stream().filter(sysOrganizationVo -> sysOrganizationVo.getFkParentId() == null
                        || sysOrganizationVo.getFkParentId() == 0)
                        .collect(Collectors.toList());

        loop(listVo, sysOrganizationList);

        return sysOrganizationList;
    }

    private void loop(List<SysOrganizationVo> listVo, List<SysOrganizationVo> sysOrganizationList) {
        sysOrganizationList.forEach(sysOrganization -> {
            Integer id = sysOrganization.getfId();
            List<SysOrganizationVo> listChild = listVo
                    .stream()
                    .filter(organizationVo ->
                            id.equals(organizationVo.getFkParentId()))
                    .collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(listChild)) {
                sysOrganization.setChildNodeList(listChild);
                loop(listVo, listChild);
            }
        });
    }

}
