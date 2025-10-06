package com.louis.user_service.integration;

import com.louis.user_service.configuration.SecurityConfig;
import com.louis.user_service.model.Role;
import com.louis.user_service.model.UserEntity;
import com.louis.user_service.repository.UserRepository;
import com.louis.user_service.utils.TestUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Testcontainers
// @SpringBootTest loads the entire application context so is good for integration tests. See UserControllerTest for
// comparison
@SpringBootTest
// @AutoConfigureMockMvc enables and configures MockMvc. We don't need this in the controller test class because
// @WebMvcTest does this for us
@AutoConfigureMockMvc
// See UserControllerTest to see why this import is needed
@Import(SecurityConfig.class)
public class UserIntegrationTest {
    private static final String VALID_USER = TestUtils.getJsonString("/controller/validUser.json");
    private static final String SIGNUP_PATH = "/api/users/signup";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @Test
    public void testUserSignupSuccess() throws Exception {
        mockMvc.perform(post(SIGNUP_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_USER))
                .andExpect(status().isOk());
    }

    @Test
    public void testExistingUserWithSameEmailReturnsConflict() throws Exception {
        UserEntity newUser = new UserEntity(
                "john@doe.com",
                "John",
                LocalDate.of(2020, 1, 4),
                Role.USER,
                "hashedPassword"
        );
        userRepository.save(newUser);
        mockMvc.perform(post(SIGNUP_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_USER))
                .andExpect(status().isConflict());
    }
}
