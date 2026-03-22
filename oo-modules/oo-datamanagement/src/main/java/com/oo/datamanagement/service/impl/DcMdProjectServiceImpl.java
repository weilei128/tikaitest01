package com.oo.datamanagement.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.datamanagement.mapper.DcMdProjectMapper;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.service.IDcMdProjectService;

/**
 * 地质单元--区块管理Service业务层处理
 *
 * @author oo
 * @date 2023-09-13
 */
@Service
public class DcMdProjectServiceImpl extends ServiceImpl<DcMdProjectMapper, DcMdProject> implements IDcMdProjectService
{

    /**
     * 查询地质单元--区块管理列表
     *
     * @param dcMdProject 地质单元--区块管理
     * @return 地质单元--区块管理
     */
    @Override
    public List<DcMdProject> selectDcMdProjectList(DcMdProject dcMdProject)
    {
        return baseMapper.selectDcMdProjectList(dcMdProject);
    }

}
