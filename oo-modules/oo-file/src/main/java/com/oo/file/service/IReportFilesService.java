package com.oo.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.file.domain.ReportFiles;
import com.oo.file.oss.UploadFileResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件记录Service接口
 * 
 * @author oo
 * @date 2023-09-26
 */
public interface IReportFilesService extends IService<ReportFiles>
{

    /**
     * 查询文件记录列表
     * 
     * @param reportFiles 文件记录
     * @return 文件记录集合
     */
    public List<ReportFiles> selectReportFilesList(ReportFiles reportFiles);

    /**
     * 查询文件记录浏览列表（文件最新版本）
     *
     * @param reportFiles
     * @return
     */
    List<ReportFiles> selectReportFilesLatestList(ReportFiles reportFiles);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    ReportFiles getInfo(String id);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);

    /**
     * 文件名解析
     *
     * @param fileNameList 文件名列表
     * @return
     */
    List<ReportFiles> fileNameParse(List<String> fileNameList);

    /**
     * 批量上传文件
     *
     * @param fileList
     * @return
     */
    List<UploadFileResult> batchUploadFiles(List<ReportFiles> fileList, List<MultipartFile> multipartFiles, String templateId, String rptType);

    /**
     * 上传解析后的excel文件
     *
     * @param files 文件
     * @param templateId 模板id
     * @return
     */
    List<UploadFileResult> uploadParseExcel(List<MultipartFile> files, String templateId);

    /**
     * 通过文件名获取对象
     *
     * @param originalFileName 文件原名
     * @param filePath 文件上传路径
     * @param fileVersion 文件版本号
     * @return
     */
    InputStream getFileObject(String originalFileName, String filePath, String fileVersion);

    /**
     * 文件版本号拼接
     *
     * @param fileVersion 版本号
     * @return
     */
    String getVersionCode(Double fileVersion);
}
