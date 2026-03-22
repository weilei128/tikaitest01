package com.oo.reportforms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportProjectBriefly;
import com.oo.reportforms.domain.vo.DcReportProjectBrieflyVo;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface DcReportProjectBrieflyService extends IService<DcReportProjectBriefly> {
    /**
     * 导出
     *
     * @param response
     * @param
     */
    void export(HttpServletResponse response, String project_id, String organization_id, Date censon_begin_date, Date censon_end_date) throws IOException;

    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    String importData(List<DcReportProjectBrieflyVo> dataList) throws IOException;
}
