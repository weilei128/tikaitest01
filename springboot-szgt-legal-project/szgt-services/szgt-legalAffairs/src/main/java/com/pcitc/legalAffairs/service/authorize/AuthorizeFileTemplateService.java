package com.pcitc.legalAffairs.service.authorize;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeFileTempQueryBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeFileTemplateBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeFileTemplateService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeFileTemplate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 授权文件模板Service
 * @author meihongli
 * 
 */
@Service
public class AuthorizeFileTemplateService {

    @Autowired
    private IAuthorizeFileTemplateService ifileTemplateService;

    public void save(AuthorizeFileTemplateBo bo) {
        FwAuthorizeFileTemplate entity = boToEntity(bo);
        entity.setfPublishDate(new Date());
        ifileTemplateService.save(entity);
    }

    public void update(AuthorizeFileTemplateBo bo) {
        FwAuthorizeFileTemplate entity = boToEntity(bo);
        ifileTemplateService.updateById(entity);
    }

    public void delete(Long id) {
        ifileTemplateService.removeById(id);
    }

    public void delete(List<Long> id) {
        ifileTemplateService.removeByIds(id);
    }

    /**
     * 启用
     * @param id
     */
    public void enable(Long id) {
        ifileTemplateService.lambdaUpdate()
            .eq(FwAuthorizeFileTemplate::getfId, id)
            .set(FwAuthorizeFileTemplate::getfIsAvailable, 1)
            .update();
    }

    /**
     * 禁用
     * @param id
     */
    public void disable(Long id) {
        ifileTemplateService.lambdaUpdate()
            .eq(FwAuthorizeFileTemplate::getfId, id)
            .set(FwAuthorizeFileTemplate::getfIsAvailable, 0)
            .update();
    }

    /**
     * 启用
     * @param id
     */
    public void enable(List<Long> id) {
        ifileTemplateService.lambdaUpdate()
            .in(FwAuthorizeFileTemplate::getfId, id)
            .set(FwAuthorizeFileTemplate::getfIsAvailable, 1)
            .update();
    }

    /**
     * 禁用
     * @param id
     */
    public void disable(List<Long> id) {
        ifileTemplateService.lambdaUpdate()
            .in(FwAuthorizeFileTemplate::getfId, id)
            .set(FwAuthorizeFileTemplate::getfIsAvailable, 0)
            .update();
    }

    public AuthorizeFileTemplateBo getById(String id) {
        FwAuthorizeFileTemplate entity = ifileTemplateService.getById(id);
        return entityToBo(entity);
    }

    public IPage<AuthorizeFileTemplateBo> page(AuthorizeFileTempQueryBo bo) {
        IPage<FwAuthorizeFileTemplate> query = new Page<>(bo.getCurrent(), bo.getSize());
        IPage<FwAuthorizeFileTemplate> page = ifileTemplateService.lambdaQuery()
            .like(bo.getName() != null, FwAuthorizeFileTemplate::getfName, bo.getName())
            .eq(bo.getTemplateType() != null, FwAuthorizeFileTemplate::getfTemplateType, bo.getTemplateType())
            .eq(bo.getAuthorizeProperty() != null, FwAuthorizeFileTemplate::getfAuthorizeProperty, bo.getAuthorizeProperty())
            .eq(bo.getAuthorizeType() != null, FwAuthorizeFileTemplate::getfAuthorizeType, bo.getAuthorizeType())
            .eq(bo.getIsAvaliable() != null, FwAuthorizeFileTemplate::getfIsAvailable, bo.getIsAvaliable())
            .gt(bo.getPublishDateBegin() != null, FwAuthorizeFileTemplate::getfPublishDate, bo.getPublishDateBegin())
            .lt(bo.getPublishDateEnd() != null, FwAuthorizeFileTemplate::getfPublishDate, bo.getPublishDateEnd())
            .page(query);
        IPage<AuthorizeFileTemplateBo> convert = page.convert(entity -> entityToBo(entity));
        return convert;
    }

    public static FwAuthorizeFileTemplate boToEntity(AuthorizeFileTemplateBo bo) {
        if (bo == null) {
            return null;
        }
        FwAuthorizeFileTemplate entity = new FwAuthorizeFileTemplate();
        BeanUtils.copyProperties(bo, entity);
        return entity;
    }

    public static AuthorizeFileTemplateBo entityToBo(FwAuthorizeFileTemplate entity) {
        if (entity == null) {
            return null;
        }
        AuthorizeFileTemplateBo bo = new AuthorizeFileTemplateBo();
        BeanUtils.copyProperties(entity, bo);
        return bo;
    }

}