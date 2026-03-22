package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSixmonMonth;

/**
 * 滚动预测6+6-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetSixmonMonthMapper extends BaseMapper<DcReportRollingBugdetSixmonMonth>
{

    /**
     * 查询滚动预测6+6-月份列表
     * 
     * @param dcReportRollingBugdetSixmonMonth 滚动预测6+6-月份
     * @return 滚动预测6+6-月份集合
     */
    public List<DcReportRollingBugdetSixmonMonth> selectDcReportRollingBugdetSixmonMonthList(DcReportRollingBugdetSixmonMonth dcReportRollingBugdetSixmonMonth);

}
