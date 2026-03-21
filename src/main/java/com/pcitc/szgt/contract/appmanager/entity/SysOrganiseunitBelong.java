package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-28
 */
public class SysOrganiseunitBelong implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("OUID")
    private Integer ouid;

    @TableField("InGroup")
    private Integer InGroup;

    @TableField("GroupCanSign")
    private Integer GroupCanSign;

    @TableField("GroupAliasName")
    private String GroupAliasName;

    @TableField("GroupLegalPerson")
    private String GroupLegalPerson;

    @TableField("GroupWatermark")
    private String GroupWatermark;

    @TableField("GroupSinopecCode")
    private String GroupSinopecCode;

    @TableField("GroupPayTo")
    private String GroupPayTo;

    @TableField("InStock")
    private Integer InStock;

    @TableField("StockCanSign")
    private Integer StockCanSign;

    @TableField("StockAliasName")
    private String StockAliasName;

    @TableField("StockLegalPerson")
    private String StockLegalPerson;

    @TableField("StockWatermark")
    private String StockWatermark;

    @TableField("StockSinopecCode")
    private String StockSinopecCode;

    @TableField("StockPayTo")
    private String StockPayTo;

    @TableField("InAsset")
    private Integer InAsset;

    @TableField("AssetCanSign")
    private Integer AssetCanSign;

    @TableField("AssetAliasName")
    private String AssetAliasName;

    @TableField("AssetLegalPerson")
    private String AssetLegalPerson;

    @TableField("AssetWatermark")
    private String AssetWatermark;

    @TableField("AssetSinopecCode")
    private String AssetSinopecCode;

    @TableField("AssetPayTo")
    private String AssetPayTo;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Oulabel")
    private Integer Oulabel;

    public Integer getOuid() {
        return ouid;
    }

    public void setOuid(Integer ouid) {
        this.ouid = ouid;
    }
    public Integer getInGroup() {
        return InGroup;
    }

    public void setInGroup(Integer InGroup) {
        this.InGroup = InGroup;
    }
    public Integer getGroupCanSign() {
        return GroupCanSign;
    }

    public void setGroupCanSign(Integer GroupCanSign) {
        this.GroupCanSign = GroupCanSign;
    }
    public String getGroupAliasName() {
        return GroupAliasName;
    }

    public void setGroupAliasName(String GroupAliasName) {
        this.GroupAliasName = GroupAliasName;
    }
    public String getGroupLegalPerson() {
        return GroupLegalPerson;
    }

    public void setGroupLegalPerson(String GroupLegalPerson) {
        this.GroupLegalPerson = GroupLegalPerson;
    }
    public String getGroupWatermark() {
        return GroupWatermark;
    }

    public void setGroupWatermark(String GroupWatermark) {
        this.GroupWatermark = GroupWatermark;
    }
    public String getGroupSinopecCode() {
        return GroupSinopecCode;
    }

    public void setGroupSinopecCode(String GroupSinopecCode) {
        this.GroupSinopecCode = GroupSinopecCode;
    }
    public String getGroupPayTo() {
        return GroupPayTo;
    }

    public void setGroupPayTo(String GroupPayTo) {
        this.GroupPayTo = GroupPayTo;
    }
    public Integer getInStock() {
        return InStock;
    }

    public void setInStock(Integer InStock) {
        this.InStock = InStock;
    }
    public Integer getStockCanSign() {
        return StockCanSign;
    }

    public void setStockCanSign(Integer StockCanSign) {
        this.StockCanSign = StockCanSign;
    }
    public String getStockAliasName() {
        return StockAliasName;
    }

    public void setStockAliasName(String StockAliasName) {
        this.StockAliasName = StockAliasName;
    }
    public String getStockLegalPerson() {
        return StockLegalPerson;
    }

    public void setStockLegalPerson(String StockLegalPerson) {
        this.StockLegalPerson = StockLegalPerson;
    }
    public String getStockWatermark() {
        return StockWatermark;
    }

    public void setStockWatermark(String StockWatermark) {
        this.StockWatermark = StockWatermark;
    }
    public String getStockSinopecCode() {
        return StockSinopecCode;
    }

    public void setStockSinopecCode(String StockSinopecCode) {
        this.StockSinopecCode = StockSinopecCode;
    }
    public String getStockPayTo() {
        return StockPayTo;
    }

    public void setStockPayTo(String StockPayTo) {
        this.StockPayTo = StockPayTo;
    }
    public Integer getInAsset() {
        return InAsset;
    }

    public void setInAsset(Integer InAsset) {
        this.InAsset = InAsset;
    }
    public Integer getAssetCanSign() {
        return AssetCanSign;
    }

    public void setAssetCanSign(Integer AssetCanSign) {
        this.AssetCanSign = AssetCanSign;
    }
    public String getAssetAliasName() {
        return AssetAliasName;
    }

    public void setAssetAliasName(String AssetAliasName) {
        this.AssetAliasName = AssetAliasName;
    }
    public String getAssetLegalPerson() {
        return AssetLegalPerson;
    }

    public void setAssetLegalPerson(String AssetLegalPerson) {
        this.AssetLegalPerson = AssetLegalPerson;
    }
    public String getAssetWatermark() {
        return AssetWatermark;
    }

    public void setAssetWatermark(String AssetWatermark) {
        this.AssetWatermark = AssetWatermark;
    }
    public String getAssetSinopecCode() {
        return AssetSinopecCode;
    }

    public void setAssetSinopecCode(String AssetSinopecCode) {
        this.AssetSinopecCode = AssetSinopecCode;
    }
    public String getAssetPayTo() {
        return AssetPayTo;
    }

    public void setAssetPayTo(String AssetPayTo) {
        this.AssetPayTo = AssetPayTo;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    @Override
    public String toString() {
        return "SysOrganiseunitBelong{" +
        "ouid=" + ouid +
        ", InGroup=" + InGroup +
        ", GroupCanSign=" + GroupCanSign +
        ", GroupAliasName=" + GroupAliasName +
        ", GroupLegalPerson=" + GroupLegalPerson +
        ", GroupWatermark=" + GroupWatermark +
        ", GroupSinopecCode=" + GroupSinopecCode +
        ", GroupPayTo=" + GroupPayTo +
        ", InStock=" + InStock +
        ", StockCanSign=" + StockCanSign +
        ", StockAliasName=" + StockAliasName +
        ", StockLegalPerson=" + StockLegalPerson +
        ", StockWatermark=" + StockWatermark +
        ", StockSinopecCode=" + StockSinopecCode +
        ", StockPayTo=" + StockPayTo +
        ", InAsset=" + InAsset +
        ", AssetCanSign=" + AssetCanSign +
        ", AssetAliasName=" + AssetAliasName +
        ", AssetLegalPerson=" + AssetLegalPerson +
        ", AssetWatermark=" + AssetWatermark +
        ", AssetSinopecCode=" + AssetSinopecCode +
        ", AssetPayTo=" + AssetPayTo +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
