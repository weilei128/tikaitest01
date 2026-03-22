package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import com.oo.reportforms.domain.DcReportDesionCensor;
import com.oo.reportforms.domain.DcReportDesionCensorUserView;
import com.oo.reportforms.domain.DcReportExpertLibrary;

import javax.servlet.http.HttpServletResponse;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总Service接口
 *
 * @author oo
 * @date 2023-08-11
 */
public interface IDcReportDesionCensorService extends IService<DcReportDesionCensor>
{

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总列表
     *
     * @param designCensorName （技术管理岗）前期研究及设计审查会汇总-设计审查汇总
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总集合
     */
    public List<DcReportDesionCensorUserView> selectDcReportDesionCensorList(String designCensorName, Date beginTime, Date endTime);

    /**
     * 查询描述：设计审查会专家参与次数
     *
     * @return 设计审查会专家参与次数
     */
    public Map<String, List> screenExamination(Date beginTime, Date endTime);
    /**
     * 导出
     *
     * @param response
     * @param designCensorName
     */
    void export(HttpServletResponse response,String designCensorName, Date beginTime, Date endTime) throws IOException;

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
    /**
     * 导入数据
     * @param dataList
     * @return
     */
    String importData(List<DcReportDesionCensorUserView> dataList);
}
