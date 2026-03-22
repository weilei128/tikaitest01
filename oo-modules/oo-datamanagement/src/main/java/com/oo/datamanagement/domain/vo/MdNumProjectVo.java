package com.oo.datamanagement.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MdNumProjectVo {
    public String companyId;
    public String companyName;
    public BigDecimal companyLongitude;
    public BigDecimal companyLatitude;
    public String iffocus;
    public BigDecimal projectNum;
    public BigDecimal wellNum;
}
