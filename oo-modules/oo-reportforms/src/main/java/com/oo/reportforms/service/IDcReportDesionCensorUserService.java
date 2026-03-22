package com.oo.reportforms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.reportforms.domain.DcReportDesionCensorUser;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单Service接口
 * 
 * @author oo
 * @date 2023-08-11
 */
public interface IDcReportDesionCensorUserService extends IService<DcReportDesionCensorUser>
{

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单列表
     * 
     * @param dcReportDesionCensorUser （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单集合
     */
    public List<DcReportDesionCensorUser> selectDcReportDesionCensorUserList(DcReportDesionCensorUser dcReportDesionCensorUser);

}
