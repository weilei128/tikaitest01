package com.pcitc.legalAffairs.service.litigate.dispute;

import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.entityToBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeExecuteProgressService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeExecuteService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecuteProgress;
import com.pctic.common.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 纠纷执行Service
 * @author meihongli
 */
@Service
public class LitigateDisputeExecuteService {

    @Autowired
    private ILitigateDisputeService disputeService;
    @Autowired
    private ILitigateDisputeExecuteService disputeExecuteService;
    @Autowired
    private ILitigateDisputeExecuteProgressService disputeExecuteProgressService;
	@Autowired
	private LitigateDisputeOppositeService oppositeService;
	@Autowired
	private LitigateDisputeFileDictService fileService;

    /**
     * 查询纠纷执行单据
     * @param bo
     * @return
     */
    public IPage<DisputeBo> queryList(DisputeQueryBo bo) {
		IPage<FwLitigateDispute> page = new Page<>(bo.getCurrent(), bo.getSize());
		LambdaQueryWrapper<FwLitigateDispute> wrapper = disputeService.lambdaQueryWrapper()
				.like(StringUtils.checkValNotNull(bo.getCode()), FwLitigateDispute::getfCode, bo.getCode())
				.like(StringUtils.checkValNotNull(bo.getName()), FwLitigateDispute::getfName, bo.getName())
				.eq(FwLitigateDispute::getfStatus, DisputeStatus.DISPUTE_SETTLED_EXECUTING)
				.eq(bo.getDiscarded() != null, FwLitigateDispute::getfIsDiscard, bo.getDiscarded())
				.eq(bo.getStatus() != null, FwLitigateDispute::getfStatus, bo.getStatus())
				.eq(FwLitigateDispute::getfUserId, UserUtils.getUserInfo().getfId());
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
     * 保存执行信息
     * @param bo
     * @param disputeId
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long save(DisputeExecuteBo bo, Long disputeId) {
        Long execId = saveExec(bo, disputeId);
        List<DisputeExecuteProgressBo> progress = bo.getProgress();
        save(progress, disputeId, execId);
        return execId;
    }

    /**
     * 仅保存执行主表信息
     * @param bo
     * @param disputeId 纠纷ID
     * @return 已保存的ID
     */
    public Long saveExec(DisputeExecuteBo bo, Long disputeId) {
        FwLitigateDispute dispute = disputeService.getById(disputeId);
        if (dispute == null) {
            throw new BaseException("该单据不存在或已被删除! ", 500);
        }
        FwLitigateDisputeExecute entity = DisputePojoConverter.boToEntity(bo, disputeId, dispute.getfName());
        disputeExecuteService.save(entity);
        Long execId = entity.getfId();
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.EXECUTE, execId);
        return execId;
    }

    /**
     * 批量保存执行进度信息
     * @param progress
     * @param disputeId
     * @param execId
     */
    @Transactional(rollbackFor = Throwable.class)
    public void save(List<DisputeExecuteProgressBo> progress, Long disputeId, Long execId) {
        progress.forEach(prog -> save(prog, disputeId, execId));
    }

    /**
     * 单个保存执行进度信息
     * @param progress
     * @param disputeId
     * @param execId
     */
    public void save(DisputeExecuteProgressBo progress, Long disputeId, Long execId) {
        FwLitigateDisputeExecuteProgress entity = DisputePojoConverter.boToEntity(progress, disputeId, execId);
        disputeExecuteProgressService.save(entity);
        Long id = entity.getfId();
        fileService.getFilesByType(disputeId, DisputeAttachEnum.EXECUTE_PROG, id);
    }

    /**
     * 编辑执行信息
     * @param bo
     * @param disputeId
     */
    public Long update(DisputeExecuteBo bo, Long disputeId) {
        Long execId = updateExec(bo, disputeId);
        update(bo.getProgress(), disputeId, execId);
        return execId;
    }

