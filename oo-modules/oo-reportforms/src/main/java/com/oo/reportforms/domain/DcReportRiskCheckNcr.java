package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
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
 * 隐患排查：NCR
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@TableName("dc_report_risk_check_ncr")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "隐患排查：NCR对象", description = "隐患排查：NCR表")
public class DcReportRiskCheckNcr extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 井控检查
     */
    @ApiModelProperty(value = "井控检查")
    @Excel(name = "Well control inspection/井控检查")
    private String wellControlInspection;
    
    /**
     * 检查类型 1：pre-spud inspection 2：inspection before reservoir
     */
    @ApiModelProperty(value = "检查类型 1：pre-spud inspection 2：inspection before reservoir")
    @Excel(name = "Resources Type/检查类型")
    @DictField(dictType = "resource_type")
    private String resourceType;
    
    /**
     * 检查单位 1：LR7005 2：LR7006  3：LR7001
     */
    @ApiModelProperty(value = "检查单位 1：LR7005 2：LR7006  3：LR7001")
    @Excel(name = "Facilities/检查单位")
    @DictField(dictType = "check_facilities")
    private String facilities;
    
    /**
     * 井号id
     */
    @ApiModelProperty(value = "井号id")
   // @Excel(name = "井号id")
    private String wellId;
    
    /**
     * 风险等级 1：重大 2：较大 3：一般
     */
    @ApiModelProperty(value = "风险等级 1：重大 2：较大 3：一般")
    @Excel(name = "Risk  Criticality Ranking/风险等级")
    @DictField(dictType = "risk_criticality_ranking")
    private String riskCriticalityRanking;
    
    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Registering Date/检查日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registeringDate;
    
    /**
     * 不符合项描述
     */
    @ApiModelProperty(value = "不符合项描述")
    @Excel(name = "NC Description/不符合项描述")
    private String ncDescription;
    
    /**
     * 不符合项整改
     */
    @ApiModelProperty(value = "不符合项整改")
    @Excel(name = "NC Corrective Action/不符合项整改")
    private String ncCorrectiveAction;
    
    /**
     * 要求关闭时间
     */
    @ApiModelProperty(value = "要求关闭时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Required close time/要求关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requiredCloseTime;
    
    /**
     * 实际关闭时间
     */
    @ApiModelProperty(value = "实际关闭时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Actrual close time /实际关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actrualCloseTime;
    
    /**
     * 不符合项目前状态 1：open 2:close
     */
    @ApiModelProperty(value = "不符合项目前状态 1：open 2:close")
    @Excel(name = "NC Status/不符合项目前状态")
    @DictField(dictType = "nc_status")
    private String ncStatus;
    
    /**
     * 未关闭项原因说明
     */
    @ApiModelProperty(value = "未关闭项原因说明")
    @Excel(name = "Reasons for open items/未关闭项原因说明")
    private String reasonsForOpenItems;
    
    /**
     * 未关闭项风险管控措施
     */
    @ApiModelProperty(value = "未关闭项风险管控措施")
    @Excel(name = "Risk control measures  \n" + "for open items/未关闭项风险管控措施")
    private String riskControlMeasures;
    
    /**
     * 计划关闭时间
     */
    @ApiModelProperty(value = "计划关闭时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Planned close time for open items/计划关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planCloseTime;
    
    /**
     * 整改负责人
     */
    @ApiModelProperty(value = "整改负责人")
    @Excel(name = "Responsible person/整改负责人")
    private String responsiblePerson;
}