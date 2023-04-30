package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void savAndReadTest() {

        Product product = new Product();

        product.setName("스프링 부트 jpa");
        product.setPrice(5000);
        product.setStock(500);
        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();

        System.out.println("------------------------");
        System.out.println(productDetail.getId());
        System.out.println("------------------------");


        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트와 JPA를 함께 볼 수 있는 책");

        productDetailRepository.save(productDetail);

        System.out.println("------------------------");
        System.out.println(productDetail.getId());
        System.out.println("------------------------");

        System.out.println(productDetailRepository
                .findById(productDetail.getId())
                .get().getProduct());

        System.out.println(productDetailRepository
                .findById(productDetail.getId())
                .get());
    }

}