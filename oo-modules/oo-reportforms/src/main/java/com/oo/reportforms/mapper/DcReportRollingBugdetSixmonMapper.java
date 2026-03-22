package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSixmon;

/**
 * 滚动预测5+7Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetSixmonMapper extends BaseMapper<DcReportRollingBugdetSixmon>
{

    /**
     * 查询滚动预测5+7列表
     * 
     * @param dcReportRollingBugdetSixmon 滚动预测5+7
     * @return 滚动预测5+7集合
     */
    public List<DcReportRollingBugdetSixmon> selectDcReportRollingBugdetSixmonList(DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon);

}
