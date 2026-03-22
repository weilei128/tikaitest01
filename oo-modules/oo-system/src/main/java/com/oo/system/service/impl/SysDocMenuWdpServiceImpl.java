package com.oo.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.MethodTranslate;
import com.oo.common.core.utils.StringUtils;
import com.oo.system.api.domain.*;
import com.oo.system.service.ISysMenuService;
import com.oo.system.service.ISysTranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.system.mapper.SysDocMenuWdpMapper;
import com.oo.system.service.ISysDocMenuWdpService;
import org.springframework.transaction.annotation.Transactional;

/**
 * WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书Service业务层处理
 * 
 * @author oo
 * @date 2023-07-21
 */
@Service
public class SysDocMenuWdpServiceImpl extends ServiceImpl<SysDocMenuWdpMapper, SysDocMenuWdp> implements ISysDocMenuWdpService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired(required = false)
    private SysDocMenuWdpMapper docMenuWdpMapper;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysTranslateService sysTranslateService;

    /**
     * 查询WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书列表
     * 
     * @param sysDocMenuWdp WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
     * @return WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
     */
    @Override
    @MethodTranslate(dtoClass = SysDocMenuWdp.class)
    public List<SysDocMenuWdp> selectSysDocMenuWdpList(SysDocMenuWdp sysDocMenuWdp)
    {
        return docMenuWdpMapper.selectSysDocMenuWdpList(sysDocMenuWdp);
    }

    /**
     * 根据wdp名称查询WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书列表
     *
     * @param wdpName WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
     * @return WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书集合
     */
    @Override
    public List<TreeSelect> selectSysDocMenuWdpNameList(String wdpName){
        List<SysDocMenuWdp> sysMenuList=docMenuWdpMapper.selectSysDocMenuWdpNameList(wdpName);
        return iSysMenuService.buildMenuWdpTreeSelect(sysMenuList);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<SysDocMenuWdp> buildDeptTree(List<SysDocMenuWdp> depts)
    {
        List<SysDocMenuWdp> returnList = new ArrayList<SysDocMenuWdp>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDocMenuWdp dept : depts)
        {
            tempList.add(dept.getId());
        }
        for (SysDocMenuWdp dept : depts)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        // 得到子节点列表
        List<SysDocMenuWdp> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDocMenuWdp tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<SysDocMenuWdp> getChildList(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        List<SysDocMenuWdp> tlist = new ArrayList<SysDocMenuWdp>();
        Iterator<SysDocMenuWdp> it = list.iterator();
        while (it.hasNext())
        {
            SysDocMenuWdp n = (SysDocMenuWdp) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        return getChildList(list, t).size() > 0;
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDocMenuWdp> depts)
    {
        List<SysDocMenuWdp> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

/*    @Override
    public List<Long> selectDeptListByRoleId(Long roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return docMenuWdpMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
    }*/

    /**
     * 新增保存文件域文件类型
     *
     * @param sysDocMenuWdp 文件域文件类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDocMenuWdp(SysDocMenuWdp sysDocMenuWdp) {
        int rows = docMenuWdpMapper.insertDocMenuWdp(sysDocMenuWdp);
        if (rows <= 0) {
            return rows;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(sysDocMenuWdp.getId().toString(), sysDocMenuWdp.getTransList());
        return transResult ? rows : 0;
    }

    /**
     * 修改保存文件域文件类型
     *
     * @param sysDocMenuWdp 文件域文件类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDocMenuWdp(SysDocMenuWdp sysDocMenuWdp) {
        int rows = docMenuWdpMapper.updateDocMenuWdp(sysDocMenuWdp);
        if (rows <= 0) {
            return rows;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(sysDocMenuWdp.getId().toString(), sysDocMenuWdp.getTransList());
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
    public int deleteMenuWdpById(Long id)
    {
        return docMenuWdpMapper.deleteMenuWdpById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDocMenuWdpByIds(Long[] id) {
        int result = docMenuWdpMapper.deleteMenuWdpByIds(id);
        if (result <= 0) {
            return result;
        }
        //删除多语言
        List<String> idList = Arrays.stream(id).map(Object::toString).collect(Collectors.toList());
        boolean transResult = sysTranslateService.remove(idList);
        return transResult ? result : 0;
    }

    /**
     * 查看wdp详情
     *
     * @param id wdpID
     * @return 结果
     */
    @Override
    @MethodTranslate(dtoClass = SysDocMenuWdp.class)
    public SysDocMenuWdp getInfo(Long id) {
        return getById(id);
    }
}
