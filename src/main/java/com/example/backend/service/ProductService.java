package com.example.backend.service;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    @Transactional
    public Product create(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    @Transactional
    public Product update(Integer id, Product data) {
        Product product = findById(id);
        product.setName(data.getName());
        product.setPrice(data.getPrice());
        product.setStock(data.getStock());
        product.setImageUri(data.getImageUri());
        return repository.save(product);
    }

    @Transactional
    public void delete(Integer id) {
        repository.delete(findById(id));
    }
}
