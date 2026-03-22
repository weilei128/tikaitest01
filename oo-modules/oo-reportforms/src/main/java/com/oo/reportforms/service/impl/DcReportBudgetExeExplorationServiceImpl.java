package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportBudgetExeExplorationMapper;
import com.oo.reportforms.domain.DcReportBudgetExeExploration;
import com.oo.reportforms.service.DcReportBudgetExeExplorationService;

/**
 * 国际公司钻完井工作量及预算执行情况-探井Service业务层处理
 * 
 * @author oo
 * @date 2023-10-23
 */
@Service
public class DcReportBudgetExeExplorationServiceImpl extends ServiceImpl<DcReportBudgetExeExplorationMapper, DcReportBudgetExeExploration> implements DcReportBudgetExeExplorationService
{

    /**
     * 查询国际公司钻完井工作量及预算执行情况-探井列表
     * 
     * @param dcReportBudgetExeExploration 国际公司钻完井工作量及预算执行情况-探井
     * @return 国际公司钻完井工作量及预算执行情况-探井
     */
    @Override
    public List<DcReportBudgetExeExploration> selectDcReportBudgetExeExplorationList(DcReportBudgetExeExploration dcReportBudgetExeExploration)
    {
        return baseMapper.selectDcReportBudgetExeExplorationList(dcReportBudgetExeExploration);
    }

}
