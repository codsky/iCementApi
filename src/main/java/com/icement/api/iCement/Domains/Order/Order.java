package com.icement.api.iCement.Domains.Order;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.Domains.Base.Entities.Address;
import com.icement.api.iCement.Domains.Base.Entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Order extends BaseEntity {

    private String orderNumber;

    private String customerId;

    private OrderStatus status;

    private Double totalNetPrice;

    private Double totalGrossPrice;

    private Double discount;

    private Double taxAmount;

    private Double shippingPrice;

    private ArrayList<OrderItem> items;

    private Address shippingAddress;

}