    /**
     * 编辑执行主表
     * @param bo
     * @param disputeId
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long updateExec(DisputeExecuteBo bo, Long disputeId) {
        FwLitigateDisputeExecute entity = DisputePojoConverter.boToEntity(bo, null, null);
        disputeExecuteService.updateById(entity);
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.EXECUTE, bo.getFId());
        return bo.getFId();
    }

    /**
     * 编辑单个执行进度
     */
    public void update(DisputeExecuteProgressBo bo, Long disputeId, Long execId) {
        FwLitigateDisputeExecuteProgress entity = DisputePojoConverter.boToEntity(bo, disputeId, execId);
        disputeExecuteProgressService.updateById(entity);
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.EXECUTE_PROG, bo.getFId());
    }

    /**
     * 编辑执行进度列表
     * @param bos
     * @param disputeId
     * @param execId
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(List<DisputeExecuteProgressBo> bos, Long disputeId, Long execId) {
        if (bos == null) {
            bos = new ArrayList<DisputeExecuteProgressBo>();
        }
        List<DisputeExecuteProgressBo> toUpdate = bos.stream().filter(bo -> bo.getFId() != null).collect(Collectors.toList());
        List<DisputeExecuteProgressBo> toSave = bos.stream().filter(bo -> bo.getFId() == null).collect(Collectors.toList());
        List<Long> updatedIdList = Optional.ofNullable(toUpdate).orElse(new ArrayList<>()).stream().map(DisputeExecuteProgressBo::getFId).collect(Collectors.toList());
        // update
        Optional.ofNullable(toUpdate).orElse(new ArrayList<>()).forEach(e -> update(e, disputeId, execId));
        // del
        disputeExecuteProgressService.remove(disputeExecuteProgressService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeExecuteProgress::getFkDisputeId, disputeId)
            .eq(FwLitigateDisputeExecuteProgress::getFkExecuteId, execId)
            .notIn(updatedIdList != null && updatedIdList.size() > 0, FwLitigateDisputeExecuteProgress::getfId, updatedIdList));
        // save
        save(toSave, disputeId, execId);
    }

    /**
     * 获取纠纷执行信息
     * @param disputeId
     * @return
     */
    public DisputeExecuteBo getExecuteInfo(String disputeId) {
        DisputeExecuteBo bo = getExecute(disputeId);
        if (bo == null) {
			return null;
		}
        Long execId = bo.getFId();
        List<DisputeExecuteProgressBo> progress = getExecProg(disputeId, execId);
        bo.setProgress(progress);
        return bo;
    }

    /**
     * 获取纠纷单的执行主表信息
     * @param disputeId
     * @return
     */
    public DisputeExecuteBo getExecute(String disputeId) {
        FwLitigateDisputeExecute entity = disputeExecuteService.getOne(disputeExecuteService.lambdaQueryWrapper().eq(FwLitigateDisputeExecute::getFkDisputeId, disputeId));
        DisputeExecuteBo bo = DisputePojoConverter.entityToBo(entity);
        if (bo == null) {
			return null;
		}
        bo.setFiles(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.EXECUTE, bo.getFId()));
        return bo;
    }

    /**
     * 获取纠纷单的执行进度信息
     * @param disputeId
     * @param execId
     * @return
     */
    public List<DisputeExecuteProgressBo> getExecProg(String disputeId, Long execId) {
        List<FwLitigateDisputeExecuteProgress> entities = disputeExecuteProgressService.list(disputeExecuteProgressService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeExecuteProgress::getFkDisputeId, disputeId)
            .eq(FwLitigateDisputeExecuteProgress::getFkExecuteId, execId));
        List<DisputeExecuteProgressBo> bos = DisputePojoConverter.execProgEntitiesToBoList(entities);
        bos.forEach(bo -> bo.setFiles(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.EXECUTE, bo.getFId())));
        return bos;
    }

    /**
     * 关闭案件
     */
    public void closeCase(Long disputeId) {
        disputeService.lambdaUpdate()
            .eq(FwLitigateDispute::getfId, disputeId)
            .set(FwLitigateDispute::getfStatus, DisputeStatus.DISPUTE_SETTLED_CLOSED)
            .update();
    }
}