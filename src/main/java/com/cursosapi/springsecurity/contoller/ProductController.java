package com.cursosapi.springsecurity.contoller;

import com.cursosapi.springsecurity.dto.SaveProduct;
import com.cursosapi.springsecurity.persistence.entity.Product;
import com.cursosapi.springsecurity.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll (Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);
        if (!productPage.isEmpty()) {
            return ResponseEntity.ok(productPage);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{product}")
    public ResponseEntity<Product> findOne (@PathVariable Long productId) {
        Optional<Product> product = productService.findOneId(productId);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId ,
                                                 @RequestBody @Valid SaveProduct saveProduct){
        Product product = productService.updateOneById(productId, saveProduct);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createOne (@RequestBody @Valid SaveProduct saveProduct) {
       Product product = productService.createOne(saveProduct);
       return ResponseEntity.status(HttpStatus.CREATED).body(product);


    }
}
