package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemon;
import com.oo.reportforms.domain.vo.DcReportOutputWellBugdetVo;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetFivemonVo;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetOnemonVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 滚动预测1+11Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetOnemonService extends IService<DcReportRollingBugdetOnemon>
{

    /**
     * 查询滚动预测1+11列表
     * 
     * @param dcReportRollingBugdetOnemon 滚动预测1+11
     * @return 滚动预测1+11集合
     */
    public List<DcReportRollingBugdetOnemon> selectDcReportRollingBugdetOnemonList(DcReportRollingBugdetOnemon dcReportRollingBugdetOnemon);

    public List<DcReportRollingBugdetOnemonVo> selectList(String organizationId, String year, String budgetSubjects) throws Exception;

    String add(List<DcReportRollingBugdetOnemonVo> dcReportRollingBugdetOnemonVos) throws Exception;

    boolean deleteItemsByIds(String[] ids);
    /**
     * 导出
     *
     * @param response
     * @param organizationId
     * @param year
     * @param budgetSubjects
     */
    void export(HttpServletResponse response, String organizationId, String year, String budgetSubjects) throws Exception;
    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportRollingBugdetOnemonVo> dataList);
}
