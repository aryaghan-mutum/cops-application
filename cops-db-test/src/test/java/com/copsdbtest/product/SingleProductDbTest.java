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

import java.util.List;

@Slf4j
public class SingleProductDbTest {
    private ProductDbService productDbService;
    private ProductDto productDto;

    private List<ProductDto> productList;
    private List<ProductDto> actualProductList;


    @BeforeEach
    public void setup() {
        log.info("MultipleProductDbTest::setup");
        productDto = new ProductData().createProductData();
        productDbService = new ProductDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("MultipleProductDbTest::deleteRecords");

        // delete a product
        productDbService.deleteProduct(productList);

        // validate if the product is not present in db after deleted
        actualProductList = productDbService.getProductByProductId(productDto.getProductId());
        Assertions.assertTrue(actualProductList.isEmpty());
    }

    @Test
    @Description("")
    public void testOneProduct() {
        log.info("MultipleProductDbTest::testProduct");

        // insert a product
        productList = productDbService.insertProduct(productDto);

        // get the product from the db
        actualProductList = productDbService.getProductByProductId(productDto.getProductId());

        // validate
        Assertions.assertEquals(productList.size(), actualProductList.size(), "expected and actual product size do not match");
        Assertions.assertFalse(actualProductList.isEmpty(), "inserted products mustn't be empty");
        Assertions.assertEquals(productDto.getProductId(), actualProductList.get(0).getProductId());
        Assertions.assertEquals(productDto.getProductName(), actualProductList.get(0).getProductName());
        Assertions.assertEquals(productDto.getSupplierId(), actualProductList.get(0).getSupplierId());
        Assertions.assertEquals(productDto.getCategoryId(), actualProductList.get(0).getCategoryId());
        Assertions.assertEquals(productDto.getUnit(), actualProductList.get(0).getUnit());
        Assertions.assertEquals(productDto.getPrice(), actualProductList.get(0).getPrice());
    }
}
