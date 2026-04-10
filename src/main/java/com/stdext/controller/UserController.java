package com.stdext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stdext.User;
import com.stdext.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // allow frontend access
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Register User
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        
        // check if email already exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return "User already exists!";
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Login User
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            return "User not found!";
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid credentials!";
        }

        return "Login successful!";
    }

    // ✅ Get all users
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}