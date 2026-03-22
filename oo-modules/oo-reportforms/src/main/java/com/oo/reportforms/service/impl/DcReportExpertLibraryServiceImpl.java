package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.reportforms.service.IDcReportContractOrdersWorkService;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportExpertLibraryMapper;
import com.oo.reportforms.domain.DcReportExpertLibrary;
import com.oo.reportforms.service.IDcReportExpertLibraryService;

import javax.servlet.http.HttpServletResponse;

/**
 * 技术岗-专家库Service业务层处理
 * 
 * @author oo
 * @date 2023-09-18
 */
@Service
public class DcReportExpertLibraryServiceImpl extends ServiceImpl<DcReportExpertLibraryMapper, DcReportExpertLibrary> implements IDcReportExpertLibraryService
{
    private final IDcReportContractOrdersWorkService iDcReportContractOrdersWorkService = null;

    /**
     * 查询技术岗-专家库列表
     * 
     * @param beginTime 技术岗-专家库
     * @return 技术岗-专家库
     */
    @Override
    public List<DcReportExpertLibrary> selectDcReportExpertLibraryList(Date beginTime, Date endTime, String unit, String technicalTitle, String ifRetire)
    {
        List<DcReportExpertLibrary> voList = baseMapper.selectDcReportExpertLibraryList(beginTime,endTime,unit,technicalTitle,ifRetire);
        return listVO(voList);
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
     * 导出
     *
     * @param response
     * @param unit
     */
    @Override
    public void export(HttpServletResponse response,Date beginTime, Date endTime, String unit, String technicalTitle, String ifRetire) throws IOException {
        // excel模板路径
        String templateName = "专家库.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportExpertLibrary> list = selectDcReportExpertLibraryList(beginTime,endTime,unit,technicalTitle,ifRetire);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }

        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        String titleName = "中国海洋石油有限公司钻完井技术审查专家名单";
        String minYear = list.stream().map(DcReportExpertLibrary::getYear).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
        String maxYear = list.stream().map(DcReportExpertLibrary::getYear).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);

        //统计在职/退休人数
/*        Integer yRetire = 0;//在职
        Integer nRetire = 0;//退休
        for (DcReportExpertLibrary item : list) {
            if(item.getIfRetire() == "是"){
                yRetire ++;
            }
            else {
                nRetire ++;
            }
        }*/
        String title = titleName + ((minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年度");

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 1;
        // 更换标题
        XSSFRow titleRow1 = sheet.getRow(0);
        titleRow1.getCell(0).setCellValue(title);
        for (DcReportExpertLibrary item : list) {
            XSSFRow row = sheet.createRow(1 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getUnit());
            row.createCell(3).setCellValue(item.getTechnicalTitle());
            row.createCell(4).setCellValue(item.getExpertLevel());
            row.createCell(5).setCellValue(item.getEngineerLevel());
            row.createCell(6).setCellValue(item.getSpeciality());
            row.createCell(7).setCellValue(item.getIfRetire());
            row.getCell(0).setCellStyle(defaultStyle);
            row.getCell(1).setCellStyle(defaultStyle);
            row.getCell(2).setCellStyle(defaultStyle);
            row.getCell(3).setCellStyle(defaultStyle);
            row.getCell(4).setCellStyle(defaultStyle);
            row.getCell(5).setCellStyle(defaultStyle);
            row.getCell(6).setCellStyle(defaultStyle);
            row.getCell(7).setCellStyle(defaultStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }
    /**
     * 导入数据
     * @param dataList
     * @return
     */
    @DictMethod(dtoClass = DcReportExpertLibrary.class)
    public String importData(List<DcReportExpertLibrary> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportExpertLibrary item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
               // item.setYear(iDcReportContractOrdersWorkService.getCurrentYearByCalendar());
                //是否退休
                if (StringUtils.isEmpty(item.getIfRetire())) {
                    isFailure = true;
                    itemFailureMsg.append("是否退休为空；");
                }
                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }
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

    public List<DcReportExpertLibrary> listVO(List<DcReportExpertLibrary> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public DcReportExpertLibrary entityVO(DcReportExpertLibrary vo){
        if(StringUtils.isNotEmpty(vo.getIfRetire())) {//
            vo.setIfRetire(DictUtils.getDictLabel("if_retire", vo.getIfRetire()));
        }
        return vo;
    }
}
