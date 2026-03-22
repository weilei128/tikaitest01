package com.oo.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.oo.common.core.annotation.MethodTranslate;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.system.api.domain.*;
import com.oo.system.api.vo.DcMdWellVo;
import com.oo.system.domain.vo.RouterVo;
import com.oo.system.mapper.SysRoleMenuMapper;
import com.oo.system.service.ISysMenuService;
import com.oo.system.service.ISysTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.common.core.constant.Constants;
import com.oo.common.core.constant.UserConstants;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.SecurityUtils;
import com.oo.system.domain.vo.MetaVo;
import com.oo.system.mapper.SysMenuMapper;
import com.oo.system.mapper.SysRoleMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单 业务层处理
 *
 * @author oo
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired(required = false)
    private SysMenuMapper menuMapper;

    @Autowired(required = false)
    private SysRoleMapper roleMapper;

    @Autowired(required = false)
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private ISysTranslateService sysTranslateService;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    @MethodTranslate(dtoClass = SysMenu.class)
    public List<SysMenu> selectMenuList(Long userId)
    {
        return selectMenuList(new SysMenu(), userId);
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    @MethodTranslate(dtoClass = SysMenu.class)
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId)
    {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId))
        {
            menuList = menuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("userId", userId);
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId,String effect,String site)
    {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId,effect,site);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
