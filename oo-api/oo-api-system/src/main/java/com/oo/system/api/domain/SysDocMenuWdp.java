package com.oo.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DataTranslate;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.TranslateVO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
 *
 * @author oo
 * @date 2023-07-21
 */
@Data
@TableName("sys_doc_menu_wdp")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WDP文件类型菜单", description = "WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书表")
public class SysDocMenuWdp extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * wdp文件类型ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "wdp文件类型ID")
    private Long id;
    
    /**
     * wdp文件类型名称
     */
    @ApiModelProperty(value = "wdp文件类型名称")
    @Excel(name = "wdp文件类型名称")
    @DataTranslate(fieldId = "id", category = "docMenuWdp")
    private String wdpName;
    
    /**
     * 父级文件类型id
     */
    @ApiModelProperty(value = "父级文件类型id")
    @Excel(name = "父级文件类型id")
    private Long parentId;
    
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Excel(name = "排序号")
    private Integer orderNum;

    /** 子菜单 */
    @TableField(exist = false)
    private List<SysDocMenuWdp> children = new ArrayList<SysDocMenuWdp>();

//    public List<SysDocMenuWdp> getChildren()
//    {
//        return children;
//    }
//
//    public void setChildren(List<SysDocMenuWdp> children)
//    {
//        this.children = children;
//    }

    /**
     * 翻译菜单名称数组
     */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();
}