package com.oo.reportforms.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.reportforms.domain.DcReportOutputWellBudgetMonth;
import com.oo.system.api.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DcReportOutputWellBudgetMonthMapper extends MppBaseMapper<DcReportOutputWellBudgetMonth> {

    public void deletebyIds(String[] Ids);

    /**
     * 插入元素
     *
     * @param depts 子元素
     * @return 结果
     */
    public int insertDcReportOutputWellBudgetMonth(List<DcReportOutputWellBudgetMonth> arraryList);
    /**
     * 修改元素
     *
     * @param depts
     * @return 结果
     */
    public int updateDcReportOutputWellBudgetMonth(List<DcReportOutputWellBudgetMonth> arraryList);
}
