package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportBudgetExeDevelopment;

/**
 * 国际公司钻完井工作量及预算执行情况-开发井Mapper接口
 * 
 * @author oo
 * @date 2023-10-23
 */
public interface DcReportBudgetExeDevelopmentMapper extends BaseMapper<DcReportBudgetExeDevelopment>
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-开发井列表
     * 
     * @param dcReportBudgetExeDevelopment 国际公司钻完井工作量及预算执行情况-开发井
     * @return 国际公司钻完井工作量及预算执行情况-开发井集合
     */
    public List<DcReportBudgetExeDevelopment> selectDcReportBudgetExeDevelopmentList(DcReportBudgetExeDevelopment dcReportBudgetExeDevelopment);

}
