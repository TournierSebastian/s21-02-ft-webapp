package com.wallex.financial_platform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallex.financial_platform.services.impl.UserService;
import lombok.AllArgsConstructor;
import java.util.List;
import com.wallex.financial_platform.entities.User;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class userController {
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
