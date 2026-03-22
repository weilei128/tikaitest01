package com.oo.file.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.log.annotation.FileLog;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.log.enums.FileOperType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.datamanagement.api.cache.DataManageCache;
import com.oo.file.domain.ReportFiles;
import com.oo.file.oss.UploadFileResult;
import com.oo.file.service.IReportFilesService;
import com.oo.system.api.domain.ReportFileLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 文件记录Controller
 * 
 * @author oo
 * @date 2023-09-26
 */
@AllArgsConstructor
@RestController
@RequestMapping("/reportFiles")
@Api(value = "文件记录", tags = "文件记录接口")
public class ReportFilesController extends BaseController
{
    private final IReportFilesService reportFilesService;

    /**
     * 列表
     */
    @RequiresPermissions("file:reportFiles:list")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询文件记录列表", notes = "传入reportFiles")
    public TableDataInfo list(ReportFiles reportFiles)
    {
        startPage();
        List<ReportFiles> list = reportFilesService.selectReportFilesList(reportFiles);
        return getDataTable(list);
    }

    /**
     * 列表（文件最新版本）
     */
    @RequiresPermissions("file:reportFiles:list")
    @GetMapping("/viewList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询文件记录浏览列表", notes = "传入reportFiles")
    public TableDataInfo viewList(ReportFiles reportFiles)
    {
        startPage();
        List<ReportFiles> list = reportFilesService.selectReportFilesLatestList(reportFiles);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @RequiresPermissions("file:reportFiles:export")
    @Log(title = "文件记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出文件记录列表", notes = "传入reportFiles")
    public void export(HttpServletResponse response, ReportFiles reportFiles)
    {
        List<ReportFiles> list = reportFilesService.selectReportFilesList(reportFiles);
        ExcelUtil<ReportFiles> util = new ExcelUtil<ReportFiles>(ReportFiles.class);
        util.exportExcel(response, list, "文件记录数据");
    }

    /**
     * 详细信息
     */
    @RequiresPermissions("file:reportFiles:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询文件记录详情", notes = "传入id")
    public R<ReportFiles> getInfo(@PathVariable("id") String id)
    {
        return R.ok(reportFilesService.getInfo(id));
    }

    /**
     * 新增
     */
    @RequiresPermissions("file:reportFiles:add")
    @FileLog(fileOperType = FileOperType.UPLOAD)
    @PostMapping
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增文件记录", notes = "传入reportFiles")
    public R add(@RequestBody ReportFiles reportFiles)
    {
        return R.status(reportFilesService.save(reportFiles));
    }

    /**
     * 修改
     */
    @RequiresPermissions("file:reportFiles:edit")
    @Log(title = "文件记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改文件记录", notes = "传入reportFiles")
    public R edit(@RequestBody ReportFiles reportFiles)
    {
        return R.status(reportFilesService.updateById(reportFiles));
    }

    /**
     * 删除
     */
    @RequiresPermissions("file:reportFiles:remove")
    @FileLog(fileOperType = FileOperType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除文件记录", notes = "传入ids")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(reportFilesService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 物理删除
     */
    @Log(title = "文件记录", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/delete")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "物理删除", notes = "传入ids")
    public R delete(@RequestParam String[] ids)
    {
        //todo 删除obs文件
        return R.status(reportFilesService.deleteItemsByIds(ids));
    }

    /**
     * 根据文件id查询文件信息
     */
    @GetMapping(value = "/getFileInfo")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询文件记录详情", notes = "传入id")
    public R<ReportFileLog> getFileInfo(@RequestParam("id") String id)
    {
        ReportFiles reportFile = reportFilesService.getInfo(id);
        ReportFileLog fileLog = new ReportFileLog();
        fileLog.setFileName(reportFile.getFileName());
        fileLog.setWellName(DataManageCache.getWellName(reportFile.getWellId()));
        fileLog.setOrganizationName(DataManageCache.getOrganizationName(reportFile.getOrganizationId()));
        fileLog.setFileVersion(reportFilesService.getVersionCode(reportFile.getScanBatch()));
        return R.ok(fileLog);
    }

    /**
     * 文件名解析
     */
    @PostMapping("/fileNameParse")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "文件名解析", notes = "传入fileNameList")
    public R fileNameParse(@RequestBody List<String> fileNameList)
    {
        return R.ok(reportFilesService.fileNameParse(fileNameList));
    }

    /**
     * 批量上传文件
     */
    @FileLog(fileOperType = FileOperType.UPLOAD)
    @PostMapping("/batchUploadFiles")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "批量上传文件", notes = "传入fileList")
    public R<List<UploadFileResult>> batchUploadFiles(@RequestPart("fileInfoList") List<ReportFiles> fileInfoList, @RequestPart("multipartFile") List<MultipartFile> multipartFiles
            , @RequestPart(value = "templateId", required = false) String templateId, @RequestPart(value = "rptType", required = false) String rptType) {
        return R.ok(reportFilesService.batchUploadFiles(fileInfoList, multipartFiles, templateId, rptType));
    }

    /**
     * 上传解析后的excel文件
     */
    @PostMapping("/uploadParseExcel")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "上传解析后的excel文件", notes = "传入files,templateId")
    public R<List<UploadFileResult>> uploadParseExcel(@RequestPart("multipartFile") List<MultipartFile> files, @RequestPart("templateId") String templateId) {
        return R.ok(reportFilesService.uploadParseExcel(files, templateId));
    }

    /**
     * 根据文件名获取文件
     */
    @PostMapping("/getFileObject")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "根据文件名获取文件", notes = "传入originalFileName,filePath,fileVersion")
    public R getFileObject(@RequestParam String originalFileName, @RequestParam(required = false) String filePath, @RequestParam(required = false) String fileVersion)
    {
        return R.ok(reportFilesService.getFileObject(originalFileName, filePath, fileVersion));
    }
}
