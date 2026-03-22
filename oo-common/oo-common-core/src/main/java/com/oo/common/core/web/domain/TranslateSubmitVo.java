package com.oo.common.core.web.domain;

import lombok.Data;

import java.util.List;

@Data
public class TranslateSubmitVo {
    private static final long serialVersionUID = 1L;

    private String fieldId;

    private List<TranslateVO> voList;

}
