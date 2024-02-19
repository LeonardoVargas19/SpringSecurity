package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;

public interface UserServices {
    User createOneCustomer(SaveUser newUser);
}
