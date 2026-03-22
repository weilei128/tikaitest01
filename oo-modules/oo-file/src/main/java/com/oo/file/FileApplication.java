package com.oo.file;

import com.oo.common.security.annotation.EnableCustomConfig;
import com.oo.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.oo.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author oo
 */
@EnableCustomConfig
@EnableRyFeignClients
@EnableCustomSwagger2
@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
