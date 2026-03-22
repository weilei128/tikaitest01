package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomon;

/**
 * 滚动预测2+10Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetTwomonMapper extends BaseMapper<DcReportRollingBugdetTwomon>
{

    /**
     * 查询滚动预测2+10列表
     * 
     * @param dcReportRollingBugdetTwomon 滚动预测2+10
     * @return 滚动预测2+10集合
     */
    public List<DcReportRollingBugdetTwomon> selectDcReportRollingBugdetTwomonList(DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon);

}
