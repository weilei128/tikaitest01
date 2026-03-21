package com.pcitc.legalAffairs.service.person;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.common.exception.CustomException;
import com.pcitc.legalAffairs.bo.person.*;
import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.dbService.person.IPersonHonorService;
import com.pcitc.legalAffairs.dbService.person.IPersonInfoService;
import com.pcitc.legalAffairs.dbService.person.IPersonQualificationService;
import com.pcitc.legalAffairs.dbService.person.IPersonResumeService;
import com.pcitc.legalAffairs.po.person.FwPersonHonor;
import com.pcitc.legalAffairs.po.person.FwPersonInfo;
import com.pcitc.legalAffairs.po.person.FwPersonQualification;
import com.pcitc.legalAffairs.po.person.FwPersonResume;
import com.pcitc.legalAffairs.service.dict.DictionaryService;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.person.FwExcelPersonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 法律人员管理Service
 *
 * @author meihongli
 */
@Service
@Slf4j
public class PersonInfoService {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private IPersonInfoService personInfoService;
    @Autowired
    private IPersonHonorService personHonorService;
    @Autowired
    private IPersonQualificationService personQualificationService;
    @Autowired
    private IPersonResumeService personResumeService;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private IPrivilegeInfoService privilegeInfoService;

    /**
     * 新增法务人员及其个人荣誉和资质信息
     *
     * @param personInfo BO
     * @return
     * @throws ParseException
     */
    public Long savePersonInfo(PersonInfoBo personInfo) throws ParseException {
        // 保存主表
        FwPersonInfo entity = personInfoBoToEntity(personInfo);
        personInfoService.save(entity);
        Long id = entity.getfId();
        String personName = entity.getfName();
        // 保存个人荣誉表
        List<FwPersonHonor> honors = personHonorBoToEntityList(personInfo.getHonors(), id, personName);
        if (honors != null) {
            personHonorService.saveBatch(honors);
        }
        // 保存资质表
        List<FwPersonQualification> qualifications = personQualificationBoToEntityList(personInfo.getQualifications(), id, personName);
        if (qualifications != null) {
            personQualificationService.saveBatch(qualifications);
        }
        // 保存简历表
        List<FwPersonResume> resumes = personResumeBosToEntities(personInfo.getResumes(), id, personName);
        if (resumes != null) {
            personResumeService.saveBatch(resumes);
        }
        return id;
    }

    /**
     * 删除法务人员及其个人荣誉和资质信息
     *
     * @param id PersonInfo.fid
     * @return success
     */
    public boolean removePersonInfoById(String id) {
        FwPersonInfo person = personInfoService.getById(id);
        if (person == null) {
            throw new BaseException("该人物不存在或者已经删除! ", 500);
        }
        boolean result = personInfoService.removeById(id);
        if (result == false) {
            return result;
        }
        LambdaQueryWrapper<FwPersonHonor> honorQuery = new LambdaQueryWrapper<>();
        honorQuery.eq(FwPersonHonor::getFkPersonId, id);
        personHonorService.remove(honorQuery);
        LambdaQueryWrapper<FwPersonQualification> qualificationQuery = new LambdaQueryWrapper<>();
        qualificationQuery.eq(FwPersonQualification::getFkPersonId, id);
        personQualificationService.remove(qualificationQuery);
        LambdaQueryWrapper<FwPersonResume> resumeQuery = new LambdaQueryWrapper<>();
        resumeQuery.eq(FwPersonResume::getFkPersonId, id);
        personResumeService.remove(resumeQuery);
        return result;
    }

    public void removeBatch(List<String> ids) {
        personInfoService.removeByIds(ids);
        personHonorService.remove(personHonorService.lambdaQueryWrapper().in(FwPersonHonor::getFkPersonId, ids));
        personQualificationService.remove(personQualificationService.lambdaQueryWrapper().in(FwPersonQualification::getFkPersonId, ids));
        personResumeService.remove(personResumeService.lambdaQueryWrapper().in(FwPersonResume::getFkPersonId, ids));
    }

