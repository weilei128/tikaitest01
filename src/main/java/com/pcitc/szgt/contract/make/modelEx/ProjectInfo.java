package com.pcitc.szgt.contract.make.modelEx;


import java.math.BigDecimal;

/*
 * 项目信息
 * */
public class ProjectInfo {
    /*
     * 主键
     * */
    public String projectId;
    /*
     * 项目名称
     * */
    public String projectName;
    /*
     * 项目编号
     * */
    public String projectCode;
    /*
     * 建设单位
     * */
    public String execOrgan;
    /*
     * 立项年度
     * */
    public int atYear;
    /*
     * 负责人
     * */
    public String responsibleperson;
    /*
     * 负责人编码
     * */
    public String responsiblepersoncode;
    /*
     * 公司代码
     * */
    public String companycode;

    /*
     * 项目投资金额
     * */
    public BigDecimal investAmount;
    /*
     * 币种
     * */
    public int currency;
    /*
     * 公开范围
     * */
    public Integer[] orgID;
    /*
     * 数据来源 0自建 1ERP
     * */
    public int source;
    /*
     * 工厂
     * */
    public String factory;
    /*
     * 是否有效 1是 0否
     * */
    public int isValid;
    /*
     *备注
     * */
    public String Remark;
    /*
     * 创建日期
     * */
    public String createDate;

}
