package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetElevenmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetElevenmon;
import com.oo.reportforms.service.DcReportRollingBugdetElevenmonService;

/**
 * 滚动预测11+1Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetElevenmonServiceImpl extends ServiceImpl<DcReportRollingBugdetElevenmonMapper, DcReportRollingBugdetElevenmon> implements DcReportRollingBugdetElevenmonService
{

    /**
     * 查询滚动预测11+1列表
     * 
     * @param dcReportRollingBugdetElevenmon 滚动预测11+1
     * @return 滚动预测11+1
     */
    @Override
    public List<DcReportRollingBugdetElevenmon> selectDcReportRollingBugdetElevenmonList(DcReportRollingBugdetElevenmon dcReportRollingBugdetElevenmon)
    {
        return baseMapper.selectDcReportRollingBugdetElevenmonList(dcReportRollingBugdetElevenmon);
    }

}
