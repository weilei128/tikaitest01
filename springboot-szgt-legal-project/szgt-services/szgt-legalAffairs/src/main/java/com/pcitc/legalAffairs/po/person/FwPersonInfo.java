package com.pcitc.legalAffairs.po.person;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 法律人员信息表
 */
@TableName(value = "fw_person_info")
public class FwPersonInfo {
    /**
     * 主键
     */
	@TableId(value="f_ID", type=IdType.AUTO)
    private Long fId;

    /**
     * 姓名
     */
    private String fName;

    /**
     * 所属组织
     */
    private Long fkOrgId;

    /**
     * 所属组织名称
     */
    private String fkOrgName;
    
    /**
     * 所属组织层级
     */
    private Integer fkOrgLevel;

    /**
     * 账号
     */
    private String fUsername;

    /**
     * 性别 0-女 1-男
     */
    private Byte fGender;

    /**
     * 国籍
     */
    private String fNationality;

    /**
     * 出生日期
     */
    private Date fBirthdate;

    /**
     * 职务
     */
    private Long fPosition;

    /**
     * 岗位
     */
    private String fPost;

    /**
     * 职级
     */
    private Long fRank;

    /**
     * 职称级别
     */
    private Long fTitleLvl;

    /**
     * 毕业院校
     */
    private String fGraduatedSchool;

    /**
     * 毕业时间
     */
    private Date fGraduateDate;

    /**
     * 最高学历
     */
    private Long fEducation;

    /**
     * 最高学位
     */
    private Long fAcademicDegree;

    /**
     * 是否有全日制法律教学背景 0-否 1-是
     */
    private Byte fIsFullTimeLawEducated;

    /**
     * 所学专业
     */
    private String fMajor;

    /**
     * 参加工作时间
     */
    private Date fJoinJobDate;

    /**
     * 从事法律工作时间
     */
    private Date fJoinLawJobDate;

    /**
     * 政治面貌
     */
    private Long fPoliticalStatus;

    /**
     * 电子邮件
     */
    private String fEmail;

    /**
     * 办公电话
     */
    private String fOfficePhone;

    /**
     * 手机
     */
    private String fMobilePhone;

    /**
     * 是否分管领导 0-否 1-是
     */
    private Byte fIsInChargeLeader;

    /**
     * 是否总法律顾问 0-否 1-是
     */
    private Byte fIsGeneralAdvisor;

    /**
     * 任总法律顾问时间 f_Is_General_Advisor=0时应无视该字段
     */
    private Date fGaAssumeDate;

    /**
     * 是否专职法律人员 0-否 1-是 f_Is_General_Advisor=0时应无视该字段
     */
    private Byte fIsFullTimeGa;

    /**
     * 是否为副总法律顾问 0-否 1-是
     */
    private Byte fIsSubGeneralAdvisor;

    /**
     * 副总法律顾问任职时间
     */
    private Date fSubGaAssumeDate;

    /**
     * 是否为法律机构主要负责人 0-否 1-是
     */
    private Byte fIsMainOfLegalAgency;

    /**
     * 法律机构主要负责人任职时间
     */
    private Date fMainOfLegalAgencyAssumeDate;

    /**
     * 是否为法律机构分管法律工作负责人 0-否 1-是
     */
    private Byte fIsInChargeLegalAgency;

    /**
     * 除法律外其他分管业务
     */
    private String fBussinessExceptLegal;

    /**
     * 是否普法联络员 0-否 1-是
     */
    private Byte fIsSpreadLiaison;

    /**
     * 是否合同管理员
     */
    private Byte fIsContractManager;

    /**
     * 是否在法律岗位 0-否 1-是
     */
    private Byte fIsOnLegalPosition;

    /**
     * 是否专职法律工作人员
     */
    private Byte fIsFullTimeLegal;
    
    /**
     * 是否公司律师
     */
    private Byte fIsCompanyLawyer;
    
    /**
     * 在职状态
     */
    private String fWorkingStatus;

    /**
     * 合同管理员编号
     */
    private String fContractManagerCode;

    /**
     * 任命文件ID
     */
    private Long fkAppointFileId;

    /**
     * 任命文件名
     */
    private String fkAppointFileName;

    /**
     * 任命文件路径
     */
    private String fkAppointFileServerPath;

    /**
     * 任命文件扩展名
     */
    private String fkAppointFileExt;

    /**
     * 任命文件文号
     */
    private String fAppointmentSymbol;
    /**
     * 是否法律从业人员 0否 1是
     */
    private Byte fIsLegalPractitioner;

    /**
     * 专业领域
     */
    private String fAreaOfExpertise;

    /**
     * 代表性业绩
     */
    private String fPerformance;
    
    /**
     * 类型 0-草稿 1-保存
     */
    private Integer fType;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableLogic
    private Integer fIsdel;

    private Long fCreateId;

    /**
     * 创建人账号
     */
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    private String fCreateName;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    private Long fUpdateId;

    /**
     * 修改人账号
     */
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    private String fUpdateName;

    /**
     * 修改时间
     */
    private Date fUpdateTime;

