package com.chari.crud.controller;

import com.chari.crud.model.User;
import com.chari.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private User user;
    @GetMapping("/index")
    public String welcome() {
        return "Welcome Page";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid User user, BindingResult result){
        if(result.hasErrors()) {
            return "create user failed";
        }
        return "User created successfully";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id) {
       user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" +id));
        userRepository.delete(user);
        return "User deleted";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id, User userModel) {
        user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" +id));
        userRepository.save(userModel);
        return "User updated successfully";
    }

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

}
