package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportBudgetTrack;

/**
 * 年度开发井工作量与预算跟踪-预算Service接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetTrackService extends IService<DcReportBudgetTrack>
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算列表
     * 
     * @param dcReportBudgetTrack 年度开发井工作量与预算跟踪-预算
     * @return 年度开发井工作量与预算跟踪-预算集合
     */
    public List<DcReportBudgetTrack> selectDcReportBudgetTrackList(DcReportBudgetTrack dcReportBudgetTrack);

}
