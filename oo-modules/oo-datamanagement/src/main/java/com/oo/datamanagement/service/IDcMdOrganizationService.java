package com.oo.datamanagement.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.system.api.domain.DcMdOrganization;

/**
 * 组织机构层级关系--项目管理Service接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface IDcMdOrganizationService extends IService<DcMdOrganization>
{

    /**
     * 查询组织机构层级关系--项目管理列表
     *
     * @param dcMdOrganization 组织机构层级关系--项目管理
     * @return 组织机构层级关系--项目管理集合
     */
    public List<DcMdOrganization> selectDcMdOrganizationList(DcMdOrganization dcMdOrganization);

}
