package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportBudgetExeInfill;

/**
 * 国际公司钻完井工作量及预算执行情况-调整井Service接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetExeInfillService extends IService<DcReportBudgetExeInfill>
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-调整井列表
     * 
     * @param dcReportBudgetExeInfill 国际公司钻完井工作量及预算执行情况-调整井
     * @return 国际公司钻完井工作量及预算执行情况-调整井集合
     */
    public List<DcReportBudgetExeInfill> selectDcReportBudgetExeInfillList(DcReportBudgetExeInfill dcReportBudgetExeInfill);

}
