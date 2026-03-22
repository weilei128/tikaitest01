package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportExpertLibrary;
import org.apache.ibatis.annotations.Param;

/**
 * 技术岗-专家库Mapper接口
 * 
 * @author oo
 * @date 2023-09-18
 */
public interface DcReportExpertLibraryMapper extends BaseMapper<DcReportExpertLibrary>
{
    /**
     * 查询技术岗-专家库列表
     *
     * @param beginTime 技术岗-专家库
     * @return 技术岗-专家库集合
     */
    public List<DcReportExpertLibrary> selectDcReportExpertLibraryList(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,@Param("unit") String unit,@Param("technicalTitle") String technicalTitle,@Param("ifRetire") String ifRetire);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
