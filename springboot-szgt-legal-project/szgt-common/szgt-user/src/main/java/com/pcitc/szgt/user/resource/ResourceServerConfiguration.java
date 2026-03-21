package com.pcitc.szgt.user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/***
 * @description 资源服务器
 * @author leigang
 * @date 2019年10月22日 13:49:04
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    private RedisTokenStore redisTokenStore;

    private static final String resourcesId = "springsec";

    @Autowired
    public void setRedisTokenStore(RedisTokenStore redisTokenStore) {
        this.redisTokenStore = redisTokenStore;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(redisTokenStore);
        resources.resourceId(resourcesId).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll() //
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/*.html").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/common/**").permitAll()
                .antMatchers("/service/**").permitAll()
                .antMatchers("/dpst-web/**").permitAll()
                .antMatchers("/**").authenticated()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();

    }

}
