package com.pcitc.szgt.contract.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

@ApiModel(value = "SysUserinfo",description ="用户基本信息表" )
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据主键")
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Integer fId;

    @ApiModelProperty(value = "用户编码/员工编号")
    @TableField("f_Code")
    private String fCode;

    @ApiModelProperty(value = "中文名")
    @TableField("f_Cname")
    private String fCname;

    @ApiModelProperty(value = "英文名")
    @TableField("f_Ename")
    private String fEname;

    @ApiModelProperty(value = "登录账户名称")
    @TableField("f_Account")
    private String fAccount;

    @ApiModelProperty(value = "职务")
    @TableField("f_Position")
    private String fPosition;

    @ApiModelProperty(value = "账户密码")
    @TableField("f_Password")
    private String fPassword;

    @ApiModelProperty(value = "是否被锁定",example = "0未锁定/1已锁定")
    @TableField("f_Is_Lock")
    private Integer fIsLock;

    @ApiModelProperty(value = "性别",example = "M男 / W女")
    @TableField("f_Sex")
    private String fSex;

    @ApiModelProperty(value = "生日")
    @TableField("f_Birthday")
    private LocalDate fBirthday;

    /**
     *	 年龄
     */
    @TableField("f_Age")
    private Integer fAge;

    /**
     * 	手机号
     */
    @TableField("f_Phone_Num")
    private String fPhoneNum;

    /**
     *	 座机号
     */
    @TableField("f_Land_Line")
    private String fLandLine;

    /**
     * 	公司邮箱
     */
    @TableField("f_Company_Email")
    private String fCompanyEmail;

    /**
     * 	邮箱
     */
    @TableField("f_Email")
    private String fEmail;

    /**
     * 	参加工作时间
     */
    @TableField("f_Job_Time")
    private LocalDate fJobTime;

    /**
     * 	身份证号
     */
    @TableField("f_ID_Card")
    private String fIdCard;

    /**
     *	 护照
     */
    @TableField("f_Pass_Port")
    private String fPassPort;

    /**
     * 	婚姻状况：1已婚/0未婚
     */
    @TableField("f_Marital")
    private String fMarital;

    /**
     * 	身高(cm)
     */
    @TableField("f_Height")
    private Integer fHeight;

    /**
     *	 籍贯
     */
    @TableField("f_Hometown")
    private String fHometown;

    /**
     * 	民族
     */
    @TableField("f_Nation")
    private String fNation;

    /**
     * 	在职状态
     */
    @TableField("f_Work_Status")
    private String fWorkStatus;

    /**
     * 	在职状态文本
     */
    @TableField("f_Work_Status_Text")
    private String fWorkStatusText;

    /**
     *	 民族文本
     */
    @TableField("f_Nation_Text")
    private String fNationText;

    /**
     * 	用工方式
     */
    @TableField("f_Employment_Way")
    private String fEmploymentWay;

    /**
     * 	用工方式文本
     */
    @TableField("f_Employment_Way_Text")
    private String fEmploymentWayText;

    /**
     * 	最高学历
     */
    @TableField("f_Education")
    private String fEducation;

    /**
     * 最高学历对应学校
     */
    @TableField("f_Graduated_School")
    private String fGraduatedSchool;

    /**
     * 学历对应专业
     */
    @TableField("f_Profession")
    private String fProfession;

    /**
     * 政治面貌
     */
    @TableField("f_PoliticalStatus")
    private String fPoliticalstatus;

    /**
     * 政治面貌文本
     */
    @TableField("f_PoliticalStatus_Text")
    private String fPoliticalstatusText;

    /**
     * 家庭住址
     */
    @TableField("f_Address")
    private String fAddress;

    /**
     * 用户描述
     */
    @TableField("f_Description")
    private String fDescription;

    /**
     * 启用状态：0启用/1未启用
     */
    @TableField("f_State")
    private Integer fState;

    /**
     * 排序字段
     */
    @TableField("f_Sort")
    private Integer fSort;

    /**
     * 类型：0普通员工，1待定
     */
    @TableField("f_Type")
    private Integer fType;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableField("f_IsDel")
    private Integer fIsdel;

    /**
     * 创建人账号
     */
    @TableField("f_Create_User")
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    @TableField("f_Create_Name")
    private String fCreateName;

    /**
     * 创建时间
     */
    @TableField("f_Create_Time")
    private LocalDateTime fCreateTime;

    /**
     * 修改人账号
     */
    @TableField("f_Update_User")
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    @TableField("f_Update_Name")
    private String fUpdateName;

    /**
     * 修改时间
     */
    @TableField("f_Update_Time")
    private LocalDateTime fUpdateTime;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }
    public String getfCname() {
        return fCname;
    }

    public void setfCname(String fCname) {
        this.fCname = fCname;
    }
    public String getfEname() {
        return fEname;
    }

    public void setfEname(String fEname) {
        this.fEname = fEname;
    }
    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount;
    }
    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
    }
    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }
    public Integer getfIsLock() {
        return fIsLock;
    }

    public void setfIsLock(Integer fIsLock) {
        this.fIsLock = fIsLock;
    }
    public String getfSex() {
        return fSex;
    }

    public void setfSex(String fSex) {
        this.fSex = fSex;
    }
    public LocalDate getfBirthday() {
        return fBirthday;
    }

    public void setfBirthday(LocalDate fBirthday) {
        this.fBirthday = fBirthday;
    }
    public Integer getfAge() {
        return fAge;
    }

    public void setfAge(Integer fAge) {
        this.fAge = fAge;
    }
    public String getfPhoneNum() {
        return fPhoneNum;
    }

    public void setfPhoneNum(String fPhoneNum) {
        this.fPhoneNum = fPhoneNum;
    }
    public String getfLandLine() {
        return fLandLine;
    }

    public void setfLandLine(String fLandLine) {
        this.fLandLine = fLandLine;
    }
    public String getfCompanyEmail() {
        return fCompanyEmail;
    }

    public void setfCompanyEmail(String fCompanyEmail) {
        this.fCompanyEmail = fCompanyEmail;
    }
    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }
    public LocalDate getfJobTime() {
        return fJobTime;
    }

    public void setfJobTime(LocalDate fJobTime) {
        this.fJobTime = fJobTime;
    }
    public String getfIdCard() {
        return fIdCard;
    }

    public void setfIdCard(String fIdCard) {
        this.fIdCard = fIdCard;
    }
    public String getfPassPort() {
        return fPassPort;
    }

    public void setfPassPort(String fPassPort) {
        this.fPassPort = fPassPort;
    }
    public String getfMarital() {
        return fMarital;
    }

    public void setfMarital(String fMarital) {
        this.fMarital = fMarital;
    }
    public Integer getfHeight() {
        return fHeight;
    }

    public void setfHeight(Integer fHeight) {
        this.fHeight = fHeight;
    }
    public String getfHometown() {
        return fHometown;
    }

    public void setfHometown(String fHometown) {
        this.fHometown = fHometown;
    }
    public String getfNation() {
        return fNation;
    }

    public void setfNation(String fNation) {
        this.fNation = fNation;
    }
    public String getfWorkStatus() {
        return fWorkStatus;
    }

    public void setfWorkStatus(String fWorkStatus) {
        this.fWorkStatus = fWorkStatus;
    }
    public String getfWorkStatusText() {
        return fWorkStatusText;
    }

    public void setfWorkStatusText(String fWorkStatusText) {
        this.fWorkStatusText = fWorkStatusText;
    }
    public String getfNationText() {
        return fNationText;
    }

    public void setfNationText(String fNationText) {
        this.fNationText = fNationText;
    }
    public String getfEmploymentWay() {
        return fEmploymentWay;
    }

    public void setfEmploymentWay(String fEmploymentWay) {
        this.fEmploymentWay = fEmploymentWay;
    }
    public String getfEmploymentWayText() {
        return fEmploymentWayText;
    }

    public void setfEmploymentWayText(String fEmploymentWayText) {
        this.fEmploymentWayText = fEmploymentWayText;
    }
    public String getfEducation() {
        return fEducation;
    }

    public void setfEducation(String fEducation) {
        this.fEducation = fEducation;
    }
    public String getfGraduatedSchool() {
        return fGraduatedSchool;
    }

    public void setfGraduatedSchool(String fGraduatedSchool) {
        this.fGraduatedSchool = fGraduatedSchool;
    }
    public String getfProfession() {
        return fProfession;
    }

    public void setfProfession(String fProfession) {
        this.fProfession = fProfession;
    }
    public String getfPoliticalstatus() {
        return fPoliticalstatus;
    }

    public void setfPoliticalstatus(String fPoliticalstatus) {
        this.fPoliticalstatus = fPoliticalstatus;
    }
    public String getfPoliticalstatusText() {
        return fPoliticalstatusText;
    }

    public void setfPoliticalstatusText(String fPoliticalstatusText) {
        this.fPoliticalstatusText = fPoliticalstatusText;
    }
    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }
    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
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
    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }
    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }
    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser;
    }
    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName;
    }
    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(LocalDateTime fCreateTime) {
        this.fCreateTime = fCreateTime;
    }
    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser;
    }
    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName;
    }
    public LocalDateTime getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(LocalDateTime fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    @Override
    public String toString() {
        return "SysUserinfo{" +
                "fId=" + fId +
                ", fCode=" + fCode +
                ", fCname=" + fCname +
                ", fEname=" + fEname +
                ", fAccount=" + fAccount +
                ", fPosition=" + fPosition +
                ", fPassword=" + fPassword +
                ", fIsLock=" + fIsLock +
                ", fSex=" + fSex +
                ", fBirthday=" + fBirthday +
                ", fAge=" + fAge +
                ", fPhoneNum=" + fPhoneNum +
                ", fLandLine=" + fLandLine +
                ", fCompanyEmail=" + fCompanyEmail +
                ", fEmail=" + fEmail +
                ", fJobTime=" + fJobTime +
                ", fIdCard=" + fIdCard +
                ", fPassPort=" + fPassPort +
                ", fMarital=" + fMarital +
                ", fHeight=" + fHeight +
                ", fHometown=" + fHometown +
                ", fNation=" + fNation +
                ", fWorkStatus=" + fWorkStatus +
                ", fWorkStatusText=" + fWorkStatusText +
                ", fNationText=" + fNationText +
                ", fEmploymentWay=" + fEmploymentWay +
                ", fEmploymentWayText=" + fEmploymentWayText +
                ", fEducation=" + fEducation +
                ", fGraduatedSchool=" + fGraduatedSchool +
                ", fProfession=" + fProfession +
                ", fPoliticalstatus=" + fPoliticalstatus +
                ", fPoliticalstatusText=" + fPoliticalstatusText +
                ", fAddress=" + fAddress +
                ", fDescription=" + fDescription +
                ", fState=" + fState +
                ", fSort=" + fSort +
                ", fType=" + fType +
                ", fIsdel=" + fIsdel +
                ", fCreateUser=" + fCreateUser +
                ", fCreateName=" + fCreateName +
                ", fCreateTime=" + fCreateTime +
                ", fUpdateUser=" + fUpdateUser +
                ", fUpdateName=" + fUpdateName +
                ", fUpdateTime=" + fUpdateTime +
                "}";
    }
}
