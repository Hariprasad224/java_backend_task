package com.example.demo.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.ProductRepository.ProductRepository;
import com.example.demo.product.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductPrice(Long id, double price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id " + id));
        product.setPrice(price);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    public List<Product> getAllProductsSortedByName() {
        return productRepository.findAllByOrderBynameAsc();
    }
}
