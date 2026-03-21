package com.pcitc.legalAffairs.service.litigate.dispute;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeDictBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeFileDictBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOthersideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettleChangeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettledBo;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeDict;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecuteProgress;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeFileDict;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOpposite;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirm;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirmStep;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOtherside;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOurside;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirm;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirmStep;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeProgress;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettleChange;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettled;
import com.pcitc.legalAffairs.service.dict.DictionaryService;
import com.pctic.common.utils.DateUtils;

/**
 * 争议相关的实体类之间转换
 * 
 * @author meihongli
 *
 */
public class DisputePojoConverter {

	public static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 主表BO转实体
	 * 
	 * @param bo
	 * @return
	 */
	public static FwLitigateDispute boToEntity(DisputeBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDispute entity = new FwLitigateDispute();
		BeanUtils.copyProperties(bo, entity);
		entity.setfIncidentDate(stringToDate(bo.getFIncidentDate()));
		entity.setfSettleDate(stringToDate(bo.getFSettleDate()));
		return entity;
	}

	/**
	 * 主表实体转BO
	 * 
	 * @param entity
	 * @return
	 */
	public static DisputeBo entityToBo(FwLitigateDispute entity) {
		if (entity == null) {
			return null;
		}
		DisputeBo bo = new DisputeBo();
		BeanUtils.copyProperties(entity, bo);
		bo.setFIncidentDate(dateToString(entity.getfIncidentDate()));
		bo.setFSettleDate(dateToString(entity.getfSettleDate()));
		bo.setFTypeName(DictionaryService.queryDictionaryById(bo.getFType()));
		bo.setFType2Name(DictionaryService.queryDictionaryById(bo.getFType2()));
		bo.setFType3Name(DictionaryService.queryDictionaryById(bo.getFType3()));
		bo.setFProvinceName(DictionaryService.queryDictionaryById(bo.getFProvince()));
		bo.setFPrefectureName(DictionaryService.queryDictionaryById(bo.getFPrefecture()));
		bo.setFRegionName(DictionaryService.queryDictionaryById(bo.getFRegion()));
		bo.setFIsMajorString(1 == bo.getFIsMajor()? "重大": "一般");
		return bo;
	}

	/**
	 * 主表实体List转BO List
	 * 
	 * @param entities
	 * @return
	 */
	public static List<DisputeBo> disputeEntitiesToBoList(List<FwLitigateDispute> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(entity -> entityToBo(entity)).collect(Collectors.toList());
	}

	/**
	 * 我方BO转实体
	 * 
	 * @param bo
	 * @return
	 */
	public static FwLitigateDisputeOurside boToEntity(DisputeOursideBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOurside entity = new FwLitigateDisputeOurside();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}

	public static FwLitigateDisputeOurside boToEntity(DisputeOursideBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOurside entity = new FwLitigateDisputeOurside();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setFkDisputeName(disputeName);
		return entity;
	}
	
	public static DisputeOursideBo entityToBo(FwLitigateDisputeOurside entity) {
		if (entity == null) {
			return null;
		}
		DisputeOursideBo bo = new DisputeOursideBo();
		BeanUtils.copyProperties(entity, bo);
		bo.setFLitigantStatusName(DictionaryService.queryDictionaryById(bo.getFLitigantStatus()));
		bo.setFLegalStatusName(DictionaryService.queryDictionaryById(bo.getFLegalStatus()));
		return bo;
	}

	public static List<DisputeOursideBo> oursideEntitiesToBoList(List<FwLitigateDisputeOurside> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(entity -> entityToBo(entity)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOurside> oursideBosToEntityList(List<DisputeOursideBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo)).collect(Collectors.toList());
	}
	
	public static List<FwLitigateDisputeOurside> oursideBosToEntityList(List<DisputeOursideBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}

	public static FwLitigateDisputeOpposite boToEntity(DisputeOppositeBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOpposite entity = new FwLitigateDisputeOpposite();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}
	
	public static FwLitigateDisputeOpposite boToEntity(DisputeOppositeBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOpposite entity = new FwLitigateDisputeOpposite();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setFkDisputeName(disputeName);
		return entity;
	}

	public static DisputeOppositeBo entityToBo(FwLitigateDisputeOpposite entity) {
		if (entity == null) {
			return null;
		}
		DisputeOppositeBo bo = new DisputeOppositeBo();
		BeanUtils.copyProperties(entity, bo);
		bo.setFLegalStatusName(DictionaryService.queryDictionaryById(bo.getFLegalStatus()));
		bo.setFStatusName(DictionaryService.queryDictionaryById(bo.getFStatus()));
		return bo;
	}

