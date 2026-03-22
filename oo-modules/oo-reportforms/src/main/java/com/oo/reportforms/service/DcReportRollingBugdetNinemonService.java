package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRollingBugdetNinemon;

/**
 * 滚动预测9+3Service接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetNinemonService extends IService<DcReportRollingBugdetNinemon>
{

    /**
     * 查询滚动预测9+3列表
     * 
     * @param dcReportRollingBugdetNinemon 滚动预测9+3
     * @return 滚动预测9+3集合
     */
    public List<DcReportRollingBugdetNinemon> selectDcReportRollingBugdetNinemonList(DcReportRollingBugdetNinemon dcReportRollingBugdetNinemon);

}
