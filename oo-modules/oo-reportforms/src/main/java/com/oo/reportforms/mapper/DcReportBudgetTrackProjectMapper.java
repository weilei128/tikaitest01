package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportBudgetTrackProject;

/**
 * 年度开发井工作量与预算跟踪-预算-项目Mapper接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetTrackProjectMapper extends BaseMapper<DcReportBudgetTrackProject>
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算-项目列表
     * 
     * @param dcReportBudgetTrackProject 年度开发井工作量与预算跟踪-预算-项目
     * @return 年度开发井工作量与预算跟踪-预算-项目集合
     */
    public List<DcReportBudgetTrackProject> selectDcReportBudgetTrackProjectList(DcReportBudgetTrackProject dcReportBudgetTrackProject);

}
