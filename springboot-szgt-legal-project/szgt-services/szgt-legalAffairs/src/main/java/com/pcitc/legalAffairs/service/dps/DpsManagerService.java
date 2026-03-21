package com.pcitc.legalAffairs.service.dps;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.entity.Result;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppWorkflowData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * @description 工作流状态入口
 * @author leigang
 * @date 2020年3月26日 14:34:36
 *
 */
@Service
@Slf4j
public class DpsManagerService {

    private ApplicationContext applicationContext;

    private Map<String, DpsComplete> dpsCompleteMap;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T extends CallBackVo> void execute(T t) {
        DpsComplete<T> dpsComplete = getDpsComplete(t);
        if (dpsComplete != null) {
            dpsComplete.execute(t);
        }
    }

    public void startProcess(int type, StartVo startVo) {
        CallBackVo callBackVo = new CallBackVo();
        callBackVo.setCategoryCode(startVo.getCategoryCode());
        DpsComplete<CallBackVo> dpsComplete = getDpsComplete(callBackVo);
        dpsComplete.start(type, startVo);
    }

    private <T extends CallBackVo> DpsComplete<T> getDpsComplete(T t) {
        if (dpsCompleteMap == null) {
            dpsCompleteMap = applicationContext.getBeansOfType(DpsComplete.class);
        }
        DpsComplete<T> dpsComplete = null;
        Set<String> keySet = dpsCompleteMap.keySet();
        for (String key : keySet) {
            String categoryCode = dpsCompleteMap.get(key).getCategoryCode();
            if (t.getCategoryCode().equals(categoryCode)) {
                dpsComplete = dpsCompleteMap.get(key);
                break;
            }
        }
        return dpsComplete;
    }

    @Autowired
    private DpsHttpService dpsHttpService;

    public Result start(StartVo startVo) {
        String businessId = startVo.getBusinessId();
        if (StringUtils.isEmpty(businessId)) {
            throw new BaseException("业务ID不能为空 ", 500);
        }
        businessId = Utils.getHandlerStr(businessId);
        if (StringUtils.isEmpty(businessId)) {
            throw new BaseException("业务ID不存在 ", 500);
        }
        DpsCategoryEnum categoryEnum = DpsCategoryEnum.getCategoryEnum(startVo.getCategoryCode());
        if (categoryEnum == null) {
            throw new BaseException("分类编码不存在或者有误 ", 500);
        }
        //验证
        startProcess(0, startVo);

        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("categoryCode", startVo.getCategoryCode());
        bodyParams.add("ownKind", startVo.getOwnKind());
        bodyParams.add("organiseId", startVo.getOrganiseId());
        Result start = dpsHttpService.workflow(bodyParams);
        List<AppWorkflowData> workflowDataList = JSON.parseArray(JSON.toJSONString(start.getData()), AppWorkflowData.class);
        if (CollectionUtils.isEmpty(workflowDataList)) {
            throw new BaseException("不存在流程模板,请检查分类编码和流程图", 500);
        }
        AppWorkflowData appWorkflowData = workflowDataList.get(0);
        startVo.setWorkflowId(appWorkflowData.getWorkflowId());
        startVo.setAppId("111");
        Result result = dpsHttpService.start(startVo);
        if (result.getCode() != 200) {
            throw new BaseException(result.getMsg(), 500);
        }
        AppCallResult data = JSON.parseObject(JSON.toJSONString(result.getData()), AppCallResult.class);
        if (!data.getResult()) {
            throw new BaseException("工作流启动失败--" + data.getMessage(), 500);
        }
        startProcess(1, startVo);
        return result;
    }
}
