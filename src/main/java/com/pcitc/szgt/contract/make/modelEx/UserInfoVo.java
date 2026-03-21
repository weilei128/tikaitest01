package com.pcitc.szgt.contract.make.modelEx;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoVo implements Serializable {

	private static final long serialVersionUID = 5763594996208398614L;
	
	@ApiModelProperty(value = "数据主键")
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Integer fId;

    @ApiModelProperty(value = "用户编码/员工编号")
    @TableField("f_Code")
    private String fCode;

    @ApiModelProperty(value = "中文名")
    @TableField("f_Cname")
    private String fCname;

    @ApiModelProperty(value = "英文名")
    @TableField("f_Ename")
    private String fEname;

    @ApiModelProperty(value = "登录账户名称")
    @TableField("f_Account")
    private String fAccount;

    @ApiModelProperty(value = "职务")
    @TableField("f_Position")
    private String fPosition;

    @ApiModelProperty(value = "是否被锁定",example = "0未锁定/1已锁定")
    @TableField("f_Is_Lock")
    private Integer fIsLock;

    @ApiModelProperty(value = "性别",example = "M男 / W女")
    @TableField("f_Sex")
    private String fSex;

    /**
     * 	在职状态
     */
    @ApiModelProperty(value = "在职状态",example = "")
    @TableField("f_Work_Status")
    private String fWorkStatus;

    /**
     * 	在职状态文本
     */
    @TableField("f_Work_Status_Text")
    private String fWorkStatusText;

    /**
     * 启用状态：0启用/1未启用
     */
    @TableField("f_State")
    private Integer fState;

    /**
     * 排序字段
     */
    @TableField("f_Sort")
    private Integer fSort;

    /**
     * 类型：0普通员工，1待定
     */
    @TableField("f_Type")
    private Integer fType;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableField("f_IsDel")
    private Integer fIsdel;

    /**
     * 创建时间
     */
    @TableField("f_Create_Time")
    private LocalDateTime fCreateTime;


}
