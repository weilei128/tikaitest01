package com.oo.common.core.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.oo.common.core.annotation.DataTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long menuId;

    /** 菜单名称 */
    @DataTranslate(fieldId = "menuId", category = "menu")
    private String menuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

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
    private Integer type;

    /** check */
    private Integer check;

    /** 是否是重点国家项目 关联字典表 */
    private String if_focus;

    /** 所属组织机构 */
    private String organization_id;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();

    /** 翻译菜单名称数组 */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();

//    public Long getMenuId()
//    {
//        return menuId;
//    }
//
//    public void setMenuId(Long menuId)
//    {
//        this.menuId = menuId;
//    }

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    public String getMenuName()
    {
        return menuName;
    }

//    public void setMenuName(String menuName)
//    {
//        this.menuName = menuName;
//    }
//
//    public String getParentName()
//    {
//        return parentName;
//    }
//
//    public void setParentName(String parentName)
//    {
//        this.parentName = parentName;
//    }
//
//    public Long getParentId()
//    {
//        return parentId;
//    }
//
//    public void setParentId(Long parentId)
//    {
//        this.parentId = parentId;
//    }

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

//    public void setComponent(String component)
//    {
//        this.component = component;
//    }
//
//    public String getQuery()
//    {
//        return query;
//    }
//
//    public void setQuery(String query)
//    {
//        this.query = query;
//    }
//
//    public String getIsFrame()
//    {
//        return isFrame;
//    }
//
//    public void setIsFrame(String isFrame)
//    {
//        this.isFrame = isFrame;
//    }
//
//    public String getIsCache()
//    {
//        return isCache;
//    }
//
//    public void setIsCache(String isCache)
//    {
//        this.isCache = isCache;
//    }
//
//    @NotBlank(message = "菜单类型不能为空")
//    public String getMenuType()
//    {
//        return menuType;
//    }
//
//    public void setMenuType(String menuType)
//    {
//        this.menuType = menuType;
//    }
//
//    public String getVisible()
//    {
//        return visible;
//    }
//
//    public void setVisible(String visible)
//    {
//        this.visible = visible;
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

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

//    public void setPerms(String perms)
//    {
//        this.perms = perms;
//    }
//
//    public String getIcon()
//    {
//        return icon;
//    }
//
//    public void setIcon(String icon)
//    {
//        this.icon = icon;
//    }
//
//    public void setEffect(String effect)
//    {
//        this.effect = effect;
//    }
//
//    public String getEffect()
//    {
//        return effect;
//    }
//    public String getSite() {
//        return site;
//    }
//
//    public void setSite(String site) {
//        this.site = site;
//    }
//
//    public List<SysMenu> getChildren()
//    {
//        return children;
//    }
//
//    public void setChildren(List<SysMenu> children)
//    {
//        this.children = children;
//    }
//
//    public String getLabel_datail() {
//        return label_datail;
//    }
//
//    public void setLabel_datail(String label_datail) {
//        this.label_datail = label_datail;
//    }
//
//    public BigDecimal getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(BigDecimal longitude) {
//        this.longitude = longitude;
//    }
//
//    public BigDecimal getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(BigDecimal latitude) {
//        this.latitude = latitude;
//    }
//
//    public Date getCreate_time() {
//        return create_time;
//    }
//
//    public void setCreate_time(Date create_time) {
//        this.create_time = create_time;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    public Integer getCheck() {
//        return check;
//    }
//
//    public void setCheck(Integer check) {
//        this.check = check;
//    }
//
//    public String getOrganization_id() {
//        return organization_id;
//    }
//
//    public void setOrganization_id(String organization_id) {
//        this.organization_id = organization_id;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//                .append("menuId", getMenuId())
//                .append("menuName", getMenuName())
//                .append("parentId", getParentId())
//                .append("orderNum", getOrderNum())
//                .append("path", getPath())
//                .append("component", getComponent())
//                .append("isFrame", getIsFrame())
//                .append("IsCache", getIsCache())
//                .append("menuType", getMenuType())
//                .append("visible", getVisible())
//                .append("status ", getStatus())
//                .append("perms", getPerms())
//                .append("icon", getIcon())
//                .append("effect", getEffect())
//                .append("site", getSite())
//                .append("createBy", getCreateBy())
//                .append("createTime", getCreateTime())
//                .append("updateBy", getUpdateBy())
//                .append("updateTime", getUpdateTime())
//                .append("remark", getRemark())
//                .toString();
//    }
}
