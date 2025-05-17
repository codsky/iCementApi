package com.icement.api.iCement.Integration.Order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;

@AutoConfigureMockMvc
public class OrderControllerTest extends BaseIntegrationTest {
    // @Autowired
    // private OrderRepository orderRepository;
    @Autowired
    private OrderTestHelper orderTestHelper;
    @Autowired
    private UserAuthTestHelper userAuthTestHelper;
    
    
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
