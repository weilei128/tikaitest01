package com.pcitc.szgt.contract.workflow.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.pcitc.ssc.dps.inte.workflow.*;
import com.pcitc.ssc.dps.vars.TaskType;
import com.pcitc.szgt.contract.appmanager.entity.AmUnitconfiguration;
import com.pcitc.szgt.contract.appmanager.mapper.AmUnitconfigurationMapper;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentQueryVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.service.AttachmentService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.make.entity.*;
import com.pcitc.szgt.contract.make.mapper.*;
import com.pcitc.szgt.contract.make.modelEx.NextApproverVo;
import com.pcitc.szgt.contract.make.modelEx.UserInfoListVo;
import com.pcitc.szgt.contract.make.service.IMakeService;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.entity.CrContractend;
import com.pcitc.szgt.contract.perform.entity.CrContracttransfer;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangeMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractendMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContracttransferMapper;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.DpsCallbackVo;
import com.pcitc.szgt.contract.share.model.DpsTaskMessage;
import com.pcitc.szgt.contract.share.model.TaskQueryModel;
import com.pcitc.szgt.contract.share.request.*;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.DateUtil;
import com.pcitc.szgt.contract.util.UserUtil;
import com.pcitc.szgt.contract.workflow.entityExt.*;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>工作流服务</p>
 *
 * @author ziranzhou
 * @since 2020-02-25
 */
@Slf4j
@Service
public class WorkFlowServiceImpl implements IWorkFlowService {
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;
    @Autowired
    private CrContractinfoMapper crContractinfoMapper;
    @Autowired
    private CrOfficeAgentsMapper crOfficeAgentsMapper;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 合同变更
     */
    @Autowired
    private CrContractchangeMapper crContractchangeMapper;
    /**
     * 合同转让
     */
    @Autowired
    private CrContracttransferMapper crContracttransferMapper;
    /**
     * 合同终止
     */
    @Autowired
    private CrContractendMapper crContractendMapper;
    /**
     * 组织机构管理
     */
    @Autowired
    private OrganizationRequest organizationRequest;
    /**
     * 数据字典管理
     */
    @Autowired
    private DictionaryRequest dictionaryRequest;
    @Autowired
    private DpsRequest dpsRequest;
    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;
    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Autowired
    private AmUnitconfigurationMapper amUnitconfigurationMapper;
    @Autowired
    private WfMessageMapper wfMessageMapper;
    @Autowired
    private OfficeAgentRequest officeRequest;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private IMakeService makeService;

    @Autowired
    private UserUtil userUtil;

    /*
     * 合同相对人
     * */
    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;

