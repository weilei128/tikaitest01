package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOpposite;

/**
 * 诉前争议对方Service
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeOppositeService {

	@Autowired
	private ILitigateDisputeOppositeService disputeOppositeService;
	
	public void updateBatch(List<DisputeOppositeBo> boList, Long pid, String pname) {
		if (boList == null) {
			boList = new ArrayList<>();
		}
		List<DisputeOppositeBo> newItems = boList.stream().filter(entity -> entity.getFId() == null).collect(Collectors.toList());
		List<DisputeOppositeBo> toUpdateItems = boList.stream().filter(entity -> entity.getFId() != null).collect(Collectors.toList());
		List<Long> updateIds = toUpdateItems.stream().map(DisputeOppositeBo::getFId).collect(Collectors.toList());
		List<FwLitigateDisputeOpposite> toSave = DisputePojoConverter.oppositeBosToEntityList(newItems, pid, pname);
		List<FwLitigateDisputeOpposite> toUpdate = DisputePojoConverter.oppositeBosToEntityList(toUpdateItems, pid, pname);
		if (toUpdate != null && toUpdate.size() > 0) {
			toUpdate.stream().forEach(entity -> disputeOppositeService.update(entity, disputeOppositeService.lambdaUpdateWrapper().eq(FwLitigateDisputeOpposite::getfId, entity.getfId()).eq(FwLitigateDisputeOpposite::getFkDisputeId, pid)));
		}
		disputeOppositeService.remove(disputeOppositeService.lambdaUpdateWrapper().eq(FwLitigateDisputeOpposite::getFkDisputeId, pid).notIn(updateIds != null && updateIds.size() > 0, FwLitigateDisputeOpposite::getfId, updateIds));
		if (toSave != null && toSave.size() > 0) {
			disputeOppositeService.saveBatch(toSave);
		}
	}
	
	public List<DisputeOppositeBo> getByDisputeId(String disputeId) {
		List<FwLitigateDisputeOpposite> list = disputeOppositeService.list(disputeOppositeService.lambdaQueryWrapper().eq(FwLitigateDisputeOpposite::getFkDisputeId, disputeId));
		return DisputePojoConverter.oppositeEntitiesToBoList(list);
	}

}
