package com.security.service;

import com.security.entity.User;

import java.util.List;

public interface UserService {
   List<User> getAll();
    User findById(int id);
    List<User> saveAll(List<User> user);
}
