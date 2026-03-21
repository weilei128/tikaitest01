package com.pcitc.szgt.contract.offeree.model;

import org.springframework.util.StringUtils;

public class OffereeQueryVo {

    private String offereename;

    private String offereecode;

    private String offereesort;

    private String registerAddr;

    private Integer isEnable;

    private String creditCode;
    private Integer blong;//相对人归属

    private Integer unusualPerform;//异常履约情况
    private Integer status;//状态
    private Integer offCase;//发案情况
    private Integer isValid;//证照时效
    private Integer dataSource;//数据来源
    private Integer isBlack;//是否黑名单
    private Integer pageNum;
    private Integer pageSize;

    /**
     * 是否要查询自然人
     *
     * @return
     */
    public Boolean isQueryPerson() {
        if (StringUtils.isEmpty(offereecode) &&
                StringUtils.isEmpty(offereesort) &&
                StringUtils.isEmpty(registerAddr) &&
                StringUtils.isEmpty(creditCode)) {
            return true;
        }

        return false;
    }

    public String getOffereename() {
        return offereename;
    }

    public void setOffereename(String offereename) {
        this.offereename = offereename;
    }

    public String getOffereecode() {
        return offereecode;
    }

    public void setOffereecode(String offereecode) {
        this.offereecode = offereecode;
    }

    public String getOffereesort() {
        return offereesort;
    }

    public void setOffereesort(String offereesort) {
        this.offereesort = offereesort;
    }

    public String getRegisterAddr() {
        return registerAddr;
    }

    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
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

    public Integer getBlong() {
        return blong;
    }

    public Integer getUnusualPerform() {
        return unusualPerform;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getOffCase() {
        return offCase;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setBlong(Integer blong) {
        this.blong = blong;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setOffCase(Integer offCase) {
        this.offCase = offCase;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public void setUnusualPerform(Integer unusualPerform) {
        this.unusualPerform = unusualPerform;
    }


}
