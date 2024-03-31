package com.cursosapi.springsecurity.services.auth;

import com.cursosapi.springsecurity.dto.RegisteredUser;
import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.dto.auth.AuthenticationResponse;
import com.cursosapi.springsecurity.dto.auth.AuthenticationResquest;
import com.cursosapi.springsecurity.exception.ObjectNorFoundExeption;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AutenticationServices {
    @Autowired
    private UserServices userServices;
    @Autowired
    private JwtServices jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AutenticationServices(UserServices userServices, JwtServices jwtService) {
        this.userServices = userServices;
        this.jwtService = jwtService;
    }

    public RegisteredUser registerOneCustomer(SaveUser newUser) {
        User user = userServices.createOneCustomer(newUser);

        RegisteredUser userDTO = new RegisteredUser();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        String jwt = jwtService.generarToken(user, generateExtraClaims(user));
        userDTO.setJwt(jwt);
        return userDTO;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationResquest requestAuthentication) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                requestAuthentication.getUsername(), requestAuthentication.getPassword()
        );
        authenticationManager.authenticate(authentication);
        UserDetails oneByUsername = userServices.findOneByUsername(requestAuthentication.getUsername()).get();
        String jwt = jwtService.generarToken(oneByUsername,generateExtraClaims((User) oneByUsername));
        AuthenticationResponse authResp = new AuthenticationResponse();
        authResp.setJwt(jwt);
        return authResp;
    }

    public boolean validateToken(String jwt) {
        try{
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e ){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser() {

        Authentication authentication = (UsernamePasswordAuthenticationToken ) SecurityContextHolder.getContext().getAuthentication();

            String username = (String) authentication.getPrincipal();
            return userServices.findOneByUsername(username)
                    .orElseThrow(()-> new ObjectNorFoundExeption("User not Found Username :"+username));

    }
}
