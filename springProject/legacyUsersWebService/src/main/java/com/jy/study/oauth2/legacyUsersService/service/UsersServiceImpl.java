package com.jy.study.oauth2.legacyUsersService.service;

import com.jy.study.oauth2.legacyUsersService.data.UserEntity;
import com.jy.study.oauth2.legacyUsersService.data.UsersRepository;
import com.jy.study.oauth2.legacyUsersService.response.UserRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserRest getUserDetails(String userName) {
        UserRest returnValue = new UserRest();

        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (userEntity == null) {
            return returnValue;
        }

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserRest getUserDetails(String userName, String password) {
        UserRest returnValue = null;

        UserEntity userEntity = usersRepository.findByEmail(userName);

        if (userEntity == null) {
            return returnValue;
        }

        if (bCryptPasswordEncoder.matches(password,
                userEntity.getEncryptedPassword())) {
            System.out.println("password matches!!!");

            returnValue = new UserRest();
            BeanUtils.copyProperties(userEntity, returnValue);

        }

        return returnValue;
    }

}
