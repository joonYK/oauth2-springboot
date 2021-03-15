package com.jy.study.oauth2.resourceServer.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * KeyCloak 서버에서 발행한 access token에서 Roles를 추출하고, StpringSecurity의 GrantedAuthority로 변환
 */
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        //Jwt의 claims에는 access token의 Payload 부분이 담겨져 있다.
        //keycloak는 realm_access에 roles를 array로 저장한다.
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");

        //없으면 빈 List 반환
        if (realmAccess == null || realmAccess.isEmpty())
            return new ArrayList<>();

        //Role마다 "ROLE_" 접두사를 붙이고, SimpleGrantedAuthority로 생성 후, list로 collect
        Collection<GrantedAuthority> grantedAuthorities = ((List<String>)realmAccess.get("roles"))
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return grantedAuthorities;
    }
}
