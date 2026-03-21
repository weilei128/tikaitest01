package com.pcitc.szgt.contract.offeree.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.szgt.contract.finality.entity.CrContractcase;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.model.OffereeContractVo;
import com.pcitc.szgt.contract.perform.entity.CrContractend;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface FfOffereeinfoMapper extends BaseMapper<FfOffereeinfo> {
    /*
     * 异常履约
     * */
    List<OffereeContractVo> selectUnusualPerform(@Param("ew") QueryWrapper<FfOffereeinfo> ffOffereeinfoQueryWrapper);

    /*
     * 发案情况
     * */
    List<OffereeContractVo> selectCase(@Param("ew") QueryWrapper<FfOffereeinfo> ffOffereeinfoQueryWrapper);
}
