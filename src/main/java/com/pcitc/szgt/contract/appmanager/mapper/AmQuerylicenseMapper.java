package com.pcitc.szgt.contract.appmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.appmanager.entity.AmQuerylicense;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseQueryBo;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseResultBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-01
 */
public interface AmQuerylicenseMapper extends BaseMapper<AmQuerylicense> {

    List<AmQueryLicenseResultBo> queryAMQueryLicense(Page<String> page, @Param("queryBo") AmQueryLicenseQueryBo queryBo);

}
