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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// We need to extend with MockitoExtension so that mocks can be initialised
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

    // I have opted not to use @InjectMocks because it does not tell you if it fails
    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testSavesToRepositoryWithCorrectFieldAssignment() {
        when(passwordEncoder.encode(any())).thenReturn("mockHashedPassword");
        userService.addUser(validUser);
        UserEntity validUserEntity = new UserEntity(
            "john@doe.com",
            "John",
            LocalDate.of(2000, 2, 5),
            Role.USER,
            "mockHashedPassword"
        );
        verify(userRepository).save(validUserEntity);
    }

    @Test
    public void testUserAlreadyExistsThrowsConflictException() {
        when(userRepository.existsByEmail(validUser.email().toLowerCase())).thenReturn(true);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> userService.addUser(validUser));
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }
}
