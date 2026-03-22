package com.oo.reportforms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.utils.DictUtils;
import com.oo.reportforms.domain.*;
import com.oo.reportforms.domain.vo.CountryByWellVo;
import com.oo.reportforms.domain.vo.DcAllWellVo;
import com.oo.reportforms.domain.vo.DcRportWellCheckVo;
import com.oo.reportforms.mapper.DcReportDrillWellAssesMapper;
import com.oo.reportforms.mapper.DcRportWellCheckMapper;
import com.oo.reportforms.service.DcRportWellCheckService;
import com.oo.reportforms.utils.ReportUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * （井控管理岗）海油国际井控检查记录报表
 *
 * @author
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dcrportwellcheck")
@Api(value = "（井控管理岗）海油国际井控检查记录", tags = "（井控管理岗）海油国际井控检查记录")
public class DcRportWellCheckController extends BaseController {
    @Autowired
    private DcRportWellCheckMapper dcRportWellCheckMapper;
    @Autowired
    private DcRportWellCheckService dcRportWellCheckService;
    @Autowired
    private DcReportDrillWellAssesMapper dcReportDrillWellAssesMapper;

    /**
     * （井控管理岗）海油国际井控检查记录报表查询
     */
    @GetMapping(value = "/select")
    @ApiOperation(value = "查询")
    public TableDataInfo selectDcReportProjectExecuteTrace(String well_id, String organization_id, String year)
    {
        startPage();
        QueryWrapper<DcRportWellCheck> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(well_id)){//井id
            queryWrapper.eq("well_id",well_id);
        }
        if(StringUtils.isNotEmpty(organization_id)){//国家id
            queryWrapper.eq("organization_id",organization_id);
        }
        if(StringUtils.isNotEmpty(year)){//年份
            queryWrapper.eq("year",year);
        }
        queryWrapper.eq("del_flag","0");
        queryWrapper.orderByAsc("year");
        String dictLabel="";

