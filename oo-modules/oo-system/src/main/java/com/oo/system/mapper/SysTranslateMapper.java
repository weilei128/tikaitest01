package com.oo.system.mapper;

import java.util.List;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.oo.system.api.domain.SysTranslate;

/**
 * 多语言配置Mapper接口
 *
 * @author oo
 * @date 2023-08-09
 */
public interface SysTranslateMapper extends MppBaseMapper<SysTranslate>
{

    /**
     * 查询多语言配置列表
     *
     * @param sysTranslate 多语言配置
     * @return 多语言配置集合
     */
    public List<SysTranslate> selectSysTranslateList(SysTranslate sysTranslate);

    /**
     * 批量删除
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    public int removeByIds(Long[] logIds);

}
