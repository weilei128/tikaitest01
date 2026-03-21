package com.pcitc.szgt.contract.appmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pcitc.szgt.contract.appmanager.entity.*;
import com.pcitc.szgt.contract.appmanager.mapper.*;
import com.pcitc.szgt.contract.appmanager.model.OrgSignVo;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigResult;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigResultVo;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigSaveVo;
import com.pcitc.szgt.contract.appmanager.service.UnitConfigService;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentQueryVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.service.AttachmentService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.share.entity.SysDictionary;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UnitConfigServiceImpl implements UnitConfigService {

    @Autowired
    private AmUnitconfigurationMapper amUnitconfigurationMapper;

    @Autowired
    private SysOrganiseunitSingingMapper organiseunitSingingMapper;

    @Autowired
    private AmUnitselfconfigMapper amUnitselfconfigMapper;

    @Autowired
    private SysOrganiseunitBelongMapper sysOrganiseunitBelongMapper;

    @Autowired
    private SysInterfaceconfigMapper sysInterfaceconfigMapper;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private OrganizationRequest orgRequest;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;

    /**
     * 添加或保存单位配置
     *
     * @param unitConfigSaveVo
     * @return
     */
    @Transactional
    public DataResult<?> saveUnitConfig(UnitConfigSaveVo unitConfigSaveVo) {

        if (unitConfigSaveVo == null) {
            throw new BaseException("数据不合法", 500);
        }

        if (StringUtils.isEmpty(unitConfigSaveVo.getOrgConfigID())) {
            throw new BaseException("数据不合法", 500);
        }

        if (StringUtils.isEmpty(unitConfigSaveVo.getOrgID())) {
            throw new BaseException("数据不合法", 500);
        }

        // 旧数据
        AmUnitconfiguration oldUnitConfig = amUnitconfigurationMapper.selectById(unitConfigSaveVo.getOrgConfigID());
        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, unitConfigSaveVo.getOrgID());
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);

        if (oldUnitConfig == null) {
            //新增校验
            if (amUnitconfigurations.size() > 0) {
                throw new BaseException("单位配置信息已存在, 保存失败", 500);
            }
        } else {
            //修改校验
            if (!unitConfigSaveVo.getOrgID().equals(oldUnitConfig.getOrgID())) {
                throw new BaseException("单位配置信息有误，保存失败", 500);
            }
        }

        if (oldUnitConfig == null) {
            // 添加
            return addUnitConfigOp(unitConfigSaveVo);
        } else {
            // 修改
            return editUnitConfigOp(unitConfigSaveVo);
        }

    }
	/**
	 * 应用管理-单位配置-数据保存
	 * @param unitConfigSaveVo
	 * @return
	 */
    private DataResult<?> addUnitConfigOp(UnitConfigSaveVo unitConfigSaveVo) {

        SysOrganization sysOrganization = orgRequest.queryTopOrg(Integer.valueOf(unitConfigSaveVo.getOrgID()));

        if (sysOrganization == null) {
            throw new BaseException("数据不合法", 500);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        AmUnitconfiguration amUnitconfiguration = new AmUnitconfiguration();
        BeanUtils.copyProperties(unitConfigSaveVo, amUnitconfiguration);
        amUnitconfiguration.setOulabel(sysOrganization.getfId());

        amUnitconfiguration.setCreatedDate(LocalDateTime.now());
        amUnitconfiguration.setCreatedBy(userInfo.getSysUser().getfId().toString());
        int result = amUnitconfigurationMapper.insert(amUnitconfiguration);

        if (result != 1) {
            throw new BaseException("操作失败", 500);
        }

        //如果是签约主体
        if (amUnitconfiguration.getIsSingingBody() != null && amUnitconfiguration.getIsSingingBody() == 1) {
            if (unitConfigSaveVo.getOrgSigns().size() > 0) {
                for (OrgSignVo orgSignVo : unitConfigSaveVo.getOrgSigns()) {
                    SysOrganiseunitSinging sysOrganiseunitSinging = new SysOrganiseunitSinging();
                    sysOrganiseunitSinging.setOuid(Integer.valueOf(unitConfigSaveVo.getOrgID()));
                    sysOrganiseunitSinging.setCreatedBy(userInfo.getSysUser().getfId().toString());
                    sysOrganiseunitSinging.setCreatedDate(LocalDateTime.now());
                    sysOrganiseunitSinging.setSingingName(orgSignVo.getSingingName());
                    sysOrganiseunitSinging.setOulabel(sysOrganization.getfId());
                    int insert = organiseunitSingingMapper.insert(sysOrganiseunitSinging);
                    if (insert != 1) {
                        throw new BaseException("操作失败", 500);
                    }
                }
            }
        }

        return DataResult.success(null);
    }
    /**
     * 应用管理-单位配置-数据修改
     * @param unitConfigSaveVo
     * @return
     */
    private DataResult<?> editUnitConfigOp(UnitConfigSaveVo unitConfigSaveVo) {

        SysOrganization sysOrganization = orgRequest.queryTopOrg(Integer.valueOf(unitConfigSaveVo.getOrgID()));

        if (sysOrganization == null) {
            throw new BaseException("数据不合法", 500);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        AmUnitconfiguration amUnitconfiguration = new AmUnitconfiguration();
        BeanUtils.copyProperties(unitConfigSaveVo, amUnitconfiguration);
        amUnitconfiguration.setOulabel(sysOrganization.getfId());

        amUnitconfiguration.setModifiedDate(LocalDateTime.now());
        amUnitconfiguration.setModifiedBy(userInfo.getSysUser().getfId().toString());
        int result = amUnitconfigurationMapper.updateById(amUnitconfiguration);

        if (result != 1) {
            throw new BaseException("操作失败", 500);
        }

        //如果是签约主体
        if (amUnitconfiguration.getIsSingingBody() != null && amUnitconfiguration.getIsSingingBody() == 1) {

            List<Integer> singids = unitConfigSaveVo.getOrgSigns().stream().
                    filter(i -> i.getSingID() != null).
                    map(OrgSignVo::getSingID).
                    collect(Collectors.toList());

            // 删除多余的 2021-05-07注释掉
//            UpdateWrapper<SysOrganiseunitSinging> singingUpdateWrapper = new UpdateWrapper<>();
//            if (!CollectionUtils.isEmpty(singids)) {
//                singingUpdateWrapper.lambda().notIn(SysOrganiseunitSinging::getSingID, singids);
//            } else {
//                singingUpdateWrapper.lambda().eq(SysOrganiseunitSinging::getOuid, unitConfigSaveVo.getOrgID());
//            }
//
//            organiseunitSingingMapper.delete(singingUpdateWrapper);

            for (OrgSignVo orgSignVo : unitConfigSaveVo.getOrgSigns()) {
                SysOrganiseunitSinging sysOrganiseunitSinging = new SysOrganiseunitSinging();
                if (orgSignVo.getSingID() != null) {
                    //修改
                    sysOrganiseunitSinging.setSingID(orgSignVo.getSingID());
                    sysOrganiseunitSinging.setOuid(Integer.valueOf(unitConfigSaveVo.getOrgID()));
                    sysOrganiseunitSinging.setSingingName(orgSignVo.getSingingName());
                    sysOrganiseunitSinging.setOulabel(sysOrganization.getfId());
                    sysOrganiseunitSinging.setModifiedBy(userInfo.getSysUser().getfId().toString());
                    sysOrganiseunitSinging.setModifiedDate(LocalDateTime.now());
                    int i = organiseunitSingingMapper.updateById(sysOrganiseunitSinging);
                    if (i != 1) {
                        throw new BaseException("操作失败", 500);
                    }
                } else {
                    //新增
                    sysOrganiseunitSinging.setOuid(Integer.valueOf(unitConfigSaveVo.getOrgID()));
                    sysOrganiseunitSinging.setCreatedBy(userInfo.getSysUser().getfId().toString());
                    sysOrganiseunitSinging.setCreatedDate(LocalDateTime.now());
                    sysOrganiseunitSinging.setSingingName(orgSignVo.getSingingName());
                    sysOrganiseunitSinging.setOulabel(sysOrganization.getfId());
                    int insert = organiseunitSingingMapper.insert(sysOrganiseunitSinging);
                    if (insert != 1) {
                        throw new BaseException("操作失败", 500);
                    }
                }
            }

        } else {
            //删掉旧的数据
            UpdateWrapper<SysOrganiseunitSinging> singingUpdateWrapper = new UpdateWrapper<>();
            singingUpdateWrapper.lambda().eq(SysOrganiseunitSinging::getOuid, unitConfigSaveVo.getOrgID());
            organiseunitSingingMapper.delete(singingUpdateWrapper);
        }

        return DataResult.success(null);
    }

    /**
     * 查询单位配置
     *
     * @return
     */
    public DataResult queryUnitConfig(String orgId) {

        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(amUnitconfigurations)) {
            return DataResult.success(new UnitConfigResultVo());
        }

        AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
        UnitConfigResultVo unitConfigResultVo = new UnitConfigResultVo();
        unitConfigResultVo.setOrgConfigID(amUnitconfiguration.getOrgConfigID());
        unitConfigResultVo.setOrgID(amUnitconfiguration.getOrgID().toString());
        unitConfigResultVo.setFinalDay(amUnitconfiguration.getFinalDay());

        List<String> userIds = new ArrayList<>();
        boolean assetCorp = !StringUtils.isEmpty(amUnitconfiguration.getAssetCorp());
        boolean groupCorp = !StringUtils.isEmpty(amUnitconfiguration.getGroupCorp());
        boolean shareCorp = !StringUtils.isEmpty(amUnitconfiguration.getShareCorp());
        boolean flowDistributer = !StringUtils.isEmpty(amUnitconfiguration.getFlowDistributer());

        if (assetCorp) {
            userIds.add(amUnitconfiguration.getAssetCorp());
        }
        if (groupCorp) {
            userIds.add(amUnitconfiguration.getGroupCorp());
        }
        if (shareCorp) {
            userIds.add(amUnitconfiguration.getShareCorp());
        }
        if (flowDistributer) {
            userIds.add(amUnitconfiguration.getFlowDistributer());
        }

        Integer[] intUserIds = userIds.stream().map(Integer::valueOf).toArray(Integer[]::new);
        List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(intUserIds);
        Map<Integer, String> userMap = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, SysUserinfo::getfCname, (k1, k2) -> k2));

        unitConfigResultVo.setAssetCorp(assetCorp ? amUnitconfiguration.getAssetCorp() : "");
        unitConfigResultVo.setGroupCorp(groupCorp ? amUnitconfiguration.getGroupCorp() : "");
        unitConfigResultVo.setShareCorp(shareCorp ? amUnitconfiguration.getShareCorp() : "");
        unitConfigResultVo.setFlowDistributer(flowDistributer ? amUnitconfiguration.getFlowDistributer() : "");
        unitConfigResultVo.setAssetCorpName(assetCorp ? userMap.get(Integer.valueOf(amUnitconfiguration.getAssetCorp())) : "");
        unitConfigResultVo.setGroupCorpName(groupCorp ? userMap.get(Integer.valueOf(amUnitconfiguration.getGroupCorp())) : "");
        unitConfigResultVo.setShareCorpName(shareCorp ? userMap.get(Integer.valueOf(amUnitconfiguration.getShareCorp())) : "");
        unitConfigResultVo.setFlowDistributerName(flowDistributer ? userMap.get(Integer.valueOf(amUnitconfiguration.getFlowDistributer())) : "");
        unitConfigResultVo.setIsSingingBody(amUnitconfiguration.getIsSingingBody());

        List<OrgSignVo> orgSigns = new ArrayList<>();
        //查签约主体
        if (amUnitconfiguration.getIsSingingBody() != null && amUnitconfiguration.getIsSingingBody() == 1) {
            QueryWrapper<SysOrganiseunitSinging> singingQueryWrapper = new QueryWrapper<>();
            singingQueryWrapper.lambda().eq(SysOrganiseunitSinging::getOuid, amUnitconfiguration.getOrgID());

            List<SysOrganiseunitSinging> sysOrganiseunitSingings = organiseunitSingingMapper.selectList(singingQueryWrapper);
            for (SysOrganiseunitSinging singing : sysOrganiseunitSingings) {
                OrgSignVo orgSignVo = new OrgSignVo();
                orgSignVo.setSingID(singing.getSingID());
                orgSignVo.setSingingName(singing.getSingingName());
                orgSigns.add(orgSignVo);
            }
        }

        unitConfigResultVo.setOrgSigns(orgSigns);

        return DataResult.success(unitConfigResultVo);
    }

    /**
     * 获取合同打印关联附件
     *
     * @return
     */
    public DataResult<?> getPrintAtta(String orgId) {
        SysOrganization orgCompany = orgRequest.getOrgCompany(Integer.valueOf(orgId));

        AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
        attachmentQueryVo.setPropertyID(String.valueOf(orgCompany.getfId()));
        DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
        List<AttachmentResultVo> attaList = listDataResult.getData();

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (AttachmentResultVo attachmentResultVo : attaList) {
            Map<String, Object> attas = new HashMap<>();
            attas.put("attachmentType", attachmentResultVo.getAttachmentType());
            attas.put("typeCode", attachmentResultVo.getTypeCode());
            attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
            resultList.add(attas);
        }

        return DataResult.success(resultList);

    }

    /**
     * 根据单位配置ID 查询单位配置
     *
     * @param ouid 组织机构ID
     * @return PcitcResult<UnitConfigResult>
     */
    public DataResult<UnitConfigResult> queryUnitConfigByOuid(Integer ouid) {
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        SysOrganization sysOrganization = orgRequest.queryOrganization(ouid);
        if (sysOrganization == null) {
            throw new BaseException("未找到组织机构信息", 500);
        }

        //TODO 查询根组织机构
        SysOrganization topOrg = orgRequest.queryTopOrg(sysOrganization.getfId());
        Integer oulabel = topOrg.getfId();

        UnitConfigResult result = new UnitConfigResult();
        SysOrganiseunitBelong sysOrganiseunitBelong = sysOrganiseunitBelongMapper.selectById(ouid);
        result.setOrganiseUnitBelong(sysOrganiseunitBelong);

        QueryWrapper<SysInterfaceconfig> sicQueryWrapper = new QueryWrapper<>();
        sicQueryWrapper.lambda().eq(SysInterfaceconfig::getOuid, ouid);
        List<SysInterfaceconfig> sysInterfaceconfigs = sysInterfaceconfigMapper.selectList(sicQueryWrapper);
        if (!CollectionUtils.isEmpty(sysInterfaceconfigs)) {
            result.setInterfaceConfig(sysInterfaceconfigs.get(0));
        }

        //TODO 字典
//        result.setApprovalSwitchList(GetListByDictionaryCode("138001001", 2));//获取 审批流程开关配置(字典)
//        result.setOffereeAdmittanceList(GetListByDictionaryCode("138001002", 2));//获取 相对人准入要求(字典)
//        result.setApplyPageConfigDiclist(GetListByDictionaryCode("138001003", 2));//获取 审批页面配置(字典)

        QueryWrapper<AmUnitconfiguration> aucQueryWrapper = new QueryWrapper<>();
        aucQueryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, ouid);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(aucQueryWrapper);
        if (CollectionUtils.isEmpty(amUnitconfigurations)) {
            //未找到配置信息
            result.setOuid(ouid);
            result.setOulabel(oulabel);
            result.setOrgConfigID(UUIDUtils.getUUID());
            AmUnitconfiguration tempAmUnitConfig = new AmUnitconfiguration();
            tempAmUnitConfig.setOrgID(ouid);
            tempAmUnitConfig.setOulabel(oulabel);
            tempAmUnitConfig.setOrgConfigID(result.getOrgConfigID());
            tempAmUnitConfig.setShareWaterMarkID(UUIDUtils.getUUID());
            tempAmUnitConfig.setStockElectronicSealID(UUIDUtils.getUUID());
            tempAmUnitConfig.setGroupWaterMarkID(UUIDUtils.getUUID());
            tempAmUnitConfig.setGroupElectronicSealID(UUIDUtils.getUUID());
            tempAmUnitConfig.setAssetWaterMarkID(UUIDUtils.getUUID());
            tempAmUnitConfig.setAssetElectronicSealID(UUIDUtils.getUUID());
            tempAmUnitConfig.setEntrustID(UUIDUtils.getUUID());
            tempAmUnitConfig.setHonestDutyID(UUIDUtils.getUUID());
            tempAmUnitConfig.setSafeProtocolID(UUIDUtils.getUUID());
            tempAmUnitConfig.setPerformPaymentID(UUIDUtils.getUUID());
            result.setUnitConfiguration(tempAmUnitConfig);
        } else {
            result.setUnitConfiguration(amUnitconfigurations.get(0));
            //region 附件处理
            if (StringUtils.isEmpty(result.getUnitConfiguration().getShareWaterMarkID())) {
                result.getUnitConfiguration().setShareWaterMarkID(UUID.randomUUID().toString());
            } else {
                result.setAttShareWaterMark(attachmentService.selectById(result.getUnitConfiguration().getShareWaterMarkID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getStockElectronicSealID())) {
                result.getUnitConfiguration().setStockElectronicSealID(UUIDUtils.getUUID());
            } else {
                result.setAttStockElectronicSeal(attachmentService.selectById(result.getUnitConfiguration().getStockElectronicSealID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getGroupWaterMarkID())) {
                result.getUnitConfiguration().setGroupWaterMarkID(UUIDUtils.getUUID());
            } else {
                result.setAttGroupWaterMark(attachmentService.selectById(result.getUnitConfiguration().getGroupWaterMarkID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getGroupElectronicSealID())) {
                result.getUnitConfiguration().setGroupElectronicSealID(UUIDUtils.getUUID());
            } else {
                result.setAttGroupElectronicSeal(attachmentService.selectById(result.getUnitConfiguration().getGroupElectronicSealID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getAssetWaterMarkID())) {
                result.getUnitConfiguration().setAssetWaterMarkID(UUIDUtils.getUUID());
            } else {
                result.setAttAssetWaterMark(attachmentService.selectById(result.getUnitConfiguration().getAssetWaterMarkID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getAssetElectronicSealID())) {
                result.getUnitConfiguration().setAssetElectronicSealID(UUIDUtils.getUUID());
            } else {
                result.setAttAssetElectronicSeal(attachmentService.selectById(result.getUnitConfiguration().getAssetElectronicSealID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getEntrustID())) {
                result.getUnitConfiguration().setEntrustID(UUIDUtils.getUUID());
            } else {
                result.setAttEntrust(attachmentService.selectById(result.getUnitConfiguration().getEntrustID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getHonestDutyID())) {
                result.getUnitConfiguration().setHonestDutyID(UUIDUtils.getUUID());
            } else {
                result.setAttHonestDuty(attachmentService.selectById(result.getUnitConfiguration().getHonestDutyID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getSafeProtocolID())) {
                result.getUnitConfiguration().setSafeProtocolID(UUIDUtils.getUUID());
            } else {
                result.setAttSafeProtocol(attachmentService.selectById(result.getUnitConfiguration().getSafeProtocolID()));
            }
            if (StringUtils.isEmpty(result.getUnitConfiguration().getPerformPaymentID())) {
                result.getUnitConfiguration().setPerformPaymentID(UUIDUtils.getUUID());
            } else {
                result.setAttPerform(attachmentService.selectById(result.getUnitConfiguration().getPerformPaymentID()));
            }
        }

        //流程配置员名称设定
        if (!StringUtils.isEmpty(result.getUnitConfiguration().getFlowDistributer())) {
            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.valueOf(result.getUnitConfiguration().getFlowDistributer()));
            if (null != sysUserinfo) {
                result.setFlowDistributerName(sysUserinfo.getfCname());
            }
        }

        //TODO 组织机构类型
        boolean IsEnterprise = false;
        if (sysOrganization.getfType() != null
                && (sysOrganization.getfType() == 2//TODO 硬编码 2:企业
                || sysOrganization.getfType() == 0//TODO 硬编码 0:总部
                || sysOrganization.getfType() == 1//TODO 硬编码 1:事业部
        )) {
            IsEnterprise = true;
        }
        result.setIsEnterprise(IsEnterprise);

        QueryWrapper<AmUnitselfconfig> auscQueryWrapper = new QueryWrapper<>();
        auscQueryWrapper.lambda().eq(AmUnitselfconfig::getOulabel, ouid);
        List<AmUnitselfconfig> amUnitselfconfigs = amUnitselfconfigMapper.selectList(auscQueryWrapper);
        result.setUnitSelfConfigList(amUnitselfconfigs);

        if (!CollectionUtils.isEmpty(amUnitselfconfigs)) {
            for (AmUnitselfconfig amUnitselfconfig : amUnitselfconfigs) {
                // TODO 字典
                if (amUnitselfconfig.getItemID() == 220)//复核相对人 220 138004001
                {
                    result.setUnitSelfConfig(amUnitselfconfig);
                    boolean pOffereeIsEnabled = false;
                    if (!IsEnterprise) {
                        //  判断企业是否启用相对人复核，如果企业，单位显示
                        Integer offereeIsEnabled = amUnitselfconfig.getIsEnabled();

                        if (offereeIsEnabled != null && offereeIsEnabled == 1) {
                            pOffereeIsEnabled = true;
                        }
                    }
                    result.setPOffereeIsEnabled(pOffereeIsEnabled);
                    break;
                }
            }
        }

        boolean IsUnit = false;
//        if (sysOrganiseUnit.getIsunit() == 1) {//TODO isUnit被废弃
        IsUnit = true;
        result.setIsUnit(IsUnit);

        boolean offereeEnabled = false;
        // 如果下属企业有未完成的复核数据时，控件只读
//        DataSet UnCheckOffereeInfoDs = BFoffereeScope.GetUnCheckOffereeInfo(ouid);
//        if (UnCheckOffereeInfoDs != null && UnCheckOffereeInfoDs.Tables.Count > 0 && UnCheckOffereeInfoDs.Tables[0].Rows.Count > 0)
//        {
        offereeEnabled = true;
//        }
        //TODO 相对人部分 未实装
        result.setOffereeEnabled(offereeEnabled);

        return DataResult.success(result);

    }


    /**
     * 添加或修改单位配置
     *
     * @param unitConfigResult 添加或修改该单位配置对象
     * @return PcitcResult
     */
    public DataResult<?> saveOrUpdateInformationbulletin(UnitConfigResult unitConfigResult) {

        //region 获取页面数据
        Integer ouid = unitConfigResult.getOuid();
        if (ouid == null || ouid < 1) {
            throw new BaseException("未设定正确的ouid", 500);
        }

        boolean isAdd = true;
        //获取 单位尸体对象 AM_UnitConfiguration
        QueryWrapper<AmUnitconfiguration> aucQueryWrapper = new QueryWrapper<>();
        aucQueryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, ouid);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(aucQueryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            //已存在数据 设定为修改模式
            isAdd = false;
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        SysOrganization sysOrganization = orgRequest.queryOrganization(ouid);

        //TODO 查询根组织机构
        SysOrganization topOrg = orgRequest.queryTopOrg(sysOrganization.getfId());
        Integer oulabel = topOrg.getfId();
//        boolean IsUnit = sysOrganiseUnit.getIsunit() == 1 ? true : false;//TODO
        boolean IsUnit = true;
        boolean IsEnterprise = false;
        Integer organiseUnitCategory = sysOrganization.getfType() == null ? -1 : sysOrganization.getfType();
        if (IsUnit && (organiseUnitCategory == 2 //TODO 2:企业
                || organiseUnitCategory == 0 //TODO 2:总部
                || organiseUnitCategory == 1 //TODO 1:事业部
        )) {
            IsEnterprise = true;
        }
        boolean IsGroupCorp = false;

        //TODO 组织机构层级
        Integer organiseUnitLevel = sysOrganization.getfLevel() == null ? -1 : sysOrganization.getfLevel();
        if (organiseUnitLevel == 1) {
            IsGroupCorp = true;
        }

        //水印文件
        String groupWaterMarkId = unitConfigResult.getGroupWaterMarkID();
        String assetWaterMarkId = unitConfigResult.getAssetWaterMarkID();
        String stockWaterMarkId = unitConfigResult.getShareWaterMarkID();

        //审批开关配置
        List<SysDictionary> approvalSwitchList = unitConfigResult.getApprovalSwitchList();

        //endregion

        if (isAdd) {
            //region 新增数据
            AmUnitconfiguration insertAmUnitconfiguration = new AmUnitconfiguration();
            BeanUtils.copyProperties(unitConfigResult.getUnitConfiguration(), insertAmUnitconfiguration);//复制页面信息
            String Orgconfigid = UUID.randomUUID().toString();//新增数据：新生成uuid
            insertAmUnitconfiguration.setOrgConfigID(Orgconfigid);
            insertAmUnitconfiguration.setOulabel(oulabel);
            insertAmUnitconfiguration.setOrgID(ouid);
            insertAmUnitconfiguration.setCreatedBy(userInfo.getSysUser().getfId().toString());
            insertAmUnitconfiguration.setCreatedDate(LocalDateTime.now());

            //region 各配置保存
            List<AmUnitselfconfig> amUnitSelfConfigList = unitConfigResult.getUnitSelfConfigList();
            if (null != amUnitSelfConfigList && amUnitSelfConfigList.size() > 0) {
                for (AmUnitselfconfig tempAmUnitSelfConfig : amUnitSelfConfigList
                        ) {
                    AmUnitselfconfig target = new AmUnitselfconfig();
                    BeanUtils.copyProperties(tempAmUnitSelfConfig, target);
                    target.setOrgConfigID(Orgconfigid);
                    target.setOulabel(oulabel);
                    amUnitselfconfigMapper.insert(target);
                }
            }
            //endregion
            amUnitconfigurationMapper.insert(insertAmUnitconfiguration);

            //endregion
        } else {
            //region 修改数据
            AmUnitconfiguration updateAmUnitconfiguration = new AmUnitconfiguration();
            BeanUtils.copyProperties(unitConfigResult.getUnitConfiguration(), updateAmUnitconfiguration);//复制页面信息
            updateAmUnitconfiguration.setModifiedBy(userInfo.getSysUser().getfId().toString());
            updateAmUnitconfiguration.setModifiedDate(LocalDateTime.now());

            //region 各配置更新
            List<AmUnitselfconfig> amUnitselfconfigList = unitConfigResult.getUnitSelfConfigList();
            for (AmUnitselfconfig tempAmUnitselfconfig : amUnitselfconfigList
                    ) {
                AmUnitselfconfig target = new AmUnitselfconfig();
                BeanUtils.copyProperties(tempAmUnitselfconfig, target);
                amUnitselfconfigMapper.updateById(target);
            }
            //endregion
            amUnitconfigurationMapper.updateById(updateAmUnitconfiguration);

            //endregion
        }

        return DataResult.success(null);
    }

    /*
     * 根据合同获取水印图片
     * */
    @Override
    public DataResult<?> getWaterMark(String contractId) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        CrContractbasic crContractbasic = crContractbasicMapper.selectById(contractId);
        if (crContractbasic != null) {
            SysOrganization orgCompany = orgRequest.getOrgCompany(Integer.valueOf(crContractbasic.getMainDeptID()));
            List<Map<String, Object>> maps = queryWatermarkByOrgId(orgCompany.getfId());
            return DataResult.success(maps);
//            QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgCompany.getfId());
//            List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
//            if (CollectionUtils.isEmpty(amUnitconfigurations)) {
//                return DataResult.success(resultList);
//            }
//            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
//            AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
//            attachmentQueryVo.setPropertyID(amUnitconfiguration.getOrgConfigID());
//            attachmentQueryVo.setTypeCode("1");
//            attachmentQueryVo.setAttachmentType("1");//合同水印
//            DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryAttachment(attachmentQueryVo);
//            List<AttachmentResultVo> attaList = listDataResult.getData();
//            for (AttachmentResultVo attachmentResultVo : attaList) {
//                Map<String, Object> attas = new HashMap<>();
//                attas.put("attachmentType", attachmentResultVo.getAttachmentType());
//                attas.put("typeCode", attachmentResultVo.getTypeCode());
//                attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
//                resultList.add(attas);
//            }
//            return DataResult.success(resultList);
        }
        return DataResult.success(resultList);
    }

    //根据组织机构id查水印
    public List<Map<String, Object>> queryWatermarkByOrgId(Integer orgId){
    	long startTime = System.currentTimeMillis() ; 
        List<Map<String, Object>> resultList = new ArrayList<>();
        if(orgId == null){
            return resultList;
        }

        SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);

        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
            AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
            attachmentQueryVo.setPropertyID(amUnitconfiguration.getOrgConfigID());
            attachmentQueryVo.setTypeCode("1");
            attachmentQueryVo.setAttachmentType("1");//合同水印
            DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
            List<AttachmentResultVo> attaList = listDataResult.getData();
            if(!CollectionUtils.isEmpty(attaList)){
                for (AttachmentResultVo attachmentResultVo : attaList) {
                    Map<String, Object> attas = new HashMap<>();
                    attas.put("attachmentType", attachmentResultVo.getAttachmentType());
                    attas.put("typeCode", attachmentResultVo.getTypeCode());
                    attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
                    resultList.add(attas);
                }
                return resultList;
            }
        }

        //当前节点没找到, 向上级查找
        if(sysOrganization == null){
            return resultList;
        }
        long endTime = System.currentTimeMillis() ; 
        log.info("-----会签审查审批表总时间= {} ms",(endTime - startTime));
        return queryWatermarkByOrgId(sysOrganization.getFkParentId());
    }

    //根据组织机构id查廉洁责任书
    public List<AttachmentResultVo> queryHonestdutyByOrgId(Integer orgId){
        List<AttachmentResultVo> resultList = new ArrayList<>();
        if(orgId == null){
            return resultList;
        }

        SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);

        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
            AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
            attachmentQueryVo.setPropertyID(amUnitconfiguration.getOrgConfigID());
            attachmentQueryVo.setTypeCode("1");
            attachmentQueryVo.setAttachmentType("5");
            DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
            List<AttachmentResultVo> attaList = listDataResult.getData();
            if(!CollectionUtils.isEmpty(attaList)){
                for (AttachmentResultVo attachmentResultVo : attaList) {
//                    Map<String, Object> attas = new HashMap<>();
//                    attas.put("attachmentType", attachmentResultVo.getAttachmentType());
//                    attas.put("typeCode", attachmentResultVo.getTypeCode());
//                    attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
                    resultList.add(attachmentResultVo);
                }
                return resultList;
            }
        }

        //当前节点没找到, 向上级查找
        if(sysOrganization == null){
            return resultList;
        }
        return queryHonestdutyByOrgId(sysOrganization.getFkParentId());
    }

    //根据组织机构id查安全协议
    public List<AttachmentResultVo> querySafeProtocolByOrgId(Integer orgId){
        List<AttachmentResultVo> resultList = new ArrayList<>();
        if(orgId == null){
            return resultList;
        }

        SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);

        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
            AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
            attachmentQueryVo.setPropertyID(amUnitconfiguration.getOrgConfigID());
            attachmentQueryVo.setTypeCode("1");
            attachmentQueryVo.setAttachmentType("6");
            DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
            List<AttachmentResultVo> attaList = listDataResult.getData();
            if(!CollectionUtils.isEmpty(attaList)){
                for (AttachmentResultVo attachmentResultVo : attaList) {
//                    Map<String, Object> attas = new HashMap<>();
//                    attas.put("attachmentType", attachmentResultVo.getAttachmentType());
//                    attas.put("typeCode", attachmentResultVo.getTypeCode());
//                    attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
                    resultList.add(attachmentResultVo);
                }
                return resultList;
            }
        }

        //当前节点没找到, 向上级查找
        if(sysOrganization == null){
            return resultList;
        }
        return querySafeProtocolByOrgId(sysOrganization.getFkParentId());
    }

    //根据组织机构id查保密承诺函
    public List<AttachmentResultVo> queryKeepsecretByOrgId(Integer orgId){
        List<AttachmentResultVo> resultList = new ArrayList<>();
        if(orgId == null){
            return resultList;
        }

        SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);

        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
            AttachmentQueryVo attachmentQueryVo = new AttachmentQueryVo();
            attachmentQueryVo.setPropertyID(amUnitconfiguration.getOrgConfigID());
            attachmentQueryVo.setTypeCode("1");
            attachmentQueryVo.setAttachmentType("7");
            DataResult<List<AttachmentResultVo>> listDataResult = attachmentService.queryOneAttachment(attachmentQueryVo);
            List<AttachmentResultVo> attaList = listDataResult.getData();
            if(!CollectionUtils.isEmpty(attaList)){
                for (AttachmentResultVo attachmentResultVo : attaList) {
//                    Map<String, Object> attas = new HashMap<>();
//                    attas.put("attachmentType", attachmentResultVo.getAttachmentType());
//                    attas.put("typeCode", attachmentResultVo.getTypeCode());
//                    attas.put("url", attachmentResultVo.getDocUrl() + attachmentResultVo.getAttachmentPath());
                    resultList.add(attachmentResultVo);
                }
                return resultList;
            }
        }

        //当前节点没找到, 向上级查找
        if(sysOrganization == null){
            return resultList;
        }
        return queryKeepsecretByOrgId(sysOrganization.getFkParentId());
    }

    @Override
    public Integer queryFinalDay(Integer orgId) {
        if(orgId == null){
            return null;
        }

        SysOrganization sysOrganization = orgRequest.queryOrganization(orgId);
        QueryWrapper<AmUnitconfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AmUnitconfiguration::getOrgID, orgId);
        List<AmUnitconfiguration> amUnitconfigurations = amUnitconfigurationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(amUnitconfigurations)) {
            AmUnitconfiguration amUnitconfiguration = amUnitconfigurations.get(0);
            if(amUnitconfiguration.getFinalDay() != null){
                return amUnitconfiguration.getFinalDay();
            }
        }

        if(sysOrganization == null){
            return null;
        }

        return queryFinalDay(sysOrganization.getFkParentId());
    }
}
