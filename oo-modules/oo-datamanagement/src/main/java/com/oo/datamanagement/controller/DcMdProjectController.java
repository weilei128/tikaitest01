package com.oo.datamanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.TreeUtils;
import com.oo.common.core.utils.uuid.IdUtils;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;
import com.oo.common.core.web.domain.TranslateSubmitVo;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.redis.service.RedisService;
import com.oo.common.security.utils.DictUtils;
import com.oo.datamanagement.api.cache.DataManageCache;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.mapper.DcMdProjectMapper;
import com.oo.datamanagement.service.IDcMdProjectService;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.domain.TreeSelectForString;
import com.oo.system.api.feign.RemoteTranslateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 区块管理
 *
 * @author
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dcmdproject")
@Api(value = "地质单元--区块管理", tags = "地质单元--区块管理接口")
public class DcMdProjectController extends BaseController {

    private final DcMdProjectMapper dcMdProjectMapper;
    private final IDcMdProjectService dcMdProjectService;
    private final RedisService redisService;

    @Autowired
    private RemoteTranslateService remoteTranslateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询地质单元--区块管理列表", notes = "传入dcMdProject")
    public TableDataInfo list(DcMdProject dcMdProject)
    {
        startPage();
        List<DcMdProject> list = dcMdProjectService.selectDcMdProjectList(dcMdProject);

        String dictLabel="";
        //关联字典表查询
        for(DcMdProject list1:list){
            dictLabel = DictUtils.getDictLabel("base_field_type", list1.getType());
            list1.setType(dictLabel == null ? "" : dictLabel);
        }

        return getDataTable(list);
    }

    /**
     * 根据id获取区块管理详细信息
     */
    @GetMapping(value = "/selectDcMdProject")
    public AjaxResult getInfo(@RequestParam String Id)
    {
        if(StringUtils.isEmpty(Id)){
            return AjaxResult.error("Id不能为空");
        }
        DcMdProject dcMdProject=dcMdProjectMapper.selectById(Id);
        String dictLabel="";
        //关联字典表查询
        dictLabel = DictUtils.getDictLabel("base_field_type", dcMdProject.getType());
        dcMdProject.setType(dictLabel == null ? "" : dictLabel);
        return AjaxResult.success(dcMdProject);
    }
    /**
     * 查询区块管理
     */
    @GetMapping(value = "/selectDcMdProjectQuery")
    public AjaxResult selectDcMdProjectQuery(String name,String nameDetail,Integer type)
    {
        startPage();
        QueryWrapper<DcMdProject> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.isNotEmpty(nameDetail)){
            queryWrapper.like("name_detail",nameDetail);
        }
        if(type!=null){
            queryWrapper.eq("type",type);
        }
        queryWrapper.orderByAsc("order_num");

        List<DcMdProject> dcMdProjectList= dcMdProjectMapper.selectList(queryWrapper);
        String dictLabel="";
        //关联字典表查询
        for(DcMdProject dcMdProject:dcMdProjectList){
            dictLabel = DictUtils.getDictLabel("base_field_type", dcMdProject.getType());
            dcMdProject.setType(dictLabel == null ? "" : dictLabel);
        }
        return AjaxResult.success(dcMdProjectList);
    }
    /**
     * 根据id删除单井信息详细信息
     */
    @DeleteMapping(value = "/delete")
    public AjaxResult deleteDcMdProject(@RequestParam String Ids)
    {
        String[] Id = Ids.split(",");
        List<String> list = new ArrayList(Arrays.asList(Id));
        for (String a : list) {
            if (a == null || StringUtils.isEmpty(a)) {
                return AjaxResult.error("id不能为空");
            }
        }
        int result=dcMdProjectMapper.deleteBatchIds(list);//主表删除
        //清除缓存
        redisService.clear(DataManageCache.PROJECT_CACHE);
        //删除多语言
        List<String> deptIdList = Arrays.stream(Id).map(Object::toString).collect(Collectors.toList());
        int transResult = remoteTranslateService.translateRemove(Id);

        if (result <= 0) {
            return AjaxResult.error("主表删除失败");
        }else if(transResult <= 0){
            return AjaxResult.error("多语言删除失败");
        }
        return AjaxResult.success();
    }
    /**
     * 根据id更新、新增单井信息详细信息
     */
    @PostMapping(value = "/saveOrUpdateDcMdProject")
//    @DictMethod(dtoClass = DcMdProject.class)
    public AjaxResult saveOrUpdateDcMdProject(@RequestBody DcMdProject dcMdProject)
    {
        dcMdProject.setCreateTime(DateUtils.getNowDate());
        dcMdProject.setUpdateTime(DateUtils.getNowDate());
        String id= IdUtils.simpleUUID();
        dcMdProject.setId(id);
        boolean result=dcMdProjectService.saveOrUpdate(dcMdProject);//主表保存/更新

        //清除缓存
        redisService.clear(DataManageCache.PROJECT_CACHE);
        //保存多语言
        TranslateSubmitVo translateSubmitVo=new TranslateSubmitVo();
        translateSubmitVo.setFieldId(dcMdProject.getId());
        translateSubmitVo.setVoList(dcMdProject.getTransList());
        boolean transResult = remoteTranslateService.translateSubmit(translateSubmitVo);
        return AjaxResult.success(result&&transResult);
    }

    /**
     * 查询区块管理
     */
    @GetMapping(value = "/selectTreeList")
    public R<List<TreeSelectForString>> selectTreeList(String name,Integer type)
    {
        List<SysMenuWell> sysRoleDocMenuList=dcMdProjectMapper.selectTreeList(name,type);
        List<SysMenuWell> menuTrees = TreeUtils.buildMenuWellTree(sysRoleDocMenuList);

        return R.ok(menuTrees.stream().map(TreeSelectForString::new).collect(Collectors.toList()));
    }





}
