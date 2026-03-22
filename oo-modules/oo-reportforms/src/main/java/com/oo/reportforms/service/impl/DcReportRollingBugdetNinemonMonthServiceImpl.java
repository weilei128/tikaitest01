package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetNinemonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetNinemonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetNinemonMonthService;

/**
 * 滚动预测9+3-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetNinemonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetNinemonMonthMapper, DcReportRollingBugdetNinemonMonth> implements DcReportRollingBugdetNinemonMonthService
{

    /**
     * 查询滚动预测9+3-月份列表
     * 
     * @param dcReportRollingBugdetNinemonMonth 滚动预测9+3-月份
     * @return 滚动预测9+3-月份
     */
    @Override
    public List<DcReportRollingBugdetNinemonMonth> selectDcReportRollingBugdetNinemonMonthList(DcReportRollingBugdetNinemonMonth dcReportRollingBugdetNinemonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetNinemonMonthList(dcReportRollingBugdetNinemonMonth);
    }

}
