package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportOutputWellBugdet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "年度预算Vo", description = "年度预算Vo")
public class DcReportOutputWellBugdetVo extends DcReportOutputWellBugdet {
    @Excel(name = "")
    private String serialNumber;

    @ApiModelProperty(value = "项目名称")
    @Excel(name = "资产/子资产")
    private String organizationName;

    @ApiModelProperty(value = "预算科目名称")
    private String wellConfig;

    @ApiModelProperty(value = "井型名称")
    private String wellType;

    @ApiModelProperty(value = "预算正式1月")
    @Excel(name = "预算正式1月")
    private BigDecimal budgetFormal1;

    @ApiModelProperty(value = "预算正式2月")
    @Excel(name = "预算正式2月")
    private BigDecimal budgetFormal2;

    @ApiModelProperty(value = "预算正式3月")
    @Excel(name = "预算正式3月")
    private BigDecimal budgetFormal3;

    @ApiModelProperty(value = "预算正式4月")
    @Excel(name = "预算正式4月")
    private BigDecimal budgetFormal4;

    @ApiModelProperty(value = "预算正式5月")
    @Excel(name = "预算正式5月")
    private BigDecimal budgetFormal5;

    @ApiModelProperty(value = "预算正式6月")
    @Excel(name = "预算正式6月")
    private BigDecimal budgetFormal6;

    @ApiModelProperty(value = "预算正式7月")
    @Excel(name = "预算正式7月")
    private BigDecimal budgetFormal7;

    @ApiModelProperty(value = "预算正式8月")
    @Excel(name = "预算正式8月")
    private BigDecimal budgetFormal8;

    @ApiModelProperty(value = "预算正式9月")
    @Excel(name = "预算正式9月")
    private BigDecimal budgetFormal9;

    @ApiModelProperty(value = "预算正式10月")
    @Excel(name = "预算正式10月")
    private BigDecimal budgetFormal10;

    @ApiModelProperty(value = "预算正式11月")
    @Excel(name = "预算正式11月")
    private BigDecimal budgetFormal11;

    @ApiModelProperty(value = "预算正式12月")
    @Excel(name = "预算正式12月")
    private BigDecimal budgetFormal12;


    @ApiModelProperty(value = "预算待批1月")
    @Excel(name = "预算待批1月")
    private BigDecimal budgetPending1;

    @ApiModelProperty(value = "预算待批2月")
    @Excel(name = "预算待批2月")
    private BigDecimal budgetPending2;

    @ApiModelProperty(value = "预算待批3月")
    @Excel(name = "预算待批3月")
    private BigDecimal budgetPending3;

    @ApiModelProperty(value = "预算待批4月")
    @Excel(name = "预算待批4月")
    private BigDecimal budgetPending4;

    @ApiModelProperty(value = "预算待批5月")
    @Excel(name = "预算待批5月")
    private BigDecimal budgetPending5;

    @ApiModelProperty(value = "预算待批6月")
    @Excel(name = "预算待批6月")
    private BigDecimal budgetPending6;

    @ApiModelProperty(value = "预算待批7月")
    @Excel(name = "预算待批7月")
    private BigDecimal budgetPending7;

    @ApiModelProperty(value = "预算待批8月")
    @Excel(name = "预算待批8月")
    private BigDecimal budgetPending8;

    @ApiModelProperty(value = "预算待批9月")
    @Excel(name = "预算待批9月")
    private BigDecimal budgetPending9;

    @ApiModelProperty(value = "预算待批10月")
    @Excel(name = "预算待批10月")
    private BigDecimal budgetPending10;

    @ApiModelProperty(value = "预算待批11月")
    @Excel(name = "预算待批11月")
    private BigDecimal budgetPending11;

    @ApiModelProperty(value = "预算待批12月")
    @Excel(name = "预算待批12月")
    private BigDecimal budgetPending12;


    @ApiModelProperty(value = "预算合计1月")
    @Excel(name = "预算合计1月")
    private BigDecimal budgetTotal1;

    @ApiModelProperty(value = "预算合计2月")
    @Excel(name = "预算合计2月")
    private BigDecimal budgetTotal2;

    @ApiModelProperty(value = "预算合计3月")
    @Excel(name = "预算合计3月")
    private BigDecimal budgetTotal3;

    @ApiModelProperty(value = "预算合计4月")
    @Excel(name = "预算合计4月")
    private BigDecimal budgetTotal4;

    @ApiModelProperty(value = "预算合计5月")
    @Excel(name = "预算合计5月")
    private BigDecimal budgetTotal5;

    @ApiModelProperty(value = "预算合计6月")
    @Excel(name = "预算合计6月")
    private BigDecimal budgetTotal6;

    @ApiModelProperty(value = "预算合计7月")
    @Excel(name = "预算合计7月")
    private BigDecimal budgetTotal7;

    @ApiModelProperty(value = "预算合计8月")
    @Excel(name = "预算合计8月")
    private BigDecimal budgetTotal8;

    @ApiModelProperty(value = "预算合计9月")
    @Excel(name = "预算合计9月")
    private BigDecimal budgetTotal9;

    @ApiModelProperty(value = "预算合计10月")
    @Excel(name = "预算合计10月")
    private BigDecimal budgetTotal10;

    @ApiModelProperty(value = "预算合计11月")
    @Excel(name = "预算合计11月")
    private BigDecimal budgetTotal11;

