package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomonMonth;

/**
 * 滚动预测2+10-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetTwomonMonthMapper extends BaseMapper<DcReportRollingBugdetTwomonMonth>
{

    /**
     * 查询滚动预测2+10-月份列表
     * 
     * @param dcReportRollingBugdetTwomonMonth 滚动预测2+10-月份
     * @return 滚动预测2+10-月份集合
     */
    public List<DcReportRollingBugdetTwomonMonth> selectDcReportRollingBugdetTwomonMonthList(DcReportRollingBugdetTwomonMonth dcReportRollingBugdetTwomonMonth);

}
