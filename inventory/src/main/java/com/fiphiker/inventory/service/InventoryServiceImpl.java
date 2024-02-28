package com.fiphiker.inventory.service;

import com.fiphiker.inventory.client.CatalogServiceClient;
import com.fiphiker.inventory.dto.ProductAvailability;
import com.fiphiker.inventory.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final String[] PRODUCT_AVAILABILITY_STATUS = new String[]{"AVAILABLE","NOT AVAILABLE"};

    private final CatalogServiceClient catalogServiceClient;

    private List<ProductAvailability> productAvailabilityList;

    private final Map<String, ProductAvailability> productAvailabilityMap = new ConcurrentHashMap<>();

    @Autowired
    public InventoryServiceImpl( CatalogServiceClient catalogServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public List<ProductAvailability> getProductsAvailabilityByUniqIds(List<String> uniqIds) {
        List<ProductAvailability> productAvailabilities = new ArrayList<>();

        uniqIds.forEach(uniqId -> {
            productAvailabilityMap.computeIfAbsent(uniqId, this::obtainProductAvailabilityStatus);
            productAvailabilities.add(productAvailabilityMap.get(uniqId));
        });

        return productAvailabilities;
    }

    private ProductAvailability obtainProductAvailabilityStatus(String uniqId) {
        ResponseEntity<Product> productResponseEntity = catalogServiceClient.getProductByUniqId(uniqId);
        Product product = productResponseEntity.getBody();
        return new ProductAvailability(product, generateRandomStatus());
    }

    private String generateRandomStatus() {
        Random random = new Random();
        int randomIndex = random.nextInt(PRODUCT_AVAILABILITY_STATUS.length);
        return PRODUCT_AVAILABILITY_STATUS[randomIndex];
    }
}
