package com.icement.api.iCement.product.dto;

import com.icement.api.iCement.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class ProductCreationDto {

    private String name;
    private String description;
    private Double price;
    private Integer version;
    private String productNumber;
    private String imageUrl;
    private String categoryId;
    private Boolean isActive;
    private Boolean isNew;

    public Product toProduct() {
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .version(this.version != null ? this.version : 1)
                .productNumber(this.productNumber)
                .imageUrl(this.imageUrl)
                .categoryId(this.categoryId)
                .isActive(this.isActive == null ? true : this.isActive)
                .isNew(this.isNew == null ? true : this.isNew)
                .build();
    }
}
