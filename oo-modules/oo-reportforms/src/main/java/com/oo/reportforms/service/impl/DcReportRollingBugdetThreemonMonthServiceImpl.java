package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetThreemonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetThreemonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetThreemonMonthService;

/**
 * 滚动预测3+9-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetThreemonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetThreemonMonthMapper, DcReportRollingBugdetThreemonMonth> implements DcReportRollingBugdetThreemonMonthService
{

    /**
     * 查询滚动预测3+9-月份列表
     * 
     * @param dcReportRollingBugdetThreemonMonth 滚动预测3+9-月份
     * @return 滚动预测3+9-月份
     */
    @Override
    public List<DcReportRollingBugdetThreemonMonth> selectDcReportRollingBugdetThreemonMonthList(DcReportRollingBugdetThreemonMonth dcReportRollingBugdetThreemonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetThreemonMonthList(dcReportRollingBugdetThreemonMonth);
    }

}
