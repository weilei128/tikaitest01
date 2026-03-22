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
import com.oo.reportforms.domain.DcReportRollingBugdetSixmon;
import com.oo.reportforms.service.DcReportRollingBugdetSixmonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测6+6Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetSixmon")
@Api(value = "滚动预测6+6", tags = "滚动预测6+6接口")
public class DcReportRollingBugdetSixmonController extends BaseController
{
    private final DcReportRollingBugdetSixmonService dcReportRollingBugdetSixmonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测6+6列表", notes = "传入dcReportRollingBugdetSixmon")
    public TableDataInfo list(DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon)
    {
        startPage();
        List<DcReportRollingBugdetSixmon> list = dcReportRollingBugdetSixmonService.selectDcReportRollingBugdetSixmonList(dcReportRollingBugdetSixmon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:export")
    @Log(title = "滚动预测6+6", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测6+6列表", notes = "传入dcReportRollingBugdetSixmon")
    public void export(HttpServletResponse response, DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon)
    {
        List<DcReportRollingBugdetSixmon> list = dcReportRollingBugdetSixmonService.selectDcReportRollingBugdetSixmonList(dcReportRollingBugdetSixmon);
        ExcelUtil<DcReportRollingBugdetSixmon> util = new ExcelUtil<DcReportRollingBugdetSixmon>(DcReportRollingBugdetSixmon.class);
        util.exportExcel(response, list, "滚动预测6+6数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测6+6详情", notes = "传入id")
    public R<DcReportRollingBugdetSixmon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetSixmonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:add")
    @Log(title = "滚动预测6+6", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测6+6", notes = "传入dcReportRollingBugdetSixmon")
    public R add(@RequestBody DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon)
    {
        return R.status(dcReportRollingBugdetSixmonService.save(dcReportRollingBugdetSixmon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:edit")
    @Log(title = "滚动预测6+6", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测6+6", notes = "传入dcReportRollingBugdetSixmon")
    public R edit(@RequestBody DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon)
    {
        return R.status(dcReportRollingBugdetSixmonService.updateById(dcReportRollingBugdetSixmon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetSixmon:remove")
    @Log(title = "滚动预测6+6", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测6+6", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetSixmonService.removeByIds(Arrays.asList(ids)));
    }
}
