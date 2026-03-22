package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFourmon;

/**
 * 滚动预测4+8Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetFourmonMapper extends BaseMapper<DcReportRollingBugdetFourmon>
{

    /**
     * 查询滚动预测4+8列表
     * 
     * @param dcReportRollingBugdetFourmon 滚动预测4+8
     * @return 滚动预测4+8集合
     */
    public List<DcReportRollingBugdetFourmon> selectDcReportRollingBugdetFourmonList(DcReportRollingBugdetFourmon dcReportRollingBugdetFourmon);

}
