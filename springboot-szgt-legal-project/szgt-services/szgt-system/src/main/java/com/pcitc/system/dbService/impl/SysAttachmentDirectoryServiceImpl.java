package com.pcitc.system.dbService.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysAttachmentDirectoryBo;
import com.pcitc.system.bo.SysAttachmentDirectoryQueryBo;
import com.pcitc.system.dbService.SysAttachmentDirectoryService;
import com.pcitc.system.mapper.SysAttachmentDirectoryMapper;
import com.pcitc.system.po.SysAttachmentDirectory;
import com.pcitc.system.vo.SysAttachmentDirectoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SysAttachmentDirectoryServiceImpl extends ServiceImpl<SysAttachmentDirectoryMapper, SysAttachmentDirectory>
implements SysAttachmentDirectoryService {
    @Autowired
    private SysAttachmentDirectoryMapper sysAttachmentDirectoryMapper;
    /**
     * 根据主键查讯系统附件目录
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Long fId) {
        Result result = new Result();
        SysAttachmentDirectory sysAttachmentDirectory = sysAttachmentDirectoryMapper.selectById(fId);
        SysAttachmentDirectoryVo sysAttachmentDirectoryVo = new SysAttachmentDirectoryVo();
        if (sysAttachmentDirectory != null) {
            BeanUtils.copyProperties(sysAttachmentDirectory,sysAttachmentDirectoryVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(sysAttachmentDirectoryVo);
            result.setMsg("获取数据成功！");
        } else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    /**
     * 根据分类名称查讯系统附件目录并分页
     *
     * @param sysAttachmentDirectoryQueryBo
     * @return
     */
    @Override
    public Result queryPageByParentName(SysAttachmentDirectoryQueryBo sysAttachmentDirectoryQueryBo) {
        IPage<SysAttachmentDirectory> page = new Page<>(sysAttachmentDirectoryQueryBo.getPageIndex(),sysAttachmentDirectoryQueryBo.getPageSize());
        QueryWrapper<SysAttachmentDirectory> queryWrapper = new QueryWrapper<>();
        if (sysAttachmentDirectoryQueryBo.getFkParentId() != null) {
            queryWrapper.eq("fk_Parent_ID",sysAttachmentDirectoryQueryBo.getFkParentId());
        }
        if (sysAttachmentDirectoryQueryBo.getFkParentName() != null) {
            queryWrapper.eq("fk_Parent_Name",sysAttachmentDirectoryQueryBo.getFkParentName());
        }
        IPage<SysAttachmentDirectory> result = sysAttachmentDirectoryMapper.selectPage(page,queryWrapper);
        return Result.data(result);
    }

    /**
     * 添加系统附件目录信息
     *
     * @param sysAttachmentDirectoryBo
     * @return
     */
    @Override
    public Result save(SysAttachmentDirectoryBo sysAttachmentDirectoryBo) {
        if (sysAttachmentDirectoryBo == null) {
            throw new BaseException("所需参数为空",500);
        }
        SysAttachmentDirectory sysAttachmentDirectory = new SysAttachmentDirectory();
        BeanUtils.copyProperties(sysAttachmentDirectoryBo,sysAttachmentDirectory);
        sysAttachmentDirectory.setfCreateTime(new Date());
        sysAttachmentDirectoryMapper.insert(sysAttachmentDirectory);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键批量删除系统附件目录信息
     *
     * @param ids
     * @return
     */
    @Override
    public Result deleteBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            throw new BaseException("所需参数为空",500);
        }
        sysAttachmentDirectoryMapper.deleteBatchIds(ids);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键更新系统附件目录信息
     *
     * @param sysAttachmentDirectoryBo
     * @return
     */
    @Override
    public Result updateById(SysAttachmentDirectoryBo sysAttachmentDirectoryBo) {
        if (sysAttachmentDirectoryBo == null){
            throw new BaseException("传入参数为空", 500);
        }
        Long id = sysAttachmentDirectoryBo.getfId();
        if (this.getById(id) == null){
            throw new BaseException("所要更新的系统附件目录信息不存在",500);
        }
        SysAttachmentDirectory sysAttachmentDirectory = new SysAttachmentDirectory();
        BeanUtils.copyProperties(sysAttachmentDirectoryBo,sysAttachmentDirectory);
        sysAttachmentDirectory.setfUpdateTime(new Date());
        sysAttachmentDirectoryMapper.updateById(sysAttachmentDirectory);
        return Result.success(ResultCode.SUCCESS);
    }
}
