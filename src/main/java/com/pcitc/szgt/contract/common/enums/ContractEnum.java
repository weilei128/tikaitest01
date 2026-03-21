package com.pcitc.szgt.contract.common.enums;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *	 枚举
 */
public class ContractEnum {

    public static Map<String, String> enumSectionMap;
    public static Map<String, String> enumSectionActualMap;
    public static Map<String, String> enumNodeMap;
    public static Map<String, String> enumStatusMap;
    public static Map<String, String> enumModuleMap;
    public static Map<String, String> enumIsLogicDelMap;
    public static Map<String, String> enumTextTypeMap;
    public static Map<String, String> enumContractTextStatusMap;
    public static Map<String, String> enumContractChageTypeMap;
    public static Map<String, String> enumContractProposerMap;
    public static Map<String, String> enumPauseKindMap;
    public static Map<String, String> enumOrgTypeMap;
    public static Map<String, String> enumWorkFlowMap;
    public static Map<String, String> enumWorkFlowTypeMap;
    public static Map<String, String> EnumExecuteResultMap;
    public static Map<String, String> enumPayTypeMap;

    static {

        enumSectionActualMap = new LinkedHashMap<>();
        for(EnumSectionActual esa : EnumSectionActual.values()){
            enumSectionActualMap.put(esa.getCode(), esa.getMessage());
        }

        enumSectionMap = new HashMap<>();
        for (EnumSection es : EnumSection.values()) {
            enumSectionMap.put(es.getCode(), es.getMessage());
        }

        enumNodeMap = new HashMap<>();
        for (EnumNode en : EnumNode.values()) {
            enumNodeMap.put(en.getCode(), en.getMessage());
        }

        enumStatusMap = new HashMap<>();
        for (EnumStatus es : EnumStatus.values()) {
            enumStatusMap.put(es.getCode(), es.getMessage());
        }

        enumModuleMap = new HashMap<>();
        for (EnumModule em : EnumModule.values()) {
            enumModuleMap.put(em.getCode(), em.getMessage());
        }

        enumIsLogicDelMap = new HashMap<>();
        for (EnumIsLogicDel em : EnumIsLogicDel.values()) {
            enumIsLogicDelMap.put(em.getCode(), em.getMessage());
        }

        enumTextTypeMap = new HashMap<>();
        for (EnumTextType em : EnumTextType.values()) {
            enumTextTypeMap.put(em.getCode(), em.getMessage());
        }

        enumContractTextStatusMap = new HashMap<>();
        for (EnumContractTextStatus em : EnumContractTextStatus.values()) {
            enumContractTextStatusMap.put(em.getCode(), em.getMessage());
        }

        enumContractChageTypeMap = new HashMap<>();
        for (EnumContractChageType em : EnumContractChageType.values()) {
            enumContractChageTypeMap.put(em.getCode(), em.getMessage());
        }

        enumContractProposerMap = new HashMap<>();
        for (EnumContractProposer em : EnumContractProposer.values()) {
            enumContractProposerMap.put(em.getCode(), em.getMessage());
        }

        enumPauseKindMap = new HashMap<>();
        for (EnumPauseKind em : EnumPauseKind.values()) {
            enumPauseKindMap.put(em.getCode(), em.getMessage());
        }

        enumOrgTypeMap = new HashMap<>();
        for (EnumOrgType em : EnumOrgType.values()) {
            enumOrgTypeMap.put(em.getCode(), em.getMessage());
        }

        enumWorkFlowMap = new HashMap<>();
        for (EnumWorkFlow em : EnumWorkFlow.values()) {
            enumWorkFlowMap.put(em.getCode(), em.getMessage());
        }

        enumWorkFlowTypeMap = new HashMap<>();
        for (EnumWorkFlowType em : EnumWorkFlowType.values()) {
            enumWorkFlowTypeMap.put(em.getCode(), em.getMessage());
        }
        EnumExecuteResultMap = new HashMap<>();
        for (EnumExecuteResult es : EnumExecuteResult.values()) {
            EnumExecuteResultMap.put(es.getCode(), es.getMessage());
        }

        enumPayTypeMap = new HashMap<>();
        for(PayTypeEnum pt : PayTypeEnum.values()){
            enumPayTypeMap.put(pt.getCode(), pt.getName());
        }

    }

    /**
     * 	合同业务流程的阶段
     */
    public enum EnumSection {

