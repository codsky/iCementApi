package com.icement.api.iCement.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.common.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.ALWAYS)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Double price;
    private Integer version;

    private String productNumber;
    private String imageUrl;
    private String categoryId;
    private Boolean isActive;
    private Boolean isNew;
    private Boolean current;
}
