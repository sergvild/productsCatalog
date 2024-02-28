package com.fiphiker.product.service;

import com.fiphiker.product.entity.Product;
import com.fiphiker.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getProductByUniqId(String uniqId) throws ProductNotFoundException;
    List<Product> getProductsBySku(String sku);
}
