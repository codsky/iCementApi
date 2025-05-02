package com.icement.api.iCement.Domains.Order;

import java.util.List;

import com.icement.api.iCement.Domains.Order.Dtos.OrderListFilterDto;
import com.icement.api.iCement.Exceptions.NotFoundException;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
    }

    public List<Order> getOrdersByFilter() {
        OrderListFilterDto filter = new OrderListFilterDto();
        return orderRepository.findOrdersByFilter(filter);
    }
    
    public List<Order> getOrdersByFilter(OrderListFilterDto filter) {
        return orderRepository.findOrdersByFilter(filter);
    }

    public Order cancelOrder(String id) {
        Order order = this.getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
