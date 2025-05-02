package com.icement.api.iCement.Domains.User;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.icement.api.iCement.Domains.Shared.Dtos.PaginationDto;
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

    @Override
    public MatchOperation generateAggregationMatchStage() {
        Criteria criteria = new Criteria();
        criteria.and("deleted_at").is(null);

        if (username != null && !username.isEmpty()) {
            criteria.and("username").regex(username, "i");
        }
        if (email != null && !email.isEmpty()) {
            criteria.and("email").regex(email, "i");
        }
        if (role != null) {
            criteria.and("role").is(role);
        }
        if (status != null) {
            criteria.and("status").is(status);
        }

        return Aggregation.match(criteria);
    }
}
