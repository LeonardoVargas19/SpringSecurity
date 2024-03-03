package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;

import java.util.Optional;

public interface UserServices {
    User createOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}
