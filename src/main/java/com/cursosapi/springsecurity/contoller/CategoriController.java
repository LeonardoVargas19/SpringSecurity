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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriController {
    @Autowired
    private CategoriService categoriService;
    @PreAuthorize("hasAuthority('READ_ALL_CATEGORIES')")
    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoriService.findAll(pageable);
        if (!categoryPage.isEmpty()) {
            return ResponseEntity.ok(categoryPage);
        }
        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasAuthority('READ_ONE_CATEGORY')")

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable Long categoryId){

        Optional<Category> category = categoriService.findOneById(categoryId);

        if(category.isPresent()){
            return ResponseEntity.ok(category.get());
        }

        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasAuthority('CREATE_ONE_CATEGORY')")
    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategory saveCategory) {
        Category category = categoriService.createOne(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @PreAuthorize("hasAuthority('UPDATE_ONE_CATEGORY')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId,  @RequestBody @Valid SaveCategory saveCategory) {
        Category category = categoriService.updateOneById(categoryId,saveCategory);
        return ResponseEntity.ok(category);
    }
    @PreAuthorize("hasAuthority('DISABLE_ONE_CATEGORY')")
    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId) {
        Category category = categoriService.disableOneById(categoryId);
        return ResponseEntity.ok(category);
    }


}
