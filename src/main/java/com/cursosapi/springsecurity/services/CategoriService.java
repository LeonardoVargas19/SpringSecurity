package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.dto.SaveCategory;
import com.cursosapi.springsecurity.persistence.entity.Category;
import com.cursosapi.springsecurity.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriService {
     Page<Category> findAll(Pageable pageable);

     Optional<Category> findOneId(Long categoryId);

     Category createOne(SaveCategory saveCategory);



     Category updateOneById(Long categoryId, SaveCategory saveCategory);

     Category disableOneById(Long categoryId);

    Optional<Category> findOneById(Long categoryId);
}
