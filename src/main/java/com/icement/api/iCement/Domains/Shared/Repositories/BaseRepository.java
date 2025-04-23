package com.icement.api.iCement.Domains.Shared.Repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.icement.api.iCement.Domains.Shared.Entities.BaseEntity;

public class BaseRepository <T extends BaseEntity> {
    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> entityType;

    public BaseRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), entityType));
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(entityType);
    }

    public T delete(T entity, String deletedByUserId) {
        entity.setDeletedAt(Instant.now());
        entity.setDeletedBy(deletedByUserId);
        return save(entity);
    }

    public void deleteAll() {
        mongoTemplate.updateMulti(
            this.getBaseQuery(),
            new Update().set("deleted_at", Instant.now()),
            entityType
        );
    }

    protected Query getBaseQuery() {
        return new Query(Criteria.where("deleted_at").isNull());
    }

    public Optional<T> findByColumnName(String columnName, String value) {
        return Optional.ofNullable(
            mongoTemplate.findOne(
                this.getBaseQuery().addCriteria(Criteria.where(columnName).is(value)),
                entityType
            )
        );
    }
}
