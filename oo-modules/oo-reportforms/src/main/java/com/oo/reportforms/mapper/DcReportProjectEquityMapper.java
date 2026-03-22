package com.oo.reportforms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.reportforms.domain.DcReportProjectEquity;
import com.oo.reportforms.domain.vo.DcReportProjectEquityVo;
import org.apache.ibatis.annotations.Param;

/**
 * 国际公司项目权益情况Mapper接口
 * 
 * @author oo
 * @date 2023-09-07
 */
public interface DcReportProjectEquityMapper extends BaseMapper<DcReportProjectEquity>
{
    /**
     * 根据区块查询国际公司项目权益情况列表
     *
     * @param blockId 区块
     * @return 国际公司项目权益情况集合
     */
    public List<DcReportProjectEquityVo> selectList(@Param("blockId") String blockId);

    public List<DcReportProjectEquityVo> selectScreenList(@Param("blockId") String blockId);
    /**
     * 查询国际公司项目权益情况列表
     * 
     * @param dcReportProjectEquity 国际公司项目权益情况
     * @return 国际公司项目权益情况集合
     */
    public List<DcReportProjectEquity> selectDcReportProjectEquityList(DcReportProjectEquity dcReportProjectEquity);
    /**
     * 根据区块名查询区块id
     *
     * @param blockName
     * @return
     */
    DcMdProject getBlockId(@Param("blockName") String blockName);
    boolean deleteItemsByIds(String ids);
}
