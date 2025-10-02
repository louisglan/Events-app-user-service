package com.louis.user_service.controller;

import com.louis.user_service.configuration.SecurityConfig;
import com.louis.user_service.service.UserService;
import com.louis.user_service.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {
    @MockitoBean
    @SuppressWarnings("unused")
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private static final String VALID_USER_PATH = "controller/validUser.json";

    @Test
    public void testUserCreatedSuccess() throws Exception {
        mockMvc.perform(post("/api/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonString(VALID_USER_PATH)))
                .andExpect(status().isOk());
    }
}
