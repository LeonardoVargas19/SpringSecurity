package com.cursosapi.springsecurity.services.impl;

import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.persistence.repository.UserRepository;
import com.cursosapi.springsecurity.persistence.util.Role;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServicesImp implements UserServices {

    private UserRepository userRepository;

    @Override
    public User createOneCustomer(SaveUser newUser) {
        //validatorPassword(newUser);
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getRepetedPassword());
        user.setName(newUser.getUsername());
        user.setRole(Role.ROLE_CUSTOMER);
        return userRepository.save(user);
    }




}