//    @MethodTranslate(dtoClass = SysMenu.class)
    public List<SysMenu> selectMenuTreeByUserId(Long userId,String effect,String site)
    {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId))
        {
            menus = menuMapper.selectMenuTreeAll(effect,site);
        }
        else
        {
            menus = menuMapper.selectMenuTreeByUserId(userId,effect,site);
        }
        return getChildPerms(menus, 0);
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return menuMapper.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath(), menu.getIsFrame()));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMenuFrame(menu))
            {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath(), menu.getIsFrame()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            else if (menu.getParentId().intValue() == 0 && isInnerLink(menu))
            {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysMenu dept : menus)
        {
            tempList.add(dept.getMenuId());
        }
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysMenu menu = (SysMenu) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus)
    {
        List<SysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */

    public List<SysDocMenuWdp> buildMenuWdpTree(List<SysDocMenuWdp> menus)
    {
        List<SysDocMenuWdp> returnList = new ArrayList<SysDocMenuWdp>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDocMenuWdp dept : menus)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<SysDocMenuWdp> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysDocMenuWdp menu = (SysDocMenuWdp) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFnMenuWdp(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFnMenuWdp(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        // 得到子节点列表
        List<SysDocMenuWdp> childList = getChildWdpList(list, t);
        t.setChildren(childList);
        for (SysDocMenuWdp tChild : childList)
        {
            if (hasChildWdp(list, tChild))
            {
                recursionFnMenuWdp(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDocMenuWdp> getChildWdpList(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        List<SysDocMenuWdp> tlist = new ArrayList<SysDocMenuWdp>();
        Iterator<SysDocMenuWdp> it = list.iterator();
        while (it.hasNext())
        {
            SysDocMenuWdp n = (SysDocMenuWdp) it.next();
            if (Long.valueOf(n.getParentId()) == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChildWdp(List<SysDocMenuWdp> list, SysDocMenuWdp t)
    {
        return getChildWdpList(list, t).size() > 0;
    }

    @Override
    public List<TreeSelect> buildMenuWdpTreeSelect(List<SysDocMenuWdp> menus) {
        List<SysDocMenuWdp> menuTrees = buildMenuWdpTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */

    public List<SysDocMenuBusiness> buildMenuBusinessTree(List<SysDocMenuBusiness> menus)
    {
        List<SysDocMenuBusiness> returnList = new ArrayList<SysDocMenuBusiness>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDocMenuBusiness dept : menus)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<SysDocMenuBusiness> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysDocMenuBusiness menu = (SysDocMenuBusiness) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFnMenuBusiness(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFnMenuBusiness(List<SysDocMenuBusiness> list, SysDocMenuBusiness t)
    {
        // 得到子节点列表
        List<SysDocMenuBusiness> childList = getChildMenuBusinessList(list, t);
        t.setChildren(childList);
        for (SysDocMenuBusiness tChild : childList)
        {
            if (hasChildMenuBusiness(list, tChild))
            {
                recursionFnMenuBusiness(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDocMenuBusiness> getChildMenuBusinessList(List<SysDocMenuBusiness> list, SysDocMenuBusiness t)
    {
        List<SysDocMenuBusiness> tlist = new ArrayList<SysDocMenuBusiness>();
        Iterator<SysDocMenuBusiness> it = list.iterator();
        while (it.hasNext())
        {
            SysDocMenuBusiness n = (SysDocMenuBusiness) it.next();
            if (Long.valueOf(n.getParentId()) == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChildMenuBusiness(List<SysDocMenuBusiness> list, SysDocMenuBusiness t)
    {
        return getChildMenuBusinessList(list, t).size() > 0;
    }

    @Override
    public List<TreeSelect> buildMenuBusinessTreeSelect(List<SysDocMenuBusiness> menus) {
        List<SysDocMenuBusiness> menuTrees = buildMenuBusinessTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<TreeSelect> buildMdWellVoTreeSelect(List<DcMdWellVo> menus) {
        List<DcMdWellVo> menuTrees = buildMdWellVoTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */

    public List<DcMdWellVo> buildMdWellVoTree(List<DcMdWellVo> menus)
    {
        List<DcMdWellVo> returnList = new ArrayList<DcMdWellVo>();
        List<String> tempList = new ArrayList<String>();
        for (DcMdWellVo dept : menus)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<DcMdWellVo> iterator = menus.iterator(); iterator.hasNext();)
        {
            DcMdWellVo menu = (DcMdWellVo) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFnMdWellVo(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFnMdWellVo(List<DcMdWellVo> list, DcMdWellVo t)
    {
        // 得到子节点列表
        List<DcMdWellVo> childList = getChildMdWellVoList(list, t);
        t.setChildren(childList);
        for (DcMdWellVo tChild : childList)
        {
            if (hasChildMdWellVo(list, tChild))
            {
                recursionFnMdWellVo(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DcMdWellVo> getChildMdWellVoList(List<DcMdWellVo> list, DcMdWellVo t)
    {
        List<DcMdWellVo> tlist = new ArrayList<DcMdWellVo>();
        Iterator<DcMdWellVo> it = list.iterator();
        while (it.hasNext())
        {
            DcMdWellVo n = (DcMdWellVo) it.next();
            if ((n.getParentId()) .equals(t.getId()) )
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChildMdWellVo(List<DcMdWellVo> list, DcMdWellVo t)
    {
        return getChildMdWellVoList(list, t).size() > 0;
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    @MethodTranslate(dtoClass = SysMenu.class)
    public SysMenu selectMenuById(Long menuId)
    {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId)
    {
        int result = menuMapper.hasChildByMenuId(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId)
    {
        int result = roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu)
    {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMenu(SysMenu menu)
    {
        int result = menuMapper.updateMenu(menu);
        if (result <= 0) {
            return result;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(menu.getMenuId().toString(), menu.getTransList());
        return transResult ? result : 0;
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenu menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu)
    {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu)
    {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu))
        {
            component = menu.getComponent();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu)
    {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu)
    {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(menu.getPath());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenu menu)
    {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
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
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS },
                new String[] { "", "" });
    }

    /**
     * 删除子集菜单
     *
     * @param menuIds 菜单id数组
     * @return
     */
    @Override
    public boolean deleteChildByMenuId(Long[] menuIds)
    {
        return menuMapper.deleteChildByMenuId(menuIds);
    }

    /**
     * 批量删除菜单
     *
     * @param menuIds 菜单id数组
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchMenuById(Long[] menuIds)
    {
        //删除多语言
        List<String> menuIdList = Arrays.stream(menuIds).map(Object::toString).collect(Collectors.toList());
        return menuMapper.deleteBatchMenuById(menuIds) && sysTranslateService.remove(menuIdList);
    }

    /**
     * 删除菜单权限
     *
     * @param menuIds 菜单id数组
     * @return
     */
    @Override
    public boolean deleteMenuExistRole(Long[] menuIds)
    {
        return roleMenuMapper.deleteMenuExistRole(menuIds);
    }
}
