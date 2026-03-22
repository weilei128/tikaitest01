package com.oo.reportforms.service;

import java.io.IOException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportContractOrdersService;
import com.oo.reportforms.domain.DcReportContractOrdersWork;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）Service接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface IDcReportContractOrdersWorkService extends IService<DcReportContractOrdersWork>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param ordersNum 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）集合
     */
    public List<DcReportContractOrdersWork> selectList(String ordersNum,String year);
    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param dcReportContractOrdersWork 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）集合
     */
    public List<DcReportContractOrdersWork> selectDcReportContractOrdersWorkList(DcReportContractOrdersWork dcReportContractOrdersWork);
    /**
     * 通过日历类获取当前年份
     * @return
     */
    String getCurrentYearByCalendar();
    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportContractOrdersWork> dataList);
    /**
     * 导出
     *
     * @param response
     * @param dcReportContractOrdersWork
     */
    void export(HttpServletResponse response, DcReportContractOrdersWork dcReportContractOrdersWork) throws IOException;
}
