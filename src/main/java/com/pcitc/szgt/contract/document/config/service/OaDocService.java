package com.pcitc.szgt.contract.document.config.service;

import com.pcitc.szgt.contract.document.config.model.Enclosures;
import com.pcitc.szgt.contract.document.config.model.OaResult;
import com.pcitc.szgt.contract.document.config.model.PushScope;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService(targetNamespace = "OaDoc")
public interface OaDocService {

    OaResult getOaDoc(@WebParam(name = "createCode") String createCode,
                      @WebParam(name = "createDept") String createDept,
                      @WebParam(name = "createCompany") String createCompany,
                      @WebParam(name = "createTime") String createTime,
                      @WebParam(name = "pushScope")List<PushScope> pushScope,
                      @WebParam(name = "pushTime") String pushTime,
                      @WebParam(name = "subject") String subject,
                      @WebParam(name = "tempateName") String tempateName,
                      @WebParam(name = "baseinfoUrl") String baseinfoUrl,
                      @WebParam(name = "contentUrl") String contentUrl,
                      @WebParam(name = "enclosures") List<Enclosures> enclosures,
                      @WebParam(name = "param1") String param1,
                      @WebParam(name = "param2") String param2,
                      @WebParam(name = "param3") String param3);

}
