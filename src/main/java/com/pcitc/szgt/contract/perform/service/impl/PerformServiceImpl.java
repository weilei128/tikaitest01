package com.pcitc.szgt.contract.perform.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.pcitc.szgt.contract.perform.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.pcitc.szgt.contract.appmanager.service.UnitConfigService;
import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.attachment.mapper.SysAttachmentinfoMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.common.enums.FinancialEnum;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.finality.entity.CrContractcase;
import com.pcitc.szgt.contract.finality.mapper.CrContractcaseMapper;
import com.pcitc.szgt.contract.interactive.entity.CrExecutepayment;
import com.pcitc.szgt.contract.interactive.mapper.CrExecutepaymentMapper;
import com.pcitc.szgt.contract.interactive.model.ContractInfo;
import com.pcitc.szgt.contract.interactive.service.FinancialService;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.entity.CrContractofferee;
import com.pcitc.szgt.contract.make.entity.CrContractrununit;
import com.pcitc.szgt.contract.make.entity.CrContracttext;
import com.pcitc.szgt.contract.make.entity.CrProjectinfo;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractoffereeMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractrununitMapper;
import com.pcitc.szgt.contract.make.mapper.CrContracttextMapper;
import com.pcitc.szgt.contract.make.mapper.CrProjectinfoMapper;
import com.pcitc.szgt.contract.make.service.IMakeService;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeinfoMapper;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.entity.CrContractchangebiddetail;
import com.pcitc.szgt.contract.perform.entity.CrContractchangedetail;
import com.pcitc.szgt.contract.perform.entity.CrContractchangeofferee;
import com.pcitc.szgt.contract.perform.entity.CrContractend;
import com.pcitc.szgt.contract.perform.entity.CrContractpayalert;
import com.pcitc.szgt.contract.perform.entity.CrContracttransfer;
import com.pcitc.szgt.contract.perform.entityEx.ContractChange;
import com.pcitc.szgt.contract.perform.entityEx.ContractChangeBid;
import com.pcitc.szgt.contract.perform.entityEx.ContractEnd;
import com.pcitc.szgt.contract.perform.entityEx.ContractQuery;
import com.pcitc.szgt.contract.perform.entityEx.ContractSeal;
import com.pcitc.szgt.contract.perform.entityEx.ContractTrans;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangeMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangebiddetailMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangedetailMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractchangeoffereeMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractendMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContractpayalertMapper;
import com.pcitc.szgt.contract.perform.mapper.CrContracttransferMapper;
import com.pcitc.szgt.contract.perform.service.IPerformService;
import com.pcitc.szgt.contract.share.entity.SysDictionary;
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
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;

import static org.apache.commons.lang3.StringUtils.substring;

/**
 * <p>
 * 项目信息管理
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
@Service
@Slf4j
public class PerformServiceImpl implements IPerformService {
    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;
    @Autowired
    private CrContractinfoMapper crContractinfoMapper;
    @Autowired
    private CrContractrununitMapper crContractrununitMapper;
    /**
     * 办公代理
     */
    @Autowired
    private OfficeAgentRequest agentRequest;
    /**
     * 合同变更
     */
    @Autowired
    private CrContractchangeMapper crContractchangeMapper;
    /*
     * 合同变更明细表
     * */
    @Autowired
    private CrContractchangedetailMapper crContractchangedetailMapper;
    /*
     * 相对人变更记录
     * */
    @Autowired
    private CrContractchangeoffereeMapper crContractchangeoffereeMapper;
    /*
     * 相对人
     * */
    @Autowired
    private FfOffereeinfoMapper ffOffereeinfoMapper;
    /*
     * 合同相对人
     * */
    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;
    /*
     * 合同变更明细
     * */

    @Autowired
    private CrContractchangebiddetailMapper crContractchangebiddetailMapper;
    /*
     * 合同文本模板
     * */
    @Autowired
    private CrContracttextmodelMapper crContracttextmodelMapper;
    /*
     * 合同转让
     * */
    @Autowired
    private CrContracttransferMapper crContracttransferMapper;
    /*
     * 合同终止
     * */
    @Autowired
    private CrContractendMapper crContractendMapper;

    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;
    /*
     * 组织机构
     * */
    @Autowired
    private OrganizationRequest organizationRequest;
    @Autowired
    private DictionaryRequest dictionaryRequest;
    /*
     * 用户管理
     * */
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Autowired
    private DpsRequest dpsRequest;
    @Autowired
    private CrContracttextMapper crContracttextMapper;
    @Autowired
    private SysAttachmentinfoMapper sysAttachmentinfoMapper;
    /*
     * 发案记录
     * */
    @Autowired
    private CrContractcaseMapper crContractcaseMapper;
    /*
     * 工作流服务
     * */
    @Autowired
    private IWorkFlowService workFlowService;
    /*
     * 项目信息
     * */
    @Autowired
    private CrProjectinfoMapper crProjectinfoMapper;

    @Autowired
    private AmQuerylicenseMapper amQuerylicenseMapper;
    @Autowired
    private SysOrganiseunitSingingMapper sysOrganiseunitSingingMapper;

    @Autowired
    private CrContractpayalertMapper contractpayalertMapper;

    @Autowired
    private FinancialService financialService;

    @Autowired
    private IMakeService iMakeService;

    @Autowired
    private CrExecutepaymentMapper executepaymentMapper;

    @Autowired
    private UnitConfigService unitConfigServicel;

    /*
     * 合同是否继续履行
     * */
    @Override
    public boolean isPerformContract(String contractId) {

        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        //合同是否正在变更
        QueryWrapper<CrContractchange> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
     /*   queryWrapper.lambda().ne(CrContractchange::getState, ContractEnum.EnumStatus.Handing.getCode());
        queryWrapper.lambda().ne(CrContractchange::getState, ContractEnum.EnumStatus.TempSave.getCode());//提交或草稿箱状态*/
        queryWrapper.lambda().ne(CrContractchange::getState, ContractEnum.EnumStatus.Approved.getCode());
        List<CrContractchange> crContractchangeList = crContractchangeMapper.selectList(queryWrapper);
        if (crContractchangeList != null && crContractchangeList.size() > 0) {
            return false;
        }
        //合同是否存在转让
        QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
        contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
      /*  contracttransferQueryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.Handing.getCode());
        contracttransferQueryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.TempSave.getCode());//提交或草稿箱状态*/
        contracttransferQueryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.Approved.getCode());
        List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
        if (crContracttransferList.size() > 0) {
            return false;
        }
        //合同终止
        QueryWrapper<CrContractend> contractendQueryWrapper = new QueryWrapper<>();
        contractendQueryWrapper.lambda().eq(CrContractend::getContractID, contractId);
        contractendQueryWrapper.lambda().isNotNull(true, CrContractend::getEffectiveType);//终止签署完成，可以继续履行 update byzhouziran 20200622
        List<CrContractend> crContractendList = crContractendMapper.selectList(contractendQueryWrapper);
        if (crContractendList.size() > 0) {
            return false;
        }
        //合同发案
        QueryWrapper<CrContractcase> crContractcaseQueryWrapper = new QueryWrapper<>();
        crContractcaseQueryWrapper.lambda().eq(CrContractcase::getContractID, contractId);
        crContractcaseQueryWrapper.lambda().eq(CrContractcase::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//
        List<CrContractcase> crContractpauseList = crContractcaseMapper.selectList(crContractcaseQueryWrapper);
        if (crContractpauseList != null && crContractpauseList.size() > 0) {
            return false;
        }
        //合同履行完毕
        if (crContractbasic.getPropertyModel() == Integer.parseInt(ContractEnum.EnumModule.Finality.getCode())
                && crContractbasic.getSection() == Integer.parseInt(ContractEnum.EnumSection.Finality.getCode())) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    /**
     * 	合同转交
     * @param   contractId
     * @param   performUser
     */
    public boolean contractForward(String contractId, String performUser, String receivePayUser, String finalityUser) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        if (crContractbasic.getMainOrgUserID().equals(performUser)) {
            throw new NotFoundException("原经办人和履行经办人不能相同!", Constants.FAILCODE);
        }
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                crContractrununit.setUserID(performUser);
                List<Integer> orgIds = userInfoRequest.queryOrgs(performUser);
                if (orgIds != null && orgIds.size() > 0) {
                    crContractrununit.setOrgID(orgIds.get(0));//取用户所在部门
                }
                if (!StringUtils.isEmpty(receivePayUser)) {
                    crContractrununit.setPayUserId(receivePayUser);//合同付款人
                    orgIds = userInfoRequest.queryOrgs(receivePayUser);
                    if (orgIds != null && orgIds.size() > 0) {
                        crContractrununit.setPayUserOrg(orgIds.get(0));//取用户所在部门
                    }
                } else {

                }


                crContractrununit.setFinalityUserId(finalityUser);//合同终结人
                orgIds = userInfoRequest.queryOrgs(finalityUser);
                if (orgIds != null && orgIds.size() > 0) {
                    crContractrununit.setFinalityUserOrg(orgIds.get(0));//取用户所在部门
                }

                crContractrununitMapper.updateById(crContractrununit);
            }

        } else {
            CrContractrununit crContractrununit = new CrContractrununit();
            crContractrununit.setRunUnitID(UUID.randomUUID().toString());
            crContractrununit.setContractID(contractId);
            crContractrununit.setIsFrameContract(crContractinfo.getIsFrameContract());
            crContractrununit.setFrameOrg(crContractbasic.getMainDeptID());
            crContractrununit.setOulabel(crContractbasic.getOulabel());
            crContractrununit.setUserID(performUser);//合同履行人
            List<Integer> orgIds = userInfoRequest.queryOrgs(performUser);
            if (orgIds != null && orgIds.size() > 0) {
                crContractrununit.setOrgID(orgIds.get(0));//取用户所在部门
            }
            if (!StringUtils.isEmpty(receivePayUser)) {
                crContractrununit.setPayUserId(receivePayUser);//合同付款人
                orgIds = userInfoRequest.queryOrgs(receivePayUser);
                if (orgIds != null && orgIds.size() > 0) {
                    crContractrununit.setPayUserOrg(orgIds.get(0));//取用户所在部门
                }
            } else {

            }

            crContractrununit.setFinalityUserId(finalityUser);
            orgIds = userInfoRequest.queryOrgs(finalityUser);//合同终结人
            if (orgIds != null && orgIds.size() > 0) {
                crContractrununit.setPayUserOrg(orgIds.get(0));//取用户所在部门
            }
            crContractrununitMapper.insert(crContractrununit);
        }
        //合同履行人 0812
        if (!StringUtils.isEmpty(performUser)) {
            List<String> taskIds = new ArrayList<>();
            //调整变更待办事项
            QueryWrapper<CrContractchange> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
            queryWrapper.lambda().ne(CrContractchange::getState, ContractEnum.EnumStatus.Approved.getCode());
            List<CrContractchange> crContractchangeList = crContractchangeMapper.selectList(queryWrapper);
            if (crContractchangeList != null && crContractchangeList.size() > 0) {
                for (CrContractchange crContractchange : crContractchangeList) {
                    PagedList pagedList = dpsRequest.todotaskByBusinessId(crContractchange.getContractChangeID());
                    if (pagedList != null && pagedList.getExecuteTaskList() != null && pagedList.getExecuteTaskList().size() > 0) {
                        for (ExecuteTaskData executeTaskData : pagedList.getExecuteTaskList()) {
                            taskIds.add(executeTaskData.getTaskId());
                        }
                    }
                }
            }
            //调整转让待办事项
            QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
            contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
            contracttransferQueryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.Approved.getCode());
            List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
            if (crContracttransferList != null && crContracttransferList.size() > 0) {
                for (CrContracttransfer crContracttransfer : crContracttransferList) {
                    PagedList pagedList = dpsRequest.todotaskByBusinessId(crContracttransfer.getTransferId());
                    if (pagedList != null && pagedList.getExecuteTaskList() != null && pagedList.getExecuteTaskList().size() > 0) {
                        for (ExecuteTaskData executeTaskData : pagedList.getExecuteTaskList()) {
                            taskIds.add(executeTaskData.getTaskId());
                        }
                    }
                }
            }
            //调整合同终止待办事项
            QueryWrapper<CrContractend> contractendQueryWrapper = new QueryWrapper<>();
            contractendQueryWrapper.lambda().eq(CrContractend::getContractID, contractId);
            contractendQueryWrapper.lambda().isNotNull(true, CrContractend::getEffectiveType);//终止签署完成，可以继续履行 update byzhouziran 20200622
            List<CrContractend> crContractendList = crContractendMapper.selectList(contractendQueryWrapper);
            if (crContractendList != null && crContractendList.size() > 0) {
                for (CrContractend crContractend : crContractendList) {
                    PagedList pagedList = dpsRequest.todotaskByBusinessId(crContractend.getContractEndID());
                    if (pagedList != null && pagedList.getExecuteTaskList() != null && pagedList.getExecuteTaskList().size() > 0) {
                        for (ExecuteTaskData executeTaskData : pagedList.getExecuteTaskList()) {
                            taskIds.add(executeTaskData.getTaskId());
                        }
                    }
                }
            }
            if (taskIds.size() > 0) {
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(performUser));
                dpsRequest.transferByTask(taskIds, performUser, sysUserinfo != null ? sysUserinfo.getfCname() : "");
            }


        }
        //合同终结人
        if (!StringUtils.isEmpty(finalityUser)) {

        }

        List<String> users = new ArrayList<>();

        users.add(performUser);
        //排除相同用户
        if (!users.contains(receivePayUser)) {
            users.add(receivePayUser);
        }
        if (!users.contains(finalityUser)) {
            users.add(finalityUser);
        }

        SysUserinfo creator = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));

        for (String user : users) {
            //发送待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(contractId);
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            /*  taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());*/
            taskMessage.setExecutorId(user);
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(user));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractbasic.getContractName());
                taskMessage.setCreatorCode(creator.getfCode());
            }
            AppExtendsData appExtendsData = new AppExtendsData();

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
            appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节
            appExtendsData.setExt020(ContractEnum.EnumSection.Perform.getCode());//履行环节*/

            //合同履行
            appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());
            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Make.getCode(), sysUserinfo);//插入临时消息
        }
        return true;
    }

    /**
     * 合同转交状态
     *
     * @return
     */
    public CrContractrununit contractForwardStatus(String contractId) {
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);

        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (CollectionUtils.isEmpty(crContractrununitList)) {
            UserInfo userInfo = currentUserUtil.currentUserInfo();

            CrContractrununit crContractrununit = new CrContractrununit();
            crContractrununit.setPayUserId(userInfo.getSysUser().getfId().toString());
            crContractrununit.setFinalityUserId(userInfo.getSysUser().getfId().toString());
            crContractrununit.setUserID(userInfo.getSysUser().getfId().toString());

            return crContractrununit;
        } else {
            return crContractrununitList.get(0);
        }
    }

    /*
     * 是否启用变更流程
     * */
    @Override
    public boolean isExistChangeFlow(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        //履行经办人所在单位/企业
        Integer orgId = 0;
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                orgId = crContractrununit.getOrgID();
                break;
            }

        } else {
            orgId = crContractbasic.getMainDeptID();
        }
        // 获取orgId信息，找所在企业/单位信息
        SysOrganization orgCompany = organizationRequest.getOrgCompany(orgId);
        if (orgCompany != null) {

        }
        //判断是否启用变更流程
        return true;
    }


    /*
     * 合同是否变更
     * */
    @Override
    public boolean contractIsChange(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
        crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
        crContractchangeQueryWrapper.lambda().or().eq(CrContractchange::getState, ContractEnum.EnumStatus.Handing.getCode())
                .or().eq(CrContractchange::getState, ContractEnum.EnumStatus.TempSave.getCode());//提交或草稿箱状态
        int count = crContractchangeMapper.selectCount(crContractchangeQueryWrapper);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /*
     * 获取变更信息by  contractId
     * */
    @Override
    public DataResult<?> getContractChange(String ContractID) {
        List<GetContractChangeVo> getContractChangeVoList = crContractchangeMapper.getContractChange(ContractID);
        List<Object> list = new ArrayList<>();

        if (getContractChangeVoList != null) {
            for (GetContractChangeVo getContractChangeVo : getContractChangeVoList) {
                JSONObject obj = new JSONObject(true);
                obj.put("ContractID", getContractChangeVo.getContractID());
                obj.put("ContractChangeID", getContractChangeVo.getContractChangeID());
                obj.put("CcNo", getContractChangeVo.getCcNo());//变更单编码CreatedBy
                if(getContractChangeVo.getProposer()!=null) {
                    obj.put("Proposer", ContractEnum.EnumContractProposer.getEnumContractProposer(getContractChangeVo.getProposer()).getMessage());//变更申请方
                }
                obj.put("ChangeReason", getContractChangeVo.getChangeReason());//变更原因
                if(getContractChangeVo.getChangeType()!=0) {
                    obj.put("changeType", ContractEnum.EnumContractChageType.getEnumContractChageType(String.valueOf(getContractChangeVo.getChangeType())).getMessage());//变更事项
                }
                obj.put("changeAmount", getContractChangeVo.getChangeAmount());//变更后含税金额
                obj.put("changeNoTaxAmount", getContractChangeVo.getChangeNoTaxAmount());//变更后不含税金额
                obj.put("changeTaxAmount", getContractChangeVo.getChangeTaxAmount());//变更后税额
                list.add(obj);
            }
        }
        return DataResult.success(list);
    }
    /*
     * 获取变更环节合同文本模板列表
     * */
    @Override
    public DataResult<?> getChangeTextModel(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }
        String userId = crContractbasic.getMainOrgUserID();//合同经办人
        //获取合同经办人所在单位
        /*Integer orgId = 0;
        List<Integer> userOrgs = userInfoRequest.queryOrgs(userId);
        if (userOrgs != null && userOrgs.size() > 0) {
            for (Integer deptId : userOrgs) {
                SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
                orgId = sysOrganization.getfId();
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

        Integer textModelType = Integer.parseInt(ContractEnum.EnumTextType.changeType.getCode());//变更文本
        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper<>();
       /* if (orgId != 0) {
            queryWrapper.lambda().in(CrContracttextmodel::getOrgID, orgId);//公开范围
        }
*/
      /*  queryWrapper.lambda().in(CrContracttextmodel::getType1, type1);//合同类型1
        queryWrapper.lambda().in(CrContracttextmodel::getType2, type2);//合同类型2
        queryWrapper.lambda().in(CrContracttextmodel::getType3, type3);//合同类型3*/
     /*   queryWrapper.lambda().in(CrContracttextmodel::getType1, type1).or().in(CrContracttextmodel::getType2, type2)
                .or().in(CrContracttextmodel::getType3, type3);//合同类型1 /合同类型2/合同类型3*/
        queryWrapper.lambda().eq(CrContracttextmodel::getTextModelType, textModelType);//变更文本
        queryWrapper.lambda().eq(CrContracttextmodel::getStatus, Integer.parseInt(ContractEnum.EnumContractTextStatus.Publish.getCode()));//发布状态
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
                    continue;
                }
            }
        }
        return DataResult.success(list);
    }

    /**
     * 合同文本生成
     */
    @Override
    public DataResult<?> createChangeContractText(String contractTextId, String changeId, String textId, Integer textType) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrContracttext crContracttext = new CrContracttext();
        crContracttext.setTextID(contractTextId);
        crContracttext.setContractID(changeId);
        crContracttext.setTextType(textType);
        crContracttext.setIssuer(textId);//记录选择的标准文本模板
        crContracttext.setStatus(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));
        crContracttext.setCreatedDate(LocalDateTime.now());
        crContracttext.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContracttextMapper.insert(crContracttext);
        return DataResult.success(true);
    }

    /*
     *获取合同文本
     * */
    @Override
    public DataResult<?> getChangeContractText(String changeId, boolean edit) {
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CrContracttext::getContractID, changeId);
        if (!edit) {
            queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//文本状态
        }

        queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);
        CrContracttext crContracttext = new CrContracttext();
        if (crContracttextList != null && crContracttextList.size() > 0) {
            crContracttext = crContracttextList.get(0);
            JSONObject obj = new JSONObject(true);
            obj.put("changeId", changeId);//变更合同id
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
            obj.put("textId", crContracttext.getTextID());//合同文本id
            obj.put("contractTextType", crContracttext.getTextType());//合同文本类型
            obj.put("textModelId", crContracttext.getIssuer());//所选择的合同模板id
            CrContracttextmodel crContracttextmodel = crContracttextmodelMapper.selectById(crContracttext.getIssuer());
            if (crContracttextmodel != null) {
                obj.put("textModelName", crContracttextmodel.getTextName());//所选择的合同模板
            } else {
                obj.put("textModelName", "");//所选择的合同模板名称
            }
        }
        return DataResult.success(crContracttext);
    }

    /**
     * 获取最新合同文本
     */
    @Override
    public DataResult<?> getNewChangeContractText(String changeId) {

        List<JSONObject> list = new ArrayList<>();

        List<CrContracttext> crContracttexts = new ArrayList<>();
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CrContracttext::getContractID, changeId);
        queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);

        if (crContracttextList != null && crContracttextList.size() > 0) {
            CrContracttext crContracttext = crContracttextList.get(0);
            JSONObject obj = new JSONObject(true);
            obj.put("contractId", changeId);//变更合同id
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
        return DataResult.success(list);
    }

    /**
     * 获取历史合同文本
     */
    @Override
    public DataResult<?> getChangeHistoryContractText(String changeId) {

        List<JSONObject> list = new ArrayList<>();
        QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CrContracttext::getContractID, changeId);
        queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//文本状态
        queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
        List<CrContracttext> crContracttextList = crContracttextMapper.selectList(queryWrapper);

        if (crContracttextList != null && crContracttextList.size() > 0) {
            int i = 0;
            for (CrContracttext crContracttext : crContracttextList) {
                if (i > 0) {
                    //排除最新的合同文本
                    crContracttext = crContracttextList.get(i);
                    JSONObject obj = new JSONObject(true);
                    obj.put("changeId", changeId);//合同id
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
     * 合同变更
     */
    @Override
    @Transactional
    public Integer contractChange(ContractChange contractChangeModel, boolean isSubmit) {
        if (StringUtils.isEmpty(contractChangeModel.contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractChangeModel.changeId)) {
            throw new NotFoundException("合同变更ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractChangeModel.contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractChangeModel.contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同不存在！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
        crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractChangeModel.contractId);
        crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractChangeModel.contractId);
        crContractchangeQueryWrapper.lambda().eq(CrContractchange::getState, ContractEnum.EnumStatus.Handing.getCode());//正在审批中
        int count = crContractchangeMapper.selectCount(crContractchangeQueryWrapper);
        count = -1;//合同履行控制按钮，因此不再做校验
        if (count > 0) {
            return 0;//正在审批中/草稿箱存在
        } else {
            if (contractChangeModel.taskId != null && !StringUtils.isEmpty(contractChangeModel.taskId)) {
                dpsRequest.taskMessageComplete(contractChangeModel.taskId);
                workFlowService.completeMessage(contractChangeModel.taskId);//临时消息关闭
            }
            UserInfo userInfo = currentUserUtil.currentUserInfo();
            LocalDateTime date = LocalDateTime.now();
            CrContractchange crContractchange = crContractchangeMapper.selectById(contractChangeModel.changeId);
            if (crContractchange == null) {
                //新增
                crContractchangeQueryWrapper = new QueryWrapper<>();
                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractChangeModel.contractId);
                crContractchangeQueryWrapper.lambda().orderByDesc(true, CrContractchange::getCteatedDate);
                List<CrContractchange> crContractchangeList = crContractchangeMapper.selectList(crContractchangeQueryWrapper);
                String ccNo = "";
                if (crContractchangeList.size() > 0) {
                    Integer changeCount = crContractchangeList.size();
                    if (changeCount < 10) {
                        ccNo = crContractbasic.getContractNum() + "-BG" + ("0" + changeCount.toString());
                    } else {
                        ccNo = crContractbasic.getContractNum() + "-BG" + (changeCount.toString());
                    }

                } else {
                    ccNo = crContractbasic.getContractNum() + "-BG" + "-01";
                }

                crContractchange = new CrContractchange();
                crContractchange.setContractChangeID(contractChangeModel.changeId);//变更主键
                crContractchange.setContractID(crContractbasic.getContractID());//合同主键
                crContractchange.setCcNo(ccNo);
                crContractchange.setProposer(contractChangeModel.proposer);//变更事项


                //变更类型
                Integer[] changeTypes = contractChangeModel.changeType;
                for (Integer changType : changeTypes) {
                    //标的金额
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.BidAmount.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.BidAmount.getCode()));//标的金额
                        crContractchangedetail.setChangeValue1(contractChangeModel.changeAmount);//变更后金额（含税合同额）
                        crContractchangedetail.setChangeValue2(contractChangeModel.changeTaxAmount);//税额
                        crContractchangedetail.setChangeValue3(contractChangeModel.changeNoTaxAmount);//不含税额
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //履行期限
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.PerformDate.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.PerformDate.getCode()));//履行期限

                        crContractchangedetail.setChangeValue1(contractChangeModel.isMakeSure.toString());//履行期限确定/不确定
                        if (contractChangeModel.isMakeSure == 1) {
                            crContractchangedetail.setChangeValue2(contractChangeModel.performStart);//履行期限开始时间
                            crContractchangedetail.setChangeValue3(contractChangeModel.PerformEnd);//履行期限结束时间
                        } else {
                            crContractchangedetail.setChangeValue2(contractChangeModel.term);//履行期限不确定备注
                        }
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //变更签约主体
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.MySignBody.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.MySignBody.getCode()));//履行期限


                        //对方变更时，需要更新相对人信息

                        String offereeId = "";
                        String offereeName = "";
                        String offereeCode = "";

                        String[] offerees = contractChangeModel.offereeId.split(",");
                        for (String offeree : offerees) {
                            FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(offeree);
                            if (ffOffereeinfo != null) {
                                offereeId = String.join(",", ffOffereeinfo.getOffereeId());
                                offereeName = String.join(",", ffOffereeinfo.getOffereeName());
                                offereeCode = String.join(",", ffOffereeinfo.getOffereeCode());
                            }
                        }

                        CrContractchangeofferee crContractchangeofferee = new CrContractchangeofferee();
                        crContractchangeofferee.setChangeOffereeId(UUID.randomUUID().toString());
                        crContractchangeofferee.setContractChangeDetailID(crContractchange.getContractChangeID());
                        crContractchangeofferee.setContractID(crContractbasic.getContractID());
                        crContractchangeofferee.setNewOffereeId(offereeId);
                        crContractchangeofferee.setNewOffereeName(offereeName);
                        crContractchangeofferee.setNewOffereeCode(offereeCode);

                        String oldOffereeCode = "";
                        String oldOffereeId = "";
                        String oldOffereeName = "";
                        QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
                        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, crContractbasic.getContractID());
                        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
                        if (crContractoffereeList.size() > 0) {
                            for (CrContractofferee crContractofferee : crContractoffereeList) {
                                FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(crContractofferee.getOffereeID());
                                if (ffOffereeinfo != null) {
                                    oldOffereeId = String.join(",", ffOffereeinfo.getOffereeId());
                                    oldOffereeCode = String.join(",", ffOffereeinfo.getOffereeId());
                                    oldOffereeName = String.join(",", ffOffereeinfo.getOffereeId());
                                }
                            }
                        }
                        crContractchangeofferee.setOrginalOffereeId(oldOffereeId);
                        crContractchangeofferee.setOrginalOffereeCode(oldOffereeCode);
                        crContractchangeofferee.setOrginalOffereeName(oldOffereeName);
                        crContractchangeofferee.setOulabel(crContractbasic.getOulabel());
                        crContractchangeofferee.setCreatedBy(userInfo.getSysUser().getfAccount());
                        crContractchangeofferee.setCreatedDate(date);

                        crContractchangedetail.setChangeValue1(offereeName);//合同相对人信息
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetail.setMySignBodyCode(contractChangeModel.mySignBodyCode);
                        crContractchangedetail.setMySignBodyName(contractChangeModel.mySignBodyName);

                        crContractchangedetailMapper.insert(crContractchangedetail);
                        crContractchangeoffereeMapper.insert(crContractchangeofferee);
                    }
                    //其他
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.Other.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.Other.getCode()));//其他
                        crContractchangedetail.setChangeValue1(contractChangeModel.other);
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //标的明细变更
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.BidDetail.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.BidDetail.getCode()));//标的明细变更
                        crContractchangedetailMapper.insert(crContractchangedetail);
                        if (contractChangeModel.changeBidList != null && contractChangeModel.changeBidList.size() > 0) {
                            for (ContractChangeBid bid : contractChangeModel.changeBidList) {
                                CrContractchangebiddetail crContractchangebiddetail = new CrContractchangebiddetail();
                                crContractchangebiddetail.setAccordingID(UUID.randomUUID().toString());
                                crContractchangebiddetail.setSourceAccordingID(bid.accordId);
                                crContractchangebiddetail.setMaterialCode(bid.materialCode);
                                crContractchangebiddetail.setMaterialName(bid.materialName);
                                crContractchangebiddetail.setUnits(bid.units);
                                crContractchangebiddetail.setMaterialGroupCode(bid.materialGroupCode);
                                crContractchangebiddetail.setMaterialGroupName(bid.materialGroupName);
                                crContractchangebiddetail.setNorms(bid.norms);
                                crContractchangebiddetail.setMaterialCode8(bid.standCode);
                                crContractchangebiddetail.setPrice(bid.price);
                                crContractchangebiddetail.setRate(bid.rate);
                                crContractchangebiddetail.setNumber(bid.number);
                                crContractchangebiddetail.setRowTotal(bid.rowTotal);
                                crContractchangebiddetail.setContractChangeId(crContractchange.getContractChangeID());
                                crContractchangebiddetail.setContractId(crContractbasic.getContractID());
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                if (!StringUtils.isEmpty(bid.deliveryDate)) {
                                    crContractchangebiddetail.setDeliveryDate(LocalDateTime.parse(bid.deliveryDate, dateTimeFormatter));//交货日期
                                }
                                crContractchangebiddetail.setCreatedBy(userInfo.getSysUser().getfAccount());
                                crContractchangebiddetail.setCreatedDate(date);
                                crContractchangebiddetailMapper.insert(crContractchangebiddetail);
                            }
                        }
                    }
                }
                if (isSubmit) {
                    crContractchange.setState(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//提交审批

                    //取变更合同文本第一行改成发布状态
                    QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(CrContracttext::getContractID, crContractchange.getContractChangeID());
                    // queryWrapper.lambda().eq(CrContracttext::getStatus, Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//临时保存
                    queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
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
                    crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Change.getCode()));//合同变更 20200907
                    crContractbasicMapper.updateById(crContractbasic);

                    //do 触发工作流
                    StartContext startContext = new StartContext();
                    startContext.setBusinessId(contractChangeModel.changeId);
                    startContext.setBusinessName(crContractbasic.getContractName());
                    List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(), Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                            crContractbasic.getMainDeptID().toString());

                    if (appWorkflowData != null && appWorkflowData.size() > 0) {
                        startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
                    }

                    startContext.setPropertyModel(ContractEnum.EnumModule.Perform.getCode());//合同变更
                    startContext.setSection(ContractEnum.EnumSection.Change.getCode());//合同变更
                    startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
                    startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());// 合同订立 发起的流程分类编码
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
                   /* if (org != null) {
                        startContext.setOrganiseId(org.getfId().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
                    } else {
                        startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
                    }*/

                    startContext.setEnterpriseId(crContractbasic.getOulabel().toString());//所属企业

                    startContext.setExecuteDate(new Date());//发起时间

                    startContext.setUserId(crContractbasic.getMainOrgUserID());//发起用户id
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
                    if (sysUserinfo != null) {
                        startContext.setUnitName(sysUserinfo.getfCname());
                    } else {
                        startContext.setUserId(userInfo.getSysUser().getfId().toString());//发起用户id
                        startContext.setUserName(userInfo.getSysUser().getfCname());//发起用户名
                    }
                    startContext.setAppId(cmisDefaultConfig.getAppId());//应用ID

                    List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Change.getCode());//合同变更
