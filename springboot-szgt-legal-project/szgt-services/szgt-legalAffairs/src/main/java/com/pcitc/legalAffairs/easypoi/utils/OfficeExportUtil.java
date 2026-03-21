package com.pcitc.legalAffairs.easypoi.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.google.common.collect.Lists;
import com.pcitc.legalAffairs.easypoi.model.ExcelModel;
import com.pcitc.legalAffairs.easypoi.model.ImportExcelResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: hanyafei
 * @date: 2020-06-03 15:10
 */
@Slf4j
public class OfficeExportUtil {

    /** 允许导出的最大条数 */
    private static final Integer EXPORT_EXCEL_MAX_NUM = 10000;

    /**
     * 获取导出的 Workbook对象
     *
     * @param title     大标题
     * @param sheetName 页签名
     * @param object    导出实体
     * @param list      数据集合
     * @return Workbook
     */
    public static Workbook getWorkbook(String title, String sheetName, Class object, List list) {
        //判断导出数据是否为空
        if (list == null) {
            list = new ArrayList<>();
        }
        //判断导出数据数量是否超过限定值
        if (list.size() > EXPORT_EXCEL_MAX_NUM) {
            title = "导出数据行数超过:" + EXPORT_EXCEL_MAX_NUM + "条,无法导出、请添加导出条件!";
            list = new ArrayList<>();
        }
        //获取导出参数
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        //设置导出样式
        exportParams.setStyle(MyExcelStyleUtil.class);
        //输出Workbook流
        return ExcelExportUtil.exportExcel(exportParams, object, list);
    }

    /**
     * 获取导出的 Workbook对象
     *
     * @param path 模板路径
     * @param map  导出内容map
     * @return Workbook
     */
    public static Workbook getWorkbook(String path, Map<String, Object> map) {
        //获取导出模板
        TemplateExportParams params = new TemplateExportParams(path);
        //设置导出样式
        params.setStyle(MyExcelStyleUtil.class);
        //输出Workbook流
        return ExcelExportUtil.exportExcel(params, map);
    }

    /**
     * 导出Excel
     *
     * @param workbook workbook流
     * @param fileName 文件名
     * @param response 响应
     */
    public static void exportExcel(Workbook workbook, String fileName, HttpServletResponse response) {
        //输出文件
        try (OutputStream out = response.getOutputStream()) {
            //获取文件名并转码
            String name = URLEncoder.encode(fileName, "UTF-8");
            //编码
            response.setCharacterEncoding("UTF-8");
            // 设置强制下载不打开
//            response.setContentType("application/force-download");
            response.setContentType("application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
            //输出表格
            workbook.write(out);
        } catch (IOException e) {
            log.error("文件导出异常,详情如下:", e);
        } finally {
            try {
                if (workbook != null) {
                    //关闭输出流
                    workbook.close();
                }
            } catch (IOException e) {
                log.error("文件导出异常,详情如下:", e);
            }
        }
    }
    /**
     * 功能描述：根据接收的Excel文件来导入Excel,并封装成实体类
     *
     * @param file 上传的文件
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T extends ExcelModel> ImportExcelResult<T> importExcel(MultipartFile file, IExcelVerifyHandler handler, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        List<String> msgs = Lists.newArrayList();
        ExcelImportResult<T> result;
        try {
            ImportParams params = new ImportParams();
            // 表头设置为1行
            params.setHeadRows(titleRows);
            // 标题行设置为0行，默认是0，可以不设置
            params.setTitleRows(headerRows);
            // 开启Excel校验
            params.setNeedVerfiy(true);
            params.setVerifyHandler(handler);
            result = ExcelImportUtil.importExcelMore(file.getInputStream(), pojoClass, params);
        }catch (Exception e){
            throw new RuntimeException("excel文件不能为空");
        }
        // 合并结果集
        List<T> resultList = new ArrayList<>();
        resultList.addAll(result.getFailList());
        for (T inputEntity : resultList) {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(inputEntity.getErrorMsg());
            inputEntity.setErrorMsg(joiner.toString());
        }
        for (T entity : result.getFailList()) {
            int line = entity.getRowNum() + 1;
            String msg = "第" + line + "行的错误是：" + entity.getErrorMsg();
            msgs.add(msg);
        }
        return new ImportExcelResult(msgs,result.getList());
    }
}
