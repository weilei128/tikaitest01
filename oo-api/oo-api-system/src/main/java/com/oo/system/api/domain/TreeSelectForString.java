package com.oo.system.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;
import com.oo.system.api.vo.DcMdWellVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeSelectForString树结构实体类
 *
 * @author ruoyi
 */
public class TreeSelectForString implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private String id;
    private String wellId;

    /** 节点名称 */
    private String label;

    /** 全称*/
    private String label_datail;

    /** 经度 */
    private BigDecimal longitude;

    /** 纬度 */
    private BigDecimal latitude;

    /** 父节点ID */
    private String parent_id;
    private String well_parent_id;

    /** 父节点ID */
    private Integer order_num;

    /** 创建时间 */
    private Date create_time;

    /** type */
    private String type;

    /** check */
    private Integer check;

    /** check */
    private String remark;

    /** 是否是重点国家项目 关联字典表 */
    private String if_focus;

    /** 所属组织机构 */
    private String organization_id;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectForString> children;

    public TreeSelectForString()
    {

    }

    public TreeSelectForString(SysDept dept)
    {
        this.id = dept.getDeptId().toString();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }

    public TreeSelectForString(SysMenu menu)
    {
        this.id = menu.getMenuId().toString();
        this.label = menu.getMenuName();
        this.parent_id = menu.getParentId().toString();
        this.order_num = menu.getOrderNum();
        this.label_datail=menu.getLabel_datail();
        this.latitude=menu.getLatitude();
        this.longitude=menu.getLongitude();
        this.create_time=menu.getCreate_time();
        this.remark = menu.getRemark();
        this.type=menu.getType().toString();
        this.check=menu.getCheck();
        this.organization_id=menu.getOrganization_id();
        this.if_focus=menu.getIf_focus();
        this.children = menu.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }

    public TreeSelectForString(SysDocMenuWdp menuWdp)
    {
        this.id = menuWdp.getId().toString();
        this.label = menuWdp.getWdpName();
        this.parent_id = menuWdp.getParentId().toString();
        this.order_num = menuWdp.getOrderNum();
        this.create_time=menuWdp.getCreateTime();
        this.remark=menuWdp.getRemark();

        this.children = menuWdp.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }

    public TreeSelectForString(SysDocMenuBusiness menuWdp)
    {
        this.id = menuWdp.getId().toString();
        this.label = menuWdp.getBusinessName();
        this.parent_id = menuWdp.getParentId().toString();
        this.order_num = menuWdp.getOrderNum();
        this.create_time=menuWdp.getCreateTime();
        this.remark=menuWdp.getRemark();
        this.check = menuWdp.getIsCheck();

        this.children = menuWdp.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }

    public TreeSelectForString(DcMdWellVo menuWdp)
    {
        this.wellId = menuWdp.getId();
        this.label = menuWdp.getName();
        this.well_parent_id = menuWdp.getParentId();
        this.order_num = menuWdp.getOrderNum();
        this.type = menuWdp.getType();

        this.children = menuWdp.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }

    public TreeSelectForString(SysMenuWell sysMenuWell) {
        this.wellId = sysMenuWell.getMenuId();
        this.well_parent_id = sysMenuWell.getParentId();

        this.id = sysMenuWell.getMenuId();
        this.label = sysMenuWell.getMenuName();
        this.parent_id = sysMenuWell.getParentId();
        this.order_num = sysMenuWell.getOrderNum();
        this.label_datail=sysMenuWell.getLabel_datail();
        this.latitude=sysMenuWell.getLatitude();
        this.longitude=sysMenuWell.getLongitude();
        this.create_time=sysMenuWell.getCreate_time();
        this.remark = sysMenuWell.getRemark();
        this.type=sysMenuWell.getType();
        this.check=sysMenuWell.getCheck();
        this.organization_id=sysMenuWell.getOrganization_id();
        this.if_focus=sysMenuWell.getIf_focus();
        this.children = sysMenuWell.getChildren().stream().map(TreeSelectForString::new).collect(Collectors.toList());
    }
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getWellId() {return wellId;}

    public void setWellId(String wellId){this.wellId = wellId;}

    public String getWell_Parent_id() {
        return well_parent_id;
    }

    public void setWell_Parent_id(String well_parent_id) {
        this.well_parent_id = well_parent_id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }


    public String getLabel_datail() {
        return label_datail;
    }

    public void setLabel_datail(String label_datail) {
        this.label_datail = label_datail;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public List<TreeSelectForString> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelectForString> children)
    {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIf_focus() {
        return if_focus;
    }

    public void setIf_focus(String if_focus) {
        this.if_focus = if_focus;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }
}
