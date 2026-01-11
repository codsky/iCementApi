package com.icement.api.iCement.order.service;

import org.springframework.stereotype.Service;

import com.icement.api.iCement.order.Order;
import com.icement.api.iCement.order.repository.OrderRepository;
import com.icement.api.iCement.user.User;
import com.icement.api.iCement.common.exception.AccessDeniedException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    // private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        // this.productRepository = productRepository;
    }

    // public Order createOrder(Order order) {
    //     validateOrderItemsProducts(order);
    //     return orderRepository.save(order);
    // }

    // private void validateOrderItemsProducts(Order order) {
    //     List<String> productNumbers = getProductNumbersFromOrderItems(order.getItems());
    //     List<Product> products = productRepository.findByProductNumbers(productNumbers);        
    //     checkOrderItemsNonExistingProductNumbers(order, products);
    // }

    // private List<String> getProductNumbersFromOrderItems(List<OrderItem> orderItems) {
    //     List<String> productNumbers = orderItems.stream()
    //         .map(OrderItem::getProductNumber)
    //         .toList();

    //     if (productNumbers.isEmpty()) {
    //         throw new NotFoundException("No product numbers found in order items");
    //     }

    //     return productNumbers;
    // }

    // private void checkOrderItemsNonExistingProductNumbers(Order order, List<Product> products) {
    //     String productNumbersNotFound = order.getItems().stream()
    //         .filter(item -> products.stream().noneMatch(product -> product.getProductNumber().equals(item.getProductNumber())))
    //         .map(OrderItem::getProductNumber)
    //         .distinct()
    //         .reduce((a, b) -> a + ", " + b)
    //         .orElse(null);

    //     if (productNumbersNotFound != null) {
    //         throw new NotFoundException("Products with product numbers " + productNumbersNotFound + " not found!");
    //     }
    // }

    // public Order getOrderById(String id) {
    //     return orderRepository.findById(id)
    //         .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
    // }

    // public List<Order> getOrdersByFilter() {
    //     OrderListFilterDto filter = new OrderListFilterDto();
    //     return orderRepository.findOrdersByFilter(filter);
    // }
    
    // public List<Order> getOrdersByFilter(OrderListFilterDto filter) {
    //     return orderRepository.findOrdersByFilter(filter);
    // }

    // public Order cancelOrder(String id) {
    //     Order order = this.getOrderById(id);
    //     order.setStatus(OrderStatus.CANCELLED);
    //     return orderRepository.save(order);
    // }

    // Order tansistioning methods

    public Order confirm(String orderId, User user) {

        if (!user.isAgent()) {
            throw new AccessDeniedException("Only agents can confirm orders");
        }

        Order order = getOrderByOrderNumber(orderId);
        order.confirm();
        return orderRepository.save(order);
    }

    public Order startProduction(String orderId, User user) {

        if (!user.isAgent()) {
            throw new AccessDeniedException("Only agents can start production of orders");
        }

        Order order = getOrderByOrderNumber(orderId);
        order.startProduction();
        return orderRepository.save(order);
    }

    public Order assignToDriver(String orderId, User user) {

        if (!user.isAgent()) {
            throw new AccessDeniedException("Only agents can assign orders to drivers");
        }

        Order order = getOrderByOrderNumber(orderId);
        // TODO: Update order with driver user id
        order.assignToDriver();
        return orderRepository.save(order);
    }

    public Order dispatch(String orderId, User user) {
        if (!user.isAgent() || !user.isDriver()) {
            throw new AccessDeniedException("You are not allowed to dispatch orders");
        }
        Order order = getOrderByOrderNumber(orderId);
        order.dispatch();
        return orderRepository.save(order);
    }

    public Order deliver(String orderId, User user) {
        if (!user.isDriver()) {
            throw new AccessDeniedException("Only drivers can deliver orders");
        }
        Order order = getOrderByOrderNumber(orderId);
        order.deliver();
        return orderRepository.save(order);
    }

    public Order cancel(String orderId, User user) {
         if (!user.isAgent() || !user.isRetailer()) {
            throw new AccessDeniedException("You are not allowed to cancel orders");
        }

        Order order = getOrderByOrderNumber(orderId);
        order.cancel();
        return orderRepository.save(order);
    }

    public Order hold(String orderId, User user) {
        if (!user.isAgent()) {
            throw new AccessDeniedException("Only agents can put orders on hold");
        }
        
        Order order = getOrderByOrderNumber(orderId);
        order.hold();
        return orderRepository.save(order);
    }

    private Order getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null) {
            throw new RuntimeException("Order with ID " + orderNumber + " not found");
        }
        return order;
    }
}
