package com.louis.user_service.controller;

import com.louis.user_service.model.dto.UserRegistrationDto;
import com.louis.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    @SuppressWarnings("unused")
    public ResponseEntity<UserRegistrationDto> CreateUser(@RequestBody UserRegistrationDto user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }
}
