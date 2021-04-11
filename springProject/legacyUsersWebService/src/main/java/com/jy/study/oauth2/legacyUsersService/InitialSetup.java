package com.jy.study.oauth2.legacyUsersService;


import com.jy.study.oauth2.legacyUsersService.data.UserEntity;
import com.jy.study.oauth2.legacyUsersService.data.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitialSetup {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                1L,
                "qswe3mg84mfjtu",
                "joonyeop",
                "kim",
                "test2@test.com",
                bCryptPasswordEncoder.encode("joonyeop"),
                "",
                false);

        usersRepository.save(user);
    }
}
