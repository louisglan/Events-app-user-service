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

// The @WebMvcTest annotation allows you to use MockMvc but only loads the web layer - it is more lightweight than
// @SpringBootTest and is better suited to controller tests. Passing in UserController only instantiates the controller
// you pass in
@WebMvcTest(UserController.class)

// Without the below import, the test configuration will not be aware of the security configuration and so will just
// use the default spring boot configuration
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
