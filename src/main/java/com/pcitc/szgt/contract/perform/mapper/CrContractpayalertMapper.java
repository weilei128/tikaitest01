package com.pcitc.szgt.contract.perform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.perform.entity.CrContractpayalert;
import com.pcitc.szgt.contract.perform.model.PayAlertMsgVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-18
 */
public interface CrContractpayalertMapper extends BaseMapper<CrContractpayalert> {

    IPage<PayAlertMsgVo> queryPayAlertMsg(Page<PayAlertMsgVo> page,
                                          @Param("createuser") String createuser,
                                          @Param("nowdate") LocalDate nowdate);

    Long queryPayAlertMsgCnt(@Param("createuser") String createuser,
                             @Param("nowdate") LocalDate nowdate);
}
