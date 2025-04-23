package com.icement.api.iCement.Domains.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private String productId;
    private Double price;
    private Integer quantity;
    private Double totalPrice;

    public Double calculateTotalPrice() {
        return this.price * this.quantity;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
        this.totalPrice = calculateTotalPrice();
    }
    public void updatePrice(double newPrice) {
        this.price = newPrice;
        this.totalPrice = calculateTotalPrice();
    }

}
