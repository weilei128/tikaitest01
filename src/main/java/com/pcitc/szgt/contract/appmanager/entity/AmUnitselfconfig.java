package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-28
 */
public class AmUnitselfconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("OrgConfigID")
    private String OrgConfigID;

    @TableField("ItemID")
    private Integer ItemID;

    @TableField("IsEnabled")
    private Integer IsEnabled;

    @TableField("Oulabel")
    private Integer Oulabel;

    public String getOrgConfigID() {
        return OrgConfigID;
    }

    public void setOrgConfigID(String OrgConfigID) {
        this.OrgConfigID = OrgConfigID;
    }
    public Integer getItemID() {
        return ItemID;
    }

    public void setItemID(Integer ItemID) {
        this.ItemID = ItemID;
    }
    public Integer getIsEnabled() {
        return IsEnabled;
    }

    public void setIsEnabled(Integer IsEnabled) {
        this.IsEnabled = IsEnabled;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    @Override
    public String toString() {
        return "AmUnitselfconfig{" +
        "OrgConfigID=" + OrgConfigID +
        ", ItemID=" + ItemID +
        ", IsEnabled=" + IsEnabled +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
