package com.oo.file.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.StringUtils;
import com.oo.datamanagement.api.cache.DataManageCache;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.file.domain.ReportFiles;
import com.oo.file.mapper.ReportFilesMapper;
import com.oo.file.oss.OssTemplate;
import com.oo.file.oss.UploadFileResult;
import com.oo.file.service.IReportFilesService;
import com.oo.system.api.cache.SystemCache;
import com.oo.system.api.domain.DcMdOrganization;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.feign.RemoteMenuService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件记录Service业务层处理
 * 
 * @author oo
 * @date 2023-09-26
 */
@Service
@AllArgsConstructor
public class ReportFilesServiceImpl extends ServiceImpl<ReportFilesMapper, ReportFiles> implements IReportFilesService
{
    private final RemoteDataManageService remoteDataManageService;
    private final RemoteMenuService remoteMenuService;
    private final OssTemplate ossTemplate;

    /**
     * 查询文件记录列表
     * 
     * @param reportFiles 文件记录
     * @return 文件记录
     */
    @Override
    public List<ReportFiles> selectReportFilesList(ReportFiles reportFiles)
    {
        List<ReportFiles> list = baseMapper.selectReportFilesList(reportFiles);
        return listVO(list);
    }

    /**
     * 查询文件记录浏览列表（文件最新版本）
     *
     * @param reportFiles
     * @return
     */
    @Override
    public List<ReportFiles> selectReportFilesLatestList(ReportFiles reportFiles) {
        List<ReportFiles> list = baseMapper.selectReportFilesLatestList(reportFiles);
        return listVO(list);
    }

    /**
     * 查看详情
     *
     * @param id 文件id
     * @return
     */
    @Override
    public ReportFiles getInfo(String id) {
        ReportFiles reportFiles = getById(id);
        return entityVO(reportFiles);
    }

