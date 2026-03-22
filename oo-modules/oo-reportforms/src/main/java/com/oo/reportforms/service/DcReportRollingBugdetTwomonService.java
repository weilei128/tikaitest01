package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomon;

/**
 * 滚动预测2+10Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetTwomonService extends IService<DcReportRollingBugdetTwomon>
{

    /**
     * 查询滚动预测2+10列表
     * 
     * @param dcReportRollingBugdetTwomon 滚动预测2+10
     * @return 滚动预测2+10集合
     */
    public List<DcReportRollingBugdetTwomon> selectDcReportRollingBugdetTwomonList(DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon);

}
