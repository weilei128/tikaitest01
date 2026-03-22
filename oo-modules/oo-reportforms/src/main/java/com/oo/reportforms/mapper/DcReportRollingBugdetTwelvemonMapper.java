package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemon;

/**
 * 滚动预测12+0Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetTwelvemonMapper extends BaseMapper<DcReportRollingBugdetTwelvemon>
{

    /**
     * 查询滚动预测12+0列表
     * 
     * @param dcReportRollingBugdetTwelvemon 滚动预测12+0
     * @return 滚动预测12+0集合
     */
    public List<DcReportRollingBugdetTwelvemon> selectDcReportRollingBugdetTwelvemonList(DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon);

}