    /**
     * 更新法务人员及其个人荣誉和资质信息, 子表信息使用全部删除再添加的方法
     *
     * @param personInfo
     * @return
     */
    public Long updatePersonInfo(PersonInfoBo personInfo) {
        FwPersonInfo person = personInfoService.getById(personInfo.getFId());
        if (person == null) {
            throw new BaseException("该人物不存在或者已经删除! ", 500);
        }
        // 保存主表
        FwPersonInfo entity = personInfoBoToEntity(personInfo);
        personInfoService.updateById(entity);
        Long id = entity.getfId();
        String personName = entity.getfName();

        // 删除旧有子表数据后增加新的数据

        LambdaQueryWrapper<FwPersonHonor> honorQuery = new LambdaQueryWrapper<>();
        honorQuery.eq(FwPersonHonor::getFkPersonId, id);
        personHonorService.remove(honorQuery);
        LambdaQueryWrapper<FwPersonQualification> qualificationQuery = new LambdaQueryWrapper<>();
        qualificationQuery.eq(FwPersonQualification::getFkPersonId, id);
        personQualificationService.remove(qualificationQuery);
        LambdaQueryWrapper<FwPersonResume> resumeQuery = new LambdaQueryWrapper<>();
        resumeQuery.eq(FwPersonResume::getFkPersonId, id);
        personResumeService.remove(resumeQuery);

        // 保存新的个人荣誉表
        List<FwPersonHonor> honors = personHonorBoToEntityList(personInfo.getHonors(), id, personName);
        personHonorService.saveBatch(honors);
        // 保存新的资质表
        List<FwPersonQualification> qualifications = personQualificationBoToEntityList(personInfo.getQualifications(), id, personName);
        personQualificationService.saveBatch(qualifications);
        // 保存新的简历表
        List<FwPersonResume> resumes = personResumeBosToEntities(personInfo.getResumes(), id, personName);
        if (resumes != null) {
            personResumeService.saveBatch(resumes);
        }

        return id;
    }

    public Long updateHonorInfo(PersonHonorBo honor) {
        FwPersonHonor saved = personHonorService.getById(honor.getFId());
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        FwPersonHonor entity = personHonorBoToEntity(honor, null, null);
        personHonorService.updateById(entity);
        return honor.getFId();
    }

    public Long updateQualificationInfo(PersonQualificationBo qualification) {
        FwPersonQualification saved = personQualificationService.getById(qualification.getFId());
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        FwPersonQualification entity = personQualificationBoToEntity(qualification, null, null);
        personQualificationService.updateById(entity);
        return qualification.getFId();
    }

    public Long updateResumeInfo(PersonResumeBo resume) {
        FwPersonResume saved = personResumeService.getById(resume.getFId());
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        FwPersonResume entity = personResumeBoToEntity(resume, null, null);
        personResumeService.updateById(entity);
        return entity.getfId();
    }

    public void removeHonorInfo(String id) {
        FwPersonHonor saved = personHonorService.getById(id);
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        personHonorService.removeById(id);
    }

    public void removeQualificationInfo(String id) {
        FwPersonQualification saved = personQualificationService.getById(id);
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        personQualificationService.removeById(id);
    }

    public void removeResumeInfo(String id) {
        FwPersonResume saved = personResumeService.getById(id);
        if (saved == null) {
            throw new BaseException("该信息不存在或者已经删除! ", 500);
        }
        personResumeService.removeById(id);
    }

    /**
     * 根据ID获取法律人员信息, 携带个人荣誉、资质信息、简历内容
     *
     * @param personId
     * @return
     */
    public PersonInfoBo getById(String personId) {
        FwPersonInfo entity = personInfoService.getById(personId);
        if (entity == null) {
            throw new BaseException("该人物不存在或者已经删除! ", 500);
        }
        PersonInfoBo bo = personInfoEntityToBo(entity);
        // 个人荣誉
        LambdaQueryWrapper<FwPersonHonor> honorQuery = new LambdaQueryWrapper<>();
        honorQuery.eq(FwPersonHonor::getFkPersonId, personId);
        List<FwPersonHonor> honorEntities = personHonorService.list(honorQuery);
        List<PersonHonorBo> honors = personHonorEntitiesToBoList(honorEntities);
        bo.setHonors(honors);
        // 资质
        LambdaQueryWrapper<FwPersonQualification> qualificationQuery = new LambdaQueryWrapper<>();
        qualificationQuery.eq(FwPersonQualification::getFkPersonId, personId);
        List<FwPersonQualification> qualificationEntities = personQualificationService.list(qualificationQuery);
        List<PersonQualificationBo> qualifications = personQualificationrEntitiesToBoList(qualificationEntities);
        bo.setQualifications(qualifications);
        // 简历
        LambdaQueryWrapper<FwPersonResume> resumeQuery = new LambdaQueryWrapper<>();
        resumeQuery.eq(FwPersonResume::getFkPersonId, personId);
        List<FwPersonResume> resumeEntities = personResumeService.list(resumeQuery);
        List<PersonResumeBo> resumes = personResumeEntityToBoList(resumeEntities);
        bo.setResumes(resumes);

        return bo;
    }

