package com.louis.user_service.service;

import com.louis.user_service.model.Role;
import com.louis.user_service.model.UserEntity;
import com.louis.user_service.model.dto.UserRegistrationDto;
import com.louis.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    @SuppressWarnings("unused")
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    UserRegistrationDto validUser = new UserRegistrationDto(
            "John@Doe.com",
            "John",
            LocalDate.of(2000, 2, 5),
            "abc",
            "abc");

    @Captor
    ArgumentCaptor<UserEntity> acUserEntity;

    @Test
    public void testSavesToRepositoryWithCorrectFieldAssignment() {
        userService.addUser(validUser);
        verify(userRepository).save(acUserEntity.capture());
        assertEquals(validUser.email(), acUserEntity.getValue().email());
        assertEquals(validUser.username(), acUserEntity.getValue().username());
        assertEquals(validUser.dateOfBirth(), acUserEntity.getValue().dateOfBirth());
        assertEquals(Role.USER, acUserEntity.getValue().role());
        assertNotEquals(validUser.password(), acUserEntity.getValue().hashedPassword());
    }
}
