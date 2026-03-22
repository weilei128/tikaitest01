package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetThreemon;

/**
 * 滚动预测3+9Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetThreemonService extends IService<DcReportRollingBugdetThreemon>
{

    /**
     * 查询滚动预测3+9列表
     * 
     * @param dcReportRollingBugdetThreemon 滚动预测3+9
     * @return 滚动预测3+9集合
     */
    public List<DcReportRollingBugdetThreemon> selectDcReportRollingBugdetThreemonList(DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon);

}
