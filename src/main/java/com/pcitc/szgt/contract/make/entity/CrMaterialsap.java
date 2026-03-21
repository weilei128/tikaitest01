package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-21
 */
public class CrMaterialsap implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("materialGroup")
    private String materialGroup;

    @TableField("standCode")
    private String standCode;

    @TableField("newCode")
    private String newCode;

    @TableField("baseUnit")
    private String baseUnit;

    @TableField("materialDesc")
    private String materialDesc;

    @TableField("materialName")
    private String materialName;

    @TableField("signalmentByOne")
    private String signalmentByOne;

    @TableField("signalmentByTwo")
    private String signalmentByTwo;

    @TableField("signalmentByThere")
    private String signalmentByThere;

    @TableField("industryDesc")
    private String industryDesc;

    @TableField("industryArea")
    private String industryArea;

    @TableField("materialType")
    private String materialType;

    private String dept;

    @TableField("delTark")
    private String delTark;

    @TableField("lubriType")
    private String lubriType;

    @TableField("priceFactor")
    private String priceFactor;

    @TableField("unitReplace")
    private String unitReplace;

    private String transform;

    @TableField("englistText")
    private String englistText;

    @TableField("englistTextShort")
    private String englistTextShort;

    @TableField("useState")
    private Integer useState;

    @TableField("recordStatus")
    private Integer recordStatus;

    @TableField("addDate")
    private LocalDateTime addDate;

    private String memo;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("CategoryCode")
    private String CategoryCode;

    @TableField("IsFreeze")
    private Integer IsFreeze;

    @TableField("UnitPrice")
    private BigDecimal UnitPrice;

    @TableField("GoodsType")
    private String GoodsType;

    @TableField("Model")
    private String Model;

    @TableField("Specifications")
    private String Specifications;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }
    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }
    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }
    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }
    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getSignalmentByOne() {
        return signalmentByOne;
    }

    public void setSignalmentByOne(String signalmentByOne) {
        this.signalmentByOne = signalmentByOne;
    }
    public String getSignalmentByTwo() {
        return signalmentByTwo;
    }

    public void setSignalmentByTwo(String signalmentByTwo) {
        this.signalmentByTwo = signalmentByTwo;
    }
    public String getSignalmentByThere() {
        return signalmentByThere;
    }

    public void setSignalmentByThere(String signalmentByThere) {
        this.signalmentByThere = signalmentByThere;
    }
    public String getIndustryDesc() {
        return industryDesc;
    }

    public void setIndustryDesc(String industryDesc) {
        this.industryDesc = industryDesc;
    }
    public String getIndustryArea() {
        return industryArea;
    }

    public void setIndustryArea(String industryArea) {
        this.industryArea = industryArea;
    }
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getDelTark() {
        return delTark;
    }

    public void setDelTark(String delTark) {
        this.delTark = delTark;
    }
    public String getLubriType() {
        return lubriType;
    }

    public void setLubriType(String lubriType) {
        this.lubriType = lubriType;
    }
    public String getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(String priceFactor) {
        this.priceFactor = priceFactor;
    }
    public String getUnitReplace() {
        return unitReplace;
    }

    public void setUnitReplace(String unitReplace) {
        this.unitReplace = unitReplace;
    }
    public String getTransform() {
        return transform;
    }

    public void setTransform(String transform) {
        this.transform = transform;
    }
    public String getEnglistText() {
        return englistText;
    }

    public void setEnglistText(String englistText) {
        this.englistText = englistText;
    }
    public String getEnglistTextShort() {
        return englistTextShort;
    }

    public void setEnglistTextShort(String englistTextShort) {
        this.englistTextShort = englistTextShort;
    }
    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }
    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
    public String getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(String CategoryCode) {
        this.CategoryCode = CategoryCode;
    }
    public Integer getIsFreeze() {
        return IsFreeze;
    }

    public void setIsFreeze(Integer IsFreeze) {
        this.IsFreeze = IsFreeze;
    }
    public BigDecimal getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(BigDecimal UnitPrice) {
        this.UnitPrice = UnitPrice;
    }
    public String getGoodsType() {
        return GoodsType;
    }

    public void setGoodsType(String GoodsType) {
        this.GoodsType = GoodsType;
    }
    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }
    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String Specifications) {
        this.Specifications = Specifications;
    }

    @Override
    public String toString() {
        return "CrMaterialsap{" +
        "id=" + id +
        ", materialGroup=" + materialGroup +
        ", standCode=" + standCode +
        ", newCode=" + newCode +
        ", baseUnit=" + baseUnit +
        ", materialDesc=" + materialDesc +
        ", materialName=" + materialName +
        ", signalmentByOne=" + signalmentByOne +
        ", signalmentByTwo=" + signalmentByTwo +
        ", signalmentByThere=" + signalmentByThere +
        ", industryDesc=" + industryDesc +
        ", industryArea=" + industryArea +
        ", materialType=" + materialType +
        ", dept=" + dept +
        ", delTark=" + delTark +
        ", lubriType=" + lubriType +
        ", priceFactor=" + priceFactor +
        ", unitReplace=" + unitReplace +
        ", transform=" + transform +
        ", englistText=" + englistText +
        ", englistTextShort=" + englistTextShort +
        ", useState=" + useState +
        ", recordStatus=" + recordStatus +
        ", addDate=" + addDate +
        ", memo=" + memo +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", CategoryCode=" + CategoryCode +
        ", IsFreeze=" + IsFreeze +
        ", UnitPrice=" + UnitPrice +
        ", GoodsType=" + GoodsType +
        ", Model=" + Model +
        ", Specifications=" + Specifications +
        "}";
    }
}
