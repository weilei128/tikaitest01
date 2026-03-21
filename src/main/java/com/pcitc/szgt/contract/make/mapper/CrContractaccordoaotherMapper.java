package com.pcitc.szgt.contract.make.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口（签约依据）
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
public interface CrContractaccordoaotherMapper extends BaseMapper<CrContractaccordoaother> {
    /*
     * 签约依据多表查询
     * */
    List<HashMap> selectAccord(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractaccordoaother> queryWrapper);
}
