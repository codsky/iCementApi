package com.icement.api.iCement.Integration.User;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.icement.api.iCement.BaseIntegrationTest;
import com.icement.api.iCement.Domains.User.User;
import com.icement.api.iCement.Domains.User.UserRepository;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private UserAuthTestHelper userAuthTestHelper;

    private boolean isUserCollectionCleared = false;

    @BeforeAll
    public void init() {
        userAuthTestHelper = new UserAuthTestHelper(mockMvc);
    }

    @BeforeEach
    public void setUp() throws Exception {
        deleteAllUsers();
        userAuthTestHelper.setAuthorizationHeader();
    }

    private void deleteAllUsers() {
        if (isUserCollectionCleared) {
            return;
        }
        isUserCollectionCleared = true;
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userRepository.findUsersByFilter().get(0);

        mockMvc.perform(get("/api/users/" + user.getId()).headers(userAuthTestHelper.getAuthorizationHeaders()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        userAuthTestHelper.registerUser("test2@test.com");

        mockMvc.perform(get("/api/users").headers(userAuthTestHelper.getAuthorizationHeaders()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String email = "test3@test.com";
        userAuthTestHelper.registerUser(email);
        Optional<User> user = userRepository.findByEmail(email);
        assert user.isPresent();

        mockMvc.perform(delete("/api/users/" + user.get().getId()).headers(userAuthTestHelper.getAuthorizationHeaders()))
                .andExpect(status().isOk());
    }
}
