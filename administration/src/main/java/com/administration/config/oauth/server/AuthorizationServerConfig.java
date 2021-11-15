package com.administration.config.oauth.server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${app.oauth.jwt.secret}")
    private String jwtSecret;

    @Value("${app.oauth.jwt.keystore-location}")
    private String keyStorePath;

    @Value("${app.oauth.jwt.keystore-password}")
    private String keyStorePassword;

    @Value("${app.oauth.jwt.key-alias}")
    private String keyAlias;

    @Value("${app.oauth.jwt.private-key-passphrase}")
    private String privateKeyPassphrase;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

//
//    @Autowired
//    private UserDetailsService userDetailsService;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(jdbcClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService)
        ;
    }

    @Bean
    public TokenStore tokenStore() {
        JdbcTokenStore store = new JdbcTokenStore(dataSource);
        return store;

    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtSecret);
        return converter;
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//
//        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
//                new ClassPathResource(keyStorePath), keyStorePassword.toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias));
//        return converter;
//    }

//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setAccessTokenValiditySeconds(3600);
//        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
//        defaultTokenServices.setAuthenticationManager(authenticationManager);
//        return defaultTokenServices;
//    }

//    @Scheduled(fixedRate = 1800000)
//    public void purgeOldTokens() {
//        JdbcTemplate jdbcTemplate;
//        jdbcTemplate = new JdbcTemplate(dataSource);
//        Date now = new Date();
//        jdbcTemplate.update("delete from oauth_access_token where expiration <?", now);
//    }
}
