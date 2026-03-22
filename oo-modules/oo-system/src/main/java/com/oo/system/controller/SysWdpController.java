package com.oo.system.controller;


import com.oo.common.core.domain.R;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.domain.vo.RoleFilePermissionVo;
import com.oo.system.domain.vo.WdpQueryVo;
import com.oo.system.service.ISysWdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wdp")
public class SysWdpController extends BaseController {


    @Autowired
    private ISysWdpService iSysWdpService;

    @Log(title = "查询WDP文件", businessType = BusinessType.OTHER)
    @PostMapping("/wdpQuery")
    public R<List<WdpQueryVo>> selectWdpList(String userId)
    {
        if (StringUtils.isEmpty(userId) )
        {
            return R.fail("用户id不能为空");
        }
        return R.ok(iSysWdpService.selectWdpList(userId));

    }
    @Log(title = "更新角色权限文件类型", businessType = BusinessType.UPDATE)
    @PostMapping("/updateRoleFilePermission")
    public AjaxResult updateRoleFilePermission(@RequestBody List<SysRoleDocMenu> sysRoleDocMenuList)
    {
        if (sysRoleDocMenuList.size()==0||(sysRoleDocMenuList.get(0).getRoleId())==null)
        {
            return AjaxResult.error("角色权限文件类型不能为空");
        }
        int a=iSysWdpService.updateRoleFilePermission(sysRoleDocMenuList,1);
        if(a>0){
            return AjaxResult.success();
        }
        return AjaxResult.error("更新失败");
    }

    @Log(title = "查询角色的文件权限", businessType = BusinessType.UPDATE)
    @PostMapping("/selectRoleFilePermission")
    public R<RoleFilePermissionVo> selectRoleFilePermission(Long role_id)
    {
        if (role_id==null)
        {
            return R.fail("角色权限不能为空");
        }
        return R.ok(iSysWdpService.selectRoleFilePermission(role_id));
    }


    @Log(title = "查询角色的文件所有功能权限", businessType = BusinessType.UPDATE)
    @PostMapping("/selectRoleFile")
    public R<List<Integer>> selectRoleFile(Long role_id,Long business_id)
    {
        if (business_id==null ||role_id==null)
        {
            return R.fail("business_id和role_id不能为空");
        }
        return R.ok(iSysWdpService.selectRoleFile(role_id,business_id));
    }

    @Log(title = "查询业务域", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysDocMenuWdp sysDocMenuWdp)
    {
        startPage();
        List<SysDocMenuBusiness> list = iSysWdpService.selectDocMenuBusinessList(sysDocMenuWdp);
        return getDataTable(list);

    }
}
