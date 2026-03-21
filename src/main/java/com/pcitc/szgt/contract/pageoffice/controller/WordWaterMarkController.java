package com.pcitc.szgt.contract.pageoffice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.szgt.contract.appmanager.entity.AmUnitconfiguration;
import com.pcitc.szgt.contract.appmanager.mapper.AmUnitconfigurationMapper;
import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.attachment.mapper.SysAttachmentinfoMapper;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.entity.CrContracttext;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.make.mapper.CrContracttextMapper;
import com.pcitc.szgt.contract.pageoffice.model.OfficeAttachmentModel;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangeMapper;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.DpsTaskMessage;
import com.pcitc.szgt.contract.share.request.DpsRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class WordWaterMarkController {

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private SysAttachmentinfoMapper attachmentinfoMapper;
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;
    @Autowired
    private CrContractinfoMapper crContractinfoMapper;
    @Autowired
    private CrContractchangeMapper crContractchangeMapper;
    @Autowired
    private CrContracttextMapper crContracttextMapper;
    @Autowired
    private SysAttachmentinfoMapper sysAttachmentinfoMapper;
    @Autowired
    private OrganizationRequest organizationRequest;
    @Autowired
    private DpsRequest dpsRequest;
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Value("${server.port}")
    private String currentPort;
    @Autowired
    private IWorkFlowService workFlowService;
    @Autowired
    private AmUnitconfigurationMapper amUnitconfigurationMapper;

    @GetMapping("wordwatermark")
    public ModelAndView addWaterMark(HttpServletRequest request, Map<String, Object> map) {

        //OfficeAttachmentModel attachmentVo = getAtta(request.getSession());
        //String imgurl = (String) request.getSession().getAttribute("imgurl");
        //String fileurl = attachmentVo.getDocUrl() + attachmentVo.getAttachmentPath();
        String contextPath = request.getContextPath();
        String access_token = RestTemplateUtil.getToken();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        String businessId = request.getParameter("businessId");
        String textId = request.getParameter("textId");
        String textType = request.getParameter("textType");
        String section = request.getParameter("section");
        String taskId = request.getParameter("taskId");
//        //关闭待办
//        if (!StringUtils.isEmpty(taskId)) {
//            dpsRequest.taskMessageComplete(taskId);
//        }


        CrContractbasic crContractbasic = new CrContractbasic();
        CrContractinfo crContractinfo = new CrContractinfo();
        CrContractchange crContractchange = new CrContractchange();
        String imgurl = "";//水印图片路径
        //合同订立
        if (section.equals(ContractEnum.EnumWorkFlow.Make.getCode())) {
            crContractbasic = crContractbasicMapper.selectById(businessId);
            crContractinfo = crContractinfoMapper.selectById(businessId);
        }
        //合同变更
        if (section.equals(ContractEnum.EnumWorkFlow.Change.getCode())) {
            crContractchange = crContractchangeMapper.selectById(businessId);
            crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
            crContractinfo = crContractinfoMapper.selectById(crContractchange.getContractID());
        }
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttext::getContractID, businessId);
        queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//文本状态
        queryWrapper.lambda().orderByDesc(CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
        CrContracttext crContracttext = new CrContracttext();
        if (crContracttextList != null && crContracttextList.size() > 0) {
            crContracttext = crContracttextList.get(0);
        }

        QueryWrapper<SysAttachmentinfo> sysAttachmentinfoQueryWrapper = new QueryWrapper<>();
        sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttext.getTextID());
        sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
        sysAttachmentinfoQueryWrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);
        List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(sysAttachmentinfoQueryWrapper);
        SysAttachmentinfo sysAttachmentinfo = new SysAttachmentinfo();
        if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
            sysAttachmentinfo = sysAttachmentinfoList.get(0);
        }
        String fileurl = sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath();

        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
        poCtrl.setFileTitle(crContractbasic.getContractName());

//        poCtrl.setJsFunction_BeforeDocumentClosed("BeforeBrowserClosed");
//        poCtrl.setJsFunction_AfterDocumentSaved("CloseWin");
        poCtrl.setAllowCopy(false);//禁止拷贝
        poCtrl.setMenubar(false);//隐藏菜单栏
        poCtrl.setOfficeToolbars(false);//隐藏Office工具条
        poCtrl.setTitlebar(false); //隐藏标题栏
//        poCtrl.setCustomToolbar(false);//隐藏自定义工具栏
        //添加自定义按钮
//        poCtrl.addCustomToolButton("保存到服务器", "Save", 1);
        poCtrl.addCustomToolButton("另存为", "SaveAs", 11);
        poCtrl.addCustomToolButton("打印设置","PrintSet",0);
