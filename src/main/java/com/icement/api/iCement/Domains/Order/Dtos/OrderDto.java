package com.icement.api.iCement.Domains.Order.Dtos;

import java.util.ArrayList;

import com.icement.api.iCement.Domains.Order.Order;
import com.icement.api.iCement.Domains.Order.OrderItem;
import com.icement.api.iCement.Domains.Order.OrderStatus;
import com.icement.api.iCement.Domains.Shared.Entities.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    @NotBlank(message = "Customer ID is required")
    private String customerId;
    private OrderStatus status;
    private Double discount;
    private Double shippingPrice;
    @Size(min = 1, message = "At least one order item is required")
    @Valid
    private ArrayList<OrderItemDto> items;
    @NotNull(message = "Shipping address is required")
    private Address shippingAddress;

    public Order toOrder() {
        return Order.builder()
                .orderNumber(this.generateOrderNumber())
                .customerId(this.customerId)
                .status(OrderStatus.PENDING) // Default status, can be changed later
                .discount(this.getDiscount())
                .shippingPrice(this.getShippingPrice())
                .shippingAddress(this.shippingAddress)
                .items(convertDtoToOrderItems())
                .totalGrossPrice(calculateTotalGrossPrice())
                .totalNetPrice(calculateTotalNetPrice())
                .taxAmount(calculateTax())
                .build();
    }

    private String generateOrderNumber() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase();
    }

    public ArrayList<OrderItem> convertDtoToOrderItems() {
        return new ArrayList<>(
            items.stream()
            .map(OrderItemDto::toOrderItem)
            .toList()
        );
    }

    public Double calculateTotalGrossPrice() {
        return this.calculateTotalNetPrice() + this.calculateTax() + this.getShippingPrice() - this.getDiscount();
    }

    public Double calculateTax() {
        return this.calculateTotalNetPrice() * 0.1;
    }

    public Double calculateTotalNetPrice() {
        return this.items.stream()
                .mapToDouble(OrderItemDto::calculateTotalPrice)
                .sum();
    }

    public Double getDiscount() {
        return this.discount != null ? this.discount : 0.0;
    }

    public Double getShippingPrice() {
        return this.shippingPrice != null ? this.shippingPrice : 0.0;
    }

}
