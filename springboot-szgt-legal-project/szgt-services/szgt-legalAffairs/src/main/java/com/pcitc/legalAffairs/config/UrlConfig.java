package com.pcitc.legalAffairs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UrlConfig {

	/**
	 * 法务系统(本系统)后端URL
	 */
	@Value("${law.url}")
	private String lawUrl;
	/**
	 * 文件存储路径URL
	 */
	@Value("${filestorage.url}")
	private String fileStorage;
}
