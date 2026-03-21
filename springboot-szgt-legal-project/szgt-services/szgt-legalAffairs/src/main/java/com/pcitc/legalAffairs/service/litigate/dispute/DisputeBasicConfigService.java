package com.pcitc.legalAffairs.service.litigate.dispute;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBasicConfigBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.IDisputeBasicConfigService;
import com.pcitc.legalAffairs.po.dispute.DisputeBasicConfig;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeBasicConfigVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @description 基础配置服务类
 * @author leigang
 * @date 2020年4月3日 14:41:25
 *
 */
@Service
public class DisputeBasicConfigService {

    @Autowired
    private IDisputeBasicConfigService disputeBasicConfigService;


    @Transactional
    public void saveOrUpdate(DisputeBasicConfigBo disputeBasicConfigBo) {
        Long id = disputeBasicConfigBo.getfId();
        if (id != null && id > 0) {
            //更新
            DisputeBasicConfig basicConfig = disputeBasicConfigService.getById(id);
            if (basicConfig == null) {
                throw new BaseException("信息不存在请检查id", 500);
            }
            isUpdate(1, disputeBasicConfigBo);
            return;
        }
        isUpdate(0, disputeBasicConfigBo);
    }

    private void isUpdate(int isUpdate, DisputeBasicConfigBo disputeBasicConfigBo) {
        DisputeBasicConfig disputeBasicConfig = new DisputeBasicConfig();
        BeanUtils.copyProperties(disputeBasicConfigBo, disputeBasicConfig);
        disputeBasicConfig.setfContent(JSON.toJSONString(disputeBasicConfigBo.getBasicConfig()));
        if (isUpdate == 1) {
            disputeBasicConfigService.updateById(disputeBasicConfig);
            return;
        }
        disputeBasicConfigService.save(disputeBasicConfig);
    }

    public DisputeBasicConfigBo queryState(DisputeBasicConfigBo disputeBasicConfigBo) {
        DisputeBasicConfig disputeBasicConfig = disputeBasicConfigService.getOne(
                disputeBasicConfigService.lambdaQueryWrapper()
                        .eq(DisputeBasicConfig::getFkOrgId, disputeBasicConfigBo.getFkOrgId())
                        .eq(DisputeBasicConfig::getfType, disputeBasicConfigBo.getfType())
                        .eq(DisputeBasicConfig::getFkDictionaryTypeId, disputeBasicConfigBo.getFkDictionaryTypeId())

        );
        String content = disputeBasicConfig.getfContent();
        List<DisputeBasicConfigVo> disputeBasicConfigVos = JSON.parseArray(content, DisputeBasicConfigVo.class);
        DisputeBasicConfigBo basicConfigBo = new DisputeBasicConfigBo();
        BeanUtils.copyProperties(disputeBasicConfig, basicConfigBo);
        basicConfigBo.setBasicConfig(disputeBasicConfigVos);
        return basicConfigBo;
    }
}
