package com.icement.api.iCement.Domains.Base.Entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BaseEntity {
    @Id
    protected Integer id;

    @CreatedDate
    @Nullable
    protected Instant createdAt;

    @LastModifiedDate
    protected Instant updatedAt;

    protected Instant deletedAt;
    protected String deletedBy;
}
