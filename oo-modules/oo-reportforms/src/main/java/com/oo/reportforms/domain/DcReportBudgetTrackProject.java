package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 年度开发井工作量与预算跟踪-预算-项目
 *
 * @author oo
 * @date 2023-10-23
 */
@Data
@TableName("dc_report_budget_track_project")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "年度开发井工作量与预算跟踪-预算-项目对象", description = "年度开发井工作量与预算跟踪-预算-项目表")
public class DcReportBudgetTrackProject extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 关联dc_report_budget_track主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "关联dc_report_budget_track主键ID")
    private String trackId;
    
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    @Excel(name = "项目名称")
    private String name;
    
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @Excel(name = "值")
    private BigDecimal value;
}