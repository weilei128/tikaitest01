package com.pcitc.legalAffairs.bo.person;

import java.util.List;

import lombok.Data;

@Data
public class PersonInfoBo {

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
	private String fBirthdate;

	/**
	 * 职务
	 */
	private Long fPosition;
	
	/**
	 * 职务
	 */
	private String fPositionName;

	/**
	 * 岗位
	 */
	private String fPost;

	/**
	 * 职级
	 */
	private Long fRank;
	
	/**
	 * 职级
	 */
	private String fRankName;

	/**
	 * 职称级别
	 */
	private Long fTitleLvl;
	
	/**
	 * 职称级别
	 */
	private String fTitleLvlName;

	/**
	 * 毕业院校
	 */
	private String fGraduatedSchool;

	/**
	 * 毕业时间
	 */
	private String fGraduateDate;

	/**
	 * 最高学历
	 */
	private Long fEducation;
	
	/**
	 * 最高学历
	 */
	private String fEducationName;

	/**
	 * 最高学位
	 */
	private Long fAcademicDegree;
	/**
	 * 最高学位
	 */
	private String fAcademicDegreeName;

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
	private String fJoinJobDate;

	/**
	 * 从事法律工作时间
	 */
	private String fJoinLawJobDate;

	/**
	 * 政治面貌
	 */
	private Long fPoliticalStatus;
	
	/**
	 * 政治面貌
	 */
	private String fPoliticalStatusName;

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
	private String fGaAssumeDate;

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
	private String fSubGaAssumeDate;

	/**
	 * 是否为法律机构主要负责人 0-否 1-是
	 */
	private Byte fIsMainOfLegalAgency;

	/**
	 * 法律机构主要负责人任职时间
	 */
	private String fMainOfLegalAgencyAssumeDate;

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
	 * 法律资格证书 0.是、1.否、2.无
	 */
	private Integer fisQualification;

	private String fisQualificationName;



//	/**
//	 * 是否删除 1：删除，0：未删除
//	 */
//	private Integer fIsdel;
//
//	private Long fCreateId;
//
//	/**
//	 * 创建人账号
//	 */
//	private String fCreateUser;
//
//	/**
//	 * 创建人姓名
//	 */
//	private String fCreateName;
//
//	/**
//	 * 创建时间
//	 */
//	private Date fCreateTime;
//
//	private Long fUpdateId;
//
//	/**
//	 * 修改人账号
//	 */
//	private String fUpdateUser;
//
//	/**
//	 * 修改人姓名
//	 */
//	private String fUpdateName;
//
//	/**
//	 * 修改时间
//	 */
//	private Date fUpdateTime;
	
	private List<PersonHonorBo> honors;
	
	private List<PersonQualificationBo> qualifications;

	private List<PersonResumeBo> resumes;

}
