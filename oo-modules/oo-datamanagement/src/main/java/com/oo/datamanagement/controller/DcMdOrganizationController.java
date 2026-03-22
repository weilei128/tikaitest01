package com.oo.datamanagement.controller;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
import com.oo.system.api.domain.DcMdOrganization;
import com.oo.datamanagement.domain.vo.MdNumProjectVo;
import com.oo.datamanagement.domain.vo.MdProjectVo;
import com.oo.datamanagement.mapper.DcMdProjectMapper;
import com.oo.datamanagement.mapper.DcMdWellMapper;
import com.oo.system.api.domain.*;
import com.oo.datamanagement.mapper.DcMdOrganizationMapper;
import com.oo.datamanagement.service.IDcMdOrganizationService;
import com.oo.system.api.feign.RemoteTranslateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 项目管理
 *
 * @author
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dcmdorganization")
@Api(value = "组织机构层级关系--项目管理", tags = "组织机构层级关系--项目管理接口")
public class DcMdOrganizationController extends BaseController {

    private final DcMdOrganizationMapper dcMdOrganizationMapper;
    private final IDcMdOrganizationService dcMdOrganizationService;
    private final RedisService redisService;
    @Autowired(required = false)
    private RemoteTranslateService remoteTranslateService;
    private final DcMdProjectMapper dcMdProjectMapper;
    private final DcMdWellMapper dcMdWellMapper;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询组织机构层级关系--项目管理列表", notes = "传入dcMdOrganization")
    public TableDataInfo list(DcMdOrganization dcMdOrganization)
    {
        startPage();
        List<DcMdOrganization> list = dcMdOrganizationService.selectDcMdOrganizationList(dcMdOrganization);
        String dictLabel="";

        //关联字典表查询
        for(DcMdOrganization list1:list){
            dictLabel = DictUtils.getDictLabel("if_or_no", list1.getIfFocus());
            list1.setIfFocus(dictLabel == null ? "" : dictLabel);
            dictLabel = DictUtils.getDictLabel("base_organization_type", list1.getType());
            list1.setType(dictLabel == null ? "" : dictLabel);
        }

        return getDataTable(list);
    }

    /**
     * 根据id获取区块管理详细信息
     */
    @GetMapping(value = "/selectDcMdOrganization")
    public AjaxResult getInfo(@RequestParam String Id) {
        if (StringUtils.isEmpty(Id)) {
            return AjaxResult.error("Id不能为空");
        }

        //关联字典表查询
        DcMdOrganization dcMdOrganization=dcMdOrganizationMapper.selectById(Id);
        String dictLabel = DictUtils.getDictLabel("if_or_no", dcMdOrganization.getIfFocus());
        dcMdOrganization.setIfFocus(dictLabel == null ? "" : dictLabel);
        dictLabel = DictUtils.getDictLabel("base_organization_type", dcMdOrganization.getType());
        dcMdOrganization.setType(dictLabel == null ? "" : dictLabel);

        return AjaxResult.success(dcMdOrganization);
    }

    /**
     * 查询区块管理
     */
    @GetMapping(value = "/selectDcMdOrganizationQuery")
    public AjaxResult selectDcMdProjectQuery(String name, String nameDetail, Integer type) {
        startPage();
        QueryWrapper<DcMdOrganization> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(nameDetail)) {
            queryWrapper.like("name_detail", nameDetail);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        queryWrapper.orderByAsc("order_num");
        List<DcMdOrganization>  dcMdOrganizationList =dcMdOrganizationMapper.selectList(queryWrapper);
        String dictLabel="";

        //关联字典表查询
        for(DcMdOrganization dcMdOrganization:dcMdOrganizationList){
            dictLabel = DictUtils.getDictLabel("if_or_no", dcMdOrganization.getIfFocus());
            dcMdOrganization.setIfFocus(dictLabel == null ? "" : dictLabel);

            dictLabel = DictUtils.getDictLabel("base_organization_type", dcMdOrganization.getType());
            dcMdOrganization.setType(dictLabel == null ? "" : dictLabel);
        }
        return AjaxResult.success(dcMdOrganizationList);
    }

