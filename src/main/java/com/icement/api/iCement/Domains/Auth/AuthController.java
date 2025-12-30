package com.icement.api.iCement.Domains.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icement.api.iCement.Domains.Auth.Dtos.UserCreationDto;
import com.icement.api.iCement.Domains.Auth.Dtos.UserLoginDto;

import jakarta.validation.Valid;

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
