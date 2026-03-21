package com.pcitc.legalAffairs.bo.person;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonQueryBo extends BaseEntity {

	private String fname;
    private Byte fisLegalPractitioner;
    private Byte ftype;
	private Byte fisInChargeLeader;
	private Byte fIsMainOfLegalAgency;
    private Byte fIsFullTimeLegal;
    private String fQulificationName;
    private Byte fIsGeneralAdvisor;
    private String fWorkingStatus;
    /**
     * 是否公司律师
     */
    private Byte fIsCompanyLawyer;

    /**
     * 组织结构层级
     */
    private Integer orgLevel;
    private Long orgId;
    
}
