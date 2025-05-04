package com.icement.api.iCement.Domains.Order;

import java.util.List;

import com.icement.api.iCement.Domains.Order.Dtos.OrderListFilterDto;
import com.icement.api.iCement.Domains.Product.Product;
import com.icement.api.iCement.Domains.Product.ProductRepository;
import com.icement.api.iCement.Exceptions.NotFoundException;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order) {
        this.validateOrderItemsProducts(order);
        return orderRepository.save(order);
    }

    private void validateOrderItemsProducts(Order order) {
        List<String> productNumbers = getProductNumbersFromOrderItems(order.getItems());
        List<Product> products = productRepository.findByProductNumbers(productNumbers)
            .orElseThrow(() -> new NotFoundException("Products with product numbers " + productNumbers + " not found"));
        checkOrderItemsNonExistingProductNumbers(order, products);
    }

    private List<String> getProductNumbersFromOrderItems(List<OrderItem> orderItems) {
        List<String> productNumbers = orderItems.stream()
            .map(OrderItem::getProductNumber)
            .toList();

        if (productNumbers.isEmpty()) {
            throw new NotFoundException("No product numbers found in order items");
        }

        return productNumbers;
    }

    private void checkOrderItemsNonExistingProductNumbers(Order order, List<Product> products) {
        String productNumbersNotFound = order.getItems().stream()
            .filter(item -> products.stream().noneMatch(product -> product.getProductNumber().equals(item.getProductNumber())))
            .map(OrderItem::getProductNumber)
            .distinct()
            .reduce((a, b) -> a + ", " + b)
            .orElse(null);

        if (productNumbersNotFound != null) {
            throw new NotFoundException("Products with product numbers " + productNumbersNotFound + " not found");
        }
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
