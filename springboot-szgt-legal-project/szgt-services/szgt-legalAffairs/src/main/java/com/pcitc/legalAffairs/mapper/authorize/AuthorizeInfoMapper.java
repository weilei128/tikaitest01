package com.pcitc.legalAffairs.mapper.authorize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.vo.authorize.AuthorizeInfoVo;
import com.pcitc.szgt.legalAffairs.base.IBaseMapper;

import org.apache.ibatis.annotations.Param;

public interface AuthorizeInfoMapper extends IBaseMapper<FwAuthorizeInfo> {
    
    public IPage<AuthorizeInfoVo> getPageByConditions(Page<AuthorizeInfoVo> page, @Param(value = "bo") AuthorizeQueryBo bo);
    
}