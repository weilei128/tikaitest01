package com.pcitc.szgt.contract.appmanager.model;

import com.pcitc.szgt.contract.appmanager.entity.*;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.share.entity.SysDictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * describe:
 * 单位配置 的返回值和参数
 *
 * @author juncheng.zhu
 * @date 2019/04/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitConfigResult {

    /**
     *单位配置ID
     */
    public String orgConfigID;

    /**
     *组织机构ID
     */
    public Integer ouid;

    /**
     * 组织机构
     */
    public Integer oulabel;

    /**
     * 单位配置的实体对象
     */
    public AmUnitconfiguration unitConfiguration;

    /**
     * 流程配置员名
     */
    public String flowDistributerName;

    /**
     * 组织机构所属实体
     */
    public SysOrganiseunitBelong organiseUnitBelong;

    /**
     * 单位配置告的实体对象列表
     */
    public List<AmUnitconfiguration> unitConfigurationList;

    /**
    * 附件的实体对象
    */
    public AttachmentResultVo attachmentInfo;

    /**
     * 集团水印实体对象
     */
    public AttachmentResultVo attGroupWaterMark;

    /**
     * 股份水印实体对象
     */
    public AttachmentResultVo attShareWaterMark;

    /**
     * 资产水印实体对象
     */
    public AttachmentResultVo attAssetWaterMark;

    /**
     * 集团电子印章实体对象
     */
    public AttachmentResultVo attGroupElectronicSeal;

    /**
     * 股份电子印章实体对象
     */
    public AttachmentResultVo attStockElectronicSeal;

    /**
     * 资产电子印章实体对象
     */
    public AttachmentResultVo attAssetElectronicSeal;

    /**
     * 廉洁责任书实体对象
     */
    public AttachmentResultVo attHonestDuty;

    /**
     * 安全协议模板实体对象
     */
    public AttachmentResultVo attSafeProtocol;

    /**
     * 合同授权委托书实体对象
     */
    public AttachmentResultVo attEntrust;

    /**
     * 履行付款审查审批表实体对象
     */
    public AttachmentResultVo attPerform;

    /**
     * 单位配置生效时间
     */
    public LocalDate effectDate;

    /**
     * 修改人
     */
    public String modifiedBy;

    /**
     * 修改时间
     */
    public LocalDate modifiedDate;

    /**
     * 附件类型ID
     */
    public String entityTypeID;

    /**
     * 资产水印ID
     */
    public String assetWaterMarkID;

    /**
     * 集团水印ID
     */
    public String groupWaterMarkID;

    /**
     * 股份水印ID
     */
    public String shareWaterMarkID;

    /**
     * 廉洁责任书ID
     */
    public String honestDutyID;

    /**
     * 安全协议模板ID
     */
    public String safeProtocolID;

    /**
     * 合同授权委托书ID
     */
    public String entrustID;

    /**
     * 是否为企业
     */
    public Boolean isEnterprise;

    /**
     * 是否为单位
     */
    public Boolean isUnit;

    /**
     * 是否启用相对人复核
     */
    public Boolean pOffereeIsEnabled;

    /**
     * 相对人配置
     */
    public Boolean offereeEnabled;

    /**
     * 企业单位个性配置实体对象
     */
    public AmUnitselfconfig unitSelfConfig;

    /**
     * 流程配置实体对象
     */
    public SysInterfaceconfig interfaceConfig;

    /**
     * 企业单位个性配置的实体对象列表
     */
    public List<AmUnitselfconfig> unitSelfConfigList;

    /**
     * 根据结算付款接口的配置是否显示对应的流程配置
     */
    public List<SysDictionary> paymentDiclist;

    /**
     * 审批页面配置
     */
    public List<SysDictionary> applyPageConfigDiclist;

    /**
     * 审批开关
     */
    public List<SysDictionary> approvalSwitchList;

    /**
     * 相对人资历
     */
    public List<SysDictionary> offereeAdmittanceList;

    /**
     * 银行列表
     */
    public List<ZAmBank> banklist;

//    public static UnitConfigResult UnitConfigResult(UnitConfigResult orign) {
//        UnitConfigResult result = new UnitConfigResult();
//        BeanUtils.copyProperties(orign, result);  // 将dto转成pojo :result
//        return result;
//    }
}
