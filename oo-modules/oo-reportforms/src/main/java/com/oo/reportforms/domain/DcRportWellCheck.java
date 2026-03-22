package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("dc_report_well_check")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "（井控管理岗）海油国际井控检查记录", description = "（井控管理岗）海油国际井控检查记录")
public class DcRportWellCheck extends LogicEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 单井id
     */
    @TableField("well_id")
    @ApiModelProperty(value = "单井id")
    private String wellId;

    /**
     * 年份 如 2023
     */
    @TableField("year")
    @ApiModelProperty(value = "年份如 2023")
    private String year;

    /**
     * 国家id关联
     */
    @TableField("organization_id")
    @ApiModelProperty(value = "国家id关联")
    private String organizationId;

    /**
     * 分险级别 明文存储 如 一类 二类
     */
    @TableField("risk_level")
    @DictField(dictType = "risk_type")
    @ApiModelProperty(value = "分险级别 明文存储 如 一类 二类")
    @Excel(name = "海油国际风险级别")
    private String riskLevel;

    /**
     * 开钻前检查 打钩或者为空
     */
    @TableField("check_drilling_before")
    @ApiModelProperty(value = "开钻前检查 打钩或者为空")
    @Excel(name = "开钻前检查")
    private String checkDrillingBefore;

    /**
     * 钻开油气层检查 打钩或者为空
     */
    @TableField("check_drill_oil")
    @ApiModelProperty(value = "钻开油气层检查 打钩或者为空")
    @Excel(name = "钻开油气层检查")
    private String checkDrillOil;

    /**
     * 开钻前检查日期
     */
    @TableField("check_drilling_before_date")
    @ApiModelProperty(value = "开钻前检查日期")
    @Excel(name = "开钻前检查日期")
    private Date checkDrillingBeforeDate;

    /**
     * 钻开油气层检查日期
     */
    @TableField("check_drill_oil_date")
    @ApiModelProperty(value = "钻开油气层检查日期")
    @Excel(name = "钻开油气层检查日期")
    private Date checkDrillOilDate;

    /**
     * 钻机
     */
    @TableField("borer")
    @ApiModelProperty(value = "钻机")
    @Excel(name = "钻机")
    private String borer;
}
