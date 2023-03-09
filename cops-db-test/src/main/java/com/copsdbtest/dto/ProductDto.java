package com.copsdbtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    private String productName;
    private int supplierId;
    private int categoryId;
    private String unit;
    private double price;
}
