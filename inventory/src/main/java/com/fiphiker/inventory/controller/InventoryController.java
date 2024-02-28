package com.fiphiker.inventory.controller;

import com.fiphiker.inventory.dto.ProductAvailability;
import com.fiphiker.inventory.entity.Product;
import com.fiphiker.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<List<ProductAvailability>> getProductsAvailabilityByUniqIds(@RequestBody List<String> uniqIds){
        List<ProductAvailability> productAvailabilityList =  inventoryService.getProductsAvailabilityByUniqIds(uniqIds);
        return new ResponseEntity<>(productAvailabilityList, HttpStatus.OK);
    }
}
