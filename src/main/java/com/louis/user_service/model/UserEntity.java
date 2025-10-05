package com.louis.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// I did not use a record here as they are ideal for immutable data, especially DTOs
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
        @Id
        private String email;
        private String username;
        private LocalDate dateOfBirth;
        private Role role;
        private String hashedPassword;
}
