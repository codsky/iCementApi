package com.icement.api.iCement.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icement.api.iCement.common.exception.NotFoundException;
import com.icement.api.iCement.user.User;
import com.icement.api.iCement.user.dto.UserListFilterDto;
import com.icement.api.iCement.user.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User getUserById(Integer id) throws NotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    List<User> getAllUsers(UserListFilterDto filter) {
        return userRepository.findAll();
    }

    private UserListFilterDto setFilterIfNoneIsGiven(UserListFilterDto filter) {
        if (filter == null) {
            filter = new UserListFilterDto();
        }
        return filter;
    }

    void delete(Integer id, Integer deletedByUserId) throws NotFoundException {
        User user = this.getUserById(id);
        userRepository.deleteById(deletedByUserId);
    }

}
