package com.pcitc.legalAffairs.service.file;

import com.pcitc.common.fastdfs.service.FastDFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @description 文件操作服务类
 * @author leigang
 * @date 2019年10月25日 15:33:44
 *
 */
@Service
public class FileService {


    private FastDFSService fastDFSService;

    @Autowired
    public void setFastDFSService(FastDFSService fastDFSService) {
        this.fastDFSService = fastDFSService;
    }


    /**
     * 根据图片的相对路径删除图片
     *
     * @param imageRelativePath
     * @return 1 删除成功 0 图片已经删除 2图片删除异常
     * group1/M00/00/00/Cu4_Gl2vzlqAMeW6AABYcr4exVM996.jpg
     */
    public Integer deleteImage(String imageRelativePath) {
        try {
            boolean deleteFile = fastDFSService.deleteFile(imageRelativePath);
            if (deleteFile) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

}
