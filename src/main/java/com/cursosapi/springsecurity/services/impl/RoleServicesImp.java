package com.cursosapi.springsecurity.services.impl;

import com.cursosapi.springsecurity.persistence.entity.security.Role;
import com.cursosapi.springsecurity.persistence.repository.security.RoleRepository;
import com.cursosapi.springsecurity.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServicesImp  implements RoleServices {
    @Value("${security.default.role}")
    private String defaultRol;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRol);
    }
}
