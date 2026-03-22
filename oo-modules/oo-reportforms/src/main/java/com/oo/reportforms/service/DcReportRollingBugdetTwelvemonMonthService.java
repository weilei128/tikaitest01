package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemonMonth;

/**
 * 滚动预测12+0-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetTwelvemonMonthService extends IService<DcReportRollingBugdetTwelvemonMonth>
{

    /**
     * 查询滚动预测12+0-月份列表
     * 
     * @param dcReportRollingBugdetTwelvemonMonth 滚动预测12+0-月份
     * @return 滚动预测12+0-月份集合
     */
    public List<DcReportRollingBugdetTwelvemonMonth> selectDcReportRollingBugdetTwelvemonMonthList(DcReportRollingBugdetTwelvemonMonth dcReportRollingBugdetTwelvemonMonth);

}
