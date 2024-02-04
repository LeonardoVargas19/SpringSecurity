package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriService {
     Page<Category> findAll(Pageable pageable);

     Optional<Category> findOneId(Long categoryId);
}
