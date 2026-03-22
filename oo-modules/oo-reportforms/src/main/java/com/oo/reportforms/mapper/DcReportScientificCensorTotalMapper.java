package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportScientificCensorTotal;

/**
 * （技术管理岗）十四五重大科研课题审查统计0620-林志强Mapper接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportScientificCensorTotalMapper extends BaseMapper<DcReportScientificCensorTotal>
{

    /**
     * 查询重大科研课题审查统计列表
     * 
     * @param dcReportScientificCensorTotal 重大科研课题审查统计
     * @return 重大科研课题审查统计集合
     */
    public List<DcReportScientificCensorTotal> selectDcReportScientificCensorTotalList(DcReportScientificCensorTotal dcReportScientificCensorTotal);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
