package com.icement.api.iCement.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.common.entities.Address;
import com.icement.api.iCement.common.entities.BaseEntity;
import com.icement.api.iCement.order.enums.OrderStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(name = "orders")
public class Order extends BaseEntity {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");

    @Setter
    private String orderNumber;

    private String customerId;

    private OrderStatus status;

    private BigDecimal totalNetPrice = BigDecimal.ZERO;

    private BigDecimal totalGrossPrice = BigDecimal.ZERO;

    @Setter
    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Setter
    private BigDecimal shippingPrice = BigDecimal.ZERO;

    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private Address shippingAddress;

    public static Order create(String customerId, List<OrderItem> items, Address shippingAddress) {
        validateCreation(customerId, items, shippingAddress);
        
        Order order = new Order();
        order.customerId = customerId;
        order.items = new ArrayList<>(items);
        order.shippingAddress = shippingAddress;
        order.recalculateTotals();
        order.status = OrderStatus.PENDING;
        return order;
    }

    private static void validateCreation(String customerId, List<OrderItem> items, Address shippingAddress) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item.");
        }
        if (shippingAddress == null) {
            throw new IllegalArgumentException("Order must have a shipping address.");
        }
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Order must have a customer ID.");
        }
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Order item cannot be null.");
        }
        this.items.add(item);
        recalculateTotals();
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
        recalculateTotals();
    }

    public void recalculateTotals() {
        this.totalNetPrice = this.items.stream()
                .map(item -> item.getTotalPrice() != null ? item.getTotalPrice() : item.calculateTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal discountValue = this.discount != null ? this.discount : BigDecimal.ZERO;
        BigDecimal shippingValue = this.shippingPrice != null ? this.shippingPrice : BigDecimal.ZERO;
        
        
        this.taxAmount = this.totalNetPrice.multiply(TAX_RATE);
        
        this.totalGrossPrice = this.totalNetPrice.add(this.taxAmount).add(shippingValue).subtract(discountValue);
    }

    public void confirm() {
        if (this.status == OrderStatus.PENDING || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.CONFIRMED;
            return;
        }
        throw new IllegalStateException("Order status can be changed to CONFIRMED only from PENDING or ON_HOLD states.");
    }

    public void startProduction() {
        if (Arrays.asList(OrderStatus.PENDING, OrderStatus.CONFIRMED, OrderStatus.ON_HOLD).contains(this.status)) {
            this.status = OrderStatus.IN_PRODUCTION;
            return;
        }
        throw new IllegalStateException("Order status can be changed to IN_PRODUCTION only from PENDING, CONFIRMED, or ON_HOLD states.");
        
    }

    public void assignToDriver() {
        if (this.status == OrderStatus.IN_PRODUCTION || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.ASSIGNED_TO_DRIVER;
            return;
        }
        throw new IllegalStateException("Order status can be changed to ASSIGNED_TO_DRIVER only from IN_PRODUCTION or ON_HOLD state.");
    }

    public void dispatch() {
        if (this.status == OrderStatus.ASSIGNED_TO_DRIVER || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.OUT_FOR_DELIVERY;
            return;
        }
        throw new IllegalStateException("Order status can be changed to OUT_FOR_DELIVERY only from IN_PRODUCTION or ON_HOLD state.");
    }

    public void deliver() {
        if (this.status == OrderStatus.OUT_FOR_DELIVERY) {
            this.status = OrderStatus.DELIVERED;
            return;
        }
        throw new IllegalStateException("Order status can be changed to DELIVERED only from OUT_FOR_DELIVERY state.");
    }

    public void cancel() {
        if (this.status != OrderStatus.DELIVERED) {
            this.status = OrderStatus.CANCELLED;
            return;
        }
        throw new IllegalStateException("Order status cannot be changed to CANCELLED from DELIVERED state.");
    }

    public void hold() {
        if (this.status != OrderStatus.DELIVERED && this.status != OrderStatus.CANCELLED) {
            this.status = OrderStatus.ON_HOLD;
            return;
        }
        throw new IllegalStateException("Order status can be changed to ON_HOLD only from states other than DELIVERED or CANCELLED.");
    }

}
