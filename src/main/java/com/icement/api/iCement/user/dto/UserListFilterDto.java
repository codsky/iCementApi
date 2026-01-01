package com.icement.api.iCement.user.dto;

import com.icement.api.iCement.common.dto.PaginationDto;
import com.icement.api.iCement.user.enums.UserRole;
import com.icement.api.iCement.user.enums.UserStatus;

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
