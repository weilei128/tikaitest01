package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportContractOrdersWork;
import org.apache.ibatis.annotations.Param;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）Mapper接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportContractOrdersWorkMapper extends BaseMapper<DcReportContractOrdersWork>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param ordersNum 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）集合
     */
    public List<DcReportContractOrdersWork> selectList(@Param("ordersNum") String ordersNum, @Param("year") String year);

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）列表
     *
     * @param dcReportContractOrdersWork 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海外公司与海油国际工作订单）集合
     */
    public List<DcReportContractOrdersWork> selectDcReportContractOrdersWorkList(DcReportContractOrdersWork dcReportContractOrdersWork);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
