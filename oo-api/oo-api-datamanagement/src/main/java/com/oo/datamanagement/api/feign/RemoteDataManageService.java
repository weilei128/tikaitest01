package com.oo.datamanagement.api.feign;

import com.oo.common.core.constant.ServiceNameConstants;
import com.oo.common.core.domain.R;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.factory.RemoteDataManageFallbackFactory;
import com.oo.system.api.domain.DcMdOrganization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 数据管理feign接口
 */
@FeignClient(contextId = "remoteDataManageService", value = ServiceNameConstants.DATA_MANAGE_SERVICE, fallbackFactory = RemoteDataManageFallbackFactory.class)
public interface RemoteDataManageService {

    String API_PREFIX = "/client";

    String PROJECT_LIST = API_PREFIX + "/project-list";
    String ORGANIZATION_LIST = API_PREFIX + "/organization-list";
    String WELL_LIST = API_PREFIX + "/well-list";
    String PROJECT_INFO = API_PREFIX + "/project-info";
    String ORGANIZATION_INFO = API_PREFIX + "/organization-info";
    String GET_ORG_NAME = API_PREFIX + "/organization-name";
    String GET_PROJECT_NAME = API_PREFIX + "/project-name";
    String GET_WELL_NAME = API_PREFIX + "/well-name";

    /**
     * 查询地质单元--区块管理列表
     *
     * @param queryDTO
     * @return
     */
    @GetMapping(PROJECT_LIST)
    R<List<DcMdProject>> selectProjectList(@SpringQueryMap MdQueryDTO queryDTO);

    /**
     * 查询组织机构层级关系--项目管理列表
     *
     * @param queryDTO
     * @return
     */
    @GetMapping(ORGANIZATION_LIST)
    R<List<DcMdOrganization>> selectOrganizationList(@SpringQueryMap MdQueryDTO queryDTO);

    /**
     * 查询井列表
     *
     * @param queryDTO
     * @return
     */
    @GetMapping(WELL_LIST)
    R<List<DcMdWell>> selectWellList(@SpringQueryMap MdQueryDTO queryDTO);

    /**
     * 查询地质单元--区块详情
     *
     * @param projectId
     * @return
     */
    @GetMapping(PROJECT_INFO)
    R<DcMdProject> getProject(@RequestParam("projectId") String projectId);

    /**
     * 查询组织机构层级关系--项目详情
     *
     * @param organizationId
     * @return
     */
    @GetMapping(ORGANIZATION_INFO)
    R<DcMdOrganization> getOrganization(@RequestParam("organizationId") String organizationId);

    /**
     * 获取组织机构名称
     *
     * @param organizationId 组织机构id
     * @return 组织机构名称
     */
    @GetMapping(GET_ORG_NAME)
    R<String> getOrganizationName(@RequestParam("organizationId") String organizationId);

    /**
     * 获取区块/井场名称
     *
     * @param projectId 区块/井场id
     * @return 区块/井场名称
     */
    @GetMapping(GET_PROJECT_NAME)
    R<String> getProjectName(@RequestParam("projectId") String projectId);

    /**
     * 获取井名称
     *
     * @param wellId 井id
     * @return 井名称
     */
    @GetMapping(GET_WELL_NAME)
    R<String> getWellName(@RequestParam("wellId") String wellId);
}
