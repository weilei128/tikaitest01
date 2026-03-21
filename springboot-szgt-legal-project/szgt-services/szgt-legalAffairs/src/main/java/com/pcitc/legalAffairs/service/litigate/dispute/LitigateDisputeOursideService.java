package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOurside;

/**
 * 诉前争议我方信息Service
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeOursideService {

	@Autowired
	private ILitigateDisputeOursideService disputeOursideService;
	
	public void updateBatch(List<DisputeOursideBo> boList, Long pid, String pname) {
		if (boList == null) {
			boList = new ArrayList<>();
		}
		List<DisputeOursideBo> newItems = boList.stream().filter(entity -> entity.getFId() == null).collect(Collectors.toList());
		List<DisputeOursideBo> toUpdateItems = boList.stream().filter(entity -> entity.getFId() != null).collect(Collectors.toList());
		List<Long> updateIds = toUpdateItems.stream().map(DisputeOursideBo::getFId).collect(Collectors.toList());
		List<FwLitigateDisputeOurside> toSave = DisputePojoConverter.oursideBosToEntityList(newItems, pid, pname);
		List<FwLitigateDisputeOurside> toUpdate = DisputePojoConverter.oursideBosToEntityList(toUpdateItems, pid, pname);
		if (toUpdate != null && toUpdate.size() > 0) {
			toUpdate.stream().forEach(entity -> disputeOursideService.update(entity, disputeOursideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOurside::getfId, entity.getfId()).eq(FwLitigateDisputeOurside::getFkDisputeId, pid)));
		}
		disputeOursideService.remove(disputeOursideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOurside::getFkDisputeId, pid).notIn(updateIds != null && updateIds.size() > 0, FwLitigateDisputeOurside::getfId, updateIds));
		if (toSave != null && toSave.size() > 0) {
			disputeOursideService.saveBatch(toSave);
		}
	}
	
	public List<DisputeOursideBo> getByDisputeId(String disputeId) {
		List<FwLitigateDisputeOurside> list = disputeOursideService.list(disputeOursideService.lambdaQueryWrapper().eq(FwLitigateDisputeOurside::getFkDisputeId, disputeId));
		return DisputePojoConverter.oursideEntitiesToBoList(list);
	}
}
