package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.reportforms.domain.*;
import com.oo.reportforms.domain.vo.CensorNameVo;
import com.oo.reportforms.domain.vo.DcReportDesionCensorScreenExamination;
import com.oo.reportforms.domain.vo.DcReportDesionCensorSummaryExamination;
import com.oo.reportforms.domain.vo.ScreenExaminationVo;
import com.oo.reportforms.mapper.DcReportDesionCensorUserMapper;
import com.oo.reportforms.service.IDcReportDesionCensorUserService;
import com.oo.reportforms.service.IDcReportExpertLibraryService;
import com.oo.reportforms.utils.ReportUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportDesionCensorMapper;
import com.oo.reportforms.service.IDcReportDesionCensorService;

import javax.servlet.http.HttpServletResponse;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总Service业务层处理
 *
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportDesionCensorServiceImpl extends ServiceImpl<DcReportDesionCensorMapper, DcReportDesionCensor> implements IDcReportDesionCensorService
{
    @Autowired(required = false)
    private DcReportDesionCensorUserMapper dcReportDesionCensorUserMapper;
    @Autowired(required = false)
    private DcReportDesionCensorMapper dcReportDesionCensorMapper;

    private final IDcReportDesionCensorUserService dcReportDesionCensorUserService;
    private final IDcReportExpertLibraryService dcReportExpertLibraryService;

    public DcReportDesionCensorServiceImpl(IDcReportDesionCensorUserService dcReportDesionCensorUserService, IDcReportExpertLibraryService dcReportExpertLibraryService) {
        this.dcReportDesionCensorUserService = dcReportDesionCensorUserService;
        this.dcReportExpertLibraryService = dcReportExpertLibraryService;
    }

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总列表
     *
     * @param designCensorName （技术管理岗）前期研究及设计审查会汇总-设计审查汇总
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总
     */
    @Override
    public List<DcReportDesionCensorUserView> selectDcReportDesionCensorList(String designCensorName, Date beginTime, Date endTime)
    {
        return baseMapper.selectDcReportDesionCensorList(designCensorName,beginTime,endTime);
    }


    @Override
    public Map<String, List> screenExamination(Date beginTime, Date endTime){
        List<String> stringList = Arrays.asList("深水", "深水岩下", "陆地盐膏层","油砂","大位移","高温高压","其它");

        Map<String, List> cellMap = new HashMap<String, List>();
        List<ScreenExaminationVo> keywordsList=new ArrayList<>();

        for(String keywords:stringList){
            if(keywords.equals("其它")){
                keywordsList = dcReportDesionCensorUserMapper.screenExamination1(beginTime,endTime);
            }else {
                keywordsList = dcReportDesionCensorUserMapper.screenExamination(keywords,beginTime,endTime);
            }

            cellMap.put(keywords, keywordsList);
        }
        keywordsList = dcReportDesionCensorUserMapper.screenExamination2(beginTime,endTime);
        cellMap.put("合计", keywordsList);
        return cellMap;
    }

    /**
    * 筛选表
    * */
    public List<DcReportDesionCensorScreenExamination> selectScreenExaminationList()
    {
        List<DcReportDesionCensorScreenExamination> ScreenExaminationList = new ArrayList<>();
        List<ScreenExaminationVo> screenExaminationVos = dcReportDesionCensorUserMapper.screenExamination3();
        List<ScreenExaminationVo> listnew = screenExaminationVos.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ScreenExaminationVo::getName))),
                        ArrayList::new)
                );
        for (ScreenExaminationVo item:listnew) {
            DcReportDesionCensorScreenExamination screenExamination = new DcReportDesionCensorScreenExamination();
            screenExamination.setCensorName(item.getName());
            ScreenExaminationList.add(screenExamination);
        }

        //("深水", "深水岩下", "陆地盐膏层","油砂","大位移","高温高压","其它");
        for (DcReportDesionCensorScreenExamination item:ScreenExaminationList) {
            Integer Examination1 =0;Integer Examination2 =0;Integer Examination3 =0;
            Integer Examination4 =0;Integer Examination5 =0;Integer Examination6 =0;
            Integer Examination7 =0;Integer sum =0;
            for (ScreenExaminationVo vo:screenExaminationVos) {
                if(item.getCensorName().equals(vo.getName())){
                    if(vo.getCount().contains("深水")){
                        Examination1++;
                    }else if(vo.getCount().contains("深水岩下")){
                        Examination2++;
                    }else if(vo.getCount().contains("陆地盐膏层")){
                        Examination3++;
                    }else if(vo.getCount().contains("油砂")){
                        Examination4++;
                    }else if(vo.getCount().contains("大位移")){
                        Examination5++;
                    }else if(vo.getCount().contains("高温高压")){
                        Examination6++;
                    }else{
                        Examination7++;
                    }
                }
            }

            sum = Examination1 + Examination2 + Examination3 + Examination4 + Examination5 + Examination6 + Examination7;
            item.setDeepWater(String.valueOf(Examination1));
            item.setUnderDeepWaterRocks(String.valueOf(Examination2));
            item.setLandSaltGypsumLayer(String.valueOf(Examination3));
            item.setOilSand(String.valueOf(Examination4));
            item.setLargeDisplacement(String.valueOf(Examination5));
            item.setHighTemperatureHighPressure(String.valueOf(Examination6));
            item.setOther(String.valueOf(Examination7));
            item.setAmountTo(String.valueOf(sum));
        }
        return ScreenExaminationList;
    }

    /**
     * 导出
     *
     * @param response
     * @param designCensorName
     */
    @Override
    public void export(HttpServletResponse response,String designCensorName, Date beginTime, Date endTime) throws IOException {
        // excel模板路径
        String templateName = "（技术管理岗）前期研究及设计审查会汇总.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportDesionCensorUserView> list = baseMapper.selectDcReportDesionCensorList(designCensorName,beginTime,endTime);
        List<DcReportDesionCensorScreenExamination> ScreenExaminationList = selectScreenExaminationList();//筛选表
        List<DcReportDesionCensorSummaryExamination> SummaryExamination = baseMapper.getSummaryExamination();//汇总表

        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }
        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);

        for (int i = 0; i <= 2; i++) { //类型 1:设计审查汇总  2:筛选表 3:汇总
            String titleName = "";
            switch (i) {
                case 0:
                    titleName = "前期研究及设计审查汇总";
                    if(list.size() > 0){
                        Date minDate = list.stream().map(DcReportDesionCensorUserView::getCensonBeginDate).filter(Objects::nonNull).min(Comparator.comparing(a -> a)).orElse(null);
                        Date maxDate = list.stream().map(DcReportDesionCensorUserView::getCensonEndDate).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse(null);
                        String minYear = "";
                        String maxYear = "";
                        String title = "";
                        if(minDate != null && maxDate != null)
                        {
                            minYear = DateUtils.parseDateToStr("yyyy", minDate);
                            maxYear = DateUtils.parseDateToStr("yyyy", maxDate);
                            title = "(" + (minYear.equals(maxYear) ? minYear : minYear + "-" + maxYear) + "年度)" + titleName;

                        }else{
                            title = titleName;
                        }
                        // 读取模板内sheet内容
                        XSSFSheet sheet = wb.getSheetAt(0);
                        int m = 1;
                        // 更换标题
                        XSSFRow titleRow = sheet.getRow(0);
                        titleRow.getCell(0).setCellValue(title);
                        for (DcReportDesionCensorUserView item : list)  {
                            XSSFRow row = sheet.createRow(1 + m);
                            row.createCell(0).setCellValue(m);
                            row.createCell(1).setCellValue(item.getDesignCensorName());
                            row.createCell(2).setCellValue(item.getCensonBeginDate() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getCensonBeginDate()) + "-" + DateUtils.parseDateToStr("yyyy.M.d", item.getCensonEndDate()));
                            row.createCell(3).setCellValue(item.getKeywords());
                            row.createCell(4).setCellValue(item.getExpertNum() == null ? null : item.getExpertNum().toString());
                            row.createCell(5).setCellValue(item.getCensorName());
                            row.createCell(6).setCellValue(item.getExpertOpinion());
                            row.createCell(7).setCellValue(item.getWellCount() == null ? null : item.getWellCount().toString());
                            row.createCell(8).setCellValue(item.getWaterDepth() == null ? null : item.getWaterDepth().toString());
                            row.createCell(9).setCellValue(item.getEconomizeCost() == null ? null : item.getEconomizeCost().toString());
                            row.createCell(10).setCellValue(item.getEconomizeCostSum() == null ? null : item.getEconomizeCostSum().toString());
                            row.createCell(11).setCellValue(item.getRemark());
                            row.getCell(0).setCellStyle(defaultStyle);
                            row.getCell(1).setCellStyle(defaultStyle);
                            row.getCell(2).setCellStyle(defaultStyle);
                            row.getCell(3).setCellStyle(defaultStyle);
                            row.getCell(4).setCellStyle(defaultStyle);
                            row.getCell(5).setCellStyle(contentStyle);
                            row.getCell(6).setCellStyle(contentStyle);
                            row.getCell(7).setCellStyle(defaultStyle);
                            row.getCell(8).setCellStyle(defaultStyle);
                            row.getCell(9).setCellStyle(defaultStyle);
                            row.getCell(10).setCellStyle(defaultStyle);
                            row.getCell(11).setCellStyle(contentStyle);
                            m++;
                        }
                    }
                    break;
                case 1:
                    if(ScreenExaminationList.size() > 0) {
                        // 读取模板内sheet内容
                        XSSFSheet sheet1 = wb.getSheetAt(1);
                        int s = 1;
                        for (DcReportDesionCensorScreenExamination item : ScreenExaminationList) {
                            XSSFRow row = sheet1.createRow(1 + s);
                            row.createCell(0).setCellValue(item.getCensorName());
                            row.createCell(1).setCellValue(item.getDeepWater());
                            row.createCell(2).setCellValue(item.getUnderDeepWaterRocks());
                            row.createCell(3).setCellValue(item.getLandSaltGypsumLayer());
                            row.createCell(4).setCellValue(item.getOilSand());
                            row.createCell(5).setCellValue(item.getLargeDisplacement());
                            row.createCell(6).setCellValue(item.getHighTemperatureHighPressure());
                            row.createCell(7).setCellValue(item.getOther());
                            row.createCell(8).setCellValue(item.getAmountTo());
                            row.getCell(0).setCellStyle(defaultStyle);
                            row.getCell(1).setCellStyle(defaultStyle);
                            row.getCell(2).setCellStyle(defaultStyle);
                            row.getCell(3).setCellStyle(defaultStyle);
                            row.getCell(4).setCellStyle(defaultStyle);
                            row.getCell(5).setCellStyle(contentStyle);
                            row.getCell(6).setCellStyle(contentStyle);
                            row.getCell(7).setCellStyle(defaultStyle);
                            row.getCell(8).setCellStyle(defaultStyle);
                            s++;
                        }
                    }
                    break;
                case 2:
                    if(SummaryExamination.size() > 0) {
                        // 读取模板内sheet内容
                        XSSFSheet sheet2 = wb.getSheetAt(2);
                        int t = 1;
                        for (DcReportDesionCensorSummaryExamination item : SummaryExamination) {
                            XSSFRow row = sheet2.createRow(1 + t);
                            row.createCell(0).setCellValue(item.getYear());
                            row.createCell(1).setCellValue(item.getCount());
                            row.getCell(0).setCellStyle(defaultStyle);
                            row.getCell(1).setCellStyle(defaultStyle);
                            t++;
                        }
                    }
                    break;
            }
        }
        ReportUtils.download(response, wb, templateName);
    }

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    public String importData(List<DcReportDesionCensorUserView> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DcReportDesionCensorUserView item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                //tip1:导入时，专家名单判断专家库是否存在，不存在则新增专家库（if_retire=1,year=当前年份），存在直接添加到设计审查汇总表
                //tip2:导入时，专家名单判断专家库是否存在，不存在则不插入本条数据。

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

                saveOrUpdate(item);

                String[] temp = {};
                String delimiter = ",";// 指定分割字符
                if(item.getCensorName() != null && item.getCensorName() != ""){
                    temp = item.getCensorName().split(delimiter);// 分割字符串
                    System.out.println(Arrays.toString(temp));
                }

                for (int i = 0; i < temp.length; i++) {
                    CensorNameVo censorNameVos = dcReportDesionCensorMapper.getExpertCensorName(temp[i]);
                    //专家库存在此专家
                    if(censorNameVos != null){
                        DcReportDesionCensorUser censorUser = new DcReportDesionCensorUser();
                        censorUser.setCensorId(item.getId());
                        censorUser.setExpertId(censorNameVos.getId());
                        dcReportDesionCensorUserService.save(censorUser);
                    }
                    //专家库不存在，因此专家库新增一条数据
                    else {
                        //专家库新增
                        DcReportExpertLibrary dcReportExpertLibrary = new DcReportExpertLibrary();
                        dcReportExpertLibrary.setId(UUID.randomUUID().toString().replace("-",""));
                        dcReportExpertLibrary.setName(temp[i]);
                        Calendar calendar = Calendar.getInstance();
                        String year = String.valueOf(calendar.get(Calendar.YEAR));
                        dcReportExpertLibrary.setYear(year);
                        dcReportExpertLibrary.setIfRetire(String.valueOf(1));//没退休
                        dcReportExpertLibraryService.saveOrUpdate(dcReportExpertLibrary);
                        //专家清单新增
                        DcReportDesionCensorUser censorUser = new DcReportDesionCensorUser();
                        censorUser.setCensorId(item.getId());
                        censorUser.setExpertId(dcReportExpertLibrary.getId());
                        dcReportDesionCensorUserService.save(censorUser);
                    }
                }

                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }

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
     * 随机生成32位Id
     * */
    public String getRandom(){
        Random random = new Random();
        int randomNum = random.nextInt();
        String randdomString = Integer.toString(randomNum);
        return randdomString;
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
}
