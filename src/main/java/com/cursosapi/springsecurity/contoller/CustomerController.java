package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.RegisteredUser;
import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.services.auth.AutenticationServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private AutenticationServices autenticationServices;
    @PostMapping
    public ResponseEntity<RegisteredUser> registrerOne(@RequestBody @Valid SaveUser newUser){
        RegisteredUser registeredUser = autenticationServices.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
