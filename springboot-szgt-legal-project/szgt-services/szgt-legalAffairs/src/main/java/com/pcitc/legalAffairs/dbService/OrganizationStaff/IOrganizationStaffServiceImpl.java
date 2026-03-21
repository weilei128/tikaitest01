package com.pcitc.legalAffairs.dbService.OrganizationStaff;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Organization.OrganizationStaffBo;
import com.pcitc.legalAffairs.mapper.OrganizationStaff.OrganizationStaffMapper;
import com.pcitc.legalAffairs.po.Organization.OrganizationStaff;
import com.pcitc.legalAffairs.vo.Organization.OrganizationStaffVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IOrganizationStaffServiceImpl extends ServiceImpl<OrganizationStaffMapper,OrganizationStaff>
        implements  IOrganizationStaffService{
    @Autowired
    private OrganizationStaffMapper organizationStaffMapper;
    @Override
    public Result queryOrganizationStaff(Integer id) {
        Result result = new Result();
        OrganizationStaff organizationStaff = organizationStaffMapper.selectById(id);
        OrganizationStaffVo organizationStaffVo = new OrganizationStaffVo();
        if (organizationStaff != null){
            BeanUtils.copyProperties(organizationStaff,organizationStaffVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(organizationStaffVo);
            result.setMsg("获取数据成功！");
        }else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    /**
     * 根据法律机构信息id查询法律人员列表
     *
     * @param lawFirmID
     * @return
     */
    @Override
    public Result queryOrganizationStaffList(Integer lawFirmID) {
        Result result = new Result();
        QueryWrapper<OrganizationStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Law_Firm_ID", lawFirmID);
        List<OrganizationStaff> list = organizationStaffMapper.selectList(queryWrapper);
        List<OrganizationStaffVo> resultList = new ArrayList<>(list.size());
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(organizationStaff -> {
                OrganizationStaffVo organizationStaffVo = new OrganizationStaffVo();
                BeanUtils.copyProperties(organizationStaff,organizationStaffVo);
                resultList.add(organizationStaffVo);
            });
            result.setSuccess(true);
            result.setCode(200);
            result.setData(resultList);
            result.setMsg("获取数据成功！");
        }else {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(null);
            result.setMsg("查询数据为空！");
        }
        return result;
    }

    @Override
    public Result queryOrganizationStaffPage(Integer pageIndex, Integer pageSize, Integer lawFirmID) {
        Page<OrganizationStaff> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<OrganizationStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_Law_Firm_ID", lawFirmID);
        IPage<OrganizationStaff> result = organizationStaffMapper.selectPage(page,queryWrapper);
        return Result.data(result);
    }

    @Override
    public Result deleteOrganizationStaff(Integer id) {
        organizationStaffMapper.deleteById(id);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result deleteOrganizationStaffBatch(List<Integer> ids) {
        organizationStaffMapper.deleteBatchIds(ids);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result updateOrganizationStaff(OrganizationStaffBo organizationStaffBo) {
        if (organizationStaffBo == null){
            throw new BaseException("传入参数为空", 500);
        }
        Integer id = organizationStaffBo.getfId();
        if (this.getById(id) == null){
            throw new BaseException("所要更新的法律人员信息不存在",500);
        }
        OrganizationStaff organizationStaff = new OrganizationStaff();
        BeanUtils.copyProperties(organizationStaffBo,organizationStaff);
        organizationStaffMapper.updateById(organizationStaff);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result saveOrganizationStaff(OrganizationStaffBo organizationStaffBo) {
        if (organizationStaffBo == null) {
            throw new BaseException("所需参数为空",500);
        }
        OrganizationStaff organizationStaff = new OrganizationStaff();
        BeanUtils.copyProperties(organizationStaffBo,organizationStaff);
        organizationStaffMapper.insert(organizationStaff);
        return Result.success(ResultCode.SUCCESS);
    }
}
