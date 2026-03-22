package com.oo.common.core.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oo.common.core.constant.HttpStatus;
import com.oo.common.core.utils.sql.SqlUtil;
import com.oo.common.core.web.page.PageDomain;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.core.web.page.TableSupport;

import java.util.List;

/**
 * 分页工具类
 * 
 * @author
 */
public class PageUtils extends PageHelper
{
    private static Page page;

    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        page = new Page();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            page = PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    public static TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(page.isEmpty() ? new PageInfo(list).getTotal() : page.getTotal());
        return rspData;
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
