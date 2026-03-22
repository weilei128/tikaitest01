package com.oo.reportforms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportOutputWellBudgetMonth;
import com.oo.reportforms.domain.DcReportOutputWellBugdet;

import java.util.List;

/**
 * 年度预算-Output-Well  Mapper接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportOutputWellBugdetMapper extends BaseMapper<DcReportOutputWellBugdet> {

    public List<DcReportOutputWellBudgetMonth> selectDcReportOutputWellBugdetYear(String output_well_id);


    public void updateDelete(String[] Id);



}
