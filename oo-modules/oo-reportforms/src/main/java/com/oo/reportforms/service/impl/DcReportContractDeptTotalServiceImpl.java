package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.*;
import com.oo.reportforms.mapper.DcReportContractOrdersServiceMapper;
import com.oo.reportforms.mapper.DcReportContractOrdersWorkMapper;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.system.api.domain.SysUser;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportContractDeptTotalMapper;
import com.oo.reportforms.service.IDcReportContractDeptTotalService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程技术分中心合同台账及执行情况记录-部门合同统计Service业务层处理
 * 
 * @author oo
 * @date 2023-08-21
 */
@Service
public class DcReportContractDeptTotalServiceImpl extends ServiceImpl<DcReportContractDeptTotalMapper, DcReportContractDeptTotal> implements IDcReportContractDeptTotalService
{
     @Autowired(required = false)
     private DcReportContractOrdersServiceMapper dcReportContractOrdersServiceMapper;

    @Autowired(required = false)
    private DcReportContractOrdersWorkMapper dcReportContractOrdersWorkMapper;

    /**
     * 查询工程技术分中心合同台账及执行情况记录-部门合同统计列表
     * 
     * @param contractCode 工程技术分中心合同台账及执行情况记录-部门合同统计
     * @return 工程技术分中心合同台账及执行情况记录-部门合同统计
     */
    @Override
    public List<DcReportContractDeptTotal> selectList(String contractCode, Date beginTime, Date endTime)
    {
        return baseMapper.selectList(contractCode,beginTime,endTime);
    }

    /**
     * 查询工程技术分中心合同台账及执行情况记录-部门合同统计列表
     *
     * @param dcReportContractDeptTotal 工程技术分中心合同台账及执行情况记录-部门合同统计
     * @return 工程技术分中心合同台账及执行情况记录-部门合同统计
     */
    @Override
    public List<DcReportContractDeptTotal> selectDcReportContractDeptTotalList(DcReportContractDeptTotal dcReportContractDeptTotal)
    {
        return baseMapper.selectDcReportContractDeptTotalList(dcReportContractDeptTotal);
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String[] ids)
    {
        return baseMapper.deleteItemsByIds(ids);
    }

