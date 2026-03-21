package com.pcitc.szgt.contract.config;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ToString
@Mapper
/*
 * 配置文件
 * */

public class CmisDefaultConfig {

    @Value("${Page.PageSize}")
    private Integer pageSize;
    @Value("${Page.PageNum}")
    private Integer pageNum;
    @Value("${DPS.AppId}")
    private String AppId;
}
