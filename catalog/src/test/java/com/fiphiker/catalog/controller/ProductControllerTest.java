package com.fiphiker.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiphiker.catalog.entity.Product;
import com.fiphiker.catalog.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CatalogService productService;

    Product product;
    List<Product> productList;

    @BeforeEach
    void init(){
        product = new Product();
        product.setNameTitle("Alfred Dunner® Essential Pull On Capri Pant");
        product.setCategory("alfred dunner");

        productList = List.of(product);
    }

    @Test
    void getProductByUniqId() throws Exception {
        when(productService.getProductByUniqId(anyString())).thenReturn(product);

        mockMvc.perform(get("/products/b6c0b6bea69c722939585baeac73c13d"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameTitle").value("Alfred Dunner® Essential Pull On Capri Pant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("alfred dunner"));
    }

    @Test
    void getProductsBySku() throws Exception {
        when(productService.getProductsBySku(anyString())).thenReturn(productList);

        mockMvc.perform(get("/products/sku/1234"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameTitle").value("Alfred Dunner® Essential Pull On Capri Pant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category").value("alfred dunner"));
    }
}