package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import lombok.Data;

@Data
public class DisputeBo {

    private Long fId;
    
    /**
     * 经办用户ID
     */
    private Integer fUserId;

    /**
     * 案件编号
     */
    private String fCode;
    
    /**
     * 案号
     */
    private String fCaseCode;


    /**
     * 纠纷名称
     */
    private String fName;
    
    /**
     * 案件行政区域-省
     */
    private String fProvince;
    /**
     * 案件行政区域-市
     */
    private String fPrefecture;
    /**
     * 案件行政区域-县区
     */
    private String fRegion;
    
    private String fProvinceName;
    private String fPrefectureName;
    private String fRegionName;
    
    /**
     * 保存时间
     */
    private Date fSaveTime;

    /**
     * 是否涉外纠纷
     */
    private Byte fIsForeignRelated;

    /**
     * 案发时间
     */
    private String fIncidentDate;

    /**
     * 纠纷类别
     */
    private String fType;
    
    /**
     * 纠纷类别2
     */
    private String fType2;
    
    /**
     * 纠纷类别3
     */
    private String fType3;

    /**
     * 纠纷类别
     */
    private String fTypeName;
    
    /**
     * 纠纷类别2
     */
    private String fType2Name;
    
    /**
     * 纠纷类别3
     */
    private String fType3Name;
    
    /**
     * 是否涉刑
     */
    private Byte fIsCriminalInvolved;

    /**
     * 内外部纠纷 0内部 1外部
     */
    private Long fIsExternal;

    /**
     * 处理方式
     */
    private Long fSettleMethod;

    /**
     * 纠纷性质 0一般 1重大
     */
    private Long fIsMajor;

    /**
     * 纠纷性质 一般/重大
     */
    private String fIsMajorString;

    /**
     * 涉及金额
     */
    private BigDecimal fRelatedAmount;

    /**
     * 涉及金额的币种
     */
    private Long fRelatedCurrency;

    /**
     * 是否涉及维稳
     */
    private Byte fIsAboutPetition;

    /**
     * 信访案件ID
     */
    private Long fkPetitionId;

    /**
     * 信访案件名称
     */
    private String fkPetitionName;

    /**
     * 是否债权清收
     */
    private Byte fIsDebtCollection;

    /**
     * 是否财产保全
     */
    private Byte fIsPropertyPreservation;

    /**
     * 是否有担保
     */
    private Byte fIsGuaranted;
    
    /**
     * 是否有预计损失
     */
    private Byte fHasLossEstimated;
    
    /**
     * 预计损失(万)
     */
    private BigDecimal fEstimatedLossAmount;
    
    /**
     * 预计损失币种
     */
    private Long fEstimatedLossCurrency;

    /**
     * 案件描述
     */
    private String fDescription;

    /**
     * 拟处理方案
     */
    private String fPlan;

    /**
     * 附件ID
     */
    private Long fkAttachmentId;

    /**
     * 附件路径
     */
    private String fkAttachmentPath;

    /**
     * 附件文件名
     */
    private String fkAttachmentName;

    /**
     * 附件扩展名
     */
    private String fkAttachmentExt;

    /**
     * 填报部门ID
     */
    private Long fkReportedOrgId;

    /**
     * 填报部门编码
     */
    private String fkReportedOrgCode;

    /**
     * 填报部门名称
     */
    private String fkReportedOrgName;

    /**
     * 填报人ID
     */
    private Long fkReportedPersonId;
    
    /**
     * 填报人姓名
     */
    private String fkReportedPersonName;

    /**
     * 属性
     */
    private Integer fkAttribute;
    
    /**
     * 关联历史案件ID
     */
    private Long fkHistoryId;
    
    /**
     * 关联历史案件名称
     */
    private String fkHistoryName;

    /**
     * 是否废弃
     */
    private Byte fIsDiscard;

    /**
     * 单据状态(办结 废弃)
     */
    private Byte fStatus;

    /**
     * 废弃原因
     */
    private String fDiscardDescription;

    /**
     * 办结时间
     */
    private String fSettleDate;

    /**
     * 办结信息
     */
    private String fSettleDescription;

    /**
     * 备注
     */
    private String fRemark;

    /**
     * 是否已归档
     */
    private Integer fArchived;

    /**
     * 排序
     */
    private Integer fSort;
    /**
     * 来自 0-本系统 1-合同
     */
    private Integer fSource;
    /**
     * 外部系统ID
     */
    private String fExternalId;

    /**
     * 财产保全额
     */
    private BigDecimal propertyPreservation;
    /**
     * 是否经济类纠纷
     */
    private Integer isEconomic;
    /**
     * 经济类纠纷-金额
     */
    private BigDecimal economicAmt;
    /**
     * 经济类纠纷-利息
     */
    private BigDecimal economicInterest;
    /**
     * 经济类纠纷-违约金
     */
    private BigDecimal economicLiquidatedDamage;
    /**
     * 经济类纠纷-股权
     */
    private BigDecimal economicEquity;
    /**
     * 被冻结金额
     */
    private BigDecimal amtFrozen;
    /**
     * 冻结他人金额
     */
    private BigDecimal oppositeAmtFrozen;
    /**
     * 风险等级
     */
    private String riskLvl;

    /**
     * 我方
     */
    private List<DisputeOursideBo> ourside;
    
    /**
     * 对方
     */
    private List<DisputeOppositeBo> opposite;
    
    /**
     * 第三方
     */
    private List<DisputeOthersideBo> otherside;

    /**
     * 我方代理律所
     */
    private List<DisputeOursideFirmBo> ourFirm;

    /**
     * 对方代理律所
     */
    private List<DisputeOppositeFirmBo> oppositeFirm;

    /**
     * 进度
     */
    private List<DisputeProgressBo> progresses;

    /**
     * 处理方式变更
     */
    private List<DisputeSettleChangeBo> settleChanges;

    /**
     * 结案信息
     */
    private DisputeSettledBo settleInfo;
    
    /**
     * 执行信息
     */
    private DisputeExecuteBo executeInfo;
    
    /**
     * 相关纠纷列表
     */
    private List<DisputeVo> related;
        
//    /**
//     * 是否删除 1：删除，0：未删除
//     */
//    private Integer fIsdel;
//
//    private Long fCreateId;
//
//    /**
//     * 创建人账号
//     */
//    private String fCreateUser;
//
//    /**
//     * 创建人姓名
//     */
//    private String fCreateName;
//
//    /**
//     * 创建时间
//     */
//    private Date fCreateTime;
//
//    private Long fUpdateId;
//
//    /**
//     * 修改人账号
//     */
//    private String fUpdateUser;
//
//    /**
//     * 修改人姓名
//     */
//    private String fUpdateName;
//
//    /**
//     * 修改时间
//     */
//    private Date fUpdateTime;

    private String oppositeName;
    
    private List<DisputeFileDictBo> files;
}
