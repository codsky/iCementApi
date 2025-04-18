package com.icement.api.iCement.Integration.User;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTestHelper {
    private MockMvc mockMvc;

    UserTestHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public void registerUser() throws Exception {
        this.registerUser("test@test.com");
    }

    public void registerUser(String email) throws Exception {
        String postRequest = String.format("""
            { \"email\": \"%s\", 
              \"password\": \"password\",
              \"username\": \"userTest\",
              \"role\": \"RETAILER\"
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
