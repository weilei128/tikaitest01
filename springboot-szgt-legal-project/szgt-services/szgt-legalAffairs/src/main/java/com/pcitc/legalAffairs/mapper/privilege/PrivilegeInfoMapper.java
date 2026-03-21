package com.pcitc.legalAffairs.mapper.privilege;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.legalAffairs.po.fwPrivilegeInfo.FwPrivilegeInfo;
import com.pcitc.legalAffairs.vo.fwPrivilegeInfo.PrivilegeUserQueryVo;
import com.pcitc.legalAffairs.vo.fwPrivilegeInfo.PrivilegeUserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 法务查询权限表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-29
 */
public interface PrivilegeInfoMapper extends BaseMapper<FwPrivilegeInfo> {

	IPage<PrivilegeUserVo> pageUsers(Page<PrivilegeUserVo> page, @Param(value = "param") PrivilegeUserQueryVo param);

}
