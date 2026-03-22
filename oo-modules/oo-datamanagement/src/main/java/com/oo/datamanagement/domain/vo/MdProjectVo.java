package com.oo.datamanagement.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MdProjectVo {
    public String companyId;
    public String companyName;//国家名称
    public String projectId;
    public String projectName;//项目名称
    public String blockId;
    public String blockName;//区块名称
    public String type;
    public String wellId;
    public String wellName;//单井名称
    public String chineseWellName;
    public String ifWork;
    public String iffocus;
    public Date prodDate;
    public BigDecimal longitude;
    public BigDecimal latitude;
}
