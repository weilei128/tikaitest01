package com.pcitc.szgt.contract.common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.common.model.SimpleOrgInfo;
import com.pcitc.szgt.contract.common.model.SimpleUserInfo;
import com.pcitc.szgt.contract.common.model.SysUserinfoWithOrg;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfoOrganizationR;
import com.pcitc.szgt.contract.share.model.DictionaryCategoryTree;
import com.pcitc.szgt.contract.share.model.SysOrganizationTree;
import com.pcitc.szgt.contract.share.model.UserQueryResultModel;
import com.pcitc.szgt.contract.share.model.UserRole;
import com.pcitc.szgt.contract.share.model.UserRoleList;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.DpsRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private DictionaryRequest dictionaryRequest;

    @Autowired
    private OrganizationRequest organizationRequest;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private DpsRequest dpsRequest;

    @Value("${wfssourl}")
    private String wfssourl;


    @GetMapping("uuid")
    public DataResult<?> uuid() {
        return DataResult.success(UUIDUtils.getUUID());
    }
    /**
     * 当前登录用户的信息方法
     * @return
     */
    @GetMapping("currentUser")
    public DataResult<SimpleUserInfo> currentUser() {
    	long startTimeBatch = System.currentTimeMillis() ;
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        List<SysOrganization> sysOrgList = userInfo.getSysOrgList();
        List<SimpleOrgInfo> orgs = new ArrayList<>();
        if(sysOrgList!=null && sysOrgList.size() >0) {
            for (SysOrganization sysOrganization : sysOrgList) {
                SimpleOrgInfo simpleOrgInfo = new SimpleOrgInfo();
                BeanUtils.copyProperties(sysOrganization, simpleOrgInfo);
                orgs.add(simpleOrgInfo);
            }
        }

        SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
        BeanUtils.copyProperties(userInfo.getSysUser(), simpleUserInfo);
        simpleUserInfo.setOrgs(orgs);
        simpleUserInfo.setUnitId(userInfo.getUnitId());//所属企业/单位
        simpleUserInfo.setUnitName(userInfo.getUnitName());//所属企业/单位
         
        UserRoleList role = userInfoRequest.queryUserRoleByUserId(userInfo.getSysUser().getfId());
		if(role!=null) {
    		List<UserRole> roles = role.getUserSelectRoleInfoList().stream()
    				.filter(i->i.getRoleCode().equals("appYes"))
    				.collect(Collectors.toList());
    	    if(roles !=null && roles.size()>0) {
    	    	simpleUserInfo.setfRole("Y");
    	    }else {
            	simpleUserInfo.setfRole("N");
            }
		}
		long endTimeBatch = System.currentTimeMillis() ; 
        log.info("-----方法=currentUser当前登录用户的信息方法总耗时--> {} ms",(endTimeBatch - startTimeBatch));
        return DataResult.success(simpleUserInfo);
    }

    /**
     * 	根据id获取数据字典列表
     */
    @GetMapping("queryDictionary")
    public DataResult<?> queryDictionary(String dictionaryId) {
        return DataResult.success(dictionaryRequest.queryDictionary(dictionaryId));
    }

    /**
     * 	根据ID获取分类信息
     */
    @GetMapping("queryCategoryById")
    public DataResult<?> queryCategoryById(Integer categoryId) {
        return DataResult.success(dictionaryRequest.queryCategoryById(categoryId));
    }

    /**
     * 	根据ID获取下一级分类信息
     */
    @GetMapping("querySubCategorys")
    public DataResult<?> querySubCategorys(Integer categoryId) {
        return DataResult.success(dictionaryRequest.querySubCategorys(categoryId));
    }


    /**
     * 	查询分类下的字典项列表
     */
    @GetMapping("queryDictionaryByCid")
    public DataResult<?> queryDictionaryByCid(String categoryId) {
        return DataResult.success(dictionaryRequest.queryDictionaryByCid(categoryId));
    }

    /**
     * 	根据code查询分类信息
     */
    @GetMapping("queryCategotyByCode")
    public DataResult<?> queryCategotyByCode(String code) {
        return DataResult.success(dictionaryRequest.queryCategotyByCode(code));
    }

    /**
     * 	据code查询子分类
     */
    @GetMapping("querySubCategoryByCode")
    public DataResult<?> querySubCategoryByCode(String code) {
        return DataResult.success(dictionaryRequest.querySubCategoryByCode(code));
    }

    /**
     * 	查询组织机构树
     */
    @GetMapping("queryOrganizationTree")
    public DataResult<?> queryOrganizationTree() {
        return DataResult.success(organizationRequest.queryOrganizationTree());
    }

    /**
     * 	查询某个机构下的所有用户(包括子机构)
     */
    @GetMapping("queryUserByOrgId")
    public DataResult<?> queryUserByOrgId(Integer orgId) {
        return DataResult.success(userInfoRequest.queryByOrgId(orgId));
    }

    /**
     * 	查询当前组织机构所在企业/单位by fId
     */
    @GetMapping("getOrgCompanyByFId")
    public DataResult<?> getOrgCompanyByFId(Integer fId) {
        return DataResult.success(organizationRequest.getOrgCompany(fId));
    }

    /**
     * 	查询所有用户
     */
    @GetMapping("queryAllUser")
    public DataResult<?> queryAllUser() {
        return DataResult.success(userInfoRequest.queryAll());
    }

    /**
     *	按名称分页查询用户
     * @return
     */
    @GetMapping("queryUserPaged")
    public PageData queryUserPaged(String userName, String organizationName, @RequestParam Integer current, @RequestParam Integer size) {
        UserQueryResultModel userQueryResultModel = userInfoRequest.queryUserPaged(userName, organizationName, current, size);
        PageData<SysUserinfoWithOrg> pageData = new PageData<>();
        pageData.setCurrentPage(userQueryResultModel.getCurrent());
        pageData.setPageSize(userQueryResultModel.getSize());
        pageData.setTotalCount(userQueryResultModel.getTotal());
        pageData.setTotalPage(userQueryResultModel.getPages());
        pageData.setData(userQueryResultModel.getRecords());

        List<Integer> uids = userQueryResultModel.getRecords().stream().map(SysUserinfoWithOrg::getfId).collect(Collectors.toList());
        Map<String, List<SysUserinfoOrganizationR>> stringListMap = userInfoRequest.queryUserOrgR(uids);

        //-------query all org ---------
        Collection<List<SysUserinfoOrganizationR>> values = stringListMap.values();
        List<SysUserinfoOrganizationR> collect = values.stream().flatMap(List::stream).collect(Collectors.toList());
        List<Integer> allOrgIds = collect.stream().map(SysUserinfoOrganizationR::getFkOrganizationId).collect(Collectors.toList());
        List<SysOrganization> sysOrganizations = organizationRequest.queryOrgByIdBatch(allOrgIds.toArray(new Integer[0]));

        int i1 = Integer.parseInt(ContractEnum.EnumOrgType.Dept.getCode());
        for (SysUserinfoWithOrg i : userQueryResultModel.getRecords()) {
            List<SysUserinfoOrganizationR> sysUserinfoOrganizationRS = stringListMap.get(String.valueOf(i.getfId()));
            if(CollectionUtils.isEmpty(sysUserinfoOrganizationRS)){
                continue;
            }
            List<Integer> orgIds = sysUserinfoOrganizationRS.stream().map(SysUserinfoOrganizationR::getFkOrganizationId).collect(Collectors.toList());

            //所有所属机构
            List<SysOrganization> collect1 = sysOrganizations.stream().filter(o -> orgIds.contains(o.getfId())).collect(Collectors.toList());
            //单位机构
            List<SysOrganization> collect2 = collect1.stream().filter(o -> o.getfType() != i1).collect(Collectors.toList());

            if(!CollectionUtils.isEmpty(collect2)){
                i.setUnit(collect2.get(0));
            }else{
                if(!CollectionUtils.isEmpty(collect1)){
                    SysOrganization sysOrganization = organizationRequest.queryTopOrg(collect1.get(0).getfId());
                    i.setUnit(sysOrganization);
                }else{
                    i.setUnit(new SysOrganization());
                }
            }
            //只保留部门
            collect1.removeAll(collect2);
            i.setOrgs(collect1);
        }

        return pageData;
    }

    /**
     * 	工作流单点登录
     * @param userId
     * @return
     */
    @GetMapping("dpsSSO")
    public DataResult<?> dpsSSO(String userId) {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("url", wfssourl);
        tokenMap.put("token", dpsRequest.sso(userId));

        return DataResult.success(tokenMap);
    }

    /**
     * 	查询子字典
     * @return
     */
    @GetMapping("queryCategotyByCodeUsingLike")
    public DataResult<?> queryCategotyByCodeUsingLike(String code) {
        List<SysDictionarycategory> sysDictionarycategories = dictionaryRequest.queryCategoryByCodeUsingLike(code);
        return DataResult.success(sysDictionarycategories);
    }

    /**
     * 	根据code查字典树
     * @param code
     * @return
     */
    @GetMapping("queryDicCategoryTreeByCode")
    public DataResult<?> queryDicCategoryTreeByCode(String code) {
        DictionaryCategoryTree tree = dictionaryRequest.queryDictionaryCategoryTreeByCode(code);
        return DataResult.success(tree);
    }

    /**
     * 	查询组织树排除部门
     * @return
     */
    @GetMapping("queryOrgTreeExcludeNormal")
    public DataResult<?> queryOrgTreeExcludeNormal(){
        List<SysOrganizationTree> sysOrganizationTrees = organizationRequest.queryOrganizationTreeExcludeNormal();
        return DataResult.success(sysOrganizationTrees);
    }

    /**
     * 	获取环节接口
     * @return
     */
    @GetMapping("sectiondict")
    public DataResult<List<Map<String, String>>> sectionDict(){
        List<Map<String, String>> collect = ContractEnum.enumSectionActualMap.entrySet().stream().map(entry -> new HashMap<String, String>() {{
            put("code", entry.getKey());
            put("title", entry.getValue());
        }}).collect(Collectors.toList());
        return DataResult.success(collect);
    }

    /**
     * 	获取付款类型接口
     * @return
     */
    @GetMapping("paytypedict")
    public DataResult<List<Map<String, String>>> payTypeDict(){
        List<Map<String, String>> collect = ContractEnum.enumPayTypeMap.entrySet().stream().map(entry -> new HashMap<String, String>() {{
            put("code", entry.getKey());
            put("title", entry.getValue());
        }}).collect(Collectors.toList());
        return DataResult.success(collect);
    }

    /**
     * 	查询当前单位的下的所有部门
     * @return
     */
    @GetMapping("queryAllDeptInUnit")
    public DataResult<List<SysOrganizationTree>> queryAllDeptInUnit(){
        UserInfo userInfo = currentUserUtil.currentUserInfo();
        List<SysOrganizationTree> sysOrganizationTrees = organizationRequest.queryAllDeptInUnit(userInfo.getUnitId());
        return DataResult.success(sysOrganizationTrees);
    }

    /**
     * oa待办变已办
     * @return
     */
    @PostMapping("oaTaskDone")
    public DataResult<Object> oaTaskDone(@RequestParam String taskId){
        boolean b = dpsRequest.oaTaskDone(taskId);
        if(b){
            return DataResult.success(null);
        }else{
            return DataResult.fail(null, 500, "fail");
        }
    }

    /**
     * oa待办删除
     * @param taskId
     * @return
     */
    @PostMapping("oaTaskDel")
    public DataResult<Object> oaTaskDel(@RequestParam String taskId){
        boolean b = dpsRequest.oaTaskDel(taskId);
        if(b){
            return DataResult.success(null);
        }else{
            return DataResult.fail(null, 500, "fail");
        }
    }

    /**
     * 重发oa待办
     * @param taskId
     * @return
     */
    @PostMapping("resendOaTask")
    public DataResult<Object> resendOaTask(@RequestParam String taskId){
        boolean b = dpsRequest.resendOaTask(taskId);
        if(b){
            return DataResult.success(null);
        }else{
            return DataResult.fail(null, 500, "fail");
        }
    }

    /**
     * 发送oa待办
     * @return
     */
    @PostMapping("sendOaTask")
    public DataResult<Object> sendOaTask(@RequestParam String taskId){
        boolean b = dpsRequest.sendOaTask(taskId);
        if(b){
            return DataResult.success(null);
        }else{
            return DataResult.fail(null, 500, "fail");
        }
    }

    /**
     * 删除oa待办
     * @return
     */
    @PostMapping("deleteOaTask")
    public DataResult<Object> deleteOaTask(@RequestParam String taskId){
        boolean b = dpsRequest.deleteOaTask(taskId);
        if(b){
            return DataResult.success(null);
        }else{
            return DataResult.fail(null, 500, "fail");
        }
    }
}
