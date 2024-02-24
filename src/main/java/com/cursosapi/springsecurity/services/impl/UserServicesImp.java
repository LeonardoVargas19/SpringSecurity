package com.cursosapi.springsecurity.services.impl;

import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.exception.InvalidPasswordException;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.persistence.repository.UserRepository;
import com.cursosapi.springsecurity.persistence.util.Role;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserServicesImp implements UserServices {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User createOneCustomer(SaveUser newUser) {
        validatePassword(newUser);
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getUsername());
        user.setRole(Role.ROLE_CUSTOMER);
        return userRepository.save(user);
    }

    private void validatePassword(SaveUser dto) {
        if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepetedPassword())) {
            throw new InvalidPasswordException("Password don't match");
        }
        if (!dto.getPassword().equals(dto.getRepetedPassword())) {
            throw new InvalidPasswordException("Password don't match");
        }
    }
}