    /**
     * 查询法律人员信息 带分页 无个人荣誉和资质信息内容
     *
     * @param bo {@code PersonQueryBo} 查询条件
     * @return
     */
    public IPage<PersonInfoBo> queryPersonListBo(PersonQueryBo bo) {
        IPage<FwPersonInfo> page = new Page<>(bo.getCurrent(), bo.getSize());
        LambdaQueryWrapper<FwPersonInfo> wrapper = personInfoService.lambdaQueryWrapper();
        wrapper.like(bo.getFname() != null, FwPersonInfo::getfName, bo.getFname())
                .eq(bo.getFisLegalPractitioner() != null, FwPersonInfo::getfIsLegalPractitioner, bo.getFisLegalPractitioner())
                .eq(bo.getFtype() != null, FwPersonInfo::getfType, bo.getFtype())
                .eq(bo.getFisInChargeLeader() != null, FwPersonInfo::getfIsInChargeLeader, bo.getFisInChargeLeader())
                .eq(bo.getFIsMainOfLegalAgency() != null, FwPersonInfo::getfIsMainOfLegalAgency, bo.getFIsMainOfLegalAgency())
                .eq(bo.getFIsFullTimeLegal() != null, FwPersonInfo::getfIsFullTimeLegal, bo.getFIsFullTimeLegal())
                .eq(bo.getFIsGeneralAdvisor() != null, FwPersonInfo::getfIsGeneralAdvisor, bo.getFIsGeneralAdvisor())
                .eq(bo.getOrgId() != null, FwPersonInfo::getFkOrgId, bo.getOrgId())
                .eq(bo.getFWorkingStatus() != null, FwPersonInfo::getfWorkingStatus, bo.getFWorkingStatus())
                .eq(bo.getFIsCompanyLawyer() != null, FwPersonInfo::getfIsCompanyLawyer, bo.getFIsCompanyLawyer())
                .inSql(bo.getFQulificationName() != null, FwPersonInfo::getfId, String.format("select fk_Person_ID from fw_person_qualification where f_isDel = 0 and f_Name = %s", bo.getFQulificationName()))
                .eq(bo.getOrgLevel() != null, FwPersonInfo::getFkOrgLevel, bo.getOrgLevel())
                // 数据权限
                .in(FwPersonInfo::getFkOrgId, userOrgService.getLoginUserChildren());
        //当前登录人 法务查询权限表 组织机构id   2020年9月29日
//		List<Long> longs = fwPrivilegeInfoService.queryOrgIdByUserId(Long.valueOf(UserUtils.getUserInfo().getfId()));
//		if(null!=longs&&longs.size()>0){
//			wrapper.or().in(FwPersonInfo::getFkOrgId,longs);
//		}
        IPage<FwPersonInfo> res = personInfoService.page(page, wrapper);
        return res.convert(this::personInfoEntityToBo);
    }

