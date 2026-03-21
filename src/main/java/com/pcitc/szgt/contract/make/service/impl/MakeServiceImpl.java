package com.pcitc.szgt.contract.make.service.impl;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppWorkflowData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.ssc.dps.inte.workflow.StartContext;
import com.pcitc.szgt.contract.appmanager.entity.AmQuerylicense;
import com.pcitc.szgt.contract.appmanager.entity.SysOrganiseunitSinging;
import com.pcitc.szgt.contract.appmanager.mapper.AmQuerylicenseMapper;
import com.pcitc.szgt.contract.appmanager.mapper.SysOrganiseunitSingingMapper;
import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.attachment.mapper.SysAttachmentinfoMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.common.enums.FinancialEnum;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.interactive.model.ContractInfo;
import com.pcitc.szgt.contract.interactive.service.FinancialService;
import com.pcitc.szgt.contract.make.entity.CrContractaccord;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import com.pcitc.szgt.contract.make.entity.CrContractaccordsap;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.entity.CrContractofferee;
import com.pcitc.szgt.contract.make.entity.CrContractprint;
import com.pcitc.szgt.contract.make.entity.CrContractrununit;
import com.pcitc.szgt.contract.make.entity.CrContracttext;
import com.pcitc.szgt.contract.make.entity.CrMaterialsap;
import com.pcitc.szgt.contract.make.entity.CrOfficeAgents;
import com.pcitc.szgt.contract.make.entity.CrOfficeAgentsList;
import com.pcitc.szgt.contract.make.entity.CrProjectinfo;
import com.pcitc.szgt.contract.make.mapper.CrContractaccordMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractaccordoaotherMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractaccordsapMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractoffereeMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractprintMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractrununitMapper;
import com.pcitc.szgt.contract.make.mapper.CrContracttextMapper;
import com.pcitc.szgt.contract.make.mapper.CrMaterialsapMapper;
import com.pcitc.szgt.contract.make.mapper.CrOfficeAgentsMapper;
import com.pcitc.szgt.contract.make.mapper.CrProjectinfoMapper;
import com.pcitc.szgt.contract.make.mapper.WfMessageMapper;
import com.pcitc.szgt.contract.make.modelEx.BidItem;
import com.pcitc.szgt.contract.make.modelEx.ContractOfferee;
import com.pcitc.szgt.contract.make.modelEx.CrOfficeAgentsVo;
import com.pcitc.szgt.contract.make.modelEx.OffereeSaveVo;
import com.pcitc.szgt.contract.make.modelEx.PrepareContract;
import com.pcitc.szgt.contract.make.modelEx.StdTextCnt;
import com.pcitc.szgt.contract.make.modelEx.StdTextUseRateVo;
import com.pcitc.szgt.contract.make.service.IMakeService;
import com.pcitc.szgt.contract.offeree.entity.FfOffereebank;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.entity.FfOffereelinkman;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereebankMapper;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeinfoMapper;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereelinkmanMapper;
import com.pcitc.szgt.contract.offeree.model.OffereeContractVo;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.entity.CrContractend;
import com.pcitc.szgt.contract.perform.mapper.CrContractendMapper;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.DpsTaskMessage;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.DpsRequest;
import com.pcitc.szgt.contract.share.request.OfficeAgentRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.textmanage.entity.CrContracttextmodel;
import com.pcitc.szgt.contract.textmanage.mapper.CrContracttextmodelMapper;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.workflow.entityExt.TaskOpinion;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;

/**
 * <p>合同起草订立</p>
 * @author ziran.zhou
 * @since 2020-02-20
 */
@Service
@Slf4j
public class MakeServiceImpl implements IMakeService {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private CrContractbasicMapper crContractbasicMapper;
    @Autowired
    private CrContractinfoMapper crContractinfoMapper;
    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;
    @Autowired
    private CrContractaccordMapper crContractaccordMapper;
    @Autowired
    private CrContractaccordoaotherMapper crContractaccordoaotherMapper;
    @Autowired
    private CrContractrununitMapper crContractrununitMapper;
    @Autowired
    private CrOfficeAgentsMapper crOfficeAgentsMapper ;
    @Autowired
    private WfMessageMapper wfMessageMapper ;
    @Autowired
    private UserInfoRequest userRequest;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 	相对人
     */
    @Autowired
    private FfOffereeinfoMapper ffOffereeinfoMapper;
    /**
     * 	相对人联系方式
     */
    @Autowired
    private FfOffereelinkmanMapper ffOffereelinkmanMapper;
    /**
     * 	相对人开户行信息
     */
    @Autowired
    private FfOffereebankMapper ffOffereebankMapper;
    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;
    /**
     * 	物料管理
     */
    @Autowired
    private CrMaterialsapMapper crMaterialsapMapper;
    /**
     * 	合同物料
     */
    @Autowired
    private CrContractaccordsapMapper crContractaccordsapMapper;

    /**
     * 	合同文本模板
     */
    @Autowired
    private CrContracttextmodelMapper crContracttextmodelMapper;

    /**
     * 	合同打印
     */
    @Autowired
    private CrContractprintMapper crContractprintMapper;
    /**
     * 	用户接口
     */
    @Autowired
    private UserInfoRequest userInfoRequest;
    /**
     * 	组织机构接口
     */
    @Autowired
    private OrganizationRequest organizationRequest;
    /**
     * 	签约主体
     */
    @Autowired
    private SysOrganiseunitSingingMapper sysOrganiseunitSingingMapper;

    /**
     *	工作流
     */
    @Autowired
    private DpsRequest dpsRequest;
    /**
     *	办公代理
     */
    @Autowired
    private OfficeAgentRequest  agentRequest ;
    /**
     * 	数据字典
     */
    @Autowired
    private DictionaryRequest dictionaryRequest;
    /**
     * 	合同文本
     */
    @Autowired
    private CrContracttextMapper crContracttextMapper;
    /**
     * 	项目信息
     */
    @Autowired
    private CrProjectinfoMapper crProjectinfoMapper;
    @Autowired
    private SysAttachmentinfoMapper sysAttachmentinfoMapper;
    /**
     * 	工作流
     */
    @Autowired
    private IWorkFlowService workFlowService;
    /**
     * 	相对人信息
     */
    @Autowired
    private FfOffereeinfoMapper offereeinfoMapper;

    @Autowired
    private FinancialService financialService;
    @Value("${server.port}")
    private String currentPort;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CrContractendMapper crContractendMapper;
    @Autowired
    private AmQuerylicenseMapper amQuerylicenseMapper;

    @Override
    /**
     * 	根据当前用户获取主办部门
     */
    public DataResult<?> getMainDept() {
        List<Object> list = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();
        if (sysOrgList != null && sysOrgList.size() > 0) {
            for (SysOrganization sysOrganization : sysOrgList) {
                SysOrganization organization = organizationRequest.queryOrganization(sysOrganization.getfId());
                if (organization != null) {
                    JSONObject obj = new JSONObject(true);
                    obj.put("orgId", organization.getfId());
                    obj.put("orgName", organization.getfName());
                    list.add(obj);
                }
            }
        }
        return DataResult.success(list);
    }

    /**
     * 	获取签约主体
     */
    @Override
    public DataResult<?> getMySignDept(Integer deptId) {
        List<Object> list = new ArrayList<>();
        // SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
        SysOrganization sysOrganization = organizationRequest.getEnterprise(deptId);  //只有企业配置签约主体
        int ouid = deptId;
        if (sysOrganization != null) {
            ouid = sysOrganization.getfId();
        }
        QueryWrapper<SysOrganiseunitSinging> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysOrganiseunitSinging::getOuid, ouid);
        List<SysOrganiseunitSinging> sysOrganiseunitSingingList = sysOrganiseunitSingingMapper.selectList(queryWrapper);
        for (SysOrganiseunitSinging sysOrganiseunitSinging : sysOrganiseunitSingingList) {
            JSONObject obj = new JSONObject(true);
            obj.put("singId", sysOrganiseunitSinging.getSingID());
            obj.put("singName", sysOrganiseunitSinging.getSingingName());
            list.add(obj);
        }

        return DataResult.success(list);
    }

    /**
     * 	合同保存
     */
    @Override
    @Transactional
    public boolean contractSave(PrepareContract prepareContract, boolean submit) {
        if (submit) {
            if (prepareContract.contractId == null || StringUtils.isEmpty(prepareContract.contractId)) {
                throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
            }
            if (prepareContract.contractName == null || StringUtils.isEmpty(prepareContract.contractName)) {
                throw new NotFoundException("合同名称必填！", Constants.FAILCODE);
            }
            if (prepareContract.mainDept == null || prepareContract.mainDept <= 0) {
                throw new NotFoundException("合同主办部门必填！", Constants.FAILCODE);
            }
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrContractbasic crContractBasic;
        CrContractinfo crContractInfo = new CrContractinfo();
        List<CrContractaccord> crContractAccordList = new ArrayList<>();//签约依据
        List<CrContractrununit> contractRunUnits = new ArrayList<>();//收付款执行人
        Calendar calendar = Calendar.getInstance();
        LocalDateTime localDateTime = LocalDateTime.now();
        boolean isAdd = false;
        crContractBasic = crContractbasicMapper.selectById(prepareContract.contractId);
        //合同序号 年+8位流水号，如1900000001
        if (crContractBasic == null) {
            isAdd = true;
            crContractBasic = new CrContractbasic();
            crContractBasic.setContractID(prepareContract.contractId);//合同主键
            String ruleSerialNum = "";
            int year = calendar.get(Calendar.YEAR);
            String createDate = year + "-01-01 00:00:00";
            HashMap<String, Long> mapList = crContractbasicMapper.getMaxRuleSerialNum(createDate);//取当年最大的流水号
            if (mapList == null || mapList.size() == 0) {
                ruleSerialNum = Integer.toString(year).substring(2, 4) + "00000001";
                crContractBasic.setSerialNum(1L);
            } else {
                Long serialNum = mapList.get("serialNum");
                serialNum++;
                DecimalFormat df = new DecimalFormat("00000000");
                String numNew = df.format(serialNum);
                ruleSerialNum = Integer.toString(year).substring(2, 4) + numNew;
                crContractBasic.setSerialNum(serialNum);
            }
            QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
            //queryWrapper.lambda().eq(CrContractbasic::getRuleSerialNum, ruleSerialNum);
//            int count = crContractbasicMapper.selectCount(queryWrapper);
//            count++;
//            Long serialNum = (long) count;
//            crContractBasic.setSerialNum(serialNum);
            crContractBasic.setRuleSerialNum(ruleSerialNum);

            crContractBasic.setCreatedBy(userInfo.getSysUser().getfId().toString());//创建人
            crContractBasic.setCreatedDate(localDateTime);//创建时间
            crContractBasic.setStatus(1);
            crContractBasic.setLogicDel(0);
            crContractBasic.setOulabel(userInfo.getUnitId());

            crContractInfo.setCreatedBy(userInfo.getSysUser().getfId().toString());//创建人
            crContractInfo.setCreatedDate(localDateTime);//创建时间
            crContractInfo.setLogicDel(0);
            crContractInfo.setOulabel(userInfo.getUnitId());
            crContractBasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));
            crContractBasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Prepare.getCode()));//合同准备
            crContractBasic.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
            crContractBasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//处理中
        } else {
            crContractBasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractBasic.setModifiedDate(localDateTime);
            crContractInfo = crContractinfoMapper.selectById(prepareContract.contractId);
            if (crContractInfo == null) {
                throw new NotFoundException("cr_contractinfo表缺少数据，请联系管理员！", Constants.FAILCODE);
            }
            crContractInfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractInfo.setModifiedDate(localDateTime);
        }
        crContractBasic.setContractName(prepareContract.contractName);//合同名称
        crContractBasic.setMoneyFlow(prepareContract.moneyFlow);//资金流向
        if(StringUtils.isEmpty(prepareContract.type1)) {
        	throw new NotFoundException("合同分类一级不能为空！", Constants.NOTCODE);
        }
        crContractBasic.setType1(prepareContract.type1);//合同分类一级
        crContractBasic.setTypeName1("");//合同分类一级名称空
        if(StringUtils.isEmpty(prepareContract.type2)) {
        	throw new NotFoundException("合同分类二级不能为空！", Constants.NOTCODE);
        }
        crContractBasic.setType2(prepareContract.type2);//合同分类二级
        crContractBasic.setTypeName2("");//合同分类二级名称空
        if(StringUtils.isEmpty(prepareContract.type3)) {
        	throw new NotFoundException("合同分类三级不能为空！", Constants.NOTCODE);
        }
        crContractBasic.setType3(prepareContract.type3);//合同分类三级
        crContractBasic.setTypeName3("");//合同分类三级名称空
        
        crContractBasic.setType4(prepareContract.type4);//合同分类四级
        crContractBasic.setTypeName4("");//合同分类四级名称空
        
        crContractBasic.setMainOrgUserID(userInfo.getSysUser().getfId().toString());//主办人
        crContractBasic.setMainDeptID(prepareContract.mainDept);//主办部门ID
        crContractBasic.setMainDeptName(prepareContract.mainDeptName);//主办部门名称
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(prepareContract.mainDept);//获取部门所在单位/企业
        if (sysOrganization != null) {
            crContractBasic.setMainOrgID(sysOrganization.getfId());//主办单位ID
            crContractBasic.setMainOrgName(sysOrganization.getfName());//主办单位名称
        } else {
            crContractBasic.setMainOrgID(0);//主办单位ID
            crContractBasic.setMainOrgName("");//主办单位名称
        }

        crContractBasic.setMainOrgNamePath("");//主办单位/主办部门 路径
        crContractBasic.setIsMakeSureMoney(prepareContract.isMakeSure);//合同金额是否确认
        crContractBasic.setContractObjectMoney(prepareContract.contractObjectMoney);//合同金额
        crContractBasic.setContractObjectCurrency(prepareContract.currentcy);//合同金额币种
        if (prepareContract.contractObjectRate == null) {
            crContractBasic.setContractObjectRate(new BigDecimal(1));//合同金额汇率
        } else {
            crContractBasic.setContractObjectRate(prepareContract.contractObjectRate);//合同金额汇率
        }

        crContractBasic.setContractObjectAmount(prepareContract.contractobjectamount);//合同人民币金额（含税合同金额）
        crContractBasic.setTaxAmount(prepareContract.contractTaxAmount);//税额
        crContractBasic.setNoTaxAmount(prepareContract.contractNoTaxAmount);//不含税
        crContractBasic.setOulabel(userInfo.getUnitId());//所在企业标识
        crContractBasic.setRemarksText(prepareContract.remark);//备注
        TaskOpinion taskOpinion = workFlowService.revertOpinion(crContractBasic.getContractID());//最新退回审批意见
        boolean isBackContract = false;//是否退回合同， 退回合同临时保存/提交不再生成新的待办
        if (taskOpinion != null && !StringUtils.isEmpty(taskOpinion.taskId)) {
            isBackContract = true;
        }

//        if (prepareContract.taskId != null && !StringUtils.isEmpty(prepareContract.taskId)) {
//            if (!isBackContract) {
//                dpsRequest.taskMessageComplete(prepareContract.taskId);
//                //临时消息处理完毕
//                workFlowService.completeMessage(prepareContract.taskId);
//            }
//        }

        //当前合同所处阶段
        Integer propertyModel = crContractBasic.getPropertyModel();

        if (submit) {
            //待办完成
            crContractBasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));//合同订立发起
            crContractBasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Make.getCode()));//合同订立发起
            crContractBasic.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
            crContractBasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//处理中
            //发送待办数据
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractBasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractBasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractBasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractBasic.getContractName());
            }
