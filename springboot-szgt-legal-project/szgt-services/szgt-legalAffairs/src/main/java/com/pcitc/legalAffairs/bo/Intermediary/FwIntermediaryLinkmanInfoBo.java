package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

public class FwIntermediaryLinkmanInfoBo {
    /**
     * 中介机构联系人信息主键
     */
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 联系人名称
     */
    private String fLinkmanName;
    /**
     * 移动电话号码
     */
    private String fMobileNum;
    /**
     * 固定电话号码
     */
    private String fFixlineNum;
    /**
     * 联系电邮
     */
    private String fContactEmail;
    private String fCreateuser;
    private String fCreatename;
    private Date fCreatetime;
    private String fUpdateuser;
    private String fUpdatename;
    private Date fUpdatetime;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public String getfLinkmanName() {
        return fLinkmanName;
    }

    public void setfLinkmanName(String fLinkmanName) {
        this.fLinkmanName = fLinkmanName;
    }

    public String getfMobileNum() {
        return fMobileNum;
    }

    public void setfMobileNum(String fMobileNum) {
        this.fMobileNum = fMobileNum;
    }

    public String getfFixlineNum() {
        return fFixlineNum;
    }

    public void setfFixlineNum(String fFixlineNum) {
        this.fFixlineNum = fFixlineNum;
    }

    public String getfContactEmail() {
        return fContactEmail;
    }

    public void setfContactEmail(String fContactEmail) {
        this.fContactEmail = fContactEmail;
    }

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }
}
