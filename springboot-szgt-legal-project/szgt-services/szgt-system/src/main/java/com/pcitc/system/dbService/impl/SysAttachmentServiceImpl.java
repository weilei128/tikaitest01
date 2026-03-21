package com.pcitc.system.dbService.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.common.fastdfs.service.FastDFSService;
import com.pcitc.system.bo.SysAttachmentBo;
import com.pcitc.system.bo.SysAttachmentQueryBo;
import com.pcitc.system.dbService.SysAttachmentService;
import com.pcitc.system.mapper.SysAttachmentMapper;
import com.pcitc.system.po.SysAttachment;
import com.pcitc.system.vo.SysAttachmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/***
 * @description
 * @author leigang
 * @date 2019年10月22日 16:47:48
 *
 */
@Service
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentMapper, SysAttachment>
        implements SysAttachmentService {

    /**
     * FastDFS服务层
     */
    @Autowired
    private FastDFSService fastDFSService;
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    /**
     * 添加或修改附件
     *
     * @param sysAttachmentBo
     * @return
     */
    @Override
    public Result saveOrUpdate(SysAttachmentBo sysAttachmentBo) throws Exception {
        Result result = new Result();
        if (sysAttachmentBo == null) {
            throw new BaseException("所需参数为空", 500);
        }
        MultipartFile file = sysAttachmentBo.getFile();
        if (file.isEmpty()) {
            throw new BaseException("上传文件为空，请重新上传！", 500);
        }
        if (sysAttachmentBo.getfBusinessId() == null) {
            throw new BaseException("所上传附件没有绑定业务字典类型！", 500);
        }
        if (sysAttachmentBo.getFkDirectoryId() == null) {
            throw new BaseException("所上传附件没有绑定关联文件目录！", 500);
        }
        String fileName = file.getOriginalFilename();
        String author = "";
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setfBusinessId(sysAttachmentBo.getfBusinessId());
        sysAttachment.setfBusinessType(sysAttachmentBo.getfBusinessType());
        sysAttachment.setFkDirectoryId(sysAttachmentBo.getFkDirectoryId());
        sysAttachment.setFkDirectoryName(sysAttachmentBo.getFkDirectoryName());
        sysAttachment.setfFileName(fileName);
        sysAttachment.setfFileExt(ext);
        //根据附件全称、附件绑定的业务字典类型、附件绑定的关联文件目录查询附件信息
        QueryWrapper<SysAttachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_File_Name", fileName);
        queryWrapper.eq("f_Business_ID", sysAttachmentBo.getfBusinessId());
        queryWrapper.eq("fk_Directory_ID", sysAttachmentBo.getFkDirectoryId());
        List<SysAttachment> list = sysAttachmentMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            String fileId = fastDFSService.saveFile(file, author);
            sysAttachmentMapper.insert(sysAttachment);
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("添加附件信息成功！");
        }else {
            String deleteFileId = list.get(0).getfFileId();
            fastDFSService.deleteFile(deleteFileId);
            String updatefileId =fastDFSService.saveFile(file,author);
            sysAttachmentMapper.updateById(sysAttachment);
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("更新附件信息成功！");
        }
        return null;
    }

    /**
     * 根据主键删除附件信息
     *
     * @param fId
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(Integer fId) {
        if (fId == null) {
            throw new BaseException("所需参数为空", 500);
        }
        SysAttachment sysAttachment = this.getById(fId);
        if (sysAttachment == null) {
            throw new BaseException("所要删除的数据不存在！", 500);
        }
        String fileId = sysAttachment.getfFileId();
        try {
            fastDFSService.deleteFile(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysAttachmentMapper.deleteById(fId);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * 根据主键查询附件信息
     *
     * @param fId
     * @return
     */
    @Override
    public Result queryById(Integer fId) {
        Result result = new Result();
        if (fId == null) {
            throw new BaseException("所需参数为空", 500);
        }
        SysAttachment sysAttachment = sysAttachmentMapper.selectById(fId);
        SysAttachmentVo sysAttachmentVo = new SysAttachmentVo();
        if (sysAttachment != null) {
            BeanUtils.copyProperties(sysAttachment, sysAttachmentVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(sysAttachmentVo);
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
     * 根据条件查询附件信息列表
     *
     * @param sysAttachmentQueryBo
     * @return
     */
    @Override
    public Result queryForList(SysAttachmentQueryBo sysAttachmentQueryBo) {
        Result result = new Result();
        if (sysAttachmentQueryBo == null) {
            throw new BaseException("查询参数为空", 500);
        }
        QueryWrapper<SysAttachment> queryWrapper = new QueryWrapper<>();
        if (sysAttachmentQueryBo.getfBusinessId() != null) {
            queryWrapper.eq("f_Business_ID", sysAttachmentQueryBo.getfBusinessId());
        }
        if (!StringUtils.isEmpty(sysAttachmentQueryBo.getfBusinessType())) {
            queryWrapper.eq("f_Business_Type", sysAttachmentQueryBo.getfBusinessType());
        }
        if (sysAttachmentQueryBo.getFkDirectoryId() != null) {
            queryWrapper.eq("fk_Directory_ID", sysAttachmentQueryBo.getFkDirectoryId());
        }
        if (!StringUtils.isEmpty(sysAttachmentQueryBo.getFkDirectoryName())) {
            queryWrapper.eq("fk_Directory_Name", sysAttachmentQueryBo.getFkDirectoryName());
        }
        List<SysAttachment> list = sysAttachmentMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        } else {
            List<SysAttachmentVo> listVo = new ArrayList<>(list.size());
            list.forEach(sysAttachment -> {
                SysAttachmentVo sysAttachmentVo = new SysAttachmentVo();
                BeanUtils.copyProperties(sysAttachment, sysAttachmentVo);
                listVo.add(sysAttachmentVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(listVo);
            result.setMsg("查询数据成功！");
        }
        return result;
    }

    /**
     * 根据条件查询附件信息并进行分页处理
     *
     * @param sysAttachmentQueryBo
     * @return
     */
    @Override
    public Result queryForPage(SysAttachmentQueryBo sysAttachmentQueryBo) {
        IPage<SysAttachment> page = new Page<>(sysAttachmentQueryBo.getPageIndex(), sysAttachmentQueryBo.getPageSize());
        QueryWrapper<SysAttachment> queryWrapper = new QueryWrapper<>();
        if (sysAttachmentQueryBo.getfBusinessId() != null) {
            queryWrapper.eq("f_Business_ID", sysAttachmentQueryBo.getfBusinessId());
        }
        if (!StringUtils.isEmpty(sysAttachmentQueryBo.getfBusinessType())) {
            queryWrapper.eq("f_Business_Type", sysAttachmentQueryBo.getfBusinessType());
        }
        if (sysAttachmentQueryBo.getFkDirectoryId() != null) {
            queryWrapper.eq("fk_Directory_ID", sysAttachmentQueryBo.getFkDirectoryId());
        }
        if (!StringUtils.isEmpty(sysAttachmentQueryBo.getFkDirectoryName())) {
            queryWrapper.eq("fk_Directory_Name", sysAttachmentQueryBo.getFkDirectoryName());
        }
        IPage<SysAttachment> poPage = sysAttachmentMapper.selectPage(page, queryWrapper);
        return Result.data(poPage);
    }
}
