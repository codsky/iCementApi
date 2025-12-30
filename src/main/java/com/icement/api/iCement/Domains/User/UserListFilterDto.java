package com.icement.api.iCement.Domains.User;

import com.icement.api.iCement.Domains.Base.Dtos.PaginationDto;
import com.icement.api.iCement.Domains.User.Enums.UserRole;
import com.icement.api.iCement.Domains.User.Enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserListFilterDto extends PaginationDto {

    private String username;
    private String email;
    private UserRole role;
    private UserStatus status;

    public UserListFilterDto() {
        super();
    }
}
