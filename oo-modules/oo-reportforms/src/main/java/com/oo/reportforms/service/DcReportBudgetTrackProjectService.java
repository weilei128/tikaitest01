package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportBudgetTrackProject;

/**
 * 年度开发井工作量与预算跟踪-预算-项目Service接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetTrackProjectService extends IService<DcReportBudgetTrackProject>
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算-项目列表
     * 
     * @param dcReportBudgetTrackProject 年度开发井工作量与预算跟踪-预算-项目
     * @return 年度开发井工作量与预算跟踪-预算-项目集合
     */
    public List<DcReportBudgetTrackProject> selectDcReportBudgetTrackProjectList(DcReportBudgetTrackProject dcReportBudgetTrackProject);

}
