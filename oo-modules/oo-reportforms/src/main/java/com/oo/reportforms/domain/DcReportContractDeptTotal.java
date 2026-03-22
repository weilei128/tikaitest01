package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 工程技术分中心合同台账及执行情况记录-部门合同统计
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@TableName("dc_report_contract_dept_total")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "工程技术分中心合同台账及执行情况记录-部门合同统计对象", description = "工程技术分中心合同台账及执行情况记录-部门合同统计表")
public class DcReportContractDeptTotal extends LogicEntity {

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
    @Excel(name = "合同号")
    private String contractCode;
    
    /**
     * 合同内容
     */
    @ApiModelProperty(value = "合同内容")
    @Excel(name = "合同内容")
    private String contractContent;
    
    /**
     * 签订主体
     */
    @ApiModelProperty(value = "签订主体")
    @Excel(name = "签订主体")
    private String contractEntity;
    
    /**
     * 服务商
     */
    @ApiModelProperty(value = "服务商")
    @Excel(name = "服务商")
    private String serviceName;
    
    /**
     * 签订日期
     */
    @ApiModelProperty(value = "签订日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signedDate;
    
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;
    
    /**
     * 合同总额
     */
    @ApiModelProperty(value = "合同总额")
    @Excel(name = "合同总额")
    private BigDecimal contractTotalMoney;
    
    /**
     * 货币单位
     */
    @ApiModelProperty(value = "货币单位")
    @Excel(name = "货币单位")
    private String moneyUnit;
    
    /**
     * 付款方式
     */
    @ApiModelProperty(value = "付款方式")
    @Excel(name = "付款方式")
    private String paymentType;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;
}