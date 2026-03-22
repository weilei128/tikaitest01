package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcRportWellCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcRportWellCheckVo extends DcRportWellCheck {

    @Excel(name = "")
    private String serialNumber;

    @Excel(name = "井名")
    private String wellName;

    @Excel(name = "国家")
    private String organizationName;
}
