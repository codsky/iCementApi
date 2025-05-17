package com.icement.api.iCement.Domains.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icement.api.iCement.Exceptions.NotFoundException;

@Service
public class UserService {

    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User getUserById(String id) throws NotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    List<User> getAllUsers(UserListFilterDto filter) {
        return userRepository.findUsersByFilter(setFilterIfNoneIsGiven(filter));
    }

    private UserListFilterDto setFilterIfNoneIsGiven(UserListFilterDto filter) {
        if (filter == null) {
            filter = new UserListFilterDto();
        }
        return filter;
    }

    void delete(String id, String deletedByUserId) throws NotFoundException {
        User user = this.getUserById(id);
        userRepository.delete(user, deletedByUserId);
    }

}
