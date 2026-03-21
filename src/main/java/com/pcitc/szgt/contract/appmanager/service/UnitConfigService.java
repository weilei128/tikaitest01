package com.pcitc.szgt.contract.appmanager.service;

import com.pcitc.szgt.contract.appmanager.model.UnitConfigResult;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigResultVo;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigSaveVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.common.DataResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface UnitConfigService {

    /**
     * 保存单位配置
     *
     * @param unitConfigSaveVo
     * @return
     */
    public DataResult<?> saveUnitConfig(UnitConfigSaveVo unitConfigSaveVo);

    /**
     * 查询单位配置
     *
     * @param orgId
     * @return
     */
    public DataResult<UnitConfigResultVo> queryUnitConfig(@RequestParam String orgId);

    /**
     * 获取合同打印关联附件
     *
     * @return
     */
    public DataResult<?> getPrintAtta(String orgId);

    /**
     * 查询单位配置
     *
     * @param ouid
     * @return
     */
    @Deprecated
    public DataResult<UnitConfigResult> queryUnitConfigByOuid(Integer ouid);

    /**
     * 添加或修改单位配置
     *
     * @param unitConfigResult
     * @return
     */
    @Deprecated
    public DataResult<?> saveOrUpdateInformationbulletin(UnitConfigResult unitConfigResult);

    /*
     * 获取合同水印图片
     * */
    public DataResult<?> getWaterMark(String contractId);

    /**
     * 根据组织机构id获取水印
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> queryWatermarkByOrgId(Integer orgId);

    //获取廉洁责任书
    public List<AttachmentResultVo> queryHonestdutyByOrgId(Integer orgId);

    //获取安全协议
    public List<AttachmentResultVo> querySafeProtocolByOrgId(Integer orgId);

    //获取保密承诺函
    public List<AttachmentResultVo> queryKeepsecretByOrgId(Integer orgId);

    public Integer queryFinalDay(Integer orgId);

}