    /**
     * 法律资格证书 0.是、1.否、2.无
     */
    private Integer fIsQualification;


    public Integer getfIsQualification() {
        return fIsQualification;
    }

    public void setfIsQualification(Integer fIsQualification) {
        this.fIsQualification = fIsQualification;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Long getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(Long fkOrgId) {
        this.fkOrgId = fkOrgId;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getfUsername() {
        return fUsername;
    }

    public void setfUsername(String fUsername) {
        this.fUsername = fUsername;
    }

    public Byte getfGender() {
        return fGender;
    }

    public void setfGender(Byte fGender) {
        this.fGender = fGender;
    }

    public String getfNationality() {
        return fNationality;
    }

    public void setfNationality(String fNationality) {
        this.fNationality = fNationality;
    }

    public Date getfBirthdate() {
        return fBirthdate;
    }

    public void setfBirthdate(Date fBirthdate) {
        this.fBirthdate = fBirthdate;
    }

    public Long getfPosition() {
        return fPosition;
    }

    public void setfPosition(Long fPosition) {
        this.fPosition = fPosition;
    }

    public String getfPost() {
        return fPost;
    }

    public void setfPost(String fPost) {
        this.fPost = fPost;
    }

    public Long getfRank() {
        return fRank;
    }

    public void setfRank(Long fRank) {
        this.fRank = fRank;
    }

    public Long getfTitleLvl() {
        return fTitleLvl;
    }

    public void setfTitleLvl(Long fTitleLvl) {
        this.fTitleLvl = fTitleLvl;
    }

    public String getfGraduatedSchool() {
        return fGraduatedSchool;
    }

    public void setfGraduatedSchool(String fGraduatedSchool) {
        this.fGraduatedSchool = fGraduatedSchool;
    }

    public Date getfGraduateDate() {
        return fGraduateDate;
    }

    public void setfGraduateDate(Date fGraduateDate) {
        this.fGraduateDate = fGraduateDate;
    }

    public Long getfEducation() {
        return fEducation;
    }

    public void setfEducation(Long fEducation) {
        this.fEducation = fEducation;
    }

    public Long getfAcademicDegree() {
        return fAcademicDegree;
    }

    public void setfAcademicDegree(Long fAcademicDegree) {
        this.fAcademicDegree = fAcademicDegree;
    }

    public Byte getfIsFullTimeLawEducated() {
        return fIsFullTimeLawEducated;
    }

    public void setfIsFullTimeLawEducated(Byte fIsFullTimeLawEducated) {
        this.fIsFullTimeLawEducated = fIsFullTimeLawEducated;
    }

    public String getfMajor() {
        return fMajor;
    }

    public void setfMajor(String fMajor) {
        this.fMajor = fMajor;
    }

    public Date getfJoinJobDate() {
        return fJoinJobDate;
    }

    public void setfJoinJobDate(Date fJoinJobDate) {
        this.fJoinJobDate = fJoinJobDate;
    }

    public Date getfJoinLawJobDate() {
        return fJoinLawJobDate;
    }

    public void setfJoinLawJobDate(Date fJoinLawJobDate) {
        this.fJoinLawJobDate = fJoinLawJobDate;
    }

    public Long getfPoliticalStatus() {
        return fPoliticalStatus;
    }

    public void setfPoliticalStatus(Long fPoliticalStatus) {
        this.fPoliticalStatus = fPoliticalStatus;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfOfficePhone() {
        return fOfficePhone;
    }

    public void setfOfficePhone(String fOfficePhone) {
        this.fOfficePhone = fOfficePhone;
    }

    public String getfMobilePhone() {
        return fMobilePhone;
    }

    public void setfMobilePhone(String fMobilePhone) {
        this.fMobilePhone = fMobilePhone;
    }

    public Byte getfIsInChargeLeader() {
        return fIsInChargeLeader;
    }

    public void setfIsInChargeLeader(Byte fIsInChargeLeader) {
        this.fIsInChargeLeader = fIsInChargeLeader;
    }

    public Byte getfIsGeneralAdvisor() {
        return fIsGeneralAdvisor;
    }

    public void setfIsGeneralAdvisor(Byte fIsGeneralAdvisor) {
        this.fIsGeneralAdvisor = fIsGeneralAdvisor;
    }

    public Date getfGaAssumeDate() {
        return fGaAssumeDate;
    }

    public void setfGaAssumeDate(Date fGaAssumeDate) {
        this.fGaAssumeDate = fGaAssumeDate;
    }

    public Byte getfIsFullTimeGa() {
        return fIsFullTimeGa;
    }

    public void setfIsFullTimeGa(Byte fIsFullTimeGa) {
        this.fIsFullTimeGa = fIsFullTimeGa;
    }

    public Byte getfIsSubGeneralAdvisor() {
        return fIsSubGeneralAdvisor;
    }

    public void setfIsSubGeneralAdvisor(Byte fIsSubGeneralAdvisor) {
        this.fIsSubGeneralAdvisor = fIsSubGeneralAdvisor;
    }

    public Date getfSubGaAssumeDate() {
        return fSubGaAssumeDate;
    }

    public void setfSubGaAssumeDate(Date fSubGaAssumeDate) {
        this.fSubGaAssumeDate = fSubGaAssumeDate;
    }

    public Byte getfIsMainOfLegalAgency() {
        return fIsMainOfLegalAgency;
    }

    public void setfIsMainOfLegalAgency(Byte fIsMainOfLegalAgency) {
        this.fIsMainOfLegalAgency = fIsMainOfLegalAgency;
    }

    public Date getfMainOfLegalAgencyAssumeDate() {
        return fMainOfLegalAgencyAssumeDate;
    }

    public void setfMainOfLegalAgencyAssumeDate(Date fMainOfLegalAgencyAssumeDate) {
        this.fMainOfLegalAgencyAssumeDate = fMainOfLegalAgencyAssumeDate;
    }

    public Byte getfIsInChargeLegalAgency() {
        return fIsInChargeLegalAgency;
    }

    public void setfIsInChargeLegalAgency(Byte fIsInChargeLegalAgency) {
        this.fIsInChargeLegalAgency = fIsInChargeLegalAgency;
    }

    public String getfBussinessExceptLegal() {
        return fBussinessExceptLegal;
    }

    public void setfBussinessExceptLegal(String fBussinessExceptLegal) {
        this.fBussinessExceptLegal = fBussinessExceptLegal;
    }

    public Byte getfIsSpreadLiaison() {
        return fIsSpreadLiaison;
    }

    public void setfIsSpreadLiaison(Byte fIsSpreadLiaison) {
        this.fIsSpreadLiaison = fIsSpreadLiaison;
    }

    public Byte getfIsContractManager() {
        return fIsContractManager;
    }

    public void setfIsContractManager(Byte fIsContractManager) {
        this.fIsContractManager = fIsContractManager;
    }

    public Byte getfIsOnLegalPosition() {
        return fIsOnLegalPosition;
    }

    public void setfIsOnLegalPosition(Byte fIsOnLegalPosition) {
        this.fIsOnLegalPosition = fIsOnLegalPosition;
    }

    public Byte getfIsFullTimeLegal() {
        return fIsFullTimeLegal;
    }

    public void setfIsFullTimeLegal(Byte fIsFullTimeLegal) {
        this.fIsFullTimeLegal = fIsFullTimeLegal;
    }

    public String getfContractManagerCode() {
        return fContractManagerCode;
    }

    public void setfContractManagerCode(String fContractManagerCode) {
        this.fContractManagerCode = fContractManagerCode;
    }

    public Long getFkAppointFileId() {
        return fkAppointFileId;
    }

    public void setFkAppointFileId(Long fkAppointFileId) {
        this.fkAppointFileId = fkAppointFileId;
    }

    public String getFkAppointFileName() {
        return fkAppointFileName;
    }

    public void setFkAppointFileName(String fkAppointFileName) {
        this.fkAppointFileName = fkAppointFileName;
    }

    public String getFkAppointFileServerPath() {
        return fkAppointFileServerPath;
    }

    public void setFkAppointFileServerPath(String fkAppointFileServerPath) {
        this.fkAppointFileServerPath = fkAppointFileServerPath;
    }

    public String getFkAppointFileExt() {
        return fkAppointFileExt;
    }

    public void setFkAppointFileExt(String fkAppointFileExt) {
        this.fkAppointFileExt = fkAppointFileExt;
    }

    public String getfAppointmentSymbol() {
        return fAppointmentSymbol;
    }

    public void setfAppointmentSymbol(String fAppointmentSymbol) {
        this.fAppointmentSymbol = fAppointmentSymbol;
    }

	public Byte getfIsLegalPractitioner() {
		return fIsLegalPractitioner;
	}

	public void setfIsLegalPractitioner(Byte fIsLegalPractitioner) {
		this.fIsLegalPractitioner = fIsLegalPractitioner;
	}

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public Long getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Long fCreateId) {
        this.fCreateId = fCreateId;
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

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Long getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Long fUpdateId) {
        this.fUpdateId = fUpdateId;
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

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

	public Integer getFkOrgLevel() {
		return fkOrgLevel;
	}

	public void setFkOrgLevel(Integer fkOrgLevel) {
		this.fkOrgLevel = fkOrgLevel;
	}

	public String getfAreaOfExpertise() {
		return fAreaOfExpertise;
	}

	public void setfAreaOfExpertise(String fAreaOfExpertise) {
		this.fAreaOfExpertise = fAreaOfExpertise;
	}

	public String getfPerformance() {
		return fPerformance;
	}

	public void setfPerformance(String fPerformance) {
		this.fPerformance = fPerformance;
	}

	public Byte getfIsCompanyLawyer() {
		return fIsCompanyLawyer;
	}

	public void setfIsCompanyLawyer(Byte fIsCompanyLawyer) {
		this.fIsCompanyLawyer = fIsCompanyLawyer;
	}

	public String getfWorkingStatus() {
		return fWorkingStatus;
	}

	public void setfWorkingStatus(String fWorkingStatus) {
		this.fWorkingStatus = fWorkingStatus;
	}

}