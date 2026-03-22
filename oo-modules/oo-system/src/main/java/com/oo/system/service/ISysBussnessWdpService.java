package com.oo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.system.domain.SysBussnessWdp;

import java.util.List;

/**
 * 业务域与WDP权限关系映射（通过最后一级做映射）Service接口
 *
 * @author oo
 * @date 2023-07-21
 */
public interface ISysBussnessWdpService extends IService<SysBussnessWdp>
{

    /**
     * 查询业务域与WDP权限关系映射（通过最后一级做映射）列表
     *
     * @param sysBussnessWdp 业务域与WDP权限关系映射（通过最后一级做映射）
     * @return 业务域与WDP权限关系映射（通过最后一级做映射）集合
     */
    public List<SysBussnessWdp> selectSysBussnessWdpList(SysBussnessWdp sysBussnessWdp);

    /**
     * 新增保存文WDP信息
     *
     * @param sysBussnessWdp 文件域信息
     * @return 结果
     */
    public int insertSysBussnessWdp (List<SysBussnessWdp> sysBussnessWdp);

    /**
     * 通过用户ID删除用户详细
     *
     * @param wdpId wdpId
     * @return 结果
     */
    public int deleteSysBussnessWdpById(Long wdpId);

    /**
     * 根据业务域id查询对应的wdpId列表
     *
     * @param businessId 业务域id
     * @return wdpId列表
     */
    public List<Long> selectWdpIdsByBusinessId(Long businessId);
}
