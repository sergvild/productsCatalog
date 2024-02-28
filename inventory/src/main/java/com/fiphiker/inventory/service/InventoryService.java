package com.fiphiker.inventory.service;

import com.fiphiker.inventory.dto.ProductAvailability;
import com.fiphiker.inventory.entity.Product;

import java.util.List;

public interface InventoryService {
    List<ProductAvailability> getProductsAvailabilityByUniqIds(List<String> uniqIds);
}
