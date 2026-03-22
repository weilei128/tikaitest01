package com.oo.reportforms.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;
import com.oo.reportforms.service.IDcReportScientificCensorTotalService;
import com.oo.reportforms.utils.ReportUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * （技术管理岗）十四五重大科研课题审查统计0620-林志强Controller
 * 
 * @author oo
 * @date 2023-08-11
 */
@AllArgsConstructor
@RestController
@RequestMapping("/total")
@Api(value = "重大科研课题审查统计", tags = "重大科研课题审查统计")
public class DcReportScientificCensorTotalController extends BaseController
{
    private final IDcReportScientificCensorTotalService dcReportScientificCensorTotalService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:total:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询重大科研课题审查统计列表", notes = "传入dcReportScientificCensorTotal")
    public TableDataInfo list(DcReportScientificCensorTotal dcReportScientificCensorTotal)
    {
        startPage();
        List<DcReportScientificCensorTotal> list = dcReportScientificCensorTotalService.selectDcReportScientificCensorTotalList(dcReportScientificCensorTotal);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    //@RequiresPermissions("reportforms:total:export")
    @Log(title = "重大科研课题审查统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出重大科研课题审查统计列表", notes = "传入dcReportScientificCensorTotal")
    public void export(HttpServletResponse response, DcReportScientificCensorTotal dcReportScientificCensorTotal) throws IOException {
        dcReportScientificCensorTotalService.export(response, dcReportScientificCensorTotal);
    }

    /**
     * 详细信息
     */
    //@RequiresPermissions("reportforms:total:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询重大科研课题审查统计详情", notes = "传入id")
    public R<DcReportScientificCensorTotal> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportScientificCensorTotalService.getById(id));
    }

    /**
     * 保存
     */
    //@RequiresPermissions("reportforms:total:submit")
    @Log(title = "重大科研课题审查统计", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存重大科研课题审查统计", notes = "传入dcReportScientificCensorTotal")
    public R submit(@RequestBody List<DcReportScientificCensorTotal> reportScientificCensorTotals)
    {
        return R.status(dcReportScientificCensorTotalService.saveOrUpdateBatch(reportScientificCensorTotals));
    }

    /**
     * 删除
     */
    //@RequiresPermissions("reportforms:total:remove")
    @Log(title = "重大科研课题审查统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除重大科研课题审查统计", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportScientificCensorTotalService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 物理删除
     */
    @Log(title = "重大科研课题审查统计", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/delete")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "物理删除", notes = "传入ids")
    public R delete(@RequestParam String[] ids)
    {
        return R.status(dcReportScientificCensorTotalService.deleteItemsByIds(ids));
    }

    /**
     * 下载导入模板
     */
    @PostMapping("/importTemplate")
    @ApiOperation(value = "下载导入模板", notes = "")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ReportUtils.downloadTemplate(response, "（技术管理岗）十四五重大科研课题审查统计.xlsx");
    }

    /**
     * 导入数据
     */
    @Log(title = "重大科研课题审查统计", businessType = BusinessType.IMPORT)
    @PostMapping("/importData/{sheetNum}")
    @ApiOperation(value = "导入数据", notes = "传入file")
    public R importData(MultipartFile file, String sheetNum) throws Exception
    {
        ExcelUtil<DcReportScientificCensorTotal> util = new ExcelUtil<>(DcReportScientificCensorTotal.class);
        if (StringUtils.isEmpty(sheetNum)) {
            sheetNum = "0";
        }
        StringBuilder message = new StringBuilder();
        for (String num : sheetNum.split(",")) {
            List<DcReportScientificCensorTotal> dataList = util.importExcel(Integer.parseInt(num), file.getInputStream(), 0);
            message.append(dcReportScientificCensorTotalService.importData(dataList, Integer.parseInt(num)));
        }
        return R.ok(message.toString());
    }
}
