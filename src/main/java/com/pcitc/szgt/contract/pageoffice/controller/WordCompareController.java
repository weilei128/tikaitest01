package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

/**
 * word两版本比较
 */
@RestController
public class WordCompareController {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    /**
     * 比较文件
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/wordcompare", method= RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String, Object> map){

        UserInfo userInfo = currentUserUtil.currentUserInfo();

//        String fileurl1 = (String)request.getSession().getAttribute("fileurl1");
//        String fileurl2 = (String)request.getSession().getAttribute("fileurl2");

        // TODO 两个文件的url
        String fileurl1= request.getParameter("fileurl1");
        String fileurl2 = request.getParameter("fileurl2");

        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        //添加自定义按钮
        poCtrl.addCustomToolButton("显示A文档", "ShowFile1View()", 0);
        poCtrl.addCustomToolButton("显示B文档", "ShowFile2View()", 0);
        poCtrl.addCustomToolButton("显示比较结果", "ShowCompareView()", 0);
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
        poCtrl.setTitlebar(false);
        poCtrl.setMenubar(false);
//        poCtrl.addCustomToolButton("保存","SaveDocument",1);
//        poCtrl.addCustomToolButton("关闭","Close",21);
//        poCtrl.addCustomToolButton("打印设置","PrintSet",0);
//        poCtrl.addCustomToolButton("打印","PrintFile",6);
//        poCtrl.addCustomToolButton("全屏/还原", "IsFullScreen", 4);
//        poCtrl.addCustomToolButton("-", "", 0);
        //打开Word文档

        byte[] encode1 = Base64.getUrlEncoder().encode(fileurl1.getBytes());
        String encodeFileUrl1 = new String(encode1);

        byte[] encode2 = Base64.getUrlEncoder().encode(fileurl2.getBytes());
        String encodeFileUrl2 = new String(encode2);

        poCtrl.wordCompare(pageOfficeConfig.getOpencontext() + "/attachment/downloadforpgo?fileurl=" + encodeFileUrl1 + "&access_token=" + RestTemplateUtil.getToken(),
                pageOfficeConfig.getOpencontext() + "/attachment/downloadforpgo?fileurl=" + encodeFileUrl2 + "&access_token=" + RestTemplateUtil.getToken(),
                OpenModeType.docAdmin, userInfo.getSysUser().getfCname());
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return new ModelAndView("WordCompare");

    }

}
