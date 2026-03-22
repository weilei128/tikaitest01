package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import com.oo.common.core.web.domain.TranslateVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("dc_report_project_briefly")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "（技术管理岗）项目简介-蔡元君", description = "（技术管理岗）项目简介-蔡元君")
public class DcReportProjectBriefly extends LogicEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一键id
     */
    @TableId("id")
    @ApiModelProperty(value = "唯一键id")
    private String id;

    /**
     * 油田id 关联主数据油田区块
     */
    @TableField("project_id")
    @ApiModelProperty(value = "油田id 关联主数据油田区块")
    private String projectId;

    /**
     * 油田简介
     */
    @TableField("oil_briefly")
    @ApiModelProperty(value = "油田简介")
    @Excel(name = "油田简介")
    private String oilBriefly;

    /**
     * 权益
     */
    @TableField("oil_equity")
    @ApiModelProperty(value = "权益")
    @Excel(name = "权益")
    private String oilEquity;

    /**
     * 项目名称--关联项目表
     */
    @TableField("organization_id")
    @ApiModelProperty(value = "项目名称--关联项目表")
    private String organizationId;

    /**
     * 钻完井项目简介
     */
    @TableField("project_briefly")
    @ApiModelProperty(value = "钻完井项目简介")
    @Excel(name = "钻完井项目简介")
    private String projectBriefly;

    /**
     * 项井数
     */
    @TableField("well_num")
    @ApiModelProperty(value = "项井数")
    @Excel(name = "井数")
    private Integer wellNum;

    /**
     * 水深
     */
    @TableField("water_depth")
    @ApiModelProperty(value = "水深")
    @Excel(name = "水深/m")
    private BigDecimal waterDepth;

    /**
     * 井型id 关联主数据
     */
    @TableField("well_type_id")
    @ApiModelProperty(value = "井型id 关联主数据")
    @DictField(dictType = "dc_pk_well_type")
    private String wellTypeId;

    /**
     * 平均井深
     */
    @TableField("well_depth_avg")
    @ApiModelProperty(value = "平均井深")
    @Excel(name = "平均井深")
    private BigDecimal wellDepthAvg;

    /**
     * 预计作业时间
     */
    @TableField("work_date_estimate")
    @ApiModelProperty(value = "预计作业时间")
    @Excel(name = "预计作业时间")
    private Date workDateEstimate;

    /**
     * 平均单井工期
     */
    @TableField("well_duration_avg")
    @ApiModelProperty(value = "平均单井工期")
    @Excel(name = "平均单井工期")
    private BigDecimal wellDurationAvg;

    /**
     * 总费用
     */
    @TableField("expenses_total")
    @ApiModelProperty(value = "总费用")
    @Excel(name = "总费用")
    private BigDecimal expensesTotal;

    /**
     * 审查阶段
     */
    @TableField("censor_stage")
    @ApiModelProperty(value = "审查阶段")
    @Excel(name = "审查阶段")
    private String censorStage;

    /**
     * 审查会开始时间
     */
    @TableField("censon_begin_date")
    @ApiModelProperty(value = "审查会开始时间")
    private Date censonBeginDate;

    /**
     * 审查会结束时间
     */
    @TableField("censon_end_date")
    @ApiModelProperty(value = "审查会结束时间")
    private Date censonEndDate;

    /** 翻译菜单名称数组 */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();
}
