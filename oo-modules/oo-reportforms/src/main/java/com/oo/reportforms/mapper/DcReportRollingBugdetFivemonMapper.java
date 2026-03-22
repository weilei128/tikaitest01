package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemon;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemonMonth;

/**
 * 滚动预测5+7Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetFivemonMapper extends BaseMapper<DcReportRollingBugdetFivemon>
{

    /**
     * 查询滚动预测5+7列表
     * 
     * @param dcReportRollingBugdetFivemon 滚动预测5+7
     * @return 滚动预测5+7集合
     */
    public List<DcReportRollingBugdetFivemon> selectDcReportRollingBugdetFivemonList(DcReportRollingBugdetFivemon dcReportRollingBugdetFivemon);

    public List<DcReportRollingBugdetFivemonMonth> selectDcReportRollingBugdetFivemonYear(String rollingBugdetId);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
