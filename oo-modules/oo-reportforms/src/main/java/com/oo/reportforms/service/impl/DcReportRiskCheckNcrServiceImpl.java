package com.oo.reportforms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.DateUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.security.utils.DictUtils;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.reportforms.utils.ReportUtils;
import com.oo.system.api.vo.DcMdWellVo;
import com.oo.system.api.domain.TreeSelect;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportRiskCheckNcrMapper;
import com.oo.reportforms.domain.DcReportRiskCheckNcr;
import com.oo.reportforms.service.IDcReportRiskCheckNcrService;

import javax.servlet.http.HttpServletResponse;

/**
 * 隐患排查：NCRService业务层处理
 *
 * @author oo
 * @date 2023-08-21
 */
@Service
public class DcReportRiskCheckNcrServiceImpl extends ServiceImpl<DcReportRiskCheckNcrMapper, DcReportRiskCheckNcr> implements IDcReportRiskCheckNcrService
{
    /**
     * 查询隐患排查：NCR列表
     *
     * @param wellName 隐患排查：NCR
     * @return 隐患排查：NCR
     */
    @Override
    public List<DcReportRiskCheckNcrVo> selectList(String wellName, Date beginTime, Date endTime,String ncStatus)
    {
        List<DcReportRiskCheckNcrVo> voList = baseMapper.selectList(wellName,beginTime,endTime,ncStatus);
        return listVO(voList);
    }

    /**
     * 查询隐患排查：NCR列表
     *
     * @param
     * @return 隐患排查：NCR
     */
    @Override
    public List<DcReportRiskCheckNcrVo> selectDcReportRiskCheckNcrList()
    {
        return baseMapper.selectDcReportRiskCheckNcrList();
    }

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteItemsByIds(String[] ids) {
        return baseMapper.deleteItemsByIds(ids);
    }

    /**
     * 保存数据
     * @param dataList
     * @return
     */