//                    metas = setAppMetasData(metas, crContractbasic, crContractinfo);
                    metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");
                    startContext.setMetasList(metas);
                    startContext.setRepeatCheckFlag(1);//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理 20200821
                    AppExtendsData appExtendsData = new AppExtendsData();

                  /*  appExtendsData.setBusinessId(crContractchange.getContractChangeID());//业务数据Id
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
                    }*/
                    //设置流程条件
                    appExtendsData = workFlowService.setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                            ContractEnum.EnumModule.Perform.getCode(), "", "");
                    startContext.setExtendsData(appExtendsData);
                    String json = JSON.toJSONString(startContext);
                    dpsRequest.start(startContext);
                } else {
                    crContractchange.setState(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//暂存
                    //do 发送待办消息
                    DpsTaskMessage taskMessage = new DpsTaskMessage();
                    taskMessage.setBusinessId(contractChangeModel.changeId);
                    taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
                    taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
                    if (sysUserinfo != null) {
                        taskMessage.setExecutorName(sysUserinfo.getfCname());
                        taskMessage.setExecutorCode(sysUserinfo.getfCode());
                        taskMessage.setBusinessName(crContractbasic.getContractName());
                        taskMessage.setCreatorCode(sysUserinfo.getfCode());
                    }
                    AppExtendsData appExtendsData = new AppExtendsData();

                 /*   appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
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
                    appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节
                    appExtendsData.setExt020(ContractEnum.EnumSection.Change.getCode());//合同变更*/
                    //设置流程条件，合同变更
                    appExtendsData = workFlowService.setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                            ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Change.getCode());

                    taskMessage.setExtendsData(appExtendsData);
                    AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                    workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Change.getCode(), sysUserinfo);//插入临时消息
                }
                crContractchange.setChangeReason(contractChangeModel.changeReason);//变更原因
                crContractchange.setCreatedBy(userInfo.getSysUser().getfAccount());//变更申请人
                crContractchange.setCteatedDate(date);//变更日期

                crContractchangeMapper.insert(crContractchange);//变更主表

            } else {
                //更新
                crContractchange.setProposer(contractChangeModel.proposer);//变更事项
                crContractchange.setChangeReason(contractChangeModel.changeReason);//变更原因
                crContractchange.setModifiedBy(userInfo.getSysUser().getfAccount());//变更申请人
                crContractchange.setCteatedDate(date);//变更日期

                //删除变更明细，再插入
                QueryWrapper<CrContractchangedetail> contractchangedetailQueryWrapper = new QueryWrapper<>();
                contractchangedetailQueryWrapper.lambda().eq(CrContractchangedetail::getContractChangeID, crContractchange.getContractChangeID());
                List<CrContractchangedetail> crContractchangedetailList = crContractchangedetailMapper.selectList(contractchangedetailQueryWrapper);
                if (crContractchangedetailList != null && crContractchangedetailList.size() > 0) {
                    for (CrContractchangedetail crContractchangedetail : crContractchangedetailList) {
                        //删除变更相对人信息
                        QueryWrapper<CrContractchangeofferee> contractchangeoffereeQueryWrapper = new QueryWrapper<>();
                        contractchangeoffereeQueryWrapper.lambda().eq(CrContractchangeofferee::getContractChangeDetailID, crContractchangedetail.getContractChangeDetailID());
                        List<CrContractchangeofferee> crContractchangeoffereeList = crContractchangeoffereeMapper.selectList(contractchangeoffereeQueryWrapper);
                        if (crContractchangeoffereeList != null && crContractchangeoffereeList.size() > 0) {
                            for (CrContractchangeofferee crContractchangeofferee : crContractchangeoffereeList) {
                                crContractchangeoffereeMapper.deleteById(crContractchangeofferee.getChangeOffereeId());
                            }
                        }
                        //删除变更明细信息
                        QueryWrapper<CrContractchangebiddetail> contractchangebiddetailQueryWrapper = new QueryWrapper<>();
                        contractchangebiddetailQueryWrapper.lambda().eq(CrContractchangebiddetail::getContractChangeId, crContractchangedetail.getContractChangeID());
                        List<CrContractchangebiddetail> crContractchangebiddetailList = crContractchangebiddetailMapper.selectList(contractchangebiddetailQueryWrapper);
                        if (crContractchangebiddetailList != null && crContractchangebiddetailList.size() > 0) {
                            for (CrContractchangebiddetail crContractchangebiddetail : crContractchangebiddetailList) {
                                crContractchangebiddetailMapper.deleteById(crContractchangebiddetail.getAccordingID());
                            }
                        }
                        //删除变更明细表
                        crContractchangedetailMapper.deleteById(crContractchangedetail.getContractChangeDetailID());
                    }
                }

                //变更类型
                Integer[] changeTypes = contractChangeModel.changeType;
                for (Integer changType : changeTypes) {
                    //标的金额
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.BidAmount.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.BidAmount.getCode()));//标的金额
                        crContractchangedetail.setChangeValue1(contractChangeModel.changeAmount);//变更后金额
                        crContractchangedetail.setChangeValue2(contractChangeModel.changeTaxAmount);//税额
                        crContractchangedetail.setChangeValue3(contractChangeModel.changeNoTaxAmount);//不含税额
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //履行期限
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.PerformDate.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.PerformDate.getCode()));//履行期限

                        crContractchangedetail.setChangeValue1(contractChangeModel.isMakeSure.toString());//履行期限确定/不确定
                        if (contractChangeModel.isMakeSure == 1) {
                            crContractchangedetail.setChangeValue2(contractChangeModel.performStart);//履行期限开始时间
                            crContractchangedetail.setChangeValue3(contractChangeModel.PerformEnd);//履行期限结束时间
                        } else {
                            crContractchangedetail.setChangeValue2(contractChangeModel.term);//履行期限不确定备注
                        }
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //变更签约主体
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.MySignBody.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.MySignBody.getCode()));//履行期限


                        //对方变更时，需要更新相对人信息

                        String offereeId = "";
                        String offereeName = "";
                        String offereeCode = "";

                        String[] offerees = contractChangeModel.offereeId.split(",");
                        List<FfOffereeinfo> ffOffereeinfos = ffOffereeinfoMapper.selectBatchIds(Arrays.asList(offerees));
                        offereeId = ffOffereeinfos.stream().map(FfOffereeinfo::getOffereeId).collect(Collectors.joining(","));
                        offereeName = ffOffereeinfos.stream().map(FfOffereeinfo::getOffereeName).collect(Collectors.joining(","));
                        offereeCode = ffOffereeinfos.stream().map(FfOffereeinfo::getOffereeCode).collect(Collectors.joining(","));

                        CrContractchangeofferee crContractchangeofferee = new CrContractchangeofferee();
                        crContractchangeofferee.setChangeOffereeId(UUID.randomUUID().toString());
                        crContractchangeofferee.setContractChangeDetailID(crContractchangedetail.getContractChangeDetailID());
                        crContractchangeofferee.setContractID(crContractbasic.getContractID());
                        crContractchangeofferee.setNewOffereeId(offereeId);
                        crContractchangeofferee.setNewOffereeName(offereeName);
                        crContractchangeofferee.setNewOffereeCode(offereeCode);

                        String oldOffereeCode = "";
                        String oldOffereeId = "";
                        String oldOffereeName = "";
                        QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
                        contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, crContractbasic.getContractID());
                        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
                        List<String> offereeIds = crContractoffereeList.stream().map(CrContractofferee::getOffereeID).collect(Collectors.toList());
                        List<FfOffereeinfo> ffOffereeinfos2 = ffOffereeinfoMapper.selectBatchIds(offereeIds);
                        oldOffereeId = ffOffereeinfos2.stream().map(FfOffereeinfo::getOffereeId).collect(Collectors.joining(","));
                        oldOffereeName = ffOffereeinfos2.stream().map(FfOffereeinfo::getOffereeName).collect(Collectors.joining(","));
                        oldOffereeCode = ffOffereeinfos2.stream().map(FfOffereeinfo::getOffereeCode).collect(Collectors.joining(","));

                        crContractchangeofferee.setOrginalOffereeId(oldOffereeId);
                        crContractchangeofferee.setOrginalOffereeCode(oldOffereeCode);
                        crContractchangeofferee.setOrginalOffereeName(oldOffereeName);
                        crContractchangeofferee.setOulabel(crContractbasic.getOulabel());
                        crContractchangeofferee.setCreatedBy(userInfo.getSysUser().getfId().toString());
                        crContractchangeofferee.setCreatedDate(date);

                        crContractchangedetail.setChangeValue1(offereeName);//合同相对人信息
                        crContractchangedetail.setOulabel(crContractbasic.getOulabel());
                        crContractchangedetail.setMySignBodyCode(contractChangeModel.mySignBodyCode);
                        crContractchangedetail.setMySignBodyName(contractChangeModel.mySignBodyName);

                        crContractchangedetailMapper.insert(crContractchangedetail);
                        crContractchangeoffereeMapper.insert(crContractchangeofferee);
                    }
                    //其他
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.Other.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.Other.getCode()));//其他
                        crContractchangedetail.setChangeValue1(contractChangeModel.other);
                        crContractchangedetailMapper.insert(crContractchangedetail);
                    }
                    //标的明细变更
                    if (changType.toString().equals(ContractEnum.EnumContractChageType.BidDetail.getCode())) {
                        CrContractchangedetail crContractchangedetail = new CrContractchangedetail();
                        crContractchangedetail.setContractChangeDetailID(UUID.randomUUID().toString());
                        crContractchangedetail.setContractChangeID(crContractchange.getContractChangeID());
                        crContractchangedetail.setChangeType(Integer.parseInt(ContractEnum.EnumContractChageType.BidDetail.getCode()));//标的明细变更
                        crContractchangedetailMapper.insert(crContractchangedetail);
                        if (contractChangeModel.changeBidList != null && contractChangeModel.changeBidList.size() > 0) {
                            for (ContractChangeBid bid : contractChangeModel.changeBidList) {
                                CrContractchangebiddetail crContractchangebiddetail = new CrContractchangebiddetail();
                                crContractchangebiddetail.setAccordingID(UUID.randomUUID().toString());
                                crContractchangebiddetail.setSourceAccordingID(bid.accordId);
                                crContractchangebiddetail.setMaterialCode(bid.materialCode);
                                crContractchangebiddetail.setMaterialName(bid.materialName);
                                crContractchangebiddetail.setUnits(bid.units);
                                crContractchangebiddetail.setMaterialGroupCode(bid.materialGroupCode);
                                crContractchangebiddetail.setMaterialGroupName(bid.materialGroupName);
                                crContractchangebiddetail.setNorms(bid.norms);
                                crContractchangebiddetail.setMaterialCode8(bid.standCode);
                                crContractchangebiddetail.setPrice(bid.price);
                                crContractchangebiddetail.setRate(bid.rate);
                                crContractchangebiddetail.setNumber(bid.number);
                                crContractchangebiddetail.setRowTotal(bid.rowTotal);
                                crContractchangebiddetail.setContractChangeId(crContractchange.getContractChangeID());
                                crContractchangebiddetail.setContractId(crContractbasic.getContractID());

                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                                if (!StringUtils.isEmpty(bid.deliveryDate)) {
                                    crContractchangebiddetail.setDeliveryDate(LocalDateTime.parse(bid.deliveryDate, dateTimeFormatter));//交货日期
                                }

                                crContractchangebiddetail.setCreatedBy(userInfo.getSysUser().getfId().toString());
                                crContractchangebiddetail.setCreatedDate(date);
                                crContractchangebiddetailMapper.insert(crContractchangebiddetail);
                            }
                        }
                    }
                }

                if (isSubmit) {
                    crContractchange.setState(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//提交审批
                    //取变更合同文本第一行改成发布状态
                    QueryWrapper<CrContracttext> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(CrContracttext::getContractID, crContractchange.getContractChangeID());
                    queryWrapper.lambda().orderByDesc(true, CrContracttext::getCreatedDate);
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
                    //触发工作流 0408
                    StartContext startContext = new StartContext();
                    startContext.setBusinessId(contractChangeModel.changeId);
                    startContext.setBusinessName(crContractbasic.getContractName());
                    List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(), Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                            crContractbasic.getMainDeptID().toString());

                    if (appWorkflowData != null && appWorkflowData.size() > 0) {
                        startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
                    }

                    startContext.setPropertyModel(ContractEnum.EnumModule.Perform.getCode());//合同变更
                    startContext.setSection(ContractEnum.EnumSection.Change.getCode());//合同变更
                    startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
                    startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());// 合同订立 发起的流程分类编码
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
                    /*startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)*/
                   /* if (org != null) {
                        startContext.setOrganiseId(org.getfId().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
                    } else {
                        startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
                    }*/
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
                        startContext.setUnitName(sysUserinfo.getfCname());
                    } else {
                        startContext.setUserId(userInfo.getSysUser().getfId().toString());//发起用户id
                        startContext.setUserName(userInfo.getSysUser().getfCname());//发起用户名
                    }
                    startContext.setAppId(cmisDefaultConfig.getAppId());//应用ID

                    List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Change.getCode());//合同变更
