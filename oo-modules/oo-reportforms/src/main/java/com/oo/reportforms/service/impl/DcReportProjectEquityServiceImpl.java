package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.StringUtils;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.reportforms.domain.vo.DcReportProjectEquityVo;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportProjectEquityMapper;
import com.oo.reportforms.domain.DcReportProjectEquity;
import com.oo.reportforms.service.IDcReportProjectEquityService;

import javax.servlet.http.HttpServletResponse;

/**
 * 国际公司项目权益情况Service业务层处理
 * 
 * @author oo
 * @date 2023-09-07
 */
@Service
public class DcReportProjectEquityServiceImpl extends ServiceImpl<DcReportProjectEquityMapper, DcReportProjectEquity> implements IDcReportProjectEquityService
{
    /**
     * 根据区块查询国际公司项目权益情况列表
     *
     * @param blockId 区块
     * @return 国际公司项目权益情况集合
     */
    public List<DcReportProjectEquityVo> selectList(String blockId)
    {
        return baseMapper.selectList(blockId);
    }
    /**
     * 查询国际公司项目权益情况列表
     * 
     * @param dcReportProjectEquity 国际公司项目权益情况
     * @return 国际公司项目权益情况
     */
    @Override
    public List<DcReportProjectEquity> selectDcReportProjectEquityList(DcReportProjectEquity dcReportProjectEquity)
    {
        return baseMapper.selectDcReportProjectEquityList(dcReportProjectEquity);
    }

