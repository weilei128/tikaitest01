package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettlingBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeProgressService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeProgress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 纠纷进展Service
 * @author meihongli
 */
@Service
public class LitigateDisputeProgressService {

    @Autowired
    private ILitigateDisputeProgressService idisputeProgressService;
    @Autowired
    private LitigateDisputeLawFirmService lawFirmService;
    @Autowired
    private LitigateDisputeFileDictService fileService;

    public Long save(DisputeProgressBo bo, Long disputeId, String disputeName) {
        FwLitigateDisputeProgress entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
        idisputeProgressService.save(entity);
        Long businessId = entity.getfId();
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.PROG, businessId);
        fileService.save(bo.getAttachments(), disputeId, DisputeAttachEnum.PROG_ATTACH, businessId);
        return businessId;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void save(List<DisputeProgressBo> bos, Long disputeId, String disputeName) {
        bos.forEach(bo -> save(bo, disputeId, disputeName));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Long update(DisputeProgressBo bo) {
        FwLitigateDisputeProgress entity = DisputePojoConverter.boToEntity(bo);
        idisputeProgressService.updateById(entity);
        Long businessId = entity.getfId();
        fileService.save(bo.getFiles(), bo.getFkDisputeId(), DisputeAttachEnum.PROG, businessId);
        fileService.save(bo.getAttachments(), bo.getFkDisputeId(), DisputeAttachEnum.PROG_ATTACH, businessId);
        return businessId;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(List<DisputeProgressBo> bos, Long disputeId, String disputeName) {
        List<DisputeProgressBo> neoData = bos.stream().filter(entity -> entity.getFId() == null).collect(Collectors.toList());
        List<DisputeProgressBo> toUpdate = bos.stream().filter(entity -> entity.getFId() != null).collect(Collectors.toList());
        List<Long> updatingIds = toUpdate.stream().map(DisputeProgressBo::getFId).collect(Collectors.toList());
        
        Optional.ofNullable(toUpdate).orElse(new ArrayList<>()).forEach(bo -> update(bo));
        idisputeProgressService.remove(idisputeProgressService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeProgress::getFkDisputeId, disputeId)
            .notIn(updatingIds != null && updatingIds.size() > 0, FwLitigateDisputeProgress::getfId, updatingIds));
        if (neoData != null) {
        	save(neoData, disputeId, disputeName);
		}
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long id) {
        idisputeProgressService.removeById(id);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public void delete(List<Long> ids) {
        idisputeProgressService.removeByIds(ids);
    }

    /**
     * 查询纠纷单下的进展情况
     * @param disputeId 纠纷单ID
     * @param eventOnly 只查找大事记
     */
    public List<DisputeProgressBo> getByDisputeId(String disputeId, boolean eventOnly) {
        List<FwLitigateDisputeProgress> list = idisputeProgressService.list(idisputeProgressService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeProgress::getFkDisputeId, disputeId)
            .eq(eventOnly, FwLitigateDisputeProgress::getfIsEvent, 1)
            .orderByAsc(FwLitigateDisputeProgress::getfBegindate));
        List<DisputeProgressBo> bos = DisputePojoConverter.progressEntitiesToBoList(list);
        if (bos == null) {
			return null;
		}
        bos.forEach(bo -> {
        	bo.setFiles(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.PROG, bo.getFId()));
        	bo.setAttachments(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.PROG_ATTACH, bo.getFId()));
        });
        return bos;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void save(DisputeSettlingBo bo, Long disputeId, String disputeName) {
        save(bo.getProgress(), disputeId, disputeName);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveAll(DisputeSettlingBo bo, Long disputeId, String disputeName) {
        save(bo.getProgress(), bo.getDisputeId(), bo.getDisputeName());
        lawFirmService.saveOursideFirm(bo.getOursideFirm(), disputeId, disputeName);
        lawFirmService.saveOppositeFirm(bo.getOppositeFirm(), disputeId, disputeName);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(DisputeSettlingBo bo, Long disputeId, String disputeName) {
        update(bo.getProgress(), disputeId, disputeName);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateAll(DisputeSettlingBo bo, Long disputeId, String disputeName) {
        update(bo.getProgress(), disputeId, disputeName);
        lawFirmService.updateOursideFirm(bo.getOursideFirm(), disputeId, disputeName);
        lawFirmService.updateOppositeFirm(bo.getOppositeFirm(), disputeId, disputeName);
    }
}