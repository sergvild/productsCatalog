package com.fiphiker.catalog.service;

import com.fiphiker.catalog.entity.Product;
import com.fiphiker.catalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository productRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.productRepository = catalogRepository;
    }

    @Override
    public Product getProductByUniqId(String uniqId) {
        return productRepository.findProductByUniqId(uniqId).orElseThrow(()-> new RuntimeException("Couldn't find product by uniqId="+uniqId));
    }

    @Override
    public List<Product> getProductsBySku(String sku) {
        return productRepository.findProductsBySku(sku);
    }
}
