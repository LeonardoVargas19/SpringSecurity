package com.cursosapi.springsecurity.services;

import com.cursosapi.springsecurity.dto.SaveProduct;
import com.cursosapi.springsecurity.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface ProductService {


    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneId(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);
}
