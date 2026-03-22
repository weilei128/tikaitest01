package com.oo.datamanagement.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.StringUtils;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.datamanagement.service.IDcMdOrganizationService;
import com.oo.datamanagement.service.IDcMdProjectService;
import com.oo.datamanagement.service.IDcMdWellService;
import com.oo.system.api.domain.DcMdOrganization;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Feign实现类
 *
 * @author
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class DataManagementClient implements RemoteDataManageService {

    private final IDcMdProjectService dcMdProjectService;
    private final IDcMdOrganizationService dcMdOrganizationService;
    private final IDcMdWellService dcMdWellService;

    /**
     * 查询地质单元--区块管理列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public R<List<DcMdProject>> selectProjectList(MdQueryDTO queryDTO) {
        QueryWrapper<DcMdProject> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryDTO.getNames())) {
            queryWrapper.in("name", queryDTO.getNames());
        }
        if (StringUtils.isNotEmpty(queryDTO.getType())) {
            queryWrapper.eq("type", queryDTO.getType());
        }
        return R.ok(dcMdProjectService.list(queryWrapper));
    }

    /**
     * 查询组织机构层级关系--项目管理列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public R<List<DcMdOrganization>> selectOrganizationList(MdQueryDTO queryDTO) {
        QueryWrapper<DcMdOrganization> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryDTO.getNames())) {
            queryWrapper.in("name", queryDTO.getNames());
        }
        if (StringUtils.isNotEmpty(queryDTO.getType())) {
            queryWrapper.eq("type", queryDTO.getType());
        }
        return R.ok(dcMdOrganizationService.list(queryWrapper));
    }

    /**
     * 查询井列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public R<List<DcMdWell>> selectWellList(MdQueryDTO queryDTO) {
        QueryWrapper<DcMdWell> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryDTO.getNames())) {
            queryWrapper.in("wellname", queryDTO.getNames());
        }
        return R.ok(dcMdWellService.list(queryWrapper));
    }

    /**
     * 查询地质单元--区块详情
     *
     * @param projectId
     * @return
     */
    @Override
    public R<DcMdProject> getProject(String projectId) {
        return R.ok(dcMdProjectService.getById(projectId));
    }

    /**
     * 查询组织机构层级关系--项目详情
     *
     * @param organizationId
     * @return
     */
    @Override
    public R<DcMdOrganization> getOrganization(String organizationId) {
        return R.ok(dcMdOrganizationService.getById(organizationId));
    }

    /**
     * 获取组织机构名称
     *
     * @param organizationId 组织机构id
     * @return 组织机构名称
     */
    @Override
    public R<String> getOrganizationName(String organizationId) {
        DcMdOrganization organization = dcMdOrganizationService.getById(organizationId);
        if (organization == null) {
            return R.ok(null);
        }
        return R.ok(organization.getName(), "操作成功");
    }

    /**
     * 获取区块/井场名称
     *
     * @param projectId 区块/井场id
     * @return 区块/井场名称
     */
    @Override
    public R<String> getProjectName(String projectId) {
        DcMdProject project = dcMdProjectService.getById(projectId);
        if (project == null) {
            return R.ok(null);
        }
        return R.ok(project.getName(), "操作成功");
    }

    /**
     * 获取井名称
     *
     * @param wellId 井id
     * @return 井名称
     */
    @Override
    public R<String> getWellName(String wellId) {
        DcMdWell well = dcMdWellService.getById(wellId);
        if (well == null) {
            return R.ok(null);
        }
        return R.ok(well.getWellname(), "操作成功");
    }
}