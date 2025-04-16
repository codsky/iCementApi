package com.icement.api.iCement.Integration.User;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.icement.api.iCement.User.User;
import com.icement.api.iCement.User.UserRepository;

@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTestHelper userTestHelper;

    private final HttpHeaders Headers = new HttpHeaders();

    @BeforeEach
    public void setUp() throws Exception {
        userRepository.deleteAll();
        setAuthorizationHeader();
    }

    private void setAuthorizationHeader() throws Exception {
        if (Headers.isEmpty()) {
            Headers.add("Authorization", "Bearer " + userTestHelper.loginUserAndReturnToken());
        }
    }



    @Test
    public void testGetUserById() throws Exception {
        userTestHelper.registerUser();
        User user = userRepository.findUsersByFilter().get(0);

        mockMvc.perform(get("/api/users/" + user.getId()).headers(Headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        userTestHelper.registerUser();
        userTestHelper.registerUser("test2@test.com");

        mockMvc.perform(get("/api/users/").headers(Headers))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteUserById() throws Exception {
        userTestHelper.registerUser();
        User user = userRepository.findUsersByFilter().get(0);

        mockMvc.perform(delete("/api/users/" + user.getId()).headers(Headers))
                .andExpect(status().isOk());
    }
}
