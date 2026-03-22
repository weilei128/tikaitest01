package com.oo.datamanagement.api.dto;

import lombok.Data;

import java.util.List;

/**
 * 查询类
 */
@Data
public class MdQueryDTO {

    /**
     * 名称集合
     */
    private List<String> names;

    /*
     * 类型
     */
    private String type;
}
