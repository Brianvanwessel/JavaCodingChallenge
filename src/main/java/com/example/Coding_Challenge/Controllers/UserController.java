package com.example.Coding_Challenge.Controllers;

import com.example.Coding_Challenge.Objects.User;
import com.example.Coding_Challenge.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class UserController defines all API mappings for the User table.
 */

@RestController
@RequestMapping(path = "api/v1/User")
public class UserController {

    private final UserService userService;

    /**
     * Constructor that initiates the UserService.
     *
     * @param userService Instance of the UserService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * The function getUsers is a Get mapping that calls the getUsers function from the userService.
     *
     * @return List of User Objects.
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * The function createUser is a Post mapping that first parses the request body and creates a User object.
     * After that it calls the createUser function from the userService.
     *
     * @param user
     */
    @PostMapping
    public ResponseEntity CreateUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
