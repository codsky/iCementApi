package com.icement.api.iCement.Integration.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.User.User;
import com.icement.api.iCement.User.UserRepository;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private UserTestHelper userTestHelper;

    private final HttpHeaders Headers = new HttpHeaders();

    private boolean isUserCollectionCleared = false;

    @BeforeAll
    public void init() {
        userTestHelper = new UserTestHelper(mockMvc);
    }

    @BeforeEach
    public void setUp() throws Exception {
        deleteAllUsers();
        setAuthorizationHeader();
    }

    private void deleteAllUsers() {
        if (isUserCollectionCleared) {
            return;
        }
        isUserCollectionCleared = true;
        userRepository.deleteAll();
    }

    private void setAuthorizationHeader() throws Exception {
        if (Headers.isEmpty()) {
            userTestHelper.registerUser();
            Headers.add("Authorization", "Bearer " + userTestHelper.loginUserAndReturnToken());
        }
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userRepository.findUsersByFilter().get(0);

        mockMvc.perform(get("/api/users/" + user.getId()).headers(Headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        userTestHelper.registerUser("test2@test.com");

        mockMvc.perform(get("/api/users").headers(Headers))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String email = "test3@test.com";
        userTestHelper.registerUser(email);
        Optional<User> user = userRepository.findByEmail(email);
        assert user.isPresent();

        mockMvc.perform(delete("/api/users/" + user.get().getId()).headers(Headers))
                .andExpect(status().isOk());
    }
}
