package com.oo.reportforms;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.oo.common.security.annotation.EnableCustomConfig;
import com.oo.common.security.annotation.EnableRyFeignClients;
import com.oo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 报表分析模块
 *
 * @author oo
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableMPP
public class ReportFormsApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(ReportFormsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  报表模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
