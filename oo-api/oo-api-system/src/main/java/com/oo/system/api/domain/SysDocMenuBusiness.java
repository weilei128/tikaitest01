package com.oo.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DataTranslate;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.web.domain.TranslateVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@TableName("sys_doc_menu_business")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "业务域文件类型菜单表", description = "业务域文件类型菜单表-用于维护文件类型的层级关系  例如：技术》技术》技术管理》前期研究与设计审查》井位建议书")
public class SysDocMenuBusiness extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 业务域文件类型ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "业务域文件类型ID")
    private Long id;

    /**
     * 业务域文件类型名称
     */
    @ApiModelProperty(value = "业务域文件类型名称")
    @Excel(name = "业务域文件类型名称")
    @DataTranslate(fieldId = "id", category = "docMenuBusiness")
    private String businessName;

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
    private int orderNum;

    /**
     * wdp文件类型是否选中(0:未选中，1:选中)
     */
    @ApiModelProperty(value = "wdp文件类型是否选中")
    @Excel(name = "wdp文件类型是否选中")
    private int isCheck;

    /**
     * 子集
     */
    @TableField(exist = false)
    private List<SysDocMenuBusiness> children = new ArrayList<>();

    public void addChild(SysDocMenuBusiness child) {
        this.children.add(child);
    }

    /**
     * 翻译菜单名称数组
     */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();
}
