package com.oo.system.feign;

import com.oo.common.core.domain.R;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.redis.service.RedisService;
import com.oo.common.security.utils.TranslateUtils;
import com.oo.system.api.cache.SystemCache;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.feign.RemoteMenuService;
import com.oo.system.service.ISysBussnessWdpService;
import com.oo.system.service.ISysDocMenuBusinessService;
import com.oo.system.service.ISysDocMenuWdpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 菜单服务Feign实现类
 *
 * @author
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class MenuClient implements RemoteMenuService {

    private final ISysDocMenuWdpService sysDocMenuWdpService;
    private final ISysDocMenuBusinessService sysDocMenuBusinessService;
    private final ISysBussnessWdpService sysBussnessWdpService;

    /**
     * 查询wdp树形列表
     *
     * @param wdpName
     * @return 结果
     */
    @Override
    public R<List<TreeSelect>> selectWdpTreeList(String wdpName) {
        return R.ok(sysDocMenuWdpService.selectSysDocMenuWdpNameList(wdpName));
    }

    /**
     * 查询wdp列表
     *
     * @param
     * @return
     */
    @Override
    public R<List<SysDocMenuWdp>> selectWdpList() {
        return R.ok(sysDocMenuWdpService.selectSysDocMenuWdpList(new SysDocMenuWdp()));
    }

    /**
     * 查询业务域树形列表
     *
     * @param businessName
     * @return 结果
     */
    @Override
    public R<List<TreeSelect>> selectBusinessTreeList(String businessName) {
        return R.ok(sysDocMenuBusinessService.selectDocMenuBusinessNameList(businessName));
    }

    /**
     * 查询业务域列表
     *
     * @param
     * @return
     */
    @Override
    public R<List<SysDocMenuBusiness>> selectBusinessList() {
        return R.ok(sysDocMenuBusinessService.selectDocMenuBusinessList(new SysDocMenuBusiness()));
    }

    /**
     * 获取wdp名称
     *
     * @param wdpId wdpId
     * @return wdp名称
     */
    @Override
    public R<String> getWdpName(String wdpId) {
        if (StringUtils.isEmpty(wdpId)) {
            return null;
        }
        List<SysTranslate> translateList = TranslateUtils.getLangTranslateCache("docMenuWdp", null);
        if (translateList != null && !translateList.isEmpty()) { //查对应的翻译缓存
            SysTranslate item = translateList.stream().filter(a -> a.getFieldId().equals(Long.parseLong(wdpId))).findFirst().orElse(null);
            if (item != null) {
                return R.ok(item.getContent(), "操作成功");
            }
        }
        String result = null;
        //没有对应翻译 先从缓存查询
        if (SpringUtils.getBean(RedisService.class).hasKey(SystemCache.WDP_NAME + wdpId)) {
            result = SpringUtils.getBean(RedisService.class).getCacheObject(SystemCache.WDP_NAME + wdpId);
        }
        else { //没有缓存 从数据库查
            SysDocMenuWdp docMenuWdp = sysDocMenuWdpService.getInfo(Long.parseLong(wdpId));
            if (docMenuWdp != null) {
                result = docMenuWdp.getWdpName();
                SpringUtils.getBean(RedisService.class).setCacheObject(SystemCache.WDP_NAME + wdpId, result);
            }
        }
        return R.ok(result, "操作成功");
    }

    /**
     * 获取业务域文件夹名称
     *
     * @param businessId 业务域id
     * @return 业务域文件名称
     */
    @Override
    public R<String> getBusinessDocName(String businessId) {
        if (StringUtils.isEmpty(businessId)) {
            return null;
        }
        List<SysTranslate> translateList = TranslateUtils.getLangTranslateCache("docMenuBusiness", null);
        if (translateList != null && !translateList.isEmpty()) { //查对应的翻译缓存
            SysTranslate item = translateList.stream().filter(a -> a.getFieldId().equals(Long.parseLong(businessId))).findFirst().orElse(null);
            if (item != null) {
                return R.ok(item.getContent(), "操作成功");
            }
        }
        String result = null;
        //没有对应翻译 先从缓存查询
        if (SpringUtils.getBean(RedisService.class).hasKey(SystemCache.BUSINESS_DOC_NAME + businessId)) {
            result = SpringUtils.getBean(RedisService.class).getCacheObject(SystemCache.BUSINESS_DOC_NAME + businessId);
        }
        else { //没有缓存 从数据库查
            SysDocMenuBusiness docMenuBusiness = sysDocMenuBusinessService.getInfo(Long.parseLong(businessId));
            if (docMenuBusiness != null) {
                result = docMenuBusiness.getBusinessName();
                SpringUtils.getBean(RedisService.class).setCacheObject(SystemCache.BUSINESS_DOC_NAME + businessId, result);
            }
        }
        return R.ok(result, "操作成功");
    }

    /**
     * 获取某个业务域id下所有子节点的id列表(包括当前节点)
     *
     * @param businessId 业务域id
     * @return
     */
    @Override
    public R<List<Long>> selectChildIds(Long businessId) {
        return R.ok(sysDocMenuBusinessService.selectChildIds(businessId));
    }

    /**
     * 根据业务域id查询对应的wdpId列表
     *
     * @param businessId 业务域id
     * @return wdpId列表
     */
    @Override
    public R<List<Long>> selectWdpIdsByBusinessId(Long businessId) {
        return R.ok(sysBussnessWdpService.selectWdpIdsByBusinessId(businessId));
    }
}
