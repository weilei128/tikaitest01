package com.pcitc.legalAffairs.service.mdm.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/***
 * @description 配置文件
 * @author leigang
 * @date 2020年4月17日 14:18:11
 *
 */
@Configuration
@Data
@ToString
public class MdmConfig {

    @Value("${mdm.url}")
    private String url;

}
