package com.oo.reportforms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportAssetBudget;
import com.oo.reportforms.domain.DcReportAssetBudgetMonth;

import java.util.List;
/**
 * 年度预算-AssetBudget  Mapper接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportAssetBudgetMapper extends BaseMapper<DcReportAssetBudget> {

    public List<DcReportAssetBudgetMonth> selectDcReportOutputWellBugdetYear(String asset_id);


    public void updateDelete(String[] Id);
}
