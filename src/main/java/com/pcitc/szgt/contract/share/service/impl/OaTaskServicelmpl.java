package com.pcitc.szgt.contract.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.szgt.contract.share.entity.SysOaTaskLog;
import com.pcitc.szgt.contract.share.mapper.OaTaskMapper;
import com.pcitc.szgt.contract.share.service.IOaTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OaTaskServicelmpl extends ServiceImpl<OaTaskMapper, SysOaTaskLog> implements IOaTaskService {
    @Autowired
    private OaTaskMapper oaTaskMapper;

    public Integer deleteOaTask(String taskId) {
        return oaTaskMapper.deleteOaTask(taskId);
    }
}
