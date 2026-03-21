package com.pcitc.szgt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhuozhengsoft.pageoffice.poserver.Server;

/**
 * PageOffice初始化
 * @author meihongli
 *
 */
@Configuration
public class PageOfficeInitConfig {

	@Value("${pageoffice.posyspath}")
	private String posyspath;
	
	@Bean
	public ServletRegistrationBean<Server> servletRegistrationBean() {
		Server poserver = new Server();
		poserver.setSysPath(posyspath);
		ServletRegistrationBean<Server> srb = new ServletRegistrationBean<>(poserver);
		srb.addUrlMappings("/poserver.zz", "/posetup.exe", "/pageoffice.js", "/jquery.min.js", "/pobstyle.css", "/sealsetup.exe");
		return srb;
	}
	
	public void setPosyspath(String posyspath) {
		this.posyspath = posyspath;
	}
}
