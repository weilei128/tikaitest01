package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.DataTag;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class DataFillTestController {

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @GetMapping("datafilltest")
    public ModelAndView dataRegionFill(HttpServletRequest request, Map<String, Object> map) {
        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
        poCtrl1.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");

        WordDocument doc = new WordDocument();

        DataRegion dataRegion1 = doc.openDataRegion("contractName");//合同名称
        dataRegion1.setValue("合同の名称");

        DataTag dataTag1 = doc.openDataTag("{OffereeName}");
        dataTag1.setValue("相对人の名称");

        poCtrl1.setWriter(doc);

        String fileurl = "http://10.238.222.210:8081/group1/M00/00/04/Cu7e0l6njB2AGGOyAARn99eqrNs01.docx";
        poCtrl1.webOpen(pageOfficeConfig.getOpencontext() + "/getFile2?fileurl=" + fileurl, OpenModeType.docReadOnly, "张三");
        map.put("pageoffice", poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        return new ModelAndView("NormalOpen");
    }
}
