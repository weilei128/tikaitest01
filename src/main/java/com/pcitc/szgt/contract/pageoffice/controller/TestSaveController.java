package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

@RestController
public class TestSaveController {

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @GetMapping("testsave")
    public ModelAndView addWaterMark(HttpServletRequest request, Map<String, Object> map) {

        String contextPath = request.getContextPath();
        String access_token = RestTemplateUtil.getToken();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        String fileurl = "http://10.238.222.210:8238/group1/M00/00/37/Cu7e0l-aG3WAQd5uAAjqs25LDvE260.doc";

        byte[] encode = Base64.getUrlEncoder().encode(fileurl.getBytes());
        String encodeFileUrl = new String(encode);

        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
        poCtrl.setFileTitle("测试另存名称");

        poCtrl.setAllowCopy(false);//禁止拷贝
        poCtrl.setMenubar(false);//隐藏菜单栏
        poCtrl.setOfficeToolbars(false);//隐藏Office工具条
        poCtrl.setTitlebar(false); //隐藏标题栏
        
        //添加自定义按钮
        poCtrl.addCustomToolButton("另存为", "SaveAs", 11);
        
        //打开Word文档
//        poCtrl.webOpen(fileurl, OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());
//        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/attachment/download2?fileurl=" + encodeFileUrl + "&filename=" + "iiiooo" + "&access_token=" + access_token,
//                OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());

        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl + "&access_token=" + access_token,
                OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());

        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return new ModelAndView("testsave");
    }
}
