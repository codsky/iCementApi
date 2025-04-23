package com.icement.api.iCement.Domains.Order.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.icement.api.iCement.Domains.Order.OrderStatus;
import java.util.ArrayList;

import com.icement.api.iCement.Domains.Order.Order;
import com.icement.api.iCement.Domains.Order.OrderItem;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private Long orderNumber;
    private String customerId;
    private OrderStatus status;
    private Double totalDiscount;
    private Double totalTax;
    private Double totalShipping;
    private ArrayList<OrderItemDto> items;

    public Double calculateTotalGrossPrice() {
        return this.totalDiscount + this.totalTax + this.totalShipping;
    }

    public Double calculateTotalNetPrice() {
        return this.items.stream()
                .mapToDouble(OrderItemDto::calculateTotalPrice)
                .sum();
    }

    public Order toOrder() {
        return Order.builder()
                .orderNumber(this.orderNumber)
                .customerId(this.customerId)
                .status(this.status)
                .totalDiscount(this.totalDiscount)
                .totalTax(this.totalTax)
                .totalShipping(this.totalShipping)
                .items(convertDtoToOrderItems())
                .totalGrossPrice(calculateTotalGrossPrice())
                .totalNetPrice(calculateTotalNetPrice())
                .build();
    }

    public ArrayList<OrderItem> convertDtoToOrderItems() {
        return new ArrayList<>(
            items.stream()
            .map(OrderItemDto::toOrderItem)
            .toList()
        );
    }

}
