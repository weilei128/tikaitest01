package com.oo.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 文件操作日志
 *
 * @author oo
 * @date 2023-10-18
 */
@Data
@TableName("dc_report_file_log")
@ApiModel(value = "文件操作日志对象", description = "文件操作日志表")
public class ReportFileLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "日志id")
    private String id;

    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    @Excel(name = "文件id")
    private String fileId;

    /**
     * 日志内容
     */
    @ApiModelProperty(value = "日志内容")
    @Excel(name = "日志内容")
    @DictField(dictType = "file_log_content")
    private String content;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    @Excel(name = "操作类型")
    @DictField(dictType = "file_oper_type")
    private String operType;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    @Excel(name = "文件名")
    private String fileName;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    @Excel(name = "版本号")
    private String fileVersion;

    /**
     * 井号
     */
    @ApiModelProperty(value = "井号")
    @Excel(name = "井号")
    private String wellName;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    @Excel(name = "项目名")
    private String organizationName;

    /**
     * 操作人员
     */
    @ApiModelProperty(value = "操作人员")
    @Excel(name = "操作人员")
    private String operName;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    /**
     * 主机地址
     */
    @ApiModelProperty(value = "主机地址")
    @Excel(name = "主机地址")
    private String operIp;

    /**
     * 操作状态（0正常 1异常）
     */
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    @Excel(name = "操作状态")
    private String status;

    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息")
    @Excel(name = "错误消息")
    private String errorMsg;

    /**
     * 批次id
     */
    @ApiModelProperty(value = "批次id")
    @Excel(name = "批次id")
    private Long batchId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date startDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date endDate;
}