package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportRiskCheckNcr;
import com.oo.reportforms.domain.vo.DcReportRiskCheckNcrVo;
import com.oo.system.api.vo.DcMdWellVo;
import com.oo.system.api.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

/**
 * 隐患排查：NCRMapper接口
 * 
 * @author oo
 * @date 2023-08-21
 */
public interface DcReportRiskCheckNcrMapper extends BaseMapper<DcReportRiskCheckNcr>
{

    /**
     * 查询隐患排查：NCR列表
     * 
     * @param wellId 隐患排查：NCR
     * @return 隐患排查：NCR集合
     */
    public List<DcReportRiskCheckNcrVo> selectList(@Param("wellId") String wellId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("ncStatus") String ncStatus);

    /**
     * 查询隐患排查：NCR列表
     *
     * @param
     * @return 隐患排查：NCR集合
     */
    public List<DcReportRiskCheckNcrVo> selectDcReportRiskCheckNcrList();
    /**
     * 构建组织树
     *
     */
    public List<DcMdWellVo> getWellName();

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);
    /**
     * 根据井名查询井号
     *
     * @param wellName
     * @return
     */
    DcMdWellVo getWellId(@Param("wellName") String wellName);
    List<SysDictData> getDicData();
}
