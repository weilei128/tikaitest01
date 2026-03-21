package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.vo.authorize.AuthorizeInfoVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IAuthorizeInfoService extends IBaseService<FwAuthorizeInfo> {

    public IPage<AuthorizeInfoVo> getPageByConditions(Page<AuthorizeInfoVo> page, AuthorizeQueryBo bo);
    
    /**
     * 审批用更新接口，不请求用户信息
     * @param entity
     * @return
     */
    public boolean updateByIdApproval(FwAuthorizeInfo entity);
}