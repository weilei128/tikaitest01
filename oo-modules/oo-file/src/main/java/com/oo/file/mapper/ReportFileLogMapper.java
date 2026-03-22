package com.oo.file.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.system.api.domain.ReportFileLog;

/**
 * 文件操作日志Mapper接口
 * 
 * @author oo
 * @date 2023-10-18
 */
public interface ReportFileLogMapper extends BaseMapper<ReportFileLog>
{

    /**
     * 查询文件操作日志列表
     * 
     * @param reportFileLog 文件操作日志
     * @return 文件操作日志集合
     */
    public List<ReportFileLog> selectReportFileLogList(ReportFileLog reportFileLog);

}
