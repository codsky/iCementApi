package com.icement.api.iCement.Integration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.User.User;
import com.icement.api.iCement.User.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@AutoConfigureMockMvc
public class AuthControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testRegistration() throws Exception {
       this.registerUser();
        // Verify that the user was created in the database
        List<User> users = userRepository.findUsersByFilter();
        assertEquals(1, users.size());
    }

    @Test
    public void testLogin() throws Exception {
        this.registerUser();

        String postRequest = """
            { \"email\": \"test@test.com\", 
              \"password\": \"password\"
            }                
        """;

        this.mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postRequest))
            .andExpect(status().isOk());
    }

    private void registerUser() throws Exception {
        String postRequest = """
            { \"email\": \"test@test.com\", 
              \"password\": \"password\",
              \"username\": \"testuser\",
              \"role\": \"RETAILER\"
            }                
        """;
        this.mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequest))
                .andExpect(status().isOk());
    }

}
