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
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmon;
import com.oo.reportforms.service.DcReportRollingBugdetSevenmonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测7+5Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetSevenmon")
@Api(value = "滚动预测7+5", tags = "滚动预测7+5接口")
public class DcReportRollingBugdetSevenmonController extends BaseController
{
    private final DcReportRollingBugdetSevenmonService dcReportRollingBugdetSevenmonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测7+5列表", notes = "传入dcReportRollingBugdetSevenmon")
    public TableDataInfo list(DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon)
    {
        startPage();
        List<DcReportRollingBugdetSevenmon> list = dcReportRollingBugdetSevenmonService.selectDcReportRollingBugdetSevenmonList(dcReportRollingBugdetSevenmon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:export")
    @Log(title = "滚动预测7+5", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测7+5列表", notes = "传入dcReportRollingBugdetSevenmon")
    public void export(HttpServletResponse response, DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon)
    {
        List<DcReportRollingBugdetSevenmon> list = dcReportRollingBugdetSevenmonService.selectDcReportRollingBugdetSevenmonList(dcReportRollingBugdetSevenmon);
        ExcelUtil<DcReportRollingBugdetSevenmon> util = new ExcelUtil<DcReportRollingBugdetSevenmon>(DcReportRollingBugdetSevenmon.class);
        util.exportExcel(response, list, "滚动预测7+5数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测7+5详情", notes = "传入id")
    public R<DcReportRollingBugdetSevenmon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetSevenmonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:add")
    @Log(title = "滚动预测7+5", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测7+5", notes = "传入dcReportRollingBugdetSevenmon")
    public R add(@RequestBody DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon)
    {
        return R.status(dcReportRollingBugdetSevenmonService.save(dcReportRollingBugdetSevenmon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:edit")
    @Log(title = "滚动预测7+5", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测7+5", notes = "传入dcReportRollingBugdetSevenmon")
    public R edit(@RequestBody DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon)
    {
        return R.status(dcReportRollingBugdetSevenmonService.updateById(dcReportRollingBugdetSevenmon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSevenmon:remove")
    @Log(title = "滚动预测7+5", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测7+5", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetSevenmonService.removeByIds(Arrays.asList(ids)));
    }
}
