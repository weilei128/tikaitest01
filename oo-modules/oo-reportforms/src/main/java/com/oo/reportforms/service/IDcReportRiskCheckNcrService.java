package com.oo.reportforms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportRiskCheckNcr;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.system.api.vo.DcMdWellVo;
import com.oo.system.api.domain.TreeSelect;

import javax.servlet.http.HttpServletResponse;

/**
 * 隐患排查：NCRService接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface IDcReportRiskCheckNcrService extends IService<DcReportRiskCheckNcr>
{

    /**
     * 查询隐患排查：NCR列表
     *
     * @param wellId 隐患排查：NCR
     * @return 隐患排查：NCR集合
     */
    public List<DcReportRiskCheckNcrVo> selectList(String wellId, Date beginTime, Date endTime,String ncStatus);

    /**
     * 查询隐患排查：NCR列表
     *
     * @param
     * @return 隐患排查：NCR集合
     */
    public List<DcReportRiskCheckNcrVo> selectDcReportRiskCheckNcrList();

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
    public String importData(List<DcReportRiskCheckNcrVo> dataList);

    /**
     * 获取组织机构集合
     * @return
     */
    public List<DcMdWellVo> getWellName();

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDcMdWellVoTreeSelect(List<DcMdWellVo> menus);

    /**
     * 导出
     *
     * @param response
     * @param wellId
     */
    void export(HttpServletResponse response,String wellId, Date beginTime, Date endTime,String ncStatus) throws IOException;
}