//        poCtrl.addCustomToolButton("打印预览", "PrintPreview", 7);
        poCtrl.addCustomToolButton("打印","PrintFile",6);

        //设置保存页面
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAccess_token(access_token);
        attachmentVo.setAttachmentName(crContractbasic.getContractName());
        attachmentVo.setAttachmentTypeName("contractWaterText");
        attachmentVo.setPropertyModel(section);
        attachmentVo.setSection(section);
        attachmentVo.setRemark("合同水印文本生成");
        attachmentVo.setPropertyID(crContracttext.getTextID());//合同
        attachmentVo.setExtension(sysAttachmentinfo.getExtension());
        poCtrl.setSaveFilePage(pageOfficeConfig.getContext() + "/save" + attachmentVo.toParamString());

        //根据合同主办部门获取水印图片
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(crContractbasic.getMainDeptID());
        if (sysOrganization != null) {
            QueryWrapper<AmUnitconfiguration> unitconfigurationQueryWrapper = new QueryWrapper<>();
            unitconfigurationQueryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, sysOrganization.getfId());
            List<AmUnitconfiguration> amUnitconfigurationList = amUnitconfigurationMapper.selectList(unitconfigurationQueryWrapper);
            if (amUnitconfigurationList != null && amUnitconfigurationList.size() > 0) {
                log.info("PropertyID:" + amUnitconfigurationList.get(0).getOrgConfigID());
                sysAttachmentinfoQueryWrapper = new QueryWrapper<>();
                sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, amUnitconfigurationList.get(0).getOrgConfigID());
                sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getTypeCode, 1);
                sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentType, 1);
                sysAttachmentinfoQueryWrapper.lambda().orderByDesc(true, SysAttachmentinfo::getCreatedDate);
                sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(sysAttachmentinfoQueryWrapper);
                log.info("watermark-attachment size:" + sysAttachmentinfoList.size());
                if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
                    sysAttachmentinfo = sysAttachmentinfoList.get(0);
                    imgurl = sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath();
                }
            }

        }
       /* sysAttachmentinfoQueryWrapper = new QueryWrapper<>();
        sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, "13c3f7fec0e74f95899847fd4ff0aa8b");
        sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractWaterImg");
        sysAttachmentinfoQueryWrapper.lambda().orderByDesc(true, SysAttachmentinfo::getCreatedDate);
        sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(sysAttachmentinfoQueryWrapper);
        if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
            sysAttachmentinfo = sysAttachmentinfoList.get(0);
            imgurl = sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath();
        }*/

        //fileurl = "http://10.238.222.210:8081/group1/M00/00/00/Cu7e0l6QQfuAfGRQAABxSnamQ7k72.docx";
        //打开Word文档
        //poCtrl.webOpen("localhost:8088/getFile?fileurl=" + fileurl, OpenModeType.docNormalEdit, "张佚名");
//        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl + "&access_token=" + access_token,
//                OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());

        byte[] encode = Base64.getUrlEncoder().encode(fileurl.getBytes());
        String encodeFileUrl = new String(encode);
        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/attachment/downloadforpgo?fileurl=" + encodeFileUrl + "&access_token=" + access_token,
                OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());

        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //map.put("imgurl", "http://10.238.222.210:8081/group1/M00/00/00/Cu7e0l6ENuiAeAisAAD3N4Cwi8A797.png");\

        log.info("imgurl:" + imgurl);

        map.put("imgurl", imgurl);
        map.put("locklocal", true);
        ModelAndView word = new ModelAndView("WordWaterMark");
        //发送合同打印页面完成页面
        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContractbasic.getContractID());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
        }
/*
        appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
        appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(ContractEnum.EnumSection.Perform.getCode());//合同环节
        appExtendsData.setExt005(crContractbasic.getMainDeptID().toString());//主办部门ID
        sysOrganization = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
        if (sysOrganization != null) {
            appExtendsData.setExt006(sysOrganization.getfName());
        }
        if (crContractinfo.getIsFrameContract() != null) {
            appExtendsData.setExt007(crContractinfo.getIsFrameContract().toString());//是否框架合同
        }
        if (sysUserinfo != null) {
            appExtendsData.setExt008(sysUserinfo.getfCname());//经办人名称
            appExtendsData.setExt009(sysUserinfo.getfId().toString());//经办id
        }
        appExtendsData.setExt019(ContractEnum.EnumSection.Perform.getCode());//合同环节
        appExtendsData.setExt020(ContractEnum.EnumSection.PrintCompelete.getCode());//合同打印完成*/

        //合同打印完成
        AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.PrintCompelete.getCode());
        taskMessage.setExtendsData(appExtendsData);//设置扩展字段
        //  AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);//合同审批完直接跳转到打印页面
        return word;
    }

    private OfficeAttachmentModel getAtta(HttpSession session) {
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAttachmentName((String) session.getAttribute("attachmentName"));
        attachmentVo.setAttachmentPath((String) session.getAttribute("attachmentPath"));
        attachmentVo.setPropertyID((String) session.getAttribute("propertyID"));
        attachmentVo.setExtension((String) session.getAttribute("extension"));
        attachmentVo.setRemark((String) session.getAttribute("remark"));
        attachmentVo.setDocUrl((String) session.getAttribute("docUrl"));
        attachmentVo.setAccess_token((String) session.getAttribute("access_token"));

        return attachmentVo;
    }
}
