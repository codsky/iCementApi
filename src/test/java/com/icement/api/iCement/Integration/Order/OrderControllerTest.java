package com.icement.api.iCement.Integration.Order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.Domains.Order.OrderRepository;
import com.icement.api.iCement.Integration.User.UserAuthTestHelper;

public class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private OrderRepository orderRepository;

    private UserAuthTestHelper userAuthTestHelper;
    
    private boolean isOrderCollectionCleared = false;
    
    @BeforeAll
    public void init() {
        // orderTestHelper = new OrderTestHelper(mockMvc);
        userAuthTestHelper = new UserAuthTestHelper(mockMvc);
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        deleteAllOrders();
        userAuthTestHelper.setAuthorizationHeader();
    }
    
    private void deleteAllOrders() {
        if (isOrderCollectionCleared) {
            return;
        }
        isOrderCollectionCleared = true;
        orderRepository.deleteAll();
    }

}
