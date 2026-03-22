package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetTenmonMonth;

/**
 * 滚动预测10+2-月份Service接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetTenmonMonthService extends IService<DcReportRollingBugdetTenmonMonth>
{

    /**
     * 查询滚动预测10+2-月份列表
     * 
     * @param dcReportRollingBugdetTenmonMonth 滚动预测10+2-月份
     * @return 滚动预测10+2-月份集合
     */
    public List<DcReportRollingBugdetTenmonMonth> selectDcReportRollingBugdetTenmonMonthList(DcReportRollingBugdetTenmonMonth dcReportRollingBugdetTenmonMonth);

}
