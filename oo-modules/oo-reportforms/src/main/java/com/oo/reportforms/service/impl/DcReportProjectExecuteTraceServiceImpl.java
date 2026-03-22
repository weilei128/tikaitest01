package com.oo.reportforms.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.reportforms.domain.DcReportProjectExecuteTrace;
import com.oo.reportforms.domain.DcReportProjectExecuteTraceYear;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceImportVo;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceImportYearVo;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceVo;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceYearVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.mapper.DcReportProjectExecuteTraceMapper;
import com.oo.reportforms.service.DcReportProjectExecuteTraceService;
import com.oo.reportforms.service.DcReportProjectExecuteTraceYearService;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.system.api.domain.DcMdOrganization;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DcReportProjectExecuteTraceServiceImpl extends ServiceImpl<DcReportProjectExecuteTraceMapper, DcReportProjectExecuteTrace> implements DcReportProjectExecuteTraceService {
    @Autowired
    private DcReportProjectExecuteTraceMapper dcReportProjectExecuteTraceMapper;
    @Autowired
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;
    @Autowired
    private RemoteDataManageService remoteDataManageService;
    @Autowired
    private DcReportProjectExecuteTraceYearService dcReportProjectExecuteTraceYearService;


    @Override
    public String listTransToString(List<DcReportProjectExecuteTraceYearVo> infoList) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String studentInfoJsonString = objectMapper.writeValueAsString(infoList);
            return studentInfoJsonString;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response, String organization_id, Date post_date1, Date post_date2) throws IOException {
        // excel模板路径
        String templateName = "（计划费控岗04）海外项目投资执行跟踪.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        QueryWrapper<DcReportProjectExecuteTrace> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(organization_id)){
            queryWrapper.eq("organization_id",organization_id);
        }
        if(post_date1 != null){
            queryWrapper.ge("post_date",post_date1);
        }
        if(post_date2 != null){
            queryWrapper.le("post_date",post_date2);
        }
        queryWrapper.eq("del_flag","0");

        queryWrapper.orderByAsc("post_date");
        List<DcReportProjectExecuteTrace> dcReportProjectExecuteTraceList=dcReportProjectExecuteTraceMapper.selectList(queryWrapper);
        List<DcReportProjectExecuteTraceVo> dcReportProjectExecuteTraceVoList=new ArrayList<>();

        for(DcReportProjectExecuteTrace dcReportProjectExecuteTrace:dcReportProjectExecuteTraceList){
            DcReportProjectExecuteTraceVo dcReportProjectExecuteTraceVo=new DcReportProjectExecuteTraceVo();
            BeanUtils.copyProperties(dcReportProjectExecuteTrace,dcReportProjectExecuteTraceVo);
            if(StringUtils.isNotEmpty(dcReportProjectExecuteTrace.getOrganizationId())) {
                dcReportProjectExecuteTraceVo.setName(dcReportDrillWellAssesMapper.selectName((dcReportProjectExecuteTrace.getOrganizationId())));
            }
            List<DcReportProjectExecuteTraceYearVo> dcReportProjectExecuteTraceYearVoList=dcReportProjectExecuteTraceMapper.selectDcReportProjectExecuteTraceYear(dcReportProjectExecuteTrace.getOrganizationId());
            dcReportProjectExecuteTraceVo.setDcReportProjectExecuteTraceYearVoList(dcReportProjectExecuteTraceYearVoList);
            dcReportProjectExecuteTraceVoList.add(dcReportProjectExecuteTraceVo);
        }
        if (dcReportProjectExecuteTraceVoList == null || dcReportProjectExecuteTraceVoList.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        List<DcReportProjectExecuteTraceYearVo> dcReportProjectExecuteTraceYearVoList=new ArrayList<>();
        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 0;
        for (DcReportProjectExecuteTraceVo item : dcReportProjectExecuteTraceVoList) {
            XSSFRow row = sheet.createRow(2 + m);
            row.createCell(0).setCellValue(1 + m);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getWorker());
            row.createCell(3).setCellValue(String.valueOf(item.getEquity()));
            row.createCell(4).setCellValue(item.getPostDate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getPostDate()));
            row.createCell(5).setCellValue(item.getFileCode());
            row.createCell(6).setCellValue(item.getPortionBudget());
            row.createCell(7).setCellValue(item.getFullAmountBudget());
            row.createCell(8).setCellValue(item.getPortionReal());
            row.createCell(9).setCellValue(item.getFullAmountReal());

            dcReportProjectExecuteTraceYearVoList=item.getDcReportProjectExecuteTraceYearVoList();
            for(int i = 0; i <= 11; i++){
                for(DcReportProjectExecuteTraceYearVo dpetyo:dcReportProjectExecuteTraceYearVoList){
                    if(dpetyo.getYear().equals(String.valueOf(2012 + i))) {
                        row.createCell(10 + i).setCellValue(dpetyo.getValue());
                        break;
                    }else {
                        row.createCell(10 + i).setCellValue("");
                    }
                }
            }
            row.createCell(22).setCellValue(item.getRemark());

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
            row.getCell(10).setCellStyle(defaultStyle);
            row.getCell(11).setCellStyle(defaultStyle);
            row.getCell(12).setCellStyle(contentStyle);
            row.getCell(13).setCellStyle(contentStyle);
            row.getCell(14).setCellStyle(contentStyle);
            row.getCell(15).setCellStyle(contentStyle);
            row.getCell(16).setCellStyle(contentStyle);
            row.getCell(17).setCellStyle(contentStyle);
            row.getCell(18).setCellStyle(contentStyle);
            row.getCell(19).setCellStyle(contentStyle);
            row.getCell(20).setCellStyle(contentStyle);
            row.getCell(21).setCellStyle(contentStyle);
            row.getCell(22).setCellStyle(contentStyle);
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
    @DictMethod(dtoClass = DcReportProjectExecuteTraceImportVo.class)
    public String importData(List<DcReportProjectExecuteTraceImportVo> dataList) throws IOException {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
//        List<String> projectNameList = dataList.stream().map(DcReportProjectBrieflyVo::getProjectName).collect(Collectors.toList());
//        List<String> projectList = dcReportDrillWellAssesMapper.selectList(Wrappers.<DcReportDrillWellAsses>lambdaQuery().in(DcReportDrillWellAsses::))
//        dcReportDrillWellAssesMapper.selectName(Long.valueOf(dcReportProjectBriefly.getOrganizationId()))

        MdQueryDTO queryOrg = new MdQueryDTO();
//        queryOrg.setType("2");
        List<DcMdOrganization> organizationList = remoteDataManageService.selectOrganizationList(queryOrg).getData();//查询所有组织机构名称

        DcReportProjectExecuteTraceImportYearVo dcReportProjectExecuteTraceImportYearVo=new DcReportProjectExecuteTraceImportYearVo();
        DcReportProjectExecuteTrace dcReportProjectExecuteTrace=new DcReportProjectExecuteTrace();
        Field[] declaredFields = dcReportProjectExecuteTraceImportYearVo.getClass().getDeclaredFields();
        for (DcReportProjectExecuteTraceImportVo item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                if (StringUtils.isNotEmpty(item.getName())) {
                    if (organizationList != null && !organizationList.isEmpty()) {
                        DcMdOrganization organization = organizationList.stream().filter(a -> a.getName().equals(item.getName())).findFirst().orElse(null);
                        if (organization != null) {
                            item.setOrganizationId(organization.getId().toString());
                        } else {
                            isFailure = true;
                            itemFailureMsg.append("组织机构名称不存在；");
                        }
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("组织机构名称不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("项目名称为空；");
                }

                List<DcReportProjectExecuteTraceYear> dcReportProjectExecuteTraceYearList =new ArrayList<>();

                BeanUtils.copyProperties(item,dcReportProjectExecuteTraceImportYearVo);
                BeanUtils.copyProperties(item,dcReportProjectExecuteTrace);

                for (int i = 0; i <declaredFields.length; i++) {
                    String year=declaredFields[i].getName();
                    Field f = dcReportProjectExecuteTraceImportYearVo.getClass().getDeclaredField(declaredFields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(dcReportProjectExecuteTraceImportYearVo);

                    DcReportProjectExecuteTraceYear dcReportProjectExecuteTraceYear=new DcReportProjectExecuteTraceYear();
                    dcReportProjectExecuteTraceYear.setOrganizationId(item.getOrganizationId());
                    dcReportProjectExecuteTraceYear.setYear(year.substring(7));
                    dcReportProjectExecuteTraceYear.setValue(o.toString());
                    dcReportProjectExecuteTraceYearList.add(dcReportProjectExecuteTraceYear);
                }
                dcReportProjectExecuteTraceYearService.saveOrUpdateBatchByMultiId(dcReportProjectExecuteTraceYearList);
                    if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }
                saveOrUpdate(dcReportProjectExecuteTrace);
                successNum++;
            }
            catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + " 序号" + item.getSerialNumber() + " 导入失败：";
                if (e.getMessage().contains("Unable to parse the date:")) {
                    failureMsg.append(msg).append(e.getMessage()).append("时间格式不正确；");
                }
                else {
                    failureMsg.append(msg).append(e.getMessage());
                }
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
}
