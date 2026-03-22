package com.oo.datamanagement;

import com.oo.common.security.annotation.EnableCustomConfig;
import com.oo.common.security.annotation.EnableRyFeignClients;
import com.oo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 数据管理模块
 *
 * @author oo
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class DatamanagementApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DatamanagementApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  数据管理模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
