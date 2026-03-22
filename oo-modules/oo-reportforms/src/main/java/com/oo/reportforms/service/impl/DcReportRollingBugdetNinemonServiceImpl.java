package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetNinemonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetNinemon;
import com.oo.reportforms.service.DcReportRollingBugdetNinemonService;

/**
 * 滚动预测9+3Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetNinemonServiceImpl extends ServiceImpl<DcReportRollingBugdetNinemonMapper, DcReportRollingBugdetNinemon> implements DcReportRollingBugdetNinemonService
{

    /**
     * 查询滚动预测9+3列表
     * 
     * @param dcReportRollingBugdetNinemon 滚动预测9+3
     * @return 滚动预测9+3
     */
    @Override
    public List<DcReportRollingBugdetNinemon> selectDcReportRollingBugdetNinemonList(DcReportRollingBugdetNinemon dcReportRollingBugdetNinemon)
    {
        return baseMapper.selectDcReportRollingBugdetNinemonList(dcReportRollingBugdetNinemon);
    }

}
