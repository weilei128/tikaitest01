package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgLogService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryOrgLogMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgLog;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryOrgLogVo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FwIntermediaryOrgLogServiceImpl extends ServiceImpl<FwIntermediaryOrgLogMapper, FwIntermediaryOrgLog>
        implements IFwIntermediaryOrgLogService {

    @Override
    public Result<List<FwIntermediaryOrgLogVo>> getOperateLog(String intermediaryId) {
        List<FwIntermediaryOrgLog> entityList = lambdaQuery()
            .eq(FwIntermediaryOrgLog::getFkIntermediaryId, intermediaryId)
            .orderByDesc(FwIntermediaryOrgLog::getfOperateTime)
            .list();
        if (entityList == null) {
            return Result.data(null);
        }
        return Result.data(entityList.stream().map(entity -> {
            FwIntermediaryOrgLogVo vo = new FwIntermediaryOrgLogVo();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList()));
    }

    @Override
    public boolean save(FwIntermediaryOrgLog entity) {
        entity.setfOperateTime(new Date());
        return super.save(entity);
    }

    public boolean saveBatch(Collection<FwIntermediaryOrgLog> entityList) {
        Date date = new Date();
        entityList.stream().forEach(entity -> entity.setfOperateTime(date));
        return super.saveBatch(entityList);
    }
}