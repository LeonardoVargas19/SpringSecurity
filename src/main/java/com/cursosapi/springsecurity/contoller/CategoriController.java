package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.SaveCategory;
import com.cursosapi.springsecurity.persistence.entity.Category;
import com.cursosapi.springsecurity.persistence.entity.Product;
import com.cursosapi.springsecurity.services.CategoriService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategory saveCategory) {
        Category category = categoriService.createOne(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PostMapping
    public ResponseEntity<Category> updateOneById(@RequestBody @Valid SaveCategory saveCategory) {
        Category category = categoriService.createOne(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{product}/disabled")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId) {
        Product product = categoriService.disableOneById(productId);
        return ResponseEntity.ok(product);


    }


}
