package com.oo.datamanagement.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.redis.service.RedisService;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.common.security.utils.DictUtils;
import com.oo.datamanagement.api.cache.DataManageCache;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.domain.vo.MdWellVo;
import com.oo.datamanagement.mapper.DcMdOrganizationMapper;
import com.oo.datamanagement.mapper.DcMdProjectMapper;
import com.oo.datamanagement.mapper.DcMdWellMapper;
import com.oo.datamanagement.service.IDcMdWellService;
import com.oo.system.api.domain.DcMdOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据管理
 *
 * @author
 */
@AllArgsConstructor
@RestController
@RequestMapping("/datamanagement")
@Api(value = "单井基础信息", tags = "单井基础信息接口")
public class DcMdWellController extends BaseController {

    private final DcMdWellMapper dcMdWellMapper;
    private final IDcMdWellService dcMdWellService;
    private final DcMdOrganizationMapper dcMdOrganizationMapper;
    private final DcMdProjectMapper dcMdProjectMapper;
    private final RedisService redisService;

    /**
     * 列表
     */
    @RequiresPermissions("datamanagement:well:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询单井基础信息列表", notes = "传入dcMdWell")
    public TableDataInfo list(String wellId,String type,String projectId)
    {
        startPage();
        List<MdWellVo> dcMdWellList=dcMdWellMapper.select(wellId,type,projectId);
        return getDataTable(dcMdWellList);
    }

