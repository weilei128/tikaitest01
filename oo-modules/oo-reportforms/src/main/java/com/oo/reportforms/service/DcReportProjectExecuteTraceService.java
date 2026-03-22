package com.oo.reportforms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportProjectExecuteTrace;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceImportVo;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceVo;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceYearVo;
import com.oo.reportforms.domain.vo.DcRportWellCheckVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface DcReportProjectExecuteTraceService extends IService<DcReportProjectExecuteTrace> {

    public String listTransToString(List<DcReportProjectExecuteTraceYearVo> infoList);

    /**
     * 导出
     *
     * @param response
     * @param
     */
    void export(HttpServletResponse response, String organization_id, Date post_date1, Date post_date2) throws IOException;


    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    String importData(List<DcReportProjectExecuteTraceImportVo> dataList) throws IOException;

    }
