package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOthersideBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOthersideService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOtherside;

/**
 * 诉前争议第三方Service
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeOthersideService {

	@Autowired
	private ILitigateDisputeOthersideService disputeOthersideService;
	
	public void updateBatch(List<DisputeOthersideBo> boList, Long pid, String pname) {
		if (boList == null) {
			boList = new ArrayList<>();
		}
		List<DisputeOthersideBo> newItems = boList.stream().filter(entity -> entity.getFId() == null).collect(Collectors.toList());
		List<DisputeOthersideBo> toUpdateItems = boList.stream().filter(entity -> entity.getFId() != null).collect(Collectors.toList());
		List<Long> updateIds = toUpdateItems.stream().map(DisputeOthersideBo::getFId).collect(Collectors.toList());
		List<FwLitigateDisputeOtherside> toSave = DisputePojoConverter.othersideBosToEntityList(newItems, pid, pname);
		List<FwLitigateDisputeOtherside> toUpdate = DisputePojoConverter.othersideBosToEntityList(toUpdateItems, pid, pname);
		if (toUpdate != null && toUpdate.size() > 0) {
			toUpdate.stream().forEach(entity -> disputeOthersideService.update(entity, disputeOthersideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOtherside::getfId, entity.getfId()).eq(FwLitigateDisputeOtherside::getFkDisputeId, pid)));
		}
		disputeOthersideService.remove(disputeOthersideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOtherside::getFkDisputeId, pid).notIn(updateIds != null && updateIds.size() > 0, FwLitigateDisputeOtherside::getfId, updateIds));
		if (toSave != null && toSave.size() > 0) {
			disputeOthersideService.saveBatch(toSave);
		}
	}
	
	public List<DisputeOthersideBo> getByDisputeId(String disputeId) {
		List<FwLitigateDisputeOtherside> list = disputeOthersideService.list(disputeOthersideService.lambdaQueryWrapper().eq(FwLitigateDisputeOtherside::getFkDisputeId, disputeId));
		return DisputePojoConverter.othersideEntitiesToBoList(list);
	}

}
