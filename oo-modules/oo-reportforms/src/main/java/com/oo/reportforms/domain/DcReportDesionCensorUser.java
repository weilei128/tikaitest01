package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单
 *
 * @author oo
 * @date 2023-08-11
 */
@Data
@TableName("dc_report_desion_censor_user")
@ApiModel(value = "（技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单对象", description = "（技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单表")
public class DcReportDesionCensorUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 前期研究id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "前期研究id")
    private String censorId;
    
    /**
     * 专家名称
     */
    @ApiModelProperty(value = "专家名称")
    @Excel(name = "专家名称")
    private String expertId;
}