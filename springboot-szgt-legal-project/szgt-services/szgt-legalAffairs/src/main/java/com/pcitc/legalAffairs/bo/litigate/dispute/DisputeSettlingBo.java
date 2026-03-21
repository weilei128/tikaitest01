package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeSettlingBo {

    private Long disputeId;
    private String disputeName;
    private List<DisputeOursideFirmBo> oursideFirm;
    private List<DisputeOppositeFirmBo> oppositeFirm;
    private List<DisputeProgressBo> progress;

}