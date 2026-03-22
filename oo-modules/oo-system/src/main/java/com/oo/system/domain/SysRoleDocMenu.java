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
 * 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型
 *
 * @author oo
 * @date 2023-07-21
 */
@Data
@TableName("sys_role_doc_menu")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型对象", description = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型表")
public class SysRoleDocMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 角色ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    
    /**
     * 业务域文件类型ID
     */

    @ApiModelProperty(value = "业务域文件类型ID")
    private Long businessId;

    /**
     * 操作编号 1：上传； 2：下载；3：预览；4：分享；5：编辑；6：删除
     */

    @ApiModelProperty(value = "操作编号")
    private int operatetype;
}