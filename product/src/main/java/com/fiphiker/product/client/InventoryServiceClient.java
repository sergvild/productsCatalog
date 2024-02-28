package com.fiphiker.product.client;
import com.fiphiker.product.dto.ProductAvailability;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryServiceClient {

    @PostMapping("/inventories")
    ResponseEntity<List<ProductAvailability>> getProductsAvailabilityByUniqIds(@RequestBody List<String> uniqIds);
}
