package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetFivemonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetFivemonMonthService;

/**
 * 滚动预测5+7-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetFivemonMonthServiceImpl extends MppServiceImpl<DcReportRollingBugdetFivemonMonthMapper, DcReportRollingBugdetFivemonMonth> implements DcReportRollingBugdetFivemonMonthService
{

//    /**
//     * 查询滚动预测5+7-月份列表
//     *
//     * @param dcReportRollingBugdetFivemonMonth 滚动预测5+7-月份
//     * @return 滚动预测5+7-月份
//     */
//    @Override
//    public List<DcReportRollingBugdetFivemonMonth> selectDcReportRollingBugdetFivemonMonthList(DcReportRollingBugdetFivemonMonth dcReportRollingBugdetFivemonMonth)
//    {
//        return baseMapper.selectDcReportRollingBugdetFivemonMonthList(dcReportRollingBugdetFivemonMonth);
//    }

}
