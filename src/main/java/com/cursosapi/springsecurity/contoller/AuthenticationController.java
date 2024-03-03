package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.auth.AuthenticationResponse;
import com.cursosapi.springsecurity.dto.auth.AuthenticationResquest;
import com.cursosapi.springsecurity.services.auth.AutenticationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AutenticationServices autenticationServices;
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationResquest
                                                                       requestAuthentication){
        AuthenticationResponse rsp = autenticationServices.login(requestAuthentication);
        return ResponseEntity.ok(rsp);
    }
}
