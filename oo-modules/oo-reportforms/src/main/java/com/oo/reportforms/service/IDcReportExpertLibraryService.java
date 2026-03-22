package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.DcReportExpertLibrary;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;

/**
 * 技术岗-专家库Service接口
 * 
 * @author oo
 * @date 2023-09-18
 */
public interface IDcReportExpertLibraryService extends IService<DcReportExpertLibrary>
{

    /**
     * 查询技术岗-专家库列表
     *
     * @param beginTime 技术岗-专家库
     * @return 技术岗-专家库集合
     */
    public List<DcReportExpertLibrary> selectDcReportExpertLibraryList(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("unit") String unit, @Param("technicalTitle") String technicalTitle, @Param("ifRetire") String ifRetire);
    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
    /**
     * 导出
     *
     * @param response
     * @param unit
     */
    void export(HttpServletResponse response,Date beginTime, Date endTime,String unit,String technicalTitle,String ifRetire) throws IOException;
    /**
     * 导入数据
     * @param dataList
     * @return
     */
    String importData(List<DcReportExpertLibrary> dataList);
}
