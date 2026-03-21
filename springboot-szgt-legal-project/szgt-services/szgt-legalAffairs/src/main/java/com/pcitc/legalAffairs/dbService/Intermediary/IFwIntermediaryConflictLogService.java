package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryConflictLog;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IFwIntermediaryConflictLogService extends IBaseService<FwIntermediaryConflictLog> {

    public void save(ConflictLogBo bo);

    public List<ConflictLogBo> getByConflictId(String conflictId);
    
    public Result<List<ConflictLogBo>> getByConflictIdAndGetResult(String conflictId);
}