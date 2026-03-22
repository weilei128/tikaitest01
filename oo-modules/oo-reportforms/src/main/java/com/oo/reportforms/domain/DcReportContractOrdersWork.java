package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@TableName("dc_report_contract_orders_work")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）对象", description = "工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）表")
public class DcReportContractOrdersWork extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 合同主体
     */
    @ApiModelProperty(value = "合同主体")
    @Excel(name = "合同主体")
    private String contractContent;
    
    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    @Excel(name = "合同名称")
    private String contractName;
    
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @Excel(name = "订单编号")
    private String ordersNum;
    
    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    @Excel(name = "订单金额（$)")
    private BigDecimal ordersMoney;
    
    /**
     * 收费金额
     */
    @ApiModelProperty(value = "收费金额")
    @Excel(name = "收费金额（$)")
    private BigDecimal tollMoney;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;
}