package com.pcitc.szgt.contract.offeree.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.common.enums.offeree.OffereeStatus;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.finality.mapper.CrContractcaseMapper;
import com.pcitc.szgt.contract.offeree.entity.*;
import com.pcitc.szgt.contract.offeree.mapper.*;
import com.pcitc.szgt.contract.offeree.model.*;
import com.pcitc.szgt.contract.offeree.service.OffereeService;
import com.pcitc.szgt.contract.perform.mapper.CrContractendMapper;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
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

/**
 * @Auther lihe
 * @Date 2019/04/12
 * @Desc 相对人添加操作
 */
@Service
public class OffereeServiceImpl implements OffereeService {

    @Autowired
    private FfOffereeinfoMapper offereeinfoMapper;

    @Autowired
    private FfOffereebusinesslicenseMapper offereebusinesslicenseMapper;

    @Autowired
    private FfOffereelinkmanMapper offereelinkmanMapper;

    @Autowired
    private FfOffereebankMapper offereebankMapper;

    @Autowired
    private MdmOffereedataMapper mdmOffereedataMapper;

    @Autowired
    private DictionaryRequest dictionaryRequest;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private CrContractendMapper crContractendMapper;
    @Autowired
    private CrContractcaseMapper crContractcaseMapper;
    @Autowired
    private OrganizationRequest organizationRequest;

    /**
     * 相对人新增
     *
     * @param offereeVo
     * @return
     */
    @Transactional
    @Override
    public DataResult add(OffereeVo offereeVo) {

        // 如果提交，做数据校验
        if (offereeVo.getOffereeInfo().getStatus() == OffereeStatus.Complete.getType()) {
            offereeVo.validate();
        }

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();

        String orgIds = null;
        if (sysOrgList != null && sysOrgList.size() > 0) {
            orgIds = sysOrgList.stream().map(SysOrganization::getfId).map(Object::toString).collect(Collectors.joining(","));
        }

        // 插入相对人信息
        FfOffereeinfo offereeInfo = new FfOffereeinfo();
        BeanUtils.copyProperties(offereeVo.getOffereeInfo(), offereeInfo);

        offereeInfo.setOffereeId(offereeVo.getOffereeInfo().getOffereeId());
        offereeInfo.setDataSource(offereeVo.getOffereeInfo().getDataSource());
        offereeInfo.setLogicDel(0);
        offereeInfo.setCreatedBy(String.valueOf(userInfo.getSysUser().getfId()));
        offereeInfo.setCreatedDate(LocalDateTime.now());
        offereeInfo.setModifiedBy(String.valueOf(userInfo.getSysUser().getfId()));
        offereeInfo.setModifiedDate(LocalDateTime.now());
        offereeInfo.setCodeNames(offereeInfo.getOffereeCode() + "[" + offereeInfo.getOffereeName() + "]");
        offereeInfo.setOulabel(userInfo.getUnitId());
        offereeInfo.setOffereeOrgIds(orgIds);

        int num = offereeinfoMapper.insert(offereeInfo);
        if (num != 1) {
            throw new BaseException("相对人信息添加失败", 500);
        }

        //如果是机构，添加联系人
        if (offereeInfo.getOffereeType() == 0) {
            insertLinkMan(offereeVo);
        }

        //插入开户行信息
        insertBank(offereeVo);

        return DataResult.success(null);
    }

    /**
     * 相对人详情
     *
     * @param offereeId
     * @return
     */
    @Override
    public DataResult<OffereeResultVo> queryDetail(String offereeId) {

        FfOffereeinfo offereeinfo = offereeinfoMapper.selectById(offereeId);

        if (offereeinfo == null || offereeinfo.getLogicDel() == 1) {
            throw new BaseException("相对人不存在", 500);
        }

        //查询联系人信息
        QueryWrapper<FfOffereelinkman> linkmanWrapper = new QueryWrapper<FfOffereelinkman>();
        linkmanWrapper.lambda().eq(FfOffereelinkman::getOffereeID, offereeId).
                eq(FfOffereelinkman::getLogicDel, 0).
                orderByAsc(FfOffereelinkman::getOrderNumber);
        List<FfOffereelinkman> linkmen = offereelinkmanMapper.selectList(linkmanWrapper);

        //查询开户行信息
        QueryWrapper<FfOffereebank> bankWrapper = new QueryWrapper<FfOffereebank>();
        bankWrapper.lambda().eq(FfOffereebank::getOffereeID, offereeId).
                eq(FfOffereebank::getLogicDel, 0).
                orderByAsc(FfOffereebank::getOrderNumber);
        List<FfOffereebank> banks = offereebankMapper.selectList(bankWrapper);

        OffereeResultVo offereeVo = new OffereeResultVo();
        offereeVo.setOffereeInfo(new OffereeInfoVo());
        offereeVo.setOffereeBankList(new ArrayList<>());
        offereeVo.setOffereeLinkmanList(new ArrayList<>());

        BeanUtils.copyProperties(offereeinfo, offereeVo.getOffereeInfo());

        //如果内外部单位为空并且是mdm数据，去mdm里查
        if(StringUtils.isEmpty(offereeVo.getOffereeInfo().getSourceType())
                && offereeVo.getOffereeInfo().getDataSource() != null
                && offereeVo.getOffereeInfo().getDataSource() == 0){
            QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<>();
            LambdaQueryWrapper<MdmOffereedata> lambdaQueryWrapper = queryWrapper.lambda().
                    eq(MdmOffereedata::getOffereeCode, offereeVo.getOffereeInfo().getOffereeCode()).
                    select(MdmOffereedata::getSourceType);
            MdmOffereedata mdmOffereedata = mdmOffereedataMapper.selectOne(lambdaQueryWrapper);
            if(mdmOffereedata != null && !StringUtils.isEmpty(mdmOffereedata.getSourceType())){
                offereeVo.getOffereeInfo().setSourceType(mdmOffereedata.getSourceType());
            }
        }

        for (FfOffereelinkman linkman : linkmen) {
            OffereeLinkmanVo linkmanVo = new OffereeLinkmanVo();
            BeanUtils.copyProperties(linkman, linkmanVo);
            offereeVo.getOffereeLinkmanList().add(linkmanVo);
        }

        for (FfOffereebank bank : banks) {
            OffereeBankVo bankVo = new OffereeBankVo();
            BeanUtils.copyProperties(bank, bankVo);
            offereeVo.getOffereeBankList().add(bankVo);
        }

        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.valueOf(offereeVo.getOffereeInfo().getCreatedBy()));
        offereeVo.getOffereeInfo().setCreatedByName(sysUserinfo.getfCname());

