package com.cursosapi.springsecurity.services.auth;

import com.cursosapi.springsecurity.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServices {


    public String generarToken(UserDetails user) {
        return null;
    }
}
