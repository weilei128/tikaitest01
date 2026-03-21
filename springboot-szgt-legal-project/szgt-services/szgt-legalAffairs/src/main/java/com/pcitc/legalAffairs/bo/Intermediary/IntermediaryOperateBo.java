package com.pcitc.legalAffairs.bo.Intermediary;

import lombok.Data;

@Data
public class IntermediaryOperateBo {

    private Long id;
    private String uscCode;
    private Long operatorId;
    private String operatorName;
    private String cause;
}