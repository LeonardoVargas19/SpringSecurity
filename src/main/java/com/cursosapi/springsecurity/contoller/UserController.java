package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.persistence.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername (@PathVariable String username){


        return null;
    }
}
