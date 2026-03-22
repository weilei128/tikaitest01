package com.oo.system.api.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.annotation.Excel.ColumnType;
import com.oo.common.core.annotation.Excel.Type;
import com.oo.common.core.annotation.Excels;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.xss.Xss;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户对象 sys_user
 * 
 * @author
 */
public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

  /*  *//** 创建人 *//*
    private String createBy;

    *//** 最后登录时间 *//*
    private Date createTime;

    *//** 修改人 *//*
    private String updateBy;

    *//** 修改时间 *//*
    private Date updateTime;*/

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String degreeMax;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workAddress;

    /** 当前国外派国家 */
    @Excel(name = "工作地点")
    private String expatriateCountry;

    /** 最高学位 */
    @Excel(name = "工作地点")
    private String graduateMax;

    /** 当前位置-国家，省市，三级联动 */
    @Excel(name = "国家省市")
    private String address;

    /** 当前位置-详细地址 */
    @Excel(name = "详细地址")
    private String addressDetails;

    /** 当前国家外派年限 */
    @Excel(name = "当前国家外派年限")
    private double expatriateYear;

    /** 职称 */
    @Excel(name = "职称")
    private String professional;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String draguateSchool;

    /** 负责人-直属上级 */
    private Long parentId;//关联

    /** 家庭详细住址 */
    @Excel(name = "毕业院校")
    private String familyAddress;

    /** KPI近三年考核结果 */
    @Excel(name = "KPI近三年考核结果")
    private String kpiResultRecent;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalLandscape;

    /** 中海油入职时间 */
    @Excel(name = "中海油入职时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onboardDateCn;

    /** 座机号 */
    @Excel(name = "座机号")
    private String landlineNumber;

    /** 工资级别 */
    @Excel(name = "工资级别")
    private String salaryScale;

    /** 出生日期 */
    @Excel(name = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /** 转正日期 */
    @Excel(name = "转正日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date positiveDate;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 累计外派时间 */
    @Excel(name = "累计外派时间")
    private BigDecimal outsiteDateSum;

    /** 入党时间 */
    @Excel(name = "入党时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date partyDate;

    /** 海油国际入职时间 */
    @Excel(name = "海油国际入职时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onboardDateWorld;

    /** 所有外派国家 */
    @Excel(name = "所有外派国家")
    private String outsiteCountryAll;

    /** 当前位置-经度 */
    @Excel(name = "经度")
    private BigDecimal localLongitude;

    /** 当前位置-纬度 */
    @Excel(name = "纬度")
    private BigDecimal localLatitude;

    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;
    private SysUserInfo userInfo;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 角色ID */
    private Long roleId;

    public SysUser()
    {

    }

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    @JsonProperty
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

   /* public String getcreateBy(){return createBy;}

    public void setcreateBy(String createBy){this.createBy = createBy;}

    public Date getcreateTime(){return createTime;}

    public void setcreateTime(Date createTime){this.createTime = createTime;}

    public String getupdateBy(){return updateBy;}

    public void setupdateBy(String updateBy){this.updateBy = updateBy;}

    public Date getupdateTime(){return updateTime;}

    public void setupdateTime(Date updateTime){this.updateTime = updateTime;}*/

    public String getDegreeMax(){return degreeMax;}

    public void setDegreeMax(String degreeMax){this.degreeMax = degreeMax;}

    public String getWorkAddress(){return workAddress;}

    public void setWorkAddress(String workAddress){this.workAddress = workAddress;}

    public String getExpatriateCountry(){return expatriateCountry;}

    public void setExpatriateCountry(String expatriateCountry){this.expatriateCountry = expatriateCountry;}

    public String getGraduateMax(){return graduateMax;}

    public void setGraduateMax(String graduateMax){this.graduateMax = graduateMax;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public String getAddressDetails(){return addressDetails;}

    public void setAddressDetails(String addressDetails){this.addressDetails = addressDetails;}

    public Double getExpatriateYear(){return expatriateYear;}

    public void setExpatriateYear(String expatriateYear){this.expatriateYear = Double.parseDouble(expatriateYear);}

    public String getProfessional(){return professional;}

    public void setProfessional(String professional){this.professional = professional;}

    public String getDraguateSchool(){return draguateSchool;}

    public void setDraguateSchool(String draguateSchool){this.draguateSchool = draguateSchool;}

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getFamilyAddress(){return familyAddress;}

    public void setFamilyAddress(String familyAddress){this.familyAddress = familyAddress;}

    public String getKpiResultRecent(){return kpiResultRecent;}

    public void setKpiResultRecent(String kpiResultRecent){this.kpiResultRecent = kpiResultRecent;}

    public String getPoliticalLandscape(){return politicalLandscape;}

    public void setPoliticalLandscape(String politicalLandscape){this.politicalLandscape = politicalLandscape;}

    public Date getOnboardDateCn(){return onboardDateCn;}

    public void setOnboardDateCn(Date onboardDateCn){this.onboardDateCn = onboardDateCn;}

    public String getLandlineNumber(){return landlineNumber;}

    public void setLandlineNumber(String landlineNumber){this.landlineNumber = landlineNumber;}

    public String getSalaryScale(){return salaryScale;}

    public void setSalaryScale(String salaryScale){this.salaryScale = salaryScale;}

    public Date getBirthDate(){return birthDate;}

    public void setBirthDate(Date birthDate){this.birthDate = birthDate;}

    public Date getPositiveDate(){return positiveDate;}

    public void setPositiveDate(Date positiveDate){this.positiveDate = positiveDate;}

    public BigDecimal getOutsiteDateSum(){return outsiteDateSum;}

    public void setOutsiteDateSum(BigDecimal outsiteDateSum){this.outsiteDateSum = outsiteDateSum;}

    public Date getPartyDate(){return partyDate;}

    public void setPartyDate(Date partyDate){this.partyDate = partyDate;}

    public Date getOnboardDateWorld(){return onboardDateWorld;}

    public void setOnboardDateWorld(Date onboardDateWorld){this.onboardDateWorld = onboardDateWorld;}

    public String getOutsiteCountryAll(){return outsiteCountryAll;}

    public void setOutsiteCountryAll(String outsiteCountryAll){this.outsiteCountryAll = outsiteCountryAll;}

    public BigDecimal getLocalLongitude(){return localLongitude;}

    public void setLocalLongitude(BigDecimal localLongitude){this.localLongitude = localLongitude;}

    public BigDecimal getLocalLatitude(){return localLatitude;}

    public void setLocalLatitude(BigDecimal localLatitude){this.localLatitude = localLatitude;}

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    public List<SysRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRole> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
                .append("degreeMax",getDegreeMax())
                .append("workAddress",getWorkAddress())
                .append("expatriateCountry",getExpatriateCountry())
                .append("graduateMax",getGraduateMax())
                .append("address",getAddress())
                .append("addressDetails",getAddressDetails())
                .append("expatriateYear",getExpatriateYear())
                .append("professional",getProfessional())
                .append("draguateSchool",getDraguateSchool())
                .append("parentId",getParentId())
                .append("familyAddress",getFamilyAddress())
                .append("kpiResultRecent",getKpiResultRecent())
                .append("politicalLandscape",getPoliticalLandscape())
                .append("onboardDateCn",getOnboardDateCn())
                .append("landlineNumber",getLandlineNumber())
                .append("salaryScale",getSalaryScale())
                .append("birthDate",getBirthDate())
                .append("positiveDate",getPositiveDate())
                .append("outsiteDateSum",getOutsiteDateSum())
                .append("partyDate",getPartyDate())
                .append("onboardDateWorld",getOnboardDateWorld())
                .append("outsiteCountryAll",getOutsiteCountryAll())
                .append("localLongitude",getLocalLongitude())
                .append("localLatitude",getLocalLatitude())
            .append("dept", getDept())
            .toString();
    }
}
