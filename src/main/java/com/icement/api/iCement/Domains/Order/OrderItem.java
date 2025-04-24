package com.icement.api.iCement.Domains.Order;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Field("product_id")
    private String productId;

    @Field("price")
    private Double price;

    @Field("quantity")
    private Integer quantity;
    
    @Field("total_price")
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
