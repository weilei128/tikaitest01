package com.pcitc.legalAffairs.bo.authorize;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizeFileTempQueryBo extends BaseEntity {

    private String name;
    private String templateType;
    private String authorizeProperty;
    private String authorizeType;
    private String publishDateBegin;
    private String publishDateEnd;
    private Integer isAvaliable;
}