package com.icement.api.iCement.Integration.Order;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icement.api.iCement.Domains.Order.Dtos.OrderDto;
import com.icement.api.iCement.Domains.Order.Dtos.OrderItemDto;
import com.icement.api.iCement.Domains.Order.OrderRepository;
import com.icement.api.iCement.Domains.Shared.Entities.Address;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;

public class OrderTestHelper {

    private final MockMvc mockMvc;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final UserAuthTestHelper userAuthTestHelper;

    private boolean isOrderCollectionCleared = false;

    public OrderTestHelper(
        MockMvc mockMvc,
        OrderRepository orderRepository,
        ObjectMapper objectMapper,
        UserAuthTestHelper userAuthTestHelper
    ) {
        this.mockMvc = mockMvc;
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.userAuthTestHelper = userAuthTestHelper;
    }

    public void deleteAllOrders() {
        if (isOrderCollectionCleared) {
            return;
        }
        isOrderCollectionCleared = true;
        orderRepository.deleteAll();
    }

    public ResultActions createOrder() throws Exception {
    
        return this.mockMvc.perform(post("/api/orders/create")
                .headers(userAuthTestHelper.getAuthorizationHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .content(createOrderRequest()));
    }

    public String createOrderRequest() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .customerId("customerId")
                .items(createOrderItems())
                .shippingAddress(createShippingAddress())
                .build();
        return objectMapper.writeValueAsString(orderDto);
    }

    private ArrayList<OrderItemDto> createOrderItems() {
        ArrayList<OrderItemDto> items = new ArrayList<>();
        OrderItemDto item = OrderItemDto.builder()
                .productNumber("productNumber123")
                .productName("Cement")
                .productVersion(1)
                .quantity(2)
                .price(50.0)
                .build();
        items.add(item);
        return items;
    }

    private Address createShippingAddress() {
        return Address.builder()
                .street("123 Main St")
                .city("Esiama")
                .postalCode("12345")
                .country("Ghana")
                .phoneNumber("1234567890")
                .region("Western Region")
                .name("John Doe")
                .digitalAddress("GA-123-4567")
                .build();
    }
}
