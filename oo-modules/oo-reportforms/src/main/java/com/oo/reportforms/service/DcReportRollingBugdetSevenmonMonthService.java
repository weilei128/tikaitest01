package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmonMonth;

/**
 * 滚动预测7+5-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetSevenmonMonthService extends IService<DcReportRollingBugdetSevenmonMonth>
{

    /**
     * 查询滚动预测7+5-月份列表
     * 
     * @param dcReportRollingBugdetSevenmonMonth 滚动预测7+5-月份
     * @return 滚动预测7+5-月份集合
     */
    public List<DcReportRollingBugdetSevenmonMonth> selectDcReportRollingBugdetSevenmonMonthList(DcReportRollingBugdetSevenmonMonth dcReportRollingBugdetSevenmonMonth);

}
