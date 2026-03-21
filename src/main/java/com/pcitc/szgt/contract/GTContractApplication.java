package com.pcitc.szgt.contract;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.pcitc.szgt.contract.config.FinancialConfig;
import com.pcitc.szgt.contract.config.MainDataConfig;
import com.pcitc.szgt.contract.config.attachment.AttachmentConfig;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableSwagger2
@MapperScan(value = "com.pcitc.szgt.contract.*.mapper")
@EnableConfigurationProperties({AttachmentConfig.class, ShareConfig.class, MainDataConfig.class, PageOfficeConfig.class, FinancialConfig.class})
public class GTContractApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTContractApplication.class, args);
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${posyspath}")
    private String poSysPath;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws IOException {
        com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
        //设置PageOffice注册成功后,license.lic文件存放的目录
        poserver.setSysPath(poSysPath);
        ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
        srb.addUrlMappings("/poserver.zz");
        srb.addUrlMappings("/posetup.exe");
        srb.addUrlMappings("/pageoffice.js");
        srb.addUrlMappings("/jquery.min.js");
        srb.addUrlMappings("/pobstyle.css");
        srb.addUrlMappings("/sealsetup.exe");
        return srb;//
    }

}
