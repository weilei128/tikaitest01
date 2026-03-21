package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryOperateBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.*;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryOrgBasicMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireElectionR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInterestConflict;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgLog;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryQualifiInfo;
import com.pcitc.legalAffairs.vo.Intermediary.*;
import com.pctic.common.utils.UserUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FwIntermediaryOrgBasicServiceImpl extends ServiceImpl<FwIntermediaryOrgBasicMapper, FwIntermediaryOrgBasic>
        implements IFwIntermediaryOrgBasicService {
    /**
     * 银行信息服务层
     */
    @Autowired
    private IFwIntermediaryBankInfoService bankInfoService;
    /**
     * 联系人服务层
     */
    @Autowired
    private IFwIntermediaryLinkmanInfoService linkmanInfoService;
    /**
     * 市场评价服务层
     */
    @Autowired
    private IFwIntermediaryMarketEvalService marketEvalService;
    /**
     * 资质信息服务层
     */
    @Autowired
    private IFwIntermediaryQualifiInfoService qualifiInfoService;
    /**
     * 利益冲突服务层
     */
    @Autowired
    private IFwIntermediaryInterestConflictService conflictService;
    /**
     * 操作记录服务层
     */
    @Autowired
    private IFwIntermediaryOrgLogService ologService;
    /**
     * 操作记录服务层
     */
	@Autowired
	private IFwIntermediaryConflictLogService clogService;
    /**
     * 聘用信息服务层
     */
    @Autowired
    private IFwIntermediaryHireInfoService hireService;
    /**
     * 聘用信息服务层
     */
    @Autowired
    private IFwIntermediaryInfoHireRService hireRService;
    /**
     * 聘用信息服务层
     */
    @Autowired
    private IFwIntermediaryInfoHireElectionRService electionRService;
    @Autowired
    private FwIntermediaryOrgBasicMapper orgBasicMapper;

    /**
     * 根据主键查询中介机构信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryOrgBasic orgBasic = orgBasicMapper.selectById(fId);
        FwIntermediaryOrgBasicVo orgBasicVo = new FwIntermediaryOrgBasicVo();
        if (orgBasic != null) {
            BeanUtils.copyProperties(orgBasic, orgBasicVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(orgBasicVo);
            result.setMsg("获取数据成功！");
        } else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    /**
     * 根据关联聘用信息主键查询中介机构信息列表
     *
     * @param fkHireInfoId
     * @return
     */
    @Override
    public Result queryListHireInfoId(Long fkHireInfoId) {
    	return queryByHireId(fkHireInfoId);
    }

    /**
     * 根据关联聘用信息主键查询中介机构信息列表
     *
     * @param fkHireInfoId
     * @return
     */
    @Override
    public Result queryListElectionInfoId(Long fkHireInfoId) {
    	return queryElectionsByHireId(fkHireInfoId);
    }
    
    /**
     * 根据主键查询中介机构所有信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryAllById(Long fId) {
        Result result = new Result();
        FwIntermediaryOrgBasic orgBasic = orgBasicMapper.selectById(fId);
        if (orgBasic == null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        } else {
            FwIntermediaryOrgPageVo orgPageVo = new FwIntermediaryOrgPageVo();
            BeanUtils.copyProperties(orgBasic, orgPageVo);
            List<FwIntermediaryBankInfoVo> bankInfoVoList = (List<FwIntermediaryBankInfoVo>) bankInfoService.queryListByIntermId(fId).getData();
            orgPageVo.setBankInfoVoList(bankInfoVoList);
            List<FwIntermediaryLinkmanInfoVo> linkmanInfoVoList = (List<FwIntermediaryLinkmanInfoVo>) linkmanInfoService.queryListByIntermId(fId).getData();
            orgPageVo.setLinkmanInfoVoList(linkmanInfoVoList);
            List<FwIntermediaryMarketEvalVo> marketEvalVoList = (List<FwIntermediaryMarketEvalVo>) marketEvalService.queryListByIntermId(fId).getData();
            orgPageVo.setMarketEvalVoList(marketEvalVoList);
            List<FwIntermediaryQualifiInfoVo> qualifiInfoVoList = (List<FwIntermediaryQualifiInfoVo>) qualifiInfoService.queryListByIntermId(fId).getData();
            orgPageVo.setQualifiInfoVoList(qualifiInfoVoList);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(orgPageVo);
            result.setMsg("获取数据成功！");
        }
        return result;
    }

    /**
     * 多条件分页查询中介机构所有信息
     *
     * @param orgBasicQueryBo
     * @return
     */
    @Override
    public Result queryPageByConditions(FwIntermediaryOrgBasicQueryBo orgBasicQueryBo) {
        IPage<FwIntermediaryOrgBasic> page = new Page<>(orgBasicQueryBo.getPageIndex(), orgBasicQueryBo.getPageSize());
        QueryWrapper<FwIntermediaryOrgBasic> queryWrapperOrgBasic = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfOrgName())) {
            queryWrapperOrgBasic.like("f_Org_Name", orgBasicQueryBo.getfOrgName());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfSocialCreditCode())) {
            queryWrapperOrgBasic.like("f_Social_Credit_Code", orgBasicQueryBo.getfSocialCreditCode());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfOrgTypeName())) {
            queryWrapperOrgBasic.eq("f_Org_Type_Name", orgBasicQueryBo.getfOrgTypeName());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfOrgTypeCode())) {
            queryWrapperOrgBasic.eq("f_Org_Type_Code", orgBasicQueryBo.getfOrgTypeCode());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfResponsibleOfficer())) {
            queryWrapperOrgBasic.like("f_Responsible_Officer", orgBasicQueryBo.getfResponsibleOfficer());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfRegisterDomicile())) {
            queryWrapperOrgBasic.like("f_Register_Domicile", orgBasicQueryBo.getfRegisterDomicile());
        }
        if (StringUtils.isNotEmpty(orgBasicQueryBo.getfRealOfficeAddress())) {
            queryWrapperOrgBasic.like("f_Real_Office_Address", orgBasicQueryBo.getfRealOfficeAddress());
        }
        if (orgBasicQueryBo.getfRegisterStartTime() != null) {
            queryWrapperOrgBasic.gt("f_Register_Time", orgBasicQueryBo.getfRegisterStartTime());
        }
        if (orgBasicQueryBo.getfRegisterEndTime() != null) {
            queryWrapperOrgBasic.le("f_Register_Time", orgBasicQueryBo.getfRegisterEndTime());
        }
        if (orgBasicQueryBo.getfLawyersTotalMin() != null) {
            queryWrapperOrgBasic.gt("f_Lawyers_Total", orgBasicQueryBo.getfLawyersTotalMin());
        }
        if (orgBasicQueryBo.getfLawyersTotalMax() != null) {
            queryWrapperOrgBasic.le("f_Lawyers_Total", orgBasicQueryBo.getfLawyersTotalMax());
        }
        if (orgBasicQueryBo.getfPartnerCareermanTotalMin() != null) {
            queryWrapperOrgBasic.gt("f_Partner_Careerman_Total", orgBasicQueryBo.getfPartnerCareermanTotalMin());
        }
        if (orgBasicQueryBo.getfPartnerCareermanTotalMax() != null) {
            queryWrapperOrgBasic.le("f_Partner_Careerman_Total", orgBasicQueryBo.getfPartnerCareermanTotalMax());
        }
        if (orgBasicQueryBo.getfIsProxyQuali() != null) {
            queryWrapperOrgBasic.eq("f_Is_Proxy_Quali", orgBasicQueryBo.getfIsProxyQuali());
        }
        if (orgBasicQueryBo.getfIsPatentAgency() != null) {
            queryWrapperOrgBasic.eq("f_Is_Patent_Agency", orgBasicQueryBo.getfIsPatentAgency());
        }
        queryWrapperOrgBasic.eq(orgBasicQueryBo.getfWorkFlowId() != null, "f_Work_Flow_Id", orgBasicQueryBo.getfWorkFlowId())
        	.eq(orgBasicQueryBo.getfState() != null, "f_State", orgBasicQueryBo.getfState())
        	.eq("f_User_ID", UserUtils.getUserInfo().getfId())
        	.orderByDesc("f_CreateTime");
        IPage<FwIntermediaryOrgBasic> result = orgBasicMapper.selectPage(page, queryWrapperOrgBasic);
        IPage<FwIntermediaryOrgPageVo> resultVo = new Page<>();
        resultVo.setCurrent(result.getCurrent());
        resultVo.setPages(result.getPages());
        resultVo.setSize(result.getSize());
        resultVo.setTotal(result.getTotal());
        List<FwIntermediaryOrgBasic> orgBasicList = result.getRecords();
        List<FwIntermediaryOrgPageVo> pageVoList = new ArrayList<>(orgBasicList.size());
        if (CollectionUtils.isNotEmpty(orgBasicList)) {
            orgBasicList.forEach(orgBasic -> {
                FwIntermediaryOrgPageVo orgPageVo = new FwIntermediaryOrgPageVo();
                BeanUtils.copyProperties(orgBasic, orgPageVo);
                List<FwIntermediaryBankInfoVo> bankInfoVoList = (List<FwIntermediaryBankInfoVo>) bankInfoService.queryListByIntermId(orgBasic.getfId()).getData();
                orgPageVo.setBankInfoVoList(bankInfoVoList);
                List<FwIntermediaryLinkmanInfoVo> linkmanInfoVoList = (List<FwIntermediaryLinkmanInfoVo>) linkmanInfoService.queryListByIntermId(orgBasic.getfId()).getData();
                orgPageVo.setLinkmanInfoVoList(linkmanInfoVoList);
                List<FwIntermediaryMarketEvalVo> marketEvalVoList = (List<FwIntermediaryMarketEvalVo>) marketEvalService.queryListByIntermId(orgBasic.getfId()).getData();
                orgPageVo.setMarketEvalVoList(marketEvalVoList);
                QueryWrapper<FwIntermediaryQualifiInfo> qualifiInfoQueryWrapper = new QueryWrapper<>();
                qualifiInfoQueryWrapper.eq("fk_Intermediary_Id", orgBasic.getfId());
                if (StringUtils.isNotEmpty(orgBasicQueryBo.getfProfessionCode())) {
                    qualifiInfoQueryWrapper.eq("f_Profession_Code", orgBasicQueryBo.getfProfessionCode());
                }
                if (StringUtils.isNotEmpty(orgBasicQueryBo.getfProfessionName())) {
                    qualifiInfoQueryWrapper.eq("f_Profession_Name", orgBasicQueryBo.getfProfessionName());
                }
                List<FwIntermediaryQualifiInfo> qualifiInfoList = qualifiInfoService.list(qualifiInfoQueryWrapper);
                List<FwIntermediaryQualifiInfoVo> qualifiInfoVoList = new ArrayList<>(qualifiInfoList.size());
                if (CollectionUtils.isNotEmpty(qualifiInfoList)) {
                    qualifiInfoList.forEach(qualifiInfo -> {
                        FwIntermediaryQualifiInfoVo qualifiInfoVo = new FwIntermediaryQualifiInfoVo();
                        BeanUtils.copyProperties(qualifiInfo, qualifiInfoVo);
                        qualifiInfoVoList.add(qualifiInfoVo);
                    });
                    orgPageVo.setQualifiInfoVoList(qualifiInfoVoList);
                }

                pageVoList.add(orgPageVo);
            });
            resultVo.setRecords(pageVoList);
        } else {
            resultVo.setRecords(null);
        }
        return Result.data(resultVo);
    }

    /**
     * 单个保存中介机构基本信息
     *
     * @param orgBasicBo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Result save(FwIntermediaryOrgBasicBo orgBasicBo) {
        if (orgBasicBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
		if (conflictService.isBanned(orgBasicBo.getfSocialCreditCode())) {
			return Result.fail(400, "该中介机构因利益冲突被禁用");
		}

        FwIntermediaryOrgBasic orgBasic = new FwIntermediaryOrgBasic();
        BeanUtils.copyProperties(orgBasicBo, orgBasic);
        orgBasic.setfIsdel(0);
        orgBasic.setfCode(generateCode(orgBasic));
        orgBasic.setfUserId(UserUtils.getUserInfo().getfId());
        orgBasicMapper.insert(orgBasic);

        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 单个保存中介机构基本信息并返回主键
     *
     * @param orgBasicBo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Result saveReturnId(FwIntermediaryOrgBasicBo orgBasicBo) {
        Result result = new Result();
        if (orgBasicBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
		if (conflictService.isBanned(orgBasicBo.getfSocialCreditCode())) {
			return Result.fail(400, "该中介机构因利益冲突被禁用");
		}

        FwIntermediaryOrgBasic orgBasic = new FwIntermediaryOrgBasic();
        BeanUtils.copyProperties(orgBasicBo, orgBasic);
        orgBasic.setfIsdel(0);
        orgBasic.setfCode(generateCode(orgBasic));
        orgBasic.setfUserId(UserUtils.getUserInfo().getfId());

        orgBasicMapper.insert(orgBasic);

        result.setSuccess(true);
        result.setMsg("保存数据成功！");
        result.setCode(200);
        result.setData(orgBasic.getfId());
        return result;
    }

    /**
     * 根据主键更新中介机构基本信息
     *
     * @param orgBasicBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryOrgBasicBo orgBasicBo) {
        if (orgBasicBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = orgBasicBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的中介机构不存在", 500);
        }
		if (conflictService.isBanned(orgBasicBo.getfSocialCreditCode())) {
			return Result.fail(400, "该中介机构因利益冲突被禁用");
		}

        FwIntermediaryOrgBasic orgBasic = new FwIntermediaryOrgBasic();
        BeanUtils.copyProperties(orgBasicBo, orgBasic);
        orgBasic.setfUserId(UserUtils.getUserInfo().getfId());

        orgBasicMapper.updateById(orgBasic);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除中介机构所有信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        this.deleteAllById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    @Transactional
    public void deleteAllById(Long fId) {
        orgBasicMapper.deleteById(fId);
        /**
         * 关联银行信息批量删除
         */
        Result bankResult = bankInfoService.queryListByIntermId(fId);
        List<FwIntermediaryBankInfoVo> listBankInfoVo = (List<FwIntermediaryBankInfoVo>) bankResult.getData();
        if (CollectionUtils.isNotEmpty(listBankInfoVo)) {
            List<Long> listBankIds = new ArrayList<>(listBankInfoVo.size());
            listBankInfoVo.forEach(bankInfoVo -> {
                listBankIds.add(bankInfoVo.getfId());
            });
            bankInfoService.removeByIds(listBankIds);
        }
        /**
         * 关联联系人信息批量删除
         */
        Result linkmanResult = linkmanInfoService.queryListByIntermId(fId);
        List<FwIntermediaryLinkmanInfoVo> linkmanInfoVos = (List<FwIntermediaryLinkmanInfoVo>) linkmanResult.getData();
        if (CollectionUtils.isNotEmpty(linkmanInfoVos)) {
            List<Long> listLinkmanIds = new ArrayList<>(linkmanInfoVos.size());
            linkmanInfoVos.forEach(linkmanInfoVo -> {
                listLinkmanIds.add(linkmanInfoVo.getfId());
            });
            linkmanInfoService.removeByIds(listLinkmanIds);
        }
        /**
         * 关联市场评价批量删除
         */
        Result marketEvalResult = marketEvalService.queryListByIntermId(fId);
        List<FwIntermediaryMarketEvalVo> marketEvalVos = (List<FwIntermediaryMarketEvalVo>) marketEvalResult.getData();
        if (CollectionUtils.isNotEmpty(marketEvalVos)) {
            List<Long> listMarketEvalIds = new ArrayList<>(marketEvalVos.size());
            marketEvalVos.forEach(marketEvalVo -> {
                listMarketEvalIds.add(marketEvalVo.getfId());
            });
            marketEvalService.removeByIds(listMarketEvalIds);
        }
        /**
         * 关联资质信息批量删除
         */
        Result qualifiResult = qualifiInfoService.queryListByIntermId(fId);
        List<FwIntermediaryQualifiInfoVo> qualifiInfoVos = (List<FwIntermediaryQualifiInfoVo>) qualifiResult.getData();
        if (CollectionUtils.isNotEmpty(qualifiInfoVos)) {
            List<Long> listQualifiIds = new ArrayList<>(qualifiInfoVos.size());
            qualifiInfoVos.forEach(qualifiInfoVo -> {
                listQualifiIds.add(qualifiInfoVo.getfId());
            });
            qualifiInfoService.removeByIds(listQualifiIds);
        }
    }

    @Override
    public Result<List<FwIntermediaryOrgBasicVo>> queryByHireId(Long hireId) {
        List<FwIntermediaryOrgBasic> list = lambdaQuery()
            .inSql(FwIntermediaryOrgBasic::getfId, String.format("select fk_Intermediary_ID from fw_intermediary_info_hire_r where fk_Hire_ID = %d", hireId))
            .list();
        if (list != null) {
        	List<FwIntermediaryOrgBasicVo> collect = list.stream().map(entity -> {
                FwIntermediaryOrgBasicVo orgBasicVo = new FwIntermediaryOrgBasicVo();
                BeanUtils.copyProperties(entity,orgBasicVo);
                List<FwIntermediaryInfoHireR> list2 = hireRService.lambdaQuery().eq(FwIntermediaryInfoHireR::getFkHireId, hireId).eq(FwIntermediaryInfoHireR::getFkIntermediaryId, entity.getfId()).list();
                if (list2 != null && list2.size() >= 1) {
                	FwIntermediaryInfoHireR r = list2.get(0);
                	orgBasicVo.setFkAttachmentExt(r.getFkAttachmentExt());
                	orgBasicVo.setFkAttachmentPath(r.getFkAttachmentPath());
                	orgBasicVo.setFkAttachmentName(r.getFkAttachmentName());
                	orgBasicVo.setFkAttachmentId(r.getFkAttachmentId());
                }
                return orgBasicVo;
        	}).collect(Collectors.toList());
        	return Result.data(collect);
        }
        return Result.data(null);
    }
    
    @Override
    public Result<List<FwIntermediaryOrgBasicVo>> queryElectionsByHireId(Long hireId) {
    	List<FwIntermediaryOrgBasic> list = lambdaQuery()
    			.inSql(FwIntermediaryOrgBasic::getfId, String.format("select fk_Intermediary_ID from fw_intermediary_info_hire_election_r where fk_Hire_ID = %d", hireId))
    			.list();
    	if (list != null) {
    		List<FwIntermediaryOrgBasicVo> collect = list.stream().map(entity -> {
    			FwIntermediaryOrgBasicVo orgBasicVo = new FwIntermediaryOrgBasicVo();
    			BeanUtils.copyProperties(entity,orgBasicVo);
    			List<FwIntermediaryInfoHireElectionR> list2 = electionRService.lambdaQuery().eq(FwIntermediaryInfoHireElectionR::getFkHireId, hireId).eq(FwIntermediaryInfoHireElectionR::getFkIntermediaryId, entity.getfId()).list();
    			if (list2 != null && list2.size() >= 1) {
    				FwIntermediaryInfoHireElectionR r = list2.get(0);
    				orgBasicVo.setFkAttachmentExt(r.getFkAttachmentExt());
    				orgBasicVo.setFkAttachmentPath(r.getFkAttachmentPath());
    				orgBasicVo.setFkAttachmentName(r.getFkAttachmentName());
    				orgBasicVo.setFkAttachmentId(r.getFkAttachmentId());
    			}
    			return orgBasicVo;
    		}).collect(Collectors.toList());
    		return Result.data(collect);
    	}
    	return Result.data(null);
    }
    
    private String generateCode(FwIntermediaryOrgBasic entity) {
    	String prefix = "ZR";
    	int yr = LocalDate.now().getYear();
    	String year = String.valueOf(yr).substring(2, 4);
    	StringBuilder sb = new StringBuilder().append(prefix).append(year);
    	FwIntermediaryOrgBasic last = lambdaQuery().likeRight(FwIntermediaryOrgBasic::getfCode, sb.toString()).orderByDesc(FwIntermediaryOrgBasic::getfCode).last("LIMIT 1").one();
    	int number = 1;
    	if (last != null) {
    		String code = last.getfCode();
    		number = Integer.valueOf(code.substring(code.length() - 5, code.length()));
    		number = number + 1;
    	}
    	sb.append(String.format("%05d", number));
    	return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void enable(IntermediaryOperateBo bo) {
        FwIntermediaryOrgBasic entity = new FwIntermediaryOrgBasic();
        entity.setfId(bo.getId());
        entity.setfState(1);
        updateById(entity);

        FwIntermediaryOrgLog olog = new FwIntermediaryOrgLog();
        olog.setFkIntermediaryId(bo.getId());
        olog.setFkOperatorId(bo.getOperatorId());
        olog.setFkOperatorName(bo.getOperatorName());
        olog.setfOperate(IntermediaryOperate.ENABLE);
        ologService.save(olog);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void disable(IntermediaryOperateBo bo) {
        FwIntermediaryOrgBasic entity = new FwIntermediaryOrgBasic();
        entity.setfId(bo.getId());
        entity.setfState(0);
        updateById(entity);

        FwIntermediaryOrgLog olog = new FwIntermediaryOrgLog();
        olog.setFkIntermediaryId(bo.getId());
        olog.setFkOperatorId(bo.getOperatorId());
        olog.setFkOperatorName(bo.getOperatorName());
        olog.setfOperate(IntermediaryOperate.DISABLE);
        ologService.save(olog);
    }
    
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void cancelAdmit(IntermediaryOperateBo bo) {
        FwIntermediaryOrgBasic entity = new FwIntermediaryOrgBasic();
        entity.setfId(bo.getId());
        entity.setfIsValid((byte) 0);
        entity.setfCause(bo.getCause());
        updateById(entity);

        FwIntermediaryOrgLog olog = new FwIntermediaryOrgLog();
        olog.setFkIntermediaryId(bo.getId());
        olog.setFkOperatorId(bo.getOperatorId());
        olog.setFkOperatorName(bo.getOperatorName());
        olog.setfOperate(IntermediaryOperate.CANCEL);
        
        FwIntermediaryOrgBasic intermediary = getById(bo.getId());
        String uscCode = intermediary.getfSocialCreditCode();
        
        List<FwIntermediaryInterestConflict> list = conflictService.lambdaQuery()
        	.eq(FwIntermediaryInterestConflict::getfUscCode, uscCode)
        	.list();
        if (list != null) {
        	list.parallelStream().forEach(c -> {
        		ConflictLogBo clog = new ConflictLogBo();
        		clog.setFkConflictId(c.getfId());
        		clog.setFkUscCode(uscCode);
        		// 操作: 解除准入
        		clog.setFkOperate(2);
        		clog.setFkOperatorId(bo.getOperatorId());
        		clog.setFkOperatorName(bo.getOperatorName());
        		clogService.save(clog);
        	});
        }
        
        ologService.save(olog);
    }
    
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void cancelAdmitByUscCode(IntermediaryOperateBo bo) {
    	String uscCode = bo.getUscCode();
    	List<FwIntermediaryOrgBasic> list = lambdaQuery().eq(FwIntermediaryOrgBasic::getfSocialCreditCode, bo.getUscCode()).list();
    	if (list != null) {
    		list.stream().forEach(each -> {
    	    	FwIntermediaryOrgBasic entity = new FwIntermediaryOrgBasic();
    	    	entity.setfId(each.getfId());
    	    	entity.setfIsValid((byte) 0);
    	    	entity.setfCause("利益冲突");
    	    	updateById(entity);
    	    	
    	    	FwIntermediaryOrgLog olog = new FwIntermediaryOrgLog();
    	    	olog.setFkIntermediaryId(each.getfId());
    	    	olog.setFkOperatorId(bo.getOperatorId());
    	    	olog.setFkOperatorName(bo.getOperatorName());
    	    	olog.setfOperate(IntermediaryOperate.CANCEL);
    	    	ologService.save(olog);

				List<FwIntermediaryInterestConflict> list1 = conflictService.lambdaQuery()
    	            	.eq(FwIntermediaryInterestConflict::getfUscCode, uscCode )
    	            	.list();
    	            if (list1 != null) {
    	            	list1.parallelStream().forEach(c -> {
    	            		ConflictLogBo clog = new ConflictLogBo();
    	            		clog.setFkConflictId(c.getfId());
    	            		clog.setFkUscCode(uscCode);
    	            		// 操作: 解除准入
    	            		clog.setFkOperate(2);
    	            		clog.setFkOperatorId(bo.getOperatorId());
    	            		clog.setFkOperatorName(bo.getOperatorName());
    	            		clogService.save(clog);
    	            	});
    	            }
    		});
    	}
    }

	@Override
	public IPage<FwIntermediaryOrgBasicVo> pageIntermediary(IntermediaryQueryBo bo) {
		Page<FwIntermediaryOrgBasic> page = new Page<>(bo.getCurrent(), bo.getSize());
		IPage<FwIntermediaryOrgBasic> result = lambdaQuery()
			// 名称
			.like(StringUtils.isNotEmpty(bo.getName()), FwIntermediaryOrgBasic::getfOrgName, bo.getName())
			// 统一社会信用代码
			.like(StringUtils.isNotEmpty(bo.getUscCode()), FwIntermediaryOrgBasic::getfSocialCreditCode, bo.getUscCode())
			// 类型
			.eq(StringUtils.isNotEmpty(bo.getType()), FwIntermediaryOrgBasic::getfOrgTypeCode, bo.getType())
			// 成立时间
			.ge(bo.getEstablishDateBegin() != null, FwIntermediaryOrgBasic::getfRegisterTime, bo.getEstablishDateBegin())
			.le(bo.getEstablishDateEnd() != null, FwIntermediaryOrgBasic::getfRegisterTime, bo.getEstablishDateEnd())
			// 负责人
			.like(StringUtils.checkValNotNull(bo.getOfficer()), FwIntermediaryOrgBasic::getfResponsibleOfficer, bo.getOfficer())
			// 注册地址
			.like(StringUtils.checkValNotNull(bo.getRegisterAddress()), FwIntermediaryOrgBasic::getfRegisterDomicile, bo.getRegisterAddress())
			// 办公地址
			.like(StringUtils.checkValNotNull(bo.getActualAddress()), FwIntermediaryOrgBasic::getfRealOfficeAddress, bo.getActualAddress())
			// 律师人员数量
			.ge(bo.getLawyerNumMin() != null, FwIntermediaryOrgBasic::getfLawyersTotal, bo.getLawyerNumMin())
			.le(bo.getLawyerNumMax() != null, FwIntermediaryOrgBasic::getfLawyersTotal, bo.getLawyerNumMax())
			// 合伙人数量
			.ge(bo.getPartnerNumMin() != null, FwIntermediaryOrgBasic::getfPartnerCareermanTotal, bo.getPartnerNumMin())
			.le(bo.getPartnerNumMax() != null, FwIntermediaryOrgBasic::getfPartnerCareermanTotal, bo.getPartnerNumMax())
			// 专业领域
			.inSql(StringUtils.checkValNotNull(bo.getProfessionCode()), FwIntermediaryOrgBasic::getfId, String.format("select fk_Intermediary_Id from fw_intermediary_qualifi_info where f_isDel = 0 and f_Profession_Code = '%s'", bo.getProfessionCode()))
			// 常年法律顾问
			.notInSql(Integer.valueOf(0) == bo.getIsAdvisor(), FwIntermediaryOrgBasic::getfId, "select fk_Intermediary_ID from fw_intermediary_info_hire_r where f_IsDel = 0 and fk_Hire_ID in (select f_ID from fw_intermediary_hire_info where f_IsDel = 0 and f_Hire_Type = 0)")
			.inSql(Integer.valueOf(1) == bo.getIsAdvisor(), FwIntermediaryOrgBasic::getfId, "select fk_Intermediary_ID from fw_intermediary_info_hire_r where f_IsDel = 0 and fk_Hire_ID in (select f_ID from fw_intermediary_hire_info where f_IsDel = 0 and f_Hire_Type = 0)")
			// 专利代理机构
			.eq(bo.getPatentAgency() != null, FwIntermediaryOrgBasic::getfIsPatentAgency, bo.getPatentAgency())
			// 商标代理机构
			.eq(bo.getTmAgency() != null, FwIntermediaryOrgBasic::getfIsProxyQuali, bo.getTmAgency())
			// 准入企业
			.eq(bo.getHandleOrgId() != null, FwIntermediaryOrgBasic::getFkHandleOrgId, bo.getHandleOrgId())
			// 首次准入时间
			.ge(bo.getAdmitDateBegin() != null, FwIntermediaryOrgBasic::getfAdmitTime, bo.getAdmitDateBegin())
			.le(bo.getAdmitDateEnd() != null, FwIntermediaryOrgBasic::getfAdmitTime, bo.getAdmitDateEnd())
			// 工作流ID
			.eq(bo.getWorkFlowId() != null, FwIntermediaryOrgBasic::getfWorkFlowId, bo.getWorkFlowId())
			// 是否启用
			.eq(bo.getState() != null, FwIntermediaryOrgBasic::getfState, bo.getState())
			// 是否准入
			.eq(bo.getAdmit() != null, FwIntermediaryOrgBasic::getfIsValid, bo.getAdmit())
			.page(page);
		IPage<FwIntermediaryOrgBasicVo> convert = result.convert(entity -> {
			FwIntermediaryOrgBasicVo vo = new FwIntermediaryOrgBasicVo();
			Integer hireTimes = hireService.lambdaQuery()
				.in(FwIntermediaryHireInfo::getfId, String.format("select fk_Hire_ID from fw_intermediary_info_hire_r where fk_Intermediary_ID = %d and f_IsDel = 0", entity.getfId()))
				.eq(FwIntermediaryHireInfo::getfWorkFlowId, 1)
				.count();
			vo.setfHiredTimes(hireTimes);
			BeanUtils.copyProperties(entity, vo);
			return vo;
		});
		return convert;
	}
    
}
