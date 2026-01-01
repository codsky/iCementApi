package com.icement.api.iCement.order.dto;

import java.time.Instant;

import com.icement.api.iCement.common.dto.PaginationDto;
import com.icement.api.iCement.order.enums.OrderStatus;

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
