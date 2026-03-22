package com.oo.datamanagement.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.oo.datamanagement.mapper.DcMdOrganizationMapper;
import com.oo.system.api.domain.DcMdOrganization;
import com.oo.datamanagement.service.IDcMdOrganizationService;

/**
 * 组织机构层级关系--项目管理Service业务层处理
 *
 * @author oo
 * @date 2023-09-13
 */
@Service
public class DcMdOrganizationServiceImpl extends ServiceImpl<DcMdOrganizationMapper, DcMdOrganization> implements IDcMdOrganizationService
{

    /**
     * 查询组织机构层级关系--项目管理列表
     *
     * @param dcMdOrganization 组织机构层级关系--项目管理
     * @return 组织机构层级关系--项目管理
     */
    @Override
    public List<DcMdOrganization> selectDcMdOrganizationList(DcMdOrganization dcMdOrganization)
    {
        return baseMapper.selectDcMdOrganizationList(dcMdOrganization);
    }

}
