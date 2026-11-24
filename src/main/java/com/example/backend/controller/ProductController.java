package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /api/products
    @GetMapping
    public List<Product> getAll() {
        return service.findAll();
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product created = service.create(product);
        return ResponseEntity.created(URI.create("/api/products/" + created.getId())).body(created);
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @Valid @RequestBody Product product) {
        return service.update(id, product);
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
