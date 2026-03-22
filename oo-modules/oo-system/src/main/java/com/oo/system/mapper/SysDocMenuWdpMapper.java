package com.oo.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.common.core.annotation.MethodTranslate;
import com.oo.system.api.domain.SysDocMenuWdp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * WDP文件类型菜单-用于维护文件类型的层级关系     例如：1.活动》井位建议书Mapper接口
 * 
 * @author oo
 * @date 2023-07-21
 */
@Mapper
public interface SysDocMenuWdpMapper extends BaseMapper<SysDocMenuWdp>
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
    @MethodTranslate(dtoClass = SysDocMenuWdp.class)
    public List<SysDocMenuWdp> selectSysDocMenuWdpNameList(@Param("wdpName") String wdpName);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    /*public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);
*/

    /**
     * 新增WDP文件类型菜单
     *
     * @param sysDocMenuWdp 用户信息
     * @return 结果
     */
    public int insertDocMenuWdp(SysDocMenuWdp sysDocMenuWdp);

    /**
     * 修改WDP文件类型菜单
     *
     * @param sysDocMenuWdp 用户信息
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
     * 批量删除WDP文件类型菜单
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    public int deleteMenuWdpByIds(Long[] id);
}
