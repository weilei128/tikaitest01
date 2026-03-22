package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetFourmonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFourmon;
import com.oo.reportforms.service.DcReportRollingBugdetFourmonService;

/**
 * 滚动预测4+8Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetFourmonServiceImpl extends ServiceImpl<DcReportRollingBugdetFourmonMapper, DcReportRollingBugdetFourmon> implements DcReportRollingBugdetFourmonService
{

    /**
     * 查询滚动预测4+8列表
     * 
     * @param dcReportRollingBugdetFourmon 滚动预测4+8
     * @return 滚动预测4+8
     */
    @Override
    public List<DcReportRollingBugdetFourmon> selectDcReportRollingBugdetFourmonList(DcReportRollingBugdetFourmon dcReportRollingBugdetFourmon)
    {
        return baseMapper.selectDcReportRollingBugdetFourmonList(dcReportRollingBugdetFourmon);
    }

}