/*
            appExtendsData.setBusinessId(crContractBasic.getContractID());//业务数据Id
            appExtendsData.setExt001("contract");//合同系统标识
            appExtendsData.setExt002(crContractBasic.getRuleSerialNum());//合同序号
            appExtendsData.setExt003(crContractBasic.getContractName());//合同名称
            appExtendsData.setExt004(ContractEnum.EnumModule.Make.getCode());//合同订立发起
            appExtendsData.setExt005(crContractBasic.getMainDeptID().toString());//主办部门ID
            sysOrganization = organizationRequest.queryOrganization(crContractBasic.getMainDeptID());
            if (sysOrganization != null) {
                appExtendsData.setExt006(sysOrganization.getfName());
            }
            if (crContractInfo.getIsFrameContract() != null) {
                appExtendsData.setExt007(crContractInfo.getIsFrameContract().toString());//是否框架合同
            }
            if (sysUserinfo != null) {
                appExtendsData.setExt008(sysUserinfo.getfCname());//经办人名称
                appExtendsData.setExt009(sysUserinfo.getfId().toString());//经办id
            }
            appExtendsData.setExt019(ContractEnum.EnumModule.Make.getMessage());//合同环节中文名称
            appExtendsData.setExt020(ContractEnum.EnumSection.Make.getCode());//合同订立发起
            taskMessage.setExtendsData(appExtendsData);*/

            /**
             *	 如果当前是合同准备
             */
            if(propertyModel == Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode())){
                if(!StringUtils.isEmpty(prepareContract.taskId)){
                    dpsRequest.taskMessageComplete(prepareContract.taskId);
                    //临时消息处理完毕
                    workFlowService.completeMessage(prepareContract.taskId);
                }

                AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractBasic.getContractID(), crContractBasic, crContractInfo,
                        ContractEnum.EnumModule.Make.getCode(), ContractEnum.EnumModule.Make.getMessage(), ContractEnum.EnumSection.Make.getCode());
                taskMessage.setExtendsData(appExtendsData);//设置扩展字段
                if (!isBackContract) {
                    AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                    //插入临时待办消息
                    workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
                }
            }

        } else {
            //发送待办数据
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractBasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractBasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractBasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractBasic.getContractName());
            }

           /* appExtendsData.setBusinessId(crContractBasic.getContractID());//业务数据Id
            appExtendsData.setExt001("contract");//合同系统标识
            appExtendsData.setExt002(crContractBasic.getRuleSerialNum());//合同序号
            appExtendsData.setExt003(crContractBasic.getContractName());//合同名称
            appExtendsData.setExt004(ContractEnum.EnumModule.Prepare.getCode());//合同环节
            appExtendsData.setExt005(crContractBasic.getMainDeptID().toString());//主办部门ID
            sysOrganization = organizationRequest.queryOrganization(crContractBasic.getMainDeptID());
            if (sysOrganization != null) {
                appExtendsData.setExt006(sysOrganization.getfName());
            }
            if (crContractInfo.getIsFrameContract() != null) {
                appExtendsData.setExt007(crContractInfo.getIsFrameContract().toString());//是否框架合同
            }
            if (sysUserinfo != null) {
                appExtendsData.setExt008(sysUserinfo.getfCname());//经办人名称
                appExtendsData.setExt009(sysUserinfo.getfId().toString());//经办id
            }
            appExtendsData.setExt019(ContractEnum.EnumModule.Prepare.getMessage());//合同环节中文名称
            appExtendsData.setExt020(ContractEnum.EnumSection.Prepare.getCode());//合同准备
            taskMessage.setExtendsData(appExtendsData);*/

            /**
             * 	如果当前是合同准备
             */
            if(propertyModel == Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode())){
                if(StringUtils.isEmpty(prepareContract.taskId)){
                    AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractBasic.getContractID(), crContractBasic, crContractInfo,
                            ContractEnum.EnumModule.Prepare.getCode(), ContractEnum.EnumModule.Prepare.getMessage(), ContractEnum.EnumSection.Prepare.getCode());
                    taskMessage.setExtendsData(appExtendsData);//设置扩展字段
                    if (!isBackContract) {
                        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                        //插入临时待办消息
                        workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
                    }
                }
            }
        }


        crContractInfo.setContractID(prepareContract.contractId);
        crContractInfo.setMoneySource(prepareContract.moneySource);//资金来源一级
        crContractInfo.setMoneySource2(prepareContract.moneySource2 == null ? null : prepareContract.moneySource2.toString());//资金来源二级
        crContractInfo.setSelectWay1(prepareContract.selectWay1);//选商方式一级
        crContractInfo.setSelectWay2(prepareContract.selectWay2);//选商方式二级
        crContractInfo.setIsGuarantee(prepareContract.isGuarantee);//是否有质保金
        crContractInfo.setImprest(prepareContract.isImprest);//是否有预付款
        crContractInfo.setIsFrameContract(prepareContract.isFrameContract);//是否框架合同
        crContractInfo.setPContractID(prepareContract.frameContractId);//父框架合同ID
        crContractInfo.setIsSlaveContract(prepareContract.isSlaveContract);//是否从合同
        crContractInfo.setMasterContractID(prepareContract.masterContractID);//主合同ID
        crContractInfo.setIsOnlineMaster(prepareContract.isOnlineMaster);//是否线上主合同

        crContractInfo.setIsInnerContract(prepareContract.InnerContract);//是否内部合同
        crContractInfo.setPlanMoney(prepareContract.planMoney);//计划金额
        crContractInfo.setPlanMoneyCurrency(prepareContract.planMoneyCurrency);//计划金额币种
        crContractInfo.setProjectID(prepareContract.projectId);//所属项目
        crContractInfo.setMySignBodyCode(prepareContract.mySignBodyId);//我方签约主体
        crContractInfo.setMySignBodyName(prepareContract.mySignBodyName);//我方签约主体名称
        crContractInfo.setMySignPerson(prepareContract.mySignPersonId);//我方签约人ID
        crContractInfo.setMySignPersonName(prepareContract.mySignPersonName);//我方签约人名称
        crContractInfo.setMySignPersonPhone(prepareContract.mySignPersonPhone);//我方签约人电话
        crContractInfo.setMySignPersonCard(prepareContract.mySignPersonCard);//我方签约人身份证
        crContractInfo.setMySignPersonUnit(prepareContract.mySignPersonUnit);//我方签约人单位
        crContractInfo.setMySignPersonDept(prepareContract.mySignPersonDept);//我方签约人部门
        crContractInfo.setMySignPersonPostion(prepareContract.mySignPersonPostion);//我方签约人职务
        crContractBasic.setSourceContractNum(prepareContract.sourceContractNum);//对方合同编号
        //合同相对人数据
        QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
        crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, prepareContract.contractId);
        List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
        if (crContractOffereeList != null && crContractOffereeList.size() > 0) {
            if (crContractOffereeList.size() <= 1) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
            }
            if (crContractOffereeList.size() > 1 && crContractOffereeList.size() <= 2) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
                crContractInfo.setOffereeName3(crContractOffereeList.get(1).getOffereeName());
                crContractInfo.setOffereeID3(crContractOffereeList.get(1).getOffereeID());
            }
            if (crContractOffereeList.size() > 2 && crContractOffereeList.size() <= 3) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
                crContractInfo.setOffereeName3(crContractOffereeList.get(1).getOffereeName());
                crContractInfo.setOffereeID3(crContractOffereeList.get(1).getOffereeID());
                crContractInfo.setOffereeName4(crContractOffereeList.get(2).getOffereeName());
                crContractInfo.setOffereeID4(crContractOffereeList.get(2).getOffereeID());
            }
        }
        //签约依据
        if (prepareContract.accordingIds != null && prepareContract.accordingIds.size() > 0) {
            for (String accordingId : prepareContract.accordingIds) {
                CrContractaccord crContractAccord = new CrContractaccord();
                crContractAccord.setContractID(prepareContract.contractId);
                crContractAccord.setAccordingID(accordingId);
                crContractAccord.setKind(Constants.HandWorkAccord);//手工签约依据
                crContractAccord.setLogicDel(0);
                crContractAccord.setCreatedBy(userInfo.getSysUser().getfId().toString());
                crContractAccord.setCreatedDate(localDateTime);
                crContractAccord.setOulabel(userInfo.getUnitId());
                crContractAccordList.add(crContractAccord);

            }
        }
        //框架合同，保存下发单位信息
        if (crContractInfo.getIsFrameContract() != null && crContractInfo.getIsFrameContract() == 1) {
            if (prepareContract.executeOrgIDs != null && prepareContract.executeOrgIDs.size() > 0) {
                List<Integer> orgIDs = prepareContract.executeOrgIDs;
                if (orgIDs == null || orgIDs.size() == 0) {
                    orgIDs = Arrays.asList(userInfo.getUnitId());
                }
                for (Integer orgId : orgIDs) {
                    CrContractrununit crContractRunUnit = new CrContractrununit();
                    crContractRunUnit.setRunUnitID(UUID.randomUUID().toString());
                    crContractRunUnit.setContractID(prepareContract.contractId);
                    crContractRunUnit.setIsFrameContract(1);
                    crContractRunUnit.setFrameOrg(orgId);
                    crContractRunUnit.setOulabel(crContractBasic.getOulabel());
                    crContractRunUnit.setUserID(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setOrgID(crContractBasic.getMainDeptID());
                    crContractRunUnit.setPayUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setFinalityUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setFinalityUserOrg(crContractBasic.getMainDeptID());
                    contractRunUnits.add(crContractRunUnit);
                }
            }


        } else {
            //收付款执行人
            if (prepareContract.executeOrgUserIDs != null && prepareContract.executeOrgUserIDs.size() > 0) {
                for (String executeOrgUserID : prepareContract.executeOrgUserIDs) {
                    CrContractrununit crContractRunUnit = new CrContractrununit();
                    crContractRunUnit.setRunUnitID(UUID.randomUUID().toString());
                    crContractRunUnit.setContractID(prepareContract.contractId);
                    crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setPayUserId(executeOrgUserID);
                    crContractRunUnit.setUserID(executeOrgUserID);
                    crContractRunUnit.setOrgID(crContractBasic.getMainDeptID());
                    crContractRunUnit.setFinalityUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setFinalityUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setOulabel(crContractBasic.getOulabel());
                    crContractRunUnit.setIsFrameContract(0);
                    if (crContractRunUnit.getPayUserId().equals(crContractBasic.getMainOrgUserID())) {
                        crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    }
                    contractRunUnits.add(crContractRunUnit);
                }
            }
        }
        if (isAdd) {
            crContractbasicMapper.insert(crContractBasic);//插入合同基础表
            crContractinfoMapper.insert(crContractInfo);//插入info表
        } else {
            crContractbasicMapper.updateById(crContractBasic);//更新合同基础表
            crContractinfoMapper.updateById(crContractInfo);//更新info表
        }
        //更新合同签约依据表
        if (crContractAccordList.size() > 0) {
            //删除合同签约依据
            QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
            crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractBasic.getContractID());
            crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getKind, Constants.HandWorkAccord);//手工签约依据
            crContractaccordMapper.delete(crContractaccordQueryWrapper);
            for (CrContractaccord crContractAccord : crContractAccordList) {
                //插入合同签约依据
                crContractaccordMapper.insert(crContractAccord);
                //更新签约依据
                CrContractaccordoaother crContractAccordOaOther = crContractaccordoaotherMapper.selectById(crContractAccord.getAccordingID());
                if (crContractAccordOaOther != null) {
                    if (crContractAccordOaOther.getUseCount() == 0 || crContractAccordOaOther.getUseCount() == null) {
                        crContractAccordOaOther.setUseCount(1);
                    } else {
                        crContractAccordOaOther.setUseCount(crContractAccordOaOther.getUseCount() + 1);
                    }
                    crContractaccordoaotherMapper.updateById(crContractAccordOaOther);
                }
            }
        }
        //保存下发单位/收付款执行人
        if (contractRunUnits.size() > 0) {

            QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
            crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, crContractBasic.getContractID());
            crContractrununitMapper.delete(crContractrununitQueryWrapper);
            for (CrContractrununit crContractRunUnit : contractRunUnits) {
                crContractrununitMapper.insert(crContractRunUnit);
            }
        }

        return true;
    }
    /**
     * 	合同补录功能录入
     * @param prepareContract
     * @param submit
     * return boolean 
     */
    @Transactional
    public boolean replenishContract(PrepareContract prepareContract, boolean submit) {

        if (submit) {
            if (prepareContract.contractId == null || StringUtils.isEmpty(prepareContract.contractId)) {
                throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
            }
            if (prepareContract.contractName == null || StringUtils.isEmpty(prepareContract.contractName)) {
                throw new NotFoundException("合同名称必填！", Constants.FAILCODE);
            }
            if (prepareContract.mainDept == null || prepareContract.mainDept <= 0) {
                throw new NotFoundException("合同主办部门必填！", Constants.FAILCODE);
            }
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrContractbasic crContractBasic;
        CrContractinfo crContractInfo = new CrContractinfo();
        List<CrContractaccord> crContractAccordList = new ArrayList<>();//签约依据
        List<CrContractrununit> contractRunUnits = new ArrayList<>();//收付款执行人
        Calendar calendar = Calendar.getInstance();
        LocalDateTime localDateTime = LocalDateTime.now();
        boolean isAdd = false;
        crContractBasic = crContractbasicMapper.selectById(prepareContract.contractId);
        //合同序号 年+8位流水号，如1900000001
        if (crContractBasic == null) {

            //补录时生成合同编号
            // 企业编码+年度（20）+合同类型（1，2，3）+四位流水号
            String contractNum = "";
            try{
                String orgCode = "";
                SysOrganization sysOrganization = organizationRequest.getOrgCompany(prepareContract.mainDept);
                if (sysOrganization != null) {
                    orgCode = sysOrganization.getfCode();
                }
                Calendar calendar1 = Calendar.getInstance();
                Integer year1 = calendar1.get(Calendar.YEAR);
                contractNum = orgCode + "-" + year1.toString().substring(2) + "-";

                SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(prepareContract.type1);
                if (sysDictionarycategory != null) {
                    contractNum += sysDictionarycategory.getfRemarks();
                }
                if (prepareContract.type2 != null) {
                    SysDictionarycategory sysDictionarycategory2 = dictionaryRequest.queryCategoryById(prepareContract.type2);
                    if (sysDictionarycategory2 != null && sysDictionarycategory2.getfId() != null) {
                        String code = "";
                        if (sysDictionarycategory2.getfCode().length() > 2) {
                            code = sysDictionarycategory2.getfCode().substring(sysDictionarycategory2.getfCode().length() - 2);
                        }
                        contractNum += code;
                    }
                }
                if (prepareContract.type3 != null) {
                    SysDictionarycategory sysDictionarycategory3 = dictionaryRequest.queryCategoryById(prepareContract.type3);
                    String code = "";
                    if (sysDictionarycategory3 != null && sysDictionarycategory3.getfId() != null) {
                        if (sysDictionarycategory3.getfCode().length() > 2) {
                            code = sysDictionarycategory3.getfCode().substring(sysDictionarycategory3.getfCode().length() - 2);
                        }
                        contractNum += code;
                    }


                }
                if (prepareContract.type4 != null) {
                    SysDictionarycategory sysDictionarycategory4 = dictionaryRequest.queryCategoryById(prepareContract.type4);
                    if (sysDictionarycategory4 != null && sysDictionarycategory4.getfId() != null) {
                        if (sysDictionarycategory4 != null) {
                            contractNum += sysDictionarycategory4.getfRemarks();
                        }
                    }

                }
                Integer num = crContractbasicMapper.selectSeqFunc(contractNum);
                DecimalFormat df = new DecimalFormat("0000");
                String numNew = df.format(num);
                contractNum += "-" + numNew;
            } catch (Exception ex) {

            }

            isAdd = true;
            crContractBasic = new CrContractbasic();
            crContractBasic.setContractID(prepareContract.contractId);//合同主键
            crContractBasic.setContractNum(contractNum);//合同编码
            String ruleSerialNum = "";
            int year = calendar.get(Calendar.YEAR);
            String createDate = year + "-01-01 00:00:00";
            //取当年最大的流水号
            HashMap<String, Long> mapList = crContractbasicMapper.getMaxRuleSerialNum(createDate);
            if (mapList == null || mapList.size() == 0) {
                ruleSerialNum = Integer.toString(year).substring(2, 4) + "00000001";
                crContractBasic.setSerialNum(1L);
            } else {
                Long serialNum = mapList.get("serialNum");
                serialNum++;
                DecimalFormat df = new DecimalFormat("00000000");
                String numNew = df.format(serialNum);
                ruleSerialNum = Integer.toString(year).substring(2, 4) + numNew;
                crContractBasic.setSerialNum(serialNum);
            }

            crContractBasic.setRuleSerialNum(ruleSerialNum);
            crContractBasic.setCreatedBy(userInfo.getSysUser().getfId().toString());//创建人
            crContractBasic.setCreatedDate(localDateTime);//创建时间
            crContractBasic.setStatus(1);
            crContractBasic.setLogicDel(0);
            crContractBasic.setOulabel(userInfo.getUnitId());

            crContractInfo.setCreatedBy(userInfo.getSysUser().getfId().toString());//创建人
            crContractInfo.setCreatedDate(localDateTime);//创建时间
            crContractInfo.setLogicDel(0);
            crContractInfo.setOulabel(userInfo.getUnitId());
            
            crContractBasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同订立
            crContractBasic.setSection(Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同订立
            
            crContractBasic.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
            int status = submit ? Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()) : Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()) ;
            crContractBasic.setStatus(status);
        } else {

            //补录时生成合同编号
            // 企业编码+年度（20）+合同类型（1，2，3）+四位流水号
            String contractNum = "";
            try{
                Integer orgId = 0;
                String orgCode = "";
                SysOrganization sysOrganization = organizationRequest.getOrgCompany(crContractBasic.getMainDeptID());
                if (sysOrganization != null) {
                    orgId = sysOrganization.getfId();
                    orgCode = sysOrganization.getfCode();
                }
                Calendar calendar1 = Calendar.getInstance();
                Integer year1 = calendar1.get(Calendar.YEAR);
                contractNum = orgCode + "-" + year1.toString().substring(2) + "-";

                SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractBasic.getType1());
                if (sysDictionarycategory != null) {
                    contractNum += sysDictionarycategory.getfRemarks();
                }
                if (crContractBasic.getType2() != null) {
                    SysDictionarycategory sysDictionarycategory2 = dictionaryRequest.queryCategoryById(crContractBasic.getType2());
                    if (sysDictionarycategory2 != null && sysDictionarycategory2.getfId() != null) {
                        String code = "";
                        if (sysDictionarycategory2.getfCode().length() > 2) {
                            code = sysDictionarycategory2.getfCode().substring(sysDictionarycategory2.getfCode().length() - 2);
                        }
                        contractNum += code;
                    }
                }
                if (crContractBasic.getType3() != null) {
                    SysDictionarycategory sysDictionarycategory3 = dictionaryRequest.queryCategoryById(crContractBasic.getType3());
                    String code = "";
                    if (sysDictionarycategory3 != null && sysDictionarycategory3.getfId() != null) {
                        if (sysDictionarycategory3.getfCode().length() > 2) {
                            code = sysDictionarycategory3.getfCode().substring(sysDictionarycategory3.getfCode().length() - 2);
                        }
                        contractNum += code;
                    }


                }
                if (crContractBasic.getType4() != null) {
                    SysDictionarycategory sysDictionarycategory4 = dictionaryRequest.queryCategoryById(crContractBasic.getType4());
                    if (sysDictionarycategory4 != null && sysDictionarycategory4.getfId() != null) {
                        if (sysDictionarycategory4 != null) {
                            contractNum += sysDictionarycategory4.getfRemarks();
                        }
                    }

                }
                Integer num = crContractbasicMapper.selectSeqFunc(contractNum);
                DecimalFormat df = new DecimalFormat("0000");
                String numNew = df.format(num);
                contractNum += "-" + numNew;
            } catch (Exception ex) {

            }
            crContractBasic.setContractNum(contractNum);//合同编码


            int status = submit ? Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()) : Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()) ;
        	crContractBasic.setStatus(status);//处理中
            crContractBasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractBasic.setModifiedDate(localDateTime);
            crContractInfo = crContractinfoMapper.selectById(prepareContract.contractId);
            if (crContractInfo == null) {
                throw new NotFoundException("cr_contractinfo表缺少数据，请联系管理员！", Constants.FAILCODE);
            }
            crContractInfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractInfo.setModifiedDate(localDateTime);
        }
        crContractBasic.setContractName(prepareContract.contractName);//合同名称
        crContractBasic.setMoneyFlow(prepareContract.moneyFlow);//资金流向
        //对应系统字典表中的数据
        crContractBasic.setType1(prepareContract.type1);//合同分类一级
        crContractBasic.setTypeName1("");//合同分类一级名称空
        crContractBasic.setType2(prepareContract.type2);//合同分类二级
        crContractBasic.setTypeName2("");//合同分类二级名称空
        crContractBasic.setType3(prepareContract.type3);//合同分类三级
        crContractBasic.setTypeName3("");//合同分类三级名称空
        crContractBasic.setType4(prepareContract.type4);//合同分类四级
        crContractBasic.setTypeName4("");//合同分类四级名称空
        
        crContractBasic.setMainOrgUserID(userInfo.getSysUser().getfId().toString());//主办人
        crContractBasic.setMainDeptID(prepareContract.mainDept);//主办部门ID
        crContractBasic.setMainDeptName(prepareContract.mainDeptName);//主办部门名称
        
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(prepareContract.mainDept);//获取部门所在单位/企业
        if (sysOrganization != null) {
            crContractBasic.setMainOrgID(sysOrganization.getfId());//主办单位ID
            crContractBasic.setMainOrgName(sysOrganization.getfName());//主办单位名称
        } else {
            crContractBasic.setMainOrgID(0);//主办单位ID
            crContractBasic.setMainOrgName("");//主办单位名称
        }

        crContractBasic.setMainOrgNamePath("");//主办单位/主办部门 路径
        crContractBasic.setIsMakeSureMoney(prepareContract.isMakeSure);//合同金额是否确认
        crContractBasic.setContractObjectMoney(prepareContract.contractObjectMoney);//合同金额
        crContractBasic.setContractObjectCurrency(prepareContract.currentcy);//合同金额币种
        if (prepareContract.contractObjectRate == null) {
            crContractBasic.setContractObjectRate(new BigDecimal(1));//合同金额汇率
        } else {
            crContractBasic.setContractObjectRate(prepareContract.contractObjectRate);//合同金额汇率
        }

        crContractBasic.setContractObjectAmount(prepareContract.contractobjectamount);//合同人民币金额（含税合同金额）
        crContractBasic.setTaxAmount(prepareContract.contractTaxAmount);//税额
        crContractBasic.setNoTaxAmount(prepareContract.contractNoTaxAmount);//不含税
        crContractBasic.setOulabel(userInfo.getUnitId());//所在企业标识
        crContractBasic.setRemarksText(prepareContract.remark);//备注

        TaskOpinion taskOpinion = workFlowService.revertOpinion(crContractBasic.getContractID());//最新退回审批意见
        boolean isBackContract = false;//是否退回合同， 退回合同临时保存/提交不再生成新的待办
        if (taskOpinion != null && !StringUtils.isEmpty(taskOpinion.taskId)) {
            isBackContract = true;
        }
        
        //当前合同所处阶段
		Integer propertyModel = crContractBasic.getPropertyModel();
		if (submit) {
            //待办完成
            crContractBasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));//合同订立发起
            crContractBasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Make.getCode()));//合同订立发起
            crContractBasic.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
            crContractBasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//处理中
            int bitype = (prepareContract.getReflag()!=null && prepareContract.getReflag() >= 0) ? 10 : 0 ;
            crContractBasic.setBidItemType(bitype);//列表中增加一个标识,10代表是合同补录的列表信息
            //发送待办数据
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractBasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractBasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractBasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractBasic.getContractName());
            }

            /**
             * 如果当前是合同准备
             */
            if(propertyModel == Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode())){
                if(!StringUtils.isEmpty(prepareContract.taskId)){
                    dpsRequest.taskMessageComplete(prepareContract.taskId);
                    //临时消息处理完毕
                    workFlowService.completeMessage(prepareContract.taskId);
                }

                AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractBasic.getContractID(), crContractBasic, crContractInfo,
                        ContractEnum.EnumModule.Make.getCode(), ContractEnum.EnumModule.Make.getMessage(), ContractEnum.EnumSection.Make.getCode());
                taskMessage.setExtendsData(appExtendsData);//设置扩展字段
                if (!isBackContract) {
                    AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                    //插入临时待办消息
                    workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
                }
            }

        } 
//		else {
//            //发送待办数据
//            DpsTaskMessage taskMessage = new DpsTaskMessage();
//            taskMessage.setBusinessId(crContractBasic.getContractID());
//            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
//            taskMessage.setExecutorId(crContractBasic.getMainOrgUserID());
//            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractBasic.getMainOrgUserID()));
//            if (sysUserinfo != null) {
//                taskMessage.setExecutorName(sysUserinfo.getfCname());
//                taskMessage.setExecutorCode(sysUserinfo.getfCode());
//                taskMessage.setCreatorCode(sysUserinfo.getfCode());
//                taskMessage.setBusinessName(crContractBasic.getContractName());
//            }
//            /**
//             * 如果当前是合同准备
//             */
//            if(propertyModel == Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode())){
//                if(StringUtils.isEmpty(prepareContract.taskId)){
//                    AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractBasic.getContractID(), crContractBasic, crContractInfo,
//                            ContractEnum.EnumModule.Prepare.getCode(), ContractEnum.EnumModule.Prepare.getMessage(), ContractEnum.EnumSection.Prepare.getCode());
//                    taskMessage.setExtendsData(appExtendsData);//设置扩展字段
//                    if (!isBackContract) {
//                        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
//                        //插入临时待办消息
//                        workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
//                    }
//                }
//            }
//        }

		
        crContractInfo.setContractID(prepareContract.contractId);
        crContractInfo.setMoneySource(prepareContract.moneySource);//资金来源一级
        crContractInfo.setMoneySource2(prepareContract.moneySource2 == null ? null : prepareContract.moneySource2.toString());//资金来源二级
        crContractInfo.setSelectWay1(prepareContract.selectWay1);//选商方式一级
        crContractInfo.setSelectWay2(prepareContract.selectWay2);//选商方式二级
        crContractInfo.setIsGuarantee(prepareContract.isGuarantee);//是否有质保金
        crContractInfo.setImprest(prepareContract.isImprest);//是否有预付款
        crContractInfo.setIsFrameContract(prepareContract.isFrameContract);//是否框架合同
        crContractInfo.setPContractID(prepareContract.frameContractId);//父框架合同ID
        crContractInfo.setIsSlaveContract(prepareContract.isSlaveContract);//是否从合同
        crContractInfo.setMasterContractID(prepareContract.masterContractID);//主合同ID
        crContractInfo.setIsOnlineMaster(prepareContract.isOnlineMaster);//是否线上主合同

        crContractInfo.setIsInnerContract(prepareContract.InnerContract);//是否内部合同
        crContractInfo.setPlanMoney(prepareContract.planMoney);//计划金额
        crContractInfo.setPlanMoneyCurrency(prepareContract.planMoneyCurrency);//计划金额币种
        crContractInfo.setProjectID(prepareContract.projectId);//所属项目
        crContractInfo.setMySignBodyCode(prepareContract.mySignBodyId);//我方签约主体
        crContractInfo.setMySignBodyName(prepareContract.mySignBodyName);//我方签约主体名称
        crContractInfo.setMySignPerson(prepareContract.mySignPersonId);//我方签约人ID
        crContractInfo.setMySignPersonName(prepareContract.mySignPersonName);//我方签约人名称
        crContractInfo.setMySignPersonPhone(prepareContract.mySignPersonPhone);//我方签约人电话
        crContractInfo.setMySignPersonCard(prepareContract.mySignPersonCard);//我方签约人身份证
        crContractInfo.setMySignPersonUnit(prepareContract.mySignPersonUnit);//我方签约人单位
        crContractInfo.setMySignPersonDept(prepareContract.mySignPersonDept);//我方签约人部门
        crContractInfo.setMySignPersonPostion(prepareContract.mySignPersonPostion);//我方签约人职务
        crContractBasic.setSourceContractNum(prepareContract.sourceContractNum);//对方合同编号
        //合同相对人数据
        QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
        crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, prepareContract.contractId);
        List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
        if (crContractOffereeList != null && crContractOffereeList.size() > 0) {
            if (crContractOffereeList.size() <= 1) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
            }
            if (crContractOffereeList.size() > 1 && crContractOffereeList.size() <= 2) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
                crContractInfo.setOffereeName3(crContractOffereeList.get(1).getOffereeName());
                crContractInfo.setOffereeID3(crContractOffereeList.get(1).getOffereeID());
            }
            if (crContractOffereeList.size() > 2 && crContractOffereeList.size() <= 3) {
                crContractInfo.setOffereeName2(crContractOffereeList.get(0).getOffereeName());
                crContractInfo.setOffereeID2(crContractOffereeList.get(0).getOffereeID());
                crContractInfo.setOffereeName3(crContractOffereeList.get(1).getOffereeName());
                crContractInfo.setOffereeID3(crContractOffereeList.get(1).getOffereeID());
                crContractInfo.setOffereeName4(crContractOffereeList.get(2).getOffereeName());
                crContractInfo.setOffereeID4(crContractOffereeList.get(2).getOffereeID());
            }
        }
        //签约依据
        if (prepareContract.accordingIds != null && prepareContract.accordingIds.size() > 0) {
            for (String accordingId : prepareContract.accordingIds) {
                CrContractaccord crContractAccord = new CrContractaccord();
                crContractAccord.setContractID(prepareContract.contractId);
                crContractAccord.setAccordingID(accordingId);
                crContractAccord.setKind(Constants.HandWorkAccord);//手工签约依据
                crContractAccord.setLogicDel(0);
                crContractAccord.setCreatedBy(userInfo.getSysUser().getfId().toString());
                crContractAccord.setCreatedDate(localDateTime);
                crContractAccord.setOulabel(userInfo.getUnitId());
                crContractAccordList.add(crContractAccord);

            }
        }
        //框架合同，保存下发单位信息
        if (crContractInfo.getIsFrameContract() != null && crContractInfo.getIsFrameContract() == 1) {
            if (prepareContract.executeOrgIDs != null && prepareContract.executeOrgIDs.size() > 0) {
                List<Integer> orgIDs = prepareContract.executeOrgIDs;
                if (orgIDs == null || orgIDs.size() == 0) {
                    orgIDs = Arrays.asList(userInfo.getUnitId());
                }
                for (Integer orgId : orgIDs) {
                    CrContractrununit crContractRunUnit = new CrContractrununit();
                    crContractRunUnit.setRunUnitID(UUID.randomUUID().toString());
                    crContractRunUnit.setContractID(prepareContract.contractId);
                    crContractRunUnit.setIsFrameContract(1);
                    crContractRunUnit.setFrameOrg(orgId);
                    crContractRunUnit.setOulabel(crContractBasic.getOulabel());
                    crContractRunUnit.setUserID(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setOrgID(crContractBasic.getMainDeptID());
                    crContractRunUnit.setPayUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setFinalityUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setFinalityUserOrg(crContractBasic.getMainDeptID());
                    contractRunUnits.add(crContractRunUnit);
                }
            }


        } else {
            //收付款执行人
            if (prepareContract.executeOrgUserIDs != null && prepareContract.executeOrgUserIDs.size() > 0) {
                for (String executeOrgUserID : prepareContract.executeOrgUserIDs) {
                    CrContractrununit crContractRunUnit = new CrContractrununit();
                    crContractRunUnit.setRunUnitID(UUID.randomUUID().toString());
                    crContractRunUnit.setContractID(prepareContract.contractId);
                    crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setPayUserId(executeOrgUserID);
                    crContractRunUnit.setUserID(executeOrgUserID);
                    crContractRunUnit.setOrgID(crContractBasic.getMainDeptID());
                    crContractRunUnit.setFinalityUserId(crContractBasic.getMainOrgUserID());
                    crContractRunUnit.setFinalityUserOrg(crContractBasic.getMainDeptID());
                    crContractRunUnit.setOulabel(crContractBasic.getOulabel());
                    crContractRunUnit.setIsFrameContract(0);
                    if (crContractRunUnit.getPayUserId().equals(crContractBasic.getMainOrgUserID())) {
                        crContractRunUnit.setPayUserOrg(crContractBasic.getMainDeptID());
                    }
                    contractRunUnits.add(crContractRunUnit);
                }
            }
        }
        if (isAdd) {
        	//插入合同基础表
            crContractbasicMapper.insert(crContractBasic);
            //插入info表
            crContractinfoMapper.insert(crContractInfo);
        } else {
            crContractbasicMapper.updateById(crContractBasic);//更新合同基础表
            crContractinfoMapper.updateById(crContractInfo);//更新info表
        }
        //更新合同签约依据表
        if (crContractAccordList.size() > 0) {
            //删除合同签约依据
            QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
            crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractBasic.getContractID());
            crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getKind, Constants.HandWorkAccord);//手工签约依据
            crContractaccordMapper.delete(crContractaccordQueryWrapper);
            for (CrContractaccord crContractAccord : crContractAccordList) {
                //插入合同签约依据
                crContractaccordMapper.insert(crContractAccord);
                //更新签约依据
                CrContractaccordoaother crContractAccordOaOther = crContractaccordoaotherMapper.selectById(crContractAccord.getAccordingID());
                if (crContractAccordOaOther != null) {
                    if (crContractAccordOaOther.getUseCount() == 0 || crContractAccordOaOther.getUseCount() == null) {
                        crContractAccordOaOther.setUseCount(1);
                    } else {
                        crContractAccordOaOther.setUseCount(crContractAccordOaOther.getUseCount() + 1);
                    }
                    crContractaccordoaotherMapper.updateById(crContractAccordOaOther);
                }
            }
        }
        //保存下发单位/收付款执行人
        if (contractRunUnits.size() > 0) {

            QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
            crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, crContractBasic.getContractID());
            crContractrununitMapper.delete(crContractrununitQueryWrapper);
            for (CrContractrununit crContractRunUnit : contractRunUnits) {
                crContractrununitMapper.insert(crContractRunUnit);
            }
        }

        return true;
    
    }
    /**
     * 	合同补录功能-订立页面 保存/提交 -不走审批流程
     * return 
     */
    @Override
    @Transactional
    public boolean updateReplenishContract(String contractId, Integer needPrintCount, Integer perFormIsConfirm,
                                  	String perFormStartDate, String perFormEndDate, Integer issueSolveMode,
                                  	String settleDeadline, String taskId, String perFormNotConfirmRemark, 
                                  	boolean isSubmit, String changeRemark, Integer type) {
    	if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //合同履行是否确定 0否 1是
        crContractinfo.setPerFormIsConfirm(perFormIsConfirm);
        if (!StringUtils.isEmpty(perFormStartDate)) {//合同履行开始时间
            crContractinfo.setPerFormStartDate(LocalDateTime.parse(perFormStartDate, dateTimeFormatter));
        }
        if (!StringUtils.isEmpty(perFormEndDate)) {//合同履行结束时间
            crContractinfo.setPerFormEndDate(LocalDateTime.parse(perFormEndDate, dateTimeFormatter));
        }
        if (!StringUtils.isEmpty(settleDeadline)) {//结算期限
            crContractinfo.setSettleDeadline(LocalDateTime.parse(settleDeadline, dateTimeFormatter));
        }
        crContractinfo.setIssueSolveMode(issueSolveMode);//合同纠纷解决方式
        crContractinfo.setNeedPrintCount(needPrintCount);//合同份数
        crContractinfo.setPerFormNotConfirm(perFormNotConfirmRemark);//履行期限不确定时备注
        crContractinfo.setChangeRemark(changeRemark);//修改说明 ，合同审批退回时，填写修改说明
        if (type != null && type == 0) {
            //合同退回后，重新送审选择送至退回人时，因为调用了backrevert接口，不再触发工作流和发送临时待办
            crContractinfoMapper.updateById(crContractinfo);
            setNewTextApproved(crContractbasic.getContractID(), userInfo);
            return true;
        }

//        if (isSubmit) {
            if (crContractinfo.getSendCheckDate() != null) {
                crContractinfo.setSendCheckDate(LocalDateTime.now());//送审时间
            }

            setNewTextApproved(crContractbasic.getContractID(), userInfo);
//        }
        crContractinfoMapper.updateById(crContractinfo);
        
        if (!StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);//设置为完成
            //临时消息处理完毕
            workFlowService.completeMessage(taskId);
        }