//                    metas = setAppMetasData(metas, crContractbasic, crContractinfo);
                    metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");

                    startContext.setMetasList(metas);
                    startContext.setRepeatCheckFlag(1);//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理 20200821
                    AppExtendsData appExtendsData = new AppExtendsData();

                   /* appExtendsData.setBusinessId(crContractchange.getContractChangeID());//业务数据Id
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
                    }*/
                    appExtendsData = workFlowService.setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                            ContractEnum.EnumModule.Perform.getCode(), "", "");

                    startContext.setExtendsData(appExtendsData);
                    String json = JSON.toJSONString(startContext);
                    dpsRequest.start(startContext);
                } else {
                    crContractchange.setState(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//暂存
                    //do 发送待办消息
                    DpsTaskMessage taskMessage = new DpsTaskMessage();
                    taskMessage.setBusinessId(contractChangeModel.changeId);
                    taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
                    taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
                    if (sysUserinfo != null) {
                        taskMessage.setExecutorName(sysUserinfo.getfCname());
                        taskMessage.setExecutorCode(sysUserinfo.getfCode());
                        taskMessage.setBusinessName(crContractbasic.getContractName());
                        taskMessage.setCreatorCode(sysUserinfo.getfCode());
                    }
                    AppExtendsData appExtendsData = new AppExtendsData();

                   /* appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
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
                    appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节
                    appExtendsData.setExt020(ContractEnum.EnumSection.Change.getCode());//合同变更*/
                    //设置扩展字段，合同变更
                    appExtendsData = workFlowService.setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                            ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Change.getCode());

                    taskMessage.setExtendsData(appExtendsData);
                    String dd = JSON.toJSONString(taskMessage);
                    AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
                    workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Change.getCode(), sysUserinfo);//插入临时消息
                }
                crContractchangeMapper.updateById(crContractchange);
            }
        }
        return 1;
    }

//    /**
//     * 设置流程条件
//     */
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
//                if (appMetasData.getMetaCode().equals("szgt_contract_mainorg")) {//主办单位
//                    if (crContractbasic.getMainOrgID() != null) {
//                        appMetasData.setDataValue(crContractbasic.getMainOrgID().toString());
//                    } else {
//                        appMetasData.setDataValue("");
//                    }
//                }
//
//                if (appMetasData.getMetaCode().equals("szgt_contract_planmoney")) {//计划金额
//                    if (crContractinfo.getPlanMoney() != null) {
//                        appMetasData.setDataValue(crContractinfo.getPlanMoney().toString());
//                    } else {
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
//                if (appMetasData.getMetaCode().equals("szgt_contract_MoneyFlow")) {
//                    if (crContractbasic.getMoneyFlow() > 0) {
//                        appMetasData.setDataValue(crContractbasic.getMoneyFlow().toString());//资金流向
//                    } else {
//                        appMetasData.setDataValue("");//资金流向
//                    }
//                }
//                appMetasDataList.add(appMetasData);
//
//            }
//        }
//        return appMetasDataList;
//    }

    /*
     * 获取变更记录
     * */
    @Override
    public DataResult<?> getChangeList(String contractId, Integer pageNum, Integer pageSize) {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }

        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }

        QueryWrapper<CrContractchange> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
        //全部显示
//        queryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过的
        queryWrapper.lambda().orderByDesc(CrContractchange::getCteatedDate);
        Page<CrContractchange> page = new Page<CrContractchange>(pageNum, pageSize);
        IPage<CrContractchange> CrContractchangePage = crContractchangeMapper.selectPage(page, queryWrapper);
        for (CrContractchange crContractchange : CrContractchangePage.getRecords()) {
            JSONObject obj = new JSONObject(true);
            obj.put("changeId", crContractchange.getContractChangeID());
            obj.put("contractId", crContractchange.getContractID());
            obj.put("ccNo", crContractchange.getCcNo());//变更编号
            if (crContractchange.getProposer() != null) {
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.OurSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.OurSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.OtherSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.OtherSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.BothSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.BothSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.Other.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.Other.getMessage());//申请变更方
                }
            }

            obj.put("applyDate", crContractchange.getCteatedDate());//申请时间
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

    /**
     * 获取所有变更记录
     */
    @Override
    public DataResult<?> getAllChangeList(String contractId, Integer pageNum, Integer pageSize) {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractchange> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
        /* queryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过的*/
        queryWrapper.lambda().orderByDesc(true, CrContractchange::getCteatedDate);
        Page<CrContractchange> page = new Page<CrContractchange>(pageNum, pageSize);
        IPage<CrContractchange> CrContractchangePage = crContractchangeMapper.selectPage(page, queryWrapper);
        for (CrContractchange crContractchange : CrContractchangePage.getRecords()) {
            JSONObject obj = new JSONObject(true);
            obj.put("changeId", crContractchange.getContractChangeID());
            obj.put("contractId", crContractchange.getContractID());
            obj.put("ccNo", crContractchange.getCcNo());//变更编号
            if (crContractchange.getProposer() != null) {
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.OurSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.OurSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.OtherSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.OtherSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.BothSide.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.BothSide.getMessage());//申请变更方
                }
                if (crContractchange.getProposer() == Integer.parseInt(ContractEnum.EnumContractProposer.Other.getCode())) {
                    obj.put("proposer", ContractEnum.EnumContractProposer.Other.getMessage());//申请变更方
                }
            }
            String state = crContractchange.getState().toString();
            state = ContractEnum.enumStatusMap.get(crContractchange.getState().toString());
            obj.put("state", state);//变更状态
            /* obj.put("state", crContractchange.getchange());//变更事项//0502*/
            String changeTypeName = "";
            QueryWrapper<CrContractchangedetail> contractchangedetailQueryWrapper = new QueryWrapper<>();
            contractchangedetailQueryWrapper.lambda().eq(CrContractchangedetail::getContractChangeID, crContractchange.getContractChangeID());
            List<CrContractchangedetail> crContractchangedetailList = crContractchangedetailMapper.selectList(contractchangedetailQueryWrapper);
            if (crContractchangedetailList != null && crContractchangedetailList.size() > 0) {
                for (CrContractchangedetail crContractchangedetail : crContractchangedetailList) {
                    Integer changeType = crContractchangedetail.getChangeType();//0502
                    changeTypeName += ContractEnum.enumContractChageTypeMap.get(changeType.toString()) + ",";
                }
            }
            if (!StringUtils.isEmpty(changeTypeName)) {
                changeTypeName = changeTypeName.substring(0, changeTypeName.length() - 1);
            }
            obj.put("changeType", changeTypeName);//变更事项
            obj.put("applyDate", crContractchange.getCteatedDate());//变更申请时间
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

    /**
     * 查看变更信息
     */
    @Override
    public DataResult<?> getChange(String changeCntractId) {
        if (StringUtils.isEmpty(changeCntractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractchange crContractchange = crContractchangeMapper.selectById(changeCntractId);
        if (crContractchange == null) {
            if (StringUtils.isEmpty(changeCntractId)) {
                throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
            }
        }
        ContractChange contractChange = new ContractChange();
        contractChange.changeId = crContractchange.getContractChangeID();
        contractChange.changeCode = crContractchange.getCcNo();//转让编号
        contractChange.contractId = crContractchange.getContractID();
        contractChange.proposer = crContractchange.getProposer();//申请变更方
        contractChange.changeReason = crContractchange.getChangeReason();//变更原因
        List<Integer> changeType = new ArrayList<>();//变更申请方
        String changeAmount = "0";//变更金额
        String changeTaxAmount = "0";//含税额
        String changeNoTaxAmount = "0";//不含税额
        Integer isMakeSure = 0;//是否确定变更
        String performStart = "";//履行开始时间
        String performEnd = "";//履行结束时间
        String term = "";
        Integer mySignBodyCode = 0;
        String mySignBodyName = "";
        String offereeId = "";
        String offereeName = "";
        String oldOffereeId = "";
        String oldOffereeName = "";
        String other = "";
        contractChange.changeBidList = new ArrayList<>();
        QueryWrapper<CrContractchangedetail> crContractchangedetailQueryWrapper = new QueryWrapper<>();
        crContractchangedetailQueryWrapper.lambda().eq(CrContractchangedetail::getContractChangeID, crContractchange.getContractChangeID());
        List<CrContractchangedetail> crContractchangedetailList = crContractchangedetailMapper.selectList(crContractchangedetailQueryWrapper);
        if (crContractchangedetailList != null && crContractchangedetailList.size() > 0) {
            for (CrContractchangedetail crContractchangedetail : crContractchangedetailList) {
                //changeType += crContractchangedetail.getChangeType().toString() + ",";
                changeType.add(crContractchangedetail.getChangeType());
                //标的金额变更
                if (crContractchangedetail.getChangeType() == Integer.parseInt(ContractEnum.EnumContractChageType.BidAmount.getCode())) {
                    changeAmount = crContractchangedetail.getChangeValue1();//合同金额（含税合同金额）
                    changeTaxAmount = crContractchangedetail.getChangeValue2();//含税额
                    changeNoTaxAmount = crContractchangedetail.getChangeValue3();//不含税额
                }
                if (crContractchangedetail.getChangeType() == Integer.parseInt(ContractEnum.EnumContractChageType.PerformDate.getCode())) {
                    if (crContractchangedetail.getChangeValue1().equals("1")) {
                        //履行期限确定
                        performStart = crContractchangedetail.getChangeValue2();//履行期限开始时间
                        performEnd = crContractchangedetail.getChangeValue3();//履行期限结束时间
                    } else {
                        term = crContractchangedetail.getChangeValue2();
                    }
                    isMakeSure = Integer.parseInt(crContractchangedetail.getChangeValue1());
                }
                if (crContractchangedetail.getChangeType() == Integer.parseInt(ContractEnum.EnumContractChageType.MySignBody.getCode())) {
                    if (!StringUtils.isEmpty(crContractchangedetail.getMySignBodyCode())) {
                        mySignBodyCode = crContractchangedetail.getMySignBodyCode();
                        mySignBodyName = crContractchangedetail.getMySignBodyName();
                    }
                    QueryWrapper<CrContractchangeofferee> crContractchangeoffereeQueryWrapper = new QueryWrapper<>();
                    crContractchangeoffereeQueryWrapper.lambda().eq(CrContractchangeofferee::getContractChangeDetailID, crContractchangedetail.getContractChangeDetailID());
                    List<CrContractchangeofferee> crContractchangeoffereelist = crContractchangeoffereeMapper.selectList(crContractchangeoffereeQueryWrapper);
                    if (crContractchangeoffereelist != null && crContractchangeoffereelist.size() > 0) {
                        for (CrContractchangeofferee crContractchangeofferee : crContractchangeoffereelist) {
                            offereeId = crContractchangeofferee.getNewOffereeId();
                            offereeName = crContractchangeofferee.getNewOffereeName();
                            oldOffereeId = crContractchangeofferee.getOrginalOffereeId();
                            oldOffereeName = crContractchangeofferee.getOrginalOffereeName();
                        }
                    }
                }
                //其他
                if (crContractchangedetail.getChangeType() == Integer.parseInt(ContractEnum.EnumContractChageType.Other.getCode())) {
                    other = crContractchangedetail.getChangeValue1();
                }
            }
        }
       /* if (!StringUtils.isEmpty(changeType)) {
            String changeType = changeType.substring(0, changeType.length() - 1); //变更事项 标的金额、履行期限、主体名称、标的明细、其他
        }*/
        if (changeType != null && changeType.size() > 0) {
            Integer[] changeType1 = new Integer[changeType.size()];
            for (int i = 0; i < changeType.size(); i++) {
                changeType1[i] = changeType.get(i);
            }
            contractChange.changeType = changeType1;//变更事项
        }
        contractChange.changeAmount = changeAmount;//变更后金额（不含税合同金额）
        contractChange.changeTaxAmount = changeTaxAmount;//税额
        contractChange.changeNoTaxAmount = changeNoTaxAmount;//不含税额
        contractChange.isMakeSure = isMakeSure;//履行期限是否变更 0否 1是
        contractChange.performStart = performStart;//履行期限开始时间
        contractChange.PerformEnd = performEnd;//履行期限结束时间
        contractChange.term = term;//履行期限不确定时，备注
        contractChange.mySignBodyCode = mySignBodyCode;//我方签约主体id
        if (mySignBodyName == null && StringUtils.isEmpty(mySignBodyName)) {
            SysOrganiseunitSinging sysOrganiseunitSinging = sysOrganiseunitSingingMapper.selectById(mySignBodyCode);
            if (sysOrganiseunitSinging != null) {
                mySignBodyName = sysOrganiseunitSinging.getSingingName();
            }
        }
        contractChange.mySignBodyName = mySignBodyName;//我方签约主体名称
        contractChange.offereeId = offereeId;//变更后相对人id 以','隔开
        contractChange.offereeName = offereeName;//变更后相对人名称 以','隔开
        contractChange.oldOffereeId = oldOffereeId;//原相对人id
        contractChange.oldOffereeName = oldOffereeName;//原相对人名称
        contractChange.other = other;//变更事项其他时，备注
        return DataResult.success(contractChange);
    }

    @Override
    /**
     * 	获取变更信息，标的明细
     */
    public DataResult<?> getChangeBid(String changeCntractId, Integer pageNum, Integer pageSize) {
        List<ContractChangeBid> list = new ArrayList<>();
        if (StringUtils.isEmpty(changeCntractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractchange crContractchange = crContractchangeMapper.selectById(changeCntractId);
        if (crContractchange == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractchangebiddetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractchangebiddetail::getContractChangeId, crContractchange.getContractChangeID());
        Page<CrContractchangebiddetail> page = new Page<CrContractchangebiddetail>(pageNum, pageSize);
        IPage<CrContractchangebiddetail> CrContractchangePage = crContractchangebiddetailMapper.selectPage(page, queryWrapper);
        for (CrContractchangebiddetail crContractchangebiddetail : CrContractchangePage.getRecords()) {
            ContractChangeBid contractChangeBid = new ContractChangeBid();
            contractChangeBid.accordId = crContractchangebiddetail.getAccordingID();
            contractChangeBid.materialCode = crContractchangebiddetail.getMaterialCode();
            contractChangeBid.materialName = crContractchangebiddetail.getMaterialName();
            contractChangeBid.materialGroupCode = crContractchangebiddetail.getMaterialGroupCode();
            contractChangeBid.materialGroupName = crContractchangebiddetail.getMaterialGroupName();
            contractChangeBid.units = crContractchangebiddetail.getUnits();
            contractChangeBid.norms = crContractchangebiddetail.getNorms();
            contractChangeBid.standCode = crContractchangebiddetail.getMaterialCode8();
            contractChangeBid.price = crContractchangebiddetail.getPrice();
            contractChangeBid.rate = crContractchangebiddetail.getRate();
            contractChangeBid.number = crContractchangebiddetail.getNumber();
            contractChangeBid.rowTotal = crContractchangebiddetail.getRowTotal();
            if (crContractchangebiddetail.getDeliveryDate() != null) {
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strDate2 = dtf2.format(crContractchangebiddetail.getDeliveryDate());
                contractChangeBid.deliveryDate = strDate2;
            } else {
                contractChangeBid.deliveryDate = "";
            }

            list.add(contractChangeBid);
        }
        PageData<ContractChangeBid> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(list);
        return DataResult.success(pageData);
    }

    @Override
    /**
     *	合同变更签署
     */
    public boolean contractChangeSign(String changeContractId, String ourSignatoryName, String ourSignatory,
                                      String opponentSignatory, String ourSealedTime, Integer effective,
                                      String effectiveDate, String effectiveElements, String taskId, boolean isSumbit) {
        List<ContractChangeBid> list = new ArrayList<>();
        if (StringUtils.isEmpty(changeContractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractchange crContractchange = crContractchangeMapper.selectById(changeContractId);
        if (crContractchange == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        crContractchange.setOurSignatory(ourSignatory);//我方签约人ID
        crContractchange.setOurSignatoryName(ourSignatoryName);//我方签约人姓名
        crContractchange.setOpponentSignatory(opponentSignatory);//对方签约人
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(ourSealedTime)) {
            crContractchange.setOurSealedTime(LocalDateTime.parse(ourSealedTime, dateTimeFormatter));//签订日期
        }
        if (!StringUtils.isEmpty(effectiveDate)) {
            crContractchange.setEffectiveDate(LocalDateTime.parse(effectiveDate, dateTimeFormatter));//合同生效日期
        }
        crContractchange.setEffectiveType(effective);//生效情况 及时生效/其他
        crContractchange.setEffectiveElements(effectiveElements);//变更生效要件
        crContractchange.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractchange.setModifiedDate(date);

        if (isSumbit) {
            //合同变更签署待办设置完结
            if (!StringUtils.isEmpty(taskId)) {
                dpsRequest.taskMessageComplete(taskId);
                workFlowService.completeMessage(taskId);//临时消息关闭
            }
            CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractchange.getContractID());
            CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContractchange.getContractID());
            if (crContractbasic != null) {
                crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
                crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
                crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//合同审批结束
                crContractbasic.setModifiedDate(date);
                crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractbasicMapper.updateById(crContractbasic);
            }
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Change.getCode());
            taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setBusinessName(crContractbasic.getContractName());
                taskMessage.setCreatorCode(sysUserinfo.getfCode());
            }
            AppExtendsData appExtendsData = new AppExtendsData();

           /* appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
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
            }*/
            appExtendsData = workFlowService.setExtendsData(crContractchange.getContractChangeID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());

            taskMessage.setExtendsData(appExtendsData);
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);

            // 同步合同信息到财务系统
            sendToFinancial(crContractbasic, crContractinfo, FinancialEnum.CHANGE);
            if (!StringUtils.isEmpty(taskId)) {
                agentRequest.sendMessageForTransactor(crContractbasic.getContractID(), "合同变更签署");
            }
        }
        return crContractchangeMapper.updateById(crContractchange) > 0 ? true : false;

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

        //合同金额
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
        contractInfo.setContrIsFrame(crContractinfo.getIsFrameContract().toString());

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

        DataResult<?> contractText = iMakeService.getContractText(contractInfo.getContractId(), false);
        JSONObject jsonObject = (JSONObject) contractText.getData();
        contractInfo.setContractUrl((String) jsonObject.get("contractTextUrl"));

        financialService.sendDataToFinancial(contractInfo);
    }

    /**
     * 获取合同变更签署信息
     */
    @Override
    public DataResult<?> getContractChangeSign(String changeContractId) {
        List<ContractChangeBid> list = new ArrayList<>();
        if (StringUtils.isEmpty(changeContractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractchange crContractchange = crContractchangeMapper.selectById(changeContractId);
        if (crContractchange == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("changeContractId", crContractchange.getContractChangeID());//变更ID
        jsonObject.put("ourSignatory", crContractchange.getOurSignatory());//我方签约人ID
        String ourSignatoryName = crContractchange.getOurSignatoryName();
        if (StringUtils.isEmpty(crContractchange.getOurSignatoryName())) {
            if (!StringUtils.isEmpty(crContractchange.getOurSignatory())) {
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractchange.getOurSignatory()));
                if (sysUserinfo != null) {
                    ourSignatoryName = sysUserinfo.getfCname();
                }
            }
        }
        jsonObject.put("ourSignatoryName", ourSignatoryName);//我方签约人姓名
        jsonObject.put("opponentSignatory", crContractchange.getOpponentSignatory());//对方签约人姓名

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (crContractchange.getOurSealedTime() != null) {
            jsonObject.put("ourSealedTime", crContractchange.getOurSealedTime().format(dateTimeFormatter));//签订日期
        } else {
            jsonObject.put("ourSealedTime", "");//签订日期
        }
        String effectiveDate = "";
        if (crContractchange.getEffectiveDate() != null) {
            jsonObject.put("effectiveDate", crContractchange.getEffectiveDate().format(dateTimeFormatter));//变更生效日期
        } else {
            jsonObject.put("effectiveDate", "");//变更生效日期
        }
        jsonObject.put("effective", crContractchange.getEffectiveType());//生效情况 及时生效/其他
        jsonObject.put("effectiveElements", crContractchange.getEffectiveElements());//变更生效要件
        jsonObject.put("createDate", crContractchange.getCteatedDate());//创建日期
        return DataResult.success(jsonObject);
    }

    @Override
    /*
     * 判断是否存在转让流程
     * */
    public boolean isExistTransFlow(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        //履行经办人所在单位/企业
        String orgId = "";
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                orgId = crContractrununit.getOrgID().toString();
                break;
            }

        } else {
            orgId = crContractbasic.getMainDeptID().toString();
        }
        // 获取orgId信息，找所在企业/单位信息
        //判断是否转让变更流程
        return true;
    }

    /**
     * 判断合同是否正在转让审批中
     */
    @Override
    public boolean isContractTrans(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        QueryWrapper<CrContracttransfer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
        queryWrapper.lambda().or().eq(CrContracttransfer::getState, ContractEnum.EnumStatus.Handing.getCode())
                .or().eq(CrContracttransfer::getState, ContractEnum.EnumStatus.TempSave.getCode());//提交或草稿箱状态
        List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(queryWrapper);
        if (crContracttransferList.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 合同转让
     */
    @Override
    @Transactional
    public Integer contractTrans(ContractTrans contractTrans, boolean isSumbit) {
        if (StringUtils.isEmpty(contractTrans.transId)) {
            throw new NotFoundException("转让ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractTrans.contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractTrans.contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractTrans.contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        QueryWrapper<CrContracttransfer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttransfer::getContractID, crContractbasic.getContractID());
        queryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.Approved.getCode());
        queryWrapper.lambda().ne(CrContracttransfer::getTransferId, contractTrans.transId);//排除自己
        List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(queryWrapper);
        if (crContracttransferList.size() > 0) {
            return 0;//合同转让审批中/草稿状态禁止再次申请
        }


        LocalDateTime date = LocalDateTime.now();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        boolean isExist = false;
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(contractTrans.transId);
        if (crContracttransfer == null) {
            crContracttransfer = new CrContracttransfer();
            crContracttransfer.setTransferId(contractTrans.transId);
            crContracttransfer.setContractID(crContractbasic.getContractID());
            queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContracttransfer::getContractID, crContractbasic.getContractID());
            queryWrapper.lambda().orderByDesc(true, CrContracttransfer::getCreatedDate);
            List<CrContracttransfer> crContracttransfers = crContracttransferMapper.selectList(queryWrapper);
            String transNo = "";
            if (crContracttransfers.size() > 0) {
                Integer transCount = crContracttransfers.size();
                if (transCount < 10) {
                    transNo = crContractbasic.getContractNum() + "-ZR" + ("0" + transCount.toString());
                } else {
                    transNo = crContractbasic.getContractNum() + "-ZR" + (transCount.toString());
                }

            } else {
                transNo = crContractbasic.getContractNum() + "-ZR" + "01";
            }
            crContracttransfer.setTransferNo(transNo);//转让编号
            crContracttransfer.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContracttransfer.setCreatedDate(date);

        } else {
            isExist = true;
            crContracttransfer.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContracttransfer.setModifiedDate(date);
        }

        crContracttransfer.setTransferType(contractTrans.transferType);//转让类型，对应数据字典
        crContracttransfer.setApplicant(contractTrans.applicant);//申请转让方
        crContracttransfer.setTransferReason(contractTrans.transferReason);//转让原因
        crContracttransfer.setOffereeId1(contractTrans.offereeId);//合同转让相对人
        crContracttransfer.setMySignBodyCode(contractTrans.mySignBodyCode);//我方签约主体id
        crContracttransfer.setMySignBodyName(contractTrans.mySignBodyName);//我方签约主体名称
        if (isSumbit) {
            crContracttransfer.setState(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//提交

            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Transfer.getCode()));//合同转让 20200907
            crContractbasicMapper.updateById(crContractbasic);

        } else {
            crContracttransfer.setState(Integer.parseInt(ContractEnum.EnumStatus.TempSave.getCode()));//临时保存
        }
        crContracttransfer.setOulabel(crContractbasic.getOulabel());

        //更新
        if (isExist) {
            crContracttransferMapper.updateById(crContracttransfer);
        } else {
            crContracttransferMapper.insert(crContracttransfer);
        }

        if (isSumbit) {
            //触发流程转让审批流
            StartContext startContext = new StartContext();
            startContext.setBusinessId(contractTrans.transId);
            startContext.setBusinessName(crContractbasic.getContractName());
            List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(),
                    Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                    crContractbasic.getMainDeptID().toString());

            if (appWorkflowData != null && appWorkflowData.size() > 0) {
                startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
            }

            startContext.setPropertyModel(ContractEnum.EnumModule.Perform.getCode());//合同履行
            startContext.setSection(ContractEnum.EnumSection.Transfer.getCode());//合同转让
            startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
            startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Trasfer.getCode());// 合同订立 发起的流程分类编码
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
            /*startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)*/
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
                startContext.setUnitName(sysUserinfo.getfCname());
            } else {
                startContext.setUserId(userInfo.getSysUser().getfId().toString());//发起用户id
                startContext.setUserName(userInfo.getSysUser().getfCname());//发起用户名
            }
            startContext.setAppId(cmisDefaultConfig.getAppId());//应用ID

            List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Trasfer.getCode());//合同转让
