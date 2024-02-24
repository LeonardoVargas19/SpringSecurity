package com.cursosapi.springsecurity.services.auth;

import com.cursosapi.springsecurity.dto.RegisteredUser;
import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

        String jwt = jwtService.generarToken(user,generateExtraClaims(user));
        userDTO.setJwt(jwt);
        return userDTO;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String , Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());
        return extraClaims;
    }
}
