package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleServices {

    Optional<Role> findDefaultRole();
}
