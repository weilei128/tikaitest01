package com.pcitc.szgt.contract.textmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.enums.textmodel.TextStatusEnum;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.textmanage.entity.CrContracttextmodel;
import com.pcitc.szgt.contract.textmanage.mapper.CrContracttextmodelMapper;
import com.pcitc.szgt.contract.textmanage.model.*;
import com.pcitc.szgt.contract.textmanage.service.ICrContracttextmodelService;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-02-20
 */
@Service
public class CrContracttextmodelServiceImpl extends ServiceImpl<CrContracttextmodelMapper, CrContracttextmodel> implements ICrContracttextmodelService {

    @Autowired
    private CrContracttextmodelMapper textMapper;

    @Autowired
    private DictionaryRequest dictionaryRequest;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    /**
     * 文本申请(第一版)
     *
     * @param textApplyVo
     * @return
     */
    @Override
    public DataResult textApply(TextApplyVo textApplyVo) {
        if (StringUtils.isEmpty(textApplyVo.getFileTemplateID())) {
            throw new BaseException("主键不能为空", 500);
        }

        if (StringUtils.isEmpty(textApplyVo.getStatus())) {
            throw new BaseException("状态不能为空", 500);
        }

        if (textApplyVo.getStatus() == 5) {
            textApplyVo.validateApply();
        }

        CrContracttextmodel model = textMapper.selectById(textApplyVo.getFileTemplateID());
        if (model == null) {
            //新插入
            return textApplyinsert(textApplyVo);
        } else {
            //更新
            return textApplyUpdate(model, textApplyVo);
        }

    }

    /**
     * 文本申请, 第一版新增
     *
     * @param textApplyVo
     */
    private DataResult textApplyinsert(TextApplyVo textApplyVo) {

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();

        Integer orgId = null;
        Integer oulabel = userInfo.getUnitId();
        String orgIds = null;
        Map<Integer, SysOrganization> orgMap = null;

        if (sysOrgList != null && sysOrgList.size() > 0) {
            orgId = sysOrgList.get(0).getfId();
            orgMap = sysOrgList.stream().collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k2));
        }

        CrContracttextmodel contracttextmodel = new CrContracttextmodel();
        contracttextmodel.setFileTemplateID(textApplyVo.getFileTemplateID());
        contracttextmodel.setOrgID(oulabel);
        contracttextmodel.setTextName(textApplyVo.getTextName());
