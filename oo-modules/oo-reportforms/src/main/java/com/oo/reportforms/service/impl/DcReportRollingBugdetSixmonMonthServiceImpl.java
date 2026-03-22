package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetSixmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSixmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetSixmonMonthService;

/**
 * 滚动预测6+6-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetSixmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetSixmonMonthMapper, DcReportRollingBugdetSixmonMonth> implements DcReportRollingBugdetSixmonMonthService
{

    /**
     * 查询滚动预测6+6-月份列表
     * 
     * @param dcReportRollingBugdetSixmonMonth 滚动预测6+6-月份
     * @return 滚动预测6+6-月份
     */
    @Override
    public List<DcReportRollingBugdetSixmonMonth> selectDcReportRollingBugdetSixmonMonthList(DcReportRollingBugdetSixmonMonth dcReportRollingBugdetSixmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetSixmonMonthList(dcReportRollingBugdetSixmonMonth);
    }

}
