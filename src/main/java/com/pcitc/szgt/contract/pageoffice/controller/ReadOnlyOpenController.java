package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

/**
 * 只读打开文件,
 * 包含可选下载本地和文档锁定
 */
@RestController
public class ReadOnlyOpenController {

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    /**
     *	 打开文件
     * 0b000000001  另存为功能
     * 0b000000010  另存时文档锁定
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/filereadonly", method= RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        String fileUrl = request.getParameter("fileUrl");
        int toolNum = 0;
        String tools = request.getParameter("tools");
        if(!StringUtils.isEmpty(tools)){
            toolNum = Integer.parseInt(tools);
        }

        // TODO 文件路径
        OpenModeType openModeType = checkOpenModeType(fileUrl);

        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setAllowCopy(false);//禁止拷贝
        poCtrl.setMenubar(false);//隐藏菜单栏
        poCtrl.setOfficeToolbars(false);//隐藏Office工具条

        //没有附加功能隐藏
        if(toolNum == 0){
            poCtrl.setCustomToolbar(false);//隐藏自定义工具栏
        }else{
            //默认隐藏
            boolean showToolBar = false;
            if((toolNum & 0b000000001) > 0){
                showToolBar = true;
                poCtrl.addCustomToolButton("另存为", "SaveAs", 11);
                //如果传了filetitle设置
                String filetitle = request.getParameter("filetitle");
                if(!StringUtils.isEmpty(filetitle)){
                    poCtrl.setFileTitle(filetitle);
                }
                //如果下载到本地需要锁定
                if((toolNum & 0b000000010) > 0){
                    map.put("locklocal", true);
                }else{
                    map.put("locklocal", false);
                }
            }

            poCtrl.setCustomToolbar(showToolBar);
        }

        poCtrl.setTitlebar(false);
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");

        byte[] encode = Base64.getUrlEncoder().encode(fileUrl.getBytes());
        String encodeFileUrl = new String(encode);
        //打开Word文档 由于 Pageoffice 不能跨域，所以采取先下载文件到本地，在打开的方式。
        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/attachment/downloadforpgo?fileurl=" + encodeFileUrl + "&access_token=" + RestTemplateUtil.getToken(),
                openModeType, userInfo.getSysUser().getfCname());
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return new ModelAndView("ReadonlyOpen");
    }

    private OpenModeType checkOpenModeType(String var1){
        if (!var1.toLowerCase().endsWith("doc") && !var1.toLowerCase().endsWith("docx") && !var1.toLowerCase().endsWith("docm") && !var1.toLowerCase().endsWith("rtf") && !var1.toLowerCase().endsWith("wps")) {
            if (!var1.toLowerCase().endsWith(".xls") && !var1.toLowerCase().endsWith(".xlsx") && !var1.toLowerCase().endsWith(".xlsm") && !var1.toLowerCase().endsWith(".et")) {
                if (!var1.toLowerCase().endsWith(".ppt") && !var1.toLowerCase().endsWith(".pptx")) {
                    if (var1.toLowerCase().endsWith(".vsd")) {
                        return OpenModeType.vsdNormalEdit;
                    }
                }else{
                    return OpenModeType.pptReadOnly;
                }
            }else{
                return OpenModeType.xlsReadOnly;
            }
        }else{
            return OpenModeType.docReadOnly;
        }

        return OpenModeType.docReadOnly;
    }
}
