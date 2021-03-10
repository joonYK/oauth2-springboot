package com.jy.study.oauth2.resourceServer.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("401 에러 테스트")
    public void unauthorizedTest() throws Exception {
        mockMvc.perform(get("/users/check/status"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}

