package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTenmon;

/**
 * 滚动预测10+2Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetTenmonMapper extends BaseMapper<DcReportRollingBugdetTenmon>
{

    /**
     * 查询滚动预测10+2列表
     * 
     * @param dcReportRollingBugdetTenmon 滚动预测10+2
     * @return 滚动预测10+2集合
     */
    public List<DcReportRollingBugdetTenmon> selectDcReportRollingBugdetTenmonList(DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon);

}
