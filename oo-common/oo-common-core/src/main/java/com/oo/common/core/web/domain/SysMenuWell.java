package com.oo.common.core.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.oo.common.core.annotation.DataTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuWell extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private String menuId;

    /** 菜单名称 */
    @DataTranslate(fieldId = "menuId", category = "menu")
    private String menuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private String parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 路由地址 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 路由参数 */
    private String query;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 是否缓存（0缓存 1不缓存） */
    private String isCache;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    private String visible;

    /** 菜单状态（0显示 1隐藏） */
    private String status;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 有效范围(0后端 1应用) */
    private String effect;

    /** 站点 */
    private String site;

    /** 全称*/
    private String label_datail;

    /** 经度 */
    private BigDecimal longitude;

    /** 纬度 */
    private BigDecimal latitude;

    /** 创建时间 */
    private Date create_time;

    /** type */
    private String type;

    /** check */
    private Integer check;

    /** 是否是重点国家项目 关联字典表 */
    private String if_focus;

    /** 所属组织机构 */
    private String organization_id;

    /** 子菜单 */
    private List<SysMenuWell> children = new ArrayList<SysMenuWell>();

    /** 翻译菜单名称数组 */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    public String getMenuName()
    {
        return menuName;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    public String getComponent()
    {
        return component;
    }

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

}
