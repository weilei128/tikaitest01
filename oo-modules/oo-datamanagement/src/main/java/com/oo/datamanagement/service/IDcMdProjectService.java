package com.oo.datamanagement.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.datamanagement.api.domain.DcMdProject;

/**
 * 地质单元--区块管理Service接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface IDcMdProjectService extends IService<DcMdProject>
{

    /**
     * 查询地质单元--区块管理列表
     *
     * @param dcMdProject 地质单元--区块管理
     * @return 地质单元--区块管理集合
     */
    public List<DcMdProject> selectDcMdProjectList(DcMdProject dcMdProject);

}