    /**
     * 查询法律人员信息 带分页 无个人荣誉和资质信息内容
     *
     * @param bo {@code PersonQueryBo} 查询条件
     * @return
     */
    public IPage<FwPersonInfo> queryPersonList(PersonQueryBo bo) {
        IPage<FwPersonInfo> page = new Page<>(bo.getCurrent(), bo.getSize());
        LambdaQueryWrapper<FwPersonInfo> wrapper = personInfoService.lambdaQueryWrapper();
        wrapper.like(bo.getFname() != null, FwPersonInfo::getfName, bo.getFname())
            .eq(bo.getFisLegalPractitioner() != null, FwPersonInfo::getfIsLegalPractitioner, bo.getFisLegalPractitioner())
            .eq(bo.getFtype() != null, FwPersonInfo::getfType, bo.getFtype())
            .eq(bo.getFisInChargeLeader() != null, FwPersonInfo::getfIsInChargeLeader, bo.getFisInChargeLeader())
            .eq(bo.getFIsMainOfLegalAgency() != null, FwPersonInfo::getfIsMainOfLegalAgency, bo.getFIsMainOfLegalAgency())
            .eq(bo.getFIsFullTimeLegal() != null, FwPersonInfo::getfIsFullTimeLegal, bo.getFIsFullTimeLegal())
            .eq(bo.getFIsGeneralAdvisor() != null, FwPersonInfo::getfIsGeneralAdvisor, bo.getFIsGeneralAdvisor())
            .eq(bo.getOrgId() != null, FwPersonInfo::getFkOrgId, bo.getOrgId())
            .eq(bo.getFWorkingStatus() != null, FwPersonInfo::getfWorkingStatus, bo.getFWorkingStatus())
            .eq(bo.getFIsCompanyLawyer() != null, FwPersonInfo::getfIsCompanyLawyer, bo.getFIsCompanyLawyer())
            .inSql(bo.getFQulificationName() != null, FwPersonInfo::getfId, String.format("select fk_Person_ID from fw_person_qualification where f_isDel = 0 and f_Name = %s", bo.getFQulificationName()))
            .eq(bo.getOrgLevel() != null, FwPersonInfo::getFkOrgLevel, bo.getOrgLevel())
            // 数据权限
            .and(w -> w.in(FwPersonInfo::getFkOrgId, userOrgService.getLoginUserChildren())
                // 20210416 接入数据权限表
                .or(privilegeInfoService.getPrivilegedOrgsR() != null, w1 -> w1
                    .in(FwPersonInfo::getFkOrgId, privilegeInfoService.getPrivilegedOrgsR()))
            );
        return personInfoService.page(page, wrapper);
    }

    public void setLegalPractitioner(List<Long> personId, Byte legalPractitioner) {
        if (personId == null) {
            throw new BaseException("缺少参数", 500);
        }
        if (legalPractitioner == null) {
            throw new BaseException("缺少参数", 500);
        }
        FwPersonInfo person = new FwPersonInfo();
        person.setfIsLegalPractitioner(legalPractitioner);
        personInfoService.update(person, personInfoService.lambdaUpdateWrapper().in(FwPersonInfo::getfId, personId));
    }

    public List<PersonQulificationSummaryBo> qulificationSummary() {
        List<PersonQulificationSummaryBo> summary = personInfoService.qulificationSummary();
        Set<Integer> lvls = summary.stream().map(PersonQulificationSummaryBo::getOrgLevel).collect(Collectors.toSet());
        if (!lvls.contains(0)) {
            PersonQulificationSummaryBo bo = new PersonQulificationSummaryBo();
            bo.setOrgLevel(0);
            bo.setPersonNumber(0);
            bo.setQulificationNumber(0);
            summary.add(bo);
        }
        if (!lvls.contains(1)) {
            PersonQulificationSummaryBo bo = new PersonQulificationSummaryBo();
            bo.setOrgLevel(1);
            bo.setPersonNumber(0);
            bo.setQulificationNumber(0);
            summary.add(bo);
        }
        if (!lvls.contains(2)) {
            PersonQulificationSummaryBo bo = new PersonQulificationSummaryBo();
            bo.setOrgLevel(2);
            bo.setPersonNumber(0);
            bo.setQulificationNumber(0);
            summary.add(bo);
        }
        summary.stream().sorted((res1, res2) -> res1.getOrgLevel() - res2.getOrgLevel());
        return summary;
    }

    private FwPersonInfo personInfoBoToEntity(PersonInfoBo bo) {
        FwPersonInfo entity = new FwPersonInfo();
        BeanUtils.copyProperties(bo, entity);
        try {
            entity.setfBirthdate(FORMAT.parse(bo.getFBirthdate()));
            entity.setfGraduateDate(FORMAT.parse(bo.getFGraduateDate()));
            entity.setfJoinJobDate(FORMAT.parse(bo.getFJoinJobDate()));
            entity.setfJoinLawJobDate(FORMAT.parse(bo.getFJoinLawJobDate()));
            if (bo.getFGaAssumeDate() != null) {
                entity.setfGaAssumeDate(FORMAT.parse(bo.getFGaAssumeDate()));
            }
            if (bo.getFSubGaAssumeDate() != null) {
                entity.setfSubGaAssumeDate(FORMAT.parse(bo.getFSubGaAssumeDate()));
            }
            if (bo.getFMainOfLegalAgencyAssumeDate() != null) {
                entity.setfMainOfLegalAgencyAssumeDate(FORMAT.parse(bo.getFMainOfLegalAgencyAssumeDate()));
            }
            entity.setfIsQualification(bo.getFisQualification());
        } catch (ParseException e) {
            throw new CustomException(ResultCode.PARAM_MISS);
        }
        return entity;
    }

