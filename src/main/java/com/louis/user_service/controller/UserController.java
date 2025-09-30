package com.louis.user_service.controller;

import com.louis.user_service.model.dto.UserRegistrationDto;
import com.louis.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(path="/api/users")
    public ResponseEntity<UserRegistrationDto> CreateUser(@RequestBody UserRegistrationDto user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }
}
