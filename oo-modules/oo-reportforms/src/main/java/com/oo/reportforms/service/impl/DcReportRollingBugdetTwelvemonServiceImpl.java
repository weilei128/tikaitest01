package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRollingBugdetTwelvemonMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetTwelvemon;
import com.oo.reportforms.service.DcReportRollingBugdetTwelvemonService;

/**
 * 滚动预测12+0Service业务层处理
 * 
 * @author oo
 * @date 2023-10-31
 */
@Service
public class DcReportRollingBugdetTwelvemonServiceImpl extends ServiceImpl<DcReportRollingBugdetTwelvemonMapper, DcReportRollingBugdetTwelvemon> implements DcReportRollingBugdetTwelvemonService
{

    /**
     * 查询滚动预测12+0列表
     * 
     * @param dcReportRollingBugdetTwelvemon 滚动预测12+0
     * @return 滚动预测12+0
     */
    @Override
    public List<DcReportRollingBugdetTwelvemon> selectDcReportRollingBugdetTwelvemonList(DcReportRollingBugdetTwelvemon dcReportRollingBugdetTwelvemon)
    {
        return baseMapper.selectDcReportRollingBugdetTwelvemonList(dcReportRollingBugdetTwelvemon);
    }

}
