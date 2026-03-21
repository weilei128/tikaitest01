package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PgActiveController {

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @GetMapping("pgactive")
    public ModelAndView pgactive(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();

        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setAllowCopy(false);//禁止拷贝
        poCtrl.setMenubar(false);//隐藏菜单栏
        poCtrl.setOfficeToolbars(false);//隐藏Office工具条
        poCtrl.setCustomToolbar(false);//隐藏自定义工具栏
        poCtrl.setTitlebar(false);
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");

        String fileurl = "http://10.238.222.210:8081/group1/M00/00/04/Cu7e0l6njB2AGGOyAARn99eqrNs01.docx";
        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/getFile2?fileurl=" + fileurl, OpenModeType.docReadOnly, "张三");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return new ModelAndView("ReadonlyOpen");
    }
}
