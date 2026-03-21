package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireLegalTeamBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireLegalTeamService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryHireLegalTeamMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireLegalTeam;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryHireLegalTeamVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FwIntermediaryHireLegalTeamServiceImpl extends ServiceImpl<FwIntermediaryHireLegalTeamMapper, FwIntermediaryHireLegalTeam>
implements IFwIntermediaryHireLegalTeamService {
    @Autowired
    private FwIntermediaryHireLegalTeamMapper hireLegalTeamMapper;
    /**
     * 单个保存聘用法律团队信息
     *
     * @param hireLegalTeamBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryHireLegalTeamBo hireLegalTeamBo) {
        Result result = new Result();
        if (hireLegalTeamBo == null){
            throw new BaseException("传入参数为空", 500);
        }

        FwIntermediaryHireLegalTeam hireLegalTeam = new FwIntermediaryHireLegalTeam();
        BeanUtils.copyProperties(hireLegalTeamBo,hireLegalTeam);
        hireLegalTeam.setfIsdel(0);
        hireLegalTeam.setfSort(1);
        hireLegalTeamMapper.insert(hireLegalTeam);
        result.setSuccess(true);
        result.setMsg("保存数据成功！");
        result.setCode(200);
        result.setData(hireLegalTeam.getfId());
        return result;
    }

    /**
     * 根据主键更新聘用法律团队信息
     *
     * @param hireLegalTeamBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryHireLegalTeamBo hireLegalTeamBo) {
        if (hireLegalTeamBo == null){
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = hireLegalTeamBo.getfId();
        if (this.getById(fId) == null ){
            throw new BaseException("所要更新的聘用法律团队信息不存在", 500);
        }
        FwIntermediaryHireLegalTeam hireLegalTeam = new FwIntermediaryHireLegalTeam();
        BeanUtils.copyProperties(hireLegalTeamBo,hireLegalTeam);
        hireLegalTeamMapper.updateById(hireLegalTeam);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键查询聘用法律团队信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryHireLegalTeam hireLegalTeam = hireLegalTeamMapper.selectById(fId);
        if (hireLegalTeam == null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }else {
            FwIntermediaryHireLegalTeamVo hireLegalTeamVo = new FwIntermediaryHireLegalTeamVo();
            BeanUtils.copyProperties(hireLegalTeam,hireLegalTeamVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(hireLegalTeamVo);
            result.setMsg("获取数据成功！");
        }
        return result;
    }

    /***
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @Override
    public Result queryListByOrgBasicId(Long fkOrgBasicId, Integer type) {
        Result result = new Result();
        QueryWrapper<FwIntermediaryHireLegalTeam>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Org_Basic_Id",fkOrgBasicId);
        queryWrapper.eq(type != null, "f_Type", type);
        List<FwIntermediaryHireLegalTeam> list = hireLegalTeamMapper.selectList(queryWrapper);
        List<FwIntermediaryHireLegalTeamVo>  legalTeamVos = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(hireLegalTeam -> {
                FwIntermediaryHireLegalTeamVo hireLegalTeamVo = new FwIntermediaryHireLegalTeamVo();
                BeanUtils.copyProperties(hireLegalTeam,hireLegalTeamVo);
                legalTeamVos.add(hireLegalTeamVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(legalTeamVos);
            result.setMsg("获取数据成功！");
        }else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }
    
    /***
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @Override
    public Result queryListByHireId(Long hireId, Integer type) {
    	Result result = new Result();
    	QueryWrapper<FwIntermediaryHireLegalTeam>  queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fk_Hire_ID", hireId);
    	queryWrapper.eq(type != null, "f_Type", type);
    	List<FwIntermediaryHireLegalTeam> list = hireLegalTeamMapper.selectList(queryWrapper);
    	List<FwIntermediaryHireLegalTeamVo>  legalTeamVos = new ArrayList<>(list.size());
    	if (CollectionUtils.isNotEmpty(list)) {
    		list.forEach(hireLegalTeam -> {
    			FwIntermediaryHireLegalTeamVo hireLegalTeamVo = new FwIntermediaryHireLegalTeamVo();
    			BeanUtils.copyProperties(hireLegalTeam,hireLegalTeamVo);
    			legalTeamVos.add(hireLegalTeamVo);
    		});
    		result.setSuccess(true);
    		result.setCode(200);
    		result.setData(legalTeamVos);
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
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @Override
    public Result queryListByOrgBasicIdAndHireId(Long fkOrgBasicId, Long hireId, Integer type) {
    	Result result = new Result();
    	QueryWrapper<FwIntermediaryHireLegalTeam>  queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fk_Org_Basic_Id",fkOrgBasicId);
    	queryWrapper.eq("fk_Hire_ID", hireId);
    	queryWrapper.eq(type != null, "f_Type", type);
    	List<FwIntermediaryHireLegalTeam> list = hireLegalTeamMapper.selectList(queryWrapper);
    	List<FwIntermediaryHireLegalTeamVo>  legalTeamVos = new ArrayList<>(list.size());
    	if (CollectionUtils.isNotEmpty(list)) {
    		list.forEach(hireLegalTeam -> {
    			FwIntermediaryHireLegalTeamVo hireLegalTeamVo = new FwIntermediaryHireLegalTeamVo();
    			BeanUtils.copyProperties(hireLegalTeam,hireLegalTeamVo);
    			legalTeamVos.add(hireLegalTeamVo);
    		});
    		result.setSuccess(true);
    		result.setCode(200);
    		result.setData(legalTeamVos);
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
     * 根据主键删除聘用法律团队信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        hireLegalTeamMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除聘用法律团队信息
     *
     * @param fIds
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> fIds) {
        hireLegalTeamMapper.deleteBatchIds(fIds);
        return Result.success(ResultCode.SUCCESS);
    }
}
