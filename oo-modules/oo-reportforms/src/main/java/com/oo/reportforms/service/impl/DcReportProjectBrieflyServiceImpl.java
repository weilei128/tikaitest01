package com.oo.reportforms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.reportforms.domain.DcReportProjectBriefly;
import com.oo.reportforms.domain.vo.DcReportProjectBrieflyVo;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.mapper.DcReportProjectBrieflyMapper;
import com.oo.reportforms.service.DcReportProjectBrieflyService;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.system.api.domain.DcMdOrganization;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DcReportProjectBrieflyServiceImpl extends ServiceImpl<DcReportProjectBrieflyMapper, DcReportProjectBriefly> implements DcReportProjectBrieflyService {

    private final DcReportProjectBrieflyMapper dcReportProjectBrieflyMapper;

    private final DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;

    private final RemoteDataManageService remoteDataManageService;

    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response, String project_id, String organization_id, Date censon_begin_date, Date censon_end_date) throws IOException {
        // excel模板路径
        String templateName = "（技术管理岗）项目简介.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);

        QueryWrapper<DcReportProjectBriefly> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(project_id)) {//项目名称
            queryWrapper.eq("project_id", project_id);
        }
        if (StringUtils.isNotEmpty(organization_id)) {//油田名称
            queryWrapper.eq("organization_id", organization_id);
        }
        if (null != censon_begin_date) {//审查会时间时间
            queryWrapper.ge("censon_begin_date", censon_begin_date);
        }
        if (null != censon_begin_date) {//审查会时间时间
            queryWrapper.le("censon_end_date", censon_end_date);
        }
