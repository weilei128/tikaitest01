package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetThreemonMonth;

/**
 * 滚动预测3+9-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetThreemonMonthMapper extends BaseMapper<DcReportRollingBugdetThreemonMonth>
{

    /**
     * 查询滚动预测3+9-月份列表
     * 
     * @param dcReportRollingBugdetThreemonMonth 滚动预测3+9-月份
     * @return 滚动预测3+9-月份集合
     */
    public List<DcReportRollingBugdetThreemonMonth> selectDcReportRollingBugdetThreemonMonthList(DcReportRollingBugdetThreemonMonth dcReportRollingBugdetThreemonMonth);

}
