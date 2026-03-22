package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmonMonth;

/**
 * 滚动预测11+1-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetElevenmonMonthMapper extends BaseMapper<DcReportRollingBugdetElevenmonMonth>
{

    /**
     * 查询滚动预测11+1-月份列表
     * 
     * @param dcReportRollingBugdetElevenmonMonth 滚动预测11+1-月份
     * @return 滚动预测11+1-月份集合
     */
    public List<DcReportRollingBugdetElevenmonMonth> selectDcReportRollingBugdetElevenmonMonthList(DcReportRollingBugdetElevenmonMonth dcReportRollingBugdetElevenmonMonth);

}
