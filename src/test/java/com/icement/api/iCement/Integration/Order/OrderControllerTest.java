package com.icement.api.iCement.Integration.Order;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.Domains.Order.OrderRepository;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;

@AutoConfigureMockMvc
public class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderTestHelper orderTestHelper;

    private UserAuthTestHelper userAuthTestHelper;
    
    @BeforeAll
    public void init() {
        orderTestHelper = new OrderTestHelper(
            mockMvc,
            orderRepository,
            objectMapper,
            new UserAuthTestHelper(mockMvc)
        );
        userAuthTestHelper = new UserAuthTestHelper(mockMvc);
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        orderTestHelper.deleteAllOrders();
        userAuthTestHelper.setAuthorizationHeader();
    }

    @Test
    public void testCreateOrder() throws Exception {
        orderTestHelper.createOrder();
    }

}