	public static List<DisputeOppositeBo> oppositeEntitiesToBoList(List<FwLitigateDisputeOpposite> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOpposite> oppositeBosToEntityList(List<DisputeOppositeBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOpposite> oppositeBosToEntityList(List<DisputeOppositeBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}
	
	public static FwLitigateDisputeOtherside boToEntity(DisputeOthersideBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOtherside entity = new FwLitigateDisputeOtherside();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}

	public static FwLitigateDisputeOtherside boToEntity(DisputeOthersideBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOtherside entity = new FwLitigateDisputeOtherside();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setFkDisputeName(disputeName);
		return entity;
	}
	
	public static DisputeOthersideBo entityToBo(FwLitigateDisputeOtherside entity) {
		if (entity == null) {
			return null;
		}
		DisputeOthersideBo bo = new DisputeOthersideBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}

	public static List<DisputeOthersideBo> othersideEntitiesToBoList(List<FwLitigateDisputeOtherside> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOtherside> othersideBosToEntityList(List<DisputeOthersideBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}
	public static List<FwLitigateDisputeOtherside> othersideBosToEntityList(List<DisputeOthersideBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}
		
	public static FwLitigateDisputeOursideFirm boToEntity(DisputeOursideFirmBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOursideFirm entity = new FwLitigateDisputeOursideFirm();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setFkDisputeName(disputeName);
		return entity;
	}
	
	public static FwLitigateDisputeOursideFirm boToEntity(DisputeOursideFirmBo bo) {
		return boToEntity(bo, null, null);
	}
	
	public static List<FwLitigateDisputeOursideFirm> oursideFirmBosToEntities(List<DisputeOursideFirmBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOursideFirm> oursideFirmBosToEntities(List<DisputeOursideFirmBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}
	
	public static DisputeOursideFirmBo entityToBo(FwLitigateDisputeOursideFirm entity, List<FwLitigateDisputeOursideFirmStep> steps) {
		if (entity == null) {
			return null;
		}
		DisputeOursideFirmBo bo = new DisputeOursideFirmBo();
		BeanUtils.copyProperties(entity, bo);
		if (steps != null) {
			List<Long> list = steps.stream().map(FwLitigateDisputeOursideFirmStep::getfStep).collect(Collectors.toList());
			List<String> name = list.parallelStream().map(DictionaryService::queryDictionaryById).collect(Collectors.toList());
			bo.setSteps(list);
			bo.setStepNames(name);
		}
		return bo;
	}
	
	public static List<DisputeOursideFirmBo> oursideFirmEntitiesToBoList(List<FwLitigateDisputeOursideFirm> entities, List<FwLitigateDisputeOursideFirmStep> steps) {
		if (entities == null) {
			return null;
		}
		List<DisputeOursideFirmBo> boList = entities.stream().map(entity -> entityToBo(entity, null)).collect(Collectors.toList());
		if (steps != null) {
			boList.forEach(bo -> {
				Long id = bo.getFId();
				List<Long> list = steps.stream().filter(step -> step.getFkLawFirmId() == id).map(FwLitigateDisputeOursideFirmStep::getfStep).collect(Collectors.toList());
				List<String> name = list.parallelStream().map(DictionaryService::queryDictionaryById).collect(Collectors.toList());
				bo.setSteps(list);
				bo.setStepNames(name);
			});
		}
		return boList;
	}
	
	public static List<FwLitigateDisputeOursideFirmStep> oursideFirmStepsToEntityList(List<Long> steps, Long firmId, Long disputeId) {
		if (steps == null) {
			return null;
		}
		List<FwLitigateDisputeOursideFirmStep> list = steps.stream().map(step -> {
			FwLitigateDisputeOursideFirmStep entity = new FwLitigateDisputeOursideFirmStep();
			entity.setFkLawFirmId(firmId);
			entity.setFkDisputeId(disputeId);
			entity.setfStep(step);
			return entity;
		}).collect(Collectors.toList());
		return list;
	}
	
	public static FwLitigateDisputeOppositeFirm boToEntity(DisputeOppositeFirmBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOppositeFirm entity = new FwLitigateDisputeOppositeFirm();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setFkDisputeName(disputeName);
		return entity;
	}

	public static FwLitigateDisputeOppositeFirm boToEntity(DisputeOppositeFirmBo bo) {
		return boToEntity(bo, null, null);
	}

	public static List<FwLitigateDisputeOppositeFirm> oppositeFirmBosToEntities(List<DisputeOppositeFirmBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeOppositeFirm> oppositeFirmBosToEntities(List<DisputeOppositeFirmBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}
	
	public static DisputeOppositeFirmBo entityToBo(FwLitigateDisputeOppositeFirm entity, List<FwLitigateDisputeOppositeFirmStep> steps) {
		if (entity == null) {
			return null;
		}
		DisputeOppositeFirmBo bo = new DisputeOppositeFirmBo();
		BeanUtils.copyProperties(entity, bo);
		if (steps != null) {
			List<Long> list = steps.stream().map(FwLitigateDisputeOppositeFirmStep::getfStep).collect(Collectors.toList());
			List<String> name = list.parallelStream().map(DictionaryService::queryDictionaryById).collect(Collectors.toList());
			bo.setSteps(list);
			bo.setStepNames(name);
		}
		return bo;
	}
	
	public static List<DisputeOppositeFirmBo> oppositeFirmEntitiesToBoList(List<FwLitigateDisputeOppositeFirm> entities, List<FwLitigateDisputeOppositeFirmStep> steps) {
		if (entities == null) {
			return null;
		}
		List<DisputeOppositeFirmBo> boList = entities.stream().map(entity -> entityToBo(entity, null)).collect(Collectors.toList());
		if (steps != null) {
			boList.forEach(bo -> {
				Long id = bo.getFId();
				List<Long> list = steps.stream().filter(step -> step.getFkLawFirmId() == id).map(FwLitigateDisputeOppositeFirmStep::getfStep).collect(Collectors.toList());
				List<String> name = list.parallelStream().map(DictionaryService::queryDictionaryById).collect(Collectors.toList());
				bo.setSteps(list);
				bo.setStepNames(name);
			});
		}
		return boList;
	}
	
	public static List<FwLitigateDisputeOppositeFirmStep> oppositeFirmStepsToEntityList(List<Long> steps, Long firmId, Long disputeId) {
		if (steps == null) {
			return null;
		}
		List<FwLitigateDisputeOppositeFirmStep> list = steps.stream().map(step -> {
			FwLitigateDisputeOppositeFirmStep entity = new FwLitigateDisputeOppositeFirmStep();
			entity.setFkLawFirmId(firmId);
			entity.setFkDisputeId(disputeId);
			entity.setfStep(step);
			return entity;
		}).collect(Collectors.toList());
		return list;
	}

	public static DisputeSettleChangeBo entityToBo(FwLitigateDisputeSettleChange entity) {
		if (entity == null) {
			return null;
		}
		DisputeSettleChangeBo bo = new DisputeSettleChangeBo();
		BeanUtils.copyProperties(entity, bo);
		if (StringUtils.checkValNotNull(entity.getfTime())) {
			bo.setFTime(dateToString(entity.getfTime(), DATETIME_FORMAT));
		}
		return bo;
	}

	public static FwLitigateDisputeSettleChange boToEntity(DisputeSettleChangeBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeSettleChange entity = new FwLitigateDisputeSettleChange();
		BeanUtils.copyProperties(bo, entity);
		if (disputeId != null) {
			entity.setFkDisputeId(disputeId);
		}
		if (StringUtils.checkValNotNull(disputeName)) {
			entity.setFkDisputeName(disputeName);
		}
		if (bo.getFTime() != null) {
			entity.setfTime(stringToDate(bo.getFTime(), DATETIME_FORMAT));
		}
		return entity;
	}

	public static FwLitigateDisputeSettleChange boToEntity(DisputeSettleChangeBo bo) {
		return boToEntity(bo, null, null);
	}

	public static List<DisputeSettleChangeBo> settleChangeEntitiesToBoList(List<FwLitigateDisputeSettleChange> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeSettleChange> settleChangeBosToEntityList(List<DisputeSettleChangeBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeSettleChange> settleChangeBosToEntityList(List<DisputeSettleChangeBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}

	public static FwLitigateDisputeProgress boToEntity(DisputeProgressBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeProgress entity = new FwLitigateDisputeProgress();
		BeanUtils.copyProperties(bo, entity);
		if (disputeId != null) {
			entity.setFkDisputeId(disputeId);
		}
		if (StringUtils.checkValNotNull(disputeName)) {
			entity.setFkDisputeName(disputeName);
		}
		if (StringUtils.checkValNotNull(bo.getFBegindate())) {
			entity.setfBegindate(stringToDate(bo.getFBegindate()));
		}
		if (StringUtils.checkValNotNull(bo.getFEnddate())) {
			entity.setfEnddate(stringToDate(bo.getFEnddate()));
		}
		if (StringUtils.checkValNotNull(bo.getFReceiveDate())) {
			entity.setfReceiveDate(stringToDate(bo.getFReceiveDate()));
		}
		
		return entity;
	}

	public static FwLitigateDisputeProgress boToEntity(DisputeProgressBo bo) {
		return boToEntity(bo, null, null);
	}

	public static List<FwLitigateDisputeProgress> progressBosToEntityList(List<DisputeProgressBo> bos, Long disputeId, String disputeName) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeName)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeProgress> progressBosToEntityList(List<DisputeProgressBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}

	public static DisputeProgressBo entityToBo(FwLitigateDisputeProgress entity) {
		if (entity == null) {
			return null;
		}
		DisputeProgressBo bo = new DisputeProgressBo();
		BeanUtils.copyProperties(entity, bo);
		if (StringUtils.checkValNotNull(entity.getfBegindate())) {
			bo.setFBegindate(dateToString(entity.getfBegindate()));
		}
		if (StringUtils.checkValNotNull(entity.getfEnddate())) {
			bo.setFEnddate(dateToString(entity.getfEnddate()));
		}
		if (StringUtils.checkValNotNull(entity.getfReceiveDate())) {
			bo.setFReceiveDate(dateToString(entity.getfReceiveDate()));
		}
		bo.setFStatusName(DictionaryService.queryDictionaryById(bo.getFStatus()));
		bo.setFStepName(DictionaryService.queryDictionaryById(bo.getFStep()));
		return bo;
	}

	public static List<DisputeProgressBo> progressEntitiesToBoList(List<FwLitigateDisputeProgress> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static FwLitigateDisputeSettled boToEntity(DisputeSettledBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeSettled entity = new FwLitigateDisputeSettled();
		BeanUtils.copyProperties(bo, entity);
		if (disputeId != null) {
			entity.setFkDisputeId(disputeId);
		}
		if (StringUtils.checkValNotNull(disputeName)) {
			entity.setFkDisputeName(disputeName);
		}
		if (StringUtils.checkValNotNull(bo.getFDate())) {
			entity.setfDate(stringToDate(bo.getFDate()));
		}
		return entity;
	}

	public static DisputeSettledBo entityToBo(FwLitigateDisputeSettled entity) {
		if (entity == null) {
			return null;
		}
		DisputeSettledBo bo = new DisputeSettledBo();
		BeanUtils.copyProperties(entity, bo);
		if (entity.getfDate() != null) {
			bo.setFDate(dateToString(entity.getfDate()));
		}
		return bo;
	}

	public static DisputeExecuteBo entityToBo(FwLitigateDisputeExecute entity) {
		if (entity == null) {
			return null;
		}
		DisputeExecuteBo bo = new DisputeExecuteBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}

	public static FwLitigateDisputeExecute boToEntity(DisputeExecuteBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeExecute entity = new FwLitigateDisputeExecute();
		BeanUtils.copyProperties(bo, entity);
		if (disputeId != null) {
			entity.setFkDisputeId(disputeId);
		}
		if (disputeName != null) {
			entity.setFkDisputeName(disputeName);
		}
		return entity;
	}

	public static DisputeExecuteProgressBo entityToBo(FwLitigateDisputeExecuteProgress entity) {
		if (entity == null) {
			return null;
		}
		DisputeExecuteProgressBo bo = new DisputeExecuteProgressBo();
		BeanUtils.copyProperties(entity, bo);
		if (entity.getfDate() != null) {
			bo.setFDate(dateToString(entity.getfDate()));
		}
		return bo;
	}

	public static FwLitigateDisputeExecuteProgress boToEntity(DisputeExecuteProgressBo bo, Long disputeId, Long disputeExecId) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeExecuteProgress entity = new FwLitigateDisputeExecuteProgress();
		BeanUtils.copyProperties(bo, entity);
		if (disputeId != null) {
			entity.setFkDisputeId(disputeId);
		}
		if (disputeExecId != null) {
			entity.setFkExecuteId(disputeExecId);
		}
		if (StringUtils.checkValNotNull(bo.getFDate())) {
			entity.setfDate(stringToDate(bo.getFDate()));
		}
		return entity;
	}

	public static FwLitigateDisputeExecuteProgress boToEntity(DisputeExecuteProgressBo bo) {
		return boToEntity(bo, null, null);
	}

	public static List<DisputeExecuteProgressBo> execProgEntitiesToBoList(List<FwLitigateDisputeExecuteProgress> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(entity -> entityToBo(entity)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeExecuteProgress> execProgBosToEntityList(List<DisputeExecuteProgressBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo)).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeExecuteProgress> execProgBosToEntityList(List<DisputeExecuteProgressBo> bos, Long disputeId, Long disputeExecId) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, disputeExecId)).collect(Collectors.toList());
	}

	private static Date stringToDate(String string) {
		try {
			return DateUtils.stringToDate(string, FORMAT);
		} catch (ParseException e) {
			throw new BaseException("时间格式异常! ", 500);
		}
	}

	public static DisputeDictBo entityToBo(FwLitigateDisputeDict entity) {
		if (entity == null) {
			return null;
		}
		DisputeDictBo bo = new DisputeDictBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}

	public static FwLitigateDisputeDict boToEntity(DisputeDictBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeDict entity = new FwLitigateDisputeDict();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}

	public static List<DisputeDictBo> dictEntitiesToBoList(List<FwLitigateDisputeDict> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeDict> dictBosToEntityList(List<DisputeDictBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}

	public static DisputeFileDictBo entityToBo(FwLitigateDisputeFileDict entity) {
		if (entity == null) {
			return null;
		}
		DisputeFileDictBo bo = new DisputeFileDictBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}

	public static FwLitigateDisputeFileDict boToEntity(DisputeFileDictBo bo) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeFileDict entity = new FwLitigateDisputeFileDict();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}

	public static FwLitigateDisputeFileDict boToEntity(DisputeFileDictBo bo, Long disputeId, DisputeAttachEnum fileType, Long businessId) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeFileDict entity = new FwLitigateDisputeFileDict();
		BeanUtils.copyProperties(bo, entity);
		entity.setFkDisputeId(disputeId);
		entity.setfFileType(fileType.getType());
		entity.setfBusinessId(businessId);
		return entity;
	}
	
	public static List<DisputeFileDictBo> fileEntitiesToBoList(List<FwLitigateDisputeFileDict> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(DisputePojoConverter::entityToBo).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeFileDict> fileBosToEntityList(List<DisputeFileDictBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(DisputePojoConverter::boToEntity).collect(Collectors.toList());
	}

	public static List<FwLitigateDisputeFileDict> fileBosToEntityList(List<DisputeFileDictBo> bos, Long disputeId, DisputeAttachEnum fileType, Long businessId) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, disputeId, fileType, businessId)).collect(Collectors.toList());
	}

	public static DisputeVo disputeBoToVo(DisputeBo bo) {
		if (bo == null) {
			return null;
		}
		DisputeVo vo = new DisputeVo();
		vo.setId(bo.getFId());
		vo.setCode(bo.getFCode());
		vo.setName(bo.getFName());
		vo.setOrgName(bo.getFkReportedOrgName());
		vo.setDate(bo.getFIncidentDate());
		vo.setSettleMethod(bo.getFSettleMethod());
		vo.setClosed(DisputeStatus.PRE_LITIGATE_SETTLED.equals(bo.getFStatus()) || DisputeStatus.DISPUTE_SETTLED_EXECUTING.equals(bo.getFStatus()) || DisputeStatus.DISPUTE_SETTLED_CLOSED.equals(bo.getFStatus())? 1: 0);
		vo.setIsExternal(bo.getFIsExternal());
		vo.setIsMajor(bo.getFIsMajor());
		vo.setType(bo.getFType());
		vo.setType2(bo.getFType2());
		vo.setType3(bo.getFType3());
		vo.setDescription(bo.getFDescription());
		vo.setRelatedAmount(bo.getFRelatedAmount());
		vo.setStatus(bo.getFStatus().intValue());

		return vo;
	}
	
	private static String dateToString(Date date) {
		return DateUtils.dateToString(date, FORMAT);
	}

	private static Date stringToDate(String string, DateFormat format) {
		try {
			return DateUtils.stringToDate(string, format);
		} catch (ParseException e) {
			throw new BaseException("时间格式异常! ", 500);
		}
	}

	private static String dateToString(Date date, DateFormat format) {
		return DateUtils.dateToString(date, format);
	}

}
