package com.icement.api.iCement.Domains.Shared.Entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class BaseEntity {
    @MongoId(FieldType.OBJECT_ID)
    protected String id;

    @CreatedDate
    @Nullable
    @Field("created_at")
    protected Instant createdAt;

    @LastModifiedDate
    @Field("updated_at")
    protected Instant updatedAt;

    @Field(name = "deleted_at")
    protected Instant deletedAt;

    @Field(name = "deleted_by")
    protected String deletedBy;
}
