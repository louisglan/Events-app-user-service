package com.louis.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public record UserEntity(
        @Id
        String email,
        String username,
        LocalDate dateOfBirth,
        Role role,
        String hashedPassword) {
}
