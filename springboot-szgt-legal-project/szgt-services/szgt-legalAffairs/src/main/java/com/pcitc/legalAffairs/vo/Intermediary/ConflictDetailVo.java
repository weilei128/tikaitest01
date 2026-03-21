package com.pcitc.legalAffairs.vo.Intermediary;

import lombok.Data;

@Data
public class ConflictDetailVo {
	/**
	 * 案件名称
	 */
    private String fkDisputeName;
    /**
     * 经办人
     */
    private String manager;
    
    private String ourSide;
    
    private String opposite;
    
    private String firm;

}
