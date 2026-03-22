package com.oo.reportforms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;
import com.oo.reportforms.mapper.DcReportScientificCensorTotalMapper;
import com.oo.reportforms.service.IDcReportScientificCensorTotalService;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * （技术管理岗）十四五重大科研课题审查统计0620-林志强Service业务层处理
 * 
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportScientificCensorTotalServiceImpl extends ServiceImpl<DcReportScientificCensorTotalMapper, DcReportScientificCensorTotal> implements IDcReportScientificCensorTotalService
{

    /**
     * 查询（技术管理岗）十四五重大科研课题审查统计0620-林志强列表
     * 
     * @param dcReportScientificCensorTotal （技术管理岗）十四五重大科研课题审查统计0620-林志强
     * @return （技术管理岗）十四五重大科研课题审查统计0620-林志强
     */
    @Override
    public List<DcReportScientificCensorTotal> selectDcReportScientificCensorTotalList(DcReportScientificCensorTotal dcReportScientificCensorTotal)
    {
        return baseMapper.selectDcReportScientificCensorTotalList(dcReportScientificCensorTotal);
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
     * @param dcReportScientificCensorTotal
     */
    @Override
    public void export(HttpServletResponse response, DcReportScientificCensorTotal dcReportScientificCensorTotal) throws IOException {
        // excel模板路径
        String templateName = "（技术管理岗）十四五重大科研课题审查统计.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportScientificCensorTotal> list = baseMapper.selectDcReportScientificCensorTotalList(dcReportScientificCensorTotal);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        for (int i = 1; i <= 6; i++) { //类型 1:顶层设计  2:立项论证 3:预算审查 4:预算批准及任务书签订 5:项目启动及外委采办 6:课题管理
            String titleName = "";
            String typeName = "";
            switch(i) {
                case 1:
                    typeName = "顶层设计";
                    titleName = "顶层设计阶段历次讨论会及设计审查汇总";
                    break;
                case 2:
                    typeName = "立项论证";
                    titleName = "立项论证阶段历次讨论会及设计审查汇总";
                    break;
                case 3:
                    typeName = "预算审查";
                    titleName = "预算审查阶段历次讨论会及设计审查汇总";
                    break;
                case 4:
                    typeName = "预算批准及任务书签订";
                    titleName = "任务书签订历次讨论会及设计审查汇总";
                    break;
                case 5:
                    typeName = "项目启动及外委采办";
                    titleName = "历次讨论会及设计审查汇总";
                    break;
                case 6:
                    typeName = "课题管理";
                    titleName = "历次讨论会及设计审查汇总";
                    break;
                default:
                    typeName = "顶层设计";
                    titleName = "顶层设计阶段历次讨论会及设计审查汇总";
            }
            int finalI = i;
            List<DcReportScientificCensorTotal> dataList = list.stream().filter(a -> a.getType() == finalI).collect(Collectors.toList());
            if (dataList.isEmpty()) {
                continue;
            }
            Date minDate = dataList.stream().map(DcReportScientificCensorTotal::getDate).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
            Date maxDate = dataList.stream().map(DcReportScientificCensorTotal::getDate).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
            String minYear = DateUtils.parseDateToStr("yyyy", minDate);
            String maxYear = DateUtils.parseDateToStr("yyyy", maxDate);
            String sheetName = (minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年" + typeName + "（" + dataList.size() + "次）";
            String title = (minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年" + titleName;
            // 读取模板内sheet内容
            XSSFSheet sheet = wb.getSheetAt(i);
            wb.setSheetName(i, sheetName);
            int m = 1;
            // 更换标题
            XSSFRow titleRow = sheet.getRow(0);
            titleRow.getCell(0).setCellValue(title);
            for (DcReportScientificCensorTotal item : dataList) {
                XSSFRow row = sheet.createRow(1 + m);
                row.createCell(0).setCellValue(m);
                row.createCell(1).setCellValue(DateUtils.parseDateToStr("yyyy.M.d", item.getDate()));
                row.createCell(2).setCellValue(item.getAddress());
                row.createCell(3).setCellValue(item.getParticipants());
                row.createCell(4).setCellValue(item.getLssuesMain());
                row.getCell(0).setCellStyle(defaultStyle);
                row.getCell(1).setCellStyle(defaultStyle);
                row.getCell(2).setCellStyle(contentStyle);
                row.getCell(3).setCellStyle(contentStyle);
                row.getCell(4).setCellStyle(contentStyle);
                m++;
            }
        }
        ReportUtils.download(response, wb, templateName);
    }

    @Override
    public String importData(List<DcReportScientificCensorTotal> dataList, Integer dataType) {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            return "导入数据为空！";
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportScientificCensorTotal item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                if (dataType != null) {
                    item.setType(dataType);
                }
                else {
                    isFailure = true;
                    itemFailureMsg.append("数据类型为空；");
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
                if (StringUtils.isNotEmpty(e.getMessage()) && e.getMessage().contains("Unable to parse the date:")) {
                    failureMsg.append(msg).append(e.getMessage()).append("时间格式不正确；");
                } else {
                    failureMsg.append(msg).append(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : e);
                }
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "<br/>导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "<br/>导入成功！共 " + successNum + " 条。");
        }
        return successMsg.toString();
    }
}
