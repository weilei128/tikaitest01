package com.oo.reportforms.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.oo.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DcReportProjectExecuteTraceImportVo {
    @Excel(name = "")
    private String serialNumber;

    /** 项目名称*/
    @Excel(name = "项目名称")
    private String name;

    private String organizationId;

    @Excel(name = "作业者")
    private String worker;

    @Excel(name = "中海油股权")
    private float equity;

    @Excel(name = "发文时间")
    private Date postDate;

    @Excel(name = "文件号")
    private String fileCode;

    @Excel(name = "批准概算中海油份额")
    private String portionBudget;

    @Excel(name = "批准概算全额")
    private String fullAmountBudget;

    @Excel(name = "实际发生中海油份额")
    private String portionReal;

    @Excel(name = "实际发生全额")
    private String fullAmountReal;

    @Excel(name = "2012")
    private String execute2012;

    @Excel(name = "2013")
    private String execute2013;

    @Excel(name = "2014")
    private String execute2014;

    @Excel(name = "2015")
    private String execute2015;

    @Excel(name = "2016")
    private String execute2016;

    @Excel(name = "2017")
    private String execute2017;

    @Excel(name = "2018")
    private String execute2018;

    @Excel(name = "2019")
    private String execute2019;

    @Excel(name = "2020")
    private String execute2020;

    @Excel(name = "2021")
    private String execute2021;

    @Excel(name = "2022")
    private String execute2022;

    @Excel(name = "2023")
    private String execute2023;

}
