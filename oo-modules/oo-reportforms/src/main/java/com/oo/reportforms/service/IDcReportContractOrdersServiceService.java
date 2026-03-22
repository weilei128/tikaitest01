package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.DcReportContractOrdersService;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））Service接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface IDcReportContractOrdersServiceService extends IService<DcReportContractOrdersService>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param contractCode 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））集合
     */
    public List<DcReportContractOrdersService> selectList(String contractCode, String year);

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param dcReportContractOrdersService 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））集合
     */
    public List<DcReportContractOrdersService> selectDcReportContractOrdersServiceList(DcReportContractOrdersService dcReportContractOrdersService);

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
    public String importData(List<DcReportContractOrdersService> dataList);
    /**
     * 导出
     *
     * @param response
     * @param dcReportContractOrdersService
     */
    void export(HttpServletResponse response, DcReportContractOrdersService dcReportContractOrdersService) throws IOException;
}
