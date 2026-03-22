package com.oo.reportforms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.reportforms.domain.DcRportWellCheck;
import com.oo.reportforms.domain.vo.DcRportWellCheckVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.mapper.DcRportWellCheckMapper;
import com.oo.reportforms.service.DcRportWellCheckService;
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
import java.util.*;

@Service
public class DcRportWellCheckServiceImpl extends ServiceImpl<DcRportWellCheckMapper, DcRportWellCheck> implements DcRportWellCheckService {

    @Autowired
    private DcRportWellCheckMapper dcRportWellCheckMapper;
    @Autowired
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;
    @Autowired
    private  RemoteDataManageService remoteDataManageService;

    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response,String well_id, String organization_id, String year) throws IOException {
        // excel模板路径
        String templateName = "（井控管理岗）海油国际井控检查记录.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        QueryWrapper<DcRportWellCheck> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(well_id)){//井id
            queryWrapper.eq("well_id",well_id);
        }
        if(StringUtils.isNotEmpty(organization_id)){//国家id
            queryWrapper.eq("organization_id",organization_id);
        }
        if(StringUtils.isNotEmpty(year)){//年份
            queryWrapper.eq("year",year);
        }
        queryWrapper.orderByAsc("year");
        List<DcRportWellCheckVo> dcRportWellCheckVoList=new ArrayList<>();
        List<DcRportWellCheck> dcRportWellCheckList=dcRportWellCheckMapper.selectList(queryWrapper);
        String dictLabel="";
        for(DcRportWellCheck dcRportWellCheck:dcRportWellCheckList){
            DcRportWellCheckVo dcRportWellCheckVo=new DcRportWellCheckVo();
            BeanUtils.copyProperties(dcRportWellCheck,dcRportWellCheckVo);

            if(StringUtils.isNotEmpty(dcRportWellCheck.getOrganizationId())) {
                dcRportWellCheckVo.setOrganizationName(dcReportDrillWellAssesMapper.selectName((dcRportWellCheck.getOrganizationId())));
            }
            if(StringUtils.isNotEmpty(dcRportWellCheck.getWellId())) {
                dcRportWellCheckVo.setWellName(dcReportDrillWellAssesMapper.selectWellName((dcRportWellCheck.getWellId())));
            }
            dictLabel = DictUtils.getDictLabel("risk_type", dcRportWellCheck.getRiskLevel());//
            dcRportWellCheckVo.setRiskLevel(dictLabel == null ? "" : dictLabel);

            dcRportWellCheckVoList.add(dcRportWellCheckVo);
        }
        if (dcRportWellCheckVoList.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 0;
            for (DcRportWellCheckVo item : dcRportWellCheckVoList) {
                XSSFRow row = sheet.createRow(1 + m);
                row.createCell(0).setCellValue(1 + m);
                row.createCell(1).setCellValue(item.getWellName());
                row.createCell(2).setCellValue(item.getOrganizationName());
                row.createCell(3).setCellValue(item.getRiskLevel());
                row.createCell(4).setCellValue(item.getCheckDrillingBefore() == null ? "" :"√");
                row.createCell(5).setCellValue(item.getCheckDrillOil()== null ? "" :"√");
                row.createCell(6).setCellValue(item.getRemark());
                row.createCell(7).setCellValue(item.getCheckDrillingBeforeDate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getCheckDrillingBeforeDate()));
                row.createCell(8).setCellValue(item.getCheckDrillOilDate() == null ? "" : DateUtils.parseDateToStr("yyyy/M/d", item.getCheckDrillOilDate()));
                row.createCell(9).setCellValue(item.getBorer());

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
    @DictMethod(dtoClass = DcRportWellCheckVo.class)
    public String importData(List<DcRportWellCheckVo> dataList) throws IOException {
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
        queryOrg.setType("2");
        List<DcMdOrganization> organizationList = remoteDataManageService.selectOrganizationList(queryOrg).getData();//查询国家名称

        MdQueryDTO queryOrg1 = new MdQueryDTO();
        List<DcMdWell> dcMdWellList = remoteDataManageService.selectWellList(queryOrg).getData();//查询所有井名称

        for (DcRportWellCheckVo item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                if (StringUtils.isNotEmpty(item.getOrganizationName())) {
                    if (organizationList != null && !organizationList.isEmpty()) {
                        DcMdOrganization organization = organizationList.stream().filter(a -> a.getName().equals(item.getOrganizationName())).findFirst().orElse(null);
                        if (organization != null) {
                            item.setOrganizationId(organization.getId().toString());
                        } else {
                            isFailure = true;
                            itemFailureMsg.append("国家名称不存在；");
                        }
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("国家名称不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("国家名称为空；");
                }

                if (StringUtils.isNotEmpty(item.getWellName())) {
                    if (dcMdWellList != null && !dcMdWellList.isEmpty()) {
                        DcMdWell dcMdWell = dcMdWellList.stream().filter(a -> a.getWellname().equals(item.getWellName())).findFirst().orElse(null);
                        if (dcMdWell != null) {
                            item.setWellId(dcMdWell.getId());
                        } else {
                            isFailure = true;
                            itemFailureMsg.append("井号名称不存在；");
                        }
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("井号名称不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("井号名称为空；");
                }

//                if (StringUtils.isNotEmpty(item.getRiskLevel())) {
//                    String riskType = DictUtils.getDictValue("risk_type", item.getRiskLevel());
//                    if (StringUtils.isNotEmpty(riskType)) {
//                        item.setRiskLevel(riskType);
//                    } else {
//                        isFailure = true;
//                        itemFailureMsg.append("分险级别不存在；");
//                    }
//                } else {
//                    isFailure = true;
//                    itemFailureMsg.append("分险级别为空；");
//                }
                if ("√".equals(item.getCheckDrillingBefore())) {
                    item.setCheckDrillingBefore("1");
                }
                if ("√".equals(item.getCheckDrillOil())) {
                    item.setCheckDrillOil("1");
                }
                item.setYear(DateUtils.getDate().substring(0,4));


                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }
                saveOrUpdate(item);
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
