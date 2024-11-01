package com.security.controller;

import com.security.entity.User;
import com.security.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")

public class DemoController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/")
    public List<User> getAllDemos() {

    return userService.getAll();

    }
    @GetMapping("/{id}")
    public User getDemoById (@PathVariable int id) {

        return userService.findById(id);
    }
    @PostMapping("/")
    public List<User> createDemos(@RequestBody List<User> userList) {
        return userService.saveAll(userList);

    }
}
