package com.oo.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.system.api.domain.SysDept;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.domain.SysDocMenuWdp;

/**
 * WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书Service接口
 * 
 * @author oo
 * @date 2023-07-21
 */
public interface ISysDocMenuWdpService extends IService<SysDocMenuWdp>
{

    /**
     * 查询WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书列表
     * 
     * @param sysDocMenuWdp WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
     * @return WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书集合
     */
    public List<SysDocMenuWdp> selectSysDocMenuWdpList(SysDocMenuWdp sysDocMenuWdp);

    /**
     * 根据wdp名称查询WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书列表
     *
     * @param wdpName WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书
     * @return WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书集合
     */
    public List<TreeSelect> selectSysDocMenuWdpNameList(String wdpName);

    public List<TreeSelect> buildDeptTreeSelect(List<SysDocMenuWdp> depts);
    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
   /* public List<Long> selectDeptListByRoleId(Long roleId);*/

    /**
     * 新增保存WDP信息
     *
     * @param sysDocMenuWdp WDP信息
     * @return 结果
     */
    public int insertDocMenuWdp(SysDocMenuWdp sysDocMenuWdp);

    /**
     * 修改保存WDP信息
     *
     * @param sysDocMenuWdp WDP信息
     * @return 结果
     */
    public int updateDocMenuWdp(SysDocMenuWdp sysDocMenuWdp);

    /**
     * 通过ID删除WDP文件类型菜单
     *
     * @param id 用户ID
     * @return 结果
     */
    public int deleteMenuWdpById(Long id);

    /**
     * 批量删除WDP信息
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    public int deleteDocMenuWdpByIds(Long[] id);

    /**
     * 查看wdp详情
     *
     * @param id wdpID
     * @return 结果
     */
    SysDocMenuWdp getInfo(Long id);
}
