package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryBankInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryBankInfoService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryBankInfoMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryBankInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryQualifiInfo;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryBankInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FwIntermediaryBankInfoServiceImpl extends ServiceImpl<FwIntermediaryBankInfoMapper, FwIntermediaryBankInfo>
implements IFwIntermediaryBankInfoService {
    @Autowired
    private FwIntermediaryBankInfoMapper bankInfoMapper;
    /**
     * 根据主键查询银行信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryBankInfo bankInfo =  bankInfoMapper.selectById(fId);
        FwIntermediaryBankInfoVo bankInfoVo = new FwIntermediaryBankInfoVo();
        if (bankInfo != null) {
            BeanUtils.copyProperties(bankInfo,bankInfoVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(bankInfoVo);
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
     * 根据关联中介机构外键查询银行信息
     *
     * @param fkIntermediaryId
     * @return
     */
    @Override
    public Result queryListByIntermId(Long fkIntermediaryId) {
        Result result = new Result();
        QueryWrapper<FwIntermediaryBankInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Intermediary_Id",fkIntermediaryId);
        List<FwIntermediaryBankInfo> list = bankInfoMapper.selectList(queryWrapper);
        List<FwIntermediaryBankInfoVo> bankInfoList = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(bankInfo -> {
                FwIntermediaryBankInfoVo bankInfoVo = new FwIntermediaryBankInfoVo();
                BeanUtils.copyProperties(bankInfo,bankInfoVo);
                bankInfoList.add(bankInfoVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(bankInfoList);
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
     * 单个保存银行信息
     *
     * @param bankInfoBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryBankInfoBo bankInfoBo) {
        if (bankInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwIntermediaryBankInfo bankInfo = new FwIntermediaryBankInfo();
        BeanUtils.copyProperties(bankInfoBo,bankInfo);
        bankInfo.setfIsdel(0);
        bankInfoMapper.insert(bankInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键更新银行信息
     *
     * @param bankInfoBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryBankInfoBo bankInfoBo) {
        if (bankInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = bankInfoBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的资质信息不存在",500);
        }
        FwIntermediaryBankInfo bankInfo = new FwIntermediaryBankInfo();
        BeanUtils.copyProperties(bankInfoBo,bankInfo);
        bankInfoMapper.updateById(bankInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除银行信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        bankInfoMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除银行信息
     *
     * @param fIds
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> fIds) {
        bankInfoMapper.deleteBatchIds(fIds);
        return Result.success(ResultCode.SUCCESS);
    }
}