    @ApiModelProperty(value = "预算合计12月")
    @Excel(name = "预算合计12月")
    private BigDecimal budgetTotal12;



    @ApiModelProperty(value = "工作量正式1月")
    @Excel(name = "工作量正式1月")
    private BigDecimal realityFormal1;

    @ApiModelProperty(value = "工作量正式2月")
    @Excel(name = "工作量正式2月")
    private BigDecimal realityFormal2;

    @ApiModelProperty(value = "工作量正式3月")
    @Excel(name = "工作量正式3月")
    private BigDecimal realityFormal3;

    @ApiModelProperty(value = "工作量正式4月")
    @Excel(name = "工作量正式4月")
    private BigDecimal realityFormal4;

    @ApiModelProperty(value = "工作量正式5月")
    @Excel(name = "工作量正式5月")
    private BigDecimal realityFormal5;

    @ApiModelProperty(value = "工作量正式6月")
    @Excel(name = "工作量正式6月")
    private BigDecimal realityFormal6;

    @ApiModelProperty(value = "工作量正式7月")
    @Excel(name = "工作量正式7月")
    private BigDecimal realityFormal7;

    @ApiModelProperty(value = "工作量正式8月")
    @Excel(name = "工作量正式8月")
    private BigDecimal realityFormal8;

    @ApiModelProperty(value = "工作量正式9月")
    @Excel(name = "工作量正式9月")
    private BigDecimal realityFormal9;

    @ApiModelProperty(value = "工作量正式10月")
    @Excel(name = "工作量正式10月")
    private BigDecimal realityFormal10;

    @ApiModelProperty(value = "工作量正式11月")
    @Excel(name = "工作量正式11月")
    private BigDecimal realityFormal11;

    @ApiModelProperty(value = "工作量正式12月")
    @Excel(name = "工作量正式12月")
    private BigDecimal realityFormal12;


    @ApiModelProperty(value = "工作量待批1月")
    @Excel(name = "工作量待批1月")
    private BigDecimal realityPending1;

    @ApiModelProperty(value = "工作量待批2月")
    @Excel(name = "工作量待批2月")
    private BigDecimal realityPending2;

    @ApiModelProperty(value = "工作量待批3月")
    @Excel(name = "工作量待批3月")
    private BigDecimal realityPending3;

    @ApiModelProperty(value = "工作量待批4月")
    @Excel(name = "工作量待批4月")
    private BigDecimal realityPending4;

    @ApiModelProperty(value = "工作量待批5月")
    @Excel(name = "工作量待批5月")
    private BigDecimal realityPending5;

    @ApiModelProperty(value = "工作量待批6月")
    @Excel(name = "工作量待批6月")
    private BigDecimal realityPending6;

    @ApiModelProperty(value = "工作量待批7月")
    @Excel(name = "工作量待批7月")
    private BigDecimal realityPending7;

    @ApiModelProperty(value = "工作量待批8月")
    @Excel(name = "工作量待批8月")
    private BigDecimal realityPending8;

    @ApiModelProperty(value = "工作量待批9月")
    @Excel(name = "工作量待批9月")
    private BigDecimal realityPending9;

    @ApiModelProperty(value = "工作量待批10月")
    @Excel(name = "工作量待批10月")
    private BigDecimal realityPending10;

    @ApiModelProperty(value = "工作量待批11月")
    @Excel(name = "工作量待批11月")
    private BigDecimal realityPending11;

    @ApiModelProperty(value = "工作量待批12月")
    @Excel(name = "工作量待批12月")
    private BigDecimal realityPending12;


    @ApiModelProperty(value = "工作量合计1月")
    @Excel(name = "工作量合计1月")
    private BigDecimal realityTotal1;

    @ApiModelProperty(value = "工作量合计2月")
    @Excel(name = "工作量合计2月")
    private BigDecimal realityTotal2;

    @ApiModelProperty(value = "工作量合计3月")
    @Excel(name = "工作量合计3月")
    private BigDecimal realityTotal3;

    @ApiModelProperty(value = "工作量合计4月")
    @Excel(name = "工作量合计4月")
    private BigDecimal realityTotal4;

    @ApiModelProperty(value = "工作量合计5月")
    @Excel(name = "工作量合计5月")
    private BigDecimal realityTotal5;

    @ApiModelProperty(value = "工作量合计6月")
    @Excel(name = "工作量合计6月")
    private BigDecimal realityTotal6;

    @ApiModelProperty(value = "工作量合计7月")
    @Excel(name = "工作量合计7月")
    private BigDecimal realityTotal7;

    @ApiModelProperty(value = "工作量合计8月")
    @Excel(name = "工作量合计8月")
    private BigDecimal realityTotal8;

    @ApiModelProperty(value = "工作量合计9月")
    @Excel(name = "工作量合计9月")
    private BigDecimal realityTotal9;

    @ApiModelProperty(value = "工作量合计10月")
    @Excel(name = "工作量合计10月")
    private BigDecimal realityTotal10;

    @ApiModelProperty(value = "工作量合计11月")
    @Excel(name = "工作量合计11月")
    private BigDecimal realityTotal11;

    @ApiModelProperty(value = "工作量合计12月")
    @Excel(name = "工作量合计12月")
    private BigDecimal realityTotal12;
}
