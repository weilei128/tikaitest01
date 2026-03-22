package com.oo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.MethodTranslate;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.mapper.SysDocMenuBusinessMapper;
import com.oo.system.service.ISysDocMenuBusinessService;
import com.oo.system.service.ISysMenuService;
import com.oo.system.service.ISysTranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysDocMenuBusinessServiceImpl extends ServiceImpl<SysDocMenuBusinessMapper, SysDocMenuBusiness> implements ISysDocMenuBusinessService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired(required = false)
    private SysDocMenuBusinessMapper docMenuBusinessMapper;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysTranslateService sysTranslateService;

    /**
     * 根据条件分页查询用户列表
     *
     * @param docMenuBusiness 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    @Override
    @MethodTranslate(dtoClass = SysDocMenuBusiness.class)
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(SysDocMenuBusiness docMenuBusiness)
    {
        return docMenuBusinessMapper.selectDocMenuBusinessList(docMenuBusiness);
    }

    /**
     * 根据文件类型查询
     *
     * @param business_name 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    /*public List<SysDocMenuBusiness> selectDocMenuBusinessNameList(String business_name)
    {
        return docMenuBusinessMapper.selectDocMenuBusinessNameList(business_name);
    }*/
    @Override
    public List<TreeSelect> selectDocMenuBusinessNameList(String business_name){
        List<SysDocMenuBusiness> sysMenuList=docMenuBusinessMapper.selectDocMenuBusinessNameList(business_name);
        return iSysMenuService.buildMenuBusinessTreeSelect(sysMenuList);
    }


    /**
     * 新增保存文件域文件类型
     *
     * @param docMenuBusiness 文件域文件类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDocMenuBusiness(SysDocMenuBusiness docMenuBusiness)
    {
        // 新增文件域文件类型
        int rows = docMenuBusinessMapper.insertDocMenuBusiness(docMenuBusiness);
        if (rows <= 0) {
            return rows;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(docMenuBusiness.getId().toString(), docMenuBusiness.getTransList());
        return transResult ? rows : 0;
    }

    /**
     * 修改保存文件域文件类型
     *
     * @param docMenuBusiness 文件域文件类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDocMenuBusiness(SysDocMenuBusiness docMenuBusiness)
    {
        //Long userId = userInfo.getUserId();
        int rows = docMenuBusinessMapper.updateDocMenuBusiness(docMenuBusiness);
        if (rows <= 0) {
            return rows;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(docMenuBusiness.getId().toString(), docMenuBusiness.getTransList());
        return transResult ? rows : 0;
    }

    /**
     * 删除文件域文件类型
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDocMenuBusinessById(Long id)
    {
        return docMenuBusinessMapper.deleteDocMenuBusinessById(id);
    }

    /**
     * 批量删除文件域文件类型
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDocMenuBusinessByIds(Long[] id)
    {
        int result = docMenuBusinessMapper.deleteDocMenuBusinessByIds(id);
        if (result <= 0) {
            return result;
        }
        //删除多语言
        List<String> idList = Arrays.stream(id).map(Object::toString).collect(Collectors.toList());
        boolean transResult = sysTranslateService.remove(idList);
        return transResult ? result : 0;
    }

    /**
     * 查看业务域详情
     *
     * @param id 业务域ID
     * @return 结果
     */
    @Override
    @MethodTranslate(dtoClass = SysDocMenuBusiness.class)
    public SysDocMenuBusiness getInfo(Long id) {
        return getById(id);
    }

    /**
     * 获取某个业务域id下所有子节点的id列表(包括当前节点)
     *
     * @param businessId 业务域id
     * @return
     */
    @Override
    public List<Long> selectChildIds(Long businessId)
    {
        return docMenuBusinessMapper.selectChildIds(businessId);
    }
}
