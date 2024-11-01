package com.example.jwt.jwtToken.controller;

import com.example.jwt.jwtToken.service.UserService;
import com.example.jwt.jwtToken.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")

public class controller {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser() {
        System.out.println("working now");
        return userService.getStore();

    }
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();


    }
}
