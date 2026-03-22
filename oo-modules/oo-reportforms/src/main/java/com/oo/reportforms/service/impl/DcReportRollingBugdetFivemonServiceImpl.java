package com.oo.reportforms.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemonMonth;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemonMonth;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetFivemonVo;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetFivemonVo2;
import com.oo.reportforms.domain.vo.DcReportRollingBugdetOnemonVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.service.DcReportRollingBugdetFivemonMonthService;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.reportforms.utils.RowConvertColUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetFivemonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemon;
import com.oo.reportforms.service.DcReportRollingBugdetFivemonService;

import javax.servlet.http.HttpServletResponse;

/**
 * 滚动预测5+7Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetFivemonServiceImpl extends ServiceImpl<DcReportRollingBugdetFivemonMapper, DcReportRollingBugdetFivemon> implements DcReportRollingBugdetFivemonService
{
    @Autowired(required = false)
    private DcReportRollingBugdetFivemonMapper dcReportRollingBugdetFivemonMapper;
    @Autowired(required = false)
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;
    @Autowired(required = false)
    private DcReportRollingBugdetFivemonMonthService dcReportRollingBugdetFivemonMonthService;

    /**
     * 查询滚动预测5+7列表
     * 
     * @param dcReportRollingBugdetFivemon 滚动预测5+7
     * @return 滚动预测5+7
     */
    @Override
    public List<DcReportRollingBugdetFivemon> selectDcReportRollingBugdetFivemonList(DcReportRollingBugdetFivemon dcReportRollingBugdetFivemon)
    {
        return baseMapper.selectDcReportRollingBugdetFivemonList(dcReportRollingBugdetFivemon);
    }

    /**
     * 查询
     */
    @Override
    public List<DcReportRollingBugdetFivemonVo> selectList(String organizationId, String year,String budgetSubjects) throws Exception{
        QueryWrapper<DcReportRollingBugdetFivemon> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(organizationId)) {
            queryWrapper.eq("organization_id", organizationId);
        }
        if (StringUtils.isNotEmpty(year)) {
            queryWrapper.eq("year", year);
        }
        if (StringUtils.isNotEmpty(budgetSubjects)) {
            queryWrapper.eq("budget_subjects", budgetSubjects);
        }
        queryWrapper.eq("del_flag", "0");

        List<DcReportRollingBugdetFivemon> dcReportOutputWellBugdetList = dcReportRollingBugdetFivemonMapper.selectList(queryWrapper);
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        DcReportRollingBugdetFivemonVo book = new DcReportRollingBugdetFivemonVo();
        List<DcReportRollingBugdetFivemonVo> dcReportOutputWellBugdetVoList = new ArrayList<>();
        String dictLabel ="";

        for (DcReportRollingBugdetFivemon dcReportRollingBugdetFivemon : dcReportOutputWellBugdetList) {
            List<DcReportRollingBugdetFivemonMonth> dcReportRollingBugdetFivemonMonthsList = dcReportRollingBugdetFivemonMapper.selectDcReportRollingBugdetFivemonYear(dcReportRollingBugdetFivemon.getId());
            String[] fixedColumn = {"rollingBugdetId"};
            String[] fixedColumnName = {"ID"};
            // 要行转列的List,要行转列的字段,固定列字段数组,行转列对应值列的字段,是否返回表头,固定列字段名称数组,定义空值补数
            // 行转列生成Map<String, Object> map
            if (dcReportRollingBugdetFivemonMonthsList.size() > 0) {
                maps = RowConvertColUtil.doConvertReturnObj(dcReportRollingBugdetFivemonMonthsList, "month", fixedColumn, "value", true, fixedColumnName, null).getDataList();
                book = JSON.parseObject(JSON.toJSONString(maps.get(0)), DcReportRollingBugdetFivemonVo.class);
            }//map转DcReportOutputWellBugdetVo实体类
            BeanUtils.copyProperties(dcReportRollingBugdetFivemon, book);
            if (StringUtils.isNotEmpty(dcReportRollingBugdetFivemon.getOrganizationId())) {
                book.setOrganizationName(dcReportDrillWellAssesMapper.selectName((dcReportRollingBugdetFivemon.getOrganizationId())));
            }
            if (StringUtils.isNotEmpty(dcReportRollingBugdetFivemon.getType())) {
                dictLabel = DictUtils.getDictLabel("dc_pk_well_type", dcReportRollingBugdetFivemon.getType());
                book.setType(dictLabel == null ? "" : dictLabel);
                if (StringUtils.isNotEmpty(dcReportRollingBugdetFivemon.getBudgetSubjects())) {
                    dictLabel = DictUtils.getDictLabel("well_config", dcReportRollingBugdetFivemon.getBudgetSubjects());
                    book.setBudgetSubjects(dictLabel == null ? "" : dictLabel);
                }
            }
            dcReportOutputWellBugdetVoList.add(book);
        }
        return dcReportOutputWellBugdetVoList;
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
     * 修改/保存
     *
     * @param
     * @param
     */
    @Override
    public String add(List<DcReportRollingBugdetFivemonVo> dcReportRollingBugdetFivemonVos) throws Exception{
        for(DcReportRollingBugdetFivemonVo dcReportRollingBugdetFivemonVo:dcReportRollingBugdetFivemonVos){
            if("0".equals(save1(dcReportRollingBugdetFivemonVo))){
                return "保存/更新失败";
            }
        }
        return "保存/更新成功";
    }
    @DictMethod(dtoClass = DcReportRollingBugdetFivemonVo.class)
    public String save1(DcReportRollingBugdetFivemonVo dcReportRollingBugdetFivemonVo) {
        List<DcReportRollingBugdetOnemonMonth> arraryList = new ArrayList<>();

        DcReportRollingBugdetFivemonVo2 dcReportRollingBugdetFivemonVo2 = new DcReportRollingBugdetFivemonVo2();
        if (StringUtils.isEmpty(dcReportRollingBugdetFivemonVo.getId())) {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().replace("-","");
            dcReportRollingBugdetFivemonVo.setId(id);
        }
        DcReportRollingBugdetFivemon dcReportRollingBugdetFivemon = new DcReportRollingBugdetFivemon();
        BeanUtils.copyProperties(dcReportRollingBugdetFivemonVo, dcReportRollingBugdetFivemonVo2);
        BeanUtils.copyProperties(dcReportRollingBugdetFivemonVo, dcReportRollingBugdetFivemon);

        dcReportRollingBugdetFivemon.setYear(DateUtils.getDate().substring(0,4));

        if(!this.saveOrUpdate(dcReportRollingBugdetFivemon)){
            return "0";
        }

        RowConvertColUtil.coverObjToListRollingBugdet(dcReportRollingBugdetFivemonVo.getId(), dcReportRollingBugdetFivemonVo2, arraryList);
        List<DcReportRollingBugdetFivemonMonth> arraryList2 = new ArrayList<>();
        for (DcReportRollingBugdetOnemonMonth item:arraryList) {
            DcReportRollingBugdetFivemonMonth month = new DcReportRollingBugdetFivemonMonth();
            BeanUtils.copyProperties(item, month);
            arraryList2.add(month);
        }
        if(!dcReportRollingBugdetFivemonMonthService.saveOrUpdateBatchByMultiId(arraryList2)){
            return "0";
        }
        return "1";
    }

    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response, String organizationId, String year, String budgetSubjects) throws Exception {
        // excel模板路径
        String templateName = "（计划费控岗03）滚动预测5+7VM0(导出).xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportRollingBugdetFivemonVo> dcReportRollingBugdetOnemonVoList = selectList(organizationId,year,budgetSubjects);
        if (dcReportRollingBugdetOnemonVoList == null || dcReportRollingBugdetOnemonVoList.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 0;

        Calendar calendar = Calendar.getInstance();
        int years = calendar.get(calendar.YEAR);

        //根据年份信息修改列名  5 ,6,20,21
        XSSFRow titleRow = sheet.getRow(8);
        titleRow.getCell(5).setCellValue(String.valueOf(years) + "年度预算");
        titleRow.getCell(6).setCellValue(String.valueOf(years) + " 5+7滚动预测（预算）");
        titleRow.getCell(20).setCellValue(String.valueOf(years) + "工作量");
        titleRow.getCell(21).setCellValue(String.valueOf(years) + " 5+7滚动预测（工作量）");


        for (DcReportRollingBugdetFivemonVo item : dcReportRollingBugdetOnemonVoList) {
            XSSFRow row = sheet.createRow(2 + m);
            row.createCell(0).setCellValue(1 + m);
            row.createCell(1).setCellValue(item.getOrganizationName());
            row.createCell(2).setCellValue(item.getType());
            row.createCell(3).setCellValue(item.getDeclareName());
            row.createCell(4).setCellValue(item.getBudgetSubjects());

            row.createCell(5).setCellValue(item.getForecastsYear()== null ? "" : item.getForecastsYear().toString());
            row.createCell(6).setCellValue(item.getForecasts()== null ? "" : item.getForecasts().toString());
            row.createCell(7).setCellValue(item.getForecasts1()== null ? "" : item.getForecasts1().toString());
            row.createCell(8).setCellValue(item.getForecasts2()== null ? "" : item.getForecasts2().toString());
            row.createCell(9).setCellValue(item.getForecasts3()== null ? "" : item.getForecasts3().toString());
            row.createCell(10).setCellValue(item.getForecasts4()== null ? "" : item.getForecasts4().toString());
            row.createCell(11).setCellValue(item.getForecasts5()== null ? "" : item.getForecasts5().toString());
            row.createCell(12).setCellValue(item.getForecasts6()== null ? "" : item.getForecasts6().toString());
            row.createCell(13).setCellValue(item.getForecasts7()== null ? "" : item.getForecasts7().toString());
            row.createCell(14).setCellValue(item.getForecasts8()== null ? "" : item.getForecasts8().toString());
            row.createCell(15).setCellValue(item.getForecasts9()== null ? "" : item.getForecasts9().toString());
            row.createCell(16).setCellValue(item.getForecasts10()== null ? "" : item.getForecasts10().toString());
            row.createCell(17).setCellValue(item.getForecasts11()== null ? "" : item.getForecasts11().toString());
            row.createCell(18).setCellValue(item.getForecasts12()== null ? "" : item.getForecasts12().toString());
            row.createCell(19).setCellValue(item.getForecastsDiff()== null ? "" : item.getForecastsDiff().toString());

            row.createCell(20).setCellValue(item.getWorkYear()== null ? "" : item.getWorkYear().toString());
            row.createCell(21).setCellValue(item.getWorkForecasts()== null ? "" : item.getWorkForecasts().toString());
            row.createCell(22).setCellValue(item.getWorkForecasts1()== null ? "" : item.getWorkForecasts1().toString());
            row.createCell(23).setCellValue(item.getWorkForecasts2()== null ? "" : item.getWorkForecasts2().toString());
            row.createCell(24).setCellValue(item.getWorkForecasts3()== null ? "" : item.getWorkForecasts3().toString());
            row.createCell(25).setCellValue(item.getWorkForecasts4()== null ? "" : item.getWorkForecasts4().toString());
            row.createCell(26).setCellValue(item.getWorkForecasts5()== null ? "" : item.getWorkForecasts5().toString());
            row.createCell(27).setCellValue(item.getWorkForecasts6()== null ? "" : item.getWorkForecasts6().toString());
            row.createCell(28).setCellValue(item.getWorkForecasts7()== null ? "" : item.getWorkForecasts7().toString());
            row.createCell(29).setCellValue(item.getWorkForecasts8()== null ? "" : item.getWorkForecasts8().toString());
            row.createCell(30).setCellValue(item.getWorkForecasts9()== null ? "" : item.getWorkForecasts9().toString());
            row.createCell(31).setCellValue(item.getWorkForecasts10()== null ? "" : item.getWorkForecasts10().toString());
            row.createCell(32).setCellValue(item.getWorkForecasts11()== null ? "" : item.getWorkForecasts11().toString());
            row.createCell(33).setCellValue(item.getWorkForecasts12()== null ? "" : item.getWorkForecasts12().toString());
            row.createCell(34).setCellValue(item.getWorkDiff()== null ? "" : item.getWorkDiff().toString());


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
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }

    /**
     * 导入
     */
    @Override
    @DictMethod(dtoClass = DcReportRollingBugdetFivemonVo.class)
    public String importData(List<DcReportRollingBugdetFivemonVo> dataList){
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportRollingBugdetFivemonVo item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();

            try {
                //资产子资产 验证
                if (StringUtils.isNotEmpty(item.getOrganizationName())) {
                    String ncStatus=dcReportDrillWellAssesMapper.organizationId(item.getOrganizationName());
                    if (StringUtils.isNotEmpty(ncStatus)) {
                        item.setOrganizationId(ncStatus);
                    } else {
                        isFailure = true;
                        itemFailureMsg.append("资产子资产不存在；");
                    }
                } else {
                    isFailure = true;
                    itemFailureMsg.append("资产子资产为空；");
                }
                //井类型
                if (StringUtils.isEmpty(item.getType())) {
                    isFailure = true;
                    itemFailureMsg.append("井类型为空；");
                }
                //预算科目
                if (StringUtils.isEmpty(item.getBudgetSubjects())) {
                    isFailure = true;
                    itemFailureMsg.append("预算科目为空；");
                }
                item.setYear(DateUtils.getDate().substring(0,4));
                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }

                save1(item);
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
}
