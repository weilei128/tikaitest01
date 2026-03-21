package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryQualifiInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryQualifiInfoService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryQualifiInfoMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryQualifiInfo;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryQualifiInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FwIntermediaryQualifiInfoServiceImpl extends ServiceImpl<FwIntermediaryQualifiInfoMapper, FwIntermediaryQualifiInfo>
implements IFwIntermediaryQualifiInfoService {
    @Autowired
    private FwIntermediaryQualifiInfoMapper qualifiInfoMapper;
    /**
     * 根据资质信息主键查询资质信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryQualifiInfo qualifiInfo = qualifiInfoMapper.selectById(fId);
        FwIntermediaryQualifiInfoVo qualifiInfoVo = new FwIntermediaryQualifiInfoVo();
        if (qualifiInfo != null) {
            BeanUtils.copyProperties(qualifiInfo,qualifiInfoVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(qualifiInfoVo);
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
     * 根据关联中介机构外键查询资质信息列表
     *
     * @param fkIntermediaryId
     * @return
     */
    @Override
    public Result queryListByIntermId(Long fkIntermediaryId) {
        Result result = new Result();
        QueryWrapper<FwIntermediaryQualifiInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Intermediary_Id",fkIntermediaryId);
        List<FwIntermediaryQualifiInfo> list = qualifiInfoMapper.selectList(queryWrapper);
        List<FwIntermediaryQualifiInfoVo> listResult = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(qualifiInfo->{
                FwIntermediaryQualifiInfoVo qualifiInfoVo = new FwIntermediaryQualifiInfoVo();
                BeanUtils.copyProperties(qualifiInfo,qualifiInfoVo);
                listResult.add(qualifiInfoVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(listResult);
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
     * 单个保存中介机构资质信息
     *
     * @param qualifiInfoBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryQualifiInfoBo qualifiInfoBo) {
        if (qualifiInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwIntermediaryQualifiInfo qualifiInfo = new FwIntermediaryQualifiInfo();
        BeanUtils.copyProperties(qualifiInfoBo,qualifiInfo);
        qualifiInfo.setfIsdel(0);
        qualifiInfoMapper.insert(qualifiInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键更新中介机构资质信息
     *
     * @param qualifiInfoBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryQualifiInfoBo qualifiInfoBo) {
        if (qualifiInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = qualifiInfoBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的资质信息不存在",500);
        }
        FwIntermediaryQualifiInfo qualifiInfo = new FwIntermediaryQualifiInfo();
        BeanUtils.copyProperties(qualifiInfoBo,qualifiInfo);
        qualifiInfoMapper.updateById(qualifiInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除中介机构资质信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        qualifiInfoMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除中介机构资质信息
     *
     * @param fIds
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> fIds) {
        qualifiInfoMapper.deleteBatchIds(fIds);
        return Result.success(ResultCode.SUCCESS);
    }
}
