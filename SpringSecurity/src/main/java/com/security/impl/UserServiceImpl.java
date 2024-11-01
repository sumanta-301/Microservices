package com.security.impl;

import com.security.entity.User;
import com.security.repostiroy.UserRepo;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> saveAll(List<User> user) {
        return userRepo.saveAll(user);
    }
}
