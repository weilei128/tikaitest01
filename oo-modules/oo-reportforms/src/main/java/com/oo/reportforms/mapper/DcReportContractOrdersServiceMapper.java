package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportContractOrdersService;
import org.apache.ibatis.annotations.Param;

/**
 * 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））Mapper接口
 *
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportContractOrdersServiceMapper extends BaseMapper<DcReportContractOrdersService>
{

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param contractCode 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））集合
     */
    public List<DcReportContractOrdersService> selectList(@Param("contractCode") String contractCode, @Param("year") String year);

    /**
     * 查询工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））列表
     *
     * @param dcReportContractOrdersService 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））
     * @return 工程技术分中心合同台账及执行情况记录-工作订单统计（海油国际与海能发等公司服务订单（2022年度））集合
     */
    public List<DcReportContractOrdersService> selectDcReportContractOrdersServiceList(DcReportContractOrdersService dcReportContractOrdersService);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
}
