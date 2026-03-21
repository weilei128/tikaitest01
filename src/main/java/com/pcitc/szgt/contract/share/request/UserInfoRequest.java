package com.pcitc.szgt.contract.share.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.make.modelEx.UserInfoListVo;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.entity.SysUserinfoOrganizationR;
import com.pcitc.szgt.contract.share.model.UserQueryResultModel;
import com.pcitc.szgt.contract.share.model.UserRole;
import com.pcitc.szgt.contract.share.model.UserRoleList;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@Component
@Slf4j
public class UserInfoRequest {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private ShareConfig shareConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 	根据id查询用户信息
     * @param uid
     * @return
     */
    public SysUserinfo queryById(Integer uid){

        Map<String, Object> params = new HashMap<>();
        params.put("userId", uid);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/userInfo/queryUserById", params, null);

        SysUserinfo userInfo = null;
        try {
            DataResult<SysUserinfo> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysUserinfo>>(){});
            userInfo = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return userInfo;
    }

    /**
     * 	根据用户id批量查
     * @return
     */
    public List<SysUserinfo> queryByIdBatch(Integer... userIds){
        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/userInfo/queryListUserById", Arrays.asList(userIds), null);

        List<SysUserinfo> users = null;
        try {
            DataResult<List<SysUserinfo>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysUserinfo>>>(){});
            users = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(CollectionUtils.isEmpty(users)){
            return new ArrayList<>();
        }

        return users;
    }

    /**
     * 	根据用户中文名和账号名模糊查询
     * @param name
     * @return
     */
    public List<SysUserinfo> queryByName(String name){
        Map<String, Object> map = new HashMap<>();
        map.put("accountName", name);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/userInfo/queryUserByAccountOrName", map);
        List<SysUserinfo> users = null;
        try {
            DataResult<List<SysUserinfo>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysUserinfo>>>(){});
            users = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(CollectionUtils.isEmpty(users)){
            return new ArrayList<>();
        }

        return users;
    }

    /**
     * 	查询用户所在组织机构id
     * @return
     */
    public List<Integer> queryOrgs(String userId){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/userManage/querySelectOrganization", params);

        List<Integer> users = null;
        try {
            DataResult<List<Integer>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<Integer>>>(){});
            users = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(CollectionUtils.isEmpty(users)){
            return new ArrayList<>();
        }

        return users;

    }

    /**
     *	 查询某个机构下的所有用户(包括子机构)
     * @param orgId
     * @return
     */
    public List<SysUserinfo> queryByOrgId(Integer orgId){
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", orgId);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/userManage/queryOrganizationUser", map);
        List<SysUserinfo> users = null;
        try {
            DataResult<List<SysUserinfo>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysUserinfo>>>(){});
            users = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(CollectionUtils.isEmpty(users)){
            return new ArrayList<>();
        }

        return users;
    }

    /**
     * 	查询所有用户
     * @return
     */
    public List<SysUserinfo> queryAll(){
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/userInfo/getAllUser", null);
        List<SysUserinfo> users = null;
        try {
            DataResult<List<SysUserinfo>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysUserinfo>>>(){});
            users = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(CollectionUtils.isEmpty(users)){
            return new ArrayList<>();
        }

        return users;
    }

    /**
     * 	按名称模糊分页查询
     * @param username
     * @return
     */
    public UserQueryResultModel queryUserPaged(String username, String organizationName, Integer current, Integer size){
        Map<String, Object> params = new HashMap<>();
        params.put("userName", username);
        params.put("organizationName", organizationName);
        params.put("current", current);
        params.put("size", size);

        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/userInfo/queryUserByName", params, null);

        try {
            DataResult<UserQueryResultModel> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<UserQueryResultModel>>(){});
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 	根据用户id批量查用户-组织机构中间表信息
     * @return
     */
    public Map<String, List<SysUserinfoOrganizationR>> queryUserOrgR(List<Integer> usserIds){

        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/userManage/querySelectOrganizationByIds", usserIds, null);

        try {
            DataResult<Map<String, List<SysUserinfoOrganizationR>>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Map<String, List<SysUserinfoOrganizationR>>>>(){});
            return dataResult.getData()!=null?dataResult.getData():new HashMap<>();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     *	 根据编码查用户
     * @param code
     * @return
     */
    public SysUserinfo queryByCode(String code){
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/userInfo/queryUserByCode", params);
        SysUserinfo userInfo = null;
        try {
            DataResult<SysUserinfo> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysUserinfo>>(){});
            userInfo = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return userInfo;
    }

    /**
     *	 根据角色名称查询出用户信息
     * @param code
     * @return
     */
    public UserInfoListVo queryUserInfoByRoleName(String roleName){
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("size",10);
        params.put("role", roleName);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/userInfo/queryUserByRoleOrgUserName?page={page}&size={size}&role={role}",params);
        try {
            DataResult<UserInfoListVo> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<UserInfoListVo>>(){});
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }
    /**
     * 	根据用户ID查询用户角色
     */
    public UserRoleList queryUserRoleByUserId(Integer userId) {
    	 Map<String, Object> params = new HashMap<>();
         params.put("userId", userId);

         String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/userManage/queryUserRole/", params);
         UserRoleList userRole = null;
         try {
             DataResult<UserRoleList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<UserRoleList>>(){});
             userRole = dataResult.getData();
         } catch (IOException e) {
             StringWriter trace = new StringWriter();
             e.printStackTrace(new PrintWriter(trace));
             log.error(trace.toString());
             throw new BaseException("对象解析失败", 500);
         }

         return userRole;
    }
}
