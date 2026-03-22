package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmonMonth;

/**
 * 滚动预测7+5-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetSevenmonMonthMapper extends BaseMapper<DcReportRollingBugdetSevenmonMonth>
{

    /**
     * 查询滚动预测7+5-月份列表
     * 
     * @param dcReportRollingBugdetSevenmonMonth 滚动预测7+5-月份
     * @return 滚动预测7+5-月份集合
     */
    public List<DcReportRollingBugdetSevenmonMonth> selectDcReportRollingBugdetSevenmonMonthList(DcReportRollingBugdetSevenmonMonth dcReportRollingBugdetSevenmonMonth);

}
