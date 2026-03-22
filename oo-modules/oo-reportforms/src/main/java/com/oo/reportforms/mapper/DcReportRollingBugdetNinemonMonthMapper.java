package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetNinemonMonth;

/**
 * 滚动预测9+3-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetNinemonMonthMapper extends BaseMapper<DcReportRollingBugdetNinemonMonth>
{

    /**
     * 查询滚动预测9+3-月份列表
     * 
     * @param dcReportRollingBugdetNinemonMonth 滚动预测9+3-月份
     * @return 滚动预测9+3-月份集合
     */
    public List<DcReportRollingBugdetNinemonMonth> selectDcReportRollingBugdetNinemonMonthList(DcReportRollingBugdetNinemonMonth dcReportRollingBugdetNinemonMonth);

}
