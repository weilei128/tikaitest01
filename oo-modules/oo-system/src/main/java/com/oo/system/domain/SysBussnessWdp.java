package com.oo.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 业务域与WDP权限关系映射（通过最后一级做映射）
 *
 * @author oo
 * @date 2023-07-21
 */
@Data
@TableName("sys_bussness_wdp")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "业务域与WDP权限关系映射（通过最后一级做映射）对象", description = "业务域与WDP权限关系映射（通过最后一级做映射）表")
public class SysBussnessWdp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private Long id;

    /**
     * 业务域文件类型ID
     */
    @ApiModelProperty(value = "业务域文件类型ID")
    @Excel(name = "业务域文件类型ID")
    private Long bussnessId;

    /**
     * wdp文件类型ID
     */
    @ApiModelProperty(value = "wdp文件类型ID")
    @Excel(name = "wdp文件类型ID")
    private Long wdpId;
}