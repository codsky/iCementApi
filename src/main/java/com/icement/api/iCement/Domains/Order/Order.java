package com.icement.api.iCement.Domains.Order;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icement.api.iCement.Domains.Shared.Entities.Address;
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
@Document(collection = "orders")
public class Order extends BaseEntity {

    @Field(name = "order_number")
    private Long orderNumber;

    @MongoId(FieldType.OBJECT_ID)
    @Field(name = "customer_id")
    private String customerId;

    private OrderStatus status;

    @Field(name = "total_net_price")
    private Double totalNetPrice;

    @Field(name = "total_gross_price")
    private Double totalGrossPrice;

    @Field(name = "total_discount")
    private Double totalDiscount;

    @Field(name = "total_tax")
    private Double totalTax;

    @Field(name = "total_shipping")
    private Double totalShipping;

    @Field(name = "total_amount")
    private Double totalAmount;
    private ArrayList<OrderItem> items;

    @Field(name = "shipping_address")
    private Address shippingAddress;

}
