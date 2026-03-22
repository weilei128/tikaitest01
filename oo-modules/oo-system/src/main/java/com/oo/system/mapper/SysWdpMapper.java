package com.oo.system.mapper;

import com.oo.common.core.web.domain.SysMenu;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.domain.vo.WdpQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysWdpMapper {
    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public List<WdpQueryVo> selectWdpList(@Param("userId") String userId);

    public void delete(@Param("role_id") Long role_id);

    /**
     * 批量新增角色业务域文件类型ID
     *
     * @param sysRoleDocMenuList
     * @return 结果
     */
    public int insert_business_id(List<SysRoleDocMenu> sysRoleDocMenuList);
    /**
     * 更新SysRoles的doc_type
     *
     * @param
     * @return 结果
     */
    public int updateSysRoles(@Param("role_id") Long role_id,@Param("doc_type") int doc_type);
    /**
     * 查询
     *
     * @param role_id
     * @return 结果
     */
    public int selectSysRoles(@Param("role_id") Long role_id);
    /**
     * 查询sys_doc_menu_business 生成树结构导出
     *
     * @param role_id
     * @return 结果
     */
    public List<SysMenu> selectSysDocMenuBusiness(@Param("role_id") Long role_id);
    /**
     * 查询
     *
     * @param role_id
     * @return 结果
     */
    public List<SysMenu> selectSysRoleDocMenu(@Param("role_id") Long role_id);
    /**
     * 查询 色菜单权限
     * @param role_id
     * @return 结果
     */
    public List<SysMenu> selectSysRoleMenu(@Param("role_id") Long role_id);
    /**
     * 查询 色菜单权限
     * @param role_id
     * @return 结果
     */
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(@Param("wdpName") String wdpName,@Param("parentId") Long parentId,@Param("orderNum")
            Integer orderNum);

    /**
     * 查询sys_role_doc_menu中被选择的business_name  by  role_id
     * 生成树结构导出
     *
     * @param role_id
     * @return 结果
     */
    public List<SysMenu> selectSysRoleDocMenu1(@Param("role_id") Long role_id);
    /**
     * 方法已注释
     *
     * @param role_id
     * @return 结果
     */
    public List<SysMenu> selectSysRoleDocMenu2(@Param("role_id") Long role_id);
    /**
     * 查询sys_role_doc_menu 操作编号 operatetype
     *
     * @param role_id
     * @return 结果
     */
    public List<Integer> selectRoleFile(@Param("role_id") Long role_id,@Param("business_id") Long business_id);

    /**
     * 查询sys_role_doc_menu 选择的 business_id
     *
     * @param role_id
     * @return 结果
     */
    public List<Integer> selectSysRoleDocMenu3(@Param("role_id") Long role_id);
}
