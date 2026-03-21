package com.pcitc.legalAffairs.service.mdm.entity;

import lombok.Data;

/***
 * @description 接口参数
 * @author leigang
 * @date 2020年4月17日 14:55:44
 *
 */
@Data
public class MdmBo {

    private String method;
    private Object[] objectParams;

}