//            metas = setAppMetasData(metas, crContractbasic, crContractinfo);
            metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");
            startContext.setMetasList(metas);
            startContext.setRepeatCheckFlag(1);//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理 20200821
            AppExtendsData appExtendsData = new AppExtendsData();

           /* appExtendsData.setBusinessId(crContracttransfer.getTransferId());//业务数据Id
            appExtendsData.setExt001("contract");//合同系统标识
            appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
            appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
            appExtendsData.setExt004(ContractEnum.EnumSection.Transfer.getCode());//合同环节
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
            }*/
            appExtendsData = workFlowService.setExtendsData(crContracttransfer.getTransferId(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), "", "");

            startContext.setExtendsData(appExtendsData);
            String json = JSON.toJSONString(startContext);
            dpsRequest.start(startContext);

        } else {
            //发送流程转让待办消息
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
            AppExtendsData appExtendsData = new AppExtendsData();

           /* appExtendsData.setBusinessId(crContracttransfer.getTransferId());//业务数据Id
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
            appExtendsData.setExt019(ContractEnum.EnumModule.Perform.getMessage());//合同环节
            appExtendsData.setExt020(ContractEnum.EnumSection.Transfer.getCode());//合同转让*/
            //设置扩展字段，合同转让
            appExtendsData = workFlowService.setExtendsData(crContracttransfer.getTransferId(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Transfer.getCode());

            taskMessage.setExtendsData(appExtendsData);
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            workFlowService.saveTempMessage(appExtendsData, ContractEnum.EnumWorkFlow.Trasfer.getCode(), sysUserinfo);//插入临时消息
        }
        if (!StringUtils.isEmpty(contractTrans.transId)) {
            agentRequest.sendMessageForTransactor(crContractbasic.getContractID(), "合同转让");
        }
        return 1;

    }

    /**
     * 获取合同转让详细信息
     */
    @Override
    public DataResult<?> getContractTrans(String transId) {
        if (StringUtils.isEmpty(transId)) {
            throw new NotFoundException("转让ID必填！", Constants.FAILCODE);
        }
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(transId);
        if (crContracttransfer == null) {
            throw new NotFoundException("合同转让记录不存在!", Constants.FAILCODE);
        }
        ContractTrans contractTrans = new ContractTrans();
        contractTrans.transId = crContracttransfer.getTransferId();
        contractTrans.transferNo = crContracttransfer.getTransferNo();
        contractTrans.contractId = crContracttransfer.getContractID();
        contractTrans.transferType = crContracttransfer.getTransferType();//转让类型
        contractTrans.applicant = crContracttransfer.getApplicant();//转让申请方
        contractTrans.transferReason = crContracttransfer.getTransferReason();//转让原因
        contractTrans.offereeId = crContracttransfer.getOffereeId1();
        String offereeName = "";
        if (!StringUtils.isEmpty(crContracttransfer.getOffereeId1())) {
            String[] offereeIds = crContracttransfer.getOffereeId1().split(",");
            for (String offereeId : offereeIds) {
                FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(offereeId);
                if (ffOffereeinfo != null) {
                    offereeName += ffOffereeinfo.getOffereeName() + ",";
                }
            }
        }
        if (!StringUtils.isEmpty(offereeName)) {
            contractTrans.offereeName = offereeName.substring(0, offereeName.length() - 1);
        }

        contractTrans.mySignBodyCode = crContracttransfer.getMySignBodyCode();//我方签约主体ID
        String mySignBodyName = "";
        if (StringUtils.isEmpty(crContracttransfer.getMySignBodyName())) {
            SysOrganiseunitSinging sysOrganiseunitSinging = sysOrganiseunitSingingMapper.selectById(crContracttransfer.getMySignBodyName());
            if (sysOrganiseunitSinging != null) {
                mySignBodyName = sysOrganiseunitSinging.getSingingName();
            }
        } else {
            mySignBodyName = crContracttransfer.getMySignBodyName();
        }
        contractTrans.mySignBodyName = mySignBodyName;//我方签约主体名称
        return DataResult.success(contractTrans);
    }

    /*
     * 获取转让记录
     * */
    @Override
    public DataResult getContractTrasnList(String contractId, Integer pageNum, Integer pageSize) {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContracttransfer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
        /* queryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过的*/
        queryWrapper.lambda().orderByDesc(true, CrContracttransfer::getCreatedDate);
        Page<CrContracttransfer> page = new Page<CrContracttransfer>(pageNum, pageSize);
        IPage<CrContracttransfer> crContracttransferPage = crContracttransferMapper.selectPage(page, queryWrapper);
        for (CrContracttransfer crContracttransfer : crContracttransferPage.getRecords()) {
            JSONObject obj = new JSONObject(true);
            obj.put("transferId", crContracttransfer.getTransferId());
            obj.put("contractId", crContracttransfer.getContractID());
            obj.put("transferNo", crContracttransfer.getTransferNo());//转让编号
            obj.put("transType", crContracttransfer.getTransferType());//转让类型
            obj.put("applicant", crContracttransfer.getApplicant());//转让申请方
            obj.put("applyDate", crContracttransfer.getCreatedDate());//申请时间
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

    /**
     * 获取所有转让记录
     */
    @Override
    public DataResult<?> getContractAllTrasnList(String contractId, Integer pageNum, Integer pageSize) {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContracttransfer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);
        queryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过的
        queryWrapper.lambda().orderByDesc(true, CrContracttransfer::getCreatedDate);
        Page<CrContracttransfer> page = new Page<CrContracttransfer>(pageNum, pageSize);
        IPage<CrContracttransfer> crContracttransferPage = crContracttransferMapper.selectPage(page, queryWrapper);
        for (CrContracttransfer crContracttransfer : crContracttransferPage.getRecords()) {
            JSONObject obj = new JSONObject(true);
            obj.put("transferId", crContracttransfer.getTransferId());
            obj.put("contractId", crContracttransfer.getContractID());
            obj.put("transferNo", crContracttransfer.getTransferNo());//转让编号
            obj.put("transType", crContracttransfer.getTransferType());//转让类型
            obj.put("applicant", crContracttransfer.getApplicant());//转让申请方
            obj.put("applyDate", crContracttransfer.getCreatedDate());//申请时间
            String state = ContractEnum.enumStatusMap.get(crContracttransfer.getState().toString());
            obj.put("state", state);//状态
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

    @Override
    /**
     * 	根据合同获取相对人及我方签约主体
     */
    public DataResult<?> getContractOffAndMysig(String contractId) {
        JSONObject obj = new JSONObject(true);
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同不存在！", Constants.FAILCODE);
        }
        obj.put("mySignBodyCode", crContractinfo.getMySignBodyCode());//我方签约主体Id
        String mySignBodyName = "";
        if (StringUtils.isEmpty(crContractinfo.getMySignBodyName())) {
            SysOrganiseunitSinging sysOrganiseunitSinging = sysOrganiseunitSingingMapper.selectById(crContractinfo.getMySignBodyCode());
            if (sysOrganiseunitSinging != null) {
                mySignBodyName = sysOrganiseunitSinging.getSingingName();
            }
        } else {
            mySignBodyName = crContractinfo.getMySignBodyName();
        }
        obj.put("mySignBodyName", mySignBodyName);//我方签约主体名称
        String offereeId = "";
        String offereeName = "";
        QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractofferee::getContractID, crContractinfo.getContractID());
        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(queryWrapper);
        if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
            for (CrContractofferee crContractofferee : crContractoffereeList) {
                offereeId += crContractofferee.getOffereeID() + ",";
                offereeName += crContractofferee.getOffereeName() + ",";
            }
        }
        if (!StringUtils.isEmpty(offereeId)) {
            offereeId = offereeId.substring(0, offereeId.length() - 1);
        }
        if (!StringUtils.isEmpty(offereeName)) {
            offereeName = offereeName.substring(0, offereeName.length() - 1);
        }
        obj.put("offereeId", offereeId);//原合同相对id
        obj.put("offereeName", offereeName);//原合同相对人名称
        return DataResult.success(obj);
    }

    /*
     * 合同转让签署
     * */
    @Override
    @Transactional
    public boolean contractTransSign(String transContractId, String ourSignatory,
                                     String opponentSignatory, String ourSealedTime, Integer effective,
                                     String effectiveDate, String effectiveElements, String taskId, boolean isSumbit) {

        if (StringUtils.isEmpty(transContractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(transContractId);
        if (crContracttransfer == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        LocalDateTime date = LocalDateTime.now();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContracttransfer.setOurSignatory(ourSignatory);//我方签约人ID
        crContracttransfer.setOpponentSignatory(opponentSignatory);//对方签约人

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(ourSealedTime)) {
            crContracttransfer.setOurSealedTime(LocalDateTime.parse(ourSealedTime, dateTimeFormatter));//签订日期
        }
        if (!StringUtils.isEmpty(effectiveDate)) {
            crContracttransfer.setEffectiveDate(LocalDateTime.parse(effectiveDate, dateTimeFormatter));//生效日期
        }

        crContracttransfer.setEffectiveType(effective);//生效情况 及时生效/其他
        crContracttransfer.setEffectiveElements(effectiveElements);//转让生效要件
        crContracttransfer.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContracttransfer.setModifiedDate(date);
        if (isSumbit) {
            CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContracttransfer.getContractID());
            CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContracttransfer.getContractID());
            if (crContractbasic != null) {
                crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
                crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
                crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//合同审批结束
                crContractbasic.setModifiedDate(date);
                crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractbasicMapper.updateById(crContractbasic);
            }
            //转让签署待办设置完结
            if (!StringUtils.isEmpty(taskId)) {
                dpsRequest.taskMessageComplete(taskId);
                workFlowService.completeMessage(taskId);//临时消息关闭
                //发送合同履行待办消息
                DpsTaskMessage taskMessage = new DpsTaskMessage();
                taskMessage.setBusinessId(crContractbasic.getContractID());
                taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
                taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));
                if (sysUserinfo != null) {
                    taskMessage.setExecutorName(sysUserinfo.getfCname());
                    taskMessage.setExecutorCode(sysUserinfo.getfCode());
                    taskMessage.setBusinessName(crContractbasic.getContractName());
                    taskMessage.setCreatorCode(sysUserinfo.getfCode());
                }
                AppExtendsData appExtendsData = new AppExtendsData();

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
                appExtendsData.setExt019(ContractEnum.EnumSection.Perform.getMessage());//合同环节
                appExtendsData.setExt020(ContractEnum.EnumSection.Perform.getCode());//合同履行*/
                //设置扩展字段，合同履行
                appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                        ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());

                taskMessage.setExtendsData(appExtendsData);
                AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
            }
        }
        return crContracttransferMapper.updateById(crContracttransfer) > 0;
    }


    /*
     * 获取合同转让签署信息
     * */

    @Override
    public DataResult getContractTransSign(String transContractId) {
        if (StringUtils.isEmpty(transContractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(transContractId);
        if (crContracttransfer == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        LocalDateTime date = LocalDateTime.now();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transferId", crContracttransfer.getTransferId());//转让ID
        jsonObject.put("ourSignatory", crContracttransfer.getOurSignatory());//我方签约人ID
        String ourSignatoryName = "";
        if (!StringUtils.isEmpty(crContracttransfer.getOurSignatory())) {
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContracttransfer.getOurSignatory()));
            if (sysUserinfo != null) {
                ourSignatoryName = sysUserinfo.getfCname();
            }
        }
        jsonObject.put("ourSignatoryName", ourSignatoryName);//我方签约人名称
        jsonObject.put("opponentSignatory", crContracttransfer.getOpponentSignatory());//对方签约人
        String ourSealedTime = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (crContracttransfer.getOurSealedTime() != null) {
            ourSealedTime = crContracttransfer.getOurSealedTime().format(dateTimeFormatter);//签订日期
        }
        jsonObject.put("ourSealedTime", ourSealedTime);//签订日期
        String effectiveDate = "";
        if (crContracttransfer.getEffectiveDate() != null) {
            effectiveDate = crContracttransfer.getEffectiveDate().format(dateTimeFormatter);//生效日期
        }
        jsonObject.put("effectiveDate", effectiveDate);//生效日期
        jsonObject.put("effective", crContracttransfer.getEffectiveType());//生效情况 及时生效/其他
        jsonObject.put("effectiveElements", crContracttransfer.getEffectiveElements());//转让生效要件
        jsonObject.put("createdDate", crContracttransfer.getCreatedDate().format(dateTimeFormatter));
        return DataResult.success(jsonObject);
    }

    @Override
    /*
     * 判断是否存在终止流程
     * */
    public boolean isExistContractEnd(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同不存在!", Constants.FAILCODE);
        }
        //履行经办人所在单位/企业
        String orgId = "";
        QueryWrapper<CrContractrununit> crContractrununitQueryWrapper = new QueryWrapper<>();
        crContractrununitQueryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(crContractrununitQueryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                orgId = crContractrununit.getOrgID().toString();
                break;
            }

        } else {
            orgId = crContractbasic.getMainDeptID().toString();
        }
        // 获取orgId信息，找所在企业/单位信息
        //判断是否转让变更流程
        return true;
    }


    /**
     * 合同终止
     */
    @Override
    @Transactional
    public Integer contractEnd(ContractEnd contrctEnd) {
        if (StringUtils.isEmpty(contrctEnd.endId)) {
            throw new NotFoundException("终止ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contrctEnd.contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contrctEnd.contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("数据不存在！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contrctEnd.contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("数据不存在！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        QueryWrapper<CrContractend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractend::getContractID, crContractbasic.getContractID());
        CrContractend crContractend = new CrContractend();
        List<CrContractend> crContractends = crContractendMapper.selectList(queryWrapper);
        String endNo = "";
        if (crContractends.size() > 0) {
            Integer transCount = crContractends.size();
            if (transCount < 10) {
                endNo = crContractbasic.getContractNum() + "-ZZ" + ("0" + transCount.toString());
            } else {
                endNo = crContractbasic.getContractNum() + "-ZZ" + (transCount.toString());
            }

        } else {
            endNo = crContractbasic.getContractNum() + "-ZZ" + "01";
        }
        crContractend.setEndNo(endNo);
        crContractend.setContractEndID(contrctEnd.endId);
        crContractend.setContractID(contrctEnd.contractId);
        crContractend.setEndReason(contrctEnd.endReason);//终止原因 数据字典
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(contrctEnd.endTime)) {
            //0501
            //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          /*  if (!StringUtils.isEmpty(ourSealedTime)) {
                crContractend.setSignDate(LocalDateTime.parse(ourSealedTime, dateTimeFormatter));//签订日期
            }
            if (!StringUtils.isEmpty(effectiveDate)) {
                crContractend.setEffectiveDate(LocalDateTime.parse(effectiveDate, dateTimeFormatter));//生效日期
            }*/
            crContractend.setEndTime(LocalDateTime.parse(contrctEnd.endTime, dateTimeFormatter));//签订日期
        }

        crContractend.setEndDescription(contrctEnd.endRemark);//终止说明
        crContractend.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContractend.setCreatedDate(date);
        crContractend.setOulabel(crContractbasic.getOulabel());
        crContractendMapper.insert(crContractend);
        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Terminate.getCode()));//合同终止
        crContractbasic.setNode(Integer.parseInt(ContractEnum.EnumNode.Check.getCode()));//审批
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));//合同审批中
        crContractbasicMapper.updateById(crContractbasic);
        //触发终止流程
        StartContext startContext = new StartContext();
        startContext.setBusinessId(contrctEnd.endId);
        startContext.setBusinessName(crContractbasic.getContractName());
        List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(), Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                crContractbasic.getMainDeptID().toString());

        if (appWorkflowData != null && appWorkflowData.size() > 0) {
            startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
        }

        startContext.setPropertyModel(ContractEnum.EnumModule.Perform.getCode());//合同变更
        startContext.setSection(ContractEnum.EnumSection.Terminate.getCode());//合同终止
        startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
        startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Terminate.getCode());// 合同终止 发起的流程分类编码
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
        /*startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)*/
        //0501
       /* if (org != null) {
            startContext.setOrganiseId(org.getfId().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
        } else {
            startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)
        }*/
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
            startContext.setUnitName(sysUserinfo.getfCname());
        } else {
            startContext.setUserId(userInfo.getSysUser().getfId().toString());//发起用户id
            startContext.setUserName(userInfo.getSysUser().getfCname());//发起用户名
        }
        startContext.setAppId(cmisDefaultConfig.getAppId());//应用ID

        List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Terminate.getCode());//合同转让
