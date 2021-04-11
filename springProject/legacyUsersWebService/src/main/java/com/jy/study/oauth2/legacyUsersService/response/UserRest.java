package com.jy.study.oauth2.legacyUsersService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
}
