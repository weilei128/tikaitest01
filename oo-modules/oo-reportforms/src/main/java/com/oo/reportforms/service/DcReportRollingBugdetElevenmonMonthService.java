package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmonMonth;

/**
 * 滚动预测11+1-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetElevenmonMonthService extends IService<DcReportRollingBugdetElevenmonMonth>
{

    /**
     * 查询滚动预测11+1-月份列表
     * 
     * @param dcReportRollingBugdetElevenmonMonth 滚动预测11+1-月份
     * @return 滚动预测11+1-月份集合
     */
    public List<DcReportRollingBugdetElevenmonMonth> selectDcReportRollingBugdetElevenmonMonthList(DcReportRollingBugdetElevenmonMonth dcReportRollingBugdetElevenmonMonth);

}
