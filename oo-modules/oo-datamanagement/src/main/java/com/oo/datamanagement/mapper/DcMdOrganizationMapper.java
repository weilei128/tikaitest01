package com.oo.datamanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;
import com.oo.datamanagement.domain.vo.MdNumProjectVo;
import com.oo.datamanagement.domain.vo.MdProjectVo;
import com.oo.datamanagement.domain.vo.PkWellPurposeVo;
import com.oo.system.api.domain.DcMdOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 组织机构层级关系--项目管理Mapper接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface DcMdOrganizationMapper extends BaseMapper<DcMdOrganization>
{
    /**
     * 查询组织机构层级关系--项目管理列表
     *
     * @param dcMdOrganization 组织机构层级关系--项目管理
     * @return 组织机构层级关系--项目管理集合
     */
    public List<DcMdOrganization> selectDcMdOrganizationList(DcMdOrganization dcMdOrganization);

    /**
     *
     */
    public List<SysMenuWell> selectTreeList(@Param("name") String name, @Param("type") Integer type);

    public List<SysMenuWell> selectScreenTreeList(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("name") String name);

    public List<MdProjectVo> selectProjectList(@Param("companyName") String companyId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    public List<MdNumProjectVo> selectProjectNum(@Param("companyName") String companyId,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    public List<MdProjectVo> selectBlockList(@Param("companyName") String companyId,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    public List<MdNumProjectVo> selectBlockNum(@Param("companyName") String companyId,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);


    public List<MdProjectVo> selectOrganizationProjectList(@Param("companyId") String companyId);
    public List<MdProjectVo> selectOrganizationBlockList(@Param("companyId") String companyId);
    /**
     * 查询最大id
     *
     */
    public Long selectMaxId();
    /**
     * 获取所有国家
     *
     */
    public List<PkWellPurposeVo> selectAllCountry();
}
