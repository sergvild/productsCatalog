package com.fiphiker.catalog.repository;

import com.fiphiker.catalog.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository {
    Optional<Product> findProductByUniqId(String uniqId);
    List<Product> findProductsBySku(String sku);
}
