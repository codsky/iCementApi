package com.icement.api.iCement.Domains.User;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icement.api.iCement.Exceptions.UserNotFoundException;

@Service
public class UserService {

    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User getUserById(String id) throws UserNotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    List<User> getAllUsers(UserListFilterDto filter) {
        return userRepository.findUsersByFilter(filter);
    }

    void delete(String id, String deletedByUserId) throws UserNotFoundException {
        User user = this.getUserById(id);
        userRepository.delete(user, deletedByUserId);
    }

}
