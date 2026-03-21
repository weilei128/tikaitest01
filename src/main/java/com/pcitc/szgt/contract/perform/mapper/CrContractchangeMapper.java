package com.pcitc.szgt.contract.perform.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.model.GetContractChangeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-02
 */
public interface CrContractchangeMapper extends BaseMapper<CrContractchange> {
    /*
     * 合同变更备案查询
     * */
    List<HashMap> selectSealContractChange(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> contractbasicQueryWrapper);

    List<GetContractChangeVo> getContractChange(String ContractID);


}
