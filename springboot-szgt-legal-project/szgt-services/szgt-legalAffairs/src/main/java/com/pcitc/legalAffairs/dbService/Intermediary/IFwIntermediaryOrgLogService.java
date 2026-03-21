package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgLog;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryOrgLogVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

/**
 * 中介准入操作记录Service
 * @author meihongli
 */
public interface IFwIntermediaryOrgLogService extends IBaseService<FwIntermediaryOrgLog> {
    
    /**
     * 获取操作记录
     * @param intermediaryId
     * @return
     */
    public Result<List<FwIntermediaryOrgLogVo>> getOperateLog(String intermediaryId);
}