package com.pcitc.legalAffairs.service.litigate.dispute;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettledBo;
import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeSettledService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettled;
import com.pcitc.legalAffairs.service.dps.DpsApproveService;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pctic.common.utils.UserUtils;

import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.entityToBo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 纠纷办结Service
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeSettleService {

    @Autowired
    private ILitigateDisputeService disputeService;
    @Autowired
    private ILitigateDisputeSettledService disputeSettledService;
	@Autowired
	private LitigateDisputeOppositeService oppositeService;
	@Autowired
	private LitigateDisputeFileDictService fileService;
	@Autowired
	private DpsApproveService dpsApproveService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;

    /**
     * 查询纠纷办理单据
     * @param bo
     * @return
     */
    public IPage<DisputeBo> queryList(DisputeQueryBo bo) {
		IPage<FwLitigateDispute> page = new Page<>(bo.getCurrent(), bo.getSize());
		LambdaQueryWrapper<FwLitigateDispute> wrapper = disputeService.lambdaQueryWrapper()
				.like(StringUtils.checkValNotNull(bo.getCode()), FwLitigateDispute::getfCode, bo.getCode())
				.like(StringUtils.checkValNotNull(bo.getName()), FwLitigateDispute::getfName, bo.getName())
				.in(FwLitigateDispute::getfStatus, DisputeStatus.DISPUTE_SETTLING, DisputeStatus.DISPUTE_SETTLED_EXECUTING, DisputeStatus.DISPUTE_SETTLED_CLOSED)
				.eq(bo.getDiscarded() != null, FwLitigateDispute::getfIsDiscard, bo.getDiscarded())
				.eq(bo.getStatus() != null, FwLitigateDispute::getfStatus, bo.getStatus())
                .and(w -> w.eq(FwLitigateDispute::getfUserId, UserUtils.getUserInfo().getfId())
		                .or(privilegeInfoService.privilegedOrgs() != null, w1 -> w1.in(FwLitigateDispute::getFkReportedOrgId, privilegeInfoService.privilegedOrgs()))
                )
                .orderByAsc(FwLitigateDispute::getfStatus)
				.orderByDesc(FwLitigateDispute::getfUpdateTime);
		IPage<FwLitigateDispute> pagedOri = disputeService.page(page, wrapper);
		IPage<DisputeBo> convert = pagedOri.convert(entity -> {
			DisputeBo queriedBo = entityToBo(entity);
			List<DisputeOppositeBo> opposite = oppositeService.getByDisputeId(queriedBo.getFId().toString());
			String oppositeName = "";
			StringBuilder oppositeNameBuilder = new StringBuilder();
			if (opposite != null) {
				opposite.stream().forEach(e -> {
					oppositeNameBuilder.append(e.getFName());
					oppositeNameBuilder.append(", ");
				});
				if (oppositeNameBuilder.toString().length() >= 1) {
					oppositeName = oppositeNameBuilder.toString().substring(0, oppositeNameBuilder.toString().length() - 2);
				}
				queriedBo.setOppositeName(oppositeName);
			}
			
			return queriedBo;
		});
		return convert;
    }
    
    /**
     * 通用结案
     * @param disputeId
     * @param disputeName
     * @param bo
     * @param disputeStatus
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long settleCase(Long disputeId, String disputeName, DisputeSettledBo bo, byte disputeStatus) {
    	switch (disputeStatus) {
    	default: 
    		throw new BaseException("结案状态异常! ", 500);
    	// 结案并执行
    	// fall through
    	// no handle
    	case 7: 
    	// 结案并关闭
    	// fall through
    	// no handle
    	case 8: 
    	}
        FwLitigateDispute dispute = disputeService.getById(disputeId);
        if (dispute == null) {
            throw new BaseException("单据不存在或已删除! ", 500);
        }
        // 检查是否已经结案
        LambdaQueryWrapper<FwLitigateDisputeSettled> wrapper = disputeSettledService.lambdaQueryWrapper().eq(FwLitigateDisputeSettled::getFkDisputeId, disputeId);
        if (disputeSettledService.count(wrapper) > 0) {
        	throw new BaseException("该单据已有结案信息! ", 500);
        }
        FwLitigateDisputeSettled entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
        disputeSettledService.save(entity);
        dispute.setfStatus(disputeStatus);
        // 结案时间存入主表
        dispute.setfSettleDate(entity.getfDate());
        disputeService.updateById(dispute);

        // 保存文件
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.SETTLED, entity.getfId());
        
        // 办结该案待办
        dpsApproveService.taskMessageComplete(DpsCategoryEnum.DpsCategory_Settle.getCategoryCode() + "_" + disputeId);

        return entity.getfId();
    }
    
    /**
     * 纠纷结案并关闭案件
     * @param disputeId
     * @param disputeName
     * @param bo
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long settleAndCloseCase(Long disputeId, String disputeName, DisputeSettledBo bo) {
        return settleCase(disputeId, disputeName, bo, DisputeStatus.DISPUTE_SETTLED_CLOSED);
    }

    /**
     * 纠纷结案并进入执行环节
     * @param disputeId
     * @param disputeName
     * @param bo
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long settleAndExecute(Long disputeId, String disputeName, DisputeSettledBo bo) {
    	return settleCase(disputeId, disputeName, bo, DisputeStatus.DISPUTE_SETTLED_EXECUTING);
    }

    public DisputeSettledBo getSettleInfo(String disputeId) {
        FwLitigateDisputeSettled entity = disputeSettledService.getOne(disputeSettledService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeSettled::getFkDisputeId, disputeId));
        DisputeSettledBo bo = DisputePojoConverter.entityToBo(entity);
        if (bo == null) {
			return null;
		}
        bo.setFiles(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.SETTLED, entity.getfId()));
        return bo;
    }
}