        List<DcRportWellCheckVo> dcRportWellCheckVoList=new ArrayList<>();
        List<DcRportWellCheck> dcRportWellCheckList=dcRportWellCheckMapper.selectList(queryWrapper);
        for(DcRportWellCheck dcRportWellCheck:dcRportWellCheckList){
            DcRportWellCheckVo dcRportWellCheckVo=new DcRportWellCheckVo();
            BeanUtils.copyProperties(dcRportWellCheck,dcRportWellCheckVo);

            if(StringUtils.isNotEmpty(dcRportWellCheck.getOrganizationId())) {
                dcRportWellCheckVo.setOrganizationName(dcReportDrillWellAssesMapper.selectName((dcRportWellCheck.getOrganizationId())));
            }
            if(StringUtils.isNotEmpty(dcRportWellCheck.getWellId())) {
                dcRportWellCheckVo.setWellName(dcReportDrillWellAssesMapper.selectWellName(dcRportWellCheck.getWellId()));
            }
            dictLabel = DictUtils.getDictLabel("risk_type", dcRportWellCheck.getRiskLevel());//
            dcRportWellCheckVo.setRiskLevel(dictLabel == null ? "" : dictLabel);
            dcRportWellCheckVoList.add(dcRportWellCheckVo);
        }
        return getDataTable(dcRportWellCheckVoList);
    }

    /**
     * 下载导入模板
     */
    @PostMapping("/importTemplate")
    @ApiOperation(value = "下载导入模板", notes = "")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ReportUtils.downloadTemplate(response, "（井控管理岗）海油国际井控检查记录.xlsx");
    }

    /**
     * 导入数据
     */
    @Log(title = "海油国际井控检查记录", businessType = BusinessType.IMPORT)
    @PostMapping("/importData/{sheetNum}")
    @ApiOperation(value = "导入数据", notes = "传入file")
    public R importData(@PathVariable String sheetNum,MultipartFile file) throws Exception
    {
        ExcelUtil<DcRportWellCheckVo> util = new ExcelUtil(DcRportWellCheckVo.class);
        List<DcRportWellCheckVo> dataList = util.importExcel("井控检查", file.getInputStream(), 0);
        String message = dcRportWellCheckService.importData(dataList);
        return R.ok(message);
    }
    /**
     * 根据id删除单井信息详细信息
     */
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "批量删除")
    public AjaxResult deleteDcMdOrganization(@RequestParam String ids) {
        String[] Id = ids.split(",");
        List<String> list = new ArrayList(Arrays.asList(Id));
        for (String a : list) {
            if (a == null || StringUtils.isEmpty(a)) {
                return AjaxResult.error("id不能为空");
            }
        }
        return AjaxResult.success(dcRportWellCheckMapper.deleteBatchIds(list));

    }
    /**
     * 根据id删除单井信息详细信息
     */
    @DeleteMapping(value = "/falseDelete")
    @ApiOperation(value = "删除海油国际井控检查记录(伪删除)")
    public AjaxResult falseDeleteDcMdOrganization(@RequestParam String ids) {
        String[] Id = ids.split(",");
//        List<String> list = new ArrayList(Arrays.asList(Id));
//        for (String a : list) {
//            if (a == null || StringUtils.isEmpty(a)) {
//                return AjaxResult.error("id不能为空");
//            }
//        }
        dcRportWellCheckMapper.updateDelete(Id);
        return AjaxResult.success("删除成功");

    }
    /**
     * 根据id更新、新增海油国际井控检查记录
     */
    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "批量更新、新增")
    @DictMethod(dtoClass = DcRportWellCheck.class)
    public AjaxResult saveOrUpdateDcMdOrganization(@RequestBody List<DcRportWellCheck> dcRportWellCheckList)
    {
        for(DcRportWellCheck dcRportWellCheck:dcRportWellCheckList){
            dcRportWellCheck.setYear(DateUtils.getDate().substring(0,4));
            dcRportWellCheck.setUpdateTime(DateUtils.getNowDate());
            dcRportWellCheck.setCreateTime(DateUtils.getNowDate());
        }
        return AjaxResult.success(dcRportWellCheckService.saveOrUpdateBatch(dcRportWellCheckList));
    }


    /**
     * 导出
     */
    @Log(title = "导出海油国际井控检查记录列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出海油国际井控检查记录列表", notes = "传入dcReportContractOrdersService")
    public void export(HttpServletResponse response,String well_id, String organization_id, String year) throws IOException {
        dcRportWellCheckService.export(response, well_id,organization_id,year);
    }


    /**
     * 获取单井信息表格
     */
    @ApiOperation(value = "获取全部单井id和井名")
    @GetMapping(value = "/selectAllWell")
    public AjaxResult selectAllWell()
    {
        List<DcAllWellVo> dcAllWellVoList=dcRportWellCheckMapper.selectAllWell();
        return AjaxResult.success(dcAllWellVoList);
    }
    /**
     * 获取单井信息表格
     */
    @ApiOperation(value = "获取全部国家id和井名")
    @GetMapping(value = "/selectAllCountry")
    public AjaxResult selectAllCountry()
    {
        List<DcAllWellVo> dcAllWellVoList=dcRportWellCheckMapper.selectAllCountry();
        return AjaxResult.success(dcAllWellVoList);
    }
    /**
     * 通过井id查国家名
     */
    @ApiOperation(value = "通过井id查国家名")
    @GetMapping(value = "/selectCountryByWell")
    public AjaxResult selectCountryByWell(String id)
    {
        if(StringUtils.isEmpty(id)){
            return AjaxResult.error("id不能为空");
        }
        CountryByWellVo countryByWellVo=dcRportWellCheckMapper.selectCountryByWell(id);
        if(countryByWellVo==null){
            return AjaxResult.success("表里无对应国家数据");
        }
        return AjaxResult.success(countryByWellVo);
    }
}
