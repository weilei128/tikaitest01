package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRollingBugdetFivemonMonth;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemon;
import com.oo.reportforms.domain.DcReportRollingBugdetOnemonMonth;

/**
 * 滚动预测1+11Mapper接口
 * 
 * @author oo
 * @date 2023-10-31
 */
public interface DcReportRollingBugdetOnemonMapper extends BaseMapper<DcReportRollingBugdetOnemon>
{

    /**
     * 查询滚动预测1+11列表
     * 
     * @param dcReportRollingBugdetOnemon 滚动预测1+11
     * @return 滚动预测1+11集合
     */
    public List<DcReportRollingBugdetOnemon> selectDcReportRollingBugdetOnemonList(DcReportRollingBugdetOnemon dcReportRollingBugdetOnemon);
    public List<DcReportRollingBugdetOnemonMonth> selectDcReportRollingBugdetOnemonYear(String rollingBugdetId);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
