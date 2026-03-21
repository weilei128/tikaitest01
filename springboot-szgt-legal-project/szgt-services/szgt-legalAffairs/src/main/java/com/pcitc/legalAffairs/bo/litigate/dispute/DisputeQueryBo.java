package com.pcitc.legalAffairs.bo.litigate.dispute;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisputeQueryBo extends BaseEntity {

	private String code;
	private String name;
	private Byte discarded;
	private Byte status;
}