//        contracttextmodel.setBusiID(textApplyVo.getBusiID());
//        contracttextmodel.setBusiType(textApplyVo.getBusiType());
        contracttextmodel.setApplicant(userInfo.getSysUser().getfCname());
        contracttextmodel.setApplicantTime(LocalDateTime.now());
        contracttextmodel.setApplicantExplain(textApplyVo.getApplicantExplain());
        contracttextmodel.setStatus(textApplyVo.getStatus());
        contracttextmodel.setLogicDel(0);
        contracttextmodel.setCreatedBy(userInfo.getSysUser().getfCname());
        contracttextmodel.setCreatedDate(LocalDateTime.now());
        contracttextmodel.setTextModelType(textApplyVo.getTextModelType());
        contracttextmodel.setVersion(new BigDecimal(1));
        contracttextmodel.setPubCorps(textApplyVo.getPubCorps());
        contracttextmodel.setCreatorID(userInfo.getSysUser().getfId().toString());
        contracttextmodel.setCreatorName(userInfo.getSysUser().getfCname());
        contracttextmodel.setCreatorDepartmentID(orgId);
        contracttextmodel.setCreatorDepartmentName(orgMap.get(orgId).getfName());
        contracttextmodel.setCreatorUnitID(oulabel);
        contracttextmodel.setCreatorUnitName(userInfo.getUnitName());
        contracttextmodel.setParentID("0");
        contracttextmodel.setIdentity(UUIDUtils.getUUID());
        contracttextmodel.setIsPrime(1);

        dealTextType(textApplyVo.getTypeTextIDs(), contracttextmodel);

        //文本编号
        if (textApplyVo.getStatus() == 5) {
            String textCode = generateTextCode("1");
            contracttextmodel.setTextCode(textCode);
        }

        textMapper.insert(contracttextmodel);

        return DataResult.success(null);

    }

    /**
     * 处理合同类别
     */
    private void dealTextType(List<String> typeTextIDs, CrContracttextmodel contracttextmodel){
        Map<Integer, SysDictionarycategory> dictMap = dictionaryRequest.queryAllSubCategoryToMap("006");

        List<String> typeTextList = new ArrayList<>();
        List<String> typeTextNames = new ArrayList<>();
        for(String typeId:typeTextIDs){
            List<String> typeIdList = new ArrayList<>();
            List<String> typeNameList = new ArrayList<>();
            this.queryPathType(typeId, dictMap, typeIdList, typeNameList);

            String typeIdJoin = String.join("/", typeIdList);
            String typeNameJoin = String.join("/", typeNameList);

            typeTextList.add(typeIdJoin);
            typeTextNames.add(typeNameJoin);
        }

        //合同类别id、名称
        if (!CollectionUtils.isEmpty(typeTextList)) {

            String typeTextID = String.join("#", typeTextList);
            String typeTestName = String.join("#", typeTextNames);
            contracttextmodel.setTypeText(typeTestName);
            contracttextmodel.setTypeTextID(typeTextID);

            List<String> type1s = new ArrayList<>();
            List<String> type2s = new ArrayList<>();
            List<String> type3s = new ArrayList<>();

            for (String s: typeTextList) {
                String[] splits = s.split("/");
                if (splits.length == 1) {
                    type1s.add(splits[0]);
                    type2s.add("");
                    type3s.add("");
                }

                if (splits.length == 2) {
                    type1s.add(splits[0]);
                    type2s.add(splits[1]);
                    type3s.add("");
                }

                if (splits.length >= 3) {
                    type1s.add(splits[0]);
                    type2s.add(splits[1]);
                    type3s.add(splits[2]);
                }
            }

            contracttextmodel.setType1(String.join(",", type1s));
            contracttextmodel.setType2(String.join(",", type2s));
            contracttextmodel.setType3(String.join(",", type3s));
        }
    }

    /**
     * 生成文本编号
     * @return
     */
    private String generateTextCode(String version){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        Integer integer = textMapper.selectCount(null);
        String textCode = "ZB-" + String.valueOf(year) + "-" + version + String.valueOf(integer + 1);

        return textCode;
    }

    /**
     * 获取合同类别路径
     * @param tid
     * @return
     */
    private void queryPathType(String tid, Map<Integer, SysDictionarycategory> dictMap,
                                       List<String> typeIdList,
                                       List<String> typeNameList){

        SysDictionarycategory dict = dictMap.get(Integer.parseInt(tid));

        // 006 合同类别根字典
        while(dict != null && !dict.getfCode().equals("006")){
            typeIdList.add(dict.getfId().toString());
            typeNameList.add(dict.getfName());
            dict = dictMap.get(dict.getFkParentId());
        }

        Collections.reverse(typeIdList);
        Collections.reverse(typeNameList);
    }

    /**
     * 文本申请, 第一版编辑
     *
     * @param textApplyVo
     */
    private DataResult textApplyUpdate(CrContracttextmodel contracttextmodel, TextApplyVo textApplyVo) {

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();

        contracttextmodel.setTextName(textApplyVo.getTextName());
//        contracttextmodel.setBusiID(textApplyVo.getBusiID());
//        contracttextmodel.setBusiType(textApplyVo.getBusiType());
        contracttextmodel.setApplicantExplain(textApplyVo.getApplicantExplain());
        contracttextmodel.setStatus(textApplyVo.getStatus());
        contracttextmodel.setTextModelType(textApplyVo.getTextModelType());
        contracttextmodel.setPubCorps(textApplyVo.getPubCorps());
        contracttextmodel.setModifiedBy(userInfo.getSysUser().getfCname());
        contracttextmodel.setModifiedDate(LocalDateTime.now());

        dealTextType(textApplyVo.getTypeTextIDs(), contracttextmodel);

        //文本编号
        if (textApplyVo.getStatus() == 5) {
            String textCode = generateTextCode("1");
            contracttextmodel.setTextCode(textCode);
        }

        textMapper.updateById(contracttextmodel);

        return DataResult.success(null);
    }

    /**
     * 文本编辑(第二版及以后)
     *
     * @param textEditVo
     * @return
     */
    @Transactional
    public DataResult textEdit(TextEditVo textEditVo) {
        if (StringUtils.isEmpty(textEditVo.getFileTemplateID())) {
            throw new BaseException("主键为空", 500);
        }

        if (StringUtils.isEmpty(textEditVo.getParentID())) {
            throw new BaseException("ParentID为为空", 500);
        }

        if (StringUtils.isEmpty(textEditVo.getStatus())) {
            throw new BaseException("状态为空", 500);
        }

        if (textEditVo.getStatus() == 5) {
            textEditVo.validateEdit();
        }

        CrContracttextmodel model = textMapper.selectById(textEditVo.getFileTemplateID());

        if (model == null) {
            //新插入
            return textEditinsert(textEditVo);
        } else {
            //更新
            return textEditUpdate(model, textEditVo);
        }
    }

    /**
     * 文本编辑新增(第二版及以后)
     *
     * @return
     */
    private DataResult textEditinsert(TextEditVo textEditVo) {

        CrContracttextmodel parentText = textMapper.selectById(textEditVo.getParentID());
        if (parentText == null) {
            throw new BaseException("ParentID不存在", 500);
        }

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();

        Integer orgId = null;
        Map<Integer, SysOrganization> orgMap = null;

        if (sysOrgList != null && sysOrgList.size() > 0) {
            orgId = sysOrgList.get(0).getfId();
            orgMap = sysOrgList.stream().collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k2));
        }

        CrContracttextmodel contracttextmodel = new CrContracttextmodel();
        contracttextmodel.setFileTemplateID(textEditVo.getFileTemplateID());
        contracttextmodel.setOrgID(parentText.getOrgID());
        contracttextmodel.setTextName(textEditVo.getTextName());
