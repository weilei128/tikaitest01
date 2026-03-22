package com.oo.common.log.service;

import com.oo.system.api.domain.ReportFileLog;
import com.oo.system.api.feign.RemoteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.oo.common.core.constant.SecurityConstants;
import com.oo.system.api.feign.RemoteLogService;
import com.oo.system.api.domain.SysOperLog;

/**
 * 异步调用日志服务
 * 
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }

    /**
     * 保存文件日志记录
     */
    @Async
    public void saveFileLog(ReportFileLog reportFileLog)
    {
        remoteFileService.saveFileLog(reportFileLog);
    }
}
