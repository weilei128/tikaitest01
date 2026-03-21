package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeLawFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideFirmBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeLawFirmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 纠纷办理 - 代理律所 Controller
 * 
 * @author meihongli
 */
@RestController
@RequestMapping("dispute/lawFirm")
public class DisputeLawFirmController {

    @Autowired
    private LitigateDisputeLawFirmService lawFirmService;

    /**
     * 保存律所信息
     * @param bo
     * @return
     */
    @PostMapping("saveLawFirm")
    public Result saveLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOursideFirmBo> ourFirm = bo.getOurFirm();
        List<DisputeOppositeFirmBo> oppositeFirm = bo.getOppositeFirm();
        if (ourFirm != null) {
            lawFirmService.saveOursideFirm(ourFirm, id, name);
        }
        if (oppositeFirm != null) {
            lawFirmService.saveOppositeFirm(oppositeFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 更新律所信息
     * @param bo
     * @return
     */
    @PostMapping("updateLawFirm")
    public Result updateLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOursideFirmBo> ourFirm = bo.getOurFirm();
        List<DisputeOppositeFirmBo> oppositeFirm = bo.getOppositeFirm();
        if (ourFirm != null) {
            lawFirmService.updateOursideFirm(ourFirm, id, name);
        }
        if (oppositeFirm != null) {
            lawFirmService.updateOppositeFirm(oppositeFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 获取双方律所信息
     */
    @PostMapping("getLawFirms")
    public Result getLawFirms(@RequestParam String disputeId) {
        List<DisputeOursideFirmBo> ourside = lawFirmService.getOursideLawFirms(Long.parseLong(disputeId));
        List<DisputeOppositeFirmBo> opposite = lawFirmService.getOppositeLawFirms(Long.parseLong(disputeId));
        DisputeLawFirmBo res = new DisputeLawFirmBo();
        res.setOursideFirm(ourside);
        res.setOppositeFirm(opposite);
        Result<DisputeLawFirmBo> result = Result.data(res);
        return result;
    }

    /**
     * 保存我方律所信息
     * @param bo
     * @return
     */
    @PostMapping("saveOurLawFirm")
    public Result saveOurLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOursideFirmBo> ourFirm = bo.getOurFirm();
        if (ourFirm != null) {
            lawFirmService.saveOursideFirm(ourFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 更新我方律所信息
     * @param bo
     * @return
     */
    @PostMapping("updateOurLawFirm")
    public Result updateOurLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOursideFirmBo> ourFirm = bo.getOurFirm();
        if (ourFirm != null) {
            lawFirmService.updateOursideFirm(ourFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 删除我方律所信息
     * @param firmId
     * @return
     */
    @PostMapping("removeOurLawFirm")
    public Result removeOurLawFirm(@RequestParam String firmId) {
    	lawFirmService.deleteOursideFirm(firmId);
        Result result = Result.status(true);
		return result;
    }
    
    /**
     * 删除我方律所信息
     * @param firmId
     * @return
     */
    @PostMapping("removeOurLawFirmBatch")
    public Result removeOurLawFirmBatch(@RequestBody List<String> firmId) {
    	lawFirmService.deleteOursideFirm(firmId);
    	Result result = Result.status(true);
    	return result;
    }
    
    /**
     * 保存对方律所信息
     * @param bo
     * @return
     */
    @PostMapping("saveOppositeLawFirm")
    public Result saveOppositeLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOppositeFirmBo> oppositeFirm = bo.getOppositeFirm();
        if (oppositeFirm != null) {
            lawFirmService.saveOppositeFirm(oppositeFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 更新对方律所信息
     * @param bo
     * @return
     */
    @PostMapping("updateOppositeLawFirm")
    public Result updateOppositeLawFirm(@RequestBody DisputeBo bo) {
        Long id = bo.getFId();
        String name = bo.getFName();
        List<DisputeOppositeFirmBo> oppositeFirm = bo.getOppositeFirm();
        if (oppositeFirm != null) {
            lawFirmService.updateOppositeFirm(oppositeFirm, id, name);
        }
        Result result = Result.status(true);
		return result;
    }

    /**
     * 删除对方律所信息
     * @param firmId
     * @return
     */
    @PostMapping("removeOppositeLawFirm")
    public Result removeOppositeLawFirm(@RequestParam String firmId) {
    	lawFirmService.deleteOppositeFirm(firmId);
        Result result = Result.status(true);
		return result;
    }
    
    /**
     * 删除对方律所信息
     * @param firmId
     * @return
     */
    @PostMapping("removeOppositeLawFirmBatch")
    public Result removeOppositeLawFirmBatch(@RequestBody List<String> firmId) {
    	lawFirmService.deleteOppositeFirm(firmId);
    	Result result = Result.status(true);
    	return result;
    }
    
    /**
     * 获取我方律所信息
     */
    @PostMapping("getOursideFirms")
    public Result getOursideLawFirms(@RequestParam String disputeId) {
        List<DisputeOursideFirmBo> firms = lawFirmService.getOursideLawFirms(Long.parseLong(disputeId));
        Result<List<DisputeOursideFirmBo>> result = Result.data(firms);
        return result;
    }

    /**
     * 获取对方律所信息
     */
    @PostMapping("getOppositeFirms")
    public Result getOppositeLawFirms(@RequestParam String disputeId) {
        List<DisputeOppositeFirmBo> firms = lawFirmService.getOppositeLawFirms(Long.parseLong(disputeId));
        Result<List<DisputeOppositeFirmBo>> result = Result.data(firms);
        return result;
    }

}