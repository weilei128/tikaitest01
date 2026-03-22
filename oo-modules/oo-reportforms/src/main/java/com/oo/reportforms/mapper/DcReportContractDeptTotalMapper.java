package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportContractDeptTotal;
import org.apache.ibatis.annotations.Param;

/**
 * 工程技术分中心合同台账及执行情况记录-部门合同统计Mapper接口
 * 
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportContractDeptTotalMapper extends BaseMapper<DcReportContractDeptTotal>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-部门合同统计列表
     * 
     * @param contractCode 工程技术分中心合同台账及执行情况记录-部门合同统计
     * @return 工程技术分中心合同台账及执行情况记录-部门合同统计集合
     */
    public List<DcReportContractDeptTotal> selectList(@Param("contractCode") String contractCode, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    public List<DcReportContractDeptTotal> selectDcReportContractDeptTotalList(DcReportContractDeptTotal dcReportContractDeptTotal);

    /**
     * 软删除部门合同统计列表
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int DeleteContractDeptTotalByIds(@Param("ids") String[] ids);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
