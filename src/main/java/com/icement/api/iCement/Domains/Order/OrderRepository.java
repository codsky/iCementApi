package com.icement.api.iCement.Domains.Order;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import com.icement.api.iCement.Domains.Order.Dtos.OrderListFilterDto;
import com.icement.api.iCement.Domains.Shared.Repositories.BaseRepository;

@Repository
public class OrderRepository extends BaseRepository<Order> {
    
    OrderRepository() {
        super(Order.class);
    }

    public List<Order> findOrdersByFilter(OrderListFilterDto filter) {
        Aggregation aggregation = Aggregation.newAggregation(
                filter.generateAggregationMatchStage(),
                filter.generateAggregationSortStage(),
                filter.generateAggregationSkipStage()
        );
        return mongoTemplate.aggregate(aggregation, "orders", Order.class).getMappedResults();
    }
    
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return findByColumnName("order_number", orderNumber);
    }

}