//        contracttextmodel.setTextName(parentText.getTextName());
        contracttextmodel.setBusiID(parentText.getBusiID());
        contracttextmodel.setBusiType(parentText.getBusiType());
        contracttextmodel.setApplicant(parentText.getApplicant());
        contracttextmodel.setApplicantTime(parentText.getPublishTime());
        contracttextmodel.setApplicantExplain(parentText.getApplicantExplain());
        contracttextmodel.setStatus(textEditVo.getStatus());
        contracttextmodel.setLogicDel(0);
        contracttextmodel.setCreatedBy(userInfo.getSysUser().getfCname());
        contracttextmodel.setCreatedDate(LocalDateTime.now());
//        contracttextmodel.setTextModelType(parentText.getTextModelType());
        contracttextmodel.setTextModelType(textEditVo.getTextModelType());
        contracttextmodel.setVersion(parentText.getVersion().add(new BigDecimal(1)));
        contracttextmodel.setPubCorps(textEditVo.getPubCorps());
        contracttextmodel.setCreatorID(userInfo.getSysUser().getfId().toString());
        contracttextmodel.setCreatorName(userInfo.getSysUser().getfCname());
        contracttextmodel.setCreatorDepartmentID(orgId);
        contracttextmodel.setCreatorDepartmentName(orgMap.get(orgId).getfName());
        contracttextmodel.setCreatorUnitID(userInfo.getUnitId());
        contracttextmodel.setCreatorUnitName(userInfo.getUnitName());
        contracttextmodel.setParentID(textEditVo.getParentID());
        contracttextmodel.setIdentity(parentText.getIdentity());
        contracttextmodel.setIsPrime(1);

        dealTextType(textEditVo.getTypeTextIDs(), contracttextmodel);