    private FwPersonHonor personHonorBoToEntity(PersonHonorBo bo, Long personID, String personName) {
        try {
            FwPersonHonor entity = new FwPersonHonor();
            BeanUtils.copyProperties(bo, entity);
            entity.setFkPersonId(personID);
            entity.setFkPersonName(personName);
            if (bo.getFAwardDate() != null) {
                entity.setfAwardDate(FORMAT.parse(bo.getFAwardDate()));
            }
            return entity;
        } catch (ParseException e) {
            throw new CustomException(ResultCode.PARAM_MISS);
        }
    }


    private List<FwPersonHonor> personHonorBoToEntityList(List<PersonHonorBo> bo, Long personID, String personName) {
        if (bo == null) {
            return null;
        }
        return bo.stream().map(entity -> personHonorBoToEntity(entity, personID, personName)).collect(Collectors.toList());
    }

    private FwPersonQualification personQualificationBoToEntity(PersonQualificationBo bo, Long personID, String personName) {
        try {
            FwPersonQualification entity = new FwPersonQualification();
            BeanUtils.copyProperties(bo, entity);
            entity.setFkPersonId(personID);
            entity.setFkPersonName(personName);
            if (bo.getFAwardedDate() != null) {
                entity.setfAwardedDate(FORMAT.parse(bo.getFAwardedDate()));
            }
            if (bo.getFYearlyCheckDate() != null) {
                entity.setfYearlyCheckDate(FORMAT.parse(bo.getFYearlyCheckDate()));
            }
            return entity;
        } catch (ParseException e) {
            throw new CustomException(ResultCode.PARAM_MISS);
        }
    }

    private List<FwPersonQualification> personQualificationBoToEntityList(List<PersonQualificationBo> bo, Long personID, String personName) {
        if (bo == null) {
            return null;
        }
        return bo.stream().map(entity -> personQualificationBoToEntity(entity, personID, personName)).collect(Collectors.toList());
    }

    private FwPersonResume personResumeBoToEntity(PersonResumeBo bo, Long personID, String personName) {
        try {
            FwPersonResume entity = new FwPersonResume();
            BeanUtils.copyProperties(bo, entity);
            entity.setFkPersonId(personID);
            entity.setFkPersonName(personName);
            if (bo.getFBegindate() != null) {
                entity.setfBegindate(FORMAT.parse(bo.getFBegindate()));
            }
            if (bo.getFEnddate() != null) {
                entity.setfEnddate(FORMAT.parse(bo.getFEnddate()));
            }
            return entity;
        } catch (ParseException e) {
            throw new CustomException(ResultCode.PARAM_MISS);
        }
    }

    private List<FwPersonResume> personResumeBosToEntities(List<PersonResumeBo> bos, Long personID, String personName) {
        if (bos == null) {
            return null;
        }
        return bos.stream().map(bo -> personResumeBoToEntity(bo, personID, personName)).collect(Collectors.toList());
    }