    public List<ReportFiles> listVO(List<ReportFiles> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public ReportFiles entityVO(ReportFiles item){
        item.setOrganizationName(DataManageCache.getOrganizationName(item.getOrganizationId())); //项目名称
        item.setOrganizationParentName(DataManageCache.getOrganizationName(item.getOrganizationParentId())); //国家名称
        item.setProjectName(DataManageCache.getProjectName(item.getProjectId())); //区块/井场名称
        item.setWellName(DataManageCache.getWellName(item.getWellId())); //井名称
        item.setWdpName(SystemCache.getWdpName(item.getWdpId() == null ? null : item.getWdpId().toString()));
        item.setBusinessName(SystemCache.getBusinessDocName(item.getBusinessId() == null ? null : item.getBusinessId().toString()));
        return item;
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String[] ids)
    {
        return baseMapper.deleteItemsByIds(ids);
    }

    /**
     * 文件名解析
     *
     * @param fileNameList 文件名列表
     * @return
     */
    @Override
    public List<ReportFiles> fileNameParse(List<String> fileNameList) {
        if (fileNameList.isEmpty()) {
            return null;
        }
        //查询井列表
        List<DcMdWell> wellList = remoteDataManageService.selectWellList(new MdQueryDTO()).getData();
        //查询业务域列表
        List<SysDocMenuBusiness> businessList = remoteMenuService.selectBusinessList().getData();
        List<ReportFiles> fileList = new ArrayList<>();
        for (String fileName : fileNameList) {
            ReportFiles reportFile = new ReportFiles();
            reportFile.setFileName(fileName);
            if (wellList != null && !wellList.isEmpty()) {
                wellList.stream().filter(a -> fileName.contains(a.getWellname()) || fileName.contains(a.getChinesewellname())).findFirst().ifPresent(e -> {
                    reportFile.setWellId(e.getId());
                    reportFile.setOrganizationId(e.getOrganizationId());
                    reportFile.setProjectId(e.getProjectId());
                });
                if (StringUtils.isNotEmpty(reportFile.getProjectId())) {
                    DcMdProject project = remoteDataManageService.getProject(reportFile.getProjectId()).getData();
                    reportFile.setProjectName(project.getName());
                }
                if (StringUtils.isNotEmpty(reportFile.getOrganizationId())) {
                    DcMdOrganization organization = remoteDataManageService.getOrganization(reportFile.getOrganizationId()).getData();
                    reportFile.setOrganizationName(organization.getName());
                    if (organization.getParentId() != null) {
                        DcMdOrganization parentOrg = remoteDataManageService.getOrganization(organization.getParentId().toString()).getData();
                        reportFile.setOrganizationParentId(organization.getParentId().toString());
                        reportFile.setOrganizationParentName(parentOrg.getName());
                    }
                }
            }
            if (businessList != null && !businessList.isEmpty()) {
                SysDocMenuBusiness menuBusiness = businessList.stream().filter(a -> fileName.contains(a.getBusinessName())).findFirst().orElse(null);
                if (menuBusiness != null) {
                    reportFile.setBusinessId(menuBusiness.getId());
                    reportFile.setBusinessName(menuBusiness.getBusinessName());
                    List<Long> wdpIdList = remoteMenuService.selectWdpIdsByBusinessId(menuBusiness.getId()).getData();
                    if (wdpIdList != null && !wdpIdList.isEmpty()) {
                        String wdpIds = wdpIdList.stream().map(Object::toString).collect(Collectors.joining(","));
                        reportFile.setWdpId(wdpIds);
                        String wdpNames = wdpIdList.stream().map(a -> SystemCache.getWdpName(a.toString())).collect(Collectors.joining(","));
                        reportFile.setWdpName(wdpNames);
                    }
                }
            }
            fileList.add(reportFile);
        }
        return fileList;
    }

    /**
     * 批量上传文件
     *
     * @param fileList 文件信息
     * @param multipartFiles 文件
     * @return
     * @throws Exception
     */
    @Override
    @SneakyThrows
    public List<UploadFileResult> batchUploadFiles(List<ReportFiles> fileList, List<MultipartFile> multipartFiles, String templateId, String rptType) {
        if (fileList.size() != multipartFiles.size()) {
            throw new ServiceException("文件信息与上传文件数量不符");
        }
        List<UploadFileResult> resultList = new ArrayList<>();
        int i = 0;
        for (MultipartFile multipartFile : multipartFiles) {
            ReportFiles fileInfo = fileList.get(i);
            String uploadFileName = multipartFile.getOriginalFilename();
            List<ReportFiles> reportFileList = list(Wrappers.<ReportFiles>lambdaQuery().eq(StringUtils.isNotEmpty(fileInfo.getWellId()), ReportFiles::getWellId, fileInfo.getWellId()).eq(fileInfo.getBusinessId() != null, ReportFiles::getBusinessId, fileInfo.getBusinessId())
                    .eq(ReportFiles::getFileName, multipartFile.getOriginalFilename()));
            // 判断文件版本号
            if (reportFileList != null && !reportFileList.isEmpty()) { //同一文件夹下有同名文件
                Double maxVersion = reportFileList.stream().map(ReportFiles::getScanBatch).filter(Objects::nonNull).max(Comparator.comparing(a -> a)).orElse((double) 0);
                fileInfo.setScanBatch(maxVersion + 1);
                uploadFileName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename()) + " " + getVersionCode(fileInfo.getScanBatch()) + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            }
            else { //没有同名文件
                fileInfo.setScanBatch(1.0);
            }
            InputStream inputStream = multipartFile.getInputStream();
            String path = "";
            if (StringUtils.isNotEmpty(rptType) && rptType.equals("1")) { //解析日报上传至单独文件夹 按照模板id分类文件夹
                path = "parseReport/pdf/" + (StringUtils.isNotEmpty(templateId) ? templateId : "") + "/";
            }
            UploadFileResult uploadFileResult = ossTemplate.putObject(ossTemplate.BUCKET_NAME, uploadFileName, path, inputStream);
            // 保存文件信息
            fileInfo.setUploadTime(new Date());
            fileInfo.setFileName(multipartFile.getOriginalFilename());
            fileInfo.setFilePath(path);
            fileInfo.setFileUnitSize(String.valueOf(multipartFile.getSize()));
            fileInfo.setFileFormat(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
            fileInfo.setRptMd5(uploadFileResult.getRptMd5());
            if (StringUtils.isEmpty(rptType)) {
                fileInfo.setRptType("2"); //普通日报
            }
            else {
                fileInfo.setRptType("1"); //解析日报
            }
            saveOrUpdate(fileInfo);
            uploadFileResult.setFileId(fileList.get(i).getId());
            uploadFileResult.setFileVersion(getVersionCode(fileInfo.getScanBatch()));
            resultList.add(uploadFileResult);
            i++;
        }
        return resultList;
    }

    /**
     * 上传解析后的excel文件
     *
     * @param files 文件
     * @param templateId 模板id
     * @return
     * @throws Exception
     */
    @Override
    @SneakyThrows
    public List<UploadFileResult> uploadParseExcel(List<MultipartFile> files, String templateId) {
        List<UploadFileResult> resultList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            InputStream inputStream = multipartFile.getInputStream();
            String path = "parseReport/excel/";
            if (StringUtils.isNotEmpty(templateId)) {
                path = path + templateId + "/";
            }
            UploadFileResult uploadFileResult = ossTemplate.putObject(ossTemplate.BUCKET_NAME, multipartFile.getOriginalFilename(), path, inputStream);
            resultList.add(uploadFileResult);
        }
        return resultList;
    }

    /**
     * 通过文件名获取对象
     *
     * @param originalFileName 文件原名
     * @param filePath 文件上传路径
     * @param fileVersion 文件版本号
     * @return
     */
    @Override
    public InputStream getFileObject(String originalFileName, String filePath, String fileVersion) {
        String extension = FilenameUtils.getExtension(originalFileName);
        String baseName = FilenameUtils.getBaseName(originalFileName);
        String objectName = (StringUtils.isEmpty(filePath) ? "" : filePath) + baseName + (StringUtils.isEmpty(fileVersion) ? "" : fileVersion) + extension;
        S3Object object = ossTemplate.getObject(ossTemplate.BUCKET_NAME, objectName);
        return object.getObjectContent();
    }

    /**
     * 文件版本号拼接
     *
     * @param fileVersion 版本号
     * @return
     */
    @Override
    public String getVersionCode(Double fileVersion) {
        return "v" + (fileVersion == null ? "1.0" : String.format("%.1f", fileVersion));
    }
}