//        metas = setAppMetasData(metas, crContractbasic, crContractinfo);
        metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");
        startContext.setMetasList(metas);
        startContext.setRepeatCheckFlag(1);//是否处理重复审批，一个流程实例中相同审批人不审批两次，默认0不处理 1处理 20200821
        AppExtendsData appExtendsData = new AppExtendsData();

       /* appExtendsData.setBusinessId(crContractend.getContractEndID());//业务数据Id
        appExtendsData.setExt001("contract");//合同系统标识
        appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
        appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
        appExtendsData.setExt004(ContractEnum.EnumSection.Terminate.getCode());//合同环节
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
        }*/
        appExtendsData = workFlowService.setExtendsData(crContractend.getContractEndID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Perform.getCode(), "", "");

        startContext.setExtendsData(appExtendsData);
        //数据封装后请求工作流方法
        dpsRequest.start(startContext);
        return 1;
    }

    /**
     * 获取合同终止详细信息
     */
    @Override
    public DataResult<?> getContractEnd(String endId) {
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("终止ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend == null) {
            throw new NotFoundException("合同终止信息不存在！", Constants.FAILCODE);
        }
        ContractEnd contractEnd = new ContractEnd();
        contractEnd.endId = crContractend.getContractEndID();
        contractEnd.endCode = crContractend.getEndNo();//终止编号
        contractEnd.contractId = crContractend.getContractID();
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
        if (crContractbasic != null) {
            contractEnd.contractName = crContractbasic.getContractName();
        }
        contractEnd.endReason = crContractend.getEndReason();//终止原因
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (crContractend.getEndTime() != null) {
            contractEnd.endTime = crContractend.getEndTime().format(dateTimeFormatter);//终止日期
        }
        contractEnd.endRemark = crContractend.getEndDescription();//终止说明
        return DataResult.success(contractEnd);
    }

    /**
     * 获取合同终止记录
     */
    @Override
    public DataResult<?> getContractEndList(String contractId, Integer pageSize, Integer pageNum) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractend::getContractID, contractId);
        queryWrapper.lambda().isNull(CrContractend::getCategory);//终止操作，排除合同终结情况
        queryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
        /* List<CrContractend> crContractendList = crContractendMapper.selectList(queryWrapper);*/
        Page<CrContractend> page = new Page<CrContractend>(pageNum, pageSize);
        IPage<CrContractend> crContracttransferPage = crContractendMapper.selectPage(page, queryWrapper);
        if (crContracttransferPage.getRecords() != null && crContracttransferPage.getRecords().size() > 0) {
            for (CrContractend crContractend : crContracttransferPage.getRecords()) {
                ContractEnd contractEnd = new ContractEnd();
                contractEnd.endId = crContractend.getContractEndID();
                contractEnd.endCode = crContractend.getEndNo();//终止编号
                contractEnd.isNormal = crContractend.getIsNormal();//异常/正常终结
                contractEnd.contractId = crContractend.getContractID();
                CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
                if (crContractbasic != null) {
                    contractEnd.contractName = crContractbasic.getContractName();
                }
                contractEnd.endReason = crContractend.getEndReason();//终止原因
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                if (crContractend.getEndTime() != null) {
                    contractEnd.endTime = crContractend.getEndTime().format(dateTimeFormatter);//终止日期
                }
                contractEnd.endRemark = crContractend.getEndDescription();//终止说明
                list.add(contractEnd);
            }
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
     * 取消合同终止
     * */
    @Override
    @Transactional
    public boolean cancelContractEnd(String endId) {
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("终止ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend == null) {
            throw new NotFoundException("合同终止信息不存在！", Constants.FAILCODE);
        }
        crContractendMapper.deleteById(crContractend.getContractEndID());
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
        if (crContractbasic != null) {
            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
            crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
            crContractbasicMapper.updateById(crContractbasic);
        }
        //取消终止审批待办数据  同时设置待办消息失效
        dpsRequest.businessdiscard(crContractend.getContractEndID().toString(), crContractbasic.getMainDeptID().toString());
        return true;
    }

    /*
     * 合同终止签署
     * */
    @Override
    public boolean contractEndSign(String endContractId, String ourSignatory,
                                   String opponentSignatory, String ourSealedTime, Integer effective,
                                   String effectiveDate, String effectiveElements, String taskId, boolean isSumbit) {

        if (StringUtils.isEmpty(endContractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endContractId);
        if (crContractend == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        LocalDateTime date = LocalDateTime.now();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crContractend.setOurSignatory(ourSignatory);//我方签约人ID
        crContractend.setOpponentSignatory(opponentSignatory);//对方签约人
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(ourSealedTime)) {
            crContractend.setSignDate(LocalDateTime.parse(ourSealedTime, dateTimeFormatter));//签订日期
        }
        if (!StringUtils.isEmpty(effectiveDate)) {
            crContractend.setEffectiveDate(LocalDateTime.parse(effectiveDate, dateTimeFormatter));//生效日期
        }
        crContractend.setEffectiveType(effective);//生效情况 及时生效/其他
        crContractend.setEffectiveElements(effectiveElements);//终止生效要件
        crContractend.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractend.setModifiedDate(date);
        CrContractbasic crContractbasic = null;
        if (isSumbit) {
            crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
            if (crContractbasic != null) {
                crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
                crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
                crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//合同审批结束
                crContractbasic.setModifiedDate(date);
                crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractbasicMapper.updateById(crContractbasic);
            }
            //终止签署待办设置结束
            if (!StringUtils.isEmpty(taskId)) {
                dpsRequest.taskMessageComplete(taskId);
                //临时消息关闭
                workFlowService.completeMessage(taskId);
            }

            CrContractinfo crContractinfo = crContractinfoMapper.selectById(crContractend.getContractID());
            sendToFinancial(crContractbasic, crContractinfo, FinancialEnum.TERMINATE);
        }

        return crContractendMapper.updateById(crContractend) > 0 ? true : false;
    }

    /*
     * 获取合同终止签署信息
     * */
    @Override
    public DataResult getContractEndSign(String endId) {
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend == null) {
            throw new NotFoundException("未找到相关记录！", Constants.FAILCODE);
        }
        LocalDateTime date = LocalDateTime.now();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("endId", crContractend.getContractEndID());//终止ID
        jsonObject.put("ourSignatory", crContractend.getOurSignatory());//我方签约人ID
        String ourSignatoryName = "";
        if (!StringUtils.isEmpty(crContractend.getOurSignatory())) {
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractend.getOurSignatory()));
            if (sysUserinfo != null) {
                ourSignatoryName = sysUserinfo.getfCname();
            }
        }
        jsonObject.put("ourSignatoryName", ourSignatoryName);//我方签约人名称
        jsonObject.put("opponentSignatory", crContractend.getOpponentSignatory());//对方签约人
        String ourSealedTime = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (crContractend.getSignDate() != null) {
            ourSealedTime = crContractend.getSignDate().format(dateTimeFormatter);//签订日期
        }
        jsonObject.put("ourSealedTime", ourSealedTime);//签订日期
        String effectiveDate = "";
        if (crContractend.getEffectiveDate() != null) {
            effectiveDate = crContractend.getEffectiveDate().format(dateTimeFormatter);//生效日期
        }
        jsonObject.put("effectiveDate", effectiveDate);//生效日期
        jsonObject.put("effective", crContractend.getEffectiveType());//生效情况 及时生效/其他
        jsonObject.put("effectiveElements", crContractend.getEffectiveElements());//转让生效要件
        jsonObject.put("createdDate", crContractend.getCreatedDate().format(dateTimeFormatter));
        return DataResult.success(jsonObject);
    }

    /*
     * 合同变更备案查询
     * */
    @Override
    public DataResult<?> getSealContractChange(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                               String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                               String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum) {
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
        if (mainOrgUserId != null && !StringUtils.isEmpty(mainOrgUserId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, mainOrgUserId.trim());//经办人
        }
        if (type1 != null && !StringUtils.isEmpty(type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, type1.trim());
        }
        if (type2 != null && !StringUtils.isEmpty(type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, type2.trim());
        }
        if (type3 != null && !StringUtils.isEmpty(type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, type3.trim());
        }
        if (type4 != null && !StringUtils.isEmpty(type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, type4.trim());
        }
        if (isMakeSureMoney != null && !StringUtils.isEmpty(isMakeSureMoney)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getIsMakeSureMoney, isMakeSureMoney.trim());
        }
        if (minContractMoney != null && !StringUtils.isEmpty(minContractMoney)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, isMakeSureMoney.trim());//大于等于 金额未转换人民币
        }
        if (maxContractMoney != null && !StringUtils.isEmpty(maxContractMoney)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, maxContractMoney.trim());//小于于等于 金额未转换人民币
        }
        if (isSeal != null && !StringUtils.isEmpty(isSeal)) {
            /*  crContractchangeQueryWrapper.lambda().eq(CrContractchange::getIsSeal, isSeal.trim());*/
           /* if (isSeal.equals("0")) {
                crContractbasicQueryWrapper.isNull("a.IsSeal");
            } else {
                crContractbasicQueryWrapper.eq("a.IsSeal", isSeal.trim());
            }*/
            crContractbasicQueryWrapper.eq("a.IsSeal", isSeal.trim());
        }
        crContractbasicQueryWrapper.eq("a.State", Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        crContractbasicQueryWrapper.orderByAsc("b.RuleSerialNum");
        //crContractchangeQueryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        // crContractbasicQueryWrapper.lambda().or(CrContractchange::getCteatedDate);
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractchangeMapper.selectSealContractChange(page, crContractbasicQueryWrapper);

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractId"));//合同id
                obj.put("contractchangeId", map.get("ContractchangeId"));//变更ID
                obj.put("ccNo", map.get("CCNo"));//变更编号
                String IsSeal = "";
                if (map.get("IsSeal") == null) {
                    IsSeal = "0";
                } else {
                    IsSeal = map.get("IsSeal").toString();
                }
                obj.put("isSeal", IsSeal);//是否备案 0否 1是
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
                if (map.get("type2") != null && !StringUtils.isEmpty(map.get("type2"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("type2").toString()));
                    if (sysDictionary != null) {
                        type2Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type2Name", type2Name);//合同类型2名称


                obj.put("type3", map.get("Type3"));//合同类型3
                String type3Name = "";
                if (map.get("type3") != null && !StringUtils.isEmpty(map.get("type3"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("type3").toString()));
                    if (sysDictionary != null) {
                        type3Name = sysDictionary.getfCnName();
                    }
                }

                obj.put("type3Name", type3Name);//合同类型3名称


                obj.put("type4", map.get("Type4"));//合同类型4
                String type4Name = "";
                if (map.get("type4") != null && !StringUtils.isEmpty(map.get("type4"))) {
                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("type4").toString()));
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

    /*
     * 合同订立备案
     * */
    @Override
    @Transactional
    public boolean contractSeal(List<ContractSeal> crcontactSealList) {
        boolean isSuccess = false;
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        for (ContractSeal crcontractSeal : crcontactSealList) {

            CrContractinfo crContractinfo = crContractinfoMapper.selectById(crcontractSeal.id);//前端传合同ID
            if (crContractinfo == null) {
                crContractinfo = crContractinfoMapper.selectById(crcontractSeal.contractId);//前端传合同ID
            }
            if (crContractinfo != null) {
                crContractinfo.setTextServer(crcontractSeal.textServer);//文本送达人
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                if (!StringUtils.isEmpty(crcontractSeal.serverTime)) {
                    crContractinfo.setServerTime(LocalDateTime.parse(crcontractSeal.serverTime, dateTimeFormatter));//送达时间
                }
                crContractinfo.setTextSource(Integer.parseInt(crcontractSeal.textSource));//文本来源
                if (!StringUtils.isEmpty(crcontractSeal.sealDate)) {
                    crContractinfo.setMySealDate(LocalDateTime.parse(crcontractSeal.sealDate, dateTimeFormatter));//盖章时间
                }
                crContractinfo.setMySealTimes(crcontractSeal.sealTimes);//盖章份数
                crContractinfo.setSealRemark(crcontractSeal.sealRemark);//盖章备注
                crContractinfo.setSealPerSon(userInfo.getSysUser().getfId().toString());
                crContractinfo.setIsSeal(1);
                crContractinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractinfo.setModifiedDate(LocalDateTime.now());
                isSuccess = crContractinfoMapper.updateById(crContractinfo) > 0 ? true : false;
            }
        }
        return isSuccess;
    }

    /**
     * 合同订立备案查询
     */
    @Override
    public DataResult<?> getSealContract(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                         String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                         String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }

        //当前用户
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<Integer> orgIds = userInfoRequest.queryOrgs(String.valueOf(userInfo.getSysUser().getfId()));

        //用户查询授权
        QueryWrapper<AmQuerylicense> amQuerylicenseQueryWrapper = new QueryWrapper<>();
        amQuerylicenseQueryWrapper.lambda().eq(AmQuerylicense::getUserID, userInfo.getSysUser().getfId());
        List<AmQuerylicense> amQuerylicenseList = amQuerylicenseMapper.selectList(amQuerylicenseQueryWrapper);
        if (!CollectionUtils.isEmpty(amQuerylicenseList)) {
            List<Integer> licenseOrgIds = amQuerylicenseList.stream().map(AmQuerylicense::getOrgID).collect(Collectors.toList());
            orgIds.addAll(licenseOrgIds);
        }

        Set<Integer> allOrgIds = new HashSet<>(orgIds);
        Set<Integer> uniqueOrdIds = new HashSet<>(orgIds);
        for (Integer orgId : uniqueOrdIds) {
            List<SysOrganization> sysOrganizations = organizationRequest.queryAllSubOrgs(orgId);
            if (!CollectionUtils.isEmpty(sysOrganizations)) {
                Set<Integer> orgIdSet = sysOrganizations.stream().map(SysOrganization::getfId).collect(Collectors.toSet());
                allOrgIds.addAll(orgIdSet);
            }
        }

//        QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();

        if (allOrgIds.size() > 0) {
            crContractbasicQueryWrapper.lambda().in(CrContractbasic::getMainDeptID, orgIds);
        } else {
            PageData<Object> pageData = new PageData<>();
            pageData.setCurrentPage(pageNum);
            pageData.setPageSize(pageSize);
            pageData.setTotalCount(0);
            pageData.setTotalPage(0);
            pageData.setData(new ArrayList<>());
            return DataResult.success(pageData);
        }

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
            crContractbasicQueryWrapper.lambda().in(CrContractbasic::getMainDeptID, maindeptId);
        }
        if (mainOrgUserId != null && !StringUtils.isEmpty(mainOrgUserId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, mainOrgUserId.trim());//经办人
        }
        if (type1 != null && !StringUtils.isEmpty(type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, type1.trim());
        }
        if (type2 != null && !StringUtils.isEmpty(type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, type2.trim());
        }
        if (type3 != null && !StringUtils.isEmpty(type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, type3.trim());
        }
        if (type4 != null && !StringUtils.isEmpty(type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, type4.trim());
        }
        if (isMakeSureMoney != null && !StringUtils.isEmpty(isMakeSureMoney)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getIsMakeSureMoney, isMakeSureMoney.trim());
        }
        if (minContractMoney != null && !StringUtils.isEmpty(minContractMoney)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, isMakeSureMoney.trim());//大于等于 金额未转换人民币
        }
        if (maxContractMoney != null && !StringUtils.isEmpty(maxContractMoney)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, maxContractMoney.trim());//小于于等于 金额未转换人民币
        }

        if (isSeal != null && !StringUtils.isEmpty(isSeal)) {
            //crContracttransferQueryWrapper.lambda().eq(CrContracttransfer::getIsSeal, isSeal.trim());
            if (isSeal.equals("0")) {
                crContractbasicQueryWrapper.isNull("b.IsSeal ");
            } else if (isSeal.equals("1")) {
                crContractbasicQueryWrapper.eq("b.IsSeal", 1);
            }

        }
        crContractbasicQueryWrapper.eq("a.Status", Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");

        //crContracttransferQueryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        //crContracttransferQueryWrapper.lambda().orderByDesc(CrContracttransfer::getCreatedDate);
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractbasicMapper.getSealContract(page, crContractbasicQueryWrapper);
        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("isSeal", map.get("IsSeal"));//是否备案 0否 1是
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
     * 合同订立备案详情
     *
     * @param infoId
     * @return
     */
    public DataResult<?> getContractSeal(String infoId) {
        ContractSeal contractInfoSeal = new ContractSeal();
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(infoId);
        if (crContractinfo != null) {
            contractInfoSeal.id = crContractinfo.getContractID();
            contractInfoSeal.contractId = crContractinfo.getContractID();
            contractInfoSeal.textServer = crContractinfo.getTextServer();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (crContractinfo.getServerTime() != null) {
                contractInfoSeal.serverTime = crContractinfo.getServerTime().format(dateTimeFormatter);//送达时间
            }
            if (crContractinfo.getTextSource() != null) {
                contractInfoSeal.textSource = crContractinfo.getTextSource().toString();//文本来源
            }
            if (crContractinfo.getMySealDate() != null) {
                contractInfoSeal.sealDate = crContractinfo.getMySealDate().format(dateTimeFormatter);//盖章时间
            }
            contractInfoSeal.sealTimes = crContractinfo.getMySealTimes();
            contractInfoSeal.sealRemark = crContractinfo.getSealRemark();
        }

        return DataResult.success(contractInfoSeal);
    }

    /*
     * 合同变更备案
     * */
    @Override
    @Transactional
    public boolean contractChangeSeal(List<ContractSeal> contractChangeSealList) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        for (ContractSeal contractChangeSeal : contractChangeSealList) {
            CrContractchange crContractchange = crContractchangeMapper.selectById(contractChangeSeal.id);
            if (crContractchange != null) {
                crContractchange.setTextServer(contractChangeSeal.textServer);//文本送达人
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                if (!StringUtils.isEmpty(contractChangeSeal.serverTime)) {
                    crContractchange.setServerTime(LocalDateTime.parse(contractChangeSeal.serverTime, dateTimeFormatter));//送达时间
                }
                crContractchange.setTextSource(Integer.parseInt(contractChangeSeal.textSource));//文本来源
                if (!StringUtils.isEmpty(contractChangeSeal.sealDate)) {
                    crContractchange.setSealDate(LocalDateTime.parse(contractChangeSeal.sealDate, dateTimeFormatter));//盖章时间
                }
                crContractchange.setSealTimes(contractChangeSeal.sealTimes);//盖章份数
                crContractchange.setSealRemark(contractChangeSeal.sealRemark);//盖章备注
                crContractchange.setSealPerson(userInfo.getSysUser().getfId().toString());
                crContractchange.setIsSeal(1);
                crContractchange.setSealInputDate(LocalDateTime.now());
                crContractchange.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractchange.setModifiedDate(LocalDateTime.now());
                crContractchangeMapper.updateById(crContractchange);
            }
        }
        return true;
    }

    /**
     * 获取合同变更备案
     */
    @Override
    @Transactional
    public DataResult<?> getContractChangeSeal(String changeId) {
        ContractSeal contractChangeSeal = new ContractSeal();
        CrContractchange crContractchange = crContractchangeMapper.selectById(changeId);
        if (crContractchange != null) {
            contractChangeSeal.id = crContractchange.getContractChangeID();
            contractChangeSeal.contractId = crContractchange.getContractID();
            contractChangeSeal.textServer = crContractchange.getTextServer();//文本送达人
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(crContractchange.getServerTime()) && (crContractchange.getServerTime() != null)) {
                contractChangeSeal.serverTime = crContractchange.getServerTime().format(dateTimeFormatter);//送达时间
            }
            if (crContractchange.getTextSource() != null) {
                contractChangeSeal.textSource = crContractchange.getTextSource().toString();//文本来源
            }
            if (!StringUtils.isEmpty(crContractchange.getSealDate()) && crContractchange.getSealDate() != null) {
                contractChangeSeal.sealDate = crContractchange.getSealDate().format(dateTimeFormatter);//盖章时间
            }
            contractChangeSeal.sealTimes = crContractchange.getSealTimes();//盖章份数
            contractChangeSeal.sealRemark = crContractchange.getSealRemark();//盖章备注
        }

        return DataResult.success(contractChangeSeal);
    }

    /**
     * 获取转让备案详细信息
     */
    @Override
    public DataResult<?> getContractTransSeal(String transId) {
        ContractSeal contractTransSeal = new ContractSeal();
        CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(transId);
        if (crContracttransfer != null) {
            contractTransSeal.id = crContracttransfer.getTransferId();
            contractTransSeal.contractId = crContracttransfer.getContractID();
            contractTransSeal.textServer = crContracttransfer.getTextServer();//文本送达人
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(crContracttransfer.getServerTime()) && (crContracttransfer.getServerTime() != null)) {
                contractTransSeal.serverTime = crContracttransfer.getServerTime().format(dateTimeFormatter);//送达时间
            }
            if (crContracttransfer.getTextSource() != null) {
                contractTransSeal.textSource = crContracttransfer.getTextSource().toString();//文本来源
            }
            if (!StringUtils.isEmpty(crContracttransfer.getSealDate()) && crContracttransfer.getSealDate() != null) {
                contractTransSeal.sealDate = crContracttransfer.getSealDate().format(dateTimeFormatter);//盖章时间
            }
            contractTransSeal.sealTimes = crContracttransfer.getSealTimes();//盖章份数
            contractTransSeal.sealRemark = crContracttransfer.getSealRemark();//盖章备注
        }

        return DataResult.success(contractTransSeal);
    }

    /**
     * 获取终止备案详细信息
     */
    @Override
    public DataResult<?> getContractEndSeal(String endId) {
        ContractSeal contractEndSeal = new ContractSeal();
        CrContractend crContractend = crContractendMapper.selectById(endId);
        if (crContractend != null) {
            contractEndSeal.id = crContractend.getContractEndID();
            contractEndSeal.contractId = crContractend.getContractID();
            contractEndSeal.textServer = crContractend.getTextServer();//文本送达人
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(crContractend.getServerTime()) && (crContractend.getServerTime() != null)) {
                contractEndSeal.serverTime = crContractend.getServerTime().format(dateTimeFormatter);//送达时间
            }
            if (crContractend.getTextSource() != null) {
                contractEndSeal.textSource = crContractend.getTextSource().toString();//文本来源
            }
            if (!StringUtils.isEmpty(crContractend.getSealDate()) && crContractend.getSealDate() != null) {
                contractEndSeal.sealDate = crContractend.getSealDate().format(dateTimeFormatter);//盖章时间
            }
            contractEndSeal.sealTimes = crContractend.getSealTimes();//盖章份数
            contractEndSeal.sealRemark = crContractend.getSealRemark();//盖章备注
        }

        return DataResult.success(contractEndSeal);
    }


    @Override
    /*
     * 合同转让备案查询
     * */
    public DataResult getSealContractTransfer(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                              String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                              String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
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
        if (mainOrgUserId != null && !StringUtils.isEmpty(mainOrgUserId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, mainOrgUserId.trim());//经办人
        }
        if (type1 != null && !StringUtils.isEmpty(type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, type1.trim());
        }
        if (type2 != null && !StringUtils.isEmpty(type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, type2.trim());
        }
        if (type3 != null && !StringUtils.isEmpty(type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, type3.trim());
        }
        if (type4 != null && !StringUtils.isEmpty(type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, type4.trim());
        }
        if (isMakeSureMoney != null && !StringUtils.isEmpty(isMakeSureMoney)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getIsMakeSureMoney, isMakeSureMoney.trim());
        }
        if (minContractMoney != null && !StringUtils.isEmpty(minContractMoney)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, isMakeSureMoney.trim());//大于等于 金额未转换人民币
        }
        if (maxContractMoney != null && !StringUtils.isEmpty(maxContractMoney)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, maxContractMoney.trim());//小于于等于 金额未转换人民币
        }
        if (isSeal != null && !StringUtils.isEmpty(isSeal)) {
            //crContracttransferQueryWrapper.lambda().eq(CrContracttransfer::getIsSeal, isSeal.trim());
          /*  if (isSeal.equals("0")) {
                crContractbasicQueryWrapper.eq("a.IsSeal", 0);
            } else {
                crContractbasicQueryWrapper.eq("a.IsSeal", isSeal);
            }*/
            crContractbasicQueryWrapper.eq("a.IsSeal", isSeal);

        }
        crContractbasicQueryWrapper.eq("a.State", Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractbasicQueryWrapper.orderByAsc("b.RuleSerialNum");

        //crContracttransferQueryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        //crContracttransferQueryWrapper.lambda().orderByDesc(CrContracttransfer::getCreatedDate);
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContracttransferMapper.selectSealContractTransfer(page, crContractbasicQueryWrapper);
        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("transferId", map.get("TransferId"));//转让ID
                obj.put("transferNo", map.get("TransferNo"));//转让编号
                String IsSeal = "";
                if (map.get("IsSeal") == null) {
                    IsSeal = "0";
                } else {
                    IsSeal = map.get("IsSeal").toString();
                }
                obj.put("isSeal", IsSeal);//是否备案 0否 1是
                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
                obj.put("contractName", map.get("ContractName"));//合同名称
                obj.put("contractNum", map.get("ContractNum"));//合同编码
                obj.put("type1", map.get("Type1"));//合同类型1
                String type1Name = "";
                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
                    SysDictionary sysDictionary = dictionaryRequest.queryDictionary(map.get("Type1").toString());
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

    /*
     * 合同转让备案
     * */
    @Override
    @Transactional
    public boolean contractTransferSeal(List<ContractSeal> contractSealList) {
        boolean isSuccess = false;
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        for (ContractSeal contractSeal : contractSealList) {
            CrContracttransfer crContracttransfer = crContracttransferMapper.selectById(contractSeal.id);
            if (crContracttransfer != null) {
                crContracttransfer.setTextServer(contractSeal.textServer);//文本送达人
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                if (!StringUtils.isEmpty(contractSeal.serverTime)) {
                    crContracttransfer.setServerTime(LocalDateTime.parse(contractSeal.serverTime, dateTimeFormatter));//送达时间
                }
                crContracttransfer.setTextSource(Integer.parseInt(contractSeal.textSource));//文本来源
                if (!StringUtils.isEmpty(contractSeal.sealDate)) {
                    crContracttransfer.setSealDate(LocalDateTime.parse(contractSeal.sealDate, dateTimeFormatter));//盖章时间
                }
                crContracttransfer.setSealTimes(contractSeal.sealTimes);//盖章份数
                crContracttransfer.setSealRemark(contractSeal.sealRemark);//盖章备注
                crContracttransfer.setSealPerson(userInfo.getSysUser().getfId().toString());
                crContracttransfer.setIsSeal(1);
                crContracttransfer.setSealInputDate(LocalDateTime.now());
                crContracttransfer.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContracttransfer.setModifiedDate(LocalDateTime.now());
                isSuccess = crContracttransferMapper.updateById(crContracttransfer) > 0 ? true : false;
            }
        }
        return isSuccess;
    }

    @Override
    /**
     * 	合同终止备案查询
     */
    public DataResult<?> getSealContractEnd(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                            String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                            String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum) {
        if (pageNum <= 0) {
            pageNum = cmisDefaultConfig.getPageNum();
        }
        if (pageSize <= 0) {
            pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContractend> crContractendQueryWrapper = new QueryWrapper<>();
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
        if (mainOrgUserId != null && !StringUtils.isEmpty(mainOrgUserId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, mainOrgUserId.trim());//经办人
        }
        if (type1 != null && !StringUtils.isEmpty(type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, type1.trim());
        }
        if (type2 != null && !StringUtils.isEmpty(type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, type2.trim());
        }
        if (type3 != null && !StringUtils.isEmpty(type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, type3.trim());
        }
        if (type4 != null && !StringUtils.isEmpty(type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, type4.trim());
        }
        if (isMakeSureMoney != null && !StringUtils.isEmpty(isMakeSureMoney)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getIsMakeSureMoney, isMakeSureMoney.trim());
        }
        if (minContractMoney != null && !StringUtils.isEmpty(minContractMoney)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, isMakeSureMoney.trim());//大于等于 金额未转换人民币
        }
        if (maxContractMoney != null && !StringUtils.isEmpty(maxContractMoney)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, maxContractMoney.trim());//小于于等于 金额未转换人民币
        }
        if (isSeal != null && !StringUtils.isEmpty(isSeal)) {
            /*crContractendQueryWrapper.lambda().eq(CrContractend::getIsSeal, isSeal.trim());*/
            /*if (isSeal.equals("0")) {
                crContractbasicQueryWrapper.isNull("a.isSeal");
            } else {
                crContractbasicQueryWrapper.eq("a.isSeal", isSeal.trim());
            }*/
            crContractbasicQueryWrapper.eq("a.isSeal", isSeal.trim());
        }
        crContractbasicQueryWrapper.eq("a.State", Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        crContractbasicQueryWrapper.orderByAsc("b.RuleSerialNum");
        //crContractendQueryWrapper.lambda().eq(CrContractend::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));//审批通过
        //crContractendQueryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(pageNum, pageSize);
        List<HashMap> list = crContractendMapper.selectSealContractEnd(page, crContractbasicQueryWrapper);
        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractId"));//合同id
                obj.put("contractEndId", map.get("ContractEndID"));//终止ID
                obj.put("endNo", map.get("EndNo"));//终止编号
                String IsSeal = "";
                if (map.get("IsSeal") == null) {
                    IsSeal = "0";
                } else {
                    IsSeal = map.get("IsSeal").toString();
                }
                obj.put("isSeal", IsSeal);//是否备案 0否 1是
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

    /*
     * 合同终止备案
     * */
    @Override
    @Transactional
    public boolean contractEndSeal(List<ContractSeal> contractendList) {
        boolean isSuccess = false;
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        for (ContractSeal contractChangeSeal : contractendList) {
            CrContractend crContractend = crContractendMapper.selectById(contractChangeSeal.id);
            if (crContractend != null) {
                crContractend.setTextServer(contractChangeSeal.textServer);//文本送达人
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                if (!StringUtils.isEmpty(contractChangeSeal.serverTime)) {
                    crContractend.setServerTime(LocalDateTime.parse(contractChangeSeal.serverTime, dateTimeFormatter));//送达时间
                }
                crContractend.setTextSource(Integer.parseInt(contractChangeSeal.textSource));//文本来源
                if (!StringUtils.isEmpty(contractChangeSeal.sealDate)) {
                    crContractend.setSealDate(LocalDateTime.parse(contractChangeSeal.sealDate, dateTimeFormatter));//盖章时间
                }
                crContractend.setSealTimes(contractChangeSeal.sealTimes);//盖章份数
                crContractend.setSealRemark(contractChangeSeal.sealRemark);//盖章备注
                crContractend.setSealPerson(userInfo.getSysUser().getfId().toString());
                crContractend.setIsSeal(1);
                crContractend.setSealInputDate(LocalDateTime.now());
                crContractend.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractend.setModifiedDate(LocalDateTime.now());
                isSuccess = crContractendMapper.updateById(crContractend) > 0 ? true : false;
            }
        }
        return isSuccess;
    }

    /**
     * 合同运行简表查询
     */
    @Override
    public DataResult<?> queryContractOverTable(ContractQuery contractQuery) {
        if (contractQuery.pageNum <= 0) {
            contractQuery.pageNum = cmisDefaultConfig.getPageNum();
        }
        if (contractQuery.pageSize <= 0) {
            contractQuery.pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (contractQuery.ruleserialNum != null && !StringUtils.isEmpty(contractQuery.ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, contractQuery.ruleserialNum.trim());
        }
        if (contractQuery.contractName != null && !StringUtils.isEmpty(contractQuery.contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractQuery.contractName.trim());
        }
        if (contractQuery.contractNum != null && !StringUtils.isEmpty(contractQuery.contractNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractQuery.contractNum.trim());
        }


        if (contractQuery.mainDeptId != null && !StringUtils.isEmpty(contractQuery.mainDeptId)) {
            List<Integer> maindeptId = new ArrayList<>();
            maindeptId.add(contractQuery.mainDeptId);
            //获取下级单位
            List<SysOrganization> organizationList = organizationRequest.queryAllSubOrgs(contractQuery.mainDeptId);
            if (organizationList != null && organizationList.size() > 0) {
                for (SysOrganization sysOrganization : organizationList) {
                    maindeptId.add(sysOrganization.getfId());
                }
            }
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, maindeptId);
        }
        if (contractQuery.mainOrgUserId == null && !StringUtils.isEmpty(contractQuery.mainOrgUserId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, contractQuery.mainOrgUserId);//经办人
        }
        if (contractQuery.type1 != null && !StringUtils.isEmpty(contractQuery.type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, contractQuery.type1);
        }
        if (contractQuery.type2 != null && !StringUtils.isEmpty(contractQuery.type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, contractQuery.type2);
        }
        if (contractQuery.type3 != null && !StringUtils.isEmpty(contractQuery.type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, contractQuery.type3);
        }
        if (contractQuery.type4 != null && !StringUtils.isEmpty(contractQuery.type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, contractQuery.type4);
        }
        if (contractQuery.projectId != null && !StringUtils.isEmpty(contractQuery.projectId)) {
            crContractbasicQueryWrapper.eq("b.ProjectId ", " in (select ProjectId from cr_projectinfo) ");
        }
        if (contractQuery.offereeId != null && !StringUtils.isEmpty(contractQuery.offereeId)) {
            crContractbasicQueryWrapper.eq("a.ContractId ", " in (select ContractId from cr_contractofferee ) ");
        }
        if (contractQuery.moneySource1 != null && !StringUtils.isEmpty(contractQuery.moneySource1)) {
            crContractbasicQueryWrapper.eq("b.MoneySource", contractQuery.moneySource1);
        }
        if (contractQuery.moneySource2 != null && !StringUtils.isEmpty(contractQuery.moneySource2)) {
            crContractbasicQueryWrapper.eq("b.MoneySource2", contractQuery.moneySource2);
        }
        if (contractQuery.selectWay1 != null && !StringUtils.isEmpty(contractQuery.selectWay1)) {
            crContractbasicQueryWrapper.eq("b.SelectWay1", contractQuery.selectWay1);
        }
        if (contractQuery.selectWay2 != null && !StringUtils.isEmpty(contractQuery.selectWay2)) {
            crContractbasicQueryWrapper.eq("b.selectWay2", contractQuery.selectWay2);
        }
        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
        }
        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
        }
        if (contractQuery.beginContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.beginContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, contractQuery.beginContractCurrenyAmount);//大于等于 金额未转换人民币
        }
        if (contractQuery.endContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.endContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, contractQuery.endContractCurrenyAmount);
        }
        if (contractQuery.curreny != null && !StringUtils.isEmpty(contractQuery.curreny)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getContractObjectCurrency, contractQuery.curreny);
        }
        if (contractQuery.beginContractAmount != null && !StringUtils.isEmpty(contractQuery.beginContractAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectAmount, contractQuery.beginContractAmount);
        }
        if (contractQuery.endContractAmount != null && !StringUtils.isEmpty(contractQuery.endContractAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectAmount, contractQuery.endContractAmount);
        }
        if (contractQuery.beginMySiginDate != null && !StringUtils.isEmpty(contractQuery.beginMySiginDate)) {
            crContractbasicQueryWrapper.ge("b.MySignDate", contractQuery.beginMySiginDate);
        }
        if (contractQuery.endMySiginDate != null && !StringUtils.isEmpty(contractQuery.endMySiginDate)) {
            crContractbasicQueryWrapper.le("b.MySignDate", contractQuery.endMySiginDate);
        }
        if (contractQuery.beginCreateTime != null && !StringUtils.isEmpty(contractQuery.beginCreateTime)) {
            crContractbasicQueryWrapper.ge("a.CreatedDate", contractQuery.beginCreateTime);
//            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.le("a.CreatedDate", contractQuery.endCreateTime);
//            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getSection, contractQuery.section);
        }
        if (contractQuery.mainOrgId != null && !StringUtils.isEmpty(contractQuery.mainOrgId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgID, contractQuery.mainOrgId);
        }
      /*  crContractbasicQueryWrapper.in(true, "a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()))
                .or().in("true", "a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.LogicDel.getCode()));*/

        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//查询有效合同
        crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同准备
        crContractbasicQueryWrapper.lambda().lt(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结

        List<Integer> orgIds = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //当前用户所在组织机构
        List<SysOrganization> sysOrganizationList = currentUserUtil.currentUserInfo().getSysOrgList();
        if (sysOrganizationList != null && sysOrganizationList.size() > 0) {
            for (SysOrganization sysOrganization : sysOrganizationList) {
                int orgId = sysOrganization.getfId();
                if (!orgIds.contains(orgId)) {
                    orgIds.add(orgId);
                }
                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(orgId);
                if (childOrgList != null && childOrgList.size() > 0) {
                    for (SysOrganization childDept : childOrgList) {
                        if (!orgIds.contains(childDept.getfId())) {
                            orgIds.add(childDept.getfId());
                        }

                    }
                }
            }

        }
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
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, orgIds);
        }

        crContractbasicQueryWrapper.orderByDesc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.pageNum, contractQuery.pageSize);
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
                obj.put("mainOrgName", map.get("MainOrgName"));
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
                QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, map.get("ContractID"));
                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
                List<CrContractchange> contractchangeList = crContractchangeMapper.selectList(crContractchangeQueryWrapper);
                if (contractchangeList != null && contractchangeList.size() > 0) {
                    obj.put("changeCount", contractchangeList.size());//变更次数
                } else {
                    obj.put("changeCount", 0);//变更次数
                }
                //   合同阶段 合同环节 待终结
                if (map.get("Section") != null && map.get("Section").toString().equals(ContractEnum.EnumSection.Terminate.getCode())) {
                    obj.put("isTerminate", 1);//合同是否终止
                } else {
                    obj.put("isTerminate", 0);//合同是否终止
                }
                QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, map.get("ContractID"));
                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
                List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
                if (crContracttransferList != null && crContracttransferList.size() > 0) {
                    obj.put("isTransfer", 1);//合同是否转让
                } else {
                    obj.put("isTransfer", 0);//合同是否转让
                }
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
                obj.put("propertyModelCode", map.get("PropertyModel").toString());

                obj.put("section", section);//合同环节
                obj.put("sectionCode", map.get("Section").toString());

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
                //增加当前处理人 0910
                String executorId = "";
                String executorName = "";
                String contractId = map.get("ContractID").toString();
                PagedList pagedList = dpsRequest.todotaskByBusinessId(contractId);
                List<ExecuteTaskData> executeTaskDataList = pagedList.getExecuteTaskList();
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
                            QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper1 = new QueryWrapper<>();
                            crContracttransferQueryWrapper1.lambda().eq(CrContracttransfer::getContractID, contractId);
                            crContracttransferQueryWrapper1.lambda().orderByDesc(CrContracttransfer::getCreatedDate);
                            List<CrContracttransfer> crContracttransferList1 = crContracttransferMapper.selectList(crContracttransferQueryWrapper1);
                            if (crContracttransferList1 != null && crContracttransferList1.size() > 0) {
                                pagedList = dpsRequest.todotaskByBusinessId(crContracttransferList1.get(0).getTransferId());
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
                    }
                }
                if (!StringUtils.isEmpty(executorId)) {
                    executorId = executorId.substring(0, executorId.length() - 1);
                } else {
//                    executorId = map.get("MainOrgUserID").toString();
                    executorId = "";
                }
                if (!StringUtils.isEmpty(executorName)) {
                    executorName = executorName.substring(0, executorName.length() - 1);
                } else {
//                    executorName = createdByName;
                    executorName = "";
                }
                obj.put("currentUserId", executorId);
                obj.put("currentUserName", executorName);
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
     * 合同查询统计
     */
    @Override
    public DataResult<?> queryContract(ContractQuery contractQuery,Integer ifPerform) {
        if (contractQuery.pageNum <= 0) {
            contractQuery.pageNum = cmisDefaultConfig.getPageNum();
        }
        if (contractQuery.pageSize <= 0) {
            contractQuery.pageSize = cmisDefaultConfig.getPageSize();
        }
        QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (contractQuery.ruleserialNum != null && !StringUtils.isEmpty(contractQuery.ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, contractQuery.ruleserialNum.trim());
        }
        if (contractQuery.contractName != null && !StringUtils.isEmpty(contractQuery.contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractQuery.contractName.trim());
        }
        if (contractQuery.contractNum != null && !StringUtils.isEmpty(contractQuery.contractNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractQuery.contractNum.trim());
        }


        if (contractQuery.mainDeptId != null && !StringUtils.isEmpty(contractQuery.mainDeptId)) {
            List<Integer> maindeptId = new ArrayList<>();
            maindeptId.add(contractQuery.mainDeptId);
            //获取下级单位
            List<SysOrganization> organizationList = organizationRequest.queryAllSubOrgs(contractQuery.mainDeptId);
            if (organizationList != null && organizationList.size() > 0) {
                for (SysOrganization sysOrganization : organizationList) {
                    maindeptId.add(sysOrganization.getfId());
                }
            }
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, maindeptId);
        }

        if (contractQuery.offereeName != null && !StringUtils.isEmpty(contractQuery.offereeName)) {
            List<String> contractIdList = new ArrayList<>();
            QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
            contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getOffereeName, contractQuery.offereeName);
            List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
            if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
                for (CrContractofferee contractofferee : crContractoffereeList) {
                    contractIdList.add(contractofferee.getContractID());
                }
                crContractbasicQueryWrapper.in(" a.contractId ", contractIdList);
            } else {
                contractIdList.add("");
                crContractbasicQueryWrapper.in(" a.contractId ", contractIdList);
            }
            //crContractbasicQueryWrapper.eq("a.ContractID ", " in (select ContractId from cr_contractofferee where OffereeName='"+contractQuery.offereeName.trim()+"') ");
        }

        if (contractQuery.mainOrgUserId != null) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, contractQuery.mainOrgUserId);//经办人
        }
        if (contractQuery.type1 != null && !StringUtils.isEmpty(contractQuery.type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, contractQuery.type1);
        }
        if (contractQuery.type2 != null && !StringUtils.isEmpty(contractQuery.type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, contractQuery.type2);
        }
        if (contractQuery.type3 != null && !StringUtils.isEmpty(contractQuery.type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, contractQuery.type3);
        }
        if (contractQuery.type4 != null && !StringUtils.isEmpty(contractQuery.type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, contractQuery.type4);
        }
        if (contractQuery.projectId != null && !StringUtils.isEmpty(contractQuery.projectId)) {
            crContractbasicQueryWrapper.eq("b.ProjectId ", " in (select ProjectId from cr_projectinfo) ");
        }
        if (contractQuery.offereeId != null && !StringUtils.isEmpty(contractQuery.offereeId)) {
            crContractbasicQueryWrapper.eq("a.ContractId ", " in (select ContractId from cr_contractofferee ) ");
        }
        if (contractQuery.moneySource1 != null && !StringUtils.isEmpty(contractQuery.moneySource1)) {
            crContractbasicQueryWrapper.eq("b.MoneySource", contractQuery.moneySource1);
        }
        if (contractQuery.moneySource2 != null && !StringUtils.isEmpty(contractQuery.moneySource2)) {
            crContractbasicQueryWrapper.eq("b.MoneySource2", contractQuery.moneySource2);
        }
        if (contractQuery.selectWay1 != null && !StringUtils.isEmpty(contractQuery.selectWay1)) {
            crContractbasicQueryWrapper.eq("b.SelectWay1", contractQuery.selectWay1);
        }
        if (contractQuery.selectWay2 != null && !StringUtils.isEmpty(contractQuery.selectWay2)) {
            crContractbasicQueryWrapper.eq("b.selectWay2", contractQuery.selectWay2);
        }
        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
        }
