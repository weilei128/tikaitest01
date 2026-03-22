package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTwomonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwomon;
import com.oo.reportforms.service.DcReportRollingBugdetTwomonService;

/**
 * 滚动预测2+10Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetTwomonServiceImpl extends ServiceImpl<DcReportRollingBugdetTwomonMapper, DcReportRollingBugdetTwomon> implements DcReportRollingBugdetTwomonService
{

    /**
     * 查询滚动预测2+10列表
     * 
     * @param dcReportRollingBugdetTwomon 滚动预测2+10
     * @return 滚动预测2+10
     */
    @Override
    public List<DcReportRollingBugdetTwomon> selectDcReportRollingBugdetTwomonList(DcReportRollingBugdetTwomon dcReportRollingBugdetTwomon)
    {
        return baseMapper.selectDcReportRollingBugdetTwomonList(dcReportRollingBugdetTwomon);
    }

}
