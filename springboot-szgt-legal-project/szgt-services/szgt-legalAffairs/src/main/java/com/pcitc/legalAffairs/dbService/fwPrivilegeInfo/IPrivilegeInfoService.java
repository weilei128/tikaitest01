package com.pcitc.legalAffairs.dbService.fwPrivilegeInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.po.fwPrivilegeInfo.FwPrivilegeInfo;
import com.pcitc.legalAffairs.vo.fwPrivilegeInfo.*;

import java.util.List;

/**
 * <p>
 * 法务查询权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-29
 */
public interface IPrivilegeInfoService extends IService<FwPrivilegeInfo> {

    Result updateFwPrivilegeInfo(FwPrivilegeInfoVo param);
    
//    void updateBatch(List<FwPrivilegeInfoVo> param);

    Result deleteFwPrivilegeInfo(List<Long> ids);

    Result viewFwPrivilegeInfo(Long id);

    List<FwPrivilegeInfo> listFwPrivilegeInfo(QueryFwPrivilegeInfoParam param);

    IPage<FwPrivilegeInfoVo> pageFwPrivilegeInfo(QueryFwPrivilegeInfoParam param);

    List<Long> queryOrgIdByUserId(Long userId);

    List<Long> queryOrgIdByUserId(List<Long> userId);

    void saveBatch(SavePrivilegeInfoVo vo);

    public List<Long> privilegedOrgs();

    List<Long> getPrivilegedOrgsR();

    public IPage<PrivilegeUserVo> pageUsers(PrivilegeUserQueryVo query);

    public void deleteByUserId(Long userId);
}
