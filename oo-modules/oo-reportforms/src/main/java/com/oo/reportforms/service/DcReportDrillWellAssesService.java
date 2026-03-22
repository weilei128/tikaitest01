package com.oo.reportforms.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.oo.reportforms.domain.DcReportDrillWellAsses;
import com.oo.reportforms.domain.vo.DcReportDrillWellAssesVo;
import com.oo.reportforms.domain.vo.DcRportWellCheckVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface DcReportDrillWellAssesService extends IMppService<DcReportDrillWellAsses> {


    /**
     * 导出
     *
     * @param response
     * @param
     */
    void export(HttpServletResponse response,String year,Long country) throws IOException;

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);

    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    String importData(List<DcReportDrillWellAssesVo> dataList) throws IOException;
}
