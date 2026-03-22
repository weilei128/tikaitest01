package com.oo.reportforms.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 报表导出方法类
 */
public class ReportUtils {

    /**
     * 获取模板
     *
     * @param templatePath 模板路径
     * @return
     * @throws IOException
     */
    public static XSSFWorkbook getTemplate(String templatePath) throws IOException {
        // excel模板路径
        Resource resource = new ClassPathResource("template/" + templatePath);
        InputStream fis = resource.getInputStream();
        return new XSSFWorkbook(fis);
    }

    /**
     * 下载文件
     *
     * @param response 请求
     * @param wb 报表实体
     * @param fileName 文件名
     */
    public static void download(HttpServletResponse response, XSSFWorkbook wb, String fileName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
//            String fileName = new String(excelName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载模板
     *
     * @param response
     * @param templateName
     * @throws IOException
     */
    public static void downloadTemplate(HttpServletResponse response, String templateName) throws IOException {
        Resource resource = new ClassPathResource("template/" + templateName);
        InputStream inStream = resource.getInputStream();
        // 清空response
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + templateName);
//        byte[] buffer = new byte[inStream.available()];
//        inStream.read(buffer);
//        inStream.close();
//        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//        response.setContentType("application/octet-stream");
//        toClient.write(buffer);
//        toClient.flush();
//        toClient.close();
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置单元格样式
     *
     * @param wb 报表实体
     * @param fontSize 字体大小
     * @param alignment 居中方式
     * @param hasBorder 是否有边框
     * @param isBold 是否加粗
     * @return
     */
    public static XSSFCellStyle setStyle(XSSFWorkbook wb, int fontSize, HorizontalAlignment alignment, boolean hasBorder, boolean isBold) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(alignment);
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setFontName("等线");
        font.setBold(isBold);
        style.setFont(font);
        style.setWrapText(true);//自动换行
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        if (hasBorder) {
            style.setBorderBottom(BorderStyle.THIN); //下边框
            style.setBorderLeft(BorderStyle.THIN);//左边框
            style.setBorderTop(BorderStyle.THIN);//上边框
            style.setBorderRight(BorderStyle.THIN);//右边框
        }
        return style;
    }
}
