package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeLawFirmBo {

    private List<DisputeOursideFirmBo> oursideFirm;
    private List<DisputeOppositeFirmBo> oppositeFirm;

}