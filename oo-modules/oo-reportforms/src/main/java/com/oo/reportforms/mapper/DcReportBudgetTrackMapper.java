package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportBudgetTrack;

/**
 * 年度开发井工作量与预算跟踪-预算Mapper接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetTrackMapper extends BaseMapper<DcReportBudgetTrack>
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算列表
     * 
     * @param dcReportBudgetTrack 年度开发井工作量与预算跟踪-预算
     * @return 年度开发井工作量与预算跟踪-预算集合
     */
    public List<DcReportBudgetTrack> selectDcReportBudgetTrackList(DcReportBudgetTrack dcReportBudgetTrack);

}
