package com.oo.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 文件记录
 *
 * @author oo
 * @date 2023-09-26
 */
@Data
@TableName("dc_report_files")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "文件记录对象", description = "文件记录表")
public class ReportFiles extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键 文件id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键 文件id")
    private String id;
    
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @Excel(name = "文件名称")
    private String fileName;
    
    /**
     * 文件后缀
     */
    @ApiModelProperty(value = "文件后缀")
    @Excel(name = "文件后缀")
    private String fileFormat;
    
    /**
     * 文件上传地址（obs的文件路径）
     */
    @ApiModelProperty(value = "文件上传地址（obs的文件路径）")
    @Excel(name = "文件上传地址")
    private String filePath;
    
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    @Excel(name = "文件大小")
    private String fileUnitSize;
    
    /**
     * 井号id
     */
    @ApiModelProperty(value = "井号")
    @Excel(name = "井号id")
    private String wellId;

    /**
     * 井号
     */
    @ApiModelProperty(value = "井号")
    @TableField(exist = false)
    private String wellName;
    
    /**
     * 项目组织机构id（项目id）
     */
    @ApiModelProperty(value = "项目组织机构id（项目id）")
    @Excel(name = "项目组织机构id")
    private String organizationId;

    /**
     * 项目组织机构名称（项目名称）
     */
    @ApiModelProperty(value = "项目组织机构名称（项目名称）")
    @TableField(exist = false)
    private String organizationName;

    /**
     * 项目组织机构父级id（国家id）
     */
    @ApiModelProperty(value = "项目组织机构父级id（国家id）")
    @Excel(name = "项目组织机构父级id")
    private String organizationParentId;

    /**
     * 项目组织机构父级名称（国家名称）
     */
    @ApiModelProperty(value = "项目组织机构父级名称（国家名称）")
    @TableField(exist = false)
    private String organizationParentName;
    
    /**
     * 油田、区块id
     */
    @ApiModelProperty(value = "油田、区块id")
    @Excel(name = "油田、区块id")
    private String projectId;

    /**
     * 油田、区块名称
     */
    @ApiModelProperty(value = "油田、区块名称")
    @TableField(exist = false)
    private String projectName;
    
    /**
     * 文件域id
     */
    @ApiModelProperty(value = "文件域id")
    @Excel(name = "文件域id")
    private Long businessId;

    /**
     * 文件域名称
     */
    @ApiModelProperty(value = "文件域名称")
    @TableField(exist = false)
    private String businessName;
    
    /**
     * WDPId
     */
    @ApiModelProperty(value = "WDPId")
    @Excel(name = "WDPId")
    private String wdpId;

    /**
     * WDP名称
     */
    @ApiModelProperty(value = "WDP名称")
    @TableField(exist = false)
    private String wdpName;
    
    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    @Excel(name = "版本号")
    private Double scanBatch;

    /**
     * 文件类型 （1 解析日报 2 普通文件）
     */
    @ApiModelProperty(value = "文件类型 （1 解析日报 2 普通文件）")
    @Excel(name = "文件类型 （1 解析日报 2 普通文件）")
    private String rptType;

    /**
     * md5码
     */
    @ApiModelProperty(value = "md5码")
    @Excel(name = "md5码")
    private String rptMd5;
}