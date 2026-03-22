package com.oo.datamanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;
import com.oo.datamanagement.api.domain.DcMdProject;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 地质单元--区块管理Mapper接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface DcMdProjectMapper extends BaseMapper<DcMdProject>
{

    /**
     * 查询地质单元--区块管理列表
     *
     * @param dcMdProject 地质单元--区块管理
     * @return 地质单元--区块管理集合
     */
    public List<DcMdProject> selectDcMdProjectList(DcMdProject dcMdProject);

    /**
     *
     */
    public List<SysMenuWell> selectTreeList(@Param("name") String name, @Param("type") Integer type);

    public List<SysMenuWell> selectScreenTreeList(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("name") String name);
    /**
     * 查询最大id
     *
     */
    public Long selectMaxId();
}
