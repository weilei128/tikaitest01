package com.pcitc.legalAffairs.dbService.OrganizationBasicInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoQueryBo;
import com.pcitc.legalAffairs.dbService.OrganizationStaff.IOrganizationStaffService;
import com.pcitc.legalAffairs.dbService.OrganizationWrokReward.IOrganizationWrokRewardService;
import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.mapper.OrganizationBasicInfo.OrganizationBasicInfoMapper;
import com.pcitc.legalAffairs.po.Organization.OrganizationBasicInfo;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.Organization.OrganizationBasicInfoVo;
import com.pcitc.legalAffairs.vo.Organization.OrganizationInfoAllVo;
import com.pcitc.legalAffairs.vo.Organization.OrganizationStaffVo;
import com.pcitc.legalAffairs.vo.Organization.OrganizationWrokRewardVo;
import com.pcitc.legalAffairs.vo.person.FwExcelOrganVo;
import com.pcitc.system.bo.SysOrganizationBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOrganizationBasicInfoServiceImpl extends ServiceImpl<OrganizationBasicInfoMapper, OrganizationBasicInfo>
        implements IOrganizationBasicInfoService {
    @Autowired
    private OrganizationBasicInfoMapper organizationBasicInfoMapper;
    @Autowired
    private IPrivilegeInfoService fwPrivilegeInfoService;
    /**
     * 法律机构职员服务层
     */
    @Autowired
    private IOrganizationStaffService staffService;
    /**
     * 法律工作获奖服务层
     */
    @Autowired
    private IOrganizationWrokRewardService wrokRewardService;
    @Autowired
    private UserOrgService userOrgService;

    @Override
    public Result queryOrganizationBasicInfo(Integer id) {
        Result result = new Result();
        OrganizationBasicInfo organizationBasicInfo = organizationBasicInfoMapper.selectById(id);
        OrganizationBasicInfoVo organizationBasicInfoVo = new OrganizationBasicInfoVo();
        if (organizationBasicInfo != null) {
            BeanUtils.copyProperties(organizationBasicInfo, organizationBasicInfoVo);
            result.setSuccess(true);
            result.setCode(200);
            result.setData(organizationBasicInfoVo);
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
    public Result queryOrganizationBasicInfoGlobalPage(OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo) {
        IPage<OrganizationBasicInfo> page = new Page<>(organizationBasicInfoQueryBo.getPageIndex(), organizationBasicInfoQueryBo.getPageSize());
        QueryWrapper<OrganizationBasicInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getFkOrgName())) {
            queryWrapper.like("fk_Org_Name", organizationBasicInfoQueryBo.getFkOrgName());
        }
        /**
         * 所属单位不为空，层级为全级时查询所属单位本级以及下级的法律机构信息
         */
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit())
                && (StringUtils.isEmpty(organizationBasicInfoQueryBo.getfLevel()) || organizationBasicInfoQueryBo.getfLevel() == 1)) {
            queryWrapper.like("f_Org_Node", organizationBasicInfoQueryBo.getfAffiliatedUnit());
        }
        /**
         * 所属单位不为空，层级为本级时查询所属单位本级法律机构信息
         */
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit()) && organizationBasicInfoQueryBo.getfLevel() == 0) {
            queryWrapper.eq("f_Affiliated_Unit", organizationBasicInfoQueryBo.getfAffiliatedUnit());
        }
        /**
         * 所属单位为空时，层级为全级时查询当前登录者信息所属单位本级以及下级的法律机构信息
         */
        /**
         * 所属单位为空时，层级为本级时查询当前登录者信息所属单位本级的法律机构信息
         */
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfOrgTypeName())) {
            queryWrapper.eq("f_Org_Type_Name", organizationBasicInfoQueryBo.getfOrgTypeName());
        }
        if (organizationBasicInfoQueryBo.getfSetStatus() != null) {
            queryWrapper.eq("f_Set_Status", organizationBasicInfoQueryBo.getfSetStatus());
        }
        queryWrapper.in("f_Org_Node", userOrgService.getLoginUserChildren());
        //当前登录人 法务查询权限表 组织机构id   2020年9月29日
