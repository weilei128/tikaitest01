package com.oo.reportforms.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportDesionCensorUser;
import com.oo.reportforms.domain.vo.ScreenExaminationVo;
import org.apache.ibatis.annotations.Param;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单Mapper接口
 *
 * @author oo
 * @date 2023-08-11
 */
public interface DcReportDesionCensorUserMapper extends BaseMapper<DcReportDesionCensorUser>
{

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单列表
     *
     * @param dcReportDesionCensorUser （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单集合
     */
    public List<DcReportDesionCensorUser> selectDcReportDesionCensorUserList(DcReportDesionCensorUser dcReportDesionCensorUser);

    /**
     * 查询描述：设计审查会专家参与次数
     *
     * @param  //描述：设计审查会专家参与次数
     * @return 设计审查会专家参与次数
     */
    public List<ScreenExaminationVo> screenExamination(@Param("keywords") String keywords,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    /**
     * 查询描述：设计审查会专家参与次数
     *
     * @param  //描述：设计审查会专家参与次数
     * @return 设计审查会专家参与次数
     */
    public List<ScreenExaminationVo> screenExamination1(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    public List<ScreenExaminationVo> screenExamination2(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
    public List<ScreenExaminationVo> screenExamination3();

    /**
     * 汇总每年的钻完井设计审查次数
     */
    public List<ScreenExaminationVo> summaryExamination(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

}