        Prepare("1", "订立准备"),
        CheckPrepare("2", "合同准备审核"),
        PurchasePrepare("3", "合同准备"),
        Make("11", "订立发起"),
        Check("17", "文本审查审批"),
        Print("13", "合同打印"),
        PrintCompelete("14", "合同打印"),
        Sign("19", "合同签署"),
        Record("20", "合同要素登记"),
        InnerPrepare("21", "待对方处理"),
        ElectronicSignature("22", "电子签名"),
        ElectronicSeal("23", "电子盖章"),
        Perform("30", "合同履行"),
        Saving("31", "合同收款"),
        Payment("32", "合同付款"),
        Suspend("33", "合同中止"),
        Terminate("34", "合同终止"),
        Change("35", "合同变更"),
        Transfer("36", "合同转让"),
        TmpArchive("37", "合同临时归档"),
        PaymentAdd("38", "付款详情录入"),
        PaymentEntry("39", "付款录入"),
        SavingPayment("40", "收付款执行"),
        PrePayment("41", "PrePayment"),
        PrePaymentAdd("42", "预付款申请录入"),
        Finality("61", "合同终结"),
        Archive("91", "合同归档"),
        PaperArchive("93", "合同纸质归档"),
        Abandoned("94", "合同废弃审核"),
        ChangeSign("97", "合同变更签署"),
        TransferSign("98", "合同转让签署"),
        TerminateSign("99", "合同终止签署");

        private String code;
        private String message;

