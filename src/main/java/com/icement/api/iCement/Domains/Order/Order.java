package com.icement.api.iCement.Domains.Order;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.Domains.Base.Entities.Address;
import com.icement.api.iCement.Domains.Base.Entities.BaseEntity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderNumber;

    private String customerId;

    private OrderStatus status;

    private Double totalNetPrice;

    private Double totalGrossPrice;

    private Double discount;

    private Double taxAmount;

    private Double shippingPrice;

    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private Address shippingAddress;

}
