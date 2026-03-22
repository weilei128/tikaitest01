package com.oo.system.api.feign;

import com.oo.common.core.constant.ServiceNameConstants;
import com.oo.common.core.domain.R;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.factory.RemoteMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 菜单服务
 */
@FeignClient(contextId = "remoteMenuService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteMenuFallbackFactory.class)
public interface RemoteMenuService {

    String API_PREFIX = "/client";
    String WDP_TREE_LIST = API_PREFIX + "/wdpTreeList";
    String WDP_LIST = API_PREFIX + "/wdpList";
    String BUSINESS_TREE_LIST = API_PREFIX + "/businessTreeList";
    String BUSINESS_LIST = API_PREFIX + "/businessList";
    String GET_BUSINESS_NAME = API_PREFIX + "/businessName";
    String GET_WDP_NAME = API_PREFIX + "/wdpName";
    String CHILD_IDS = API_PREFIX + "/selectChildIds";
    String WDP_IDS_BY_BUSINESS_ID = API_PREFIX + "/selectWdpIdsByBusinessId";

    /**
     * 查询wdp树形列表
     *
     * @param wdpName
     * @return 结果
     */
    @GetMapping(WDP_TREE_LIST)
    R<List<TreeSelect>> selectWdpTreeList(String wdpName);

    /**
     * 查询wdp列表
     *
     * @param
     * @return 结果
     */
    @GetMapping(WDP_LIST)
    R<List<SysDocMenuWdp>> selectWdpList();

    /**
     * 查询业务域树形列表
     *
     * @param businessName
     * @return 结果
     */
    @GetMapping(BUSINESS_TREE_LIST)
    R<List<TreeSelect>> selectBusinessTreeList(String businessName);

    /**
     * 查询业务域列表
     *
     * @param
     * @return 结果
     */
    @GetMapping(BUSINESS_LIST)
    R<List<SysDocMenuBusiness>> selectBusinessList();

    /**
     * 获取wdp名称
     *
     * @param wdpId wdpId
     * @return wdp名称
     */
    @GetMapping(GET_WDP_NAME)
    R<String> getWdpName(@RequestParam("wdpId") String wdpId);

    /**
     * 获取业务域文件夹名称
     *
     * @param businessId 业务域id
     * @return 业务域文件夹名称
     */
    @GetMapping(GET_BUSINESS_NAME)
    R<String> getBusinessDocName(@RequestParam("businessId") String businessId);

    /**
     * 获取某个业务域id下所有子节点的id列表(包括当前节点)
     *
     * @param businessId 业务域id
     * @return
     */
    @GetMapping(CHILD_IDS)
    R<List<Long>> selectChildIds(@RequestParam("businessId") Long businessId);

    /**
     * 根据业务域id查询对应的wdpId列表
     *
     * @param businessId 业务域id
     * @return wdpId列表
     */
    @GetMapping(WDP_IDS_BY_BUSINESS_ID)
    R<List<Long>> selectWdpIdsByBusinessId(@RequestParam("businessId") Long businessId);
}
