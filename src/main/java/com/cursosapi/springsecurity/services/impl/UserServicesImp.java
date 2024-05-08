package com.cursosapi.springsecurity.services.impl;

import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.exception.InvalidPasswordException;
import com.cursosapi.springsecurity.exception.ObjectNorFoundExeption;
import com.cursosapi.springsecurity.persistence.entity.security.Role;
import com.cursosapi.springsecurity.persistence.entity.security.User;
import com.cursosapi.springsecurity.persistence.repository.security.UserRepository;
import com.cursosapi.springsecurity.services.RoleServices;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleServices roleServices;


    @Override
    public User createOneCustomer(SaveUser newUser) {
        validatePassword(newUser);
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getUsername());
        Role defatultRole = roleServices.findDefaultRole()
                .orElseThrow(()-> new ObjectNorFoundExeption(" Role Not Found. Default Role"));
        user.setRole(defatultRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUser dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepetedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

        if(!dto.getPassword().equals(dto.getRepetedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

    }
}