    /**
     * 根据id获取单井信息详细信息
     */
    @GetMapping(value = "/selectDcMdWell")
    public AjaxResult getInfo(@RequestParam String Id)
    {
        if(StringUtils.isEmpty(Id)){
            return AjaxResult.error("Id不能为空");
        }
        DcMdWell dcMdWell=dcMdWellMapper.selectById(Id);

        DcMdOrganization dcMdOrganization=new DcMdOrganization();
        DcMdProject dcMdProject=new DcMdProject();

        MdWellVo mdWellVo=new MdWellVo();
        BeanUtils.copyProperties(dcMdWell,mdWellVo);

        dcMdOrganization=dcMdOrganizationMapper.selectById(dcMdWell.getOrganizationId());
        if(null!=dcMdOrganization&&StringUtils.isNotEmpty(dcMdOrganization.getName())){
            mdWellVo.setOrganizationName(dcMdOrganization.getName());
        }
        if(StringUtils.isNotEmpty(dcMdWell.getProjectId())){
            dcMdProject=dcMdProjectMapper.selectById(dcMdWell.getProjectId());
            if(null!=dcMdProject&&StringUtils.isNotEmpty(dcMdProject.getName())){
                mdWellVo.setProjectName(dcMdProject.getName());
            }
        }
        String dictLabel="";
        MdWellVo dcMdWellVo=new MdWellVo();
        BeanUtils.copyProperties(dcMdWell,dcMdWellVo);

        //关联字典表查询
        dictLabel = DictUtils.getDictLabel("if_or_no", dcMdWell.getIfwork());//是否作业者
        dcMdWellVo.setIfwork(dictLabel == null ? "" : dictLabel);

        dictLabel = DictUtils.getDictLabel("well_type", dcMdWell.getWelltype());//
        dcMdWellVo.setWelltype(dictLabel == null ? "" : dictLabel);

        dictLabel = DictUtils.getDictLabel("wellsubtype", dcMdWell.getWellsubtype());//
        dcMdWellVo.setWellsubtype(dictLabel == null ? "" : dictLabel);

        dictLabel = DictUtils.getDictLabel("usertxt6", dcMdWell.getUsertxt6());//
        dcMdWellVo.setUsertxt6(dictLabel == null ? "" : dictLabel);

        dictLabel = DictUtils.getDictLabel("dc_pk_well_type", dcMdWell.getType());//井型
        dcMdWellVo.setType(dictLabel == null ? "" : dictLabel);

        return AjaxResult.success(mdWellVo);
    }
    /**
     * 获取单井信息表格
     */
    @GetMapping(value = "/selectDcMdWellQuery")
    public TableDataInfo selectDcMdWellQuery(String wellId,String type,String projectId)
    {
        startPage();
        List<MdWellVo> mdWellVoList=new ArrayList<>();
        MdWellVo mdWellVo1=new MdWellVo();
        List<MdWellVo> dcMdWellList=dcMdWellMapper.select(wellId,type,projectId);
        String dictLabel="";

//        for(DcMdWell dcMdWell:dcMdWellList){
//            DcMdOrganization dcMdOrganization=new DcMdOrganization();
//            DcMdProject dcMdProject=new DcMdProject();
//            MdWellVo mdWellVo=new MdWellVo();
//            BeanUtils.copyProperties(dcMdWell,mdWellVo);
//
//            if(StringUtils.isNotEmpty(dcMdWell.getOrganizationId())){
//                dcMdOrganization=dcMdOrganizationMapper.selectById(dcMdWell.getOrganizationId());
//                if(null!=dcMdOrganization&&StringUtils.isNotEmpty(dcMdOrganization.getName())){
//                    mdWellVo.setOrganizationName(dcMdOrganization.getName());
//                }
//
//                if ("2".equals(type)) {//TYPE=2-国家
//                    mdWellVo.setCompanyName(dcMdOrganization.getName());//直接取国家id和name
//                    mdWellVo.setCompanyId(dcMdWell.getOrganizationId());
//                }else if ("3".equals(type)) {//TYPE=3项目
//                    dcMdOrganization=dcMdOrganizationMapper.selectById(dcMdOrganization.getParentId());//取父节点（国家）id和name
//                    mdWellVo.setCompanyName(dcMdOrganization.getName());
//                    mdWellVo.setCompanyId(dcMdOrganization.getId().toString());
//                }
//            }
//            if(StringUtils.isNotEmpty(dcMdWell.getProjectId())){
//                dcMdProject=dcMdProjectMapper.selectById(dcMdWell.getProjectId());
//                if(null!=dcMdProject&&StringUtils.isNotEmpty(dcMdProject.getName())){
//                    mdWellVo.setProjectNmae(dcMdProject.getName());
//                }
//            }
//            BeanUtils.copyProperties(dcMdWell,mdWellVo);
//
//            //关联字典表查询
//            dictLabel = DictUtils.getDictLabel("if_or_no", dcMdWell.getIfwork());//是否作业者
//            mdWellVo.setIfwork(dictLabel == null ? "" : dictLabel);
//
//            dictLabel = DictUtils.getDictLabel("well_type", dcMdWell.getWelltype());//
//            mdWellVo.setWelltypeName(dictLabel == null ? "" : dictLabel);
//
//            dictLabel = DictUtils.getDictLabel("wellsubtype", dcMdWell.getWellsubtype());//
//            mdWellVo.setWellsubtypeName(dictLabel == null ? "" : dictLabel);
//
//            dictLabel = DictUtils.getDictLabel("usertxt6", dcMdWell.getUsertxt6());//
//            mdWellVo.setUsertxt6Name(dictLabel == null ? "" : dictLabel);
//
//            dictLabel = DictUtils.getDictLabel("dc_pk_well_type", dcMdWell.getType());//井型
//            mdWellVo.setTypeName(dictLabel == null ? "" : dictLabel);
//
//            mdWellVoList.add(mdWellVo);
//        }
        return getDataTable(dcMdWellList);
    }
    /**
     * 根据id删除单井信息详细信息
     */
    @DeleteMapping(value = "/delete")
    public AjaxResult deleteDcMdWell(@RequestParam String Ids)
    {
        String[] Id = Ids.split(",");
        List<String> list = new ArrayList(Arrays.asList(Id));
        for (String a : list) {
            if (a == null || StringUtils.isEmpty(a)) {
                return AjaxResult.error("id不能为空");
            }
        }
        redisService.clear(DataManageCache.WELL_CACHE);
        return AjaxResult.success(dcMdWellMapper.deleteBatchIds(list));
    }
    /**
     * 根据id更新、新增单井信息详细信息
     */
    @PostMapping(value = "/saveOrUpdateDcMdWell")
//    @DictMethod(dtoClass = DcMdWell.class)
    public AjaxResult saveOrUpdateDcMdWell(@RequestBody MdWellVo mdWellVo)
    {
//        if(dcMdWell==null||StringUtils.isEmpty(dcMdWell.getId())){
//            return AjaxResult.error("不能为空");
//        }

//        //获取字典表键值
//        String dictLabel = DictUtils.getDictValue("if_or_no", dcMdWell.getIfwork().toString());
//        dcMdWell.setIfwork(dictLabel == null ? "" : dictLabel);
        //清除缓存
        DcMdWell dcMdWell=new DcMdWell();
        BeanUtils.copyProperties(mdWellVo,dcMdWell);
        if(StringUtils.isNotEmpty(mdWellVo.getIfworkId())) {
            dcMdWell.setIfwork(mdWellVo.getIfworkId());
        }
        if(StringUtils.isNotEmpty(mdWellVo.getWelltypeId())) {
            dcMdWell.setWelltype(mdWellVo.getWelltypeId());
        }
        if(StringUtils.isNotEmpty(mdWellVo.getWellsubtypeId())) {
            dcMdWell.setWellsubtype(mdWellVo.getWellsubtypeId());
        }
        if(StringUtils.isNotEmpty(mdWellVo.getUsertxt6Id())) {
            dcMdWell.setUsertxt6(mdWellVo.getUsertxt6Id());
        }
        if(StringUtils.isNotEmpty(mdWellVo.getTypeId())) {
            dcMdWell.setType(mdWellVo.getTypeId());
        }
        redisService.clear(DataManageCache.WELL_CACHE);
        return AjaxResult.success(dcMdWellService.saveOrUpdate(dcMdWell));
    }

    /**
     * 查询井型
     */
    @GetMapping(value = "/selectWellType")
    public AjaxResult selectWellType()
    {
        return AjaxResult.success(dcMdWellMapper.selectWellType());
    }

    /**
     * 按照级别 查询井别
     */
    @GetMapping(value = "/selectPurpose")
    public AjaxResult selectPurpose(Integer type)
    {
        return AjaxResult.success(dcMdWellMapper.selectPurpose(type));
    }

}