    private PersonInfoBo personInfoEntityToBo(FwPersonInfo entity) {
        PersonInfoBo bo = new PersonInfoBo();
        BeanUtils.copyProperties(entity, bo);
        bo.setFBirthdate(FORMAT.format(entity.getfBirthdate()));
        bo.setFGraduateDate(FORMAT.format(entity.getfGraduateDate()));
        bo.setFJoinJobDate(FORMAT.format(entity.getfJoinJobDate()));
        bo.setFJoinLawJobDate(FORMAT.format(entity.getfJoinLawJobDate()));
        if (entity.getfGaAssumeDate() != null) {
            bo.setFGaAssumeDate(FORMAT.format(entity.getfGaAssumeDate()));
        }
        if (entity.getfSubGaAssumeDate() != null) {
            bo.setFSubGaAssumeDate(FORMAT.format(entity.getfSubGaAssumeDate()));
        }
        if (entity.getfMainOfLegalAgencyAssumeDate() != null) {
            bo.setFMainOfLegalAgencyAssumeDate(FORMAT.format(entity.getfMainOfLegalAgencyAssumeDate()));
        }
        bo.setFPositionName(DictionaryService.queryDictionaryById(bo.getFPosition()));
        bo.setFRankName(DictionaryService.queryDictionaryById(bo.getFRank()));
        bo.setFTitleLvlName(DictionaryService.queryDictionaryById(bo.getFTitleLvl()));
        bo.setFEducationName(DictionaryService.queryDictionaryById(bo.getFEducation()));
        bo.setFPoliticalStatusName(DictionaryService.queryDictionaryById(bo.getFPoliticalStatus()));
        bo.setFAcademicDegreeName(DictionaryService.queryDictionaryById(bo.getFAcademicDegree()));
        bo.setFisQualification(bo.getFisQualification() == null ? 2 : bo.getFisQualification());
        bo.setFisQualificationName(bo.getFisQualification() == null ? "无" : bo.getFisQualification() == 0 ? "是" : bo.getFisQualification() == 1 ? "否" : "无");
        return bo;
    }

    private PersonHonorBo personHonorEntityToBo(FwPersonHonor entity) {
        PersonHonorBo bo = new PersonHonorBo();
        BeanUtils.copyProperties(entity, bo);
        if (entity.getfAwardDate() != null) {
            bo.setFAwardDate(FORMAT.format(entity.getfAwardDate()));
        }
        return bo;
    }

    private List<PersonHonorBo> personHonorEntitiesToBoList(List<FwPersonHonor> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(entity -> personHonorEntityToBo(entity)).collect(Collectors.toList());
    }

    private PersonQualificationBo personQualificationEntityToBo(FwPersonQualification entity) {
        PersonQualificationBo bo = new PersonQualificationBo();
        BeanUtils.copyProperties(entity, bo);
        if (entity.getfAwardedDate() != null) {
            bo.setFAwardedDate(FORMAT.format(entity.getfAwardedDate()));
        }
        if (entity.getfYearlyCheckDate() != null) {
            bo.setFYearlyCheckDate(FORMAT.format(entity.getfYearlyCheckDate()));
        }
        return bo;
    }

    private List<PersonQualificationBo> personQualificationrEntitiesToBoList(List<FwPersonQualification> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(entity -> personQualificationEntityToBo(entity)).collect(Collectors.toList());
    }

    private PersonResumeBo personResumeBoEntityToBo(FwPersonResume entity) {
        if (entity == null) {
            return null;
        }
        PersonResumeBo bo = new PersonResumeBo();
        BeanUtils.copyProperties(entity, bo);
        if (entity.getfBegindate() != null) {
            bo.setFBegindate(FORMAT.format(entity.getfBegindate()));
        }
        if (entity.getfEnddate() != null) {
            bo.setFEnddate(FORMAT.format(entity.getfEnddate()));
        }
        return bo;
    }

    private List<PersonResumeBo> personResumeEntityToBoList(List<FwPersonResume> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(entity -> personResumeBoEntityToBo(entity)).collect(Collectors.toList());
    }

