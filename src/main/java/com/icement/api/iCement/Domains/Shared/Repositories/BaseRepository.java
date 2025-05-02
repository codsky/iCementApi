package com.icement.api.iCement.Domains.Shared.Repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.icement.api.iCement.Domains.Shared.Dtos.PaginationDto;
import com.icement.api.iCement.Domains.Shared.Entities.BaseEntity;

public class BaseRepository <T extends BaseEntity> {
    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> entity;
    private final String collectionName;

    public BaseRepository(Class<T> entity, String collectionName) {
        this.entity = entity;
        this.collectionName = collectionName;
    }

    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), entity));
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(entity);
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
         entity
        );
    }

    protected Query getBaseQuery() {
        return new Query(Criteria.where("deleted_at").isNull());
    }

    protected Optional<T> findByColumnName(String columnName, String value) {
        return Optional.ofNullable(
            mongoTemplate.findOne(
                this.getBaseQuery().addCriteria(Criteria.where(columnName).is(value)),
             entity
            )
        );
    }

    protected List<T> findByFilter(PaginationDto filter) {
        Aggregation aggregation = Aggregation.newAggregation(
                filter.generateAggregationMatchStage(),
                filter.generateAggregationSortStage(),
                filter.generateAggregationSkipStage()
        );
        return mongoTemplate.aggregate(aggregation, collectionName, entity).getMappedResults();
    }

    protected Optional<T> findWithCriteria(Criteria criteria) {
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, entity));
    }
}
