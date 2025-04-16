package com.icement.api.iCement.Integration.User;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.User.User;
import com.icement.api.iCement.User.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserTestHelper.class})
public class AuthControllerTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTestHelper userTestHelper;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testRegistration() throws Exception {
       this.userTestHelper.registerUser();

        List<User> users = userRepository.findUsersByFilter();
        assertEquals(1, users.size());
    }

    @Test
    public void testLogin() throws Exception {
        this.userTestHelper.registerUser();
        String Token = this.userTestHelper.loginUserAndReturnToken();
        assertNotNull(Token);
    }

}
