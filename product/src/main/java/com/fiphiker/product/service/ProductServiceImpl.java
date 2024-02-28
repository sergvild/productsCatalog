package com.fiphiker.product.service;

import com.fiphiker.product.client.CatalogServiceClient;
import com.fiphiker.product.client.InventoryServiceClient;
import com.fiphiker.product.dto.ProductAvailability;
import com.fiphiker.product.entity.Product;
import com.fiphiker.product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final CatalogServiceClient catalogServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public ProductServiceImpl(CatalogServiceClient catalogServiceClient, InventoryServiceClient inventoryServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    public Product getProductByUniqId(String uniqId) throws ProductNotFoundException {
        ResponseEntity<Product> productResponseEntity = catalogServiceClient.getProductByUniqId(uniqId);
        Product product = productResponseEntity.getBody();

        List<ProductAvailability> productAvailabilityList = inventoryServiceClient.getProductsAvailabilityByUniqIds(List.of(uniqId)).getBody();

        Optional<ProductAvailability> productOptional = productAvailabilityList.stream()
                .filter(productAvailability -> "AVAILABLE".equals(productAvailability.getAvailabilityStatus()))
                .findFirst();


        return productOptional.map(ProductAvailability::getProduct).orElseThrow(()->new ProductNotFoundException("Coudn't find product by uniqId="+uniqId));

    }

    @Override
    public List<Product> getProductsBySku(String sku) {
        ResponseEntity<List<Product>> productsResponseEntity = catalogServiceClient.getProductsBySku(sku);
        List<Product> productList = productsResponseEntity.getBody();
        List<String> uniqIds = productList.stream().map(product -> product.getUniqId()).collect(Collectors.toList());

        List<ProductAvailability> productAvailabilityList = inventoryServiceClient.getProductsAvailabilityByUniqIds(uniqIds).getBody();

        return productAvailabilityList.stream()
                .filter(productAvailability -> "AVAILABLE".equals(productAvailability.getAvailabilityStatus()))
                .map(ProductAvailability::getProduct)
                .collect(Collectors.toList());
    }
}
