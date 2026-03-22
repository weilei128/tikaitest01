package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTenmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTenmon;
import com.oo.reportforms.service.DcReportRollingBugdetTenmonService;

/**
 * 滚动预测10+2Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetTenmonServiceImpl extends ServiceImpl<DcReportRollingBugdetTenmonMapper, DcReportRollingBugdetTenmon> implements DcReportRollingBugdetTenmonService
{

    /**
     * 查询滚动预测10+2列表
     * 
     * @param dcReportRollingBugdetTenmon 滚动预测10+2
     * @return 滚动预测10+2
     */
    @Override
    public List<DcReportRollingBugdetTenmon> selectDcReportRollingBugdetTenmonList(DcReportRollingBugdetTenmon dcReportRollingBugdetTenmon)
    {
        return baseMapper.selectDcReportRollingBugdetTenmonList(dcReportRollingBugdetTenmon);
    }

}
