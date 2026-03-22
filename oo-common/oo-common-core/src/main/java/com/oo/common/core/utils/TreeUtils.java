package com.oo.common.core.utils;

import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils {

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public static List<SysMenu> buildMenuTree(List<SysMenu> menus)
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
            if (menu.getParentId()==null||!tempList.contains(menu.getParentId()))
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
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<SysMenu> list, SysMenu t)
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
    private static List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId()!=null&&n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public static List<SysMenuWell> buildMenuWellTree(List<SysMenuWell> menus)
    {
        List<SysMenuWell> returnList = new ArrayList<SysMenuWell>();
        List<String> tempList = new ArrayList<String>();
        for (SysMenuWell dept : menus)
        {
            tempList.add(dept.getMenuId());
        }
        for (Iterator<SysMenuWell> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysMenuWell menu = (SysMenuWell) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (menu.getParentId()==null||!tempList.contains(menu.getParentId()))
            {
                recursionFnWell(menus, menu);
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
    private static void recursionFnWell(List<SysMenuWell> list, SysMenuWell t)
    {
        // 得到子节点列表
        List<SysMenuWell> childList = getChildWellList(list, t);
        t.setChildren(childList);
        for (SysMenuWell tChild : childList)
        {
            if (hasChildWell(list, tChild))
            {
                recursionFnWell(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<SysMenuWell> getChildWellList(List<SysMenuWell> list, SysMenuWell t)
    {
        List<SysMenuWell> tlist = new ArrayList<SysMenuWell>();
        Iterator<SysMenuWell> it = list.iterator();
        while (it.hasNext())
        {
            SysMenuWell n = (SysMenuWell) it.next();
            if (n.getParentId()!=null&&n.getParentId().equals(t.getMenuId()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChildWell(List<SysMenuWell> list, SysMenuWell t)
    {
        return getChildWellList(list, t).size() > 0;
    }
}
