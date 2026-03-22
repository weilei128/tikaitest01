package com.oo.reportforms.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import com.oo.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.reportforms.domain.DcReportRollingBugdetThreemon;
import com.oo.reportforms.service.DcReportRollingBugdetThreemonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测3+9Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetThreemon")
@Api(value = "滚动预测3+9", tags = "滚动预测3+9接口")
public class DcReportRollingBugdetThreemonController extends BaseController
{
    private final DcReportRollingBugdetThreemonService dcReportRollingBugdetThreemonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测3+9列表", notes = "传入dcReportRollingBugdetThreemon")
    public TableDataInfo list(DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon)
    {
        startPage();
        List<DcReportRollingBugdetThreemon> list = dcReportRollingBugdetThreemonService.selectDcReportRollingBugdetThreemonList(dcReportRollingBugdetThreemon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:export")
    @Log(title = "滚动预测3+9", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测3+9列表", notes = "传入dcReportRollingBugdetThreemon")
    public void export(HttpServletResponse response, DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon)
    {
        List<DcReportRollingBugdetThreemon> list = dcReportRollingBugdetThreemonService.selectDcReportRollingBugdetThreemonList(dcReportRollingBugdetThreemon);
        ExcelUtil<DcReportRollingBugdetThreemon> util = new ExcelUtil<DcReportRollingBugdetThreemon>(DcReportRollingBugdetThreemon.class);
        util.exportExcel(response, list, "滚动预测3+9数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测3+9详情", notes = "传入id")
    public R<DcReportRollingBugdetThreemon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetThreemonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:add")
    @Log(title = "滚动预测3+9", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测3+9", notes = "传入dcReportRollingBugdetThreemon")
    public R add(@RequestBody DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon)
    {
        return R.status(dcReportRollingBugdetThreemonService.save(dcReportRollingBugdetThreemon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:edit")
    @Log(title = "滚动预测3+9", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测3+9", notes = "传入dcReportRollingBugdetThreemon")
    public R edit(@RequestBody DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon)
    {
        return R.status(dcReportRollingBugdetThreemonService.updateById(dcReportRollingBugdetThreemon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetThreemon:remove")
    @Log(title = "滚动预测3+9", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测3+9", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetThreemonService.removeByIds(Arrays.asList(ids)));
    }
}
