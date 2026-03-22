package com.oo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.common.core.annotation.MethodTranslate;
import com.oo.system.api.domain.SysDocMenuBusiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDocMenuBusinessMapper extends BaseMapper<SysDocMenuBusiness> {
    /**
     * 根据条件分页查询用户列表
     *
     * @param docMenuBusiness 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    public List<SysDocMenuBusiness> selectDocMenuBusinessList(SysDocMenuBusiness docMenuBusiness);

    /**
     * 根据文件类型名称查询
     *
     * @param business_name 文件域文件类型
     * @return 文件域文件类型集合信息
     */
    @MethodTranslate(dtoClass = SysDocMenuBusiness.class)
    public List<SysDocMenuBusiness> selectDocMenuBusinessNameList(@Param("business_name") String business_name);

    /**
     * 新增保存文件域信息
     *
     * @param docMenuBusiness 文件域信息
     * @return 结果
     */
    public int insertDocMenuBusiness (SysDocMenuBusiness docMenuBusiness);

    /**
     * 修改保存文件域信息
     *
     * @param docMenuBusiness 文件域信息
     * @return 结果
     */
    public int updateDocMenuBusiness(SysDocMenuBusiness docMenuBusiness);
    /**
     * 删除文件域信息
     *
     * @param id 参数ID
     * @return 结果
     */
    public int deleteDocMenuBusinessById(Long id);
    /**
     * 批量删除文件域信息
     *
     * @param id 需要删除的用户ID
     * @return 结果
     */
    public int deleteDocMenuBusinessByIds(Long[] id);

    /**
     * 获取某个业务域id下所有子节点的id列表(包括当前节点)
     *
     * @param businessId 业务域id
     * @return
     */
    List<Long> selectChildIds(@Param("businessId") Long businessId);
}
