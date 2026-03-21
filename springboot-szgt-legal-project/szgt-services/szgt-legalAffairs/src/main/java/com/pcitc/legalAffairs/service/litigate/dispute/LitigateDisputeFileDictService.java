package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeDictBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeFileDictBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettledBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeFileDictService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeFileDict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LitigateDisputeFileDictService {

    @Autowired
    private ILitigateDisputeFileDictService ifileDictService;
    @Autowired
    private LitigateDisputeDictService dictService;
    @Autowired
    private LitigateDisputeService disputeService;
    @Autowired
    private ILitigateDisputeService idisputeService;

    public void save(List<DisputeFileDictBo> bos, Long disputeId, DisputeAttachEnum fileType, Long businessId) {
    	if (bos == null) {
			return;
		}
    	List<FwLitigateDisputeFileDict> entities = DisputePojoConverter.fileBosToEntityList(bos, disputeId, fileType, businessId);
    	
    	deleteByType(disputeId, fileType, businessId);
    	
    	entities.forEach(entity -> save(entity));
    }

    public void save(List<DisputeFileDictBo> bos) {
    	if (bos == null) {
			return;
		}
    	List<FwLitigateDisputeFileDict> entities = DisputePojoConverter.fileBosToEntityList(bos);
    	
    	// deleteByType(disputeId, fileType, businessId);
    	
    	entities.forEach(entity -> save(entity));
    }
    
    public List<DisputeFileDictBo> getFilesByType(Long disputeId, DisputeAttachEnum fileType, Long businessId) {
    	List<FwLitigateDisputeFileDict> list = ifileDictService.lambdaQuery()
			.eq(FwLitigateDisputeFileDict::getFkDisputeId, disputeId)
			.eq(FwLitigateDisputeFileDict::getfFileType, fileType.getType())
			.eq(FwLitigateDisputeFileDict::getfBusinessId, businessId)
			.list();
    	return DisputePojoConverter.fileEntitiesToBoList(list);
    }
    
    public Long save(FwLitigateDisputeFileDict entity) {
    	entity.setFkDictName(entity.getFkDictId() == null? "": dictService.getById(entity.getFkDictId().toString()).getFName());
    	entity.setFkDisputeName(disputeService.getbyId(entity.getFkDisputeId().toString()).getFName());
    	
    	ifileDictService.save(entity);
    	return entity.getfId();
    }
    
    public Long save(DisputeFileDictBo bo) {
        FwLitigateDisputeFileDict entity = DisputePojoConverter.boToEntity(bo);
        if (entity == null) {
			return null;
		}
        
        DisputeDictBo dict = dictService.getById(Optional.ofNullable(bo.getFkDictId()).orElse(Long.valueOf(-1)).toString());
        DisputeBo dispute = disputeService.getbyId(bo.getFkDisputeId().toString());
        entity.setFkDictName(dict != null? dict.getFName(): null);
        entity.setFkDisputeName(dispute == null? null: dispute.getFName());

        ifileDictService.save(entity);
        return entity.getfId();
    }

    public Long update(DisputeFileDictBo bo) {
        FwLitigateDisputeFileDict entity = DisputePojoConverter.boToEntity(bo);
        if (entity == null) {
			return null;
		}

        DisputeDictBo dict = dictService.getById(bo.getFkDictId().toString());
        DisputeBo dispute = disputeService.getbyId(bo.getFkDisputeId().toString());
        entity.setFkDictName(dict != null? dict.getFName(): null);
        entity.setFkDisputeName(dispute == null? null: dispute.getFName());

        ifileDictService.updateById(entity);
        return entity.getfId();
    }

    public void delete(List<Long> ids) {
        ifileDictService.removeByIds(ids);
    }
    
    public void deleteByType(Long disputeId, DisputeAttachEnum fileType, Long businessId) {
    	ifileDictService.lambdaUpdate()
    		.eq(FwLitigateDisputeFileDict::getFkDisputeId, disputeId)
    		.eq(FwLitigateDisputeFileDict::getfFileType, fileType.getType())
    		.eq(FwLitigateDisputeFileDict::getfBusinessId, businessId)
    		.remove();
    }
    
    /**
     * 根据文件ID删除归档信息
     * @param fileIds
     */
    public void deleteByFileId(List<Long> fileIds) {
    	ifileDictService.lambdaUpdate().in(FwLitigateDisputeFileDict::getFkFileId, fileIds).remove();
    }

    /**
     * 根据纠纷ID删除归档信息
     * @param fileIds
     */
    public void deleteByDisputeId(List<Long> fileIds) {
    	ifileDictService.lambdaUpdate().in(FwLitigateDisputeFileDict::getFkDisputeId, fileIds).remove();
    }
    
    public List<DisputeFileDictBo> getByConditions(DisputeFileDictBo bo) {
        List<FwLitigateDisputeFileDict> list = ifileDictService.lambdaQuery()
            .eq(bo.getFkDictId() != null, FwLitigateDisputeFileDict::getFkDictId, bo.getFkDictId())
            .eq(bo.getFkDisputeId() != null, FwLitigateDisputeFileDict::getFkDisputeId, bo.getFkDisputeId())
            .list();
        return DisputePojoConverter.fileEntitiesToBoList(list);
    }

    public DisputeFileDictBo getById(String id) {
        FwLitigateDisputeFileDict entity = ifileDictService.getById(id);
        return DisputePojoConverter.entityToBo(entity);
    }

    public FwLitigateDisputeFileDict getArchive(String dictName, Long disputeId, String disputeName, Long fileId, String fileName, String filePath, String fileExt) {
        if (fileId == null) {
            return null;
        }
        FwLitigateDisputeFileDict file = new FwLitigateDisputeFileDict();

        DisputeDictBo dict = dictService.findByName(dictName);
        if (dict == null) {
            return null;
        }
        file.setFkDictId(dict.getFId());
        file.setFkDictName(dict.getFName());
        file.setFkDisputeId(disputeId);
        file.setFkDisputeName(disputeName);
        file.setFkFileId(fileId);
        file.setFkFileName(fileName);
        file.setFkFilePath(filePath);
        file.setFkFileExt(fileExt);
        return file;
    }

    @Transactional
    public void archiveDispute(Long disputeId) {
        DisputeBo dispute = disputeService.getbyId(disputeId.toString());
        if (dispute.getFArchived() == 1) {
            return;
        }
        String disputeName = dispute.getFName();

        List<FwLitigateDisputeFileDict> archives = new ArrayList<>();

        // 主表
        FwLitigateDisputeFileDict archive1 = getArchive("01内部文件", disputeId, disputeName, dispute.getFkAttachmentId(), dispute.getFkAttachmentName(), dispute.getFkAttachmentPath(), dispute.getFkAttachmentExt());
        if (archive1 != null) {
            archives.add(archive1);
        }
        // 处理方式变更
        if (dispute.getSettleChanges() != null) {
            dispute.getSettleChanges().stream().forEach(change -> {
                FwLitigateDisputeFileDict a = getArchive("01内部文件", disputeId, disputeName, change.getFkAttachmentId(), change.getFkAttachmentName(), change.getFkAttachmentPath(), change.getFkAttachmentExt());
                if (a != null) {
                    archives.add(a);
                }
            });
        }
        // 进展情况
        if (dispute.getProgresses() != null) {
            dispute.getProgresses().stream().forEach(change -> {
                FwLitigateDisputeFileDict a = getArchive("15其他", disputeId, disputeName, change.getFkAttachmentId(), change.getFkAttachmentName(), change.getFkAttachmentPath(), change.getFkAttachmentExt());
                FwLitigateDisputeFileDict a1 = getArchive("15其他", disputeId, disputeName, change.getFkProgressAttachmentId(), change.getFkProgressAttachmentName(), change.getFkProgressAttachmentPath(), change.getFkProgressAttachmentExt());
                if (a != null) {
                    archives.add(a);
                }
                if (a1 != null) {
                    archives.add(a1);
                }
            });
        }
        // 纠纷结案
        if (dispute.getSettleInfo() != null) {
            DisputeSettledBo settle = dispute.getSettleInfo();
            FwLitigateDisputeFileDict a = getArchive("02法律文书", disputeId, disputeName, settle.getFkAttachmentId(), settle.getFkAttachmentName(), settle.getFkAttachmentPath(), settle.getFkAttachmentExt());
            if (a != null) {
                archives.add(a);
            }
        }
        // 纠纷执行
        if (dispute.getExecuteInfo() != null) {
            DisputeExecuteBo exec = dispute.getExecuteInfo();
            FwLitigateDisputeFileDict a = getArchive("02法律文书", disputeId, disputeName, exec.getFkAttachmentId(), exec.getFkAttachmentName(), exec.getFkAttachmentPath(), exec.getFkAttachmentExt());
            if (a != null) {
                archives.add(a);
            }
            if (exec.getProgress() != null) {
                exec.getProgress().stream().forEach(entity -> {
                    FwLitigateDisputeFileDict a1 = getArchive("15其他", disputeId, disputeName, entity.getFkAttachmentId(), entity.getFkAttachmentName(), entity.getFkAttachmentPath(), entity.getFkAttachmentExt());
                    if (a1 != null) {
                        archives.add(a1);
                    }
                });
            }
        }

        ifileDictService.saveBatch(archives);
        idisputeService.lambdaUpdate().eq(FwLitigateDispute::getfId, disputeId).set(FwLitigateDispute::getfArchived, 1).update();
    }
}