package com.jy.study.oauth2.resourceServer.controller;

import com.jy.study.oauth2.resourceServer.model.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working..";
    }

    //developer Role을 가진 User만 해당 api를 실행 가능.
    @Secured("ROLE_developer")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }

    /*
     * 메서드를 실행하기 전에 권한을 체크한다.
     * developer Role이 있는지 체크하거나 (hasAuthority('ROLE_developer')로 체크해도 된다.) ,
     * 메서드의 arguments를 이용해서 id가 jwt의 getSubject 메서드를 사용해서 가져온 값과 일치하는지 확인한다.
     */
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    @PutMapping("/{id}/name")
    public String updateUserName(
            @PathVariable String id,
            String name,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return "updated user name as " + name + " and JWT subject " + jwt.getSubject();
    }

    /*
     * 메서드를 실행하고 권한을 체크한다.
     * returnObject(return 하는 User 객체)의 id와 argument인 jwt의 subject가 같은지 확인.
     */
    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping("{id}")
    public User getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new User(id, "joonyeop", "kim");
    }

}