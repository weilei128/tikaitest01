package com.oo.system.api.domain;

import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

    public class SysUserInfo extends BaseEntity
    {
        private static final long serialVersionUID = 1L;

        /** 用户ID */
        @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
        private Long userId;

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

        public SysUserInfo()
        {

        }

        public SysUserInfo(Long userId)
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

        public void setExpatriateYear(Double expatriateYear){this.expatriateYear = expatriateYear;}

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

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("userId", getUserId())
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
                    .toString();
        }
    }

