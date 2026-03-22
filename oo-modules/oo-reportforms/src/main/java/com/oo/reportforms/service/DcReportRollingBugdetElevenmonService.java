package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmon;

/**
 * 滚动预测11+1Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetElevenmonService extends IService<DcReportRollingBugdetElevenmon>
{

    /**
     * 查询滚动预测11+1列表
     * 
     * @param dcReportRollingBugdetElevenmon 滚动预测11+1
     * @return 滚动预测11+1集合
     */
    public List<DcReportRollingBugdetElevenmon> selectDcReportRollingBugdetElevenmonList(DcReportRollingBugdetElevenmon dcReportRollingBugdetElevenmon);

}
