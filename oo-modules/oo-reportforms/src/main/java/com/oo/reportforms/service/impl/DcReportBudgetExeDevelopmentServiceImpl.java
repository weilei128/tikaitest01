package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportBudgetExeDevelopmentMapper;
import com.oo.reportforms.domain.DcReportBudgetExeDevelopment;
import com.oo.reportforms.service.DcReportBudgetExeDevelopmentService;

/**
 * 国际公司钻完井工作量及预算执行情况-开发井Service业务层处理
 * 
 * @author oo
 * @date 2023-10-23
 */
@Service
public class DcReportBudgetExeDevelopmentServiceImpl extends ServiceImpl<DcReportBudgetExeDevelopmentMapper, DcReportBudgetExeDevelopment> implements DcReportBudgetExeDevelopmentService
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-开发井列表
     * 
     * @param dcReportBudgetExeDevelopment 国际公司钻完井工作量及预算执行情况-开发井
     * @return 国际公司钻完井工作量及预算执行情况-开发井
     */
    @Override
    public List<DcReportBudgetExeDevelopment> selectDcReportBudgetExeDevelopmentList(DcReportBudgetExeDevelopment dcReportBudgetExeDevelopment)
    {
        return baseMapper.selectDcReportBudgetExeDevelopmentList(dcReportBudgetExeDevelopment);
    }

}