//        List<Long> longs = fwPrivilegeInfoService.queryOrgIdByUserId(Long.valueOf(UserUtils.getUserInfo().getfId()));
//        if(null!=longs&&longs.size()>0){
//            queryWrapper.or().in("fk_Org_ID",longs);
//        }
        IPage<OrganizationBasicInfo> result = organizationBasicInfoMapper.selectPage(page, queryWrapper);
        List<OrganizationBasicInfo> basicInfoList = result.getRecords();
        IPage<OrganizationInfoAllVo> infoAllVoIPage = new Page<>();
        infoAllVoIPage.setCurrent(result.getCurrent());
        infoAllVoIPage.setPages(result.getPages());
        infoAllVoIPage.setSize(result.getSize());
        infoAllVoIPage.setTotal(result.getTotal());
        if (CollectionUtils.isNotEmpty(basicInfoList)) {
            List<OrganizationInfoAllVo> infoAllVos = new ArrayList<>(basicInfoList.size());
            basicInfoList.forEach(basicInfo -> {
                OrganizationInfoAllVo infoAllVo = getInfoAll(basicInfo);
                infoAllVos.add(infoAllVo);
            });
            infoAllVoIPage.setRecords(infoAllVos);
        } else {
            infoAllVoIPage.setRecords(new ArrayList<>());
        }
        return Result.data(infoAllVoIPage);
    }

    private OrganizationInfoAllVo getInfoAll(OrganizationBasicInfo basicInfo) {
        OrganizationInfoAllVo infoAllVo = new OrganizationInfoAllVo();
        BeanUtils.copyProperties(basicInfo, infoAllVo);
        Result resultStaff = staffService.queryOrganizationStaffList(basicInfo.getfId());
        List<OrganizationStaffVo> staffList = (List<OrganizationStaffVo>) resultStaff.getData();
        infoAllVo.setStaffVoList(staffList);
        Result resultReward = wrokRewardService.queryOrganizationWrokRewardList(basicInfo.getfId());
        List<OrganizationWrokRewardVo> wrokRewardVoList = (List<OrganizationWrokRewardVo>) resultReward.getData();
        infoAllVo.setWrokRewardVoList(wrokRewardVoList);
        return infoAllVo;
    }

    @Override
    public Result queryOrganizationBasicInfoManagePage(OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo) {
        //获取组织机构List

        List<SysOrganizationBo> boList = userOrgService.getLoginUserChildrenBo();
        QueryWrapper<OrganizationBasicInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getFkOrgName())) {
            queryWrapper.like("fk_Org_Name", organizationBasicInfoQueryBo.getFkOrgName());
        }
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit())) {
            queryWrapper.like("f_Affiliated_Unit", organizationBasicInfoQueryBo.getfAffiliatedUnit());
        }
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfOrgTypeName())) {
            queryWrapper.eq("f_Org_Type_Name", organizationBasicInfoQueryBo.getfOrgTypeName());
        }
        if (organizationBasicInfoQueryBo.getfSetStatus() != null) {
            queryWrapper.eq("f_Set_Status", organizationBasicInfoQueryBo.getfSetStatus());
        }
        IPage<OrganizationBasicInfo> page = new Page<>(organizationBasicInfoQueryBo.getPageIndex(), organizationBasicInfoQueryBo.getPageSize());

        IPage<OrganizationInfoAllVo> infoAllVoIPage = new Page<>();

        List<OrganizationBasicInfo> basicInfoList = organizationBasicInfoMapper.selectList(queryWrapper);
        List<SysOrganizationBo> orgList = boList.stream().filter(entity -> entity.getfType() > 0).collect(Collectors.toList());
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit())) {
            orgList = orgList.stream().filter(entity -> (entity.getfName().contains(organizationBasicInfoQueryBo.getfAffiliatedUnit()))).collect(Collectors.toList());
        }
        List<OrganizationInfoAllVo> infoAllVos = new ArrayList<>(basicInfoList.size());

        for (SysOrganizationBo bo : orgList) {
            boolean isIn = false;
            for (OrganizationBasicInfo basicInfo : basicInfoList) {
                OrganizationInfoAllVo infoAllVo = null;
                if ((bo.getfId() + "").equals(basicInfo.getFkOrgId())) {
                    infoAllVo = getInfoAll(basicInfo);
                    infoAllVos.add(infoAllVo);
                    isIn = true;
                    continue;
                }
            }
            if (!isIn) {
                OrganizationInfoAllVo infoAllVo = new OrganizationInfoAllVo();
                infoAllVo.setfAffiliatedUnit(bo.getfName());
                infoAllVo.setFkOrgId(bo.getfId() + "");
                infoAllVos.add(infoAllVo);
            }
        }
        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getFkOrgName())) {
            infoAllVos = infoAllVos.stream().filter(entity -> (!StringUtils.isEmpty(entity.getFkOrgName()) && entity.getFkOrgName().contains(organizationBasicInfoQueryBo.getFkOrgName()))).collect(Collectors.toList());
        }

        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfOrgTypeName())) {
            infoAllVos = infoAllVos.stream().filter(entity -> (!StringUtils.isEmpty(entity.getfOrgTypeName()) && entity.getfOrgTypeName().contains(organizationBasicInfoQueryBo.getfOrgTypeName()))).collect(Collectors.toList());
        }
        if (organizationBasicInfoQueryBo.getfSetStatus() != null) {
            infoAllVos = infoAllVos.stream().filter(entity -> (entity.getfSetStatus() == organizationBasicInfoQueryBo.getfSetStatus())).collect(Collectors.toList());

        }
        infoAllVoIPage.setRecords(infoAllVos);
        long pages = infoAllVos.size() / organizationBasicInfoQueryBo.getPageSize();
        if (infoAllVos.size() % organizationBasicInfoQueryBo.getPageSize() != 0) {
            pages++;
        }
        infoAllVoIPage.setCurrent(organizationBasicInfoQueryBo.getPageIndex());
        infoAllVoIPage.setPages(pages);
        infoAllVoIPage.setSize(organizationBasicInfoQueryBo.getPageSize());
        infoAllVoIPage.setTotal(infoAllVos.size());

