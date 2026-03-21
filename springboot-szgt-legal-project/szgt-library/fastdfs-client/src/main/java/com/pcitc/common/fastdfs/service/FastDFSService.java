package com.pcitc.common.fastdfs.service;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FastDFSService {
    private static Logger logger = LoggerFactory.getLogger(FastDFSService.class);
    private TrackerClient trackerClient;
    private TrackerServer trackerServer;

    public FastDFSService(Properties properties) throws IOException, MyException {
        ClientGlobal.initByProperties(properties);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        //给dfs发送一个消息,否则在启动的时候，连接超时的时候没有收到任何消息,服务会被踢掉 2019年6月28日 15:30:33
        ProtoCommon.activeTest(trackerServer.getSocket());
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        String result = storageClient1.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }


    /**
     * 删除文件
     *
     * @param fileId
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String fileId) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        int i = storageClient1.delete_file1(fileId);
        return (i == 0);
    }


    /**
     * 根据文件id下载文件 -- 不支持断点续传
     *
     * @param fileId 文件id
     * @return
     * @throws Exception
     */
    public byte[] downloadFile(String fileId) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        return storageClient1.download_file1(fileId);
    }

    public byte[] downloadFile(String fileId, long file_offset, long download_bytes) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        return storageClient1.download_file1(fileId, file_offset, download_bytes);
    }

    public String saveFile(MultipartFile multipartFile,String author) throws IOException {
        String result = null;
        String fileName = multipartFile.getOriginalFilename();
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", author);
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        try {
            result = uploadFile(file_buff, ext, meta_list);
        } catch (Exception e) {

            logger.error("upload file Exception!", e);
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("upload file failed,please upload again!");
        }
        return result;
    }
}
