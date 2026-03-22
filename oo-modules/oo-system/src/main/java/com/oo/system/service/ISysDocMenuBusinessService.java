package com.oo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.domain.SysDocMenuBusiness;

import java.util.List;

public interface ISysDocMenuBusinessService extends IService<SysDocMenuBusiness> {

    /**
     * 根据条件分页查询用户列表
     *
     * @param docMenuBusiness 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(SysDocMenuBusiness docMenuBusiness);

    /**
     * 根据文件类型查询
     *
     * @param business_name 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    public List<TreeSelect> selectDocMenuBusinessNameList(String business_name);

    /**
     * 校验文件域文件类型名称是否唯一
     *
     * @param docMenuBusiness 文件域信息
     * @return 结果
     *//*
    public String checkBusinessNameUnique(SysDocMenuBusiness docMenuBusiness);*/

    /**
     * 新增保存文件域信息
     *
     * @param docMenuBusiness 文件域信息
     * @return 结果
     */
    public int insertDocMenuBusiness(SysDocMenuBusiness docMenuBusiness);

    /**
     * 修改保存文件域信息
     *
     * @param docMenuBusiness 文件域信息
     * @return 结果
     */
    public int updateDocMenuBusiness(SysDocMenuBusiness docMenuBusiness);

    /**
     * 批量删除文件域信息
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    public int deleteDocMenuBusinessById(Long id);

    /**
     * 批量删除文件域信息
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    public int deleteDocMenuBusinessByIds(Long[] id);

    /**
     * 查看业务域详情
     *
     * @param id 业务域ID
     * @return 结果
     */
    public SysDocMenuBusiness getInfo(Long id);

    /**
     * 获取某个业务域id下所有子节点的id列表(包括当前节点)
     *
     * @param businessId 业务域id
     * @return
     */
    public List<Long> selectChildIds(Long businessId);
}