//        if (StringUtils.isEmpty(organizationBasicInfoQueryBo.getFkOrgName()) && (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit()) || !StringUtils.isEmpty(organizationBasicInfoQueryBo.getfOrgTypeName()) || organizationBasicInfoQueryBo.getfSetStatus() != null )) {
//            IPage<OrganizationBasicInfo> result = organizationBasicInfoMapper.selectPage(page, queryWrapper);
//            List<OrganizationBasicInfo> basicInfoList = result.getRecords();
//            infoAllVoIPage.setCurrent(result.getCurrent());
//            infoAllVoIPage.setPages(result.getPages());
//            infoAllVoIPage.setSize(result.getSize());
//            infoAllVoIPage.setTotal(result.getTotal());
//            if (CollectionUtils.isNotEmpty(basicInfoList)) {
//                List<OrganizationInfoAllVo> infoAllVos = new ArrayList<>(basicInfoList.size());
//                basicInfoList.forEach(basicInfo -> {
//                    OrganizationInfoAllVo infoAllVo = getInfoAll(basicInfo);
//                    infoAllVos.add(infoAllVo);
//                });
//                infoAllVoIPage.setRecords(infoAllVos);
//            } else {
//                infoAllVoIPage.setRecords(new ArrayList<>());
//            }
//        } else {
//            //如果前只有第一个条件。以第一个条件为主。
//            List<SysOrganizationBo> orgList = new ArrayList<>();
//            if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getFkOrgName())) {
//                orgList = boList.stream().filter(entity -> entity.getfName().indexOf(organizationBasicInfoQueryBo.getFkOrgName()) != -1).collect(Collectors.toList());
//            } else {
//                orgList = boList;
//            }
//        }


