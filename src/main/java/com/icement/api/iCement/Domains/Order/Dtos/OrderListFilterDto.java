package com.icement.api.iCement.Domains.Order.Dtos;

import java.time.Instant;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.icement.api.iCement.Domains.Order.OrderStatus;
import com.icement.api.iCement.Domains.Shared.Dtos.PaginationDto;

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

    @Override
    public MatchOperation generateAggregationMatchStage() {
        var criteria = new Criteria();
        criteria.and("deleted_at").is(null);

        if (orderNumber != null && !orderNumber.isEmpty()) {
            criteria.and("order_number").regex(orderNumber, "i");
        }
        if (customerId != null && !customerId.isEmpty()) {
            criteria.and("customer_id").regex(customerId, "i");
        }
        if (status != null) {
            criteria.and("status").is(status);
        }
        if (productId != null && !productId.isEmpty()) {
            criteria.and("product_id").regex(productId, "i");
        }
        if (orderDate != null) {
            criteria.and("order_date").is(orderDate);
        }

        return Aggregation.match(criteria);
    }
}
