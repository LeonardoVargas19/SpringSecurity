package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.RegisteredUser;
import com.cursosapi.springsecurity.dto.SaveUser;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.auth.AutenticationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AutenticationServices autenticationServices;
    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RegisteredUser> registrerOne(@RequestBody @Valid SaveUser newUser) {
        RegisteredUser registeredUser = autenticationServices.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
       return ResponseEntity.ok(List.of());
    }
}
