package com.sumanta.userService.repositories;

import com.sumanta.userService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
