package com.icement.api.iCement.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icement.api.iCement.order.Order;

@Repository
public interface  OrderRepository extends JpaRepository<Order, Integer> {

    public Order findByOrderNumber(String orderNumber);
}
