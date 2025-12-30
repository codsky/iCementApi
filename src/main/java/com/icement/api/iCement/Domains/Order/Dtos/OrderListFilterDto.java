package com.icement.api.iCement.Domains.Order.Dtos;

import java.time.Instant;

import com.icement.api.iCement.Domains.Base.Dtos.PaginationDto;
import com.icement.api.iCement.Domains.Order.OrderStatus;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class OrderListFilterDto extends PaginationDto {
    private String orderNumber;
    private String customerId;
    private OrderStatus status;
    private String productId;
    private Instant orderDate;

    public OrderListFilterDto() {
        super();
    }
}
