package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("dc_report_project_execute_trace_year")
@ApiModel(value = "海外项目投资执行跟踪（年度数据）", description = "海外项目投资执行跟踪（年度数据）")
public class DcReportProjectExecuteTraceYear implements Serializable {

    @MppMultiId
    @TableField("organization_id")
    @ApiModelProperty(value = "关联dc_report_project_execute_trace表的id")
    private String organizationId;

    @MppMultiId
    @TableField("year")
    @ApiModelProperty(value = "年份")
    private String year;

    @TableField("value")
    @ApiModelProperty(value = "值")
    private String value;
}
