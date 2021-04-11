package com.jy.study.oauth2.legacyUsersService.controllers;


import com.jy.study.oauth2.legacyUsersService.response.UserRest;
import com.jy.study.oauth2.legacyUsersService.response.VerifyPasswordResponse;
import com.jy.study.oauth2.legacyUsersService.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{userName}")
    public UserRest getUser(@PathVariable("userName") String userName) {
        return usersService.getUserDetails(userName);
    }

    @PostMapping("/{userName}/verify-password")
    public VerifyPasswordResponse verifyUserPassword(@PathVariable("userName") String userName,
                                                     @RequestBody String password) {

        VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);

        UserRest user = usersService.getUserDetails(userName, password);

        if (user != null) {
            returnValue.setResult(true);
        }

        return returnValue;
    }

}
