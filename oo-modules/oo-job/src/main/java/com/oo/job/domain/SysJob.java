package com.oo.job.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.annotation.Excel.ColumnType;
import com.oo.common.core.constant.ScheduleConstants;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.job.util.CronUtils;

/**
 * 定时任务调度表 sys_job
 * 
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysJob extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @ApiModelProperty(value = "任务ID")
    @Excel(name = "任务ID", cellType = ColumnType.NUMERIC)
    private Long jobId;

    /** 任务名称 */
    @ApiModelProperty(value = "任务名称")
    @Excel(name = "任务名称")
    private String jobName;

    /** 任务组名 */
    @ApiModelProperty(value = "任务组名")
    @Excel(name = "任务组名")
    private String jobGroup;

    /** 调用目标字符串 */
    @ApiModelProperty(value = "调用目标字符串")
    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    /** cron执行表达式 */
    @ApiModelProperty(value = "cron执行表达式")
    @Excel(name = "cron执行表达式 ")
    private String cronExpression;

    /** cron计划策略 */
    @ApiModelProperty(value = "cron计划策略")
    @Excel(name = "计划策略 ", readConverterExp = "0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 是否并发执行（0允许 1禁止） */
    @ApiModelProperty(value = "是否并发执行（0允许 1禁止）")
    @Excel(name = "并发执行", readConverterExp = "0=允许,1=禁止")
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    @ApiModelProperty(value = "任务状态（0正常 1暂停）")
    @Excel(name = "任务状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    /**
     * 任务开始时间
     */
    @ApiModelProperty(value = "任务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date jobStartTime;

    /**
     * 任务结束时间
     */
    @ApiModelProperty(value = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date jobEndTime;

    /**
     * 国家id
     */
    @ApiModelProperty(value = "国家id")
    @Excel(name = "国家id")
    private String countryId;

    /**
     * 报表类型
     */
    @ApiModelProperty(value = "报表类型")
    @Excel(name = "报表类型")
    private String reportType;

    /**
     * 解析模板id
     */
    @ApiModelProperty(value = "解析模板id")
    @Excel(name = "解析模板id")
    private String templateId;

    /**
     * 通知类型
     */
    @ApiModelProperty(value = "通知类型")
    @Excel(name = "通知类型")
    private String noticeType;

    /**
     * 通知用户ids
     */
    @ApiModelProperty(value = "通知用户ids")
    @Excel(name = "通知用户ids")
    private String noticeUserIds;

    public Long getJobId()
    {
        return jobId;
    }

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    @NotBlank(message = "任务名称不能为空")
    @Size(min = 0, max = 64, message = "任务名称不能超过64个字符")
    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    @NotBlank(message = "调用目标字符串不能为空")
    @Size(min = 0, max = 500, message = "调用目标字符串长度不能超过500个字符")
    public String getInvokeTarget()
    {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget)
    {
        this.invokeTarget = invokeTarget;
    }

    @NotBlank(message = "Cron执行表达式不能为空")
    @Size(min = 0, max = 255, message = "Cron执行表达式不能超过255个字符")
    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getNextValidTime()
    {
        if (StringUtils.isNotEmpty(cronExpression))
        {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }

//    public String getMisfirePolicy()
//    {
//        return misfirePolicy;
//    }
//
//    public void setMisfirePolicy(String misfirePolicy)
//    {
//        this.misfirePolicy = misfirePolicy;
//    }
//
//    public String getConcurrent()
//    {
//        return concurrent;
//    }
//
//    public void setConcurrent(String concurrent)
//    {
//        this.concurrent = concurrent;
//    }
//
//    public String getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
//
//    public Date getJobStartTime()
//    {
//        return jobStartTime;
//    }
//
//    public void setJobStartTime(Date jobStartTime)
//    {
//        this.jobStartTime = jobStartTime;
//    }
//
//    public Date getJobEndTime()
//    {
//        return jobEndTime;
//    }
//
//    public void setJobEndTime(Date jobEndTime)
//    {
//        this.jobEndTime = jobEndTime;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("jobId", getJobId())
//            .append("jobName", getJobName())
//            .append("jobGroup", getJobGroup())
//            .append("cronExpression", getCronExpression())
//            .append("nextValidTime", getNextValidTime())
//            .append("misfirePolicy", getMisfirePolicy())
//            .append("concurrent", getConcurrent())
//            .append("status", getStatus())
//            .append("createBy", getCreateBy())
//            .append("createTime", getCreateTime())
//            .append("updateBy", getUpdateBy())
//            .append("updateTime", getUpdateTime())
//            .append("remark", getRemark())
//            .append("jobStartTime", getRemark())
//            .append("jobEndTime", getRemark())
//            .toString();
//    }
}