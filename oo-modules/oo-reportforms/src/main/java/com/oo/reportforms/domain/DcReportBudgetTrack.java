package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 年度开发井工作量与预算跟踪-预算
 *
 * @author oo
 * @date 2023-10-23
 */
@Data
@TableName("dc_report_budget_track")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "年度开发井工作量与预算跟踪-预算对象", description = "年度开发井工作量与预算跟踪-预算表")
public class DcReportBudgetTrack extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 月份
     */
    @ApiModelProperty(value = "月份")
    @Excel(name = "月份")
    private String month;
    
    /**
     * 指标
     */
    @ApiModelProperty(value = "指标")
    @Excel(name = "指标")
    private String target;
    
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String title;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;
    
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateBy;
    
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    
    /**
     * 删除人
     */
    @ApiModelProperty(value = "删除人")
    @Excel(name = "删除人")
    private String delUser;
    
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date delTime;
}