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
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemon;
import com.oo.reportforms.service.DcReportRollingBugdetTwelvemonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测12+0Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetTwelvemon")
@Api(value = "滚动预测12+0", tags = "滚动预测12+0接口")
public class DcReportRollingBugdetTwelvemonController extends BaseController
{
    private final DcReportRollingBugdetTwelvemonService dcReportRollingBugdetTwelvemonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测12+0列表", notes = "传入dcReportRollingBugdetTwelvemon")
    public TableDataInfo list(DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon)
    {
        startPage();
        List<DcReportRollingBugdetTwelvemon> list = dcReportRollingBugdetTwelvemonService.selectDcReportRollingBugdetTwelvemonList(dcReportRollingBugdetTwelvemon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:export")
    @Log(title = "滚动预测12+0", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测12+0列表", notes = "传入dcReportRollingBugdetTwelvemon")
    public void export(HttpServletResponse response, DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon)
    {
        List<DcReportRollingBugdetTwelvemon> list = dcReportRollingBugdetTwelvemonService.selectDcReportRollingBugdetTwelvemonList(dcReportRollingBugdetTwelvemon);
        ExcelUtil<DcReportRollingBugdetTwelvemon> util = new ExcelUtil<DcReportRollingBugdetTwelvemon>(DcReportRollingBugdetTwelvemon.class);
        util.exportExcel(response, list, "滚动预测12+0数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测12+0详情", notes = "传入id")
    public R<DcReportRollingBugdetTwelvemon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetTwelvemonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:add")
    @Log(title = "滚动预测12+0", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测12+0", notes = "传入dcReportRollingBugdetTwelvemon")
    public R add(@RequestBody DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon)
    {
        return R.status(dcReportRollingBugdetTwelvemonService.save(dcReportRollingBugdetTwelvemon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:edit")
    @Log(title = "滚动预测12+0", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测12+0", notes = "传入dcReportRollingBugdetTwelvemon")
    public R edit(@RequestBody DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon)
    {
        return R.status(dcReportRollingBugdetTwelvemonService.updateById(dcReportRollingBugdetTwelvemon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwelvemon:remove")
    @Log(title = "滚动预测12+0", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测12+0", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetTwelvemonService.removeByIds(Arrays.asList(ids)));
    }
}