//        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
//            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
//        }
        if (contractQuery.beginContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.beginContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, contractQuery.beginContractCurrenyAmount);//大于等于 金额未转换人民币
        }
        if (contractQuery.endContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.endContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, contractQuery.endContractCurrenyAmount);
        }
        if (contractQuery.curreny != null && !StringUtils.isEmpty(contractQuery.curreny)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getContractObjectCurrency, contractQuery.curreny);
        }
        if (contractQuery.beginContractAmount != null && !StringUtils.isEmpty(contractQuery.beginContractAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectAmount, contractQuery.beginContractAmount);
        }
        if (contractQuery.endContractAmount != null && !StringUtils.isEmpty(contractQuery.endContractAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectAmount, contractQuery.endContractAmount);
        }
        if (contractQuery.beginMySiginDate != null && !StringUtils.isEmpty(contractQuery.beginMySiginDate)) {
            crContractbasicQueryWrapper.ge("b.MySignDate", contractQuery.beginMySiginDate);
        }
        if (contractQuery.endMySiginDate != null && !StringUtils.isEmpty(contractQuery.endMySiginDate)) {
            crContractbasicQueryWrapper.le("b.MySignDate", contractQuery.endMySiginDate);
        }
        if (contractQuery.beginCreateTime != null && !StringUtils.isEmpty(contractQuery.beginCreateTime)) {
            crContractbasicQueryWrapper.ge("a.CreatedDate", contractQuery.beginCreateTime);
//            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.le("a.CreatedDate", contractQuery.endCreateTime);
//            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getSection, contractQuery.section);
        }
        if (ifPerform.toString().equals ("30")) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getSection, contractQuery.section);
        }

        if (contractQuery.mainOrgId != null && !StringUtils.isEmpty(contractQuery.mainOrgId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgID, contractQuery.mainOrgId);
        }
        crContractbasicQueryWrapper.ne("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));//查询有效合同
       /* crContractbasicQueryWrapper.lambda().gt(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同准备
        crContractbasicQueryWrapper.lambda().lt(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
*/
        List<Integer> orgIds = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //当前用户所在组织机构
        List<SysOrganization> sysOrganizationList = currentUserUtil.currentUserInfo().getSysOrgList();
        if (sysOrganizationList != null && sysOrganizationList.size() > 0) {
            for (SysOrganization sysOrganization : sysOrganizationList) {
                int orgId = sysOrganization.getfId();
                if (!orgIds.contains(orgId)) {
                    orgIds.add(orgId);
                }
                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(orgId);
                if (childOrgList != null && childOrgList.size() > 0) {
                    for (SysOrganization childDept : childOrgList) {
                        if (!orgIds.contains(childDept.getfId())) {
                            orgIds.add(childDept.getfId());
                        }

                    }
                }
            }

        }
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
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, orgIds);
        }

        crContractbasicQueryWrapper.orderByDesc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.pageNum, contractQuery.pageSize);
        /*        userInfoRequest.queryAll();*/
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
                obj.put("mainOrgName", map.get("MainOrgName"));
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
                 /*   SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
                    if (sysDictionary != null) {
                        moneyFlowName = sysDictionary.getfCnName();
                    }*/
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
                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额（含税合同金额）
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
                obj.put("mysealDate", map.get("MySealDate"));//合同订立备案日期
                obj.put("settleDeadline", map.get("SettleDeadline"));  //结算期限
                QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, map.get("ContractID"));
                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
                List<CrContractchange> contractchangeList = crContractchangeMapper.selectList(crContractchangeQueryWrapper);
                if (contractchangeList != null && contractchangeList.size() > 0) {
                    obj.put("changeCount", contractchangeList.size());//变更次数
                } else {
                    obj.put("changeCount", 0);//变更次数
                }
                //   合同阶段 合同环节 待终结
                if (map.get("Section") != null && map.get("Section").toString().equals(ContractEnum.EnumSection.Terminate.getCode())) {
                    obj.put("isTerminate", 1);//合同是否终止
                } else {
                    obj.put("isTerminate", 0);//合同是否终止
                }
                QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, map.get("ContractID"));
                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
                List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
                if (crContracttransferList != null && crContracttransferList.size() > 0) {
                    obj.put("isTransfer", 1);//合同是否转让
                } else {
                    obj.put("isTransfer", 0);//合同是否转让
                }
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

                String moneySource1 = "";
                String moneySource2 = "";
                if (map.get("MoneySource") != null && !StringUtils.isEmpty(map.get("MoneySource"))) {
                    SysDictionarycategory ms1 = dictionaryRequest.queryCategoryById((Integer) map.get("MoneySource"));
                    if (ms1 != null) {
                        moneySource1 = ms1.getfCnName();
                    }
                }
                if (map.get("MoneySource2") != null && !StringUtils.isEmpty(map.get("MoneySource2"))) {
                    SysDictionarycategory ms2 = dictionaryRequest.queryCategoryById(Integer.parseInt((String) map.get("MoneySource2")));
                    if (ms2 != null) {
                        moneySource2 = ms2.getfCnName();
                    }
                }


                obj.put("moneySource1", moneySource1);
                obj.put("moneySource2", moneySource2);


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
     * 合同查询统计
     */
    @Override
    public List<ExportContractVo> queryContractExport(ContractQuery contractQuery) {

        QueryWrapper<CrContracttransfer> crContracttransferQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
        if (contractQuery.ruleserialNum != null && !StringUtils.isEmpty(contractQuery.ruleserialNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getRuleSerialNum, contractQuery.ruleserialNum.trim());
        }
        if (contractQuery.contractName != null && !StringUtils.isEmpty(contractQuery.contractName)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractQuery.contractName.trim());
        }
        if (contractQuery.contractNum != null && !StringUtils.isEmpty(contractQuery.contractNum)) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractQuery.contractNum.trim());
        }


        if (contractQuery.mainDeptId != null && !StringUtils.isEmpty(contractQuery.mainDeptId)) {
            List<Integer> maindeptId = new ArrayList<>();
            maindeptId.add(contractQuery.mainDeptId);
            //获取下级单位
            List<SysOrganization> organizationList = organizationRequest.queryAllSubOrgs(contractQuery.mainDeptId);
            if (organizationList != null && organizationList.size() > 0) {
                for (SysOrganization sysOrganization : organizationList) {
                    maindeptId.add(sysOrganization.getfId());
                }
            }
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, maindeptId);
        }

        if (contractQuery.offereeName != null && !StringUtils.isEmpty(contractQuery.offereeName)) {
            List<String> contractIdList = new ArrayList<>();
            QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
            contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getOffereeName, contractQuery.offereeName);
            List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
            if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
                for (CrContractofferee contractofferee : crContractoffereeList) {
                    contractIdList.add(contractofferee.getContractID());
                }
                crContractbasicQueryWrapper.in(" a.contractId ", contractIdList);
            } else {
                contractIdList.add("");
                crContractbasicQueryWrapper.in(" a.contractId ", contractIdList);
            }
            //crContractbasicQueryWrapper.eq("a.ContractID ", " in (select ContractId from cr_contractofferee where OffereeName='"+contractQuery.offereeName.trim()+"') ");
        }

        if (contractQuery.mainOrgUserId != null) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgUserID, contractQuery.mainOrgUserId);//经办人
        }
        if (contractQuery.type1 != null && !StringUtils.isEmpty(contractQuery.type1)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType1, contractQuery.type1);
        }
        if (contractQuery.type2 != null && !StringUtils.isEmpty(contractQuery.type2)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType2, contractQuery.type2);
        }
        if (contractQuery.type3 != null && !StringUtils.isEmpty(contractQuery.type3)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType3, contractQuery.type3);
        }
        if (contractQuery.type4 != null && !StringUtils.isEmpty(contractQuery.type4)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getType4, contractQuery.type4);
        }
        if (contractQuery.projectId != null && !StringUtils.isEmpty(contractQuery.projectId)) {
            crContractbasicQueryWrapper.eq("b.ProjectId ", " in (select ProjectId from cr_projectinfo) ");
        }
        if (contractQuery.offereeId != null && !StringUtils.isEmpty(contractQuery.offereeId)) {
            crContractbasicQueryWrapper.eq("a.ContractId ", " in (select ContractId from cr_contractofferee ) ");
        }
        if (contractQuery.moneySource1 != null && !StringUtils.isEmpty(contractQuery.moneySource1)) {
            crContractbasicQueryWrapper.eq("b.MoneySource", contractQuery.moneySource1);
        }
        if (contractQuery.moneySource2 != null && !StringUtils.isEmpty(contractQuery.moneySource2)) {
            crContractbasicQueryWrapper.eq("b.MoneySource2", contractQuery.moneySource2);
        }
        if (contractQuery.selectWay1 != null && !StringUtils.isEmpty(contractQuery.selectWay1)) {
            crContractbasicQueryWrapper.eq("b.SelectWay1", contractQuery.selectWay1);
        }
        if (contractQuery.selectWay2 != null && !StringUtils.isEmpty(contractQuery.selectWay2)) {
            crContractbasicQueryWrapper.eq("b.selectWay2", contractQuery.selectWay2);
        }
        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
        }
