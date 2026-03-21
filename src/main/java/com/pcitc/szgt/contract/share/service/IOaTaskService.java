package com.pcitc.szgt.contract.share.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.szgt.contract.share.entity.SysOaTaskLog;

public interface IOaTaskService extends IService<SysOaTaskLog> {
    Integer deleteOaTask(String taskId);
}
