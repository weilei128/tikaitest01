package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmon;

/**
 * 滚动预测7+5Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetSevenmonMapper extends BaseMapper<DcReportRollingBugdetSevenmon>
{

    /**
     * 查询滚动预测7+5列表
     * 
     * @param dcReportRollingBugdetSevenmon 滚动预测7+5
     * @return 滚动预测7+5集合
     */
    public List<DcReportRollingBugdetSevenmon> selectDcReportRollingBugdetSevenmonList(DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon);

}
