package com.cursosapi.springsecurity.persistence.repository;

import com.cursosapi.springsecurity.persistence.entity.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository  extends JpaRepository<Operation,Long> {

    @Query("SELECT o FROM Operation o WHERE o.permitAll = TRUE ")
    List<Operation> findByPucliccAcces();
}
