package com.louis.user_service.model.dto;

import java.time.LocalDate;

public record UserRegistrationDto(
        String email,
        String username,
        LocalDate dateOfBirth,
        String password,
        String confirmPassword
) {}
