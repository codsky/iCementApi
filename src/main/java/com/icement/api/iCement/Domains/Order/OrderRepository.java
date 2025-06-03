package com.icement.api.iCement.Domains.Order;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.icement.api.iCement.Domains.Order.Dtos.OrderListFilterDto;
import com.icement.api.iCement.Domains.Shared.Repositories.BaseRepository;

@Repository
public class OrderRepository extends BaseRepository<Order> {
    
    public OrderRepository() {
        super(Order.class, "orders");
    }

    public List<Order> findOrdersByFilter(OrderListFilterDto filter) {
        return findByFilter(filter);
    }
    
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return findByColumnName("order_number", orderNumber);
    }

}
