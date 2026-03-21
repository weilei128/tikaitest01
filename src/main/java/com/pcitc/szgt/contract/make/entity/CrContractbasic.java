package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-02-20
 */
public class CrContractbasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合同流水号自增列
     */
    @TableField("SerialNum")
    private Long SerialNum;

    /**
     * 合同ID guid
     */
    @TableId("ContractID")
    private String ContractID;

    /**
     * 合同序号
     */
    @TableField("RuleSerialNum")
    private String RuleSerialNum;

    /**
     * 合同编号
     */
    @TableField("ContractNum")
    private String ContractNum;

    /**
     * 合同名称
     */
    @TableField("ContractName")
    private String ContractName;

    /**
     * 资金流向  1:支出  2:收入 3:不涉及
     */
    @ApiModelProperty(name = "MoneyFlow" , value = "1:支出  2:收入 3:不涉及" ,notes = "资金流向")
    @TableField("MoneyFlow")
    private Integer MoneyFlow;

    /**
     * 合同类型1 ID
     */
    @TableField("Type1")
    private Integer Type1;

    /**
     * 合同类型1 名称
     */
    @TableField("TypeName1")
    private String TypeName1;

    /**
     * 合同类型2 ID
     */
    @TableField("Type2")
    private Integer Type2;

    /**
     * 合同类型2 名称
     */
    @TableField("TypeName2")
    private String TypeName2;

    /**
     * 合同类型3 ID
     */
    @TableField("Type3")
    private Integer Type3;

    /**
     * 合同类型3 名称
     */
    @TableField("TypeName3")
    private String TypeName3;

    /**
     * 合同类型4 ID
     */
    @TableField("Type4")
    private Integer Type4;

    /**
     * 合同类型4 名称
     */
    @TableField("TypeName4")
    private String TypeName4;

    /**
     * 主办单位 ID
     */
    @TableField("MainOrgID")
    private Integer MainOrgID;

    /**
     * 主办单位 name
     */
    @TableField("MainOrgName")
    private String MainOrgName;

    /**
     * 主办部门ID
     */
    @TableField("MainDeptID")
    private Integer MainDeptID;

    /**
     * 主办部门name
     */
    @TableField("MainDeptName")
    private String MainDeptName;

    /**
     * 主办部门路径 石化盈科/互联网事业部
     */
    @TableField("MainOrgNamePath")
    private String MainOrgNamePath;

    /**
     * 主办人ID
     */
    @TableField("MainOrgUserID")
    private String MainOrgUserID;

    /**
     * 主办人name
     */
    @TableField("MainOrgUserName")
    private String MainOrgUserName;

    /**
     * 主办人电话
     */
    @TableField("MainOrgUserPhone")
    private String MainOrgUserPhone;

    /**
     * 标的金额是否确定 0/1
     */
    @TableField("IsMakeSureMoney")
    private Integer IsMakeSureMoney;

    /**
     * 标的金额是否确定名称 0未确定 1确定
     */
    @TableField("IsMakeSureMoneyName")
    private String IsMakeSureMoneyName;

    /**
     * 合同金额
     */
    @TableField("ContractObjectMoney")
    private BigDecimal ContractObjectMoney;

    /**
     * 合同币种
     */
    @TableField("ContractObjectCurrency")
    private Integer ContractObjectCurrency;

    /**
     * 合同汇率
     */
    @TableField("ContractObjectRate")
    private BigDecimal ContractObjectRate;

    /**
     * 合同人名币金额
     */
    @TableField("ContractObjectAmount")
    private BigDecimal ContractObjectAmount;
    /**
     * 合同人名币金额（含税金额）
     */
    @TableField("TaxAmount")
    private BigDecimal TaxAmount;

    /**
     * 合同人名币金额（不含税金额）
     */
    @TableField("NoTaxAmount")
    private BigDecimal NoTaxAmount;


    /**
     * 合同性质 总部/企业
     */
    @TableField("CKind")
    private Integer CKind;

    /**
     * 合同系统模块 合同准备 合同订立 合同履行 合同终结 合同归档
     */
    @TableField("PropertyModel")
    private Integer PropertyModel;

    /**
     * 合同业务流程的阶段 合同准备  订立发起  合同文本审查审批 合同打印 合同签署 合同履行 合同中止 合同终止 合同变更 合同转让等
     */
    @TableField("Section")
    private Integer Section;

    /**
     * 环节  拟稿 审查审批 协同审查 协同等待 等
     */
    @TableField("Node")
    private Integer Node;

    /**
     * 合同状态
     */
    @TableField("Status")
    private Integer Status;

    /**
     * 合同是否废弃 0有效 1删除 2废弃
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 修改时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 企业标识
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 是否发送ERP
     */
    @TableField("IsSendToERP")
    private Integer IsSendToERP;

    /**
     * 企业类型 集团/资产/股份
     */
    @TableField("CompanyID")
    private String CompanyID;

    /**
     * 备注
     */
    @TableField("RemarksText")
    private String RemarksText;

    /**
     * 是否引用其他合同的标的
     */
    @TableField("IsRefBidItem")
    private Integer IsRefBidItem;

    /**
     * 合同打印时间
     */
    @TableField("PrintDate")
    private LocalDateTime PrintDate;

    /**
     * 合同审查审批完成时间
     */
    @TableField("CheckDate")
    private LocalDateTime CheckDate;

    /**
     * 合同完成终结时间
     */
    @TableField("FinalityDate")
    private LocalDateTime FinalityDate;

    /**
     * 合同归档时间
     */
    @TableField("ElecFillDate")
    private LocalDateTime ElecFillDate;

    /**
     * 父框架合同ID
     */
    @TableField("ExeOrgFrameID")
    private String ExeOrgFrameID;

    /**
     * 合同来源系统集成
     */
    @TableField("Source")
    private Integer Source;

    /**
     * 对方系统主键
     */
    @TableField("SourceContractID")
    private String SourceContractID;

    /**
     * 对方系统合同类型
     */
    @TableField("SourceContractType")
    private String SourceContractType;

    /**
     * 对方系统合同编号
     */
    @TableField("SourceContractNum")
    private String SourceContractNum;

    /**
     * 对方合同名称
     */
    @TableField("SourceContractName")
    private String SourceContractName;

    /**
     * 对方合同系统订单号
     */
    @TableField("SourceContractOrder")
    private String SourceContractOrder;

    /**
     * 标的类型（0-手工物料/MRO订单物料，1-PMS工单，2-PS服务协议）
     */
    @TableField("BidItemType")
    private Integer BidItemType;
    /*
     * 是否归档 0否 1是
     * */
    @TableField("IsFileArchive")
    private Integer IsFileArchive;

    public Long getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(Long SerialNum) {
        this.SerialNum = SerialNum;
    }

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }

    public String getRuleSerialNum() {
        return RuleSerialNum;
    }

    public void setRuleSerialNum(String RuleSerialNum) {
        this.RuleSerialNum = RuleSerialNum;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public void setContractNum(String ContractNum) {
        this.ContractNum = ContractNum;
    }

    public String getContractName() {
        return ContractName;
    }

    public void setContractName(String ContractName) {
        this.ContractName = ContractName;
    }

    public Integer getMoneyFlow() {
        return MoneyFlow;
    }

    public void setMoneyFlow(Integer MoneyFlow) {
        this.MoneyFlow = MoneyFlow;
    }

    public Integer getType1() {
        return Type1;
    }

    public void setType1(Integer Type1) {
        this.Type1 = Type1;
    }

    public String getTypeName1() {
        return TypeName1;
    }

    public void setTypeName1(String TypeName1) {
        this.TypeName1 = TypeName1;
    }

    public Integer getType2() {
        return Type2;
    }

    public void setType2(Integer Type2) {
        this.Type2 = Type2;
    }

    public String getTypeName2() {
        return TypeName2;
    }

    public void setTypeName2(String TypeName2) {
        this.TypeName2 = TypeName2;
    }

    public Integer getType3() {
        return Type3;
    }

    public void setType3(Integer Type3) {
        this.Type3 = Type3;
    }

    public String getTypeName3() {
        return TypeName3;
    }

    public void setTypeName3(String TypeName3) {
        this.TypeName3 = TypeName3;
    }

    public Integer getType4() {
        return Type4;
    }

    public void setType4(Integer Type4) {
        this.Type4 = Type4;
    }

    public String getTypeName4() {
        return TypeName4;
    }

    public void setTypeName4(String TypeName4) {
        this.TypeName4 = TypeName4;
    }

    public Integer getMainOrgID() {
        return MainOrgID;
    }

    public void setMainOrgID(Integer MainOrgID) {
        this.MainOrgID = MainOrgID;
    }

    public String getMainOrgName() {
        return MainOrgName;
    }

    public void setMainOrgName(String MainOrgName) {
        this.MainOrgName = MainOrgName;
    }

    public Integer getMainDeptID() {
        return MainDeptID;
    }

    public void setMainDeptID(Integer MainDeptID) {
        this.MainDeptID = MainDeptID;
    }

    public String getMainDeptName() {
        return MainDeptName;
    }

    public void setMainDeptName(String MainDeptName) {
        this.MainDeptName = MainDeptName;
    }

    public String getMainOrgNamePath() {
        return MainOrgNamePath;
    }

    public void setMainOrgNamePath(String MainOrgNamePath) {
        this.MainOrgNamePath = MainOrgNamePath;
    }

    public String getMainOrgUserID() {
        return MainOrgUserID;
    }

    public void setMainOrgUserID(String MainOrgUserID) {
        this.MainOrgUserID = MainOrgUserID;
    }

    public String getMainOrgUserName() {
        return MainOrgUserName;
    }

    public void setMainOrgUserName(String MainOrgUserName) {
        this.MainOrgUserName = MainOrgUserName;
    }

    public String getMainOrgUserPhone() {
        return MainOrgUserPhone;
    }

    public void setMainOrgUserPhone(String MainOrgUserPhone) {
        this.MainOrgUserPhone = MainOrgUserPhone;
    }

    public Integer getIsMakeSureMoney() {
        return IsMakeSureMoney;
    }

    public void setIsMakeSureMoney(Integer IsMakeSureMoney) {
        this.IsMakeSureMoney = IsMakeSureMoney;
    }

    public String getIsMakeSureMoneyName() {
        return IsMakeSureMoneyName;
    }

    public void setIsMakeSureMoneyName(String IsMakeSureMoneyName) {
        this.IsMakeSureMoneyName = IsMakeSureMoneyName;
    }

    public BigDecimal getContractObjectMoney() {
        return ContractObjectMoney;
    }

    public void setContractObjectMoney(BigDecimal ContractObjectMoney) {
        this.ContractObjectMoney = ContractObjectMoney;
    }

    public Integer getContractObjectCurrency() {
        return ContractObjectCurrency;
    }

    public void setContractObjectCurrency(Integer ContractObjectCurrency) {
        this.ContractObjectCurrency = ContractObjectCurrency;
    }

    public BigDecimal getContractObjectRate() {
        return ContractObjectRate;
    }

    public void setContractObjectRate(BigDecimal ContractObjectRate) {
        this.ContractObjectRate = ContractObjectRate;
    }

    public BigDecimal getContractObjectAmount() {
        return ContractObjectAmount;
    }

    public void setTaxAmount(BigDecimal TaxAmount) {
        this.TaxAmount = TaxAmount;
    }
    public BigDecimal getTaxAmount() {
        return TaxAmount;
    }

    public void setNoTaxAmount(BigDecimal NoTaxAmount) {
        this.NoTaxAmount = NoTaxAmount;
    }

    public BigDecimal getNoTaxAmount() {
        return NoTaxAmount;
    }

    public void setContractObjectAmount(BigDecimal ContractObjectAmount) {
        this.ContractObjectAmount = ContractObjectAmount;
    }



    public Integer getCKind() {
        return CKind;
    }

    public void setCKind(Integer CKind) {
        this.CKind = CKind;
    }

    public Integer getPropertyModel() {
        return PropertyModel;
    }

    public void setPropertyModel(Integer PropertyModel) {
        this.PropertyModel = PropertyModel;
    }

    public Integer getSection() {
        return Section;
    }

    public void setSection(Integer Section) {
        this.Section = Section;
    }

    public Integer getNode() {
        return Node;
    }

    public void setNode(Integer Node) {
        this.Node = Node;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    public Integer getIsSendToERP() {
        return IsSendToERP;
    }

    public void setIsSendToERP(Integer IsSendToERP) {
        this.IsSendToERP = IsSendToERP;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String CompanyID) {
        this.CompanyID = CompanyID;
    }

    public String getRemarksText() {
        return RemarksText;
    }

    public void setRemarksText(String RemarksText) {
        this.RemarksText = RemarksText;
    }

    public Integer getIsRefBidItem() {
        return IsRefBidItem;
    }

    public void setIsRefBidItem(Integer IsRefBidItem) {
        this.IsRefBidItem = IsRefBidItem;
    }

    public LocalDateTime getPrintDate() {
        return PrintDate;
    }

    public void setPrintDate(LocalDateTime PrintDate) {
        this.PrintDate = PrintDate;
    }

    public LocalDateTime getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(LocalDateTime CheckDate) {
        this.CheckDate = CheckDate;
    }

    public LocalDateTime getFinalityDate() {
        return FinalityDate;
    }

    public void setFinalityDate(LocalDateTime FinalityDate) {
        this.FinalityDate = FinalityDate;
    }

    public LocalDateTime getElecFillDate() {
        return ElecFillDate;
    }

    public void setElecFillDate(LocalDateTime ElecFillDate) {
        this.ElecFillDate = ElecFillDate;
    }

    public String getExeOrgFrameID() {
        return ExeOrgFrameID;
    }

    public void setExeOrgFrameID(String ExeOrgFrameID) {
        this.ExeOrgFrameID = ExeOrgFrameID;
    }

    public Integer getSource() {
        return Source;
    }

    public void setSource(Integer Source) {
        this.Source = Source;
    }

    public String getSourceContractID() {
        return SourceContractID;
    }

    public void setSourceContractID(String SourceContractID) {
        this.SourceContractID = SourceContractID;
    }

    public String getSourceContractType() {
        return SourceContractType;
    }

    public void setSourceContractType(String SourceContractType) {
        this.SourceContractType = SourceContractType;
    }

    public String getSourceContractNum() {
        return SourceContractNum;
    }

    public void setSourceContractNum(String SourceContractNum) {
        this.SourceContractNum = SourceContractNum;
    }

    public String getSourceContractName() {
        return SourceContractName;
    }

    public void setSourceContractName(String SourceContractName) {
        this.SourceContractName = SourceContractName;
    }

    public String getSourceContractOrder() {
        return SourceContractOrder;
    }

    public void setSourceContractOrder(String SourceContractOrder) {
        this.SourceContractOrder = SourceContractOrder;
    }

    public Integer getBidItemType() {
        return BidItemType;
    }

    public void setBidItemType(Integer BidItemType) {
        this.BidItemType = BidItemType;
    }

    public Integer getIsFileArchive() {
        return IsFileArchive;
    }

    public void setIsFileArchive(Integer IsFileArchive) {
        this.IsFileArchive = IsFileArchive;
    }

    @Override
    public String toString() {
        return "CrContractbasic{" +
                "SerialNum=" + SerialNum +
                ", ContractID=" + ContractID +
                ", RuleSerialNum=" + RuleSerialNum +
                ", ContractNum=" + ContractNum +
                ", ContractName=" + ContractName +
                ", MoneyFlow=" + MoneyFlow +
                ", Type1=" + Type1 +
                ", TypeName1=" + TypeName1 +
                ", Type2=" + Type2 +
                ", TypeName2=" + TypeName2 +
                ", Type3=" + Type3 +
                ", TypeName3=" + TypeName3 +
                ", Type4=" + Type4 +
                ", TypeName4=" + TypeName4 +
                ", MainOrgID=" + MainOrgID +
                ", MainOrgName=" + MainOrgName +
                ", MainDeptID=" + MainDeptID +
                ", MainDeptName=" + MainDeptName +
                ", MainOrgNamePath=" + MainOrgNamePath +
                ", MainOrgUserID=" + MainOrgUserID +
                ", MainOrgUserName=" + MainOrgUserName +
                ", MainOrgUserPhone=" + MainOrgUserPhone +
                ", IsMakeSureMoney=" + IsMakeSureMoney +
                ", IsMakeSureMoneyName=" + IsMakeSureMoneyName +
                ", ContractObjectMoney=" + ContractObjectMoney +
                ", ContractObjectCurrency=" + ContractObjectCurrency +
                ", ContractObjectRate=" + ContractObjectRate +
                ", ContractObjectAmount=" + ContractObjectAmount +
                ", CKind=" + CKind +
                ", PropertyModel=" + PropertyModel +
                ", Section=" + Section +
                ", Node=" + Node +
                ", Status=" + Status +
                ", LogicDel=" + LogicDel +
                ", CreatedBy=" + CreatedBy +
                ", CreatedDate=" + CreatedDate +
                ", ModifiedBy=" + ModifiedBy +
                ", ModifiedDate=" + ModifiedDate +
                ", Oulabel=" + Oulabel +
                ", IsSendToERP=" + IsSendToERP +
                ", CompanyID=" + CompanyID +
                ", RemarksText=" + RemarksText +
                ", IsRefBidItem=" + IsRefBidItem +
                ", PrintDate=" + PrintDate +
                ", CheckDate=" + CheckDate +
                ", FinalityDate=" + FinalityDate +
                ", ElecFillDate=" + ElecFillDate +
                ", ExeOrgFrameID=" + ExeOrgFrameID +
                ", Source=" + Source +
                ", SourceContractID=" + SourceContractID +
                ", SourceContractType=" + SourceContractType +
                ", SourceContractNum=" + SourceContractNum +
                ", SourceContractName=" + SourceContractName +
                ", SourceContractOrder=" + SourceContractOrder +
                ", BidItemType=" + BidItemType +
                ", IsFileArchive=" + IsFileArchive +
                "}";
    }

    public static void main(String[] args) {
        String code = "006006015";
        System.out.print(code.substring(code.length() - 2));
    }
}
