package com.oo.reportforms.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.reportforms.domain.DcReportAssetBudgetMonth;

public interface DcReportAssetBudgetMonthMapper extends MppBaseMapper<DcReportAssetBudgetMonth> {

    public void deletebyIds(String[] Ids);

}