//        if (isSubmit) {
            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Sign.getCode()));
            crContractbasicMapper.updateById(crContractbasic); //0903 合同文本审查审批
          //发送待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractbasic.getContractName());
            }
            AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumSection.Make.getMessage(), ContractEnum.EnumSection.Sign.getCode());
            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            //插入临时待办消息
            workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
//        } 
        return true;
    }  
    /**
     *	合同补录功能列表
     */
    @Override
    public DataResult<?> getReplenishContractList(String ruleserialNum, String contractName, 
    		String isFrameContract, Integer pageNum, Integer pageSize) {
    	        if (pageNum <= 0) {
    	            pageNum = cmisDefaultConfig.getPageNum();
    	        }
    	        if (pageSize <= 0) {
    	            pageSize = cmisDefaultConfig.getPageSize();
    	        }
    	        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
    	        if (ruleserialNum != null && !StringUtils.isEmpty(ruleserialNum)) {
    	            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum.trim());
    	        }
    	        if (contractName != null && !StringUtils.isEmpty(contractName)) {
    	            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractName.trim());
    	        }
    	        if (isFrameContract != null && !StringUtils.isEmpty(isFrameContract)) {
    	            crContractbasicQueryWrapper.eq("isFrameContract", isFrameContract);
    	        }
    	        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//查询有效合同
    	        crContractbasicQueryWrapper.eq("Status", Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));
