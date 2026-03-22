package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemon;
import com.oo.reportforms.domain.vo.DcReportOutputWellBugdetVo;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetFivemonVo;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetOnemonVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 滚动预测5+7Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetFivemonService extends IService<DcReportRollingBugdetFivemon>
{

    /**
     * 查询滚动预测5+7列表
     * 
     * @param dcReportRollingBugdetFivemon 滚动预测5+7
     * @return 滚动预测5+7集合
     */
    public List<DcReportRollingBugdetFivemon> selectDcReportRollingBugdetFivemonList(DcReportRollingBugdetFivemon dcReportRollingBugdetFivemon);

    public List<DcReportRollingBugdetFivemonVo> selectList(String organizationId, String year, String budgetSubjects) throws Exception;

    /**
     * add
     */
     String add(List<DcReportRollingBugdetFivemonVo> dcReportRollingBugdetFivemonVos) throws Exception;

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
    public String importData(List<DcReportRollingBugdetFivemonVo> dataList);
}
