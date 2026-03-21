package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryConflictLogService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryConflictLogMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryConflictLog;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FwIntermediaryConflictLogServiceImpl extends ServiceImpl<FwIntermediaryConflictLogMapper, FwIntermediaryConflictLog> implements IFwIntermediaryConflictLogService {

    @Override
    public void save(ConflictLogBo bo) {
        FwIntermediaryConflictLog entity = new FwIntermediaryConflictLog();
        BeanUtils.copyProperties(bo, entity);
        entity.setfOperatetime(new Date());
        save(entity);
    }

    @Override
    public List<ConflictLogBo> getByConflictId(String conflictId) {
        List<FwIntermediaryConflictLog> list = lambdaQuery().eq(FwIntermediaryConflictLog::getFkConflictId, conflictId)
                .orderByAsc(FwIntermediaryConflictLog::getfOperatetime).list();
        if (list == null) {
            return null;
        }
        return list.stream().map(entity -> {
            ConflictLogBo bo = new ConflictLogBo();
            BeanUtils.copyProperties(entity, bo);
            return bo;
        }).collect(Collectors.toList());
    }

    @Override
    public Result<List<ConflictLogBo>> getByConflictIdAndGetResult(String conflictId) {
        return Result.data(getByConflictId(conflictId));
    }

}