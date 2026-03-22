package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemon;

/**
 * 滚动预测12+0Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetTwelvemonService extends IService<DcReportRollingBugdetTwelvemon>
{

    /**
     * 查询滚动预测12+0列表
     * 
     * @param dcReportRollingBugdetTwelvemon 滚动预测12+0
     * @return 滚动预测12+0集合
     */
    public List<DcReportRollingBugdetTwelvemon> selectDcReportRollingBugdetTwelvemonList(DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon);

}
