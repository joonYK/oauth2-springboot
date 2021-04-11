package com.jy.study.oauth2.legacyUsersService.service;

import com.jy.study.oauth2.legacyUsersService.response.UserRest;

public interface UsersService {
   UserRest getUserDetails(String userName, String password);
   UserRest getUserDetails(String userName);
}
