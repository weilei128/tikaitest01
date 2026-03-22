package com.oo.file.oss;

import lombok.Data;

/**
 * 上传文件结果类
 */
@Data
public class UploadFileResult {

    /**
     * 文件链接
     */
    private String link;

    /**
     * 文件名（不带后缀）
     */
    private String name;

    /**
     * 文件原始名称（带后缀）
     */
    private String originalName;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * md5码
     */
    private String rptMd5;

    /**
     * 上传文件路径
     */
    private String filePath;

    /**
     * 版本号
     */
    private String fileVersion;

    /**
     * 状态码 200成功 500失败
     */
    private String statusCode;

    /**
     * 错误信息
     */
    private String errMsg;
}
