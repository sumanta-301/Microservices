package com.example.jwt.jwtToken.service;

import com.example.jwt.jwtToken.modal.User;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service

public class UserService {


    private List<User> store = new ArrayList<User>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Sumanta Ghosh","sumantaghosh301@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Sachin Ghosh","sachinghosh107@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Sourav Ghosh","sourabghosh@gmail.com"));

    }
    public List<User> getStore() {
        return store;
    }
}
