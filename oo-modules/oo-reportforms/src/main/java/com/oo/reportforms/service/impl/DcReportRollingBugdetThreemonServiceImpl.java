package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetThreemonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetThreemon;
import com.oo.reportforms.service.DcReportRollingBugdetThreemonService;

/**
 * 滚动预测3+9Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetThreemonServiceImpl extends ServiceImpl<DcReportRollingBugdetThreemonMapper, DcReportRollingBugdetThreemon> implements DcReportRollingBugdetThreemonService
{

    /**
     * 查询滚动预测3+9列表
     * 
     * @param dcReportRollingBugdetThreemon 滚动预测3+9
     * @return 滚动预测3+9
     */
    @Override
    public List<DcReportRollingBugdetThreemon> selectDcReportRollingBugdetThreemonList(DcReportRollingBugdetThreemon dcReportRollingBugdetThreemon)
    {
        return baseMapper.selectDcReportRollingBugdetThreemonList(dcReportRollingBugdetThreemon);
    }

}