    /**
     * 导入数据
     * @param dataList
     * @return
     */
    @Override
    @DictMethod(dtoClass = DcReportRiskCheckNcrVo.class)
    public String importData(List<DcReportRiskCheckNcrVo> dataList)
    {
        if (StringUtils.isNull(dataList) || dataList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
       // List<SysDictData> dictDataList = baseMapper.getDicData();//数据字典
        for (DcReportRiskCheckNcrVo item : dataList){
            boolean isFailure = false;
            StringBuilder itemFailureMsg = new StringBuilder();
            try {
                DcMdWellVo wellId = baseMapper.getWellId(item.getWellName());
                item.setWellId(wellId.getWellId());
                //检查类型
                if (StringUtils.isEmpty(item.getResourceType())) {
                    isFailure = true;
                    itemFailureMsg.append("检查类型为空；");
                }
                //检查单位
                if (StringUtils.isEmpty(item.getFacilities())) {
                    isFailure = true;
                    itemFailureMsg.append("检查单位为空；");
                }
                //风险等级
                if (StringUtils.isEmpty(item.getRiskCriticalityRanking())) {
                    isFailure = true;
                    itemFailureMsg.append("风险等级为空；");
                }
                if (StringUtils.isEmpty(item.getNcStatus())) {
                    isFailure = true;
                    itemFailureMsg.append("不符合项目前状态为空；");
                }
                if (isFailure) {
                    throw new ServiceException(itemFailureMsg.toString());
                }

                saveOrUpdate(item);
                successNum++;
            }
            catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }

    /**
     * 导出
     *
     * @param response
     * @param wellId
     */
    @Override
    public void export(HttpServletResponse response,String wellId, Date beginTime, Date endTime,String ncStatus) throws IOException {
        // excel模板路径
        String templateName = "隐患排查表.xlsx";
        XSSFWorkbook wb = ReportUtils.getTemplate(templateName);
        List<DcReportRiskCheckNcrVo> list = selectList(wellId,beginTime,endTime,ncStatus);
        if (list == null || list.isEmpty()) {
            ReportUtils.download(response, wb, templateName);
            return;
        }

        XSSFCellStyle defaultStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.CENTER, true, false);
        XSSFCellStyle contentStyle = ReportUtils.setStyle(wb, 11, HorizontalAlignment.LEFT, true, false);
        // 读取模板内sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        int m = 1;
        for (DcReportRiskCheckNcrVo item : list) {
            XSSFRow row = sheet.createRow(1 + m);
            row.createCell(0).setCellValue(m);
            row.createCell(1).setCellValue(item.getWellControlInspection());
            row.createCell(2).setCellValue(item.getResourceType());
            row.createCell(3).setCellValue(item.getFacilities());
            row.createCell(4).setCellValue(item.getWellName());
            row.createCell(5).setCellValue(item.getRiskCriticalityRanking());
            row.createCell(6).setCellValue(item.getRegisteringDate() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getRegisteringDate()));
            row.createCell(7).setCellValue(item.getNcDescription());
            row.createCell(8).setCellValue(item.getNcCorrectiveAction());
            row.createCell(9).setCellValue(item.getRequiredCloseTime() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getRequiredCloseTime()));
            row.createCell(10).setCellValue(item.getActrualCloseTime() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getActrualCloseTime()));
            row.createCell(11).setCellValue(item.getNcStatus());
            row.createCell(12).setCellValue(item.getReasonsForOpenItems());
            row.createCell(13).setCellValue(item.getRiskControlMeasures());
            row.createCell(14).setCellValue(item.getPlanCloseTime() == null ? null : DateUtils.parseDateToStr("yyyy.M.d", item.getPlanCloseTime()));
            row.createCell(15).setCellValue(item.getResponsiblePerson());
            row.getCell(0).setCellStyle(defaultStyle);
            row.getCell(1).setCellStyle(defaultStyle);
            row.getCell(2).setCellStyle(defaultStyle);
            row.getCell(3).setCellStyle(defaultStyle);
            row.getCell(4).setCellStyle(defaultStyle);
            row.getCell(5).setCellStyle(defaultStyle);
            row.getCell(6).setCellStyle(defaultStyle);
            row.getCell(7).setCellStyle(defaultStyle);
            row.getCell(8).setCellStyle(defaultStyle);
            row.getCell(9).setCellStyle(defaultStyle);
            row.getCell(10).setCellStyle(defaultStyle);
            row.getCell(11).setCellStyle(defaultStyle);
            row.getCell(12).setCellStyle(defaultStyle);
            row.getCell(13).setCellStyle(defaultStyle);
            row.getCell(14).setCellStyle(defaultStyle);
            row.getCell(15).setCellStyle(defaultStyle);
            m++;
        }
        ReportUtils.download(response, wb, templateName);
    }


    /**
     * 获取组织树集合信息
     *
     */
    @Override
    public List<DcMdWellVo> getWellName(){
        return baseMapper.getWellName();
    }

    @Override
    public List<TreeSelect> buildDcMdWellVoTreeSelect(List<DcMdWellVo> menus) {
        List<DcMdWellVo> menuTrees = buildMdWellVoTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */

    public List<DcMdWellVo> buildMdWellVoTree(List<DcMdWellVo> menus)
    {
        List<DcMdWellVo> returnList = new ArrayList<DcMdWellVo>();
        List<String> tempList = new ArrayList<String>();
        for (DcMdWellVo dept : menus)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<DcMdWellVo> iterator = menus.iterator(); iterator.hasNext();)
        {
            DcMdWellVo menu = (DcMdWellVo) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFnMdWellVo(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFnMdWellVo(List<DcMdWellVo> list, DcMdWellVo t)
    {
        // 得到子节点列表
        List<DcMdWellVo> childList = getChildMdWellVoList(list, t);
        t.setChildren(childList);
        for (DcMdWellVo tChild : childList)
        {
            if (hasChildMdWellVo(list, tChild))
            {
                recursionFnMdWellVo(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DcMdWellVo> getChildMdWellVoList(List<DcMdWellVo> list, DcMdWellVo t)
    {
        List<DcMdWellVo> tlist = new ArrayList<DcMdWellVo>();
        Iterator<DcMdWellVo> it = list.iterator();
        while (it.hasNext())
        {
            DcMdWellVo n = (DcMdWellVo) it.next();
            if ((n.getParentId()) .equals(t.getId()) )
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChildMdWellVo(List<DcMdWellVo> list, DcMdWellVo t)
    {
        return getChildMdWellVoList(list, t).size() > 0;
    }

    public List<DcReportRiskCheckNcrVo> listVO(List<DcReportRiskCheckNcrVo> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public DcReportRiskCheckNcrVo entityVO(DcReportRiskCheckNcrVo vo){
        if(StringUtils.isNotEmpty(vo.getResourceType())) {//
            vo.setResourceType(DictUtils.getDictLabel("resource_type", vo.getResourceType()));
        }
        if(StringUtils.isNotEmpty(vo.getFacilities())) {//
            vo.setFacilities(DictUtils.getDictLabel("check_facilities", vo.getFacilities()));
        }
        if(StringUtils.isNotEmpty(vo.getRiskCriticalityRanking())) {//
            vo.setRiskCriticalityRanking(DictUtils.getDictLabel("risk_criticality_ranking", vo.getRiskCriticalityRanking()));
        }
        if(StringUtils.isNotEmpty(vo.getNcStatus())) {//
            vo.setNcStatus(DictUtils.getDictLabel("nc_status", vo.getNcStatus()));
        }
        return vo;
    }
}
