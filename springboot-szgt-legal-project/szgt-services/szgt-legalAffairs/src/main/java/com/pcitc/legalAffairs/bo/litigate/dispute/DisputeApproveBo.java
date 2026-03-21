package com.pcitc.legalAffairs.bo.litigate.dispute;

import lombok.Data;

@Data
public class DisputeApproveBo {

	private Long id;
	private Byte approve;
	private String cause;
}
