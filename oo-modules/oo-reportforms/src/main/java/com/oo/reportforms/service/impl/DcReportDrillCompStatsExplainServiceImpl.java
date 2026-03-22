package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportDrillCompStatsExplainMapper;
import com.oo.reportforms.domain.DcReportDrillCompStatsExplain;
import com.oo.reportforms.service.IDcReportDrillCompStatsExplainService;

/**
 * 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据Service业务层处理
 * 
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportDrillCompStatsExplainServiceImpl extends ServiceImpl<DcReportDrillCompStatsExplainMapper, DcReportDrillCompStatsExplain> implements IDcReportDrillCompStatsExplainService
{

    /**
     * 查询描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据列表
     * 
     * @param dcReportDrillCompStatsExplain 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据
     * @return 描述：（作业岗）海油国际钻完井季度统计模板-解释说明sheet页  不存历史数据
     */
    @Override
    public List<DcReportDrillCompStatsExplain> selectDcReportDrillCompStatsExplainList(DcReportDrillCompStatsExplain dcReportDrillCompStatsExplain)
    {
        return baseMapper.selectDcReportDrillCompStatsExplainList(dcReportDrillCompStatsExplain);
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String[] ids) {
        return baseMapper.deleteItemsByIds(ids);
    }
}
