package com.oo.report;

import com.oo.common.security.annotation.EnableCustomConfig;
import com.oo.common.security.annotation.EnableRyFeignClients;
import com.oo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 报表分析
 * 
 * @author oo
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class ReportApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReportApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  报表分析模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
