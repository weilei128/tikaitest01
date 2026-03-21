package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.zhuozhengsoft.pageoffice.PDFCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 打开pdf文件
 */
@RestController
public class PdfOpenController {

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @GetMapping("pdfopen")
    public ModelAndView showpdf(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();

        String fileurl= (String)request.getSession().getAttribute("fileurl");
        PDFCtrl pdfCtrl1 = new PDFCtrl(request);
        pdfCtrl1.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz"); //此行必须
        pdfCtrl1.setTitlebar(false);
        pdfCtrl1.setMenubar(false);
        // Create custom toolbar
        pdfCtrl1.addCustomToolButton("打印", "PrintFile()", 6);
        pdfCtrl1.addCustomToolButton("隐藏/显示书签", "SetBookmarks()", 0);
        pdfCtrl1.addCustomToolButton("-", "", 0);
        pdfCtrl1.addCustomToolButton("实际大小", "SetPageReal()", 16);
        pdfCtrl1.addCustomToolButton("适合页面", "SetPageFit()", 17);
        pdfCtrl1.addCustomToolButton("适合宽度", "SetPageWidth()", 18);
        pdfCtrl1.addCustomToolButton("-", "", 0);
        pdfCtrl1.addCustomToolButton("首页", "FirstPage()", 8);
        pdfCtrl1.addCustomToolButton("上一页", "PreviousPage()", 9);
        pdfCtrl1.addCustomToolButton("下一页", "NextPage()", 10);
        pdfCtrl1.addCustomToolButton("尾页", "LastPage()", 11);
        pdfCtrl1.addCustomToolButton("-", "", 0);
        pdfCtrl1.addCustomToolButton("向左旋转90度", "SetRotateLeft()", 12);
        pdfCtrl1.addCustomToolButton("向右旋转90度", "SetRotateRight()", 13);
        pdfCtrl1.webOpen( pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl);

        map.put("pageoffice",pdfCtrl1.getHtmlCode("PDFCtrl1"));
        ModelAndView word = new ModelAndView("PdfOpen");

        return word;
    }
}
