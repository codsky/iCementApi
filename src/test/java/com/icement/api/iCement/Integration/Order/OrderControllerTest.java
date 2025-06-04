package com.icement.api.iCement.Integration.Order;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.Domains.Order.OrderRepository;
import com.icement.api.iCement.Domains.User.UserRepository;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;
import com.icement.api.iCement.Domains.Product.ProductRepository;

@AutoConfigureMockMvc
public class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private OrderTestHelper orderTestHelper;

    private UserAuthTestHelper userAuthTestHelper;
    private ProductTestHelper productTestHelper;
    
    @BeforeAll
    public void init() {
        userAuthTestHelper = new UserAuthTestHelper(mockMvc, userRepository);
        orderTestHelper = new OrderTestHelper(
            mockMvc,
            orderRepository,
            objectMapper,
            userAuthTestHelper
        );
        productTestHelper = new ProductTestHelper(productRepository);
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        orderTestHelper.deleteAllOrders();
        userAuthTestHelper.setAuthorizationHeader();
    }

    @Test
    public void testCreateOrder() throws Exception {
        productTestHelper.createProduct();
        orderTestHelper.createOrder();
    }

}
