package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportDrillCompStatsExplain;

/**
 * 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据Service接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface IDcReportDrillCompStatsExplainService extends IService<DcReportDrillCompStatsExplain>
{

    /**
     * 查询描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据列表
     * 
     * @param dcReportDrillCompStatsExplain 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据
     * @return 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据集合
     */
    public List<DcReportDrillCompStatsExplain> selectDcReportDrillCompStatsExplainList(DcReportDrillCompStatsExplain dcReportDrillCompStatsExplain);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
