package com.pcitc.szgt.contract.Approval.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.szgt.contract.Approval.entity.CrApprovalcomments;
import com.pcitc.szgt.contract.Approval.model.Comment;
import com.pcitc.szgt.contract.make.modelEx.Accord;

import java.util.List;

public interface ICrApprovalcommentsService  extends IService<CrApprovalcomments> {
    /*
     * 常用审批意见添加
     * */
    boolean addComment(Comment comment);

    /*
     * 常用审批意见修改
     * */
    boolean updateComment(Comment comment);

    /*
     * 常用审批意见删除
     * */
    boolean delComment(Integer Id);

    /*
     * 常用审批意见查询
     * */
    List<CrApprovalcomments> findComments();

    /*
     * 常用审批意见查询所有（删除和未删除的）
     * */
    List<CrApprovalcomments> findCommentsAll();
}
