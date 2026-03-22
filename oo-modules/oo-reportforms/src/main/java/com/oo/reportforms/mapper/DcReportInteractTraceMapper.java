package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportInteractTrace;
import com.oo.reportforms.domain.DcReportInteractTraceView;
import com.oo.system.api.domain.SysDept;
import org.apache.ibatis.annotations.Param;

/**
 * （技术管理岗）培训和技术交流情况更踪-王荣Mapper接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportInteractTraceMapper extends BaseMapper<DcReportInteractTrace>
{

    /**
     * 查询（技术管理岗）培训和技术交流情况更踪-王荣列表
     * 
     * @param dcReportInteractTrace （技术管理岗）培训和技术交流情况更踪-王荣
     * @return （技术管理岗）培训和技术交流情况更踪-王荣集合
     */
    public List<DcReportInteractTraceView> selectDcReportInteractTraceList(DcReportInteractTrace dcReportInteractTrace);
    /**
     * 查询（技术管理岗）培训和技术交流情况更踪-王荣列表详情
     *
     * @param trainName，trainDate1，trainDate2 （技术管理岗）培训和技术交流情况更踪-王荣
     * @return （技术管理岗）培训和技术交流情况更踪-王荣集合
     */
    public List<DcReportInteractTraceView> getInfo(@Param("trainName") String trainName, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
    /**
     * 根据部门名称获取部门id
     *
     * @param deptName
     * @return
     */
    SysDept getDeptId(String deptName);

}
