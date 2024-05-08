package com.cursosapi.springsecurity.persistence.repository.security;

import com.cursosapi.springsecurity.persistence.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String defaultRol);
}
