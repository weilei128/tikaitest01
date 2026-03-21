package com.pcitc.legalAffairs.po;


import com.pcitc.szgt.legalAffairs.base.BasePojo;

//@TableName(value = "t_client")
public class Client extends BasePojo {

//    @TableId(value = "f_Id", type = IdType.AUTO)
    private Integer fId;

//    private Integer fIsdel;

//    private String fCreateuser;
//
//    private String fCreatename;
//
//    private Date fCreatetime;
//
//    private String fUpdateuser;
//
//    private Date fUpdatetime;
//
//    private String fUpdatename;

    private Integer fType;

    private Integer fState;

    private Integer fSort;

    private Integer fClientAcctokenSeconds;

    private Integer fClientReftokenSeconds;

    private String fClientId;

    private String fClientDes;

    private String fClientSecret;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

//    public Integer getfIsdel() {
//        return fIsdel;
//    }
//
//    public void setfIsdel(Integer fIsdel) {
//        this.fIsdel = fIsdel;
//    }

//    public String getfCreateuser() {
//        return fCreateuser;
//    }
//
//    public void setfCreateuser(String fCreateuser) {
//        this.fCreateuser = fCreateuser == null ? null : fCreateuser.trim();
//    }
//
//    public String getfCreatename() {
//        return fCreatename;
//    }
//
//    public void setfCreatename(String fCreatename) {
//        this.fCreatename = fCreatename == null ? null : fCreatename.trim();
//    }
//
//    public Date getfCreatetime() {
//        return fCreatetime;
//    }
//
//    public void setfCreatetime(Date fCreatetime) {
//        this.fCreatetime = fCreatetime;
//    }
//
//    public String getfUpdateuser() {
//        return fUpdateuser;
//    }
//
//    public void setfUpdateuser(String fUpdateuser) {
//        this.fUpdateuser = fUpdateuser == null ? null : fUpdateuser.trim();
//    }
//
//    public Date getfUpdatetime() {
//        return fUpdatetime;
//    }
//
//    public void setfUpdatetime(Date fUpdatetime) {
//        this.fUpdatetime = fUpdatetime;
//    }
//
//    public String getfUpdatename() {
//        return fUpdatename;
//    }
//
//    public void setfUpdatename(String fUpdatename) {
//        this.fUpdatename = fUpdatename == null ? null : fUpdatename.trim();
//    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfClientAcctokenSeconds() {
        return fClientAcctokenSeconds;
    }

    public void setfClientAcctokenSeconds(Integer fClientAcctokenSeconds) {
        this.fClientAcctokenSeconds = fClientAcctokenSeconds;
    }

    public Integer getfClientReftokenSeconds() {
        return fClientReftokenSeconds;
    }

    public void setfClientReftokenSeconds(Integer fClientReftokenSeconds) {
        this.fClientReftokenSeconds = fClientReftokenSeconds;
    }

    public String getfClientId() {
        return fClientId;
    }

    public void setfClientId(String fClientId) {
        this.fClientId = fClientId == null ? null : fClientId.trim();
    }

    public String getfClientDes() {
        return fClientDes;
    }

    public void setfClientDes(String fClientDes) {
        this.fClientDes = fClientDes == null ? null : fClientDes.trim();
    }

    public String getfClientSecret() {
        return fClientSecret;
    }

    public void setfClientSecret(String fClientSecret) {
        this.fClientSecret = fClientSecret == null ? null : fClientSecret.trim();
    }
}