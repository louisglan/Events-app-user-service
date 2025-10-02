package com.louis.user_service.service;

import com.louis.user_service.model.Role;
import com.louis.user_service.model.UserEntity;
import com.louis.user_service.model.dto.UserRegistrationDto;
import com.louis.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    UserRegistrationDto validUser = new UserRegistrationDto(
            "John@Doe.com",
            "John",
            LocalDate.of(2000, 2, 5),
            "abc",
            "abc");

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testSavesToRepositoryWithCorrectFieldAssignment() {
        when(passwordEncoder.encode(any())).thenReturn("mockHashedPassword");
        userService.addUser(validUser);
        UserEntity validUserEntity = new UserEntity(
            "John@Doe.com",
            "John",
            LocalDate.of(2000, 2, 5),
            Role.USER,
            "mockHashedPassword"
        );
        verify(userRepository).save(validUserEntity);
    }
}
