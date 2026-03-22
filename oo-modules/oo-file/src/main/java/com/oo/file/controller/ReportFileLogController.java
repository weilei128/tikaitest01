package com.oo.file.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import com.oo.common.core.domain.R;
import com.oo.file.service.IReportFileLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.system.api.domain.ReportFileLog;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 文件操作日志Controller
 * 
 * @author oo
 * @date 2023-10-18
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportFileLog")
@Api(value = "文件操作日志", tags = "文件操作日志接口")
public class ReportFileLogController extends BaseController
{
    private final IReportFileLogService reportFileLogService;

    /**
     * 列表
     */
//    @RequiresPermissions("file:reportFileLog:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询文件操作日志列表", notes = "传入reportFileLog")
    public TableDataInfo list(ReportFileLog reportFileLog)
    {
        startPage();
        List<ReportFileLog> list = reportFileLogService.selectReportFileLogList(reportFileLog);
        return getDataTable(list);
    }

    /**
     * 导出
     */
//    @RequiresPermissions("file:reportFileLog:export")
    @Log(title = "文件操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出文件操作日志列表", notes = "传入reportFileLog")
    public void export(HttpServletResponse response, ReportFileLog reportFileLog)
    {
        List<ReportFileLog> list = reportFileLogService.selectReportFileLogList(reportFileLog);
        ExcelUtil<ReportFileLog> util = new ExcelUtil<ReportFileLog>(ReportFileLog.class);
        util.exportExcel(response, list, "文件操作日志数据");
    }

    /**
     * 详细信息
     */
//    @RequiresPermissions("file:reportFileLog:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询文件操作日志详情", notes = "传入id")
    public R<ReportFileLog> getInfo(@PathVariable("id") String id)
    {
        return R.ok(reportFileLogService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增文件操作日志", notes = "传入reportFileLog")
    public R add(@RequestBody ReportFileLog reportFileLog)
    {
        return R.status(reportFileLogService.save(reportFileLog));
    }
//
//    /**
//     * 修改
//     */
//    @RequiresPermissions("file:reportFileLog:edit")
//    @Log(title = "文件操作日志", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperationSupport(order = 5)
//    @ApiOperation(value = "修改文件操作日志", notes = "传入reportFileLog")
//    public R edit(@RequestBody ReportFileLog reportFileLog)
//    {
//        return R.status(reportFileLogService.updateById(reportFileLog));
//    }

    /**
     * 删除
     */
    @RequiresPermissions("file:reportFileLog:remove")
    @Log(title = "文件操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除文件操作日志", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(reportFileLogService.removeByIds(Arrays.asList(ids)));
    }
}
