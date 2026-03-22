package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetEightmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetEightmon;
import com.oo.reportforms.service.DcReportRollingBugdetEightmonService;

/**
 * 滚动预测8+4Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetEightmonServiceImpl extends ServiceImpl<DcReportRollingBugdetEightmonMapper, DcReportRollingBugdetEightmon> implements DcReportRollingBugdetEightmonService
{

    /**
     * 查询滚动预测8+4列表
     * 
     * @param dcReportRollingBugdetEightmon 滚动预测8+4
     * @return 滚动预测8+4
     */
    @Override
    public List<DcReportRollingBugdetEightmon> selectDcReportRollingBugdetEightmonList(DcReportRollingBugdetEightmon dcReportRollingBugdetEightmon)
    {
        return baseMapper.selectDcReportRollingBugdetEightmonList(dcReportRollingBugdetEightmon);
    }

}
