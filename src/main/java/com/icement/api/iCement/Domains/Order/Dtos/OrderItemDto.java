package com.icement.api.iCement.Domains.Order.Dtos;

import com.icement.api.iCement.Domains.Order.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemDto {
    private String productId;
    private Double price;
    private Integer quantity;

    public Double calculateTotalPrice() {
        return this.price * this.quantity;
    }

    public OrderItem toOrderItem() {
        return OrderItem.builder()
                .productId(this.productId)
                .price(this.price)
                .quantity(this.quantity)
                .totalPrice(this.calculateTotalPrice())
                .build();
    }
}
