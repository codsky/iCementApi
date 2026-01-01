package com.icement.api.iCement.order.dto;

import com.icement.api.iCement.order.OrderItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Product version is required")
    @Positive(message = "Product version must be a positive number")
    private Integer productVersion;
    @NotNull(message = "Order Item Price is required")
    @Positive(message = "Order Item Price must be a positive number")
    private Double price;
    @NotNull(message = "Order Item Quantity is required")
    @Positive(message = "Order Item Quantity must be a positive number")
    private Integer quantity;

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

    public Double calculateTotalPrice() {
        return this.price * this.quantity;
    }
}
