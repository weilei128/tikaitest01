package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetEightmonMonth;

/**
 * 滚动预测8+4-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetEightmonMonthMapper extends BaseMapper<DcReportRollingBugdetEightmonMonth>
{

    /**
     * 查询滚动预测8+4-月份列表
     * 
     * @param dcReportRollingBugdetEightmonMonth 滚动预测8+4-月份
     * @return 滚动预测8+4-月份集合
     */
    public List<DcReportRollingBugdetEightmonMonth> selectDcReportRollingBugdetEightmonMonthList(DcReportRollingBugdetEightmonMonth dcReportRollingBugdetEightmonMonth);

}
