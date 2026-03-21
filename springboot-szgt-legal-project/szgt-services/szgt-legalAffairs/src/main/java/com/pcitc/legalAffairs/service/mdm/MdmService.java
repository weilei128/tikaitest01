package com.pcitc.legalAffairs.service.mdm;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.service.mdm.config.MdmConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @description
 * @author leigang
 * @date 2020年4月17日 14:14:38
 *
 */
@Service
@Slf4j
public class MdmService {

    private MdmConfig mdmConfig;

    @Autowired
    public void setMdmConfig(MdmConfig mdmConfig) {
        this.mdmConfig = mdmConfig;
    }

    public Object getMdmData(String method, Object... objectParams) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(mdmConfig.getUrl());
        Object[] objects;
        try {
            objects = client.invoke(method, objectParams);
            log.info(JSON.toJSONString(objects[0]));
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), 500);
        }
    }

}
