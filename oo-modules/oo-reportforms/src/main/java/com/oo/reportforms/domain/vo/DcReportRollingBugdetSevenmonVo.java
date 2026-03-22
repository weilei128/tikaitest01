package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmon;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DcReportRollingBugdetSevenmonVo extends DcReportRollingBugdetSevenmon {
    @Excel(name = "")
    private String serialNumber;

    @ApiModelProperty(value = "国家名称")
    @Excel(name = "业务单元")
    private String countryName;

    @ApiModelProperty(value = "项目名称")
    @Excel(name = "资产/子资产")
    private String organizationName;

    @ApiModelProperty(value = "预算实际1月")
    @Excel(name = "预算实际1月")
    private BigDecimal forecasts1;

    @ApiModelProperty(value = "预算实际2月")
    @Excel(name = "预算实际2月")
    private BigDecimal forecasts2;

    @ApiModelProperty(value = "预算实际3月")
    @Excel(name = "预算实际3月")
    private BigDecimal forecasts3;

    @ApiModelProperty(value = "预算预测4月")
    @Excel(name = "预算预测4月")
    private BigDecimal forecasts4;

    @ApiModelProperty(value = "预算预测5月")
    @Excel(name = "预算预测5月")
    private BigDecimal forecasts5;

    @ApiModelProperty(value = "预算预测6月")
    @Excel(name = "预算预测6月")
    private BigDecimal forecasts6;

    @ApiModelProperty(value = "预算预测7月")
    @Excel(name = "预算预测7月")
    private BigDecimal forecasts7;

    @ApiModelProperty(value = "预算预测8月")
    @Excel(name = "预算预测8月")
    private BigDecimal forecasts8;

    @ApiModelProperty(value = "预算预测9月")
    @Excel(name = "预算预测9月")
    private BigDecimal forecasts9;

    @ApiModelProperty(value = "预算预测10月")
    @Excel(name = "预算预测10月")
    private BigDecimal forecasts10;

    @ApiModelProperty(value = "预算预测11月")
    @Excel(name = "预算预测11月")
    private BigDecimal forecasts11;

    @ApiModelProperty(value = "预算预测12月")
    @Excel(name = "预算预测12月")
    private BigDecimal forecasts12;



    @ApiModelProperty(value = "工作量实际1月")
    @Excel(name = "工作量实际1月")
    private BigDecimal workForecasts1;

    @ApiModelProperty(value = "工作量实际2月")
    @Excel(name = "工作量实际2月")
    private BigDecimal workForecasts2;

    @ApiModelProperty(value = "工作量实际3月")
    @Excel(name = "工作量实际3月")
    private BigDecimal workForecasts3;

    @ApiModelProperty(value = "工作量预测4月")
    @Excel(name = "工作量预测4月")
    private BigDecimal workForecasts4;

    @ApiModelProperty(value = "工作量预测5月")
    @Excel(name = "工作量预测5月")
    private BigDecimal workForecasts5;

    @ApiModelProperty(value = "工作量预测6月")
    @Excel(name = "工作量预测6月")
    private BigDecimal workForecasts6;

    @ApiModelProperty(value = "工作量预测7月")
    @Excel(name = "工作量预测7月")
    private BigDecimal workForecasts7;

    @ApiModelProperty(value = "工作量预测8月")
    @Excel(name = "工作量预测8月")
    private BigDecimal workForecasts8;

    @ApiModelProperty(value = "工作量预测9月")
    @Excel(name = "工作量预测9月")
    private BigDecimal workForecasts9;

    @ApiModelProperty(value = "工作量预测10月")
    @Excel(name = "工作量预测10月")
    private BigDecimal workForecasts10;

    @ApiModelProperty(value = "工作量预测11月")
    @Excel(name = "工作量预测11月")
    private BigDecimal workForecasts11;

    @ApiModelProperty(value = "工作量预测12月")
    @Excel(name = "工作量预测12月")
    private BigDecimal workForecasts12;
}
