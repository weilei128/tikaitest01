package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetEightmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetEightmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetEightmonMonthService;

/**
 * 滚动预测8+4-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetEightmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetEightmonMonthMapper, DcReportRollingBugdetEightmonMonth> implements DcReportRollingBugdetEightmonMonthService
{

    /**
     * 查询滚动预测8+4-月份列表
     * 
     * @param dcReportRollingBugdetEightmonMonth 滚动预测8+4-月份
     * @return 滚动预测8+4-月份
     */
    @Override
    public List<DcReportRollingBugdetEightmonMonth> selectDcReportRollingBugdetEightmonMonthList(DcReportRollingBugdetEightmonMonth dcReportRollingBugdetEightmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetEightmonMonthList(dcReportRollingBugdetEightmonMonth);
    }

}
