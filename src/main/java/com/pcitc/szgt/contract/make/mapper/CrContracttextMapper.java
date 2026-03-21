package com.pcitc.szgt.contract.make.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.make.entity.CrContracttext;
import com.pcitc.szgt.contract.make.modelEx.StdTextCnt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-27
 */
public interface CrContracttextMapper extends BaseMapper<CrContracttext> {


    List<StdTextCnt> queryStdTextCnt(Page<StdTextCnt> page,
                                      @Param("ruleSerialNum") String ruleSerialNum,
                                      @Param("contractNum") String contractNum,
                                      @Param("contractName") String contractName,
                                      @Param("mainDeptID")String mainDeptID);

    List<StdTextCnt> queryAllTextCnt(@Param("ruleSerialNum") String ruleSerialNum,
                                     @Param("contractNum") String contractNum,
                                     @Param("contractName") String contractName,
                                     @Param("mainDeptID")String mainDeptID);

}
