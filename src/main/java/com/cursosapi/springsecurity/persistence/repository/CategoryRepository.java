package com.cursosapi.springsecurity.persistence.repository;

import com.cursosapi.springsecurity.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
