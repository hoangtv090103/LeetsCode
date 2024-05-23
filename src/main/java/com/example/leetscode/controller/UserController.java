package com.example.leetscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leetscode.model.User;
import com.example.leetscode.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Value("${cross-origin-urls}")
    private String crossOriginUrls;

    @Autowired // Sử dụng để tiêm UserService vào UserController
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
