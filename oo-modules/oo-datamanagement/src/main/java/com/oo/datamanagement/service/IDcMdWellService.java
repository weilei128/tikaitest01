package com.oo.datamanagement.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oo.datamanagement.api.domain.DcMdWell;

/**
 * 单井基础信息Service接口
 *
 * @author oo
 * @date 2023-09-13
 */
public interface IDcMdWellService extends IService<DcMdWell>
{

    /**
     * 查询单井基础信息列表
     *
     * @param dcMdWell 单井基础信息
     * @return 单井基础信息集合
     */
    public List<DcMdWell> selectDcMdWellList(DcMdWell dcMdWell);

}
