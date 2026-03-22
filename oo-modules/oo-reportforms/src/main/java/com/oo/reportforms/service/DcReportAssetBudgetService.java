package com.oo.reportforms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportAssetBudget;
import com.oo.reportforms.domain.DcReportContractOrdersService;
import com.oo.reportforms.domain.vo.DcReportAssetBudgetVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 描述：年度预算-AssetBudget sheet页  Service接口
 *
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportAssetBudgetService extends IService<DcReportAssetBudget> {

    /**
     * 导出
     *
     * @param response
     * @param organization_id
     * @param year
     */
    void export(HttpServletResponse response, String organization_id, String year) throws Exception;

    /**
     * add
     */
    public String add(List<DcReportAssetBudgetVo> dcReportAssetBudgetVoList) throws Exception;

    /**
     * selectList
     */
    public List<DcReportAssetBudgetVo> selectList(String organization_id, String year) throws Exception;

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportAssetBudgetVo> dataList);
}
