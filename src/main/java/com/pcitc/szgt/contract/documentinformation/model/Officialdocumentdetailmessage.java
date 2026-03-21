package com.pcitc.szgt.contract.documentinformation.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Officialdocumentdetailmessage {

    /**
     * 文档创建人（用户编码）
     */
    private String createCode;
    /**
     * 文文档创建人部门（部门编码）
     */
    private String createDept;
    /**
     * 文档创建人单位 (单位编码)
     */
    private String createCompany;
    /**
     * 文档创建时间
     */
    private String createTime;
    /**
     * 文档推送范围
     */
    private List<Map<Object,String>> pushScope;
    /**
     * 文档推送时间
     */
    private String pushTime;
    /**
     * 文档标题
     */
    private String subject;
    /**
     * 流程分类名称
     */
    private String tempateName;
    /**
     * 处理笺（PDF形式）下载地址
     */
    private List<Map<Object,String>> baseinfoUrl;
    /**
     * 正文下载地址
     */
    private List<Map<Object,String>> contentUrl;
    /**
     * 附件地址
     */
    private List<Map<Object,String>> enclosures;
    /**
     * 预留字段1
     */
    private String param1;
    /**
     * 预留字段2
     */
    private String param2;
    /**
     * 预留字段3
     */
    private String param3;

    @Override
    public String toString() {
        return "OAReceiveInfoVO{" +
                "createCode='" + createCode + '\'' +
                ", createDept='" + createDept + '\'' +
                ", createCompany='" + createCompany + '\'' +
                ", createTime='" + createTime + '\'' +
                ", pushScope=" + pushScope +
                ", pushTime='" + pushTime + '\'' +
                ", subject='" + subject + '\'' +
                ", tempateName='" + tempateName + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                '}';
    }
}
