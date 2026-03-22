package com.oo.reportforms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.reportforms.domain.DcReportDrillWellAsses;
import com.oo.reportforms.domain.vo.DcReportDrillWellAssesVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.service.DcReportDrillWellAssesService;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class DcReportDrillWellAssesServiceImpl extends MppServiceImpl<DcReportDrillWellAssesMapper, DcReportDrillWellAsses> implements DcReportDrillWellAssesService {
    @Autowired
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;
    @Autowired
    private RemoteDataManageService remoteDataManageService;
    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response,String year,Long country) throws IOException {
        // excel模板路径
        String templateName = "（计划费控岗06）涉及钻完井的考核项目.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);

        QueryWrapper<DcReportDrillWellAsses> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(year)){
            queryWrapper.eq("year",year);
        }
        if(country != null){
            queryWrapper.eq("country",country);
        }
        List<DcReportDrillWellAssesVo> dcReportDrillWellAssesVos=new ArrayList<>();
        List<DcReportDrillWellAsses> dcReportDrillWellAssesList=dcReportDrillWellAssesMapper.selectList(queryWrapper);
        dcReportDrillWellAssesList.parallelStream().collect();


        for(DcReportDrillWellAsses dcReportDrillWellAsses:dcReportDrillWellAssesList){
            DcReportDrillWellAssesVo dcReportDrillWellAssesVo=new DcReportDrillWellAssesVo();
            BeanUtils.copyProperties(dcReportDrillWellAsses,dcReportDrillWellAssesVo);
            if(dcReportDrillWellAsses.getCountry()!=null) {
                dcReportDrillWellAssesVo.setName(dcReportDrillWellAssesMapper.selectName(dcReportDrillWellAsses.getCountry()));
            }
            dcReportDrillWellAssesVos.add(dcReportDrillWellAssesVo);
        }

        if (dcReportDrillWellAssesVos == null || dcReportDrillWellAssesVos.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 1;
        for (DcReportDrillWellAssesVo item : dcReportDrillWellAssesVos) {
            XSSFRow row = sheet.createRow(2 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getPerformanMetricsKey());
            row.createCell(3).setCellValue(item.getMetricsDefinedKey());
            row.createCell(4).setCellValue(item.getWeightKey());
            row.createCell(5).setCellValue(item.getBasicKey()== null ? "" : item.getBasicKey().toString());
            row.createCell(6).setCellValue(item.getTruggleKey()== null ? "" : item.getTruggleKey().toString());
            row.createCell(7).setCellValue(item.getChallengeKey()== null ? "" : item.getChallengeKey().toString());
            row.createCell(8).setCellValue(item.getForecastFinishSelfKey());
            row.createCell(9).setCellValue(item.getScoreSelfKey()== null ? "" : item.getScoreSelfKey().toString());
            row.createCell(10).setCellValue(item.getDescriptionSelfKey());
            row.createCell(11).setCellValue(item.getForecastFinishGroupKey());
            row.createCell(12).setCellValue(item.getScoreGroupKey()== null ? "" : item.getScoreGroupKey().toString());
            row.createCell(13).setCellValue(item.getDescriptionGroupKey());
            row.createCell(14).setCellValue(item.getScoreDeptGroupKeyName());
            row.createCell(15).setCellValue(item.getAssessMetricsDiff());
            row.createCell(16).setCellValue(item.getMetricsDefinedDiff());
            row.createCell(17).setCellValue(item.getBasicDiff()== null ? "" : item.getBasicDiff().toString());
            row.createCell(18).setCellValue(item.getTruggleDiff()== null ? "" : item.getTruggleDiff().toString());
            row.createCell(19).setCellValue(item.getChallengeDiff()== null ? "" : item.getChallengeDiff().toString());
            row.createCell(20).setCellValue(item.getForecastFinishSelfDiff());
            row.createCell(21).setCellValue(item.getScoreSelfDiff()== null ? "" : item.getScoreSelfDiff().toString());
            row.createCell(22).setCellValue(item.getDescriptionSelfDiff());
            row.createCell(23).setCellValue(item.getForecastFinishGroupDiff());
            row.createCell(24).setCellValue(item.getScoreGroupDiff()== null ? "" : item.getScoreGroupDiff().toString());
            row.createCell(25).setCellValue(item.getDescriptionGroupDiff());

            row.createCell(26).setCellValue(item.getScoreDeptGroupDiffName());
            row.createCell(27).setCellValue(item.getAssessMetricsGeneral());
            row.createCell(28).setCellValue(item.getBasicGeneral());
            row.createCell(29).setCellValue(item.getTruggleGeneral());
            row.createCell(30).setCellValue(item.getChallengeGeneral());
            row.createCell(31).setCellValue(item.getDeductedScoresSelfGeneral()== null ? "" : item.getDeductedScoresSelfGeneral().toString());
            row.createCell(32).setCellValue(item.getAddScoresSelfGeneral()== null ? "" : item.getAddScoresSelfGeneral().toString());
            row.createCell(33).setCellValue(item.getDescriptionSelfGeneral());
            row.createCell(34).setCellValue(item.getSupportStuffSelfGeneral());
            row.createCell(35).setCellValue(item.getDeductedScoresGroupGeneral()== null ? "" : item.getDeductedScoresGroupGeneral().toString());

            row.createCell(36).setCellValue(item.getAddScoresGroupGeneral()== null ? "" : item.getAddScoresGroupGeneral().toString());
            row.createCell(37).setCellValue(item.getDescriptionGroupGeneral());
            row.createCell(38).setCellValue(item.getKeySelfSummary());
            row.createCell(39).setCellValue(item.getDiffSelfSummary());
            row.createCell(40).setCellValue(item.getGeneralSelfSummary());
            row.createCell(41).setCellValue(item.getKeyGroupSummary());
            row.createCell(42).setCellValue(item.getDiffGroupSummary());
            row.createCell(43).setCellValue(item.getGeneralGroupSummary());
            row.createCell(44).setCellValue(item.getTotlaSummary() == null ? "" : item.getTotlaSummary().toString());

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
            row.getCell(12).setCellStyle(defaultStyle);
            row.getCell(13).setCellStyle(defaultStyle);
            row.getCell(14).setCellStyle(contentStyle);
            row.getCell(15).setCellStyle(contentStyle);
            row.getCell(16).setCellStyle(contentStyle);
            row.getCell(17).setCellStyle(contentStyle);
            row.getCell(18).setCellStyle(contentStyle);
            row.getCell(19).setCellStyle(contentStyle);
            row.getCell(20).setCellStyle(contentStyle);
            row.getCell(21).setCellStyle(contentStyle);
            row.getCell(22).setCellStyle(contentStyle);
            row.getCell(23).setCellStyle(contentStyle);
            row.getCell(24).setCellStyle(contentStyle);
            row.getCell(25).setCellStyle(contentStyle);
            row.getCell(26).setCellStyle(contentStyle);
            row.getCell(27).setCellStyle(contentStyle);
            row.getCell(28).setCellStyle(contentStyle);
            row.getCell(29).setCellStyle(contentStyle);
            row.getCell(30).setCellStyle(contentStyle);
            row.getCell(31).setCellStyle(contentStyle);
            row.getCell(32).setCellStyle(contentStyle);
            row.getCell(33).setCellStyle(contentStyle);
            row.getCell(34).setCellStyle(contentStyle);
            row.getCell(35).setCellStyle(contentStyle);
            row.getCell(36).setCellStyle(contentStyle);
            row.getCell(37).setCellStyle(contentStyle);
            row.getCell(38).setCellStyle(contentStyle);
            row.getCell(39).setCellStyle(contentStyle);
            row.getCell(40).setCellStyle(contentStyle);
            row.getCell(41).setCellStyle(contentStyle);
            row.getCell(42).setCellStyle(contentStyle);
            row.getCell(43).setCellStyle(contentStyle);
            row.getCell(44).setCellStyle(contentStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
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
     *
     * @param dataList
     * @return
     */
    @Override
    @DictMethod(dtoClass = DcReportDrillWellAssesVo.class)
    public String importData(List<DcReportDrillWellAssesVo> dataList) throws IOException {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        MdQueryDTO queryOrg = new MdQueryDTO();
        queryOrg.setType("2");
        List<DcMdOrganization> organizationList = remoteDataManageService.selectOrganizationList(queryOrg).getData();//查询国家名称

        for (DcReportDrillWellAssesVo item : dataList){
            DcReportDrillWellAsses dcReportDrillWellAsses=new DcReportDrillWellAsses();
            BeanUtils.copyProperties(item,dcReportDrillWellAsses);

            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                if (StringUtils.isNotEmpty(item.getName())) {
                    if (organizationList != null && !organizationList.isEmpty()) {
                        DcMdOrganization organization = organizationList.stream().filter(a -> a.getName().equals(item.getName())).findFirst().orElse(null);
                        if (organization != null) {
                            dcReportDrillWellAsses.setCountry(organization.getId());
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
                dcReportDrillWellAsses.setYear(DateUtils.getDate().substring(0,4));

                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }
                saveOrUpdateByMultiId(dcReportDrillWellAsses);
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
