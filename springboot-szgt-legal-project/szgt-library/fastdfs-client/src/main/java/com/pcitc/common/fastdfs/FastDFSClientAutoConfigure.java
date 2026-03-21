package com.pcitc.common.fastdfs;

import com.pcitc.common.fastdfs.service.FastDFSService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(value = FastDFSClientProperties.class)
public class FastDFSClientAutoConfigure {


    private FastDFSClientProperties fastDFSClientProperties;

    @Autowired
    public void setFastDFSClientProperties(FastDFSClientProperties fastDFSClientProperties) {
        this.fastDFSClientProperties = fastDFSClientProperties;
    }

    @Bean
    public FastDFSService fastDFSService() throws IOException, MyException {
        return new FastDFSService(fastDFSClientProperties.generateProperties());
    }

}
