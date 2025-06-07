package com.icement.api.iCement.Domains.Product;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.Domains.Shared.Entities.BaseEntity;

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
@Document(collection = "products")
@ToString
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Double price;
    private Integer version;

    @Field(name = "product_number")
    private String productNumber;
    @Field(name = "image_url")
    private String imageUrl;
    @Field(name = "category_id")
    private String categoryId;
    @Field(name = "is_active")
    private Boolean isActive;
    @Field(name = "is_new")
    private Boolean isNew;
    @Field(name = "current")
    private Boolean current;
}
