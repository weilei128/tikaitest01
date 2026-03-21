package com.pcitc.szgt.contract.Approval.controller;

import com.pcitc.szgt.contract.Approval.entity.CrApprovalcomments;
import com.pcitc.szgt.contract.Approval.model.Comment;
import com.pcitc.szgt.contract.Approval.service.ICrApprovalcommentsService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.modelEx.Accord;
import com.pcitc.szgt.contract.make.service.ICrContractaccordoaotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 常用审批意见管理
 * </p>
 */
@RestController
@RequestMapping("/approval/comments")

public class ApprovalController {
    @Autowired
    private ICrApprovalcommentsService crApprovalcommentsService;

    /*
     * 常用审批意见添加
     * */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public DataResult addComment(@RequestBody Comment comment) {
        return DataResult.success(crApprovalcommentsService.addComment(comment));
    }

    /*
     * 常用审批意见修改
     * */
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public DataResult updateComment(@RequestBody Comment comment) {
        return DataResult.success(crApprovalcommentsService.updateComment(comment));
    }

    /*
     * 常用审批意见删除
     * */
    @RequestMapping(value = "/delComment", method = RequestMethod.POST)
    public DataResult delComment(@RequestParam(required = true) Integer Id) {
        return DataResult.success(crApprovalcommentsService.delComment(Id));
    }

    /*
     * 常用审批意见查询
     * */
    @RequestMapping(value = "/queryComment", method = RequestMethod.GET)
    public DataResult queryComment() {
        return DataResult.success(crApprovalcommentsService.findComments());
    }

    /*
     * 常用审批意见查询所有（删除和未删除的）
     * */
    @RequestMapping(value = "/queryCommentAll", method = RequestMethod.GET)
    public DataResult queryCommentAll() {
        return DataResult.success(crApprovalcommentsService.findCommentsAll());
    }

}
