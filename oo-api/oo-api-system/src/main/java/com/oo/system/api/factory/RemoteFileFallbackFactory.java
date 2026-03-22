package com.oo.system.api.factory;

import com.oo.system.api.domain.ReportFileLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import com.oo.common.core.domain.R;
import com.oo.system.api.feign.RemoteFileService;
import com.oo.system.api.domain.SysFile;

/**
 * 文件服务降级处理
 * 
 * @author
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable)
    {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService()
        {
            @Override
            public R<SysFile> upload(MultipartFile file)
            {
                return R.fail("上传文件失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> saveFileLog(@RequestBody ReportFileLog reportFileLog)
            {
                return null;
            }
        };
    }
}
