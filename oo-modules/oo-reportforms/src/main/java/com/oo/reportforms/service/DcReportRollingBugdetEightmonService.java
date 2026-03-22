package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetEightmon;

/**
 * 滚动预测8+4Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetEightmonService extends IService<DcReportRollingBugdetEightmon>
{

    /**
     * 查询滚动预测8+4列表
     * 
     * @param dcReportRollingBugdetEightmon 滚动预测8+4
     * @return 滚动预测8+4集合
     */
    public List<DcReportRollingBugdetEightmon> selectDcReportRollingBugdetEightmonList(DcReportRollingBugdetEightmon dcReportRollingBugdetEightmon);

}
