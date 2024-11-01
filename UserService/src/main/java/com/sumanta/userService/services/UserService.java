package com.sumanta.userService.services;

import com.sumanta.userService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<?> getAllUser();
    User getUser(String userId);
    User deleteUser(String userId);

}