//        contracttextmodel.setTypeText(parentText.getTypeText());
//        contracttextmodel.setTypeTextID(parentText.getTypeTextID());
//        contracttextmodel.setType1(parentText.getType1());
//        contracttextmodel.setType2(parentText.getType2());
//        contracttextmodel.setType3(parentText.getType3());

        contracttextmodel.setModifiedExplain(textEditVo.getModifiedExplain());

        //文本编号
        if (textEditVo.getStatus() == 5) {
            String textCode = generateTextCode(contracttextmodel.getVersion().toString());
            contracttextmodel.setTextCode(textCode);
        }

        textMapper.insert(contracttextmodel);

        //更新IsPrime
        UpdateWrapper<CrContracttextmodel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(CrContracttextmodel::getIdentity, contracttextmodel.getIdentity()).
                ne(CrContracttextmodel::getFileTemplateID, contracttextmodel.getFileTemplateID());

        CrContracttextmodel isPrimeModel = new CrContracttextmodel();
        isPrimeModel.setIsPrime(0);

        textMapper.update(isPrimeModel, updateWrapper);

        return DataResult.success(null);

    }

    /**
     * 文本编辑修改(第二版及以后)
     *
     * @return
     */
    private DataResult textEditUpdate(CrContracttextmodel contracttextmodel, TextEditVo textEditVo) {

        CrContracttextmodel parentText = textMapper.selectById(textEditVo.getParentID());
        if (parentText == null) {
            throw new BaseException("ParentID不存在", 500);
        }

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        contracttextmodel.setStatus(textEditVo.getStatus());
        contracttextmodel.setPubCorps(textEditVo.getPubCorps());
        contracttextmodel.setParentID(textEditVo.getParentID());
        contracttextmodel.setModifiedExplain(textEditVo.getModifiedExplain());
        contracttextmodel.setModifiedBy(userInfo.getSysUser().getfCname());
        contracttextmodel.setModifiedDate(LocalDateTime.now());

        contracttextmodel.setTextName(textEditVo.getTextName());
        contracttextmodel.setTextModelType(textEditVo.getTextModelType());
        dealTextType(textEditVo.getTypeTextIDs(), contracttextmodel);

        //文本编号
        if (textEditVo.getStatus() == 5) {
            String textCode = generateTextCode(contracttextmodel.getVersion().toString());
            contracttextmodel.setTextCode(textCode);
        }

        textMapper.updateById(contracttextmodel);

        return DataResult.success(null);

    }

    /**
     * 文本废弃
     *
     * @return
     */
    public DataResult textDiscard(TextDiscardVo textDiscardVo) {
        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CrContracttextmodel::getIdentity, textDiscardVo.getIdentity());
        List<CrContracttextmodel> crContracttextmodelList = textMapper.selectList(queryWrapper);
        if (crContracttextmodelList != null && crContracttextmodelList.size() > 0) {
            for (CrContracttextmodel contracttextmodel : crContracttextmodelList) {
                contracttextmodel.setLogicDel(1);
                contracttextmodel.setDiscardReason(textDiscardVo.getReason());
                contracttextmodel.setDiscardDate(LocalDateTime.now());
                textMapper.updateById(contracttextmodel);
            }
        }
        return DataResult.success(null);


    }

    /**
     * 文本草稿删除
     *
     * @return
     */
    public DataResult textDraftDel(TextDraftDelVo textDraftDelVo) {
        CrContracttextmodel contracttextmodel = new CrContracttextmodel();
        contracttextmodel.setLogicDel(1);

        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper<CrContracttextmodel>();
        queryWrapper.lambda().eq(CrContracttextmodel::getStatus, TextStatusEnum.DRAFT).
                eq(CrContracttextmodel::getFileTemplateID, textDraftDelVo.getTextId());

        int update = textMapper.update(contracttextmodel, queryWrapper);
        if (update > 0) {
            return DataResult.success(null);
        } else {
            return DataResult.fail(null, 500, "操作失败");
        }

    }

    /**
     * 文本查询
     *
     * @param textQueryVo
     * @return
     */
    public DataResult<PageData<TextDetailResultVo>> textQuery(TextQueryVo textQueryVo) {

        Page<CrContracttextmodel> page = new Page<CrContracttextmodel>(textQueryVo.getCurrentPage(), textQueryVo.getPageSize());

        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper<CrContracttextmodel>();
        LambdaQueryWrapper<CrContracttextmodel> lambdaQueryWrapper = queryWrapper.lambda().
                eq(CrContracttextmodel::getLogicDel, 0).
                eq(CrContracttextmodel::getIsPrime, 1).
                orderByDesc(CrContracttextmodel::getApplicantTime);

        if (!StringUtils.isEmpty(textQueryVo.getTextName())) {
            lambdaQueryWrapper.like(CrContracttextmodel::getTextName, textQueryVo.getTextName());
        }

        if (!StringUtils.isEmpty(textQueryVo.getTextCode())) {
            lambdaQueryWrapper.like(CrContracttextmodel::getTextCode, textQueryVo.getTextCode());
        }

        if (textQueryVo.getStatus() != null) {
            lambdaQueryWrapper.eq(CrContracttextmodel::getStatus, textQueryVo.getStatus());
        }

        if (!StringUtils.isEmpty(textQueryVo.getStartTime())) {
            lambdaQueryWrapper.ge(CrContracttextmodel::getApplicantTime, textQueryVo.getStartTime());
        }

        if (!StringUtils.isEmpty(textQueryVo.getEndTime())) {
            lambdaQueryWrapper.le(CrContracttextmodel::getApplicantTime, textQueryVo.getEndTime());
        }

        IPage<CrContracttextmodel> iPage = textMapper.selectPage(page, lambdaQueryWrapper);

        List<TextDetailResultVo> resultList = new ArrayList<>();
        for (CrContracttextmodel model : iPage.getRecords()) {
            TextDetailResultVo resultVo = new TextDetailResultVo();
            BeanUtils.copyProperties(model, resultVo);
            resultList.add(resultVo);
        }

        PageData<TextDetailResultVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(resultList);

        return DataResult.success(pageData);

    }

    @Override
    public DataResult<TextDetailResultVo> textDetail(String textId) {
        CrContracttextmodel model = textMapper.selectById(textId);
        TextDetailResultVo resultVo = new TextDetailResultVo();
        BeanUtils.copyProperties(model, resultVo);
        return DataResult.success(resultVo);
    }

    @Override
    public DataResult<List<TextDetailResultVo>> historyList(String textId) {

        CrContracttextmodel model = textMapper.selectById(textId);
        if (model == null) {
            return DataResult.success(Collections.emptyList());
        }

        QueryWrapper<CrContracttextmodel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContracttextmodel::getIdentity, model.getIdentity()).
                eq(CrContracttextmodel::getIsPrime, 0).
                orderByDesc(CrContracttextmodel::getVersion);

        List<CrContracttextmodel> selectList = textMapper.selectList(queryWrapper);
        List<TextDetailResultVo> hisList = new ArrayList<>();
        for (CrContracttextmodel historyModel : selectList) {
            TextDetailResultVo resultVo = new TextDetailResultVo();
            BeanUtils.copyProperties(historyModel, resultVo);
            hisList.add(resultVo);
        }

        return DataResult.success(hisList);
    }


}