//    	        crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));//合同订立
//    	        crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getSection, Integer.parseInt(ContractEnum.EnumSection.Make.getCode()));//合同订立
    	        
    	        List<Integer> orgIds = new ArrayList<>();
    	        UserInfo userInfo = currentUserUtil.currentUserInfo();
    	        //用户查询授权
    	        QueryWrapper<AmQuerylicense> amQuerylicenseQueryWrapper = new QueryWrapper<>();
    	        amQuerylicenseQueryWrapper.lambda().eq(AmQuerylicense::getUserID, userInfo.getSysUser().getfId());
    	        List<AmQuerylicense> amQuerylicenseList = amQuerylicenseMapper.selectList(amQuerylicenseQueryWrapper);
    	        if (amQuerylicenseList != null && amQuerylicenseList.size() > 0) {
    	            for (AmQuerylicense amQuerylicense : amQuerylicenseList) {
    	                int orgId = amQuerylicense.getOrgID();
    	                if (!orgIds.contains(orgId)) {
    	                    orgIds.add(orgId);
    	                }
    	                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(orgId);
    	                if (childOrgList != null && childOrgList.size() > 0) {
    	                    for (SysOrganization sysOrganization : childOrgList) {
    	                        if (!orgIds.contains(sysOrganization.getfId())) {
    	                            orgIds.add(sysOrganization.getfId());
    	                        }

    	                    }
    	                }
    	            }
    	        }
    	        if (orgIds != null && orgIds.size() > 0) {
    	            crContractbasicQueryWrapper.and(wrapper -> wrapper.in("a.MainDeptID", orgIds).or().eq("a.CreatedBy", userInfo.getSysUser().getfId().toString()));
    	        } else {
    	            crContractbasicQueryWrapper.eq("a.CreatedBy", userInfo.getSysUser().getfId());
    	        }
    	        crContractbasicQueryWrapper.orderByDesc("a.CreatedDate");
    	        List<Object> objectList = new ArrayList<>();
    	        IPage<HashMap> page = new Page<>(pageNum, pageSize);
    	        /* userInfoRequest.queryAll();*/
    	        List<HashMap> list = crContractbasicMapper.queryContract(page, crContractbasicQueryWrapper);
    	        Integer[] mainDeptIDS = list.stream().map(i -> (Integer) i.get("MainDeptID")).toArray(Integer[]::new);
    	        List<SysOrganization> sysOrganizations = organizationRequest.queryOrgByIdBatch(mainDeptIDS);
    	        Map<Integer, SysOrganization> mainDeptMap = sysOrganizations.stream().
    	                collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k1));

    	        if (list != null && list.size() > 0) {
    	            for (HashMap map : list) {
    	                JSONObject obj = new JSONObject(true);
    	                obj.put("contractID", map.get("ContractID"));//合同id
    	                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
    	                obj.put("contractName", map.get("ContractName"));//合同名称
    	                obj.put("contractNum", map.get("ContractNum"));//合同编码
    	                obj.put("type1", map.get("Type1"));//合同类型1
    	                String type1Name = "";
    	                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type1").toString()));
    	                    if (sysDictionary != null) {
    	                        type1Name = sysDictionary.getfCnName();
    	                    }
    	                }

    	                obj.put("type1Name", type1Name);//合同类型1名称

    	                obj.put("type2", map.get("Type2"));//合同类型2
    	                String type2Name = "";
    	                if (map.get("Type2") != null && !StringUtils.isEmpty(map.get("Type2"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type2").toString()));
    	                    if (sysDictionary != null) {
    	                        type2Name = sysDictionary.getfCnName();
    	                    }
    	                }

    	                obj.put("type2Name", type2Name);//合同类型2名称


    	                obj.put("type3", map.get("Type3"));//合同类型3
    	                String type3Name = "";
    	                if (map.get("Type3") != null && !StringUtils.isEmpty(map.get("Type3"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type3").toString()));
    	                    if (sysDictionary != null) {
    	                        type3Name = sysDictionary.getfCnName();
    	                    }
    	                }

    	                obj.put("type3Name", type3Name);//合同类型3名称


    	                obj.put("type4", map.get("Type4"));//合同类型4
    	                String type4Name = "";
    	                if (map.get("Type4") != null && !StringUtils.isEmpty(map.get("Type4"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type4").toString()));
    	                    if (sysDictionary != null) {
    	                        type4Name = sysDictionary.getfCnName();
    	                    }
    	                }
    	                obj.put("type4Name", type4Name);//合同类型4名称
    	                obj.put("projectId", map.get("ProjectID"));//项目id
    	                String projectName = "";
    	                if (map.get("ProjectID") != null && !StringUtils.isEmpty(map.get("ProjectID"))) {
    	                    CrProjectinfo crProjectinfo = crProjectinfoMapper.selectById(map.get("ProjectID").toString());
    	                    if (crProjectinfo != null) {
    	                        projectName = crProjectinfo.getProjectName();
    	                    }
    	                }
    	                obj.put("projectName", projectName);//项目名称

    	                //合同相对人信息
    	                String offereeId = "";
    	                String offereeName = "";
    	                QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
    	                contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, map.get("ContractID"));
    	                List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
    	                if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
    	                    for (CrContractofferee crContractofferee : crContractoffereeList) {
    	                        FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(crContractofferee.getOffereeID());
    	                        if (ffOffereeinfo != null) {
    	                            offereeId += ffOffereeinfo.getOffereeId() + ";";
    	                            offereeName += ffOffereeinfo.getOffereeName() + ";";
    	                        }
    	                    }
    	                }
    	                if (!StringUtils.isEmpty(offereeId)) {
    	                    offereeId = offereeId.substring(0, offereeId.length() - 1);
    	                }
    	                if (!StringUtils.isEmpty(offereeName)) {
    	                    offereeName = offereeName.substring(0, offereeName.length() - 1);
    	                }
    	                obj.put("offereeId", offereeId);//相对人id
    	                obj.put("offereeName", offereeName);//相对人名称

    	                obj.put("moneyFlow", map.get("MoneyFlow"));//资金流向id
    	                String moneyFlowName = "";
    	                if (map.get("MoneyFlow") != null && !StringUtils.isEmpty(map.get("MoneyFlow"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
    	                    if (sysDictionary != null) {
    	                        moneyFlowName = sysDictionary.getfCnName();
    	                    }
    	                }
    	                obj.put("moneyFlowName", moneyFlowName);//资金流向名称
    	                obj.put("planMoney", map.get("PlanMoney"));//计划金额
    	                obj.put("planMoneyCurrency", map.get("PlanMoneyCurrency"));//计划金额币种
    	                String planMoneyCurrencyName = "";
    	                if (map.get("PlanMoneyCurrency") != null && !StringUtils.isEmpty(map.get("PlanMoneyCurrency"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("PlanMoneyCurrency").toString()));
    	                    if (sysDictionary != null) {
    	                        planMoneyCurrencyName = sysDictionary.getfCnName();
    	                    }
    	                }
    	                obj.put("planMoneyCurrencyName", planMoneyCurrencyName);//计划金额币种

    	                obj.put("contractObjectMoney", map.get("ContractObjectMoney"));//标的金额
    	                obj.put("contractObjectCurrency", map.get("ContractObjectCurrency"));//标的金额币种
    	                String contractObjectCurrencyName = "";
    	                if (map.get("ContractObjectCurrency") != null && !StringUtils.isEmpty(map.get("ContractObjectCurrency"))) {
    	                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("ContractObjectCurrency").toString()));
    	                    if (sysDictionary != null) {
    	                        contractObjectCurrencyName = sysDictionary.getfCnName();
    	                    }
    	                }
    	                obj.put("contractObjectCurrencyName", contractObjectCurrencyName);//标的金额币种中文
    	                obj.put("contractObjectRate", map.get("ContractObjectRate"));//汇率
    	                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额
    	                obj.put("contractTaxAmount", map.get("TaxAmount"));//税额
    	                obj.put("contractNoTaxAmount", map.get("NoTaxAmount"));//不含税额
    	                obj.put("isInnerContract", map.get("IsInnerContract"));//是否内部合同 0否 1是
    	                obj.put("mySignPerson", map.get("MySignPerson"));//我方签约代表id
    	                String mySignPersonName = "";
    	                if (map.get("MySignPersonName") == null || StringUtils.isEmpty(map.get("MySignPersonName"))) {
    	                    if (map.get("MySignPerson") != null && !StringUtils.isEmpty(map.get("MySignPerson").toString())) {
    	                        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MySignPerson").toString()));
    	                        if (sysUserinfo != null) {
    	                            mySignPersonName = sysUserinfo.getfCname();
    	                        }
    	                    }

    	                } else {
    	                    mySignPersonName = map.get("MySignPersonName").toString();
    	                }
    	                obj.put("mySignPersonName", mySignPersonName);//我方签约代表姓名

    	                obj.put("createdBy", map.get("MainOrgUserID"));//经办人id
    	                String createdByName = "";
    	                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
    	                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
    	                    if (sysUserinfo != null) {
    	                        createdByName = sysUserinfo.getfCname();
    	                    }
    	                }
    	                obj.put("createdByName", createdByName);//经办人名称
    	                obj.put("createdDate", map.get("CreatedDate"));//主办时间
    	                obj.put("finalityDate", map.get("FinalityDate"));//合同履行完成日期
    	                obj.put("mySignDate", map.get("MySignDate"));//签订日期
    	                obj.put("mySealDate", map.get("MySealDate"));//合同订立备案日期

    	                String propertyModel = "";//合同模块
    	                String section = "";//合同环节
    	                if (map.get("PropertyModel") != null) {
    	                    propertyModel = map.get("PropertyModel").toString();
    	                }
    	                if (map.get("Section") != null) {
    	                    section = map.get("Section").toString();
    	                }
    	                if (!StringUtils.isEmpty(propertyModel)) {
    	                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
    	                }
    	                if (!StringUtils.isEmpty(section)) {
    	                    section = ContractEnum.enumSectionMap.get(section);
    	                }
    	                obj.put("propertyModel", propertyModel);//合同模块

    	                obj.put("section", section);//合同环节

    	                obj.put("mainDeptId", map.get("MainDeptID"));

    	                Integer mainDeptID = (Integer) map.get("MainDeptID");
    	                obj.put("mainDeptName", mainDeptMap.get(mainDeptID).getfName());

    	                if (map.get("MoneySource") != null && !StringUtils.isEmpty(map.get("MoneySource"))) {
    	                    SysDictionarycategory ms1 = dictionaryRequest.queryCategoryById((Integer) map.get("MoneySource"));
    	                    obj.put("moneySource1", ms1 != null ? ms1.getfCnName() : "");
    	                } else {
    	                    obj.put("moneySource1", "");
    	                }
    	                if (map.get("MoneySource2") != null && !StringUtils.isEmpty(map.get("MoneySource2"))) {
    	                    SysDictionarycategory ms2 = dictionaryRequest.queryCategoryById(Integer.parseInt((String) map.get("MoneySource2")));
    	                    obj.put("moneySource2", ms2 != null ? ms2.getfCnName() : "");
    	                } else {
    	                    obj.put("moneySource2", "");
    	                }


    	                obj.put("mySignPerson", map.get("MySignPerson"));
    	                obj.put("mySignPersonName", map.get("MySignPersonName"));

    	                if (map.get("FinalityDate") == null || StringUtils.isEmpty(map.get("FinalityDate"))) {
    	                    obj.put("isFinality", 0);//合同是否终结
    	                } else {
    	                    obj.put("isFinality", 1);//合同是否终结
    	                }
    	                objectList.add(obj);
    	            }
    	        }
    	        PageData<Object> pageData = new PageData<>();
    	        pageData.setCurrentPage(page.getCurrent());
    	        pageData.setPageSize(page.getSize());
    	        pageData.setTotalCount(page.getTotal());
    	        pageData.setTotalPage(page.getPages());
    	        pageData.setData(objectList);
    	        return DataResult.success(pageData);
    }
    
    /**
     * 	合同相对人保存
     */
    @Override
    @Transactional
    public boolean contractOffereeSave(OffereeSaveVo offereeSaveVo) {
        String contractId = offereeSaveVo.getContractId();
        List<String> offereeIds = offereeSaveVo.getOffereeIds();

        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (offereeIds == null || offereeIds.size() <= 0) {
            throw new NotFoundException("相对人必填！", Constants.FAILCODE);
        }
        for (String offereeId : offereeIds) {
            FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(offereeId);
            if (ffOffereeinfo == null) {
                throw new NotFoundException("【" + offereeId + "】相对人不存在！", Constants.FAILCODE);
            }
            QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
            queryWrapper.lambda().eq(CrContractofferee::getOffereeID, offereeId);
            List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(queryWrapper);
            if (crContractOffereeList != null && crContractOffereeList.size() > 0) {
                throw new NotFoundException("该合同已引用【" + offereeId + "】！", Constants.FAILCODE);
            }


            LocalDateTime localDateTime = LocalDateTime.now();
            UserInfo userInfo = currentUserUtil.currentUserInfo();
            CrContractofferee crContractOfferee = new CrContractofferee();
            crContractOfferee.setContractID(contractId);
            crContractOfferee.setOffereeID(offereeId);
            crContractOfferee.setOffereeName(ffOffereeinfo.getOffereeName());
            crContractOfferee.setOffereeBelong(ffOffereeinfo.getOffereeeBelong());
            crContractOfferee.setOffereeType(ffOffereeinfo.getOffereeType());
            if (ffOffereeinfo.getCompanyType() == null) {
                crContractOfferee.setContractOffereeType(0);
            } else {
                crContractOfferee.setContractOffereeType(ffOffereeinfo.getCompanyType());
            }
            crContractOfferee.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContractOfferee.setCreatedDate(localDateTime);
            crContractOfferee.setOulabel(userInfo.getUnitId());

            //相对人联系方式

            QueryWrapper<FfOffereelinkman> ffOffereelinkmanQueryWrapper = new QueryWrapper<>();
            ffOffereelinkmanQueryWrapper.lambda().eq(FfOffereelinkman::getOffereeID, offereeId);
            List<FfOffereelinkman> ffOffereelinkmanList = ffOffereelinkmanMapper.selectList(ffOffereelinkmanQueryWrapper);
            if (ffOffereelinkmanList != null && ffOffereelinkmanList.size() > 0) {
                FfOffereelinkman ffOffereelinkman = ffOffereelinkmanList.get(0);
                crContractOfferee.setLinkID(ffOffereelinkman.getLinkID());
                crContractOfferee.setOffereeLinkMan(ffOffereelinkman.getLinkManName());//联系人
                crContractOfferee.setPosition(ffOffereelinkman.getLinkManPosition());//联系人职务
                crContractOfferee.setOffereeLinkPhone(ffOffereelinkman.getPhone());//联系电话
                crContractOfferee.setEmail(ffOffereelinkman.getEmail());//联系人邮箱
                crContractOfferee.setFax(ffOffereelinkman.getFax());//联系人传真
            }
            //开户行信息
            QueryWrapper<FfOffereebank> ffOffereebankQueryWrapper = new QueryWrapper<>();
            ffOffereebankQueryWrapper.lambda().eq(FfOffereebank::getOffereeID, offereeId);
            List<FfOffereebank> ffOffereebankList = ffOffereebankMapper.selectList(ffOffereebankQueryWrapper);
            if (ffOffereebankList != null && ffOffereebankList.size() > 0) {
                FfOffereebank ffOffereebank = ffOffereebankList.get(0);
                crContractOfferee.setBankID(ffOffereebank.getBankID());
                crContractOfferee.setBankName(ffOffereebank.getBankName());//开户银行名称
                crContractOfferee.setOpenUints(ffOffereebank.getBankUK());//开户银行单位
                crContractOfferee.setBankAccount(ffOffereebank.getBankAcount());//开户行账号
            }

            crContractoffereeMapper.insert(crContractOfferee);
        }
        return true;
    }

    /**
     * 	合同相对人修改
     */
    @Override
    public boolean contractOffereeUpdate(ContractOfferee contractOfferee) {
        if (StringUtils.isEmpty(contractOfferee.contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractOfferee.offereeId)) {
            throw new NotFoundException("相对人必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, contractOfferee.contractId);
        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getOffereeID, contractOfferee.offereeId);
        List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
        if (crContractOffereeList == null || crContractOffereeList.size() <= 0) {
            throw new NotFoundException("该合同未引用【" + contractOfferee.offereeId + "】！", Constants.FAILCODE);
        }
        CrContractofferee crContractOfferee = crContractOffereeList.get(0);
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        crContractOfferee.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractOfferee.setModifiedDate(LocalDateTime.now());
        //合同相对人联系
        crContractOfferee.setOffereeName(contractOfferee.offereeName);//相对人名称
        crContractOfferee.setOffereeLinkMan(contractOfferee.offereelinkman);//联系人
        crContractOfferee.setPosition(contractOfferee.linkmanposition);//联系人职务
        crContractOfferee.setOffereeLinkPhone(contractOfferee.phone);//联系电话
        crContractOfferee.setEmail(contractOfferee.email);//联系人邮箱
        crContractOfferee.setFax(contractOfferee.fax);//联系人传真

        //合同相对人银行
        crContractOfferee.setBankName(contractOfferee.bankname);//开户银行名称
        crContractOfferee.setOpenUints(contractOfferee.Bankuk);//开户银行单位
        crContractOfferee.setBankAccount(contractOfferee.bankacount);//开户行账号

        QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractofferee::getContractID, contractOfferee.contractId);
        queryWrapper.lambda().eq(CrContractofferee::getOffereeID, contractOfferee.offereeId);
        crContractoffereeMapper.update(crContractOfferee, queryWrapper);

        return true;
    }

    /**
     *	合同相对人明细查看
     */
    @Override
    public DataResult<?> contractOffereeInfo(String contractId, String offereeId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(offereeId)) {
            throw new NotFoundException("相对人必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getOffereeID, offereeId);
        List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
        if (crContractOffereeList == null || crContractOffereeList.size() <= 0) {
            throw new NotFoundException("该合同未引用【" + offereeId + "】！", Constants.FAILCODE);
        }
        CrContractofferee crContractOfferee = crContractOffereeList.get(0);

        ContractOfferee contractOfferee = new ContractOfferee();
        contractOfferee.contractId = crContractOfferee.getContractID();
        contractOfferee.offereeId = crContractOfferee.getOffereeID();
        contractOfferee.offereeName = crContractOfferee.getOffereeName();
        contractOfferee.offereelinkman = crContractOfferee.getOffereeLinkMan();
        contractOfferee.linkmanposition = crContractOfferee.getPosition();
        contractOfferee.phone = crContractOfferee.getOffereeLinkPhone();
        contractOfferee.email = crContractOfferee.getEmail();
        contractOfferee.fax = crContractOfferee.getFax();
        contractOfferee.bankname = crContractOfferee.getBankName();
        contractOfferee.Bankuk = crContractOfferee.getOpenUints();
        contractOfferee.bankacount = crContractOfferee.getBankAccount();
        FfOffereeinfo ffOffereeinfo = offereeinfoMapper.selectById(crContractOfferee.getOffereeID());
        if (ffOffereeinfo != null) {
            contractOfferee.offereeSort = ffOffereeinfo.getOffereeSort();
            contractOfferee.corporation = ffOffereeinfo.getCorporation();
        }

        //异常履约情况
        QueryWrapper<FfOffereeinfo> offereeinfoQueryWrapper = new QueryWrapper<>();
        offereeinfoQueryWrapper.eq("c.OffereeId", crContractOfferee.getContractID());
        offereeinfoQueryWrapper.eq("IsNormal", 0);//异常履约
        contractOfferee.ends = new ArrayList<>();
        contractOfferee.ends = offereeinfoMapper.selectUnusualPerform(offereeinfoQueryWrapper);
        if (contractOfferee.ends != null && contractOfferee.ends.size() > 0) {
            contractOfferee.endsCount = contractOfferee.ends.size();
        } else {
            List<OffereeContractVo> list = new ArrayList<>();
            list.add(new OffereeContractVo() {
            });
            contractOfferee.ends = list;
            contractOfferee.endsCount = 0;
        }
        //发案情况
        contractOfferee.cases = new ArrayList<>();
        offereeinfoQueryWrapper = new QueryWrapper<>();
        offereeinfoQueryWrapper.eq("c.OffereeId", crContractOfferee.getOffereeID());
        offereeinfoQueryWrapper.eq("b.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        contractOfferee.cases = offereeinfoMapper.selectCase(offereeinfoQueryWrapper);
        if (contractOfferee.cases != null && contractOfferee.cases.size() > 0) {
            contractOfferee.casesCount = contractOfferee.cases.size();
        } else {
            List<OffereeContractVo> list = new ArrayList<>();
            list.add(new OffereeContractVo() {
            });
            contractOfferee.cases = list;
            contractOfferee.casesCount = 0;
        }
        return DataResult.success(contractOfferee);
    }

    /*
     * 合同相对人删除
     * */
    @Override
    public boolean contractOffereeDel(String contractId, String offereeId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(offereeId)) {
            throw new NotFoundException("相对人必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
        queryWrapper.lambda().eq(CrContractofferee::getOffereeID, offereeId);
        crContractoffereeMapper.delete(queryWrapper);
        return true;
    }

    /**
     * 	根据合同ID获取合同相对人信息
     */
    @Override
    public DataResult<?> getContractOffereList(String contractId) {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
        List<CrContractofferee> crContractOffereeList = crContractoffereeMapper.selectList(queryWrapper);
        if (crContractOffereeList != null && crContractOffereeList.size() > 0) {

            for (CrContractofferee crContractOfferee : crContractOffereeList) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractId", crContractOfferee.getContractID());
                obj.put("offereeId", crContractOfferee.getOffereeID());
                obj.put("offereeName", crContractOfferee.getOffereeName());
                //合同相对人联系
                obj.put("offereelinkman", crContractOfferee.getOffereeLinkMan());  //联系人
                obj.put("linkmanposition", crContractOfferee.getPosition()); //联系人职务
                obj.put("phone", crContractOfferee.getOffereeLinkPhone()); //联系电话
                obj.put("email", crContractOfferee.getEmail());//联系人邮箱
                obj.put("fax", crContractOfferee.getFax());//联系人传真
                //合同相对人银行
                obj.put("bankname", crContractOfferee.getBankName()); //开户银行名称
                obj.put("Bankuk", crContractOfferee.getOpenUints());  //开户银行单位
                obj.put("bankacount", crContractOfferee.getBankAccount()); //开户行账号
                String offereeSort = "";
                FfOffereeinfo ffOffereeinfo = offereeinfoMapper.selectById(crContractOfferee.getOffereeID());
                if (ffOffereeinfo != null) {
                    offereeSort = ffOffereeinfo.getOffereeSort();
                    obj.put("offereeSort", ffOffereeinfo.getOffereeSort());
                }
                obj.put("offereeSort", offereeSort);//相对人分类
                String sorts = "";
                if (!StringUtils.isEmpty(offereeSort)) {
                    sorts = transferOffereeSort(offereeSort);
                }
                obj.put("offereeSortName", sorts);//相对人分类名称
                //异常履约情况
                QueryWrapper<FfOffereeinfo> offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", crContractOfferee.getContractID());
                offereeinfoQueryWrapper.eq("IsNormal", 0);//异常履约

                ContractOfferee contractOfferee = new ContractOfferee();
                contractOfferee.ends = new ArrayList<>();
                contractOfferee.ends = offereeinfoMapper.selectUnusualPerform(offereeinfoQueryWrapper);
                if (contractOfferee.ends != null && contractOfferee.ends.size() > 0) {
                    contractOfferee.endsCount = contractOfferee.ends.size();
                } else {
                    List<OffereeContractVo> ends = new ArrayList<>();
                    ends.add(new OffereeContractVo() {
                    });
                    contractOfferee.ends = ends;
                    contractOfferee.endsCount = 0;
                }
                obj.put("ends", contractOfferee.ends);//异常履约情况
                obj.put("endsCount", contractOfferee.endsCount);

                //发案情况
                contractOfferee.cases = new ArrayList<>();
                offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", crContractOfferee.getOffereeID());
                offereeinfoQueryWrapper.eq("b.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
                contractOfferee.cases = offereeinfoMapper.selectCase(offereeinfoQueryWrapper);
                if (contractOfferee.cases != null && contractOfferee.cases.size() > 0) {
                    contractOfferee.casesCount = contractOfferee.cases.size();
                } else {
                    List<OffereeContractVo> cases = new ArrayList<>();
                    cases.add(new OffereeContractVo() {
                    });
                    contractOfferee.cases = cases;
                    contractOfferee.casesCount = 0;
                }
                obj.put("cases", contractOfferee.cases);//发案情况
                obj.put("casesCount", contractOfferee.casesCount);

                list.add(obj);
            }
        }
        return DataResult.success(list);
    }

    /**
     * 	将相对人类型id转化为名称
     *
     * @param offereeSort
     * @return
     */
    private String transferOffereeSort(String offereeSort) {
        if (StringUtils.isEmpty(offereeSort)) {
            return offereeSort;
        }
        String[] sorts = offereeSort.split(",");
        List<String> sorts2 = new ArrayList<>();
        for (String sort : sorts) {
            SysDictionarycategory category = dictionaryRequest.queryCategotyByCode(sort);
            if (category != null) {
                sorts2.add(category.getfName());
            }
        }
        return String.join(",", sorts2);

    }

    /**
     *	物料查询
     */
    @Override
    public DataResult<?> queryMaterial(String groupCode, String materialCode, String materialName, Integer pageNum, Integer pageSize) {
        List<Object> list = new ArrayList<>();
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrMaterialsap> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(groupCode)) {
            queryWrapper.lambda().like(CrMaterialsap::getMaterialGroup, groupCode);
        }
        if (!StringUtils.isEmpty(materialCode)) {
            queryWrapper.lambda().like(CrMaterialsap::getStandCode, materialCode);
        }
        if (!StringUtils.isEmpty(materialName)) {
            queryWrapper.lambda().like(CrMaterialsap::getMaterialName, materialName);
        }

        Page<CrMaterialsap> page = new Page<CrMaterialsap>(pageNum, pageSize);
        IPage<CrMaterialsap> CrMaterialsaps = crMaterialsapMapper.selectPage(page, queryWrapper);
        for (CrMaterialsap crProjectinfo : CrMaterialsaps.getRecords()) {
            JSONObject obj = new JSONObject(true);
            obj.put("materialId", crProjectinfo.getId());
            obj.put("materialGroup", crProjectinfo.getMaterialGroup());
            obj.put("standCode", crProjectinfo.getStandCode());
            obj.put("newCode", crProjectinfo.getNewCode());
            obj.put("baseUnit", crProjectinfo.getBaseUnit());
            obj.put("materialDesc", crProjectinfo.getMaterialDesc());
            obj.put("materialName", crProjectinfo.getMaterialName());
            list.add(obj);
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(list);
        return DataResult.success(pageData);

    }

    /*
     * 物料转换成标的库
     * */
    @Override
    public List<BidItem> materialToBidItem(String contractId, String[] materialIds) {
        List<BidItem> bidItemList = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (materialIds == null || materialIds.length <= 0) {
            throw new NotFoundException("物料信息必填！", Constants.FAILCODE);
        }
        for (String materialId : materialIds) {
            CrMaterialsap crMaterialSAP = crMaterialsapMapper.selectById(materialId);
            if (crMaterialSAP == null) {
                throw new NotFoundException("物料不存在！", Constants.FAILCODE);
            }
            BidItem bidItem = new BidItem();
            bidItem.materialId = crMaterialSAP.getId();
            bidItem.contractID = contractId;
            bidItem.code = crMaterialSAP.getNewCode();//物料组编码
            bidItem.standCode = crMaterialSAP.getStandCode();//原编码
            bidItem.name = crMaterialSAP.getMaterialName();//物料名称
            bidItem.unit = crMaterialSAP.getBaseUnit();//计量单位
            bidItem.itemKind = Constants.HandWorkMaterial;//手工物料
            bidItemList.add(bidItem);
        }
        return bidItemList;
    }

    /*
     * 插入合同标的明细
     * */
    @Override
    @Transactional
    public boolean addContractMaterial(List<BidItem> bidItemList) {
        if (bidItemList == null || bidItemList.size() <= 0) {
            throw new NotFoundException("标的明细为空！", Constants.FAILCODE);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        BidItem bidItem = bidItemList.get(0);
        QueryWrapper<CrContractaccord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractaccord::getContractID, bidItem.contractID);
        queryWrapper.lambda().eq(CrContractaccord::getKind, Constants.HandWorkMaterial);//手工物料
        List<CrContractaccord> crContractAccordList = crContractaccordMapper.selectList(queryWrapper);
        if (crContractAccordList != null && crContractAccordList.size() > 0) {
            for (CrContractaccord crContractAccord : crContractAccordList) {
                //删除物料标的表
                QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
                crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractAccord.getContractID());
                crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getAccordingID, crContractAccord.getAccordingID());
                crContractaccordMapper.delete(crContractaccordQueryWrapper);

                //删除物料标的表
                crContractaccordsapMapper.deleteById(crContractAccord.getAccordingID());
            }
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        for (BidItem bidItem1 : bidItemList) {
            //手工物料
            CrContractaccord crContractAccord = new CrContractaccord();
            crContractAccord.setContractID(bidItem1.contractID);
            crContractAccord.setAccordingID(UUID.randomUUID().toString());
            crContractAccord.setKind(Constants.HandWorkMaterial);//手工物料
            crContractAccord.setLogicDel(0);
            crContractAccord.setOulabel(userInfo.getUnitId());
            crContractAccord.setCreatedDate(localDateTime);
            crContractAccord.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContractAccord.setStatus(1);
            crContractaccordMapper.insert(crContractAccord);

            CrContractaccordsap crContractAccordSAP = new CrContractaccordsap();
            crContractAccordSAP.setAccordingID(crContractAccord.getAccordingID());
            crContractAccordSAP.setRemark01(bidItem.materialId);//物料ID
            crContractAccordSAP.setOrderID(bidItem.orderId);
            crContractAccordSAP.setMaterialGroupCode(bidItem.code);//物料组编码
            crContractAccordSAP.setMaterialCode(bidItem.standCode);//标准编码
            crContractAccordSAP.setMaterialName(bidItem.name);//物料名称
            crContractAccordSAP.setRemark08(bidItem.remark08);//采购备注
            crContractAccordSAP.setUnits(bidItem.unit);//计量单位
            if (!StringUtils.isEmpty(bidItem.deliveryDate)) {
                crContractAccordSAP.setDeliveryDate(LocalDateTime.parse(bidItem.deliveryDate, dateTimeFormatter));//交货日期
            }
            crContractAccordSAP.setNumber(bidItem.amount);//数量
            crContractAccordSAP.setPrice(bidItem.price);//单价
            crContractAccordSAP.setRate(bidItem.rate);//税率
            crContractAccordSAP.setTotalAmount(bidItem.subTotal);//小计
            crContractAccordSAP.setCurrency(bidItem.Currency);//币种
            crContractAccordSAP.setMaterialLevel(bidItem.materialLevel);//等级
            crContractAccordSAP.setRemark03(bidItem.remark03);//质量标准
            crContractAccordSAP.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContractAccordSAP.setOulabel(userInfo.getUnitId());
            crContractAccordSAP.setCreatedDate(localDateTime);
            crContractAccordSAP.setLogicDel(0);
            crContractaccordsapMapper.insert(crContractAccordSAP);
        }

        return true;
    }

    /*
     * 合同物料标的删除
     * */
    @Override
    @Transactional
    public boolean delContractMaterial(String contractId, String Id) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(Id)) {
            throw new NotFoundException("标的明细ID必填！", Constants.FAILCODE);
        }
        //删除合同标的
        QueryWrapper<CrContractaccord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractaccord::getContractID, contractId);
        queryWrapper.lambda().eq(CrContractaccord::getAccordingID, Id);
        crContractaccordMapper.delete(queryWrapper);
        //删除合同物料
        crContractaccordsapMapper.deleteById(Id);
        return true;
    }

    /*
     *获取合同标的明细
     * */
    @Override
    public DataResult<?> getContractMaterial(String contractId, Integer pageNum, Integer pageSize) {
        List<BidItem> bidItemList = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, contractId);
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getKind, Constants.HandWorkMaterial); //手工物料
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        Page<CrContractaccord> page = new Page<CrContractaccord>(pageNum, pageSize);
        IPage<CrContractaccord> crContractAccordList = crContractaccordMapper.selectPage(page, crContractaccordQueryWrapper);
        if (crContractAccordList != null && crContractAccordList.getSize() > 0) {
            for (CrContractaccord crContractAccord : crContractAccordList.getRecords()) {
                BidItem bidItem = new BidItem();
                bidItem.Id = crContractAccord.getAccordingID();
                bidItem.contractID = crContractAccord.getContractID();//合同ID
                bidItem.itemKind = crContractAccord.getKind();
                CrContractaccordsap crContractAccordSAP = crContractaccordsapMapper.selectById(bidItem.Id);
                if (crContractAccordSAP != null) {
                    bidItem.materialId = crContractAccordSAP.getRemark01();//物料ID
                    bidItem.code = crContractAccordSAP.getMaterialGroupCode();//物料组编码
                    bidItem.standCode = crContractAccordSAP.getMaterialCode();//标准编码
                    bidItem.name = crContractAccordSAP.getMaterialName();//物料名称
                    bidItem.remark08 = crContractAccordSAP.getRemark08();//采购备注
                    bidItem.unit = crContractAccordSAP.getUnits();//计量单位
                    if (crContractAccordSAP.getDeliveryDate() != null) {
                        bidItem.deliveryDate = crContractAccordSAP.getDeliveryDate().toString();//交货日期
                    } else {
                        bidItem.deliveryDate = "";//交货日期
                    }

                    bidItem.amount = crContractAccordSAP.getNumber();//数量
                    bidItem.price = crContractAccordSAP.getPrice();//单价
                    bidItem.rate = crContractAccordSAP.getRate();//税率
                    bidItem.subTotal = crContractAccordSAP.getTotalAmount();//小计
                    bidItem.Currency = crContractAccordSAP.getCurrency();//币种
                    bidItem.CurrencyName = crContractAccordSAP.getCurrencyName();
                    if (crContractAccordSAP.getCurrency() != null && StringUtils.isEmpty(bidItem.CurrencyName)) {
                        SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractAccordSAP.getCurrency());
                        if (sysDictionarycategory != null) {
                            bidItem.CurrencyName = sysDictionarycategory.getfCnName();
                        }
                    }
                    bidItem.materialLevel = crContractAccordSAP.getMaterialLevel();//等级
                    bidItem.remark03 = crContractAccordSAP.getRemark03();//质量标准
                    bidItemList.add(bidItem);

                }
            }
        }
        PageData<BidItem> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(bidItemList);

        return DataResult.success(pageData);
    }

    /**
     * 	获取合同信息 crcontractbasic crcontractinfo
     */
    @Override
    public DataResult<?> getContractById(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        JSONObject obj = new JSONObject(true);
        obj.put("contractId", crContractbasic.getContractID());//合同id
        obj.put("contractName", crContractbasic.getContractName());//合同名称
        obj.put("contractNum", crContractbasic.getContractNum());//合同编号
        obj.put("ruleserialNum", crContractbasic.getRuleSerialNum());//合同序号
        obj.put("mainDept", crContractbasic.getMainDeptID());//主办单位id
        SysOrganization sysOrganization = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
        if (sysOrganization != null) {
            obj.put("mainDeptName", sysOrganization.getfName());//主办单位名称
        } else {
            obj.put("mainDeptName", "");//主办单位名称
        }
        obj.put("mainUserId", crContractbasic.getMainOrgUserID());//经办人id
        String mainUserName = "";
        if (!StringUtils.isEmpty(crContractbasic.getMainOrgUserID())) {
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                mainUserName = sysUserinfo.getfCname();
            }
        }
        obj.put("mainUserName", mainUserName);//经办人名称

        obj.put("moneyFlow", crContractbasic.getMoneyFlow());//资金流向id
        String moneyFlowName = "";
        if (crContractbasic.getMoneyFlow() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getMoneyFlow());
            if (sysDictionary != null) {
                moneyFlowName = sysDictionary.getfCnName();
            }
        }
        obj.put("moneyFlowName", moneyFlowName);//资金流向名称
        obj.put("moneySource", crContractinfo.getMoneySource());//资金来源一级id
        String moneySourceName = "";
        if (crContractinfo.getMoneySource() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractinfo.getMoneySource());
            if (sysDictionary != null) {
                moneySourceName = sysDictionary.getfCnName();
            }
        }
        obj.put("moneySourceName", moneySourceName);//资金来源一级名称

        obj.put("moneySource2", crContractinfo.getMoneySource2());//资金来源二级id
        String moneySource2Name = "";
        if (crContractinfo.getMoneySource2() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(crContractinfo.getMoneySource2()));
            if (sysDictionary != null) {
                moneySource2Name = sysDictionary.getfCnName();
            }
        }
        obj.put("moneySource2Name", moneySource2Name);//资金来源二级名称

        obj.put("selectWay1", crContractinfo.getSelectWay1());//选商方式1 id
        String selectWay1Name = "";
        if (crContractinfo.getSelectWay1() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractinfo.getSelectWay1());
            if (sysDictionary != null) {
                selectWay1Name = sysDictionary.getfCnName();
            }
        }
        obj.put("selectWay1Name", selectWay1Name);//选商方式1名称

        obj.put("selectWay2", crContractinfo.getSelectWay2());//选商方式2 id
        String selectWay2Name = "";
        if (crContractinfo.getSelectWay2() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractinfo.getSelectWay2());
            if (sysDictionary != null) {
                selectWay2Name = sysDictionary.getfCnName();
            }
        }
        obj.put("selectWay2Name", selectWay2Name);//选商方式2名称

        obj.put("selectWay3", crContractinfo.getSelectWay3());//选商方式3 id
        String selectWay3Name = "";
        if (crContractinfo.getSelectWay3() != null && !StringUtils.isEmpty(crContractinfo.getSelectWay3())) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(crContractinfo.getSelectWay3()));
            if (sysDictionary != null) {
                selectWay3Name = sysDictionary.getfCnName();
            }
        }
        obj.put("selectWay3Name", selectWay3Name);//选商方式3名称
        obj.put("isGuarantee", crContractinfo.getIsGuarantee());//是否质保金
        obj.put("isImprest", crContractinfo.getImprest());//是否有预付款
        obj.put("type1", crContractbasic.getType1());//合同类型1 id
        String type1Name = "";
        if (crContractbasic.getType1() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
            if (sysDictionary != null) {
                type1Name = sysDictionary.getfCnName();
            }
        }
        obj.put("type1Name", type1Name);//合同类型1名称

        obj.put("type2", crContractbasic.getType2());//合同类型2 id
        String type2Name = "";
        if (crContractbasic.getType2() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
            if (sysDictionary != null) {
                type2Name = sysDictionary.getfCnName();
            }
        }
        obj.put("type2Name", type2Name);//合同类型2名称

        obj.put("type3", crContractbasic.getType3());//合同类型3 id
        String type3Name = "";
        if (crContractbasic.getType3() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
            if (sysDictionary != null) {
                type3Name = sysDictionary.getfCnName();
            }
        }
        obj.put("type3Name", type3Name);//合同类型3名称

        obj.put("type4", crContractbasic.getType4());//合同类型4 id
        String getType4Name = "";
        if (crContractbasic.getType4() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
            if (sysDictionary != null) {
                getType4Name = sysDictionary.getfCnName();
            }
        }
        obj.put("getType4Name", getType4Name);//合同类型4名称
        obj.put("isFrameContract", crContractinfo.getIsFrameContract());//是否框架合同
        obj.put("frameContractId", crContractinfo.getPContractID());//父框架合同ID

        String frameContractName = "";
        String frameContractRuleSerialNum = "";
        if (!StringUtils.isEmpty(crContractinfo.getPContractID())) {
            CrContractbasic PCrContractbasic = crContractbasicMapper.selectById(crContractinfo.getPContractID());
            if (PCrContractbasic != null) {
                frameContractName = PCrContractbasic.getContractName();
                frameContractRuleSerialNum = PCrContractbasic.getRuleSerialNum();
            }
        }
        obj.put("frameContractName", frameContractName);//父框架合同名称
        obj.put("frameContractRuleSerialNum", frameContractRuleSerialNum);//父框架合同序号

        // 主从合同
        obj.put("isSlaveContract", crContractinfo.getIsSlaveContract());    //是否从合同
        obj.put("masterContractID", crContractinfo.getMasterContractID());  //主合同ID
        obj.put("isOnlineMaster", crContractinfo.getIsOnlineMaster());      //是否线上主合同

        String masterContractName = "";
        String masterContractRuleSerialNum = "";
        String masterContractPropertyModel = "";
        String masterContractSection = "";

        if (!StringUtils.isEmpty(crContractinfo.getMasterContractID())) {
            CrContractbasic PCrContractbasic = crContractbasicMapper.selectById(crContractinfo.getMasterContractID());
            if (PCrContractbasic != null) {
                masterContractName = PCrContractbasic.getContractName();
                masterContractRuleSerialNum = PCrContractbasic.getRuleSerialNum();
                masterContractPropertyModel = ContractEnum.enumModuleMap.get(PCrContractbasic.getPropertyModel().toString());
                masterContractSection = ContractEnum.enumSectionMap.get(PCrContractbasic.getSection().toString());
            }
        }
        obj.put("masterContractName", masterContractName);                  //主合同名称
        obj.put("masterContractRuleSerialNum", masterContractRuleSerialNum);//主合同序号
        obj.put("masterContractPropertyModel", masterContractPropertyModel);//主合同模块
        obj.put("masterContractSection", masterContractSection);//主合同环节

        obj.put("isInnerContract", crContractinfo.getIsInnerContract());//是否内部合同
        String pFrameRuleSerialNum = "";
        if (!StringUtils.isEmpty(crContractinfo.getPContractID())) {
            CrContractbasic pcrcontractbasic = crContractbasicMapper.selectById(crContractinfo.getContractID());
            if (pcrcontractbasic != null) {
                pFrameRuleSerialNum = pcrcontractbasic.getRuleSerialNum();
            }
        }
        obj.put("pFrameRuleSerialNum", pFrameRuleSerialNum);//父框架合同序号
        obj.put("planMoney", crContractinfo.getPlanMoney());//计划金额
        obj.put("planMoneyCurrency", crContractinfo.getPlanMoneyCurrency());//计划金额币种
        String planMoneyCurrencyName = "";
        if (crContractinfo.getPlanMoneyCurrency() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractinfo.getPlanMoneyCurrency());
            if (sysDictionary != null) {
                planMoneyCurrencyName = sysDictionary.getfCnName();
            }
        }
        obj.put("planMoneyCurrencyName", planMoneyCurrencyName);//计划金额币种名称
        obj.put("projectId", crContractinfo.getProjectID());//所属项目
        String projectName = "";//所属项目名称
        if (!StringUtils.isEmpty(crContractinfo.getProjectID())) {
            CrProjectinfo crProjectinfo = crProjectinfoMapper.selectById(crContractinfo.getProjectID());
            if (crProjectinfo != null) {
                projectName = crProjectinfo.getProjectName();
            }
        }
        obj.put("projectName", projectName);//所属项目名称
        obj.put("isMakeSure", crContractbasic.getIsMakeSureMoney());//标的金额是否确定
        String isMakeSureName = "";
        if (crContractbasic.getIsMakeSureMoney() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getIsMakeSureMoney());
            if (sysDictionary != null) {
                isMakeSureName = sysDictionary.getfCnName();
            }
        }
        obj.put("isMakeSureName", isMakeSureName);//标的金额是否确定（取数据字典）

        obj.put("contractObjectMoney", crContractbasic.getContractObjectMoney());//标的金额
        obj.put("currency", crContractbasic.getContractObjectCurrency());//标的币种id
        String currencyName = "";
        if (crContractbasic.getContractObjectCurrency() != null) {
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getContractObjectCurrency());
            if (sysDictionary != null) {
                currencyName = sysDictionary.getfCnName();
            }
        }
        obj.put("currencyName", currencyName);//标的币种名称
        obj.put("contractObjectRate", crContractbasic.getContractObjectRate());//汇率
        obj.put("contractobjectamount", crContractbasic.getContractObjectAmount());//标的金额（人民币含税合同金额）
        obj.put("contractTaxAmount", crContractbasic.getTaxAmount());//合同税额
        obj.put("contractNoTaxAmount", crContractbasic.getNoTaxAmount());//不含税额

        obj.put("mySignBodyId", crContractinfo.getMySignBodyCode());//我方签约主体
        String mySignBodyName = crContractinfo.getMySignBodyName();
        if (StringUtils.isEmpty(crContractinfo.getMySignBodyName())) {
            SysOrganiseunitSinging sysOrganiseunitSinging = sysOrganiseunitSingingMapper.selectById(crContractinfo.getMySignBodyCode());
            if (sysOrganiseunitSinging != null) {
                mySignBodyName = sysOrganiseunitSinging.getSingingName();
            }
        }
        obj.put("mySignBodyName", mySignBodyName);//我方签约主体名称
        obj.put("mySignPersonId", crContractinfo.getMySignPerson());//我方签约人
        obj.put("mySignPersonName", crContractinfo.getMySignPersonName());//我方签约人姓名
        obj.put("mySignPersonPhone", crContractinfo.getMySignPersonPhone());//我方签约人联系电话
        obj.put("mySignPersonCard", crContractinfo.getMySignPersonCard());//我方签约人身份证
        obj.put("mySignPersonUnit", crContractinfo.getMySignPersonUnit());//我方签约人单位
        obj.put("mySignPersonDept", crContractinfo.getMySignPersonDept());//我方签约人部门
        obj.put("mySignPersonPostion", crContractinfo.getMySignPersonPostion());//我方签约人职务
        obj.put("sourceContractNum", crContractbasic.getSourceContractNum());//对方合同编号
        obj.put("remark", crContractbasic.getRemarksText());//备注
        ArrayList<String> executeOrgUserIDs = new ArrayList<>();//收付款执行人
        String executeOrgUserNames = "";//收付款执行人名称
        ArrayList<Integer> executeOrgIDs = new ArrayList<>();//下发单位
        String executeOrgNames = "";//下发单位名称
        //收付款执行人 下发单位
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, crContractbasic.getContractID());
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                //contract.executeOrgUserIDs.add(String.join(",", crContractrununit.getPayUserId()));
                //contract.executeOrgIDs.add(crContractrununit.getFrameOrg());
                if (crContractrununit.getPayUserId() != null) {
                    executeOrgUserIDs.add(crContractrununit.getPayUserId());
                }

                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractrununit.getPayUserId()));
                if (sysUserinfo != null) {
                    executeOrgUserNames += sysUserinfo.getfCname() + ",";
                }
                if (crContractrununit.getFrameOrg() != null) {
                    //  executeOrgIDs += crContractrununit.getFrameOrg() + ",";
                    executeOrgIDs.add(crContractrununit.getFrameOrg());
                }
                SysOrganization sysOrganization1 = organizationRequest.queryOrganization(crContractrununit.getFrameOrg());
                if (sysOrganization1 != null) {
                    executeOrgNames += sysOrganization1.getfName() + ",";
                }
            }
        }
      /*  if (!StringUtils.isEmpty(executeOrgUserIDs)) {
            executeOrgUserIDs = executeOrgUserIDs.substring(0, executeOrgUserIDs.length() - 1);
        }*/
        obj.put("executeOrgUserIDs", executeOrgUserIDs.toArray());//收付款执行人id

        if (!StringUtils.isEmpty(executeOrgUserNames)) {
            executeOrgUserNames = executeOrgUserNames.substring(0, executeOrgUserNames.length() - 1);
        }
        obj.put("executeOrgUserNames", executeOrgUserNames);//收付款执行人名称

       /* if (!StringUtils.isEmpty(executeOrgIDs)) {
            executeOrgIDs = executeOrgIDs.substring(0, executeOrgIDs.length() - 1);
        }*/
        obj.put("executeOrgIDs", executeOrgIDs.toArray());//下发单位

        if (!StringUtils.isEmpty(executeOrgNames)) {
            executeOrgNames = executeOrgNames.substring(0, executeOrgNames.length() - 1);
        }
        obj.put("executeOrgNames", executeOrgNames);//下发单位名称

        ArrayList<String> accordId = new ArrayList<>();
        String accordName = "";
        QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractbasic.getContractID());
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getKind, Constants.HandWorkAccord);//手工签约依据
        List<CrContractaccord> crContractaccordList = crContractaccordMapper.selectList(crContractaccordQueryWrapper);
        if (crContractaccordList != null && crContractaccordList.size() > 0) {
            //String[] accordId = new String[crContractaccordList.size()];
            for (CrContractaccord crContractaccord : crContractaccordList) {
                CrContractaccordoaother crContractaccordoaother = crContractaccordoaotherMapper.selectById(crContractaccord.getAccordingID());
                if (crContractaccordoaother != null) {
                    //accordId += "" + crContractaccordoaother.getAccordingID() + "" + ",";
                    accordId.add(crContractaccordoaother.getAccordingID());
                    accordName += crContractaccordoaother.getAccordingName() + ",";
                }
            }
        }
       /* if (!StringUtils.isEmpty(accordId)) {
            accordId = accordId.substring(0, accordId.length() - 1);
            accordId = "[" + accordId + "]";
        }*/
        obj.put("accordId", accordId.toArray());//签约依据id
        if (!StringUtils.isEmpty(accordName)) {
            accordName = accordName.substring(0, accordName.length() - 1);
        }
        obj.put("accordName", accordName);//签约依据名称
        obj.put("perFormIsConfirm", crContractinfo.getPerFormIsConfirm());//合同履行是否确定 0否 1是
        obj.put("perFormNotConfirmRemark", crContractinfo.getPerFormNotConfirm());//履行期限不确定时返回备注
        obj.put("perFormStartDate", crContractinfo.getPerFormStartDate());//合同履行开始时间
        obj.put("perFormEndDate", crContractinfo.getPerFormEndDate());//合同履行结束时间
        obj.put("changeRemark", crContractinfo.getChangeRemark());//合同退回重新送审，修改说明 0813
        obj.put("settleDeadline", crContractinfo.getSettleDeadline());//结算期限
        obj.put("issueSolveMode", crContractinfo.getIssueSolveMode());//合同纠纷解决方式 //0424
        String issueSolveModeName = "";
        if (crContractinfo.getIssueSolveMode() != null) {
            SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(crContractinfo.getIssueSolveMode());
            if (sysDictionarycategory != null) {
                issueSolveModeName = sysDictionarycategory.getfName();//合同纠纷解决方式中文
            }

        }
        obj.put("issueSolveModeName", issueSolveModeName);//合同纠纷解决方式中文
        obj.put("needPrintCount", crContractinfo.getNeedPrintCount());//合同份数
        obj.put("createdDate", crContractbasic.getCreatedDate());//创建时间
        obj.put("propertyModel", ContractEnum.enumModuleMap.get(crContractbasic.getPropertyModel().toString()));//合同模块
        obj.put("section", ContractEnum.enumSectionMap.get(crContractbasic.getSection().toString()));//合同环节
        obj.put("propertyModelCode", crContractbasic.getPropertyModel().toString());
        obj.put("sectionCode", crContractbasic.getSection().toString());
        //合同终结 增加终结ID  0817
        String endId = "";
        QueryWrapper<CrContractend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractend::getContractID, crContractbasic.getContractID());
        queryWrapper.lambda().eq(CrContractend::getCategory, 1);//合同终结
        queryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
        List<CrContractend> crContractendList = crContractendMapper.selectList(queryWrapper);
        if (crContractendList != null && crContractendList.size() > 0) {
            endId = crContractendList.get(0).getContractEndID();
        }
        obj.put("endId", endId);//合同终结ID
        //2021-03-27添加
        obj.put("reflag", crContractbasic.getBidItemType());//前端返回一个合同补录类型值为10
        return DataResult.success(obj);

    }


    /**
     * 	修改合同履行信息，订立保存/送审
     */
    @Override
    @Transactional
    public boolean updatePerContractInfo(String contractId, Integer needPrintCount, Integer perFormIsConfirm,
                                         String perFormStartDate, String perFormEndDate, Integer issueSolveMode,
                                         String settleDeadline, String taskId, String perFormNotConfirmRemark,
                                         boolean isSubmit,String changeRemark, Integer type,String mySignBodyName,Integer textType,Integer moneyFlow) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        if (!StringUtils.isEmpty(mySignBodyName)) {
            crContractinfo.setMySignBodyName(mySignBodyName);
        }
        if (null!=textType) {
            crContractinfo.setTextType(textType);
        }
        if (null!=moneyFlow) {
            crContractbasic.setMoneyFlow(moneyFlow);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractinfo.setPerFormIsConfirm(perFormIsConfirm);//合同履行是否确定 0否 1是
        if (!StringUtils.isEmpty(perFormStartDate)) {
            crContractinfo.setPerFormStartDate(LocalDateTime.parse(perFormStartDate, dateTimeFormatter));//合同履行开始时间
        }
        if (!StringUtils.isEmpty(perFormEndDate)) {
            crContractinfo.setPerFormEndDate(LocalDateTime.parse(perFormEndDate, dateTimeFormatter));//合同履行结束时间
        }
        if (!StringUtils.isEmpty(settleDeadline)) {
            crContractinfo.setSettleDeadline(LocalDateTime.parse(settleDeadline, dateTimeFormatter));//结算期限
        }
        crContractinfo.setIssueSolveMode(issueSolveMode);//合同纠纷解决方式
        crContractinfo.setNeedPrintCount(needPrintCount);//合同份数
        crContractinfo.setPerFormNotConfirm(perFormNotConfirmRemark);//履行期限不确定时备注
        crContractinfo.setChangeRemark(changeRemark);//修改说明 ，合同审批退回时，填写修改说明
        if (type != null && type == 0) {
            crContractinfoMapper.updateById(crContractinfo);
            setNewTextApproved(crContractbasic.getContractID(), userInfo);
            //合同退回后，重新送审选择送至退回人时，因为调用了backrevert接口，不再触发工作流和发送临时待办

            //修改回退重提不重新推送待办
            dpsRequest.sendOaTask(taskId);
            return true;
        }

        if (isSubmit) {
            if (crContractinfo.getSendCheckDate() != null) {
                crContractinfo.setSendCheckDate(LocalDateTime.now());//送审时间
            }

            setNewTextApproved(crContractbasic.getContractID(), userInfo);
//            //取合同文本第一行改成发布状态
//            QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().eq(CrContracttext::getContractID, crContractbasic.getContractID());
//            // queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//临时保存
//            queryWrapper.lambda().orderByDesc(CrContracttext::getCreatedDate);
//            List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
//            if (crContracttextList != null && crContracttextList.size() > 0) {
//                CrContracttext crContracttext = crContracttextList.get(0);
//                if (crContracttext.getStatus() == Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode())) {
//                    crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//
//                    crContracttext.setModifiedDate(LocalDateTime.now());
//                    crContracttext.setModifiedBy(userInfo.getSysUser().getfId().toString());
//                    crContracttextMapper.updateById(crContracttext);
//                }
//            }
        }
        crContractinfoMapper.updateById(crContractinfo);
        if (!StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);//设置为完成
            //临时消息处理完毕
            workFlowService.completeMessage(taskId);           	
        }
        //触发工作流
        if (isSubmit) {

            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Check.getCode()));
            crContractbasicMapper.updateById(crContractbasic); //0903 合同文本审查审批

