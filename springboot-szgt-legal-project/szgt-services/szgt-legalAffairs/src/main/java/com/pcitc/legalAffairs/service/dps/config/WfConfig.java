package com.pcitc.legalAffairs.service.dps.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/***
 * @description 工作流ip地址
 * @author leigang
 * @date 2020年3月27日 09:48:47
 *
 */
@Configuration
@Data
public class WfConfig {

    @Value("${dps.url}")
    private String url;

}
