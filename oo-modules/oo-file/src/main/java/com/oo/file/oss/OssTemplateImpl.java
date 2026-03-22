package com.oo.file.oss;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.oo.common.core.utils.file.FileUtils;
import com.oo.file.config.OssProperties;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * oss操作方法实现类
 */
@RequiredArgsConstructor //lomnok的注解，替代@Autowired。
public class OssTemplateImpl implements OssTemplate {

    private final OssProperties ossProperties;

    private final AmazonS3 amazonS3;

    /**
     * 创建Bucket
     * AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html
     *
     * @param bucketName bucket名称
     */
    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket((bucketName));
        }
    }

    /**
     * 获取所有的buckets
     * AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListBuckets.html
     *
     * @return
     */
    @Override
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return amazonS3.listBuckets();
    }

    /**
     * 通过Bucket名称删除Bucket
     * AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteBucket.html
     *
     * @param bucketName
     */
    @Override
    @SneakyThrows
    public void removeBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }

    /**
     * 上传对象
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param contextType 文件类型
     *                    AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObject.html
     */
    @Override
    @SneakyThrows
    public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, String contextType) {
        return putObject(bucketName, objectName, stream, stream.available(), contextType);
    }

    /**
     * 上传对象
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     *                   AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObject.html
     */
    @Override
    @SneakyThrows
    public PutObjectResult putObject(String bucketName, String objectName, InputStream stream) {
        return putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件（自定义返回）
     *
     * @param bucketName 桶名
     * @param originalFileName 原始文件名
     * @param filePath 文件上传路径
     * @param stream 文件流
     * @return 自定义文件结果类
     */
    @Override
    public UploadFileResult putObject(String bucketName, String originalFileName, String filePath, InputStream stream) {
        UploadFileResult uploadFileResult = new UploadFileResult();
        try {
            String objectName = filePath + originalFileName;
            PutObjectResult putResult = putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
            uploadFileResult.setStatusCode("200");
            uploadFileResult.setOriginalName(originalFileName);
            uploadFileResult.setName(FilenameUtils.getBaseName(originalFileName));
            uploadFileResult.setRptMd5(putResult.getETag());
            uploadFileResult.setFilePath(filePath);
            setObjectAcl(bucketName, objectName, CannedAccessControlList.PublicRead);
            uploadFileResult.setLink(fileLink(bucketName, objectName));
            //                uploadFileResult.setLink(ossTemplate.getObjectURL(bucketName, multipartFile.getOriginalFilename(), 7));
        }
        catch (Exception e) {
            uploadFileResult.setStatusCode("500");
            uploadFileResult.setErrMsg(e.getMessage());
        }
        return uploadFileResult;
    }

    /**
     * 通过bucketName和objectName获取对象
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObject.html
     */
    @Override
    @SneakyThrows
    public S3Object getObject(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName);
    }

    /**
     * 获取临时访问对象的url
     *
     * @param bucketName
     * @param objectName
     * @param expires
     * @return AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_GeneratePresignedUrl.html
     */
    @Override
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
        return url.toString();
    }

    /**
     * 通过bucketName和objectName删除对象
     *
     * @param bucketName
     * @param objectName AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteObject.html
     */
    @Override
    @SneakyThrows
    public void removeObject(String bucketName, String objectName) {
        amazonS3.deleteObject(bucketName, objectName);
    }

    /**
     * 根据bucketName和prefix获取对象集合
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归查询
     * @return AmazonS3：https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListObjects.html
     */
    @Override
    @SneakyThrows
    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
        return objectListing.getObjectSummaries();
    }


    /**
     * 上传文件
     *
     * @param bucketName
     * @param objectName
     * @param stream
     * @param size
     * @param contextType
     * @return
     */
    @SneakyThrows
    private PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size,
                                      String contextType) {

        byte[] bytes = IOUtils.toByteArray(stream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 上传
        return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);

    }

    /**
     * 获取文件信息
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS
     * API Documentation</a>
     */
    public S3Object getObjectInfo(String bucketName, String objectName) throws Exception {
        @Cleanup
        S3Object object = amazonS3.getObject(bucketName, objectName);
        return object;
    }

    public String getOssHost(String bucketName) {
        String prefix = this.ossProperties.getEndpoint().contains("https://") ? "https://" : "http://";
        return prefix + bucketName + "." + this.ossProperties.getEndpoint().replaceFirst(prefix, "");
    }

    public String getOssHost() {
        return this.getOssHost(this.ossProperties.getBucketName());
    }

    @SneakyThrows
    public String fileLink(String fileName) {
        return this.getOssHost().concat("/").concat(FileUtils.percentEncode(fileName));
    }

    @SneakyThrows
    public String fileLink(String bucketName, String fileName) {
        return this.getOssHost(bucketName).concat("/").concat(FileUtils.percentEncode(fileName));
    }

    /**
     * 通过bucketName、key、过期时间生成文件访问链接（时效性）
     *
     * @param bucketName bucket名称
     * @param key 文件名
     * @param expiration 过期时间
     * @return 文件访问链接
     */
    @Override
    public String getSignObjectUrl(String bucketName, String key, Date expiration) {
        return amazonS3.generatePresignedUrl(bucketName, key, expiration).toString();
    }

    /**
     * 设置对象 ACL
     *
     * @param bucketName
     * @param key
     * @param acl
     */
    @Override
    public void setObjectAcl(String bucketName, String key, CannedAccessControlList acl) {
        amazonS3.setObjectAcl(bucketName, key, acl);
    }


}