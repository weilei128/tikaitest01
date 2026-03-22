package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetFourmon;

/**
 * 滚动预测4+8Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetFourmonService extends IService<DcReportRollingBugdetFourmon>
{

    /**
     * 查询滚动预测4+8列表
     * 
     * @param dcReportRollingBugdetFourmon 滚动预测4+8
     * @return 滚动预测4+8集合
     */
    public List<DcReportRollingBugdetFourmon> selectDcReportRollingBugdetFourmonList(DcReportRollingBugdetFourmon dcReportRollingBugdetFourmon);

}
