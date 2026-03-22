package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetFourmonMonth;

/**
 * 滚动预测4+8-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetFourmonMonthService extends IService<DcReportRollingBugdetFourmonMonth>
{

    /**
     * 查询滚动预测4+8-月份列表
     * 
     * @param dcReportRollingBugdetFourmonMonth 滚动预测4+8-月份
     * @return 滚动预测4+8-月份集合
     */
    public List<DcReportRollingBugdetFourmonMonth> selectDcReportRollingBugdetFourmonMonthList(DcReportRollingBugdetFourmonMonth dcReportRollingBugdetFourmonMonth);

}
