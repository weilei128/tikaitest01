package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetSevenmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetSevenmonMonthService;

/**
 * 滚动预测7+5-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetSevenmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetSevenmonMonthMapper, DcReportRollingBugdetSevenmonMonth> implements DcReportRollingBugdetSevenmonMonthService
{

    /**
     * 查询滚动预测7+5-月份列表
     * 
     * @param dcReportRollingBugdetSevenmonMonth 滚动预测7+5-月份
     * @return 滚动预测7+5-月份
     */
    @Override
    public List<DcReportRollingBugdetSevenmonMonth> selectDcReportRollingBugdetSevenmonMonthList(DcReportRollingBugdetSevenmonMonth dcReportRollingBugdetSevenmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetSevenmonMonthList(dcReportRollingBugdetSevenmonMonth);
    }

}
