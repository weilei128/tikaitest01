//package com.pcitc.szgt.contract.config.oauthconfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private CustomUserDetailService userDetailService;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // demo1_clientsecret
//        clients.inMemory().withClient("cmis").
//                secret("{bcrypt}$2a$10$JqgNMBLIQ8h/xRWZJZvrZ.6asU2UOnmAB5daE8p2fi003etKhbu2i").
//                resourceIds("abc").
//                scopes("scope").
//                authorizedGrantTypes("password", "refresh_token").
//                authorities("read", "write");
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients().
//                tokenKeyAccess("permitAll()").
//                checkTokenAccess("permitAll()");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .userDetailsService(userDetailService)
//                .authenticationManager(authenticationManager)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .reuseRefreshTokens(false);
//    }
//}
