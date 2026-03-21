package com.pcitc.szgt.contract.attachment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.attachment.mapper.SysAttachmentinfoMapper;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentQueryVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentUploadVo;
import com.pcitc.szgt.contract.attachment.service.AttachmentService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.attachment.AttachmentConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    SysAttachmentinfoMapper attachmentinfoMapper;

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private AttachmentConfig attachmentConfig;

    @Transactional
    @Override
    public DataResult uploadFile(AttachmentUploadVo attachmentUploadVo) {

        if (attachmentUploadVo == null){
            throw new BaseException("附件信息不完整", 500);
        }

        MultipartFile multiFile = attachmentUploadVo.getFile();

        if (multiFile.isEmpty()) {
            throw new BaseException("附件信息不完整", 500);
        }

        String originName = multiFile.getOriginalFilename();
        String suffixName = "";
        String fileName = originName;
        if(originName.lastIndexOf(".") != -1){
            suffixName = originName.substring(originName.lastIndexOf(".") + 1);
            fileName = originName.substring(0, originName.lastIndexOf("."));
        }

        long size = multiFile.getSize();

        StorePath storePath = null;
        try {
            storePath = this.storageClient.uploadFile(
                    multiFile.getInputStream(), size, suffixName, null);
        } catch (IOException e) {
            throw new BaseException("文件上传失败", 500);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();
        Integer oulabel = userInfo.getUnitId();

        //查出当前排序最大
        if(attachmentUploadVo.getOrderNumber() == null){
            int order = 0;
            QueryWrapper<SysAttachmentinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().
                    eq(SysAttachmentinfo::getPropertyID, attachmentUploadVo.getPropertyID()).
                    eq(SysAttachmentinfo::getPropertyModel, attachmentUploadVo.getPropertyModel()).
                    eq(SysAttachmentinfo::getSection, attachmentUploadVo.getSection()).
                    eq(SysAttachmentinfo::getAttachmentType, attachmentUploadVo.getAttachmentType()).
                    orderByDesc(SysAttachmentinfo::getOrderNumber);
            List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(queryWrapper);
            if(!CollectionUtils.isEmpty(sysAttachmentinfos)){
                SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfos.get(0);
                if(sysAttachmentinfo.getOrderNumber() != null){
                    order = sysAttachmentinfo.getOrderNumber() + 1;
                }
            }

            attachmentUploadVo.setOrderNumber(order);
        }

        SysAttachmentinfo attachmentinfo = new SysAttachmentinfo();
        BeanUtils.copyProperties(attachmentUploadVo, attachmentinfo);
        attachmentinfo.setAttachmentID(UUIDUtils.getUUID());
        attachmentinfo.setAttachmentName(fileName);
        attachmentinfo.setAttachmentPath("/" + storePath.getFullPath());
        attachmentinfo.setDocUrl("http://" + attachmentConfig);
        attachmentinfo.setFileSize(new BigDecimal(size));
        attachmentinfo.setExtension(suffixName);
        attachmentinfo.setCreatedBy(userInfo.getSysUser().getfId().toString());
        attachmentinfo.setCreatedDate(LocalDateTime.now());
        attachmentinfo.setOulabel(oulabel);
        attachmentinfo.setOrgID(oulabel);
        int insertCount = attachmentinfoMapper.insert(attachmentinfo);

        if(insertCount == 0){
            throw new BaseException("附件上传失败", 500);
        }

        AttachmentResultVo attachmentResultVo = new AttachmentResultVo();
        BeanUtils.copyProperties(attachmentinfo, attachmentResultVo);

        return DataResult.success(attachmentinfo.getAttachmentID());

    }

    @Transactional
    @Override
    public DataResult uploadFileReturnDetail(AttachmentUploadVo attachmentUploadVo) {

        if (attachmentUploadVo == null){
            throw new BaseException("附件信息不完整", 500);
        }

        MultipartFile multiFile = attachmentUploadVo.getFile();

        if (multiFile.isEmpty()) {
            throw new BaseException("附件信息不完整", 500);
        }

        String originName = multiFile.getOriginalFilename();
        String suffixName = "";
        String fileName = originName;
        if(originName.lastIndexOf(".") != -1){
            suffixName = originName.substring(originName.lastIndexOf(".") + 1);
            fileName = originName.substring(0, originName.lastIndexOf("."));
        }

        long size = multiFile.getSize();

        StorePath storePath = null;
        try {
            storePath = this.storageClient.uploadFile(
                    multiFile.getInputStream(), size, suffixName, null);
        } catch (IOException e) {
            throw new BaseException("文件上传失败", 500);
        }

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();
        Integer oulabel = userInfo.getUnitId();

        //查出当前排序最大
        if(attachmentUploadVo.getOrderNumber() == null){
            int order = 0;
            QueryWrapper<SysAttachmentinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().
                    eq(SysAttachmentinfo::getPropertyID, attachmentUploadVo.getPropertyID()).
                    eq(SysAttachmentinfo::getPropertyModel, attachmentUploadVo.getPropertyModel()).
                    eq(SysAttachmentinfo::getSection, attachmentUploadVo.getSection()).
                    eq(SysAttachmentinfo::getAttachmentType, attachmentUploadVo.getAttachmentType()).
                    orderByDesc(SysAttachmentinfo::getOrderNumber);
            List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(queryWrapper);
            if(!CollectionUtils.isEmpty(sysAttachmentinfos)){
                SysAttachmentinfo sysAttachmentinfo = sysAttachmentinfos.get(0);
                if(sysAttachmentinfo.getOrderNumber() != null){
                    order = sysAttachmentinfo.getOrderNumber() + 1;
                }
            }

            attachmentUploadVo.setOrderNumber(order);
        }

        SysAttachmentinfo attachmentinfo = new SysAttachmentinfo();
        BeanUtils.copyProperties(attachmentUploadVo, attachmentinfo);
        attachmentinfo.setAttachmentID(UUIDUtils.getUUID());
        attachmentinfo.setAttachmentName(fileName);
        attachmentinfo.setAttachmentPath("/" + storePath.getFullPath());
        attachmentinfo.setDocUrl("http://" + attachmentConfig);
        attachmentinfo.setFileSize(new BigDecimal(size));
        attachmentinfo.setExtension(suffixName);
        attachmentinfo.setCreatedBy(userInfo.getSysUser().getfId().toString());
        attachmentinfo.setCreatedDate(LocalDateTime.now());
        attachmentinfo.setOulabel(oulabel);
        attachmentinfo.setOrgID(oulabel);
        int insertCount = attachmentinfoMapper.insert(attachmentinfo);

        if(insertCount == 0){
            throw new BaseException("附件上传失败", 500);
        }

        AttachmentResultVo attachmentResultVo = new AttachmentResultVo();
        BeanUtils.copyProperties(attachmentinfo, attachmentResultVo);

        return DataResult.success(attachmentResultVo);

    }

    @Override
    public DataResult<List<AttachmentResultVo>> queryMultiAttachment(AttachmentQueryVo queryVo) {
        QueryWrapper<SysAttachmentinfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVo.getPropertyID())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyID, queryVo.getPropertyID());
        }
        if(!StringUtils.isEmpty(queryVo.getPropertyModel())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyModel, queryVo.getPropertyModel());
        }
        if(!StringUtils.isEmpty(queryVo.getSection())){
            wrapper.lambda().eq(SysAttachmentinfo::getSection, queryVo.getSection());
        }
        if(!StringUtils.isEmpty(queryVo.getAttachmentType())){
            wrapper.lambda().eq(SysAttachmentinfo::getAttachmentType, queryVo.getAttachmentType());
        }
        if(!StringUtils.isEmpty(queryVo.getTypeCode())){
            wrapper.lambda().eq(SysAttachmentinfo::getTypeCode, queryVo.getTypeCode());
        }

        wrapper.lambda().eq(SysAttachmentinfo::getLogicDel, 0);
        wrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);

        List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(wrapper);
        List<AttachmentResultVo> resultVoList = new ArrayList<>();
        for(SysAttachmentinfo attachmentinfo : sysAttachmentinfos){
            AttachmentResultVo resultVo = new AttachmentResultVo();
            BeanUtils.copyProperties(attachmentinfo, resultVo);
            resultVoList.add(resultVo);
        }

        return DataResult.success(resultVoList);
    }

    @Override
    public DataResult<List<AttachmentResultVo>> queryAllAttachment(AttachmentQueryVo queryVo) {

        QueryWrapper<SysAttachmentinfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVo.getPropertyID())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyID, queryVo.getPropertyID());
        }
        if(!StringUtils.isEmpty(queryVo.getPropertyModel())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyModel, queryVo.getPropertyModel());
        }
        if(!StringUtils.isEmpty(queryVo.getSection())){
            wrapper.lambda().eq(SysAttachmentinfo::getSection, queryVo.getSection());
        }
        if(!StringUtils.isEmpty(queryVo.getAttachmentType())){
            wrapper.lambda().eq(SysAttachmentinfo::getAttachmentType, queryVo.getAttachmentType());
        }
        if(!StringUtils.isEmpty(queryVo.getTypeCode())){
            wrapper.lambda().eq(SysAttachmentinfo::getTypeCode, queryVo.getTypeCode());
        }

        wrapper.lambda().eq(SysAttachmentinfo::getLogicDel, 0);
        wrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);

        List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(wrapper);
        List<AttachmentResultVo> resultVoList = new ArrayList<>();
        for(SysAttachmentinfo attachmentinfo : sysAttachmentinfos){
            AttachmentResultVo resultVo = new AttachmentResultVo();
            BeanUtils.copyProperties(attachmentinfo, resultVo);
            resultVoList.add(resultVo);
        }

        return DataResult.success(resultVoList);
    }

    @Override
    public DataResult<List<AttachmentResultVo>> queryOneAttachment(AttachmentQueryVo queryVo) {
        QueryWrapper<SysAttachmentinfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVo.getPropertyID())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyID, queryVo.getPropertyID());
        }
        if(!StringUtils.isEmpty(queryVo.getPropertyModel())){
            wrapper.lambda().eq(SysAttachmentinfo::getPropertyModel, queryVo.getPropertyModel());
        }
        if(!StringUtils.isEmpty(queryVo.getSection())){
            wrapper.lambda().eq(SysAttachmentinfo::getSection, queryVo.getSection());
        }
        if(!StringUtils.isEmpty(queryVo.getAttachmentType())){
            wrapper.lambda().eq(SysAttachmentinfo::getAttachmentType, queryVo.getAttachmentType());
        }
        if(!StringUtils.isEmpty(queryVo.getTypeCode())){
            wrapper.lambda().eq(SysAttachmentinfo::getTypeCode, queryVo.getTypeCode());
        }

        wrapper.lambda().eq(SysAttachmentinfo::getLogicDel, 0);
        wrapper.lambda().orderByDesc(SysAttachmentinfo::getCreatedDate);

        List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(wrapper);
        List<AttachmentResultVo> resultVoList = new ArrayList<>();
        if(sysAttachmentinfos.size() > 0){
            AttachmentResultVo resultVo = new AttachmentResultVo();
            BeanUtils.copyProperties(sysAttachmentinfos.get(0), resultVo);
            resultVoList.add(resultVo);

        }

        return DataResult.success(resultVoList);
    }

    @Override
    public AttachmentResultVo selectById(String attachmentId) {
        SysAttachmentinfo sysAttachmentinfo = attachmentinfoMapper.selectById(attachmentId);
        AttachmentResultVo resultVo = new AttachmentResultVo();
        BeanUtils.copyProperties(sysAttachmentinfo, resultVo);
        return resultVo;
    }


    @Override
    public DataResult deleteAttachment(String attachmentId) {
        System.out.println("attachmentId:" + attachmentId);

        SysAttachmentinfo attachmentinfo = new SysAttachmentinfo();
        attachmentinfo.setAttachmentID(attachmentId);
        attachmentinfo.setLogicDel(1);

        attachmentinfoMapper.updateById(attachmentinfo);
//        SysAttachmentinfo attachmentinfo = attachmentinfoMapper.selectById(attachmentId);
//        if(attachmentinfo==null){
//            throw new BaseException("附件不存在", 500);
//        }
//
//        int deleteCount = attachmentinfoMapper.deleteById(attachmentId);
//        if(deleteCount!=1){
//            throw new BaseException("删除附件失败", 500);
//        }
//
//        storageClient.deleteFile(attachmentinfo.getAttachmentPath());

        return DataResult.success(null);
    }

    @Override
    @Transactional
    public DataResult<?> orderAttachment(List<String> attIds) {
        if(CollectionUtils.isEmpty(attIds) || attIds.size() < 2){
            return DataResult.success(null);
        }

        QueryWrapper<SysAttachmentinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SysAttachmentinfo::getAttachmentID, attIds);

        List<SysAttachmentinfo> sysAttachmentinfos = attachmentinfoMapper.selectList(queryWrapper);
        if(sysAttachmentinfos.size() != attIds.size()){
            throw new BaseException("操作失败", 500);
        }

        int count = 0;
        for (String attId:attIds){
            SysAttachmentinfo sysAttachmentinfo = new SysAttachmentinfo();
            sysAttachmentinfo.setAttachmentID(attId);
            sysAttachmentinfo.setOrderNumber(count);

            int i = attachmentinfoMapper.updateById(sysAttachmentinfo);
            if(i != 1){
                throw new BaseException("操作失败", 500);
            }

            count++;
        }

        return DataResult.success(null);
    }

}
