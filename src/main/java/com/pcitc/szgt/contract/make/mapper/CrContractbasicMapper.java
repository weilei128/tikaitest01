package com.pcitc.szgt.contract.make.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.perform.model.ExportContractVo;
import com.pcitc.szgt.contract.perform.model.FinalAlertMsgVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-20
 */
public interface CrContractbasicMapper extends BaseMapper<CrContractbasic> {
    /*
     * 获取最大流水号
     * */
    HashMap getMaxRuleSerialNum(@Param("createDate") String createDate);

    /*
     * 获取框架合同
     * */
    List<CrContractbasic> getFrameContract(IPage<CrContractbasic> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 获取框架合同
     * */
    List<CrContractbasic> getMasterContract(IPage<CrContractbasic> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 待/已发案合同查询
     * */
    List<HashMap> queryContractIncidence(IPage<CrContractbasic> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 合同订立备案查询
     * */
    List<HashMap> getSealContract(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 签约依据关联合同查询
     * */
    List<HashMap> queryContractAccord(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 项目关联合同查询
     * */
    List<HashMap> queryContractProject(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /*
     * 合同查询统计
     * */
    List<HashMap> queryContract(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);
    /*
     * 合同查询导出
     * */
    List<ExportContractVo> queryContractExport(@Param("ew") QueryWrapper<CrContractbasic> queryWrapper);

    /**
     * 分页查询终结合同警告信息
     * @param page
     * @param createuser
     * @param finaldate
     * @return
     */
    IPage<FinalAlertMsgVo> queryFinalAlertMsg(Page<FinalAlertMsgVo> page, @Param("createuser") String createuser, @Param("finaldate")LocalDateTime finaldate);

    /**
     * 	终结合同警报数量
     * @param createuser
     * @param finaldate
     * @return
     */
    Long queryFinalAlertMsgCnt(@Param("createuser") String createuser, @Param("finaldate")LocalDateTime finaldate);

    /**
     * 	倒签合同
     * @return
     */
    List<HashMap> queryContractPayment(IPage<HashMap> page, @Param("ew") QueryWrapper<CrContractbasic> queryWrapper);


    Integer selectSeqFunc(@Param("contractNum")String contractNum);
}
