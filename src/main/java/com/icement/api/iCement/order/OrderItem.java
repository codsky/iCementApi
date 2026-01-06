package com.icement.api.iCement.order;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderItem {

    private String productName;

    private String productNumber;

    private Integer productVersion;

    private BigDecimal price;

    private Integer quantity;
    
    private BigDecimal totalPrice;

    public BigDecimal calculateTotalPrice() {
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
        this.totalPrice = calculateTotalPrice();
    }

    public void updatePrice(BigDecimal newPrice) {
        this.price = newPrice;
        this.totalPrice = calculateTotalPrice();
    }
}
