package com.oo.file.oss;

import com.amazonaws.services.s3.model.*;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * oss操作方法
 */
public interface OssTemplate {

    String BUCKET_NAME = "gjgs";

    /**
     * 创建bucket
     * @param bucketName bucket名称
     */
    void createBucket(String bucketName);

    /**
     * 获取全部bucket
     * <p>
     *
     * API Documentation</a>
     */
    List<Bucket> getAllBuckets();

    /**
     * @param bucketName bucket名称
     * @see <a href= Documentation</a>
     */
    void removeBucket(String bucketName);

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @param contextType 文件类型
     * @throws Exception
     */
    PutObjectResult putObject(String bucketName, String objectName, InputStream stream, String contextType) throws Exception;

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @throws Exception
     */
    PutObjectResult putObject(String bucketName, String objectName, InputStream stream) throws Exception;

    /**
     * 上传文件（自定义返回）
     *
     * @param bucketName 桶名
     * @param originalFileName 原始文件名
     * @param filePath 文件上传路径
     * @param stream 文件流
     * @return 自定义文件结果类
     */
    UploadFileResult putObject(String bucketName, String originalFileName, String filePath, InputStream stream);

    /**
     * 获取文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流 API Documentation</a>
     */
    S3Object getObject(String bucketName, String objectName);

    /**
     * 获取临时访问对象的url
     * @param bucketName
     * @param objectName
     * @param expires
     * @return
     */
    String getObjectURL(String bucketName, String objectName, Integer expires);

    /**
     * 通过bucketName和objectName删除对象
     * @param bucketName
     * @param objectName
     * @throws Exception
     */
    void removeObject(String bucketName, String objectName) throws Exception;

//    /**
//     * @throws Exception
//     */
//    @Override
//    default void afterPropertiesSet() throws Exception {
//    }

    /**
     * 根据文件前置查询文件
     *
     * @param bucketName bucket名称
     * @param prefix 前缀
     * @param recursive 是否递归查询
     * @return S3ObjectSummary 列表
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListObjects">AWS
     * API Documentation</a>
     */
    List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive);

    /**
     * 获取上传后文件url
     *
     * @param fileName 文件名
     * @return
     */
    String fileLink(String fileName);

    /**
     * 获取上传后文件url
     *
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return
     */
    String fileLink(String bucketName, String fileName);

    /**
     * 通过bucketName、key、过期时间生成文件访问链接（时效性）
     *
     * @param bucketName bucket名称
     * @param key 文件名
     * @param expiration 过期时间
     * @return 文件访问链接
     */
    String getSignObjectUrl(String bucketName, String key, Date expiration);

    /**
     * 设置对象 ACL (设置预定义策略)
     *
     * @param bucketName
     * @param key
     * @param acl
     */
    void setObjectAcl(String bucketName, String key, CannedAccessControlList acl);
}
