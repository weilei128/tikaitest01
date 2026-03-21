package com.pcitc.szgt.contract.finality.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.ssc.dps.inte.workflow.*;
import com.pcitc.szgt.contract.appmanager.entity.AmQuerylicense;
import com.pcitc.szgt.contract.appmanager.entity.AmUnitconfiguration;
import com.pcitc.szgt.contract.appmanager.entity.SysOrganiseunitSinging;
import com.pcitc.szgt.contract.appmanager.mapper.AmQuerylicenseMapper;
import com.pcitc.szgt.contract.appmanager.mapper.AmUnitconfigurationMapper;
import com.pcitc.szgt.contract.appmanager.mapper.SysOrganiseunitSingingMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.common.enums.FinancialEnum;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.documentinformation.entity.Officialdocument;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.finality.entity.CrContractcase;
import com.pcitc.szgt.contract.finality.entity.CrContractfromlegal;
import com.pcitc.szgt.contract.finality.entity.CrContractpause;
import com.pcitc.szgt.contract.finality.mapper.CrContractcaseMapper;
import com.pcitc.szgt.contract.finality.mapper.CrContractfromlegalMapper;
import com.pcitc.szgt.contract.finality.mapper.CrContractpauseMapper;
import com.pcitc.szgt.contract.finality.model.ContractCaseData;
import com.pcitc.szgt.contract.finality.model.ContractCaseModel;
import com.pcitc.szgt.contract.finality.model.ContractFromLegal;
import com.pcitc.szgt.contract.finality.model.ContractToLegal;
import com.pcitc.szgt.contract.finality.service.IFinalityService;
import com.pcitc.szgt.contract.interactive.model.ContractInfo;
import com.pcitc.szgt.contract.interactive.service.FinancialService;
import com.pcitc.szgt.contract.make.entity.*;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractoffereeMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractrununitMapper;
import com.pcitc.szgt.contract.make.service.IMakeService;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeappraise;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeappraiseMapper;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeinfoMapper;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.entity.CrContractend;
import com.pcitc.szgt.contract.perform.entity.CrContracttransfer;
import com.pcitc.szgt.contract.perform.entityEx.ContractChangeBid;
import com.pcitc.szgt.contract.perform.entityEx.ContractQuery;
import com.pcitc.szgt.contract.perform.mapper.CrContractendMapper;
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
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.DateUtil;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FinalityServiceImpl implements IFinalityService {
    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;
    @Autowired
    private CrContractinfoMapper crContractinfoMapper;
    /**
     * 	合同发案
     */
    @Autowired
    private CrContractcaseMapper crContractcaseMapper;
    /**
     * 	合同终止记录
     */
    @Autowired
    private CrContractpauseMapper crContractpauseMapper;
    /**
     *	办公代理
     */
    @Autowired
    private OfficeAgentRequest  agentRequest ;
    @Autowired
    private CrContractrununitMapper crContractrununitMapper;
    /**
     *	单位配置
     */
    @Autowired
    private AmUnitconfigurationMapper amUnitconfigurationMapper;
    /**
     *	合同终结
     */
    @Autowired
    private CrContractendMapper crContractendMapper;
    /**
     *	合同相对人
     */
    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;
    /**
     * 	相对人履行评价
     */
    @Autowired
    private FfOffereeappraiseMapper ffOffereeappraiseMapper;
    /**
     * 	组织机构
     */
    @Autowired
    private OrganizationRequest organizationRequest;
    /**
     *	数据字典
     */
    @Autowired
    private DictionaryRequest dictionaryRequest;
    /**
     *	工作流
     */
    @Autowired
    private DpsRequest dpsRequest;
    /**
     *	用户接口
     */
    @Autowired
    private UserInfoRequest userInfoRequest;
    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;
    @Autowired
    private AmQuerylicenseMapper amQuerylicenseMapper;
    @Autowired
    private IWorkFlowService workFlowService;
    @Autowired
    private SysOrganiseunitSingingMapper sysOrganiseunitSingingMapper;
    @Autowired
    private FfOffereeinfoMapper ffOffereeinfoMapper;
    @Autowired
    private IMakeService iMakeService;
    @Autowired
    private FinancialService financialService;
    @Autowired
    private CrContractfromlegalMapper crContractfromlegalMapper;

    /**
     *	待（已）发案合同列表
     */
    @Override
    public DataResult<?> queryContractIncidence(String ruleserialNum, String contractName,String type1, String beginCreateTime, 
    		String endCreateTime, String dataType, Integer pageSize, Integer pageNum) {
        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(ruleserialNum)) {
            queryWrapper.like("a.ruleserialNum", ruleserialNum);
        }
        if (!StringUtils.isEmpty(contractName)) {
            queryWrapper.like("a.contractName", contractName);
        }
        if (!StringUtils.isEmpty(type1)) {
            queryWrapper.eq("a.type1", type1);
        }
        if (!StringUtils.isEmpty(beginCreateTime)) {
            queryWrapper.ge("a.CreatedDate", beginCreateTime);
        }
        if (!StringUtils.isEmpty(beginCreateTime)) {
            queryWrapper.le("a.CreatedDate", endCreateTime);
        }
        queryWrapper.gt("Section", Integer.parseInt(ContractEnum.EnumSection.Sign.getCode()));//签署
        queryWrapper.lt("Section", Integer.parseInt(ContractEnum.EnumSection.Finality.getCode()));//终结
        queryWrapper.eq("MainOrgUserID", this.currentUserUtil.currentUserInfo().getSysUser().getfId());
        List<HashMap> list = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        IPage<CrContractbasic> page = new Page<>(pageNum, pageSize);
        if (!StringUtils.isEmpty(dataType)) {
            list = crContractbasicMapper.queryContractIncidence(page, queryWrapper); //待发案合同
         /*   if (dataType.equals("0")) {
                list = crContractbasicMapper.queryContractIncidence(page, queryWrapper); //待发案合同
            } else {
                list = crContractbasicMapper.queryDoneContractIncidence(page, queryWrapper);//已发案合同查询
            }*/
        }

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);

                boolean isCase = false;
                QueryWrapper<CrContractcase> contractcaseQueryWrapper = new QueryWrapper<>();
                contractcaseQueryWrapper.lambda().eq(CrContractcase::getContractID, map.get("ContractID").toString());
                contractcaseQueryWrapper.lambda().eq(CrContractcase::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
                contractcaseQueryWrapper.lambda().orderByDesc(CrContractcase::getCreatedDate);
                List<CrContractcase> contractcaseList = crContractcaseMapper.selectList(contractcaseQueryWrapper);
                String caseId = "";
                if (contractcaseList != null && contractcaseList.size() > 0) {
                    caseId = contractcaseList.get(0).getCaseID();
                    isCase = true;
                }
                if (dataType.equals("0") && isCase) {
                    continue;//当查未发案合同时，排除已发案合同
                }
                if (dataType.equals("1") && !isCase) {
                    continue;//当查已发案合同时，排除未发案合同
                }

                obj.put("contractID", map.get("ContractID"));//合同id
                if (dataType.equals("1")) {
                    obj.put("caseId", caseId);//发案ID
                }
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
     *	合同发案
     */
    @Override
    @Transactional
    public boolean contractIncidence(String contractId, String caseTime, BigDecimal amout, String reason) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
       /* if (!StringUtils.isEmpty(taskId)) {
            //dpsRequest.taskMessageComplete(taskId);
        }*/
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        //合同发案记录
        CrContractcase crContractcase = new CrContractcase();
        crContractcase.setCaseID(UUID.randomUUID().toString());
        crContractcase.setContractID(crContractbasic.getContractID());
        crContractcase.setTotalAmount(amout);//发案金额
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(caseTime)) {
            crContractcase.setCaseDate(LocalDate.parse(caseTime, dateTimeFormatter));//发案日期
        }

        crContractcase.setRamark(reason);//发案原因
        crContractcase.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContractcase.setCreatedDate(date);
        crContractcase.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        crContractcaseMapper.insert(crContractcase);

        //合同中止记录
        CrContractpause crContractpause = new CrContractpause();
        crContractpause.setContractPauseID(UUID.randomUUID().toString());
        crContractpause.setContractID(crContractbasic.getContractID());
        crContractpause.setKind(Integer.parseInt(ContractEnum.EnumPauseKind.Case.getCode()));//发案中止
        crContractpause.setCreatedBy(userInfo.getSysUser().getfId().toString());
        crContractpause.setPauseStart(date);//合同终止开始时间
        crContractpause.setPauseRemark("发案合同中止合同");//合同中止原因
        crContractpause.setOulabel(crContractbasic.getOulabel());
        crContractpauseMapper.insert(crContractpause);

        try{//推送法务接口
        ContractToLegal contractToLegal = new ContractToLegal();
        contractToLegal.setRuleSerialNum(crContractbasic.getRuleSerialNum());
        contractToLegal.setContractNum(crContractbasic.getContractNum());
        contractToLegal.setContractName(crContractbasic.getContractName());

        contractToLegal.setCaseDate(LocalDate.parse(caseTime, dateTimeFormatter));
        contractToLegal.setTotalAmount(amout);//发案金额
        contractToLegal.setRamark(reason);//发案原因

        contractToLegal.setTypename1(crContractbasic.getTypeName1());//合同类别名称1（）
        contractToLegal.setTypename2(crContractbasic.getTypeName2());
        contractToLegal.setTypename3(crContractbasic.getTypeName3());
        contractToLegal.setTypename4(crContractbasic.getTypeName4());
        contractToLegal.setType1(crContractbasic.getType1());//合同类别id1（）
        contractToLegal.setType2(crContractbasic.getType2());
        contractToLegal.setType3(crContractbasic.getType3());
        contractToLegal.setType4(crContractbasic.getType4());

        String OffereeNames="";
        QueryWrapper<CrContractofferee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
        List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(queryWrapper);
        if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
            for (CrContractofferee crContractofferee : crContractoffereeList) {
                OffereeNames += crContractofferee.getOffereeName() + ",";
            }
        }
        contractToLegal.setOffereeNames(OffereeNames);



        }catch (Exception e) {
        }
        return true;
    }


    /**
     * 	合同发案从法务系统推送回合同系统
     */
    @Override
    public DataResult<?> contractFromLegal(ContractFromLegal contractFromLegal) {
        if (StringUtils.isEmpty(contractFromLegal.getDisputeNo())) {
            throw new NotFoundException("纠纷编号必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(contractFromLegal.getContractNum())) {
            throw new NotFoundException("合同编号必填！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractbasic> crContractbasicqueryWrapper = new QueryWrapper<>();
        crContractbasicqueryWrapper.lambda().eq(CrContractbasic::getContractNum, contractFromLegal.getContractNum());
        CrContractbasic crContractbasic = crContractbasicMapper.selectOne(crContractbasicqueryWrapper);
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }

        CrContractfromlegal crContractfromlegal=new CrContractfromlegal();
        String success = "保存失败";
        boolean isSuccess = false;

        QueryWrapper<CrContractfromlegal> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractfromlegal::getDisputeNo, contractFromLegal.getDisputeNo());
        CrContractfromlegal contractFromLegalSelectOne = crContractfromlegalMapper.selectOne(queryWrapper);
        if (contractFromLegalSelectOne == null) {
            try{
            crContractfromlegal.setDisputeNo(contractFromLegal.getDisputeNo());//主键  不为空
            crContractfromlegal.setHandlingResults(contractFromLegal.getHandlingResults());
            crContractfromlegal.setClosingTime(contractFromLegal.getClosingTime());
            crContractfromlegal.setContractNum(contractFromLegal.getContractNum());
            success = crContractfromlegalMapper.insert(crContractfromlegal) > 0 ? "保存成功" : "保存失败";


                isSuccess=cancelContractIncidence(crContractbasic.getContractID(), contractFromLegal.getHandlingResults());//发案取消，履行回转
                if(isSuccess) {
                    return DataResult.success("法务回传成功");
                }else {
                    return DataResult.success("法务回传失败");
                }

            }catch (Exception e){
                    e.printStackTrace();
                    throw new BaseException(success, 500);
            }

        }else {
            try{
            crContractfromlegal.setDisputeNo(contractFromLegalSelectOne.getDisputeNo());//主键  不变
            crContractfromlegal.setHandlingResults(contractFromLegal.getHandlingResults());
            crContractfromlegal.setClosingTime(contractFromLegal.getClosingTime());
            crContractfromlegal.setContractNum(contractFromLegal.getContractNum());
            success = crContractfromlegalMapper.updateById(crContractfromlegal) > 0 ? "更新成功" : "更新失败";

            isSuccess=cancelContractIncidence(crContractbasic.getContractID(), contractFromLegal.getHandlingResults());//发案取消，履行回转
            if(isSuccess) {
                return DataResult.success("法务回传成功");
            }else {
                return DataResult.success("法务回传失败");
            }

            }catch (Exception e){
                e.printStackTrace();
                throw new BaseException(success, 500);
            }

            //return DataResult.success(success);
        }

    }
    /**
     * 	获取发案详细信息
     */
    @Override
    public DataResult<?> getContractIncidence(String contractId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
        QueryWrapper<CrContractcase> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractcase::getContractID, crContractbasic.getContractID());
        queryWrapper.lambda().eq(CrContractcase::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        List<CrContractcase> crContractcaseList = crContractcaseMapper.selectList(queryWrapper);
        JSONObject obj = new JSONObject(true);
        if (crContractcaseList != null && crContractcaseList.size() > 0) {
            CrContractcase crContractcase = crContractcaseList.get(0);

            obj.put("caseId", crContractcase.getCaseID());
            obj.put("contractId", crContractcase.getContractID());
            obj.put("amout", crContractcase.getTotalAmount());//发案金额
            obj.put("caseTime", crContractcase.getCaseDate());//发案日期
            obj.put("reason", crContractcase.getRamark());//发案原因
        }
        return DataResult.success(obj);

    }

    /**
     *	发案取消，履行回转
     */
    @Override
    @Transactional
    public boolean cancelContractIncidence(String caseId, String remark) {
        boolean isSuccess = false;
        if (StringUtils.isEmpty(caseId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
       /* QueryWrapper<CrContractcase> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractcase::getCaseID, caseId);
        queryWrapper.lambda().eq(CrContractcase::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        //List<CrContractcase> crContractcaseList = crContractcaseMapper.selectById(caseId);
        if (crContractcaseList != null && crContractcaseList.size() > 0) {
        }*/
        CrContractcase crContractcase = crContractcaseMapper.selectById(caseId);
        if (crContractcase != null) {
            crContractcase.setProcessResult(remark);//纠纷处理结果
            crContractcase.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Delete.getCode()));
            crContractcase.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractcase.setModifiedDate(date);
            crContractcaseMapper.updateById(crContractcase);

            QueryWrapper<CrContractpause> crContractpauseQueryWrapper = new QueryWrapper<>();
            crContractpauseQueryWrapper.lambda().eq(CrContractpause::getContractID, crContractcase.getContractID());
            crContractpauseQueryWrapper.lambda().eq(CrContractpause::getKind, Integer.parseInt(ContractEnum.EnumPauseKind.Case.getCode()));//发案终止
            crContractpauseQueryWrapper.lambda().orderByDesc(CrContractpause::getPauseStart);
            List<CrContractpause> crContractpauseList = crContractpauseMapper.selectList(crContractpauseQueryWrapper);
            if (crContractpauseList != null && crContractpauseList.size() > 0) {
             /*   for (CrContractpause crContractpause : crContractpauseList) {
                    crContractpause.setPauseEnd(date);
                    crContractpause.setModifiedBy(userInfo.getSysUser().getfId().toString());
                    crContractpauseMapper.updateById(crContractpause);
                }*/
                CrContractpause crContractpause = crContractpauseList.get(0);
                crContractpause.setPauseEnd(date);
                crContractpause.setModifiedBy(userInfo.getSysUser().getfId().toString());
                crContractpauseMapper.updateById(crContractpause);
            }
        }

        return isSuccess;
    }


    /**
     *	合同履行完毕
     */
    @Override
    @Transactional
    public boolean conractComplete(String contractId, String taskId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }

        QueryWrapper<CrContractbasic> basicQueryWrapper = new QueryWrapper<>();
        basicQueryWrapper.lambda().eq(CrContractbasic::getContractID, contractId).last("for update");

        CrContractbasic crContractbasic = crContractbasicMapper.selectOne(basicQueryWrapper);
        if (crContractbasic == null) {
            throw new NotFoundException("未查到相关记录！", Constants.FAILCODE);
        }

        if(crContractbasic.getPropertyModel() == Integer.parseInt(ContractEnum.EnumModule.Finality.getCode())){
            throw new BaseException("合同已处理", Constants.FAILCODE);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();

        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Finality.getCode()));//合同终结
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractbasic.setModifiedDate(date);
        crContractbasicMapper.updateById(crContractbasic);

        String userId = "";//合同终结办理人
        QueryWrapper<CrContractrununit> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractrununit::getContractID, crContractbasic.getContractID());
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(queryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                userId = String.join(",", crContractrununit.getFinalityUserId());
            }
        }
        if (StringUtils.isEmpty(userId)) {
            userId = crContractbasic.getMainOrgUserID();
        }
        String[] userIds = userId.split(",");
        String finalyUser = "";
        for (String user : userIds) {
            //发送终结待办
            finalyUser = user;
            break;
        }


        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo != null) {
            crContractinfo.setPerFormEndDate(date);
            crContractinfo.setModifiedDate(date);
            crContractinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractinfoMapper.updateById(crContractinfo);
        }
        //发送终结待办消息
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

        if (!StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);//合同履行完毕，关闭履行选择待办
        }
        AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                ContractEnum.EnumModule.Finality.getCode(), ContractEnum.EnumModule.Finality.getMessage(),
                ContractEnum.EnumSection.Finality.getCode());
        taskMessage.setExtendsData(appExtendsData);
        AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);//合同履行完毕，到合同终结模块查询
        return true;
    }

    /**
     *	合同履行回转
     */
    @Override
    @Transactional
    public boolean contractRotation(String contractId, String messageId, String taskId) {
        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Perform.getCode()));//合同履行
        crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Perform.getCode()));//合同履行
        crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractbasic.setModifiedDate(date);
        crContractbasicMapper.updateById(crContractbasic);

        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo != null) {
            crContractinfo.setPerFormEndDate(null);
            crContractinfo.setModifiedDate(date);
            crContractinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractinfoMapper.updateById(crContractinfo);
        }
        //当前待办消息设置失效
        if (!StringUtils.isEmpty(taskId)) {
            AppCallResult appCallResult = dpsRequest.taskMessageComplete(taskId);
        }
        String userId = "";//合同终结办理人
        QueryWrapper<CrContractrununit> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractrununit::getContractID, crContractbasic.getContractID());
        List<CrContractrununit> crContractrununitList = crContractrununitMapper.selectList(queryWrapper);
        if (crContractrununitList != null && crContractrununitList.size() > 0) {
            for (CrContractrununit crContractrununit : crContractrununitList) {
                userId += crContractrununit.getUserID() + ",";
            }
        }
        if (StringUtils.isEmpty(userId)) {
            userId = crContractbasic.getMainOrgUserID();
        } else {
            userId = userId.substring(0, userId.length() - 1);
        }

        SysUserinfo creator = userInfoRequest.queryById(Integer.parseInt(crContractbasic.getMainOrgUserID()));

        String[] userIds = userId.split(",");
        for (String user : userIds) {
            //发送合同履行待办消息
            DpsTaskMessage taskMessage = new DpsTaskMessage();
            taskMessage.setBusinessId(crContractbasic.getContractID());
            taskMessage.setCategoryCode(ContractEnum.EnumWorkFlow.Make.getCode());
            taskMessage.setExecutorId(crContractbasic.getMainOrgUserID());
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(user));
            if (sysUserinfo != null) {
                taskMessage.setExecutorName(sysUserinfo.getfCname());
                taskMessage.setExecutorCode(sysUserinfo.getfCode());
                taskMessage.setCreatorCode(creator.getfCode());
                taskMessage.setBusinessName(crContractbasic.getContractName());
            }
            AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractbasic.getContractID(), crContractbasic, crContractinfo,
                    ContractEnum.EnumModule.Perform.getCode(), ContractEnum.EnumModule.Perform.getMessage(), ContractEnum.EnumSection.Perform.getCode());
            taskMessage.setExtendsData(appExtendsData);//设置扩展字段
            AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);
        }
        return true;

    }

    /**
     *	合同终结提交/保存
     */
    @Override
    @Transactional
    public boolean contractTreatment(String contractId, String endId, Integer isNormal, String endDescripition, String description,
                                     Integer performApprise, String performAppriseText, String appraiseContent, String taskId, boolean isSubmit) {

        if (StringUtils.isEmpty(contractId)) {
            throw new NotFoundException("合同ID必填！", Constants.FAILCODE);
        }
        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("合同终止ID必填！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
        CrContractinfo crContractinfo = crContractinfoMapper.selectById(contractId);
        if (crContractinfo == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
        CrContractend crContractend = crContractendMapper.selectById(endId);
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        LocalDateTime date = LocalDateTime.now();
        if (!StringUtils.isEmpty(taskId)) {
            dpsRequest.taskMessageComplete(taskId);
        }

        //新增
        boolean isAdd = false;
        if (crContractend == null) {
            crContractend = new CrContractend();
            crContractend.setContractEndID(endId);
            crContractend.setContractID(contractId);
            crContractend.setCreatedDate(date);
            crContractend.setCreatedBy(userInfo.getSysUser().getfId().toString());
            crContractend.setOulabel(crContractbasic.getOulabel());
            QueryWrapper<CrContractend> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CrContractend::getContractID, crContractbasic.getContractID());
            List<CrContractend> crContractends = crContractendMapper.selectList(queryWrapper);
            String endNo = "";
            if (crContractends.size() > 0) {
                Integer transCount = crContractends.size();
                if (transCount < 10) {
                    endNo = crContractbasic.getContractNum() + "ZZ" + ("-0" + transCount.toString());
                } else {
                    endNo = crContractbasic.getContractNum() + "ZZ" + ("-" + transCount.toString());
                }

            } else {
                endNo = crContractbasic.getContractNum() + "ZZ" + "01";
            }
            crContractend.setEndNo(endNo);
            isAdd = true;
        } else {
            crContractend.setModifiedBy(userInfo.getSysUser().getfId().toString());
            crContractend.setModifiedDate(date);
        }
        crContractend.setIsNormal(isNormal);//合同终结类型
        crContractend.setEndDescription(endDescripition);//合同完结情况说明
        crContractend.setOverDescription(endDescripition);//合同完结情况说明
        crContractend.setTerminateTime(date);//合同完结时间
        crContractend.setCategory(1);//区分终止还是终结操作
        crContractend.setOffereePerformRemark(description);//相对人异常履约情况说明
        crContractend.setPerformApprise(performApprise); //履行评价
        crContractend.setPerformAppriseText(performAppriseText);
        if (isAdd) {
            crContractendMapper.insert(crContractend);
        } else {
            crContractendMapper.updateById(crContractend);
        }
        //相对人评价
        if (!StringUtils.isEmpty(appraiseContent)) {
            //先删除
            QueryWrapper<FfOffereeappraise> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FfOffereeappraise::getContractID, crContractbasic.getContractID());
            ffOffereeappraiseMapper.delete(queryWrapper);
            //新增
            QueryWrapper<CrContractofferee> crContractoffereeQueryWrapper = new QueryWrapper<>();
            crContractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, contractId);
            List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(crContractoffereeQueryWrapper);
            for (CrContractofferee crContractofferee : crContractoffereeList) {
                FfOffereeappraise ffOffereeappraise = new FfOffereeappraise();
                ffOffereeappraise.setPerformCommentID(UUID.randomUUID().toString());
                ffOffereeappraise.setContractID(crContractbasic.getContractID());
                ffOffereeappraise.setOffereeID(crContractofferee.getOffereeID());
                ffOffereeappraise.setAppraiser(userInfo.getSysUser().getfId().toString());
                ffOffereeappraise.setAppraiseDate(date);
                ffOffereeappraise.setCreatedBy(userInfo.getSysUser().getfId().toString());
                ffOffereeappraise.setCreatedDate(date);
                ffOffereeappraise.setOulabel(crContractbasic.getOulabel());
                ffOffereeappraise.setAppraiseContent(appraiseContent);
                ffOffereeappraiseMapper.insert(ffOffereeappraise);
            }
        }

        //判断合同终结流程是否启用
        Integer deptId = crContractbasic.getMainDeptID();//获取主办部门
        //获取合同主办部门所在企业/单位
        SysOrganization sysOrganization = organizationRequest.getOrgCompany(deptId);
        if (sysOrganization != null) {
            deptId = sysOrganization.getfId();
        }
        //do
        boolean isEnable = false;
        QueryWrapper<AmUnitconfiguration> amUnitconfigurationMapperQueryWrapper = new QueryWrapper<>();
        amUnitconfigurationMapperQueryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, deptId);
        List<AmUnitconfiguration> amUnitconfigurationMapperList = amUnitconfigurationMapper.selectList(amUnitconfigurationMapperQueryWrapper);
        for (AmUnitconfiguration amUnitconfiguration : amUnitconfigurationMapperList) {

            if (amUnitconfiguration.getFinalityIsEnabled() != null && amUnitconfiguration.getFinalityIsEnabled() == 1) {
                isEnable = true;
                break;
            }
        }

        if (!isEnable && isSubmit) {
            sendToFinancial(crContractbasic, crContractinfo, FinancialEnum.FINASH);
        }

        //工作流启动，并且提交
        if (isEnable && isSubmit) {
            //触发终结审批流
            //do
            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Finality.getCode()));//合同终结
            crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Handing.getCode()));

            StartContext startContext = new StartContext();
            startContext.setBusinessId(crContractbasic.getContractID());
            startContext.setBusinessName(crContractbasic.getContractName());
            List<AppWorkflowData> appWorkflowData = dpsRequest.workflow(ContractEnum.EnumWorkFlowType.Org.getCode(), Integer.parseInt(ContractEnum.EnumWorkFlowType.Org.getCode()),
                    crContractbasic.getMainDeptID().toString());

            if (appWorkflowData != null && appWorkflowData.size() > 0) {
                startContext.setWorkflowId(appWorkflowData.get(0).getWorkflowId());//订立
            }

            startContext.setPropertyModel(ContractEnum.EnumModule.Finality.getCode());//合同终结
            startContext.setSection(ContractEnum.EnumSection.Finality.getCode());//合同终结
            startContext.setUnitId(crContractbasic.getMainOrgID().toString());//所属单位/企业
            startContext.setCategoryCode(ContractEnum.EnumWorkFlow.Finally.getCode());// 合同订立 发起的流程分类编码
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
            startContext.setOrganiseId(crContractbasic.getMainDeptID().toString());//发起机构Id(公共模板传固定值”#templateorgId#”)

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

            List<AppMetasData> metas = dpsRequest.metas(ContractEnum.EnumWorkFlow.Make.getCode());//合同订立
            metas = workFlowService.setAppMetasData(metas, crContractbasic, crContractinfo,null, null, "");
            startContext.setMetasList(metas);
            AppExtendsData appExtendsData = new AppExtendsData();

            appExtendsData.setBusinessId(crContractbasic.getContractID());//业务数据Id
            appExtendsData.setExt001("contract");//合同系统标识
            appExtendsData.setExt002(crContractbasic.getRuleSerialNum());//合同序号
            appExtendsData.setExt003(crContractbasic.getContractName());//合同名称
            appExtendsData.setExt004(ContractEnum.EnumSection.Finality.getMessage());//合同环节
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


            startContext.setExtendsData(appExtendsData);
            String json = JSON.toJSONString(startContext);
            dpsRequest.start(startContext);

        } else {
            crContractbasic.setPropertyModel(Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结
            crContractbasic.setSection(Integer.parseInt(ContractEnum.EnumSection.Finality.getCode()));//合同终结
            crContractbasic.setStatus(Integer.parseInt(ContractEnum.EnumStatus.Approved.getCode()));
            crContractbasic.setFinalityDate(date);//合同终结时间
            crContractbasic.setModifiedDate(date);
            //关闭所有待办  20200809
            PagedList pagedList = dpsRequest.todotaskByBusinessId(crContractbasic.getContractID());
            if (pagedList != null) {
                List<ExecuteTaskData> executeTaskDataList = pagedList.getExecuteTaskList();
                if (executeTaskDataList != null && executeTaskDataList.size() > 0) {
                    for (ExecuteTaskData executeTaskData : executeTaskDataList) {
                        dpsRequest.taskMessageComplete(executeTaskData.getTaskId());
                    }
                }
            }
        }

        crContractbasic.setModifiedBy(userInfo.getSysUser().getfId().toString());
        crContractbasic.setModifiedDate(date);
        crContractbasicMapper.updateById(crContractbasic);
        if (!StringUtils.isEmpty(taskId)) {
        	agentRequest.sendMessageForTransactor(crContractbasic.getContractID(),"合同终结提交/保存");
        }
        return true;
    }

    /**
     *	同步合同信息到财务系统
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

        //合同金额contractState
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

        if(crContractinfo.getPerFormStartDate() != null){
            contractInfo.setContrBeginDate(dateTimeFormatter.format(crContractinfo.getPerFormStartDate()));
        }

        if(crContractinfo.getPerFormEndDate() != null){
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
//                appMetasDataList.add(appMetasData);
//
//            }
//        }
//        return appMetasDataList;
//    }

    @Override
    /**
     * 	获取合同终结记录
     */
    public DataResult<?> getcontractTreatment(String endId) {

        if (StringUtils.isEmpty(endId)) {
            throw new NotFoundException("合同终止ID必填！", Constants.FAILCODE);
        }
        /* CrContractend crContractend = crContractendMapper.selectById(endId);*/

        CrContractend crContractend = null;
        QueryWrapper<CrContractend> crContractendQueryWrapper = new QueryWrapper<>();
      /*  crContractendQueryWrapper.lambda().eq(CrContractend::getContractID, endId).or().eq(CrContractend::getContractEndID, endId)
                .eq(CrContractend::getCategory, 1);*/
        crContractendQueryWrapper.and(wrapper -> wrapper.eq("contractId", endId).or().eq("ContractEndID", endId));
        crContractendQueryWrapper.eq("Category", 1);
        crContractendQueryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
        List<CrContractend> crContractendList = crContractendMapper.selectList(crContractendQueryWrapper);
        if (crContractendList != null && crContractendList.size() > 0) {
            crContractend = crContractendList.get(0);
        }

        if (crContractend == null) {
            throw new NotFoundException("终结信息不存在！", Constants.FAILCODE);
        }
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(crContractend.getContractID());
        if (crContractbasic == null) {
            throw new NotFoundException("合同信息不存在！", Constants.FAILCODE);
        }
        JSONObject obj = new JSONObject(true);
        obj.put("endId", crContractend.getContractEndID());//合同终结Id
        obj.put("contractId", crContractend.getContractID());//合同id
        obj.put("endDescripition", crContractend.getEndDescription());//合同完结情况说明
        obj.put("terminateTime", crContractend.getTerminateTime());//合同完结时间
        obj.put("offereePerformRemark", crContractend.getOffereePerformRemark());//相对人异常履约情况说明
        obj.put("performAppriseId", crContractend.getPerformApprise());//履行评价Id
        obj.put("performAppriseName", crContractend.getPerformAppriseText());//履行评价名称
        obj.put("isNormal", crContractend.getIsNormal());//正常终结/异常终结
        String appraiseContent = "";
        QueryWrapper<FfOffereeappraise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FfOffereeappraise::getContractID, crContractbasic.getContractID());
        List<FfOffereeappraise> ffOffereeappraiseList = ffOffereeappraiseMapper.selectList(queryWrapper);
        if (ffOffereeappraiseList != null && ffOffereeappraiseList.size() > 0) {
            appraiseContent = ffOffereeappraiseList.get(0).getAppraiseContent();//相对人履行评价
        }
        obj.put("appraiseContent", appraiseContent);//相对人履行评价
        return DataResult.success(obj);
    }

    @Override
    /**
     * 	终结合同查询
     */
    public DataResult<?> queryContractFinality(ContractQuery contractQuery) {
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
            /*crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);*/
            crContractbasicQueryWrapper.ge("a.CreatedDate", contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.le("a.CreatedDate", contractQuery.endCreateTime);
            /*crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);*/
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getSection, contractQuery.section);
        }

        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//查询有效合同
        crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//终结合同查询
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

        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.pageNum, contractQuery.pageSize);
        /*userInfoRequest.queryAll();*/
        List<HashMap> list = crContractbasicMapper.queryContract(page, crContractbasicQueryWrapper);
        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                String endId = "";
                QueryWrapper<CrContractend> contractendQueryWrapper = new QueryWrapper<>();
                contractendQueryWrapper.lambda().eq(CrContractend::getContractID, map.get("ContractID").toString());
                contractendQueryWrapper.lambda().eq(CrContractend::getCategory, 1);//只取终结的ID
                contractendQueryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
                List<CrContractend> crContractendList = crContractendMapper.selectList(contractendQueryWrapper);
                if (crContractendList != null && crContractendList.size() > 0) {
                    endId = crContractendList.get(0).getContractEndID();
                }
                obj.put("endId", endId);//终止ID
                if (StringUtils.isEmpty(endId)) {
                    continue;
                }
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
                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额
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
                obj.put("sealDate", map.get("SealDate"));//合同订立备案日期

                String propertyModel = "";//合同模块
                String section = "";//合同环节
                if (map.get("PropertyModel") != null) {
                    propertyModel = map.get("PropertyModel").toString();
                }
                if (map.get("section") != null) {
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

                if (map.get("FinalityDate") == null || StringUtils.isEmpty(map.get("FinalityDate"))) {
                    obj.put("isFinality", 0);//合同是否终结
                } else {
                    obj.put("isFinality", 1);//合同是否终结
                }
                //obj.put("isFileArchive", map.get("IsFileArchive"));//是否归档  0否1是

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

    @Override
    /**
     * 	电子归档合同查询（待归档）
     */
    public DataResult<?> queryContractElectFiling(ContractQuery contractQuery) {
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
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getSection, contractQuery.section);
        }

        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));//查询有效合同
        crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getPropertyModel, Integer.parseInt(ContractEnum.EnumModule.Finality.getCode()));//合同终结后的合同
        crContractbasicQueryWrapper.orderByDesc("a.ModifiedDate");//按照终结时间倒序
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

        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.pageNum, contractQuery.pageSize);
        /*userInfoRequest.queryAll();*/
        List<HashMap> list = crContractbasicMapper.queryContract(page, crContractbasicQueryWrapper);
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
                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额
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
                obj.put("mainDeptId", map.get("MainDeptID"));//主办部门
                String mainDeptName = "";
                if (map.get("MainDeptID") != null) {
                    SysOrganization sysOrganization = organizationRequest.queryOrganization(Integer.parseInt(map.get("MainDeptID").toString()));
                    if (sysOrganization != null) {
                        mainDeptName = sysOrganization.getfName();
                        sysOrganization = organizationRequest.getOrgCompany(sysOrganization.getfId());
                        if (sysOrganization != null) {
                            mainDeptName = sysOrganization.getfName() + "/" + mainDeptName;
                        }
                    }
                }
                obj.put("mainDeptName", mainDeptName);//主办部门
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
                if (map.get("MoneySource") != null) {
                    SysDictionarycategory ms1 = dictionaryRequest.queryCategoryById((Integer) map.get("MoneySource"));
                    obj.put("moneySource1", ms1 != null ? ms1.getfCnName() : "");
                } else {
                    obj.put("moneySource1", "");
                }
                if (map.get("MoneySource2") != null) {
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
                obj.put("isFileArchive", map.get("IsFileArchive"));//是否归档  0否1是

                //合同终结 增加终结ID 0818
                String endId = "";
                QueryWrapper<CrContractend> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(CrContractend::getContractID, map.get("ContractID"));
                queryWrapper.lambda().eq(CrContractend::getCategory, 1);//合同终结
                queryWrapper.lambda().orderByDesc(CrContractend::getCreatedDate);
                List<CrContractend> crContractendList = crContractendMapper.selectList(queryWrapper);
                if (crContractendList != null && crContractendList.size() > 0) {
                    endId = crContractendList.get(0).getContractEndID();
                }
                obj.put("endId", endId);//合同终结ID
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

    @Override
    /*
     * 废弃合同查询
     * */
    public DataResult<?> queryDiscardContract(ContractQuery contractQuery) {
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
            crContractbasicQueryWrapper.lambda().ge(CrContractbasic::getCreatedDate, contractQuery.beginCreateTime);
        }
        if (contractQuery.endCreateTime != null && !StringUtils.isEmpty(contractQuery.endCreateTime)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getCreatedDate, contractQuery.endCreateTime);
        }
        if (contractQuery.propertyModel != null && !StringUtils.isEmpty(contractQuery.propertyModel)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getPropertyModel, contractQuery.propertyModel);
        }
        if (contractQuery.section != null && !StringUtils.isEmpty(contractQuery.section)) {
            crContractbasicQueryWrapper.lambda().le(CrContractbasic::getSection, contractQuery.section);
        }

        crContractbasicQueryWrapper.eq("a.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.LogicDel.getCode()));//查询有效合同
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

        crContractbasicQueryWrapper.orderByAsc("a.CreatedDate");
        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.pageNum, contractQuery.pageSize);
        /* userInfoRequest.queryAll();*/
        List<HashMap> list = crContractbasicMapper.queryContract(page, crContractbasicQueryWrapper);
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
                obj.put("contractObjectAmount", map.get("ContractObjectAmount"));//转换成人民币金额
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
                obj.put("mainDeptId", map.get("MainDeptID"));//主办部门
                String mainDeptName = "";
                if (map.get("MainDeptID") != null) {
                    SysOrganization sysOrganization = organizationRequest.queryOrganization(Integer.parseInt(map.get("MainDeptID").toString()));
                    if (sysOrganization != null) {
                        mainDeptName = sysOrganization.getfName();
                        sysOrganization = organizationRequest.getOrgCompany(sysOrganization.getfId());
                        if (sysOrganization != null) {
                            mainDeptName = sysOrganization.getfName() + "/" + mainDeptName;
                        }
                    }
                }
                obj.put("mainDeptName", mainDeptName);//主办部门
                obj.put("createdDate", map.get("CreatedDate"));//主办时间
                obj.put("finalityDate", map.get("FinalityDate"));//合同履行完成日期
                obj.put("mySignDate", map.get("MySignDate"));//签订日期
                obj.put("sealDate", map.get("SealDate"));//合同订立备案日期

                String propertyModel = "";//合同模块
                String section = "";//合同环节
                if (map.get("PropertyModel") != null) {
                    propertyModel = map.get("PropertyModel").toString();
                }
                if (map.get("section") != null) {
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

                if (map.get("FinalityDate") == null || StringUtils.isEmpty(map.get("FinalityDate"))) {
                    obj.put("isFinality", 0);//合同是否终结
                } else {
                    obj.put("isFinality", 1);//合同是否终结
                }
                obj.put("discardExplain", map.get("DiscardExplain"));//废弃原型
                obj.put("discardDate", map.get("ModifiedDate"));//废弃时间
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
     * 合同案件损失金额
     * @return
     */
    public DataResult<?> queryContractCaseLoseSum(ContractCaseData caseData){
    	long startTime = System.currentTimeMillis() ;

        SysOrganization org = organizationRequest.queryOrganizationByCode(caseData.getParentCode());
        List<SysOrganization> sysOrgList = organizationRequest.queryNextAllOrgById(org.getfId());

        sysOrgList = sysOrgList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysOrganization::getfId))), ArrayList::new)
        );


        long endTime = System.currentTimeMillis() ;
        log.info("-----合同运行监控=>合同案件损失金额=>获取机构列表： {} ms",(endTime - startTime));
        SysOrganization parent = organizationRequest.queryOrganizationByCode(caseData.getParentCode());
        if(parent.getFkParentId()==0) {
        	sysOrgList.add(0, parent);
        }
        if(caseData.getOrgId() > 0) {
        	sysOrgList.stream().filter(i -> i.getfId()==caseData.getOrgId()).collect(Collectors.toList());
        }
        if(!StringUtils.isEmpty(caseData.getStartTime())) {
        	caseData.setStartTime(caseData.getStartTime()+" 01-01");
        	caseData.setEndTime(caseData.getEndTime()+" 01-01");
        }else {
        	caseData.setStartTime(String.valueOf(DateUtil.getNowYear())+" 01-01");
        	caseData.setEndTime(String.valueOf(DateUtil.getNextYear())+" 01-01");
        }
        long startTimeOrg = System.currentTimeMillis() ;
        sysOrgList.stream().forEach(sysOrg ->{
        	caseData.setOrgId(sysOrg.getfId());
        	ContractCaseModel caseModel = crContractcaseMapper.queryTotalAndAmount(caseData);
        	if(caseModel !=null) {
	        	sysOrg.setCaseTotal(caseModel.getCaseTotal());
	        	sysOrg.setCaseAmount(caseModel.getCaseAmount());
        	}
        });
    	long endTimeOrg = System.currentTimeMillis() ; 
        log.info("-----合同运行监控=>合同案件损失金额=>循环数据封装： {} ms",(endTimeOrg - startTimeOrg));
        
        int count = sysOrgList.size();
        IPage<HashMap<Object,Object>> page = new Page<>(caseData.getPageNum(), caseData.getPageSize());
        PageData<SysOrganization> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(count);
        pageData.setTotalPage(page.getPages());
        pageData.setData(sysOrgList);
        return DataResult.success(pageData);
    }

    /**
     * 	合同倒签率
     * @return
     */
    public DataResult<?> queryContractBackdateRate(ContractCaseData caseData){
        if (caseData.getPageNum() <= 0) {
        	caseData.setPageNum(cmisDefaultConfig.getPageNum());
        }
        if (caseData.getPageSize() <= 0) {
        	caseData.setPageSize(cmisDefaultConfig.getPageSize());
        }
    	long startTime = System.currentTimeMillis() ;
        SysOrganization org = organizationRequest.queryOrganizationByCode(caseData.getParentCode());
//    	List<SysOrganization> sysOrgList = organizationRequest.queryOrganizationByParam(caseData.getParentCode(), caseData.getFtype());
        List<SysOrganization> sysOrgList = organizationRequest.queryNextAllOrgById(org.getfId());

        sysOrgList = sysOrgList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysOrganization::getfId))), ArrayList::new)
        );//去重
    	long endTime = System.currentTimeMillis() ; 
        log.info("-----合同运行监控=>合同倒签率=>获取机构列表： {} ms",(endTime - startTime));
        SysOrganization parent = organizationRequest.queryOrganizationByCode(caseData.getParentCode());
        if(parent==null) {
        	throw new NotFoundException("组织机构找不到", Constants.FAILCODE);
        }
        if(parent.getFkParentId()==0) {
        	sysOrgList.add(0, parent);
        }
        if(caseData.getOrgId() > 0) {
        	sysOrgList.stream().filter(i -> i.getfId()==caseData.getOrgId()).collect(Collectors.toList());
        }
        if(StringUtils.isEmpty(caseData.getStartTime())) {
        	caseData.setStartTime(DateUtil.getNowYear()+"-01-01 00:00:00");
        	caseData.setEndTime(DateUtil.getNextYear()+"-12-31 23:59:59");
        }
        long startTimeOrg = System.currentTimeMillis() ;
        sysOrgList.stream().forEach(sysOrg ->{
        	caseData.setOrgId(sysOrg.getfId());
        	Integer fulfilTotal = crContractcaseMapper.queryFulfilContractCount(caseData);
        	Integer backdateTotal = crContractcaseMapper.queryBackdateCount(caseData);
        	Integer normallyTotal = crContractcaseMapper.queryNormallyCount(caseData);
        	sysOrg.setFulfilTotal(fulfilTotal);
        	sysOrg.setBackdateTotal(backdateTotal);
        	sysOrg.setNormallyTotal(normallyTotal);
        	BigDecimal a = BigDecimal.valueOf(backdateTotal);
        	BigDecimal b = BigDecimal.valueOf(fulfilTotal);
        	double c = 0  ;
        	int i = b.compareTo(BigDecimal.ZERO);
        	if(i==1) {
        		c = a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
        	}
        	sysOrg.setBackdateRate(c*100);
        	
        });
        int count = sysOrgList.size();
    	long endTimeOrg = System.currentTimeMillis() ; 
        log.info("-----合同运行监控=>合同倒签率=>循环数据封装： {} ms",(endTimeOrg - startTimeOrg));
        IPage<HashMap<Object,Object>> page = new Page<>(caseData.getPageNum(), caseData.getPageSize());
        PageData<SysOrganization> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(count);
        pageData.setTotalPage(page.getPages());
        pageData.setData(sysOrgList);
        return DataResult.success(pageData);
    }
}


