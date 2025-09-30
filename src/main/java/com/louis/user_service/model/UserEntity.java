package com.louis.user_service.model;

import java.time.LocalDate;

public record UserEntity(String email,
                         String username,
                         LocalDate dateOfBirth,
                         Role role,
                         String password,
                         String confirmPassword) {
}
