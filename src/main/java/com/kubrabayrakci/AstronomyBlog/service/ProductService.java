package com.kubrabayrakci.AstronomyBlog.service;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.model.Product;
import com.kubrabayrakci.AstronomyBlog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }

    public void saveProduct(Product product) {

        productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {

        Optional<Product> postOptional = productRepository.findById(id);
        if (postOptional.isPresent()) {
            Product product = postOptional.get();
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    public Product findTheProductById(Long id) {

        Optional<Product> postOptional = productRepository.findById(id);
        if (postOptional.isPresent()) {
            Product product = postOptional.get();
            return product;
        }
        return null;
    }


}

