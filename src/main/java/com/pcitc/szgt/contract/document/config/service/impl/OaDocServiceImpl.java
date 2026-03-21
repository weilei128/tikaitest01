package com.pcitc.szgt.contract.document.config.service.impl;

import com.pcitc.szgt.contract.document.config.model.Enclosures;
import com.pcitc.szgt.contract.document.config.model.OaResult;
import com.pcitc.szgt.contract.document.config.model.PushScope;
import com.pcitc.szgt.contract.document.config.service.OaDocService;
import com.pcitc.szgt.contract.make.modelEx.Accord;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(name = "oadocService",
            targetNamespace = "OaDoc",
            endpointInterface = "com.pcitc.szgt.contract.document.config.service.OaDocService")
public class OaDocServiceImpl implements OaDocService {

    @Autowired
    private OrganizationRequest organizationRequest;

    @Override
    public OaResult getOaDoc(String createCode, String createDept, String createCompany, String createTime, List<PushScope> pushScope, String pushTime, String subject, String tempateName, String baseinfoUrl, String contentUrl, List<Enclosures> enclosures, String param1, String param2, String param3) {

        Accord accord = new Accord();
        if(!CollectionUtils.isEmpty(pushScope)){
            String[] orgCodes = pushScope.stream().filter(item -> "1".equals(item.getUserType())).map(PushScope::getUserCode).toArray(String[]::new);

        }



        OaResult oaResult = new OaResult();
        oaResult.setMessage("ok");
        oaResult.setSuccess("true");
        return oaResult;
    }
}
