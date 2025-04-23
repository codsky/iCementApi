package com.icement.api.iCement.Domains.Order;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.Domains.Shared.Entities.BaseEntity;

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

    private Long orderNumber;

    @MongoId(FieldType.OBJECT_ID)
    private String customerId;
    private OrderStatus status;
    private Double totalNetPrice;
    private Double totalGrossPrice;
    private Double totalDiscount;
    private Double totalTax;
    private Double totalShipping;
    private Double totalAmount;
    private ArrayList<OrderItem> items;

}
