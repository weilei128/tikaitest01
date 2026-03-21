package com.pcitc.szgt.contract.documentinformation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Officialdocumentpushscope {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 1:部门   2：人员
     */
    @TableField("userType")
    private String userType;

    /**
     * 部门/人员编码
     */
    @TableField("userCode")
    private String userCode;

    /**
     * 文档标题
     */
    @TableField("subject")
    private String subject;

}
