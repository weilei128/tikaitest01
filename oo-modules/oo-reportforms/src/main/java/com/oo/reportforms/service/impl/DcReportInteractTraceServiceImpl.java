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
import com.oo.reportforms.domain.DcReportInteractTraceView;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.system.api.domain.SysDept;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportInteractTraceMapper;
import com.oo.reportforms.domain.DcReportInteractTrace;
import com.oo.reportforms.service.IDcReportInteractTraceService;

import javax.servlet.http.HttpServletResponse;

/**
 * （技术管理岗）培训和技术交流情况更踪-王荣Service业务层处理
 *
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportInteractTraceServiceImpl extends ServiceImpl<DcReportInteractTraceMapper, DcReportInteractTrace> implements IDcReportInteractTraceService
{

    @Autowired(required = false)
    private DcReportInteractTraceMapper ccReportInteractTraceMapper;
    /**
     * 查询（技术管理岗）培训和技术交流情况更踪-王荣列表
     *
     * @param dcReportInteractTrace （技术管理岗）培训和技术交流情况更踪-王荣
     * @return （技术管理岗）培训和技术交流情况更踪-王荣
     */
    @Override
    public List<DcReportInteractTraceView> selectDcReportInteractTraceList(DcReportInteractTrace dcReportInteractTrace)
    {
        return ccReportInteractTraceMapper.selectDcReportInteractTraceList(dcReportInteractTrace);
    }

    /**
     * 查询（技术管理岗）培训和技术交流情况更踪-王荣列表
     *
     * @param trainName （技术管理岗）培训和技术交流情况更踪详情-王荣
     * @return （技术管理岗）培训和技术交流情况更踪-王荣
     */
    @Override
    public List<DcReportInteractTraceView> getInfo(String trainName, Date beginTime, Date endTime)
    {
        return ccReportInteractTraceMapper.getInfo(trainName,beginTime,endTime);
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
    public String importData(List<DcReportInteractTraceView> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportInteractTraceView item : dataList){
            try {
                SysDept sysDept = baseMapper.getDeptId(item.getDeptName());
                item.setBelongDeptPlan(sysDept.getDeptId());
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
     * @param trainName
     */
    @Override
    public void export(HttpServletResponse response, String trainName, Date beginTime, Date endTime) throws IOException {
        // excel模板路径
        String templateName = "（技术管理岗）培训和技术交流情况更踪表.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportInteractTraceView> list = baseMapper.getInfo(trainName,beginTime,endTime);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        String titleName = "培训和技术交流情况跟踪表";
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        Date minDate = list.stream().map(DcReportInteractTraceView::getTrainDateReal).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
        Date maxDate = list.stream().map(DcReportInteractTraceView::getTrainDateReal).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
        String minYear = "";
        String maxYear = "";
        String title = "";
        if(minDate != null && maxDate != null)
        {
             minYear = DateUtils.parseDateToStr("yyyy", minDate);
             maxYear = DateUtils.parseDateToStr("yyyy", maxDate);
             title = "海油国际" + (minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年" + titleName;

        }else{
             title = titleName;
        }

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 1;
        // 更换标题
        XSSFRow titleRow = sheet.getRow(0);
        titleRow.getCell(0).setCellValue(title);
        for (DcReportInteractTraceView item : list) {
            XSSFRow row = sheet.createRow(1 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getDeptName());
            row.createCell(2).setCellValue(item.getTrainName());
            row.createCell(3).setCellValue(item.getTrainTarget());
            row.createCell(4).setCellValue(item.getTrainContent());
            row.createCell(5).setCellValue(item.getTrainSubject());
            row.createCell(6).setCellValue(item.getOrganizer());
            row.createCell(7).setCellValue(item.getTrainPeopleNum() == null ? null : item.getTrainPeopleNum().toString());
            row.createCell(8).setCellValue(item.getTrainDayNum() == null ? null : item.getTrainDayNum().toString());
            row.createCell(9).setCellValue(item.getTrainCity());
            row.createCell(10).setCellValue(item.getTrainDateDrafted() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getTrainDateDrafted()));
            row.createCell(11).setCellValue(item.getTrainLecturer());
            row.createCell(12).setCellValue(item.getCourseName());
            row.createCell(13).setCellValue(item.getTrainDateReal() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getTrainDateReal()));
            row.createCell(14).setCellValue(item.getClassHour() == null ? null : item.getClassHour().toString());
            row.createCell(15).setCellValue(item.getParticipantsNum() == null ? null : item.getParticipantsNum().toString());
            row.createCell(16).setCellValue(item.getParticipantsNumReal());
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
            row.getCell(15).setCellStyle(defaultStyle);
            row.getCell(16).setCellStyle(defaultStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }


}
