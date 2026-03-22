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
import com.oo.reportforms.domain.DcReportRollingBugdetTenmon;
import com.oo.reportforms.service.DcReportRollingBugdetTenmonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测10+2Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetTenmon")
@Api(value = "滚动预测10+2", tags = "滚动预测10+2接口")
public class DcReportRollingBugdetTenmonController extends BaseController
{
    private final DcReportRollingBugdetTenmonService dcReportRollingBugdetTenmonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测10+2列表", notes = "传入dcReportRollingBugdetTenmon")
    public TableDataInfo list(DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon)
    {
        startPage();
        List<DcReportRollingBugdetTenmon> list = dcReportRollingBugdetTenmonService.selectDcReportRollingBugdetTenmonList(dcReportRollingBugdetTenmon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:export")
    @Log(title = "滚动预测10+2", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测10+2列表", notes = "传入dcReportRollingBugdetTenmon")
    public void export(HttpServletResponse response, DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon)
    {
        List<DcReportRollingBugdetTenmon> list = dcReportRollingBugdetTenmonService.selectDcReportRollingBugdetTenmonList(dcReportRollingBugdetTenmon);
        ExcelUtil<DcReportRollingBugdetTenmon> util = new ExcelUtil<DcReportRollingBugdetTenmon>(DcReportRollingBugdetTenmon.class);
        util.exportExcel(response, list, "滚动预测10+2数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测10+2详情", notes = "传入id")
    public R<DcReportRollingBugdetTenmon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetTenmonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:add")
    @Log(title = "滚动预测10+2", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测10+2", notes = "传入dcReportRollingBugdetTenmon")
    public R add(@RequestBody DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon)
    {
        return R.status(dcReportRollingBugdetTenmonService.save(dcReportRollingBugdetTenmon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:edit")
    @Log(title = "滚动预测10+2", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测10+2", notes = "传入dcReportRollingBugdetTenmon")
    public R edit(@RequestBody DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon)
    {
        return R.status(dcReportRollingBugdetTenmonService.updateById(dcReportRollingBugdetTenmon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTenmon:remove")
    @Log(title = "滚动预测10+2", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测10+2", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetTenmonService.removeByIds(Arrays.asList(ids)));
    }
}
