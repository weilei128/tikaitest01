package com.oo.reportforms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.*;
import com.oo.reportforms.domain.vo.DcReportAssetBudgetVo;
import com.oo.reportforms.domain.vo.DcReportAssetBudgetVo2;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.reportforms.mapper.DcReportAssetBudgetMapper;
import com.oo.reportforms.mapper.DcReportAssetBudgetMonthMapper;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.service.DcReportAssetBudgetMonthService;
import com.oo.reportforms.service.DcReportAssetBudgetService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 年度预算-AssetBudget Service业务层处理
 *
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportAssetBudgetServiceImpl extends ServiceImpl<DcReportAssetBudgetMapper, DcReportAssetBudget> implements DcReportAssetBudgetService {
    @Autowired
    private DcReportAssetBudgetMapper dcReportAssetBudgetMapper;
    @Autowired
    private DcReportAssetBudgetMonthMapper dcReportAssetBudgetMonthMapper;
    @Autowired
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;
    @Autowired
    private DcReportAssetBudgetMonthService dcReportAssetBudgetMonthService;


    /**
     * 导出
     *
     * @param response
     * @param
     */
    @Override
    public void export(HttpServletResponse response, String organization_id, String year) throws Exception {
        // excel模板路径
        String templateName = "（计划费控岗01）年度预算asset.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportAssetBudgetVo> dcReportAssetBudgetVos=selectList(organization_id,year);


        if (dcReportAssetBudgetVos == null || dcReportAssetBudgetVos.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);


        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 0;
//        XSSFRow row1 = sheet.createRow(m);
//        row1.createCell(5).setCellValue(dcReportOutputWellBugdetVoList.get(0).getYear());//第一行的年份录入

        for (DcReportAssetBudgetVo item : dcReportAssetBudgetVos) {
            XSSFRow row = sheet.createRow(2 + m);
            row.createCell(0).setCellValue(1 + m);
            row.createCell(1).setCellValue(item.getCountryName());//业务单元
            row.createCell(2).setCellValue(item.getOrganizationName());//资产自资产
            row.createCell(3).setCellValue(item.getDeclareName());//declareName
            row.createCell(4).setCellValue(item.getBudgetFormal()== null ? "" : item.getBudgetFormal().toString());
            row.createCell(5).setCellValue(item.getBudgetFormal1()== null ? "" : item.getBudgetFormal1().toString());
            row.createCell(6).setCellValue(item.getBudgetFormal2()== null ? "" : item.getBudgetFormal2().toString());
            row.createCell(7).setCellValue(item.getBudgetFormal3()== null ? "" : item.getBudgetFormal3().toString());
            row.createCell(8).setCellValue(item.getBudgetFormal4()== null ? "" : item.getBudgetFormal4().toString());
            row.createCell(9).setCellValue(item.getBudgetFormal5()== null ? "" : item.getBudgetFormal5().toString());
            row.createCell(10).setCellValue(item.getBudgetFormal6()== null ? "" : item.getBudgetFormal6().toString());
            row.createCell(11).setCellValue(item.getBudgetFormal7()== null ? "" : item.getBudgetFormal7().toString());
            row.createCell(12).setCellValue(item.getBudgetFormal8()== null ? "" : item.getBudgetFormal8().toString());
            row.createCell(13).setCellValue(item.getBudgetFormal9()== null ? "" : item.getBudgetFormal9().toString());
            row.createCell(14).setCellValue(item.getBudgetFormal10()== null ? "" : item.getBudgetFormal10().toString());
            row.createCell(15).setCellValue(item.getBudgetFormal11()== null ? "" : item.getBudgetFormal11().toString());
            row.createCell(16).setCellValue(item.getBudgetFormal12()== null ? "" : item.getBudgetFormal12().toString());

            row.createCell(17).setCellValue(item.getBudgetApproval()== null ? "" : item.getBudgetApproval().toString());
            row.createCell(18).setCellValue(item.getBudgetPending1()== null ? "" : item.getBudgetPending1().toString());
            row.createCell(19).setCellValue(item.getBudgetPending2()== null ? "" : item.getBudgetPending2().toString());
            row.createCell(20).setCellValue(item.getBudgetPending3()== null ? "" : item.getBudgetPending3().toString());
            row.createCell(21).setCellValue(item.getBudgetPending4()== null ? "" : item.getBudgetPending4().toString());
            row.createCell(22).setCellValue(item.getBudgetPending5()== null ? "" : item.getBudgetPending5().toString());
            row.createCell(23).setCellValue(item.getBudgetPending6()== null ? "" : item.getBudgetPending6().toString());
            row.createCell(24).setCellValue(item.getBudgetPending7()== null ? "" : item.getBudgetPending7().toString());
            row.createCell(25).setCellValue(item.getBudgetPending8()== null ? "" : item.getBudgetPending8().toString());
            row.createCell(26).setCellValue(item.getBudgetPending9()== null ? "" : item.getBudgetPending9().toString());
            row.createCell(27).setCellValue(item.getBudgetPending10()== null ? "" : item.getBudgetPending10().toString());
            row.createCell(28).setCellValue(item.getBudgetPending11()== null ? "" : item.getBudgetPending11().toString());
            row.createCell(29).setCellValue(item.getBudgetPending12()== null ? "" : item.getBudgetPending12().toString());

            row.createCell(30).setCellValue(item.getBudgetTotal()== null ? "" : item.getBudgetTotal().toString());
            row.createCell(31).setCellValue(item.getBudgetTotal1()== null ? "" : item.getBudgetTotal1().toString());
            row.createCell(32).setCellValue(item.getBudgetTotal2()== null ? "" : item.getBudgetTotal2().toString());
            row.createCell(33).setCellValue(item.getBudgetTotal3()== null ? "" : item.getBudgetTotal3().toString());
            row.createCell(34).setCellValue(item.getBudgetTotal4()== null ? "" : item.getBudgetTotal4().toString());
            row.createCell(35).setCellValue(item.getBudgetTotal5()== null ? "" : item.getBudgetTotal5().toString());
            row.createCell(36).setCellValue(item.getBudgetTotal6()== null ? "" : item.getBudgetTotal6().toString());
            row.createCell(37).setCellValue(item.getBudgetTotal7()== null ? "" : item.getBudgetTotal7().toString());
            row.createCell(38).setCellValue(item.getBudgetTotal8()== null ? "" : item.getBudgetTotal8().toString());
            row.createCell(39).setCellValue(item.getBudgetTotal9()== null ? "" : item.getBudgetTotal9().toString());
            row.createCell(40).setCellValue(item.getBudgetTotal10()== null ? "" : item.getBudgetTotal10().toString());
            row.createCell(41).setCellValue(item.getBudgetTotal11()== null ? "" : item.getBudgetTotal11().toString());
            row.createCell(42).setCellValue(item.getBudgetTotal12()== null ? "" : item.getBudgetTotal12().toString());

            row.createCell(43).setCellValue(item.getWorkFormal()== null ? "" : item.getWorkFormal().toString());
            row.createCell(44).setCellValue(item.getRealityFormal1()== null ? "" : item.getRealityFormal1().toString());
            row.createCell(45).setCellValue(item.getRealityFormal2()== null ? "" : item.getRealityFormal2().toString());
            row.createCell(46).setCellValue(item.getRealityFormal3()== null ? "" : item.getRealityFormal3().toString());
            row.createCell(47).setCellValue(item.getRealityFormal4()== null ? "" : item.getRealityFormal4().toString());
            row.createCell(48).setCellValue(item.getRealityFormal5()== null ? "" : item.getRealityFormal5().toString());
            row.createCell(49).setCellValue(item.getRealityFormal6()== null ? "" : item.getRealityFormal6().toString());
            row.createCell(50).setCellValue(item.getRealityFormal7()== null ? "" : item.getRealityFormal7().toString());
            row.createCell(51).setCellValue(item.getRealityFormal8()== null ? "" : item.getRealityFormal8().toString());
            row.createCell(52).setCellValue(item.getRealityFormal9()== null ? "" : item.getRealityFormal9().toString());
            row.createCell(53).setCellValue(item.getRealityFormal10()== null ? "" : item.getRealityFormal10().toString());
            row.createCell(54).setCellValue(item.getRealityFormal11()== null ? "" : item.getRealityFormal11().toString());
            row.createCell(55).setCellValue(item.getRealityFormal12()== null ? "" : item.getRealityFormal12().toString());

            row.createCell(56).setCellValue(item.getWorkApproval()== null ? "" : item.getWorkApproval().toString());
            row.createCell(57).setCellValue(item.getRealityPending1()== null ? "" : item.getRealityPending1().toString());
            row.createCell(58).setCellValue(item.getRealityPending2()== null ? "" : item.getRealityPending2().toString());
            row.createCell(59).setCellValue(item.getRealityPending3()== null ? "" : item.getRealityPending3().toString());
            row.createCell(60).setCellValue(item.getRealityPending4()== null ? "" : item.getRealityPending4().toString());
            row.createCell(61).setCellValue(item.getRealityPending5()== null ? "" : item.getRealityPending5().toString());
            row.createCell(62).setCellValue(item.getRealityPending6()== null ? "" : item.getRealityPending6().toString());
            row.createCell(63).setCellValue(item.getRealityPending7()== null ? "" : item.getRealityPending7().toString());
            row.createCell(64).setCellValue(item.getRealityPending8()== null ? "" : item.getRealityPending8().toString());
            row.createCell(65).setCellValue(item.getRealityPending9()== null ? "" : item.getRealityPending9().toString());
            row.createCell(66).setCellValue(item.getRealityPending10()== null ? "" : item.getRealityPending10().toString());
            row.createCell(67).setCellValue(item.getRealityPending11()== null ? "" : item.getRealityPending11().toString());
            row.createCell(68).setCellValue(item.getRealityPending12()== null ? "" : item.getRealityPending12().toString());

            row.createCell(69).setCellValue(item.getWorkTotal()== null ? "" : item.getWorkTotal().toString());
            row.createCell(70).setCellValue(item.getRealityTotal1()== null ? "" : item.getRealityTotal1().toString());
            row.createCell(71).setCellValue(item.getRealityTotal2()== null ? "" : item.getRealityTotal2().toString());
            row.createCell(72).setCellValue(item.getRealityTotal3()== null ? "" : item.getRealityTotal3().toString());
            row.createCell(73).setCellValue(item.getRealityTotal4()== null ? "" : item.getRealityTotal4().toString());
            row.createCell(74).setCellValue(item.getRealityTotal5()== null ? "" : item.getRealityTotal5().toString());
            row.createCell(75).setCellValue(item.getRealityTotal6()== null ? "" : item.getRealityTotal6().toString());
            row.createCell(76).setCellValue(item.getRealityTotal7()== null ? "" : item.getRealityTotal7().toString());
            row.createCell(77).setCellValue(item.getRealityTotal8()== null ? "" : item.getRealityTotal8().toString());
            row.createCell(78).setCellValue(item.getRealityTotal9()== null ? "" : item.getRealityTotal9().toString());
            row.createCell(79).setCellValue(item.getRealityTotal10()== null ? "" : item.getRealityTotal10().toString());
            row.createCell(80).setCellValue(item.getRealityTotal11()== null ? "" : item.getRealityTotal11().toString());
            row.createCell(81).setCellValue(item.getRealityTotal12()== null ? "" : item.getRealityTotal12().toString());

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
            row.getCell(45).setCellStyle(contentStyle);
            row.getCell(46).setCellStyle(contentStyle);
            row.getCell(47).setCellStyle(contentStyle);
            row.getCell(48).setCellStyle(contentStyle);
            row.getCell(49).setCellStyle(contentStyle);
            row.getCell(50).setCellStyle(contentStyle);
            row.getCell(51).setCellStyle(contentStyle);
            row.getCell(52).setCellStyle(contentStyle);
            row.getCell(53).setCellStyle(contentStyle);
            row.getCell(54).setCellStyle(contentStyle);
            row.getCell(55).setCellStyle(contentStyle);
            row.getCell(56).setCellStyle(contentStyle);
            row.getCell(57).setCellStyle(contentStyle);
            row.getCell(58).setCellStyle(contentStyle);
            row.getCell(59).setCellStyle(contentStyle);
            row.getCell(60).setCellStyle(contentStyle);
            row.getCell(61).setCellStyle(contentStyle);
            row.getCell(62).setCellStyle(contentStyle);
            row.getCell(63).setCellStyle(contentStyle);
            row.getCell(64).setCellStyle(contentStyle);
            row.getCell(65).setCellStyle(contentStyle);
            row.getCell(66).setCellStyle(contentStyle);
            row.getCell(67).setCellStyle(contentStyle);
            row.getCell(68).setCellStyle(contentStyle);
            row.getCell(69).setCellStyle(contentStyle);
            row.getCell(70).setCellStyle(contentStyle);
            row.getCell(71).setCellStyle(contentStyle);
            row.getCell(72).setCellStyle(contentStyle);
            row.getCell(73).setCellStyle(contentStyle);
            row.getCell(74).setCellStyle(contentStyle);
            row.getCell(75).setCellStyle(contentStyle);
            row.getCell(76).setCellStyle(contentStyle);
            row.getCell(77).setCellStyle(contentStyle);
            row.getCell(78).setCellStyle(contentStyle);
            row.getCell(79).setCellStyle(contentStyle);
            row.getCell(80).setCellStyle(contentStyle);
            row.getCell(81).setCellStyle(contentStyle);
            m++;
        }

        ReportUtils.download(response, wb, templateName);
    }

    /**
     * 保存/更新
     *
     * @param
     * @param
     */
    @Override
    public String add(List<DcReportAssetBudgetVo> dcReportAssetBudgetVoList) throws Exception {

        for(DcReportAssetBudgetVo dcReportAssetBudgetVo:dcReportAssetBudgetVoList){
            if("0".equals(save1(dcReportAssetBudgetVo))){
                return "保存/更新失败";
            }
        }
        return "保存/更新成功";
    }

    public String save1(DcReportAssetBudgetVo dcReportAssetBudgetVo){
        List<DcReportAssetBudgetMonth> arraryList=new ArrayList<>();

        DcReportAssetBudgetVo2 dcReportAssetBudgetVo2=new DcReportAssetBudgetVo2();
        if(StringUtils.isEmpty(dcReportAssetBudgetVo.getId())){
            UUID uuid = UUID.randomUUID();
            dcReportAssetBudgetVo.setId(uuid.toString());
        }
        DcReportAssetBudget dcReportAssetBudget=new DcReportAssetBudget();
        BeanUtils.copyProperties(dcReportAssetBudgetVo,dcReportAssetBudgetVo2);
        BeanUtils.copyProperties(dcReportAssetBudgetVo,dcReportAssetBudget);

        dcReportAssetBudget.setYear(DateUtils.getDate().substring(0,4));

        if(!this.saveOrUpdate(dcReportAssetBudget)){
            return "0";
        }
        RowConvertColUtil.coverObjToList1(dcReportAssetBudgetVo.getId(),dcReportAssetBudgetVo2,arraryList);
        if(!dcReportAssetBudgetMonthService.saveOrUpdateBatchByMultiId(arraryList)){
            return "0";
        }
        return "1";
    }


        /**
         * 查询
         *
         * @param
         * @param
         */
    @Override
    public List<DcReportAssetBudgetVo> selectList(String organization_id, String year) throws Exception{
        QueryWrapper<DcReportAssetBudget> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(organization_id)){
            queryWrapper.eq("organization_id",organization_id);
        }
        if(StringUtils.isNotEmpty(year)){
            queryWrapper.eq("year",year);
        }
        queryWrapper.eq("del_flag","0");

        List<DcReportAssetBudget> dcReportAssetBudgetList=dcReportAssetBudgetMapper.selectList(queryWrapper);
        List<Map<String, Object>> maps=new ArrayList<>();
        Map<String, Object> map=new HashMap<String,Object>();
        DcReportAssetBudgetVo book=new DcReportAssetBudgetVo();
        List<DcReportAssetBudgetVo> dcReportAssetBudgetVos=new ArrayList<>();

        for(DcReportAssetBudget dcReportAssetBudget:dcReportAssetBudgetList){
            List<DcReportAssetBudgetMonth> dcReportAssetBudgetMonths=dcReportAssetBudgetMapper.selectDcReportOutputWellBugdetYear(dcReportAssetBudget.getId());
            String[] fixedColumn = {"assetId"};
            String[] fixedColumnName = {"ID"};
            // 要行转列的List,要行转列的字段,固定列字段数组,行转列对应值列的字段,是否返回表头,固定列字段名称数组,定义空值补数
            // 行转列生成Map<String, Object> map
            if(dcReportAssetBudgetMonths.size()>0) {
                maps = RowConvertColUtil.doConvertReturnObj(dcReportAssetBudgetMonths, "month", fixedColumn, "value", true, fixedColumnName, null).getDataList();
                book = JSON.parseObject(JSON.toJSONString(maps.get(0)), DcReportAssetBudgetVo.class);
            }//map转DcReportOutputWellBugdetVo实体类
            BeanUtils.copyProperties(dcReportAssetBudget,book);
            if(StringUtils.isNotEmpty(dcReportAssetBudget.getOrganizationId())) {
                book.setOrganizationName(dcReportDrillWellAssesMapper.selectName((dcReportAssetBudget.getOrganizationId())));
                book.setCountryName(dcReportDrillWellAssesMapper.selectCountryName(dcReportAssetBudget.getOrganizationId()));

            }
            dcReportAssetBudgetVos.add(book);
        }
        return dcReportAssetBudgetVos;
    }


    /**
     * 导入数据
     * @param dataList
     * @return
     */
    @Override
    @DictMethod(dtoClass = DcReportAssetBudgetVo.class)
    public String importData(List<DcReportAssetBudgetVo> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String ncStatus="";
        for (DcReportAssetBudgetVo item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();

            try {
                //资产子资产 验证
                if (StringUtils.isNotEmpty(item.getOrganizationName())) {
                    ncStatus=dcReportDrillWellAssesMapper.organizationId(item.getOrganizationName());
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
                item.setYear(DateUtils.getDate().substring(0,4));

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