    /**
     * 查询国际公司项目权益情况列表
     *
     * @param blockId 国际公司项目权益情况
     * @return 国际公司项目权益情况
     */
    @Override
    public List<DcReportProjectEquityVo> selectScreenList(String blockId)
    {
        return baseMapper.selectScreenList(blockId);
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String ids)
    {
        return baseMapper.deleteItemsByIds(ids);
    }

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportProjectEquityVo> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportProjectEquityVo item : dataList){
            if(item.getBlockName() != ""){
                try {
                    DcMdProject blockId = baseMapper.getBlockId(item.getBlockName());
                    item.setProjectId(blockId.getId().toString());
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
     * @param blockId
     */
    @Override
    public void export(HttpServletResponse response, String blockId) throws IOException {
        // excel模板路径
        String templateName = "（计划费控岗05）国际公司项目权益情况.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportProjectEquityVo> list = baseMapper.selectList(blockId);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }

        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        if(list.size() > 0) {
            // 读取模板内sheet内容
            XSSFSheet sheet = wb.getSheetAt(0);
            int m = 1;
            for (DcReportProjectEquityVo item : list) {
                XSSFRow row = sheet.createRow(2 + m);
                row.createCell(0).setCellValue(m);
                row.createCell(1).setCellValue(item.getCompanyName());
                row.createCell(2).setCellValue(item.getBlockName());
                row.createCell(3).setCellValue(item.getWorkerCompany());
                row.createCell(4).setCellValue(item.getWorkerEquity());
                row.createCell(5).setCellValue(item.getPartner1Company());
                row.createCell(6).setCellValue(item.getPartner1Equity());
                row.createCell(7).setCellValue(item.getPartner2Company());
                row.createCell(8).setCellValue(item.getPartner2Equity());
                row.createCell(9).setCellValue(item.getPartner3Company());
                row.createCell(10).setCellValue(item.getPartner3Equity());
                row.createCell(11).setCellValue(item.getPartner4Company());
                row.createCell(12).setCellValue(item.getPartner4Equity());
                row.createCell(13).setCellValue(item.getPartner5Company());
                row.createCell(14).setCellValue(item.getPartner5Equity());
                row.createCell(15).setCellValue(item.getPartner6Company());
                row.createCell(16).setCellValue(item.getPartner6Equity());
                row.createCell(17).setCellValue(item.getPartner7Company());
                row.createCell(18).setCellValue(item.getPartner7Equity());
                row.createCell(19).setCellValue(item.getPartner8Company());
                row.createCell(20).setCellValue(item.getPartner8Equity());
                row.createCell(21).setCellValue(item.getPartner9Company());
                row.createCell(22).setCellValue(item.getPartner9Equity());
                row.createCell(23).setCellValue(item.getPartner10Company());
                row.createCell(24).setCellValue(item.getPartner10Equity());
                row.createCell(25).setCellValue(item.getPartner11Company());
                row.createCell(26).setCellValue(item.getPartner11Equity());
                row.createCell(27).setCellValue(item.getPartner12Company());
                row.createCell(28).setCellValue(item.getPartner12Equity());
                row.createCell(29).setCellValue(item.getPartner13Company());
                row.createCell(30).setCellValue(item.getPartner13Equity());
                row.createCell(31).setCellValue(item.getPartner14Company());
                row.createCell(32).setCellValue(item.getPartner14Equity());
                row.createCell(33).setCellValue(item.getPartner15Company());
                row.createCell(34).setCellValue(item.getPartner15Equity());
                row.createCell(35).setCellValue(item.getPartner16Company());
                row.createCell(36).setCellValue(item.getPartner16Equity());
                row.createCell(37).setCellValue(item.getPartner17Company());
                row.createCell(38).setCellValue(item.getPartner17Equity());
                row.createCell(39).setCellValue(item.getPartner18Company());
                row.createCell(40).setCellValue(item.getPartner18Equity());
                row.createCell(41).setCellValue(item.getPartner19Company());
                row.createCell(42).setCellValue(item.getPartner19Equity());
                row.createCell(43).setCellValue(item.getPartner20Company());
                row.createCell(44).setCellValue(item.getPartner20Equity());
                row.createCell(45).setCellValue(item.getRemark());
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
                row.getCell(17).setCellStyle(defaultStyle);
                row.getCell(18).setCellStyle(defaultStyle);
                row.getCell(19).setCellStyle(defaultStyle);
                row.getCell(20).setCellStyle(defaultStyle);
                row.getCell(21).setCellStyle(defaultStyle);
                row.getCell(22).setCellStyle(defaultStyle);
                row.getCell(23).setCellStyle(defaultStyle);
                row.getCell(24).setCellStyle(defaultStyle);
                row.getCell(25).setCellStyle(defaultStyle);
                row.getCell(26).setCellStyle(defaultStyle);
                row.getCell(27).setCellStyle(defaultStyle);
                row.getCell(28).setCellStyle(defaultStyle);
                row.getCell(29).setCellStyle(defaultStyle);
                row.getCell(30).setCellStyle(defaultStyle);
                row.getCell(31).setCellStyle(defaultStyle);
                row.getCell(32).setCellStyle(defaultStyle);
                row.getCell(33).setCellStyle(defaultStyle);
                row.getCell(34).setCellStyle(defaultStyle);
                row.getCell(35).setCellStyle(defaultStyle);
                row.getCell(36).setCellStyle(defaultStyle);
                row.getCell(37).setCellStyle(defaultStyle);
                row.getCell(38).setCellStyle(defaultStyle);
                row.getCell(39).setCellStyle(defaultStyle);
                row.getCell(40).setCellStyle(defaultStyle);
                row.getCell(41).setCellStyle(defaultStyle);
                row.getCell(42).setCellStyle(defaultStyle);
                row.getCell(43).setCellStyle(defaultStyle);
                row.getCell(44).setCellStyle(defaultStyle);
                row.getCell(45).setCellStyle(defaultStyle);
                m++;
            }
            //获取最大列数  隐藏对应列
            Integer num = list.get(0).getMaxColumn();
            if(num == 1)
            {
                for (int i = 7; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 2){
                for (int i = 9; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 3){
                for (int i = 11; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 4){
                for (int i = 13; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 5){
                for (int i = 15; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 6){
                for (int i = 17; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 7){
                for (int i = 19; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 8){
                for (int i = 21; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 9){
                for (int i = 23; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 10){
                for (int i = 25; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 11){
                for (int i = 27; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 12){
                for (int i = 29; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 13){
                for (int i = 31; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 14){
                for (int i = 33; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 15){
                for (int i = 35; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 16){
                for (int i = 37; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 17){
                for (int i = 39; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 18){
                for (int i = 41; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else if(num == 19){
                for (int i = 43; i < 45; i++) {
                    sheet.setColumnHidden(i,true);
                }
            }
            else{
            }
        }
        ReportUtils.download(response, wb, templateName);
    }


}
