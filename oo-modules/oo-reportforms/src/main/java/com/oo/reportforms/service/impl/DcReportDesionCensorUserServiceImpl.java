package com.oo.reportforms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.reportforms.mapper.DcReportDesionCensorUserMapper;
import com.oo.reportforms.domain.DcReportDesionCensorUser;
import com.oo.reportforms.service.IDcReportDesionCensorUserService;

/**
 * （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单Service业务层处理
 * 
 * @author oo
 * @date 2023-08-11
 */
@Service
public class DcReportDesionCensorUserServiceImpl extends ServiceImpl<DcReportDesionCensorUserMapper, DcReportDesionCensorUser> implements IDcReportDesionCensorUserService
{

    /**
     * 查询（技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单列表
     * 
     * @param dcReportDesionCensorUser （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单
     * @return （技术管理岗）前期研究及设计审查会汇总-设计审查汇总   专家名称清单
     */
    @Override
    public List<DcReportDesionCensorUser> selectDcReportDesionCensorUserList(DcReportDesionCensorUser dcReportDesionCensorUser)
    {
        return baseMapper.selectDcReportDesionCensorUserList(dcReportDesionCensorUser);
    }

}
