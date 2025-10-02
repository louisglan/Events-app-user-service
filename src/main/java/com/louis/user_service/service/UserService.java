package com.louis.user_service.service;

import com.louis.user_service.model.Role;
import com.louis.user_service.model.UserEntity;
import com.louis.user_service.model.dto.UserRegistrationDto;
import com.louis.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserRegistrationDto user) {
        var hashedPassword = passwordEncoder.encode(user.password());
        var userEntity = new UserEntity(
                user.email(),
                user.username(),
                user.dateOfBirth(),
                Role.USER,
                hashedPassword
        );
        userRepository.save(userEntity);
    }
}