    /**
     * 批量删除部门合同统计列表
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int DeleteContractDeptTotalByIds(String[] ids)
    {
        return baseMapper.DeleteContractDeptTotalByIds(ids);
    }

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportContractDeptTotal> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportContractDeptTotal item : dataList){
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
     * @param contractCode：合同号
     */
    @Override
    public void export(HttpServletResponse response,String contractCode , Date beginTime, Date endTime,String year ,String ordersNum) throws IOException
    {
        if(year!=null){
        if(year.equals("null")||year.equals("")){year=null;}
        }
        // excel模板路径
        String templateName = "（计划费控岗07）工程技术分中心合同台帐及执行情况纪录.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportContractDeptTotal> listDeptTotal = baseMapper.selectList(contractCode,beginTime,endTime);//部门合同
        List<DcReportContractOrdersWork> listOrdersWork = dcReportContractOrdersWorkMapper.selectList(ordersNum,year);//工作订单
        List<DcReportContractOrdersService> listOrdersService = dcReportContractOrdersServiceMapper.selectList(contractCode,year);//服务订单
     /*   if (listDeptTotal == null || listDeptTotal.isEmpty() || listOrdersWork == null || listOrdersWork.isEmpty() || listOrdersService == null || listOrdersService.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }*/
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        for (int i = 0; i <= 2; i++) { //类型 1:部门合同统计  2:工作订单 3:服务订单
            String titleName = "";
            String typeName = "";
            switch (i) {
                case 0:
                    typeName = "部门合同统计";
                    titleName = "部门合同统计";
                    if(listDeptTotal.size() > 0) {
                        Date minDate = listDeptTotal.stream().map(DcReportContractDeptTotal::getCreateTime).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
                        Date maxDate = listDeptTotal.stream().map(DcReportContractDeptTotal::getCreateTime).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
                        String minYear = DateUtils.parseDateToStr("yyyy", minDate);
                        String maxYear = DateUtils.parseDateToStr("yyyy", maxDate);
                        String title = "";
                        if (year != null) {
                            title = titleName + "(" + year + "年度" + ")";
                        }
                        else {
                            title = titleName + "("+(minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年度)";
                        }

                        // 读取模板内sheet内容
                        XSSFSheet sheet = wb.getSheetAt(0);
                        int m = 1;
                        // 更换标题
                        XSSFRow titleRow = sheet.getRow(0);
                        titleRow.getCell(0).setCellValue(title);
                        for (DcReportContractDeptTotal item : listDeptTotal) {
                            XSSFRow row = sheet.createRow(1 + m);
                            row.createCell(0).setCellValue(m);
                            row.createCell(1).setCellValue(item.getContractCode());
                            row.createCell(2).setCellValue(item.getContractContent());
                            row.createCell(3).setCellValue(item.getContractEntity());
                            row.createCell(4).setCellValue(item.getServiceName());
                            row.createCell(5).setCellValue(item.getSignedDate() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getSignedDate()));
                            row.createCell(6).setCellValue(item.getEndDate() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getEndDate()));
                            row.createCell(7).setCellValue(item.getContractTotalMoney() == null ? null : item.getContractTotalMoney().toString());
                            row.createCell(8).setCellValue(item.getPaymentType());
                            row.createCell(9).setCellValue(item.getMoneyUnit());
                            row.createCell(10).setCellValue(item.getRemark());
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
                            m++;
                        }
                    }
                    break;
                case 1:
                    typeName = "工作订单";
                    titleName = "海外公司与海油国际工作订单";
                    if(listOrdersWork.size() > 0) {
                        String minYear1 = listOrdersWork.stream().map(DcReportContractOrdersWork::getYear).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
                        String maxYear1 = listOrdersWork.stream().map(DcReportContractOrdersWork::getYear).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
                        String title1 = "";
                        if (year != null) {
                            title1 = titleName + "(" + year + "年度" + ")";
                        }
                        else {
                            title1 = titleName + "("+(minYear1.equals(maxYear1) ? minYear1 : minYear1 + "-" + maxYear1) + "年度)";
                        }


                        // 读取模板内sheet内容
                        XSSFSheet sheet1 = wb.getSheetAt(1);
                        int s = 1;
                        // 更换标题
                        XSSFRow titleRow1 = sheet1.getRow(0);
                        titleRow1.getCell(0).setCellValue(title1);
                        for (DcReportContractOrdersWork item : listOrdersWork) {
                            XSSFRow row = sheet1.createRow(1 + s);
                            row.createCell(0).setCellValue(s);
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
                            s++;
                        }
                    }
                    break;
                case 2:
                    typeName = "服务订单";
                    titleName = "海油国际与国内供应商服务订单";
                    if(listOrdersService.size() > 0) {
                        String minYear2 = listOrdersService.stream().map(DcReportContractOrdersService::getYear).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
                        String maxYear2 = listOrdersService.stream().map(DcReportContractOrdersService::getYear).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
                        String title2 = "";
                        if (year != null) {
                            title2 = titleName + "(" + year + "年度" + ")";
                        }
                       else {
                           title2 = titleName + "("+(minYear2.equals(maxYear2) ? minYear2 : minYear2 + "-" + maxYear2) + "年度)";
                       }

                        // 读取模板内sheet内容
                        XSSFSheet sheet2 = wb.getSheetAt(2);
                        int t = 1;
                        // 更换标题
                        XSSFRow titleRow2 = sheet2.getRow(0);
                        titleRow2.getCell(0).setCellValue(title2);
                        for (DcReportContractOrdersService item : listOrdersService) {
                            XSSFRow row = sheet2.createRow(1 + t);
                            row.createCell(0).setCellValue(t);
                            row.createCell(1).setCellValue(item.getContractEntity());
                            row.createCell(2).setCellValue(item.getContractCode());
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
                            row.getCell(2).setCellStyle(defaultStyle);
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
                            t++;
                        }
                    }
                    break;
            }
        }
        ReportUtils.download(response, wb, templateName);
    }

}
