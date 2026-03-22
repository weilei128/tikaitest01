package com.oo.datamanagement.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.datamanagement.mapper.DcMdWellMapper;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.service.IDcMdWellService;

/**
 * 单井基础信息Service业务层处理
 *
 * @author oo
 * @date 2023-09-13
 */
@Service
public class DcMdWellServiceImpl extends ServiceImpl<DcMdWellMapper, DcMdWell> implements IDcMdWellService
{

    /**
     * 查询单井基础信息列表
     *
     * @param dcMdWell 单井基础信息
     * @return 单井基础信息
     */
    @Override
    public List<DcMdWell> selectDcMdWellList(DcMdWell dcMdWell)
    {
        return baseMapper.selectDcMdWellList(dcMdWell);
    }

}
