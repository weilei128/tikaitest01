package com.pcitc.szgt.contract.make.modelEx;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NextApproverVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	NextApproverVo(){
		
	}

	private String id ;
	private String code ;
	private String name ;
	private String description;
	private String type;
	private String remark01;
	private String remark02;
	private String remark03;
	private String remark04;
	private String remark05;
	private String remark06;
	private String remark07;
	private String remark08;
	private String remark09;
	private List<Property> propertyList;
	private List<Participant> participantList;

	
	static class Property {
		
		public String propertyCode ;
		public String propertyValue ;
		public String getPropertyCode() {
			return propertyCode;
		}
		public void setPropertyCode(String propertyCode) {
			this.propertyCode = propertyCode;
		}
		public String getPropertyValue() {
			return propertyValue;
		}
		public void setPropertyValue(String propertyValue) {
			this.propertyValue = propertyValue;
		}
		
	}
	
	public static class Participant{
		
		public String participantId;
		public String participantType ;
		public String participantName;
		public String participantCode;
		public String participantValue;
		public String orderNo;
		public String organiseId;
		public String organiseName;
		public String unitId ;
		public String unitName ;
		public String enterpriseId ;
		public String enterpriseName ;
		public String category ;
		public String organisePath ;
		public String getParticipantId() {
			return participantId;
		}
		public void setParticipantId(String participantId) {
			this.participantId = participantId;
		}
		public String getParticipantType() {
			return participantType;
		}
		public void setParticipantType(String participantType) {
			this.participantType = participantType;
		}
		public String getParticipantName() {
			return participantName;
		}
		public void setParticipantName(String participantName) {
			this.participantName = participantName;
		}
		public String getParticipantCode() {
			return participantCode;
		}
		public void setParticipantCode(String participantCode) {
			this.participantCode = participantCode;
		}
		public String getParticipantValue() {
			return participantValue;
		}
		public void setParticipantValue(String participantValue) {
			this.participantValue = participantValue;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getOrganiseId() {
			return organiseId;
		}
		public void setOrganiseId(String organiseId) {
			this.organiseId = organiseId;
		}
		public String getOrganiseName() {
			return organiseName;
		}
		public void setOrganiseName(String organiseName) {
			this.organiseName = organiseName;
		}
		public String getUnitId() {
			return unitId;
		}
		public void setUnitId(String unitId) {
			this.unitId = unitId;
		}
		public String getUnitName() {
			return unitName;
		}
		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}
		public String getEnterpriseId() {
			return enterpriseId;
		}
		public void setEnterpriseId(String enterpriseId) {
			this.enterpriseId = enterpriseId;
		}
		public String getEnterpriseName() {
			return enterpriseName;
		}
		public void setEnterpriseName(String enterpriseName) {
			this.enterpriseName = enterpriseName;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getOrganisePath() {
			return organisePath;
		}
		public void setOrganisePath(String organisePath) {
			this.organisePath = organisePath;
		}
		
	}
	
}
