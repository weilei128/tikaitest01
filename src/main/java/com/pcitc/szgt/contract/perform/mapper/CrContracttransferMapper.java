package com.pcitc.szgt.contract.perform.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.perform.entity.CrContractchange;
import com.pcitc.szgt.contract.perform.entity.CrContracttransfer;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-04
 */
public interface CrContracttransferMapper extends BaseMapper<CrContracttransfer> {
    /*
     * 合同转让备案查询
     * */
    List<HashMap> selectSealContractTransfer(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> contractbasicQueryWrapper);
}
