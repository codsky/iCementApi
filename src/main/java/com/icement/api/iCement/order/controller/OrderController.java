package com.icement.api.iCement.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icement.api.iCement.order.dto.OrderDto;
import com.icement.api.iCement.order.dto.OrderListFilterDto;
import com.icement.api.iCement.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // @PostMapping("/create")
    // public Order create(@RequestBody @Valid OrderDto orderDto) {
    //     return orderService.createOrder(orderDto.toOrder());
    // }

    // @GetMapping("/{id}")
    // public Order getById(@PathVariable String id) {
    //     return orderService.getOrderById(id);
    // }

    // @GetMapping()
    // public List<Order> getAll(@ModelAttribute OrderListFilterDto filter) {
    //     if (filter != null) {
    //         return orderService.getOrdersByFilter(filter);
    //     }
    //     return orderService.getOrdersByFilter();
    // }

    // @PutMapping("/{id}")
    // public Order cancel(@PathVariable String id) {
    //     return orderService.cancelOrder(id);
    // }

}
