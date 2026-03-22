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
 * （技术管理岗）培训和技术交流情况更踪-王荣
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("dc_report_interact_trace")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "（技术管理岗）培训和技术交流情况更踪-王荣对象", description = "（技术管理岗）培训和技术交流情况更踪-王荣表")
public class DcReportInteractTrace extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 计划归属部门--关联部门表
     */
    @ApiModelProperty(value = "计划归属部门--关联部门表")
    // @Excel(name = "计划归属部门")
    private Long belongDeptPlan;
    
    /**
     * 培训项目名称
     */
    @ApiModelProperty(value = "培训项目名称")
    @Excel(name = "培训项目名称")
    private String trainName;
    
    /**
     * 培训总人数
     */
    @ApiModelProperty(value = "培训总人数")
    @Excel(name = "培训总人数")
    private Integer trainPeopleNum;
    
    /**
     * 培训目标
     */
    @ApiModelProperty(value = "培训目标")
    @Excel(name = "培训目标")
    private String trainTarget;
    
    /**
     * 培训内容概述
     */
    @ApiModelProperty(value = "培训内容概述")
    @Excel(name = "培训内容概述")
    private String trainContent;
    
    /**
     * 培训适用对象
     */
    @ApiModelProperty(value = "培训适用对象")
    @Excel(name = "培训适用对象")
    private String trainSubject;
    
    /**
     * 承办单位
     */
    @ApiModelProperty(value = "承办单位")
    @Excel(name = "承办单位")
    private String organizer;
    
    /**
     * 培训天数
     */
    @ApiModelProperty(value = "培训天数")
    @Excel(name = "培训天数")
    private BigDecimal trainDayNum;
    
    /**
     * 培训城市
     */
    @ApiModelProperty(value = "培训城市")
    @Excel(name = "培训城市")
    private String trainCity;
    
    /**
     * 拟培训时间
     */
    @ApiModelProperty(value = "拟培训时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拟培训时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainDateDrafted;
    
    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师")
    @Excel(name = "讲师")
    private String trainLecturer;
    
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    @Excel(name = "课程名称")
    private String courseName;
    
    /**
     * 实际培训时间
     */
    @ApiModelProperty(value = "实际培训时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际培训时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainDateReal;
    
    /**
     * 课时/h
     */
    @ApiModelProperty(value = "课时/h")
    @Excel(name = "课时/h")
    private BigDecimal classHour;
    
    /**
     * 参培人数
     */
    @ApiModelProperty(value = "参培人数")
    @Excel(name = "参培人数")
    private Integer participantsNum;
    
    /**
     * 实际参培人员
     */
    @ApiModelProperty(value = "实际参培人员")
    @Excel(name = "实际参培人员")
    private String participantsNumReal;
}