package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.auth.AuthenticationResponse;
import com.cursosapi.springsecurity.dto.auth.AuthenticationResquest;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.auth.AutenticationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AutenticationServices autenticationServices;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

      boolean isTokenValid = autenticationServices.validateToken(jwt);
      return ResponseEntity.ok(isTokenValid);
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationResquest
                                                                       requestAuthentication){
        AuthenticationResponse rsp = autenticationServices.login(requestAuthentication);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile () {
        User user = autenticationServices.findLoggedInUser();
        return ResponseEntity.ok(user);


    }

}
