package com.kamanda.security.controller;

import com.kamanda.security.model.domain.User;
import com.kamanda.security.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }


    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
