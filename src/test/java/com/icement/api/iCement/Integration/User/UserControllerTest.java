package com.icement.api.iCement.Integration.User;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.icement.api.iCement.Integration.BaseIntegrationTest;
import com.icement.api.iCement.user.User;
import com.icement.api.iCement.user.repository.UserRepository;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private UserAuthTestHelper userAuthTestHelper;

    private boolean isUserCollectionCleared = false;


    // @BeforeAll
    // public void init() {
    //     userAuthTestHelper = new UserAuthTestHelper(mockMvc, userRepository);
    // }

    // @BeforeEach
    // public void setUp() throws Exception {
    //     deleteAllUsers();
    //     userAuthTestHelper.setAuthorizationHeader();
    // }

    // private void deleteAllUsers() {
    //     if (isUserCollectionCleared) {
    //         return;
    //     }
    //     isUserCollectionCleared = true;
    //     mongoTemplate.dropCollection(User.class);
    // }

    // @Test
    // public void testGetUserById() throws Exception {
    //     User user = userRepository.findUsersByFilter().get(0);

    //     mockMvc.perform(get("/api/users/" + user.getId()).headers(userAuthTestHelper.getAuthorizationHeaders()))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id").value(user.getId()))
    //             .andExpect(jsonPath("$.email").value(user.getEmail()));
    // }

    // @Test
    // public void testGetAllUsers() throws Exception {
    //     userAuthTestHelper.registerUser("test2@test.com");

    //     mockMvc.perform(get("/api/users").headers(userAuthTestHelper.getAuthorizationHeaders()))
    //             .andExpect(status().isOk());
    // }

    // @Test
    // public void testDeleteUserById() throws Exception {
    //     String email = "test3@test.com";
    //     userAuthTestHelper.registerUser(email);
    //     Optional<User> user = userRepository.findByEmail(email);
    //     assert user.isPresent();

    //     mockMvc.perform(delete("/api/users/" + user.get().getId()).headers(userAuthTestHelper.getAuthorizationHeaders()))
    //             .andExpect(status().isOk());
    // }

    // @Test
    // @Disabled
    // public void testDropAllUsers() throws Exception {
    //     mongoTemplate.dropCollection(User.class);
    //     Criteria criteria = Criteria.where("_id").ne(null);
    //     List<User> users = userRepository.findWithCriteria(criteria);
        
    //     assert users.isEmpty() : "User collection should be empty after drop";
    // }
        
}
