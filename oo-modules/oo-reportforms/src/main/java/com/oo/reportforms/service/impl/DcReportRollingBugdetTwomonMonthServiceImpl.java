package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTwomonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetTwomonMonthService;

/**
 * 滚动预测2+10-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetTwomonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetTwomonMonthMapper, DcReportRollingBugdetTwomonMonth> implements DcReportRollingBugdetTwomonMonthService
{

    /**
     * 查询滚动预测2+10-月份列表
     * 
     * @param dcReportRollingBugdetTwomonMonth 滚动预测2+10-月份
     * @return 滚动预测2+10-月份
     */
    @Override
    public List<DcReportRollingBugdetTwomonMonth> selectDcReportRollingBugdetTwomonMonthList(DcReportRollingBugdetTwomonMonth dcReportRollingBugdetTwomonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetTwomonMonthList(dcReportRollingBugdetTwomonMonth);
    }

}