    /**
     * 根据id删除单井信息详细信息
     */
    @DeleteMapping(value = "/delete")
    public AjaxResult deleteDcMdOrganization(@RequestParam String Ids) {
        String[] Id = Ids.split(",");
        List<String> list = new ArrayList(Arrays.asList(Id));
        for (String a : list) {
            if (a == null || StringUtils.isEmpty(a)) {
                return AjaxResult.error("id不能为空");
            }
        }
        int result=dcMdOrganizationMapper.deleteBatchIds(list);//主表删除
        //清除缓存
        redisService.clear(DataManageCache.ORGANIZATION_CACHE);
        //删除多语言
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
    @PostMapping(value = "/saveOrUpdateDcMdOrganization")
    public AjaxResult saveOrUpdateDcMdOrganization(@RequestBody DcMdOrganization dcMdOrganization) {
        dcMdOrganization.setCreateTime(DateUtils.getNowDate());
        dcMdOrganization.setUpdateTime(DateUtils.getNowDate());

//        //获取字典表键值
//        String dictLabel = DictUtils.getDictValue("if_or_no", dcMdOrganization.getIfFocus());
//        dcMdOrganization.setIfFocus(dictLabel == null ? "" : dictLabel);
        String id= IdUtils.simpleUUID();
        dcMdOrganization.setId(id);
        boolean result=dcMdOrganizationService.saveOrUpdate(dcMdOrganization);//主表保存/更新

//        if(dcMdOrganization.getId()==null){
//            dcMdOrganization.setId(id);
//        }
        //保存多语言
        TranslateSubmitVo translateSubmitVo=new TranslateSubmitVo();
        translateSubmitVo.setFieldId(dcMdOrganization.getId());
        translateSubmitVo.setVoList(dcMdOrganization.getTransList());
        boolean transResult = remoteTranslateService.translateSubmit(translateSubmitVo);
        return AjaxResult.success(result&&transResult);
    }

    /**
     * 查询组织机构
     */
    @GetMapping(value = "/selectTreeList")
    public R<List<TreeSelectForString>> selectTreeList(String name, Integer type) {
        startPage();
        List<SysMenuWell> sysRoleDocMenuList = dcMdOrganizationMapper.selectTreeList(name, type);
        List<SysMenuWell> menuTrees = TreeUtils.buildMenuWellTree(sysRoleDocMenuList);
        return R.ok(menuTrees.stream().map(TreeSelectForString::new).collect(Collectors.toList()));
    }

    /**
     * 大屏根据国家名称获取项目信息 1：项目  2：区块
     */
    @GetMapping(value = "/getPorjectInfo")
    public AjaxResult getPorjectInfo(String companyName, Date beginTime, Date endTime, @RequestParam Integer type)
    {

        AjaxResult ajax = AjaxResult.success();
        List<MdProjectVo> data = new ArrayList<>();
        List<MdNumProjectVo> num = new ArrayList<>();
        //切换为项目
        if(type == 1){
           data = dcMdOrganizationMapper.selectProjectList(companyName,beginTime,endTime);
           num = dcMdOrganizationMapper.selectProjectNum(companyName,beginTime,endTime);
        }//切换为区块
        else {
           data = dcMdOrganizationMapper.selectBlockList(companyName,beginTime,endTime);
           num = dcMdOrganizationMapper.selectBlockNum(companyName,beginTime,endTime);
        }

        ajax.put("roles", data);
        ajax.put("posts", num);
        return ajax;
    }
    /**
     * 获取所有国家名称，id
     */
    @GetMapping(value = "/getAllCountry")
    public AjaxResult getAllCountry(){
        return AjaxResult.success(dcMdOrganizationMapper.selectAllCountry());
    }
    /**
     * 大屏 综合对比
     * comparisonType  选择类型  井对比/区块对比/项目对比
     */
    @GetMapping(value = "/getComprehensiveComparison")
    public R<List<TreeSelectForString>> getComprehensiveComparison(@RequestParam Integer comparisonType,Date beginTime, Date endTime,String name){
        //井对比
        if(comparisonType == 1){
            List<SysMenuWell> sysRoleDocMenuList = dcMdWellMapper.selectScreenTreeList(beginTime,endTime,name);
            List<SysMenuWell> menuTrees = TreeUtils.buildMenuWellTree(sysRoleDocMenuList);
            return R.ok(menuTrees.stream().map(TreeSelectForString::new).collect(Collectors.toList()));
        }//区块对比
        else if(comparisonType == 2){
            List<SysMenuWell> sysRoleDocMenuList = dcMdProjectMapper.selectScreenTreeList(beginTime, endTime,name);
            List<SysMenuWell> menuTrees = TreeUtils.buildMenuWellTree(sysRoleDocMenuList);
            return R.ok(menuTrees.stream().map(TreeSelectForString::new).collect(Collectors.toList()));
        }//项目对比
        else {
            List<SysMenuWell> sysRoleDocMenuList = dcMdOrganizationMapper.selectScreenTreeList(beginTime, endTime,name);
            List<SysMenuWell> menuTrees = TreeUtils.buildMenuWellTree(sysRoleDocMenuList);
            return R.ok(menuTrees.stream().map(TreeSelectForString::new).collect(Collectors.toList()));
        }
    }
    /**
     * 国家地图 1：项目  2：区块
     */
    @GetMapping(value = "/getOrganizationChangeData")
    public AjaxResult getOrganizationChangeData(@RequestParam Integer type,@RequestParam String companyId)
    {
        List<MdProjectVo> data = new ArrayList<>();
        //切换为项目
        if(type == 1){
            data = dcMdOrganizationMapper.selectOrganizationProjectList(companyId);
        }//切换为区块
        else {
            data = dcMdOrganizationMapper.selectOrganizationBlockList(companyId);
        }
        return AjaxResult.success(data);
    }
}
