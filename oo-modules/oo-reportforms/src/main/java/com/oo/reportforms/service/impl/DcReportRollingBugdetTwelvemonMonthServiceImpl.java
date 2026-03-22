package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTwelvemonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetTwelvemonMonthService;

/**
 * 滚动预测12+0-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetTwelvemonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetTwelvemonMonthMapper, DcReportRollingBugdetTwelvemonMonth> implements DcReportRollingBugdetTwelvemonMonthService
{

    /**
     * 查询滚动预测12+0-月份列表
     * 
     * @param dcReportRollingBugdetTwelvemonMonth 滚动预测12+0-月份
     * @return 滚动预测12+0-月份
     */
    @Override
    public List<DcReportRollingBugdetTwelvemonMonth> selectDcReportRollingBugdetTwelvemonMonthList(DcReportRollingBugdetTwelvemonMonth dcReportRollingBugdetTwelvemonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetTwelvemonMonthList(dcReportRollingBugdetTwelvemonMonth);
    }

}
