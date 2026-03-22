package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.DcReportContractOrdersService;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportContractOrdersWorkMapper;
import com.oo.reportforms.domain.DcReportContractOrdersWork;
import com.oo.reportforms.service.IDcReportContractOrdersWorkService;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）Service业务层处理
 *
 * @author oo
 * @date 2023-08-21
 */
@Service
public class DcReportContractOrdersWorkServiceImpl extends ServiceImpl<DcReportContractOrdersWorkMapper, DcReportContractOrdersWork> implements IDcReportContractOrdersWorkService
{
    private final IDcReportContractOrdersWorkService dcReportContractOrdersWorkService = null;

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param ordersNum 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     */
    @Override
    public List<DcReportContractOrdersWork> selectList(String ordersNum,String year)
    {
        return baseMapper.selectList(ordersNum,year);
    }

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param dcReportContractOrdersWork 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     */
    @Override
    public List<DcReportContractOrdersWork> selectDcReportContractOrdersWorkList(DcReportContractOrdersWork dcReportContractOrdersWork)
    {
        return baseMapper.selectDcReportContractOrdersWorkList(dcReportContractOrdersWork);
    }

    public String getCurrentYearByCalendar(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        return String.valueOf(year);
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
    public String importData(List<DcReportContractOrdersWork> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportContractOrdersWork item : dataList){
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
     * @param dcReportContractOrdersWork
     */
    @Override
    public void export(HttpServletResponse response, DcReportContractOrdersWork dcReportContractOrdersWork) throws IOException {
        // excel模板路径
        String templateName = "（计划费控岗07）工程技术分中心合同台帐及执行情况纪录.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportContractOrdersWork> list = baseMapper.selectDcReportContractOrdersWorkList(dcReportContractOrdersWork);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        String titleName = "工作订单";
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        String minYear = list.stream().map(DcReportContractOrdersWork::getYear).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
        String maxYear = list.stream().map(DcReportContractOrdersWork::getYear).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
        String title = titleName + ((minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年度" );
        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(1);
        int m = 1;
        // 更换标题
        XSSFRow titleRow = sheet.getRow(0);
        titleRow.getCell(0).setCellValue(title);
        for (DcReportContractOrdersWork item : list) {
            XSSFRow row = sheet.createRow(1 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getContractContent());
            row.createCell(2).setCellValue(item.getContractName());
            row.createCell(3).setCellValue(item.getOrdersNum());
            row.createCell(4).setCellValue(item.getOrdersMoney() == null ? null : item.getOrdersMoney().toString());
            row.createCell(5).setCellValue(item.getTollMoney() == null ? null : item.getTollMoney().toString());
            row.createCell(6).setCellValue(item.getRemark());
            row.getCell(0).setCellStyle(defaultStyle);
            row.getCell(1).setCellStyle(defaultStyle);
            row.getCell(2).setCellStyle(contentStyle);
            row.getCell(3).setCellStyle(defaultStyle);
            row.getCell(4).setCellStyle(defaultStyle);
            row.getCell(5).setCellStyle(defaultStyle);
            row.getCell(6).setCellStyle(defaultStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }

}
