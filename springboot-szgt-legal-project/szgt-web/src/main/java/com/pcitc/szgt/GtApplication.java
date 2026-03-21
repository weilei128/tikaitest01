package com.pcitc.szgt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = {"com.pcitc.legalAffairs.mapper","com.pcitc.system.mapper"})
@EnableScheduling //开启定时任务
@EnableAsync//开启异步任务
@ComponentScan(basePackages = {"com.pcitc"})
public class GtApplication {

    public static void main(String[] args) {
        SpringApplication.run(GtApplication.class, args);
    }

}
