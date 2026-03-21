package com.pcitc.legalAffairs.mapper.litigate.dispute;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSearchBo;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import com.pcitc.szgt.legalAffairs.base.IBaseMapper;

public interface LitigateDisputeMapper extends IBaseMapper<FwLitigateDispute>  {

	IPage<DisputeVo> searchData(Page<DisputeVo> page, @Param(value = "bo") DisputeSearchBo bo);
	
}
