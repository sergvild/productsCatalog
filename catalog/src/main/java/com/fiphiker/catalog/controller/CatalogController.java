package com.fiphiker.catalog.controller;

import com.fiphiker.catalog.entity.Product;
import com.fiphiker.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/{uniqId}")
    public ResponseEntity<Product> getProductByUniqId(@PathVariable String uniqId){
        Product product = catalogService.getProductByUniqId(uniqId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<Product>> getProductsBySku(@PathVariable String sku){
        List<Product> productList = catalogService.getProductsBySku(sku);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
