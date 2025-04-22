package com.icement.api.iCement.Domains.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import com.icement.api.iCement.Domains.Shared.Repositories.BaseRepository;

@Repository
public class UserRepository extends BaseRepository<User> {
    
    public UserRepository() {
        super(User.class);
    }

    public Optional<User> findByUsername(String username) {
        return findByColumnName("username", username);
    }

    public Optional<User> findByEmail(String email) {
        return findByColumnName("email", email);
    }

    public List<User> findUsersByFilter() {
        return findUsersByFilter(new UserListFilterDto());
    }

    public List<User> findUsersByFilter(UserListFilterDto filter) {
        Aggregation aggregation = Aggregation.newAggregation(
                filter.generateAggregationMatchStage(),
                filter.generateAggregationSortStage(),
                filter.generateAggregationSkipStage()
        );
        return mongoTemplate.aggregate(aggregation, "users", User.class).getMappedResults();
    }
}
