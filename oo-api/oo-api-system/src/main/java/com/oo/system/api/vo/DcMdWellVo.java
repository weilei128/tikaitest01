package com.oo.system.api.vo;

import com.oo.system.api.domain.DcMdOrganization;
import lombok.Data;

@Data
public class DcMdWellVo extends DcMdOrganization {
    private String projectId;//项目id
    private String projectName;//项目简称
    private String wellId;//组织id
    private String wellName;//组织简称
}
