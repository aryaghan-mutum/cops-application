package com.copsdbtest.data;

import com.copsdbtest.dto.ProductDto;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

@Slf4j
public class ProductData {

    private Faker faker = new Faker();

    @Description("generate product data")
    public ProductDto createProductData() {
        log.info("ProductData::createProductData");
        ProductDto productDto = new ProductDto();
        productDto.setProductId(faker.number().numberBetween(10000, 99999));
        productDto.setProductName(faker.commerce().productName());
        productDto.setSupplierId(faker.number().numberBetween(1, 999));
        productDto.setCategoryId(faker.number().numberBetween(1, 999));
        productDto.setUnit(faker.commerce().material());
        productDto.setPrice(Double.parseDouble(faker.commerce().price()));
        return productDto;
    }
}
