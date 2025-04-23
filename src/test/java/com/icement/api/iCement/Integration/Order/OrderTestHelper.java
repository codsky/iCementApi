package com.icement.api.iCement.Integration.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import com.icement.api.iCement.Domains.Order.OrderRepository;
import com.icement.api.iCement.Domains.Order.Dtos.OrderDto;
import com.icement.api.iCement.Domains.Order.Dtos.OrderItemDto;
import java.util.ArrayList;

public class OrderTestHelper {

    private MockMvc mockMvc;
    private boolean isOrderCollectionCleared = false;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public OrderTestHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public void deleteAllOrders() {
        if (isOrderCollectionCleared) {
            return;
        }
        isOrderCollectionCleared = true;
        orderRepository.deleteAll();
    }

    public void createOrder() throws Exception {
    
        this.mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderRequest()))
                .andExpect(status().isCreated());
    }

    public String createOrderRequest() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .orderNumber(123456L)
                .customerId("customerId")
                .items(createOrderItems())
                .build();
        return objectMapper.writeValueAsString(orderDto);
    }

    private ArrayList<OrderItemDto> createOrderItems() {
        ArrayList<OrderItemDto> items = new ArrayList<>();
        OrderItemDto item = OrderItemDto.builder()
                .productId("productId")
                .quantity(2)
                .price(50.0)
                .build();
        items.add(item);
        return items;
    }

}
