package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportBudgetTrackProjectMapper;
import com.oo.reportforms.domain.DcReportBudgetTrackProject;
import com.oo.reportforms.service.DcReportBudgetTrackProjectService;

/**
 * 年度开发井工作量与预算跟踪-预算-项目Service业务层处理
 * 
 * @author oo
 * @date 2023-10-23
 */
@Service
public class DcReportBudgetTrackProjectServiceImpl extends ServiceImpl<DcReportBudgetTrackProjectMapper, DcReportBudgetTrackProject> implements DcReportBudgetTrackProjectService
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算-项目列表
     * 
     * @param dcReportBudgetTrackProject 年度开发井工作量与预算跟踪-预算-项目
     * @return 年度开发井工作量与预算跟踪-预算-项目
     */
    @Override
    public List<DcReportBudgetTrackProject> selectDcReportBudgetTrackProjectList(DcReportBudgetTrackProject dcReportBudgetTrackProject)
    {
        return baseMapper.selectDcReportBudgetTrackProjectList(dcReportBudgetTrackProject);
    }

}
