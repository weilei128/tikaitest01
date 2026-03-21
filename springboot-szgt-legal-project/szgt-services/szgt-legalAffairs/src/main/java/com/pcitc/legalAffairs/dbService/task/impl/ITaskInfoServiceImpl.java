package com.pcitc.legalAffairs.dbService.task.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.legalAffairs.dbService.task.ITaskInfoService;
import com.pcitc.legalAffairs.mapper.task.TaskInfoMapper;
import com.pcitc.legalAffairs.po.task.TaskInfo;

@Service
public class ITaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements ITaskInfoService {

}
