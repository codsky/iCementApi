package com.icement.api.iCement.Domains.Order.Dtos;

import com.icement.api.iCement.Domains.Order.OrderItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemDto {
    @NotBlank(message = "Product number is required")
    private String productNumber;
    @NotBlank(message = "Product name is required")
    private String productName;
    @NotBlank(message = "Product version is required")
    @Positive(message = "Product version must be a positive number")
    private Integer productVersion;
    @NotEmpty(message = "Order Item Price is required")
    @Positive(message = "Order Item Price must be a positive number")
    private Double price;
    @NotEmpty(message = "Order Item Quantity is required")
    @Positive(message = "Order Item Quantity must be a positive number")
    private Integer quantity;

    public Double calculateTotalPrice() {
        return this.price * this.quantity;
    }

    public OrderItem toOrderItem() {
        return OrderItem.builder()
                .productNumber(this.productNumber)
                .productName(this.productName)
                .productVersion(this.productVersion)
                .price(this.price)
                .quantity(this.quantity)
                .totalPrice(this.calculateTotalPrice())
                .build();
    }
}