//            queryWrapper.orderByAsc("year");

        List<DcReportProjectBrieflyVo> dcReportProjectBrieflyVoArrayList = new ArrayList<>();
        List<DcReportProjectBriefly> dcReportProjectBrieflyList = dcReportProjectBrieflyMapper.selectList(queryWrapper);
        for (DcReportProjectBriefly dcReportProjectBriefly : dcReportProjectBrieflyList) {
            DcReportProjectBrieflyVo dcReportProjectBrieflyVo = new DcReportProjectBrieflyVo();
            BeanUtils.copyProperties(dcReportProjectBriefly, dcReportProjectBrieflyVo);

            if (StringUtils.isNotEmpty(dcReportProjectBriefly.getOrganizationId())) {
                dcReportProjectBrieflyVo.setOrganizationName(dcReportDrillWellAssesMapper.selectName((dcReportProjectBriefly.getOrganizationId())));
            }
            if (StringUtils.isNotEmpty(dcReportProjectBriefly.getProjectId())) {
                dcReportProjectBrieflyVo.setProjectName(dcReportDrillWellAssesMapper.projectName((dcReportProjectBriefly.getProjectId())));
            }
            if (StringUtils.isNotEmpty(dcReportProjectBriefly.getWellTypeId())) {
                String dictLabel = DictUtils.getDictLabel("dc_pk_well_type", dcReportProjectBriefly.getWellTypeId());
                dcReportProjectBrieflyVo.setWellTypeId(dictLabel == null ? "" : dictLabel);
            }

            dcReportProjectBrieflyVoArrayList.add(dcReportProjectBrieflyVo);
        }
        if (dcReportProjectBrieflyVoArrayList.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }

        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 0;
        for (DcReportProjectBrieflyVo item : dcReportProjectBrieflyVoArrayList) {
            XSSFRow row = sheet.createRow(m + 1);
            row.createCell(0).setCellValue(m + 1);
            row.createCell(1).setCellValue(item.getProjectName());
            row.createCell(2).setCellValue(item.getOilBriefly());
            row.createCell(3).setCellValue(item.getOilEquity());
            row.createCell(4).setCellValue(item.getOrganizationName());
            row.createCell(5).setCellValue(item.getProjectBriefly());
            row.createCell(6).setCellValue(item.getWellNum() == null ? null : item.getWellNum().toString());
            row.createCell(7).setCellValue(item.getWaterDepth() == null ? null : item.getWaterDepth().toString());
            row.createCell(8).setCellValue(item.getWellTypeId());
            row.createCell(9).setCellValue(item.getWellDepthAvg() == null ? "" : item.getWellDepthAvg().toString());
            row.createCell(10).setCellValue(item.getWorkDateEstimate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getWorkDateEstimate()));
            row.createCell(11).setCellValue(item.getWellDurationAvg() == null ? "" : item.getWellDurationAvg().toString());
            row.createCell(12).setCellValue(item.getExpensesTotal() == null ? null : item.getExpensesTotal().toString());
            row.createCell(13).setCellValue(item.getCensorStage());
            row.createCell(14).setCellValue((item.getCensonBeginDate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getCensonBeginDate())) + "-"
                    + (item.getCensonEndDate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getCensonEndDate())));
            row.createCell(15).setCellValue(item.getRemark());


            row.getCell(0).setCellStyle(defaultStyle);
            row.getCell(1).setCellStyle(defaultStyle);
            row.getCell(2).setCellStyle(contentStyle);
            row.getCell(3).setCellStyle(contentStyle);
            row.getCell(4).setCellStyle(contentStyle);
            row.getCell(5).setCellStyle(contentStyle);
            row.getCell(6).setCellStyle(contentStyle);
            row.getCell(7).setCellStyle(contentStyle);
            row.getCell(8).setCellStyle(contentStyle);
            row.getCell(9).setCellStyle(contentStyle);
            row.getCell(10).setCellStyle(contentStyle);
            row.getCell(11).setCellStyle(contentStyle);
            row.getCell(12).setCellStyle(contentStyle);
            row.getCell(13).setCellStyle(contentStyle);
            row.getCell(14).setCellStyle(contentStyle);
            row.getCell(15).setCellStyle(contentStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }

    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    @Override
    @DictMethod(dtoClass = DcReportRiskCheckNcrVo.class)
    public String importData(List<DcReportProjectBrieflyVo> dataList) throws IOException {
//        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
//        int sheetIndex;
//        if (StringUtils.isEmpty(sheetNum)) {
//            sheetIndex = 0;
//        }
//        XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(index));
        if (StringUtils.isNull(dataList) || dataList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<String> projectNameList = dataList.stream().map(DcReportProjectBrieflyVo::getProjectName).collect(Collectors.toList());
        MdQueryDTO queryProject = new MdQueryDTO();
        queryProject.setNames(projectNameList);
        queryProject.setType("4");
        List<DcMdProject> projectList = remoteDataManageService.selectProjectList(queryProject).getData();
        List<String> organizationNameList = dataList.stream().map(DcReportProjectBrieflyVo::getOrganizationName).collect(Collectors.toList());
        MdQueryDTO queryOrg = new MdQueryDTO();
        queryOrg.setNames(organizationNameList);
        queryOrg.setType("3");
        List<DcMdOrganization> organizationList = remoteDataManageService.selectOrganizationList(queryOrg).getData();
        for (DcReportProjectBrieflyVo item : dataList) {
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                if (StringUtils.isNotEmpty(item.getProjectName())) {
                    if (projectList != null && !projectList.isEmpty()) {
                        DcMdProject project = projectList.stream().filter(a -> a.getName().equals(item.getProjectName())).findFirst().orElse(null);
                        if (project != null) {
                            item.setProjectId(project.getId().toString());
                        } else {
                            isFailure = true;
                            itemFailureMsg.append("油田名称不存在；");
                        }
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("油田名称不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("油田名称为空；");
                }
                if (StringUtils.isNotEmpty(item.getOrganizationName())) {
                    if (organizationList != null && !organizationList.isEmpty()) {
                        DcMdOrganization organization = organizationList.stream().filter(a -> a.getName().equals(item.getOrganizationName())).findFirst().orElse(null);
                        if (organization != null) {
                            item.setOrganizationId(organization.getId().toString());
                        } else {
                            isFailure = true;
                            itemFailureMsg.append("项目名称不存在；");
                        }
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("项目名称不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("项目名称为空；");
                }
//                if (StringUtils.isNotEmpty(item.getWellTypeId())) {
//                    String wellTypeId = DictUtils.getDictValue("dc_pk_well_type", item.getWellTypeId());
//                    if (StringUtils.isNotEmpty(wellTypeId)) {
//                        item.setWellTypeId(wellTypeId);
//                    } else {
//                        isFailure = true;
//                        itemFailureMsg.append("井型名称不存在；");
//                    }
//                } else {
//                    isFailure = true;
//                    itemFailureMsg.append("井型为空；");
//                }
                if (StringUtils.isNotEmpty(item.getCensonDate())) {
                    if (item.getCensonDate().contains("-")) {
                        int i = 0;
                        String[] dateArray = item.getCensonDate().split("-");
                        if (dateArray.length == 0) {
                            isFailure = true;
                            itemFailureMsg.append("审查会时间格式不正确；");
                        }
                        else if (dateArray.length == 1) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                            item.setCensonBeginDate(sdf.parse(item.getCensonDate()));
                        }
                        else {
                            for (String date : dateArray) {
                                if (i == 0) {
                                    item.setCensonBeginDate(DateUtils.parseDate(date));
                                } else {
                                    item.setCensonEndDate(DateUtils.parseDate(date));
                                }
                                i++;
                            }
                        }

                    }

                }
//                else {
//                    isFailure = true;
//                    itemFailureMsg.append("审查会时间为空或格式不正确；");
//                }
                    if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }
                save(item);
                successNum++;
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + " 序号" + item.getSerialNumber() + " 导入失败：";
                if (StringUtils.isNotEmpty(e.getMessage()) && e.getMessage().contains("Unable to parse the date:")) {
                    failureMsg.append(msg).append(e.getMessage()).append("时间格式不正确；");
                } else {
                    failureMsg.append(msg).append(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : e);
                }
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }
}
