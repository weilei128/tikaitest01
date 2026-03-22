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
import com.oo.reportforms.domain.DcReportRollingBugdetTwomon;
import com.oo.reportforms.service.DcReportRollingBugdetTwomonService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 滚动预测2+10Controller
 * 
 * @author oo
 * @date 2023-10-31
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportRollingBugdetTwomon")
@Api(value = "滚动预测2+10", tags = "滚动预测2+10接口")
public class DcReportRollingBugdetTwomonController extends BaseController
{
    private final DcReportRollingBugdetTwomonService dcReportRollingBugdetTwomonService;

    /**
     * 列表
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询滚动预测2+10列表", notes = "传入dcReportRollingBugdetTwomon")
    public TableDataInfo list(DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon)
    {
        startPage();
        List<DcReportRollingBugdetTwomon> list = dcReportRollingBugdetTwomonService.selectDcReportRollingBugdetTwomonList(dcReportRollingBugdetTwomon);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:export")
    @Log(title = "滚动预测2+10", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出滚动预测2+10列表", notes = "传入dcReportRollingBugdetTwomon")
    public void export(HttpServletResponse response, DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon)
    {
        List<DcReportRollingBugdetTwomon> list = dcReportRollingBugdetTwomonService.selectDcReportRollingBugdetTwomonList(dcReportRollingBugdetTwomon);
        ExcelUtil<DcReportRollingBugdetTwomon> util = new ExcelUtil<DcReportRollingBugdetTwomon>(DcReportRollingBugdetTwomon.class);
        util.exportExcel(response, list, "滚动预测2+10数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询滚动预测2+10详情", notes = "传入id")
    public R<DcReportRollingBugdetTwomon> getInfo(@PathVariable("id") String id)
    {
        return R.ok(dcReportRollingBugdetTwomonService.getById(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:add")
    @Log(title = "滚动预测2+10", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增滚动预测2+10", notes = "传入dcReportRollingBugdetTwomon")
    public R add(@RequestBody DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon)
    {
        return R.status(dcReportRollingBugdetTwomonService.save(dcReportRollingBugdetTwomon));
    }

    /**
     * 修改
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:edit")
    @Log(title = "滚动预测2+10", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改滚动预测2+10", notes = "传入dcReportRollingBugdetTwomon")
    public R edit(@RequestBody DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon)
    {
        return R.status(dcReportRollingBugdetTwomonService.updateById(dcReportRollingBugdetTwomon));
    }

    /**
     * 删除
     */
    @RequiresPermissions("reportforms:reportRollingBugdetTwomon:remove")
    @Log(title = "滚动预测2+10", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除滚动预测2+10", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(dcReportRollingBugdetTwomonService.removeByIds(Arrays.asList(ids)));
    }
}
