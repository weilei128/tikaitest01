package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * （技术管理岗）十四五重大科研课题审查统计0620-林志强
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("dc_report_scientific_censor_total")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "重大科研课题审查统计", description = "重大科研课题审查统计")
public class DcReportScientificCensorTotal extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一id")
    private String id;
    
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date startDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date endDate;
    
    /**
     * 地点
     */
    @ApiModelProperty(value = "地点")
    @Excel(name = "地点")
    private String address;
    
    /**
     * 参加人员
     */
    @ApiModelProperty(value = "参加人员")
    @Excel(name = "参加人员（钻完井）")
    private String participants;
    
    /**
     * 主要议题
     */
    @ApiModelProperty(value = "主要议题")
    @Excel(name = "主要议题")
    private String lssuesMain;
    
    /**
     * 类型 1:顶层设计  2:立项论证 3:预算审查 4:预算批准及任务书签订 5:项目启动及外委采办 6:课题管理
     */
    @ApiModelProperty(value = "类型 1:顶层设计  2:立项论证 3:预算审查 4:预算批准及任务书签订 5:项目启动及外委采办 6:课题管理")
    private Integer type;
}