package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemonMonth;

/**
 * 滚动预测1+11-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetOnemonMonthMapper extends MppBaseMapper<DcReportRollingBugdetOnemonMonth>
{

    /**
     * 查询滚动预测1+11-月份列表
     * 
     * @param dcReportRollingBugdetOnemonMonth 滚动预测1+11-月份
     * @return 滚动预测1+11-月份集合
     */
    public List<DcReportRollingBugdetOnemonMonth> selectDcReportRollingBugdetOnemonMonthList(DcReportRollingBugdetOnemonMonth dcReportRollingBugdetOnemonMonth);

}
