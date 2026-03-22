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
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@TableName("dc_report_contract_orders_service")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））对象", description = "工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））表")
public class DcReportContractOrdersService extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    @Excel(name = "合同号Contract No.")
    private String contractCode;
    
    /**
     * 合同主体
     */
    @ApiModelProperty(value = "合同主体")
    @Excel(name = "合同主体Contract Subject")
    private String contractEntity;
    
    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    @Excel(name = "合同名称\n" + "Contract Title")
    private String contractName;
    
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @Excel(name = "订单号")
    private String ordersNum;
    
    /**
     * 合同相对方
     */
    @ApiModelProperty(value = "合同相对方")
    @Excel(name = "合同相对方\n" + "Party")
    private String serviceName;
    
    /**
     * 合同金额
     */
    @ApiModelProperty(value = "合同金额")
    @Excel(name = "合同金额\n" + "Contract Value")
    private BigDecimal contractMoney;
    
    /**
     * 累计付款金额
     */
    @ApiModelProperty(value = "累计付款金额")
    @Excel(name = "累计付款金额Accruing Amounts")
    private BigDecimal paymentTotal;
    
    /**
     * 货币
     */
    @ApiModelProperty(value = "货币")
    @Excel(name = "货币\n" + "Currency Unit")
    private String currency;
    
    /**
     * 付款进度
     */
    @ApiModelProperty(value = "付款进度")
    @Excel(name = "付款进度Payment Schedule")
    private BigDecimal paymentProgress;
    
    /**
     * 付款节点
     */
    @ApiModelProperty(value = "付款节点")
    @Excel(name = "付款节点Payment Node")
    private String paymentNode;
    
    /**
     * 合同期限
     */
    @ApiModelProperty(value = "合同期限")
    @Excel(name = "合同期限\n" + "Contract Term")
    private String contractTerm;
    
    /**
     * 合同违约情况及处理结果
     */
    @ApiModelProperty(value = "合同违约情况及处理结果")
    @Excel(name = "合同违约情况及处理结果\n" + "Contract Breach & Restrictions Imposed")
    private String contractBreach;
    
    /**
     * 合同违规转分包情况
     */
    @ApiModelProperty(value = "合同违规转分包情况")
    @Excel(name = "合同违规转分包情况Contract illegal subcontracting")
    private String contractIllegal;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    // @Excel(name = "年份")
    private String year;
    /**
     * 年份
     */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注\n" + "Remark")
    private String remark;
}