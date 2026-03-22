package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmon;

/**
 * 滚动预测7+5Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetSevenmonService extends IService<DcReportRollingBugdetSevenmon>
{

    /**
     * 查询滚动预测7+5列表
     * 
     * @param dcReportRollingBugdetSevenmon 滚动预测7+5
     * @return 滚动预测7+5集合
     */
    public List<DcReportRollingBugdetSevenmon> selectDcReportRollingBugdetSevenmonList(DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon);

}
