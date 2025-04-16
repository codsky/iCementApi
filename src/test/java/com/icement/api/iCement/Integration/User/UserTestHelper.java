package com.icement.api.iCement.Integration.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@Component
public class UserTestHelper {
    @Autowired
    private MockMvc mockMvc;

    public void registerUser() throws Exception {
        this.registerUser("test@test.com");
    }

    public void registerUser(String email) throws Exception {
        String postRequest = String.format("""
                { "username": "testuser", 
                  "email": "%s", 
                  "password": "password",
                  "role": "RETAILER",
                }                
            """, email);
        this.mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequest))
                .andExpect(status().isOk());
    }

    public String loginUserAndReturnToken() throws Exception {
        String postRequest = """
            { \"email\": \"test@test.com\", 
              \"password\": \"password\"
            }                
        """;

        ResultActions response = this.mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postRequest))
            .andExpect(status().isOk());

        return response.andReturn().getResponse().getContentAsString();
    }

}
