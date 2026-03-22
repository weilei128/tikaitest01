package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetSixmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetSixmon;
import com.oo.reportforms.service.DcReportRollingBugdetSixmonService;

/**
 * 滚动预测5+7Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetSixmonServiceImpl extends ServiceImpl<DcReportRollingBugdetSixmonMapper, DcReportRollingBugdetSixmon> implements DcReportRollingBugdetSixmonService
{

    /**
     * 查询滚动预测5+7列表
     * 
     * @param dcReportRollingBugdetSixmon 滚动预测5+7
     * @return 滚动预测5+7
     */
    @Override
    public List<DcReportRollingBugdetSixmon> selectDcReportRollingBugdetSixmonList(DcReportRollingBugdetSixmon dcReportRollingBugdetSixmon)
    {
        return baseMapper.selectDcReportRollingBugdetSixmonList(dcReportRollingBugdetSixmon);
    }

}
