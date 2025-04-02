package com.icement.api.iCement.Auth.Dtos;


import com.icement.api.iCement.Auth.User;
import com.icement.api.iCement.Auth.Enums.UserRole;
import com.icement.api.iCement.Auth.Enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreationDto {
    private String username;
    @NotEmpty
    private String password;
    @NotNull @Email
    private String email;
    @NotNull
    private UserRole role;
    private UserStatus status;

    public User toUser() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email.toLowerCase())
                .role(this.role)
                .status(this.status)
                .build();
    }
}
