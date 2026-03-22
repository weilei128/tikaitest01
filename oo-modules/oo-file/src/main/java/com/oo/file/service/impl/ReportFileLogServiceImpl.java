package com.oo.file.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.file.mapper.ReportFileLogMapper;
import com.oo.file.service.IReportFileLogService;
import org.springframework.stereotype.Service;
import com.oo.system.api.domain.ReportFileLog;

/**
 * 文件操作日志Service业务层处理
 * 
 * @author oo
 * @date 2023-10-18
 */
@Service
public class ReportFileLogServiceImpl extends ServiceImpl<ReportFileLogMapper, ReportFileLog> implements IReportFileLogService
{

    /**
     * 查询文件操作日志列表
     * 
     * @param reportFileLog 文件操作日志
     * @return 文件操作日志
     */
    @Override
    public List<ReportFileLog> selectReportFileLogList(ReportFileLog reportFileLog)
    {
        List<ReportFileLog> list = baseMapper.selectReportFileLogList(reportFileLog);
        return listVO(list);
    }

    public List<ReportFileLog> listVO(List<ReportFileLog> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public ReportFileLog entityVO(ReportFileLog item){
        if(StringUtils.isNotEmpty(item.getContent())) {
            item.setContent(DictUtils.getDictLabel("file_log_content", item.getContent()));
        }
        if(StringUtils.isNotEmpty(item.getOperType())) {
            item.setOperType(DictUtils.getDictLabel("file_oper_type", item.getOperType()));
        }
        if(StringUtils.isNotEmpty(item.getStatus())) {
            item.setStatus(DictUtils.getDictLabel("file_oper_status", item.getStatus()));
        }
        return item;
    }
}
