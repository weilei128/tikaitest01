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
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("dc_report_desion_censor")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "（技术管理岗）前期研究及设计审查会汇总-设计审查汇总对象", description = "（技术管理岗）前期研究及设计审查会汇总-设计审查汇总表")
public class DcReportDesionCensor extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一ID")
    private String id;
    
    /**
     * 前期研究及设计审查名称
     */
    @ApiModelProperty(value = "前期研究及设计审查名称")
    @Excel(name = "前期研究及设计审查名称")
    private String designCensorName;
    
    /**
     * 审查会开始时间 如 2023/2/3
     */
    @ApiModelProperty(value = "审查会开始时间 如 2023/2/3")
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "审查会开始时间 如 2023/2/3", width = 30, dateFormat = "yyyy-MM-dd")
    private Date censonBeginDate;
    
    /**
     * 审查会结束时间  如 2023/2/5
     */
    @ApiModelProperty(value = "审查会结束时间  如 2023/2/5")
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "审查会结束时间  如 2023/2/5", width = 30, dateFormat = "yyyy-MM-dd")
    private Date censonEndDate;
    
    /**
     * 技术关键字
     */
    @ApiModelProperty(value = "技术关键词")
    @Excel(name = "技术关键词")
    private String keywords;
    
    /**
     * 专家人数
     */
    @ApiModelProperty(value = "专家人数")
    @Excel(name = "专家人数")
    private Integer expertNum;
    
    /**
     * 专家意见
     */
    @ApiModelProperty(value = "专家意见")
    @Excel(name = "专家意见")
    private String expertOpinion;
    
    /**
     * 井数/口
     */
    @ApiModelProperty(value = "井数/口")
    @Excel(name = "井数/口")
    private Integer wellCount;
    
    /**
     * 水深/m
     */
    @ApiModelProperty(value = "水深/m")
    @Excel(name = "水深/m")
    private BigDecimal waterDepth;
    
    /**
     * 节省费用/万美元
     */
    @ApiModelProperty(value = "节约费用/万美元")
    @Excel(name = "节约费用/万美元")
    private BigDecimal economizeCost;
    
    /**
     * 累计节省费用/万美元
     */
    @ApiModelProperty(value = "累计节约费用/万美元")
    @Excel(name = "累计节约费用/万美元")
    private BigDecimal economizeCostSum;
}