    /**
     * 设置工作流扩展字段
     */
    @Override
    public AppExtendsData setExtendsData(String businessId, CrContractbasic crContractbasic, CrContractinfo crContractinfo, String moduleId, String moduleName,
                                         String section) {
        AppExtendsData appExtendsData = new AppExtendsData();
        appExtendsData.setBusinessId(businessId);//业务数据Id
        appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(moduleId);//合同模块
        appExtendsData.setExt005(crContractbasic.getMainDeptID().toString());//主办部门ID
        SysOrganization sysOrganization = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
        if (sysOrganization != null) {
            String deptName = sysOrganization.getfName();
            if (sysOrganization.getFkParentId() != null) {
                SysOrganization parentOrg = organizationRequest.queryOrganization(sysOrganization.getFkParentId());
                if (parentOrg != null && parentOrg.getFkParentId() != null && parentOrg.getFkParentId() > 0) {
                    deptName = parentOrg.getfName() + "/" + deptName;
                }
            }
            appExtendsData.setExt006(deptName);//主办部门名称
        }
        if (crContractinfo.getIsFrameContract() != null) {
            appExtendsData.setExt007(crContractinfo.getIsFrameContract().toString());//是否框架合同
        } else {
            appExtendsData.setExt007("0");//是否框架合同
        }
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            appExtendsData.setExt008(sysUserinfo.getfCname());//经办人名称
            appExtendsData.setExt009(sysUserinfo.getfId().toString());//经办id
        }
        if (crContractbasic.getContractObjectMoney() != null) {
            appExtendsData.setExt010(crContractbasic.getContractObjectMoney().toString());//标的金额
        } else {
            appExtendsData.setExt010("");
        }
        if (crContractbasic.getContractObjectCurrency() != null) {
            appExtendsData.setExt011(crContractbasic.getContractObjectCurrency().toString());//标的金额币种id
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getContractObjectCurrency());
            if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfId())) {
                appExtendsData.setExt012(sysDictionarycategory.getfCnName());//标的金额币种中文
            } else {
                appExtendsData.setExt012("");//标的金额币种中文
            }
        } else {
            appExtendsData.setExt011("");//标的金额
            appExtendsData.setExt012("");//标的金额币种中文
        }
        String typeName = "";
        if (crContractbasic.getType1() != null) {
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
            if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfId())) {
                appExtendsData.setExt013(sysDictionarycategory.getfId().toString());//合同类型1
                typeName = sysDictionarycategory.getfCnName();
            }
        }
        if (crContractbasic.getType2() != null) {
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
            if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfId())) {
                appExtendsData.setExt014(sysDictionarycategory.getfId().toString());//合同类型2
                typeName = typeName + "-" + sysDictionarycategory.getfCnName();
            }
        }
        if (crContractbasic.getType3() != null) {
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
            if (sysDictionarycategory != null && sysDictionarycategory.getfId() != null) {
                appExtendsData.setExt015(sysDictionarycategory.getfId().toString());//合同类型3
                typeName = typeName + "-" + sysDictionarycategory.getfCnName();
            }
        }
        if (crContractbasic.getType4() != null) {
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
            if (sysDictionarycategory != null && !StringUtils.isEmpty(sysDictionarycategory.getfId())) {
                appExtendsData.setExt016(sysDictionarycategory.getfId().toString());//合同类型4
                typeName = typeName + "-" + sysDictionarycategory.getfCnName();
            }
        }
        appExtendsData.setExt017(typeName);//合同类型
        appExtendsData.setExt019(moduleName);//合同模块
        appExtendsData.setExt018(ContractEnum.enumSectionMap.get(section));//合同环节中文名
        appExtendsData.setExt020(section);//合同环节id
        return appExtendsData;
    }

    /**
     * 合同订立审批结束
     */
    @Override
    @Transactional
    public boolean makeApprove(String contractId) {

        //SysDictionarycategory sysDictionary = dictionaryRequest.queryCategotyByCode("hetong");

        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractbasic> basicQueryWrapper = new QueryWrapper<>();
        basicQueryWrapper.lambda().eq(CrContractbasic::getContractID, contractId).last("for update");

        CrContractbasic crContractbasic = crContractbasicMapper.selectOne(basicQueryWrapper);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        if (crContractbasic.getSection() == Integer.parseInt(ContractEnum.EnumSection.Print.getCode())) {
            throw new BaseException("合同已处理", Constants.FAILCODE);
        }

        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        //合同编号=企业编码+年度（20）+合同类型（1，2，3）+四位流水号
        if (StringUtils.isEmpty(crContractbasic.getContractNum())) {
            String contractNum = "";
            Integer orgId = 0;
            String orgCode = "";
            SysOrganization sysOrganization = organizationRequest.getOrgCompany(crContractbasic.getMainDeptID());
            if (sysOrganization != null) {
                orgId = sysOrganization.getfId();
                orgCode = sysOrganization.getfCode();
            }
            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR);
            contractNum = orgCode + "-" + year.toString().substring(2) + "-";

            //SysDictionary sysDictionary = dictionaryRequest.queryDictionary("12");

            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
            if (sysDictionarycategory != null) {
                contractNum += sysDictionarycategory.getfRemarks();
            }
            if (crContractbasic.getType2() != null) {
                SysDictionarycategory sysDictionarycategory2 = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
                if (sysDictionarycategory2 != null && sysDictionarycategory2.getfId() != null) {
                    String code = "";
                    if (sysDictionarycategory2.getfCode().length() > 2) {
                        code = sysDictionarycategory2.getfCode().substring(sysDictionarycategory2.getfCode().length() - 2);
                    }
                    contractNum += code;
                }
            }
            if (crContractbasic.getType3() != null) {
                SysDictionarycategory sysDictionarycategory3 = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
                String code = "";
                if (sysDictionarycategory3 != null && sysDictionarycategory3.getfId() != null) {
                    if (sysDictionarycategory3.getfCode().length() > 2) {
                        code = sysDictionarycategory3.getfCode().substring(sysDictionarycategory3.getfCode().length() - 2);
                    }
                    contractNum += code;
                }


            }
            if (crContractbasic.getType4() != null) {
                SysDictionarycategory sysDictionarycategory4 = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
                if (sysDictionarycategory4 != null && sysDictionarycategory4.getfId() != null) {
                    if (sysDictionarycategory4 != null) {
                        contractNum += sysDictionarycategory4.getfRemarks();
                    }
                }

            }
            Integer num = crContractbasicMapper.selectSeqFunc(contractNum);
//            QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().like(true, CrContractbasic::getContractNum, contractNum);
//            queryWrapper.lambda().notIn(true, CrContractbasic::getContractID, crContractbasic.getContractID());
//            queryWrapper.lambda().orderByDesc(true, CrContractbasic::getCreatedDate);
//            List<CrContractbasic> contractbasicList = crContractbasicMapper.selectList(queryWrapper);
//            if (contractbasicList != null && contractbasicList.size() > 0) {
//                String contractNumNew = contractbasicList.get(0).getContractNum();
//                int length = contractNumNew.length();
//                contractNumNew = contractNumNew.substring(length - 4, length);
//                int num = Integer.parseInt(contractNumNew);
//                num++;
            DecimalFormat df = new DecimalFormat("0000");
            String numNew = df.format(num);
            contractNum += "-" + numNew;
//            } else {
//                contractNum += "-0001";
//            }
            crContractbasic.setContractNum(contractNum);
        }

        LocalDateTime dateTime = LocalDateTime.now();
        crContractbasic.setCheckDate(dateTime);//审批结束时间
        crContractbasic.setModifiedDate(dateTime);//修改时间
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));//合同订立  0903
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Print.getCode()));//合同打印环节 0903
        crContractbasicMapper.updateById(crContractbasic);

        //给合同经办人发送待办消息
        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(contractId);
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
        }
      /*  AppExtendsData appExtendsData = new AppExtendsData();

        appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
        appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(ContractEnum.EnumModule.Make.getCode());//合同环节
        appExtendsData.setExt005(crContractbasic.getMainDeptID().toString());//主办部门ID
        SysOrganization sysOrganization = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
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
        appExtendsData.setExt019(ContractEnum.EnumModule.Make.getMessage());//合同环节
        appExtendsData.setExt020(ContractEnum.EnumSection.Print.getCode());//打印环节
        taskMessage.setExtendsData(appExtendsData);*/
       /* AppExtendsData appExtendsData = setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Make.getCode(), ContractEnum.EnumModule.Make.getMessage(),
                ContractEnum.EnumSection.PrintCompelete.getCode());*/
        AppExtendsData appExtendsData = setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Make.getCode(), ContractEnum.EnumModule.Make.getMessage(),
                ContractEnum.EnumSection.PrintCompelete.getCode());//改成跳转到打印页面
        taskMessage.setExtendsData(appExtendsData);
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时消息
        saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
        return true;
    }

    /**
     * 合同变更审批结束
     */
    @Override
    public boolean changeApprove(String changeContractId) {
        if (StringUtils.isEmpty(changeContractId)) {
            throw new NotFoundException("变更合同ID必填！", Constants.FAILCODE);
        }

        CrContractchange crContractchange = crContractchangeMapper.selectById(changeContractId);
        if (crContractchange == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        crContractchange.setState(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractchange.setModifiedDate(LocalDateTime.now());
        crContractchange.setModifiedBy(crContractchange.getCreatedBy());
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContractchange.getContractID());
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        //给合同履行人发送履行待办

        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContractchange.getContractChangeID());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
        }
        AppExtendsData appExtendsData = setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo, ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(),
                ContractEnum.EnumSection.ChangeSign.getCode());
        taskMessage.setExtendsData(appExtendsData);
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时消息
        saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Change.getCode(), sysUserinfo);

        return crContractchangeMapper.updateById(crContractchange) > 0;

    }

    /**
     * 合同转让变更审批结束
     */
    @Override
    public boolean transApprove(String transId) {
        if (StringUtils.isEmpty(transId)) {
            throw new NotFoundException("转让合同ID必填！", Constants.FAILCODE);
        }
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(transId);
        if (crContracttransfer == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        crContracttransfer.setState(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContracttransfer.setModifiedDate(LocalDateTime.now());
        crContracttransfer.setModifiedBy(crContracttransfer.getCreatedBy());
        //给合同履行人发送履行待办
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContracttransfer.getContractID());
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContracttransfer.getContractID());
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContracttransfer.getTransferId());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Trasfer.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
        }
        //AppExtendsData appExtendsData = new AppExtendsData();

        //appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
        /*appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(ContractEnum.EnumModule.Perform.getCode());//合同环节
        appExtendsData.setExt005(crContractbasic.getMainDeptID().toString());//主办部门ID
        SysOrganization sysOrganization = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
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
        appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节
        appExtendsData.setExt020(ContractEnum.EnumSection.TransferSign.getCode());//合同转让签署
        taskMessage.setExtendsData(appExtendsData);*/
        AppExtendsData appExtendsData = setExtendsData(crContracttransfer.getTransferId(), crContractbasic, crContractinfo, ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(),
                ContractEnum.EnumSection.TransferSign.getCode());
        taskMessage.setExtendsData(appExtendsData);
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时消息
        saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Trasfer.getCode(), sysUserinfo);
        return crContracttransferMapper.updateById(crContracttransfer) > 0;
    }

    /**
     * 合同终止审批结束
     */
    @Override
    @Transactional
    public boolean endApprove(String endId) {
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("终止合同ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend == null) {
            throw new NotFoundException("未查到相关终止记录！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContractend.getContractID());
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        crContractend.setState(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractend.setModifiedDate(LocalDateTime.now());
        crContractend.setModifiedBy(crContractend.getCreatedBy());
        crContractendMapper.updateById(crContractend);

        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Terminate.getCode()));//合同终止
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//合同审批结束
        crContractbasicMapper.updateById(crContractbasic);

        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContractend.getContractEndID());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Terminate.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
        }
        AppExtendsData appExtendsData = setExtendsData(crContractend.getContractEndID(), crContractbasic, crContractinfo, ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(),
                ContractEnum.EnumSection.TerminateSign.getCode());
        taskMessage.setExtendsData(appExtendsData);
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时消息
        saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Terminate.getCode(), sysUserinfo);
        return true;

    }

    /**
     * 合同终结审批结束
     */
    @Override
    @Transactional
    public boolean treatmentApprove(String endId) {
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("终结合同ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend == null) {
            throw new NotFoundException("未查到相关终止记录！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        crContractend.setState(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractend.setModifiedDate(LocalDateTime.now());
        crContractend.setModifiedBy(crContractend.getCreatedBy());
        crContractendMapper.updateById(crContractend);

        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Finality.getCode()));//合同终结
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//合同审批结束
        LocalDateTime date = LocalDateTime.now();
        crContractbasic.setFinalityDate(date);//合同终结时间
        crContractbasic.setModifiedDate(date);
        crContractbasicMapper.updateById(crContractbasic);
        return true;

    }

    /**
     * 审批通过
     *
     * @param taskId
     * @param contractId
     * @param opinion
     * @param categoryCode
     * @param variableList
     * @param type
     */
    @Override
    public AppCallResult complete(String taskId, String contractId, String opinion,
                                  String categoryCode, String variableList) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (userInfo == null) {
            context.setExecutorId(sysUserinfo.getfId().toString());//执行人ID
            context.setExecutorName(sysUserinfo.getfCname());//执行人名称
        } else {
            context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
            context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        }
        context.setTaskId(taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(opinion);//审批意见
        if (!StringUtils.isEmpty(variableList)) {
            List<AppVariableData> varList = new ArrayList<>();
            AppVariableData varData = new AppVariableData();
            varData.setVariableCode("roleUids");
            varData.setVariableValue(variableList);
            varList.add(varData);
            context.setVariableList(varList);
        }
        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        //20210603添加条件用于实现用户 审批和分发同时存在的情况
//        String routeType =  "" ;
//        if(!StringUtils.isEmpty(type)) {
//        	if(type.equals(Constants.ROUTE_DISPENSE_YES)) {
//        		routeType = Constants.ROUTE_TYPE_DISPENSE ;
//        	}else if(type.equals(Constants.ROUTE_TYPE_APPROVE)) {
//        		routeType = Constants.ROUTE_TYPE_APPROVE ;
//        	}
//        }
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        String json = JSON.toJSONString(context);
        return dpsRequest.complete(context);
    }

    /**
     * 后续活动查询审批人(流程发起前可以查询下一级审批人)
     */
    public UserInfoListVo getNextActivityApprover(String taskId, String categoryCode) {
        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        metas = setApproverMetasData(metas);
        ActivityApproveUserData approver = new ActivityApproveUserData();
        approver.setTaskId(taskId);
        approver.setMetas(metas);
        List<NextApproverVo> approverVo = dpsRequest.nextActivityApprover(approver);
        UserInfoListVo userList = null;
        if (approverVo != null && approverVo.size() > 0) {
            String roleName = approverVo.get(0).getParticipantList().get(0).getParticipantName();
            userList = userInfoRequest.queryUserInfoByRoleName(roleName);
        }
        return userList;
    }

    private List<AppMetasData> setApproverMetasData(List<AppMetasData> metas) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<AppMetasData> appMetasDataList = new ArrayList<>();
        if (metas != null) {
            for (AppMetasData appMetasData : metas) {
                if (appMetasData.getMetaCode().equals("szgt_contract_currentApprover")) {
                    appMetasData.setDataValue(userInfo.getSysUser().getfId().toString());//当前审批人
                    appMetasDataList.add(appMetasData);
                    break;
                }
            }
        }
        return appMetasDataList;
    }

    /**
     * 审批退回
     */
    @Override
    @Transactional
    public AppCallResult revert(ApproveEx approveEx) {
        if (StringUtils.isEmpty(approveEx.taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(approveEx.contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }

        List<WfMessage> wfMessages = wfMessageMapper.selectList(new QueryWrapper<WfMessage>().lambda().
                eq(WfMessage::getExecuteResult, Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode())).
                eq(WfMessage::getBusinessId, approveEx.contractId).
                eq(WfMessage::getLogicDel, 0).
                eq(WfMessage::getMessageState, ContractEnum.EnumMessageSate.UnRead).
                last("for update"));

        if (!CollectionUtils.isEmpty(wfMessages)) {
            throw new BaseException("请求已处理", 500);
        }

        CrContractbasic crContractbasic = new CrContractbasic();
        CrContractinfo crContractinfo = new CrContractinfo();
        CrContractchange crContractchange = new CrContractchange();
        CrContracttransfer crContracttransfer = new CrContracttransfer();
        CrContractend crContractend = new CrContractend();

        if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Make.getCode())) {
            crContractbasic = crContractbasicMapper.selectById(approveEx.contractId);
            if (crContractbasic == null) {
                throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
            }
            crContractinfo = crContractinfoMapper.selectById(approveEx.contractId);
            if (crContractinfo == null) {
                throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
            }
        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Change.getCode())) {
            crContractchange = crContractchangeMapper.selectById(approveEx.contractId);
            if (crContractchange != null) {
                crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
                crContractinfo = crContractinfoMapper.selectById(crContractchange.getContractID());
            }
        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Trasfer.getCode())) {
            crContracttransfer = crContracttransferMapper.selectById(approveEx.contractId);
            if (crContracttransfer != null) {
                crContractbasic = crContractbasicMapper.selectById(crContracttransfer.getContractID());
                crContractinfo = crContractinfoMapper.selectById(crContracttransfer.getContractID());
            }
        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Terminate.getCode())) {
            crContractend = crContractendMapper.selectById(approveEx.contractId);
            if (crContractend != null) {
                crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
                crContractinfo = crContractinfoMapper.selectById(crContractend.getContractID());
            }
        }

        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称

        context.setTaskId(approveEx.taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(approveEx.opinion);//审批意见
        List<AppMetasData> metas = dpsRequest.metas(approveEx.categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        AppCallResult appCallResult = dpsRequest.revert(context);//退回
        //插入待办消息
        //发送订立退回待办
        if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Make.getCode())) {
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            taskMessage.setExecutorId(sysUserinfo.getfId().toString());
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());

            AppExtendsData appExtendsData = new AppExtendsData();
            appExtendsData = setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Make.getCode(),
                    ContractEnum.EnumModule.Make.getMessage(),
                    ContractEnum.EnumSection.Make.getCode());

            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
            appCallResult = dpsRequest.taskMessage(taskMessage);
            String taskId = appCallResult.getId();
            Integer executeResult = Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode());//审批退回
            String opinion = approveEx.opinion;

            //插入临时消息
            saveTempMessageNew(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo, taskId, executeResult, opinion);

        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Change.getCode())) {
            //do 发送变更退回待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractchange.getContractChangeID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            taskMessage.setExecutorId(sysUserinfo.getfId().toString());
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());

            AppExtendsData appExtendsData = setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(),
                    ContractEnum.EnumModule.Perform.getMessage(),
                    ContractEnum.EnumSection.Change.getCode());

            taskMessage.setExtendsData(appExtendsData);
            appCallResult = dpsRequest.taskMessage(taskMessage);
            String taskId = appCallResult.getId();
            Integer executeResult = Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode());//审批退回
            String opinion = approveEx.opinion;

            //插入临时消息
            saveTempMessageNew(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo, taskId, executeResult, opinion);

        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Trasfer.getCode())) {
            //do 发送转让退回待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContracttransfer.getTransferId());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            taskMessage.setExecutorId(sysUserinfo.getfId().toString());
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());

            AppExtendsData appExtendsData = setExtendsData(crContracttransfer.getTransferId(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Transfer.getCode());

            taskMessage.setExtendsData(appExtendsData);
            appCallResult = dpsRequest.taskMessage(taskMessage);
            String taskId = appCallResult.getId();
            Integer executeResult = Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode());//审批退回
            String opinion = approveEx.opinion;

            //插入临时消息
            saveTempMessageNew(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo, taskId, executeResult, opinion);

        } else if (approveEx.categoryCode.equals(ContractEnum.EnumWorkFlow.Terminate.getCode())) {
            //do 发送转让退回待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractend.getContractEndID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Terminate.getCode());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            taskMessage.setExecutorId(sysUserinfo.getfId().toString());
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());

            AppExtendsData appExtendsData = setExtendsData(crContracttransfer.getTransferId(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Terminate.getCode());

            taskMessage.setExtendsData(appExtendsData);
            appCallResult = dpsRequest.taskMessage(taskMessage);
            String taskId = appCallResult.getId();
            Integer executeResult = Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode());//审批退回
            String opinion = approveEx.opinion;

            //插入临时消息
            saveTempMessageNew(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo, taskId, executeResult, opinion);
        }
        return appCallResult;
    }

    /**
     * 插入临时消息（新）
     */
    @Override
    public boolean saveTempMessageNew(AppExtendsData appExtendsData, String categoryCode, SysUserinfo executor, String taskId, Integer executeResult, String opinion) {
        WfMessage wfMessage = new WfMessage();
        LocalDateTime date = LocalDateTime.now();
        wfMessage.setMessageId(UUID.randomUUID().toString());
        wfMessage.setTaskId(taskId);
        wfMessage.setBusinessId(appExtendsData.getBusinessId());//业务数据ID
        wfMessage.setBusinessName(appExtendsData.getExt003());//业务名称
        wfMessage.setCategoryCode(categoryCode);//流程分类编码
        wfMessage.setExecutorId(executor.getfId().toString());//执行人id
        wfMessage.setExecutorName(executor.getfCname());//执行人姓名
        wfMessage.setExecuteResult(executeResult);//执行结果（审批通过，退回等）-1 Forword未审批 2Complete完成 审批通过  3Revert退回 7Skip 跳过 8Coordinate协同审查
        wfMessage.setOpinion(opinion);//审批意见
        wfMessage.setSendDate(date);//发送时间
        wfMessage.setExt001(appExtendsData.getExt001());//合同代办标识
        wfMessage.setExt002(appExtendsData.getExt002());//合同序号
        wfMessage.setExt003(appExtendsData.getExt003());//合同名称
        wfMessage.setExt004(appExtendsData.getExt004());//合同环节id
        wfMessage.setExt005(appExtendsData.getExt005());//主办部门ID
        wfMessage.setExt006(appExtendsData.getExt006());////主办部门名称
        wfMessage.setExt007(appExtendsData.getExt007());//是否框架合同
        wfMessage.setExt008(appExtendsData.getExt008());//经办人名称
        wfMessage.setExt009(appExtendsData.getExt009());//合同模块id
        if (!StringUtils.isEmpty(appExtendsData.getExt010())) {
            wfMessage.setExt010(new BigDecimal(appExtendsData.getExt010()));//标的金额
        }
        wfMessage.setExt011(appExtendsData.getExt011());//标的金额币种id
        wfMessage.setExt012(appExtendsData.getExt012());//标的金额币种中文
        wfMessage.setExt013(appExtendsData.getExt013());//合同类型1id
        wfMessage.setExt014(appExtendsData.getExt014());//合同类型2id
        wfMessage.setExt015(appExtendsData.getExt015());//合同类型3id
        wfMessage.setExt016(appExtendsData.getExt016());//合同类型4id
        wfMessage.setExt017(appExtendsData.getExt017());//合同类型名称
        wfMessage.setExt018(appExtendsData.getExt018());//合同环节中文名
        wfMessage.setExt019(appExtendsData.getExt019());//合同模块中文名
        wfMessage.setExt020(appExtendsData.getExt020());//合同环节id
        wfMessage.setMessageState(Integer.parseInt(ContractEnum.EnumMessageSate.UnRead.getCode()));
        wfMessage.setCreateDate(date);
        wfMessage.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));

        return wfMessageMapper.insert(wfMessage) > 0;
    }

    /**
     * 退回审批人
     */
    @Override
    @Transactional
    public AppCallResult backrevert(String businessId, String taskId, String opinion) {

        List<WfMessage> wfMessages = wfMessageMapper.selectList(new QueryWrapper<WfMessage>().lambda().
                eq(WfMessage::getExecuteResult, Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode())).
                eq(WfMessage::getTaskId, taskId).eq(WfMessage::getLogicDel, 0).
                eq(WfMessage::getMessageState, ContractEnum.EnumMessageSate.UnRead).
                last("for update"));

        if (CollectionUtils.isEmpty(wfMessages)) {
            throw new BaseException("请求已处理", 500);
        }

        dpsRequest.taskMessageComplete(taskId);//关闭临时消息
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        TaskOpinion revert = revertOpinion(businessId);//获取最新的退回信息
        if (revert != null && !StringUtils.isEmpty(revert.executeId)) {
            context.setExecutorId(revert.executorId);//执行人ID
            context.setExecutorName(revert.executorName);//执行人名称
        } else {
            UserInfo userInfo = currentUserUtil.currentUserInfo();
            context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
            context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        }

        context.setTaskId(revert.taskId);//退回的待办id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(opinion);//审批意见
        context.setBusinessId(businessId);
     /* List<AppMetasData> metas = dpsRequest.metas(approveEx.categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo);
        context.setMetasList(metas);*/
        String json = JSON.toJSONString(context);
        AppCallResult appCallResult = dpsRequest.backrevert(context);//退回审批人
        completeMessage(taskId);
        return appCallResult;
    }

    public List<AppMetasData> setAppMetasData(List<AppMetasData> metas, CrContractbasic crContractbasic,
                                              CrContractinfo crContractinfo, Integer activeType, List<Integer> userId, String routeType) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<AppMetasData> appMetasDataList = new ArrayList<>();
        if (metas != null) {
            for (AppMetasData appMetasData : metas) {
                if (appMetasData.getMetaCode().equals("szgt_contract_money")) {
                    if (crContractbasic.getContractObjectAmount() != null) {
                        appMetasData.setDataValue(crContractbasic.getContractObjectAmount().toString());//合同金额
                    } else {
                        appMetasData.setDataValue("");//合同金额
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contact_type1")) {
                    if (crContractbasic.getType1() != null) {
                        appMetasData.setDataValue(crContractbasic.getType1().toString());//合同类别1
                    } else {
                        appMetasData.setDataValue("");//合同类别1
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contact_type4")) {
                    if (crContractbasic.getType4() != null) {
                        appMetasData.setDataValue(crContractbasic.getType4().toString());//合同类别4
                    } else {
                        appMetasData.setDataValue("");//合同类别4
                    }
                }

                if (appMetasData.getMetaCode().equals("szgt_contract_moneySource2")) {
                    if (crContractinfo.getMoneySource() != null) {
                        appMetasData.setDataValue(crContractinfo.getMoneySource2());//资金来源二级
                    } else {
                        appMetasData.setDataValue("");//资金来源二级
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_selectWay1")) {
                    if (crContractinfo.getSelectWay1() != null) {
                        appMetasData.setDataValue(crContractinfo.getSelectWay1().toString());//选商方式一级
                    } else {
                        appMetasData.setDataValue("");//选商方式一级
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_selectWay2")) {
                    if (crContractinfo.getSelectWay2() != null) {
                        appMetasData.setDataValue(crContractinfo.getSelectWay2().toString());//选商方式二级
                    } else {
                        appMetasData.setDataValue("");//选商方式二级
                    }
                }//是否框架合同
                if (appMetasData.getMetaCode().equals("szgt_contract_isFrameContract")) {
                    if (crContractinfo.getIsFrameContract() != null) {
                        appMetasData.setDataValue(crContractinfo.getIsFrameContract().toString());//是否框架合同
                    } else {
                        appMetasData.setDataValue("");//是否框架合同
                    }
                }//当前节点审批人
                if (appMetasData.getMetaCode().equals("szgt_contract_currentApprover")) {
                    if (activeType == ContractEnum.EnumActivityType.FENFA.getCode()) {
                        if (userId != null && userId.size() > 0) {
                            appMetasData.setDataValue(Joiner.on(",").join(userId));//当前审批人
                        } else {
                            appMetasData.setDataValue("");
                        }
                    } else {
                        if (crContractinfo.getIsFrameContract() != null) {
                            appMetasData.setDataValue(userInfo.getSysUser().getfId().toString());//当前审批人
                        } else {
                            appMetasData.setDataValue("");//当前审批人
                        }
                    }
                }//金额是否确定
                if (appMetasData.getMetaCode().equals("szgt_contract_isMakeSure")) {
                    if (crContractbasic.getCreatedBy() != null) {//金额是否确定  0不确定 1确定 2无金额
                        appMetasData.setDataValue(crContractbasic.getIsMakeSureMoney() == null ? "" : crContractbasic.getIsMakeSureMoney().toString());
                    } else {
                        appMetasData.setDataValue("");//金额是否确定
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_approve")) {//独立审批流程
                    if (crContractinfo.getIsFrameContract() != null && routeType.equals(Constants.ROUTE_TYPE_APPROVE)) {
                        appMetasData.setDataValue(routeType);
                    } else {
                        appMetasData.setDataValue("");
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_dispense")) {//独立分发流程
                    if (crContractinfo.getIsFrameContract() != null && routeType.equals(Constants.ROUTE_TYPE_DISPENSE)) {
                        appMetasData.setDataValue(routeType);
                    } else {
                        appMetasData.setDataValue("");
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_SignBodyName")) {
                    if (!StringUtils.isEmpty(crContractinfo.getMySignBodyName())) {
                        appMetasData.setDataValue(crContractinfo.getMySignBodyName());//签约主体
                    } else {
                        appMetasData.setDataValue("");//签约主体
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_TextType")) {
                    if (crContractinfo.getTextType() !=null &&crContractinfo.getTextType() > 0) {
                        appMetasData.setDataValue(crContractinfo.getTextType().toString());//文本类型
                    } else {
                        appMetasData.setDataValue("");//文本类型
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contact_type3")) {
                    if (crContractbasic.getType3() != null) {
                        appMetasData.setDataValue(crContractbasic.getType3().toString());//合同类别3
                    } else {
                        appMetasData.setDataValue("");//合同类别3
                    }
                }
                if (appMetasData.getMetaCode().equals("Szgt_MainUserID")) {
                    if (!StringUtils.isEmpty(crContractbasic.getCreatedBy()) ) {
                        appMetasData.setDataValue(crContractbasic.getCreatedBy());//经办人流程条件
                    } else {
                        appMetasData.setDataValue("");//经办人
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_MoneyFlow")) {
                    if (crContractbasic.getMoneyFlow() !=null && crContractbasic.getMoneyFlow() > 0) {
                        appMetasData.setDataValue(crContractbasic.getMoneyFlow().toString());//资金流向
                    } else {
                        appMetasData.setDataValue("");//资金流向
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contact_type2")) {
                    if (crContractbasic.getType2() != null) {
                        appMetasData.setDataValue(crContractbasic.getType2().toString());//合同类别2
                    } else {
                        appMetasData.setDataValue("");//合同类别2
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_maindept")) {
                    if (crContractbasic.getMainDeptID() != null) {
                        appMetasData.setDataValue(crContractbasic.getMainDeptID().toString());//主办部门
                    } else {
                        appMetasData.setDataValue("");//主办部门
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_mainorg")) {//主办单位
                    if (crContractbasic.getMainOrgID() != null) {
                        appMetasData.setDataValue(crContractbasic.getMainOrgID().toString());
                    } else {
                        appMetasData.setDataValue("");
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_planmoney")) {//计划金额
                    if (crContractinfo.getPlanMoney() != null) {
                        appMetasData.setDataValue(crContractinfo.getPlanMoney().toString());
                    } else {
                        appMetasData.setDataValue("");
                    }
                }
                if (appMetasData.getMetaCode().equals("szgt_contract_moneySource")) {
                    if (crContractinfo.getMoneySource() != null) {
                        appMetasData.setDataValue(crContractinfo.getMoneySource().toString());//资金来源一级
                    } else {
                        appMetasData.setDataValue("");//资金来源一级
                    }
                }
                appMetasDataList.add(appMetasData);

            }
        }
        return appMetasDataList;
    }

    /**
     * 合同审批历史
     */
    @Override
    public List<TaskOpinion> opinion(String businessId) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(businessId)) {
            throw new NotFoundException("businessId为空！", Constants.FAILCODE);
        }
        List<AppTaskOpinionData> appTaskOpinionDataList = dpsRequest.opinion(businessId);
        List<TaskOpinion> taskOpinionList = new ArrayList<>();
        if (appTaskOpinionDataList != null && appTaskOpinionDataList.size() > 0) {

            for (AppTaskOpinionData appTaskOpinionData : appTaskOpinionDataList) {
                TaskOpinion taskOpinion = new TaskOpinion();
                BeanUtils.copyProperties(appTaskOpinionData, taskOpinion);
                taskOpinion.opinionId = appTaskOpinionData.getOpinionId();
                taskOpinion.appId = appTaskOpinionData.getAppId();
                taskOpinion.taskId = appTaskOpinionData.getTaskId();
                if (!StringUtils.isEmpty(appTaskOpinionData.getAgentId())) {
                    taskOpinion.certigier = appTaskOpinionData.getOriginExecutorName();
                }
                taskOpinion.executorId = appTaskOpinionData.getExecutorId();
                taskOpinion.executorName = appTaskOpinionData.getExecutorName();
                taskOpinion.taskType = appTaskOpinionData.getTaskType();
                taskOpinion.executeId = appTaskOpinionData.getExecuteId();
                taskOpinion.businessId = appTaskOpinionData.getBusinessId();
                taskOpinion.businessCode = appTaskOpinionData.getBusinessCode();
                taskOpinion.businessName = appTaskOpinionData.getBusinessName();
                taskOpinion.executeResult = appTaskOpinionData.getExecuteResult();
                taskOpinion.executeResultName = appTaskOpinionData.getExecuteResultName();
                if (!StringUtils.isEmpty(appTaskOpinionData.getExecuteResult())) {
                    if (appTaskOpinionData.getExecuteResult() == -1) {
                        taskOpinion.executeResultName = "未审批";
                    } else if (appTaskOpinionData.getExecuteResult() == 2) {
                        taskOpinion.executeResultName = "审批通过";
                    } else if (appTaskOpinionData.getExecuteResult() == 3) {
                        taskOpinion.executeResultName = "退回";
                    } else if (appTaskOpinionData.getExecuteResult() == 7) {
                        taskOpinion.executeResultName = "跳过";
                    } else if (appTaskOpinionData.getExecuteResult() == 8 || appTaskOpinionData.getExecuteResult() == 20) {
                        taskOpinion.executeResultName = "协同审查";
                    } else if (appTaskOpinionData.getExecuteResult() == 10) {
                        taskOpinion.executeResultName = "代理审查";
                    } else if (appTaskOpinionData.getExecuteResult() == 27) {
                        taskOpinion.executeResultName = "审批通过";
                    } else if (appTaskOpinionData.getExecuteResult() == 22) {
                        taskOpinion.executeResultName = "审批通过(自动审批)";//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理
                    }
                }
                taskOpinion.remark = appTaskOpinionData.getRemark();
                taskOpinion.opinion = appTaskOpinionData.getOpinion();
                taskOpinion.executeDate = appTaskOpinionData.getExecuteDate();
                taskOpinion.dataState = appTaskOpinionData.getDataState();
                taskOpinion.activityName = appTaskOpinionData.getActivityName();
                taskOpinion.categoryCode = appTaskOpinionData.getCategoryCode();
                if (!StringUtils.isEmpty(appTaskOpinionData.getExecuteId())) {
                    long startTimeId = System.currentTimeMillis();
                    List<Integer> orgIds = userInfoRequest.queryOrgs(appTaskOpinionData.getExecutorId());
                    long endTimeId = System.currentTimeMillis();
                    log.info("-----审批记录=查询用户所在组织机构id总时间= {} ms", (endTimeId - startTimeId));
//                  2021-06-21 注释掉 影响性能，然并卵
//                    long startTimeOrg = System.currentTimeMillis() ;
//                    List<SysOrganization> orgs = organizationRequest.queryOrgByIdBatch(orgIds.toArray(new Integer[0]));
//                    long endTimeOrg = System.currentTimeMillis() ; 
//                    log.info("-----审批记录=批量查询组织机构总时间= {} ms",(endTimeOrg - startTimeOrg));
//                    if(!CollectionUtils.isEmpty(orgs)){
//                        List<String> orgCodes = orgs.stream().map(SysOrganization::getfCode).collect(Collectors.toList());
//                        //如果是公司领导节点下优先显示单位
//                        if(orgCodes.contains("20000137")){
//                            orgs.sort(Comparator.comparing(SysOrganization::getfType, Comparator.reverseOrder()));
//                            taskOpinion.executorDeptId = orgs.get(0).getfId().toString();
//                            taskOpinion.executorDeptName = orgs.get(0).getfName();
//                            taskOpinion.executorOrgId = orgs.get(0).getfId().toString();
//                            taskOpinion.executorOrgName = orgs.get(0).getfName();
//                        }else{
//                            orgs.sort(Comparator.comparing(SysOrganization::getfType));
                    if (orgIds != null && orgIds.size() > 0) {
                        int depId = orgIds.size() > 1 ? orgIds.get(orgIds.size() - 1) : orgIds.get(0);
                        long startTimeDep = System.currentTimeMillis();
                        SysOrganization sysDep = organizationRequest.queryOrganization(depId);
                        taskOpinion.executorDeptId = String.valueOf(depId);//orgs.get(0).getfId().toString();
                        if (sysDep != null) {
                            taskOpinion.executorDeptName = sysDep.getfName();//orgs.get(0).getfName();
                        }
                        long endTimeDep = System.currentTimeMillis();
                        log.info("-----审批记录=查询部门时间= {} ms", (endTimeDep - startTimeDep));
                        long startTimeOrg = System.currentTimeMillis();
                        SysOrganization sysOrg = organizationRequest.getOrgCompany(orgIds.get(0));//orgs.get(0).getfId()
                        if (sysOrg != null) {
                            taskOpinion.executorOrgId = sysOrg.getfId().toString();
                            taskOpinion.executorOrgName = sysOrg.getfName();
                        }
                        long endTimeOrg = System.currentTimeMillis();
                        log.info("-----审批记录=查询组织机构时间= {} ms", (endTimeOrg - startTimeOrg));
                    }
//                        }
//                    }
                }

                AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
                attachmentQueryVo.setPropertyID(taskOpinion.businessId);
                attachmentQueryVo.setPropertyModel(taskOpinion.taskId);
                attachmentQueryVo.setAttachmentType("1");
                attachmentQueryVo.setAttachmentType("1");
                DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
                if (!CollectionUtils.isEmpty(listDataResult.getData())) {
                    taskOpinion.attachmentResultVo = listDataResult.getData().get(0);
                }

                taskOpinionList.add(taskOpinion);
            }
        }
        long endTime = System.currentTimeMillis();
        log.info("-----审批历史方法总时间= {} ms", (endTime - startTime));
        return taskOpinionList;
        //return dpsRequest.opinion(businessId);
    }

    public String queryTaskDataByTaskId(String taskId) {
        String certigierName = "";
        ExecuteTaskData taskData = dpsRequest.taskdetail(taskId);
        if (!StringUtils.isEmpty(taskData.getAgentId())) {
            certigierName = taskData.getExecutorName();
        }
        return certigierName;
    }

    /**
     * 获取退回最新退回意见
     */
    public TaskOpinion revertOpinion(String businessId) {
        if (StringUtils.isEmpty(businessId)) {
            throw new NotFoundException("businessId为空！", Constants.FAILCODE);
        }
        TaskOpinion taskOpinion = new TaskOpinion();
        List<AppTaskOpinionData> appTaskOpinionDataList = dpsRequest.opinion(businessId);
        if (appTaskOpinionDataList != null && appTaskOpinionDataList.size() > 0) {
            AppTaskOpinionData appTaskOpinionData = appTaskOpinionDataList.get(0);
            if (appTaskOpinionData.getExecuteResult() != 3) {
                //退回
                return taskOpinion;
            }
            BeanUtils.copyProperties(appTaskOpinionData, taskOpinion);
            taskOpinion.opinionId = appTaskOpinionData.getOpinionId();
            taskOpinion.appId = appTaskOpinionData.getAppId();
            taskOpinion.taskId = appTaskOpinionData.getTaskId();
            taskOpinion.taskType = appTaskOpinionData.getTaskType();
            taskOpinion.executeId = appTaskOpinionData.getExecuteId();
            taskOpinion.businessId = appTaskOpinionData.getBusinessId();
            taskOpinion.businessCode = appTaskOpinionData.getBusinessCode();
            taskOpinion.businessName = appTaskOpinionData.getBusinessName();
            taskOpinion.executeResult = appTaskOpinionData.getExecuteResult();
            taskOpinion.executeResultName = appTaskOpinionData.getExecuteResultName();
            if (!StringUtils.isEmpty(appTaskOpinionData.getExecuteResult())) {
                if (appTaskOpinionData.getExecuteResult() == -1) {
                    taskOpinion.executeResultName = "未审批";
                } else if (appTaskOpinionData.getExecuteResult() == 2) {
                    taskOpinion.executeResultName = "审批通过";
                } else if (appTaskOpinionData.getExecuteResult() == 3) {
                    taskOpinion.executeResultName = "退回";
                } else if (appTaskOpinionData.getExecuteResult() == 7) {
                    taskOpinion.executeResultName = "跳过";
                } else if (appTaskOpinionData.getExecuteResult() == 8 || appTaskOpinionData.getExecuteResult() == 20) {
                    taskOpinion.executeResultName = "协同审查";
                } else if (appTaskOpinionData.getExecuteResult() == 10) {
                    taskOpinion.executeResultName = "代理审查";
                } else if (appTaskOpinionData.getExecuteResult() == 27) {
                    taskOpinion.executeResultName = "审批通过";
                }
            }
            taskOpinion.remark = appTaskOpinionData.getRemark();
            taskOpinion.opinion = appTaskOpinionData.getOpinion();
            taskOpinion.executorId = appTaskOpinionData.getExecutorId();
            taskOpinion.executorName = appTaskOpinionData.getExecutorName();
            taskOpinion.executeDate = appTaskOpinionData.getExecuteDate();
            taskOpinion.dataState = appTaskOpinionData.getDataState();
            taskOpinion.activityName = appTaskOpinionData.getActivityName();
            taskOpinion.categoryCode = appTaskOpinionData.getCategoryCode();
            if (!StringUtils.isEmpty(appTaskOpinionData.getExecuteId())) {
                List<Integer> orgIds = userInfoRequest.queryOrgs(appTaskOpinionData.getExecutorId());
                if (orgIds != null && orgIds.size() > 0) {
                    for (Integer orgId : orgIds) {
                        SysOrganization sysOrganization = organizationRequest.queryOrganization(orgId);
                        if (sysOrganization != null) {
                            taskOpinion.executorDeptId = sysOrganization.getfId().toString();
                            taskOpinion.executorDeptName = sysOrganization.getfName();
                        }
                        sysOrganization = organizationRequest.getOrgCompany(orgId);//上一级单位/企业
                        if (sysOrganization != null) {
                            taskOpinion.executorOrgId = sysOrganization.getfId().toString();
                            taskOpinion.executorOrgName = sysOrganization.getfName();
                        }
                    }
                }
            }

        }
        return taskOpinion;
    }


    /**
     * 协同审批
     */
    @Override
    public AppCallResult coordinate(String taskId, String contractId, String opinion, String coUserId, Integer coordinateType, String categoryCode) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称

        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(coUserId));
        if (sysUserinfo != null) {
            context.setCoUserId(sysUserinfo.getfId().toString());//协同人Id
            context.setCoUserName(sysUserinfo.getfCname());//协同人名称
        } else {
            context.setCoUserId(coUserId);//协同人Id
        }
        if (coordinateType != null) {
            context.setCoordinateType(coordinateType);//协同审查类型，标识是否等待协同者处理。默认：1 0：等待，1：不等待
        }

        context.setTaskId(taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(opinion);//审批意见
        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        String json = JSON.toJSONString(context);
        return dpsRequest.coordinate(context);
    }

    /**
     * 个人助理合同待办列表
     * 2021-04-09新增
     *
     * @param userId 用于待办转交中转入的原办理人
     */
    @Override
    public PagedList taskToDo(String businessCodeOrName, String workFlowId, String contractName, String ruleserialNum,
                              String isFrameContract, Integer pageSize, Integer pageNum, boolean isMobile, Integer userId) {
        long startTime = System.currentTimeMillis();
//        UserInfo userInfo = currentUserUtil.currentUserInfo();
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }

        String sql = "ext001='contract' ";
        if (!StringUtils.isEmpty(ruleserialNum)) {
            sql += "and ext002  like  '%" + ruleserialNum + "%' ";
        }
        if (!StringUtils.isEmpty(contractName)) {
            sql += "and ext003  like  '%" + contractName + "%' ";
        }
        if (!StringUtils.isEmpty(isFrameContract)) {
            sql += " and ext007  =  '" + isFrameContract + "' ";
        }

        TaskQueryModel taskQueryModel = new TaskQueryModel();
        /* taskQueryModel.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());*/
        taskQueryModel.setIncludeMsg(1);
        taskQueryModel.setQuery(sql);
        taskQueryModel.setSize(pageSize);
        taskQueryModel.setCurrent(pageNum);
        if (userId > 0) {
            taskQueryModel.setUserId(userId);
        }
        //2021-5-20 杨栋宇要求注释掉
//        if (isMobile) {
//            taskQueryModel.setTaskType("1");
//        }

//        String dd = JSON.toJSONString(taskQueryModel);
        List<ExecuteTaskData> list = new ArrayList<>();
        //待办列表查询
        long startTimeFlow = System.currentTimeMillis();
        PagedList pagedList = dpsRequest.pagedtodotask(taskQueryModel);
        long endTimeFlow = System.currentTimeMillis();
        log.info("-----工作流待办列表-方法-总时间= {} ms", (endTimeFlow - startTimeFlow));
        if (pagedList.getExecuteTaskList() != null && pagedList.getExecuteTaskList().size() > 0) {
            for (ExecuteTaskData executeTaskData : pagedList.getExecuteTaskList()) {
                if (!StringUtils.isEmpty(executeTaskData.getTaskId())) {
                    QueryWrapper<WfMessage> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(WfMessage::getTaskId, executeTaskData.getTaskId());
                    queryWrapper.lambda().eq(WfMessage::getExecuteResult, ContractEnum.EnumExecuteResult.Revert.getCode());//审批退回
                    List<WfMessage> wfMessageList = wfMessageMapper.selectList(queryWrapper);
                    if (wfMessageList != null && wfMessageList.size() > 0) {
                        WfMessage wfMessage = wfMessageList.get(0);
                        executeTaskData.setExecuteResult(Integer.parseInt(ContractEnum.EnumExecuteResult.Revert.getCode()));
                        executeTaskData.setOpinion(wfMessage.getOpinion());
                    }
                }
//                CrContractbasic crContractbasic = crContractbasicMapper.selectById(executeTaskData.getBusinessId());
//                if(crContractbasic != null){
//                    executeTaskData.setExt003(crContractbasic.getContractName());
//                }
                list.add(executeTaskData);
            }
        }

        pagedList.setExecuteTaskList(list);
        long endTimeMethod = System.currentTimeMillis();
        log.info("-----合同待办列表-方法-总时间= {} ms,isMobile={}", (endTimeMethod - startTime), isMobile);
        return pagedList;

//       return dpsRequest.pagedtodotask(userInfo.getSysUser().getfId().toString(), pageNum, pageSize, businessCodeOrName, workFlowId, extMap);
    }

    /**
     * 其他模块待办查询
     */
    @Override
    public PagedList taskToDoOther(String businessCodeOrName, String workFlowId, String contractName, String ruleserialNum, String isFrameContract,
                                   String ext004, String ext020, String ext010, Integer operator, Integer pageSize, Integer pageNum) {
//        UserInfo userInfo = currentUserUtil.currentUserInfo();
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }

        String sql = "ext001='contract' ";//合同系统标记
        if (!StringUtils.isEmpty(ruleserialNum)) {
            sql += "and ext002  like  '%" + ruleserialNum + "%' ";//合同序号
        }
        if (!StringUtils.isEmpty(contractName)) {
            sql += "and ext003  like  '%" + contractName + "%' ";//合同名称
        }
        if (!StringUtils.isEmpty(isFrameContract)) {
            sql += " and ext007  =  '" + isFrameContract + "' ";//是否框架合同
        }
        if (!StringUtils.isEmpty(ext004)) {
            sql += " and ext004  =  '" + ext004 + "' ";//合同模块
        }
        if (!StringUtils.isEmpty(ext020)) {
            sql += " and ext020  =  '" + ext020 + "' ";//合同环节
        }
        if (!StringUtils.isEmpty(ext010)) {
            if (operator == null) {
                sql += " and ext010 = '" + ext010 + "' "; //标的金额
            } else {
                if (operator == -1) {
                    sql += " and ext010 < '" + ext010 + "' ";
                } else if (operator == 0) {
                    sql += " and ext010 = '" + ext010 + "' ";
                } else if (operator == 1) {
                    sql += " and ext010 > '" + ext010 + "' ";
                }
            }
        }


        TaskQueryModel taskQueryModel = new TaskQueryModel();
        /*  taskQueryModel.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());*/
        taskQueryModel.setIncludeMsg(1);
        taskQueryModel.setQuery(sql);
        taskQueryModel.setSize(pageSize);
        taskQueryModel.setCurrent(pageNum);

        String json = JSON.toJSONString(taskQueryModel);
        List<ExecuteTaskData> list = new ArrayList<>();
        PagedList pagedList = dpsRequest.pagedtodotask(taskQueryModel);
        if (pagedList.getExecuteTaskList() != null && pagedList.getExecuteTaskList().size() > 0) {
            for (ExecuteTaskData executeTaskData : pagedList.getExecuteTaskList()) {
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(executeTaskData.getBusinessId());
                if (crContractbasic != null) {
                    executeTaskData.setExt003(crContractbasic.getContractName());
                }
                list.add(executeTaskData);
            }
        }
        return pagedList;

    }

    /**
     * 合同已办列表
     */
    @Override
    public PagedList taskDone(String businessCodeOrName, String workFlowId, String contractName,
                              String ruleserialNum, String isFrameContract, String offereeName, Integer pageSize,
                              Integer pageNum) {
        long startTime = System.currentTimeMillis();
//        UserInfo userInfo = currentUserUtil.currentUserInfo();
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        /*Map<String, Object> extMap = new HashMap<>();
        extMap.put("ext001", "contract");
        if (!StringUtils.isEmpty(ruleserialNum)) {
            extMap.put("ext002", ruleserialNum);
        }
        if (!StringUtils.isEmpty(contractName)) {
            extMap.put("ext003", contractName);
        }
        if (!StringUtils.isEmpty(isFrameContract)) {
            extMap.put("ext007", isFrameContract);
        }*/
      /*  "businessCodeOrName": "string",//业务编码或者名称
                "categoryCode": "string",//分类编码
                "current": 0,//当前页数
                "endTime": "2020-4-16 16:34:59",//结束时间
                "includeMsg": 0,//默认为0   0:(不查询待办消息表)1：(待办消息表)
                "query": "string",//扩展字段 列如ext001='a' and ext002 > 100(类似于sql语句条件)
                "size": 0,//每页格式
                "startTime": "2020-4-16 16:34:59",//开始时间*/
        String sql = "ext001='contract'";
        if (!StringUtils.isEmpty(ruleserialNum)) {
            sql += " and ext002  like  '%" + ruleserialNum + "%' ";
        }
        if (!StringUtils.isEmpty(contractName)) {
            sql += "and ext003  like  '%" + contractName + "%' ";
        }
        if (!StringUtils.isEmpty(isFrameContract)) {
            sql += " and ext007  =  '" + isFrameContract + "' ";
        }

        if (!StringUtils.isEmpty(offereeName)) {
            List<String> contractIdList = new ArrayList<>();
            QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
            contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getOffereeName, offereeName);
            List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
            if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
                for (CrContractofferee contractofferee : crContractoffereeList) {
                    contractIdList.add(contractofferee.getContractID());
                }
            }
            if (contractIdList != null && contractIdList.size() > 0) {
                String IdStr = contractIdList.toString();
                String ids = IdStr.substring(1, IdStr.length() - 2);
                if (!StringUtils.isEmpty(ids)) {
                    sql += " and businessId  in  ('" + ids + "') ";
                }
            } else {
                String ids = "";
                sql += " and businessId  in  ('" + ids + "') ";
            }
        }

        TaskQueryModel taskQueryModel = new TaskQueryModel();
        /*  taskQueryModel.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());*/
        taskQueryModel.setIncludeMsg(1);
        taskQueryModel.setQuery(sql);
        taskQueryModel.setSize(pageSize);
        taskQueryModel.setCurrent(pageNum);
        String groupBy = "ext002";
        taskQueryModel.setGroupBy(groupBy);

        String json = JSON.toJSONString(taskQueryModel);
        long startTimeFlow = System.currentTimeMillis();
        PagedList pageddonetask = dpsRequest.pageddonetask(taskQueryModel);
        long endTimeFlow = System.currentTimeMillis();
        log.info("-----请求工作流合同已办列表-总时间= {} ms", (endTimeFlow - startTimeFlow));
        if (!CollectionUtils.isEmpty(pageddonetask.getExecuteTaskList())) {
            for (ExecuteTaskData executeTaskData : pageddonetask.getExecuteTaskList()) {
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(executeTaskData.getBusinessId());
                if (crContractbasic != null) {
                    executeTaskData.setExt003(crContractbasic.getContractName());
                }
            }
        }
        long endTimeMethod = System.currentTimeMillis();
        log.info("-----合同已办列表-方法-总时间= {} ms", (endTimeMethod - startTime));
        return pageddonetask;
//      return dpsRequest.pagedtodotask(userInfo.getSysUser().getfId().toString(), pageNum, pageSize, businessCodeOrName, workFlowId, extMap);
    }

    /**
     * 代理审批
     *
     * @param taskId       任务ID
     * @param contractId   合同ID
     * @param opinion      审批意见
     * @param categoryCode 类型code
     */
    @Override
    public AppCallResult proxy(String taskId, String contractId, String opinion, String categoryCode) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        context.setCoUserId(userInfo.getSysUser().getfId().toString());//协同/代理审查人Id
        context.setCoUserName(userInfo.getSysUser().getfCname());//协同/代理审查人名称
        context.setTaskId(taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(opinion);//审批意见

        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        return dpsRequest.proxy(context);
    }

    /**
     * 后续审批人
     */
    @Override
    public List<Executor> nextexecutor(String taskId, String contractId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }

        List<Executor> executorList = new ArrayList<>();

        ExecuteTaskData taskdetail = dpsRequest.taskdetail(taskId);
        if (taskdetail == null) {
            throw new NotFoundException("未查到相关待办详情记录！", Constants.FAILCODE);
        }
        //如果是分发显示分发参与者
        if (taskdetail.getTaskType() == TaskType.Selective) {
            List<AppParticipantData> taskparticipant = this.taskparticipant(taskId);

            if (!CollectionUtils.isEmpty(taskparticipant)) {
                Map<String, AppParticipantData> participantMap = taskparticipant.stream().collect(Collectors.toMap(AppParticipantData::getParticipantValue, Function.identity(), (k1, k2) -> k1));
                Integer[] integers = participantMap.keySet().stream().map(Integer::parseInt).toArray(Integer[]::new);
                List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(integers);
                Map<Integer, SysUserinfo> userMap = null;
                if (!CollectionUtils.isEmpty(sysUserinfos)) {
                    userMap = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, Function.identity(), (k1, k2) -> k1));
                }

                for (AppParticipantData participantData : taskparticipant) {
                    Executor executor = new Executor();
                    executor.activityId = taskdetail.getActivityId();
                    executor.participantId = participantData.getParticipantId();
                    executor.instanceId = taskdetail.getInstanceId();
                    executor.userId = participantData.getParticipantValue();
                    executor.userName = participantData.getParticipantName();
                    if (!StringUtils.isEmpty(executor.userId)) {
                        UserUtil.UserUnitDeptInfo unitDeptInfo = userUtil.getOrgPath(Integer.parseInt(executor.userId));
                        if (unitDeptInfo.getDept() != null) {
                            executor.deptId = unitDeptInfo.getDept().getfId().toString();
                            executor.deptName = unitDeptInfo.getDept().getfName();
                        }

                        if (unitDeptInfo.getUnit() != null) {
                            executor.orgId = unitDeptInfo.getUnit().getfId().toString();
                            executor.orgName = unitDeptInfo.getUnit().getfName();
                        }

                        executor.organisePath = unitDeptInfo.toString();
                    }

                    executor.category = participantData.getCategory();
                    executor.positionName = userMap.isEmpty() ? "" : userMap.get(Integer.parseInt(participantData.getParticipantValue())).getfPosition();
                    executorList.add(executor);
                }
            }
            return executorList;

        } else {
            List<ExecutorData> executorDataList = dpsRequest.nextexecutor(taskId);
            if (executorDataList != null && executorDataList.size() > 0) {
                for (ExecutorData executorData : executorDataList) {
                    Executor executor = new Executor();
                    executor.activityId = executorData.getActivityId();
                    executor.participantId = executorData.getParticipantId();
                    executor.instanceId = executorData.getInstanceId();
                    executor.activityId = executorData.getActivityId();
                    executor.userId = executorData.getUserId();
                    executor.userName = executorData.getUserName();
                    if (!StringUtils.isEmpty(executor.userId)) {
                        UserUtil.UserUnitDeptInfo unitDeptInfo = userUtil.getOrgPath(Integer.parseInt(executor.userId));
                        if (unitDeptInfo.getDept() != null) {
                            executor.deptId = unitDeptInfo.getDept().getfId().toString();
                            executor.deptName = unitDeptInfo.getDept().getfName();
                        }

                        if (unitDeptInfo.getUnit() != null) {
                            executor.orgId = unitDeptInfo.getUnit().getfId().toString();
                            executor.orgName = unitDeptInfo.getUnit().getfName();
                        }

                        executor.organisePath = unitDeptInfo.toString();
                    }

                    executor.category = executorData.getCategory();
                    executor.positionName = executorData.getPositionName();
                    if (StringUtils.isEmpty(executor.positionName)) {
                        if (!StringUtils.isEmpty(executor.userId)) {
                            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(executor.userId));
                            executor.positionName = sysUserinfo.getfPosition();
                        }
                    }
                    executor.kind = executorData.getKind();
                    executor.dataCategory = executorData.getDataCategory();
                    executorList.add(executor);
                }
            }
            return executorList;
        }
    }

    /**
     * 分发操作
     */
    @Override
    public AppCallResult reactivate(Reactivate reactivate) {
        if (StringUtils.isEmpty(reactivate.taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(reactivate.contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(reactivate.contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(reactivate.contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        //context.setCoUserId(userInfo.getSysUser().getfId().toString());//协同/代理审查人Id
        //context.setCoUserName(userInfo.getSysUser().getfCname());//协同/代理审查人名称
        context.setTaskId(reactivate.taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(reactivate.opinion);//审批意见
        List<Integer> userId = null;
        //20210608添加条件用于实现用户 分发和审批同时存在的情况
        if (StringUtils.isEmpty(reactivate.type)) {
            DataResult<?> crcontractinfo=makeService.getContractById(reactivate.contractId);//客户要求修改了合同模板
            String Msg=Constants.SEND_MESSAGE_AGENT;
            String mainDeptName="";
            if(crcontractinfo.getData()!=null){
                JSONObject jsonObject = (JSONObject) crcontractinfo.getData() ;
                if(jsonObject.get("mainDeptName").toString().endsWith("部")||jsonObject.get("mainDeptName").toString().equals("")){
                    mainDeptName=jsonObject.get("mainDeptName").toString();
                }else{
                    mainDeptName= jsonObject.get("mainDeptName").toString()+"部";
                }
                Msg=Constants.SEND_MESSAGE+"《"+jsonObject.get("contractName")+"》，发起人："+mainDeptName+ jsonObject.get("mainUserName");
            }
            context.setParticipantJson(JSON.toJSONString(reactivate.participantJson));//分发参与者
            //2021-05-12 添加短信提醒--开始
            List<Participant> participant = reactivate.participantJson;
            userId = participant.stream().map(i -> i.participantValue).map(Integer::valueOf).collect(Collectors.toList());
            officeRequest.sendMsg(userId, Msg, "分发操作");
            //2021-05-12 添加短信提醒--结束
        } else {
            if (reactivate.type.equals(Constants.ROUTE_APPROVE_YES)) {
                //ParticipantJson 在分发类型中为必填项，默认伪造用户数据项
                List<Participant> list = new ArrayList<>();
                Participant part = new Participant();
                part.participantValue = "2";
                part.participantCode = "qwer";
                part.executeType = "1";
                part.category = "1";
                list.add(part);
                context.setParticipantJson(JSON.toJSONString(list));//分发参与者
                //封装历史审批指定类型记录
                List<AppVariableData> variableList = new ArrayList<>();
                AppVariableData appvar = new AppVariableData();
                appvar.setVariableCode("opinionType");
                appvar.setVariableValue("1");
                variableList.add(appvar);
                context.setVariableList(variableList);
            }

        }
        List<AppMetasData> metas = dpsRequest.metas(reactivate.categoryCode);//流程类型
        //20210603添加条件用于实现用户 审批和分发同时存在的情况
        String routeType = (!StringUtils.isEmpty(reactivate.type) && reactivate.type.equals(Constants.ROUTE_APPROVE_YES)) ? Constants.ROUTE_TYPE_APPROVE : "";
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, reactivate.activityType, userId, routeType);
        context.setMetasList(metas);
        //String json = JSON.toJSONString(context);
        AppCallResult appCallResult = dpsRequest.reactivate(context);

        return appCallResult;

    }


    public String putOfficeAgent(List<Participant> result) {
        List<Participant> list = new ArrayList<>();
        if (result != null && result.size() > 0) {
            for (Participant part : result) {
                boolean flag = false;
                QueryWrapper<CrOfficeAgents> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(CrOfficeAgents::getOperationId, part.participantValue);
                queryWrapper.lambda().eq(CrOfficeAgents::getAgentStatus, ContractEnum.EnumOfficeAgents.ING.getCode());
                CrOfficeAgents agents = crOfficeAgentsMapper.selectOne(queryWrapper);
                SysUserinfo sysUserinfo = null;

                if (agents != null) {
                    flag = DateUtil.isExpired(agents.getStartTime(), agents.getEndTime());
                    if (flag) {
                        //待办委托方法
                        sysUserinfo = userInfoRequest.queryById(agents.getAgentId());
                        part.participantValue = sysUserinfo.getfId().toString();
                        part.participantCode = sysUserinfo.getfCode();
                        list.add(part);
                    } else {
                        makeService.updateAgentStatusByAgentId(agents.getOperationId(), Integer.parseInt(ContractEnum.EnumOfficeAgents.OVER.getCode()));
                    }
                }
            }
        }
        return JSON.toJSONString(list);
    }


    /**
     * 分发参与者/当前活动参与者
     */
    @Override
    public List<AppParticipantData> taskparticipant(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        return dpsRequest.taskparticipant(taskId);
    }

    /**
     * 抄送
     */
    public AppCallResult cc(String taskId, String contractId, String opinion, String categoryCode) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        context.setCoUserId(userInfo.getSysUser().getfId().toString());//协同/代理审查人Id
        context.setCoUserName(userInfo.getSysUser().getfCname());//协同/代理审查人名称
        context.setTaskId(taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        context.setOpinion(opinion);//审批意见

        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        return dpsRequest.cc(context);
    }

    /**
     * 抄送完成
     */
    @Override
    public AppCallResult cccomplete(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        return dpsRequest.cccomplete(taskId);
    }

    /**
     * 流程变更
     */
    @Override
    public AppCallResult flowchange(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        return dpsRequest.flowchange(taskId);
    }

    /**
     * 跳过
     *
     * @param taskId
     * @param contractId
     * @param opinion
     * @param categoryCode
     */
    @Override
    public AppCallResult skip(String taskId, String contractId, String opinion, String categoryCode) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        ExecuteContext context = new ExecuteContext();
        context.setAppId(cmisDefaultConfig.getAppId());//所属应用Id
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        context.setExecutorId(userInfo.getSysUser().getfId().toString());//执行人ID
        context.setExecutorName(userInfo.getSysUser().getfCname());//执行人名称
        context.setTaskId(taskId);//执行的待办Id
        context.setExecuteDate(new Date());//执行时间
        List<AppMetasData> metas = dpsRequest.metas(categoryCode);//流程类型
        metas = setAppMetasData(metas, crContractbasic, crContractinfo, 0, null, "");
        context.setMetasList(metas);
        return dpsRequest.skip(context);
    }

    /**
     * 流程分发员
     */
    @Override
    public DataResult<?> flowChangeUser(String categoryCode, String businessId) {
        Integer deptId = 0;
        //订立
        if (ContractEnum.EnumWorkFlow.Make.getCode().equals(categoryCode)) {
            CrContractbasic crContractbasic = crContractbasicMapper.selectById(businessId);
            if (crContractbasic != null) {
                deptId = crContractbasic.getMainDeptID();//主办部门
            }
        } else if (ContractEnum.EnumWorkFlow.Change.getCode().equals(categoryCode)) {
            CrContractchange crContractchange = crContractchangeMapper.selectById(businessId);
            if (crContractchange != null) {
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
                if (crContractbasic != null) {
                    deptId = crContractbasic.getMainDeptID();//主办部门
                }
            }

        } else if (ContractEnum.EnumWorkFlow.Trasfer.getCode().equals(categoryCode)) {
            CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(businessId);
            if (crContracttransfer != null) {
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContracttransfer.getContractID());
                if (crContractbasic != null) {
                    deptId = crContractbasic.getMainDeptID();//主办部门
                }
            }

        } else if (ContractEnum.EnumWorkFlow.Terminate.getCode().equals(categoryCode) || ContractEnum.EnumWorkFlow.Finally.getCode().equals(categoryCode)) {
            CrContractend crContractend = crContractendMapper.selectById(businessId);
            if (crContractend != null) {
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
                if (crContractbasic != null) {
                    deptId = crContractbasic.getMainDeptID();//主办部门
                }
            }
        }
        String userId = "";
        String userName = "";
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
        if (sysOrganization != null) {
            QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, deptId);
            List<AmUnitconfiguration> amUnitconfigurationList = amUnitconfigurationMapper.selectList(queryWrapper);
            if (amUnitconfigurationList != null && amUnitconfigurationList.size() > 0) {
                userId = amUnitconfigurationList.get(0).getFlowDistributer();
            }

        }
        if (!StringUtils.isEmpty(userId)) {
            SysUserinfo userInfo = userInfoRequest.queryById(Integer.parseInt(userId));
            if (userInfo != null) {
                userName = userInfo.getfCname();
            }
        }
        JSONObject obj = new JSONObject(true);
        obj.put("userId", userId);
        obj.put("userName", userName);
        return DataResult.success(obj);
    }

    /**
     * 根据部门获取流程分发员
     */
    @Override
    public DataResult<?> flowChangeUser(Integer deptId) {
        String userId = "";
        String userName = "";
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
        if (sysOrganization != null) {
            QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, deptId);
            List<AmUnitconfiguration> amUnitconfigurationList = amUnitconfigurationMapper.selectList(queryWrapper);
            if (amUnitconfigurationList != null && amUnitconfigurationList.size() > 0) {
                userId = amUnitconfigurationList.get(0).getFlowDistributer();
            }

        }
        if (!StringUtils.isEmpty(userId)) {
            SysUserinfo userInfo = userInfoRequest.queryById(Integer.parseInt(userId));
            if (userInfo != null) {
                userName = userInfo.getfCname();
            }
        }
        JSONObject obj = new JSONObject(true);
        obj.put("userId", userId);
        obj.put("userName", userName);
        return DataResult.success(obj);
    }

    /**
     * 任务完成
     */
    @Override
    public DataResult<?> taskComplete(String taskId) {
        AppCallResult result = dpsRequest.taskMessageComplete(taskId);
        return DataResult.success(result);
    }

    /**
     * 根据合同ID,返回当前办理人
     */
    @Override
    public DataResult<?> getContractByBusinessId(String businessId) {
        JSONObject jsonObject = new JSONObject();
        String contractId = "";
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(businessId);
        if (crContractbasic != null) {
            contractId = crContractbasic.getContractID();
        }
        //变更
        CrContractchange crContractchange = crContractchangeMapper.selectById(businessId);
        if (crContractchange != null) {
            contractId = crContractchange.getContractID();
        }
        //转让
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(businessId);
        if (crContracttransfer != null) {
            contractId = crContracttransfer.getContractID();
        }
        //终止
        CrContractend crContractend = crContractendMapper.selectById(businessId);
        if (crContractend != null) {
            contractId = crContractend.getContractID();
        }
        PagedList pagedList = dpsRequest.todotaskByBusinessId(businessId);
        List<ExecuteTaskData> executeTaskDataList = pagedList.getExecuteTaskList();
        String executorId = "";
        String executorName = "";
        String agentId = "";
        String agentName = "";
        if (executeTaskDataList == null || executeTaskDataList.size() <= 0) {
            //查找变更合同
            QueryWrapper<CrContractchange> contractchangeQueryWrapper = new QueryWrapper<>();
            contractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
            contractchangeQueryWrapper.lambda().orderByDesc(CrContractchange::getCteatedDate);
            List<CrContractchange> crContractchangeList = crContractchangeMapper.selectList(contractchangeQueryWrapper);
            if (crContractchangeList != null && crContractchangeList.size() > 0) {
                pagedList = dpsRequest.todotaskByBusinessId(crContractchangeList.get(0).getContractChangeID());
                executeTaskDataList = pagedList.getExecuteTaskList();
                if (executeTaskDataList == null || executeTaskDataList.size() <= 0) {
                    //查找转让
                    QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
                    crContracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
                    crContracttransferQueryWrapper.lambda().orderByDesc(CrContracttransfer::getCreatedDate);
                    List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(crContracttransferQueryWrapper);
                    if (crContracttransferList != null && crContracttransferList.size() > 0) {
                        pagedList = dpsRequest.todotaskByBusinessId(crContracttransferList.get(0).getTransferId());
                        executeTaskDataList = pagedList.getExecuteTaskList();
                    }
                    if (executeTaskDataList == null || executeTaskDataList.size() <= 0) {
                        //查找终止
                        QueryWrapper<CrContractend> crContractendQueryWrapper = new QueryWrapper<>();
                        crContractendQueryWrapper.lambda().eq(CrContractend::getContractID, contractId);
                        crContractendQueryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
                        List<CrContractend> crContractendList = crContractendMapper.selectList(crContractendQueryWrapper);
                        if (crContractendList != null && crContractendList.size() > 0) {
                            pagedList = dpsRequest.todotaskByBusinessId(crContractendList.get(0).getContractEndID());
                            executeTaskDataList = pagedList.getExecuteTaskList();
                        }
                    }
                }
            }

        }
        if (executeTaskDataList != null && executeTaskDataList.size() > 0) {
            for (ExecuteTaskData executeTaskData : executeTaskDataList) {
                executorId += executeTaskData.getExecutorId() + ";";
                executorName += executeTaskData.getExecutorName() + ";";
                agentId = executeTaskData.getAgentId();
                agentName = executeTaskData.getAgentName();
            }
        }

        jsonObject.put("contractId", contractId);//合同id
        if (!StringUtils.isEmpty(executorId)) {
            executorId = executorId.substring(0, executorId.length() - 1);
        }
        if (!StringUtils.isEmpty(executorName)) {
            executorName = executorName.substring(0, executorName.length() - 1);
        }
        if (StringUtils.isEmpty(executorId)) {
            //不在审批流/代办丢失，不展示
            crContractbasic = crContractbasicMapper.selectById(contractId);
            if (crContractbasic != null) {
                executorId = crContractbasic.getMainOrgUserID();
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(executorId));
                if (sysUserinfo != null) {
                    executorName = sysUserinfo.getfCname();
                }
            }
        }
        if (!StringUtils.isEmpty(agentId) && !StringUtils.isEmpty(agentName)) {
            jsonObject.put("currentUserId", agentId);//当前办理人Id
            jsonObject.put("currentUserName", agentName + "(授权人:" + executorName + ")");//当前办理人
        } else {
            jsonObject.put("currentUserId", executorId);//当前办理人Id
            jsonObject.put("currentUserName", executorName);//当前办理人
        }
        return DataResult.success(jsonObject);

    }
    /**
     * 	办公代理转交
     */
//    public boolean createChangeAgents(String businessId,String taskId) {
//    	List<Executor> nextList = getNextAgentByBusinessId(taskId,businessId) ;
//    	boolean flag = false ;
//    	for(Executor exe : nextList ) {
//	    	QueryWrapper<CrOfficeAgents> queryWrapper = new QueryWrapper<>();
//	    	queryWrapper.lambda().eq(CrOfficeAgents :: getOperationId,exe.userId);
//	    	queryWrapper.lambda().eq(CrOfficeAgents :: getAgentStatus,ContractEnum.EnumOfficeAgents.ING.getCode());
//	    	List<CrOfficeAgents> agentsList = crOfficeAgentsMapper.selectList(queryWrapper);
//	    	//SysUserinfo sysUserinfo = null ;
//	    	List<String> list = new ArrayList<>();
//	    	if(agentsList!=null && agentsList.size() >0) {
//	    		for(CrOfficeAgents agents  : agentsList) {
//		        //sysUserinfo = userInfoRequest.queryById(agents.getAgentId());
//		    		flag = DateUtil.isExpired(agents.getStartTime(), agents.getEndTime());
//		    		if(flag) {
//			    		//待办委托方法
//			    		//dpsRequest.transferByTask(taskList, transferId, transferName)(agents.getOperationId().toString(),agents.getAgentId().toString() );
//		    		}else {
//			    		makeService.updateAgentStatusByAgentId(agents.getOperationId(),Integer.parseInt(ContractEnum.EnumOfficeAgents.OVER.getCode()));
//			    	}
//		    	}
//	    	}
//	    	//list.add(taskId);
//
//    	}
//    	return flag ;
//    }

    /**
     * 插入消息表
     */
    @Override
    public boolean addMessage(WfMessage wfMessage, AppExtendsData appExtendsData, DpsCallbackVo dpsCallbackVo) {

        LocalDateTime date = LocalDateTime.now();
        wfMessage.setMessageId(UUID.randomUUID().toString());
        wfMessage.setBusinessId(appExtendsData.getBusinessId());//业务数据ID
        wfMessage.setBusinessName(appExtendsData.getExt002());//业务名称
        if (dpsCallbackVo.getTaskIdList() != null && dpsCallbackVo.getTaskIdList().size() > 0) {

        }
        if (dpsCallbackVo.getExecutorIdList() != null && dpsCallbackVo.getExecutorIdList().size() > 0) {

        }
        /*  wfMessage.setTaskId();*/
        wfMessage.setFromTaskId(dpsCallbackVo.getTaskId());//当前代办ID
        /*wfMessage.setFromTaskId(dpsCallbackVo.gett);/*/
        wfMessage.setActivityId(dpsCallbackVo.getActivityId());//待办所在的活动Id
        /*wfMessage.setInstanceId(dpsCallbackVo.geti());//待办所在的实例Id*/
        /*   wfMessage.setWorkflowId(dpsCallbackVo.set);*///对应的流程模板Id
        /* wfMessage.versions*///对应的流程模板的版本号
        wfMessage.setCategoryCode(dpsCallbackVo.getCategoryCode());//流程分类编码
        wfMessage.setActivityName(dpsCallbackVo.getActivityName());//活动名称
        /*wfMessage.setActivityType(dpsCallbackVo.());*/
/*        活动类型Id 1 WorkflowUser 用户待办  2WorkflowRole 角色用户待办 3Coordinate系统审查待办
        4Selective 分发待办 7Sponsor 经办人待办 8Proxy代理人待办 9WorkflowPosition 岗位用户待办 10 CC抄送待办*/
        wfMessage.setExecuteType(0);//执行方式  0Sequence顺序 1Paraller 并行 2Vote选举
        wfMessage.setDegree(0);//审批度（执行方式为选举时）
        wfMessage.setTaskType(0);//待办类型 0Sequence顺序 1Paraller 并行 2Vote选举
       /* wfMessage.setSendUserId(dpsCallbackVo. ());//发送人
        wfMessage.setSendDate();//发送时间*/
        wfMessage.setExecutorId(dpsCallbackVo.getExecutorId());//执行人
        /*wfMessage.setExecutorName(dpsCallbackVo.ex);//执行人中文名*/
        wfMessage.setAgentId(dpsCallbackVo.getActivityId());//代理人
        wfMessage.setExecutorName("");//代理人中文名
        wfMessage.setHandlerId("");//处理人
        wfMessage.setHandlerName("");//处理人中文名
        wfMessage.setExt001(appExtendsData.getExt001());
        wfMessage.setExt002(appExtendsData.getExt002());
        wfMessage.setExt003(appExtendsData.getExt003());
        wfMessage.setExt004(appExtendsData.getExt004());
        wfMessage.setExt005(appExtendsData.getExt005());
        wfMessage.setExt006(appExtendsData.getExt006());
        wfMessage.setExt007(appExtendsData.getExt007());
        wfMessage.setExt008(appExtendsData.getExt008());
        wfMessage.setExt009(appExtendsData.getExt009());
        if (!StringUtils.isEmpty(appExtendsData.getExt010())) {
            wfMessage.setExt010(new BigDecimal(appExtendsData.getExt010()));
        }
        wfMessage.setExt011(appExtendsData.getExt011());
        wfMessage.setExt012(appExtendsData.getExt012());
        wfMessage.setExt013(appExtendsData.getExt013());
        wfMessage.setExt014(appExtendsData.getExt014());
        wfMessage.setExt015(appExtendsData.getExt015());
        wfMessage.setExt016(appExtendsData.getExt016());
        wfMessage.setExt017(appExtendsData.getExt017());
        wfMessage.setExt018(appExtendsData.getExt018());
        wfMessage.setExt019(appExtendsData.getExt019());
        wfMessage.setExt020(appExtendsData.getExt020());


        wfMessage.setCreateDate(date);
        wfMessage.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));

        return wfMessageMapper.insert(wfMessage) > 0;
    }

    /*
     * 插入临时消息
     * */
    @Override
    public boolean saveTempMessage(AppExtendsData appExtendsData, String categoryCode, SysUserinfo executor) {
        WfMessage wfMessage = new WfMessage();
        LocalDateTime date = LocalDateTime.now();
        wfMessage.setMessageId(UUID.randomUUID().toString());
        wfMessage.setTaskId(UUID.randomUUID().toString());
        wfMessage.setBusinessId(appExtendsData.getBusinessId());//业务数据ID
        wfMessage.setBusinessName(appExtendsData.getExt003());//业务名称
        wfMessage.setCategoryCode(categoryCode);//流程分类编码
        wfMessage.setExecutorId(executor.getfId().toString());//执行人id
        wfMessage.setExecutorName(executor.getfCname());//执行人姓名
        /*        wfMessage.setSendUserId(appExtendsData.);//发送人*/
        wfMessage.setSendDate(date);//发送时间
        wfMessage.setExt001(appExtendsData.getExt001());//合同代办标识
        wfMessage.setExt002(appExtendsData.getExt002());//合同序号
        wfMessage.setExt003(appExtendsData.getExt003());//合同名称
        wfMessage.setExt004(appExtendsData.getExt004());//合同环节id
        wfMessage.setExt005(appExtendsData.getExt005());//主办部门ID
        wfMessage.setExt006(appExtendsData.getExt006());////主办部门名称
        wfMessage.setExt007(appExtendsData.getExt007());//是否框架合同
        wfMessage.setExt008(appExtendsData.getExt008());//经办人名称
        wfMessage.setExt009(appExtendsData.getExt009());//合同模块id
        if (!StringUtils.isEmpty(appExtendsData.getExt010())) {
            wfMessage.setExt010(new BigDecimal(appExtendsData.getExt010()));//标的金额
        }
        wfMessage.setExt011(appExtendsData.getExt011());//标的金额币种id
        wfMessage.setExt012(appExtendsData.getExt012());//标的金额币种中文
        wfMessage.setExt013(appExtendsData.getExt013());//合同类型1id
        wfMessage.setExt014(appExtendsData.getExt014());//合同类型2id
        wfMessage.setExt015(appExtendsData.getExt015());//合同类型3id
        wfMessage.setExt016(appExtendsData.getExt016());//合同类型4id
        wfMessage.setExt017(appExtendsData.getExt017());//合同类型名称
        wfMessage.setExt018(appExtendsData.getExt018());//合同环节中文名
        wfMessage.setExt019(appExtendsData.getExt019());//合同模块中文名
        wfMessage.setExt020(appExtendsData.getExt020());//合同环节id
        wfMessage.setMessageState(Integer.parseInt(ContractEnum.EnumMessageSate.UnRead.getCode()));
        wfMessage.setCreateDate(date);
        wfMessage.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));

        return wfMessageMapper.insert(wfMessage) > 0;
    }

    /**
     * 临时消息处理完毕
     */
    @Override
    public boolean completeMessage(String taskId) {
        boolean isSuccess = true;
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        QueryWrapper<WfMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WfMessage::getTaskId, taskId);
        List<WfMessage> wfMessageList = wfMessageMapper.selectList(queryWrapper);
        if (wfMessageList != null && wfMessageList.size() > 0) {
            for (WfMessage wfMessage : wfMessageList) {
                wfMessage.setMessageState(Integer.parseInt(ContractEnum.EnumMessageSate.Deal.getCode()));
                wfMessage.setHandlerId(userInfo.getSysUser().getfId().toString());
                wfMessage.setHandlerName(userInfo.getSysUser().getfCname());
                wfMessage.setModifieDdate(date);
                isSuccess = wfMessageMapper.updateById(wfMessage) > 0;
            }
        }
        return isSuccess;

    }


    /**
     * 办公代理获取下一个节点人员
     *
     * @param contractId
     * @return
     */
    public List<Executor> getNextAgentByBusinessId(String taskId, String contractId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new NotFoundException("taskId为空！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同Id为空！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关合同记录！", Constants.FAILCODE);
        }

        List<Executor> executorList = new ArrayList<>();

        ExecuteTaskData taskdetail = dpsRequest.taskdetail(taskId);
        //如果是分发显示分发参与者
        if (taskdetail.getTaskType() == TaskType.Selective) {
            List<AppParticipantData> taskparticipant = this.taskparticipant(taskId);

            if (!CollectionUtils.isEmpty(taskparticipant)) {
                Map<String, AppParticipantData> participantMap = taskparticipant.stream().collect(Collectors.toMap(AppParticipantData::getParticipantValue, Function.identity(), (k1, k2) -> k1));
                Integer[] integers = participantMap.keySet().stream().map(Integer::parseInt).toArray(Integer[]::new);
                List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(integers);
                Map<Integer, SysUserinfo> userMap = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, Function.identity(), (k1, k2) -> k1));

                for (AppParticipantData participantData : taskparticipant) {
                    Executor executor = new Executor();
                    executor.activityId = taskdetail.getActivityId();
                    executor.participantId = participantData.getParticipantId();
                    executor.instanceId = taskdetail.getInstanceId();
                    executor.userId = participantData.getParticipantValue();
                    executor.userName = participantData.getParticipantName();
                    if (!StringUtils.isEmpty(executor.userId)) {
                        UserUtil.UserUnitDeptInfo unitDeptInfo = userUtil.getOrgPath(Integer.parseInt(executor.userId));
                        if (unitDeptInfo.getDept() != null) {
                            executor.deptId = unitDeptInfo.getDept().getfId().toString();
                            executor.deptName = unitDeptInfo.getDept().getfName();
                        }

                        if (unitDeptInfo.getUnit() != null) {
                            executor.orgId = unitDeptInfo.getUnit().getfId().toString();
                            executor.orgName = unitDeptInfo.getUnit().getfName();
                        }

                        executor.organisePath = unitDeptInfo.toString();
                    }

                    executor.category = participantData.getCategory();
                    executor.positionName = userMap.get(Integer.parseInt(participantData.getParticipantValue())).getfPosition();
                    executorList.add(executor);
                }
            }
            return executorList;

        } else {
            List<ExecutorData> executorDataList = dpsRequest.nextexecutor(taskId);
            if (executorDataList != null && executorDataList.size() > 0) {
                for (ExecutorData executorData : executorDataList) {
                    Executor executor = new Executor();
                    executor.activityId = executorData.getActivityId();
                    executor.participantId = executorData.getParticipantId();
                    executor.instanceId = executorData.getInstanceId();
                    executor.activityId = executorData.getActivityId();
                    executor.userId = executorData.getUserId();
                    executor.userName = executorData.getUserName();
                    if (!StringUtils.isEmpty(executor.userId)) {
                        UserUtil.UserUnitDeptInfo unitDeptInfo = userUtil.getOrgPath(Integer.parseInt(executor.userId));
                        if (unitDeptInfo.getDept() != null) {
                            executor.deptId = unitDeptInfo.getDept().getfId().toString();
                            executor.deptName = unitDeptInfo.getDept().getfName();
                        }

                        if (unitDeptInfo.getUnit() != null) {
                            executor.orgId = unitDeptInfo.getUnit().getfId().toString();
                            executor.orgName = unitDeptInfo.getUnit().getfName();
                        }

                        executor.organisePath = unitDeptInfo.toString();
                    }

                    executor.category = executorData.getCategory();
                    executor.positionName = executorData.getPositionName();
                    if (StringUtils.isEmpty(executor.positionName)) {
                        if (!StringUtils.isEmpty(executor.userId)) {
                            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(executor.userId));
                            executor.positionName = sysUserinfo.getfPosition();
                        }
                    }
                    executor.kind = executorData.getKind();
                    executor.dataCategory = executorData.getDataCategory();
                    executorList.add(executor);
                }
            }
            return executorList;
        }

    }

    @Override
    public JSONObject businessData(String businessId) {
        return dpsRequest.businessData(businessId);
    }
}