//        if (contractQuery.moneyFlow != null && !StringUtils.isEmpty(contractQuery.moneyFlow)) {
//            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMoneyFlow, contractQuery.moneyFlow);
//        }
        if (contractQuery.beginContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.beginContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectMoney, contractQuery.beginContractCurrenyAmount);//大于等于 金额未转换人民币
        }
        if (contractQuery.endContractCurrenyAmount != null && !StringUtils.isEmpty(contractQuery.endContractCurrenyAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectMoney, contractQuery.endContractCurrenyAmount);
        }
        if (contractQuery.curreny != null && !StringUtils.isEmpty(contractQuery.curreny)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getContractObjectCurrency, contractQuery.curreny);
        }
        if (contractQuery.beginContractAmount != null && !StringUtils.isEmpty(contractQuery.beginContractAmount)) {
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getContractObjectAmount, contractQuery.beginContractAmount);
        }
        if (contractQuery.endContractAmount != null && !StringUtils.isEmpty(contractQuery.endContractAmount)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getContractObjectAmount, contractQuery.endContractAmount);
        }
        if (contractQuery.beginMySiginDate != null && !StringUtils.isEmpty(contractQuery.beginMySiginDate)) {
            crContractbasicQueryWrapper.ge("b.MySignDate", contractQuery.beginMySiginDate);
        }
        if (contractQuery.endMySiginDate != null && !StringUtils.isEmpty(contractQuery.endMySiginDate)) {
            crContractbasicQueryWrapper.le("b.MySignDate", contractQuery.endMySiginDate);
        }
        if (contractQuery.beginCreateTime != null && !StringUtils.isEmpty(contractQuery.beginCreateTime)) {
            crContractbasicQueryWrapper.ge("a.CreatedDate", contractQuery.beginCreateTime);
//            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.le("a.CreatedDate", contractQuery.endCreateTime);
//            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getSection, contractQuery.section);
        }
        if (contractQuery.mainOrgId != null && !StringUtils.isEmpty(contractQuery.mainOrgId)) {
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainOrgID, contractQuery.mainOrgId);
        }
        crContractbasicQueryWrapper.ne("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));//查询有效合同
       /* crContractbasicQueryWrapper.lambda().gt(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Prepare.getCode()));//合同准备
        crContractbasicQueryWrapper.lambda().lt(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
*/
        List<Integer> orgIds = new ArrayList<>();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        //当前用户所在组织机构
        List<SysOrganization> sysOrganizationList = currentUserUtil.currentUserInfo().getSysOrgList();
        if (sysOrganizationList != null && sysOrganizationList.size() > 0) {
            for (SysOrganization sysOrganization : sysOrganizationList) {
                int orgId = sysOrganization.getfId();
                if (!orgIds.contains(orgId)) {
                    orgIds.add(orgId);
                }
                List<SysOrganization> childOrgList = organizationRequest.queryAllSubOrgs(orgId);
                if (childOrgList != null && childOrgList.size() > 0) {
                    for (SysOrganization childDept : childOrgList) {
                        if (!orgIds.contains(childDept.getfId())) {
                            orgIds.add(childDept.getfId());
                        }

                    }
                }
            }

        }
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
            crContractbasicQueryWrapper.lambda().in(true, CrContractbasic::getMainDeptID, orgIds);
        }

        crContractbasicQueryWrapper.orderByDesc("a.CreatedDate");
        List<ExportContractVo> exportContractVos = new ArrayList<>();

        log.info("lixianming"+crContractbasicQueryWrapper.getSqlSelect());

        List<ExportContractVo> list = crContractbasicMapper.queryContractExport(crContractbasicQueryWrapper);

