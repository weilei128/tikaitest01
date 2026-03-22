package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetEightmon;

/**
 * 滚动预测8+4Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetEightmonMapper extends BaseMapper<DcReportRollingBugdetEightmon>
{

    /**
     * 查询滚动预测8+4列表
     * 
     * @param dcReportRollingBugdetEightmon 滚动预测8+4
     * @return 滚动预测8+4集合
     */
    public List<DcReportRollingBugdetEightmon> selectDcReportRollingBugdetEightmonList(DcReportRollingBugdetEightmon dcReportRollingBugdetEightmon);

}
