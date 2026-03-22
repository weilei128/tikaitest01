package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTenmonMonth;

/**
 * 滚动预测10+2-月份Mapper接口
 * 
 * @author oo
 * @date 2023-11-02
 */
public interface DcReportRollingBugdetTenmonMonthMapper extends BaseMapper<DcReportRollingBugdetTenmonMonth>
{

    /**
     * 查询滚动预测10+2-月份列表
     * 
     * @param dcReportRollingBugdetTenmonMonth 滚动预测10+2-月份
     * @return 滚动预测10+2-月份集合
     */
    public List<DcReportRollingBugdetTenmonMonth> selectDcReportRollingBugdetTenmonMonthList(DcReportRollingBugdetTenmonMonth dcReportRollingBugdetTenmonMonth);

}
