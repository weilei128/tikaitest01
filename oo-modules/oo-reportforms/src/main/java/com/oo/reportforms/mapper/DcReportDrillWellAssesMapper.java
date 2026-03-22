package com.oo.reportforms.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.reportforms.domain.DcReportDrillWellAsses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DcReportDrillWellAssesMapper extends MppBaseMapper<DcReportDrillWellAsses> {

    public String selectName(@Param("id") String id);
    public String organizationId(@Param("name") String name);


    public String projectName(@Param("id") String id);
    public String selectWellName(@Param("id") String id);
    public String selectWellType(@Param("id") String id);

    public String selectCountryName(@Param("id") String id);


    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
