package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmon;

/**
 * 滚动预测11+1Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetElevenmonMapper extends BaseMapper<DcReportRollingBugdetElevenmon>
{

    /**
     * 查询滚动预测11+1列表
     * 
     * @param dcReportRollingBugdetElevenmon 滚动预测11+1
     * @return 滚动预测11+1集合
     */
    public List<DcReportRollingBugdetElevenmon> selectDcReportRollingBugdetElevenmonList(DcReportRollingBugdetElevenmon dcReportRollingBugdetElevenmon);

}
