package com.oo.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.system.domain.SysBussnessWdp;
import com.oo.system.mapper.SysBussnessWdpMapper;
import com.oo.system.service.ISysBussnessWdpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务域与WDP权限关系映射（通过最后一级做映射）Service业务层处理
 *
 * @author oo
 * @date 2023-07-21
 */
@Service
public class SysBussnessWdpServiceImpl extends ServiceImpl<SysBussnessWdpMapper, SysBussnessWdp> implements ISysBussnessWdpService
{

    /**
     * 查询业务域与WDP权限关系映射（通过最后一级做映射）列表
     *
     * @param sysBussnessWdp 业务域与WDP权限关系映射（通过最后一级做映射）
     * @return 业务域与WDP权限关系映射（通过最后一级做映射）
     */
    @Override
    public List<SysBussnessWdp> selectSysBussnessWdpList(SysBussnessWdp sysBussnessWdp)
    {
        return baseMapper.selectSysBussnessWdpList(sysBussnessWdp);
    }

    /**
     * 新增保存文件域文件类型
     *
     * @param sysBussnessWdp 文件域文件类型
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertSysBussnessWdp(List<SysBussnessWdp> sysBussnessWdp)
    {
        // 新增文件域文件类型
        int rows = baseMapper.insertSysBussnessWdp(sysBussnessWdp);
        return rows;
    }
    /**
     * 通过用户ID删除用户详细
     *
     * @param wdpId 用户ID
     * @return 结果
     */
    public int deleteSysBussnessWdpById(Long wdpId)
    {
        return baseMapper.deleteSysBussnessWdpById(wdpId);
    }

    /**
     * 根据业务域id查询对应的wdpId列表
     *
     * @param businessId 业务域id
     * @return wdpId列表
     */
    @Override
    public List<Long> selectWdpIdsByBusinessId(Long businessId)
    {
        List<SysBussnessWdp> list = list(Wrappers.<SysBussnessWdp>lambdaQuery().eq(SysBussnessWdp::getBussnessId, businessId));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream().map(SysBussnessWdp::getWdpId).collect(Collectors.toList());
    }
}
