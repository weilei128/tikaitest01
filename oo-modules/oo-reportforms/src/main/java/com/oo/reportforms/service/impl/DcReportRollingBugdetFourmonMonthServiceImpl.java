package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetFourmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFourmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetFourmonMonthService;

/**
 * 滚动预测4+8-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetFourmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetFourmonMonthMapper, DcReportRollingBugdetFourmonMonth> implements DcReportRollingBugdetFourmonMonthService
{

    /**
     * 查询滚动预测4+8-月份列表
     * 
     * @param dcReportRollingBugdetFourmonMonth 滚动预测4+8-月份
     * @return 滚动预测4+8-月份
     */
    @Override
    public List<DcReportRollingBugdetFourmonMonth> selectDcReportRollingBugdetFourmonMonthList(DcReportRollingBugdetFourmonMonth dcReportRollingBugdetFourmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetFourmonMonthList(dcReportRollingBugdetFourmonMonth);
    }

}
