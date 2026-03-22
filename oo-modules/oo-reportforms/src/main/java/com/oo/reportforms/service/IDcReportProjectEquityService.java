package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportProjectEquity;
import com.oo.reportforms.domain.vo.DcReportProjectEquityVo;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;

/**
 * 国际公司项目权益情况Service接口
 * 
 * @author oo
 * @date 2023-09-07
 */
public interface IDcReportProjectEquityService extends IService<DcReportProjectEquity>
{
    /**
     * 根据区块查询国际公司项目权益情况列表
     *
     * @param blockId 区块
     * @return 国际公司项目权益情况集合
     */
    public List<DcReportProjectEquityVo> selectList(String blockId);

    public List<DcReportProjectEquityVo> selectScreenList(String blockId);
    /**
     * 查询国际公司项目权益情况列表
     * 
     * @param dcReportProjectEquity 国际公司项目权益情况
     * @return 国际公司项目权益情况集合
     */
    public List<DcReportProjectEquity> selectDcReportProjectEquityList(DcReportProjectEquity dcReportProjectEquity);

    /**
     * 导出
     *
     * @param response
     * @param blockId
     */
    void export(HttpServletResponse response, String blockId) throws IOException;
    /**
     * 导入数据
     * @param dataList
     * @return
     */
    String importData(List<DcReportProjectEquityVo> dataList);
    boolean deleteItemsByIds(String ids);
}
