package com.pcitc.szgt.contract.Approval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: ChengLei.liu
 * @Date: 2021-08-30 15:36:28
 * @Description: 常用审批意见表
 */
@Data
@TableName("cr_approvalcomments")
public class CrApprovalcomments implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 审批意见
     */
    private String comment;
    /**
     * 逻辑删除：0未删除，1已经删除
     */
    private Integer logicdel;
    /**
     * 创建人Id
     */
    private String createdbyid;
    /**
     * 创建人名称
     */
    private String createdbyname;
    /**
     * 创建日期
     */
    private LocalDateTime createddate;

    /**
     * 修改人Id
     */
    private String modifiedbyid;
    /**
     * 修改人名称
     */
    private String modifiedbyname;
    /**
     * 修改日期
     */
    private LocalDateTime modifieddate;

}
