package com.pcitc.szgt.contract.config.oauthconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableResourceServer
public class OauthResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().formLogin().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/webjars/**", "/v2/api-docs", "/swagger-resources/**").permitAll()
                .antMatchers("/*.html", "/*.js", "/*.css").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/poserver.zz", "/posetup.exe", "/sealsetup.exe").permitAll()
                .antMatchers("/dpscallback/*").permitAll()
                .antMatchers("/workflow/makeApprove",
                        "/workflow/changeApprove",
                        "/workflow/transApprove",
                        "/workflow/endApprove",
                        "/workflow/treatmentApprove",
                        "/workflow/getFlowChangeUser").permitAll()
                .antMatchers("/fromoa/**").permitAll()
                .antMatchers("/pgactive", "/getFile2").permitAll()
                .antMatchers("/weboffice/*.html").permitAll()
                .antMatchers("/offereemanage/manualMDMsyn", "/offereemanage/query").permitAll()
                .antMatchers("/iac/financial/proof").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/documentinformation/callBackDocument/**").permitAll()
                .antMatchers("/**").authenticated()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(new RedisTokenStore(redisConnectionFactory));
        resources.resourceId("springsec").stateless(true);
    }
}
