package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportDesionCensor;
import com.oo.reportforms.domain.DcReportDesionCensorUserView;
import com.oo.reportforms.domain.DcReportExpertLibrary;
import com.oo.reportforms.domain.vo.CensorNameVo;
import com.oo.reportforms.domain.vo.DcReportDesionCensorSummaryExamination;
import org.apache.ibatis.annotations.Param;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总Mapper接口
 *
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportDesionCensorMapper extends BaseMapper<DcReportDesionCensor>
{

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总列表
     *
     * @param designCensorName （技术管理岗）前期研究及设计审查会汇总-设计审查汇总
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总集合
     */
    public List<DcReportDesionCensorUserView> selectDcReportDesionCensorList(@Param("designCensorName") String designCensorName,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
    /**
     * 获取专家库专家名单
     *
     * @param
     * @return
     */
    public List<CensorNameVo> getCensorName(@Param("year") String year);
    /**
     * 设计审查-汇总（年份，次数）
     *
     * @param
     * @return
     */
    public List<DcReportDesionCensorSummaryExamination> getSummaryExamination();
    /**
     * 获取专家库专家名单
     *
     * @param
     * @return
     */
    public CensorNameVo getExpertCensorName(@Param("name") String name);
}
