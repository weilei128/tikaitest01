package com.oo.file.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.system.api.domain.ReportFileLog;

/**
 * 文件操作日志Service接口
 * 
 * @author oo
 * @date 2023-10-18
 */
public interface IReportFileLogService extends IService<ReportFileLog>
{

    /**
     * 查询文件操作日志列表
     * 
     * @param reportFileLog 文件操作日志
     * @return 文件操作日志集合
     */
    public List<ReportFileLog> selectReportFileLogList(ReportFileLog reportFileLog);

}
