package com.cursosapi.springsecurity.persistence.repository;

import com.cursosapi.springsecurity.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
