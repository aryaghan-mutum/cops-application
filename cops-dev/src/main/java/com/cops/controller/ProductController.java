package com.cops.controller;

import com.cops.db.ProductDB;
import com.cops.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDB productDB;

    @GetMapping("/getProductsList")
    public List<ProductModel> getProductsList() {
        log.info("ProductController::getProductsList");
        return productDB.findAll();
    }

    @PostMapping("/addProduct")
    public ProductModel addProduct(@RequestBody ProductModel productModel) {
        log.info("ProductController::addProduct");
        return productDB.save(productModel);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestBody ProductModel productModel) {
        log.info("ProductController::deleteProduct");
        productDB.delete(productModel);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductModel productModel) {
        log.info("ProductController::updateProduct");
        productDB.save(productModel);
    }
}
