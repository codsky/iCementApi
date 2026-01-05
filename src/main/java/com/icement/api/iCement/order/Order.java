package com.icement.api.iCement.order;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.common.entities.Address;
import com.icement.api.iCement.common.entities.BaseEntity;
import com.icement.api.iCement.order.enums.OrderStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Getter
    @Setter
    private String orderNumber;

    @Getter
    @Setter
    private String customerId;

    private OrderStatus status;

    @Getter
    private Double totalNetPrice;

    @Getter
    private Double totalGrossPrice;

    @Getter
    @Setter
    private Double discount;

    @Getter
    private Double taxAmount;

    @Getter
    @Setter
    private Double shippingPrice;

    @ElementCollection
    @Getter
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private Address shippingAddress;

    public void updateStatus(OrderStatus newStatus) {
        switch (newStatus) {
            case PENDING -> setStatusToPending();
            case CONFIRMED -> setStatusToConfirmed();
            case IN_PRODUCTION -> setStatusToInProduction();
            case ASSIGNED_TO_DRIVER -> setStatusToAssignedToDriver();
            case OUT_FOR_DELIVERY -> setStatusToOutForDelivery();
            case DELIVERED -> setStatusToDelivered();
            case CANCELLED -> setStatusToCancelled();
            case ON_HOLD -> setStatusToOnHold();
            default -> throw new IllegalArgumentException("Invalid order status: " + newStatus);
        }
    }


    private void setStatusToPending() {
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
            return;
        }
        throw new IllegalStateException("Order status can be set to PENDING only if it is null.");
    }

    private void setStatusToConfirmed() {
        if (this.status == OrderStatus.PENDING || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.CONFIRMED;
            return;
        }
        throw new IllegalStateException("Order status can be changed to CONFIRMED only from PENDING or ON_HOLD states.");
    }

    private void setStatusToInProduction() {
        if (Arrays.asList(OrderStatus.PENDING, OrderStatus.CONFIRMED, OrderStatus.ON_HOLD).contains(this.status)) {
            this.status = OrderStatus.IN_PRODUCTION;
            return;
        }
        throw new IllegalStateException("Order status can be changed to IN_PRODUCTION only from PENDING, CONFIRMED, or ON_HOLD states.");
        
    }

    private void setStatusToAssignedToDriver() {
        if (this.status == OrderStatus.IN_PRODUCTION || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.ASSIGNED_TO_DRIVER;
            return;
        }
        throw new IllegalStateException("Order status can be changed to ASSIGNED_TO_DRIVER only from IN_PRODUCTION or ON_HOLD state.");
    }

    private void setStatusToOutForDelivery() {
        if (this.status == OrderStatus.ASSIGNED_TO_DRIVER || this.status == OrderStatus.ON_HOLD) {
            this.status = OrderStatus.OUT_FOR_DELIVERY;
            return;
        }
        throw new IllegalStateException("Order status can be changed to OUT_FOR_DELIVERY only from IN_PRODUCTION or ON_HOLD state.");
    }

    private void setStatusToDelivered() {
        if (this.status == OrderStatus.OUT_FOR_DELIVERY) {
            this.status = OrderStatus.DELIVERED;
            return;
        }
        throw new IllegalStateException("Order status can be changed to DELIVERED only from OUT_FOR_DELIVERY state.");
    }

    private void setStatusToCancelled() {
        if (this.status != OrderStatus.DELIVERED) {
            this.status = OrderStatus.CANCELLED;
            return;
        }
        throw new IllegalStateException("Order status cannot be changed to CANCELLED from DELIVERED state.");
    }

    private void setStatusToOnHold() {
        if (this.status != OrderStatus.DELIVERED && this.status != OrderStatus.CANCELLED) {
            this.status = OrderStatus.ON_HOLD;
            return;
        }
        throw new IllegalStateException("Order status can be changed to ON_HOLD only from states other than DELIVERED or CANCELLED.");
    }

}
