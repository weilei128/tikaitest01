package com.pcitc.szgt.contract.offeree.model;

public class MdmQueryVo {

    private String OffereeName;


    private String OffereeCode;

    private Integer offereeSource;//相对人来源

    private String sourceType;  //内部:INSYS_INUNIT  外部:INSYS_OUTUNIT

    private Integer Category;

    private Integer pageNum;

    private Integer pageSize;

    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String offereeName) {
        OffereeName = offereeName;
    }

    public String getOffereeCode() {
        return OffereeCode;
    }

    public void setOffereeCode(String offereeCode) {
        OffereeCode = offereeCode;
    }

    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer category) {
        Category = category;
    }
    public void setOffereeSource(Integer offereeSource) {
        this.offereeSource = offereeSource;
    }
    public Integer getOffereeSource() {
        return offereeSource;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