//        Integer[] mainDeptIDS = list.stream().map(i -> (Integer) i.get("MainDeptID")).toArray(Integer[]::new);
//        List<SysOrganization> sysOrganizations = organizationRequest.queryOrgByIdBatch(mainDeptIDS);
//        Map<Integer, SysOrganization> mainDeptMap = sysOrganizations.stream().
//                collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k1));
//        if (list != null && list.size() > 0) {
//            for (HashMap map : list) {
//                ExportContractVo exportContractVo = new ExportContractVo();
//
//                String type1Name = "";
//                if (map.get("Type1") != null && !StringUtils.isEmpty(map.get("Type1"))) {
//                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type1").toString()));
//                    if (sysDictionary != null) {
//                        type1Name = sysDictionary.getfCnName();
//                    }
//                }
//
//
//                String type2Name = "";
//                if (map.get("Type2") != null && !StringUtils.isEmpty(map.get("Type2"))) {
//                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type2").toString()));
//                    if (sysDictionary != null) {
//                        type2Name = sysDictionary.getfCnName();
//                    }
//                }
//                String type3Name = "";
//                if (map.get("Type3") != null && !StringUtils.isEmpty(map.get("Type3"))) {
//                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type3").toString()));
//                    if (sysDictionary != null) {
//                        type3Name = sysDictionary.getfCnName();
//                    }
//                }
//                String type4Name = "";
//                if (map.get("Type4") != null && !StringUtils.isEmpty(map.get("Type4"))) {
//                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("Type4").toString()));
//                    if (sysDictionary != null) {
//                        type4Name = sysDictionary.getfCnName();
//                    }
//                }
//
//                String typeName = type1Name;
//                if (!"".equals(type2Name)) {
//                    typeName = typeName+"/"+type2Name;
//                }
//                if (!"".equals(type3Name)) {
//                    typeName = typeName+"/"+type3Name;
//                }
//                if (!"".equals(type4Name)) {
//                    typeName = typeName+"/"+type4Name;
//                }
//                String projectName = "";
//                if (map.get("ProjectID") != null && !StringUtils.isEmpty(map.get("ProjectID"))) {
//                    CrProjectinfo crProjectinfo = crProjectinfoMapper.selectById(map.get("ProjectID").toString());
//                    if (crProjectinfo != null) {
//                        projectName = crProjectinfo.getProjectName();
//                    }
//                }
//
//                //合同相对人信息
//                String offereeId = "";
//                String offereeName = "";
//                QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
//                contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, map.get("ContractID"));
//                List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
//                if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
//                    for (CrContractofferee crContractofferee : crContractoffereeList) {
//                        FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(crContractofferee.getOffereeID());
//                        if (ffOffereeinfo != null) {
//                            offereeId += ffOffereeinfo.getOffereeId() + ";";
//                            offereeName += ffOffereeinfo.getOffereeName() + ";";
//                        }
//                    }
//                }
//                if (!StringUtils.isEmpty(offereeName)) {
//                    offereeName = offereeName.substring(0, offereeName.length() - 1);
//                }
//                Integer mainDeptID = (Integer) map.get("MainDeptID");
//
//
//                String moneyFlowName = "";
//                if (map.get("MoneyFlow") != null && !StringUtils.isEmpty(map.get("MoneyFlow"))) {
//                 /*   SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("MoneyFlow").toString()));
//                    if (sysDictionary != null) {
//                        moneyFlowName = sysDictionary.getfCnName();
//                    }*/
//                }
//                String planMoneyCurrencyName = "";
//                if (map.get("PlanMoneyCurrency") != null && !StringUtils.isEmpty(map.get("PlanMoneyCurrency"))) {
//                    SysDictionarycategory sysDictionary = dictionaryRequest.queryCategoryById(Integer.parseInt(map.get("PlanMoneyCurrency").toString()));
//                    if (sysDictionary != null) {
//                        planMoneyCurrencyName = sysDictionary.getfCnName();
//                    }
//                }
//
//                String IsInnerContract="否";
//                if (map.get("IsInnerContract").toString().equals("0")) {
//                    IsInnerContract = "是";
//                }
//
//                String mySignPersonName = "";
//                if (map.get("MySignPersonName") == null || StringUtils.isEmpty(map.get("MySignPersonName"))) {
//                    if (map.get("MySignPerson") != null && !StringUtils.isEmpty(map.get("MySignPerson").toString())) {
//                        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MySignPerson").toString()));
//                        if (sysUserinfo != null) {
//                            mySignPersonName = sysUserinfo.getfCname();
//                        }
//                    }
//
//                } else {
//                    mySignPersonName = map.get("MySignPersonName").toString();
//                }
//
//                String createdByName = "";
//                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
//                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
//                    if (sysUserinfo != null) {
//                        createdByName = sysUserinfo.getfCname();
//                    }
//                }
//
//                String changeCount="0";
//                QueryWrapper<CrContractchange> crContractchangeQueryWrapper = new QueryWrapper<>();
//                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, map.get("ContractID"));
//                crContractchangeQueryWrapper.lambda().eq(CrContractchange::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
//                List<CrContractchange> contractchangeList = crContractchangeMapper.selectList(crContractchangeQueryWrapper);
//                if (contractchangeList != null && contractchangeList.size() > 0) {
//                    changeCount=String.valueOf(contractchangeList.size());//变更次数
//                }
//                //   合同阶段 合同环节 待终结
//                String isTerminate="否";
//                if (map.get("Section") != null && map.get("Section").toString().equals(ContractEnum.EnumSection.Terminate.getCode())) {
//                    isTerminate="是";//合同是否终止
//                }
//
//                String isTransfer="否";
//                QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
//                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, map.get("ContractID"));
//                contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getState, Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
//                List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
//                if (crContracttransferList != null && crContracttransferList.size() > 0) {
//                    isTransfer="是";
//                }
//                String propertyModel = "";//合同模块
//                String section = "";//合同环节
//                if (map.get("PropertyModel") != null) {
//                    propertyModel = map.get("PropertyModel").toString();
//                }
//                if (map.get("Section") != null) {
//                    section = map.get("Section").toString();
//                }
//                if (!StringUtils.isEmpty(propertyModel)) {
//                    propertyModel = ContractEnum.enumModuleMap.get(propertyModel);
//                }
//                if (!StringUtils.isEmpty(section)) {
//                    section = ContractEnum.enumSectionMap.get(section);
//                }
//                String moneySource1 = "";
//                String moneySource2 = "";
//                if (map.get("MoneySource") != null && !StringUtils.isEmpty(map.get("MoneySource"))) {
//                    SysDictionarycategory ms1 = dictionaryRequest.queryCategoryById((Integer) map.get("MoneySource"));
//                    if (ms1 != null) {
//                        moneySource1 = ms1.getfCnName();
//                    }
//                }
//                if (map.get("MoneySource2") != null && !StringUtils.isEmpty(map.get("MoneySource2"))) {
//                    SysDictionarycategory ms2 = dictionaryRequest.queryCategoryById(Integer.parseInt((String) map.get("MoneySource2")));
//                    if (ms2 != null) {
//                        moneySource2 = ms2.getfCnName();
//                    }
//                }
//
//                String moneySource = moneySource1;
//                if (!"".equals(moneySource2)) {
//                    moneySource = moneySource+"/"+moneySource2;
//                }
//
//
//                String isFinality="是";
//                if (map.get("FinalityDate") == null || StringUtils.isEmpty(map.get("FinalityDate"))) {
//                    isFinality="否";
//                }
//                if (map.get("RuleSerialNum") != null && !StringUtils.isEmpty(map.get("RuleSerialNum"))) {
//                    exportContractVo.setRuleserialNum(map.get("RuleSerialNum").toString());//合同序号
//                }
//                if (map.get("ContractNum") != null && !StringUtils.isEmpty(map.get("ContractNum"))) {
//                    exportContractVo.setContractNum(map.get("ContractNum").toString());//合同编码
//                }
//                if (map.get("ContractName") != null && !StringUtils.isEmpty(map.get("ContractName"))) {
//                    exportContractVo.setContractName(map.get("ContractName").toString());//合同名称
//                }
//                exportContractVo.setTypeName(typeName);//合同类型
//                exportContractVo.setProjectName(projectName);//所属项目
//                exportContractVo.setOffereeName(offereeName);//相对人名称
//                exportContractVo.setMainDeptName(mainDeptMap.get(mainDeptID).getfName());//主办单位
//                if (map.get("PlanMoney") != null && !StringUtils.isEmpty(map.get("PlanMoney"))) {
//                    exportContractVo.setPlanMoney(map.get("PlanMoney").toString());//资金流向id
//                }
//                if (map.get("contractObjectAmount") != null && !StringUtils.isEmpty(map.get("contractObjectAmount"))) {
//                    exportContractVo.setContractObjectAmount(map.get("contractObjectAmount").toString());//转换成人民币金额（含税合同金额）
//                }
//                if (map.get("NoTaxAmount") != null && !StringUtils.isEmpty(map.get("NoTaxAmount"))) {
//                    exportContractVo.setContractNoTaxAmount(map.get("NoTaxAmount").toString());//不含税额
//                }
//                if (map.get("TaxAmount") != null && !StringUtils.isEmpty(map.get("TaxAmount"))) {
//                    exportContractVo.setContractTaxAmount(map.get("TaxAmount").toString());//税额
//                }
//                exportContractVo.setMainOrgUserName(createdByName);
//                exportContractVo.setMoneyFlowName(moneyFlowName);
//                exportContractVo.setMoneySource(moneySource);
//                exportContractVo.setIsInnerContract(IsInnerContract);
//                exportContractVo.setMySignPersonName(mySignPersonName);//我方签约代表
//                if (map.get("CreatedDate") != null && !StringUtils.isEmpty(map.get("CreatedDate"))) {
//                    exportContractVo.setCreatedDate(substring(map.get("CreatedDate").toString(),0,10));
//                }
//                if (map.get("FinalityDate") != null && !StringUtils.isEmpty(map.get("FinalityDate"))) {
//                    exportContractVo.setFinalityDate(substring(map.get("FinalityDate").toString(),0,10));
//                }
//                if (map.get("MySignDate") != null && !StringUtils.isEmpty(map.get("MySignDate"))) {
//                    exportContractVo.setMySignDate(substring(map.get("MySignDate").toString(),0,10));//签订时间
//                }
//                if (map.get("MySealDate") != null && !StringUtils.isEmpty(map.get("MySealDate"))) {
//                    exportContractVo.setMySealDate(substring(map.get("MySealDate").toString(),0,10));//签署备案时间
//                }
//                exportContractVo.setChangeCount(changeCount);//合同变更次数
//                exportContractVo.setIsTerminate(isTerminate);
//                exportContractVo.setIsTransfer(isTransfer);
//                exportContractVo.setPropertyModel(propertyModel);//合同阶段
//                exportContractVo.setSection(section);
//                exportContractVo.setIsFinality(isFinality);//合同是否终结
//
//                exportContractVos.add(exportContractVo);
//            }
//        }

        return list;
    }



    /*
     * 合同履行操作权限
     * */
    public DataResult performOperate(String contractId) {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic != null) {
            QueryWrapper<CrContractrununit> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContractrununit::getContractID, contractId);
            List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(queryWrapper);
            if (crContractrununitList != null && crContractrununitList.size() > 0) {
                Integer userId = userInfo.getSysUser().getfId();//当前登陆人
                CrContractrununit crContractrununit = crContractrununitList.get(0);
                if (userId == Integer.parseInt(crContractrununit.getUserID())) {
                    //合同履行人
                    jsonObject.put("isPerform", "1");//是否可以履行操作
                } else {
                    jsonObject.put("isPerform", "0");//是否可以履行操作
                }
                if (userId == Integer.parseInt(crContractrununit.getPayUserId())) {
                    //合同付款操作
                    jsonObject.put("isPay", "1");//是否可以合同付款操作
                } else {
                    jsonObject.put("isPay", "0");//是否可以合同付款操作
                }
                if (userId == Integer.parseInt(crContractrununit.getFinalityUserId())) {
                    //合同终结操作
                    jsonObject.put("isFinality", "1");//是否可以合同终结操作
                } else {
                    jsonObject.put("isFinality", "0");//是否可以合同终结操作
                }
            }
            //控制按钮是否展示
            //合同是否正在变更
            boolean isShow = true;
            QueryWrapper<CrContractchange> contractchangeQueryWrapper = new QueryWrapper<>();
            contractchangeQueryWrapper.lambda().eq(CrContractchange::getContractID, contractId);
            contractchangeQueryWrapper.lambda().ne(CrContractchange::getState, ContractEnum.EnumStatus.Approved.getCode());
            List<CrContractchange> crContractchangeList = crContractchangeMapper.selectList(contractchangeQueryWrapper);
            if (crContractchangeList != null && crContractchangeList.size() > 0) {
                isShow = false;
            }
            //合同是否存在转让
            QueryWrapper<CrContracttransfer> contracttransferQueryWrapper = new QueryWrapper<>();
            contracttransferQueryWrapper.lambda().eq(CrContracttransfer::getContractID, contractId);

            contracttransferQueryWrapper.lambda().ne(CrContracttransfer::getState, ContractEnum.EnumStatus.Approved.getCode());
            List<CrContracttransfer> crContracttransferList = crContracttransferMapper.selectList(contracttransferQueryWrapper);
            if (crContracttransferList.size() > 0) {
                isShow = false;
            }
            //合同终止
            QueryWrapper<CrContractend> contractendQueryWrapper = new QueryWrapper<>();
            contractendQueryWrapper.lambda().eq(CrContractend::getContractID, contractId);
            List<CrContractend> crContractendList = crContractendMapper.selectList(contractendQueryWrapper);
            if (crContractendList.size() > 0) {
                isShow = false;
            }
            //合同发案
            QueryWrapper<CrContractcase> crContractcaseQueryWrapper = new QueryWrapper<>();
            crContractcaseQueryWrapper.lambda().eq(CrContractcase::getContractID, contractId);
            crContractcaseQueryWrapper.lambda().eq(CrContractcase::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//
            List<CrContractcase> crContractpauseList = crContractcaseMapper.selectList(crContractcaseQueryWrapper);
            if (crContractpauseList != null && crContractpauseList.size() > 0) {
                isShow = false;
            }
            //合同履行完毕
            if (crContractbasic.getPropertyModel() == Integer.parseInt(ContractEnum.EnumModule.Finality.getCode())
                    && crContractbasic.getSection() == Integer.parseInt(ContractEnum.EnumSection.Finality.getCode())) {
                isShow = false;
            }
            if (isShow) {
                jsonObject.put("isShow", 1);//履行选择按钮是否展示
            } else {
                jsonObject.put("isShow", 0);//履行选择按钮是否展示
            }
        }
        return DataResult.success(jsonObject);
    }

    @Override
    public boolean savePayAlert(PayAlertAddVo addVo) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        int cnt = 0;
        CrContractpayalert crContractpayalert = new CrContractpayalert();
        BeanUtils.copyProperties(addVo, crContractpayalert);
        if (addVo.getfId() == null) {
            //新增
            crContractpayalert.setfCreateUser(userInfo.getSysUser().getfId());
            crContractpayalert.setfCreateTime(LocalDateTime.now());
            crContractpayalert.setfState(0);
            crContractpayalert.setfLogicdel(0);
            LocalDate localDate = crContractpayalert.getfDate().minusDays(crContractpayalert.getfDay());
            crContractpayalert.setfActualDate(localDate);

            cnt = contractpayalertMapper.insert(crContractpayalert);
        } else {
            //更新
            BeanUtils.copyProperties(addVo, crContractpayalert);
            crContractpayalert.setfModifyUser(userInfo.getSysUser().getfId());
            crContractpayalert.setfModifyTime(LocalDateTime.now());
            LocalDate localDate = crContractpayalert.getfDate().minusDays(crContractpayalert.getfDay());
            crContractpayalert.setfActualDate(localDate);

            cnt = contractpayalertMapper.updateById(crContractpayalert);
        }

        return cnt > 0;
    }

    @Override
    public boolean closePayAlert(Integer fId, Integer state) {
        CrContractpayalert crContractpayalert = new CrContractpayalert();
        crContractpayalert.setfState(state);
        crContractpayalert.setfId(fId);

        int i = contractpayalertMapper.updateById(crContractpayalert);

        return i > 0;
    }

    @Override
    public PageData<PayAlertListVo> queryPayAlert(PayAlertQueryVo queryVo) {

        if (queryVo.getPageNum() == null)
            queryVo.setPageNum(1);
        if (queryVo.getPageSize() == null)
            queryVo.setPageSize(20);

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        QueryWrapper<CrContractpayalert> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().
                eq(CrContractpayalert::getfLogicdel, 0).
                eq(CrContractpayalert::getfCreateUser, userInfo.getSysUser().getfId()).
                eq(!StringUtils.isEmpty(queryVo.getfPayType()), CrContractpayalert::getfPayType, queryVo.getfPayType()).
                ge(!StringUtils.isEmpty(queryVo.getStartDate()), CrContractpayalert::getfDate, queryVo.getStartDate()).
                le(!StringUtils.isEmpty(queryVo.getEndDate()), CrContractpayalert::getfDate, queryVo.getEndDate()).
                orderByDesc(CrContractpayalert::getfCreateTime);

        List<CrContractbasic> crContractbasics = null;
        if (!StringUtils.isEmpty(queryVo.getRuleSerialNum()) || !StringUtils.isEmpty(queryVo.getContractName())) {
            QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
            crContractbasicQueryWrapper.lambda().like(!StringUtils.isEmpty(queryVo.getRuleSerialNum()), CrContractbasic::getRuleSerialNum, queryVo.getRuleSerialNum());
            crContractbasicQueryWrapper.lambda().like(!StringUtils.isEmpty(queryVo.getContractName()), CrContractbasic::getContractName, queryVo.getContractName());
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getLogicDel, 0);
            crContractbasicQueryWrapper.lambda().select(CrContractbasic::getContractID, CrContractbasic::getRuleSerialNum, CrContractbasic::getContractName, CrContractbasic::getPropertyModel);
            crContractbasics = crContractbasicMapper.selectList(crContractbasicQueryWrapper);
            List<String> cids = crContractbasics.stream().map(CrContractbasic::getContractID).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(cids)) {
                return PageData.emptyPageData(queryVo.getPageSize(), queryVo.getPageNum());
            } else {
                queryWrapper.lambda().in(CrContractpayalert::getfContractId, cids);
            }
        }

        Page<CrContractpayalert> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        IPage<CrContractpayalert> iPage = contractpayalertMapper.selectPage(page, queryWrapper);

        Map<String, CrContractbasic> contractMap = new HashMap<>();
        if (CollectionUtils.isEmpty(crContractbasics)) {
            List<String> cids = iPage.getRecords().stream().map(CrContractpayalert::getfContractId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(cids)) {
                QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();
                crContractbasicQueryWrapper.lambda().
                        eq(CrContractbasic::getLogicDel, 0).
                        in(CrContractbasic::getContractID, cids).
                        select(CrContractbasic::getContractID, CrContractbasic::getRuleSerialNum,
                                CrContractbasic::getContractName, CrContractbasic::getPropertyModel);
                crContractbasics = crContractbasicMapper.selectList(crContractbasicQueryWrapper);
            }
        }

        if (!CollectionUtils.isEmpty(crContractbasics)) {
            contractMap = crContractbasics.stream().collect(Collectors.toMap(CrContractbasic::getContractID, Function.identity(), (k1, k2) -> k1));
        }

        List<PayAlertListVo> reslst = new ArrayList<>();
        for (CrContractpayalert payAlert : page.getRecords()) {
            PayAlertListVo payAlertListVo = new PayAlertListVo();
            BeanUtils.copyProperties(payAlert, payAlertListVo);

            CrContractbasic crContractbasic = contractMap.get(payAlert.getfContractId());
            payAlertListVo.setContractName(crContractbasic.getContractName());
            payAlertListVo.setRuleSerialNum(crContractbasic.getRuleSerialNum());
            payAlertListVo.setPayTypeName(ContractEnum.enumPayTypeMap.get(payAlert.getfPayType()));
            payAlertListVo.setPropertyModel(crContractbasic.getPropertyModel());
            reslst.add(payAlertListVo);
        }

        PageData<PayAlertListVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(iPage.getTotal());
        pageData.setTotalPage(iPage.getPages());
        pageData.setData(reslst);

        return pageData;
    }

    @Override
    public PageData<PayAlertMsgVo> queryPayAlertMsg(Integer pageNum, Integer pageSize) {

        LocalDate now = LocalDate.now();
        if (pageNum == null)
            pageNum = 1;

        if (pageSize == null)
            pageSize = 20;

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        Page<PayAlertMsgVo> page = new Page<>(pageNum, pageSize);
        IPage<PayAlertMsgVo> iPage = contractpayalertMapper.queryPayAlertMsg(page, userInfo.getSysUser().getfId().toString(), now);

        List<String> cids = iPage.getRecords().stream().map(PayAlertMsgVo::getfContractId).collect(Collectors.toList());
        Map<String, List<CrExecutepayment>> paymentMapMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(cids)) {
            QueryWrapper<CrExecutepayment> paymentQueryWrapper = new QueryWrapper<>();
            paymentQueryWrapper.lambda().
                    in(CrExecutepayment::getContractID, cids).
                    select(CrExecutepayment::getContractID, CrExecutepayment::getDmbtr);
            List<CrExecutepayment> crExecutepayments = executepaymentMapper.selectList(paymentQueryWrapper);

            paymentMapMap = crExecutepayments.stream().collect(Collectors.groupingBy(CrExecutepayment::getContractID));
        }

        for (PayAlertMsgVo alertMsgVo : iPage.getRecords()) {
            List<CrExecutepayment> crExecutepayments = paymentMapMap.get(alertMsgVo.getfContractId());
            BigDecimal reduce = BigDecimal.ZERO;
            if (!CollectionUtils.isEmpty(crExecutepayments)) {
                reduce = crExecutepayments.stream()
                        .map(map -> (map.getDmbtr() != null ? map.getDmbtr() : BigDecimal.ZERO))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            alertMsgVo.setTotalMoney(reduce);
        }

        PageData<PayAlertMsgVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(iPage.getTotal());
        pageData.setTotalPage(iPage.getPages());
        pageData.setData(iPage.getRecords());

        return pageData;
    }

    @Override
    public PageData<FinalAlertMsgVo> queryFinalAlertMsg(Integer pageNum, Integer pageSize) {

        if (pageNum == null)
            pageNum = 1;

        if (pageSize == null)
            pageSize = 20;

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        Integer day = unitConfigServicel.queryFinalDay(userInfo.getUnitId());
        if (day == null) {
            return PageData.emptyPageData(pageNum, pageSize);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDate = now.plusDays(day);

        Page<FinalAlertMsgVo> page = new Page<>(pageNum, pageSize);
        IPage<FinalAlertMsgVo> iPage = crContractbasicMapper.queryFinalAlertMsg(page, userInfo.getSysUser().getfId().toString(), localDate);

        PageData<FinalAlertMsgVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(iPage.getTotal());
        pageData.setTotalPage(iPage.getPages());
        pageData.setData(iPage.getRecords());

        return pageData;
    }

    @Override
    public Map<String, Long> queryAlertCnt() {
        //收付款数量
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDate now = LocalDate.now();
        Long payAlertMsgCnt = contractpayalertMapper.queryPayAlertMsgCnt(userInfo.getSysUser().getfId().toString(), now);

        //终结数量
        Integer day = unitConfigServicel.queryFinalDay(userInfo.getUnitId());
        Long finalAlertMsgCnt = 0L;
        if (day != null) {
            LocalDateTime nowtime = LocalDateTime.now();
            LocalDateTime localDate = nowtime.plusDays(day);
            finalAlertMsgCnt = crContractbasicMapper.queryFinalAlertMsgCnt(userInfo.getSysUser().getfId().toString(), localDate);
        }

        Map<String, Long> resMap = new HashMap<>();
        resMap.put("payCnt", payAlertMsgCnt);
        resMap.put("finalCnt", finalAlertMsgCnt);

        return resMap;
    }
}

