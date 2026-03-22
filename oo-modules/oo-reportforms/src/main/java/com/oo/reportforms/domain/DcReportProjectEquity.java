package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 国际公司项目权益情况
 *
 * @author oo
 * @date 2023-09-07
 */
@Data
@TableName("dc_report_project_equity")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际公司项目权益情况对象", description = "国际公司项目权益情况表")
public class DcReportProjectEquity extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 区块id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "区块id")
    private String projectId;
    
    /**
     * 作业者公司
     */
    @ApiModelProperty(value = "作业者公司")
    @Excel(name = "作业者公司")
    private String workerCompany;
    
    /**
     * 作业者权益
     */
    @ApiModelProperty(value = "作业者权益")
    @Excel(name = "作业者权益")
    private String workerEquity;
    
    /**
     * 伙伴1公司
     */
    @ApiModelProperty(value = "伙伴1公司")
    @Excel(name = "伙伴1公司")
    private String partner1Company;
    
    /**
     * 伙伴1权益
     */
    @ApiModelProperty(value = "伙伴1权益")
    @Excel(name = "伙伴1权益")
    private String partner1Equity;
    
    /**
     * 伙伴2公司
     */
    @ApiModelProperty(value = "伙伴2公司")
    @Excel(name = "伙伴2公司")
    private String partner2Company;
    
    /**
     * 伙伴2权益
     */
    @ApiModelProperty(value = "伙伴2权益")
    @Excel(name = "伙伴2权益")
    private String partner2Equity;
    
    /**
     * 伙伴3公司
     */
    @ApiModelProperty(value = "伙伴3公司")
    @Excel(name = "伙伴3公司")
    private String partner3Company;
    
    /**
     * 伙伴3权益
     */
    @ApiModelProperty(value = "伙伴3权益")
    @Excel(name = "伙伴3权益")
    private String partner3Equity;
    
    /**
     * 伙伴4公司
     */
    @ApiModelProperty(value = "伙伴4公司")
    @Excel(name = "伙伴4公司")
    private String partner4Company;
    
    /**
     * 伙伴4权益
     */
    @ApiModelProperty(value = "伙伴4权益")
    @Excel(name = "伙伴4权益")
    private String partner4Equity;
    
    /**
     * 伙伴5公司
     */
    @ApiModelProperty(value = "伙伴5公司")
    @Excel(name = "伙伴5公司")
    private String partner5Company;
    
    /**
     * 伙伴5权益
     */
    @ApiModelProperty(value = "伙伴5权益")
    @Excel(name = "伙伴5权益")
    private String partner5Equity;
    
    /**
     * 伙伴6公司
     */
    @ApiModelProperty(value = "伙伴6公司")
    @Excel(name = "伙伴6公司")
    private String partner6Company;
    
    /**
     * 伙伴6权益
     */
    @ApiModelProperty(value = "伙伴6权益")
    @Excel(name = "伙伴6权益")
    private String partner6Equity;
    
    /**
     * 伙伴7公司
     */
    @ApiModelProperty(value = "伙伴7公司")
    @Excel(name = "伙伴7公司")
    private String partner7Company;
    
    /**
     * 伙伴7权益
     */
    @ApiModelProperty(value = "伙伴7权益")
    @Excel(name = "伙伴7权益")
    private String partner7Equity;
    
    /**
     * 伙伴8公司
     */
    @ApiModelProperty(value = "伙伴8公司")
    @Excel(name = "伙伴8公司")
    private String partner8Company;
    
    /**
     * 伙伴8权益
     */
    @ApiModelProperty(value = "伙伴8权益")
    @Excel(name = "伙伴8权益")
    private String partner8Equity;
    
    /**
     * 伙伴9公司
     */
    @ApiModelProperty(value = "伙伴9公司")
    @Excel(name = "伙伴9公司")
    private String partner9Company;
    
    /**
     * 伙伴9权益
     */
    @ApiModelProperty(value = "伙伴9权益")
    @Excel(name = "伙伴9权益")
    private String partner9Equity;
    
    /**
     * 伙伴10公司
     */
    @ApiModelProperty(value = "伙伴10公司")
    @Excel(name = "伙伴10公司")
    private String partner10Company;
    
    /**
     * 伙伴10权益
     */
    @ApiModelProperty(value = "伙伴10权益")
    @Excel(name = "伙伴10权益")
    private String partner10Equity;
    
    /**
     * 伙伴11公司
     */
    @ApiModelProperty(value = "伙伴11公司")
    @Excel(name = "伙伴11公司")
    private String partner11Company;
    
    /**
     * 伙伴11权益
     */
    @ApiModelProperty(value = "伙伴11权益")
    @Excel(name = "伙伴11权益")
    private String partner11Equity;
    
    /**
     * 伙伴12公司
     */
    @ApiModelProperty(value = "伙伴12公司")
    @Excel(name = "伙伴12公司")
    private String partner12Company;
    
    /**
     * 伙伴12权益
     */
    @ApiModelProperty(value = "伙伴12权益")
    @Excel(name = "伙伴12权益")
    private String partner12Equity;
    
    /**
     * 伙伴13公司
     */
    @ApiModelProperty(value = "伙伴13公司")
    @Excel(name = "伙伴13公司")
    private String partner13Company;
    
    /**
     * 伙伴13权益
     */
    @ApiModelProperty(value = "伙伴13权益")
    @Excel(name = "伙伴13权益")
    private String partner13Equity;
    
    /**
     * 伙伴14公司
     */
    @ApiModelProperty(value = "伙伴14公司")
    @Excel(name = "伙伴14公司")
    private String partner14Company;
    
    /**
     * 伙伴14权益
     */
    @ApiModelProperty(value = "伙伴14权益")
    @Excel(name = "伙伴14权益")
    private String partner14Equity;
    
    /**
     * 伙伴15公司
     */
    @ApiModelProperty(value = "伙伴15公司")
    @Excel(name = "伙伴15公司")
    private String partner15Company;
    
    /**
     * 伙伴15权益
     */
    @ApiModelProperty(value = "伙伴15权益")
    @Excel(name = "伙伴15权益")
    private String partner15Equity;
    
    /**
     * 伙伴16公司
     */
    @ApiModelProperty(value = "伙伴16公司")
    @Excel(name = "伙伴16公司")
    private String partner16Company;
    
    /**
     * 伙伴16权益
     */
    @ApiModelProperty(value = "伙伴16权益")
    @Excel(name = "伙伴16权益")
    private String partner16Equity;
    
    /**
     * 伙伴17公司
     */
    @ApiModelProperty(value = "伙伴17公司")
    @Excel(name = "伙伴17公司")
    private String partner17Company;
    
    /**
     * 伙伴17权益
     */
    @ApiModelProperty(value = "伙伴17权益")
    @Excel(name = "伙伴17权益")
    private String partner17Equity;
    
    /**
     * 伙伴18公司
     */
    @ApiModelProperty(value = "伙伴18公司")
    @Excel(name = "伙伴18公司")
    private String partner18Company;
    
    /**
     * 伙伴18权益
     */
    @ApiModelProperty(value = "伙伴18权益")
    @Excel(name = "伙伴18权益")
    private String partner18Equity;
    
    /**
     * 伙伴19公司
     */
    @ApiModelProperty(value = "伙伴19公司")
    @Excel(name = "伙伴19公司")
    private String partner19Company;
    
    /**
     * 伙伴19权益
     */
    @ApiModelProperty(value = "伙伴19权益")
    @Excel(name = "伙伴19权益")
    private String partner19Equity;
    
    /**
     * 伙伴20公司
     */
    @ApiModelProperty(value = "伙伴20公司")
    @Excel(name = "伙伴20公司")
    private String partner20Company;
    
    /**
     * 伙伴20权益
     */
    @ApiModelProperty(value = "伙伴20权益")
    @Excel(name = "伙伴20权益")
    private String partner20Equity;
    /**
     * 最大列
     */
    private Integer maxColumn;
}