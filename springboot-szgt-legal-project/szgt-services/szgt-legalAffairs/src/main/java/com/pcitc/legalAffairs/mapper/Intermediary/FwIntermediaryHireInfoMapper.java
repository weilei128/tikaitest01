package com.pcitc.legalAffairs.mapper.Intermediary;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.Intermediary.HireInfoQuery;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.vo.Intermediary.IntermediaryHireVo;

public interface FwIntermediaryHireInfoMapper extends BaseMapper<FwIntermediaryHireInfo> {
	
	public IPage<IntermediaryHireVo> queryHireInfo(Page<IntermediaryHireVo> page, @Param(value = "bo") HireInfoQuery bo);
}
