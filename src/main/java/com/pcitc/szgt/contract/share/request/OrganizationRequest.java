package com.pcitc.szgt.contract.share.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.model.SysOrganizationRoot;
import com.pcitc.szgt.contract.share.model.SysOrganizationTree;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrganizationRequest {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private ShareConfig shareConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *	 查询组织机构树
     *
     * @return
     */
    public List<SysOrganizationTree> queryOrganizationTree() {

        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/system/queryOrganization", null, null);

        List<SysOrganizationTree> organizationTrees = null;
        try {
            DataResult<List<SysOrganizationTree>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysOrganizationTree>>>() {
            });
            organizationTrees = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return organizationTrees;
    }

    /**
     * 	按id查组织机构子树
     *
     * @return
     */
    private List<SysOrganizationTree> queryOrganizationSubTree(Integer... orgIds) {

        Map<String, Object> params = new HashMap<>();
        params.put("organizationIds", Arrays.asList(orgIds));
        params.put("type", 0);

        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/system/queryOrganizationByIds", params, null);
        List<SysOrganizationTree> organizationTrees = null;
        try {
            DataResult<List<SysOrganizationTree>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysOrganizationTree>>>() {
            });
            organizationTrees = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return organizationTrees;
    }

    /**
     * 根据id查组织机构信息
     *
     * @param orgaizationId
     * @return
     */
    public SysOrganization queryOrganization(Integer orgaizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", orgaizationId);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/system/queryOrganizationById", params, null);

        SysOrganizationTree organizationTree = null;
        try {
            DataResult<SysOrganizationTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysOrganizationTree>>() {
            });
            organizationTree = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if (organizationTree == null) {
            return organizationTree;
        }

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(organizationTree, org);

        return org;
    }

    /*
     * 获取当前组织机构所在企业/单位
     * */
    public SysOrganization getOrgCompany(Integer orgaizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", orgaizationId);
        long startTime = System.currentTimeMillis() ;
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/system/queryOrganizationById", params, null);

        SysOrganizationTree organizationTree = null;
        try {
            DataResult<SysOrganizationTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysOrganizationTree>>() {
            });
            organizationTree = dataResult.getData();
            //递归查询，直到查到组织机构是公司/单位/集团
            if (organizationTree != null) {
                if (organizationTree.getfType() == Integer.parseInt(ContractEnum.EnumOrgType.Dept.getCode())) {
                    return getOrgCompany(organizationTree.getFkParentId());
                }
            }

        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if (organizationTree == null) {
            return organizationTree;
        }

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(organizationTree, org);
        long endTime = System.currentTimeMillis() ; 
        log.info("-----getOrgCompany获取当前组织机构所在企业/单位总时间= {} ms,参数orgaizationId={}",(endTime - startTime),orgaizationId);
        return org;
    }

    /*
     * 获取企业/集团信息
     * */
    public SysOrganization getEnterprise(Integer orgaizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", orgaizationId);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/system/queryOrganizationById", params, null);
        SysOrganizationTree organizationTree = null;
        try {
            DataResult<SysOrganizationTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysOrganizationTree>>() {
            });
            organizationTree = dataResult.getData();
            //递归查询 ,查到企业/集团
            if (organizationTree != null) {
                if (organizationTree.getfType() != Integer.parseInt(ContractEnum.EnumOrgType.Company.getCode())
                        && organizationTree.getfType() != Integer.parseInt(ContractEnum.EnumOrgType.Group.getCode())) {
                    return getOrgCompany(organizationTree.getFkParentId());
                }
            }

        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if (organizationTree == null) {
            return organizationTree;
        }

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(organizationTree, org);
        return org;
    }

    /**
     * 根据组织机构id查询根
     *
     * @param orgaizationId
     * @return
     */
    private SysOrganizationRoot queryOrganizationRoot(Integer orgaizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", orgaizationId);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/system/queryAllParentOrganizationById", params, null);

        SysOrganizationRoot root = null;
        try {
            DataResult<SysOrganizationRoot> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysOrganizationRoot>>() {
            });
            root = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return root;

    }

    /**
     * 查询顶层组织机构节点
     *
     * @param orgaizationId
     * @return
     */
    public SysOrganization queryTopOrg(Integer orgaizationId) {
        SysOrganizationRoot root = queryOrganizationRoot(orgaizationId);
        if (root == null || StringUtils.isEmpty(root.getfId())) {
            return queryOrganization(orgaizationId);
        }

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(root, org);

        return org;
    }

    /**
     * 批量查询组织机构
     *
     * @param orgIds
     * @return
     */
    public List<SysOrganization> queryOrgByIdBatch(Integer... orgIds) {
    	
    	List<SysOrganizationTree> trees = queryOrganizationSubTree(orgIds);
        List<SysOrganization> list = new ArrayList<>();
        for (SysOrganizationTree tree : trees) {
            SysOrganization sysOrganization = new SysOrganization();
            BeanUtils.copyProperties(tree, sysOrganization);
            list.add(sysOrganization);
        }

        return list;
    }

    /**
     * 查询某个组织机构下面所有组织结构，返回列表
     *
     * @return
     */
    public List<SysOrganization> queryAllSubOrgs(Integer orgaizationId) {

        List<SysOrganization> allOrg = new ArrayList<>();
        List<SysOrganizationTree> trees = queryOrganizationSubTree(orgaizationId);
        for (SysOrganizationTree tree : trees) {
            List<SysOrganization> sysOrganizations = transferList(tree);
            if (!CollectionUtils.isEmpty(sysOrganizations)) {
                allOrg.addAll(sysOrganizations);
            }
        }

        return allOrg;
    }

    /**
     * 查询组织机构树，除去部门
     * @return
     */
    public List<SysOrganizationTree> queryOrganizationTreeExcludeNormal(){
        List<SysOrganizationTree> sysOrganizationTrees = queryOrganizationTree();
        shaveNormal(sysOrganizationTrees);
        return sysOrganizationTrees;
    }

    /**
     * 排除列表内的部门
     */
    private void shaveNormal(List<SysOrganizationTree> treeList){
        Iterator<SysOrganizationTree> iterator = treeList.iterator();
        while(iterator.hasNext()){
            SysOrganizationTree next = iterator.next();
            if(next.getfType() == Integer.parseInt(ContractEnum.EnumOrgType.Dept.getCode())){
                iterator.remove();
            }else{
                shaveNormal(next.getChildNodeList());
            }
        }
    }

    private List<SysOrganization> transferList(SysOrganizationTree tree) {
        if (tree == null) {
            return null;
        }

        List<SysOrganization> list = new ArrayList<>();

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(tree, org);
        list.add(org);
        if (!CollectionUtils.isEmpty(tree.getChildNodeList())) {
            for (SysOrganizationTree subTree : tree.getChildNodeList()) {
                List<SysOrganization> sysOrganizations = transferList(subTree);
                if (!CollectionUtils.isEmpty(sysOrganizations)) {
                    list.addAll(sysOrganizations);
                }
            }
        }

        return list;
    }

    public String getAbsPath(Integer orgId){
        StringBuilder sb = new StringBuilder();
        addPath(orgId, sb);
        return sb.toString();
    }

    public void addPath(Integer orgId, StringBuilder sb){
        if(orgId == null){
            return;
        }

        SysOrganization sysOrganization = queryOrganization(orgId);
        if(sysOrganization == null){
            return;
        }

        addPath(sysOrganization.getFkParentId(), sb);

        if(!StringUtils.isEmpty(sysOrganization.getfName())){
            sb.append("/").append(sysOrganization.getfName());
        }
    }

    /**
     * 查询当前单位的下的所有部门
     * @return
     */
    public List<SysOrganizationTree> queryAllDeptInUnit(Integer... unitIds){
        List<SysOrganizationTree> sysOrganizationTrees = queryOrganizationSubTree(unitIds);

        sysOrganizationTrees.forEach(tree->{
            List<SysOrganizationTree> childNodeList = tree.getChildNodeList();
            List<SysOrganizationTree> filterlst  = childNodeList.stream().filter(org -> ContractEnum.EnumOrgType.Dept.getCode().equals(org.getfType().toString())).collect(Collectors.toList());
            tree.setChildNodeList(filterlst);
        });

        return sysOrganizationTrees;
    }

    /**
     * 根据编码查询组织机构
     * @param orgCode
     * @return
     */
    public SysOrganization queryOrganizationByCode(String orgCode){
        Map<String, Object> params = new HashMap<>();
        params.put("code", orgCode);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/system/queryOrganizationByCode?code={code}", params);

        SysOrganizationTree organizationTree = null;
        try {
            DataResult<SysOrganizationTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<SysOrganizationTree>>() {
            });
            organizationTree = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if (organizationTree == null) {
            return organizationTree;
        }

        SysOrganization org = new SysOrganization();
        BeanUtils.copyProperties(organizationTree, org);

        return org;
    }

    /**
     * 根据条件查询出企业或者部门的列表
     * @param parentCode 当前类型的父类code
     * @param fType 0:部门  2:企业
     * @return
     */
    public List<SysOrganization> queryOrganizationByParam(String parentCode , int fType) {
    	Map<String, Object> params = new HashMap<>();
        params.put("fk_parent_code", parentCode);
        params.put("f_type", fType);
        
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/system/getOrganization", params, null);

        List<SysOrganization> organization = null;
        try {
            DataResult<List<SysOrganization>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysOrganization>>>() {
            });
            organization = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return organization;
    }

    public List<SysOrganization> queryNextAllOrgById(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("fId", id);
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/systemOrg/queryNextAllOrgById", params, null);

        List<SysOrganization> organization = null;
        try {
            DataResult<List<SysOrganization>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysOrganization>>>() {
            });
            organization = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return organization;
    }
}
