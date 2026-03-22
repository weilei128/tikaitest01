package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.service.IDcReportContractOrdersWorkService;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportContractOrdersServiceMapper;
import com.oo.reportforms.domain.DcReportContractOrdersService;
import com.oo.reportforms.service.IDcReportContractOrdersServiceService;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））Service业务层处理
 *
 * @author oo
 * @date 2023-08-21
 */
@Service
public class DcReportContractOrdersServiceServiceImpl extends ServiceImpl<DcReportContractOrdersServiceMapper, DcReportContractOrdersService> implements IDcReportContractOrdersServiceService
{
    private final IDcReportContractOrdersWorkService dcReportContractOrdersWorkService = null;

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param contractCode 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     */
    @Override
    public List<DcReportContractOrdersService> selectList(String contractCode, String year)
    {
        return baseMapper.selectList(contractCode,year);
    }
    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param dcReportContractOrdersService 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     */
    @Override
    public List<DcReportContractOrdersService> selectDcReportContractOrdersServiceList(DcReportContractOrdersService dcReportContractOrdersService)
    {
        return baseMapper.selectDcReportContractOrdersServiceList(dcReportContractOrdersService);
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String[] ids) {
        return baseMapper.deleteItemsByIds(ids);
    }

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportContractOrdersService> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportContractOrdersService item : dataList){
            try {
                saveOrUpdate(item);
                successNum++;
            }
            catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }

    /**
     * 导出
     *
     * @param response
     * @param dcReportContractOrdersService
     */
    @Override
    public void export(HttpServletResponse response, DcReportContractOrdersService dcReportContractOrdersService) throws IOException
    {
        // excel模板路径
        String templateName = "（计划费控岗07）工程技术分中心合同台帐及执行情况纪录.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportContractOrdersService> list = baseMapper.selectDcReportContractOrdersServiceList(dcReportContractOrdersService);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        String titleName = "服务订单";
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        String minYear = list.stream().map(DcReportContractOrdersService::getYear).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
        String maxYear = list.stream().map(DcReportContractOrdersService::getYear).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
        String title = titleName + ((minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年度" );
        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(2);
        int m = 1;
        // 更换标题
        XSSFRow titleRow = sheet.getRow(0);
        titleRow.getCell(0).setCellValue(title);
        for (DcReportContractOrdersService item : list) {
            XSSFRow row = sheet.createRow(1 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getContractCode());
            row.createCell(2).setCellValue(item.getContractEntity());
            row.createCell(3).setCellValue(item.getContractName());
            row.createCell(4).setCellValue(item.getOrdersNum());
            row.createCell(5).setCellValue(item.getServiceName());
            row.createCell(6).setCellValue(item.getContractMoney() == null ? null : item.getContractMoney().toString());
            row.createCell(7).setCellValue(item.getPaymentTotal() == null ? null : item.getPaymentTotal().toString());
            row.createCell(8).setCellValue(item.getCurrency());
            row.createCell(9).setCellValue(item.getPaymentProgress() == null ? null : item.getPaymentProgress().toString());
            row.createCell(10).setCellValue(item.getPaymentNode());
            row.createCell(11).setCellValue(item.getContractTerm());
            row.createCell(12).setCellValue(item.getContractBreach());
            row.createCell(13).setCellValue(item.getContractIllegal());
            row.createCell(14).setCellValue(item.getRemark());
            row.getCell(0).setCellStyle(defaultStyle);
            row.getCell(1).setCellStyle(defaultStyle);
            row.getCell(2).setCellStyle(contentStyle);
            row.getCell(3).setCellStyle(defaultStyle);
            row.getCell(4).setCellStyle(defaultStyle);
            row.getCell(5).setCellStyle(defaultStyle);
            row.getCell(6).setCellStyle(defaultStyle);
            row.getCell(7).setCellStyle(defaultStyle);
            row.getCell(8).setCellStyle(defaultStyle);
            row.getCell(9).setCellStyle(defaultStyle);
            row.getCell(10).setCellStyle(defaultStyle);
            row.getCell(11).setCellStyle(defaultStyle);
            row.getCell(12).setCellStyle(defaultStyle);
            row.getCell(13).setCellStyle(defaultStyle);
            row.getCell(14).setCellStyle(defaultStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }
}
