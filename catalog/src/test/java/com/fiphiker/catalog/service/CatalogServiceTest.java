package com.fiphiker.catalog.service;

import com.fiphiker.catalog.entity.Product;
import com.fiphiker.catalog.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogServiceTest {

    @Autowired
    CatalogRepository catalogRepository;

    @Test
    void getProductByUniqId() {
        Optional<Product> product = catalogRepository.findProductByUniqId("b6c0b6bea69c722939585baeac73c13d");
        assertFalse(product.isEmpty());
    }

    @Test
    void getProductByUniqId_incorrectUniqId() {
        Optional<Product> product = catalogRepository.findProductByUniqId("b6c0b6bea69c722939585baeac73c13dsfsf");
        assertTrue(product.isEmpty());
    }

    @Test
    void getProductsBySku() {
        List<Product> productList = catalogRepository.findProductsBySku("pp5006380337");
        assertFalse(productList.isEmpty());
    }

    @Test
    void getProductsBySku_incorrectScu() {
        List<Product> productList = catalogRepository.findProductsBySku("pp500df380337");
        assertTrue(productList.isEmpty());
    }
}