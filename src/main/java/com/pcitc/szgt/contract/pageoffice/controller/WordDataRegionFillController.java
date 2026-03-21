package com.pcitc.szgt.contract.pageoffice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.attachment.mapper.SysAttachmentinfoMapper;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.make.entity.*;
import com.pcitc.szgt.contract.make.mapper.*;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeinfoMapper;
import com.pcitc.szgt.contract.pageoffice.model.OfficeAttachmentModel;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangeMapper;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordwriter.*;
import javafx.beans.binding.StringExpression;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.event.IIOWriteProgressListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.ElementType;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 模板数据填充
 */
@RestController
public class WordDataRegionFillController {

    @Value("${server.port}")
    private String currentPort;

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
    private IWorkFlowService workFlowService;
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Autowired
    private DictionaryRequest dictionaryRequest;
    @Autowired
    private CrProjectinfoMapper crProjectinfoMapper;
    @Autowired
    private FfOffereeinfoMapper ffOffereeinfoMapper;
    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;

    /**
     * 合同文本生成(订立 、变更)
     * @param request
     * @param map
     * @return
     */
    @GetMapping("dataregionfill")
    public ModelAndView dataRegionFill(HttpServletRequest request, Map<String, Object> map) {
        String access_token = RestTemplateUtil.getToken();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        String businessId = request.getParameter("businessId");
        String textId = request.getParameter("textId");
        String textType = request.getParameter("textType");
        String section = request.getParameter("section");
        String isEdit = request.getParameter("isEdit");//重新生成/编辑  1 重新生成  0编辑
        String contractId = request.getParameter("contractId");//合同ID
        CrContractbasic crContractbasic = new CrContractbasic();
        CrContractinfo crContractinfo = new CrContractinfo();
        CrContractchange crContractchange = new CrContractchange();

        String contextPath = request.getContextPath();

        //合同订立
        if (section.equals(ContractEnum.EnumWorkFlow.Make.getCode())) {
            crContractbasic = crContractbasicMapper.selectById(businessId);
            crContractinfo = crContractinfoMapper.selectById(businessId);
        }
        //合同变更
        if (section.equals(ContractEnum.EnumWorkFlow.Change.getCode())) {
            crContractchange = crContractchangeMapper.selectById(businessId);
            if (crContractchange == null && contractId != null && !StringUtils.isEmpty(contractId)) {
                //合同变更首次打时，变更数据未保存入库
                crContractbasic = crContractbasicMapper.selectById(contractId);
                crContractinfo = crContractinfoMapper.selectById(contractId);
            } else {
                crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
                crContractinfo = crContractinfoMapper.selectById(crContractchange.getContractID());
            }

        }


        // TODO 模板地址
        String fileurl = "";
        if (!StringUtils.isEmpty(textId)) {
            QueryWrapper<SysAttachmentinfo> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyID, textId);//合同文本模板附件
        /*wrapper.lambda().eq(SysAttachmentinfo::getPropertyModel, ContractEnum.EnumWorkFlow.Make.getCode());
        wrapper.lambda().eq(SysAttachmentinfo::getSection, ContractEnum.EnumWorkFlow.Make.getCode());*/
            wrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);
            List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(wrapper);
            List<AttachmentResultVo> resultVoList = new ArrayList<>();
            for (SysAttachmentinfo attachmentinfo : sysAttachmentinfos) {
                //合同文本模板
                if (attachmentinfo.getTypeCode() == 1 && attachmentinfo.getAttachmentType() == 1) {
                    fileurl = attachmentinfo.getDocUrl() + attachmentinfo.getAttachmentPath();
                }
            }
        }

        //合同文本表保存
        //如打开编辑，则更新合同文本附件，不再查找对应的合同模板
        CrContracttext crContracttext = new CrContracttext();
        if (isEdit.equals("0")) {
            QueryWrapper<CrContracttext> contracttextQueryWrapper = new QueryWrapper<>();
            contracttextQueryWrapper.lambda().eq(CrContracttext::getContractID, businessId);
            contracttextQueryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
            List<CrContracttext> contracttextList = crContracttextMapper.selectList(contracttextQueryWrapper);
            if (contracttextList != null && contracttextList.size() > 0) {
                crContracttext = contracttextList.get(0);//取最新的文本
            }
            //如通过url打开的文本，和最新合同文本的模板不一致，则按照新的合同文本模板生成数据
            QueryWrapper<SysAttachmentinfo> sysAttachmentinfoQueryWrapper = new QueryWrapper<>();
            sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttext.getTextID());//合同文本
            sysAttachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
            sysAttachmentinfoQueryWrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);
            List<SysAttachmentinfo> sysAttachmentinfoList = attachmentinfoMapper.selectList(sysAttachmentinfoQueryWrapper);
            if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
                fileurl = sysAttachmentinfoList.get(0).getDocUrl() + sysAttachmentinfoList.get(0).getAttachmentPath();
            }
            crContracttext.setModifiedDate(LocalDateTime.now());
            crContracttext.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContracttextMapper.updateById(crContracttext);
        } else {
            crContracttext.setTextID(UUID.randomUUID().toString());
            crContracttext.setContractID(businessId);
            crContracttext.setTextType(Integer.parseInt(textType));//标准模板
            crContracttext.setIssuer(textId);//记录选择的标准文本模板
            crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));
            crContracttext.setCreatedDate(LocalDateTime.now());
            crContracttext.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContracttextMapper.insert(crContracttext);
        }
        // TODO 如果另存时的附件参数
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAccess_token(access_token);
        attachmentVo.setAttachmentName(crContractbasic.getContractName());
        attachmentVo.setAttachmentTypeName("contractText");
        attachmentVo.setPropertyModel(section);
        attachmentVo.setSection(section);
        attachmentVo.setRemark("合同文本生成");
        attachmentVo.setPropertyID(crContracttext.getTextID());//合同

        String suffixName = "";
        if(fileurl.lastIndexOf(".") != -1){
            suffixName = fileurl.substring(fileurl.lastIndexOf(".") + 1);
        }
        attachmentVo.setExtension(suffixName);

        // 操作word
        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
        poCtrl1.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl1.setTitlebar(false);
        //隐藏菜单栏
        poCtrl1.setMenubar(false);
        // 保存按钮
        poCtrl1.addCustomToolButton("保存", "Save", 1);
        poCtrl1.setJsFunction_AfterDocumentSaved("CloseWin");

        WordDocument doc = new WordDocument();

        // TODO 数据填充
        // --------------------- 数据填充操作示例 Start ----------------------
        // #### 普通填充方法 name也可以写成 PO_name, 对应模板中的 PO_name
        DataRegion dataRegion1 = doc.openDataRegion("contractName");//合同名称
        dataRegion1.setValue(crContractbasic.getContractName());

        DataRegion dataRegion2 = doc.openDataRegion("ruleserialNum");//合同序号
        dataRegion2.setValue(crContractbasic.getRuleSerialNum());

        DataRegion dataRegion3 = doc.openDataRegion("contractNum");//合同编码
        if (!StringUtils.isEmpty(crContractbasic.getContractNum())) {
            dataRegion3.setValue(crContractbasic.getContractNum());
        }

        DataTag dataTag3 = doc.openDataTag("{mSignBodyName}");
        dataTag3.setValue(crContractinfo.getMySignBodyName());

        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            DataRegion dataRegion5 = doc.openDataRegion("MainOrgUserName");//我方经办人
            dataRegion5.setValue(sysUserinfo.getfCname());
            DataRegion dataRegion6 = doc.openDataRegion("MainOrgUserPhone");//经办人联系电话
            if (!StringUtils.isEmpty(sysUserinfo.getfPhoneNum())) {
                dataRegion6.setValue(sysUserinfo.getfPhoneNum());
            }
        }

        SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
        if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfCnName())) {
            DataRegion dataRegion7 = doc.openDataRegion("Type1Name");//合同类型1
            dataRegion7.setValue(sysDictionarycategory.getfCnName());
        }

        sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
        if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfCnName())) {
            DataRegion dataRegion8 = doc.openDataRegion("Type2Name");//合同类型2
            dataRegion8.setValue(sysDictionarycategory.getfCnName());
        }

        sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
        if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfCnName())) {
            DataRegion dataRegion9 = doc.openDataRegion("Type3Name");//合同类型3
            dataRegion9.setValue(sysDictionarycategory.getfCnName());
        }

        sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
        if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfCnName())) {
            DataRegion dataRegion10 = doc.openDataRegion("Type4Name");//合同类型4
            dataRegion10.setValue(sysDictionarycategory.getfCnName());
        }

        CrProjectinfo crProjectinfo = crProjectinfoMapper.selectById(crContractinfo.getProjectID());
        if (crProjectinfo != null) {
            DataRegion dataRegion11 = doc.openDataRegion("ProjectName");//项目名称
            dataRegion11.setValue(crProjectinfo.getProjectName());
        }

        DataRegion dataRegion12 = doc.openDataRegion("ContractObject");//合同标的
        if (crContractbasic.getContractObjectMoney() != null && !StringUtils.isEmpty(crContractbasic.getContractObjectMoney())) {
            dataRegion12.setValue(crContractbasic.getContractObjectMoney().toString());
        }

        sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getContractObjectCurrency());
        if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfName())) {
            DataRegion dataRegion13 = doc.openDataRegion("ContractObjectCurrencyName");//合同标的人民币
            dataRegion13.setValue(sysDictionarycategory.getfName());
        }
        if (crContractinfo.getMySignDate() != null) {
            DataRegion dataRegion14 = doc.openDataRegion("MySignDate");//合同签署日期
            dataRegion14.setValue(crContractinfo.getMySignDate().toString());
        }

        DataRegion dataRegion15 = doc.openDataRegion("MySignPersonName");//我方签约代表
        if (!StringUtils.isEmpty(crContractinfo.getMySignPersonName())) {
            dataRegion15.setValue(crContractinfo.getMySignPersonName());
        }

        DataRegion dataRegion16 = doc.openDataRegion("MySignPersonPhone");//我方联系电话
        if (!StringUtils.isEmpty(crContractinfo.getMySignPersonPhone())) {
            dataRegion16.setValue(crContractinfo.getMySignPersonPhone());
        }
        if (!StringUtils.isEmpty(crContractinfo.getMySignPersonPostion())) {
            DataRegion dataRegion17 = doc.openDataRegion("MySignPersonPostion");//我方代表职位
            dataRegion17.setValue(crContractinfo.getMySignPersonPostion());
        }

        QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
        crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, crContractbasic.getContractID());
        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
        //合同相对人联系
        if (crContractoffereeList != null && crContractoffereeList.size() > 0)

        {
            String offereeName = "";
            String offereelinkman = "";
            String position = "";
            String OffereeLinkPhone = "";
            String Email = "";
            String Fax = "";
            String bankname = "";
            String OpenUints = "";
            String BankAccount = "";
            String Address = "";
            String Corporation = "";
            for (CrContractofferee crContractofferee : crContractoffereeList) {
                offereeName += crContractofferee.getOffereeName() + ",";
                offereelinkman += crContractofferee.getOffereeLinkMan() + ",";
                position += crContractofferee.getPosition() + ",";
                OffereeLinkPhone += crContractofferee.getOffereeLinkPhone() + ",";
                Email += crContractofferee.getEmail() + ",";
                Fax += crContractofferee.getFax() + ",";
                bankname += crContractofferee.getBankName() + ",";
                OpenUints += crContractofferee.getOpenUints() + ",";
                BankAccount += crContractofferee.getBankAccount() + ",";

                String offereeID = crContractofferee.getOffereeID();
                FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(offereeID);
                if(ffOffereeinfo != null){
                    Address += ffOffereeinfo.getAddress() + ",";
                    Corporation += ffOffereeinfo.getCorporation() + ",";
                }
            }
            if (!StringUtils.isEmpty(offereeName)) {
                offereeName = offereeName.substring(0, offereeName.length() - 1);
            }
            if (!StringUtils.isEmpty(offereelinkman)) {
                offereelinkman = offereelinkman.substring(0, offereelinkman.length() - 1);
            }
            if (!StringUtils.isEmpty(position)) {
                position = position.substring(0, position.length() - 1);
            }
            if (!StringUtils.isEmpty(OffereeLinkPhone)) {
                OffereeLinkPhone = OffereeLinkPhone.substring(0, OffereeLinkPhone.length() - 1);
            }
            if (!StringUtils.isEmpty(Email)) {
                Email = Email.substring(0, Email.length() - 1);
            }
            if (!StringUtils.isEmpty(Fax)) {
                Fax = Fax.substring(0, Fax.length() - 1);
            }
            if (!StringUtils.isEmpty(bankname)) {
                bankname = bankname.substring(0, bankname.length() - 1);
            }
            if (!StringUtils.isEmpty(OpenUints)) {
                OpenUints = OpenUints.substring(0, OpenUints.length() - 1);
            }
            if (!StringUtils.isEmpty(BankAccount)) {
                BankAccount = BankAccount.substring(0, BankAccount.length() - 1);
            }
            if(!StringUtils.isEmpty(Address)){
                Address = Address.substring(0, Address.length() - 1);
            }
            if(!StringUtils.isEmpty(Corporation)){
                Corporation = Corporation.substring(0, Corporation.length() - 1);
            }

            DataTag dataTag1 = doc.openDataTag("{OffereeName}");
            dataTag1.setValue(offereeName);

//            DataRegion dataRegion18 = doc.openDataRegion("OffereeName");//相对人
//            dataRegion18.setValue(offereeName);
            DataRegion dataRegion19 = doc.openDataRegion("Offereelinkman");//相对人联系人
            if (!StringUtils.isEmpty(offereelinkman)) {
                dataRegion19.setValue(offereelinkman);
            }
            if (!StringUtils.isEmpty(position)) {
                DataRegion dataRegion20 = doc.openDataRegion("Position");//相对人联系人职位
                dataRegion20.setValue(position);
            }

            if (!StringUtils.isEmpty(OffereeLinkPhone)) {
                DataRegion dataRegion21 = doc.openDataRegion("OffereeLinkPhone");//相对人联系人电话
                dataRegion21.setValue(OffereeLinkPhone);
            }
            if (!StringUtils.isEmpty(Email)) {
                DataRegion dataRegion22 = doc.openDataRegion("Email");//相对人联系人邮箱
                dataRegion22.setValue(Email);
            }
            if (!StringUtils.isEmpty(Fax)) {
                DataRegion dataRegion23 = doc.openDataRegion("Fax");//相对人联系人传值
                dataRegion23.setValue(Fax);
            }
            if (!StringUtils.isEmpty(bankname)) {
                DataRegion dataRegion24 = doc.openDataRegion("bankname");//相对人银行
                dataRegion24.setValue(bankname);
            }
            if (!StringUtils.isEmpty(OpenUints)) {
                DataRegion dataRegion25 = doc.openDataRegion("OpenUints");//开户银行单位
                dataRegion25.setValue(OpenUints);
            }
            if (!StringUtils.isEmpty(BankAccount)) {
                DataRegion dataRegion26 = doc.openDataRegion("BankAccount");//开户行账号
                dataRegion26.setValue(BankAccount);
            }
            if(!StringUtils.isEmpty(Address)){
                DataTag dataTag2 = doc.openDataTag("{Address}");
                dataTag2.setValue(Address);
            }
            if(!StringUtils.isEmpty(Corporation)){
                DataRegion dataRegion28 = doc.openDataRegion("Corporation");//法人
                dataRegion28.setValue(Corporation);
            }
        }

    /*<履行开始年 column = "StartDateYear" table = "CR_ContractInfo" ></履行开始年 >
<履行开始月 column = "StartDateMonth" table = "CR_ContractInfo" ></履行开始月 >
<履行开始日 column = "StartDateDay" table = "CR_ContractInfo" ></履行开始日 >
<履行结束年 column = "EndDateYear" table = "CR_ContractInfo" ></履行结束年 >
<履行结束月 column = "EndDateMonth" table = "CR_ContractInfo" ></履行结束月 >
<履行结束日 column = "EndDateDay" table = "CR_ContractInfo" ></履行结束日 >*/
        // #### 表格填充 table1也可以写成 PO_table1, 对应模板中的 PO_table1
        // 动态创建一个3行5列的表格
        Table table1 = doc.openDataRegion("table1").createTable(3, 5, WdAutoFitBehavior.wdAutoFitWindow);

        // 合并(1,1)到(3,1)的单元格并赋值
        table1.openCellRC(1, 1).

                mergeTo(3, 1);
        table1.openCellRC(1, 1).

                setValue("合并后的单元格");
        // 给表格table1中剩余的单元格赋值
        for (
                int i = 1;
                i < 4; i++)

        {
            table1.openCellRC(i, 2).setValue("AA" + String.valueOf(i));
            table1.openCellRC(i, 3).setValue("BB" + String.valueOf(i));
            table1.openCellRC(i, 4).setValue("CC" + String.valueOf(i));
            table1.openCellRC(i, 5).setValue("DD" + String.valueOf(i));
        }

        // ----------------------- 数据填充操作示例 END -----------------------

        poCtrl1.setWriter(doc);

        // 保存路径(走附件上传接口)
        poCtrl1.setSaveFilePage(pageOfficeConfig.getContext() + "/save" + attachmentVo.toParamString());

        byte[] encode = Base64.getUrlEncoder().encode(fileurl.getBytes());
        String encodeFileUrl = new String(encode);

        //打开Word文件
        poCtrl1.webOpen(pageOfficeConfig.getOpencontext() +"/attachment/downloadforpgo?fileurl=" + encodeFileUrl + "&access_token=" + access_token,
                OpenModeType.docNormalEdit, userInfo.getSysUser().getfCname());

        map.put("pageoffice", poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        return new ModelAndView("NormalOpen");
    }

    private OfficeAttachmentModel getAtta(HttpSession session) {
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAttachmentName((String) session.getAttribute("attachmentName"));
        attachmentVo.setAttachmentPath((String) session.getAttribute("attachmentPath"));
        attachmentVo.setPropertyID((String) session.getAttribute("propertyID"));
        attachmentVo.setExtension((String) session.getAttribute("extension"));
        attachmentVo.setRemark((String) session.getAttribute("remark"));
        attachmentVo.setDocUrl((String) session.getAttribute("docUrl"));
//        attachmentVo.setAccesstoken((String)session.getAttribute("access_token"));

        return attachmentVo;
    }

}
