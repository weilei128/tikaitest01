package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryMarketEvalBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryMarketEvalService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryMarketEvalMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryMarketEval;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryMarketEvalVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FwIntermediaryMarketEvalServiceImpl extends ServiceImpl<FwIntermediaryMarketEvalMapper, FwIntermediaryMarketEval>
implements IFwIntermediaryMarketEvalService {
    @Autowired
    private FwIntermediaryMarketEvalMapper marketEvalMapper;
    /**
     * 根据主键查询市场评价信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryMarketEval marketEval = marketEvalMapper.selectById(fId);
        FwIntermediaryMarketEvalVo marketEvalVo = new FwIntermediaryMarketEvalVo();
        if (marketEval != null) {
            BeanUtils.copyProperties(marketEval,marketEvalVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(marketEvalVo);
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
     * 根据关联中介机构外键查询市场评价信息列表
     *
     * @param fkIntermediaryId
     * @return
     */
    @Override
    public Result queryListByIntermId(Long fkIntermediaryId) {
        Result result = new Result();
        QueryWrapper<FwIntermediaryMarketEval> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Intermediary_Id",fkIntermediaryId);
        List<FwIntermediaryMarketEval> list = marketEvalMapper.selectList(queryWrapper);
        List<FwIntermediaryMarketEvalVo> listResult = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(marketEval -> {
                FwIntermediaryMarketEvalVo marketEvalVo = new FwIntermediaryMarketEvalVo();
                BeanUtils.copyProperties(marketEval,marketEvalVo);
                listResult.add(marketEvalVo);
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
     * 单个保存市场评价信息
     *
     * @param marketEvalBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryMarketEvalBo marketEvalBo) {
        if (marketEvalBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwIntermediaryMarketEval marketEval = new FwIntermediaryMarketEval();
        BeanUtils.copyProperties(marketEvalBo,marketEval);
        marketEval.setfIsdel(0);
        marketEvalMapper.insert(marketEval);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键更新市场评价信息
     *
     * @param marketEvalBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryMarketEvalBo marketEvalBo) {
        if (marketEvalBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = marketEvalBo.getfId();
        if (this.getById(fId) == null){
            throw new BaseException("所要更新的市场评价信息不存在",500);
        }
        FwIntermediaryMarketEval marketEval = new FwIntermediaryMarketEval();
        BeanUtils.copyProperties(marketEvalBo,marketEval);
        marketEvalMapper.updateById(marketEval);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除市场评价信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        marketEvalMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除市场评价信息
     *
     * @param fIds
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> fIds) {
        marketEvalMapper.deleteBatchIds(fIds);
        return Result.success(ResultCode.SUCCESS);
    }
}
