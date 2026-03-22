package com.oo.reportforms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * （技术管理岗）十四五重大科研课题审查统计0620-林志强Service接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface IDcReportScientificCensorTotalService extends IService<DcReportScientificCensorTotal>
{

    /**
     * 查询重大科研课题审查统计列表
     * 
     * @param dcReportScientificCensorTotal 重大科研课题审查统计
     * @return 重大科研课题审查统计集合
     */
    public List<DcReportScientificCensorTotal> selectDcReportScientificCensorTotalList(DcReportScientificCensorTotal dcReportScientificCensorTotal);

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
     * @param dcReportScientificCensorTotal
     */
    void export(HttpServletResponse response, DcReportScientificCensorTotal dcReportScientificCensorTotal) throws IOException;

    /**
     * 导入
     *
     * @param dataList
     * @return
     */
    String importData(List<DcReportScientificCensorTotal> dataList, Integer dataType);
}
