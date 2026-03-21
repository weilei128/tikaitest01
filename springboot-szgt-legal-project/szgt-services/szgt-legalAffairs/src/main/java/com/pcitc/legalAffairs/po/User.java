package com.pcitc.legalAffairs.po;

//@TableName(value = "t_user")
public class User  {
    //    @TableId(value = "f_Id", type = IdType.AUTO)
    private Integer fId;

    private Integer fkImageId;

    private Integer fkCustomerId;

    private String fUserId;

    private String fPhone;

    private String fDepartment;

    private String fPosition;

    private String fUserMail;

    private String fCompanyName;

    private String fUsername;

    private String fPassword;

    private Integer fUserType;

    private String fUserImage;

    private String fUserTypeDes;

    private String fInvitationCode;

    private String fVerificationCode;

    private Integer fIsAgree;

    private Integer fParentid;

    public Integer getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(Integer fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getFkImageId() {
        return fkImageId;
    }

    public void setFkImageId(Integer fkImageId) {
        this.fkImageId = fkImageId;
    }

    public String getfUserId() {
        return fUserId;
    }

    public void setfUserId(String fUserId) {
        this.fUserId = fUserId == null ? null : fUserId.trim();
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone == null ? null : fPhone.trim();
    }

    public String getfDepartment() {
        return fDepartment;
    }

    public void setfDepartment(String fDepartment) {
        this.fDepartment = fDepartment == null ? null : fDepartment.trim();
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition == null ? null : fPosition.trim();
    }

    public String getfUserMail() {
        return fUserMail;
    }

    public void setfUserMail(String fUserMail) {
        this.fUserMail = fUserMail == null ? null : fUserMail.trim();
    }

    public String getfCompanyName() {
        return fCompanyName;
    }

    public void setfCompanyName(String fCompanyName) {
        this.fCompanyName = fCompanyName == null ? null : fCompanyName.trim();
    }

    public String getfUsername() {
        return fUsername;
    }

    public void setfUsername(String fUsername) {
        this.fUsername = fUsername == null ? null : fUsername.trim();
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword == null ? null : fPassword.trim();
    }

    public Integer getfUserType() {
        return fUserType;
    }

    public void setfUserType(Integer fUserType) {
        this.fUserType = fUserType;
    }

    public String getfUserImage() {
        return fUserImage;
    }

    public void setfUserImage(String fUserImage) {
        this.fUserImage = fUserImage == null ? null : fUserImage.trim();
    }

    public String getfUserTypeDes() {
        return fUserTypeDes;
    }

    public void setfUserTypeDes(String fUserTypeDes) {
        this.fUserTypeDes = fUserTypeDes == null ? null : fUserTypeDes.trim();
    }

    public String getfInvitationCode() {
        return fInvitationCode;
    }

    public void setfInvitationCode(String fInvitationCode) {
        this.fInvitationCode = fInvitationCode == null ? null : fInvitationCode.trim();
    }

    public String getfVerificationCode() {
        return fVerificationCode;
    }

    public void setfVerificationCode(String fVerificationCode) {
        this.fVerificationCode = fVerificationCode == null ? null : fVerificationCode.trim();
    }

    public Integer getfIsAgree() {
        return fIsAgree;
    }

    public void setfIsAgree(Integer fIsAgree) {
        this.fIsAgree = fIsAgree;
    }


    public Integer getfParentid() {
        return fParentid;
    }

    public void setfParentid(Integer fParentid) {
        this.fParentid = fParentid;
    }
}