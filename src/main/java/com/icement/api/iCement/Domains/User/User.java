package com.icement.api.iCement.Domains.User;


import java.time.Instant;
import java.util.Collection;
import java.util.List;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.icement.api.iCement.Domains.Shared.Entities.BaseEntity;
import com.icement.api.iCement.Domains.User.Enums.UserRole;
import com.icement.api.iCement.Domains.User.Enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Document(collection = "users")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class User extends BaseEntity implements UserDetails {
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull @Email
    private String email;
    @NotNull
    private UserRole role;
    private UserStatus status;
    @Field(name = "last_login_at")
    private Instant lastLoginAt;
 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}