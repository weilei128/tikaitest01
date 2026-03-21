package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryLinkmanInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryLinkmanInfoService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryLinkmanInfoMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryLinkmanInfo;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryLinkmanInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FwIntermediaryLinkmanInfoServiceImpl extends ServiceImpl<FwIntermediaryLinkmanInfoMapper, FwIntermediaryLinkmanInfo>
implements IFwIntermediaryLinkmanInfoService {
    @Autowired
    private FwIntermediaryLinkmanInfoMapper linkmanInfoMapper;
    /**
     * 根据主键查询中介联系人信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        FwIntermediaryLinkmanInfo linkmanInfo = linkmanInfoMapper.selectById(fId);
        FwIntermediaryLinkmanInfoVo linkmanInfoVo = new FwIntermediaryLinkmanInfoVo();
        if (linkmanInfo != null) {
            BeanUtils.copyProperties(linkmanInfo,linkmanInfoVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(linkmanInfoVo);
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
     * 根据关联中介机构外键查询中介联系人信息
     *
     * @param fkIntermediaryId
     * @return
     */
    @Override
    public Result queryListByIntermId(Long fkIntermediaryId) {
        Result result = new Result();
        QueryWrapper<FwIntermediaryLinkmanInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Intermediary_Id",fkIntermediaryId);
        List<FwIntermediaryLinkmanInfo> list = linkmanInfoMapper.selectList(queryWrapper);
        List<FwIntermediaryLinkmanInfoVo> linkmanInfoVos = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(linkmanInfo -> {
                FwIntermediaryLinkmanInfoVo linkmanInfoVo = new FwIntermediaryLinkmanInfoVo();
                BeanUtils.copyProperties(linkmanInfo,linkmanInfoVo);
                linkmanInfoVos.add(linkmanInfoVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(linkmanInfoVos);
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
     * 单个保存中介联系人信息
     *
     * @param linkmanInfoBo
     * @return
     */
    @Override
    public Result save(FwIntermediaryLinkmanInfoBo linkmanInfoBo) {
        if (linkmanInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        FwIntermediaryLinkmanInfo linkmanInfo = new FwIntermediaryLinkmanInfo();
        BeanUtils.copyProperties(linkmanInfoBo,linkmanInfo);
        linkmanInfo.setfIsdel(0);
        linkmanInfoMapper.insert(linkmanInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键更新中介联系人信息
     *
     * @param linkmanInfoBo
     * @return
     */
    @Override
    public Result update(FwIntermediaryLinkmanInfoBo linkmanInfoBo) {
        if (linkmanInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Long fId = linkmanInfoBo.getfId();
        if (this.getById(fId) == null) {
            throw new BaseException("所要更新的中介联系人信息不存在",500);
        }
        FwIntermediaryLinkmanInfo linkmanInfo = new FwIntermediaryLinkmanInfo();
        BeanUtils.copyProperties(linkmanInfoBo,linkmanInfo);
        linkmanInfoMapper.updateById(linkmanInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键删除中介联系人信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result deleteById(Long fId) {
        linkmanInfoMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除中介联系人信息
     *
     * @param fIds
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> fIds) {
         linkmanInfoMapper.deleteBatchIds(fIds);
        return Result.success(ResultCode.SUCCESS);
    }
}
