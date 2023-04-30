package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Category;
import com.springboot.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository  categoryRepository;

    @Test
    void relationshipTest() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(500);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for (Product item : products) {
            System.out.println(item);
        }

    }
}