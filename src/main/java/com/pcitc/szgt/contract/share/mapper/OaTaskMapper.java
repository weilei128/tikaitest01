package com.pcitc.szgt.contract.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pcitc.szgt.contract.share.entity.SysOaTaskLog;
import org.apache.ibatis.annotations.Delete;

public interface OaTaskMapper extends BaseMapper<SysOaTaskLog> {
    @Delete("delete from szgt_system_systemdb.sys_oa_task_log where f_task_id = #{taskId}")
    Integer deleteOaTask(String taskId);
}
