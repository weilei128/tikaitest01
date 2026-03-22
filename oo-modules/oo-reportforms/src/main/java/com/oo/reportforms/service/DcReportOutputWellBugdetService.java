package com.oo.reportforms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportOutputWellBugdet;
import com.oo.reportforms.domain.vo.DcReportAssetBudgetVo;
import com.oo.reportforms.domain.vo.DcReportOutputWellBugdetVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 描述：年度预算-Output-Well sheet页  Service接口
 *
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportOutputWellBugdetService extends IService<DcReportOutputWellBugdet> {

    /**
     * 导出
     *
     * @param response
     * @param organization_id
     * @param year
     */
    void export(HttpServletResponse response, String organization_id, String year) throws Exception;
    /**
     * selectList
     */
    public List<DcReportOutputWellBugdetVo> selectList(String organization_id, String year) throws Exception;


    /**
     * add
     */
    public String add(List<DcReportOutputWellBugdetVo> dcReportAssetBudgetVoList) throws Exception;

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportOutputWellBugdetVo> dataList);
}
