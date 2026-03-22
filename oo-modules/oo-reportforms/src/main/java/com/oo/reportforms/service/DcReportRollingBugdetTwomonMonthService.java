package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomonMonth;

/**
 * 滚动预测2+10-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetTwomonMonthService extends IService<DcReportRollingBugdetTwomonMonth>
{

    /**
     * 查询滚动预测2+10-月份列表
     * 
     * @param dcReportRollingBugdetTwomonMonth 滚动预测2+10-月份
     * @return 滚动预测2+10-月份集合
     */
    public List<DcReportRollingBugdetTwomonMonth> selectDcReportRollingBugdetTwomonMonthList(DcReportRollingBugdetTwomonMonth dcReportRollingBugdetTwomonMonth);

}
