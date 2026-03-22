package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTenmonMonthMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTenmonMonth;
import com.oo.reportforms.service.DcReportRollingBugdetTenmonMonthService;

/**
 * 滚动预测10+2-月份Service业务层处理
 * 
 * @author oo
 * @date 2023-11-02
 */
@Service
public class DcReportRollingBugdetTenmonMonthServiceImpl extends ServiceImpl<DcReportRollingBugdetTenmonMonthMapper, DcReportRollingBugdetTenmonMonth> implements DcReportRollingBugdetTenmonMonthService
{

    /**
     * 查询滚动预测10+2-月份列表
     * 
     * @param dcReportRollingBugdetTenmonMonth 滚动预测10+2-月份
     * @return 滚动预测10+2-月份
     */
    @Override
    public List<DcReportRollingBugdetTenmonMonth> selectDcReportRollingBugdetTenmonMonthList(DcReportRollingBugdetTenmonMonth dcReportRollingBugdetTenmonMonth)
    {
        return baseMapper.selectDcReportRollingBugdetTenmonMonthList(dcReportRollingBugdetTenmonMonth);
    }

}
