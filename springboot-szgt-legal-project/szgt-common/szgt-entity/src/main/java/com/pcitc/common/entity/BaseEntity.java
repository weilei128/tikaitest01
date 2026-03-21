package com.pcitc.common.entity;

import lombok.Data;

/***
 * @description 基类实体类 (需要进行分页查询继承)
 * @author leigang
 * @date 2020年2月19日 15:02:10
 *
 */
@Data
public class BaseEntity {

    //每页个数
    private long size;

    //当前页数
    private long current;

}
