package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetSevenmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSevenmon;
import com.oo.reportforms.service.DcReportRollingBugdetSevenmonService;

/**
 * 滚动预测7+5Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetSevenmonServiceImpl extends ServiceImpl<DcReportRollingBugdetSevenmonMapper, DcReportRollingBugdetSevenmon> implements DcReportRollingBugdetSevenmonService
{

    /**
     * 查询滚动预测7+5列表
     * 
     * @param dcReportRollingBugdetSevenmon 滚动预测7+5
     * @return 滚动预测7+5
     */
    @Override
    public List<DcReportRollingBugdetSevenmon> selectDcReportRollingBugdetSevenmonList(DcReportRollingBugdetSevenmon dcReportRollingBugdetSevenmon)
    {
        return baseMapper.selectDcReportRollingBugdetSevenmonList(dcReportRollingBugdetSevenmon);
    }

}
