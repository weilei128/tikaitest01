package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * word痕迹列表显示
 */
@RestController
public class WordRevisionController {

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    /**
     * 显示修改痕迹
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/wordrevision", method= RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        // TODO 文件url
        String fileurl = (String)request.getSession().getAttribute("fileurl");

//        String fileurl= request.getParameter("fileurl");
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        //添加自定义按钮
        poCtrl.setOfficeToolbars(false);
        poCtrl.setTitlebar(false);
        poCtrl.setMenubar(false);
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
        poCtrl.setJsFunction_AfterDocumentSaved("CloseWin");
//        poCtrl.addCustomToolButton("保存","Save",1);
//        poCtrl.addCustomToolButton("打印设置","PrintSet",0);
//        poCtrl.addCustomToolButton("打印","PrintFile",6);
//        poCtrl.addCustomToolButton("全屏/还原", "IsFullScreen", 4);
//        poCtrl.addCustomToolButton("-", "", 0);
//        poCtrl.addCustomToolButton("关闭","Close",21);
        //打开Word文档
        poCtrl.webOpen( pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl,
                OpenModeType.docRevisionOnly, userInfo.getSysUser().getfCname());
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        ModelAndView word = new ModelAndView("WordRevision");

        return word;

    }
}
