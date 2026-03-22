package com.oo.reportforms.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class GroupByYearVo {
    private List<DcMdOrganizationVo> list;

    /** 年份*/
    private String year;
}