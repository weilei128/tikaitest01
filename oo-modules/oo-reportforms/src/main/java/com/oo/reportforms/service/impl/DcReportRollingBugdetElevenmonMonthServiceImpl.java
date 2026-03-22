package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetElevenmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetElevenmonMonthService;

/**
 * 滚动预测11+1-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetElevenmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetElevenmonMonthMapper, DcReportRollingBugdetElevenmonMonth> implements DcReportRollingBugdetElevenmonMonthService
{

    /**
     * 查询滚动预测11+1-月份列表
     * 
     * @param dcReportRollingBugdetElevenmonMonth 滚动预测11+1-月份
     * @return 滚动预测11+1-月份
     */
    @Override
    public List<DcReportRollingBugdetElevenmonMonth> selectDcReportRollingBugdetElevenmonMonthList(DcReportRollingBugdetElevenmonMonth dcReportRollingBugdetElevenmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetElevenmonMonthList(dcReportRollingBugdetElevenmonMonth);
    }

}
