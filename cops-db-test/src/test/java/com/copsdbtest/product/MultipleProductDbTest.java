package com.copsdbtest.product;

import com.copsdbtest.data.ProductData;
import com.copsdbtest.dbservice.ProductDbService;
import com.copsdbtest.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class MultipleProductDbTest {
    private ProductDbService productDbService;
    private List<ProductDto> productDtoList;
    private List<ProductDto> actualProductList;

    @BeforeEach
    public void setup() {
        log.info("MultipleProductDbTest::setup");
        productDbService = new ProductDbService();
        productDtoList = new ArrayList<>();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("MultipleProductDbTest::deleteRecords");

        // delete a product
        productDbService.deleteProduct(productDtoList);
    }

    @Test
    @Description("test ten products")
    public void testTenProducts() {
        log.info("MultipleProductDbTest::testMultipleProducts");

        // insert multiple products
        ProductData productData = new ProductData();
        final int productsCount = 10;

        IntStream.rangeClosed(1, productsCount)
                .mapToObj(product -> productData.createProductData())
                .forEach(productDto -> {
                    productDtoList.add(productDbService.insertProduct(productDto).get(0));

                    // get the product from the db and validate
                    actualProductList = productDbService.getProductByProductId(productDto.getProductId());

                    // validate
                    Assertions.assertFalse(actualProductList.isEmpty(), "inserted products mustn't be empty");
                    Assertions.assertEquals(productDto.getProductId(), actualProductList.get(0).getProductId());
                    Assertions.assertEquals(productDto.getProductName(), actualProductList.get(0).getProductName());
                    Assertions.assertEquals(productDto.getSupplierId(), actualProductList.get(0).getSupplierId());
                    Assertions.assertEquals(productDto.getCategoryId(), actualProductList.get(0).getCategoryId());
                    Assertions.assertEquals(productDto.getUnit(), actualProductList.get(0).getUnit());
                    Assertions.assertEquals(productDto.getPrice(), actualProductList.get(0).getPrice());
                });
    }
}
