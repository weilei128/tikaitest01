package com.pcitc.legalAffairs.controller.fwPrivilegeInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.IdBo;
import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.vo.fwPrivilegeInfo.*;
import com.pctic.common.utils.UserUtils;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("privilege")
public class PrivilegeInfoController {

    @Autowired
    private IPrivilegeInfoService fwPrivilegeInfoService;

    @GetMapping("view/{id}")
    public Result<?> viewFwPrivilegeInfo(@PathVariable Long id){
        Result result = fwPrivilegeInfoService.viewFwPrivilegeInfo(id);
        return result;
    }

    /**
     * 分页查看列表
     * @param param
     * @return
     */
    @PostMapping("page")
    public Result<?> pageFwPrivilegeInfo(@RequestBody QueryFwPrivilegeInfoParam param){
        Result result = Result.data(fwPrivilegeInfoService.pageFwPrivilegeInfo(param));
        return result;
    }

    /**
     * 不分页查看列表
     * @param param
     * @return
     */
    @PostMapping("list")
    public Result<?> listFwPrivilegeInfo(@RequestBody QueryFwPrivilegeInfoParam param){
        Result result = Result.data(fwPrivilegeInfoService.listFwPrivilegeInfo(param));
        return result;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    public Result<?> deleteFwPrivilegeInfo(@RequestBody List<Long> ids){
        Result result = fwPrivilegeInfoService.deleteFwPrivilegeInfo(ids);
        return result;
    }

    /**
     * 新增编辑
     * @param param
     * @return
     */
    @PostMapping("update")
    public Result<?> updateFwPrivilegeInfo(@RequestBody FwPrivilegeInfoVo param){
        Result result = fwPrivilegeInfoService.updateFwPrivilegeInfo(param);
        return result;
    }
    
//    /**
//     * 批量新增编辑
//     *
//     * @param params
//     * @return
//     */
//    @ApiOperation("批量新增编辑")
//    @PostMapping("updateBatch")
//    public Result<?> updateBatch(@RequestBody List<FwPrivilegeInfoVo> params) {
//    	fwPrivilegeInfoService.updateBatch(params);
//    	return Result.status(true);
//    }

    @PostMapping("save")
    @ApiOperation("保存权限信息")
    public Result<?> saveBatch(@RequestBody SavePrivilegeInfoVo vo) {
        fwPrivilegeInfoService.saveBatch(vo);
        return Result.status(true);
    }
    
    /**
     * 获取当前用户的权限
     *
     */
    @GetMapping("queryOrgIdByUserId")
    public Result<List<Long>> queryOrgIdByUserId() {
        List<Long> longs = fwPrivilegeInfoService.queryOrgIdByUserId(Long.valueOf(UserUtils.getUserInfo().getfId()));
        return Result.data(longs);
    }

    /**
     * 获取用户列表
     * @param queryVo 查询条件和分页
     * @return Result
     */
    @PostMapping("pageUsers")
    public Result<IPage<PrivilegeUserVo>> pageUsers(@RequestBody PrivilegeUserQueryVo queryVo) {
        return Result.data(fwPrivilegeInfoService.pageUsers(queryVo));
    }

    @PostMapping("deleteByUserId")
    public Result<?> deleteByUserId(@RequestBody IdBo bo) {
        fwPrivilegeInfoService.deleteByUserId(bo.getId());
        return Result.status(true);
    }
}
