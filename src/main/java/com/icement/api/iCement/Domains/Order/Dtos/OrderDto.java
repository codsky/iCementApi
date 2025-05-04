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
import com.icement.api.iCement.Domains.Shared.Entities.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private Long orderNumber;
    @NotBlank(message = "Customer ID is required")
    private String customerId;
    private OrderStatus status;
    private Double totalDiscount;
    private Double totalTax;
    private Double totalShipping;
    @Size(min = 1, message = "At least one order item is required")
    private ArrayList<OrderItemDto> items;
    @jakarta.validation.constraints.NotNull(message = "Shipping address is required")
    private Address shippingAddress;

    public Double calculateTotalGrossPrice() {
        return this.totalDiscount + this.calculateTax() + this.totalShipping;
    }

    public Double calculateTax() {
        return this.calculateTotalNetPrice() * 0.1;
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
                .shippingAddress(this.shippingAddress)
                .items(convertDtoToOrderItems())
                .totalGrossPrice(calculateTotalGrossPrice())
                .totalNetPrice(calculateTotalNetPrice())
                .totalTax(calculateTax())
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
