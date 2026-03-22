package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportInteractTrace;
import com.oo.reportforms.domain.DcReportInteractTraceView;
import com.oo.reportforms.domain.vo.DcReportProjectBrieflyVo;

import javax.servlet.http.HttpServletResponse;

/**
 * （技术管理岗）培训和技术交流情况更踪-王荣Service接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface IDcReportInteractTraceService extends IService<DcReportInteractTrace>
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
    public List<DcReportInteractTraceView> getInfo(String trainName, Date beginTime, Date endTime);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);

    /**
     * 导出
     *
     * @param response
     * @param trainName
     */
    void export(HttpServletResponse response, String trainName, Date beginTime, Date endTime) throws IOException;
    /**
     * 导入数据
     *
     * @param dataList
     * @return
     */
    String importData(List<DcReportInteractTraceView> dataList) throws IOException;
}
