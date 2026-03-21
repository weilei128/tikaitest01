package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author jobob
 * @since 2020-02-20
 */
public class CrContractinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractID")
    private String ContractID;

    /**
     * 是否内部合同ID
     */
    @TableField("InnerContractID")
    private String InnerContractID;

    /**
     * 是否框架下合同 ID
     */
    @TableField("PContractID")
    private String PContractID;

    /**
              * 是否框架合同0否1是
     */
    @TableField("IsFrameContract")
    private Integer IsFrameContract;

    /**
     * 所属项目
     */
    @TableField("ProjectID")
    private String ProjectID;

    /**
     * 资金来源1
     */
    @TableField("MoneySource")
    private Integer MoneySource;

    /**
     * 资金来源2
     */
    @TableField("MoneySource2")
    private String MoneySource2;

    /**
     * 选商方式1
     */
    @TableField("SelectWay1")
    private Integer SelectWay1;

    /**
     * 选商方式2
     */
    @TableField("SelectWay2")
    private Integer SelectWay2;

    /**
     * 选商方式3
     */
    @TableField("SelectWay3")
    private String SelectWay3;

    /**
     * 计划金额
     */
    @TableField("PlanMoney")
    private BigDecimal PlanMoney;

    /**
     * 计划金额币种
     */
    @TableField("PlanMoneyCurrency")
    private Integer PlanMoneyCurrency;

    /**
     * 生效日期
     */
    @TableField("EffectiveDate")
    private LocalDateTime EffectiveDate;

    /**
     * 合同生效要件
     */
    @TableField("ImportantDoc")
    private String ImportantDoc;

    /**
     * 生效情况 及时生效/其他
     */
    @TableField("ImportantType")
    private Integer ImportantType;

    /**
     * 签约主体编码
     */
    @TableField("MySignBodyCode")
    private Integer MySignBodyCode;

    /**
     * 签约主体名称
     */
    @TableField("MySignBodyName")
    private String MySignBodyName;

    /**
     * 我方签约人ID
     */
    @TableField("MySignPerson")
    private String MySignPerson;

    /**
     * 我方签约人名称
     */
    @TableField("MySignPersonName")
    private String MySignPersonName;

    /**
     * 我方签约人联系电话
     */
    @TableField("MySignPersonPhone")
    private String MySignPersonPhone;

    /**
     * 我方签约人身份证
     */
    @TableField("MySignPersonCard")
    private String MySignPersonCard;

    /**
     * 我方签约人所在单位
     */
    @TableField("MySignPersonUnit")
    private String MySignPersonUnit;

    /**
     * 我方签约人所在部门
     */
    @TableField("MySignPersonDept")
    private String MySignPersonDept;

    /**
     * 我方签约人职务
     */
    @TableField("MySignPersonPostion")
    private String MySignPersonPostion;

    /**
     * 签约日期
     */
    @TableField("MySignDate")
    private LocalDateTime MySignDate;

    /**
     * 我方盖章人
     */
    @TableField("MySealPerSon")
    private String MySealPerSon;

    /**
     * 盖章日期
     */
    @TableField("MySealDate")
    private LocalDateTime MySealDate;

    @ApiModelProperty(name = "MySealTimes",notes = "盖章份数")
    @TableField("MySealTimes")
    private Integer MySealTimes;
    
    //2021-05-08 add
    @ApiModelProperty(name = "UseSignetApprover",notes = "用印审批人")
    @TableField("UseSignetApprover")
    private String UseSignetApprover ;
    
    @ApiModelProperty(name = "IsSeal",notes = "是否已经盖章")
    @TableField("IsSeal")
    private Integer IsSeal;

    @ApiModelProperty(name = "IsESeal",notes = "是否已经电子签名")
    @TableField("IsESeal")
    private Integer IsESeal;

    /**
     * 文本发送人
     */
    @TableField("TextServer")
    private String TextServer;

    /**
     * 送达时间
     */
    @TableField("ServerTime")
    private LocalDateTime ServerTime;

    /**
     * 文本来源
     */
    @TableField("TextSource")
    private Integer TextSource;

    /**
     * 盖章备注
     */
    @TableField("SealRemark")
    private String SealRemark;

    /**
     * 对方签约人2
     */
    @TableField("OtherSignPerson2")
    private String OtherSignPerson2;

    /**
     * 对方签约人3
     */
    @TableField("OtherSignPerson3")
    private String OtherSignPerson3;

    /**
     * 签订定点
     */
    @TableField("SignAddr")
    private String SignAddr;

    /**
     * 付款方式
     */
    @TableField("PayMethod")
    private Integer PayMethod;

    /**
     * 相对人1 id
     */
    @TableField("OffereeID2")
    private String OffereeID2;

    /**
     * 相对人1name
     */
    @TableField("OffereeName2")
    private String OffereeName2;

    /**
     * 相对人类型1 id
     */
    @TableField("OffereeType2")
    private Integer OffereeType2;

    /**
     * 相对人2 id
     */
    @TableField("OffereeID3")
    private String OffereeID3;

    /**
     * 相对人2name
     */
    @TableField("OffereeName3")
    private String OffereeName3;

    /**
     * 相对人类型2 id
     */
    @TableField("OffereeType3")
    private Integer OffereeType3;

    /**
     * 相对人3 id
     */
    @TableField("OffereeID4")
    private String OffereeID4;

    /**
     * 相对人3name
     */
    @TableField("OffereeName4")
    private String OffereeName4;

    /**
     * 相对人类型3 id
     */
    @TableField("OffereeType4")
    private Integer OffereeType4;

    /**
     * 单位归属
     */
    @TableField("CompanyType")
    private Integer CompanyType;

    /**
     * 是否内部合同
     */
    @TableField("IsInnerContract")
    private Integer IsInnerContract;

    /**
     * 合同对方经办人
     */
    @TableField("InnerOperator")
    private String InnerOperator;

    /**
     * 是否关联交易
     */
    @TableField("IsRelatedTransaction")
    private Integer IsRelatedTransaction;

    /**
     * 是否涉外合同
     */
    @TableField("IsConcernForeign")
    private Integer IsConcernForeign;

    /**
     * 文本类型
     */
    @TableField("TextType")
    private Integer TextType;

    /**
     * 文本模板
     */
    @TableField("TextModel")
    private String TextModel;

    /**
     * 合同份数
     */
    @TableField("NeedPrintCount")
    private Integer NeedPrintCount;

    /**
     * 履行期限 确定/不确定
     */
    @TableField("PerFormIsConfirm")
    private Integer PerFormIsConfirm;

    @TableField("PerFormNotConfirm")
    private String PerFormNotConfirm;

    /**
     * 履行期限开始时间
     */
    @TableField("PerFormStartDate")
    private LocalDateTime PerFormStartDate;

    /**
     * 履行期限结束时间
     */
    @TableField("PerFormEndDate")
    private LocalDateTime PerFormEndDate;

    /**
     * 结算期限
     */
    @TableField("SettleDeadline")
    private LocalDateTime SettleDeadline;

    /**
     * 纠纷解决方式
     */
    @TableField("IssueSolveMode")
    private Integer IssueSolveMode;

    /**
     * 有预付款
     */
    @TableField("IsImprest")
    private Integer IsImprest;

    /**
     * 预付款金额
     */
    @TableField("Imprest")
    private BigDecimal Imprest;

    /**
     * 预付款币种
     */
    @TableField("ImprestCurrency")
    private Integer ImprestCurrency;

    /**
     * 企业合同编号
     */
    @TableField("CoContractNum")
    private String CoContractNum;

    /**
     * 废弃原因
     */
    @TableField("DiscardExplain")
    private String DiscardExplain;

    /**
     * 0正常1删除2废弃
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
     * 订立送审时间
     */
    @TableField("ReviewDate")
    private LocalDateTime ReviewDate;

    /**
     * 合同环节+节点+消息状态 modle+section+messagestatus
     */
    @TableField("Reportedhistory")
    private String Reportedhistory;

    /**
     * 合同要素修改，修改原因
     */
    @TableField("ModifiedReason")
    private String ModifiedReason;

    /**
     * 合同归档时间
     */
    @TableField("SealRecordDate")
    private LocalDateTime SealRecordDate;

    /**
     * 框架协议号
     */
    @TableField("FrameNum")
    private String FrameNum;

    @TableField("FrameSource")
    private Integer FrameSource;

    /**
     * 我方传真号
     */
    @TableField("OUFax")
    private String OUFax;

    /**
     * 涉外合同分类 境内/境外
     */
    @TableField("IsConcernForeignType")
    private String IsConcernForeignType;

    /**
     * 合同金额及支付方式
     */
    @TableField("TotalAmountPayMethod")
    private String TotalAmountPayMethod;

    /**
     * 项目名称
     */
    @TableField("ProjectName")
    private String ProjectName;

    @TableField("IsEcContract")
    private Integer IsEcContract;

    /**
     * 盖章人
     */
    @TableField("SealPerSon")
    private String SealPerSon;

    /**
     * 附件
     */
    @TableField("FrameURL")
    private String FrameURL;

    /**
     * 审批人
     */
    @TableField("CheckPerSon")
    private String CheckPerSon;

    /**
     * 关联合同
     */
    @TableField("RelationContractID")
    private String RelationContractID;

    @TableField("PurchaseMoney")
    private String PurchaseMoney;

    @TableField("SaleMoney")
    private String SaleMoney;

    /**
     * 合同起草时间
     */
    @TableField("PrepareDate")
    private LocalDateTime PrepareDate;

    /**
     * 修改意见
     */
    @TableField("BackRemarks")
    private String BackRemarks;

    @TableField("TempDate")
    private LocalDateTime TempDate;

    @TableField("SendCheckDate")
    private LocalDateTime SendCheckDate;

    /**
     * 内部合同分类
     */
    @TableField("InnerContractType")
    private Integer InnerContractType;

    /**
     * 合同废弃人
     */
    @TableField("DiscardUserID")
    private String DiscardUserID;

    /**
     * 合同废弃人姓名
     */
    @TableField("DiscardUserName")
    private String DiscardUserName;

    /**
     * 是否有质保金
     */
    @TableField("IsGuarantee")
    private Integer IsGuarantee;

    /**
     * 验证发起方是否完成签名
     */
    @TableField("IsElectronicSignature")
    private Integer IsElectronicSignature;

    /**
     * 验证发起方是否完成签名
     */
    @TableField("IsElectronicSeal")
    private Integer IsElectronicSeal;

    /**
     * 验证发起方是否完成签名
     */
    @TableField("CustomizationType")
    private Integer CustomizationType;

    /**
     * 验证发起方是否完成签名
     */
    @TableField("IsUseElectronicSignature")
    private Integer IsUseElectronicSignature;

    /**
     * 验证发起方是否完成签名
     */
    @TableField("IsUseElectronicSeal")
    private Integer IsUseElectronicSeal;

    /**
     * 内部合同退回
     */
    @TableField("InnerContractBack")
    private Integer InnerContractBack;

    /**
     * 内部合同退回消息ID
     */
    @TableField("InnerContractBackMsgID")
    private Integer InnerContractBackMsgID;

    /**
     * 是否从合同
     */
    @TableField("IsSlaveContract")
    private Integer IsSlaveContract;

    /**
     * 主合同ID
     */
    @TableField("MasterContractID")
    private String MasterContractID;

    /**
     * 是否线上主合同
     */
    @TableField("IsOnlineMaster")
    private Integer IsOnlineMaster;
    /**
     * 修改说明
     */
    @TableField("ChangeRemark")
    private String ChangeRemark;

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }

    public String getInnerContractID() {
        return InnerContractID;
    }

    public void setInnerContractID(String InnerContractID) {
        this.InnerContractID = InnerContractID;
    }

    public String getPContractID() {
        return PContractID;
    }

    public void setPContractID(String PContractID) {
        this.PContractID = PContractID;
    }

    public Integer getIsFrameContract() {
        return IsFrameContract;
    }

    public void setIsFrameContract(Integer IsFrameContract) {
        this.IsFrameContract = IsFrameContract;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }

    public Integer getMoneySource() {
        return MoneySource;
    }

    public void setMoneySource(Integer MoneySource) {
        this.MoneySource = MoneySource;
    }

    public String getMoneySource2() {
        return MoneySource2;
    }

    public void setMoneySource2(String MoneySource2) {
        this.MoneySource2 = MoneySource2;
    }

    public Integer getSelectWay1() {
        return SelectWay1;
    }

    public void setSelectWay1(Integer SelectWay1) {
        this.SelectWay1 = SelectWay1;
    }

    public Integer getSelectWay2() {
        return SelectWay2;
    }

    public void setSelectWay2(Integer SelectWay2) {
        this.SelectWay2 = SelectWay2;
    }

    public String getSelectWay3() {
        return SelectWay3;
    }

    public void setSelectWay3(String SelectWay3) {
        this.SelectWay3 = SelectWay3;
    }

    public BigDecimal getPlanMoney() {
        return PlanMoney;
    }

    public void setPlanMoney(BigDecimal PlanMoney) {
        this.PlanMoney = PlanMoney;
    }

    public Integer getPlanMoneyCurrency() {
        return PlanMoneyCurrency;
    }

    public void setPlanMoneyCurrency(Integer PlanMoneyCurrency) {
        this.PlanMoneyCurrency = PlanMoneyCurrency;
    }

    public LocalDateTime getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(LocalDateTime EffectiveDate) {
        this.EffectiveDate = EffectiveDate;
    }

    public String getImportantDoc() {
        return ImportantDoc;
    }

    public void setImportantDoc(String ImportantDoc) {
        this.ImportantDoc = ImportantDoc;
    }

    public Integer getImportantType() {
        return ImportantType;
    }

    public void setImportantType(Integer ImportantType) {
        this.ImportantType = ImportantType;
    }

    public Integer getMySignBodyCode() {
        return MySignBodyCode;
    }

    public void setMySignBodyCode(Integer MySignBodyCode) {
        this.MySignBodyCode = MySignBodyCode;
    }

    public String getMySignBodyName() {
        return MySignBodyName;
    }

    public void setMySignBodyName(String MySignBodyName) {
        this.MySignBodyName = MySignBodyName;
    }

    public String getMySignPerson() {
        return MySignPerson;
    }

    public void setMySignPerson(String MySignPerson) {
        this.MySignPerson = MySignPerson;
    }

    public String getMySignPersonName() {
        return MySignPersonName;
    }

    public void setMySignPersonName(String MySignPersonName) {
        this.MySignPersonName = MySignPersonName;
    }

    public String getMySignPersonPhone() {
        return MySignPersonPhone;
    }

    public void setMySignPersonPhone(String MySignPersonPhone) {
        this.MySignPersonPhone = MySignPersonPhone;
    }

    public String getMySignPersonCard() {
        return MySignPersonCard;
    }

    public void setMySignPersonCard(String MySignPersonCard) {
        this.MySignPersonCard = MySignPersonCard;
    }

    public String getMySignPersonUnit() {
        return MySignPersonUnit;
    }

    public void setMySignPersonUnit(String MySignPersonUnit) {
        this.MySignPersonUnit = MySignPersonUnit;
    }

    public String getMySignPersonDept() {
        return MySignPersonDept;
    }

    public void setMySignPersonDept(String MySignPersonDept) {
        this.MySignPersonDept = MySignPersonDept;
    }

    public String getMySignPersonPostion() {
        return MySignPersonPostion;
    }

    public void setMySignPersonPostion(String MySignPersonPostion) {
        this.MySignPersonPostion = MySignPersonPostion;
    }

    public LocalDateTime getMySignDate() {
        return MySignDate;
    }

    public void setMySignDate(LocalDateTime MySignDate) {
        this.MySignDate = MySignDate;
    }

    public String getMySealPerSon() {
        return MySealPerSon;
    }

    public void setMySealPerSon(String MySealPerSon) {
        this.MySealPerSon = MySealPerSon;
    }

    public LocalDateTime getMySealDate() {
        return MySealDate;
    }

    public String getUseSignetApprover() {
		return UseSignetApprover;
	}

	public void setUseSignetApprover(String useSignetApprover) {
		UseSignetApprover = useSignetApprover;
	}

	public void setMySealDate(LocalDateTime MySealDate) {
        this.MySealDate = MySealDate;
    }

    public Integer getMySealTimes() {
        return MySealTimes;
    }

    public void setMySealTimes(Integer MySealTimes) {
        this.MySealTimes = MySealTimes;
    }

    public Integer getIsSeal() {
        return IsSeal;
    }

    public void setIsSeal(Integer IsSeal) {
        this.IsSeal = IsSeal;
    }

    public Integer getIsESeal() {
        return IsESeal;
    }

    public void setIsESeal(Integer IsESeal) {
        this.IsESeal = IsESeal;
    }

    public String getTextServer() {
        return TextServer;
    }

    public void setTextServer(String TextServer) {
        this.TextServer = TextServer;
    }

    public LocalDateTime getServerTime() {
        return ServerTime;
    }

    public void setServerTime(LocalDateTime ServerTime) {
        this.ServerTime = ServerTime;
    }

    public Integer getTextSource() {
        return TextSource;
    }

    public void setTextSource(Integer TextSource) {
        this.TextSource = TextSource;
    }

    public String getSealRemark() {
        return SealRemark;
    }

    public void setSealRemark(String SealRemark) {
        this.SealRemark = SealRemark;
    }

    public String getOtherSignPerson2() {
        return OtherSignPerson2;
    }

    public void setOtherSignPerson2(String OtherSignPerson2) {
        this.OtherSignPerson2 = OtherSignPerson2;
    }

    public String getOtherSignPerson3() {
        return OtherSignPerson3;
    }

    public void setOtherSignPerson3(String OtherSignPerson3) {
        this.OtherSignPerson3 = OtherSignPerson3;
    }

    public String getSignAddr() {
        return SignAddr;
    }

    public void setSignAddr(String SignAddr) {
        this.SignAddr = SignAddr;
    }

    public Integer getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(Integer PayMethod) {
        this.PayMethod = PayMethod;
    }

    public String getOffereeID2() {
        return OffereeID2;
    }

    public void setOffereeID2(String OffereeID2) {
        this.OffereeID2 = OffereeID2;
    }

    public String getOffereeName2() {
        return OffereeName2;
    }

    public void setOffereeName2(String OffereeName2) {
        this.OffereeName2 = OffereeName2;
    }

    public Integer getOffereeType2() {
        return OffereeType2;
    }

    public void setOffereeType2(Integer OffereeType2) {
        this.OffereeType2 = OffereeType2;
    }

    public String getOffereeID3() {
        return OffereeID3;
    }

    public void setOffereeID3(String OffereeID3) {
        this.OffereeID3 = OffereeID3;
    }

    public String getOffereeName3() {
        return OffereeName3;
    }

    public void setOffereeName3(String OffereeName3) {
        this.OffereeName3 = OffereeName3;
    }

    public Integer getOffereeType3() {
        return OffereeType3;
    }

    public void setOffereeType3(Integer OffereeType3) {
        this.OffereeType3 = OffereeType3;
    }

    public String getOffereeID4() {
        return OffereeID4;
    }

    public void setOffereeID4(String OffereeID4) {
        this.OffereeID4 = OffereeID4;
    }

    public String getOffereeName4() {
        return OffereeName4;
    }

    public void setOffereeName4(String OffereeName4) {
        this.OffereeName4 = OffereeName4;
    }

    public Integer getOffereeType4() {
        return OffereeType4;
    }

    public void setOffereeType4(Integer OffereeType4) {
        this.OffereeType4 = OffereeType4;
    }

    public Integer getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(Integer CompanyType) {
        this.CompanyType = CompanyType;
    }

    public Integer getIsInnerContract() {
        return IsInnerContract;
    }

    public void setIsInnerContract(Integer IsInnerContract) {
        this.IsInnerContract = IsInnerContract;
    }

    public String getInnerOperator() {
        return InnerOperator;
    }

    public void setInnerOperator(String InnerOperator) {
        this.InnerOperator = InnerOperator;
    }

    public Integer getIsRelatedTransaction() {
        return IsRelatedTransaction;
    }

    public void setIsRelatedTransaction(Integer IsRelatedTransaction) {
        this.IsRelatedTransaction = IsRelatedTransaction;
    }

    public Integer getIsConcernForeign() {
        return IsConcernForeign;
    }

    public void setIsConcernForeign(Integer IsConcernForeign) {
        this.IsConcernForeign = IsConcernForeign;
    }

    public Integer getTextType() {
        return TextType;
    }

    public void setTextType(Integer TextType) {
        this.TextType = TextType;
    }

    public String getTextModel() {
        return TextModel;
    }

    public void setTextModel(String TextModel) {
        this.TextModel = TextModel;
    }

    public Integer getNeedPrintCount() {
        return NeedPrintCount;
    }

    public void setNeedPrintCount(Integer NeedPrintCount) {
        this.NeedPrintCount = NeedPrintCount;
    }

    public Integer getPerFormIsConfirm() {
        return PerFormIsConfirm;
    }

    public void setPerFormIsConfirm(Integer PerFormIsConfirm) {
        this.PerFormIsConfirm = PerFormIsConfirm;
    }

    public String getPerFormNotConfirm() {
        return PerFormNotConfirm;
    }

    public void setPerFormNotConfirm(String PerFormNotConfirm) {
        this.PerFormNotConfirm = PerFormNotConfirm;
    }

    public LocalDateTime getPerFormStartDate() {
        return PerFormStartDate;
    }

    public void setPerFormStartDate(LocalDateTime PerFormStartDate) {
        this.PerFormStartDate = PerFormStartDate;
    }

    public LocalDateTime getPerFormEndDate() {
        return PerFormEndDate;
    }

    public void setPerFormEndDate(LocalDateTime PerFormEndDate) {
        this.PerFormEndDate = PerFormEndDate;
    }

    public LocalDateTime getSettleDeadline() {
        return SettleDeadline;
    }

    public void setSettleDeadline(LocalDateTime SettleDeadline) {
        this.SettleDeadline = SettleDeadline;
    }

    public Integer getIssueSolveMode() {
        return IssueSolveMode;
    }

    public void setIssueSolveMode(Integer IssueSolveMode) {
        this.IssueSolveMode = IssueSolveMode;
    }

    public Integer getIsImprest() {
        return IsImprest;
    }

    public void setIsImprest(Integer IsImprest) {
        this.IsImprest = IsImprest;
    }

    public BigDecimal getImprest() {
        return Imprest;
    }

    public void setImprest(BigDecimal Imprest) {
        this.Imprest = Imprest;
    }

    public Integer getImprestCurrency() {
        return ImprestCurrency;
    }

    public void setImprestCurrency(Integer ImprestCurrency) {
        this.ImprestCurrency = ImprestCurrency;
    }

    public String getCoContractNum() {
        return CoContractNum;
    }

    public void setCoContractNum(String CoContractNum) {
        this.CoContractNum = CoContractNum;
    }

    public String getDiscardExplain() {
        return DiscardExplain;
    }

    public void setDiscardExplain(String DiscardExplain) {
        this.DiscardExplain = DiscardExplain;
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

    public LocalDateTime getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(LocalDateTime ReviewDate) {
        this.ReviewDate = ReviewDate;
    }

    public String getReportedhistory() {
        return Reportedhistory;
    }

    public void setReportedhistory(String Reportedhistory) {
        this.Reportedhistory = Reportedhistory;
    }

    public String getModifiedReason() {
        return ModifiedReason;
    }

    public void setModifiedReason(String ModifiedReason) {
        this.ModifiedReason = ModifiedReason;
    }

    public LocalDateTime getSealRecordDate() {
        return SealRecordDate;
    }

    public void setSealRecordDate(LocalDateTime SealRecordDate) {
        this.SealRecordDate = SealRecordDate;
    }

    public String getFrameNum() {
        return FrameNum;
    }

    public void setFrameNum(String FrameNum) {
        this.FrameNum = FrameNum;
    }

    public Integer getFrameSource() {
        return FrameSource;
    }

    public void setFrameSource(Integer FrameSource) {
        this.FrameSource = FrameSource;
    }

    public String getOUFax() {
        return OUFax;
    }

    public void setOUFax(String OUFax) {
        this.OUFax = OUFax;
    }

    public String getIsConcernForeignType() {
        return IsConcernForeignType;
    }

    public void setIsConcernForeignType(String IsConcernForeignType) {
        this.IsConcernForeignType = IsConcernForeignType;
    }

    public String getTotalAmountPayMethod() {
        return TotalAmountPayMethod;
    }

    public void setTotalAmountPayMethod(String TotalAmountPayMethod) {
        this.TotalAmountPayMethod = TotalAmountPayMethod;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public Integer getIsEcContract() {
        return IsEcContract;
    }

    public void setIsEcContract(Integer IsEcContract) {
        this.IsEcContract = IsEcContract;
    }

    public String getSealPerSon() {
        return SealPerSon;
    }

    public void setSealPerSon(String SealPerSon) {
        this.SealPerSon = SealPerSon;
    }

    public String getFrameURL() {
        return FrameURL;
    }

    public void setFrameURL(String FrameURL) {
        this.FrameURL = FrameURL;
    }

    public String getCheckPerSon() {
        return CheckPerSon;
    }

    public void setCheckPerSon(String CheckPerSon) {
        this.CheckPerSon = CheckPerSon;
    }

    public String getRelationContractID() {
        return RelationContractID;
    }

    public void setRelationContractID(String RelationContractID) {
        this.RelationContractID = RelationContractID;
    }

    public String getPurchaseMoney() {
        return PurchaseMoney;
    }

    public void setPurchaseMoney(String PurchaseMoney) {
        this.PurchaseMoney = PurchaseMoney;
    }

    public String getSaleMoney() {
        return SaleMoney;
    }

    public void setSaleMoney(String SaleMoney) {
        this.SaleMoney = SaleMoney;
    }

    public LocalDateTime getPrepareDate() {
        return PrepareDate;
    }

    public void setPrepareDate(LocalDateTime PrepareDate) {
        this.PrepareDate = PrepareDate;
    }

    public String getBackRemarks() {
        return BackRemarks;
    }

    public void setBackRemarks(String BackRemarks) {
        this.BackRemarks = BackRemarks;
    }

    public LocalDateTime getTempDate() {
        return TempDate;
    }

    public void setTempDate(LocalDateTime TempDate) {
        this.TempDate = TempDate;
    }

    public LocalDateTime getSendCheckDate() {
        return SendCheckDate;
    }

    public void setSendCheckDate(LocalDateTime SendCheckDate) {
        this.SendCheckDate = SendCheckDate;
    }

    public Integer getInnerContractType() {
        return InnerContractType;
    }

    public void setInnerContractType(Integer InnerContractType) {
        this.InnerContractType = InnerContractType;
    }

    public String getDiscardUserID() {
        return DiscardUserID;
    }

    public void setDiscardUserID(String DiscardUserID) {
        this.DiscardUserID = DiscardUserID;
    }

    public String getDiscardUserName() {
        return DiscardUserName;
    }

    public void setDiscardUserName(String DiscardUserName) {
        this.DiscardUserName = DiscardUserName;
    }

    public Integer getIsGuarantee() {
        return IsGuarantee;
    }

    public void setIsGuarantee(Integer IsGuarantee) {
        this.IsGuarantee = IsGuarantee;
    }

    public Integer getIsElectronicSignature() {
        return IsElectronicSignature;
    }

    public void setIsElectronicSignature(Integer IsElectronicSignature) {
        this.IsElectronicSignature = IsElectronicSignature;
    }

    public Integer getIsElectronicSeal() {
        return IsElectronicSeal;
    }

    public void setIsElectronicSeal(Integer IsElectronicSeal) {
        this.IsElectronicSeal = IsElectronicSeal;
    }

    public Integer getCustomizationType() {
        return CustomizationType;
    }

    public void setCustomizationType(Integer CustomizationType) {
        this.CustomizationType = CustomizationType;
    }

    public Integer getIsUseElectronicSignature() {
        return IsUseElectronicSignature;
    }

    public void setIsUseElectronicSignature(Integer IsUseElectronicSignature) {
        this.IsUseElectronicSignature = IsUseElectronicSignature;
    }

    public Integer getIsUseElectronicSeal() {
        return IsUseElectronicSeal;
    }

    public void setIsUseElectronicSeal(Integer IsUseElectronicSeal) {
        this.IsUseElectronicSeal = IsUseElectronicSeal;
    }

    public Integer getInnerContractBack() {
        return InnerContractBack;
    }

    public void setInnerContractBack(Integer InnerContractBack) {
        this.InnerContractBack = InnerContractBack;
    }

    public Integer getInnerContractBackMsgID() {
        return InnerContractBackMsgID;
    }

    public void setInnerContractBackMsgID(Integer InnerContractBackMsgID) {
        this.InnerContractBackMsgID = InnerContractBackMsgID;
    }

    public Integer getIsSlaveContract() {
        return IsSlaveContract;
    }

    public void setIsSlaveContract(Integer isSlaveContract) {
        IsSlaveContract = isSlaveContract;
    }

    public String getMasterContractID() {
        return MasterContractID;
    }

    public void setMasterContractID(String masterContractID) {
        MasterContractID = masterContractID;
    }

    public Integer getIsOnlineMaster() {
        return IsOnlineMaster;
    }

    public void setIsOnlineMaster(Integer isOnlineMaster) {
        IsOnlineMaster = isOnlineMaster;
    }

    public String getChangeRemark() {
        return ChangeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        ChangeRemark = changeRemark;
    }

    @Override
    public String toString() {
        return "CrContractinfo{" +
                "ContractID=" + ContractID +
                ", InnerContractID=" + InnerContractID +
                ", PContractID=" + PContractID +
                ", IsFrameContract=" + IsFrameContract +
                ", ProjectID=" + ProjectID +
                ", MoneySource=" + MoneySource +
                ", MoneySource2=" + MoneySource2 +
                ", SelectWay1=" + SelectWay1 +
                ", SelectWay2=" + SelectWay2 +
                ", SelectWay3=" + SelectWay3 +
                ", PlanMoney=" + PlanMoney +
                ", PlanMoneyCurrency=" + PlanMoneyCurrency +
                ", EffectiveDate=" + EffectiveDate +
                ", ImportantDoc=" + ImportantDoc +
                ", ImportantType=" + ImportantType +
                ", MySignBodyCode=" + MySignBodyCode +
                ", MySignBodyName=" + MySignBodyName +
                ", MySignPerson=" + MySignPerson +
                ", MySignPersonName=" + MySignPersonName +
                ", MySignPersonPhone=" + MySignPersonPhone +
                ", MySignPersonCard=" + MySignPersonCard +
                ", MySignPersonUnit=" + MySignPersonUnit +
                ", MySignPersonDept=" + MySignPersonDept +
                ", MySignPersonPostion=" + MySignPersonPostion +
                ", MySignDate=" + MySignDate +
                ", MySealPerSon=" + MySealPerSon +
                ", MySealDate=" + MySealDate +
                ", MySealTimes=" + MySealTimes +
                ", UseSignetApprover=" + UseSignetApprover +
                ", IsSeal=" + IsSeal +
                ", IsESeal=" + IsESeal +
                ", TextServer=" + TextServer +
                ", ServerTime=" + ServerTime +
                ", TextSource=" + TextSource +
                ", SealRemark=" + SealRemark +
                ", OtherSignPerson2=" + OtherSignPerson2 +
                ", OtherSignPerson3=" + OtherSignPerson3 +
                ", SignAddr=" + SignAddr +
                ", PayMethod=" + PayMethod +
                ", OffereeID2=" + OffereeID2 +
                ", OffereeName2=" + OffereeName2 +
                ", OffereeType2=" + OffereeType2 +
                ", OffereeID3=" + OffereeID3 +
                ", OffereeName3=" + OffereeName3 +
                ", OffereeType3=" + OffereeType3 +
                ", OffereeID4=" + OffereeID4 +
                ", OffereeName4=" + OffereeName4 +
                ", OffereeType4=" + OffereeType4 +
                ", CompanyType=" + CompanyType +
                ", IsInnerContract=" + IsInnerContract +
                ", InnerOperator=" + InnerOperator +
                ", IsRelatedTransaction=" + IsRelatedTransaction +
                ", IsConcernForeign=" + IsConcernForeign +
                ", TextType=" + TextType +
                ", TextModel=" + TextModel +
                ", NeedPrintCount=" + NeedPrintCount +
                ", PerFormIsConfirm=" + PerFormIsConfirm +
                ", PerFormNotConfirm=" + PerFormNotConfirm +
                ", PerFormStartDate=" + PerFormStartDate +
                ", PerFormEndDate=" + PerFormEndDate +
                ", SettleDeadline=" + SettleDeadline +
                ", IssueSolveMode=" + IssueSolveMode +
                ", IsImprest=" + IsImprest +
                ", Imprest=" + Imprest +
                ", ImprestCurrency=" + ImprestCurrency +
                ", CoContractNum=" + CoContractNum +
                ", DiscardExplain=" + DiscardExplain +
                ", LogicDel=" + LogicDel +
                ", CreatedBy=" + CreatedBy +
                ", CreatedDate=" + CreatedDate +
                ", ModifiedBy=" + ModifiedBy +
                ", ModifiedDate=" + ModifiedDate +
                ", Oulabel=" + Oulabel +
                ", ReviewDate=" + ReviewDate +
                ", Reportedhistory=" + Reportedhistory +
                ", ModifiedReason=" + ModifiedReason +
                ", SealRecordDate=" + SealRecordDate +
                ", FrameNum=" + FrameNum +
                ", FrameSource=" + FrameSource +
                ", OUFax=" + OUFax +
                ", IsConcernForeignType=" + IsConcernForeignType +
                ", TotalAmountPayMethod=" + TotalAmountPayMethod +
                ", ProjectName=" + ProjectName +
                ", IsEcContract=" + IsEcContract +
                ", SealPerSon=" + SealPerSon +
                ", FrameURL=" + FrameURL +
                ", CheckPerSon=" + CheckPerSon +
                ", RelationContractID=" + RelationContractID +
                ", PurchaseMoney=" + PurchaseMoney +
                ", SaleMoney=" + SaleMoney +
                ", PrepareDate=" + PrepareDate +
                ", BackRemarks=" + BackRemarks +
                ", TempDate=" + TempDate +
                ", SendCheckDate=" + SendCheckDate +
                ", InnerContractType=" + InnerContractType +
                ", DiscardUserID=" + DiscardUserID +
                ", DiscardUserName=" + DiscardUserName +
                ", IsGuarantee=" + IsGuarantee +
                ", IsElectronicSignature=" + IsElectronicSignature +
                ", IsElectronicSeal=" + IsElectronicSeal +
                ", CustomizationType=" + CustomizationType +
                ", IsUseElectronicSignature=" + IsUseElectronicSignature +
                ", IsUseElectronicSeal=" + IsUseElectronicSeal +
                ", InnerContractBack=" + InnerContractBack +
                ", InnerContractBackMsgID=" + InnerContractBackMsgID +
                ", ChangeRemark=" + ChangeRemark +
                "}";
    }
}
