package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemonMonth;

/**
 * 滚动预测5+7-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetFivemonMonthMapper extends MppBaseMapper<DcReportRollingBugdetFivemonMonth>
{

    /**
     * 查询滚动预测5+7-月份列表
     * 
     * @param dcReportRollingBugdetFivemonMonth 滚动预测5+7-月份
     * @return 滚动预测5+7-月份集合
     */
    public List<DcReportRollingBugdetFivemonMonth> selectDcReportRollingBugdetFivemonMonthList(DcReportRollingBugdetFivemonMonth dcReportRollingBugdetFivemonMonth);

}
