package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.List;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeDictBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeDictService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeDict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LitigateDisputeDictService {

    @Autowired
    private ILitigateDisputeDictService idictService;

    public Long save(DisputeDictBo bo) {
        FwLitigateDisputeDict entity = DisputePojoConverter.boToEntity(bo);
        idictService.save(entity);
        return entity.getfId();
    }

    public Long update(DisputeDictBo bo) {
        FwLitigateDisputeDict entity = DisputePojoConverter.boToEntity(bo);
        idictService.updateById(entity);
        return entity.getfId();
    }

    public DisputeDictBo getById(String id) {
        FwLitigateDisputeDict entity = idictService.getById(id);
        return DisputePojoConverter.entityToBo(entity);
    }

    public void delete(Long id) {
        idictService.removeById(id);
    }

    public void delete(List<Long> ids) {
        idictService.removeByIds(ids);
    }

    public List<DisputeDictBo> getAll() {
        List<FwLitigateDisputeDict> list = idictService.lambdaQuery().list();
        return DisputePojoConverter.dictEntitiesToBoList(list);
    }

    public DisputeDictBo findByName(String dictName) {
        FwLitigateDisputeDict dict = idictService.lambdaQuery()
            .eq(FwLitigateDisputeDict::getfName, dictName)
            .eq(FwLitigateDisputeDict::getfState, 0)
            .last("LIMIT 1")
            .one();
        return DisputePojoConverter.entityToBo(dict);
    }
}