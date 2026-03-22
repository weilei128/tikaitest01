package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportBudgetTrackMapper;
import com.oo.reportforms.domain.DcReportBudgetTrack;
import com.oo.reportforms.service.DcReportBudgetTrackService;

/**
 * 年度开发井工作量与预算跟踪-预算Service业务层处理
 * 
 * @author oo
 * @date 2023-10-23
 */
@Service
public class DcReportBudgetTrackServiceImpl extends ServiceImpl<DcReportBudgetTrackMapper, DcReportBudgetTrack> implements DcReportBudgetTrackService
{

    /**
     * 查询年度开发井工作量与预算跟踪-预算列表
     * 
     * @param dcReportBudgetTrack 年度开发井工作量与预算跟踪-预算
     * @return 年度开发井工作量与预算跟踪-预算
     */
    @Override
    public List<DcReportBudgetTrack> selectDcReportBudgetTrackList(DcReportBudgetTrack dcReportBudgetTrack)
    {
        return baseMapper.selectDcReportBudgetTrackList(dcReportBudgetTrack);
    }

}
