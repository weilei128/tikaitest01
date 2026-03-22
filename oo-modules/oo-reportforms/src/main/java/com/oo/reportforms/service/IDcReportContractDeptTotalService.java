package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.DcReportContractOrdersWork;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-部门合同统计Service接口
 * 
 * @author oo
 * @date 2023-08-21
 */
public interface IDcReportContractDeptTotalService extends IService<DcReportContractDeptTotal>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-部门合同统计列表
     * 
     * @param contractCode 工程技术分中心合同台账及执行情况记录-部门合同统计
     * @return 工程技术分中心合同台账及执行情况记录-部门合同统计集合
     */
    public List<DcReportContractDeptTotal> selectList(String contractCode, Date beginTime, Date endTime);

    public List<DcReportContractDeptTotal> selectDcReportContractDeptTotalList(DcReportContractDeptTotal dcReportContractDeptTotal);

    public boolean deleteItemsByIds(String[] ids);

    /**
     * 软删除部门合同统计列表
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int DeleteContractDeptTotalByIds(String[] ids);

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportContractDeptTotal> dataList);
    /**
     * 导出
     *
     * @param response
     * @param contractCode
     */
    void export(HttpServletResponse response,String contractCode, Date beginTime, Date endTime,String year,String ordersNum) throws IOException;
}
