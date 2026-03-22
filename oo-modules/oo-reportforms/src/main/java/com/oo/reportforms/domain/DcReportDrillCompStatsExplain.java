package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("dc_report_drill_comp_stats_explain")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据对象", description = "描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据表")
public class DcReportDrillCompStatsExplain extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 指标
     */
    @ApiModelProperty(value = "指标")
    @Excel(name = "指标")
    private String metrics;
    
    /**
     * 项目（不需要关联）
     */
    @ApiModelProperty(value = "项目（不需要关联）")
    @Excel(name = "项目")
    private String project;
    
    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    @Excel(name = "说明")
    private String description;
}