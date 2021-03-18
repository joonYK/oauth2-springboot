package com.jy.study.oauth2.resourceServer.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

//Method Level 보안 활성화
@EnableGlobalMethodSecurity(
        //@Secured 애노테이션 사용
        securedEnabled = true,
        //@PreAuthorize, @PostAuthorize 애노테이션 사용
        prePostEnabled = true
        )
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        /**
         * 모든 request 권한 검사.
         * oauth2 리소스 서버 및 jwt 토큰 사용
         */
        http.authorizeRequests()
                // GET "/users/status/check" url 대해
                .antMatchers(HttpMethod.GET, "/users/status/check")
                    //profile scope를 포함하는지 체크. authority 체크는 prefix를 붙여서 검사.
                    //.hasAuthority("SCOPE_profile")

                    //develpoer Role을 포함하는지 체크. role 체크는 prefix를 붙이지 않고 검사.
                    .hasRole("developer")

                    //여러개의 Role을 체크
                    //.hasAnyRole("developer", "user")
                .anyRequest().authenticated()
             .and()
                .oauth2ResourceServer()
                .jwt()
                //keycloak roles -> GrantedAuthority 변환
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }
}
