package com.pcitc.legalAffairs.dbService.litigate.dispute;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSearchBo;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface ILitigateDisputeService extends IBaseService<FwLitigateDispute> {
    
    public IPage<DisputeVo> searchPage(Page<DisputeVo> page, DisputeSearchBo bo);

    /**
     * 审批用更新接口，不请求用户信息
     * @param entity
     * @return
     */
	boolean updateByIdApproval(FwLitigateDispute entity);
}
