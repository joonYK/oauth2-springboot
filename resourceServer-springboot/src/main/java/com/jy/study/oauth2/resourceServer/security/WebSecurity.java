package com.jy.study.oauth2.resourceServer.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 모든 request 권한 검사.
         * oauth2 리소스 서버 및 jwt 토큰 사용
         */
        http.authorizeRequests()
                .anyRequest().authenticated()
             .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
