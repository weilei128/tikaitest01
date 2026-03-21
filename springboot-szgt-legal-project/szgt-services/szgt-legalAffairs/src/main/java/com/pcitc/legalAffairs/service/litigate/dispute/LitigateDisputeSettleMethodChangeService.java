package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettleChangeBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeSettleChangeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettleChange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 纠纷处理方式转换Service
 * 
 * @author meihongli
 */
@Service
public class LitigateDisputeSettleMethodChangeService {

    @Autowired
    private ILitigateDisputeService idisputeService;
    @Autowired
    private ILitigateDisputeSettleChangeService idisputeSettleChangeService;
    @Autowired
    private LitigateDisputeFileDictService fileService;

    /**
     * 变更争议处理方式
     * 
     * @param disputeId
     * @param bo
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long changeSettleMethod(Long disputeId, DisputeSettleChangeBo bo) {
        if (disputeId == null || bo == null) {
            throw new BaseException("请求参数异常! ", 500);
        }
        FwLitigateDispute dispute = idisputeService.getById(disputeId);
        if (dispute == null) {
            throw new BaseException("单据不存在或已删除! ", 500);
        }
        Date date = new Date();

        Long oriSettleMethod = dispute.getfSettleMethod();

        FwLitigateDisputeSettleChange entity = DisputePojoConverter.boToEntity(bo, dispute.getfId(),
                dispute.getfName());

        Long neoSettleMethod = bo.getFSettleMethod();
        entity.setfOriSettleMethod(oriSettleMethod);
        entity.setfTime(date);
        idisputeSettleChangeService.save(entity);
        dispute.setfSettleMethod(neoSettleMethod);
        idisputeService.updateById(dispute);
        
        fileService.save(bo.getFiles(), disputeId, DisputeAttachEnum.SETTLE_CHANGE, entity.getfId());
        return entity.getfId();
    }

    /**
     * 查看变更记录列表 按时间倒序排列
     * 
     * @param disputeId
     * @return
     */
    public List<DisputeSettleChangeBo> getByDisputeId(String disputeId) {
        if (disputeId == null) {
            throw new BaseException("请求参数异常! ", 500);
        }
        LambdaQueryWrapper<FwLitigateDisputeSettleChange> wrapper = idisputeSettleChangeService.lambdaQueryWrapper()
            .eq(FwLitigateDisputeSettleChange::getFkDisputeId, disputeId)
            .orderByDesc(FwLitigateDisputeSettleChange::getfTime);
        List<FwLitigateDisputeSettleChange> entities = idisputeSettleChangeService.list(wrapper);
        List<DisputeSettleChangeBo> boList = DisputePojoConverter.settleChangeEntitiesToBoList(entities);
        Optional.ofNullable(boList).ifPresent(bos -> {
    		bos.stream().forEach(bo -> bo.setFiles(fileService.getFilesByType(Long.parseLong(disputeId), DisputeAttachEnum.SETTLE_CHANGE, bo.getFId())));
    	});
        return boList;
    }
}