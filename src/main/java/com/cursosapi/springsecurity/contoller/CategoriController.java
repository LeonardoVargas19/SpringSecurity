package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.persistence.entity.Category;
import com.cursosapi.springsecurity.services.CategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriController {
    @Autowired
    private CategoriService categoriService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoriService.findAll(pageable);
        if (!categoryPage.isEmpty()) {
            return ResponseEntity.ok(categoryPage);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOne(@PathVariable Long categoryId) {
        Optional<Category> category = categoriService.findOneId(categoryId);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
