package com.pcitc.legalAffairs.dbService.OrganizationWrokReward;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Organization.OrganizationWrokRewardBo;
import com.pcitc.legalAffairs.mapper.OrganizationWrokReward.OrganizationWrokRewardMapper;
import com.pcitc.legalAffairs.po.Organization.OrganizationWrokReward;
import com.pcitc.legalAffairs.vo.Organization.OrganizationWrokRewardVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IOrganizationWrokRewardServiceImpl extends ServiceImpl<OrganizationWrokRewardMapper, OrganizationWrokReward>
        implements IOrganizationWrokRewardService {
    @Autowired
    private OrganizationWrokRewardMapper organizationWrokRewardMapper;

    @Override
    public Result queryOrganizationWrokReward(Integer id) {
        Result result = new Result();
        OrganizationWrokReward organizationWrokReward = organizationWrokRewardMapper.selectById(id);
        OrganizationWrokRewardVo organizationWrokRewardVo = new OrganizationWrokRewardVo();
        if (organizationWrokReward != null) {
            BeanUtils.copyProperties(organizationWrokReward, organizationWrokRewardVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(organizationWrokRewardVo);
            result.setMsg("获取数据成功！");
        } else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    /**
     * 根据法律机构信息id查询获奖信息
     *
     * @param lawFirmID
     * @return
     */
    @Override
    public Result queryOrganizationWrokRewardList(Integer lawFirmID) {
        Result result = new Result();
        QueryWrapper<OrganizationWrokReward> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Law_Firm_ID", lawFirmID);
        List<OrganizationWrokReward> list = organizationWrokRewardMapper.selectList(queryWrapper);
        List<OrganizationWrokRewardVo> resultList = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(organizationWrokReward -> {
                OrganizationWrokRewardVo oorganizationWrokRewardVo = new OrganizationWrokRewardVo();
                BeanUtils.copyProperties(organizationWrokReward, oorganizationWrokRewardVo);
                resultList.add(oorganizationWrokRewardVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(resultList);
            result.setMsg("获取数据成功！");
        } else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    @Override
    public Result queryOrganizationWrokRewardPage(Integer pageIndex, Integer pageSize, Integer lawFirmID) {
        Page<OrganizationWrokReward> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<OrganizationWrokReward> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Law_Firm_ID", lawFirmID);
        IPage<OrganizationWrokReward> result = organizationWrokRewardMapper.selectPage(page,queryWrapper);
        return Result.data(result);
    }

    @Override
    public Result deleteOrganizationWrokReward(Integer id) {
        organizationWrokRewardMapper.deleteById(id);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result updateOrganizationWrokReward(OrganizationWrokRewardBo organizationWrokRewardBo) {
        if (organizationWrokRewardBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Integer id = organizationWrokRewardBo.getfId();
        if (this.getById(id) == null) {
            throw new BaseException("所需更新的获奖信息不存在", 500);
        }
        OrganizationWrokReward organizationWrokReward = new OrganizationWrokReward();
        BeanUtils.copyProperties(organizationWrokRewardBo, organizationWrokReward);
        organizationWrokRewardMapper.updateById(organizationWrokReward);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result saveOrganizationWrokReward(OrganizationWrokRewardBo organizationWrokRewardBo) {
        if (organizationWrokRewardBo == null) {
            throw new BaseException("所需参数为空", 500);
        }
        OrganizationWrokReward organizationWrokReward = new OrganizationWrokReward();
        BeanUtils.copyProperties(organizationWrokRewardBo, organizationWrokReward);
        organizationWrokRewardMapper.insert(organizationWrokReward);
        return Result.success(ResultCode.SUCCESS);
    }
}