    /**
     * 查询法律人员信息 带分页 无个人荣誉和资质信息内容
     *
     * @param  {@code PersonQueryBo} 查询条件
     * @return
     */
    public List<FwExcelPersonVo> exportExcel() {
        LambdaQueryWrapper<FwPersonInfo> wrapper = personInfoService.lambdaQueryWrapper();
//        wrapper.like(bo.getFname() != null, FwPersonInfo::getfName, bo.getFname())
//                .eq(bo.getFisLegalPractitioner() != null, FwPersonInfo::getfIsLegalPractitioner, bo.getFisLegalPractitioner())
//                .eq(bo.getFtype() != null, FwPersonInfo::getfType, bo.getFtype())
//                .eq(bo.getFisInChargeLeader() != null, FwPersonInfo::getfIsInChargeLeader, bo.getFisInChargeLeader())
//                .eq(bo.getFIsMainOfLegalAgency() != null, FwPersonInfo::getfIsMainOfLegalAgency, bo.getFIsMainOfLegalAgency())
//                .eq(bo.getFIsFullTimeLegal() != null, FwPersonInfo::getfIsFullTimeLegal, bo.getFIsFullTimeLegal())
//                .eq(bo.getFIsGeneralAdvisor() != null, FwPersonInfo::getfIsGeneralAdvisor, bo.getFIsGeneralAdvisor())
//                .eq(bo.getOrgId() != null, FwPersonInfo::getFkOrgId, bo.getOrgId())
//                .eq(bo.getFWorkingStatus() != null, FwPersonInfo::getfWorkingStatus, bo.getFWorkingStatus())
//                .eq(bo.getFIsCompanyLawyer() != null, FwPersonInfo::getfIsCompanyLawyer, bo.getFIsCompanyLawyer())
//                .inSql(bo.getFQulificationName() != null, FwPersonInfo::getfId, String.format("select fk_Person_ID from fw_person_qualification where f_isDel = 0 and f_Name = %s", bo.getFQulificationName()))
//                .eq(bo.getOrgLevel() != null, FwPersonInfo::getFkOrgLevel, bo.getOrgLevel())
                // 数据权限
        wrapper.and(w -> w.in(FwPersonInfo::getFkOrgId, userOrgService.getLoginUserChildren())
                        // 20210416 接入数据权限表
                        .or(privilegeInfoService.privilegedOrgs() != null, w1 -> w1
                                .in(FwPersonInfo::getFkOrgId, privilegeInfoService.privilegedOrgs()))
                );
        return personEntityToVoList(personInfoService.list(wrapper));
    }

    private List<FwExcelPersonVo> personEntityToVoList(List<FwPersonInfo> entities) {
        log.info("======================="+entities.size()+"");
        if (entities == null) {
            return null;
        }
        return entities.stream().map(entity -> personEntityToVo(entity)).collect(Collectors.toList());
    }

    private FwExcelPersonVo personEntityToVo(FwPersonInfo entity) {
        if (entity == null) {
            return null;
        }
        FwExcelPersonVo bo = new FwExcelPersonVo();
        /**
         * 姓名
         */
        bo.setfName(entity.getfName());
        bo.setFkOrgName(entity.getFkOrgName());
        bo.setAge(getMonthNum(new Date(),entity.getfBirthdate(),1)+"");
        bo.setfPosition(DictionaryService.queryDictionaryById(entity.getfPosition()));
        bo.setfGender(entity.getfGender().toString().equals("0")?"男":"女");
        bo.setfIsFullTimeLegal(null!=entity.getfIsFullTimeLegal()? entity.getfIsFullTimeLegal().toString().equals("0")?"是":"否":"否");
        bo.setfIsGeneralAdvisor(null!=entity.getfIsGeneralAdvisor()? entity.getfIsGeneralAdvisor().toString().equals("0")?"是":"否":"否");
        bo.setfBussinessExceptLegal(null!=entity.getfBussinessExceptLegal()? entity.getfBussinessExceptLegal().toString().equals("0")?"是":"否":"否");
        bo.setfIsInChargeLeader(null!=entity.getfIsInChargeLeader()? entity.getfIsInChargeLeader().toString().equals("0")?"是":"否":"否");
        bo.setfIsLegalPractitioner(null!=entity.getfIsLegalPractitioner()? entity.getfIsLegalPractitioner().toString().equals("0")?"是":"否":"否");
        bo.setfIsSubGeneralAdvisor(null!=entity.getfIsSubGeneralAdvisor()? entity.getfIsSubGeneralAdvisor().toString().equals("0")?"是":"否":"否");
        bo.setfIsMainOfLegalAgency(null!=entity.getfIsMainOfLegalAgency()? entity.getfIsMainOfLegalAgency().toString().equals("0")?"是":"否":"否");

        return bo;
    }

    public static int getMonthNum(Date date1,Date date2,int type) {

        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(type==1?"yyyy":"yyyy-MM");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        if(type==1){
            result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            System.out.println("日期："+date1+"|"+date2+"|,相差"+Math.abs(result)+"年");
            return result == 0 ? 1 : Math.abs(result);
        }else{
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
            int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
            System.out.println("日期："+date1+"|"+date2+"|,相差"+Math.abs(result)+"个月");
            return result == 0 ? 1 : Math.abs(month + result);
        }
    }
}
