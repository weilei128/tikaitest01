package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportBudgetExeInfillMapper;
import com.oo.reportforms.domain.DcReportBudgetExeInfill;
import com.oo.reportforms.service.DcReportBudgetExeInfillService;

/**
 * 国际公司钻完井工作量及预算执行情况-调整井Service业务层处理
 * 
 * @author oo
 * @date 2023-10-23
 */
@Service
public class DcReportBudgetExeInfillServiceImpl extends ServiceImpl<DcReportBudgetExeInfillMapper, DcReportBudgetExeInfill> implements DcReportBudgetExeInfillService
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-调整井列表
     * 
     * @param dcReportBudgetExeInfill 国际公司钻完井工作量及预算执行情况-调整井
     * @return 国际公司钻完井工作量及预算执行情况-调整井
     */
    @Override
    public List<DcReportBudgetExeInfill> selectDcReportBudgetExeInfillList(DcReportBudgetExeInfill dcReportBudgetExeInfill)
    {
        return baseMapper.selectDcReportBudgetExeInfillList(dcReportBudgetExeInfill);
    }

}
