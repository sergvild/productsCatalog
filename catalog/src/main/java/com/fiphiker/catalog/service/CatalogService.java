package com.fiphiker.catalog.service;

import com.fiphiker.catalog.entity.Product;

import java.util.List;

public interface CatalogService {
    Product getProductByUniqId(String uniqId);
    List<Product> getProductsBySku(String sku);
}