//        //如果后三个条件存在。那么就以后面三个为主。
//        if (!StringUtils.isEmpty(organizationBasicInfoQueryBo.getfAffiliatedUnit()) || !StringUtils.isEmpty(organizationBasicInfoQueryBo.getfOrgTypeName()) || organizationBasicInfoQueryBo.getfSetStatus() != null) {
////     应该是不用
////            for (OrganizationBasicInfo basicInfo : basicInfoList) {
////
////            }
//        } else {
//            for (SysOrganizationBo bo : orgList) {
//
//            }
//        }


//        IPage<OrganizationBasicInfo> page = new Page<>(organizationBasicInfoQueryBo.getPageIndex(), organizationBasicInfoQueryBo.getPageSize());
//

        return Result.data(infoAllVoIPage);
    }

    @Override
    public Result deleteOrganizationBasicInfo(Integer id) {
        organizationBasicInfoMapper.deleteById(id);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result updateOrganizationBasicInfo(OrganizationBasicInfoBo organizationBasicInfoBo) {
        if (organizationBasicInfoBo == null) {
            throw new BaseException("传入参数为空", 500);
        }
        Integer id = organizationBasicInfoBo.getfId();
        if (this.getById(id) == null) {
            throw new BaseException("所要更新的法律机构信息不存在", 500);
        }
        OrganizationBasicInfo organizationBasicInfo = new OrganizationBasicInfo();
        BeanUtils.copyProperties(organizationBasicInfoBo, organizationBasicInfo);
        organizationBasicInfoMapper.updateById(organizationBasicInfo);
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result saveOrganizationBasicInfo(OrganizationBasicInfoBo organizationBasicInfoBo) {
        Result result = new Result();
        if (organizationBasicInfoBo == null) {
            throw new BaseException("所需参数为空", 500);
        }
        OrganizationBasicInfo organizationBasicInfo = new OrganizationBasicInfo();
        BeanUtils.copyProperties(organizationBasicInfoBo, organizationBasicInfo);
        organizationBasicInfo.setfIsdel(0);
        organizationBasicInfoMapper.insert(organizationBasicInfo);
        result.setData(organizationBasicInfo.getfId());
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加数据成功！");
        return result;
    }

    @Override
    public List<FwExcelOrganVo> exportExcel() {

        List<SysOrganizationBo> boList = userOrgService.getLoginUserChildrenBo();
        QueryWrapper<OrganizationBasicInfo> queryWrapper = new QueryWrapper<>();
        List<OrganizationBasicInfo> basicInfoList = organizationBasicInfoMapper.selectList(queryWrapper);
        List<SysOrganizationBo> orgList = boList.stream().filter(entity -> entity.getfType() > 0).collect(Collectors.toList());
        List<OrganizationInfoAllVo> infoAllVos = new ArrayList<>(basicInfoList.size());

        for (SysOrganizationBo bo : orgList) {
            boolean isIn = false;
            for (OrganizationBasicInfo basicInfo : basicInfoList) {
                OrganizationInfoAllVo infoAllVo = null;
                if ((bo.getfId() + "").equals(basicInfo.getFkOrgId())) {
                    infoAllVo = getInfoAll(basicInfo);
                    infoAllVos.add(infoAllVo);
                    isIn = true;
                    continue;
                }
            }
            if (!isIn) {
                OrganizationInfoAllVo infoAllVo = new OrganizationInfoAllVo();
                infoAllVo.setfAffiliatedUnit(bo.getfName());
                infoAllVo.setFkOrgId(bo.getfId() + "");
                infoAllVos.add(infoAllVo);
            }
        }
        List<FwExcelOrganVo> organVos = new ArrayList<>();
        for (OrganizationInfoAllVo vo : infoAllVos) {
            FwExcelOrganVo organVo = new FwExcelOrganVo();
            organVo.setfAffiliatedUnit(vo.getfAffiliatedUnit());
            organVo.setFkOrgName(vo.getFkOrgName());
            organVo.setfOrgTypeName(vo.getfOrgTypeName());
            organVo.setfSetStatus(vo.getfSetStatus() == null ? "" : vo.getfSetStatus() == 1 ? "保存" : "草稿");
            organVos.add(organVo);
        }
        return organVos;
    }

}
