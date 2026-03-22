package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportBudgetExeExploration;

/**
 * 国际公司钻完井工作量及预算执行情况-探井Mapper接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetExeExplorationMapper extends BaseMapper<DcReportBudgetExeExploration>
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-探井列表
     * 
     * @param dcReportBudgetExeExploration 国际公司钻完井工作量及预算执行情况-探井
     * @return 国际公司钻完井工作量及预算执行情况-探井集合
     */
    public List<DcReportBudgetExeExploration> selectDcReportBudgetExeExplorationList(DcReportBudgetExeExploration dcReportBudgetExeExploration);

}
