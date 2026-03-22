package com.oo.reportforms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;
import com.oo.reportforms.domain.DcRportWellCheck;
import com.oo.reportforms.domain.vo.DcReportProjectBrieflyVo;
import com.oo.reportforms.domain.vo.DcRportWellCheckVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DcRportWellCheckService extends IService<DcRportWellCheck> {
    /**
     * 导出
     *
     * @param response
     * @param
     */
    void export(HttpServletResponse response,String well_id, String organization_id, String year) throws IOException;

    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    String importData(List<DcRportWellCheckVo> dataList) throws IOException;
}
