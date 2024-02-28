package com.fiphiker.product.dto;

import com.fiphiker.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvailability {
    private Product product;
    private String availabilityStatus;
}
