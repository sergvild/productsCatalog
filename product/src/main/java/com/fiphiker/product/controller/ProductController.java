package com.fiphiker.product.controller;

import com.fiphiker.product.entity.Product;
import com.fiphiker.product.exception.ProductNotFoundException;
import com.fiphiker.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{uniqId}")
    public ResponseEntity<Product> getProductByUniqId(@PathVariable String uniqId) throws ProductNotFoundException {
        Product product = productService.getProductByUniqId(uniqId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<Product>> getProductsBySku(@PathVariable String sku){
        List<Product> productList = productService.getProductsBySku(sku);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
