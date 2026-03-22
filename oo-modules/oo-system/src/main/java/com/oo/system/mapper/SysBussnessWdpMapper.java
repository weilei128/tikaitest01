package com.oo.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.system.domain.SysBussnessWdp;

/**
 * 业务域与WDP权限关系映射（通过最后一级做映射）Mapper接口
 *
 * @author oo
 * @date 2023-07-21
 */
public interface SysBussnessWdpMapper extends BaseMapper<SysBussnessWdp>
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
     * @param wdpId wdpID
     * @return 结果
     */
    public int deleteSysBussnessWdpById(Long wdpId);

}
