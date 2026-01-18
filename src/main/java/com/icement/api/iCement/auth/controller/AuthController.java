package com.icement.api.iCement.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icement.api.iCement.auth.service.AuthService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // @PostMapping("/login")
    // public String login(@Valid @RequestBody UserLoginDto userLoginDto) {
    //     return authService.login(userLoginDto.getEmail(), userLoginDto.getPassword());
    // }

    // @PostMapping("/register")
    // public String register(@Valid @RequestBody UserCreationDto userDto) {
    //     return authService.register(userDto.toUser());
    // }

}
