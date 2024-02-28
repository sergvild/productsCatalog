package com.fiphiker.inventory.client;


import com.fiphiker.inventory.entity.Product;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("catalog-service")
public interface CatalogServiceClient {

    @GetMapping("/catalogs/{uniqId}")
    ResponseEntity<Product> getProductByUniqId(@PathVariable String uniqId);
}
