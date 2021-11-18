package com.scoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();
        // @formatter:on
    }
}
