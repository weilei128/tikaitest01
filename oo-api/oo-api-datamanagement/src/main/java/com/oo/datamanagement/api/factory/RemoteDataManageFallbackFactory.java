package com.oo.datamanagement.api.factory;

import com.oo.common.core.domain.R;
import com.oo.datamanagement.api.domain.DcMdProject;
import com.oo.datamanagement.api.domain.DcMdWell;
import com.oo.datamanagement.api.dto.MdQueryDTO;
import com.oo.datamanagement.api.feign.RemoteDataManageService;
import com.oo.system.api.domain.DcMdOrganization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 数据管理服务降级处理
 *
 * @author
 */
@Component
public class RemoteDataManageFallbackFactory implements FallbackFactory<RemoteDataManageService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteDataManageFallbackFactory.class);

    @Override
    public RemoteDataManageService create(Throwable throwable) {
        log.error("数据管理服务调用失败:{}", throwable.getMessage());
        return new RemoteDataManageService()
        {
            @Override
            public R<List<DcMdProject>> selectProjectList(@SpringQueryMap MdQueryDTO queryDTO)
            {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<DcMdOrganization>> selectOrganizationList(@SpringQueryMap MdQueryDTO queryDTO)
            {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<DcMdWell>> selectWellList(@SpringQueryMap MdQueryDTO queryDTO)
            {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<DcMdProject> getProject(@RequestParam String projectId)
            {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<DcMdOrganization> getOrganization(@RequestParam String organizationId)
            {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getOrganizationName(@RequestParam("organizationId") String organizationId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getProjectName(@RequestParam("projectId") String projectId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getWellName(@RequestParam("wellId") String wellId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
        };
    }
}