//            int updateCnt = crContractbasicMapper.update(crContractbasic, new UpdateWrapper<CrContractbasic>().lambda().
//                    eq(CrContractbasic::getContractID, crContractbasic.getContractID()).
//                    eq(CrContractbasic::getPropertyModel, crContractbasic.getPropertyModel()).
//                    eq(CrContractbasic::getSection, Integer.parseInt(ContractEnum.EnumSection.Make.getCode())));
//
//            if(updateCnt == 0){
//                throw new BaseException("当前合同状态不正确", 500);
//            }
//            int updateCnt = crContractbasicMapper.updateById(crContractbasic);//0903 合同文本审查审批

            StartContext startContext = new StartContext();
            startContext.setBusinessId(crContractbasic.getContractID());
            startContext.setBusinessName(crContractbasic.getContractName());
            List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(), Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                    crContractbasic.getMainDeptID().toString());

            if (appWorkflowData != null && appWorkflowData.size() > 0) {
                startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
            }

            startContext.setPropertyModel(ContractEnum.EnumModule.Make.getCode());//合同订立
            startContext.setSection(ContractEnum.EnumSection.Check.getCode());//合同订立文本审查审批 0903
            startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
            startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());// 合同订立 发起的流程分类编码
            SysOrganization org = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
            if (org != null) {
                startContext.setUnitName(org.getfName());//所属单位/企业
            } else {
                startContext.setUnitName(crContractbasic.getMainOrgName());//所属单位/企业
            }
            startContext.setDepartmentId(crContractbasic.getMainDeptID().toString());//所属部门

            org = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
            if (org != null) {
                startContext.setDepartmentName(org.getfName());//所属部门
                startContext.setOrganiseName(org.getfName());
            } else {
                startContext.setDepartmentName(crContractbasic.getMainDeptName());//所属部门
                startContext.setOrganiseName(crContractbasic.getMainDeptName());
            }
            org = organizationRequest.getOrgCompany(crContractbasic.getMainDeptID());//所属单位/企业/事业部
            if (org != null) {
                startContext.setOrganiseId(org.getfId().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
            } else {
                startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
            }


            startContext.setEnterpriseId(crContractbasic.getOulabel().toString());//所属企业

            startContext.setExecuteDate(new Date());//发起时间

            startContext.setUserId(crContractbasic.getMainOrgUserID());//发起用户id
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                startContext.setUserName(sysUserinfo.getfCname());
            } else {

                startContext.setUserId(userInfo.getSysUser().getfId().toString());//发起用户id
                startContext.setUserName(userInfo.getSysUser().getfCname());//发起用户名
            }
            startContext.setAppId(cmisDefaultConfig.getAppId());//应用ID
            startContext.setRepeatCheckFlag(1);//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理

            List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Make.getCode());//合同订立
//            metas = setAppMetasData(metas, crContractbasic, crContractinfo);//条件字段
            metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");
            startContext.setMetasList(metas);
/*
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

            startContext.setExtendsData(appExtendsData);*/

            AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Make.getCode(), "", "");
            startContext.setExtendsData(appExtendsData);//设置扩展字段

            String json = JSON.toJSONString(startContext);
//            ------------李宪明------------
            dpsRequest.start(startContext);

        } else {
            //发送待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractbasic.getContractName());
            }
            /*appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
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
            appExtendsData.setExt019(ContractEnum.EnumModule.Make.getMessage());//合同环节中文名称
            appExtendsData.setExt020(ContractEnum.EnumSection.Make.getCode());//合同订立*/
            AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Make.getCode(), ContractEnum.EnumModule.Make.getMessage(), ContractEnum.EnumSection.Make.getCode());
            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
            //订立发起后插入代办消息并发送OA信息
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            //插入临时待办消息
            workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
        }
//        if (!StringUtils.isEmpty(taskId)) {
//        	agentRequest.sendMessageForTransactor(crContractbasic.getContractID(),"合同订立");
//        }
        return true;

    }
   
    
    @Transactional
    @Override
    public void backrevertContract(String contractId,Integer needPrintCount,Integer perFormIsConfirm,
                                   String perFormStartDate,String perFormEndDate,Integer issueSolveMode,
                                   String settleDeadline,String perFormNotConfirmRemark, boolean isSubmit,
                                   String changeRemark,Integer type,String taskId,String opinion) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractinfo.setPerFormIsConfirm(perFormIsConfirm);//合同履行是否确定 0否 1是
        if (!StringUtils.isEmpty(perFormStartDate)) {
            crContractinfo.setPerFormStartDate(LocalDateTime.parse(perFormStartDate, dateTimeFormatter));//合同履行开始时间
        }
        if (!StringUtils.isEmpty(perFormEndDate)) {
            crContractinfo.setPerFormEndDate(LocalDateTime.parse(perFormEndDate, dateTimeFormatter));//合同履行结束时间
        }
        if (!StringUtils.isEmpty(settleDeadline)) {
            crContractinfo.setSettleDeadline(LocalDateTime.parse(settleDeadline, dateTimeFormatter));//结算期限
        }
        crContractinfo.setIssueSolveMode(issueSolveMode);//合同纠纷解决方式
        crContractinfo.setNeedPrintCount(needPrintCount);//合同份数
        crContractinfo.setPerFormNotConfirm(perFormNotConfirmRemark);//履行期限不确定时备注
        crContractinfo.setChangeRemark(changeRemark);//修改说明 ，合同审批退回时，填写修改说明
        if (type != null && type == 0) {
            //合同退回后，重新送审选择送至退回人时，因为调用了backrevert接口，不再触发工作流和发送临时待办
            crContractinfoMapper.updateById(crContractinfo);
            setNewTextApproved(crContractbasic.getContractID(), userInfo);
        }


    }

    private void setNewTextApproved(String contractId, UserInfo userInfo){
        //取合同文本第一行改成发布状态
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttext::getContractID, contractId);
        // queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//临时保存
        queryWrapper.lambda().orderByDesc(CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
        if (crContracttextList != null && crContracttextList.size() > 0) {
            CrContracttext crContracttext = crContracttextList.get(0);
            if (crContracttext.getStatus() == Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode())) {
                crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//
                crContracttext.setModifiedDate(LocalDateTime.now());
                crContracttext.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContracttextMapper.updateById(crContracttext);
            }
        }
    }

