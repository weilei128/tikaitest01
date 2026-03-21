package com.pcitc.szgt;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultList;
import com.pcitc.common.entity.ResultListData;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoQueryBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationStaffBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationWrokRewardBo;
import com.pcitc.legalAffairs.dbService.OrganizationBasicInfo.IOrganizationBasicInfoService;
import com.pcitc.legalAffairs.dbService.OrganizationStaff.IOrganizationStaffService;
import com.pcitc.legalAffairs.dbService.OrganizationWrokReward.IOrganizationWrokRewardService;
import com.pcitc.legalAffairs.dbService.client.IClientService;
import com.pcitc.system.bo.SysAttachmentDirectoryBo;
import com.pcitc.system.dbService.SysAttachmentDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GtApplicationTest {


    @Autowired
    private IClientService clientService;



    @Test
    public void test() {
        ResultListData<String, String> resultListData = new ResultListData<>();
        ResultList<String> resultList = new ResultList<>();
        resultList.setCurrent(1);
        resultList.setRecords(new ArrayList<>());
//        resultListData.setResultList(resultList);
//        resultListData.setData("测试一下");

//        log.info("data == {}", JSON.toJSONString(Result.data(resultListData)));
    }

    @Test
    public void save() {
//        SysOrganizationBo sysOrganizationBo = new SysOrganizationBo();
//        sysOrganizationBo.setFCode("dfdfdf111");
//        sysOrganizationBo.setFName("阿东工程师");
//        systemService.addNote(sysOrganizationBo);
    }

    @Autowired
    private IOrganizationBasicInfoService organizationBasicInfoService;

    @Test
    public void testqueryOrganizationBasicInfo() {
        Result result = organizationBasicInfoService.queryOrganizationBasicInfo(3);
        System.out.println(JSON.toJSON(result));
    }
    @Test
    public void queryOrganizationBasicInfoPage(){
        OrganizationBasicInfoQueryBo queryBo = new OrganizationBasicInfoQueryBo();
        queryBo.setfAffiliatedUnit("所属单位025");
        queryBo.setFkOrgName("");
        queryBo.setfOrgTypeName("");
        queryBo.setfSetStatus(0);
        queryBo.setPageIndex(0);
        queryBo.setPageSize(5);
        System.out.println(JSON.toJSON(queryBo));
        Result result = organizationBasicInfoService.queryOrganizationBasicInfoManagePage(queryBo);
        System.out.println(JSON.toJSON(result));
    }
    @Test
    public void saveOrganizationBasicInfo (){
        OrganizationBasicInfoBo organizationBasicInfoBo = new OrganizationBasicInfoBo();
        organizationBasicInfoBo.setfAffiliatedUnit("所属单位0");
        organizationBasicInfoBo.setfOrgNode("组织节点");
        organizationBasicInfoBo.setfUnitFax("单位传真");
        organizationBasicInfoBo.setfAreaCode("014");
        organizationBasicInfoBo.setfFileName("文件名称");
        organizationBasicInfoBo.setfReferenceNumber("文号");
        organizationBasicInfoBo.setfFileUrl("C:\\MavenRepository\\org\\springframework\\session");
        organizationBasicInfoBo.setFkOrgId("组织机构id");
        organizationBasicInfoBo.setFkOrgName("机构名称");
        organizationBasicInfoBo.setfOrgDuties("组织职责");
        organizationBasicInfoBo.setfOrgTypeCode("0");
        organizationBasicInfoBo.setfOrgTypeName("类型");
        organizationBasicInfoBo.setfOverseaOrgName("海外机构名称");
        organizationBasicInfoBo.setfPostCode("010");
        organizationBasicInfoBo.setfPostalAddress("通讯地址");
        organizationBasicInfoBo.setfAreaCode("010");
        organizationBasicInfoBo.setfSetStatus(1);
        System.out.println(JSON.toJSON(organizationBasicInfoBo));
        Result result = organizationBasicInfoService.saveOrganizationBasicInfo(organizationBasicInfoBo);
        System.out.println(JSON.toJSON(result));
    }
    @Test
    public void testsaveOrganizationBasicInfoBatch() {
        for (int i = 120;i<500;i++){
            OrganizationBasicInfoBo organizationBasicInfoBo = new OrganizationBasicInfoBo();
            organizationBasicInfoBo.setfAffiliatedUnit("所属单位0"+i);
            organizationBasicInfoBo.setfOrgNode("组织节点"+i);
            organizationBasicInfoBo.setfUnitFax("单位传真"+i);
            organizationBasicInfoBo.setfAreaCode("014"+i);
            organizationBasicInfoBo.setfFileName("文件名称"+i);
            organizationBasicInfoBo.setfReferenceNumber("文号"+i);
            organizationBasicInfoBo.setfFileUrl("C:\\MavenRepository\\org\\springframework\\session"+i);
            organizationBasicInfoBo.setFkOrgId("组织机构id"+i);
            organizationBasicInfoBo.setFkOrgName("机构名称"+i);
            organizationBasicInfoBo.setfOrgDuties("组织职责"+i);
            organizationBasicInfoBo.setfOrgTypeCode("0"+i);
            organizationBasicInfoBo.setfOrgTypeName("类型"+i);
            organizationBasicInfoBo.setfOverseaOrgName("海外机构名称"+i);
            organizationBasicInfoBo.setfPostCode("010"+i);
            organizationBasicInfoBo.setfPostalAddress("通讯地址"+i);
            organizationBasicInfoBo.setfAreaCode("010"+i);
            organizationBasicInfoBo.setfSetStatus(1);
            System.out.println(JSON.toJSON(organizationBasicInfoBo));
            Result result = organizationBasicInfoService.saveOrganizationBasicInfo(organizationBasicInfoBo);
            System.out.println(JSON.toJSON(result));
        }

    }

    @Test
    public void updateOrganizationBasicInfo() {
        OrganizationBasicInfoBo organizationBasicInfoBo = new OrganizationBasicInfoBo();
        organizationBasicInfoBo.setfId(3);
        organizationBasicInfoBo.setfFileName("ceshi03");
        organizationBasicInfoBo.setfAffiliatedUnit("ceshi03");
        System.out.println(JSON.toJSON(organizationBasicInfoBo));
        organizationBasicInfoService.updateOrganizationBasicInfo(organizationBasicInfoBo);
    }

    @Test
    public void deleteOrganizationBasicInfo() {
        organizationBasicInfoService.deleteOrganizationBasicInfo(5);
    }

    @Autowired
    private IOrganizationStaffService organizationStaffService;

    @Test
    public void testqueryOrganizationStaff() {
        Result result = organizationStaffService.queryOrganizationStaff(1);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void saveOrganizationStaff() {
            OrganizationStaffBo organizationStaffBo = new OrganizationStaffBo();
            organizationStaffBo.setFkLawFirmId(10);
            organizationStaffBo.setfName("姓名");
            organizationStaffBo.setfSex((short) 0);
            organizationStaffBo.setfPosition("岗位");
            organizationStaffBo.setfPositionDuties("岗位职责");
            organizationStaffBo.setfFixPositionFileName("定岗文件名称");
            organizationStaffBo.setfFixPositionFileUrl("C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2018.2\\bin\\test"+".doc");
            System.out.println(JSON.toJSON(organizationStaffBo));
            organizationStaffService.saveOrganizationStaff(organizationStaffBo);

    }

    @Test
    public void saveOrganizationStaffBatch() {
        for (int i=1;i<5001;i++){
            OrganizationStaffBo organizationStaffBo = new OrganizationStaffBo();
            organizationStaffBo.setFkLawFirmId(10);
            organizationStaffBo.setfName("姓名"+i);
            organizationStaffBo.setfSex((short) 0);
            organizationStaffBo.setfPosition("岗位"+i);
            organizationStaffBo.setfPositionDuties("岗位职责"+i);
            organizationStaffBo.setfFixPositionFileName("定岗文件名称"+i);
            organizationStaffBo.setfFixPositionFileUrl("C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2018.2\\bin\\"+i+".doc");
            System.out.println(JSON.toJSON(organizationStaffBo));
            organizationStaffService.saveOrganizationStaff(organizationStaffBo);
        }

    }

    @Test
    public void queryOrganizationStaffList() {
        Result result = organizationStaffService.queryOrganizationStaffList(110);
        System.out.println(JSON.toJSON(result));
    }
    @Test
    public  void queryOrganizationStaffPage(){
        Result result = organizationStaffService.queryOrganizationStaffPage(0,5,110);
        System.out.println(JSON.toJSON(result));
    }
    @Test
    public void deleteOrganizationStaff() {
        organizationStaffService.deleteOrganizationStaff(4);
    }
    @Test
    public void deleteOrganizationStaffBatch(){
        List<Integer> ids = new ArrayList<>();
        ids.add(9);
        ids.add(10);
        ids.add(11);
        System.out.println(JSON.toJSON(ids));
        organizationStaffService.deleteOrganizationStaffBatch(ids);
    }
    @Test
    public void updateOrganizationStaff() {
        OrganizationStaffBo organizationStaffBo = new OrganizationStaffBo();
        organizationStaffBo.setfId(1);
        organizationStaffBo.setfSex((short) 1);
        System.out.println(JSON.toJSON(organizationStaffBo));
        organizationStaffService.updateOrganizationStaff(organizationStaffBo);
    }

    @Autowired
    private IOrganizationWrokRewardService organizationWrokRewardService;
    @Test
    public  void saveOrganizationWrokReward(){
        for (int i = 1;i<50000;i++){
            OrganizationWrokRewardBo organizationWrokRewardBo = new OrganizationWrokRewardBo();
            organizationWrokRewardBo.setFkLawFirmId(16);
            organizationWrokRewardBo.setfRewardName("获奖名称"+i);
            organizationWrokRewardBo.setfRewardLevelCode("编码"+i);
            organizationWrokRewardBo.setfRewardLevelName("获奖级别名称"+i);
            organizationWrokRewardBo.setfRewardDate(new Date());
            organizationWrokRewardBo.setfAwardsOrgName("颁奖机构名称33"+i);
            System.out.println(JSON.toJSON(organizationWrokRewardBo));
            Result result = organizationWrokRewardService.saveOrganizationWrokReward(organizationWrokRewardBo);
            System.out.println(JSON.toJSON(result));
        }

    }
    @Test
    public void updateOrganizationWrokReward(){
        OrganizationWrokRewardBo organizationWrokRewardBo = new OrganizationWrokRewardBo();
        organizationWrokRewardBo.setfId(5);
        organizationWrokRewardBo.setFkLawFirmId(6);
        System.out.println(JSON.toJSON(organizationWrokRewardBo));
        organizationWrokRewardService.updateOrganizationWrokReward(organizationWrokRewardBo);
    }
    @Test
    public void testqueryOrganizationWrokReward() {
        Result result = organizationWrokRewardService.queryOrganizationWrokReward(1);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void queryOrganizationWrokRewardList() {
        Result result = organizationWrokRewardService.queryOrganizationWrokRewardList(16);
        System.out.println(JSON.toJSON(result));
    }
    @Test
   public void deleteOrganizationWrokReward(){
        organizationWrokRewardService.deleteOrganizationWrokReward(4);
   }
    @Test
    public void queryOrganizationWrokRewardPage(){
        Result result =organizationWrokRewardService.queryOrganizationWrokRewardPage(0,20,16);
        System.out.println(JSON.toJSON(result));
    }

    @Autowired
    private SysAttachmentDirectoryService sysAttachmentDirectoryService;
    @Test
    public void   saveSysAttachmentDirectory(){
        SysAttachmentDirectoryBo sysAttachmentDirectoryBo = new SysAttachmentDirectoryBo();
        sysAttachmentDirectoryBo.setfName("文件目录名称测试01");
        sysAttachmentDirectoryBo.setfNote("文件目录备注测试01");
        sysAttachmentDirectoryBo.setfRule("文件目录规则测试01");
        sysAttachmentDirectoryBo.setFkParentName("父分类名称测试01");
        sysAttachmentDirectoryBo.setfLevel(2);
        sysAttachmentDirectoryBo.setfIsdel(0);
        sysAttachmentDirectoryBo.setfCreateName("系统管理员");
        sysAttachmentDirectoryBo.setfCreateTime(new Date());
        sysAttachmentDirectoryBo.setfCreateUser("admin");
        System.out.println(JSON.toJSON(sysAttachmentDirectoryBo));
        Result result = sysAttachmentDirectoryService.save(sysAttachmentDirectoryBo);
        System.out.println(JSON.toJSON(result));
    }

}

