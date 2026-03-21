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
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireInfoService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireLegalTeamService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwLawGroupEvaluateService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwLawGroupEvaluateMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireLegalTeam;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.po.Intermediary.FwLawGroupEvaluate;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.Intermediary.FwLawGroupEvaluateVo;
import com.pcitc.legalAffairs.vo.Intermediary.FwOrgLawGroupEvaluatePageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FwLawGroupEvaluateServiceImpl extends ServiceImpl<FwLawGroupEvaluateMapper, FwLawGroupEvaluate>
implements IFwLawGroupEvaluateService {

	@Autowired
    private FwLawGroupEvaluateMapper lawGroupEvaluateMapper;
    @Autowired
    private IFwIntermediaryOrgBasicService iorgService;
    @Autowired
    private IFwIntermediaryHireLegalTeamService iteamService;
    @Autowired
    private IFwIntermediaryHireInfoService ihireService;
    @Autowired
    private UserOrgService userOrgService;
    /**
     * 根据主键查询法律团队考评信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwLawGroupEvaluate lawGroupEvaluate = lawGroupEvaluateMapper.selectById(fId);
        if (lawGroupEvaluate == null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }else {
            FwLawGroupEvaluateVo lawGroupEvaluateVo = new FwLawGroupEvaluateVo();
            BeanUtils.copyProperties(lawGroupEvaluate,lawGroupEvaluateVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(lawGroupEvaluateVo);
            result.setMsg("获取数据成功！");
        }
        return result;
    }

    /**
     * 根据关联法律团队主键查询考评信息列表
     *
     * @param fkLegalTeamId
     * @return
     */
    @Override
    public Result queryListByLegalTeamId(Long fkLegalTeamId) {
        Result result = new Result();
        QueryWrapper<FwLawGroupEvaluate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Legal_Team_Id",fkLegalTeamId);
        List<FwLawGroupEvaluate> listGroupEvaluate = lawGroupEvaluateMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(listGroupEvaluate)){
            List<FwLawGroupEvaluateVo> lawGroupEvaluateVoList = new ArrayList<>(listGroupEvaluate.size());
            listGroupEvaluate.forEach(lawGroupEvaluate -> {
                FwLawGroupEvaluateVo lawGroupEvaluateVo = new FwLawGroupEvaluateVo();
                BeanUtils.copyProperties(lawGroupEvaluate,lawGroupEvaluateVo);
                lawGroupEvaluateVoList.add(lawGroupEvaluateVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(lawGroupEvaluateVoList);
            result.setMsg("获取数据成功！");
        }else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    /**
     * 多条件分页查询律师考评信息
     *
     * @param groupEvaluateQueryBo
     * @return
     */
    @Override
    public Result queryPageByConditions(FwLawGroupEvaluateQueryBo groupEvaluateQueryBo) {
		Page<FwIntermediaryHireLegalTeam> data = new Page<>(groupEvaluateQueryBo.getPageIndex(), groupEvaluateQueryBo.getPageSize());
		List<FwIntermediaryHireInfo> hireInfo = ihireService.lambdaQuery().in(FwIntermediaryHireInfo::getfMainDepartId, userOrgService.getLoginUserOrg()).select(FwIntermediaryHireInfo::getfId).list();
		StringBuilder sb = new StringBuilder("select fk_Intermediary_ID from fw_intermediary_info_hire_r where f_IsDel = 0 and fk_Hire_ID in (-999");
		for (FwIntermediaryHireInfo info: hireInfo) {
			sb.append(" ,");
			sb.append(info.getfId());
		}
		sb.append(")");
		
		List<FwIntermediaryOrgBasic> list = iorgService.lambdaQuery()
			.like(StringUtils.isNotEmpty(groupEvaluateQueryBo.getfOrgName()), FwIntermediaryOrgBasic::getfOrgName, groupEvaluateQueryBo.getfOrgName())
			.eq(StringUtils.isNotEmpty(groupEvaluateQueryBo.getfOrgTypeName()), FwIntermediaryOrgBasic::getfOrgTypeName, groupEvaluateQueryBo.getfOrgTypeName())
			.eq(StringUtils.isNotEmpty(groupEvaluateQueryBo.getfOrgTypeCode()), FwIntermediaryOrgBasic::getfOrgTypeCode, groupEvaluateQueryBo.getfOrgTypeCode())
			.like(StringUtils.isNotEmpty(groupEvaluateQueryBo.getfResponsibleOfficer()), FwIntermediaryOrgBasic::getfResponsibleOfficer, groupEvaluateQueryBo.getfResponsibleOfficer())
			.like(StringUtils.isNotEmpty(groupEvaluateQueryBo.getfContactWay()), FwIntermediaryOrgBasic::getfContactWay, groupEvaluateQueryBo.getfContactWay())
			.inSql(FwIntermediaryOrgBasic::getfId, sb.toString())
			.inSql(FwIntermediaryOrgBasic::getfId, "select fk_Org_Basic_ID from fw_intermediary_hire_legal_team where f_IsDel = 0")
			.list();
		IPage<FwIntermediaryHireLegalTeam> page = iteamService.lambdaQuery()
			.in(list.size() > 0, FwIntermediaryHireLegalTeam::getFkOrgBasicId, list.stream().map(FwIntermediaryOrgBasic::getfId).collect(Collectors.toList()))
			.eq(FwIntermediaryHireLegalTeam::getfType, 0)
			.apply(list.size() <= 0, "0 = 1")
			.page(data);
		IPage<FwOrgLawGroupEvaluatePageVo> result = page.convert(entity -> {
			FwOrgLawGroupEvaluatePageVo vo = new FwOrgLawGroupEvaluatePageVo();
			BeanUtils.copyProperties(entity, vo);
			FwIntermediaryOrgBasic org = list.stream().filter(a -> entity.getFkOrgBasicId() == a.getfId()).findFirst().orElse(new FwIntermediaryOrgBasic());
			BeanUtils.copyProperties(org, vo);
			vo.setfId(null);
			vo.setFkLegalTeamId(entity.getfId());
			List<FwLawGroupEvaluate> evaList = lambdaQuery()
				.eq(FwLawGroupEvaluate::getFkLegalTeamId, entity.getfId())
				.list();
			if (evaList != null && evaList.size() > 0) {
				BeanUtils.copyProperties(evaList.get(evaList.size() - 1), vo);
			}
			return vo;
		});
		return Result.data(result);
    }

    /**
     * 单个保存律师考评信息
     *
     * @param lawGroupEvaluateBo
     * @return
     */
    @Override
    public Result save(FwLawGroupEvaluateBo lawGroupEvaluateBo) {
        Result result = new Result();
        if (lawGroupEvaluateBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwLawGroupEvaluate lawGroupEvaluate = new FwLawGroupEvaluate();
        BeanUtils.copyProperties(lawGroupEvaluateBo,lawGroupEvaluate);
        lawGroupEvaluate.setfIsdel(0);
        lawGroupEvaluateMapper.insert(lawGroupEvaluate);
        result.setData(lawGroupEvaluate.getfId());
        result.setCode(200);
        result.setMsg("添加成功！");
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据主键修改律师考评信息
     *
     * @param lawGroupEvaluateBo
     * @return
     */
    @Override
    public Result update(FwLawGroupEvaluateBo lawGroupEvaluateBo) {
        if (lawGroupEvaluateBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = lawGroupEvaluateBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的法律团队考评信息不存在", 500);
        }
        FwLawGroupEvaluate lawGroupEvaluate = new FwLawGroupEvaluate();
        BeanUtils.copyProperties(lawGroupEvaluateBo,lawGroupEvaluate);
        lawGroupEvaluateMapper.updateById(lawGroupEvaluate);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除律师考评信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result delete(Long fId) {
        lawGroupEvaluateMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }
}