//    /*
//     * 设置流程条件
//     * */
//    private List<AppMetasData> setAppMetasData(List<AppMetasData> metas, CrContractbasic crContractbasic, CrContractinfo crContractinfo) {
//        List<AppMetasData> appMetasDataList = new ArrayList<>();
//        if (metas != null) {
//            for (AppMetasData appMetasData : metas) {
//                if (appMetasData.getMetaCode().equals("szgt_contract_money")) {
//                    if (crContractbasic.getContractObjectAmount() != null) {
//                        appMetasData.setDataValue(crContractbasic.getContractObjectAmount().toString());//合同金额
//                    } else {
//                        appMetasData.setDataValue("");//合同金额
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contact_type1")) {
//                    if (crContractbasic.getType1() != null) {
//                        appMetasData.setDataValue(crContractbasic.getType1().toString());//合同类别1
//                    } else {
//                        appMetasData.setDataValue("");//合同类别1
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contact_type2")) {
//                    if (crContractbasic.getType2() != null) {
//                        appMetasData.setDataValue(crContractbasic.getType2().toString());//合同类别2
//                    } else {
//                        appMetasData.setDataValue("");//合同类别2
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contact_type3")) {
//                    if (crContractbasic.getType3() != null) {
//                        appMetasData.setDataValue(crContractbasic.getType3().toString());//合同类别3
//                    } else {
//                        appMetasData.setDataValue("");//合同类别3
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contact_type4")) {
//                    if (crContractbasic.getType4() != null) {
//                        appMetasData.setDataValue(crContractbasic.getType4().toString());//合同类别4
//                    } else {
//                        appMetasData.setDataValue("");//合同类别4
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_maindept")) {
//                    if (crContractbasic.getMainDeptID() != null) {
//                        appMetasData.setDataValue(crContractbasic.getMainDeptID().toString());//主办部门
//                    } else {
//                        appMetasData.setDataValue("");//主办部门
//                    }
//                }
//                if(appMetasData.getMetaCode().equals("szgt_contract_mainorg")){//主办单位
//                    if(crContractbasic.getMainOrgID() != null){
//                        appMetasData.setDataValue(crContractbasic.getMainOrgID().toString());
//                    }else{
//                        appMetasData.setDataValue("");
//                    }
//                }
//
//                if(appMetasData.getMetaCode().equals("szgt_contract_planmoney")){//计划金额
//                    if(crContractinfo.getPlanMoney() != null){
//                        appMetasData.setDataValue(crContractinfo.getPlanMoney().toString());
//                    }else{
//                        appMetasData.setDataValue("");
//                    }
//                }
//
//                if (appMetasData.getMetaCode().equals("szgt_contract_moneySource")) {
//                    if (crContractinfo.getMoneySource() != null) {
//                        appMetasData.setDataValue(crContractinfo.getMoneySource().toString());//资金来源一级
//                    } else {
//                        appMetasData.setDataValue("");//资金来源一级
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_moneySource2")) {
//                    if (crContractinfo.getMoneySource() != null) {
//                        appMetasData.setDataValue(crContractinfo.getMoneySource2().toString());//资金来源二级
//                    } else {
//                        appMetasData.setDataValue("");//资金来源二级
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_selectWay1")) {
//                    if (crContractinfo.getSelectWay1() != null) {
//                        appMetasData.setDataValue(crContractinfo.getSelectWay1().toString());//选商方式一级
//                    } else {
//                        appMetasData.setDataValue("");//选商方式一级
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_selectWay2")) {
//                    if (crContractinfo.getSelectWay2() != null) {
//                        appMetasData.setDataValue(crContractinfo.getSelectWay2().toString());//选商方式二级
//                    } else {
//                        appMetasData.setDataValue("");//选商方式二级
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_isFrameContract")) {
//                    if (crContractinfo.getIsFrameContract() != null) {
//                        appMetasData.setDataValue(crContractinfo.getIsFrameContract().toString());//是否框架合同
//                    } else {
//                        appMetasData.setDataValue("");//是否框架合同
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_Handler")) {
//                    if (crContractbasic.getCreatedBy() != null) {
//                        appMetasData.setDataValue(crContractbasic.getCreatedBy());//经办人流程条件
//                    } else {
//                        appMetasData.setDataValue("");//经办人
//                    }
//                }
//                if (appMetasData.getMetaCode().equals("szgt_contract_isMakeSure")) {
//                    if (crContractbasic.getCreatedBy() != null) {
//                        appMetasData.setDataValue(crContractbasic.getIsMakeSureMoney() == null ? "" : crContractbasic.getIsMakeSureMoney().toString());//金额是否确定  0不确定 1确定 2无金额
//                    } else {
//                        appMetasData.setDataValue("");//金额是否确定
//                    }
//                }
//                if(appMetasData.getMetaCode().equals("szgt_contract_MoneyFlow")) {
//                    if (crContractbasic.getMoneyFlow() > 0) {
//                        appMetasData.setDataValue(crContractbasic.getMoneyFlow().toString());//资金流向
//                    } else {
//                        appMetasData.setDataValue("");//资金流向
//                    }
//                }
//                if(appMetasData.getMetaCode().equals("szgt_contract_SignBodyName")) {
//                    if (crContractinfo.getMySignBodyCode() > 0) {
//                        appMetasData.setDataValue(crContractinfo.getMySignBodyName().toString());//签约主体
//                    } else {
//                        appMetasData.setDataValue("");//签约主体
//                    }
//                }
//                if(appMetasData.getMetaCode().equals("szgt_contract_TextType")) {
//                    if (crContractinfo.getMySignBodyCode() > 0) {
//                        appMetasData.setDataValue(crContractinfo.getTextType().toString());//文本类型
//                    } else {
//                        appMetasData.setDataValue("");//文本类型
//                    }
//                }
//
//
//                appMetasDataList.add(appMetasData);
//
//            }
//        }
//        return appMetasDataList;
//    }

    @Override
    @Transactional
    /*
     * 合同废弃
     * */
    public boolean discardContract(String contractId, String taskId, String discardReason) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(discardReason)) {
            throw new NotFoundException("废弃原因必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        Date date = new Date();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        //合同订立送审后，只能废弃，准备阶段状态设置删除
        if (crContractbasic.getSection() > Integer.parseInt(ContractEnum.EnumSection.Check.getCode())) {
            crContractbasic.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.LogicDel.getCode()));
        } else {
            crContractbasic.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));
        }

        crContractbasic.setModifiedDate(LocalDateTime.now());
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractinfo.setDiscardUserID(userInfo.getSysUser().getfId().toString());

        crContractbasic.setModifiedDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractinfo.setDiscardUserID(userInfo.getSysUser().getfId().toString());

        crContractinfo.setDiscardUserName(userInfo.getSysUser().getfCname());
        crContractinfo.setDiscardExplain(discardReason);
        crContractbasicMapper.updateById(crContractbasic);
        crContractinfoMapper.updateById(crContractinfo);

        QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractbasic.getContractID());
        // crContractaccordQueryWrapper.lambda().in(CrContractaccord::getKind, Constants.HandWorkAccord,);//手工签约依据
        List<CrContractaccord> crContractaccordList = crContractaccordMapper.selectList(crContractaccordQueryWrapper);
        if (crContractaccordList != null && crContractaccordList.size() > 0) {
            for (CrContractaccord crContractaccord : crContractaccordList) {
                if (crContractaccord.getKind().equals(Constants.HandWorkMaterial) || crContractaccord.getKind().equals(Constants.ERPOrder)) {
                    CrContractaccordoaother crContractaccordoaother = crContractaccordoaotherMapper.selectById(crContractaccord.getAccordingID());
                    if (crContractaccordoaother != null) {
                        crContractaccordoaother.setUseCount(crContractaccordoaother.getUseCount() - 1);
                        crContractaccordoaotherMapper.updateById(crContractaccordoaother);
                    }
                }
            }
        }
        //合同废弃，同时设置待办消息失效
        dpsRequest.businessdiscard(crContractbasic.getContractID().toString(), crContractbasic.getMainDeptID().toString());
        if (taskId != null && !StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);
            //设置待办消息废弃
            workFlowService.completeMessage(taskId);
        }

        return true;
    }

    /*
     * 合同删除
     * */
    @Override
    public boolean delContract(String contractId, String taskId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        Date date = new Date();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        //合同订立送审后，只能废弃，准备阶段状态设置删除
        if (crContractbasic.getSection() > Integer.parseInt(ContractEnum.EnumSection.Check.getCode())) {
            throw new NotFoundException("合同已送审，不能再删除！", Constants.FAILCODE);
        } else {
            crContractbasic.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));
            crContractinfo.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));
        }

        crContractbasic.setModifiedDate(LocalDateTime.now());
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractbasic.setModifiedDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());

        crContractbasicMapper.updateById(crContractbasic);
        crContractinfoMapper.updateById(crContractinfo);

        QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, crContractbasic.getContractID());
        // crContractaccordQueryWrapper.lambda().in(CrContractaccord::getKind, Constants.HandWorkAccord,);//手工签约依据
        List<CrContractaccord> crContractaccordList = crContractaccordMapper.selectList(crContractaccordQueryWrapper);
        if (crContractaccordList != null && crContractaccordList.size() > 0) {
            for (CrContractaccord crContractaccord : crContractaccordList) {
                if (Constants.HandWorkMaterial.equals(crContractaccord.getKind()) || crContractaccord.getKind().equals(Constants.ERPOrder)) {
                    CrContractaccordoaother crContractaccordoaother = crContractaccordoaotherMapper.selectById(crContractaccord.getAccordingID());
                    if (crContractaccordoaother != null) {
                        crContractaccordoaother.setUseCount(crContractaccordoaother.getUseCount() - 1);
                        crContractaccordoaotherMapper.updateById(crContractaccordoaother);
                    }
                }
            }
        }
        //合同废弃，同时设置待办消息失效
        dpsRequest.businessdiscard(crContractbasic.getContractID().toString(), crContractbasic.getMainDeptID().toString());
        if (taskId != null && !StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);
            //设置待办消息废弃
            workFlowService.completeMessage(taskId);
        }

        return true;
    }

    @Override
    /**
     * 	获取合同文本模板列表
     */
    public DataResult<?> getTextModel(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
      /*  Integer orgId = 0;//所在企业/单位
        String userId = crContractbasic.getMainOrgUserID();//合同经办人
        //获取合同经办人所在单位
        List<Integer> userOrgs = userInfoRequest.queryOrgs(userId);
        if (userOrgs != null && userOrgs.size() > 0) {
            for (Integer deptId : userOrgs) {
                SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
                if (sysOrganization != null) {
                    orgId = sysOrganization.getfId();
                }
            }
        }*/


        String type1 = "";//合同类型1
        String type2 = "";//合同类型2
        String type3 = "";//合同类型3
        List<String> type = new ArrayList<>();
        if (crContractbasic.getType1() != null) {
            type1 = crContractbasic.getType1().toString();
        }
        if (crContractbasic.getType2() != null) {
            type2 = crContractbasic.getType2().toString();
        }
        if (crContractbasic.getType3() != null) {
            type3 = crContractbasic.getType3().toString();
        }

        Integer textModelType = Integer.parseInt(ContractEnum.EnumTextType.makeType.getCode());//订立文本
        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper<>();
       /* queryWrapper.lambda().in(CrContracttextmodel::getType1, type1);//合同类型1 /合同类型2/合同类型3
        queryWrapper.lambda().in(CrContracttextmodel::getType2, type2);//合同类型2
        queryWrapper.lambda().in(CrContracttextmodel::getType3, type3);//合同类型3/
*/
        queryWrapper.lambda().eq(CrContracttextmodel::getIsPrime, 1);
        queryWrapper.lambda().eq(CrContracttextmodel::getLogicDel, 0);
        queryWrapper.lambda().eq(CrContracttextmodel::getTextModelType, textModelType);//订立文本
        queryWrapper.lambda().eq(CrContracttextmodel::getStatus, Integer.parseInt(ContractEnum.EnumContractTextStatus.Publish.getCode()));//发布状态
       /* if (orgId != null) {
            queryWrapper.lambda().in(CrContracttextmodel::getOrgID, orgId);//公开范围
        }*/
        queryWrapper.lambda().orderByAsc(CrContracttextmodel::getApplicantTime);
        List<CrContracttextmodel> crContracttextmodelList = crContracttextmodelMapper.selectList(queryWrapper);
        List<Object> list = new ArrayList<>();
        if (crContracttextmodelList != null) {
            for (CrContracttextmodel crContracttextmodel : crContracttextmodelList) {
                if (!StringUtils.isEmpty(crContracttextmodel.getType1()) && crContracttextmodel.getType1().contains(type1)) {
                    JSONObject obj = new JSONObject(true);
                    obj.put("textId", crContracttextmodel.getFileTemplateID());
                    obj.put("textName", crContracttextmodel.getTextName());
                    list.add(obj);
                    continue;
                }
                if (!StringUtils.isEmpty(crContracttextmodel.getType2()) && crContracttextmodel.getType2().contains(type2)) {
                    JSONObject obj = new JSONObject(true);
                    obj.put("textId", crContracttextmodel.getFileTemplateID());
                    obj.put("textName", crContracttextmodel.getTextName());
                    list.add(obj);
                    continue;
                }
                if (!StringUtils.isEmpty(crContracttextmodel.getType3()) && crContracttextmodel.getType3().contains(type3)) {
                    JSONObject obj = new JSONObject(true);
                    obj.put("textId", crContracttextmodel.getFileTemplateID());
                    obj.put("textName", crContracttextmodel.getTextName());
                    list.add(obj);
                }
            }
        }
        return DataResult.success(list);
    }


    /**
     *	上传非标准合同
     */
    @Override
    public DataResult<?> createContractText(String contractTextId, String contractId, String textId, Integer textType) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrContracttext crContracttext = new CrContracttext();
        crContracttext.setTextID(contractTextId);
        crContracttext.setContractID(contractId);
        crContracttext.setTextType(textType);
        crContracttext.setIssuer(textId);//记录选择的标准文本模板
        crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));
        crContracttext.setCreatedDate(LocalDateTime.now());
        crContracttext.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContracttextMapper.insert(crContracttext);
        return DataResult.success(true);
    }

    /**
     *	获取合同文本
     */
    @Override
    public DataResult<?> getContractText(String contractId, boolean edit) {
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttext::getContractID, contractId);
        if (!edit) {
            queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//文本状态
        }
        queryWrapper.lambda().orderByDesc(CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
        CrContracttext crContracttext = new CrContracttext();
        if (crContracttextList != null && crContracttextList.size() > 0) {
            crContracttext = crContracttextList.get(0);
        }
        JSONObject obj = new JSONObject(true);
        obj.put("contractId", contractId);//合同id
        QueryWrapper<SysAttachmentinfo> attachmentinfoQueryWrapper = new QueryWrapper<>();
        attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttext.getTextID());
        attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
        attachmentinfoQueryWrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);
        List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(attachmentinfoQueryWrapper);
        if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
            SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfoList.get(0);
            obj.put("contractTextName", sysAttachmentinfo.getAttachmentName());//合同文本名称
            obj.put("contractTextUrl", sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath());//合同文本链接
            obj.put("attachmentId", sysAttachmentinfo.getAttachmentID());//附件ID
        } else {
            obj.put("contractTextName", "");//合同文本名称
            obj.put("contractTextUrl", "");//合同文本链接
            obj.put("attachmentId", "");//附件ID
        }
        obj.put("textId", crContracttext.getTextID());//合同文本id
        obj.put("contractTextType", crContracttext.getTextType());//合同文本类型
        obj.put("textModelId", crContracttext.getIssuer());//所选择的合同模板id
        CrContracttextmodel crContracttextmodel = crContracttextmodelMapper.selectById(crContracttext.getIssuer());
        if (crContracttextmodel != null) {
            obj.put("textModelName", crContracttextmodel.getTextName());//所选择的合同模板
        } else {
            obj.put("textModelName", "");//所选择的合同模板名称
        }

        return DataResult.success(obj);
    }

    @Override
    public DataResult<?> getAllContractText(@RequestParam String contractId){
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttext::getContractID, contractId)
        					 .orderByDesc(CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
        Map<String, CrContracttext> textMap = crContracttextList.stream().collect(Collectors.toMap(CrContracttext::getTextID, Function.identity(), (k1, k2) -> k1));
        List<String> tids = crContracttextList.stream().map(CrContracttext::getTextID).collect(Collectors.toList());

        //合同id
        QueryWrapper<SysAttachmentinfo> attachmentinfoQueryWrapper = new QueryWrapper<>();
        attachmentinfoQueryWrapper.lambda().in(SysAttachmentinfo::getPropertyID, tids)
							               .eq(SysAttachmentinfo::getAttachmentTypeName, "contractText")
							               .orderByDesc(SysAttachmentinfo::getCreatedDate);
        
        List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(attachmentinfoQueryWrapper);
        List<Object> resList = new ArrayList<>();
        for(SysAttachmentinfo attachmentinfo : sysAttachmentinfoList){
            JSONObject obj = new JSONObject(true);
            obj.put("contractId", contractId);
            obj.put("contractTextName", attachmentinfo.getAttachmentName());//合同文本名称
            obj.put("contractTextUrl", attachmentinfo.getDocUrl() + attachmentinfo.getAttachmentPath());//合同文本链接
            obj.put("attachmentId", attachmentinfo.getAttachmentID());//附件ID

            CrContracttext crContracttext = textMap.get(attachmentinfo.getPropertyID());
            if(crContracttext != null){
                obj.put("textId", crContracttext.getTextID());//合同文本id
                obj.put("contractTextType", crContracttext.getTextType());//合同文本类型
                obj.put("textModelId", crContracttext.getIssuer());//所选择的合同模板id
            }

            CrContracttextmodel crContracttextmodel = crContracttextmodelMapper.selectById(crContracttext.getIssuer());
            if (crContracttextmodel != null) {
                obj.put("textModelName", crContracttextmodel.getTextName());//所选择的合同模板
            } else {
                obj.put("textModelName", "");//所选择的合同模板名称
            }

            resList.add(obj);
        }

        return DataResult.success(resList);
    }

    /**
     *	获取最新合同文本
     */
    @Override
    public DataResult<?> getNewContractText(String contractId) {

        List<JSONObject> list = new ArrayList<>();

        List<CrContracttext> crContracttexts = new ArrayList<>();
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CrContracttext::getContractID, contractId);
        queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);

        if (crContracttextList != null && crContracttextList.size() > 0) {
            CrContracttext crContracttext = crContracttextList.get(0);
            JSONObject obj = new JSONObject(true);
            obj.put("contractId", contractId);//合同id
            QueryWrapper<SysAttachmentinfo> attachmentinfoQueryWrapper = new QueryWrapper<>();
            attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttext.getTextID());
            attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
            List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(attachmentinfoQueryWrapper);
            if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
                SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfoList.get(0);
                obj.put("contractTextName", sysAttachmentinfo.getAttachmentName());//合同文本名称
                obj.put("contractTextUrl", sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath());//合同文本链接
                obj.put("attachmentId", sysAttachmentinfo.getAttachmentID());//附件ID
            } else {
                obj.put("contractTextName", "");//合同文本名称
                obj.put("contractTextUrl", "");//合同文本链接
                obj.put("attachmentId", "");//附件ID
            }

            obj.put("contractTextType", crContracttext.getTextType());//合同文本类型
            obj.put("textModelId", crContracttext.getIssuer());//所选择的合同模板id
            CrContracttextmodel crContracttextmodel = crContracttextmodelMapper.selectById(crContracttext.getIssuer());
            if (crContracttextmodel != null) {
                obj.put("textModelName", crContracttextmodel.getTextName());//所选择的合同模板
            } else {
                obj.put("textModelName", "");//所选择的合同模板名称
            }

            list.add(obj);

        }
        return DataResult.success(list);
    }

    /**
     *	获取历史合同文本
     */
    @Override
    public DataResult<?> getHistoryContractText(String contractId) {

        List<JSONObject> list = new ArrayList<>();

        List<CrContracttext> crContracttexts = new ArrayList<>();
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttext::getContractID, contractId);
        queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//文本状态
        queryWrapper.lambda().orderByDesc(CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);

        if (crContracttextList != null && crContracttextList.size() > 0) {
            int i = 0;
            for (CrContracttext crContracttext : crContracttextList) {
                if (i > 0) {
                    //排除最新的合同文本
                    crContracttext = crContracttextList.get(i);
                    JSONObject obj = new JSONObject(true);
                    obj.put("contractId", contractId);//合同id
                    QueryWrapper<SysAttachmentinfo> attachmentinfoQueryWrapper = new QueryWrapper<>();
                    attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttext.getTextID());
                    attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
                    List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(attachmentinfoQueryWrapper);
                    if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
                        SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfoList.get(0);
                        obj.put("contractTextName", sysAttachmentinfo.getAttachmentName());//合同文本名称
                        obj.put("contractTextUrl", sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath());//合同文本链接
                    } else {
                        obj.put("contractTextName", "");//合同文本名称
                        obj.put("contractTextUrl", "");//合同文本链接
                    }

                    obj.put("contractTextType", crContracttext.getTextType());//合同文本类型
                    obj.put("textModelId", crContracttext.getIssuer());//所选择的合同模板id
                    CrContracttextmodel crContracttextmodel = crContracttextmodelMapper.selectById(crContracttext.getIssuer());
                    if (crContracttextmodel != null) {
                        obj.put("textModelName", crContracttextmodel.getTextName());//所选择的合同模板
                    } else {
                        obj.put("textModelName", "");//所选择的合同模板名称
                    }
                    list.add(obj);
                }
                i++;
            }

        }
        return DataResult.success(list);
    }


    /**
     *	前往纸质打印
     */
    @Override
    @Transactional
    public DataResult<?> contractToSign(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        String innerContractId = crContractinfo.getInnerContractID();
        if (!StringUtils.isEmpty(innerContractId)) {
            CrContractbasic crContractbasicInner = crContractbasicMapper.selectById(innerContractId);
            String ruleserilNum = crContractbasicInner.getRuleSerialNum();
            if (crContractbasicInner.getSection() < Integer.parseInt(ContractEnum.EnumSection.Print.getCode())) {
                return DataResult.fail("对方合同" + ruleserilNum + "未审批完成，请稍等", 400, "对方合同" + ruleserilNum + "未完成审批，请等待");
            }
        } else {
            //根据合同主办部门所在企业/单位，获取水印文件模板,合同文本生成带水印
            //do
            //给合同经办人发送待办消息
            //do
        }
        return DataResult.success(true);
    }

    /**
     * 合同打印完成
     */
    @Override
    @Transactional
    public boolean contractPrintCompelete(String contractId, String taskId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractbasic> basicQueryWrapper = new QueryWrapper<>();
        basicQueryWrapper.lambda().eq(CrContractbasic::getContractID, contractId).last("for update");

        CrContractbasic crContractbasic = crContractbasicMapper.selectOne(basicQueryWrapper);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        if(crContractbasic.getSection() == Integer.parseInt(ContractEnum.EnumSection.Sign.getCode())){
            throw new BaseException("合同已处理", Constants.FAILCODE);
        }

        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        if (!StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);
            workFlowService.completeMessage(taskId);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        crContractbasic.setPrintDate(LocalDateTime.now());//打印时间
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Sign.getCode()));//合同签署
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractbasic.setModifiedDate(LocalDateTime.now());
        crContractbasicMapper.updateById(crContractbasic);

        //保存打印记录
        QueryWrapper<CrContractprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractprint::getContractID, contractId);
        List<CrContractprint> crContractprintList = crContractprintMapper.selectList(queryWrapper);
        if (crContractprintList.size() <= 0) {
            CrContractprint crContractprint = new CrContractprint();
            crContractprint.setPrintID(UUID.randomUUID().toString());
            crContractprint.setContractID(contractId);
            crContractprint.setTextGuid(contractId);
            crContractprint.setIsPrintComplete(1);
            crContractprint.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
            crContractprint.setCreatedDate(LocalDateTime.now());
            crContractprint.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContractprint.setPrintDateTime(LocalDateTime.now());
            crContractprint.setPrinter(userInfo.getSysUser().getfId().toString());
            crContractprint.setPrintCount(1);
            crContractprint.setOulabel(crContractbasic.getOulabel());
            crContractprintMapper.insert(crContractprint);

        } else {
            for (CrContractprint crContractprint : crContractprintList) {
                crContractprint.setIsPrintComplete(1);
                crContractprint.setPrintDateTime(LocalDateTime.now());
                crContractprint.setPrinter(userInfo.getSysUser().getfId().toString());
                crContractprint.setPrintCount(crContractprint.getPrintCount() + 1);
                crContractprintMapper.updateById(crContractprint);
            }
        }
        //给经办人发送签署待办消息
        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContractbasic.getContractID());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
        taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
        }

      /*  appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
        appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(ContractEnum.EnumSection.Perform.getCode());//合同环节
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
        appExtendsData.setExt020(ContractEnum.EnumSection.Sign.getCode());//打印完成
        taskMessage.setExtendsData(appExtendsData);*/

        AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Make.getMessage(), ContractEnum.EnumSection.Sign.getCode());
        taskMessage.setExtendsData(appExtendsData);//设置扩展字段

        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时待办消息
        workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);

        return true;
    }

    /*
     * 合同签署列表
     * */
    @Override
    public DataResult<?> getContractSignList(String ruleserialNum, String contractName, String isFrameContract, Integer pageNum, Integer pageSize) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (ruleserialNum != null && !StringUtils.isEmpty(ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum.trim());
        }
        if (contractName != null && !StringUtils.isEmpty(contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractName.trim());
        }
        if (isFrameContract != null && !StringUtils.isEmpty(isFrameContract)) {
            crContractbasicQueryWrapper.eq("isFrameContract", isFrameContract);
        }
        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//查询有效合同
        crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));//合同签署环节
        crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getSection, Integer.parseInt(ContractEnum.EnumSection.Sign.getCode()));//合同签署环节
        List<Integer> orgIds = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //用户查询授权
        QueryWrapper<AmQuerylicense> amQuerylicenseQueryWrapper = new QueryWrapper<>();
        amQuerylicenseQueryWrapper.lambda().eq(AmQuerylicense::getUserID, userInfo.getSysUser().getfId());
        List<AmQuerylicense> amQuerylicenseList = amQuerylicenseMapper.selectList(amQuerylicenseQueryWrapper);
        if (amQuerylicenseList != null && amQuerylicenseList.size() > 0) {
            for (AmQuerylicense amQuerylicense : amQuerylicenseList) {
                int orgId = amQuerylicense.getOrgID();
                if (!orgIds.contains(orgId)) {
                    orgIds.add(orgId);
                }
                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(orgId);
                if (childOrgList != null && childOrgList.size() > 0) {
                    for (SysOrganization sysOrganization : childOrgList) {
                        if (!orgIds.contains(sysOrganization.getfId())) {
                            orgIds.add(sysOrganization.getfId());
                        }

                    }
                }
            }
        }
        if (orgIds != null && orgIds.size() > 0) {
            crContractbasicQueryWrapper.and(wrapper -> wrapper.in("a.MainDeptID", orgIds).or().eq("a.CreatedBy", userInfo.getSysUser().getfId().toString()));
        } else {
            crContractbasicQueryWrapper.eq("a.CreatedBy", userInfo.getSysUser().getfId());
        }
        crContractbasicQueryWrapper.orderByDesc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        /* userInfoRequest.queryAll();*/
        List<HashMap> list = crContractbasicMapper.queryContract(page, crContractbasicQueryWrapper);
        Integer[] mainDeptIDS = list.stream().map(i -> (Integer) i.get("MainDeptID")).toArray(Integer[]::new);
        List<SysOrganization> sysOrganizations = organizationRequest.queryOrgByIdBatch(mainDeptIDS);
        Map<Integer, SysOrganization> mainDeptMap = sysOrganizations.stream().
                collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k1));

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
                obj.put("contractName", map.get("ContractName"));//合同名称
                obj.put("contractNum", map.get("ContractNum"));//合同编码
                obj.put("type1", map.get("Type1"));//合同类型1
                String type1Name = "";
                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type1").toString()));
                    if (sysDictionary != null) {
                        type1Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type1Name", type1Name);//合同类型1名称

                obj.put("type2", map.get("Type2"));//合同类型2
                String type2Name = "";
                if (map.get("Type2") != null && !StringUtils.isEmpty(map.get("Type2"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type2").toString()));
                    if (sysDictionary != null) {
                        type2Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type2Name", type2Name);//合同类型2名称


                obj.put("type3", map.get("Type3"));//合同类型3
                String type3Name = "";
                if (map.get("Type3") != null && !StringUtils.isEmpty(map.get("Type3"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type3").toString()));
                    if (sysDictionary != null) {
                        type3Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type3Name", type3Name);//合同类型3名称


                obj.put("type4", map.get("Type4"));//合同类型4
                String type4Name = "";
                if (map.get("Type4") != null && !StringUtils.isEmpty(map.get("Type4"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type4").toString()));
                    if (sysDictionary != null) {
                        type4Name = sysDictionary.getfCnName();
                    }
                }
                obj.put("type4Name", type4Name);//合同类型4名称
                obj.put("projectId", map.get("ProjectID"));//项目id
                String projectName = "";
                if (map.get("ProjectID") != null && !StringUtils.isEmpty(map.get("ProjectID"))) {
                    CrProjectinfo crProjectinfo = crProjectinfoMapper.selectById(map.get("ProjectID").toString());
                    if (crProjectinfo != null) {
                        projectName = crProjectinfo.getProjectName();
                    }
                }
                obj.put("projectName", projectName);//项目名称

                //合同相对人信息
                String offereeId = "";
                String offereeName = "";
                QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
                contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, map.get("ContractID"));
                List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
                if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
                    for (CrContractofferee crContractofferee : crContractoffereeList) {
                        FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(crContractofferee.getOffereeID());
                        if (ffOffereeinfo != null) {
                            offereeId += ffOffereeinfo.getOffereeId() + ";";
                            offereeName += ffOffereeinfo.getOffereeName() + ";";
                        }
                    }
                }
                if (!StringUtils.isEmpty(offereeId)) {
                    offereeId = offereeId.substring(0, offereeId.length() - 1);
                }
                if (!StringUtils.isEmpty(offereeName)) {
                    offereeName = offereeName.substring(0, offereeName.length() - 1);
                }
                obj.put("offereeId", offereeId);//相对人id
                obj.put("offereeName", offereeName);//相对人名称

                obj.put("moneyFlow", map.get("MoneyFlow"));//资金流向id
                String moneyFlowName = "";
                if (map.get("MoneyFlow") != null && !StringUtils.isEmpty(map.get("MoneyFlow"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
                    if (sysDictionary != null) {
                        moneyFlowName = sysDictionary.getfCnName();
                    }
                }
                obj.put("moneyFlowName", moneyFlowName);//资金流向名称
                obj.put("planMoney", map.get("PlanMoney"));//计划金额
                obj.put("planMoneyCurrency", map.get("PlanMoneyCurrency"));//计划金额币种
                String planMoneyCurrencyName = "";
                if (map.get("PlanMoneyCurrency") != null && !StringUtils.isEmpty(map.get("PlanMoneyCurrency"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("PlanMoneyCurrency").toString()));
                    if (sysDictionary != null) {
                        planMoneyCurrencyName = sysDictionary.getfCnName();
                    }
                }
                obj.put("planMoneyCurrencyName", planMoneyCurrencyName);//计划金额币种

                obj.put("contractObjectMoney", map.get("ContractObjectMoney"));//标的金额
                obj.put("contractObjectCurrency", map.get("ContractObjectCurrency"));//标的金额币种
                String contractObjectCurrencyName = "";
                if (map.get("ContractObjectCurrency") != null && !StringUtils.isEmpty(map.get("ContractObjectCurrency"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("ContractObjectCurrency").toString()));
                    if (sysDictionary != null) {
                        contractObjectCurrencyName = sysDictionary.getfCnName();
                    }
                }
                obj.put("contractObjectCurrencyName", contractObjectCurrencyName);//标的金额币种中文
                obj.put("contractObjectRate", map.get("ContractObjectRate"));//汇率
                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额
                obj.put("contractTaxAmount", map.get("TaxAmount"));//税额
                obj.put("contractNoTaxAmount", map.get("NoTaxAmount"));//不含税额
                obj.put("isInnerContract", map.get("IsInnerContract"));//是否内部合同 0否 1是
                obj.put("mySignPerson", map.get("MySignPerson"));//我方签约代表id
                String mySignPersonName = "";
                if (map.get("MySignPersonName") == null || StringUtils.isEmpty(map.get("MySignPersonName"))) {
                    if (map.get("MySignPerson") != null && !StringUtils.isEmpty(map.get("MySignPerson").toString())) {
                        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MySignPerson").toString()));
                        if (sysUserinfo != null) {
                            mySignPersonName = sysUserinfo.getfCname();
                        }
                    }

                } else {
                    mySignPersonName = map.get("MySignPersonName").toString();
                }
                obj.put("mySignPersonName", mySignPersonName);//我方签约代表姓名

                obj.put("createdBy", map.get("MainOrgUserID"));//经办人id
                String createdByName = "";
                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
                    if (sysUserinfo != null) {
                        createdByName = sysUserinfo.getfCname();
                    }
                }
                obj.put("createdByName", createdByName);//经办人名称
                obj.put("createdDate", map.get("CreatedDate"));//主办时间
                obj.put("finalityDate", map.get("FinalityDate"));//合同履行完成日期
                obj.put("mySignDate", map.get("MySignDate"));//签订日期
                obj.put("mySealDate", map.get("MySealDate"));//合同订立备案日期

                String propertyModel = "";//合同模块
                String section = "";//合同环节
                if (map.get("PropertyModel") != null) {
                    propertyModel = map.get("PropertyModel").toString();
                }
                if (map.get("Section") != null) {
                    section = map.get("Section").toString();
                }
                if (!StringUtils.isEmpty(propertyModel)) {
                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
                }
                if (!StringUtils.isEmpty(section)) {
                    section = ContractEnum.enumSectionMap.get(section);
                }
                obj.put("propertyModel", propertyModel);//合同模块

                obj.put("section", section);//合同环节

                obj.put("mainDeptId", map.get("MainDeptID"));

                Integer mainDeptID = (Integer) map.get("MainDeptID");
                obj.put("mainDeptName", mainDeptMap.get(mainDeptID).getfName());

                if (map.get("MoneySource") != null && !StringUtils.isEmpty(map.get("MoneySource"))) {
                    SysDictionarycategory ms1 = dictionaryRequest.queryCategoryById((Integer) map.get("MoneySource"));
                    obj.put("moneySource1", ms1 != null ? ms1.getfCnName() : "");
                } else {
                    obj.put("moneySource1", "");
                }
                if (map.get("MoneySource2") != null && !StringUtils.isEmpty(map.get("MoneySource2"))) {
                    SysDictionarycategory ms2 = dictionaryRequest.queryCategoryById(Integer.parseInt((String) map.get("MoneySource2")));
                    obj.put("moneySource2", ms2 != null ? ms2.getfCnName() : "");
                } else {
                    obj.put("moneySource2", "");
                }


                obj.put("mySignPerson", map.get("MySignPerson"));
                obj.put("mySignPersonName", map.get("MySignPersonName"));

                if (map.get("FinalityDate") == null || StringUtils.isEmpty(map.get("FinalityDate"))) {
                    obj.put("isFinality", 0);//合同是否终结
                } else {
                    obj.put("isFinality", 1);//合同是否终结
                }
                objectList.add(obj);
            }
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }


    /**
     *	合同签署提交/保存
     */
    @Override
    @Transactional
    public boolean contractSign(String contractId, String mySignPerson, String otherSignPerson2,String useSignetApprover,
                                String mySignDate, Integer perFormIsConfirm, String perFormStartDate,String perFormEndDate, 
                                String apporveUser, String signAddr,String eEffectiveDate, Integer importantDocType, 
                                String importantDoc, String taskId, boolean isSubmit, String perFormNotConfirmRemark) {
    	
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractbasic> basicQueryWrapper = new QueryWrapper<>();
        basicQueryWrapper.lambda().eq(CrContractbasic::getContractID, contractId).last("for update");

        CrContractbasic crContractbasic = crContractbasicMapper.selectOne(basicQueryWrapper);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        if(crContractbasic.getPropertyModel() == Integer.parseInt(ContractEnum.EnumModule.Perform.getCode())){
            throw new BaseException("合同已处理", Constants.FAILCODE);
        }

        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractinfo.setMySignPerson(mySignPerson);
        //根据我方签约人，获得相关电话、职位等信息
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(mySignPerson));
        if (sysUserinfo != null) {
            crContractinfo.setMySignPersonName(sysUserinfo.getfCname());//我方签约人姓名
            crContractinfo.setMySignPersonPhone(sysUserinfo.getfPhoneNum());//我方签约人电话
            crContractinfo.setMySignPersonCard(sysUserinfo.getfIdCard());//我方签约人身份证
        }
        //2021-05-08 add
        crContractinfo.setUseSignetApprover(useSignetApprover);//用印审批人
        
        crContractinfo.setOtherSignPerson2(otherSignPerson2);//对方签约人
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        crContractinfo.setPerFormIsConfirm(perFormIsConfirm);//合同履行是否确定 0否 1是
        crContractinfo.setPerFormNotConfirm(perFormNotConfirmRemark);//履行期限不确定是，备注
        if (!StringUtils.isEmpty(mySignDate)) {
            crContractinfo.setMySignDate(LocalDateTime.parse(mySignDate, dateTimeFormatter));//签约日期
        }

        crContractinfo.setPerFormIsConfirm(perFormIsConfirm);//履行期限，确定/不确定
        if (!StringUtils.isEmpty(perFormStartDate)) {
            crContractinfo.setPerFormStartDate(LocalDateTime.parse(perFormStartDate, dateTimeFormatter));//履行期限开始时间
        }
        if (!StringUtils.isEmpty(perFormEndDate)) {
            crContractinfo.setPerFormEndDate(LocalDateTime.parse(perFormEndDate, dateTimeFormatter));//履行期限结束时间
        }

        crContractinfo.setSignAddr(signAddr);//签约地点
        crContractinfo.setCheckPerSon(apporveUser);//审批人
        if (!StringUtils.isEmpty(eEffectiveDate)) {
            crContractinfo.setEffectiveDate(LocalDateTime.parse(eEffectiveDate, dateTimeFormatter));//合同生效日期
        }
        crContractinfo.setImportantType(importantDocType);//合同生效情况 0 即时生效 1 签字并盖章 2其他
        crContractinfo.setImportantDoc(importantDoc);//合同生效要间

        //&& crContractinfo.getSealRecordDate() == null
        if (isSubmit) {
            if (!StringUtils.isEmpty(taskId)) {
                dpsRequest.taskMessageComplete(taskId);
                //临时消息处理完毕
                workFlowService.completeMessage(taskId);
            }
            crContractinfo.setSealRecordDate(LocalDateTime.now());//签署提交时间
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
            crContractbasic.setModifiedDate(LocalDateTime.now());
            crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());

            SysUserinfo creator = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));

            //取收付款执行人
            QueryWrapper<CrContractrununit> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
            List<CrContractrununit> crContractrununits = crContractrununitMapper.selectList(queryWrapper);
            if (crContractrununits != null && crContractrununits.size() > 0) {
                for (CrContractrununit crContractrununit : crContractrununits) {
                    String userId = crContractrununit.getPayUserId();//收付款执行人
                    //发送合同履行待办消息
                    DpsTaskMessage taskMessage = new DpsTaskMessage();
                    taskMessage.setBusinessId(crContractbasic.getContractID());
                    taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
                    taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
                    sysUserinfo = userInfoRequest.queryById(Integer.parseInt(userId));
                    if (sysUserinfo != null) {
                        taskMessage.setExecutorName(sysUserinfo.getfCname());
                        taskMessage.setExecutorCode(sysUserinfo.getfCode());
                        taskMessage.setBusinessName(crContractbasic.getContractName());
                        taskMessage.setCreatorCode(creator.getfCode());
                    }
                    AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                            ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());
                    taskMessage.setExtendsData(appExtendsData);//设置扩展字段
                    AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                    //插入临时待办消息
                    workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
                }
            } else {
                //给经办人发送履行待办
                String userId = crContractbasic.getMainOrgUserID();//合同经办人
                //发送合同履行待办消息
                DpsTaskMessage taskMessage = new DpsTaskMessage();
                taskMessage.setBusinessId(crContractbasic.getContractID());
                taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
                taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
                sysUserinfo = userInfoRequest.queryById(Integer.parseInt(userId));
                if (sysUserinfo != null) {
                    taskMessage.setExecutorName(sysUserinfo.getfCname());
                    taskMessage.setExecutorCode(sysUserinfo.getfCode());
                    taskMessage.setBusinessName(crContractbasic.getContractName());
                    taskMessage.setCreatorCode(creator.getfCode());
                }
                AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                        ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());
                taskMessage.setExtendsData(appExtendsData);//设置扩展字段
                AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                //插入临时待办消息
                workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
            }

            //TODO 发送合同数据到财务系统
            sendToFinancial(crContractbasic, crContractinfo, FinancialEnum.FULFIL);

        } else {
            //发送合同打印页面完成页面
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
            sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
            }

            /*appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
            appExtendsData.setExt001("contract");//合同系统标识
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
            appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节中文名称
            appExtendsData.setExt020(ContractEnum.EnumSection.Sign.getCode());//合同签署完成*/
            AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Sign.getCode());
            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
          /*  AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            //插入临时待办消息
            workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);*/
        }
        crContractinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractinfo.setModifiedDate(LocalDateTime.now());

        crContractbasicMapper.updateById(crContractbasic);
        crContractinfoMapper.updateById(crContractinfo);
        return true;
    }

    /**
     * 同步合同信息到财务系统
     *
     * @param crContractbasic
     * @param crContractinfo
     */
    private void sendToFinancial(CrContractbasic crContractbasic, CrContractinfo crContractinfo, FinancialEnum financialEnum) {

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        String unitName = userInfo.getUnitName();

        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setContractId(crContractbasic.getContractID());
        contractInfo.setContractCode(crContractbasic.getContractNum());
        contractInfo.setContractName(crContractbasic.getContractName());
        contractInfo.setContractState(financialEnum.getCode());
        contractInfo.setContractStateN(financialEnum.getName());

        if (crContractbasic.getContractObjectMoney() != null) {
            contractInfo.setContractAmount(crContractbasic.getContractObjectMoney());
        }

        //币种名称
        if (crContractbasic.getContractObjectCurrency() != null) {
            contractInfo.setContractCurreC(crContractbasic.getContractObjectCurrency().toString());
            SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(crContractbasic.getContractObjectCurrency());
            if (sysDictionary != null) {
                contractInfo.setContractCurreN(sysDictionary.getfCnName());
            }
        }

        // 合同类型
        List<String> typeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        if (crContractbasic.getType1() != null) {
            SysDictionarycategory type = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
            if (!StringUtils.isEmpty(type.getfRemarks())) {
                typeList.add(type.getfRemarks());
            }
            if (!StringUtils.isEmpty(type.getfCnName())) {
                nameList.add(type.getfCnName());
            }
        }

        if (crContractbasic.getType2() != null) {
            SysDictionarycategory type = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
            if (!StringUtils.isEmpty(type.getfRemarks())) {
                typeList.add(type.getfRemarks());
            }
            if (!StringUtils.isEmpty(type.getfCnName())) {
                nameList.add(type.getfCnName());
            }
        }

        if (crContractbasic.getType3() != null) {
            SysDictionarycategory type = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
            if (!StringUtils.isEmpty(type.getfRemarks())) {
                typeList.add(type.getfRemarks());
            }
            if (!StringUtils.isEmpty(type.getfCnName())) {
                nameList.add(type.getfCnName());
            }
        }

        if (crContractbasic.getType4() != null) {
            SysDictionarycategory type = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
            if (!StringUtils.isEmpty(type.getfRemarks())) {
                typeList.add(type.getfRemarks());
            }
            if (!StringUtils.isEmpty(type.getfCnName())) {
                nameList.add(type.getfCnName());
            }
        }

        contractInfo.setContractType(String.join("/", typeList));
        contractInfo.setContractTypeN(String.join("/", nameList));

        // 只同步部门名称
        contractInfo.setDeaprCode("");
        // 部门名称
        SysOrganization sysOrganization2 = organizationRequest.queryOrganization(crContractbasic.getMainDeptID());
        if (sysOrganization2 != null) {
            contractInfo.setDeparName(sysOrganization2.getfName());
        } else {
            contractInfo.setDeparName("");
        }

        // 公司名称
        contractInfo.setCompanyCode("");
        contractInfo.setCompanyName(unitName);

        //经办人编号、名称
        contractInfo.setHandlUserCode("");
        if (!StringUtils.isEmpty(crContractbasic.getMainOrgUserID())) {
            SysUserinfo handlUser = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (handlUser != null) {
                contractInfo.setHandlUserName(handlUser.getfCname());
            }
        }

        // 合同日期
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (crContractinfo.getPerFormStartDate() != null) {
            contractInfo.setContrBeginDate(dateTimeFormatter.format(crContractinfo.getPerFormStartDate()));
        }
        if (crContractinfo.getPerFormEndDate() != null) {
            contractInfo.setContrEndDate(dateTimeFormatter.format(crContractinfo.getPerFormEndDate()));
        }

        contractInfo.setContractDate(dateTimeFormatter.format(crContractinfo.getMySignDate()));

        //合同主体(签约主体)
        String mySignBodyName = crContractinfo.getMySignBodyName();
        if (StringUtils.isEmpty(mySignBodyName)) {
            SysOrganiseunitSinging sysOrganiseunitSinging = sysOrganiseunitSingingMapper.selectById(crContractinfo.getMySignBodyCode());
            if (sysOrganiseunitSinging != null) {
                mySignBodyName = sysOrganiseunitSinging.getSingingName();
            }
        }
        contractInfo.setContractParty(mySignBodyName);

        //是否框架合同
        contractInfo.setContrIsFrame(crContractinfo.getIsFrameContract() == null ? "" : crContractinfo.getIsFrameContract().toString());


        //合同相对人
        QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
        crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, crContractbasic.getContractID());
        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
        if (!CollectionUtils.isEmpty(crContractoffereeList)) {
            int index = 1;
            for (CrContractofferee contractofferee : crContractoffereeList) {
                FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(contractofferee.getOffereeID());
                String offereeCode = ffOffereeinfo.getOffereeCode();
                String offereeName = contractofferee.getOffereeName();
                if (index == 1) {
                    contractInfo.setContrUserCode1(offereeCode);
                    contractInfo.setContrUserName1(offereeName);
                } else if (index == 2) {
                    contractInfo.setContrUserCode2(offereeCode);
                    contractInfo.setContrUserName2(offereeName);
                } else if (index == 3) {
                    contractInfo.setContrUserCode3(offereeCode);
                    contractInfo.setContrUserName3(offereeName);
                } else if (index == 4) {
                    contractInfo.setContrUserCode4(offereeCode);
                    contractInfo.setContrUserName4(offereeName);
                } else if (index == 5) {
                    contractInfo.setContrUserCode5(offereeCode);
                    contractInfo.setContrUserName5(offereeName);
                }

                index++;
            }
        }

        DataResult<?> contractText = getContractText(contractInfo.getContractId(), false);
        JSONObject jsonObject = (JSONObject) contractText.getData();
        contractInfo.setContractUrl((String) jsonObject.get("contractTextUrl"));
        financialService.sendDataToFinancial(contractInfo);

    }

    /**
     * 	获取合同签署信息
     */
    @Override
    public DataResult<?> getContractSign(String contractId) {

        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        JSONObject obj = new JSONObject(true);
        obj.put("contractId", crContractinfo.getContractID());//合同id
        obj.put("mySignPerson", crContractinfo.getMySignPerson());//我方签约人id
        obj.put("mySignPersonName", crContractinfo.getMySignPersonName());//我方签约人姓名
        obj.put("myMySignPersonPhone", crContractinfo.getMySignPersonCard());//我方签约人电话
        obj.put("mySignPersonCard", crContractinfo.getMySignPersonCard());//我方签约人身份证
        obj.put("otherSignPerson2", crContractinfo.getOtherSignPerson2());//对方签约人姓名
        obj.put("perFormIsConfirm", crContractinfo.getPerFormIsConfirm());//合同履行是否确定 0否 1是
        obj.put("mySignDate", crContractinfo.getMySignDate());//签约日期
        obj.put("perFormIsConfirm", crContractinfo.getPerFormIsConfirm());//履行期限，确定/不确定
        obj.put("perFormStartDate", crContractinfo.getPerFormStartDate());//履行期限开始时间
        obj.put("perFormEndDate", crContractinfo.getPerFormEndDate());//履行期限结束时间
        obj.put("signAddr", crContractinfo.getSignAddr());//签约地点
        obj.put("apporveUser", crContractinfo.getCheckPerSon());//审批人
        obj.put("eEffectiveDate", crContractinfo.getEffectiveDate());//合同生效日期
        obj.put("importantDocType", crContractinfo.getImportantType());//合同生效情况 0 即时生效 1 签字并盖章 2其他
        obj.put("importantDoc", crContractinfo.getImportantDoc());//合同生效要件
        obj.put("useSignetApprover",crContractinfo.getUseSignetApprover());//用印审批人
        return DataResult.success(obj);
    }

    /**
     * 	合同准备草稿箱列表
     */
    @Override
    public DataResult<?> queryPreparContract(Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        List<Object> objectList = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, userInfo.getSysUser().getfId());
        queryWrapper.lambda().eq(CrContractbasic::getStatus, Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));
        queryWrapper.lambda().eq(CrContractbasic::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        IPage<CrContractbasic> page = new Page<>(pageNum, pageSize);
        IPage<CrContractbasic> crContractbasicIPage = crContractbasicMapper.selectPage(page, queryWrapper);
        if (crContractbasicIPage != null && crContractbasicIPage.getSize() > 0) {
            for (CrContractbasic crContractbasic : crContractbasicIPage.getRecords()) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractId", crContractbasic.getContractID());//合同id
                obj.put("contractName", crContractbasic.getContractName());//合同名称
                obj.put("ruleserialNum", crContractbasic.getRuleSerialNum());//合同序号
                obj.put("type1", crContractbasic.getType1());//合同一级类别
                obj.put("type2", crContractbasic.getType2());//合同二级类别
                obj.put("type3", crContractbasic.getType3());//合同三级类别
                obj.put("type4", crContractbasic.getType4());//合同四级类别
                SysDictionarycategory dictionarycategory = new SysDictionarycategory();
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
                    if (dictionarycategory != null) {
                        obj.put("type1Name", dictionarycategory.getfName());//合同一级类别名称
                    }
                } else {
                    obj.put("type1Name", "");//合同一级类别名称
                }
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
                    if (dictionarycategory != null) {
                        obj.put("type2Name", dictionarycategory.getfName());//合同二级类别名称
                    }
                } else {
                    obj.put("type2Name", "");//合同二级类别名称
                }
                if (crContractbasic.getType3() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
                    if (dictionarycategory != null) {
                        obj.put("type3Name", dictionarycategory.getfName());//合同三级类别名称
                    }
                } else {
                    obj.put("type3Name", "");//合同三级类别名称
                }
                if (crContractbasic.getType4() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
                    if (dictionarycategory != null) {
                        obj.put("type4Name", dictionarycategory.getfName());//合同四级类别名称
                    }
                } else {
                    obj.put("type4Name", "");//合同四级类别名称
                }
                obj.put("moneyFlow", crContractbasic.getMoneyFlow());//资金流向
                if (crContractbasic.getMoneyFlow() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getMoneyFlow());
                    if (dictionarycategory != null) {
                        obj.put("moneyFlowName", dictionarycategory.getfName());//资金流向名称
                    }
                } else {
                    obj.put("moneyFlowName", "");//资金流向名称
                }
                obj.put("node", ContractEnum.EnumSection.Prepare.getMessage());//环节
                obj.put("status", ContractEnum.EnumStatus.TempSave.getMessage());//状态
                obj.put("createBy", crContractbasic.getCreatedBy());//创建人
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getCreatedBy()));
                if (sysUserinfo != null) {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                } else {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                }
                objectList.add(obj);
            }

        }
        return DataResult.success(objectList);
    }

    /**
     *	个人已办合同查询
     */
    @Override
    public DataResult<?> queryContractByUserId(String contractName, String ruleserialNum, Integer type1,String createDateBegin, 
    										   String createDateEnd, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        List<Object> objectList = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, userInfo.getSysUser().getfId());
        queryWrapper.lambda().eq(CrContractbasic::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        if (!StringUtils.isEmpty(contractName)) {
            queryWrapper.lambda().like(CrContractbasic::getContractName, contractName);
        }
        if (!StringUtils.isEmpty(ruleserialNum)) {
            queryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum);
        }
        if (type1 != null) {
            queryWrapper.lambda().like(CrContractbasic::getType1, type1);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(createDateBegin)) {
            queryWrapper.lambda().gt(CrContractbasic::getCreatedDate, LocalDate.parse(createDateBegin, dateTimeFormatter));
        }
        if (!StringUtils.isEmpty(createDateEnd)) {
            queryWrapper.lambda().lt(CrContractbasic::getCreatedDate, LocalDate.parse(createDateEnd, dateTimeFormatter));
        }
        IPage<CrContractbasic> page = new Page<>(pageNum, pageSize);
        IPage<CrContractbasic> crContractbasicIPage = crContractbasicMapper.selectPage(page, queryWrapper);
        if (crContractbasicIPage != null && crContractbasicIPage.getSize() > 0) {
            for (CrContractbasic crContractbasic : crContractbasicIPage.getRecords()) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractId", crContractbasic.getContractID());//合同id
                obj.put("contractName", crContractbasic.getContractName());//合同名称
                obj.put("ruleserialNum", crContractbasic.getRuleSerialNum());//合同序号
                obj.put("type1", crContractbasic.getType1());//合同一级类别
                obj.put("type2", crContractbasic.getType2());//合同二级类别
                obj.put("type3", crContractbasic.getType3());//合同三级类别
                obj.put("type4", crContractbasic.getType4());//合同四级类别
                SysDictionarycategory dictionarycategory = new SysDictionarycategory();
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
                    if (dictionarycategory != null) {
                        obj.put("type1Name", dictionarycategory.getfName());//合同一级类别名称
                    }
                } else {
                    obj.put("type1Name", "");//合同一级类别名称
                }
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
                    if (dictionarycategory != null) {
                        obj.put("type2Name", dictionarycategory.getfName());//合同二级类别名称
                    }
                } else {
                    obj.put("type2Name", "");//合同二级类别名称
                }
                if (crContractbasic.getType3() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
                    if (dictionarycategory != null) {
                        obj.put("type3Name", dictionarycategory.getfName());//合同三级类别名称
                    }
                } else {
                    obj.put("type3Name", "");//合同三级类别名称
                }
                if (crContractbasic.getType4() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
                    if (dictionarycategory != null) {
                        obj.put("type4Name", dictionarycategory.getfName());//合同四级类别名称
                    }
                } else {
                    obj.put("type4Name", "");//合同四级类别名称
                }
                obj.put("moneyFlow", crContractbasic.getMoneyFlow());//资金流向
                if (crContractbasic.getMoneyFlow() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getMoneyFlow());
                    if (dictionarycategory != null) {
                        obj.put("moneyFlowName", dictionarycategory.getfName());//资金流向名称
                    }
                } else {
                    obj.put("moneyFlowName", "");//资金流向名称
                }

                String propertyModel = "";//合同模块
                String section = "";//合同环节
                if (crContractbasic.getPropertyModel() != null) {
                    propertyModel = crContractbasic.getPropertyModel().toString();
                }
                if (crContractbasic.getSection() != null) {
                    section = crContractbasic.getSection().toString();
                }
                if (!StringUtils.isEmpty(propertyModel)) {
                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
                }
                if (!StringUtils.isEmpty(section)) {
                    section = ContractEnum.enumSectionMap.get(section);
                }
                obj.put("propertyModel", propertyModel);//合同模块
                obj.put("section", section);//合同环节
                String status = "";
                if (crContractbasic.getStatus() != null) {
                    status = crContractbasic.getSection().toString();
                }
                if (!StringUtils.isEmpty(status)) {
                    status = ContractEnum.enumStatusMap.get(status);
                }
                obj.put("status", status);//状态
                obj.put("createDate", crContractbasic.getCreatedDate());//创建日期
                obj.put("createBy", crContractbasic.getCreatedBy());//创建人
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getCreatedBy()));
                if (sysUserinfo != null) {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                } else {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                }
                objectList.add(obj);
            }

        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }

    /**
     * 	合同拷贝
     */
    @Override
    @Transactional
    public boolean copyContract(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关数据！", Constants.FAILCODE);
        }
        //拷贝合同基础表
        CrContractbasic crContractbasicNew = new CrContractbasic();
        BeanUtils.copyProperties(crContractbasic, crContractbasicNew);
        crContractbasicNew.setContractID(UUID.randomUUID().toString());
        crContractbasicNew.setContractName(crContractbasic.getContractName() + "副本");
        String ruleSerialNum = "";
        Calendar calendar = Calendar.getInstance();
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = calendar.get(Calendar.YEAR);
        String createDate = year + "-01-01 00:00:00";
        HashMap<String, Long> mapList = crContractbasicMapper.getMaxRuleSerialNum(createDate);//取当年最大的流水号
        if (mapList == null || mapList.size() == 0) {
            ruleSerialNum = Integer.toString(year).substring(2, 4) + "00000001";
            crContractbasicNew.setSerialNum(1L);
        } else {
            Long serialNum = mapList.get("serialNum");
            serialNum++;
            DecimalFormat df = new DecimalFormat("00000000");
            String numNew = df.format(serialNum);
            ruleSerialNum = Integer.toString(year).substring(2, 4) + numNew;
            crContractbasicNew.setSerialNum(serialNum);
        }
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
//        int count = crContractbasicMapper.selectCount(queryWrapper);
//        count++;
//        Long serialNum = (long) count;
//        crContractbasicNew.setSerialNum(serialNum);
        crContractbasicNew.setRuleSerialNum(ruleSerialNum);


        //复制时生成合同编号
        // 企业编码+年度（20）+合同类型（1，2，3）+四位流水号
            String contractNum = "";
            try{
            Integer orgId = 0;
            String orgCode = "";
            SysOrganization sysOrganization = organizationRequest.getOrgCompany(crContractbasic.getMainDeptID());
            if (sysOrganization != null) {
                orgId = sysOrganization.getfId();
                orgCode = sysOrganization.getfCode();
            }
            Calendar calendar1 = Calendar.getInstance();
            Integer year1 = calendar1.get(Calendar.YEAR);
            contractNum = orgCode + "-" + year1.toString().substring(2) + "-";

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
            DecimalFormat df = new DecimalFormat("0000");
            String numNew = df.format(num);
            contractNum += "-" + numNew;
            } catch (Exception ex) {

            }
        crContractbasicNew.setContractNum(contractNum);//合同编码

        crContractbasicNew.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同准备环节
        crContractbasicNew.setSection(Integer.parseInt(ContractEnum.EnumSection.Prepare.getCode()));//合同准备
        crContractbasicNew.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
        crContractbasicNew.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//处理中
        crContractbasicNew.setCheckDate(null);//审核结束时间
        crContractbasicNew.setFinalityDate(null);//合同终结时间
        crContractbasicNew.setCreatedDate(localDateTime);
        crContractbasicMapper.insert(crContractbasicNew);

        //拷贝合同详细表
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        CrContractinfo crContractinfoNew = new CrContractinfo();
        BeanUtils.copyProperties(crContractinfo, crContractinfoNew);
        crContractinfoNew.setContractID(crContractbasicNew.getContractID());
        crContractinfoNew.setCreatedDate(localDateTime);
        crContractinfoMapper.insert(crContractinfoNew);

        //拷贝签约依据
        QueryWrapper<CrContractaccord> crContractaccordQueryWrapper = new QueryWrapper<>();
        crContractaccordQueryWrapper.lambda().eq(CrContractaccord::getContractID, contractId);
        List<CrContractaccord> contractaccordList = crContractaccordMapper.selectList(crContractaccordQueryWrapper);
        if (contractaccordList != null && contractaccordList.size() > 0) {
            for (CrContractaccord crContractaccord : contractaccordList) {
                CrContractaccord crContractaccordNew = new CrContractaccord();
                BeanUtils.copyProperties(crContractaccord, crContractaccordNew);
                crContractaccordNew.setContractID(crContractbasicNew.getContractID());
                crContractaccordNew.setCreatedDate(localDateTime);
                crContractaccordMapper.insert(crContractaccordNew);
            }
        }
        //相对人
        QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
        crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
        if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
            for (CrContractofferee crContractofferee : crContractoffereeList) {
                CrContractofferee crContractoffereeNew = new CrContractofferee();
                BeanUtils.copyProperties(crContractofferee, crContractoffereeNew);
                crContractoffereeNew.setContractID(crContractbasicNew.getContractID());
                crContractoffereeNew.setCreatedDate(localDateTime);
                crContractoffereeMapper.insert(crContractoffereeNew);
            }
        }
        //收付款执行人
        QueryWrapper<CrContractrununit> contractrununitQueryWrapper = new QueryWrapper<>();
        contractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(contractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                CrContractrununit crContractrununitNew = new CrContractrununit();
                BeanUtils.copyProperties(crContractrununit, crContractrununitNew);
                crContractrununitNew.setRunUnitID(UUID.randomUUID().toString());
                crContractrununitNew.setContractID(crContractbasicNew.getContractID());
                crContractrununitMapper.insert(crContractrununitNew);
            }
        }
        //合同文本
        QueryWrapper<CrContracttext> crContracttextQueryWrapper = new QueryWrapper<>();
        crContracttextQueryWrapper.lambda().eq(CrContracttext::getContractID, contractId);
        crContracttextQueryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(crContracttextQueryWrapper);
        if (crContracttextList != null && crContracttextList.size() > 0) {

            CrContracttext crContracttext = new CrContracttext();
            BeanUtils.copyProperties(crContracttextList.get(0), crContracttext);
            crContracttext.setTextID(UUID.randomUUID().toString());
            /*crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));*/
            crContracttext.setIssuer(crContracttextList.get(0).getIssuer());
            crContracttext.setContractID(crContractbasicNew.getContractID());
            crContracttext.setCreatedDate(localDateTime);
            //设置路径
            crContracttextMapper.insert(crContracttext);


            QueryWrapper<SysAttachmentinfo> attachmentinfoQueryWrapper = new QueryWrapper<>();
            attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getPropertyID, crContracttextList.get(0).getTextID());
            attachmentinfoQueryWrapper.lambda().eq(SysAttachmentinfo::getAttachmentTypeName, "contractText");
            List<SysAttachmentinfo> sysAttachmentinfoList = sysAttachmentinfoMapper.selectList(attachmentinfoQueryWrapper);
            if (sysAttachmentinfoList != null && sysAttachmentinfoList.size() > 0) {
                SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfoList.get(0);
                String fileUrl = sysAttachmentinfo.getDocUrl() + sysAttachmentinfo.getAttachmentPath();//合同文本链接
                String extension = sysAttachmentinfo.getExtension();
                try {

                    HttpURLConnection httpURLConnection = null;
                    URL url = new URL(fileUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        int fileLength = inputStream.available();
                        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
                        String contextPath = request.getContextPath();
                        String uploadUrl = "http://localhost:" + currentPort + contextPath + "/attachment/uploadreturndetail";

                        InputStreamResource inputStreamResource = new InputStreamResource(inputStream) {
                            @Override
                            public String getFilename() {
                                return crContractbasicNew.getContractName() + "." + extension;
                            }

                            @Override
                            public long contentLength() throws IOException {
                                return fileLength == 0 ? 1 : fileLength;
                            }
                        };

                        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
                        paramMap.add("file", inputStreamResource);
                        paramMap.add("PropertyID", crContracttext.getTextID());
                        paramMap.add("AttachmentTypeName", "contractText");
                        paramMap.add("AttachmentType", "");
                        paramMap.add("AttachmentName", crContractbasicNew.getContractName());
                        paramMap.add("TypeCode", "");
                        paramMap.add("PropertyModel", "szgt_contract_make");
                        paramMap.add("Section", "szgt_contract_make");
                        paramMap.add("Remark", "合同文本生成");
                        paramMap.add("access_token", RestTemplateUtil.getToken());

                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);
                        HttpEntity<String> result = restTemplate.exchange(uploadUrl, HttpMethod.POST, entity, String.class);
                        String uploadResult = result.getBody();
                    }


                } catch (Exception ex) {

                }


            }


        }

        crContractbasicNew.setSection(Integer.parseInt(ContractEnum.EnumSection.Prepare.getCode()));//合同准备
        crContractbasicNew.setNode(Integer.parseInt(ContractEnum.EnumNode.Draft.getCode()));//合同草稿状态
        crContractbasicNew.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//处理中
        //发送待办数据
        DpsTaskMessage taskMessage = new DpsTaskMessage();
        taskMessage.setBusinessId(crContractbasicNew.getContractID());
        taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
        taskMessage.setExecutorId(crContractbasicNew.getMainOrgUserID());
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasicNew.getMainOrgUserID()));
        if (sysUserinfo != null) {
            taskMessage.setExecutorName(sysUserinfo.getfCname());
            taskMessage.setExecutorCode(sysUserinfo.getfCode());
            taskMessage.setBusinessName(crContractbasic.getContractName());
            taskMessage.setCreatorCode(sysUserinfo.getfCode());
        }
        AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasicNew.getContractID(), crContractbasicNew, crContractinfoNew,
                ContractEnum.EnumModule.Prepare.getCode(), ContractEnum.EnumModule.Prepare.getMessage(), ContractEnum.EnumSection.Prepare.getCode());
        taskMessage.setExtendsData(appExtendsData);//设置扩展字段
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        //插入临时待办消息
        workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);
        return true;
    }

    /**
     * 	框架合同列表
     */
    @Override
    public DataResult<?> getFramContractList(String ruleserialNum, String contractName, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        List<Object> objectList = new ArrayList<>();
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(ruleserialNum)) {
            queryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum);
        }
        if (!StringUtils.isEmpty(contractName)) {
            queryWrapper.lambda().like(CrContractbasic::getContractName, contractName);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();//当前用户//0510
        Integer orgId = userInfo.getUnitId();
       /* if (orgId > 0) {
            queryWrapper.eq("b.FrameOrg", orgId);//当前用户所在单位
        }*/
        /* queryWrapper.lambda().eq(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));*/
        queryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        queryWrapper.eq("a.PropertyModel", Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));
        queryWrapper.eq("b.IsFrameContract", 1);
        IPage<CrContractbasic> page = new Page<>(pageNum, pageSize);
        List<CrContractbasic> list = crContractbasicMapper.getFrameContract(page, queryWrapper);
        if (list != null && list.size() > 0) {
            for (CrContractbasic crContractbasic : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractId", crContractbasic.getContractID());//合同id
                obj.put("contractName", crContractbasic.getContractName());//合同名称
                obj.put("contractNum", crContractbasic.getContractNum());//合同编号
                obj.put("ruleserialNum", crContractbasic.getRuleSerialNum());//合同序号
                obj.put("type1", crContractbasic.getType1());//合同一级类别
                obj.put("type2", crContractbasic.getType2());//合同二级类别
                obj.put("type3", crContractbasic.getType3());//合同三级类别
                obj.put("type4", crContractbasic.getType4());//合同四级类别
                SysDictionarycategory dictionarycategory = new SysDictionarycategory();
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
                    if (dictionarycategory != null) {
                        obj.put("type1Name", dictionarycategory.getfName());//合同一级类别名称
                    }
                } else {
                    obj.put("type1Name", "");//合同一级类别名称
                }
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
                    if (dictionarycategory != null) {
                        obj.put("type2Name", dictionarycategory.getfName());//合同二级类别名称
                    }
                } else {
                    obj.put("type2Name", "");//合同二级类别名称
                }
                if (crContractbasic.getType3() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
                    if (dictionarycategory != null) {
                        obj.put("type3Name", dictionarycategory.getfName());//合同三级类别名称
                    }
                } else {
                    obj.put("type3Name", "");//合同三级类别名称
                }
                if (crContractbasic.getType4() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
                    if (dictionarycategory != null) {
                        obj.put("type4Name", dictionarycategory.getfName());//合同四级类别名称
                    }
                } else {
                    obj.put("type4Name", "");//合同四级类别名称
                }
                obj.put("moneyFlow", crContractbasic.getMoneyFlow());//资金流向
                if (crContractbasic.getMoneyFlow() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getMoneyFlow());
                    if (dictionarycategory != null) {
                        obj.put("moneyFlowName", dictionarycategory.getfName());//资金流向名称
                    }
                } else {
                    obj.put("moneyFlowName", "");//资金流向名称
                }
                String propertyModel = crContractbasic.getPropertyModel().toString();
                if (!StringUtils.isEmpty(propertyModel)) {
                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
                }
                obj.put("propertyModel", propertyModel);//合同模块
                String section = crContractbasic.getSection().toString();
                if (!StringUtils.isEmpty(section)) {
                    section = ContractEnum.enumSectionMap.get(propertyModel);
                }
                obj.put("section", section);//环节

                String status = crContractbasic.getStatus().toString();
                if (!StringUtils.isEmpty(section)) {
                    status = ContractEnum.enumStatusMap.get(propertyModel);
                }
                obj.put("status", status);//状态
                obj.put("createBy", crContractbasic.getCreatedBy());//创建人
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getCreatedBy()));
                if (sysUserinfo != null) {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                }
                objectList.add(obj);
            }
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
        //return DataResult.success(objectList);
    }

    @Override
    public DataResult<?> getMasterContractList(String ruleserialNum, String contractName, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        List<Object> objectList = new ArrayList<>();
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(ruleserialNum)) {
            queryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum);
        }
        if (!StringUtils.isEmpty(contractName)) {
            queryWrapper.lambda().like(CrContractbasic::getContractName, contractName);
        }

        queryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        queryWrapper.ge("a.PropertyModel", Integer.parseInt(ContractEnum.EnumModule.Make.getCode()));
        queryWrapper.le("a.PropertyModel", Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));
        queryWrapper.ge("a.Section", Integer.parseInt(ContractEnum.EnumSection.Sign.getCode()));

        IPage<CrContractbasic> page = new Page<>(pageNum, pageSize);
        List<CrContractbasic> list = crContractbasicMapper.getFrameContract(page, queryWrapper);
        if (list != null && list.size() > 0) {
            for (CrContractbasic crContractbasic : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractId", crContractbasic.getContractID());//合同id
                obj.put("contractName", crContractbasic.getContractName());//合同名称
                obj.put("contractNum", crContractbasic.getContractNum());//合同编号
                obj.put("ruleserialNum", crContractbasic.getRuleSerialNum());//合同序号
                obj.put("type1", crContractbasic.getType1());//合同一级类别
                obj.put("type2", crContractbasic.getType2());//合同二级类别
                obj.put("type3", crContractbasic.getType3());//合同三级类别
                obj.put("type4", crContractbasic.getType4());//合同四级类别
                SysDictionarycategory dictionarycategory = new SysDictionarycategory();
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType1());
                    if (dictionarycategory != null) {
                        obj.put("type1Name", dictionarycategory.getfName());//合同一级类别名称
                    }
                } else {
                    obj.put("type1Name", "");//合同一级类别名称
                }
                if (crContractbasic.getType2() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType2());
                    if (dictionarycategory != null) {
                        obj.put("type2Name", dictionarycategory.getfName());//合同二级类别名称
                    }
                } else {
                    obj.put("type2Name", "");//合同二级类别名称
                }
                if (crContractbasic.getType3() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType3());
                    if (dictionarycategory != null) {
                        obj.put("type3Name", dictionarycategory.getfName());//合同三级类别名称
                    }
                } else {
                    obj.put("type3Name", "");//合同三级类别名称
                }
                if (crContractbasic.getType4() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getType4());
                    if (dictionarycategory != null) {
                        obj.put("type4Name", dictionarycategory.getfName());//合同四级类别名称
                    }
                } else {
                    obj.put("type4Name", "");//合同四级类别名称
                }
                obj.put("moneyFlow", crContractbasic.getMoneyFlow());//资金流向
                if (crContractbasic.getMoneyFlow() != null) {
                    dictionarycategory = dictionaryRequest.queryCategoryById(crContractbasic.getMoneyFlow());
                    if (dictionarycategory != null) {
                        obj.put("moneyFlowName", dictionarycategory.getfName());//资金流向名称
                    }
                } else {
                    obj.put("moneyFlowName", "");//资金流向名称
                }
                String propertyModel = crContractbasic.getPropertyModel().toString();
                if (!StringUtils.isEmpty(propertyModel)) {
                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
                }
                obj.put("propertyModel", propertyModel);//合同模块
                String section = crContractbasic.getSection().toString();
                if (!StringUtils.isEmpty(section)) {
                    section = ContractEnum.enumSectionMap.get(propertyModel);
                }
                obj.put("section", section);//环节

                String status = crContractbasic.getStatus().toString();
                if (!StringUtils.isEmpty(section)) {
                    status = ContractEnum.enumStatusMap.get(propertyModel);
                }
                obj.put("status", status);//状态
                obj.put("createBy", crContractbasic.getCreatedBy());//创建人
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getCreatedBy()));
                if (sysUserinfo != null) {
                    obj.put("createByName", sysUserinfo.getfCname());//创建人
                }
                objectList.add(obj);
            }
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);

    }

    /**
     * 	签约依据合同关联查询
     */
    @Override
    public DataResult<?> queryContractAccord(Integer mainDept, String ruleserialNum, String contractName, String contractNum, String accordCode, String accordName,
                                          String accordType, String isEabled, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (ruleserialNum != null && !StringUtils.isEmpty(ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum.trim());
        }
        if (contractName != null && !StringUtils.isEmpty(contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractName.trim());
        }
        if (contractNum != null && !StringUtils.isEmpty(contractNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractNum.trim());
        }
        if (mainDept != null && !StringUtils.isEmpty(mainDept)) {
            List<Integer> maindeptId = new ArrayList<>();
            maindeptId.add(mainDept);
            //获取下级单位
            List<SysOrganization> organizationList = organizationRequest.queryAllSubOrgs(mainDept);
            if (organizationList != null && organizationList.size() > 0) {
                for (SysOrganization sysOrganization : organizationList) {
                    maindeptId.add(sysOrganization.getfId());
                }
            }
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, maindeptId);
        }
        if (accordCode != null && !StringUtils.isEmpty(accordCode)) {
            crContractbasicQueryWrapper.like("d.Acode", accordCode.trim());
        }
        if (accordName != null && !StringUtils.isEmpty(accordName)) {
            crContractbasicQueryWrapper.like("d.AccordingName", accordName.trim());
        }
        if (accordType != null && !StringUtils.isEmpty(accordType)) {
            crContractbasicQueryWrapper.eq("d.AccordingType", accordType.trim());
        }

        //20200809 增加公开范围
        List<Integer> userOrgIds = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //当前用户所在组织机构
        List<SysOrganization> sysOrganizationList = currentUserUtil.currentUserInfo().getSysOrgList();
        if (sysOrganizationList != null && sysOrganizationList.size() > 0) {
            for (SysOrganization sysOrganization : sysOrganizationList) {
                if (!userOrgIds.contains(sysOrganization.getfId())) {
                    userOrgIds.add(sysOrganization.getfId());
                }
                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(sysOrganization.getfId());
                if (childOrgList != null && childOrgList.size() > 0) {
                    for (SysOrganization childDept : childOrgList) {
                        if (!userOrgIds.contains(childDept.getfId())) {
                            userOrgIds.add(childDept.getfId());
                        }

                    }
                }
            }

        }
        crContractbasicQueryWrapper.in("d.OrgID", userOrgIds);//


        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//有效合同
        crContractbasicQueryWrapper.eq("c.Kind", Constants.HandWorkAccord);//手工签约依据
        crContractbasicQueryWrapper.eq("c.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//
        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");

        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractbasicMapper.queryContractAccord(page, crContractbasicQueryWrapper);

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
                obj.put("contractName", map.get("ContractName"));//合同名称
                obj.put("contractNum", map.get("ContractNum"));//合同编码
                obj.put("type1", map.get("Type1"));//合同类型1
                String type1Name = "";
                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type1").toString()));
                    if (sysDictionary != null) {
                        type1Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type1Name", type1Name);//合同类型1名称

                obj.put("type2", map.get("Type2"));//合同类型2
                String type2Name = "";
                if (map.get("Type2") != null && !StringUtils.isEmpty(map.get("Type2"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type2").toString()));
                    if (sysDictionary != null) {
                        type2Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type2Name", type2Name);//合同类型2名称


                obj.put("type3", map.get("Type3"));//合同类型3
                String type3Name = "";
                if (map.get("Type3") != null && !StringUtils.isEmpty(map.get("Type3"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type3").toString()));
                    if (sysDictionary != null) {
                        type3Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type3Name", type3Name);//合同类型3名称


                obj.put("type4", map.get("Type4"));//合同类型4
                String type4Name = "";
                if (map.get("Type4") != null && !StringUtils.isEmpty(map.get("Type4"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type4").toString()));
                    if (sysDictionary != null) {
                        type4Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type4Name", type4Name);//合同类型4名称


                obj.put("moneyFlow", map.get("MoneyFlow"));//资金流向id
                String moneyFlowName = "";
                if (map.get("MoneyFlow") != null && !StringUtils.isEmpty(map.get("MoneyFlow"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
                    if (sysDictionary != null) {
                        moneyFlowName = sysDictionary.getfCnName();
                    }
                }
                obj.put("moneyFlowName", moneyFlowName);//资金流向名称
                obj.put("createdBy", map.get("MainOrgUserID"));//经办人id
                String createdByName = "";
                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
                    if (sysUserinfo != null) {
                        createdByName = sysUserinfo.getfCname();
                    }
                }
                obj.put("createdByName", createdByName);//经办人名称

                obj.put("accordCode", map.get("ACode"));//签约依据编号
                obj.put("accordName", map.get("AccordingName"));//签约依据名称
                obj.put("accordType", map.get("AccordingType"));//签约依据类型
                String accordTypeName = "";
                if (map.get("AccordingType") != null && !StringUtils.isEmpty(map.get("AccordingType"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("AccordingType").toString()));
                    if (sysDictionary != null) {
                        accordTypeName = sysDictionary.getfCnName();
                    }
                }
                obj.put("AccordingTypeName", accordTypeName);//签约依据类型中文名

                obj.put("createdDate", map.get("CreatedDate"));//主办时间
                objectList.add(obj);
            }
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }

    /**
     * 	项目合同关联查询
     */
    @Override
    public DataResult<?> queryContractProject(Integer mainDept, String ruleserialNum, String contractName, String contractNum,
                                           String projectName, Integer atYear, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (ruleserialNum != null && !StringUtils.isEmpty(ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, ruleserialNum.trim());
        }
        if (contractName != null && !StringUtils.isEmpty(contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractName.trim());
        }
        if (contractNum != null && !StringUtils.isEmpty(contractNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractNum.trim());
        }
        if (mainDept != null && !StringUtils.isEmpty(mainDept)) {
            List<Integer> maindeptId = new ArrayList<>();
            maindeptId.add(mainDept);
            //获取下级单位
            List<SysOrganization> organizationList = organizationRequest.queryAllSubOrgs(mainDept);
            if (organizationList != null && organizationList.size() > 0) {
                for (SysOrganization sysOrganization : organizationList) {
                    maindeptId.add(sysOrganization.getfId());
                }
            }
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, maindeptId);
        }
        if (projectName != null && !StringUtils.isEmpty(projectName)) {
            crContractbasicQueryWrapper.like("c.projectName", projectName.trim());
        }
        if (atYear != null && !StringUtils.isEmpty(atYear)) {
            crContractbasicQueryWrapper.eq("c.atYear", atYear);
        }


        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//有效合同
        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");

        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractbasicMapper.queryContractProject(page, crContractbasicQueryWrapper);

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
                obj.put("contractName", map.get("ContractName"));//合同名称
                obj.put("contractNum", map.get("ContractNum"));//合同编码
                obj.put("type1", map.get("Type1"));//合同类型1
                String type1Name = "";
                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type1").toString()));
                    if (sysDictionary != null) {
                        type1Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type1Name", type1Name);//合同类型1名称

                obj.put("type2", map.get("Type2"));//合同类型2
                String type2Name = "";
                if (map.get("Type2") != null && !StringUtils.isEmpty(map.get("Type2"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type2").toString()));
                    if (sysDictionary != null) {
                        type2Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type2Name", type2Name);//合同类型2名称


                obj.put("type3", map.get("Type3"));//合同类型3
                String type3Name = "";
                if (map.get("Type3") != null && !StringUtils.isEmpty(map.get("Type3"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type3").toString()));
                    if (sysDictionary != null) {
                        type3Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type3Name", type3Name);//合同类型3名称


                obj.put("type4", map.get("Type4"));//合同类型4
                String type4Name = "";
                if (map.get("Type4") != null && !StringUtils.isEmpty(map.get("Type4"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type4").toString()));
                    if (sysDictionary != null) {
                        type4Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type4Name", type4Name);//合同类型4名称


                obj.put("moneyFlow", map.get("MoneyFlow"));//资金流向id
                String moneyFlowName = "";
                if (map.get("MoneyFlow") != null && !StringUtils.isEmpty(map.get("MoneyFlow"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
                    if (sysDictionary != null) {
                        moneyFlowName = sysDictionary.getfCnName();
                    }
                }
                obj.put("moneyFlowName", moneyFlowName);//资金流向名称
                obj.put("createdBy", map.get("MainOrgUserID"));//经办人id
                String createdByName = "";
                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
                    if (sysUserinfo != null) {
                        createdByName = sysUserinfo.getfCname();
                    }
                }
                obj.put("createdByName", createdByName);//经办人名称
                obj.put("projectId", map.get("CRProjectID"));//项目id
                obj.put("projectCode", map.get("CRProjectCode"));//项目编号
                obj.put("projectName", map.get("CRProjectName"));//项目名称
                Object CRAtYear = map.get("CRAtYear");
                obj.put("atYear", CRAtYear);//立项年度
                obj.put("execOrgan", map.get("CRExecOrgan"));//建设单位
                obj.put("investAmount", map.get("CRInvestAmount"));//项目总投资金额
                obj.put("createdDate", map.get("CreatedDate"));//主办时间
                objectList.add(obj);
            }
        }
        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }

    /**
     * 	所有部门标准文本使用率分页查询
     * @param ruleSerialNum
     * @param contractNum
     * @param contractName
     * @param mainDeptID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageData<StdTextUseRateVo> queryStdTextUseRate(String ruleSerialNum, String contractNum, String contractName, String mainDeptID, Integer pageNum, Integer pageSize) {

        Page<StdTextCnt> page = new Page<>(pageNum, pageSize);

        List<StdTextCnt> stdTextCnts = crContracttextMapper.queryStdTextCnt(page, ruleSerialNum, contractNum, contractName, mainDeptID);
        List<StdTextCnt> allTextCnts = crContracttextMapper.queryAllTextCnt(ruleSerialNum, contractNum, contractName, mainDeptID);
        Map<Integer, StdTextCnt> allTextCntMap = allTextCnts.stream().collect(Collectors.toMap(StdTextCnt::getMainDeptID, Function.identity()));

        Map<Integer, String> orgMap = new HashMap<>();
        List<StdTextUseRateVo> resList = new ArrayList<>();
        for(StdTextCnt stdTextCnt : stdTextCnts){
            StdTextUseRateVo stdTextUseRateVo = new StdTextUseRateVo();
            if(!orgMap.containsKey(stdTextCnt.getMainDeptID())){
                SysOrganization sysOrganization = organizationRequest.queryOrganization(stdTextCnt.getMainDeptID());
                if(sysOrganization != null){
                    orgMap.put(stdTextCnt.getMainDeptID(), sysOrganization.getfName());
                } else {
                    orgMap.put(stdTextCnt.getMainDeptID(), "");
                }
            }

            if(!orgMap.containsKey(stdTextCnt.getMainOrgID())){
                SysOrganization sysOrganization = organizationRequest.queryOrganization(stdTextCnt.getMainOrgID());
                if(sysOrganization != null){
                    orgMap.put(stdTextCnt.getMainOrgID(), sysOrganization.getfName());
                } else {
                    orgMap.put(stdTextCnt.getMainOrgID(), "");
                }
            }

            String deptName = orgMap.get(stdTextCnt.getMainDeptID());
            String unitName = orgMap.get(stdTextCnt.getMainOrgID());

            String orgPath = "";
            if(!StringUtils.isEmpty(unitName) && !StringUtils.isEmpty(deptName) && !unitName.equals(deptName)){
                orgPath = unitName + "/" + deptName;
            } else if(!StringUtils.isEmpty(unitName)){
                orgPath = unitName;
            } else if(!StringUtils.isEmpty(deptName)){
                orgPath = deptName;
            }

            stdTextUseRateVo.setOrgName(orgPath);
            stdTextUseRateVo.setStdCnt(stdTextCnt.getCnt());
            StdTextCnt allTextCnt = allTextCntMap.get(stdTextCnt.getMainDeptID());
            if(allTextCnt == null){
                stdTextUseRateVo.setTotalCnt(0);
            }else{
                stdTextUseRateVo.setTotalCnt(allTextCnt.getCnt());
            }

            if(stdTextUseRateVo.getStdCnt() == 0 || stdTextUseRateVo.getTotalCnt() == 0){
                stdTextUseRateVo.setRate("0%");
            }else{
                stdTextUseRateVo.setRate(String.format("%.2f", (double)stdTextUseRateVo.getStdCnt() * 100/stdTextUseRateVo.getTotalCnt()) + "%");
            }

            resList.add(stdTextUseRateVo);
        }

        PageData<StdTextUseRateVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(resList);

        return pageData;
    }

    /**
     * 	当前用户所在单位的标准文本使用率
     * @return
     */
    @Override
    public Map<String, String> queryUnitStdTextUseRate() {

        Map<String, String> data = new HashMap<>();

        List<StdTextCnt> stdTextCnts = crContracttextMapper.queryStdTextCnt(null, null, null, null, null);
        List<StdTextCnt> allTextCnts = crContracttextMapper.queryAllTextCnt(null, null, null, null);

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        int stdSum = stdTextCnts.stream().filter(i -> i.getMainOrgID().equals(userInfo.getUnitId())).mapToInt(StdTextCnt::getCnt).sum();
        int allSum = allTextCnts.stream().filter(i -> i.getMainOrgID().equals(userInfo.getUnitId())).mapToInt(StdTextCnt::getCnt).sum();

        data.put("orgName", userInfo.getUnitName());
        if(stdSum == 0 || allSum == 0){
            data.put("rate", "0%");
        }else{
            data.put("rate", String.format("%.2f", (double)stdSum * 100/allSum) + "%");
        }

        return data;
    }

    /**
     * 	个人工作助理--办公代理
     * 	添加代理记录
     *  @param proxyUserId 代理人ID
     *  @param startTime  开始时间
     *  @param endTime  结束时间
     */
    public DataResult<?>  createOfficeAgents(String proxyUserId,String startTime,String endTime) {
        //代工办理信息插入
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrOfficeAgents agents = new CrOfficeAgents();
        agents.setOperationId(userInfo.getSysUser().getfId());
        agents.setAgentId(Integer.parseInt(proxyUserId));
        agents.setStartTime(startTime);
        agents.setEndTime(endTime);
        agents.setAgentStatus(Integer.parseInt(ContractEnum.EnumOfficeAgents.ING.getCode()));

        return agentRequest.createOfficeAgents(agents);
    }
    /**
     * 	个人工作助理--办公代理列表
     * @param pageSize 个数
     * @param pageNum  页数
     */
    @Override
    public CrOfficeAgentsList queryOfficeAgentsList(Integer pageSize, Integer pageNum){
    	UserInfo userInfo = currentUserUtil.currentUserInfo();
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        Map<String,Object> mapparam = new HashMap<>();
        mapparam.put("page", pageNum);
        mapparam.put("size", pageSize);
        mapparam.put("operationId",userInfo.getSysUser().getfId());
        mapparam.put("agentStatus",0);
        CrOfficeAgentsList pagedList = agentRequest.queryOfficeAgentsList(mapparam);
        List<CrOfficeAgentsVo> list = new ArrayList<>();
        if(pagedList.getRecords()!=null)
        pagedList.getRecords().stream().forEach(agent->{
        	list.add(queryAgentById(agent));
        });
        pagedList.setRecords(list);
        return pagedList;
    }
    public CrOfficeAgentsVo queryAgentById(CrOfficeAgentsVo agentVo) {
    	SysUserinfo sysUserinfo = userRequest.queryById(agentVo.getAgentId());
    	agentVo.setAgentAccount(sysUserinfo.getfAccount());
    	List<SysOrganization> userOrgList = currentUserUtil.queryOrganizationById(agentVo.getAgentId().toString());
    	if(userOrgList!=null && userOrgList.size()>0) {
	    	StringBuffer buffer = new StringBuffer();
	    	userOrgList.stream().forEach(v -> {
	    		buffer.append(v.getfName()).append(",") ;
	    	});
	    	agentVo.setAgentOrg(buffer.substring(0, buffer.toString().length() - 1));
    	}
    	agentVo.setAgentName(sysUserinfo.getfCname());
    	if(!StringUtils.isEmpty(agentVo.getCreateTime())) {
    		agentVo.setCreateTime(agentVo.getCreateTime().replace("T", " "));
    	}
    	return agentVo ;
    }
    
    /**
     * 	办公代理根据参数修改状态
     * @param agentId
     * @return
     */
    public boolean updateAgentStatusByAgentId(Integer agentId,Integer status) {
    	return agentRequest.updateAgentStatusByAgentId(agentId,status);
    }
    
    /**
     * 	合同-代办转交
     * @param serialNum     合同序号
     * @param primitiveUser 原办理人
     * @param transferUser  新办理人
     * @return
     */
    public DataResult<?> contractChangeDispose(String serialNum,String primitiveUser,String transferUser) {
    	if(StringUtils.isEmpty(primitiveUser)) {
    		throw new NotFoundException("原办理人ID为空", Constants.FAILCODE);
    	}
    	if(StringUtils.isEmpty(transferUser)) {
    		throw new NotFoundException("新办理人ID为空", Constants.FAILCODE);
    	}
    	AppCallResult result = null ;
        SysUserinfo sysUserinfo = userRequest.queryById(Integer.parseInt(transferUser));
        if(sysUserinfo!=null) {
        	List<String> list = new ArrayList<>();
        	//获取待办列表数据
        	PagedList  todoList = workFlowService.taskToDo("", "", "", serialNum, "", 10, 1, 
        			false,Integer.parseInt(primitiveUser));
        	
            if (todoList.getExecuteTaskList() != null && todoList.getExecuteTaskList().size() > 0) {
                for (ExecuteTaskData executeTaskData : todoList.getExecuteTaskList()) {
                  list.add(executeTaskData.getTaskId());

                }
                result = dpsRequest.transferByTask(list, transferUser, sysUserinfo.getfCname());
                if(result.getResult()){
                    for(String s:list){
                        dpsRequest.oaTaskDel(s);
                        dpsRequest.sendOaTask(s);
                    }
                }

            }else {
            	throw new NotFoundException("待办消息不存在！", Constants.FAILCODE);
            }
        }else {
        	throw new NotFoundException("新办理人不存在！", Constants.FAILCODE);
        }
    	return DataResult.success(result) ;
    }

}
