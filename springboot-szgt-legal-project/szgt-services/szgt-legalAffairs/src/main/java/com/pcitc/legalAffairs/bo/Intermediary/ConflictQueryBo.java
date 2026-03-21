package com.pcitc.legalAffairs.bo.Intermediary;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConflictQueryBo extends BaseEntity {

	private String name;
	private String uscCode;
	private String type;
	private Byte status;
}
