package com.cursosapi.springsecurity.services.auth;

import com.cursosapi.springsecurity.dto.RegisteredUser;
import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.stereotype.Service;

@Service
public class AutenticationServices {
    private UserServices userServices;
    private JwtServices jwtService;
    public RegisteredUser registerOneCustomer(SaveUser newUser) {
        User user = userServices.createOneCustomer(newUser);

        RegisteredUser userDTO = new RegisteredUser();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        String jwt = jwtService.generarToken(user);
        userDTO.setJwt(jwt);
        return userDTO;
    }
}