        SysDictionarycategory dictionarycategory = dictionaryRequest.queryCategoryById(offereeVo.getOffereeInfo().getCompanyType());
        offereeVo.getOffereeInfo().setCompanyTypeName(dictionarycategory.getfName());
//        String sorts = transferOffereeSort(offereeVo.getOffereeInfo().getOffereeSort());
//        offereeVo.getOffereeInfo().setOffereeSort(sorts);

        return DataResult.success(offereeVo);
    }

    /**
     * 列表查询
     *
     * @param offereeQueryVo
     * @return
     */
    @Override
    public DataResult<PageData<OffereeInfoVo>> queryOfferee(OffereeQueryVo offereeQueryVo) {

        Boolean isQueryPerson = offereeQueryVo.isQueryPerson();

        if (offereeQueryVo.getPageNum() == null) {
            offereeQueryVo.setPageNum(1);
        }

        if (offereeQueryVo.getPageSize() == null) {
            offereeQueryVo.setPageSize(20);
        }

        Page<FfOffereeinfo> page = new Page<FfOffereeinfo>(offereeQueryVo.getPageNum(), offereeQueryVo.getPageSize());

        if (isQueryPerson) {
            //查询机构和自然人
            String offereename = offereeQueryVo.getOffereename();
            Integer isEnable = offereeQueryVo.getIsEnable();

            QueryWrapper<FfOffereeinfo> infoWrapper = new QueryWrapper<FfOffereeinfo>();
            infoWrapper.lambda().orderByDesc(FfOffereeinfo::getModifiedDate);
            infoWrapper.lambda().eq(FfOffereeinfo::getLogicDel, 0);

            if (!StringUtils.isEmpty(offereename) && isEnable == null) {
                infoWrapper.lambda().like(FfOffereeinfo::getOffereeName, offereename).
                        eq(FfOffereeinfo::getLogicDel, 0).
                        or().
                        eq(FfOffereeinfo::getNaturePerson, offereename).
                        eq(FfOffereeinfo::getLogicDel, 0);

            } else if (StringUtils.isEmpty(offereename) && isEnable != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getIsEnable, isEnable);

            } else if (!StringUtils.isEmpty(offereename) && isEnable != null) {

                infoWrapper.lambda().eq(FfOffereeinfo::getOffereeName, offereename).
                        eq(FfOffereeinfo::getIsEnable, isEnable).
                        eq(FfOffereeinfo::getLogicDel, 0).
                        or().
                        eq(FfOffereeinfo::getOffereeName, offereename).
                        eq(FfOffereeinfo::getIsEnable, isEnable).
                        eq(FfOffereeinfo::getLogicDel, 0);
            }
            if (!StringUtils.isEmpty(offereeQueryVo.getRegisterAddr())) {
                QueryWrapper<FfOffereebusinesslicense> licenseWrqpper = new QueryWrapper<>();
                licenseWrqpper.lambda().eq(FfOffereebusinesslicense::getRegisterAddr, offereeQueryVo.getRegisterAddr());
                List<FfOffereebusinesslicense> licenses = offereebusinesslicenseMapper.selectList(licenseWrqpper);
                List<String> licenseOffereeIds = licenses.stream().map(FfOffereebusinesslicense::getOffereeID).map(Object::toString).collect(Collectors.toList());
                infoWrapper.lambda().in(FfOffereeinfo::getOffereeId, licenseOffereeIds);
            }
            if (offereeQueryVo.getStatus() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getStatus, offereeQueryVo.getStatus());//状态
            }
            if (offereeQueryVo.getIsEnable() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getIsEnable, offereeQueryVo.getIsEnable());//是否启用
            }
            if (!StringUtils.isEmpty(offereeQueryVo.getOffereesort())) {
                infoWrapper.lambda().eq(FfOffereeinfo::getOffereeSort, offereeQueryVo.getOffereesort());//相对人分类
            }
            if (offereeQueryVo.getBlong() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getOffereeeBelong, offereeQueryVo.getBlong());//相对人归属
            }
            if (offereeQueryVo.getDataSource() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getDataSource, offereeQueryVo.getDataSource());//相对人来源
            }
            infoWrapper.lambda().orderByDesc(FfOffereeinfo::getCreatedDate);//按创建时间倒叙排列/引用时间
            IPage<FfOffereeinfo> ffOffereeinfoIPage = offereeinfoMapper.selectPage(page, infoWrapper);
            List<OffereeInfoVo> offereeInfoVos = new ArrayList<>();

            // userinfo map
            List<FfOffereeinfo> records = ffOffereeinfoIPage.getRecords();
            List<Integer> createUids = records.stream().map(FfOffereeinfo::getCreatedBy).map(Integer::valueOf).collect(Collectors.toList());
            List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(createUids.toArray(new Integer[0]));
            Map<Integer, String> userInfoMap = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, SysUserinfo::getfCname, (k1, k2) -> k1));

            for (FfOffereeinfo offereeinfo : records) {
                OffereeInfoVo offereeInfoVo = new OffereeInfoVo();
                BeanUtils.copyProperties(offereeinfo, offereeInfoVo);

                String sorts = transferOffereeSort(offereeInfoVo.getOffereeSort());
                offereeInfoVo.setOffereeSort(sorts);

                //如果内外部单位为空并且是mdm数据，去mdm里查
                if(StringUtils.isEmpty(offereeinfo.getSourceType())
                        && offereeinfo.getDataSource() != null
                        && offereeinfo.getDataSource() == 0){
                    QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<>();
                    LambdaQueryWrapper<MdmOffereedata> lambdaQueryWrapper = queryWrapper.lambda().
                            eq(MdmOffereedata::getOffereeCode, offereeinfo.getOffereeCode()).
                            select(MdmOffereedata::getSourceType);
                    MdmOffereedata mdmOffereedata = mdmOffereedataMapper.selectOne(lambdaQueryWrapper);
                    if(mdmOffereedata != null && !StringUtils.isEmpty(mdmOffereedata.getSourceType())){
                        offereeInfoVo.setSourceType(mdmOffereedata.getSourceType());
                    }
                }

                //异常履约情况
                QueryWrapper<FfOffereeinfo> offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", offereeinfo.getOffereeId());
                offereeinfoQueryWrapper.eq("IsNormal", 0);//异常履约
                List<OffereeContractVo> listEnd = offereeinfoMapper.selectUnusualPerform(offereeinfoQueryWrapper);
                List<OffereeContractVo> ends = new ArrayList<>();
                if (listEnd != null && listEnd.size() > 0) {
                    /*  offereeInfoVo.setEnds(listEnd);*/
                    for (OffereeContractVo offereeContractVo : listEnd) {
                        if (!StringUtils.isEmpty(offereeContractVo.getCreateUserId())) {
                            SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(offereeContractVo.getCreateUserId()));
                            if (sysUserinfo != null) {
                                offereeContractVo.setCreateUserName(sysUserinfo.getfCname());
                            }
                            List<Integer> orgIdList = new ArrayList<>();
                            String orgIds = "";
                            String orgNames = "";
                            List<Integer> userOrgs = userInfoRequest.queryOrgs(offereeContractVo.getCreateUserId());
                            if (userOrgs != null && userOrgs.size() > 0) {
                                for (Integer userOrg : userOrgs) {
                                    SysOrganization sysOrganization = organizationRequest.getOrgCompany(userOrg);
                                    if (sysOrganization != null && !orgIdList.contains(sysOrganization.getfId())) {
                                        orgIdList.add(sysOrganization.getfId());
                                        orgIds += sysOrganization.getfId() + ";";
                                        orgNames += sysOrganization.getfName() + ";";
                                    }
                                }
                            }
                            if (!StringUtils.isEmpty(orgIds)) {
                                orgIds = orgIds.substring(0, orgIds.length() - 1);
                            }
                            if (!StringUtils.isEmpty(orgNames)) {
                                orgNames = orgNames.substring(0, orgNames.length() - 1);
                            }
                            offereeContractVo.setCreateOrgId(orgIds);
                            offereeContractVo.setCreateOrgName(orgNames);
                            ends.add(offereeContractVo);
                        }
                    }
                    offereeInfoVo.setEnds(ends);
                    offereeInfoVo.setEndCount(listEnd.size());
                } else {
                    List<OffereeContractVo> list = new ArrayList<>();
                    list.add(new OffereeContractVo() {
                    });
                    offereeInfoVo.setEnds(list);
                    offereeInfoVo.setEndCount(0);
                }
                //发案情况
                offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", offereeinfo.getOffereeId());
                offereeinfoQueryWrapper.eq("b.LogicDel", Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
                List<OffereeContractVo> listCase = offereeinfoMapper.selectCase(offereeinfoQueryWrapper);
                if (listCase != null && listCase.size() > 0) {
                    offereeInfoVo.setCases(listCase);
                    offereeInfoVo.setCaseCount(listCase.size());
                } else {
                    List<OffereeContractVo> list = new ArrayList<>();
                    list.add(new OffereeContractVo() {
                    });
                    offereeInfoVo.setCases(list);
                    offereeInfoVo.setCaseCount(0);
                }

                offereeInfoVo.setCreatedByName(userInfoMap.get(Integer.valueOf(offereeInfoVo.getCreatedBy())));
                offereeInfoVos.add(offereeInfoVo);
            }

            PageData<OffereeInfoVo> pageData = new PageData<>();
            pageData.setCurrentPage(page.getCurrent());
            pageData.setPageSize(page.getSize());
            pageData.setTotalCount(ffOffereeinfoIPage.getTotal());
            pageData.setTotalPage(ffOffereeinfoIPage.getPages());
            pageData.setData(offereeInfoVos);

            return DataResult.success(pageData);

        } else {
            //只查询机构
            QueryWrapper<FfOffereeinfo> infoWrapper = new QueryWrapper<FfOffereeinfo>();
            infoWrapper.lambda().eq(FfOffereeinfo::getLogicDel, 0);
            infoWrapper.lambda().orderByDesc(FfOffereeinfo::getModifiedDate);

            if (!StringUtils.isEmpty(offereeQueryVo.getOffereename())) {
                infoWrapper.lambda().eq(FfOffereeinfo::getOffereeName, offereeQueryVo.getOffereename());
            }

            if (!StringUtils.isEmpty(offereeQueryVo.getOffereecode())) {
                infoWrapper.lambda().like(FfOffereeinfo::getOffereeCode, offereeQueryVo.getOffereecode());
            }

            if (!StringUtils.isEmpty(offereeQueryVo.getCreditCode())) {
                infoWrapper.lambda().eq(FfOffereeinfo::getCreditCode, offereeQueryVo.getCreditCode());//信用代码
            }
            if (offereeQueryVo.getStatus() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getStatus, offereeQueryVo.getStatus());//状态
            }
            if (offereeQueryVo.getIsEnable() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getIsEnable, offereeQueryVo.getIsEnable());//是否启用
            }
            if (offereeQueryVo.getBlong() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getOffereeeBelong, offereeQueryVo.getBlong());//相对人归属
            }
            if (offereeQueryVo.getDataSource() != null) {
                infoWrapper.lambda().eq(FfOffereeinfo::getDataSource, offereeQueryVo.getDataSource());//相对人来源
            }

            if (!StringUtils.isEmpty(offereeQueryVo.getRegisterAddr())) {
                QueryWrapper<FfOffereebusinesslicense> licenseWrqpper = new QueryWrapper<>();
                licenseWrqpper.lambda().eq(FfOffereebusinesslicense::getRegisterAddr, offereeQueryVo.getRegisterAddr());
                List<FfOffereebusinesslicense> licenses = offereebusinesslicenseMapper.selectList(licenseWrqpper);
                List<String> licenseOffereeIds = licenses.stream().map(FfOffereebusinesslicense::getOffereeID).map(Object::toString).collect(Collectors.toList());
                infoWrapper.lambda().in(FfOffereeinfo::getOffereeId, licenseOffereeIds);
            }

            if (!StringUtils.isEmpty(offereeQueryVo.getOffereesort())) {
                infoWrapper.lambda().apply("find_in_set({0}, OffereeSort)", offereeQueryVo.getOffereesort());
            }
            infoWrapper.lambda().orderByDesc(FfOffereeinfo::getCreatedDate);//按创建时间倒叙排列/引用时间
            IPage<FfOffereeinfo> ffOffereeinfoIPage = offereeinfoMapper.selectPage(page, infoWrapper);
            List<OffereeInfoVo> offereeInfoVos = new ArrayList<>();

            // userinfo map
            List<FfOffereeinfo> records = ffOffereeinfoIPage.getRecords();
            List<Integer> createUids = records.stream().map(FfOffereeinfo::getCreatedBy).map(Integer::valueOf).collect(Collectors.toList());
            List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(createUids.toArray(new Integer[0]));
            Map<Integer, String> userInfoMap = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, SysUserinfo::getfCname, (k1, k2) -> k1));

            for (FfOffereeinfo offereeinfo : records) {
                OffereeInfoVo offereeInfoVo = new OffereeInfoVo();
                BeanUtils.copyProperties(offereeinfo, offereeInfoVo);

                String sorts = transferOffereeSort(offereeInfoVo.getOffereeSort());
                offereeInfoVo.setOffereeSort(sorts);
                //异常履约情况
                QueryWrapper<FfOffereeinfo> offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", offereeinfo.getOffereeId());
                offereeinfoQueryWrapper.eq("IsNormal", 0);//异常履约
                List<OffereeContractVo> listEnd = offereeinfoMapper.selectUnusualPerform(offereeinfoQueryWrapper);
                if (listEnd != null && listEnd.size() > 0) {
                    offereeInfoVo.setEnds(listEnd);
                    offereeInfoVo.setEndCount(listEnd.size());
                } else {
                    List<OffereeContractVo> list = new ArrayList<>();
                    list.add(new OffereeContractVo() {
                    });
                    offereeInfoVo.setEnds(list);
                    offereeInfoVo.setEndCount(0);
                }

                //如果内外部单位为空并且是mdm数据，去mdm里查
                if(StringUtils.isEmpty(offereeinfo.getSourceType())
                        && offereeinfo.getDataSource() != null
                        && offereeinfo.getDataSource() == 0){
                    QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<>();
                    LambdaQueryWrapper<MdmOffereedata> lambdaQueryWrapper = queryWrapper.lambda().
                            eq(MdmOffereedata::getOffereeCode, offereeinfo.getOffereeCode()).
                            select(MdmOffereedata::getSourceType);
                    MdmOffereedata mdmOffereedata = mdmOffereedataMapper.selectOne(lambdaQueryWrapper);
                    if(mdmOffereedata != null && !StringUtils.isEmpty(mdmOffereedata.getSourceType())){
                        offereeinfo.setSourceType(mdmOffereedata.getSourceType());
                    }
                }

                //发案情况
                offereeinfoQueryWrapper = new QueryWrapper<>();
                offereeinfoQueryWrapper.eq("c.OffereeId", offereeinfo.getOffereeId());
                offereeinfoQueryWrapper.eq("b.LogicDel", offereeinfo.getOffereeId());
                List<OffereeContractVo> listCase = offereeinfoMapper.selectCase(offereeinfoQueryWrapper);
                if (listCase != null && listCase.size() > 0) {
                    offereeInfoVo.setEnds(listCase);
                    offereeInfoVo.setCaseCount(listCase.size());
                } else {
                    List<OffereeContractVo> list = new ArrayList<>();
                    list.add(new OffereeContractVo() {
                    });
                    offereeInfoVo.setCases(list);
                    offereeInfoVo.setCaseCount(0);
                }

                offereeInfoVo.setCreatedByName(userInfoMap.get(Integer.valueOf(offereeInfoVo.getCreatedBy())));
                offereeInfoVos.add(offereeInfoVo);
            }

            PageData<OffereeInfoVo> pageData = new PageData<>();
            pageData.setCurrentPage(page.getCurrent());
            pageData.setPageSize(page.getSize());
            pageData.setTotalCount(ffOffereeinfoIPage.getTotal());
            pageData.setTotalPage(ffOffereeinfoIPage.getPages());
            pageData.setData(offereeInfoVos);

            return DataResult.success(pageData);
        }
    }

    /**
     * 删除相对人
     *
     * @param offereeId
     */
    @Transactional
    @Override
    public void deleteOfferee(String offereeId) {
        //删除相对人信息
        FfOffereeinfo offereeinfoRecord = new FfOffereeinfo();
        offereeinfoRecord.setLogicDel(1);

        UpdateWrapper<FfOffereeinfo> infoWrapper = new UpdateWrapper<FfOffereeinfo>();
        infoWrapper.lambda().eq(FfOffereeinfo::getOffereeId, offereeId);

        offereeinfoMapper.update(offereeinfoRecord, infoWrapper);

        //删除联系人
        deleteLinkMan(offereeId);

        //删除开户行
        deleteBank(offereeId);
    }

    /**
     * 更新相对人
     *
     * @param offereeVo
     */
    @Transactional
    @Override
    public void updateOfferee(OffereeVo offereeVo) {

        // 如果提交，做数据校验
        if (offereeVo.getOffereeInfo().getStatus() == OffereeStatus.Complete.getType()) {
            offereeVo.validate();
        }

        if (StringUtils.isEmpty(offereeVo.getOffereeInfo().getOffereeId())) {
            throw new BaseException("缺少相对人ID", 500);
        }

        FfOffereeinfo oldoffereeinfo = offereeinfoMapper.selectById(offereeVo.getOffereeInfo().getOffereeId());
        if (oldoffereeinfo == null || oldoffereeinfo.getLogicDel() == 1) {
            throw new BaseException("相对人不存在", 500);
        }

        //获取当前用户信息
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        // 更新相对人信息
        FfOffereeinfo offereeInfo = new FfOffereeinfo();
        BeanUtils.copyProperties(offereeVo.getOffereeInfo(), offereeInfo);
        offereeInfo.setModifiedBy(String.valueOf(userInfo.getSysUser().getfId()));
        offereeInfo.setModifiedDate(LocalDateTime.now());
        offereeInfo.setCodeNames(offereeInfo.getOffereeCode() + "[" + offereeInfo.getOffereeName() + "]");

        int updateCount = offereeinfoMapper.updateById(offereeInfo);

        if (updateCount != 1) {
            throw new BaseException("相对人信息修改失败", 500);
        }

        //如果是机构，更新联系人
        if (offereeInfo.getOffereeType() == 0) {

            //删除联系人
            deleteLinkMan(offereeVo.getOffereeInfo().getOffereeId());

            //重新添加
            insertLinkMan(offereeVo);
        }

        //-------更新开户行------
        //删除开户行
        deleteBank(offereeVo.getOffereeInfo().getOffereeId());

        //重新添加开户行
        insertBank(offereeVo);
    }

    /**
     * 相对人引用列表查询
     *
     * @return
     */
    public DataResult<PageData<MdmOffereedataVo>> queryMdm(MdmQueryVo mdmQueryVo) {

        QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<MdmOffereedata>();
        queryWrapper.lambda().and(i -> i.eq(MdmOffereedata::getCompanyType, "0").or().
                isNull(MdmOffereedata::getCompanyType));
        if (!StringUtils.isEmpty(mdmQueryVo.getOffereeName())) {
            queryWrapper.lambda().like(MdmOffereedata::getOffereeName, mdmQueryVo.getOffereeName());
        }
        if (!StringUtils.isEmpty(mdmQueryVo.getOffereeCode())) {
            queryWrapper.lambda().like(MdmOffereedata::getOffereeCode, mdmQueryVo.getOffereeCode());
        }
        if (mdmQueryVo.getOffereeSource() != null) {
            queryWrapper.lambda().eq(MdmOffereedata::getCategory, mdmQueryVo.getOffereeSource());
        }
        if (!StringUtils.isEmpty(mdmQueryVo.getSourceType())) {
            queryWrapper.lambda().eq(MdmOffereedata::getSourceType, mdmQueryVo.getSourceType());
        }

        if (mdmQueryVo.getPageNum() == null) {
            mdmQueryVo.setPageNum(1);
        }

        if (mdmQueryVo.getPageSize() == null) {
            mdmQueryVo.setPageSize(10);
        }

        Page<MdmOffereedata> page = new Page<MdmOffereedata>(mdmQueryVo.getPageNum(), mdmQueryVo.getPageSize());
        IPage<MdmOffereedata> Ipage = mdmOffereedataMapper.selectPage(page, queryWrapper);

        List<MdmOffereedataVo> dataList = new ArrayList<>();
        for(MdmOffereedata mdmOffereedata : Ipage.getRecords()){
            MdmOffereedataVo mdmOffereedataVo = new MdmOffereedataVo();
            BeanUtils.copyProperties(mdmOffereedata, mdmOffereedataVo);
            String s = transferOffereeSort(mdmOffereedata.getOffereeSort());
            mdmOffereedataVo.setOffereeSortName(s);
            dataList.add(mdmOffereedataVo);
        }

        PageData<MdmOffereedataVo> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(dataList);

        return DataResult.success(pageData);

    }

    /**
     * 根据相对人id删除联系人
     */
    private void deleteLinkMan(String offereeId) {
        FfOffereelinkman linkmanRecord = new FfOffereelinkman();
        linkmanRecord.setLogicDel(1);

        UpdateWrapper<FfOffereelinkman> linkmanWrapper = new UpdateWrapper<FfOffereelinkman>();
        linkmanWrapper.lambda().eq(FfOffereelinkman::getOffereeID, offereeId);
        offereelinkmanMapper.update(linkmanRecord, linkmanWrapper);
    }

    /**
     * 根据相对人id删除开户行
     *
     * @param offereeId
     */
    private void deleteBank(String offereeId) {
        FfOffereebank bankRecord = new FfOffereebank();
        bankRecord.setLogicDel(1);

        UpdateWrapper<FfOffereebank> bankWrapper = new UpdateWrapper<FfOffereebank>();
        bankWrapper.lambda().eq(FfOffereebank::getOffereeID, offereeId);

        offereebankMapper.update(bankRecord, bankWrapper);
    }

    //插入机构相对人联系人
    private void insertLinkMan(OffereeVo offereeVo) {

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        //插入联系人信息
        List<OffereeLinkmanVo> offereeLinkmanVoList = offereeVo.getOffereeLinkmanList();
        if (!CollectionUtils.isEmpty(offereeLinkmanVoList)) {
            int count = 0;
            for (OffereeLinkmanVo linkmanVo : offereeLinkmanVoList) {
                FfOffereelinkman linkman = new FfOffereelinkman();
                BeanUtils.copyProperties(linkmanVo, linkman);

                String linkmanuuid = UUIDUtils.getUUID();
                linkman.setLinkID(linkmanuuid);
                linkman.setOffereeID(offereeVo.getOffereeInfo().getOffereeId());
                linkman.setCreatedBy(userInfo.getSysUser().getfCname());
                linkman.setCreatedDate(LocalDate.now());
                linkman.setOulabel(userInfo.getUnitId());
                linkman.setOrderNumber(count);
                linkman.setLogicDel(0);

                int linkInsertNum = offereelinkmanMapper.insert(linkman);
                if (linkInsertNum != 1) {
                    throw new BaseException("联系人信息添加失败", 500);
                }

                count++;
            }
        }
    }

    //插入开户行
    private void insertBank(OffereeVo offereeVo) {

        UserInfo userInfo = currentUserUtil.currentUserInfo();

        //插入开户行信息
        List<OffereeBankVo> offereeBankVoList = offereeVo.getOffereeBankList();
        if (!CollectionUtils.isEmpty(offereeBankVoList)) {
            int count = 0;
            for (OffereeBankVo bankVo : offereeBankVoList) {
                FfOffereebank bank = new FfOffereebank();
                BeanUtils.copyProperties(bankVo, bank);

                String bankuuid = UUIDUtils.getUUID();
                bank.setBankID(bankuuid);
                bank.setOffereeID(offereeVo.getOffereeInfo().getOffereeId());
                bank.setCreatedBy(userInfo.getSysUser().getfCname());
                bank.setCreatedDate(LocalDateTime.now());
                bank.setOulabel(userInfo.getUnitId());
                bank.setLogicDel(0);
                bank.setOrderNumber(count);
                int bankInsertNum = offereebankMapper.insert(bank);
                if (bankInsertNum != 1) {
                    throw new BaseException("开户行信息添加失败", 500);
                }

                count++;
            }
        }
    }

    /**
     * 将相对人类型id转化为名称
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

    /*
     * 相对人引用
     * */
    @Override
    @Transactional
    public String addMDMOfferee(String mdmOffereeId) {
        if (StringUtils.isEmpty(mdmOffereeId)) {
            throw new BaseException("引用ID为空", 500);
        }
        MdmOffereedata mdmOffereedata = mdmOffereedataMapper.selectById(mdmOffereeId);
        if (mdmOffereedata == null) {
            throw new BaseException("引用数据不存在", 500);
        }
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        QueryWrapper<FfOffereeinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FfOffereeinfo::getOffereeCode, mdmOffereedata.getOffereeCode());
        queryWrapper.lambda().eq(FfOffereeinfo::getOulabel, userInfo.getUnitId());//所在企业
        queryWrapper.lambda().eq(FfOffereeinfo::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        List<FfOffereeinfo> list = offereeinfoMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            throw new BaseException("此相对人已被引用", 500);
        }
        FfOffereeinfo ffOffereeinfo = new FfOffereeinfo();
        ffOffereeinfo.setOffereeId(UUID.randomUUID().toString());
        ffOffereeinfo.setOffereeCode(mdmOffereedata.getOffereeCode());
        ffOffereeinfo.setOffereeName(mdmOffereedata.getOffereeName());
        ffOffereeinfo.setDataSource(mdmOffereedata.getCategory());//数据来源
        ffOffereeinfo.setSourceType(mdmOffereedata.getSourceType());//INSYS_INUNIT 内部单位   INSYS_OUTUNIT 外部单位
        if (!StringUtils.isEmpty(mdmOffereedata.getOffereeBelong())) {
            ffOffereeinfo.setOffereeeBelong(Integer.parseInt(mdmOffereedata.getOffereeBelong()));
        }
        ffOffereeinfo.setOffereeSort(mdmOffereedata.getOffereeSort());//相对人分类 供应商 客户
        if (!StringUtils.isEmpty(mdmOffereedata.getCompanyType())) {
            ffOffereeinfo.setCompanyType(Integer.parseInt(mdmOffereedata.getCompanyType()));//机构类型
        }
        ffOffereeinfo.setCreditCode(mdmOffereedata.getOrgCode());//营业执照、信用证书三证
        ffOffereeinfo.setOffereeType(mdmOffereedata.getOffereeType());//相对人类型  0机构 1自然人
        ffOffereeinfo.setAddress(mdmOffereedata.getOfficeAddress());
        //begin 自然人
        if (mdmOffereedata.getOffereeType() == 1) {
            ffOffereeinfo.setNaturePerson(mdmOffereedata.getOffereeName());
            ffOffereeinfo.setIDCard(mdmOffereedata.getIDCard());
            ffOffereeinfo.setPhone(mdmOffereedata.getLinkManPhone());
        }
        ffOffereeinfo.setCorporation(mdmOffereedata.getCorporation());//法定代表人  Update 2020097
        ffOffereeinfo.setLogicDel(0);
        ffOffereeinfo.setCreatedBy(String.valueOf(userInfo.getSysUser().getfId()));
        ffOffereeinfo.setCreatedDate(LocalDateTime.now());
        ffOffereeinfo.setCodeNames(mdmOffereedata.getOffereeCode() + "[" + mdmOffereedata.getOffereeName() + "]");
        ffOffereeinfo.setIsEnable(1);//是否启用
        ffOffereeinfo.setOulabel(userInfo.getUnitId());
        ffOffereeinfo.setCreatedBy(userInfo.getSysUser().getfId().toString());
        ffOffereeinfo.setCreatedDate(LocalDateTime.now());
        ffOffereeinfo.setModifiedBy(userInfo.getSysUser().getfId().toString());
        ffOffereeinfo.setModifiedDate(LocalDateTime.now());


        //联系人
        FfOffereelinkman ffOffereelinkman = new FfOffereelinkman();
        ffOffereelinkman.setLinkID(UUID.randomUUID().toString());
        ffOffereelinkman.setOffereeID(ffOffereeinfo.getOffereeId());
        ffOffereelinkman.setLinkManName(mdmOffereedata.getLinkManName());
        ffOffereelinkman.setLinkManPosition(mdmOffereedata.getLinkManPosition());
        ffOffereelinkman.setPhone(mdmOffereedata.getLinkManPhone());
        ffOffereelinkman.setEmail(mdmOffereedata.getEmail());
        ffOffereelinkman.setFax(mdmOffereedata.getFax());
        ffOffereelinkman.setOrderNumber(1);
        ffOffereelinkman.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        ffOffereelinkman.setCreatedBy(userInfo.getSysUser().getfId().toString());
        ffOffereelinkman.setCreatedDate(LocalDate.now());
        //银行信息
        FfOffereebank ffOffereebank = new FfOffereebank();
        ffOffereebank.setBankID(UUID.randomUUID().toString());
        ffOffereebank.setOffereeID(ffOffereeinfo.getOffereeId());
        ffOffereebank.setBankAcount(mdmOffereedata.getBankAcount());
        ffOffereebank.setBankCode(mdmOffereedata.getBankCode());
        ffOffereebank.setBankName(mdmOffereedata.getBankName());
        ffOffereebank.setRemark01(mdmOffereedata.getOpenUints());//银行开户名称
        ffOffereebank.setOrderNumber(1);
        ffOffereebank.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        ffOffereebank.setOulabel(ffOffereeinfo.getOulabel());
        ffOffereebank.setCreatedBy(userInfo.getSysUser().getfId().toString());
        ffOffereebank.setCreatedDate(LocalDateTime.now());

        offereeinfoMapper.insert(ffOffereeinfo);
        offereelinkmanMapper.insert(ffOffereelinkman);
        offereebankMapper.insert(ffOffereebank);
        return ffOffereeinfo.getOffereeId();
    }

    /*
     * 获取联系人信息
     * */
    @Override
    public DataResult getOffereeLinkMan(String offereeId) {
        List<Object> list = new ArrayList<>();
        QueryWrapper<FfOffereelinkman> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FfOffereelinkman::getOffereeID, offereeId);
        queryWrapper.lambda().eq(FfOffereelinkman::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        List<FfOffereelinkman> ffOffereelinkmanList = offereelinkmanMapper.selectList(queryWrapper);
        if (ffOffereelinkmanList != null && ffOffereelinkmanList.size() > 0) {
            for (FfOffereelinkman ffOffereelinkman : ffOffereelinkmanList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("offereeeId", ffOffereelinkman.getOffereeID());//相对人ID
                jsonObject.put("linkId", ffOffereelinkman.getLinkID());//联系人ID
                jsonObject.put("linkManName", ffOffereelinkman.getLinkManName());//联系人姓名
                jsonObject.put("linkManPosition", ffOffereelinkman.getLinkManPosition());//联系人职位
                jsonObject.put("email", ffOffereelinkman.getEmail());//邮箱
                jsonObject.put("fax", ffOffereelinkman.getFax());//传值
                jsonObject.put("phone", ffOffereelinkman.getPhone());//电话
                list.add(jsonObject);
            }

        }
        return DataResult.success(list);
    }

    /*
     * 获取银行信息
     * */
    @Override
    public DataResult getoffereeBank(String offereeId) {
        List<Object> list = new ArrayList<>();
        QueryWrapper<FfOffereebank> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FfOffereebank::getOffereeID, offereeId);
        queryWrapper.lambda().eq(FfOffereebank::getLogicDel, Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
        List<FfOffereebank> ffOffereebankList = offereebankMapper.selectList(queryWrapper);
        if (ffOffereebankList != null && ffOffereebankList.size() > 0) {
            for (FfOffereebank ffOffereebank : ffOffereebankList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("offereeeId", ffOffereebank.getOffereeID());//相对人ID
                jsonObject.put("bankId", ffOffereebank.getBankID());//银行ID
                jsonObject.put("bankAcount", ffOffereebank.getBankAcount());//银行账号
                jsonObject.put("bankCode", ffOffereebank.getBankCode());//银行编号
                jsonObject.put("bankName", ffOffereebank.getBankName());//开户银行名称
                jsonObject.put("bankUK", ffOffereebank.getBankUK());//开户名称
                list.add(jsonObject);
            }

        }
        return DataResult.success(list);
    }

}
