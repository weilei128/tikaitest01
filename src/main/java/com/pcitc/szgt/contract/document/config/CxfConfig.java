package com.pcitc.szgt.contract.document.config;

import com.pcitc.szgt.contract.document.config.service.OaDocService;
import com.pcitc.szgt.contract.document.config.service.impl.OaDocServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherNewServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/fromoa/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public OaDocService getOaDocService(){
        return new OaDocServiceImpl();
    }

    @Bean
    public Endpoint oaDocService(){
        EndpointImpl endpoint = new EndpointImpl(springBus(), getOaDocService());
        endpoint.publish("/service/oaDocService");
        return endpoint;
    }


}
