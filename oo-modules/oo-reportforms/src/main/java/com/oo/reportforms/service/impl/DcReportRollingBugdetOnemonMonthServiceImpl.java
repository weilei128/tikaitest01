package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.oo.reportforms.domain.DcReportOutputWellBudgetMonth;
import com.oo.reportforms.mapper.DcReportOutputWellBudgetMonthMapper;
import com.oo.reportforms.service.DcReportOutputWellBudgetMonthService;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetOnemonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetOnemonMonthService;

/**
 * 滚动预测1+11-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetOnemonMonthServiceImpl extends MppServiceImpl<DcReportRollingBugdetOnemonMonthMapper, DcReportRollingBugdetOnemonMonth> implements DcReportRollingBugdetOnemonMonthService
{

//    /**
//     * 查询滚动预测1+11-月份列表
//     *
//     * @param dcReportRollingBugdetOnemonMonth 滚动预测1+11-月份
//     * @return 滚动预测1+11-月份
//     */
//    @Override
//    public List<DcReportRollingBugdetOnemonMonth> selectDcReportRollingBugdetOnemonMonthList(DcReportRollingBugdetOnemonMonth dcReportRollingBugdetOnemonMonth)
//    {
//        return baseMapper.selectDcReportRollingBugdetOnemonMonthList(dcReportRollingBugdetOnemonMonth);
//    }

}
