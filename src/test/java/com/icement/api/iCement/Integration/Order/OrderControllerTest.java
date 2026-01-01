package com.icement.api.iCement.Integration.Order;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icement.api.iCement.Integration.BaseIntegrationTest;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;
import com.icement.api.iCement.order.repository.OrderRepository;
import com.icement.api.iCement.product.repository.ProductRepository;
import com.icement.api.iCement.user.repository.UserRepository;

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
    
    // @BeforeAll
    // public void init() {
    //     userAuthTestHelper = new UserAuthTestHelper(mockMvc, userRepository);
    //     orderTestHelper = new OrderTestHelper(
    //         mockMvc,
    //         orderRepository,
    //         objectMapper,
    //         userAuthTestHelper
    //     );
    //     productTestHelper = new ProductTestHelper(productRepository);
    // }
    
    // @BeforeEach
    // public void setUp() throws Exception {
    //     orderTestHelper.deleteAllOrders();
    //     userAuthTestHelper.setAuthorizationHeader();
    // }

    // @Test
    // public void testCreateOrder() throws Exception {
    //     productTestHelper.createProduct();
    //     orderTestHelper.createOrder()
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$.customerId").value("customerId"))
    //         .andExpect(jsonPath("$.items").isArray())
    //         .andExpect(jsonPath("$.items[0].productNumber").value("productNumber123"));
    // }

    // @Test
    // public void testCreateOrderWithNonExistingProduct() throws Exception {
    //     productRepository.deleteAll();
    //     orderTestHelper.createOrder()
    //         .andExpect(status().isNotFound())
    //         .andExpect(jsonPath("$.error").value("Products with product numbers productNumber123 not found!"));
    // }

}
