package com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.mapper.privilege.PrivilegeInfoMapper;
import com.pcitc.legalAffairs.po.fwPrivilegeInfo.FwPrivilegeInfo;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.fwPrivilegeInfo.*;
import com.pctic.common.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 法务查询权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-29
 */
@Service
public class IPrivilegeInfoServiceImpl extends ServiceImpl<PrivilegeInfoMapper, FwPrivilegeInfo> implements IPrivilegeInfoService {

    @Autowired
    private UserOrgService userOrgService;

    @Override
    public Result<?> updateFwPrivilegeInfo(FwPrivilegeInfoVo param) {
        if(null == param ){
            throw new BaseException("请求参数为空",400);
        }
        FwPrivilegeInfo ls = new FwPrivilegeInfo();
        BeanUtils.copyProperties(param,ls);
        boolean i = false;
        if(StringUtils.isEmpty(param.getfId())){
            i = save(ls);
        }else{
            i = updateById(ls);
        }
        if (i) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.success(ResultCode.FAILURE);
    }

//    @Override
//    public void updateBatch(List<FwPrivilegeInfoVo> param) {
//    	lambdaUpdate().remove();
//    	if (param != null) {
//			param.forEach(each -> updateFwPrivilegeInfo(each));
//		}
//    }

    @Override
    public Result<?> deleteFwPrivilegeInfo(List<Long> ids) {
        int i = 0;
        if(CollectionUtils.isNotEmpty(ids)){
            i = this.baseMapper.deleteBatchIds(ids);
        }
        if(i > 0){
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.success(ResultCode.FAILURE);
    }

    @Override
    public Result<?> viewFwPrivilegeInfo(Long id) {
        if(!StringUtils.isEmpty(id)){
            FwPrivilegeInfo fwPrivilegeInfo = this.getById(id);
            if(null != fwPrivilegeInfo){
                FwPrivilegeInfoVo dto = new FwPrivilegeInfoVo();
                BeanUtils.copyProperties(fwPrivilegeInfo,dto);
                return Result.data(dto);
            }
        }
        return Result.data(null);
    }

    private LambdaQueryChainWrapper<FwPrivilegeInfo> buildWrapper(QueryFwPrivilegeInfoParam param) {
        return lambdaQuery()
                .eq(param.getUserId() != null, FwPrivilegeInfo::getFkUserId, param.getUserId())
                .eq(param.getOrgId() != null, FwPrivilegeInfo::getFkOrgId, param.getOrgId());
    }

    @Override
    public List<FwPrivilegeInfo> listFwPrivilegeInfo(QueryFwPrivilegeInfoParam param) {
        return buildWrapper(param).list();
    }

    @Override
    public IPage<FwPrivilegeInfoVo> pageFwPrivilegeInfo(QueryFwPrivilegeInfoParam param) {
        Page<FwPrivilegeInfo> page = new Page<>(param.getPageNum(),param.getPageSize());
        IPage<FwPrivilegeInfo> privilegeInfoIPage = buildWrapper(param).page(page);
        return privilegeInfoIPage.convert(this::convertEntity);
    }

    @Override
    public List<Long> queryOrgIdByUserId(Long userId) {
        lambdaQuery().eq(FwPrivilegeInfo::getfIsdel,0).eq(FwPrivilegeInfo::getFkUserId,userId).list();
        List<FwPrivilegeInfo> fwPrivilegeInfos = lambdaQuery().eq(FwPrivilegeInfo::getfIsdel,0).eq(FwPrivilegeInfo::getFkUserId,userId).list();
        if (fwPrivilegeInfos == null) {
            return null;
        }
        return fwPrivilegeInfos.stream().map(FwPrivilegeInfo::getFkOrgId).distinct().collect(Collectors.toList());
    }



    @Override
    public List<Long> queryOrgIdByUserId(List<Long> userId) {
        lambdaQuery().eq(FwPrivilegeInfo::getfIsdel,0).in(FwPrivilegeInfo::getFkUserId,userId).list();
        List<FwPrivilegeInfo> fwPrivilegeInfos = lambdaQuery().eq(FwPrivilegeInfo::getfIsdel,0).eq(FwPrivilegeInfo::getFkUserId,userId).list();
        if (fwPrivilegeInfos == null) {
            return null;
        }
        return fwPrivilegeInfos.stream().map(FwPrivilegeInfo::getFkOrgId).distinct().collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveBatch(SavePrivilegeInfoVo vo) {
        if (vo == null) {
            throw new BaseException("参数异常", 400);
        }
        if (vo.getUsers() == null || vo.getUsers().size() == 0) {
            throw new BaseException("用户不存在", 400);
        }

        if (vo.getOrgs() == null) {
            vo.setOrgs(new ArrayList<>());
        }
        List<FwPrivilegeInfo> entities = new ArrayList<>();
        for (FwPrivilegeInfoVo user : vo.getUsers()) {
            if (user.getFkUserId() == null) {
                continue;
            }
            lambdaUpdate().eq(FwPrivilegeInfo::getFkUserId, user.getFkUserId()).remove();
            for (FwPrivilegeInfoVo org : vo.getOrgs()) {
                if (org.getFkOrgId() == null) {
                    continue;
                }
                FwPrivilegeInfo entity = new FwPrivilegeInfo();
                entity.setFkUserId(user.getFkUserId());
                entity.setFkUserName(user.getFkUserName());
                entity.setFkOrgId(org.getFkOrgId());
                entity.setFkOrgName(org.getFkOrgName());
                entities.add(entity);
            }
        }
        saveBatch(entities);
    }

    @Override
    public List<Long> privilegedOrgs() {
        Integer userId = UserUtils.getUserInfo().getfId();
        List<Long> longs = queryOrgIdByUserId(Long.valueOf(userId));
        if (longs.size() == 0) {
            return null;
        }
        return longs;
    }

    @Override
    public List<Long> getPrivilegedOrgsR() {
        List<Long> longs = privilegedOrgs();
        if (longs == null) {
            return null;
        }
        return userOrgService.getOrgIds(userOrgService.getOrgInfos(longs), longs);
    }

    @Override
    public IPage<PrivilegeUserVo> pageUsers(PrivilegeUserQueryVo query) {
        Page<PrivilegeUserVo> page = new Page<>(query.getCurrent(), query.getSize());
        IPage<PrivilegeUserVo> resPage = baseMapper.pageUsers(page, query);
        return resPage.convert(e -> {
            Long userId = e.getFkUserId();
            List<Long> orgIds = userOrgService.getUserOrg(userId.toString());
            List<String> orgNames = new ArrayList<>();
            if (orgIds != null) {
                for (Long orgId: orgIds) {
                    Map<String, Object> orgInfo = userOrgService.getOrgInfo(orgId.toString());
                    orgNames.add(orgInfo.get("fName") == null? null: orgInfo.get("fName").toString());
                }
            }

            String orgNameString = orgNames.stream().filter(each -> !StringUtils.isEmpty(each)).collect(Collectors.joining(","));
            e.setOrgs(orgNameString);
            return e;
        });
    }

    @Override
    public void deleteByUserId(Long userId) {
        lambdaUpdate().eq(FwPrivilegeInfo::getFkUserId, userId).remove();
    }

    private FwPrivilegeInfoVo convertEntity(FwPrivilegeInfo entity){
        if(null == entity){
            return null;
        }
        FwPrivilegeInfoVo dto = new FwPrivilegeInfoVo();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }


}
