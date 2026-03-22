package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("report_desion_censor_user_view")
@EqualsAndHashCode(callSuper = true)
public class DcReportDesionCensorUserView extends DcReportDesionCensor {

    private String censorId;

    @Excel(name = "审查会时间")
    private String censonDate;
    /**
     * 专家名单
     */
    @ApiModelProperty(value = "专家名单")
    @Excel(name = "专家名单")
    private String censorName;

}
