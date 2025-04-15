package com.icement.api.iCement.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

@Repository
public class UserRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<User> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), User.class));
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
    
    public Optional<User> findByUsername(String username) {
        User user = mongoTemplate.findOne(
            this.getBaseQuery().addCriteria(Criteria.where("username").is(username)),
            User.class
        );
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        User user = mongoTemplate.findOne(
            this.getBaseQuery().addCriteria(Criteria.where("email").is(email)),
             User.class
            );
        return Optional.ofNullable(user);
    }

    public User save(User user) {
        return mongoTemplate.save(user);
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

     public User delete(User user, String deletedByUserId) {
        user.setDeletedAt(Instant.now());
        user.setDeletedBy(deletedByUserId);
        return save(user);
    }

    public void deleteAll() {
        mongoTemplate.updateMulti(
            this.getBaseQuery(),
            new Update().set("deleted_at", Instant.now()),
            User.class
        );
    }

    private Query getBaseQuery() {
        return new Query(Criteria.where("deleted_at").isNull());
    }
}
