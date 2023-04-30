package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.AbstractDocument;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void testPage() {

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(1000)
                .price(500)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(2000)
                .price(1000)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(3000)
                .price(1500)
                .build());

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,3));


        System.out.println(productPage);
        System.out.println(productPage.getContent());
    }

    @Test
    void testQuery() {

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(1000)
                .price(500)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(2000)
                .price(1000)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(3000)
                .price(1500)
                .build());

        List<Product> 펜 = productRepository.findByName("펜");

        for (int i = 0; i < 펜.size(); i++) {

            System.out.println(펜.get(i));
            System.out.println(펜.get(i).getName());

        }

    }

    @PersistenceContext
    EntityManager entitiyManager;

    @Test
    @DisplayName("JPAQuery를 활용한 QueryDSL 테스트 코드")
    void queryDslTest() {

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(1000)
                .price(500)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(2000)
                .price(1000)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(3000)
                .price(1500)
                .build());

        JPAQuery<Product> query = new JPAQuery<>(entitiyManager);

        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product name : " + product.getName());
            System.out.println("Product price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println("Product Stock : " + product.getCreatedAt());
            System.out.println("Product Stock : " + product.getUpdatedAt());
            System.out.println();
            System.out.println("--------------------");

        }

    }

    @Test
    @DisplayName("JPAQueryFactory를 활용한 QueryDSL 테스트 코드")
    void queryDslTest2() {

        productRepository.save(Product.builder()
                .name("펜")
                .stock(1000)
                .price(500)
                .build());

        productRepository.save(Product.builder()
                .name("펜")
                .stock(2000)
                .price(1000)
                .build());

        productRepository.save(new Product().builder()
                .name("펜")
                .stock(3000)
                .price(1500)
                .build());

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entitiyManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product name : " + product.getName());
            System.out.println("Product price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("--------------------");

        }

    }


    @Test
    @DisplayName("JPAQueryFactory의 select() 메서드를 활용")
    void queryDslTest3() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entitiyManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("Product name : " + product);
            System.out.println();
            System.out.println("--------------------");

        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : tupleList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("Product name : " + product.get(qProduct.name));
            System.out.println("Product price : " + product.get(qProduct.price));
            System.out.println();
            System.out.println("--------------------");

        }
    }

    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList ) {
            System.out.println("--------------------------");
            System.out.println(product);
            System.out.println("--------------------------");

        }
    }






}