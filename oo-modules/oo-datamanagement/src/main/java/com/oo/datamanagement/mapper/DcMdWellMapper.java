package com.oo.datamanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.common.core.web.domain.SysMenuWell;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.domain.vo.MdWellVo;
import com.oo.datamanagement.domain.vo.PkWellPurposeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 单井基础信息Mapper接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface DcMdWellMapper extends BaseMapper<DcMdWell>
{
    /**
     * 查询单井基础信息列表
     *
     * @param dcMdWell 单井基础信息
     * @return 单井基础信息集合
     */
    public List<DcMdWell> selectDcMdWellList(DcMdWell dcMdWell);

    public List<PkWellPurposeVo> selectWellType();

    public List<PkWellPurposeVo> selectPurpose(@Param("type") Integer type);

    public MdWellVo selectCompany(@Param("projectId") String projectId);

    public List<MdWellVo> select(@Param("wellId") String wellId,@Param("type") String type,@Param("projectId") String projectId);

    /**
     *
     */
    public List<SysMenuWell> selectScreenTreeList(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("name") String name);

}
