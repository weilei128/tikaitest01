package com.pcitc.szgt.contract.Approval.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.szgt.contract.Approval.entity.CrApprovalcomments;
import com.pcitc.szgt.contract.Approval.mapper.CrApprovalcommentsMapper;
import com.pcitc.szgt.contract.Approval.model.Comment;
import com.pcitc.szgt.contract.Approval.service.ICrApprovalcommentsService;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.NotFoundException;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrApprovalcommentsServicelmpl extends ServiceImpl<CrApprovalcommentsMapper, CrApprovalcomments> implements ICrApprovalcommentsService {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    /*
     * 常用审批意见添加
     * */
    public boolean addComment(Comment comment) {

        if (comment.comment == null || comment.comment.length() <= 0) {
            throw new NotFoundException("常用审批意见必填！", Constants.FAILCODE);
        }
        CrApprovalcomments crApprovalcomments = new CrApprovalcomments();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crApprovalcomments.setComment(comment.comment);
        crApprovalcomments.setLogicdel(0);//是否删除
        crApprovalcomments.setCreatedbyid(userInfo.getSysUser().getfId().toString());
        crApprovalcomments.setCreatedbyname(userInfo.getSysUser().getfCname());
        crApprovalcomments.setCreateddate(LocalDateTime.now());
        this.save(crApprovalcomments);
        return true;
    }

    /*
     * 常用审批意见修改
     * */
    @Override
    public boolean updateComment(Comment comment) {
        if (comment.id == null || StringUtils.isEmpty(comment.id)) {
            throw new NotFoundException("常用审批意见ID必填！", Constants.FAILCODE);
        }
        if (comment.comment == null || comment.comment.length() <= 0) {
            throw new NotFoundException("常用审批意见必填！", Constants.FAILCODE);
        }
        CrApprovalcomments crApprovalcomments = new CrApprovalcomments();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crApprovalcomments.setId(comment.id);
        crApprovalcomments.setComment(comment.comment);
        crApprovalcomments.setModifiedbyid(userInfo.getSysUser().getfId().toString());
        crApprovalcomments.setModifiedbyname(userInfo.getSysUser().getfCname());
        crApprovalcomments.setModifieddate(LocalDateTime.now());
        this.updateById(crApprovalcomments);
        return true;
    }

    /*
     * 常用审批意见删除
     * */
    @Override
    public boolean delComment(Integer Id) {
        if (StringUtils.isEmpty(Id)) {
            throw new NotFoundException("常用审批意见ID必填！", Constants.FAILCODE);
        }
        CrApprovalcomments crApprovalcomments = new CrApprovalcomments();
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        crApprovalcomments.setId(Id);
        crApprovalcomments.setLogicdel(1);
        crApprovalcomments.setModifiedbyid(userInfo.getSysUser().getfId().toString());
        crApprovalcomments.setModifiedbyname(userInfo.getSysUser().getfCname());
        crApprovalcomments.setModifieddate(LocalDateTime.now());
        boolean success = baseMapper.updateById(crApprovalcomments) > 0 ? true : false;
        return success;
    }

    /*
     * 常用审批意见查询
     * */
    @Override
    public List<CrApprovalcomments> findComments() {
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        QueryWrapper<CrApprovalcomments> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(CrApprovalcomments::getLogicdel, 0)
                .eq(CrApprovalcomments::getCreatedbyid, userInfo.getSysUser().getfId().toString());
        queryWrapper.orderByAsc("length(Id),Id");

        return this.list(queryWrapper);
    }

    /*
     *  常用审批意见查询所有（删除和未删除的，所有人）
     * */
    @Override
    public List<CrApprovalcomments> findCommentsAll() {
        QueryWrapper<CrApprovalcomments> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("length(Id),Id");
        return this.list(queryWrapper);
    }

}
