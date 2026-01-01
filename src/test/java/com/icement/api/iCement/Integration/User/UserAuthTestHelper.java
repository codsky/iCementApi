package com.icement.api.iCement.Integration.User;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.icement.api.iCement.user.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserAuthTestHelper {
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final String defaultUserEmail = "test@test.com";

    private final HttpHeaders headers = new HttpHeaders();

    public UserAuthTestHelper(MockMvc mockMvc, UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public void setAuthorizationHeader() throws Exception {
        if (headers.isEmpty()) {

            if (!defaultUserExists()) {
                registerUser();
            }
            headers.add("Authorization", "Bearer " + loginUserAndReturnToken());
        }
    }

    private boolean defaultUserExists() {
        //TODO: replace findAllById with findByEmail later
        return userRepository.findByUsername(defaultUserEmail).isPresent();
    }

    public String loginUserAndReturnToken() throws Exception {
        String postRequest = """
            { \"email\": \"%s\", 
              \"password\": \"password\"
            }                
        """.formatted(defaultUserEmail);

        ResultActions response = this.mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postRequest))
            .andExpect(status().isOk());

        return response.andReturn().getResponse().getContentAsString();
    }

    public HttpHeaders getAuthorizationHeaders() {
        return headers;
    }
    
    public void clearAuthorizationHeader() {
        headers.clear();
    }
}
