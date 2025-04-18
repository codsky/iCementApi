package com.icement.api.iCement.User;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(@ModelAttribute UserListFilterDto filter) {
        filter = setFilterIfNoneIsGiven(filter);
        return userService.getAllUsers(filter);
    }

    private UserListFilterDto setFilterIfNoneIsGiven(UserListFilterDto filter) {
        if (filter == null) {
            filter = new UserListFilterDto();
        }
        return filter;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, Principal principal) {
        System.out.println("Authorized user: " + principal.getName());
        userService.delete(id, principal.getName());
        return "User deleted successfully";
    }
}
