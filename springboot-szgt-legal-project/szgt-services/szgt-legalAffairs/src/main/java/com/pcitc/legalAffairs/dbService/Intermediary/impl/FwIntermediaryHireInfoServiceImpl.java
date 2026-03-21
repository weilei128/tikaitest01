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
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.HireInfoQuery;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireInfoService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireLegalTeamService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryHireInfoMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireLegalTeam;
import com.pcitc.legalAffairs.vo.Intermediary.*;
import com.pctic.common.utils.UserUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FwIntermediaryHireInfoServiceImpl extends ServiceImpl<FwIntermediaryHireInfoMapper, FwIntermediaryHireInfo>
        implements IFwIntermediaryHireInfoService {
    /**
     * 中介机构基本信息业务层
     */
    @Autowired
    private IFwIntermediaryOrgBasicService orgBasicService;
    @Autowired
    private IFwIntermediaryHireLegalTeamService hireLegalTeamService;
    @Autowired
    private FwIntermediaryHireInfoMapper hireInfoMapper;

    /**
     * 单个保存中介机构聘用信息
     *
     * @param hireInfoBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryHireInfoBo hireInfoBo) {
        Result<Long> result = new Result<Long>();
        if (hireInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwIntermediaryHireInfo hireInfo = new FwIntermediaryHireInfo();
        BeanUtils.copyProperties(hireInfoBo, hireInfo);
        hireInfo.setfIsdel(0);
        hireInfo.setfState(1);
        hireInfo.setfUserId(UserUtils.getUserInfo().getfId());
        
        hireInfo.setfCreatetime(new Date());
        hireInfoMapper.insert(hireInfo);
        result.setSuccess(true);
        result.setMsg("保存数据成功！");
        result.setCode(200);
        result.setData(hireInfo.getfId());
        return result;
    }

    /**
     * 单个修改中介机构聘用信息
     *
     * @param hireInfoBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryHireInfoBo hireInfoBo) {
        if (hireInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = hireInfoBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的中介机构聘用信息不存在", 500);
        }
        FwIntermediaryHireInfo hireInfo = new FwIntermediaryHireInfo();
        BeanUtils.copyProperties(hireInfoBo, hireInfo);
        hireInfo.setfUserId(UserUtils.getUserInfo().getfId());

        hireInfoMapper.updateById(hireInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键查询中介机构聘用信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryHireInfo hireInfo = hireInfoMapper.selectById(fId);
        FwIntermediaryHireInfoVo hireInfoVo = new FwIntermediaryHireInfoVo();
        if (hireInfo != null) {
            BeanUtils.copyProperties(hireInfo, hireInfoVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(hireInfoVo);
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
     * 根据主键查询中介机构聘用信息(包含法律团队信息和中介机构信息）
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryAllById(Long fId) {
        Result result = new Result();
        FwIntermediaryHireInfo hireInfo = hireInfoMapper.selectById(fId);
        FwIntermediaryHireInfoAllVo hireInfoAllVo = getHireInfoAllVo(hireInfo);
        if (hireInfoAllVo != null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(hireInfoAllVo);
            result.setMsg("获取数据成功！");
        } else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    private FwIntermediaryHireInfoAllVo getHireInfoAllVo(FwIntermediaryHireInfo hireInfo) {
        FwIntermediaryHireInfoAllVo hireInfoAllVo = new FwIntermediaryHireInfoAllVo();
        if (hireInfo != null) {
            BeanUtils.copyProperties(hireInfo, hireInfoAllVo);

            Result resultorgBasic = orgBasicService.queryListHireInfoId(hireInfo.getfId());
            List<FwIntermediaryOrgBasicVo> orgBasicVoList = (List<FwIntermediaryOrgBasicVo>) resultorgBasic.getData();
            if (CollectionUtils.isNotEmpty(orgBasicVoList)) {
                List<FwIntermediaryOrgBasicHasLegalTeamVo> orgBasicHasLegalTeamVoList = new ArrayList<>(orgBasicVoList.size());
                orgBasicVoList.forEach(orgBasicVo -> {
                    FwIntermediaryOrgBasicHasLegalTeamVo orgBasicHasLegalTeamVo = new FwIntermediaryOrgBasicHasLegalTeamVo();
                    BeanUtils.copyProperties(orgBasicVo,orgBasicHasLegalTeamVo);
                    Result resultLegalTeam = hireLegalTeamService.queryListByOrgBasicIdAndHireId(orgBasicVo.getfId(), hireInfo.getfId(), 0);
                    if (resultLegalTeam.getData() != null) {
                        List<FwIntermediaryHireLegalTeamVo> teamVoList = (List<FwIntermediaryHireLegalTeamVo>) resultLegalTeam.getData();
                        orgBasicHasLegalTeamVo.setLegalTeamVoList(teamVoList);
                    }
                    orgBasicHasLegalTeamVoList.add(orgBasicHasLegalTeamVo);
                });
                hireInfoAllVo.setOrgBasicHasLegalTeamVoList(orgBasicHasLegalTeamVoList);
            }
            
            Result resultorgBasic1 = orgBasicService.queryListElectionInfoId(hireInfo.getfId());
            List<FwIntermediaryOrgBasicVo> orgBasicVoList1 = (List<FwIntermediaryOrgBasicVo>) resultorgBasic1.getData();
            if (CollectionUtils.isNotEmpty(orgBasicVoList1)) {
            	List<FwIntermediaryOrgBasicHasLegalTeamVo> orgBasicHasLegalTeamVoList = new ArrayList<>(orgBasicVoList1.size());
            	orgBasicVoList1.forEach(orgBasicVo -> {
            		FwIntermediaryOrgBasicHasLegalTeamVo orgBasicHasLegalTeamVo = new FwIntermediaryOrgBasicHasLegalTeamVo();
            		BeanUtils.copyProperties(orgBasicVo,orgBasicHasLegalTeamVo);
            		Result resultLegalTeam = hireLegalTeamService.queryListByOrgBasicIdAndHireId(orgBasicVo.getfId(), hireInfo.getfId(), 1);
            		if (resultLegalTeam.getData() != null) {
            			List<FwIntermediaryHireLegalTeamVo> teamVoList = (List<FwIntermediaryHireLegalTeamVo>) resultLegalTeam.getData();
            			orgBasicHasLegalTeamVo.setLegalTeamVoList(teamVoList);
            		}
            		orgBasicHasLegalTeamVoList.add(orgBasicHasLegalTeamVo);
            	});
            	hireInfoAllVo.setElections(orgBasicHasLegalTeamVoList);
            }
        }
        return hireInfoAllVo;
    }

    /**
     * 多条件分页查询中介机构聘用信息
     *
     * @param hireInfoQueryBo
     * @return
     */
    @Override
    public Result queryPageByConditins(FwIntermediaryHireInfoQueryBo hireInfoQueryBo) {
        IPage<FwIntermediaryHireInfo> page = new Page<>(hireInfoQueryBo.getPageIndex(), hireInfoQueryBo.getPageSize());
        QueryWrapper<FwIntermediaryHireInfo> queryWrapper = new QueryWrapper<>();
        /**
         * 聘用类型:0,常年法律顾问聘用;1,专项业务聘用
         */
        if (hireInfoQueryBo.getfHireType() != null) {
            queryWrapper.eq("f_Hire_Type",hireInfoQueryBo.getfHireType());
        }
        /**
         * 聘用方式名称
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfHireWayName())) {
            queryWrapper.eq("f_Hire_Way_Name",hireInfoQueryBo.getfHireWayName());
        }
        /**
         * 聘用方式编码
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfHireWayCode())) {
            queryWrapper.eq("f_Hire_Way_Code",hireInfoQueryBo.getfHireWayCode());
        }
        /**
         * 预计服务费用(人民币)
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfPreServiceCost())) {
            queryWrapper.eq("f_Pre_Service_Cost",hireInfoQueryBo.getfPreServiceCost());
        }
        /**
         * 服务范围
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfServiceScope())) {
            queryWrapper.like("f_Service_Scope",hireInfoQueryBo.getfServiceScope());
        }

        /**
         * 专项业务名称
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfSpeBusinessName())) {
            queryWrapper.like("f_Spe_Business_Name",hireInfoQueryBo.getfSpeBusinessName());
        }
        /**
         * 专项业务类型
         */
        if (StringUtils.isNotEmpty(hireInfoQueryBo.getfSpeBusinessType())) {
            queryWrapper.eq("f_Spe_Business_Type",hireInfoQueryBo.getfSpeBusinessType());
        }
        // 审批状态
        queryWrapper.eq(hireInfoQueryBo.getfWorkFlowId() != null, "f_Work_Flow_Id", hireInfoQueryBo.getfWorkFlowId())
        // 数据权限
        	.eq("f_User_ID", UserUtils.getUserInfo().getfId())
        	.orderByDesc("f_CreateTime");
        
        IPage<FwIntermediaryHireInfo> hireInfoIPage = hireInfoMapper.selectPage(page,queryWrapper);
        IPage<FwIntermediaryHireInfoAllVo> hireInfoAllVoIPage = new Page<>();
        hireInfoAllVoIPage.setCurrent(hireInfoIPage.getCurrent());
        hireInfoAllVoIPage.setPages(hireInfoIPage.getPages());
        hireInfoAllVoIPage.setSize(hireInfoIPage.getSize());
        hireInfoAllVoIPage.setTotal(hireInfoIPage.getTotal());
        List<FwIntermediaryHireInfo> hireInfoList = hireInfoIPage.getRecords();
        List<FwIntermediaryHireInfoAllVo> hireInfoAllVoList = new ArrayList<>(hireInfoList.size());
        if (CollectionUtils.isNotEmpty(hireInfoList)){
            hireInfoList.forEach(hireInfo -> {
                FwIntermediaryHireInfoAllVo hireInfoAllVo = getHireInfoAllVo(hireInfo);
                hireInfoAllVoList.add(hireInfoAllVo);
            } );
            hireInfoAllVoIPage.setRecords(hireInfoAllVoList);
        }else {
            hireInfoAllVoIPage.setRecords(null);
        }
        return Result.data(hireInfoAllVoIPage);
    }

    /**
     * 根据主键删除中介机构聘用信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result delete(Long fId) {
        hireInfoMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除中介机构聘用信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteAllById(Long fId) {
        hireInfoMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

	@Override
	public IPage<IntermediaryHireVo> queryHireInfo(HireInfoQuery bo) {
        Page<IntermediaryHireVo> page = new Page<>(bo.getCurrent(), bo.getSize());
        return baseMapper.queryHireInfo(page, bo);

	}
}
