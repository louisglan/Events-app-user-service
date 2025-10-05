package com.louis.user_service.integration;

import com.louis.user_service.configuration.SecurityConfig;
import com.louis.user_service.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest loads the entire application context so is good for integration tests. See UserControllerTest for
// comparison
@SpringBootTest

// @AutoConfigureMockMvc enables and configures MockMvc. We don't need this in the controller test class because
// @WebMvcTest does this for us
@AutoConfigureMockMvc

// See UserControllerTest to see why this import is needed
@Import(SecurityConfig.class)
public class UserIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testUserSignupSuccess() throws Exception {
        mockMvc.perform(post("/api/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getJsonString("/controller/validUser.json")))
                .andExpect(status().isOk());
    }
}
