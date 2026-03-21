package com.pcitc.szgt.contract.documentinformation.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Officialdocumentenclosures {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 附件下载地址
     */
    @TableField("enclosureUrl")
    private String enclosureUrl;

    /**
     * 附件名称
     */
    @TableField("enclosureName")
    private String enclosureName;

    /**
     * 文档标题
     */
    @TableField("subject")
    private String subject;
    /**
     * 字段名称
     */
    @TableField("fieldname")
    private String fieldname;
}
