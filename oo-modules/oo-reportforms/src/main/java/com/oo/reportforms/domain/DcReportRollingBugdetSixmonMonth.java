package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 滚动预测6+6-月份
 *
 * @author oo
 * @date 2023-11-02
 */
@Data
@TableName("dc_report_rolling_bugdet_sixmon_month")
@ApiModel(value = "滚动预测6+6-月份对象", description = "滚动预测6+6-月份表")
public class DcReportRollingBugdetSixmonMonth implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 关联dc_report_rolling_bugdet主键ID
     */
    @MppMultiId
    @ApiModelProperty(value = "关联dc_report_rolling_bugdet主键ID")
    private String rollingBugdetId;
    
    /**
     * 月份
     */
    @MppMultiId
    @ApiModelProperty(value = "月份")
    private String month;
    
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @Excel(name = "值")
    private BigDecimal value;
}