        EnumSection(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum EnumSectionActual {
//        Prepare("1", "订立准备"),
        Prepare("1", "合同准备"),
//        CheckPrepare("2", "合同准备审核"),
//        PurchasePrepare("3", "合同准备"),
        Make("11", "订立发起"),
        Check("17", "文本审查审批"),
        Print("13", "合同打印"),
//        PrintCompelete("14", "合同打印"),
        Sign("19", "合同签署"),
//        Record("20", "合同要素登记"),

//        InnerPrepare("21", "待对方处理"),

//        ElectronicSignature("22", "电子签名"),

//        ElectronicSeal("23", "电子盖章"),

        Perform("30", "合同履行"),

//        Saving("31", "合同收款"),
//
//        Payment("32", "合同付款"),

//        Suspend("33", "合同中止"),

        Change("35", "合同变更"),
        ChangeSign("97", "合同变更签署"),

        Transfer("36", "合同转让"),
        TransferSign("98", "合同转让签署"),

        Terminate("34", "合同终止"),
        TerminateSign("99", "合同终止签署"),

//        TmpArchive("37", "合同临时归档"),
//
//        PaymentAdd("38", "付款详情录入"),
//
//        PaymentEntry("39", "付款录入"),
//
//        SavingPayment("40", "收付款执行"),
//
//        PrePayment("41", "PrePayment"),
//
//        PrePaymentAdd("42", "预付款申请录入"),

        Finality("61", "合同终结");

//        Archive("91", "合同归档"),
//
//        PaperArchive("93", "合同纸质归档"),
//
//        Abandoned("94", "合同废弃审核"),


        private String code;
        private String message;

        EnumSectionActual(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     *	环节
     */
    public enum EnumNode {
        Draft("0", "拟稿"),
        Check("1", "审查审批"),
        Coperate("2", "协同审查"),
        Waiting("3", "协同等待"),
        Print("4", "打印"),
        Payment("5", "付款录入"),
        ChangeRecord("6", "变更信息备案"),
        TransferRecord("7", "转让备案"),
        TextApply("8", "标准文本申请"),
        TextCheck("9", "标准文本审查"),
        TextBack("10", "标准文本审查退回"),
        Abandoned("11", "合同废弃审核");
        private String code;
        private String message;

        EnumNode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     *	合同状态
     */
    public enum EnumStatus {
        TempSave("0", "暂存"),
        Handing("1", "处理中"),
        Pause("2", "暂停"),
        Lock("3", "锁定"),
        Handled("4", "已处理"),
        Approved("5", "通过"),
        Reback("6", "退回"),
        Cancelled("7", "已撤销"),
        Submited("8", "提交");
        private String code;
        private String message;

        EnumStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     *	合同执行环节
     */
    public enum EnumModule {
        Prepare("1", "合同准备"),
        Make("11", "合同订立"),
        Sign("19", "合同签署"),
        Perform("30", "合同履行"),
        Finality("61", "合同终结"),
        Archive("91", "合同归档");

        private String code;
        private String message;

        EnumModule(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * 	合同删除状态
     */
    public enum EnumIsLogicDel {
        Valid("0", "有效"),
        Delete("1", "删除"),
        LogicDel("2", "废弃");

        private String code;
        private String message;

        EnumIsLogicDel(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 	合同文本使用类型
     */
    public enum EnumTextType {
        makeType("0", "合同订立"),
        changeType("1", "合同变更");
        private String code;
        private String message;

        EnumTextType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * 	标准文本模板状态
     */
    public enum EnumContractTextStatus {
        Draft("1", "合同订立"),
        Pending("2", "合同变更"),
        Back("3", "合同变更"),
        Publishing("4", "合同变更"),
        Publish("5", "合同变更");
        private String code;
        private String message;

        EnumContractTextStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     *	合同变更事项
     */
    public enum EnumContractChageType {
        BidAmount("1", "标的金额"),
        PerformDate("2", "履行期限"),
        MySignBody("3", "主体名称"),
        BidDetail("4", "标的明细"),
        Other("5", "其他");
        private String code;
        private String message;

        EnumContractChageType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        /**
         * 根据描述返回枚举类
         *
         * @param
         * @return
         */
        public static EnumContractChageType getEnumContractChageType(String code) {
            EnumContractChageType[] values = EnumContractChageType.values();
            for (int i = 0; i < values.length; i++) {
                EnumContractChageType p = values[i];
                if (p.getCode().equals(code)) {
                    return p;
                }
            }
            return null;
        }



    }

    /**
     *	合同变更申请方
     */
    public enum EnumContractProposer {
        OurSide("0", "我方"),
        OtherSide("1", "对方"),
        BothSide("2", "双方"),
        Other("3", "其他");
        private String code;
        private String message;

        EnumContractProposer(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        /**
         * 根据描述返回枚举类
         *
         * @param
         * @return
         */
        public static EnumContractProposer getEnumContractProposer(String code) {
            EnumContractProposer[] values = EnumContractProposer.values();
            for (int i = 0; i < values.length; i++) {
                EnumContractProposer p = values[i];
                if (p.getCode().equals(code)) {
                    return p;
                }
            }
            return null;
        }
    }

    /**
     * 	终止类型
     */
    public enum EnumPauseKind {
        General("0", "一般中止"),
        Case("1", "发案中止");

        private String code;
        private String message;

        EnumPauseKind(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


    /**
     * 	组织机构类型
     */
    public enum EnumOrgType {
        Dept("0", "部门"),
        Unit("1", "单位"),
        Company("2", "企业"),
        Group("3", "集团");

        private String code;
        private String message;

        EnumOrgType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 	工作流
     */
    public enum EnumWorkFlow {
        Make("szgt_contract_make", "合同订立"),
        Change("szgt_contract_change", "合同变更"),
        Trasfer("szgt_contract_transfer", "合同转让"),
        Terminate("szgt_contract_terminate", "合同终止"),
        Finally("szgt_contract_finally", "合同终结");

        private String code;
        private String message;

        EnumWorkFlow(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 	模板类型
     */
    public enum EnumWorkFlowType {
        Common("1", "公共"),
        Org("0", "机构");

        private String code;
        private String message;

        EnumWorkFlowType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 	消息状态
     */
    public enum EnumMessageSate {
        UnRead("0", "未读"),
        Readed("1", "已读"),    //未使用
        Deal("2", "已处理"),
        Discard("3", "废弃");   //未使用

        private String code;
        private String message;

        EnumMessageSate(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 	执行结果
     */
    public enum EnumExecuteResult {
        Forword("-1", "未审批"),
        Complete("2", "完成-审批通过"),
        Revert("3", "退回"),
        Skip("7", "跳过"),
        Coordinate("8", "协同审查"),
        Proxy("10", "代理审查");

        private String code;
        private String message;

        EnumExecuteResult(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
    /**
     * 	办公代理状态
     */
    public enum EnumOfficeAgents {
        ING("0", "代理中"),
        CANCEL("1", "已取消"),
        OVER("2", "已结束");

        private String code;
        private String message;

        EnumOfficeAgents(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
    
    /**
     * 	流程类型
     */
    public enum EnumActivityType {
        FENFA(80, "分发活动"),
        SPFENFA(70, "审批并分发"),
        FENFASP(40, "分发并审批"),
        YIBAN(10,"一般流程");

        private Integer code;
        private String message;

        EnumActivityType(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
